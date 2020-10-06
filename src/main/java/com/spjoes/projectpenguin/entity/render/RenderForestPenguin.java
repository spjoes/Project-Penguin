package com.spjoes.projectpenguin.entity.render;

import com.spjoes.projectpenguin.entity.EntityForestPenguin;
import com.spjoes.projectpenguin.entity.EntityPenguin;
import com.spjoes.projectpenguin.entity.model.ModelPenguin;
import com.spjoes.projectpenguin.util.Reference;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderForestPenguin extends RenderLiving<EntityForestPenguin> {

    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/forest_penguin.png");

    public RenderForestPenguin(RenderManager manager){
        super(manager, new ModelPenguin(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityForestPenguin entity) {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityForestPenguin entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);

    }


    @Override
    protected void preRenderCallback(EntityForestPenguin entitylivingbaseIn, float partialTickTime) {
        if (this.getMainModel().isChild) {
            GlStateManager.scale(0.6D, 0.6D, 0.6D);
        } else {
            GlStateManager.scale(1.0D, 1.0D, 1.0D);
        }
    }
}
