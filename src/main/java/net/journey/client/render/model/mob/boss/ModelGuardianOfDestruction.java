package net.journey.client.render.model.mob.boss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * GuardianOfDestruction - Dizzlepop12
 * Created using Tabula 7.1.0
 */

public class ModelGuardianOfDestruction extends ModelBase {

    public ModelRenderer main;
    public ModelRenderer top;
    public ModelRenderer bottom;
    public ModelRenderer side1;
    public ModelRenderer side2;
    public ModelRenderer side3;
    public ModelRenderer face;

    public ModelGuardianOfDestruction() {
    	this.textureWidth = 64;
        this.textureHeight = 128;
        this.bottom = new ModelRenderer(this, 0, 110);
        this.bottom.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.bottom.addBox(-6.0F, 8.0F, -6.0F, 12, 2, 12, 0.0F);
        this.side2 = new ModelRenderer(this, 0, 74);
        this.side2.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.side2.addBox(8.0F, -6.0F, -6.0F, 2, 12, 12, 0.0F);
        this.side3 = new ModelRenderer(this, 14, 54);
        this.side3.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.side3.addBox(-6.0F, -6.0F, 8.0F, 12, 12, 2, 0.0F);
        this.side1 = new ModelRenderer(this, 0, 74);
        this.side1.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.side1.addBox(-10.0F, -6.0F, -6.0F, 2, 12, 12, 0.0F);
        this.face = new ModelRenderer(this, 0, 34);
        this.face.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.face.addBox(-6.0F, -6.0F, -10.0F, 12, 12, 2, 0.0F);
        this.top = new ModelRenderer(this, 0, 110);
        this.top.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.top.addBox(-6.0F, -10.0F, -6.0F, 12, 2, 12, 0.0F);
        this.main = new ModelRenderer(this, 0, 0);
        this.main.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.main.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.bottom.render(f5);
        this.side2.render(f5);
        this.side3.render(f5);
        this.side1.render(f5);
        this.face.render(f5);
        this.top.render(f5);
        this.main.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {}
}