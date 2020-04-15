package net.journey.client.render.model.mob.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBunny extends ModelBase {

    ModelRenderer Body;
    ModelRenderer Head;
    ModelRenderer FrontLeftLeg;
    ModelRenderer BackLeftLeg;
    ModelRenderer FrontRightLeg;
    ModelRenderer BackRightLeg;
    ModelRenderer Tail;
    ModelRenderer LeftEar;
    ModelRenderer RightEar;

    public ModelBunny() {
        textureWidth = 128;
        textureHeight = 64;

        Body = new ModelRenderer(this, 0, 0);
        Body.addBox(-1.5F, -1F, 0F, 3, 3, 4);
        Body.setRotationPoint(0F, 20F, 0F);
        Body.setTextureSize(128, 64);
        Body.mirror = true;
        setRotation(Body, 0F, 0F, 0F);
        Head = new ModelRenderer(this, 15, 14);
        Head.addBox(-1F, -1F, -3F, 3, 3, 3);
        Head.setRotationPoint(-0.5F, 20F, 0F);
        Head.setTextureSize(128, 64);
        Head.mirror = true;
        setRotation(Head, 0F, 0F, 0F);
        FrontLeftLeg = new ModelRenderer(this, 0, 11);
        FrontLeftLeg.addBox(-0.5F, -1F, -0.5F, 1, 2, 1);
        FrontLeftLeg.setRotationPoint(1F, 23F, 0.5F);
        FrontLeftLeg.setTextureSize(128, 64);
        FrontLeftLeg.mirror = true;
        setRotation(FrontLeftLeg, 0F, 0F, 0F);
        BackLeftLeg = new ModelRenderer(this, 0, 11);
        BackLeftLeg.addBox(-0.5F, -1F, -0.5F, 1, 2, 1);
        BackLeftLeg.setRotationPoint(1F, 23F, 3.5F);
        BackLeftLeg.setTextureSize(128, 64);
        BackLeftLeg.mirror = true;
        setRotation(BackLeftLeg, 0F, 0F, 0F);
        FrontRightLeg = new ModelRenderer(this, 0, 11);
        FrontRightLeg.addBox(-0.5F, -1F, -0.5F, 1, 2, 1);
        FrontRightLeg.setRotationPoint(-1F, 23F, 0.5F);
        FrontRightLeg.setTextureSize(128, 64);
        FrontRightLeg.mirror = true;
        setRotation(FrontRightLeg, 0F, 0F, 0F);
        BackRightLeg = new ModelRenderer(this, 0, 11);
        BackRightLeg.addBox(-0.5F, -1F, -0.5F, 1, 2, 1);
        BackRightLeg.setRotationPoint(-1F, 23F, 3.5F);
        BackRightLeg.setTextureSize(128, 64);
        BackRightLeg.mirror = true;
        setRotation(BackRightLeg, 0F, 0F, 0F);
        Tail = new ModelRenderer(this, 4, 11);
        Tail.addBox(-0.5F, -0.5F, 0F, 1, 1, 1);
        Tail.setRotationPoint(0F, 20F, 4F);
        Tail.setTextureSize(128, 64);
        Tail.mirror = true;
        setRotation(Tail, 0F, 0F, 0F);
        LeftEar = new ModelRenderer(this, 14, 0);
        LeftEar.addBox(0.5F, -3F, -2.5F, 1, 2, 1);
        LeftEar.setRotationPoint(0F, 20F, 0F);
        LeftEar.setTextureSize(128, 64);
        LeftEar.mirror = true;
        setRotation(LeftEar, 0F, 0F, 0F);
        RightEar = new ModelRenderer(this, 14, 0);
        RightEar.addBox(-1.5F, -3F, -2.5F, 1, 2, 1);
        RightEar.setRotationPoint(0F, 20F, 0F);
        RightEar.setTextureSize(128, 64);
        RightEar.mirror = true;
        setRotation(RightEar, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Body.render(f5);
        Head.render(f5);
        FrontLeftLeg.render(f5);
        BackLeftLeg.render(f5);
        FrontRightLeg.render(f5);
        BackRightLeg.render(f5);
        Tail.render(f5);
        LeftEar.render(f5);
        RightEar.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.Head.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.Head.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.LeftEar.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.LeftEar.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.RightEar.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.RightEar.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.FrontRightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.FrontLeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        this.BackRightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        this.BackLeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
    }
}