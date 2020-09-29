package com.spjoes.projectpenguin.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SmeltingInit {

    public static void init() {
        GameRegistry.addSmelting(BlockInit.PENGUIN_EGG, new ItemStack(ItemInit.OMLET, 1), 1.5f);
        GameRegistry.addSmelting(ItemInit.PENGUIN_MEAT, new ItemStack(ItemInit.COOKED_PENGUIN_MEAT, 1), 1.5f);

    }
}
