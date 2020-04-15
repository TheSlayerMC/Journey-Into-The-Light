package net.journey.client.render.model.mob.euca;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelEucaCharger extends ModelBase {

	ModelRenderer Head;
	ModelRenderer LeftArmHolder;
	ModelRenderer RightArmHolder;
	ModelRenderer LeftArm;
	ModelRenderer RightArm;
	ModelRenderer RightLeg;
	ModelRenderer LeftLeg;

	public ModelEucaCharger() {
		textureWidth = 64;
		textureHeight = 32;

		Head = new ModelRenderer(this, 0, 0);
		Head.addBox(-2.5F, -2.5F, -2.5F, 5, 5, 5);
		Head.setRotationPoint(0F, 16F, 0F);
		Head.setTextureSize(64, 32);
		Head.mirror = true;
		setRotation(Head, 0F, 0F, 0F);
		LeftArmHolder = new ModelRenderer(this, 0, 10);
		LeftArmHolder.addBox(0F, -1F, -1F, 2, 2, 2);
		LeftArmHolder.setRotationPoint(2.5F, 15F, 0F);
		LeftArmHolder.setTextureSize(64, 32);
		LeftArmHolder.mirror = true;
		setRotation(LeftArmHolder, 0F, 0F, 0F);
		RightArmHolder = new ModelRenderer(this, 0, 10);
		RightArmHolder.addBox(-4F, -1F, -1F, 2, 2, 2);
		RightArmHolder.setRotationPoint(-0.5F, 15F, 0F);
		RightArmHolder.setTextureSize(64, 32);
		RightArmHolder.mirror = true;
		setRotation(RightArmHolder, 0F, 0F, 0F);
		LeftArm = new ModelRenderer(this, 20, 0);
		LeftArm.addBox(-1F, -2F, -1F, 2, 8, 2);
		LeftArm.setRotationPoint(5.5F, 15F, 0F);
		LeftArm.setTextureSize(64, 32);
		LeftArm.mirror = true;
		setRotation(LeftArm, 0F, 0F, 0F);
		RightArm = new ModelRenderer(this, 20, 0);
		RightArm.addBox(-1F, -2F, -1F, 2, 8, 2);
		RightArm.setRotationPoint(-5.5F, 15F, 0F);
		RightArm.setTextureSize(64, 32);
		RightArm.mirror = true;
		setRotation(RightArm, 0F, 0F, 0F);
		RightLeg = new ModelRenderer(this, 28, 0);
		RightLeg.addBox(-0.9F, 0F, -1F, 2, 6, 2);
		RightLeg.setRotationPoint(-1.5F, 18F, 0F);
		RightLeg.setTextureSize(64, 32);
		RightLeg.mirror = true;
		setRotation(RightLeg, 0F, 0F, 0F);
		LeftLeg = new ModelRenderer(this, 28, 0);
		LeftLeg.addBox(-1.1F, 0F, -1F, 2, 6, 2);
		LeftLeg.setRotationPoint(1.5F, 18F, 0F);
		LeftLeg.setTextureSize(64, 32);
		LeftLeg.mirror = true;
		setRotation(LeftLeg, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Head.render(f5);
		LeftArmHolder.render(f5);
		RightArmHolder.render(f5);
		LeftArm.render(f5);
		RightArm.render(f5);
		RightLeg.render(f5);
		LeftLeg.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		this.RightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.LeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.LeftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.RightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.LeftArmHolder.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.RightArmHolder.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	}
}