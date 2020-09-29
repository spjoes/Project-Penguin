package com.spjoes.projectpenguin.init;

import com.spjoes.projectpenguin.Main;
import com.spjoes.projectpenguin.entity.EntityPenguin;
import com.spjoes.projectpenguin.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import static com.spjoes.projectpenguin.util.Reference.MODID;

public class EntityInit
{
    public static void registerEntities() {
        registerEntity("penguin", EntityPenguin.class, Reference.ENTITY_PENGUIN, 50, 16513532, 16488035);
        addSpawns();
    }

    private static void addSpawns() {
        EntityRegistry.addSpawn(EntityPenguin.class, 50, 2, 4, EnumCreatureType.CREATURE, Biome.getBiome(11), Biome.getBiome(10), Biome.getBiome(26), Biome.getBiome(30), Biome.getBiome(31), Biome.getBiome(158), Biome.getBiome(12), Biome.getBiome(13), Biome.getBiome(140));
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(MODID + ":" + name), entity, name, id, Main.instance, range, 1, true, color1, color2);
    }


}

