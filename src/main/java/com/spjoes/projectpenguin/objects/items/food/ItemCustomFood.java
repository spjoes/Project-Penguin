package com.spjoes.projectpenguin.objects.items.food;

import com.spjoes.projectpenguin.Main;
import com.spjoes.projectpenguin.init.ItemInit;
import com.spjoes.projectpenguin.util.IHasModel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemCustomFood extends ItemFood implements IHasModel
{

    public ItemCustomFood(String name, int amount, boolean isWolfFood) {

        super(amount, isWolfFood);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.PPTAB);
        ItemInit.ITEMS.add(this);

    }

    @Override
    public void registerModels() {
        // Main.proxy.registerItemRenderer(this, 0, "inventory")
        Main.proxy.registerModel(this, 0);
    }
}
