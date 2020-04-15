package net.journey.client.render.model.mob.frozen;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import org.lwjgl.opengl.GL11;

public class ModelFrozenMerchant extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer hatBottom;
	ModelRenderer hatMiddle;
	ModelRenderer hatTop;
	ModelRenderer puff;

	public ModelFrozenMerchant() {
		textureWidth = 128;
		textureHeight = 64;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -8F, -4F, 8, 8, 8);
		head.setRotationPoint(0F, 6F, 0F);
		head.setTextureSize(128, 64);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		body = new ModelRenderer(this, 16, 16);
		body.addBox(-4F, 0F, -2F, 8, 10, 4);
		body.setRotationPoint(0F, 6F, 0F);
		body.setTextureSize(128, 64);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		rightarm = new ModelRenderer(this, 40, 16);
		rightarm.addBox(-3F, -2F, -2F, 4, 10, 4);
		rightarm.setRotationPoint(-5F, 8F, 0F);
		rightarm.setTextureSize(128, 64);
		rightarm.mirror = true;
		setRotation(rightarm, 0F, 0F, 0F);
		leftarm = new ModelRenderer(this, 40, 16);
		leftarm.addBox(-1F, -2F, -2F, 4, 10, 4);
		leftarm.setRotationPoint(5F, 8F, 0F);
		leftarm.setTextureSize(128, 64);
		leftarm.mirror = true;
		setRotation(leftarm, 0F, 0F, 0F);
		rightleg = new ModelRenderer(this, 0, 16);
		rightleg.addBox(-2F, 0F, -2F, 4, 8, 4);
		rightleg.setRotationPoint(-2F, 16F, 0F);
		rightleg.setTextureSize(128, 64);
		rightleg.mirror = true;
		setRotation(rightleg, 0F, 0F, 0F);
		leftleg = new ModelRenderer(this, 0, 16);
		leftleg.addBox(-2F, 0F, -2F, 4, 8, 4);
		leftleg.setRotationPoint(2F, 16F, 0F);
		leftleg.setTextureSize(128, 64);
		leftleg.mirror = true;
		setRotation(leftleg, 0F, 0F, 0F);
		hatBottom = new ModelRenderer(this, 56, 0);
		hatBottom.addBox(0F, 0F, 0F, 10, 2, 10);
		hatBottom.setRotationPoint(-5F, -9F, -4.8F);
		hatBottom.setTextureSize(128, 64);
		hatBottom.mirror = true;
		setRotation(hatBottom, -0.0349066F, 0F, 0F);
		hatMiddle = new ModelRenderer(this, 96, 0);
		hatMiddle.addBox(0F, 0F, 0F, 8, 5, 8);
		hatMiddle.setRotationPoint(-4F, -13.5F, -3.5F);
		hatMiddle.setTextureSize(128, 64);
		hatMiddle.mirror = true;
		setRotation(hatMiddle, -0.122173F, 0F, 0F);
		hatTop = new ModelRenderer(this, 32, 0);
		hatTop.addBox(0F, 0F, 0F, 6, 4, 6);
		hatTop.setRotationPoint(-3F, -17F, -1.8F);
		hatTop.setTextureSize(128, 64);
		hatTop.mirror = true;
		setRotation(hatTop, -0.2268928F, 0F, 0F);
		puff = new ModelRenderer(this, 58, 18);
		puff.addBox(0F, 0F, 0F, 2, 2, 2);
		puff.setRotationPoint(-1F, -18F, 0.5F);
		puff.setTextureSize(128, 64);
		puff.mirror = true;
		setRotation(puff, -0.2268928F, 0F, 0F);
		this.head.addChild(hatBottom);
		this.head.addChild(hatMiddle);
		this.head.addChild(hatTop);
		this.head.addChild(puff);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		GlStateManager.pushMatrix();
	    GlStateManager.enableBlend();
	    GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	    GlStateManager.depthMask(true);
	    GL11.glEnable(GL11.GL_ALPHA_TEST);
		head.render(f5);
		body.render(f5);
		rightarm.render(f5);
		leftarm.render(f5);
		rightleg.render(f5);
		leftleg.render(f5);
		GlStateManager.popMatrix();
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.head.rotateAngleY = f3 / 57.29578f;
		this.head.rotateAngleX= f4 / 57.29578f;
		this.rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * f1;
		this.leftleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
	}

}
