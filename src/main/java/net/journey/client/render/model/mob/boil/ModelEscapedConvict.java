package net.journey.client.render.model.mob.boil;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelEscapedConvict extends ModelBase {

	public ModelRenderer head;
    public ModelRenderer arm1;
    public ModelRenderer leg1;
    public ModelRenderer body;
    public ModelRenderer arm2;
    public ModelRenderer leg2;
    public ModelRenderer horn1;
    public ModelRenderer horn2;

    public ModelEscapedConvict() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.leg2 = new ModelRenderer(this, 16, 48);
        this.leg2.setRotationPoint(1.9F, 12.0F, 0.1F);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.horn2 = new ModelRenderer(this, 0, 36);
        this.horn2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.horn2.addBox(-8.0F, -10.0F, 0.0F, 2, 4, 2, 0.0F);
        this.arm1 = new ModelRenderer(this, 40, 16);
        this.arm1.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.arm1.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotation(arm1, 0.0F, 0.0F, 0.0F);
        this.arm2 = new ModelRenderer(this, 32, 48);
        this.arm2.setRotationPoint(5.0F, 2.0F, -0.0F);
        this.arm2.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotation(arm2, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 16, 16);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.leg1 = new ModelRenderer(this, 0, 16);
        this.leg1.setRotationPoint(-1.9F, 12.0F, 0.1F);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.horn1 = new ModelRenderer(this, 0, 36);
        this.horn1.setRotationPoint(0.0F, -0.0F, 0.0F);
        this.horn1.addBox(6.0F, -10.0F, 0.0F, 2, 4, 2, 0.0F);
        this.head = new ModelRenderer(this, -2, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-6.0F, -6.6F, -4.0F, 12, 6, 8, 0.5F);
    }


	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		head.render(f5);
		body.render(f5);
		arm1.render(f5);
		arm2.render(f5);
		leg1.render(f5);
		leg2.render(f5);
		horn1.render(f5);
		horn2.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		this.leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
		this.leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
		this.arm1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.arm2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.head.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.head.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.horn1.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.horn1.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.horn2.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.horn2.rotateAngleX = par5 / (180F / (float)Math.PI);
	}
}