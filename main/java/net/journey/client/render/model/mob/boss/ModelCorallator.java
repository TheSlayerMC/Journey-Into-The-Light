package net.journey.client.render.model.mob.boss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCorallator extends ModelBase {

    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer hover;
    ModelRenderer hover1;
    ModelRenderer hover2;
    ModelRenderer hover3;
    ModelRenderer hoverBase;

	public ModelCorallator() {
		  textureWidth = 128;
	      textureHeight = 128;
	    
	      head = new ModelRenderer(this, 0, 0);
	      head.addBox(-4F, -8F, -4F, 8, 8, 8);
	      head.setRotationPoint(0F, 0F, 0F);
	      head.setTextureSize(128, 128);
	      head.mirror = true;
	      setRotation(head, 0F, 0F, 0F);
	      body = new ModelRenderer(this, 16, 40);
	      body.addBox(-4F, 0F, -2F, 14, 8, 14);
	      body.setRotationPoint(-3F, 0F, -5F);
	      body.setTextureSize(128, 128);
	      body.mirror = true;
	      setRotation(body, 0F, 0F, 0F);
	      hover = new ModelRenderer(this, 40, 16);
	      hover.addBox(-3F, -2F, -2F, 4, 16, 4);
	      hover.setRotationPoint(-8F, 2F, 0F);
	      hover.setTextureSize(128, 128);
	      hover.mirror = true;
	      setRotation(hover, 0F, 0F, 0.0872665F);
	      hover1 = new ModelRenderer(this, 40, 16);
	      hover1.addBox(-1F, -2F, -2F, 4, 16, 4);
	      hover1.setRotationPoint(8F, 2F, 0F);
	      hover1.setTextureSize(128, 128);
	      hover1.mirror = true;
	      setRotation(hover1, 0F, 0F, -0.0872665F);
	      hover2 = new ModelRenderer(this, 0, 16);
	      hover2.addBox(-2F, 0F, -2F, 4, 16, 4);
	      hover2.setRotationPoint(0F, 0F, -9F);
	      hover2.setTextureSize(128, 128);
	      hover2.mirror = true;
	      setRotation(hover2, -0.0872665F, 0F, 0F);
	      hover3 = new ModelRenderer(this, 0, 16);
	      hover3.addBox(-2F, 0F, -2F, 4, 16, 4);
	      hover3.setRotationPoint(0F, 0F, 9F);
	      hover3.setTextureSize(128, 128);
	      hover3.mirror = true;
	      setRotation(hover3, 0.0872665F, 0F, 0F);
	      hoverBase = new ModelRenderer(this, 16, 62);
	      hoverBase.addBox(0F, 0F, 0F, 20, 4, 20);
	      hoverBase.setRotationPoint(-10F, 8F, -10F);
	      hoverBase.setTextureSize(128, 128);
	      hoverBase.mirror = true;
	      setRotation(hoverBase, 0F, 0F, 0F);

	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		head.render(f5);
	    body.render(f5);
	    hover.render(f5);
	    hover1.render(f5);
	    hover2.render(f5);
	    hover3.render(f5);
	    hoverBase.render(f5);

	}

	protected void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {

	}
}