package net.journey.client.render.model.mob.nether;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * WitherSpine - Dizzlepop12
 * Created using Tabula 5.1.0
 */
public class ModelWitherspine extends ModelBase {
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer body;
    public ModelRenderer spine1;
    public ModelRenderer spine2;
    public ModelRenderer rib3;
    public ModelRenderer spineNeck;
    public ModelRenderer head;
    public ModelRenderer rib1;
    public ModelRenderer rip2;
    public ModelRenderer rib3_1;

    public ModelWitherspine() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.leg2 = new ModelRenderer(this, 0, 14);
        this.leg2.setRotationPoint(5.0F, 12.0F, 0.0F);
        this.leg2.addBox(-3.0F, 0.0F, -3.0F, 6, 12, 6, 0.0F);
        this.spine2 = new ModelRenderer(this, 0, 0);
        this.spine2.setRotationPoint(-1.5F, -12.6F, 6.0F);
        this.spine2.addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, 0.0F);
        this.setRotateAngle(spine2, -0.10471975511965977F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 12, 0);
        this.body.setRotationPoint(-9.0F, 6.0F, -4.0F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 18, 6, 8, 0.0F);
        this.rib1 = new ModelRenderer(this, 26, 20);
        this.rib1.setRotationPoint(-8.0F, -1.4F, 2.4F);
        this.rib1.addBox(0.0F, 0.0F, 0.0F, 16, 3, 2, 0.0F);
        this.setRotateAngle(rib1, -0.45378560551852565F, 0.0F, 0.0F);
        this.spine1 = new ModelRenderer(this, 0, 0);
        this.spine1.setRotationPoint(-1.5F, -3.0F, 5.0F);
        this.spine1.addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, 0.0F);
        this.setRotateAngle(spine1, -0.45378560551852565F, -0.0F, 0.0F);
        this.rip2 = new ModelRenderer(this, 26, 20);
        this.rip2.setRotationPoint(-8.0F, -10.0F, 3.8F);
        this.rip2.addBox(0.0F, 0.0F, 0.0F, 16, 3, 2, 0.0F);
        this.setRotateAngle(rip2, -0.10471975511965977F, 0.0F, 0.0F);
        this.leg1 = new ModelRenderer(this, 0, 14);
        this.leg1.setRotationPoint(-5.0F, 12.0F, 0.0F);
        this.leg1.addBox(-3.0F, 0.0F, -3.0F, 6, 12, 6, 0.0F);
        this.spineNeck = new ModelRenderer(this, 0, 0);
        this.spineNeck.setRotationPoint(-1.5F, -29.4F, -1.5F);
        this.spineNeck.addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, 0.0F);
        this.setRotateAngle(spineNeck, 0.6981317007977318F, 0.0F, 0.0F);
        this.rib3_1 = new ModelRenderer(this, 26, 20);
        this.rib3_1.setRotationPoint(-8.0F, -19.0F, 3.2F);
        this.rib3_1.addBox(0.0F, 0.0F, 0.0F, 16, 3, 2, 0.0F);
        this.setRotateAngle(rib3_1, 0.12217304763960307F, 0.0F, 0.0F);
        this.rib3 = new ModelRenderer(this, 0, 0);
        this.rib3.setRotationPoint(-1.5F, -22.5F, 4.8F);
        this.rib3.addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, 0.0F);
        this.setRotateAngle(rib3, 0.12217304763960307F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 0, 32);
        this.head.setRotationPoint(0.0F, -29.0F, 0.0F);
        this.head.addBox(-5.0F, -5.0F, -8.0F, 10, 10, 10, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.leg2.render(f5);
        this.spine2.render(f5);
        this.body.render(f5);
        this.rib1.render(f5);
        this.spine1.render(f5);
        this.rip2.render(f5);
        this.leg1.render(f5);
        this.spineNeck.render(f5);
        this.rib3_1.render(f5);
        this.rib3.render(f5);
        this.head.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.head.rotateAngleY = f3 / 57.29578f;
        this.head.rotateAngleX = f4 / 57.29578f;
        this.leg1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.leg2.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
    }
}
