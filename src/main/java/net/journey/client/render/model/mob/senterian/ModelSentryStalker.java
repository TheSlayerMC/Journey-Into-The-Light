package net.journey.client.render.model.mob.senterian;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSentryStalker extends ModelBase {
	
    public ModelRenderer top;
    public ModelRenderer torso;
    public ModelRenderer backleg2;
    public ModelRenderer backleg1;
    public ModelRenderer frontleg2;
    public ModelRenderer frontleg1;
    public ModelRenderer head2;
    public ModelRenderer head1;

    public ModelSentryStalker() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.top = new ModelRenderer(this, 48, 32);
        this.top.setRotationPoint(-0.0F, -24.0F, 0.0F);
        this.top.addBox(-12.0F, -16.0F, -8.0F, 24, 4, 16, 0.0F);
        this.head2 = new ModelRenderer(this, 64, 64);
        this.head2.setRotationPoint(-0.0F, 2.0F, 0.0F);
        this.head2.addBox(-7.0F, -16.0F, -7.0F, 14, 14, 14, 0.0F);
        this.backleg2 = new ModelRenderer(this, 0, 0);
        this.backleg2.setRotationPoint(-8.0F, -36.0F, 6.0F);
        this.backleg2.addBox(-4.0F, 0.0F, -2.0F, 4, 60, 4, 0.0F);
        this.head1 = new ModelRenderer(this, 64, 0);
        this.head1.setRotationPoint(-0.0F, -20.0F, 0.0F);
        this.head1.addBox(-7.0F, -16.0F, -7.0F, 14, 14, 14, 0.0F);
        this.backleg1 = new ModelRenderer(this, 0, 0);
        this.backleg1.setRotationPoint(8.0F, -36.0F, 6.0F);
        this.backleg1.addBox(0.0F, 0.0F, -2.0F, 4, 60, 4, 0.0F);
        this.torso = new ModelRenderer(this, 16, 0);
        this.torso.setRotationPoint(-0.0F, -22.0F, 0.0F);
        this.torso.addBox(-4.0F, 0.0F, -4.0F, 8, 8, 8, 0.0F);
        this.frontleg1 = new ModelRenderer(this, 0, 0);
        this.frontleg1.setRotationPoint(8.0F, -36.0F, -6.0F);
        this.frontleg1.addBox(0.0F, 0.0F, -2.0F, 4, 60, 4, 0.0F);
        this.frontleg2 = new ModelRenderer(this, 0, 0);
        this.frontleg2.setRotationPoint(-8.0F, -36.0F, -6.0F);
        this.frontleg2.addBox(-4.0F, 0.0F, -2.0F, 4, 60, 4, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.top.render(f5);
        this.head2.render(f5);
        this.backleg2.render(f5);
        this.head1.render(f5);
        this.backleg1.render(f5);
        this.torso.render(f5);
        this.frontleg1.render(f5);
        this.frontleg2.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    @Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		this.head1.rotateAngleY = f4 / (180F / (float)Math.PI);
		this.head1.rotateAngleX = f5 / (180F / (float)Math.PI);
		
		this.head2.rotateAngleY = f2/15;
		
		this.torso.rotateAngleY = f4 / (180F / (float)Math.PI);
		this.torso.rotateAngleX = f5 / (180F / (float)Math.PI);
		
		this.frontleg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		this.frontleg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
		this.backleg1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
		this.backleg2.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }
}
