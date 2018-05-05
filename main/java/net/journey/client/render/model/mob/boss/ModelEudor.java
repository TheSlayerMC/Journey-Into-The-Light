package net.journey.client.render.model.mob.boss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelEudor extends ModelBase {

    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer body;
    ModelRenderer neck;
    ModelRenderer headMain;
    ModelRenderer head1;
    ModelRenderer head2;
    ModelRenderer brain;

	public ModelEudor() {
		textureWidth = 128;
        textureHeight = 128;
        brain = new ModelRenderer(this, 0, 96);
        brain.setRotationPoint(0.0F, -17.0F, 0.0F);
        brain.addBox(-7.0F, -20.0F, -7.0F, 14, 13, 14, 0.0F);
        leg2 = new ModelRenderer(this, 64, 64);
        leg2.setRotationPoint(4.0F, 8.0F, -4.0F);
        leg2.addBox(0.0F, 0.0F, -8.0F, 8, 16, 8, 0.0F);
        body = new ModelRenderer(this, 48, 0);
        body.setRotationPoint(-8.0F, 0.0F, -8.0F);
        body.addBox(0.0F, 0.0F, 0.0F, 16, 12, 16, 0.0F);
        head1 = new ModelRenderer(this, 0, 32);
        head1.setRotationPoint(4.0F, -8.0F, 0.0F);
        head1.addBox(0.0F, -6.0F, -6.0F, 12, 13, 12, 0.0F);
        neck = new ModelRenderer(this, 0, 64);
        neck.setRotationPoint(-4.0F, -16.0F, -4.0F);
        neck.addBox(0.0F, 0.0F, 0.0F, 8, 16, 8, 0.0F);
        leg4 = new ModelRenderer(this, 64, 64);
        leg4.setRotationPoint(-4.0F, 8.0F, 4.0F);
        leg4.addBox(-8.0F, 0.0F, 0.0F, 8, 16, 8, 0.0F);
        leg3 = new ModelRenderer(this, 64, 64);
        leg3.setRotationPoint(-4.0F, 8.0F, -4.0F);
        leg3.addBox(-8.0F, 0.0F, -8.0F, 8, 16, 8, 0.0F);
        head2 = new ModelRenderer(this, 0, 32);
        head2.setRotationPoint(-4.0F, -8.0F, -0.0F);
        head2.addBox(-12.0F, -6.0F, -6.0F, 12, 13, 12, 0.0F);
        leg1 = new ModelRenderer(this, 64, 64);
        leg1.setRotationPoint(4.0F, 8.0F, 4.0F);
        leg1.addBox(0.0F, 0.0F, 0.0F, 8, 16, 8, 0.0F);
        headMain = new ModelRenderer(this, 50, 32);
        headMain.setRotationPoint(0.0F, -17.0F, 0.0F);
        headMain.addBox(-6.0F, -12.0F, -6.0F, 12, 13, 12, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        brain.render(f5);
        leg2.render(f5);
        body.render(f5);
        head1.render(f5);
        neck.render(f5);
        leg4.render(f5);
        leg3.render(f5);
        head2.render(f5);
        leg1.render(f5);
        headMain.render(f5);

	}

	protected void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) 
	{
		this.headMain.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.headMain.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.head1.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.head1.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.head2.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.head2.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.brain.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.brain.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		float f6;
		float f7;
	}
}