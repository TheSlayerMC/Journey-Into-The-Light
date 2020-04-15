package net.journey.client.render.model.mob.depths;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelDepthsHunter extends ModelBase {

    ModelRenderer Body;
    ModelRenderer LeftArm;
    ModelRenderer RightHand;
    ModelRenderer LeftLeg;
    ModelRenderer RightLeg;
    ModelRenderer RightSpike1;
    ModelRenderer LeftSpike1;
    ModelRenderer LeftSpike2;
    ModelRenderer RightSpike2;
    ModelRenderer MiddleSpike;
    ModelRenderer RightShoulderSpike;
    ModelRenderer LeftShoulderSpike;

    public ModelDepthsHunter() {
        textureWidth = 128;
        textureHeight = 64;

        Body = new ModelRenderer(this, 0, 0);
        Body.addBox(-4.5F, 0F, -3.5F, 9, 11, 7);
        Body.setRotationPoint(0F, 2F, 0F);
        Body.setTextureSize(128, 64);
        Body.mirror = true;
        setRotation(Body, 0F, 0F, 0F);
        LeftArm = new ModelRenderer(this, 32, 0);
        LeftArm.addBox(0F, -1F, -2F, 5, 15, 4);
        LeftArm.setRotationPoint(4.5F, 3F, 0F);
        LeftArm.setTextureSize(128, 64);
        LeftArm.mirror = true;
        setRotation(LeftArm, 0F, 0F, 0F);
        RightHand = new ModelRenderer(this, 32, 0);
        RightHand.addBox(-5F, -1F, -2F, 5, 15, 4);
        RightHand.setRotationPoint(-4.5F, 3F, 0F);
        RightHand.setTextureSize(128, 64);
        RightHand.mirror = true;
        setRotation(RightHand, 0F, 0F, 0F);
        LeftLeg = new ModelRenderer(this, 50, 0);
        LeftLeg.addBox(-2F, 0F, -2F, 4, 11, 4);
        LeftLeg.setRotationPoint(2.5F, 13F, 0F);
        LeftLeg.setTextureSize(128, 64);
        LeftLeg.mirror = true;
        setRotation(LeftLeg, 0F, 0F, 0F);
        RightLeg = new ModelRenderer(this, 50, 0);
        RightLeg.addBox(-2F, 0F, -2F, 4, 11, 4);
        RightLeg.setRotationPoint(-2.5F, 13F, 0F);
        RightLeg.setTextureSize(128, 64);
        RightLeg.mirror = true;
        setRotation(RightLeg, 0F, 0F, 0F);
        RightSpike1 = new ModelRenderer(this, 0, 18);
        RightSpike1.addBox(-1F, -1F, -0.5F, 2, 2, 6);
        RightSpike1.setRotationPoint(-3F, 4F, 4F);
        RightSpike1.setTextureSize(128, 64);
        RightSpike1.mirror = true;
        setRotation(RightSpike1, 0F, 0F, 0F);
        LeftSpike1 = new ModelRenderer(this, 0, 18);
        LeftSpike1.addBox(-1F, -1F, -0.5F, 2, 2, 6);
        LeftSpike1.setRotationPoint(3F, 4F, 4F);
        LeftSpike1.setTextureSize(128, 64);
        LeftSpike1.mirror = true;
        setRotation(LeftSpike1, 0F, 0F, 0F);
        LeftSpike2 = new ModelRenderer(this, 0, 18);
        LeftSpike2.addBox(-1F, -1F, -0.5F, 2, 2, 6);
        LeftSpike2.setRotationPoint(3F, 7F, 4F);
        LeftSpike2.setTextureSize(128, 64);
        LeftSpike2.mirror = true;
        setRotation(LeftSpike2, 0F, 0F, 0F);
        RightSpike2 = new ModelRenderer(this, 0, 18);
        RightSpike2.addBox(-1F, -1F, -0.5F, 2, 2, 6);
        RightSpike2.setRotationPoint(-3F, 7F, 4F);
        RightSpike2.setTextureSize(128, 64);
        RightSpike2.mirror = true;
        setRotation(RightSpike2, 0F, 0F, 0F);
        MiddleSpike = new ModelRenderer(this, 0, 18);
        MiddleSpike.addBox(-1F, -1F, -0.5F, 2, 2, 6);
        MiddleSpike.setRotationPoint(0F, 10F, 4F);
        MiddleSpike.setTextureSize(128, 64);
        MiddleSpike.mirror = true;
        setRotation(MiddleSpike, 0F, 0F, 0F);
        RightShoulderSpike = new ModelRenderer(this, 16, 18);
        RightShoulderSpike.addBox(-1F, -6F, -1F, 2, 6, 2);
        RightShoulderSpike.setRotationPoint(-8F, 2F, 0F);
        RightShoulderSpike.setTextureSize(128, 64);
        RightShoulderSpike.mirror = true;
        setRotation(RightShoulderSpike, 0F, 0F, -0.1570796F);
        LeftShoulderSpike = new ModelRenderer(this, 16, 18);
        LeftShoulderSpike.addBox(-1F, -6F, -1F, 2, 6, 2);
        LeftShoulderSpike.setRotationPoint(8F, 2F, 0F);
        LeftShoulderSpike.setTextureSize(128, 64);
        LeftShoulderSpike.mirror = true;
        setRotation(LeftShoulderSpike, 0F, 0F, 0.1570796F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Body.render(f5);
        LeftArm.render(f5);
        RightHand.render(f5);
        LeftLeg.render(f5);
        RightLeg.render(f5);
        RightSpike1.render(f5);
        LeftSpike1.render(f5);
        LeftSpike2.render(f5);
        RightSpike2.render(f5);
        MiddleSpike.render(f5);
        RightShoulderSpike.render(f5);
        LeftShoulderSpike.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.RightHand.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 2.0F * par2 * 0.5F;
        this.LeftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.RightShoulderSpike.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 2.0F * par2 * 0.5F;
        this.LeftShoulderSpike.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.RightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.LeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
    }
}