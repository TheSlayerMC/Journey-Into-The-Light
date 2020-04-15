package net.journey.client.render.model.mob.terrania.mob;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelTerraScatterer - Dizzlepop12
 * Created using Tabula 5.1.0
 */
public class ModelTerraScatterer extends ModelBase {
    public ModelRenderer foot1;
    public ModelRenderer foot2;
    public ModelRenderer foot3;
    public ModelRenderer foot4;
    public ModelRenderer body;
    public ModelRenderer neck;
    public ModelRenderer head;

    public ModelTerraScatterer() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.head = new ModelRenderer(this, 0, 24);
        this.head.setRotationPoint(-0.0F, 7.0F, 0.0F);
        this.head.addBox(-3.0F, -6.0F, -5.0F, 6, 6, 6, 0.0F);
        this.neck = new ModelRenderer(this, 24, 0);
        this.neck.setRotationPoint(-3.5F, 4.0F, -2.0F);
        this.neck.addBox(0.0F, 0.0F, 0.0F, 7, 6, 4, 0.0F);
        this.foot2 = new ModelRenderer(this, 0, 0);
        this.foot2.setRotationPoint(-4.0F, 16.0F, -4.0F);
        this.foot2.addBox(-2.0F, 0.0F, -2.0F, 4, 8, 4, 0.0F);
        this.foot1 = new ModelRenderer(this, 0, 0);
        this.foot1.setRotationPoint(4.0F, 16.0F, -4.0F);
        this.foot1.addBox(-2.0F, 0.0F, -2.0F, 4, 8, 4, 0.0F);
        this.body = new ModelRenderer(this, 0, 40);
        this.body.setRotationPoint(-4.0F, 10.0F, -4.0F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 8, 10, 8, 0.0F);
        this.foot4 = new ModelRenderer(this, 0, 0);
        this.foot4.setRotationPoint(4.0F, 16.0F, 4.0F);
        this.foot4.addBox(-2.0F, 0.0F, -2.0F, 4, 8, 4, 0.0F);
        this.foot3 = new ModelRenderer(this, 0, 0);
        this.foot3.setRotationPoint(-4.0F, 16.0F, 4.0F);
        this.foot3.addBox(-2.0F, 0.0F, -2.0F, 4, 8, 4, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.head.render(f5);
        this.neck.render(f5);
        this.foot2.render(f5);
        this.foot1.render(f5);
        this.body.render(f5);
        this.foot4.render(f5);
        this.foot3.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.head.rotateAngleY = f4 / (180F / (float) Math.PI);
        this.head.rotateAngleX = f5 / (180F / (float) Math.PI);
        this.foot1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.foot2.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
        this.foot3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.foot4.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
    }

}
