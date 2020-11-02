package com.spjoes.projectpenguin.entity.render;

import com.spjoes.projectpenguin.entity.*;
import com.spjoes.projectpenguin.entity.model.ModelPenguin;
import com.spjoes.projectpenguin.util.Reference;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.ResourceLocation;

public class RenderEnergizedPenguin extends RenderLiving<EntityEnergizedPenguin> {

    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/energized_penguin.png");

    public RenderEnergizedPenguin(RenderManager manager){
        super(manager, new ModelPenguin(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityEnergizedPenguin entity) {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityEnergizedPenguin entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);

    }


    @Override
    protected void preRenderCallback(EntityEnergizedPenguin entitylivingbaseIn, float partialTickTime) {
        if (this.getMainModel().isChild) {
            GlStateManager.scale(0.6D, 0.6D, 0.6D);
        } else {
            GlStateManager.scale(1.0D, 1.0D, 1.0D);
        }
    }
}
