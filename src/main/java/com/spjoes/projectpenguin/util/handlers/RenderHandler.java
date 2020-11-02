package com.spjoes.projectpenguin.util.handlers;

import com.spjoes.projectpenguin.entity.*;
import com.spjoes.projectpenguin.entity.render.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraftforge.fml.client.registry.*;

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

        RenderingRegistry.registerEntityRenderingHandler(EntityForestPenguin.class, new IRenderFactory<EntityForestPenguin>()
        {
            @Override
            public Render<? super EntityForestPenguin> createRenderFor(RenderManager manager)
            {
                return new RenderForestPenguin(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityStickBug.class, new IRenderFactory<EntityStickBug>()
        {
            @Override
            public Render<? super EntityStickBug> createRenderFor(RenderManager manager)
            {
                return new RenderStickBug(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityEnergizedPenguin.class, new IRenderFactory<EntityEnergizedPenguin>()
        {
            @Override
            public Render<? super EntityEnergizedPenguin> createRenderFor(RenderManager manager)
            {
                return new RenderEnergizedPenguin(manager);
            }
        });


        RenderingRegistry.registerEntityRenderingHandler(EntityEvilPenguin.class, new IRenderFactory<EntityEvilPenguin>()
        {
            @Override
            public Render<? super EntityEvilPenguin> createRenderFor(RenderManager manager)
            {
                return new RenderEvilPenguin(manager);
            }
        });
    }




}