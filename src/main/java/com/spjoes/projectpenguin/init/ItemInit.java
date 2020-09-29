package com.spjoes.projectpenguin.init;

import com.spjoes.projectpenguin.Main;
import com.spjoes.projectpenguin.objects.armour.ArmourBase;
import com.spjoes.projectpenguin.objects.items.*;
import com.spjoes.projectpenguin.objects.items.food.ItemCustomFood;
import com.spjoes.projectpenguin.objects.items.tools.ToolSwordBase;
import com.spjoes.projectpenguin.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

public class ItemInit {

    public static final List<Item> ITEMS = new ArrayList<Item>();

    //Tool Materials
    public static final Item.ToolMaterial PENGUIN_TOOL = EnumHelper.addToolMaterial("penguin_tool", 2, 250, 1.5f, 1.5f, 10);
    public static final ItemArmor.ArmorMaterial ARMOUR_FUR = EnumHelper.addArmorMaterial("armour_fur", Reference.MODID + ":fur", 3, new int[]{1, 3, 1, 2}, 17, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);


    //Generic Items
    public static final Item PENGUIN_FUR = new ItemBase("penguin_fur", Main.PPTAB);
    public static final Item TAB_ICON = new ItemTab("tab_icon");
    public static final Item HALF_PENGUIN_EGG_TOP = new ItemBase("half_penguin_egg", Main.PPTAB);
    public static final Item HALF_PENGUIN_EGG_BOTTOM = new ItemBase("half_penguin_egg_bottom", Main.PPTAB);


    //Food
    public static final Item PENGUIN_MEAT = new ItemCustomFood("penguin_meat", 2, false);
    public static final Item COOKED_PENGUIN_MEAT = new ItemCustomFood("cooked_penguin_meat", 6, true);
    public static final Item OMLET = new ItemCustomFood("omlet", 4, false);
    public static final Item PENGUIN_HAMBURGER = new ItemCustomFood("penguin_hamburger", 9, false);


    //tools
    public static final Item PENGUIN_SWORD = new ToolSwordBase("penguin_sword", PENGUIN_TOOL, Main.PPTAB);

    //Armor
    public static final Item HELMET_FUR = new ArmourBase("helmet_fur", ARMOUR_FUR, 1, EntityEquipmentSlot.HEAD);
    public static final Item CHESTPLATE_FUR = new ArmourBase("chestplate_fur", ARMOUR_FUR,1, EntityEquipmentSlot.CHEST);
    public static final Item LEGGINGS_FUR = new ArmourBase("leggings_fur", ARMOUR_FUR,2, EntityEquipmentSlot.LEGS);
    public static final Item BOOTS_FUR = new ArmourBase("boots_fur", ARMOUR_FUR, 1, EntityEquipmentSlot.FEET);
}
