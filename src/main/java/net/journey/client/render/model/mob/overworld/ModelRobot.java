package net.journey.client.render.model.mob.overworld;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelRobot extends ModelBiped {

	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer rightarm;
	private ModelRenderer leftarm;
	private ModelRenderer rightleg;
	private ModelRenderer leftleg;
	private ModelRenderer leftHolder;
	private ModelRenderer rightHolder;
	private ModelRenderer leftLegHolder;
	private ModelRenderer rightLegHolder;
	private ModelRenderer headHolder;

	public ModelRobot() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -8F, -4F, 8, 8, 8);
		head.setRotationPoint(0F, -2F, 0F);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		body = new ModelRenderer(this, 16, 16);
		body.addBox(-4F, 0F, -2F, 8, 10, 4);
		body.setRotationPoint(0F, 0F, 0F);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		rightarm = new ModelRenderer(this, 40, 16);
		rightarm.addBox(-3F, -2F, -2F, 4, 12, 4);
		rightarm.setRotationPoint(-7F, 2F, 0F);
		rightarm.setTextureSize(64, 64);
		rightarm.mirror = true;
		setRotation(rightarm, 0F, 0F, 0F);
		leftarm = new ModelRenderer(this, 40, 16);
		leftarm.addBox(-1F, -2F, -2F, 4, 12, 4);
		leftarm.setRotationPoint(7F, 2F, 0F);
		leftarm.setTextureSize(64, 64);
		leftarm.mirror = true;
		setRotation(leftarm, 0F, 0F, 0F);
		rightleg = new ModelRenderer(this, 0, 16);
		rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
		rightleg.setRotationPoint(-2F, 12F, 0F);
		rightleg.setTextureSize(64, 64);
		rightleg.mirror = true;
		setRotation(rightleg, 0F, 0F, 0F);
		leftleg = new ModelRenderer(this, 0, 16);
		leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
		leftleg.setRotationPoint(2F, 12F, 0F);
		leftleg.setTextureSize(64, 64);
		leftleg.mirror = true;
		setRotation(leftleg, 0F, 0F, 0F);
		leftHolder = new ModelRenderer(this, 34, 0);
		leftHolder.addBox(0F, 0F, 0F, 2, 2, 2);
		leftHolder.setRotationPoint(4F, 1F, -1F);
		leftHolder.setTextureSize(64, 64);
		leftHolder.mirror = true;
		setRotation(leftHolder, 0F, 0F, 0F);
		rightHolder = new ModelRenderer(this, 34, 0);
		rightHolder.addBox(0F, 0F, 0F, 2, 2, 2);
		rightHolder.setRotationPoint(-6F, 1F, -1F);
		rightHolder.setTextureSize(64, 64);
		rightHolder.mirror = true;
		setRotation(rightHolder, 0F, 0F, 0F);
		leftLegHolder = new ModelRenderer(this, 34, 0);
		leftLegHolder.addBox(0F, 0F, 0F, 2, 2, 2);
		leftLegHolder.setRotationPoint(1F, 10F, -1F);
		leftLegHolder.setTextureSize(64, 64);
		leftLegHolder.mirror = true;
		setRotation(leftLegHolder, 0F, 0F, 0F);
		rightLegHolder = new ModelRenderer(this, 34, 0);
		rightLegHolder.addBox(0F, 0F, 0F, 2, 2, 2);
		rightLegHolder.setRotationPoint(-3F, 10F, -1F);
		rightLegHolder.setTextureSize(64, 64);
		rightLegHolder.mirror = true;
		setRotation(rightLegHolder, 0F, 0F, 0F);
		headHolder = new ModelRenderer(this, 50, 0);
		headHolder.addBox(0F, 0F, 0F, 4, 2, 2);
		headHolder.setRotationPoint(-2F, -2F, -1F);
		headHolder.setTextureSize(64, 64);
		headHolder.mirror = true;
		setRotation(headHolder, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		head.render(f5);
		body.render(f5);
		rightarm.render(f5);
		leftarm.render(f5);
		rightleg.render(f5);
		leftleg.render(f5);
		leftHolder.render(f5);
		rightHolder.render(f5);
		leftLegHolder.render(f5);
		rightLegHolder.render(f5);
		headHolder.render(f5);
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
		this.rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
		this.leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
		this.rightarm.rotateAngleZ = 0.0F;
		this.leftarm.rotateAngleZ = 0.0F;
		this.rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.rightleg.rotateAngleY = 0.0F;
		this.leftleg.rotateAngleY = 0.0F;
		this.rightarm.rotateAngleY = 0.0F;
		this.leftarm.rotateAngleY = 0.0F;
		float f6;
		float f7;

		this.body.rotateAngleX = 0.0F;
		this.rightleg.rotationPointZ = 0.1F;
		this.leftleg.rotationPointZ = 0.1F;
		this.rightleg.rotationPointY = 12.0F;
		this.leftleg.rotationPointY = 12.0F;
		this.head.rotationPointY = - 1.0F;

		this.rightarm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
		this.leftarm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
		this.rightarm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
		this.leftarm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
	}
}