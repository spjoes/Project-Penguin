package com.spjoes.projectpenguin.objects.items;

import com.spjoes.projectpenguin.Main;
import com.spjoes.projectpenguin.init.ItemInit;
import com.spjoes.projectpenguin.util.IHasModel;
import net.minecraft.item.Item;

public class ItemTab extends Item implements IHasModel
{
    public ItemTab(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerModel(this, 0);
    }
}
