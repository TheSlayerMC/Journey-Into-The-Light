package net.journey.client.render.model.mob.senterian;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSentryLord extends ModelBase {
	
    public ModelRenderer frontleg1;
    public ModelRenderer backleg1;
    public ModelRenderer head;
    public ModelRenderer frontleg2;
    public ModelRenderer backleg2;
    public ModelRenderer torso;

    public ModelSentryLord() {
        this.textureWidth = 128;
        this.textureHeight = 32;
        this.head = new ModelRenderer(this, 64, 0);
        this.head.setRotationPoint(-0.0F, -8.0F, 0.0F);
        this.head.addBox(-8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F);
        this.torso = new ModelRenderer(this, 32, 0);
        this.torso.setRotationPoint(-0.0F, -8.0F, 0.0F);
        this.torso.addBox(-4.0F, 0.0F, -4.0F, 8, 16, 8, 0.0F);
        this.backleg2 = new ModelRenderer(this, 0, 0);
        this.backleg2.mirror = true;
        this.backleg2.setRotationPoint(-4.0F, 8.0F, 4.0F);
        this.backleg2.addBox(-4.0F, 0.0F, -4.0F, 8, 16, 8, 0.0F);
        this.frontleg1 = new ModelRenderer(this, 0, 0);
        this.frontleg1.mirror = true;
        this.frontleg1.setRotationPoint(4.0F, 8.0F, -4.0F);
        this.frontleg1.addBox(-4.0F, 0.0F, -4.0F, 8, 16, 8, 0.0F);
        this.frontleg2 = new ModelRenderer(this, 0, 0);
        this.frontleg2.setRotationPoint(-4.0F, 8.0F, -4.0F);
        this.frontleg2.addBox(-4.0F, 0.0F, -4.0F, 8, 16, 8, 0.0F);
        this.backleg1 = new ModelRenderer(this, 0, 0);
        this.backleg1.setRotationPoint(4.0F, 8.0F, 4.0F);
        this.backleg1.addBox(-4.0F, 0.0F, -4.0F, 8, 16, 8, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
    	this.head.render(f5);
        this.torso.render(f5);
        this.backleg2.render(f5);
        this.frontleg1.render(f5);
        this.frontleg2.render(f5);
        this.backleg1.render(f5);
    }
    
    @Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		//this.head.rotateAngleY = f4 / (180F / (float)Math.PI);
		//this.head.rotateAngleX = f5 / (180F / (float)Math.PI);
		this.head.rotateAngleY = f2/30;
		this.frontleg1.rotateAngleX = MathHelper.cos(f * 0.0662F) * 1.4F * f1;
		this.backleg1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
		this.frontleg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
		this.backleg2.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
