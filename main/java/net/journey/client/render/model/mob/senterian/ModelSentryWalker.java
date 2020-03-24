package net.journey.client.render.model.mob.senterian;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSentryWalker extends ModelBase {
	
    public ModelRenderer head;
    public ModelRenderer leg2;
    public ModelRenderer torso;
    public ModelRenderer leg1;
    public ModelRenderer backpanel;

    public ModelSentryWalker() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.leg2 = new ModelRenderer(this, 0, 0);
        this.leg2.setRotationPoint(4.0F, -8.0F, 2.0F);
        this.leg2.addBox(-0.0F, 0.0F, -4.0F, 4, 32, 4, 0.0F);
        this.torso = new ModelRenderer(this, 16, 0);
        this.torso.setRotationPoint(-0.0F, -24.0F, 0.0F);
        this.torso.addBox(-4.0F, 0.0F, -4.0F, 8, 32, 8, 0.0F);
        this.leg1 = new ModelRenderer(this, 0, 0);
        this.leg1.setRotationPoint(-4.0F, -8.0F, 2.0F);
        this.leg1.addBox(-4.0F, 0.0F, -4.0F, 4, 32, 4, 0.0F);
        this.backpanel = new ModelRenderer(this, 0, 40);
        this.backpanel.setRotationPoint(-12.0F, -40.0F, 4.0F);
        this.backpanel.addBox(-4.0F, 0.0F, -4.0F, 32, 48, 0, 0.0F);
        this.head = new ModelRenderer(this, 64, 0);
        this.head.setRotationPoint(-0.0F, -24.0F, 0.0F);
        this.head.addBox(-8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.leg2.render(f5);
        this.torso.render(f5);
        this.leg1.render(f5);
        this.backpanel.render(f5);
        this.head.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    	this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
    }
}
