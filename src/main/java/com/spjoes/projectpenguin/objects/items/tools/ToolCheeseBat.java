package com.spjoes.projectpenguin.objects.items.tools;

import com.spjoes.projectpenguin.Main;
import com.spjoes.projectpenguin.init.ItemInit;
import com.spjoes.projectpenguin.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class ToolCheeseBat extends ItemSword implements IHasModel
{
    public ToolCheeseBat(String name, ToolMaterial material, CreativeTabs tab)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);

        ItemInit.ITEMS.add(this);
    }

//    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
//    {
//        if (entityLiving instanceof EntityPlayer && !((EntityPlayer)entityLiving).capabilities.isCreativeMode)
//        {
//            stack.shrink(1);
//        }
//
//        return stack.isEmpty() ? new ItemStack(Items.STICK) : stack;
//    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerModel(this, 0);
    }
}
