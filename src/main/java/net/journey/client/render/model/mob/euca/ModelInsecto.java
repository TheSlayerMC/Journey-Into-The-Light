package net.journey.client.render.model.mob.euca;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelInsecto extends ModelBase {

    ModelRenderer Body;
    ModelRenderer RightShoulder;
    ModelRenderer LeftShoulder;
    ModelRenderer Head;
    ModelRenderer LeftShoulderSpike;
    ModelRenderer RightShoulderSpike;
    ModelRenderer LeftLeg;
    ModelRenderer RightLeg;
    ModelRenderer LeftFoot;
    ModelRenderer RightFoot;
    ModelRenderer RightArm1;
    ModelRenderer LeftArm1;
    ModelRenderer RightArm2;
    ModelRenderer LeftArm2;
    ModelRenderer Tail1;
    ModelRenderer Tail2;
    ModelRenderer Tail3;
    ModelRenderer TailSpike;

    public ModelInsecto() {
        textureWidth = 128;
        textureHeight = 64;

        Body = new ModelRenderer(this, 0, 0);
        Body.addBox(0F, 0F, 0F, 7, 9, 5);
        Body.setRotationPoint(-3.7F, 4F, -1F);
        Body.setTextureSize(128, 64);
        Body.mirror = true;
        setRotation(Body, 0F, 0F, 0F);
        RightShoulder = new ModelRenderer(this, 0, 14);
        RightShoulder.addBox(0F, 0F, 0F, 8, 1, 5);
        RightShoulder.setRotationPoint(3F, 4F, -1.5F);
        RightShoulder.setTextureSize(128, 64);
        RightShoulder.mirror = true;
        setRotation(RightShoulder, 0F, 0F, -0.2792527F);
        LeftShoulder = new ModelRenderer(this, 0, 14);
        LeftShoulder.addBox(0F, 0F, 0F, 8, 1, 5);
        LeftShoulder.setRotationPoint(-11F, 1.8F, -1.5F);
        LeftShoulder.setTextureSize(128, 64);
        LeftShoulder.mirror = true;
        setRotation(LeftShoulder, 0F, 0F, 0.2792527F);
        Head = new ModelRenderer(this, 24, 0);
        Head.addBox(0F, 0F, 0F, 6, 6, 3);
        Head.setRotationPoint(-3.1F, 4.1F, -4F);
        Head.setTextureSize(128, 64);
        Head.mirror = true;
        setRotation(Head, 0F, 0F, 0F);
        LeftShoulderSpike = new ModelRenderer(this, 26, 9);
        LeftShoulderSpike.addBox(0F, 0F, 0F, 1, 10, 1);
        LeftShoulderSpike.setRotationPoint(-10F, -7F, 0.5F);
        LeftShoulderSpike.setTextureSize(128, 64);
        LeftShoulderSpike.mirror = true;
        setRotation(LeftShoulderSpike, 0F, 0F, -0.1396263F);
        RightShoulderSpike = new ModelRenderer(this, 26, 9);
        RightShoulderSpike.addBox(0F, 0F, 0F, 1, 10, 1);
        RightShoulderSpike.setRotationPoint(9F, -7F, 0.5F);
        RightShoulderSpike.setTextureSize(128, 64);
        RightShoulderSpike.mirror = true;
        setRotation(RightShoulderSpike, 0F, 0F, 0.1396263F);
        LeftLeg = new ModelRenderer(this, 30, 9);
        LeftLeg.addBox(-1F, 0F, -1F, 2, 9, 2);
        LeftLeg.setRotationPoint(-2F, 13F, 2F);
        LeftLeg.setTextureSize(128, 64);
        LeftLeg.mirror = true;
        setRotation(LeftLeg, 0F, 0F, 0F);
        RightLeg = new ModelRenderer(this, 30, 9);
        RightLeg.addBox(-1F, 0F, -1F, 2, 9, 2);
        RightLeg.setRotationPoint(2F, 13F, 2F);
        RightLeg.setTextureSize(128, 64);
        RightLeg.mirror = true;
        setRotation(RightLeg, 0F, 0F, 0F);
        LeftFoot = new ModelRenderer(this, 38, 9);
        LeftFoot.addBox(-1F, 10F, -4F, 2, 2, 5);
        LeftFoot.setRotationPoint(-2F, 12F, 2F);
        LeftFoot.setTextureSize(128, 64);
        LeftFoot.mirror = true;
        setRotation(LeftFoot, 0F, 0F, 0F);
        RightFoot = new ModelRenderer(this, 38, 9);
        RightFoot.addBox(-1F, 10F, -4F, 2, 2, 5);
        RightFoot.setRotationPoint(2F, 12F, 2F);
        RightFoot.setTextureSize(128, 64);
        RightFoot.mirror = true;
        setRotation(RightFoot, 0F, 0F, 0F);
        RightArm1 = new ModelRenderer(this, 0, 20);
        RightArm1.addBox(0F, 0F, 0F, 7, 2, 2);
        RightArm1.setRotationPoint(3F, 5F, 1F);
        RightArm1.setTextureSize(128, 64);
        RightArm1.mirror = true;
        setRotation(RightArm1, 0F, 0F, 0.6632251F);
        LeftArm1 = new ModelRenderer(this, 0, 20);
        LeftArm1.addBox(0F, 0F, 0F, 7, 2, 2);
        LeftArm1.setRotationPoint(-9F, 9.5F, 1F);
        LeftArm1.setTextureSize(128, 64);
        LeftArm1.mirror = true;
        setRotation(LeftArm1, 0F, 0F, -0.6632251F);
        RightArm2 = new ModelRenderer(this, 0, 24);
        RightArm2.addBox(0F, 0F, -11F, 2, 2, 11);
        RightArm2.setRotationPoint(6F, 9.5F, 2F);
        RightArm2.setTextureSize(128, 64);
        RightArm2.mirror = true;
        setRotation(RightArm2, 0.2268928F, -0.2792527F, -0.6632251F);
        LeftArm2 = new ModelRenderer(this, 0, 24);
        LeftArm2.addBox(0F, 0F, -11F, 2, 2, 11);
        LeftArm2.setRotationPoint(-8F, 8.5F, 2F);
        LeftArm2.setTextureSize(128, 64);
        LeftArm2.mirror = true;
        setRotation(LeftArm2, 0.2268928F, 0.2792527F, 0.6632251F);
        Tail1 = new ModelRenderer(this, 26, 24);
        Tail1.addBox(0F, 0F, 0F, 2, 2, 10);
        Tail1.setRotationPoint(-1F, 11F, 2.8F);
        Tail1.setTextureSize(128, 64);
        Tail1.mirror = true;
        setRotation(Tail1, 0.4537856F, 0F, 0F);
        Tail2 = new ModelRenderer(this, 26, 24);
        Tail2.addBox(0F, -10F, -8.9F, 2, 2, 10);
        Tail2.setRotationPoint(-1F, 11F, 2.8F);
        Tail2.setTextureSize(128, 64);
        Tail2.mirror = true;
        setRotation(Tail2, -1.134464F, 0F, 0F);
        Tail3 = new ModelRenderer(this, 26, 24);
        Tail3.addBox(0F, -12F, -4F, 2, 2, 10);
        Tail3.setRotationPoint(-1F, 11F, 1.8F);
        Tail3.setTextureSize(128, 64);
        Tail3.mirror = true;
        setRotation(Tail3, 0.0349066F, 0F, 0F);
        TailSpike = new ModelRenderer(this, 33, 36);
        TailSpike.addBox(0.5F, -12.5F, -3F, 1, 1, 4);
        TailSpike.setRotationPoint(-1F, 11F, 2.8F);
        TailSpike.setTextureSize(128, 64);
        TailSpike.mirror = true;
        setRotation(TailSpike, 0.4537856F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Body.render(f5);
        RightShoulder.render(f5);
        LeftShoulder.render(f5);
        Head.render(f5);
        LeftShoulderSpike.render(f5);
        RightShoulderSpike.render(f5);
        LeftLeg.render(f5);
        RightLeg.render(f5);
        LeftFoot.render(f5);
        RightFoot.render(f5);
        RightArm1.render(f5);
        LeftArm1.render(f5);
        RightArm2.render(f5);
        LeftArm2.render(f5);
        Tail1.render(f5);
        Tail2.render(f5);
        Tail3.render(f5);
        TailSpike.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.RightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.LeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        this.RightFoot.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.LeftFoot.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        this.RightArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 2.0F * par2 * 0.5F;
        this.LeftArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        Tail1.rotateAngleX = MathHelper.cos(par1 * 0.2662F) * 0.7F * par2 + 0.5F;
        Tail2.rotateAngleX = MathHelper.cos(par1 * 0.2662F) * 0.7F * par2 - 1.2F;
        Tail3.rotateAngleX = MathHelper.cos(par1 * 0.2662F) * 0.7F * par2 - 0.01F;
        TailSpike.rotateAngleX = MathHelper.cos(par1 * 0.2662F) * 0.7F * par2 + 0.5F;
    }
}