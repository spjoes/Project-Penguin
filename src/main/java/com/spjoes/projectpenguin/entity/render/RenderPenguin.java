package com.spjoes.projectpenguin.entity.render;

import com.spjoes.projectpenguin.entity.EntityPenguin;
import com.spjoes.projectpenguin.entity.model.ModelPenguin;
import com.spjoes.projectpenguin.util.Reference;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderPenguin extends RenderLiving<EntityPenguin> {

    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/penguin.png");

    public RenderPenguin(RenderManager manager){
        super(manager, new ModelPenguin(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityPenguin entity) {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityPenguin entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);

    }


    @Override
    protected void preRenderCallback(EntityPenguin entitylivingbaseIn, float partialTickTime) {
        if (this.getMainModel().isChild) {
            GlStateManager.scale(0.6D, 0.6D, 0.6D);
        } else {
            GlStateManager.scale(1.0D, 1.0D, 1.0D);
        }
    }
}
