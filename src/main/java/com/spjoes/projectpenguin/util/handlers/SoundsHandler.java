package com.spjoes.projectpenguin.util.handlers;

import com.spjoes.projectpenguin.util.Reference;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler {

    public static SoundEvent ENTITY_PENGUIN_AMBIENT, ENTITY_PENGUIN_HURT, ENTITY_PENGUIN_DEATH;

    public static void registerSounds() {
        ENTITY_PENGUIN_AMBIENT = registerSound("entity.penguin.ambient");
        ENTITY_PENGUIN_HURT = registerSound("entity.penguin.hurt");
        ENTITY_PENGUIN_DEATH = registerSound("entity.penguin.death");
    }

    private static SoundEvent registerSound(String name) {
        ResourceLocation location = new ResourceLocation(Reference.MODID, name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }

}
