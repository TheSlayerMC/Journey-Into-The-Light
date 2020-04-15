package net.journey.client.render.model.mob.overworld.desert;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSpyclops extends ModelBase {

	private ModelRenderer LowerBody;
	private ModelRenderer BackLeftThigh;
	private ModelRenderer BackLeftLeg;
	private ModelRenderer FrontLeftThigh;
	private ModelRenderer FrontLeftLeg;
	private ModelRenderer BackRightThigh;
	private ModelRenderer BackRightLeg;
	private ModelRenderer FrontRightThigh;
	private ModelRenderer FrontRightLeg;
	private ModelRenderer UpperBody;
	private ModelRenderer LeftShoulder;
	private ModelRenderer RightShoulder;
	private ModelRenderer LeftElbow;
	private ModelRenderer RightElbow;
	private ModelRenderer LeftArm;
	private ModelRenderer RightArm;
	private ModelRenderer Head;

	public ModelSpyclops() {
		textureWidth = 128;
		textureHeight = 64;

		LowerBody = new ModelRenderer(this, 0, 27);
		LowerBody.addBox(-4.533333F, 0F, -7F, 9, 5, 14);
		LowerBody.setRotationPoint(-1F, 11F, 0F);
		LowerBody.setTextureSize(128, 64);
		LowerBody.mirror = true;
		setRotation(LowerBody, 0F, 0F, 0F);
		BackLeftThigh = new ModelRenderer(this, 46, 36);
		BackLeftThigh.addBox(0F, -1F, -1F, 8, 2, 2);
		BackLeftThigh.setRotationPoint(3.5F, 14F, 4F);
		BackLeftThigh.setTextureSize(128, 64);
		BackLeftThigh.mirror = true;
		setRotation(BackLeftThigh, 0F, 0F, 0F);
		BackLeftLeg = new ModelRenderer(this, 46, 40);
		BackLeftLeg.addBox(-0.5F, -1F, -1F, 8, 2, 2);
		BackLeftLeg.setRotationPoint(10.5F, 15.5F, 4F);
		BackLeftLeg.setTextureSize(128, 64);
		BackLeftLeg.mirror = true;
		setRotation(BackLeftLeg, 0F, 0F, 1.570796F);
		FrontLeftThigh = new ModelRenderer(this, 46, 36);
		FrontLeftThigh.addBox(0F, -1F, -1F, 8, 2, 2);
		FrontLeftThigh.setRotationPoint(3.5F, 14F, -3F);
		FrontLeftThigh.setTextureSize(128, 64);
		FrontLeftThigh.mirror = true;
		setRotation(FrontLeftThigh, 0F, 0F, 0F);
		FrontLeftLeg = new ModelRenderer(this, 46, 40);
		FrontLeftLeg.addBox(-0.5F, -1F, -1F, 8, 2, 2);
		FrontLeftLeg.setRotationPoint(10.5F, 15.5F, -3F);
		FrontLeftLeg.setTextureSize(128, 64);
		FrontLeftLeg.mirror = true;
		setRotation(FrontLeftLeg, 0F, 0F, 1.570796F);
		BackRightThigh = new ModelRenderer(this, 46, 36);
		BackRightThigh.addBox(-8F, -1F, -1F, 8, 2, 2);
		BackRightThigh.setRotationPoint(-5.5F, 14F, 4F);
		BackRightThigh.setTextureSize(128, 64);
		BackRightThigh.mirror = true;
		setRotation(BackRightThigh, 0F, 0F, 0F);
		BackRightLeg = new ModelRenderer(this, 46, 40);
		BackRightLeg.addBox(-0.5F, -1F, -1F, 8, 2, 2);
		BackRightLeg.setRotationPoint(-12.5F, 15.5F, 4F);
		BackRightLeg.setTextureSize(128, 64);
		BackRightLeg.mirror = true;
		setRotation(BackRightLeg, 0F, 0F, 1.570796F);
		FrontRightThigh = new ModelRenderer(this, 46, 36);
		FrontRightThigh.addBox(-8F, -1F, -1F, 8, 2, 2);
		FrontRightThigh.setRotationPoint(-5.5F, 14F, -3F);
		FrontRightThigh.setTextureSize(128, 64);
		FrontRightThigh.mirror = true;
		setRotation(FrontRightThigh, 0F, 0F, 0F);
		FrontRightLeg = new ModelRenderer(this, 46, 40);
		FrontRightLeg.addBox(-0.5F, -1F, -1F, 8, 2, 2);
		FrontRightLeg.setRotationPoint(-12.5F, 15.5F, -3F);
		FrontRightLeg.setTextureSize(128, 64);
		FrontRightLeg.mirror = true;
		setRotation(FrontRightLeg, 0F, 0F, 1.570796F);
		UpperBody = new ModelRenderer(this, 0, 10);
		UpperBody.addBox(-3.5F, -11F, -3F, 7, 11, 6);
		UpperBody.setRotationPoint(-1F, 11F, 0F);
		UpperBody.setTextureSize(128, 64);
		UpperBody.mirror = true;
		setRotation(UpperBody, 0F, 0F, 0F);
		LeftShoulder = new ModelRenderer(this, 20, 0);
		LeftShoulder.addBox(-1F, -3.5F, -2F, 4, 4, 4);
		LeftShoulder.setRotationPoint(3F, 2F, 0F);
		LeftShoulder.setTextureSize(128, 64);
		LeftShoulder.mirror = true;
		setRotation(LeftShoulder, 0F, 0F, 1.570796F);
		RightShoulder = new ModelRenderer(this, 20, 0);
		RightShoulder.addBox(-3.5F, -1F, -2F, 4, 4, 4);
		RightShoulder.setRotationPoint(-5F, 2F, 0F);
		RightShoulder.setTextureSize(128, 64);
		RightShoulder.mirror = true;
		setRotation(RightShoulder, 0F, 0F, 0F);
		LeftElbow = new ModelRenderer(this, 36, 0);
		LeftElbow.addBox(-1.5F, 0F, -1.5F, 3, 4, 3);
		LeftElbow.setRotationPoint(5F, 5F, 0F);
		LeftElbow.setTextureSize(128, 64);
		LeftElbow.mirror = true;
		setRotation(LeftElbow, 0F, 0F, 0F);
		RightElbow = new ModelRenderer(this, 36, 0);
		RightElbow.addBox(-1.5F, 0F, -1.5F, 3, 4, 3);
		RightElbow.setRotationPoint(-7F, 5F, 0F);
		RightElbow.setTextureSize(128, 64);
		RightElbow.mirror = true;
		setRotation(RightElbow, 0F, 0F, 0F);
		LeftArm = new ModelRenderer(this, 36, 7);
		LeftArm.addBox(-1.5F, 0F, -1.5F, 3, 9, 3);
		LeftArm.setRotationPoint(5F, 7.5F, -1.5F);
		LeftArm.setTextureSize(128, 64);
		LeftArm.mirror = true;
		setRotation(LeftArm, -1.570796F, 0F, 0F);
		RightArm = new ModelRenderer(this, 36, 7);
		RightArm.addBox(-1.5F, 0F, -1.5F, 3, 9, 3);
		RightArm.setRotationPoint(-7F, 7.5F, -1.5F);
		RightArm.setTextureSize(128, 64);
		RightArm.mirror = true;
		setRotation(RightArm, -1.570796F, 0F, 0F);
		Head = new ModelRenderer(this, 0, 0);
		Head.addBox(-2.5F, -5F, -2.5F, 5, 5, 5);
		Head.setRotationPoint(-1F, 0F, 0F);
		Head.setTextureSize(128, 64);
		Head.mirror = true;
		setRotation(Head, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		LowerBody.render(f5);
		BackLeftThigh.render(f5);
		BackLeftLeg.render(f5);
		FrontLeftThigh.render(f5);
		FrontLeftLeg.render(f5);
		BackRightThigh.render(f5);
		BackRightLeg.render(f5);
		FrontRightThigh.render(f5);
		FrontRightLeg.render(f5);
		UpperBody.render(f5);
		LeftShoulder.render(f5);
		RightShoulder.render(f5);
		LeftElbow.render(f5);
		RightElbow.render(f5);
		LeftArm.render(f5);
		RightArm.render(f5);
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
        this.RightShoulder.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
        this.LeftShoulder.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.RightElbow.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
        this.LeftElbow.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.RightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F + -1.5F;
        this.LeftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F + -1.5F;
        
        float f9 = -(MathHelper.cos(par1 * 0.9662F * 1.0F + 0.0F) * 0.5F) * par2;
        this.FrontRightLeg.rotateAngleY = f9;
        this.FrontLeftLeg.rotateAngleY = f9;
        this.BackRightLeg.rotateAngleY = -f9;
        this.BackLeftLeg.rotateAngleY = -f9;
        this.FrontRightThigh.rotateAngleY = -f9;
        this.FrontLeftThigh.rotateAngleY = -f9;
        this.BackRightThigh.rotateAngleY = f9;
        this.BackLeftThigh.rotateAngleY = f9;
	}
}