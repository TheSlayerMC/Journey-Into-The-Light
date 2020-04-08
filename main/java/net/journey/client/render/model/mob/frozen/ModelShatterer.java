package net.journey.client.render.model.mob.frozen;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

public class ModelShatterer extends ModelBase {
	ModelRenderer rightFlake;
	ModelRenderer leftFlake;
	ModelRenderer bottomFlake;
	ModelRenderer topFlake;
	ModelRenderer flakeBR;
	ModelRenderer flakeTL;
	ModelRenderer flakeTR;
	ModelRenderer flakeBL;
	ModelRenderer leftEye;
	ModelRenderer rightEye;
	ModelRenderer bottomEye;
	ModelRenderer topEye;
	ModelRenderer frontEye;
	ModelRenderer flakePiece0;
	ModelRenderer flakePiece1;

	public ModelShatterer() {
		textureWidth = 64;
		textureHeight = 64;

		rightFlake = new ModelRenderer(this, 28, 35);
		rightFlake.addBox(-3F, -2F, -2F, 16, 2, 1);
		rightFlake.setRotationPoint(-13F, 0F, 1F);
		rightFlake.setTextureSize(64, 64);
		rightFlake.mirror = true;
		setRotation(rightFlake, 0F, 0F, 0F);
		leftFlake = new ModelRenderer(this, 16, 32);
		leftFlake.addBox(0F, 0F, 0F, 16, 2, 1);
		leftFlake.setRotationPoint(0F, -2F, -1F);
		leftFlake.setTextureSize(64, 64);
		leftFlake.mirror = true;
		setRotation(leftFlake, 0F, 0F, 0F);
		bottomFlake = new ModelRenderer(this, 28, 35);
		bottomFlake.addBox(0F, 0F, 0F, 16, 2, 1);
		bottomFlake.setRotationPoint(1F, 0F, -1F);
		bottomFlake.setTextureSize(64, 64);
		bottomFlake.mirror = true;
		setRotation(bottomFlake, 0F, 0F, 1.570796F);
		topFlake = new ModelRenderer(this, 16, 32);
		topFlake.addBox(0F, 0F, 0F, 16, 2, 1);
		topFlake.setRotationPoint(1F, -18F, -1F);
		topFlake.setTextureSize(64, 64);
		topFlake.mirror = true;
		setRotation(topFlake, 0F, 0F, 1.570796F);
		flakeBR = new ModelRenderer(this, 16, 32);
		flakeBR.addBox(0F, 0F, 0F, 16, 2, 1);
		flakeBR.setRotationPoint(1F, -2F, -1F);
		flakeBR.setTextureSize(64, 64);
		flakeBR.mirror = true;
		setRotation(flakeBR, 0F, 0F, 0.7853982F);
		flakeTL = new ModelRenderer(this, 16, 32);
		flakeTL.addBox(0F, 0F, 0F, 16, 2, 1);
		flakeTL.setRotationPoint(-11F, -13F, -1F);
		flakeTL.setTextureSize(64, 64);
		flakeTL.mirror = true;
		setRotation(flakeTL, 0F, 0F, 0.7853982F);
		flakeTR = new ModelRenderer(this, 16, 32);
		flakeTR.addBox(0F, 0F, 0F, 16, 2, 1);
		flakeTR.setRotationPoint(0F, -2F, -1F);
		flakeTR.setTextureSize(64, 64);
		flakeTR.mirror = true;
		setRotation(flakeTR, 0F, 0F, -0.7853982F);
		flakeBL = new ModelRenderer(this, 16, 32);
		flakeBL.addBox(0F, 0F, 0F, 16, 2, 1);
		flakeBL.setRotationPoint(-12F, 9F, -1F);
		flakeBL.setTextureSize(64, 64);
		flakeBL.mirror = true;
		setRotation(flakeBL, 0F, 0F, -0.7853982F);
		leftEye = new ModelRenderer(this, 16, 13);
		leftEye.addBox(0F, 0F, 0F, 6, 6, 1);
		leftEye.setRotationPoint(16F, -4F, -1F);
		leftEye.setTextureSize(64, 64);
		leftEye.mirror = true;
		setRotation(leftEye, 0F, 0F, 0F);
		rightEye = new ModelRenderer(this, 16, 13);
		rightEye.addBox(0F, 0F, 0F, 6, 6, 1);
		rightEye.setRotationPoint(-22F, -4F, -1F);
		rightEye.setTextureSize(64, 64);
		rightEye.mirror = true;
		setRotation(rightEye, 0F, 0F, 0F);
		bottomEye = new ModelRenderer(this, 16, 13);
		bottomEye.addBox(0F, 0F, 0F, 6, 6, 1);
		bottomEye.setRotationPoint(-3F, 16F, -1F);
		bottomEye.setTextureSize(64, 64);
		bottomEye.mirror = true;
		setRotation(bottomEye, 0F, 0F, 0F);
		topEye = new ModelRenderer(this, 16, 13);
		topEye.addBox(0F, 0F, 0F, 6, 6, 1);
		topEye.setRotationPoint(-3F, -24F, -1F);
		topEye.setTextureSize(64, 64);
		topEye.mirror = true;
		setRotation(topEye, 0F, 0F, 0F);
		frontEye = new ModelRenderer(this, 0, 0);
		frontEye.addBox(0F, 0F, 0F, 10, 10, 3);
		frontEye.setRotationPoint(-5F, -6F, -2F);
		frontEye.setTextureSize(64, 64);
		frontEye.mirror = true;
		setRotation(frontEye, 0F, 0F, 0F);
		flakePiece0 = new ModelRenderer(this, 16, 32);
		flakePiece0.addBox(0F, 0F, 0F, 12, 2, 1);
		flakePiece0.setRotationPoint(1F, 8F, -1F);
		flakePiece0.setTextureSize(64, 64);
		flakePiece0.mirror = true;
		setRotation(flakePiece0, 0F, 0F, -0.7853982F);
		flakePiece1 = new ModelRenderer(this, 16, 32);
		flakePiece1.addBox(0F, 0F, 0F, 12, 2, 1);
		flakePiece1.setRotationPoint(-11F, -3F, -1F);
		flakePiece1.setTextureSize(64, 64);
		flakePiece1.mirror = true;
		setRotation(flakePiece1, 0F, 0F, -0.7853982F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		rightFlake.render(f5);
		leftFlake.render(f5);
		bottomFlake.render(f5);
		topFlake.render(f5);
		flakeBR.render(f5);
		flakeTL.render(f5);
		flakeTR.render(f5);
		flakeBL.render(f5);
		leftEye.render(f5);
		rightEye.render(f5);
		bottomEye.render(f5);
		topEye.render(f5);
		frontEye.render(f5);
		flakePiece0.render(f5);
		flakePiece1.render(f5);
		super.render(entity, f, f1, f2, f3, f4, f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
