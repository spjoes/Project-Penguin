package com.spjoes.projectpenguin.objects.items.food;

import com.spjoes.projectpenguin.Main;
import com.spjoes.projectpenguin.init.ItemInit;
import com.spjoes.projectpenguin.util.IHasModel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemAppleSmoothieDrinkableFood extends ItemFood implements IHasModel
{

    public ItemAppleSmoothieDrinkableFood(String name, int amount, boolean isWolfFood) {

        super(amount, isWolfFood);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.PPTAB);
        ItemInit.ITEMS.add(this);

    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player){
        if (!worldIn.isRemote){
            //Effect1
            player.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 1000, 0));
            //Effect2
            player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 1000, 1));
            //Effect3
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1000, 1));
        }
    }

    @Override
    public void registerModels() {
        // Main.proxy.registerItemRenderer(this, 0, "inventory")
        Main.proxy.registerModel(this, 0);
    }
}
