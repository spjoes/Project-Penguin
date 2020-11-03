package com.spjoes.projectpenguin.machinerecipies;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.*;

import com.spjoes.projectpenguin.init.*;
import net.minecraft.init.*;
import net.minecraft.item.ItemStack;

public class SmoothieMachineV2Recipes {
    private static final SmoothieMachineV2Recipes INSTANCE = new SmoothieMachineV2Recipes();
    private final Table<ItemStack, ItemStack, ItemStack> smeltingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
    private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

    public static SmoothieMachineV2Recipes getInstance()
    {
        return INSTANCE;
    }

    private SmoothieMachineV2Recipes()
    {
        addBlendingRecipe(new ItemStack(Items.IRON_NUGGET), new ItemStack(ItemInit.STRAWBERRY), new ItemStack(ItemInit.STRAWBERRY_SMOOTHIE), 5.0F);
    }


    public void addBlendingRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience)
    {
        if(getBlendingResult(input1, input2) != ItemStack.EMPTY) return;
        this.smeltingList.put(input1, input2, result);
        this.experienceList.put(result, Float.valueOf(experience));
    }

    public ItemStack getBlendingResult(ItemStack input1, ItemStack input2)
    {
        for(Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.smeltingList.columnMap().entrySet())
        {
            if(this.compareItemStacks(input1, (ItemStack)entry.getKey()))
            {
                for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet())
                {
                    if(this.compareItemStacks(input2, (ItemStack)ent.getKey()))
                    {
                        return (ItemStack)ent.getValue();
                    }
                }
            }
        }
        return ItemStack.EMPTY;
    }

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Table<ItemStack, ItemStack, ItemStack> getDualSmeltingList()
    {
        return this.smeltingList;
    }

    public float getBlendingExperience(ItemStack stack)
    {
        for (Entry<ItemStack, Float> entry : this.experienceList.entrySet())
        {
            if(this.compareItemStacks(stack, (ItemStack)entry.getKey()))
            {
                return ((Float)entry.getValue()).floatValue();
            }
        }
        return 0.0F;
    }
}
