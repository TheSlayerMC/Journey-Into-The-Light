package net.journey.client.render.model.mob.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSmallHongo extends ModelBase {

    ModelRenderer body;
    ModelRenderer rLeg;
    ModelRenderer lLeg;
    ModelRenderer Hat;
    ModelRenderer lArm;
    ModelRenderer rArm;

    public ModelSmallHongo() {
        textureWidth = 64;
        textureHeight = 64;

        body = new ModelRenderer(this, 0, 14);
        body.addBox(0F, 0F, 0F, 5, 6, 5);
        body.setRotationPoint(-2F, 16F, -3F);
        body.setTextureSize(64, 64);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        rLeg = new ModelRenderer(this, 6, 27);
        rLeg.addBox(0F, 0F, 0F, 1, 2, 2);
        rLeg.setRotationPoint(-1.5F, 22F, -1.5F);
        rLeg.setTextureSize(64, 64);
        rLeg.mirror = true;
        setRotation(rLeg, 0F, 0F, 0F);
        lLeg = new ModelRenderer(this, 6, 27);
        lLeg.addBox(0F, 0F, 0F, 1, 2, 2);
        lLeg.setRotationPoint(1.5F, 22F, -1.5F);
        lLeg.setTextureSize(64, 64);
        lLeg.mirror = true;
        setRotation(lLeg, 0F, 0F, 0F);
        Hat = new ModelRenderer(this, 0, 0);
        Hat.addBox(0F, 0F, 0F, 9, 3, 9);
        Hat.setRotationPoint(-4F, 13F, -5F);
        Hat.setTextureSize(64, 64);
        Hat.mirror = true;
        setRotation(Hat, 0F, 0F, 0F);
        lArm = new ModelRenderer(this, 0, 27);
        lArm.addBox(0F, 0F, 0F, 1, 3, 1);
        lArm.setRotationPoint(2.5F, 18.5F, -0.5F);
        lArm.setTextureSize(64, 64);
        lArm.mirror = true;
        setRotation(lArm, 0F, 0F, -0.3490659F);
        rArm = new ModelRenderer(this, 0, 27);
        rArm.addBox(0F, 0F, 0F, 1, 3, 1);
        rArm.setRotationPoint(-2.5F, 18.1F, -0.5F);
        rArm.setTextureSize(64, 64);
        rArm.mirror = true;
        setRotation(rArm, 0F, 0F, 0.418879F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        body.render(f5);
        rLeg.render(f5);
        lLeg.render(f5);
        Hat.render(f5);
        lArm.render(f5);
        rArm.render(f5);
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