package net.journey.client.render.model.mob.boss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelClacia extends ModelBase {

    ModelRenderer Body;
    ModelRenderer LeftShoulder;
    ModelRenderer RightShoulder;
    ModelRenderer RightArm;
    ModelRenderer LeftArm;
    ModelRenderer RightLeg;
    ModelRenderer LeftLeg;
    ModelRenderer Head;

    public ModelClacia() {
        textureWidth = 64;
        textureHeight = 32;

        Body = new ModelRenderer(this, 0, 0);
        Body.addBox(-4F, 0F, -4F, 8, 11, 5);
        Body.setRotationPoint(0F, 3F, 0F);
        Body.setTextureSize(64, 32);
        Body.mirror = true;
        setRotation(Body, 0F, 0F, 0F);
        LeftShoulder = new ModelRenderer(this, 26, 0);
        LeftShoulder.addBox(-3F, -2.5F, -4F, 5, 5, 5);
        LeftShoulder.setRotationPoint(7F, 4.5F, 0F);
        LeftShoulder.setTextureSize(64, 32);
        LeftShoulder.mirror = true;
        setRotation(LeftShoulder, 0F, 0F, 0F);
        RightShoulder = new ModelRenderer(this, 26, 0);
        RightShoulder.addBox(-2F, -2.5F, -4F, 5, 5, 5);
        RightShoulder.setRotationPoint(-7F, 4.5F, 0F);
        RightShoulder.setTextureSize(64, 32);
        RightShoulder.mirror = true;
        setRotation(RightShoulder, 0F, 0F, 0F);
        RightArm = new ModelRenderer(this, 12, 16);
        RightArm.addBox(-1.5F, 0F, -2F, 3, 11, 3);
        RightArm.setRotationPoint(-7F, 6F, -1F);
        RightArm.setTextureSize(64, 32);
        RightArm.mirror = true;
        setRotation(RightArm, 0F, 0F, 0F);
        LeftArm = new ModelRenderer(this, 12, 16);
        LeftArm.addBox(-1.5F, 0F, -2F, 3, 11, 3);
        LeftArm.setRotationPoint(7F, 6F, -1F);
        LeftArm.setTextureSize(64, 32);
        LeftArm.mirror = true;
        setRotation(LeftArm, 0F, 0F, 0F);
        RightLeg = new ModelRenderer(this, 0, 16);
        RightLeg.addBox(-1.5F, 0F, -1.5F, 3, 11, 3);
        RightLeg.setRotationPoint(-2.5F, 13F, -1.5F);
        RightLeg.setTextureSize(64, 32);
        RightLeg.mirror = true;
        setRotation(RightLeg, 0F, 0F, 0F);
        LeftLeg = new ModelRenderer(this, 0, 16);
        LeftLeg.addBox(-1.5F, 0F, -1.5F, 3, 11, 3);
        LeftLeg.setRotationPoint(2.5F, 13F, -1.5F);
        LeftLeg.setTextureSize(64, 32);
        LeftLeg.mirror = true;
        setRotation(LeftLeg, 0F, 0F, 0F);
        Head = new ModelRenderer(this, 26, 10);
        Head.addBox(-3F, -6F, -3F, 6, 6, 6);
        Head.setRotationPoint(0F, 3F, -1.5F);
        Head.setTextureSize(64, 32);
        Head.mirror = true;
        setRotation(Head, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Body.render(f5);
        LeftShoulder.render(f5);
        RightShoulder.render(f5);
        RightArm.render(f5);
        LeftArm.render(f5);
        RightLeg.render(f5);
        LeftLeg.render(f5);
        Head.render(f5);
    }

    protected void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.RightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 2.0F * par2 * 0.5F;
        this.LeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.RightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.LeftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        this.RightShoulder.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.LeftShoulder.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        this.Head.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.Head.rotateAngleX = par5 / (180F / (float) Math.PI);
    }
}