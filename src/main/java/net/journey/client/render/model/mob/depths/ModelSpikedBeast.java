package net.journey.client.render.model.mob.depths;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSpikedBeast extends ModelBase {

    private ModelRenderer Body;
    private ModelRenderer LeftArm;
    private ModelRenderer RightArm;
    private ModelRenderer LeftLeg;
    private ModelRenderer RightLeg;
    private ModelRenderer RightSpike;
    private ModelRenderer LeftSpike;
    private ModelRenderer MiddleSpike;
    private ModelRenderer LeftShoulderSpike;
    private ModelRenderer Head;
    private ModelRenderer Horn;

    public ModelSpikedBeast() {
        textureWidth = 64;
        textureHeight = 32;

        Body = new ModelRenderer(this, 0, 0);
        Body.addBox(-3.5F, 0F, -2F, 7, 10, 4);
        Body.setRotationPoint(0F, 4F, -1F);
        Body.setTextureSize(64, 32);
        Body.mirror = true;
        setRotation(Body, 0.3665191F, 0F, 0F);
        LeftArm = new ModelRenderer(this, 0, 14);
        LeftArm.addBox(-1.5F, -1F, -0.5F, 11, 2, 2);
        LeftArm.setRotationPoint(4F, 5F, 0F);
        LeftArm.setTextureSize(64, 32);
        LeftArm.mirror = true;
        setRotation(LeftArm, 0.2617994F, 1.37881F, 0.715585F);
        RightArm = new ModelRenderer(this, 0, 14);
        RightArm.addBox(-1.5F, -1F, -1.5F, 11, 2, 2);
        RightArm.setRotationPoint(-4F, 5F, 0F);
        RightArm.setTextureSize(64, 32);
        RightArm.mirror = true;
        setRotation(RightArm, 0.1047198F, 1.727876F, 0.4886922F);
        LeftLeg = new ModelRenderer(this, 22, 0);
        LeftLeg.addBox(-1F, 0F, -1F, 2, 10, 2);
        LeftLeg.setRotationPoint(2F, 13.5F, 2F);
        LeftLeg.setTextureSize(64, 32);
        LeftLeg.mirror = true;
        setRotation(LeftLeg, 0F, 0F, 0F);
        RightLeg = new ModelRenderer(this, 22, 0);
        RightLeg.addBox(-1F, 0F, -1F, 2, 10, 2);
        RightLeg.setRotationPoint(-2F, 13.5F, 2F);
        RightLeg.setTextureSize(64, 32);
        RightLeg.mirror = true;
        setRotation(RightLeg, 0F, 0F, 0F);
        RightSpike = new ModelRenderer(this, 30, 0);
        RightSpike.addBox(-0.5F, -0.5F, 0F, 1, 1, 7);
        RightSpike.setRotationPoint(-2F, 5F, 1F);
        RightSpike.setTextureSize(64, 32);
        RightSpike.mirror = true;
        setRotation(RightSpike, 0.3665191F, 0F, -0.296706F);
        LeftSpike = new ModelRenderer(this, 30, 0);
        LeftSpike.addBox(-0.5F, -0.5F, 0F, 1, 1, 7);
        LeftSpike.setRotationPoint(2F, 5F, 1F);
        LeftSpike.setTextureSize(64, 32);
        LeftSpike.mirror = true;
        setRotation(LeftSpike, 0F, 0F, 0F);
        MiddleSpike = new ModelRenderer(this, 30, 0);
        MiddleSpike.addBox(-0.5F, -0.5F, 0F, 1, 1, 7);
        MiddleSpike.setRotationPoint(0F, 8F, 2F);
        MiddleSpike.setTextureSize(64, 32);
        MiddleSpike.mirror = true;
        setRotation(MiddleSpike, -0.0174533F, -0.2443461F, 0.0174533F);
        LeftShoulderSpike = new ModelRenderer(this, 30, 0);
        LeftShoulderSpike.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 7);
        LeftShoulderSpike.setRotationPoint(4.5F, 4.5F, 0.5F);
        LeftShoulderSpike.setTextureSize(64, 32);
        LeftShoulderSpike.mirror = true;
        setRotation(LeftShoulderSpike, 2.199115F, -1.012291F, 0.2617994F);
        Head = new ModelRenderer(this, 30, 8);
        Head.addBox(-2F, -2.5F, -4F, 4, 5, 4);
        Head.setRotationPoint(0F, 6F, -2F);
        Head.setTextureSize(64, 32);
        Head.mirror = true;
        setRotation(Head, 0F, 0F, 0F);
        Horn = new ModelRenderer(this, 46, 0);
        Horn.addBox(-0.5F, -0.5F, 0F, 6, 1, 1);
        Horn.setRotationPoint(1F, 4F, -4F);
        Horn.setTextureSize(64, 32);
        Horn.mirror = true;
        setRotation(Horn, 0.3490659F, 1.32645F, -0.2268928F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Body.render(f5);
        LeftArm.render(f5);
        RightArm.render(f5);
        LeftLeg.render(f5);
        RightLeg.render(f5);
        RightSpike.render(f5);
        LeftSpike.render(f5);
        MiddleSpike.render(f5);
        LeftShoulderSpike.render(f5);
        Head.render(f5);
        Horn.render(f5);
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
        this.RightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.LeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        this.RightLeg.rotateAngleY = 0.0F;
        this.LeftLeg.rotateAngleY = 0.0F;
    }
}