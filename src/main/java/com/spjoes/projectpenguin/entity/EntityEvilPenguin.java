package com.spjoes.projectpenguin.entity;


import com.spjoes.projectpenguin.util.handlers.LootTableHandler;
import com.spjoes.projectpenguin.util.handlers.SoundsHandler;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.UUID;

public class EntityEvilPenguin extends EntityPolarBear {


    private int inLove;
    private UUID playerInLove;


    public EntityEvilPenguin(World worldIn) {
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

    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.getGrowingAge() != 0) {
            this.inLove = 0;
        }

        if (this.inLove > 0) {
            --this.inLove;

            if (this.inLove % 10 == 0) {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.world.spawnParticle(EnumParticleTypes.HEART, this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + 0.5D + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1, d2);
            }
        }
    }

    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.inLove = compound.getInteger("InLove");
        this.playerInLove = compound.hasUniqueId("LoveCause") ? compound.getUniqueId("LoveCause") : null;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12.0D);


        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
    }


    @Override
    protected void initEntityAI(){
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.tasks.addTask(7, new EntityAIWander(this, 0.6D));
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
    public EntityPolarBear createChild(EntityAgeable ageable) {
        return new EntityEvilPenguin(this.world);
    }

    @Override
    protected ResourceLocation getLootTable() {
        return LootTableHandler.EVIL_PENGUIN;
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
