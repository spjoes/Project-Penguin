package com.spjoes.projectpenguin.entity.model;

//Made with Blockbench
//Paste this code into your mod.

import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelStickBug extends ModelBase {
	private ModelRenderer Body;
	private ModelRenderer Right_Theighs;
	private ModelRenderer Left_Theighs;
	private ModelRenderer Right_Legs;
	private ModelRenderer Left_Legs;

	public ModelStickBug() {
		textureWidth = 16;
		textureHeight = 16;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body.cubeList.add(new ModelBox(Body, 0, 0, -1.0F, -4.0F, -5.0F, 1, 1, 10, 0.0F, false));

		Right_Theighs = new ModelRenderer(this);
		Right_Theighs.setRotationPoint(0.0F, 24.0F, 0.0F);
		Right_Theighs.cubeList.add(new ModelBox(Right_Theighs, 0, 0, 0.0F, -4.0F, -3.0F, 3, 1, 1, 0.0F, false));
		Right_Theighs.cubeList.add(new ModelBox(Right_Theighs, 0, 0, 0.0F, -4.0F, 3.0F, 3, 1, 1, 0.0F, false));
		Right_Theighs.cubeList.add(new ModelBox(Right_Theighs, 0, 0, 0.0F, -4.0F, 0.0F, 3, 1, 1, 0.0F, false));

		Left_Theighs = new ModelRenderer(this);
		Left_Theighs.setRotationPoint(0.0F, 24.0F, 0.0F);
		Left_Theighs.cubeList.add(new ModelBox(Left_Theighs, 0, 0, -4.0F, -4.0F, -3.0F, 3, 1, 1, 0.0F, false));
		Left_Theighs.cubeList.add(new ModelBox(Left_Theighs, 0, 0, -4.0F, -4.0F, 3.0F, 3, 1, 1, 0.0F, false));
		Left_Theighs.cubeList.add(new ModelBox(Left_Theighs, 0, 0, -4.0F, -4.0F, 0.0F, 3, 1, 1, 0.0F, false));

		Right_Legs = new ModelRenderer(this);
		Right_Legs.setRotationPoint(0.0F, 24.0F, 0.0F);
		Right_Legs.cubeList.add(new ModelBox(Right_Legs, 0, 0, 2.0F, -3.0F, -3.0F, 1, 3, 1, 0.0F, false));
		Right_Legs.cubeList.add(new ModelBox(Right_Legs, 0, 0, 2.0F, -3.0F, 0.0F, 1, 3, 1, 0.0F, false));
		Right_Legs.cubeList.add(new ModelBox(Right_Legs, 0, 0, 2.0F, -3.0F, 3.0F, 1, 3, 1, 0.0F, false));

		Left_Legs = new ModelRenderer(this);
		Left_Legs.setRotationPoint(0.0F, 24.0F, 0.0F);
		Left_Legs.cubeList.add(new ModelBox(Left_Legs, 0, 0, -4.0F, -3.0F, -3.0F, 1, 3, 1, 0.0F, false));
		Left_Legs.cubeList.add(new ModelBox(Left_Legs, 0, 0, -4.0F, -3.0F, 0.0F, 1, 3, 1, 0.0F, false));
		Left_Legs.cubeList.add(new ModelBox(Left_Legs, 0, 0, -4.0F, -3.0F, 3.0F, 1, 3, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Body.render(f5);
		Right_Theighs.render(f5);
		Left_Theighs.render(f5);
		Right_Legs.render(f5);
		Left_Legs.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		this.Left_Legs.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount;
		this.Right_Legs.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 0.3F * limbSwingAmount;
	}
}