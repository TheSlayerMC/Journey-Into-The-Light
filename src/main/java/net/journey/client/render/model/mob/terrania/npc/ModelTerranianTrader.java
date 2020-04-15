package net.journey.client.render.model.mob.terrania.npc;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * terranianTrader - Dizzlepop12
 * Animated by Dizzlepop12
 * Created using Tabula 5.1.0
 */
public class ModelTerranianTrader extends ModelBase {
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer foot2;
    public ModelRenderer foot1;
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer arm1;
    public ModelRenderer arm2;
    public ModelRenderer hand1;
    public ModelRenderer hand2;

    public ModelTerranianTrader() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.body = new ModelRenderer(this, 32, 34);
        this.body.setRotationPoint(0.0F, -22.0F, 1.0F);
        this.body.addBox(-5.0F, 0.0F, -3.0F, 10, 25, 4, 0.0F);
        this.leg2 = new ModelRenderer(this, 0, 34);
        this.leg2.setRotationPoint(-8.0F, -5.0F, 0.0F);
        this.leg2.addBox(-3.0F, 0.0F, -3.0F, 6, 24, 6, 0.0F);
        this.arm1 = new ModelRenderer(this, 0, 34);
        this.arm1.setRotationPoint(12.0F, -30.0F, 0.0F);
        this.arm1.addBox(0.0F, 0.0F, -3.0F, 6, 24, 6, 0.0F);
        this.foot2 = new ModelRenderer(this, 0, 0);
        this.foot2.setRotationPoint(-8.0F, -5.0F, 0.0F);
        this.foot2.addBox(-4.0F, 24.0F, -4.0F, 8, 5, 8, 0.0F);
        this.head = new ModelRenderer(this, 0, 16);
        this.head.setRotationPoint(0.0F, -22.0F, -4.0F);
        this.head.addBox(-12.0F, -10.0F, 0.0F, 24, 10, 8, 0.0F);
        this.setRotateAngle(head, 0.025587206130201923F, 0.0F, 0.0F);
        this.hand1 = new ModelRenderer(this, 32, 0);
        this.hand1.setRotationPoint(12.0F, -30.0F, 0.0F);
        this.hand1.addBox(-1.0F, 24.0F, -4.0F, 8, 8, 8, 0.0F);
        this.leg1 = new ModelRenderer(this, 0, 34);
        this.leg1.setRotationPoint(8.0F, -5.0F, 0.0F);
        this.leg1.addBox(-3.0F, 0.0F, -3.0F, 6, 24, 6, 0.0F);
        this.arm2 = new ModelRenderer(this, 0, 34);
        this.arm2.setRotationPoint(-12.0F, -30.0F, 0.0F);
        this.arm2.addBox(-6.0F, 0.0F, -3.0F, 6, 24, 6, 0.0F);
        this.hand2 = new ModelRenderer(this, 32, 0);
        this.hand2.setRotationPoint(-12.0F, -30.0F, 0.0F);
        this.hand2.addBox(-7.0F, 24.0F, -4.0F, 8, 8, 8, 0.0F);
        this.foot1 = new ModelRenderer(this, 0, 0);
        this.foot1.setRotationPoint(8.0F, -5.0F, 0.0F);
        this.foot1.addBox(-4.0F, 24.0F, -4.0F, 8, 5, 8, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.body.render(f5);
        this.leg2.render(f5);
        this.arm1.render(f5);
        this.foot2.render(f5);
        this.head.render(f5);
        this.hand1.render(f5);
        this.leg1.render(f5);
        this.arm2.render(f5);
        this.hand2.render(f5);
        this.foot1.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        this.leg1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 2.0F * f1 * 0.5F;
        this.leg2.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        this.foot1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 2.0F * f1 * 0.5F;
        this.foot2.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        this.arm1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.arm2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
        this.hand1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.hand2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
    }
}
