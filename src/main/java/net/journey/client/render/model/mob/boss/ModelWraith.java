package net.journey.client.render.model.mob.boss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWraith extends ModelBase {

    ModelRenderer BodyTop;
    ModelRenderer Body2;
    ModelRenderer Head;
    ModelRenderer Body3;
    ModelRenderer Body4;
    ModelRenderer Body5;
    ModelRenderer Body6;
    ModelRenderer ArmLeftTop1;
    ModelRenderer ArmLeftTop2;
    ModelRenderer HandLeftTop;
    ModelRenderer ArmLeftBottom1;
    ModelRenderer ArmLeftBottom2;
    ModelRenderer HandLeftBottom;
    ModelRenderer ArmRightTop1;
    ModelRenderer ArmRightTop2;
    ModelRenderer HandRightTop;
    ModelRenderer ArmRightBottom1;
    ModelRenderer ArmRightBottom2;
    ModelRenderer handRightBottom;

    public ModelWraith() {
        textureWidth = 256;
        textureHeight = 64;

        BodyTop = new ModelRenderer(this, 111, 0);
        BodyTop.addBox(0F, 0F, 0F, 23, 15, 14);
        BodyTop.setRotationPoint(-11F, -29F, -6F);
        BodyTop.setTextureSize(256, 64);
        BodyTop.mirror = true;
        setRotation(BodyTop, 0F, 0F, 0F);
        Body2 = new ModelRenderer(this, 56, 0);
        Body2.addBox(0F, 0F, 0F, 16, 10, 11);
        Body2.setRotationPoint(-7F, -14F, -4F);
        Body2.setTextureSize(256, 64);
        Body2.mirror = true;
        setRotation(Body2, 0F, 0F, 0F);
        Head = new ModelRenderer(this, 19, 0);
        Head.addBox(0F, 0F, 0F, 10, 8, 8);
        Head.setRotationPoint(-4F, -37F, -4F);
        Head.setTextureSize(256, 64);
        Head.mirror = true;
        setRotation(Head, 0F, 0F, 0F);
        Body3 = new ModelRenderer(this, 82, 22);
        Body3.addBox(0F, 0F, 0F, 8, 8, 6);
        Body3.setRotationPoint(-3F, -4F, -1.5F);
        Body3.setTextureSize(256, 64);
        Body3.mirror = true;
        setRotation(Body3, 0.0872665F, 0F, 0F);
        Body4 = new ModelRenderer(this, 58, 22);
        Body4.addBox(0F, 0F, 0F, 7, 8, 5);
        Body4.setRotationPoint(-2.5F, 4F, -0.5F);
        Body4.setTextureSize(256, 64);
        Body4.mirror = true;
        setRotation(Body4, 0.1570796F, 0F, 0F);
        Body5 = new ModelRenderer(this, 42, 22);
        Body5.addBox(0F, 0F, 0F, 5, 8, 3);
        Body5.setRotationPoint(-1.5F, 10F, 1.5F);
        Body5.setTextureSize(256, 64);
        Body5.mirror = true;
        setRotation(Body5, 0.3141593F, 0F, 0F);
        Body6 = new ModelRenderer(this, 32, 22);
        Body6.addBox(0F, 0F, 0F, 3, 8, 2);
        Body6.setRotationPoint(-0.5F, 15F, 3.5F);
        Body6.setTextureSize(256, 64);
        Body6.mirror = true;
        setRotation(Body6, 0.4712389F, 0F, 0F);
        ArmLeftTop1 = new ModelRenderer(this, 0, 17);
        ArmLeftTop1.addBox(0F, 0F, 0F, 13, 4, 3);
        ArmLeftTop1.setRotationPoint(11F, -29.5F, 0F);
        ArmLeftTop1.setTextureSize(256, 64);
        ArmLeftTop1.mirror = true;
        setRotation(ArmLeftTop1, 0F, 0.296706F, -0.1745329F);
        ArmLeftTop2 = new ModelRenderer(this, 0, 24);
        ArmLeftTop2.addBox(0F, 0F, 0F, 12, 4, 3);
        ArmLeftTop2.setRotationPoint(22F, -31.5F, -3F);
        ArmLeftTop2.setTextureSize(256, 64);
        ArmLeftTop2.mirror = true;
        setRotation(ArmLeftTop2, 0.1396263F, 0.4712389F, -0.3665191F);
        HandLeftTop = new ModelRenderer(this, 0, 31);
        HandLeftTop.addBox(0F, 0F, 0F, 12, 4, 1);
        HandLeftTop.setRotationPoint(31F, -35.5F, -7F);
        HandLeftTop.setTextureSize(256, 64);
        HandLeftTop.mirror = true;
        setRotation(HandLeftTop, 0.2094395F, 0.7504916F, -0.3665191F);
        ArmLeftBottom1 = new ModelRenderer(this, 0, 17);
        ArmLeftBottom1.addBox(0F, 0F, 0F, 13, 4, 3);
        ArmLeftBottom1.setRotationPoint(11F, -18.5F, 0F);
        ArmLeftBottom1.setTextureSize(256, 64);
        ArmLeftBottom1.mirror = true;
        setRotation(ArmLeftBottom1, 0F, 0.296706F, 0.1396263F);
        ArmLeftBottom2 = new ModelRenderer(this, 0, 24);
        ArmLeftBottom2.addBox(0F, 0F, 0F, 12, 4, 3);
        ArmLeftBottom2.setRotationPoint(22F, -16.5F, -3F);
        ArmLeftBottom2.setTextureSize(256, 64);
        ArmLeftBottom2.mirror = true;
        setRotation(ArmLeftBottom2, 0.1396263F, 0.4712389F, 0.122173F);
        HandLeftBottom = new ModelRenderer(this, 0, 31);
        HandLeftBottom.addBox(0F, 0F, 0F, 12, 4, 1);
        HandLeftBottom.setRotationPoint(31F, -15.5F, -7F);
        HandLeftBottom.setTextureSize(256, 64);
        HandLeftBottom.mirror = true;
        setRotation(HandLeftBottom, 0.1745329F, 0.7504916F, 0.1745329F);
        ArmRightTop1 = new ModelRenderer(this, 0, 36);
        ArmRightTop1.addBox(-13F, -1F, 0F, 13, 4, 3);
        ArmRightTop1.setRotationPoint(-9F, -28F, 0F);
        ArmRightTop1.setTextureSize(256, 64);
        ArmRightTop1.mirror = true;
        setRotation(ArmRightTop1, 0.0349066F, -0.2094395F, 0.1745329F);
        ArmRightTop2 = new ModelRenderer(this, 0, 43);
        ArmRightTop2.addBox(-12F, -2F, 0F, 12, 4, 3);
        ArmRightTop2.setRotationPoint(-21F, -29F, -2.5F);
        ArmRightTop2.setTextureSize(256, 64);
        ArmRightTop2.mirror = true;
        setRotation(ArmRightTop2, 0.0349066F, -0.3839724F, 0.2094395F);
        HandRightTop = new ModelRenderer(this, 33, 38);
        HandRightTop.addBox(-10.8F, -2F, 0F, 12, 4, 1);
        HandRightTop.setRotationPoint(-33F, -32F, -7F);
        HandRightTop.setTextureSize(256, 64);
        HandRightTop.mirror = true;
        setRotation(HandRightTop, 0F, -0.5759587F, 0.2268928F);
        ArmRightBottom1 = new ModelRenderer(this, 0, 36);
        ArmRightBottom1.addBox(-12F, 0F, 0F, 13, 4, 3);
        ArmRightBottom1.setRotationPoint(-12F, -18.5F, -1F);
        ArmRightBottom1.setTextureSize(256, 64);
        ArmRightBottom1.mirror = true;
        setRotation(ArmRightBottom1, 0.0349066F, -0.1745329F, -0.1570796F);
        ArmRightBottom2 = new ModelRenderer(this, 0, 43);
        ArmRightBottom2.addBox(-12F, 0F, 0F, 12, 4, 3);
        ArmRightBottom2.setRotationPoint(-23F, -16.5F, -3F);
        ArmRightBottom2.setTextureSize(256, 64);
        ArmRightBottom2.mirror = true;
        setRotation(ArmRightBottom2, 0.0349066F, -0.418879F, -0.1745329F);
        handRightBottom = new ModelRenderer(this, 33, 38);
        handRightBottom.addBox(-11F, -2F, 0F, 12, 4, 1);
        handRightBottom.setRotationPoint(-33F, -12.5F, -7F);
        handRightBottom.setTextureSize(256, 64);
        handRightBottom.mirror = true;
        setRotation(handRightBottom, 0.0174533F, -0.6632251F, -0.3141593F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        BodyTop.render(f5);
        Body2.render(f5);
        Head.render(f5);
        Body3.render(f5);
        Body4.render(f5);
        Body5.render(f5);
        Body6.render(f5);
        ArmLeftTop1.render(f5);
        ArmLeftTop2.render(f5);
        HandLeftTop.render(f5);
        ArmLeftBottom1.render(f5);
        ArmLeftBottom2.render(f5);
        HandLeftBottom.render(f5);
        ArmRightTop1.render(f5);
        ArmRightTop2.render(f5);
        HandRightTop.render(f5);
        ArmRightBottom1.render(f5);
        ArmRightBottom2.render(f5);
        handRightBottom.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
    }
}