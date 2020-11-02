package com.spjoes.projectpenguin.entity;


import com.google.common.collect.Sets;
import com.spjoes.projectpenguin.init.BlockInit;
import com.spjoes.projectpenguin.init.ItemInit;
import com.spjoes.projectpenguin.util.handlers.*;
import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.*;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.datafix.fixes.ItemIntIDToString;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.*;

public class EntityPenguin extends EntityPolarBear {


    private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Items.FISH);
    private int inLove;
    private UUID playerInLove;
    int evilPenguinChance;



    public EntityPenguin(World worldIn) {
        super(worldIn);
        this.setSize(0.9F, 1.2F);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
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


        if (Block.getBlockFromItem(player.inventory.getCurrentItem().getItem()) == BlockInit.PENGUIN_EGG) {
            if (!player.capabilities.isCreativeMode) {
                itemstack.shrink(1);
            }

            if (!this.world.isRemote) {
                EntityPenguin entityep3 = new EntityPenguin(this.world);
                entityep3.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
                //36000 ticks = 30min
                entityep3.setGrowingAge(-36000);
                entityep3.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(entityep3)), (IEntityLivingData) null);

                this.world.spawnEntity(entityep3);
            }

            return true;

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
        this.tasks.addTask(2, new EntityAIMate(this, 1.25D));
        this.tasks.addTask(3, new EntityAITempt(this, 0.8D, Items.FISH, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 0.9D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.tasks.addTask(7, new EntityAIWander(this, 0.6D));
    }

    @Override
    public void onDeath(DamageSource cause) {

        evilPenguinChance = (Math.random() <= 0.5) ? 1 : 2;

        super.onDeath(cause);
        if (!world.isRemote && !this.isInLove()) {
            if (this.rand.nextInt(12) == 0) {
                ItemStack stack = new ItemStack(BlockInit.PENGUIN_EGG);
                this.entityDropItem(stack, 0.5F);
            }
        }

        if (!this.world.isRemote && !this.isDead)
        {

            if (evilPenguinChance == 1) {

                EntityEvilPenguin entityep2 = new EntityEvilPenguin(this.world);
                entityep2.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
                entityep2.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(entityep2)), (IEntityLivingData) null);

                this.world.spawnEntity(entityep2);

            } else {

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

    @Override
    public void onStruckByLightning(EntityLightningBolt lightningBolt)
    {
        if (!this.world.isRemote && !this.isDead)
        {
            EntityEnergizedPenguin entityep = new EntityEnergizedPenguin(this.world);
            entityep.hurtResistantTime = 35;
            entityep.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            entityep.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(entityep)), (IEntityLivingData)null);
            entityep.setNoAI(this.isAIDisabled());

            if (this.hasCustomName())
            {
                entityep.setCustomNameTag(this.getCustomNameTag());
                entityep.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
            }

            this.world.spawnEntity(entityep);
            this.setDead();
        }
    }
}
