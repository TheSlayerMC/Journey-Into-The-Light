package net.journey.client.render.model.mob.overworld;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMage extends ModelBiped {

    ModelRenderer hatBottom;
    ModelRenderer hatMiddle;
    ModelRenderer hatTop;
    ModelRenderer puff;

    public ModelMage() {
        textureWidth = 128;
        textureHeight = 64;

        bipedHead = new ModelRenderer(this, 0, 0);
        bipedHead.addBox(-4F, -8F, -4F, 8, 8, 8);
        bipedHead.setRotationPoint(0F, 0F, 0F);
        bipedHead.setTextureSize(128, 64);
        bipedHead.mirror = true;
        setRotation(bipedHead, 0F, 0F, 0F);
        bipedBody = new ModelRenderer(this, 16, 16);
        bipedBody.addBox(-4F, 0F, -2F, 8, 12, 4);
        bipedBody.setRotationPoint(0F, 0F, 0F);
        bipedBody.setTextureSize(128, 64);
        bipedBody.mirror = true;
        setRotation(bipedBody, 0F, 0F, 0F);
        bipedRightArm = new ModelRenderer(this, 40, 16);
        bipedRightArm.addBox(-3F, -2F, -2F, 4, 12, 4);
        bipedRightArm.setRotationPoint(-5F, 2F, 0F);
        bipedRightArm.setTextureSize(128, 64);
        bipedRightArm.mirror = true;
        setRotation(bipedRightArm, 0F, 0F, 0F);
        bipedLeftArm = new ModelRenderer(this, 40, 32);
        bipedLeftArm.addBox(-1F, -2F, -2F, 4, 12, 4);
        bipedLeftArm.setRotationPoint(5F, 2F, 0F);
        bipedLeftArm.setTextureSize(128, 64);
        bipedLeftArm.mirror = true;
        setRotation(bipedLeftArm, 0F, 0F, 0F);
        bipedRightLeg = new ModelRenderer(this, 0, 16);
        bipedRightLeg.addBox(-2F, 0F, -2F, 4, 12, 4);
        bipedRightLeg.setRotationPoint(-2F, 12F, 0F);
        bipedRightLeg.setTextureSize(128, 64);
        bipedRightLeg.mirror = true;
        setRotation(bipedRightLeg, 0F, 0F, 0F);
        bipedLeftLeg = new ModelRenderer(this, 0, 16);
        bipedLeftLeg.addBox(-2F, 0F, -2F, 4, 12, 4);
        bipedLeftLeg.setRotationPoint(2F, 12F, 0F);
        bipedLeftLeg.setTextureSize(128, 64);
        bipedLeftLeg.mirror = true;
        setRotation(bipedLeftLeg, 0F, 0F, 0F);
        hatBottom = new ModelRenderer(this, 56, 0);
        hatBottom.addBox(0F, 0F, 0F, 10, 2, 10);
        hatBottom.setRotationPoint(-5F, -9F, -4.8F);
        hatBottom.setTextureSize(128, 64);
        hatBottom.mirror = true;
        setRotation(hatBottom, -0.0349066F, 0F, 0F);
        hatMiddle = new ModelRenderer(this, 96, 0);
        hatMiddle.addBox(0F, 0F, 0F, 8, 5, 8);
        hatMiddle.setRotationPoint(-4F, -13.5F, -3.5F);
        hatMiddle.setTextureSize(128, 64);
        hatMiddle.mirror = true;
        setRotation(hatMiddle, -0.122173F, 0F, 0F);
        hatTop = new ModelRenderer(this, 32, 0);
        hatTop.addBox(0F, 0F, 0F, 6, 4, 6);
        hatTop.setRotationPoint(-3F, -17F, -1.8F);
        hatTop.setTextureSize(128, 64);
        hatTop.mirror = true;
        setRotation(hatTop, -0.2268928F, 0F, 0F);
        puff = new ModelRenderer(this, 58, 18);
        puff.addBox(0F, 0F, 0F, 2, 2, 2);
        puff.setRotationPoint(-1F, -18F, 0.5F);
        puff.setTextureSize(128, 64);
        puff.mirror = true;
        setRotation(puff, -0.2268928F, 0F, 0F);
        this.bipedHead.addChild(hatBottom);
        this.bipedHead.addChild(hatMiddle);
        this.bipedHead.addChild(hatTop);
        this.bipedHead.addChild(puff);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        bipedHead.render(f5);
        bipedBody.render(f5);
        bipedRightArm.render(f5);
        bipedLeftArm.render(f5);
        bipedRightLeg.render(f5);
        bipedLeftLeg.render(f5);
        bipedHeadwear.isHidden = true;
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}