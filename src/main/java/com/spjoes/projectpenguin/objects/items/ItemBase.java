package com.spjoes.projectpenguin.objects.items;

import com.spjoes.projectpenguin.Main;
import com.spjoes.projectpenguin.init.ItemInit;
import com.spjoes.projectpenguin.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel
{
    public ItemBase(String name, CreativeTabs tab)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);

        ItemInit.ITEMS.add(this);
    }


    @Override
    public void registerModels()
    {
        Main.proxy.registerModel(this, 0);
    }
}
