package net.journey.client.render.model.mob.end;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEnderLeaper extends ModelBase {
	
    ModelRenderer head;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer foot1;
    ModelRenderer foot2;

	public ModelEnderLeaper() {
		  textureWidth = 64;
	      textureHeight = 32;
	    
	      textureWidth = 64;
	      textureHeight = 64;
	      
	        head = new ModelRenderer(this, 0, 0);
	        head.addBox(-4F, -8F, -4F, 8, 8, 8);
	        head.setRotationPoint(0F, 10F, 0F);
	        head.setTextureSize(64, 64);
	        head.mirror = true;
	        setRotation(head, 0F, 0F, 0F);
	        rightarm = new ModelRenderer(this, 40, 16);
	        rightarm.addBox(-3F, -2F, -2F, 3, 8, 3);
	        rightarm.setRotationPoint(-3F, 8F, 1F);
	        rightarm.setTextureSize(64, 64);
	        rightarm.mirror = true;
	        setRotation(rightarm, 0.3141593F, 0F, 0F);
	        leftarm = new ModelRenderer(this, 40, 16);
	        leftarm.addBox(-1F, -2F, -2F, 3, 8, 3);
	        leftarm.setRotationPoint(4F, 8F, 1F);
	        leftarm.setTextureSize(64, 64);
	        leftarm.mirror = true;
	        setRotation(leftarm, 0.3141593F, 0F, 0F);
	        rightleg = new ModelRenderer(this, 0, 16);
	        rightleg.addBox(-2F, 0F, -2F, 4, 10, 4);
	        rightleg.setRotationPoint(-5F, 13F, 2F);
	        rightleg.setTextureSize(64, 64);
	        rightleg.mirror = true;
	        setRotation(rightleg, -0.2617994F, 0F, 0F);
	        leftleg = new ModelRenderer(this, 0, 16);
	        leftleg.addBox(-2F, 0F, -2F, 4, 10, 4);
	        leftleg.setRotationPoint(5F, 13F, 2F);
	        leftleg.setTextureSize(64, 64);
	        leftleg.mirror = true;
	        setRotation(leftleg, -0.2617994F, 0F, 0F);
	        foot1 = new ModelRenderer(this, 0, 16);
	        foot1.addBox(0F, 0F, 0F, 6, 2, 6);
	        foot1.setRotationPoint(2F, 22F, -5F);
	        foot1.setTextureSize(64, 64);
	        foot1.mirror = true;
	        setRotation(foot1, 0F, 0F, 0F);
	        foot2 = new ModelRenderer(this, 0, 16);
	        foot2.addBox(0F, 0F, 0F, 6, 2, 6);
	        foot2.setRotationPoint(-8F, 22F, -5F);
	        foot2.setTextureSize(64, 64);
	        foot2.mirror = true;
	        setRotation(foot2, 0F, 0F, 0F);

	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    head.render(f5);
	    rightarm.render(f5);
	    leftarm.render(f5);
	    rightleg.render(f5);
	    leftleg.render(f5);
	    foot1.render(f5);
	    foot2.render(f5);

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;

	}
}