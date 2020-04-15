package net.journey.client.render.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGrindstone extends ModelBase {

    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer side1;
    public ModelRenderer side2;
    public ModelRenderer side3;
    public ModelRenderer side4;
    public ModelRenderer stone;
    public ModelRenderer rod;

    public ModelGrindstone() {
        textureWidth = 64;
        textureHeight = 64;

        this.textureWidth = 64;
        this.textureHeight = 64;
        this.side3 = new ModelRenderer(this, 32, 40);
        this.side3.setRotationPoint(-8.0F, 3.0F, -6.0F);
        this.side3.addBox(0.0F, 0.0F, 0.0F, 1, 10, 12, 0.0F);
        this.rod = new ModelRenderer(this, 0, 0);
        this.rod.setRotationPoint(-0.5F, 4.5F, -6.0F);
        this.rod.addBox(0.0F, 0.0F, 0.0F, 1, 1, 12, 0.0F);
        this.side1 = new ModelRenderer(this, 0, 48);
        this.side1.setRotationPoint(-7.0F, 3.0F, -7.0F);
        this.side1.addBox(0.0F, 0.0F, 0.0F, 14, 10, 1, 0.0F);
        this.stone = new ModelRenderer(this, 0, 20);
        this.stone.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.stone.addBox(-7.0F, -7.0F, 0.0F, 14, 14, 0, 0.0F);
        this.leg3 = new ModelRenderer(this, 32, 0);
        this.leg3.setRotationPoint(6.0F, 8.0F, 5.0F);
        this.leg3.addBox(0.0F, 0.0F, 0.0F, 2, 16, 2, 0.0F);
        this.setRotation(leg3, 0.08726646259971647F, 0.0F, 0.0F);
        this.leg4 = new ModelRenderer(this, 32, 0);
        this.leg4.setRotationPoint(-8.0F, 8.0F, 5.0F);
        this.leg4.addBox(0.0F, 0.0F, 0.0F, 2, 16, 2, 0.0F);
        this.setRotation(leg4, 0.08726646259971647F, 0.0F, 0.0F);
        this.side2 = new ModelRenderer(this, 0, 48);
        this.side2.setRotationPoint(-7.0F, 3.0F, 6.0F);
        this.side2.addBox(0.0F, 0.0F, 0.0F, 14, 10, 1, 0.0F);
        this.side4 = new ModelRenderer(this, 32, 40);
        this.side4.setRotationPoint(7.0F, 4.0F, -6.0F);
        this.side4.addBox(0.0F, -1.0F, 0.0F, 1, 10, 12, 0.0F);
        this.leg1 = new ModelRenderer(this, 32, 0);
        this.leg1.setRotationPoint(6.0F, 8.0F, -7.0F);
        this.leg1.addBox(0.0F, 0.0F, 0.0F, 2, 16, 2, 0.0F);
        this.setRotation(leg1, -0.08726646259971647F, 0.0F, 0.0F);
        this.leg2 = new ModelRenderer(this, 32, 0);
        this.leg2.setRotationPoint(-8.0F, 8.0F, -7.0F);
        this.leg2.addBox(0.0F, 0.0F, 0.0F, 2, 16, 2, 0.0F);
        this.setRotation(leg2, -0.08726646259971647F, 0.0F, 0.0F);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.side3.render(f5);
        this.rod.render(f5);
        this.side1.render(f5);
        this.stone.render(f5);
        this.leg3.render(f5);
        this.leg4.render(f5);
        this.side2.render(f5);
        this.side4.render(f5);
        this.leg1.render(f5);
        this.leg2.render(f5);
        stone.rotateAngleY += 50;
    }

    public void render(float f5, boolean b, float r) {
        this.side3.render(f5);
        this.rod.render(f5);
        this.side1.render(f5);
        this.stone.render(f5);
        this.leg3.render(f5);
        this.leg4.render(f5);
        this.side2.render(f5);
        this.side4.render(f5);
        this.leg1.render(f5);
        this.leg2.render(f5);
        if (b) stone.render(0.0625F);
        stone.rotateAngleZ += r;
    }

    public void setGrindstoneRotation() {
        stone.render(0.0625F);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}