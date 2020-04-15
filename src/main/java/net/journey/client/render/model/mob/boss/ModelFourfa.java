package net.journey.client.render.model.mob.boss;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFourfa extends ModelBiped {

	ModelRenderer rightarm2;
	ModelRenderer joint4;
	ModelRenderer joint3;
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer leftarm2;
	ModelRenderer joint1;
	ModelRenderer joint2;
	ModelRenderer head2;

	public ModelFourfa() {
		textureWidth = 64;
		textureHeight = 32;

		rightarm2 = new ModelRenderer(this, 40, 16);
		rightarm2.addBox(-3F, -2F, -2F, 4, 12, 4);
		rightarm2.setRotationPoint(-17F, 2F, 0F);
		rightarm2.setTextureSize(64, 32);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0F, 0F, 0F);
		joint4 = new ModelRenderer(this, 32, 0);
		joint4.addBox(-3F, -2F, -2F, 4, 4, 4);
		joint4.setRotationPoint(-13F, 2F, 0F);
		joint4.setTextureSize(64, 32);
		joint4.mirror = true;
		setRotation(joint4, 0F, 0F, 0F);
		joint3 = new ModelRenderer(this, 32, 0);
		joint3.addBox(-3F, -2F, -2F, 4, 4, 4);
		joint3.setRotationPoint(-5F, 2F, 0F);
		joint3.setTextureSize(64, 32);
		joint3.mirror = true;
		setRotation(joint3, 0F, 0F, 0F);
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -8F, -4F, 8, 8, 8);
		head.setRotationPoint(5F, 0F, 0F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		body = new ModelRenderer(this, 16, 16);
		body.addBox(-4F, 0F, -2F, 8, 12, 4);
		body.setRotationPoint(0F, 0F, 0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		rightarm = new ModelRenderer(this, 40, 16);
		rightarm.addBox(-3F, -2F, -2F, 4, 12, 4);
		rightarm.setRotationPoint(-9F, 2F, 0F);
		rightarm.setTextureSize(64, 32);
		rightarm.mirror = true;
		setRotation(rightarm, 0F, 0F, 0F);
		leftarm = new ModelRenderer(this, 40, 16);
		leftarm.addBox(-1F, -2F, -2F, 4, 12, 4);
		leftarm.setRotationPoint(9F, 2F, 0F);
		leftarm.setTextureSize(64, 32);
		leftarm.mirror = true;
		setRotation(leftarm, 0F, 0F, 0F);
		rightleg = new ModelRenderer(this, 0, 16);
		rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
		rightleg.setRotationPoint(-2F, 12F, 0F);
		rightleg.setTextureSize(64, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0F, 0F, 0F);
		leftleg = new ModelRenderer(this, 0, 16);
		leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
		leftleg.setRotationPoint(2F, 12F, 0F);
		leftleg.setTextureSize(64, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0F, 0F, 0F);
		leftarm2 = new ModelRenderer(this, 40, 16);
		leftarm2.addBox(-1F, -2F, -2F, 4, 12, 4);
		leftarm2.setRotationPoint(17F, 2F, 0F);
		leftarm2.setTextureSize(64, 32);
		leftarm2.mirror = true;
		setRotation(leftarm2, 0F, 0F, 0F);
		joint1 = new ModelRenderer(this, 32, 0);
		joint1.addBox(-1F, -2F, -2F, 4, 4, 4);
		joint1.setRotationPoint(13F, 2F, 0F);
		joint1.setTextureSize(64, 32);
		joint1.mirror = true;
		setRotation(joint1, 0F, 0F, 0F);
		joint2 = new ModelRenderer(this, 32, 0);
		joint2.addBox(-1F, -2F, -2F, 4, 4, 4);
		joint2.setRotationPoint(5F, 2F, 0F);
		joint2.setTextureSize(64, 32);
		joint2.mirror = true;
		setRotation(joint2, 0F, 0F, 0F);
		head2 = new ModelRenderer(this, 0, 0);
		head2.addBox(-4F, -8F, -4F, 8, 8, 8);
		head2.setRotationPoint(-5F, 0F, 0F);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		rightarm2.render(f5);
		joint4.render(f5);
		joint3.render(f5);
		head.render(f5);
		body.render(f5);
		rightarm.render(f5);
		leftarm.render(f5);
		rightleg.render(f5);
		leftleg.render(f5);
		leftarm2.render(f5);
		joint1.render(f5);
		joint2.render(f5);
		head2.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		this.head.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.head.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.head2.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.head2.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
		this.leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
		this.rightarm.rotateAngleZ = 0.0F;
		this.leftarm.rotateAngleZ = 0.0F;
		this.rightarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
		this.leftarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
		this.rightarm2.rotateAngleZ = 0.0F;
		this.leftarm2.rotateAngleZ = 0.0F;
		this.joint3.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
		this.joint4.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
		this.joint1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
		this.joint2.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
		this.joint1.rotateAngleZ = 0.0F;
		this.joint2.rotateAngleZ = 0.0F;
		this.joint3.rotateAngleZ = 0.0F;
		this.joint4.rotateAngleZ = 0.0F;
		this.rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.rightleg.rotateAngleY = 0.0F;
		this.leftleg.rotateAngleY = 0.0F;
		this.rightarm.rotateAngleY = 0.0F;
		this.leftarm.rotateAngleY = 0.0F;
	}
}