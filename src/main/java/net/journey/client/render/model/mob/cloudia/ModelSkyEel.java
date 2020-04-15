package net.journey.client.render.model.mob.cloudia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSkyEel extends ModelBase {

    ModelRenderer scale2;
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer body1;
    ModelRenderer body2;
    ModelRenderer scale;
    ModelRenderer scale1;
    ModelRenderer fur;
    ModelRenderer fur1;
    ModelRenderer fur2;

	public ModelSkyEel() {
		textureWidth = 128;
	    textureHeight = 128;
	    
	      scale2 = new ModelRenderer(this, 0, 14);
	      scale2.addBox(0F, 0F, 0F, 2, 3, 7);
	      scale2.setRotationPoint(-1F, 5F, 12F);
	      scale2.setTextureSize(128, 128);
	      scale2.mirror = true;
	      setRotation(scale2, 0.6981317F, 0F, 0F);
	      head = new ModelRenderer(this, 0, 0);
	      head.addBox(-4F, -4F, -6F, 8, 8, 6);
	      head.setRotationPoint(0F, 7F, -8F);
	      head.setTextureSize(128, 128);
	      head.mirror = true;
	      setRotation(head, 0F, 0F, 0F);
	      body = new ModelRenderer(this, 62, 4);
	      body.addBox(-6F, -10F, -7F, 12, 18, 10);
	      body.setRotationPoint(0F, 5F, 2F);
	      body.setTextureSize(128, 128);
	      body.mirror = true;
	      setRotation(body, 1.570796F, 0F, 0F);
	      body1 = new ModelRenderer(this, 18, 97);
	      body1.addBox(0F, 0F, 0F, 8, 6, 10);
	      body1.setRotationPoint(-4F, 4F, 10F);
	      body1.setTextureSize(128, 128);
	      body1.mirror = true;
	      setRotation(body1, 0F, 0F, 0F);
	      body2 = new ModelRenderer(this, 18, 75);
	      body2.addBox(0F, 0F, 0F, 4, 2, 10);
	      body2.setRotationPoint(-2F, 7F, 20F);
	      body2.setTextureSize(128, 128);
	      body2.mirror = true;
	      setRotation(body2, 0F, 0F, 0F);
	      scale = new ModelRenderer(this, 0, 14);
	      scale.addBox(0F, 0F, 0F, 2, 3, 10);
	      scale.setRotationPoint(-1F, 2F, -6F);
	      scale.setTextureSize(128, 128);
	      scale.mirror = true;
	      setRotation(scale, 0.6981317F, 0F, 0F);
	      scale1 = new ModelRenderer(this, 0, 14);
	      scale1.addBox(0F, 0F, 0F, 2, 3, 10);
	      scale1.setRotationPoint(-1F, 4F, 2F);
	      scale1.setTextureSize(128, 128);
	      scale1.mirror = true;
	      setRotation(scale2, 0.6981317F, 0F, 0F);
	      fur = new ModelRenderer(this, 4, 40);
	      fur.addBox(3F, -5F, -4F, 12, 6, 18);
	      fur.setRotationPoint(-9F, 17F, -4F);
	      fur.setTextureSize(128, 128);
	      fur.mirror = true;
	      setRotation(fur, 0F, 0F, 0F);
	      fur1 = new ModelRenderer(this, 18, 48);
	      fur1.addBox(0F, 0F, 0F, 8, 6, 10);
	      fur1.setRotationPoint(-4F, 10F, 10F);
	      fur1.setTextureSize(128, 128);
	      fur1.mirror = true;
	      setRotation(fur1, 0F, 0F, 0F);
	      fur2 = new ModelRenderer(this, 10, 49);
	      fur2.addBox(0F, 0F, 0F, 4, 2, 10);
	      fur2.setRotationPoint(-2F, 9F, 20F);
	      fur2.setTextureSize(128, 128);
	      fur2.mirror = true;
	      setRotation(fur2, 0F, 0F, 0F);

	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    scale2.render(f5);
	    head.render(f5);
	    body.render(f5);
	    body1.render(f5);
	    body2.render(f5);
	    scale.render(f5);
	    scale2.render(f5);
	    fur.render(f5);
	    fur1.render(f5);
	    fur2.render(f5);

    }

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;

	}
}