package com.spjoes.projectpenguin;

import com.spjoes.projectpenguin.init.ItemInit;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.*;

import java.util.Random;

public class Trades implements EntityVillager.ITradeList
{
    @Override
    public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
    {
        recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD,5),new ItemStack(ItemInit.STRAWBERRY,1,0)));
    }
}
