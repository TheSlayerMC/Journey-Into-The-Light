package net.journey.client.render.model.mob.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelMediumHongo extends ModelBase {

	ModelRenderer bod;
	ModelRenderer hatB;
	ModelRenderer lArm;
	ModelRenderer rArm;
	ModelRenderer hatTop;

	public ModelMediumHongo() {
		textureWidth = 64;
		textureHeight = 64;

		      bod = new ModelRenderer(this, 0, 18);
		      bod.addBox(0F, 0F, 0F, 8, 11, 7);
		      bod.setRotationPoint(-4F, 13F, -3.5F);
		      bod.setTextureSize(64, 64);
		      bod.mirror = true;
		      setRotation(bod, 0F, 0F, 0F);
		      hatB = new ModelRenderer(this, 0, 37);
		      hatB.addBox(0F, 0F, 0F, 13, 6, 12);
		      hatB.setRotationPoint(-6.5F, 10F, -6F);
		      hatB.setTextureSize(64, 64);
		      hatB.mirror = true;
		      setRotation(hatB, 0F, 0F, 0F);
		      hatTop = new ModelRenderer(this, 0, 0);
		      hatTop.addBox(0F, 0F, 0F, 10, 3, 10);
		      hatTop.setRotationPoint(-4.8F, 7F, -5F);
		      hatTop.setTextureSize(64, 64);
		      hatTop.mirror = true;
		      setRotation(hatTop, 0F, 0F, 0F);
		      lArm = new ModelRenderer(this, 34, 24);
		      lArm.addBox(0F, 0F, 0F, 2, 6, 3);
		      lArm.setRotationPoint(3.8F, 19F, -1.5F);
		      lArm.setTextureSize(64, 64);
		      lArm.mirror = true;
		      setRotation(lArm, 0F, 0F, -0.0872665F);
		      rArm = new ModelRenderer(this, 34, 24);
		      rArm.addBox(-2F, 0F, 0F, 2, 6, 3);
		      rArm.setRotationPoint(-3.8F, 19F, -1.5F);
		      rArm.setTextureSize(64, 64);
		      rArm.mirror = true;
		      setRotation(rArm, 0F, 0F, 0.122173F);

	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bod.render(f5);
		hatB.render(f5);
		lArm.render(f5);
		rArm.render(f5);
		hatTop.render(f5);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
    	this.lArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
    	this.rArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * f1;
	}
}