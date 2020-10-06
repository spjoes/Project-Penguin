package com.spjoes.projectpenguin.creativetabs;

import com.spjoes.projectpenguin.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class PPTab extends CreativeTabs
{
    public PPTab()
    {
        super("pptab");
    }

    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(ItemInit.TAB_ICON);
    }
}
