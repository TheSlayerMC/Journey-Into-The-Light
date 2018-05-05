package net.journey.client.render.model.mob.boil;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelScreamer extends ModelBiped {

    ModelRenderer body;
    ModelRenderer arm1;
    ModelRenderer arm2;
    ModelRenderer arm3;
    ModelRenderer arm4;

	public ModelScreamer() {
		
	    textureWidth = 64;
	    textureHeight = 32;
	    
	      body = new ModelRenderer(this, 0, 0);
	      body.addBox(-3.5F, -3.5F, -3.5F, 7, 7, 7);
	      body.setRotationPoint(0F, 4F, 0F);
	      body.setTextureSize(64, 32);
	      setRotation(body, 0F, 0F, 0F);
	      arm1 = new ModelRenderer(this, 14, 14);
	      arm1.addBox(-1F, -4.5F, -1F, 2, 5, 2);
	      arm1.setRotationPoint(0F, 0F, 0F);
	      arm1.setTextureSize(64, 32);
	      setRotation(arm1, 0F, 0F, 0F);
	      arm2 = new ModelRenderer(this, 14, 14);
	      arm2.addBox(-1F, -1F, -1F, 2, 5, 2);
	      arm2.setRotationPoint(0F, 8.5F, 0F);
	      arm2.setTextureSize(64, 32);
	      setRotation(arm2, 0F, 0F, 0F);
	      arm3 = new ModelRenderer(this, 0, 21);
	      arm3.addBox(-1F, -1F, -6F, 2, 2, 7);
	      arm3.setRotationPoint(0F, -3.5F, 0F);
	      arm3.setTextureSize(64, 32);
	      setRotation(arm3, 0F, 0F, 0F);
	      arm4 = new ModelRenderer(this, 0, 21);
	      arm4.addBox(-1F, -1F, -6F, 2, 2, 7);
	      arm4.setRotationPoint(0F, 11.5F, 0F);
	      arm4.setTextureSize(64, 32);
	      setRotation(arm4, 0F, 0F, 0F);
	      
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

	    body.render(f5);
	    arm1.render(f5);
	    arm2.render(f5);
	    arm3.render(f5);
	    arm4.render(f5);
		    
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		
	}
}