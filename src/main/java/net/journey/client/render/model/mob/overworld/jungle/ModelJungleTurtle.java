package net.journey.client.render.model.mob.overworld.jungle;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelJungleTurtle extends ModelBase {
    public ModelRenderer shellBase;
    public ModelRenderer foot1;
    public ModelRenderer foot2;
    public ModelRenderer foot3;
    public ModelRenderer foot4;
    public ModelRenderer shellMain;
    public ModelRenderer shellTop;
    public ModelRenderer head;

    public ModelJungleTurtle() {
        textureWidth = 128;
        textureHeight = 128;

        shellBase = new ModelRenderer(this, 0, 0);
        shellBase.addBox(0.0F, 0.0F, 0.0F, 32, 4, 32);
        shellBase.setRotationPoint(-16.0F, 15.0F, -16.0F);

        foot1 = new ModelRenderer(this, 0, 0);
        foot1.addBox(-3.0F, -8.0F, -3.0F, 6, 8, 6);
        foot1.setRotationPoint(14.0F, 24.0F, 14.0F);

        foot2 = new ModelRenderer(this, 0, 0);
        foot2.addBox(-3.0F, -8.0F, -3.0F, 6, 8, 6);
        foot2.setRotationPoint(14.0F, 24.0F, -14.0F);

        foot3 = new ModelRenderer(this, 0, 0);
        foot3.addBox(-3.0F, -8.0F, -3.0F, 6, 8, 6);
        foot3.setRotationPoint(-14.0F, 24.0F, -14.0F);

        foot4 = new ModelRenderer(this, 0, 0);
        foot4.addBox(-3.0F, -8.0F, -3.0F, 6, 8, 6);
        foot4.setRotationPoint(-14.0F, 24.0F, 14.0F);

        shellMain = new ModelRenderer(this, 0, 36);
        shellMain.addBox(0.0F, 0.0F, 0.0F, 30, 16, 30);
        shellMain.setRotationPoint(-15.0F, 1.0F, -15.0F);

        shellTop = new ModelRenderer(this, 0, 82);
        shellTop.addBox(0.0F, 0.0F, 0.0F, 28, 2, 28);
        shellTop.setRotationPoint(-14.0F, -1.0F, -14.0F);

        head = new ModelRenderer(this, 0, 14);
        head.addBox(-4.0F, 10.0F, -2.0F, 8, 8, 8);
        head.setRotationPoint(0.0F, 2.0F, -22.0F);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        shellBase.render(f5);
        foot1.render(f5);
        foot2.render(f5);
        foot3.render(f5);
        foot4.render(f5);
        shellMain.render(f5);
        shellTop.render(f5);
        head.render(f5);

    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f2, float f3, float f4, float f5, float f7, Entity e) {
        super.setRotationAngles(f, f2, f3, f4, f5, f7, e);
        this.head.rotateAngleY = f4 / (180F / (float) Math.PI);
        this.head.rotateAngleX = f5 / (180F / (float) Math.PI);
        this.foot1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f2;
        this.foot2.rotateAngleX = MathHelper.cos(f * 0.6662F) * f2;
        this.foot3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f2;
        this.foot4.rotateAngleX = MathHelper.cos(f * 0.6662F) * f2;
    }
}
