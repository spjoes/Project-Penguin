package com.spjoes.projectpenguin.util.handlers;

import com.spjoes.projectpenguin.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootTableHandler {

    public static final ResourceLocation PENGUIN = LootTableList.register(new ResourceLocation(Reference.MODID, "penguin"));

}
