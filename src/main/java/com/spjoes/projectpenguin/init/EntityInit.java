package com.spjoes.projectpenguin.init;

import com.spjoes.projectpenguin.Main;
import com.spjoes.projectpenguin.entity.*;
import com.spjoes.projectpenguin.util.Reference;
import net.minecraft.entity.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import static com.spjoes.projectpenguin.util.Reference.MODID;

public class EntityInit
{
    public static void registerEntities() {
        registerEntity("penguin", EntityPenguin.class, Reference.ENTITY_PENGUIN, 50, 16513532, 16488035);


        // uncomment these to enable Forest Penguins and Bone Bugs (Stick Bugs)

//         registerEntity("stick_bug", EntityStickBug.class, Reference.ENTITY_STICK_BUG, 50, 16777215, 15266301);
//         registerEntity("forest_penguin", EntityForestPenguin.class, Reference.ENTITY_FOREST_PENGUIN, 50, 2273300, 2253332);
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

