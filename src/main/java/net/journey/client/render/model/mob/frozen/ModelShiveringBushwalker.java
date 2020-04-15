package net.journey.client.render.model.mob.frozen;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelShiveringBushwalker extends ModelBase {
	ModelRenderer body;
	ModelRenderer leaves;
	ModelRenderer leavesTop;
	ModelRenderer lLeg;
	ModelRenderer rLeg;

	public ModelShiveringBushwalker() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this, 0, 16);
		body.addBox(0F, 0F, 0F, 10, 12, 8);
		body.setRotationPoint(-5F, 10F, -3.5F);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		leaves = new ModelRenderer(this, 0, 37);
		leaves.addBox(0F, 0F, 0F, 13, 8, 12);
		leaves.setRotationPoint(-5.5F, 4F, -5.5F);
		leaves.setTextureSize(64, 64);
		leaves.mirror = true;
		setRotation(leaves, -0.027884F, 0.0349066F, 0.1858931F);
		leavesTop = new ModelRenderer(this, 0, 0);
		leavesTop.addBox(0F, 0F, 0F, 10, 6, 10);
		leavesTop.setRotationPoint(-4.8F, 1F, -0.5F);
		leavesTop.setTextureSize(64, 64);
		leavesTop.mirror = true;
		setRotation(leavesTop, -0.4461433F, 0F, 0.1267171F);
		lLeg = new ModelRenderer(this, 38, 24);
		lLeg.addBox(0F, 0F, 0F, 2, 6, 3);
		lLeg.setRotationPoint(4.8F, 18F, -0.5F);
		lLeg.setTextureSize(64, 64);
		lLeg.mirror = true;
		setRotation(lLeg, 0F, 0F, -0.0872665F);
		rLeg = new ModelRenderer(this, 38, 24);
		rLeg.addBox(-2F, 0F, 0F, 2, 6, 3);
		rLeg.setRotationPoint(-4.8F, 18F, -0.5F);
		rLeg.setTextureSize(64, 64);
		rLeg.mirror = true;
		setRotation(rLeg, 0F, 0F, 0.122173F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		body.render(f5);
		leaves.render(f5);
		leavesTop.render(f5);
		lLeg.render(f5);
		rLeg.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.rLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * f1;
		this.lLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
	}

}
