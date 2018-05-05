package net.journey.client.render.model.mob.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFish extends ModelBase {

	ModelRenderer Body;
	ModelRenderer BackFin1;
	ModelRenderer BackFin2;

	public ModelFish() {
		textureWidth = 64;
		textureHeight = 32;

		Body = new ModelRenderer(this, 14, 6);
		Body.addBox(0F, 0F, 0F, 1, 4, 4);
		Body.setRotationPoint(-1F, 18F, 0F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, -0.7853982F, 0F, 0F);
		BackFin1 = new ModelRenderer(this, 0, 0);
		BackFin1.addBox(0F, -1F, 0F, 0, 2, 2);
		BackFin1.setRotationPoint(0F, 20F, 3F);
		BackFin1.setTextureSize(64, 32);
		BackFin1.mirror = true;
		setRotation(BackFin1, -0.7853982F, 0F, 0F);
		BackFin2 = new ModelRenderer(this, 0, 0);
		BackFin2.addBox(0F, -1F, 0F, 0, 2, 2);
		BackFin2.setRotationPoint(-1F, 20F, 3F);
		BackFin2.setTextureSize(64, 32);
		BackFin2.mirror = true;
		setRotation(BackFin2, -0.7853982F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Body.render(f5);
		BackFin1.render(f5);
		BackFin2.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
}