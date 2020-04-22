package net.journey.client.render.model.mob.terrania.mob;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelAranaKing - Dizzlepop12
 * Created using Tabula 5.1.0
 */
public class ModelAranaKing extends ModelBase {
    public ModelRenderer leg1;
    public ModelRenderer head;
    public ModelRenderer leg3;
    public ModelRenderer leg2;
    public ModelRenderer leg4;
    public ModelRenderer fungalcap;
    public ModelRenderer fungalcap2;
    public ModelRenderer fungalcap3;

    public ModelAranaKing() {
    	this.textureWidth = 128;
        this.textureHeight = 64;
        this.fungalcap = new ModelRenderer(this, 0, 24);
        this.fungalcap.setRotationPoint(0.0F, 14.0F, 1.0F);
        this.fungalcap.addBox(-8.0F, -4.0F, -8.0F, 16, 4, 16, 0.0F);
        this.leg1 = new ModelRenderer(this, 64, 20);
        this.leg1.setRotationPoint(-4.0F, 15.0F, 0.0F);
        this.leg1.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(leg1, 0.0F, -0.39269908169872414F, -0.5811946409141118F);
        this.head = new ModelRenderer(this, 0, 46);
        this.head.setRotationPoint(0.0F, 18.0F, 1.0F);
        this.head.addBox(-6.0F, -4.0F, -6.0F, 12, 6, 12, 0.0F);
        this.fungalcap2 = new ModelRenderer(this, 0, 2);
        this.fungalcap2.setRotationPoint(0.0F, 14.0F, 1.0F);
        this.fungalcap2.addBox(-6.0F, -12.0F, -6.0F, 12, 8, 12, 0.0F);
        this.leg2 = new ModelRenderer(this, 64, 20);
        this.leg2.setRotationPoint(4.0F, 15.0F, 0.0F);
        this.leg2.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(leg2, 0.0F, 0.39269908169872414F, 0.5811946409141118F);
        this.leg3 = new ModelRenderer(this, 64, 20);
        this.leg3.setRotationPoint(4.0F, 15.0F, 1.0F);
        this.leg3.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(leg3, 0.0F, -0.39269908169872414F, 0.5811946409141118F);
        this.leg4 = new ModelRenderer(this, 64, 20);
        this.leg4.setRotationPoint(-4.0F, 15.0F, 1.0F);
        this.leg4.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(leg4, 0.0F, 0.39269908169872414F, -0.5811946409141118F);
        this.fungalcap3 = new ModelRenderer(this, 64, 4);
        this.fungalcap3.setRotationPoint(0.0F, 14.0F, 1.0F);
        this.fungalcap3.addBox(-4.0F, -14.0F, -4.0F, 8, 2, 8, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.fungalcap.render(f5);
        this.leg1.render(f5);
        this.head.render(f5);
        this.fungalcap2.render(f5);
        this.leg2.render(f5);
        this.leg3.render(f5);
        this.leg4.render(f5);
        this.fungalcap3.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.leg1.rotateAngleX = MathHelper.sin(limbSwing * 1.2662F + (float) Math.PI) * limbSwingAmount;
        this.leg2.rotateAngleX = MathHelper.sin(limbSwing * 1.2662F) * limbSwingAmount;
        this.leg3.rotateAngleX = MathHelper.sin(limbSwing * 1.2662F + (float) Math.PI) * limbSwingAmount;
        this.leg4.rotateAngleX = MathHelper.sin(limbSwing * 1.2662F) * limbSwingAmount;
    }

}
