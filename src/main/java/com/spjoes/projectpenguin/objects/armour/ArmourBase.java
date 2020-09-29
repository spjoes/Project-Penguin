package com.spjoes.projectpenguin.objects.armour;

import com.spjoes.projectpenguin.Main;
import com.spjoes.projectpenguin.init.ItemInit;
import com.spjoes.projectpenguin.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

public class ArmourBase extends ItemArmor implements IHasModel {

    public ArmourBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn)
    {
        super(materialIn, renderIndexIn, equipmentSlotIn);

        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.PPTAB);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerModel(this, 0);
    }
}