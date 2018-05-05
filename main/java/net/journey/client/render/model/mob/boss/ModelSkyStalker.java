package net.journey.client.render.model.mob.boss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSkyStalker extends ModelBase {
	
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer body0;
    ModelRenderer body1;
    ModelRenderer tailFin;
    ModelRenderer finLeft;
    ModelRenderer finRight;

	public ModelSkyStalker() {
		textureWidth = 128;
		textureHeight = 128;
	    
	      head = new ModelRenderer(this, 0, 0);
	      head.addBox(-4F, -4F, -6F, 8, 8, 6);
	      head.setRotationPoint(0F, 4F, -8F);
	      head.setTextureSize(128, 128);
	      head.mirror = true;
	      setRotation(head, 0F, 0F, 0F);
	      body = new ModelRenderer(this, 0, 14);
	      body.addBox(-6F, -10F, -7F, 12, 18, 10);
	      body.setRotationPoint(0F, 2F, 2F);
	      body.setTextureSize(128, 128);
	      body.mirror = true;
	      setRotation(body, 1.570796F, 0F, 0F);
	      body0 = new ModelRenderer(this, 0, 42);
	      body0.addBox(0F, 0F, 0F, 10, 12, 8);
	      body0.setRotationPoint(-5F, 8F, 10F);
	      body0.setTextureSize(128, 128);
	      body0.mirror = true;
	      setRotation(body0, 1.570796F, 0F, 0F);
	      body1 = new ModelRenderer(this, 0, 62);
	      body1.addBox(0F, 0F, 0F, 8, 10, 6);
	      body1.setRotationPoint(-4F, 7F, 22F);
	      body1.setTextureSize(128, 128);
	      body1.mirror = true;
	      setRotation(body1, 1.570796F, 0F, 0F);
	      tailFin = new ModelRenderer(this, 0, 78);
	      tailFin.addBox(0F, 0F, 0F, 6, 10, 2);
	      tailFin.setRotationPoint(-3F, 5F, 32F);
	      tailFin.setTextureSize(128, 128);
	      tailFin.mirror = true;
	      setRotation(tailFin, 1.570796F, 0F, 0F);
	      finLeft = new ModelRenderer(this, 0, 90);
	      finLeft.addBox(0F, 0F, 0F, 6, 10, 2);
	      finLeft.setRotationPoint(1F, 6F, -3F);
	      finLeft.setTextureSize(128, 128);
	      finLeft.mirror = true;
	      setRotation(finLeft, 1.570796F, 0.7330383F, 0F);
	      finRight = new ModelRenderer(this, 0, 102);
	      finRight.addBox(0F, 0F, 0F, 6, 10, 2);
	      finRight.setRotationPoint(-8F, 6F, 5F);
	      finRight.setTextureSize(128, 128);
	      finRight.mirror = true;
	      setRotation(finRight, 1.570796F, 2.443461F, 0F);

	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    head.render(f5);
	    body.render(f5);
	    body0.render(f5);
	    body1.render(f5);
	    tailFin.render(f5);
	    finLeft.render(f5);
	    finRight.render(f5);

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;

	}
	
	 public void setRotationAngles(float par2, float par3, float par4, float par5, float par6, float par7)
	 {
	   this.finLeft.rotateAngleZ = MathHelper.cos(par4 * 0.5F) * (float)Math.PI * 0.16F;
	   this.finRight.rotateAngleZ = MathHelper.cos(par4 * 0.5F) * (float)Math.PI * 0.16F;
	      
       this.body1.rotateAngleX = MathHelper.cos(par4 * 0.5F) * (float)Math.PI * 0.16F;
       this.tailFin.rotateAngleX = MathHelper.cos(par4 * 0.5F) * (float)Math.PI * 0.16F;
	 }
}