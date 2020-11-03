package com.spjoes.projectpenguin.init;

import com.spjoes.projectpenguin.Trades;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class TradesInit {

    public static void init() {
        VillagerRegistry.VillagerProfession nitwit= ForgeRegistries.VILLAGER_PROFESSIONS.getValue(new ResourceLocation("minecraft:farmer"));
        nitwit.getCareer(0).addTrade(1,new Trades());
    }

}
