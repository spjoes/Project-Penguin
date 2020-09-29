package com.spjoes.projectpenguin.entity;


import com.google.common.collect.Sets;
import com.spjoes.projectpenguin.entity.render.RenderPenguin;
import com.spjoes.projectpenguin.init.BlockInit;
import com.spjoes.projectpenguin.init.ItemInit;
import com.spjoes.projectpenguin.util.handlers.LootTableHandler;
import com.spjoes.projectpenguin.util.handlers.SoundsHandler;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.UUID;

public class EntityPenguin extends EntityPolarBear {


    private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Items.FISH);
    private int inLove;
    private UUID playerInLove;



    public EntityPenguin(World worldIn) {
        super(worldIn);
        this.setSize(0.9F, 1.2F);
    }

    protected void updateAITasks()
    {
        if (this.getGrowingAge() != 0)
        {
            this.inLove = 0;
        }

        super.updateAITasks();
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (this.getGrowingAge() != 0)
        {
            this.inLove = 0;
        }

        if (this.inLove > 0)
        {
            --this.inLove;

            if (this.inLove % 10 == 0)
            {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.world.spawnParticle(EnumParticleTypes.HEART, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
            }
        }
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("InLove", this.inLove);

        if (this.playerInLove != null)
        {
            compound.setUniqueId("LoveCause", this.playerInLove);
        }
    }

    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.inLove = compound.getInteger("InLove");
        this.playerInLove = compound.hasUniqueId("LoveCause") ? compound.getUniqueId("LoveCause") : null;
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!itemstack.isEmpty())
        {
            if (this.isBreedingItem(itemstack) && this.getGrowingAge() == 0 && this.inLove <= 0)
            {
                this.consumeItemFromStack(player, itemstack);
                this.setInLove(player);
                return true;
            }

            if (this.isChild() && this.isBreedingItem(itemstack))
            {
                this.consumeItemFromStack(player, itemstack);
                this.ageUp((int)((float)(-this.getGrowingAge() / 20) * 0.1F), true);
                return true;
            }
        }

        return super.processInteract(player, hand);
    }

    public void setInLove(@Nullable EntityPlayer player)
    {
        this.inLove = 600;

        if (player != null)
        {
            this.playerInLove = player.getUniqueID();
        }

        this.world.setEntityState(this, (byte)18);
    }

    @Nullable
    public EntityPlayerMP getLoveCause()
    {
        if (this.playerInLove == null)
        {
            return null;
        }
        else
        {
            EntityPlayer entityplayer = this.world.getPlayerEntityByUUID(this.playerInLove);
            return entityplayer instanceof EntityPlayerMP ? (EntityPlayerMP)entityplayer : null;
        }
    }

    public boolean isInLove()
    {
        return this.inLove > 0;
    }

    public void resetInLove()
    {
        this.inLove = 0;
    }



    @Override
    public boolean isBreedingItem(ItemStack stack)
    {
        return TEMPTATION_ITEMS.contains(stack.getItem());
    }


    @Override
    protected void initEntityAI(){
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.1D, Items.FISH, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        if (!world.isRemote && !this.isInLove()) {
            if (this.rand.nextInt(12) == 0) {
                ItemStack stack = new ItemStack(BlockInit.PENGUIN_EGG);
                this.entityDropItem(stack, 0.5F);
            }
        }
    }




    @Override
    public EntityPolarBear createChild(EntityAgeable ageable) {
        return new EntityPenguin(this.world);
    }

    public float getEyeHeight()
    {
        return this.isChild() ? this.height : 1.3F;
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
