package com.spjoes.projectpenguin.objects.items.tools;

import com.spjoes.projectpenguin.Main;
import com.spjoes.projectpenguin.init.ItemInit;
import com.spjoes.projectpenguin.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class ToolSwordBase extends ItemSword implements IHasModel
{
    public ToolSwordBase(String name, ToolMaterial material, CreativeTabs tab)
    {
        super(material);
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
