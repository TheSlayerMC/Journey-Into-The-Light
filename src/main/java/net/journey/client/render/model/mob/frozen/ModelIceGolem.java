package net.journey.client.render.model.mob.frozen;

import net.journey.entity.mob.frozen.EntityIceGolem;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModelIceGolem extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer waist;
    public ModelRenderer arm1;
    public ModelRenderer arm2;
    public ModelRenderer leg2;
    public ModelRenderer head;
    public ModelRenderer leg1;

    public ModelIceGolem() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 2.0F, -10.0F);
        this.head.addBox(-7.0F, -6.0F, -5.5F, 14, 10, 8, 0.0F);
        this.leg1 = new ModelRenderer(this, 74, 0);
        this.leg1.mirror = true;
        this.leg1.setRotationPoint(5.0F, 17.0F, 0.0F);
        this.leg1.addBox(-3.5F, -3.0F, -3.0F, 6, 10, 5, 0.0F);
        this.body = new ModelRenderer(this, 0, 40);
        this.body.setRotationPoint(0.0F, -1.0F, -7.0F);
        this.body.addBox(-9.0F, -2.0F, -6.0F, 18, 14, 11, 0.0F);
        this.setRotation(body, 0.5462880558742251F, 0.0F, 0.0F);
        this.waist = new ModelRenderer(this, 0, 70);
        this.waist.setRotationPoint(0.0F, 13.0F, 0.0F);
        this.waist.addBox(-4.5F, -3.0F, -3.0F, 9, 5, 6, 0.5F);
        this.setRotation(waist, 0.5462880558742251F, 0.0F, 0.0F);
        this.arm2 = new ModelRenderer(this, 60, 21);
        this.arm2.setRotationPoint(0.0F, 2.0F, -8.0F);
        this.arm2.addBox(-13.0F, -2.5F, -3.0F, 4, 24, 6, 0.0F);
        this.leg2 = new ModelRenderer(this, 48, 0);
        this.leg2.setRotationPoint(-4.0F, 17.0F, 0.0F);
        this.leg2.addBox(-3.5F, -3.0F, -3.0F, 6, 10, 5, 0.0F);
        this.arm1 = new ModelRenderer(this, 60, 58);
        this.arm1.mirror = true;
        this.arm1.setRotationPoint(0.0F, 2.0F, -8.0F);
        this.arm1.addBox(9.0F, -2.5F, -3.0F, 4, 24, 6, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
        this.leg1.render(f5);
        this.body.render(f5);
        this.waist.render(f5);
        this.arm2.render(f5);
        this.leg2.render(f5);
        this.arm1.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.head.rotateAngleX = headPitch * 0.017453292F;
        this.leg2.rotateAngleX = -1.5F * this.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
        this.leg1.rotateAngleX = 1.5F * this.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
        this.leg2.rotateAngleY = 0.0F;
        this.leg1.rotateAngleY = 0.0F;
    }

    @Override
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
        EntityIceGolem icegolem = (EntityIceGolem) entitylivingbaseIn;
        int i = icegolem.getAttackTimer();

        if (i > 0) {
            this.arm1.rotateAngleX = -2.0F + 1.5F * this.triangleWave((float) i - partialTickTime, 10.0F);
            this.arm2.rotateAngleX = -2.0F + 1.5F * this.triangleWave((float) i - partialTickTime, 10.0F);
        } else {
            this.arm1.rotateAngleX = (-0.2F + 1.5F * this.triangleWave(limbSwing, 13.0F))
                    * limbSwingAmount;
            this.arm2.rotateAngleX = (-0.2F - 1.5F * this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
        }
    }

    private float triangleWave(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }
}