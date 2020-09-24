package com.spjoes.projectpenguin.util.handlers;

import com.spjoes.projectpenguin.entity.EntityPenguin;
import com.spjoes.projectpenguin.entity.render.RenderPenguin;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {

    public static void registerEntityRenders()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityPenguin.class, new IRenderFactory<EntityPenguin>()
        {
            @Override
            public Render<? super EntityPenguin> createRenderFor(RenderManager manager)
            {
                return new RenderPenguin(manager);
            }
        });
    }




}