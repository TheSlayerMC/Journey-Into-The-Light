package net.journey.client.render.model.mob.boil;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelHellwing extends ModelBiped {

    ModelRenderer head;
    ModelRenderer rightwing;
    ModelRenderer leftwing;
    ModelRenderer rightwing1;
    ModelRenderer leftwing1;

	public ModelHellwing() {
		textureWidth = 64;
	    textureHeight = 32;
	    
	      head = new ModelRenderer(this, 0, 0);
	      head.addBox(-4F, -8F, -4F, 8, 8, 8);
	      head.setRotationPoint(0.1F, 11F, 0F);
	      head.setTextureSize(64, 32);
	      head.mirror = true;
	      setRotation(head, 0F, 0F, 0F);
	      rightwing = new ModelRenderer(this, 32, 9);
	      rightwing.addBox(-8F, -1F, -2F, 8, 1, 6);
	      rightwing.setRotationPoint(-4F, 6F, -1F);
	      rightwing.setTextureSize(64, 32);
	      rightwing.mirror = true;
	      setRotation(rightwing, 0F, 0F, -0.122173F);
	      leftwing = new ModelRenderer(this, 32, 9);
	      leftwing.addBox(0F, -1F, -3F, 8, 1, 6);
	      leftwing.setRotationPoint(4F, 6F, 0F);
	      leftwing.setTextureSize(64, 32);
	      leftwing.mirror = true;
	      setRotation(leftwing, 0F, 0F, 0.122173F);
	      rightwing1 = new ModelRenderer(this, 32, 16);
	      rightwing1.addBox(-15F, -3.7F, -2F, 8, 1, 4);
	      rightwing1.setRotationPoint(-4F, 6.4F, 0F);
	      rightwing1.setTextureSize(64, 32);
	      rightwing1.mirror = true;
	      setRotation(rightwing1, 0F, 0F, -0.4363323F);
	      leftwing1 = new ModelRenderer(this, 32, 16);
	      leftwing1.addBox(7F, -3.3F, -2F, 8, 1, 4);
	      leftwing1.setRotationPoint(4F, 6F, 0F);
	      leftwing1.setTextureSize(64, 32);
	      leftwing1.mirror = true;
	      setRotation(leftwing1, 0F, 0F, 0.4363323F);

	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

	      head.render(f5);
	      rightwing.render(f5);
	      leftwing.render(f5);
	      rightwing1.render(f5);
	      leftwing1.render(f5);
		    
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		
        this.rightwing.rotateAngleX = MathHelper.cos(par3 * 1.3F) * (float)Math.PI * 0.25F;
        this.leftwing.rotateAngleX = -this.rightwing.rotateAngleX;
        this.rightwing1.rotateAngleX = this.rightwing.rotateAngleX * 0.5F;
        this.leftwing1.rotateAngleX = -this.rightwing.rotateAngleX * 0.5F;
		
	}
}