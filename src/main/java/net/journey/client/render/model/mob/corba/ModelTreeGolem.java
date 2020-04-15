package net.journey.client.render.model.mob.corba;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelTreeGolem extends ModelBase {

    ModelRenderer foot1;
    ModelRenderer foot2;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer body;
    ModelRenderer arm1;
    ModelRenderer arm2;
    ModelRenderer hand1;
    ModelRenderer hand2;
    ModelRenderer head;

    public ModelTreeGolem() {
        textureWidth = 128;
        textureHeight = 64;

        foot1 = new ModelRenderer(this, 0, 0);
        foot1.addBox(-3F, 10F, -3F, 6, 8, 6);
        foot1.setRotationPoint(5F, 6F, 5F);
        foot1.setTextureSize(128, 64);
        foot1.mirror = true;
        setRotation(foot1, 0F, 0F, 0F);
        foot2 = new ModelRenderer(this, 0, 42);
        foot2.addBox(-3F, 10F, -3F, 6, 8, 6);
        foot2.setRotationPoint(-5F, 6F, 5F);
        foot2.setTextureSize(128, 64);
        foot2.mirror = true;
        setRotation(foot2, 0F, 0F, 0F);
        leg1 = new ModelRenderer(this, 81, 23);
        leg1.addBox(-2F, 0F, -2F, 4, 10, 4);
        leg1.setRotationPoint(5F, 6F, 5F);
        leg1.setTextureSize(128, 64);
        leg1.mirror = true;
        setRotation(leg1, 0F, 0F, 0F);
        leg2 = new ModelRenderer(this, 64, 23);
        leg2.addBox(-2F, 0F, -2F, 4, 10, 4);
        leg2.setRotationPoint(-5F, 6F, 5F);
        leg2.setTextureSize(128, 64);
        leg2.mirror = true;
        setRotation(leg2, 0F, 0F, 0F);
        body = new ModelRenderer(this, 99, 0);
        body.addBox(0F, 0F, 0F, 6, 19, 6);
        body.setRotationPoint(-3F, 3F, -9F);
        body.setTextureSize(128, 64);
        body.mirror = true;
        setRotation(body, 1.003822F, 0F, 0F);
        arm1 = new ModelRenderer(this, 81, 0);
        arm1.addBox(-2F, 0F, -2F, 4, 17, 4);
        arm1.setRotationPoint(5F, -1F, -4F);
        arm1.setTextureSize(128, 64);
        arm1.mirror = true;
        setRotation(arm1, 0F, 0F, 0F);
        arm2 = new ModelRenderer(this, 64, 0);
        arm2.addBox(-2F, 0F, -2F, 4, 17, 4);
        arm2.setRotationPoint(-5F, -1F, -4F);
        arm2.setTextureSize(128, 64);
        arm2.mirror = true;
        setRotation(arm2, 0F, 0F, 0F);
        hand1 = new ModelRenderer(this, 0, 14);
        hand1.addBox(-3F, 17F, -3F, 6, 8, 6);
        hand1.setRotationPoint(5F, -1F, -4F);
        hand1.setTextureSize(128, 64);
        hand1.mirror = true;
        setRotation(hand1, 0F, 0F, 0F);
        hand2 = new ModelRenderer(this, 0, 28);
        hand2.addBox(-3F, 17F, -3F, 6, 8, 6);
        hand2.setRotationPoint(-5F, -1F, -4F);
        hand2.setTextureSize(128, 64);
        hand2.mirror = true;
        setRotation(hand2, 0F, 0F, 0F);
        head = new ModelRenderer(this, 24, 0);
        head.addBox(-5F, -6F, -10F, 10, 10, 10);
        head.setRotationPoint(0F, 2F, -6F);
        head.setTextureSize(128, 64);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        foot1.render(f5);
        foot2.render(f5);
        leg1.render(f5);
        leg2.render(f5);
        body.render(f5);
        arm1.render(f5);
        arm2.render(f5);
        hand1.render(f5);
        hand2.render(f5);
        head.render(f5);

    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.head.rotateAngleY = f1 / (180F / (float) Math.PI);
        this.head.rotateAngleX = f1 / (180F / (float) Math.PI);
        this.arm2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.arm1.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
        this.leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
        this.foot2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.foot1.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
        this.hand2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.hand1.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;

    }
}