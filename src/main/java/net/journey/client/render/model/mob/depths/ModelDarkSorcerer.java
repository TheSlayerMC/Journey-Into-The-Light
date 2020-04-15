package net.journey.client.render.model.mob.depths;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelDarkSorcerer extends ModelBase {

    ModelRenderer Head;
    ModelRenderer Body;
    ModelRenderer RightArm;
    ModelRenderer LeftArm;
    ModelRenderer RightLeg;
    ModelRenderer LeftLeg;
    ModelRenderer hood;

    public ModelDarkSorcerer() {
        textureWidth = 64;
        textureHeight = 64;

        Head = new ModelRenderer(this, 0, 0);
        Head.addBox(-4F, -8F, -4F, 8, 8, 8);
        Head.setRotationPoint(0F, -10F, -1F);
        Head.setTextureSize(64, 64);
        setRotation(Head, 0F, 0F, 0F);
        Body = new ModelRenderer(this, 32, 16);
        Body.addBox(-4F, 0F, -2F, 8, 14, 4);
        Body.setRotationPoint(0F, -10F, 0F);
        Body.setTextureSize(64, 64);
        setRotation(Body, 0F, 0F, 0F);
        RightArm = new ModelRenderer(this, 56, 32);
        RightArm.addBox(-1F, 0F, -1F, 2, 30, 2);
        RightArm.setRotationPoint(-5F, -14F, 0F);
        RightArm.setTextureSize(64, 64);
        setRotation(RightArm, 0F, 0F, 0F);
        LeftArm = new ModelRenderer(this, 56, 32);
        LeftArm.addBox(-1F, -2F, -1F, 2, 30, 2);
        LeftArm.setRotationPoint(5F, -12F, 0F);
        LeftArm.setTextureSize(64, 64);
        setRotation(LeftArm, 0F, 0F, 0F);
        RightLeg = new ModelRenderer(this, 56, 0);
        RightLeg.addBox(-1F, 0F, -1F, 2, 20, 2);
        RightLeg.setRotationPoint(-2F, 4F, 0F);
        RightLeg.setTextureSize(64, 64);
        setRotation(RightLeg, 0F, 0F, 0F);
        LeftLeg = new ModelRenderer(this, 56, 0);
        LeftLeg.addBox(-1F, 0F, -1F, 2, 20, 2);
        LeftLeg.setRotationPoint(2F, 4F, 0F);
        LeftLeg.setTextureSize(64, 64);
        setRotation(LeftLeg, 0F, 0F, 0F);
        hood = new ModelRenderer(this, 0, 34);
        hood.addBox(-4.5F, -9F, -4.5F, 9, 9, 9);
        hood.setRotationPoint(0F, -9.5F, 0F);
        hood.setTextureSize(64, 64);
        setRotation(hood, -0.0436332F, 0F, 0F);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Head.render(f5);
        Body.render(f5);
        RightArm.render(f5);
        LeftArm.render(f5);
        RightLeg.render(f5);
        LeftLeg.render(f5);
        hood.render(f5);

    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.Head.rotateAngleY = f4 / (180F / (float) Math.PI);
        this.hood.rotateAngleY = f4 / (180F / (float) Math.PI);
        this.RightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.LeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
        this.RightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.LeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;

    }
}