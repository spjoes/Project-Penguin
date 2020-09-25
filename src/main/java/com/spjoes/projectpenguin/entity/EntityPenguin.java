package com.spjoes.projectpenguin.entity;


import com.google.common.collect.Sets;
import com.spjoes.projectpenguin.util.handlers.LootTableHandler;
import com.spjoes.projectpenguin.util.handlers.SoundsHandler;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import java.util.Set;

public class EntityPenguin extends EntityPolarBear {

    private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Items.FISH);


    public EntityPenguin(World worldIn) {
        super(worldIn);
    }


    @Override
    public boolean isBreedingItem(ItemStack stack)
    {
        return TEMPTATION_ITEMS.contains(stack.getItem());
    }


    @Override
    protected void initEntityAI(){
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Items.FISH, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }

    @Override
    public EntityPolarBear createChild(EntityAgeable ageable) {
        return new EntityPenguin(world);
    }

    @Override
    protected ResourceLocation getLootTable() {
        return LootTableHandler.PENGUIN;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsHandler.ENTITY_PENGUIN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsHandler.ENTITY_PENGUIN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundsHandler.ENTITY_PENGUIN_DEATH;
    }
}
