package net.journey.client.render.model.mob.euca;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelGolder extends ModelBase {

    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer scale;
    ModelRenderer scale0;
    ModelRenderer scale1;
    ModelRenderer scale2;
    ModelRenderer jaw;
    ModelRenderer foot;
    ModelRenderer foot1;
    ModelRenderer foot2;
    ModelRenderer foot3;


    public ModelGolder() {
        textureWidth = 64;
        textureHeight = 64;
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -4F, -8F, 8, 8, 8);
        head.setRotationPoint(0F, 11F, -4F);
        head.setTextureSize(64, 64);
        setRotation(head, 0F, 0F, 0F);
        body = new ModelRenderer(this, 28, 18);
        body.addBox(-5F, -10F, -7F, 10, 17, 8);
        body.setRotationPoint(0F, 12.46667F, 4F);
        body.setTextureSize(64, 64);
        setRotation(body, 1.23597F, 0F, 0F);
        leg1 = new ModelRenderer(this, 0, 34);
        leg1.addBox(-2F, 0F, -2F, 4, 6, 4);
        leg1.setRotationPoint(-3F, 18F, 7F);
        leg1.setTextureSize(64, 64);
        setRotation(leg1, -0.0349066F, 0F, 0F);
        leg2 = new ModelRenderer(this, 0, 34);
        leg2.addBox(-2F, 0F, -2F, 4, 6, 4);
        leg2.setRotationPoint(3F, 18F, 7F);
        leg2.setTextureSize(64, 64);
        setRotation(leg2, -0.0349066F, 0F, 0F);
        leg3 = new ModelRenderer(this, 0, 20);
        leg3.addBox(-2F, 18F, -2F, 4, 8, 4);
        leg3.setRotationPoint(-3F, -2F, -5F);
        leg3.setTextureSize(64, 64);
        setRotation(leg3, -0.0349066F, 0F, 0F);
        leg4 = new ModelRenderer(this, 0, 20);
        leg4.addBox(-2F, 0F, -2F, 4, 8, 4);
        leg4.setRotationPoint(3F, 16F, -5F);
        leg4.setTextureSize(64, 64);
        setRotation(leg4, -0.0349066F, 0F, 0F);
        scale = new ModelRenderer(this, 0, 54);
        scale.addBox(0F, 0F, 0F, 2, 3, 6);
        scale.setRotationPoint(-1F, 10.5F, 4F);
        scale.setTextureSize(64, 64);
        setRotation(scale, -0.3328579F, 0F, 0F);
        scale0 = new ModelRenderer(this, 0, 54);
        scale0.addBox(0F, 0F, 0F, 2, 3, 6);
        scale0.setRotationPoint(-1F, 8F, -1F);
        scale0.setTextureSize(64, 64);
        setRotation(scale0, -0.2956793F, 0F, 0F);
        scale1 = new ModelRenderer(this, 0, 54);
        scale1.addBox(0F, 0F, 0F, 2, 3, 6);
        scale1.setRotationPoint(-1F, 6F, -4F);
        scale1.setTextureSize(64, 64);
        setRotation(scale1, -0.2956793F, 0F, 0F);
        scale2 = new ModelRenderer(this, 18, 45);
        scale2.addBox(0F, 0F, 0F, 2, 6, 6);
        scale2.setRotationPoint(-1F, 4F, -10F);
        scale2.setTextureSize(64, 64);
        setRotation(scale2, -0.8533585F, 0F, 0F);
        jaw = new ModelRenderer(this, 32, 6);
        jaw.addBox(-4F, -4F, -8F, 8, 2, 8);
        jaw.setRotationPoint(0F, 18F, -3F);
        jaw.setTextureSize(64, 64);
        setRotation(jaw, 0.2974289F, 0F, 0F);
        foot = new ModelRenderer(this, 0, 48);
        foot.addBox(-2F, 0F, 0F, 4, 2, 3);
        foot.setRotationPoint(-3F, 22F, 2F);
        foot.setTextureSize(64, 64);
        setRotation(foot, 0F, 0.1047198F, 0F);
        foot1 = new ModelRenderer(this, 0, 48);
        foot1.addBox(-2F, 0F, 0F, 4, 2, 3);
        foot1.setRotationPoint(3F, 22F, 2F);
        foot1.setTextureSize(64, 64);
        setRotation(foot1, 0F, -0.1047198F, 0F);
        foot2 = new ModelRenderer(this, 0, 48);
        foot2.addBox(-2F, 0F, 0F, 4, 2, 3);
        foot2.setRotationPoint(3F, 22F, -10F);
        foot2.setTextureSize(64, 64);
        setRotation(foot2, 0F, -0.1047198F, 0F);
        foot3 = new ModelRenderer(this, 0, 48);
        foot3.addBox(-2F, 0F, 0F, 4, 2, 3);
        foot3.setRotationPoint(-3F, 22F, -10F);
        foot3.setTextureSize(64, 64);
        setRotation(foot3, 0F, 0.1047198F, 0F);

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
        leg1.render(f5);
        leg2.render(f5);
        leg3.render(f5);
        leg4.render(f5);
        scale.render(f5);
        scale0.render(f5);
        scale1.render(f5);
        scale2.render(f5);
        jaw.render(f5);
        foot.render(f5);
        foot1.render(f5);
        foot2.render(f5);
        foot3.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.foot.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.foot1.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
        this.foot2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.foot3.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
        
        this.leg1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.leg2.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
        this.leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
    }
}