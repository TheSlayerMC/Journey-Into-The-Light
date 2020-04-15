package net.journey.client.render.model.mob.depths;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelStaringGuardian extends ModelBase {

    private ModelRenderer head;
    private ModelRenderer body;
    private ModelRenderer rightarm;
    private ModelRenderer leftarm;
    private ModelRenderer rightleg;
    private ModelRenderer leftleg;
    private ModelRenderer neck;


	public ModelStaringGuardian() {
		textureWidth = 128;
	    textureHeight = 64;
	    
	      head = new ModelRenderer(this, 0, 0);
	      head.addBox(-6F, -12F, -6F, 12, 12, 12);
	      head.setRotationPoint(0F, -4F, 0F);
	      head.setTextureSize(128, 64);
	      setRotation(head, 0F, 0F, 0F);
	      body = new ModelRenderer(this, 16, 24);
	      body.addBox(-4F, 0F, -2F, 8, 12, 4);
	      body.setRotationPoint(0F, 0F, 0F);
	      body.setTextureSize(128, 64);
	      setRotation(body, 0F, 0F, 0F);
	      rightarm = new ModelRenderer(this, 72, 16);
	      rightarm.addBox(-3F, -2F, -2F, 4, 22, 4);
	      rightarm.setRotationPoint(-5F, 0F, 0F);
	      rightarm.setTextureSize(128, 64);
	      setRotation(rightarm, 0F, 0F, 0F);
	      leftarm = new ModelRenderer(this, 72, 16);
	      leftarm.addBox(-1F, -2F, -2F, 4, 22, 4);
	      leftarm.setRotationPoint(5F, 0F, 0F);
	      leftarm.setTextureSize(128, 64);
	      setRotation(leftarm, 0F, 0F, 0F);
	      rightleg = new ModelRenderer(this, 0, 24);
	      rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
	      rightleg.setRotationPoint(-2F, 12F, 0F);
	      rightleg.setTextureSize(128, 64);
	      setRotation(rightleg, 0F, 0F, 0F);
	      leftleg = new ModelRenderer(this, 0, 40);
	      leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
	      leftleg.setRotationPoint(2F, 12F, 0F);
	      leftleg.setTextureSize(128, 64);
	      setRotation(leftleg, 0F, 0F, 0F);
	      neck = new ModelRenderer(this, 56, 16);
	      neck.addBox(0F, 0F, 0F, 4, 4, 4);
	      neck.setRotationPoint(-2F, -4F, -2F);
	      neck.setTextureSize(128, 64);
	      setRotation(neck, 0F, 0F, 0F);
	  }

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    head.render(f5);
	    body.render(f5);
	    rightarm.render(f5);
	    leftarm.render(f5);
	    rightleg.render(f5);
	    leftleg.render(f5);
	    neck.render(f5);
	}
	
	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		this.head.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.head.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	}
}