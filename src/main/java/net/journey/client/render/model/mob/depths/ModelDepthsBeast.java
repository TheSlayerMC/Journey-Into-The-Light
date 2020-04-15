package net.journey.client.render.model.mob.depths;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelDepthsBeast extends ModelBase {

	private ModelRenderer Chest;
	private ModelRenderer Tummy;
	private ModelRenderer LeftLeg;
	private ModelRenderer RightLeg;
	private ModelRenderer RightShoulder;
	private ModelRenderer LeftShoulder;
	private ModelRenderer RightArm;
	private ModelRenderer LeftArm;
	private ModelRenderer RightIDK;
	private ModelRenderer LeftIDK;
	private ModelRenderer Head;

	public ModelDepthsBeast() {
		textureWidth = 128;
	    textureHeight = 64;
	    
	      Chest = new ModelRenderer(this, 0, 0);
	      Chest.addBox(-6.5F, -2F, -0.5F, 13, 7, 7);
	      Chest.setRotationPoint(0F, 1F, -3F);
	      Chest.setTextureSize(128, 64);
	      Chest.mirror = true;
	      setRotation(Chest, 0F, 0F, 0F);
	      Tummy = new ModelRenderer(this, 0, 14);
	      Tummy.addBox(-4F, 0F, -2F, 8, 6, 4);
	      Tummy.setRotationPoint(0F, 6F, 0F);
	      Tummy.setTextureSize(128, 64);
	      Tummy.mirror = true;
	      setRotation(Tummy, 0F, 0F, 0F);
	      LeftLeg = new ModelRenderer(this, 24, 14);
	      LeftLeg.addBox(-1.5F, 0F, -2F, 3, 12, 4);
	      LeftLeg.setRotationPoint(2.5F, 12F, 0F);
	      LeftLeg.setTextureSize(128, 64);
	      LeftLeg.mirror = true;
	      setRotation(LeftLeg, 0F, 0F, 0F);
	      RightLeg = new ModelRenderer(this, 24, 14);
	      RightLeg.addBox(-1.5F, 0F, -2F, 3, 12, 4);
	      RightLeg.setRotationPoint(-2.5F, 12F, 0F);
	      RightLeg.setTextureSize(128, 64);
	      RightLeg.mirror = true;
	      setRotation(RightLeg, -0.0174533F, 0F, 0F);
	      RightShoulder = new ModelRenderer(this, 40, 0);
	      RightShoulder.addBox(-5.5F, -3F, -3F, 6, 6, 6);
	      RightShoulder.setRotationPoint(-7F, 2F, 0F);
	      RightShoulder.setTextureSize(128, 64);
	      RightShoulder.mirror = true;
	      setRotation(RightShoulder, 0F, 0F, 0F);
	      LeftShoulder = new ModelRenderer(this, 40, 0);
	      LeftShoulder.addBox(-0.5F, -3F, -3F, 6, 6, 6);
	      LeftShoulder.setRotationPoint(7F, 2F, 0F);
	      LeftShoulder.setTextureSize(128, 64);
	      LeftShoulder.mirror = true;
	      setRotation(LeftShoulder, 0F, 0F, 0F);
	      RightArm = new ModelRenderer(this, 38, 14);
	      RightArm.addBox(-1.5F, -1.5F, -1.5F, 3, 13, 3);
	      RightArm.setRotationPoint(-10F, 6.5F, 0F);
	      RightArm.setTextureSize(128, 64);
	      RightArm.mirror = true;
	      setRotation(RightArm, 0F, 0F, 0F);
	      LeftArm = new ModelRenderer(this, 38, 14);
	      LeftArm.addBox(-1.5F, -1.5F, -1.5F, 3, 13, 3);
	      LeftArm.setRotationPoint(10F, 6.5F, 0F);
	      LeftArm.setTextureSize(128, 64);
	      LeftArm.mirror = true;
	      setRotation(LeftArm, 0F, 0F, 0F);
	      RightIDK = new ModelRenderer(this, 24, 30);
	      RightIDK.addBox(-2F, 5.5F, -2F, 4, 4, 4);
	      RightIDK.setRotationPoint(-10F, 6.5F, 0F);
	      RightIDK.setTextureSize(128, 64);
	      RightIDK.mirror = true;
	      setRotation(RightIDK, 0F, 0F, 0F);
	      LeftIDK = new ModelRenderer(this, 24, 30);
	      LeftIDK.addBox(-2F, 5.5F, -2F, 4, 4, 4);
	      LeftIDK.setRotationPoint(10F, 6.5F, 0F);
	      LeftIDK.setTextureSize(128, 64);
	      LeftIDK.mirror = true;
	      setRotation(LeftIDK, 0F, 0F, 0F);
	      Head = new ModelRenderer(this, 0, 24);
	      Head.addBox(-3F, -3F, -6F, 6, 6, 6);
	      Head.setRotationPoint(0F, 1F, -3.5F);
	      Head.setTextureSize(128, 64);
	      Head.mirror = true;
	      setRotation(Head, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Chest.render(f5);
		Tummy.render(f5);
		LeftLeg.render(f5);
		RightLeg.render(f5);
		RightShoulder.render(f5);
		LeftShoulder.render(f5);
		RightArm.render(f5);
		LeftArm.render(f5);
		RightIDK.render(f5);
		LeftIDK.render(f5);
		Head.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		this.RightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
		this.LeftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
		this.RightIDK.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
		this.LeftIDK.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
		this.RightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.LeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
	}
}