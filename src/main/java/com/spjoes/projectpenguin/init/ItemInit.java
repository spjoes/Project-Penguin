package com.spjoes.projectpenguin.init;

import com.spjoes.projectpenguin.objects.items.ItemBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemInit {

    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item OMLET = new ItemBase("omlet", CreativeTabs.MISC);

}
