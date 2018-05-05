package net.journey.client.render.model.mob.boil;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelMagmaGiant extends ModelBase {
	
	private ModelRenderer Chest;
	private ModelRenderer Stomach;
	private ModelRenderer Head;
	private ModelRenderer LeftShoulder;
	private ModelRenderer RightShoulder;
	private ModelRenderer LeftLeg;
	private ModelRenderer RightLeg;
	private ModelRenderer LeftArm;
	private ModelRenderer RightArm;
	private ModelRenderer LeftSpikeBase;
	private ModelRenderer RightSpikeBase;
	private ModelRenderer LeftSpikeEnd;
	private ModelRenderer RightSpikeEnd;

	public ModelMagmaGiant() {
		textureWidth = 128;
		textureHeight = 64;
		Chest = new ModelRenderer(this, 0, 0);
		Chest.addBox(-7F, 0F, -3F, 14, 10, 6);
		Chest.setRotationPoint(0F, -8F, 3F);
		Chest.setTextureSize(128, 64);
		Chest.mirror = true;
		setRotation(Chest, 0F, 0F, 0F);
		Stomach = new ModelRenderer(this, 0, 33);
		Stomach.addBox(-3.5F, 0F, -1.5F, 7, 8, 3);
		Stomach.setRotationPoint(0F, 2F, 3F);
		Stomach.setTextureSize(128, 64);
		Stomach.mirror = true;
		setRotation(Stomach, 0F, 0F, 0F);
		Head = new ModelRenderer(this, 12, 16);
		Head.addBox(-3.5F, 0F, -3F, 7, 7, 7);
		Head.setRotationPoint(0F, -15F, 2F);
		Head.setTextureSize(128, 64);
		Head.mirror = true;
		setRotation(Head, 0F, 0F, 0F);
		LeftShoulder = new ModelRenderer(this, 40, 16);
		LeftShoulder.addBox(0F, -1F, -1.5F, 4, 10, 3);
		LeftShoulder.setRotationPoint(7F, -7F, 3F);
		LeftShoulder.setTextureSize(128, 64);
		LeftShoulder.mirror = true;
		setRotation(LeftShoulder, 0F, 0F, 0F);
		RightShoulder = new ModelRenderer(this, 40, 16);
		RightShoulder.addBox(-4F, -1F, -1.5F, 4, 10, 3);
		RightShoulder.setRotationPoint(-7F, -7F, 3F);
		RightShoulder.setTextureSize(128, 64);
		RightShoulder.mirror = true;
		setRotation(RightShoulder, 0F, 0F, 0F);
		LeftLeg = new ModelRenderer(this, 0, 16);
		LeftLeg.addBox(-1.5F, 0F, -1.5F, 3, 14, 3);
		LeftLeg.setRotationPoint(2F, 10F, 3F);
		LeftLeg.setTextureSize(128, 64);
		LeftLeg.mirror = true;
		setRotation(LeftLeg, 0F, 0F, 0F);
		RightLeg = new ModelRenderer(this, 0, 16);
		RightLeg.addBox(-1.5F, 0F, -1.5F, 3, 14, 3);
		RightLeg.setRotationPoint(-2F, 10F, 3F);
		RightLeg.setTextureSize(128, 64);
		RightLeg.mirror = true;
		setRotation(RightLeg, 0F, 0F, 0F);
		LeftArm = new ModelRenderer(this, 40, 0);
		LeftArm.addBox(-2F, 0F, -1.5F, 4, 12, 3);
		LeftArm.setRotationPoint(9F, 1F, 3F);
		LeftArm.setTextureSize(128, 64);
		LeftArm.mirror = true;
		setRotation(LeftArm, -0.6457718F, 0F, 0F);
		RightArm = new ModelRenderer(this, 40, 0);
		RightArm.addBox(-2F, 0F, -1.5F, 4, 12, 3);
		RightArm.setRotationPoint(-9F, 1F, 3F);
		RightArm.setTextureSize(128, 64);
		RightArm.mirror = true;
		setRotation(RightArm, -0.6457718F, 0F, 0F);
		LeftSpikeBase = new ModelRenderer(this, 20, 33);
		LeftSpikeBase.addBox(0F, 0F, 0F, 1, 7, 1);
		LeftSpikeBase.setRotationPoint(3F, -5F, 5F);
		LeftSpikeBase.setTextureSize(128, 64);
		LeftSpikeBase.mirror = true;
		setRotation(LeftSpikeBase, 1.710423F, 0F, 0F);
		RightSpikeBase = new ModelRenderer(this, 20, 33);
		RightSpikeBase.addBox(0F, 0F, 0F, 1, 7, 1);
		RightSpikeBase.setRotationPoint(-3F, -5F, 5F);
		RightSpikeBase.setTextureSize(128, 64);
		RightSpikeBase.mirror = true;
		setRotation(RightSpikeBase, 1.710423F, 0F, 0F);
		LeftSpikeEnd = new ModelRenderer(this, 24, 33);
		LeftSpikeEnd.addBox(-0.5F, 0F, -1F, 1, 6, 1);
		LeftSpikeEnd.setRotationPoint(3.5F, -6.5F, 11F);
		LeftSpikeEnd.setTextureSize(128, 64);
		LeftSpikeEnd.mirror = true;
		setRotation(LeftSpikeEnd, 2.216568F, 0F, 0F);
		RightSpikeEnd = new ModelRenderer(this, 24, 33);
		RightSpikeEnd.addBox(-0.5F, 0F, -1F, 1, 6, 1);
		RightSpikeEnd.setRotationPoint(-2.5F, -6.5F, 11F);
		RightSpikeEnd.setTextureSize(128, 64);
		RightSpikeEnd.mirror = true;
		setRotation(RightSpikeEnd, 2.216568F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Chest.render(f5);
		Stomach.render(f5);
		Head.render(f5);
		LeftShoulder.render(f5);
		RightShoulder.render(f5);
		LeftLeg.render(f5);
		RightLeg.render(f5);
		LeftArm.render(f5);
		RightArm.render(f5);
		LeftSpikeBase.render(f5);
		RightSpikeBase.render(f5);
		LeftSpikeEnd.render(f5);
		RightSpikeEnd.render(f5);
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
		this.RightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F + -7.0F;
		this.LeftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F + -7.0F;
		this.RightShoulder.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 0.3F * par2 * 0.5F;
		this.LeftShoulder.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 0.3F * par2 * 0.4F;
		this.RightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.LeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
	}
}