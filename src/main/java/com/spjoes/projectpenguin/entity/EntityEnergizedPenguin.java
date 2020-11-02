package com.spjoes.projectpenguin.entity;


import com.google.common.collect.Sets;
import com.spjoes.projectpenguin.init.BlockInit;
import com.spjoes.projectpenguin.util.handlers.LootTableHandler;
import com.spjoes.projectpenguin.util.handlers.SoundsHandler;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.UUID;

public class EntityEnergizedPenguin extends EntityPolarBear {


    private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Items.FISH);
    private int inLove;
    private UUID playerInLove;



    public EntityEnergizedPenguin(World worldIn) {
        super(worldIn);
        this.setSize(0.9F, 1.2F);
        this.isImmuneToFire = true;
        this.setPathPriority(PathNodeType.WATER, -1.0F);
        // this.DiesInWater(this);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
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
        this.tasks.addTask(0, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.25D));
        this.tasks.addTask(3, new EntityAITempt(this, 0.8D, Items.FISH, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 0.9D));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);

        if (!this.world.isRemote && !this.isDead)
        {
            EntityLightningBolt entityLightningBolt = new EntityLightningBolt(this.world, this.posY, this.posZ, this.posX, false);
            entityLightningBolt.hurtResistantTime = 35;
            entityLightningBolt.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);

            if (this.hasCustomName())
            {
                entityLightningBolt.setCustomNameTag(this.getCustomNameTag());
                entityLightningBolt.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
            }

            this.world.addWeatherEffect(entityLightningBolt);
            this.setDead();
        }
    }


    @Override
    public EntityPolarBear createChild(EntityAgeable ageable) {
        return new EntityEnergizedPenguin(this.world);
    }

    public float getEyeHeight()
    {
        return this.isChild() ? this.height : 1.3F;
    }

    @Override
    protected ResourceLocation getLootTable() {
        return LootTableHandler.ENERGIZED_PENGUIN;
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
