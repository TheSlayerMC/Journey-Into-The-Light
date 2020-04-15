package net.journey.client.render.model.mob.euca;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelEucaFighter extends ModelBase {

	ModelRenderer Body;
	ModelRenderer LeftArm;
	ModelRenderer LeftHand;
	ModelRenderer LeftHandBracelet;
	ModelRenderer RightArm;
	ModelRenderer RightHand;
	ModelRenderer RightHandBracelet;
	ModelRenderer Stomach;
	ModelRenderer RightLeg;
	ModelRenderer LeftLeg;
	ModelRenderer Head;

	public ModelEucaFighter() {
		textureWidth = 64;
		textureHeight = 32;

		Body = new ModelRenderer(this, 0, 0);
		Body.addBox(-4F, 0F, -3F, 8, 6, 6);
		Body.setRotationPoint(0F, 4F, 0F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		LeftArm = new ModelRenderer(this, 0, 12);
		LeftArm.addBox(0F, -1F, -1F, 2, 9, 2);
		LeftArm.setRotationPoint(4F, 5F, 0F);
		LeftArm.setTextureSize(64, 32);
		LeftArm.mirror = true;
		setRotation(LeftArm, 0F, 0F, -0.0523599F);
		LeftHand = new ModelRenderer(this, 8, 12);
		LeftHand.addBox(-1F, -1F, -1F, 2, 9, 2);
		LeftHand.setRotationPoint(5.4F, 12.5F, -0.5F);
		LeftHand.setTextureSize(64, 32);
		LeftHand.mirror = true;
		setRotation(LeftHand, -0.7853982F, 0F, -0.0349066F);
		LeftHandBracelet = new ModelRenderer(this, 28, 0);
		LeftHandBracelet.addBox(-2F, 2F, -2F, 4, 4, 4);
		LeftHandBracelet.setRotationPoint(5.5F, 13F, -0.5F);
		LeftHandBracelet.setTextureSize(64, 32);
		LeftHandBracelet.mirror = true;
		setRotation(LeftHandBracelet, -0.7853982F, 0F, 0F);
		RightArm = new ModelRenderer(this, 0, 12);
		RightArm.addBox(-2F, -1F, -1F, 2, 9, 2);
		RightArm.setRotationPoint(-4F, 5F, 0F);
		RightArm.setTextureSize(64, 32);
		RightArm.mirror = true;
		setRotation(RightArm, 0F, 0F, 0.0349066F);
		RightHand = new ModelRenderer(this, 8, 12);
		RightHand.addBox(-1F, -1F, -1F, 2, 9, 2);
		RightHand.setRotationPoint(-5.3F, 12.5F, -0.5F);
		RightHand.setTextureSize(64, 32);
		RightHand.mirror = true;
		setRotation(RightHand, -0.7853982F, 0F, 0.0349066F);
		RightHandBracelet = new ModelRenderer(this, 28, 0);
		RightHandBracelet.addBox(-2F, 2F, -2F, 4, 4, 4);
		RightHandBracelet.setRotationPoint(-5.5F, 13F, -0.5F);
		RightHandBracelet.setTextureSize(64, 32);
		RightHandBracelet.mirror = true;
		setRotation(RightHandBracelet, -0.7853982F, 0F, 0F);
		Stomach = new ModelRenderer(this, 44, 0);
		Stomach.addBox(-2F, 0F, -2F, 4, 5, 4);
		Stomach.setRotationPoint(0F, 10F, 0F);
		Stomach.setTextureSize(64, 32);
		Stomach.mirror = true;
		setRotation(Stomach, 0F, 0F, 0F);
		RightLeg = new ModelRenderer(this, 16, 12);
		RightLeg.addBox(-1F, 0F, -1F, 2, 9, 2);
		RightLeg.setRotationPoint(-1F, 15F, 0F);
		RightLeg.setTextureSize(64, 32);
		RightLeg.mirror = true;
		setRotation(RightLeg, 0F, 0F, 0F);
		LeftLeg = new ModelRenderer(this, 16, 12);
		LeftLeg.addBox(-1F, 0F, -1F, 2, 9, 2);
		LeftLeg.setRotationPoint(1F, 15F, 0F);
		LeftLeg.setTextureSize(64, 32);
		LeftLeg.mirror = true;
		setRotation(LeftLeg, 0F, 0F, 0F);
		Head = new ModelRenderer(this, 24, 12);
		Head.addBox(-2.5F, -5F, -2.5F, 5, 5, 5);
		Head.setRotationPoint(0F, 4F, 0F);
		Head.setTextureSize(64, 32);
		Head.mirror = true;
		setRotation(Head, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Body.render(f5);
		LeftArm.render(f5);
		LeftHand.render(f5);
		LeftHandBracelet.render(f5);
		RightArm.render(f5);
		RightHand.render(f5);
		RightHandBracelet.render(f5);
		Stomach.render(f5);
		RightLeg.render(f5);
		LeftLeg.render(f5);
		Head.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		this.Head.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.Head.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.RightHand.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F + -7.0F;
		this.LeftHand.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F + -7.0F;
		this.RightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 0.3F * par2 * 0.5F;
		this.LeftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 0.3F * par2 * 0.4F;
		this.RightHandBracelet.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F + -7.0F;
		this.LeftHandBracelet.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F + -7.0F;
		this.RightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.LeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
	}
}