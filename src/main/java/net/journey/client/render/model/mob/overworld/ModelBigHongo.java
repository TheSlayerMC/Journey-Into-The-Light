package net.journey.client.render.model.mob.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBigHongo extends ModelBase {

    private ModelRenderer body;
    private ModelRenderer leftFrontLeg;
    private ModelRenderer rightFrontLeg;
    private ModelRenderer backLeftLeg;
    private ModelRenderer backRightLeg;
    private ModelRenderer bottomHat;
    private ModelRenderer topHat;

    public ModelBigHongo() {
        textureWidth = 256;
        textureHeight = 64;
        body = new ModelRenderer(this, 0, 38);
        body.addBox(0F, 0F, 0F, 12, 14, 11);
        body.setRotationPoint(-6F, 2F, -4F);
        body.setTextureSize(256, 64);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        leftFrontLeg = new ModelRenderer(this, 49, 38);
        leftFrontLeg.addBox(-3F, 0F, -6F, 6, 9, 6);
        leftFrontLeg.setRotationPoint(-6F, 15F, -1F);
        leftFrontLeg.setTextureSize(256, 64);
        leftFrontLeg.mirror = true;
        setRotation(leftFrontLeg, 0F, 0F, 0F);
        rightFrontLeg = new ModelRenderer(this, 49, 38);
        rightFrontLeg.addBox(-3F, 0F, -6F, 6, 9, 6);
        rightFrontLeg.setRotationPoint(6F, 15F, -1F);
        rightFrontLeg.setTextureSize(256, 64);
        rightFrontLeg.mirror = true;
        setRotation(rightFrontLeg, 0F, 0F, 0F);
        backLeftLeg = new ModelRenderer(this, 76, 38);
        backLeftLeg.addBox(-3F, 0F, 0F, 6, 8, 6);
        backLeftLeg.setRotationPoint(-6F, 16F, 4F);
        backLeftLeg.setTextureSize(256, 64);
        backLeftLeg.mirror = true;
        setRotation(backLeftLeg, 0F, 0F, 0F);
        backRightLeg = new ModelRenderer(this, 76, 38);
        backRightLeg.addBox(-3F, 0F, 0F, 6, 8, 6);
        backRightLeg.setRotationPoint(6F, 16F, 4F);
        backRightLeg.setTextureSize(256, 64);
        backRightLeg.mirror = true;
        setRotation(backRightLeg, 0F, 0F, 0F);
        bottomHat = new ModelRenderer(this, 1, 1);
        bottomHat.addBox(0F, 0F, 0F, 21, 7, 21);
        bottomHat.setRotationPoint(-10.5F, -4F, -8.5F);
        bottomHat.setTextureSize(256, 64);
        bottomHat.mirror = true;
        setRotation(bottomHat, 0F, 0F, 0F);
        topHat = new ModelRenderer(this, 92, 1);
        topHat.addBox(0F, 0F, 0F, 18, 5, 16);
        topHat.setRotationPoint(-9F, -7F, -5.8F);
        topHat.setTextureSize(256, 64);
        topHat.mirror = true;
        setRotation(topHat, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        body.render(f5);
        leftFrontLeg.render(f5);
        rightFrontLeg.render(f5);
        backLeftLeg.render(f5);
        backRightLeg.render(f5);
        bottomHat.render(f5);
        topHat.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        this.leftFrontLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.rightFrontLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
        this.backLeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
        this.backRightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }
}