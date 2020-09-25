package com.spjoes.projectpenguin.init;

import com.spjoes.projectpenguin.objects.items.ItemBase;
import com.spjoes.projectpenguin.objects.items.food.ItemCustomFood;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemInit {

    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item OMLET = new ItemBase("omlet", CreativeTabs.MISC);
    public static final Item PENGUIN_FUR = new ItemBase("penguin_fur", CreativeTabs.MISC);



    //Food
    // public static final Item PENGUIN_MEAT = new ItemBase("penguin_meat", CreativeTabs.MISC);
    public static final Item PENGUIN_MEAT = new ItemCustomFood("penguin_meat", 2, true);

}
