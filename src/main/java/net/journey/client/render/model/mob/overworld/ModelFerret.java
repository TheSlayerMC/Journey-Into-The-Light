package net.journey.client.render.model.mob.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelFerret - Dizzlepop12
 * Created using Tabula 5.1.0
 */
public class ModelFerret extends ModelBase {
    public ModelRenderer head;
    public ModelRenderer ears;
    public ModelRenderer mussle;
    public ModelRenderer whiskers;
    public ModelRenderer neck;
    public ModelRenderer body1;
    public ModelRenderer body2;
    public ModelRenderer leg1;
    public ModelRenderer leg1_2;
    public ModelRenderer leg1_3;
    public ModelRenderer leg2;
    public ModelRenderer leg2_2;
    public ModelRenderer leg2_3;
    public ModelRenderer leg3;
    public ModelRenderer leg3_2;
    public ModelRenderer leg3_3;
    public ModelRenderer leg4;
    public ModelRenderer leg4_2;
    public ModelRenderer leg4_3;
    public ModelRenderer tail1;
    public ModelRenderer tail2;


    public ModelFerret() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.leg2_3 = new ModelRenderer(this, 35, 12);
        this.leg2_3.setRotationPoint(1.0F, 19.0F, -4.0F);
        this.leg2_3.addBox(0.5F, 4.0F, -3.0F, 2, 1, 3, 0.0F);
        this.neck = new ModelRenderer(this, 20, 0);
        this.neck.setRotationPoint(-1.5F, 16.6F, -8.0F);
        this.neck.addBox(0.0F, 0.0F, 0.0F, 3, 3, 6, 0.0F);
        this.setRotateAngle(neck, -0.24434609527920614F, -0.0F, 0.0F);
        this.leg3 = new ModelRenderer(this, 44, 4);
        this.leg3.setRotationPoint(3.0F, 19.0F, 4.0F);
        this.leg3.addBox(-0.5F, 0.0F, -1.5F, 1, 3, 4, 0.0F);
        this.leg2 = new ModelRenderer(this, 44, 4);
        this.leg2.setRotationPoint(3.0F, 19.0F, -4.0F);
        this.leg2.addBox(-0.5F, 0.0F, -1.5F, 1, 3, 3, 0.0F);
        this.leg1_3 = new ModelRenderer(this, 35, 12);
        this.leg1_3.setRotationPoint(-4.0F, 19.0F, -4.0F);
        this.leg1_3.addBox(0.5F, 4.0F, -3.0F, 2, 1, 3, 0.0F);
        this.head = new ModelRenderer(this, 0, 6);
        this.head.setRotationPoint(0.0F, 19.6F, -8.0F);
        this.head.addBox(-2.0F, -4.0F, -2.0F, 4, 4, 4, 0.0F);
        this.leg1 = new ModelRenderer(this, 44, 4);
        this.leg1.setRotationPoint(-3.0F, 19.0F, -4.0F);
        this.leg1.addBox(-0.5F, 0.0F, -1.5F, 1, 3, 3, 0.0F);
        this.setRotateAngle(leg1, 0.0F, 0.0F, -0.009773843811168246F);
        this.tail2 = new ModelRenderer(this, 45, 9);
        this.tail2.setRotationPoint(-1.0F, 21.4F, 8.5F);
        this.tail2.addBox(0.0F, 0.0F, 0.0F, 2, 1, 5, 0.0F);
        this.setRotateAngle(tail2, -0.1884955592153876F, 0.0F, -0.0F);
        this.tail1 = new ModelRenderer(this, 27, 23);
        this.tail1.setRotationPoint(-1.5F, 20.0F, 6.2F);
        this.tail1.addBox(0.0F, 0.0F, 0.0F, 3, 2, 4, 0.0F);
        this.setRotateAngle(tail1, -0.2827433388230814F, -0.0F, -0.0F);
        this.leg4 = new ModelRenderer(this, 44, 4);
        this.leg4.setRotationPoint(-2.5F, 19.0F, 4.0F);
        this.leg4.addBox(-0.5F, 0.0F, -1.5F, 1, 3, 4, 0.0F);
        this.mussle = new ModelRenderer(this, 0, 15);
        this.mussle.setRotationPoint(0.0F, 19.6F, -8.0F);
        this.mussle.addBox(-1.5F, -2.4F, -3.4F, 3, 2, 2, 0.0F);
        this.ears = new ModelRenderer(this, 0, 0);
        this.ears.setRotationPoint(-0.0F, 19.6F, -8.0F);
        this.ears.addBox(-4.0F, -5.0F, -0.1F, 8, 3, 0, 0.0F);
        this.whiskers = new ModelRenderer(this, 0, 24);
        this.whiskers.setRotationPoint(0.0F, 19.6F, -8.0F);
        this.whiskers.addBox(-3.0F, -2.8F, -3.0F, 6, 3, 0, 0.0F);
        this.body1 = new ModelRenderer(this, 20, 18);
        this.body1.setRotationPoint(-2.5F, 17.6F, -6.0F);
        this.body1.addBox(0.0F, 0.0F, 0.0F, 5, 4, 9, 0.0F);
        this.leg4_3 = new ModelRenderer(this, 35, 12);
        this.leg4_3.setRotationPoint(-3.5F, 19.0F, 4.0F);
        this.leg4_3.addBox(0.5F, 4.0F, -2.0F, 2, 1, 3, 0.0F);
        this.leg4_2 = new ModelRenderer(this, 44, 4);
        this.leg4_2.setRotationPoint(-2.5F, 19.0F, 4.0F);
        this.leg4_2.addBox(0.0F, 0.0F, -0.5F, 1, 5, 2, 0.0F);
        this.setRotateAngle(leg4_2, -0.15707963267948966F, 0.0F, 0.0F);
        this.leg2_2 = new ModelRenderer(this, 44, 4);
        this.leg2_2.setRotationPoint(2.0F, 19.0F, -4.0F);
        this.leg2_2.addBox(0.0F, 0.0F, -1.5F, 1, 5, 2, 0.0F);
        this.setRotateAngle(leg2_2, -0.15707963267948966F, 0.0F, 0.0F);
        this.body2 = new ModelRenderer(this, 20, 18);
        this.body2.setRotationPoint(-2.0F, 17.6F, -2.0F);
        this.body2.addBox(0.0F, 0.0F, 0.0F, 4, 4, 9, 0.0F);
        this.setRotateAngle(body2, -0.10681415022205297F, -0.0F, -0.0F);
        this.leg1_2 = new ModelRenderer(this, 44, 4);
        this.leg1_2.setRotationPoint(-3.0F, 19.0F, -4.0F);
        this.leg1_2.addBox(0.0F, 0.0F, -1.5F, 1, 5, 2, 0.0F);
        this.setRotateAngle(leg1_2, -0.15707963267948966F, 0.0F, 0.0F);
        this.leg3_2 = new ModelRenderer(this, 44, 4);
        this.leg3_2.setRotationPoint(2.0F, 19.0F, 4.0F);
        this.leg3_2.addBox(0.0F, 0.0F, -0.5F, 1, 5, 2, 0.0F);
        this.setRotateAngle(leg3_2, -0.15707963267948966F, 0.0F, 0.0F);
        this.leg3_3 = new ModelRenderer(this, 35, 12);
        this.leg3_3.setRotationPoint(1.0F, 19.0F, 5.0F);
        this.leg3_3.addBox(0.5F, 4.0F, -3.0F, 2, 1, 3, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.body2.render(f5);
        this.leg2_3.render(f5);
        this.neck.render(f5);
        this.mussle.render(f5);
        this.body1.render(f5);
        this.leg1_2.render(f5);
        this.head.render(f5);
        this.ears.render(f5);
        this.leg3_2.render(f5);
        this.leg3.render(f5);
        this.leg2.render(f5);
        this.leg1_3.render(f5);
        this.leg2_2.render(f5);
        this.leg4_3.render(f5);
        this.tail1.render(f5);
        this.tail2.render(f5);
        this.leg3_3.render(f5);
        this.leg1.render(f5);
        this.leg4.render(f5);
        this.whiskers.render(f5);
        this.leg4_2.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        this.leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.leg1_2.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.leg1_3.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
        this.leg2_2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
        this.leg2_3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
        this.leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
        this.leg3_2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
        this.leg3_3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
        this.leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.leg4_2.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.leg4_3.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }
}
