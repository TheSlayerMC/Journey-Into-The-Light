package net.journey.client.render.model.mob.end;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelEnderCrawler extends ModelBase {
	
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer scale1;
    ModelRenderer scale2;
    ModelRenderer scale3;
    ModelRenderer ear1;
    ModelRenderer ear2;
    ModelRenderer snout;

	public ModelEnderCrawler() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this, 0, 0);
	      head.addBox(-4F, -4F, -8F, 8, 8, 8);
	      head.setRotationPoint(0F, 12F, -6F);
	      head.setTextureSize(64, 64);
	      setRotation(head, 0F, 0F, 0F);
	      body = new ModelRenderer(this, 28, 8);
	      body.addBox(-5F, -10F, -7F, 10, 16, 8);
	      body.setRotationPoint(0F, 12F, 4F);
	      body.setTextureSize(64, 64);
	      setRotation(body, 1.384903F, 0F, 0F);
	      leg1 = new ModelRenderer(this, 0, 16);
	      leg1.addBox(-2F, 0F, -2F, 4, 6, 4);
	      leg1.setRotationPoint(-3F, 18F, 7F);
	      leg1.setTextureSize(64, 64);
	      setRotation(leg1, 0F, 0F, 0F);
	      leg2 = new ModelRenderer(this, 0, 16);
	      leg2.addBox(-2F, 0F, -2F, 4, 6, 4);
	      leg2.setRotationPoint(3F, 18F, 7F);
	      leg2.setTextureSize(64, 64);
	      setRotation(leg2, 0F, 0F, 0F);
	      leg3 = new ModelRenderer(this, 0, 26);
	      leg3.addBox(-2F, 18F, -2F, 4, 7, 4);
	      leg3.setRotationPoint(-3F, -1F, -5F);
	      leg3.setTextureSize(64, 64);
	      setRotation(leg3, 0F, 0F, 0F);
	      leg4 = new ModelRenderer(this, 0, 26);
	      leg4.addBox(-2F, 0F, -2F, 4, 7, 4);
	      leg4.setRotationPoint(3F, 17F, -5F);
	      leg4.setTextureSize(64, 64);
	      setRotation(leg4, 0F, 0F, 0F);
	      scale1 = new ModelRenderer(this, 16, 21);
	      scale1.addBox(0F, 0F, 0F, 2, 4, 3);
	      scale1.setRotationPoint(-1F, 7F, 5F);
	      scale1.setTextureSize(64, 64);
	      setRotation(scale1, -0.3346075F, 0F, 0F);
	      scale2 = new ModelRenderer(this, 16, 21);
	      scale2.addBox(0F, 0F, 0F, 2, 10, 3);
	      scale2.setRotationPoint(-1F, 5F, 1F);
	      scale2.setTextureSize(64, 64);
	      setRotation(scale2, -0.2230717F, 0F, 0F);
	      scale3 = new ModelRenderer(this, 16, 21);
	      scale3.addBox(0F, 0F, 0F, 2, 10, 3);
	      scale3.setRotationPoint(-1F, 5F, -5F);
	      scale3.setTextureSize(64, 64);
	      setRotation(scale3, 0.2230717F, 0F, 0F);
	      ear1 = new ModelRenderer(this, 56, 3);
	      ear1.addBox(0F, 0F, 0F, 1, 9, 3);
	      ear1.setRotationPoint(4F, 6F, -11F);
	      ear1.setTextureSize(64, 64);
	      setRotation(ear1, 0F, 0F, 0.0698132F);
	      ear2 = new ModelRenderer(this, 56, 3);
	      ear2.addBox(0F, -1F, -2F, 1, 9, 3);
	      ear2.setRotationPoint(-5F, 7F, -9F);
	      ear2.setTextureSize(64, 64);
	      setRotation(ear2, 0F, 0F, -0.0698132F);
	      snout = new ModelRenderer(this, 16, 16);
	      snout.addBox(0F, 0F, 0F, 4, 3, 2);
	      snout.setRotationPoint(-2F, 12F, -16F);
	      snout.setTextureSize(64, 64);
	      setRotation(snout, 0F, 0F, 0F);
	      head.addChild(ear1);
	      head.addChild(ear2);
	      head.addChild(snout);

	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    head.render(f5);
	    body.render(f5);
	    leg1.render(f5);
	    leg2.render(f5);
	    leg3.render(f5);
	    leg4.render(f5);
	    scale1.render(f5);
	    scale2.render(f5);
	    scale3.render(f5);
	    ear1.render(f5);
	    ear2.render(f5);
	    snout.render(f5);

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		this.leg1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
		this.leg2.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
		this.leg3.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		this.leg4.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
		this.head.rotateAngleY = f3 / (180F / (float)Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float)Math.PI);
	}
}