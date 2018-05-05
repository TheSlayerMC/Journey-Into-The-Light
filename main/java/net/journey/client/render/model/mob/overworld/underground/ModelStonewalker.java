package net.journey.client.render.model.mob.overworld.underground;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelStonewalker extends ModelBase {
	
    ModelRenderer leftBackLeg;
    ModelRenderer rightBackLeg;
    ModelRenderer leftFrontLeg;
    ModelRenderer rightFrontLeg;
    ModelRenderer body;
    ModelRenderer limb2;
    ModelRenderer limb1;
    ModelRenderer head;

	public ModelStonewalker() {
	 	textureWidth = 64;
		textureHeight = 64;

		  leftBackLeg = new ModelRenderer(this, 0, 0);
	      leftBackLeg.addBox(0F, -1F, -1F, 2, 13, 2);
	      leftBackLeg.setRotationPoint(6F, 12F, 7F);
	      leftBackLeg.setTextureSize(64, 32);
	      leftBackLeg.mirror = true;
	      setRotation(leftBackLeg, 0F, 0F, 0F);
	      rightBackLeg = new ModelRenderer(this, 0, 0);
	      rightBackLeg.addBox(-2F, -1F, -1F, 2, 13, 2);
	      rightBackLeg.setRotationPoint(-6F, 12F, 7F);
	      rightBackLeg.setTextureSize(64, 32);
	      rightBackLeg.mirror = true;
	      setRotation(rightBackLeg, 0F, 0F, 0F);
	      leftFrontLeg = new ModelRenderer(this, 0, 0);
	      leftFrontLeg.addBox(0F, -1F, -1F, 2, 13, 2);
	      leftFrontLeg.setRotationPoint(6F, 12F, -7F);
	      leftFrontLeg.setTextureSize(64, 32);
	      leftFrontLeg.mirror = true;
	      setRotation(leftFrontLeg, 0F, 0F, 0F);
	      rightFrontLeg = new ModelRenderer(this, 0, 0);
	      rightFrontLeg.addBox(-2F, -1F, -1F, 2, 13, 2);
	      rightFrontLeg.setRotationPoint(-6F, 12F, -7F);
	      rightFrontLeg.setTextureSize(64, 32);
	      rightFrontLeg.mirror = true;
	      setRotation(rightFrontLeg, 0F, 0F, 0F);
	      body = new ModelRenderer(this, 0, 15);
	      body.addBox(0F, 0F, 0F, 4, 16, 4);
	      body.setRotationPoint(-2F, 5F, -2F);
	      body.setTextureSize(64, 32);
	      body.mirror = true;
	      setRotation(body, 0F, 0F, 0F);
	      limb2 = new ModelRenderer(this, 8, 0);
	      limb2.addBox(-10.5F, 0F, -2F, 20, 2, 2);
	      limb2.setRotationPoint(0F, 14F, 1F);
	      limb2.setTextureSize(64, 32);
	      limb2.mirror = true;
	      setRotation(limb2, 0F, -0.7853982F, 0F);
	      limb1 = new ModelRenderer(this, 8, 0);
	      limb1.addBox(-8.5F, 0F, -1F, 20, 2, 2);
	      limb1.setRotationPoint(-1F, 14F, 1F);
	      limb1.setTextureSize(64, 32);
	      limb1.mirror = true;
	      setRotation(limb1, 0F, 0.7853982F, 0F);
	      head = new ModelRenderer(this, 0, 35);
	      head.addBox(-4F, -8F, -4F, 8, 8, 8);
	      head.setRotationPoint(0F, 5F, 0F);
	      head.setTextureSize(64, 32);
	      head.mirror = true;
	      setRotation(head, 0F, 0F, 0F);

	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

	    leftBackLeg.render(f5);
	    rightBackLeg.render(f5);
	    leftFrontLeg.render(f5);
	    rightFrontLeg.render(f5);
	    body.render(f5);
	    limb2.render(f5);
	    limb1.render(f5);
	    head.render(f5);

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
		
	}
		@Override
		public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
			super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
			this.head.rotateAngleY = f4 / (180F / (float)Math.PI);
			this.head.rotateAngleX = f5 / (180F / (float)Math.PI);
			this.rightFrontLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * f1;
			this.leftFrontLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.leftBackLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * f1;
			this.rightBackLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		
	}
}