package net.journey.client.render.model.mob.euca;

import net.journey.entity.mob.pet.EntityEucaHopper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelEucaHopper extends ModelBase {

	ModelRenderer head;
	ModelRenderer tail;
	ModelRenderer leftFrontLeg;
	ModelRenderer rightFrontLeg;
	ModelRenderer rightBackLeg;
	ModelRenderer leftBackLeg;
	ModelRenderer frontFin;
	ModelRenderer backFin;
	ModelRenderer leftEar;
	ModelRenderer rightEar;

	public ModelEucaHopper() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(0F, 0F, 0F, 11, 11, 12);
		head.setRotationPoint(-6F, 8F, -6F);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		tail = new ModelRenderer(this, 0, 46);
		tail.addBox(0F, 0F, 0F, 6, 4, 8);
		tail.setRotationPoint(-3.5F, 12F, 5F);
		tail.setTextureSize(64, 64);
		tail.mirror = true;
		setRotation(tail, 0.1745329F, 0F, 0F);
		leftFrontLeg = new ModelRenderer(this, 50, 0);
		leftFrontLeg.addBox(-1F, 0F, -1F, 3, 7, 3);
		leftFrontLeg.setRotationPoint(5F, 17F, -4F);
		leftFrontLeg.setTextureSize(64, 64);
		leftFrontLeg.mirror = true;
		setRotation(leftFrontLeg, 0F, 0F, 0F);
		rightFrontLeg = new ModelRenderer(this, 50, 0);
		rightFrontLeg.addBox(-2F, 0F, -1F, 3, 7, 3);
		rightFrontLeg.setRotationPoint(-6F, 17F, -4F);
		rightFrontLeg.setTextureSize(64, 64);
		rightFrontLeg.mirror = true;
		setRotation(rightFrontLeg, 0F, 0F, 0F);
		rightBackLeg = new ModelRenderer(this, 50, 0);
		rightBackLeg.addBox(-2F, 0F, -1F, 3, 7, 3);
		rightBackLeg.setRotationPoint(-6F, 17F, 3F);
		rightBackLeg.setTextureSize(64, 64);
		rightBackLeg.mirror = true;
		setRotation(rightBackLeg, 0F, 0F, 0F);
		leftBackLeg = new ModelRenderer(this, 50, 0);
		leftBackLeg.addBox(-1F, 0F, -1F, 3, 7, 3);
		leftBackLeg.setRotationPoint(5F, 17F, 3F);
		leftBackLeg.setTextureSize(64, 64);
		leftBackLeg.mirror = true;
		setRotation(leftBackLeg, 0F, 0F, 0F);
		frontFin = new ModelRenderer(this, 1, 24);
		frontFin.addBox(0F, 0F, 0F, 2, 7, 5);
		frontFin.setRotationPoint(-1.5F, 2F, -4F);
		frontFin.setTextureSize(64, 64);
		frontFin.mirror = true;
		setRotation(frontFin, -0.2617994F, 0F, 0F);
		backFin = new ModelRenderer(this, 16, 25);
		backFin.addBox(0F, 0F, 0F, 2, 7, 4);
		backFin.setRotationPoint(-1.5F, 4F, 2F);
		backFin.setTextureSize(64, 64);
		backFin.mirror = true;
		setRotation(backFin, -0.715585F, 0F, 0F);
		leftEar = new ModelRenderer(this, 1, 37);
		leftEar.addBox(0F, 0F, 0F, 4, 4, 4);
		leftEar.setRotationPoint(5F, 6F, -3F);
		leftEar.setTextureSize(64, 64);
		leftEar.mirror = true;
		setRotation(leftEar, 0F, 0F, 0.837758F);
		rightEar = new ModelRenderer(this, 1, 37);
		rightEar.addBox(0F, 0F, 0F, 4, 4, 4);
		rightEar.setRotationPoint(-6F, 6F, -3F);
		rightEar.setTextureSize(64, 64);
		rightEar.mirror = true;
		setRotation(rightEar, 0F, 0F, 0.7504916F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		head.render(f5);
		tail.render(f5);
		leftFrontLeg.render(f5);
		rightFrontLeg.render(f5);
		rightBackLeg.render(f5);
		leftBackLeg.render(f5);
		frontFin.render(f5);
		backFin.render(f5);
		leftEar.render(f5);
		rightEar.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	@Override
	public void setLivingAnimations(EntityLivingBase mob, float f, float f1, float f2) {
		EntityEucaHopper hopper = (EntityEucaHopper)mob;
		if(hopper.isSitting()){
			tail.rotateAngleX = -((float)Math.PI * 2F / 10F);
		} else { 
			tail.rotateAngleX = 0.0F;
		}
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		this.rightBackLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		this.rightFrontLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
		
		this.leftFrontLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		this.leftBackLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
	}
}