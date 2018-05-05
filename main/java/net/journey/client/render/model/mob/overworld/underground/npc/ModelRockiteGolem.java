package net.journey.client.render.model.mob.overworld.underground.npc;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelRockiteGolem extends ModelBase {
	
    public ModelRenderer body;
    public ModelRenderer lowerBody;
    public ModelRenderer arm1;
    public ModelRenderer arm2;
    public ModelRenderer leg1;
    public ModelRenderer head;
    public ModelRenderer leg2;

    public ModelRockiteGolem() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.arm1 = new ModelRenderer(this, 60, 58);
        this.arm1.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.arm1.addBox(9.0F, -3.0F, -3.0F, 4, 22, 6, 0.0F);
        this.leg2 = new ModelRenderer(this, 60, 0);
        this.leg2.mirror = true;
        this.leg2.setRotationPoint(5.0F, 11.0F, 0.0F);
        this.leg2.addBox(-3.5F, -3.0F, -3.0F, 6, 16, 5, 0.0F);
        this.arm2 = new ModelRenderer(this, 60, 21);
        this.arm2.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.arm2.addBox(-13.0F, -3.0F, -3.0F, 4, 22, 6, 0.0F);
        this.leg1 = new ModelRenderer(this, 37, 0);
        this.leg1.setRotationPoint(-4.0F, 11.0F, 0.0F);
        this.leg1.addBox(-3.5F, -3.0F, -3.0F, 6, 16, 5, 0.0F);
        this.body = new ModelRenderer(this, 0, 40);
        this.body.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.body.addBox(-9.0F, -2.0F, -6.0F, 18, 12, 11, 0.0F);
        this.lowerBody = new ModelRenderer(this, 0, 70);
        this.lowerBody.setRotationPoint(-0.5F, -7.0F, 0.0F);
        this.lowerBody.addBox(-4.5F, 10.0F, -3.5F, 10, 7, 6, 0.5F);
        this.head = new ModelRenderer(this, 0, 100);
        this.head.setRotationPoint(0.0F, -7.0F, -2.0F);
        this.head.addBox(-8.0F, -6.0F, -8.0F, 16, 10, 8, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.arm1.render(f5);
        this.leg2.render(f5);
        this.arm2.render(f5);
        this.leg1.render(f5);
        this.body.render(f5);
        this.lowerBody.render(f5);
        this.head.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    @Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		this.head.rotateAngleY = f4 / (180F / (float)Math.PI);
		this.head.rotateAngleX = f5 / (180F / (float)Math.PI);
		this.leg1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
		this.leg2.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
		this.arm1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		this.arm2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
    }
}