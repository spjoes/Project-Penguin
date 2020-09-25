package com.spjoes.projectpenguin.objects.items.food;

import com.spjoes.projectpenguin.Main;
import com.spjoes.projectpenguin.init.ItemInit;
import com.spjoes.projectpenguin.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class ItemCustomFood extends ItemFood implements IHasModel
{

    public ItemCustomFood(String name, int amount, boolean isWolfFood) {

        super(amount, isWolfFood);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.FOOD);

        ItemInit.ITEMS.add(this);

    }

    @Override
    public void registerModels() {
        // Main.proxy.registerItemRenderer(this, 0, "inventory")
        Main.proxy.registerModel(this, 0);
    }
}
