package com.spjoes.projectpenguin.util.handlers;

import com.spjoes.projectpenguin.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootTableHandler {

    public static final ResourceLocation PENGUIN = LootTableList.register(new ResourceLocation(Reference.MODID, "penguin"));
    public static final ResourceLocation ENERGIZED_PENGUIN = LootTableList.register(new ResourceLocation(Reference.MODID, "energized_penguin"));
    public static final ResourceLocation FOREST_PENGUIN = LootTableList.register(new ResourceLocation(Reference.MODID, "forest_penguin"));
    public static final ResourceLocation EVIL_PENGUIN = LootTableList.register(new ResourceLocation(Reference.MODID, "evil_penguin"));

}
