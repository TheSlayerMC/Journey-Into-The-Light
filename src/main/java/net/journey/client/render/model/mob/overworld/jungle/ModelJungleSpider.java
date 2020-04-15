package net.journey.client.render.model.mob.overworld.jungle;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelJungleSpider extends ModelBase {
    ModelRenderer Head;
    ModelRenderer Body;
    ModelRenderer RearEnd;
    ModelRenderer spiderLeg1;
    ModelRenderer spiderLeg2;
    ModelRenderer spiderLeg3;
    ModelRenderer spiderLeg4;
    ModelRenderer spiderLeg5;
    ModelRenderer spiderLeg6;
    ModelRenderer spiderLeg7;
    ModelRenderer spiderLeg8;

    public ModelJungleSpider() {
        textureWidth = 64;
        textureHeight = 64;

        Head = new ModelRenderer(this, 32, 4);
        Head.addBox(-4F, -7F, -8F, 8, 8, 8);
        Head.setRotationPoint(0F, 18F, 0F);
        setRotateAngle(Head, 0F, 0F, 0F);

        Body = new ModelRenderer(this, 0, 0);
        Body.addBox(-3F, -7F, -3F, 6, 6, 6);
        Body.setRotationPoint(0F, 20F, 0F);
        setRotateAngle(Body, 0F, 0F, 0F);

        RearEnd = new ModelRenderer(this, 0, 20);
        RearEnd.addBox(-5F, -4F, -6F, 16, 16, 16);
        RearEnd.setRotationPoint(-3F, 12F, 9F);
        setRotateAngle(RearEnd, 0F, 0F, 0F);

        this.spiderLeg1 = new ModelRenderer(this, 18, 0);
        this.spiderLeg1.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.spiderLeg1.setRotationPoint(-4.0F, 15.0F, 2.0F);

        this.spiderLeg2 = new ModelRenderer(this, 18, 0);
        this.spiderLeg2.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.spiderLeg2.setRotationPoint(4.0F, 15.0F, 2.0F);

        this.spiderLeg3 = new ModelRenderer(this, 18, 0);
        this.spiderLeg3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.spiderLeg3.setRotationPoint(-4.0F, 15.0F, 1.0F);

        this.spiderLeg4 = new ModelRenderer(this, 18, 0);
        this.spiderLeg4.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.spiderLeg4.setRotationPoint(4.0F, 15.0F, 1.0F);

        this.spiderLeg5 = new ModelRenderer(this, 18, 0);
        this.spiderLeg5.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.spiderLeg5.setRotationPoint(-4.0F, 15.0F, 0.0F);

        this.spiderLeg6 = new ModelRenderer(this, 18, 0);
        this.spiderLeg6.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.spiderLeg6.setRotationPoint(4.0F, 15.0F, 0.0F);

        this.spiderLeg7 = new ModelRenderer(this, 18, 0);
        this.spiderLeg7.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.spiderLeg7.setRotationPoint(-4.0F, 15.0F, -1.0F);

        this.spiderLeg8 = new ModelRenderer(this, 18, 0);
        this.spiderLeg8.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.spiderLeg8.setRotationPoint(4.0F, 15.0F, -1.0F);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        Head.render(f5);
        Body.render(f5);
        RearEnd.render(f5);
        this.spiderLeg1.render(f5);
        this.spiderLeg2.render(f5);
        this.spiderLeg3.render(f5);
        this.spiderLeg4.render(f5);
        this.spiderLeg5.render(f5);
        this.spiderLeg6.render(f5);
        this.spiderLeg7.render(f5);
        this.spiderLeg8.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.Head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.Head.rotateAngleX = headPitch * 0.017453292F;
        float f = ((float) Math.PI / 4F);
        this.spiderLeg1.rotateAngleZ = -((float) Math.PI / 4F);
        this.spiderLeg2.rotateAngleZ = ((float) Math.PI / 4F);
        this.spiderLeg3.rotateAngleZ = -0.58119464F;
        this.spiderLeg4.rotateAngleZ = 0.58119464F;
        this.spiderLeg5.rotateAngleZ = -0.58119464F;
        this.spiderLeg6.rotateAngleZ = 0.58119464F;
        this.spiderLeg7.rotateAngleZ = -((float) Math.PI / 4F);
        this.spiderLeg8.rotateAngleZ = ((float) Math.PI / 4F);
        float f1 = -0.0F;
        float f2 = 0.3926991F;
        this.spiderLeg1.rotateAngleY = ((float) Math.PI / 4F);
        this.spiderLeg2.rotateAngleY = -((float) Math.PI / 4F);
        this.spiderLeg3.rotateAngleY = 0.3926991F;
        this.spiderLeg4.rotateAngleY = -0.3926991F;
        this.spiderLeg5.rotateAngleY = -0.3926991F;
        this.spiderLeg6.rotateAngleY = 0.3926991F;
        this.spiderLeg7.rotateAngleY = -((float) Math.PI / 4F);
        this.spiderLeg8.rotateAngleY = ((float) Math.PI / 4F);
        float f3 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
        float f4 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * limbSwingAmount;
        float f5 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float) Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float f6 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float) Math.PI * 3F / 2F)) * 0.4F) * limbSwingAmount;
        float f7 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;
        float f8 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI) * 0.4F) * limbSwingAmount;
        float f9 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float) Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float f10 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float) Math.PI * 3F / 2F)) * 0.4F) * limbSwingAmount;
        this.spiderLeg1.rotateAngleY += f3;
        this.spiderLeg2.rotateAngleY += -f3;
        this.spiderLeg3.rotateAngleY += f4;
        this.spiderLeg4.rotateAngleY += -f4;
        this.spiderLeg5.rotateAngleY += f5;
        this.spiderLeg6.rotateAngleY += -f5;
        this.spiderLeg7.rotateAngleY += f6;
        this.spiderLeg8.rotateAngleY += -f6;
        this.spiderLeg1.rotateAngleZ += f7;
        this.spiderLeg2.rotateAngleZ += -f7;
        this.spiderLeg3.rotateAngleZ += f8;
        this.spiderLeg4.rotateAngleZ += -f8;
        this.spiderLeg5.rotateAngleZ += f9;
        this.spiderLeg6.rotateAngleZ += -f9;
        this.spiderLeg7.rotateAngleZ += f10;
        this.spiderLeg8.rotateAngleZ += -f10;
    }
}
