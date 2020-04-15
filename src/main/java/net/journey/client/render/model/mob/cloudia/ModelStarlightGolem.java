package net.journey.client.render.model.mob.cloudia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelStarlightGolem extends ModelBase {

    ModelRenderer Booty;
    ModelRenderer Head;
    ModelRenderer Body;
    ModelRenderer Arm1;
    ModelRenderer Arm2;
    ModelRenderer Shoulder1;
    ModelRenderer Shoulder2;
    ModelRenderer Foot1;
    ModelRenderer Foot2;

    public ModelStarlightGolem() {
        textureWidth = 128;
        textureHeight = 128;

        Booty = new ModelRenderer(this, 64, 0);
        Booty.addBox(0F, 0F, 0F, 16, 16, 16);
        Booty.setRotationPoint(-8F, -1F, -4F);
        Booty.setTextureSize(128, 128);
        Booty.mirror = true;
        setRotation(Booty, 0.122173F, 0F, 0F);
        Head = new ModelRenderer(this, 0, 0);
        Head.addBox(0F, 0F, 0F, 16, 16, 16);
        Head.setRotationPoint(-8F, -24F, -8F);
        Head.setTextureSize(128, 128);
        Head.mirror = true;
        setRotation(Head, 0.122173F, 0F, 0F);
        Body = new ModelRenderer(this, 0, 32);
        Body.addBox(0F, 0F, 0F, 8, 8, 8);
        Body.setRotationPoint(-4F, -9F, -2F);
        Body.setTextureSize(128, 128);
        Body.mirror = true;
        setRotation(Body, 0.122173F, 0F, 0F);
        Arm1 = new ModelRenderer(this, 32, 32);
        Arm1.addBox(1F, 10F, 1F, 8, 24, 8);
        Arm1.setRotationPoint(8F, -18F, -4F);
        Arm1.setTextureSize(128, 128);
        Arm1.mirror = true;
        setRotation(Arm1, 0F, 0F, 0F);
        Arm2 = new ModelRenderer(this, 64, 32);
        Arm2.addBox(1F, 10F, 1F, 8, 24, 8);
        Arm2.setRotationPoint(-18F, -18F, -4F);
        Arm2.setTextureSize(128, 128);
        Arm2.mirror = true;
        setRotation(Arm2, 0F, 0F, 0F);
        Shoulder1 = new ModelRenderer(this, 0, 84);
        Shoulder1.addBox(0F, 0F, 0F, 10, 10, 10);
        Shoulder1.setRotationPoint(8F, -18F, -4F);
        Shoulder1.setTextureSize(128, 128);
        Shoulder1.mirror = true;
        setRotation(Shoulder1, 0F, 0F, 0F);
        Shoulder2 = new ModelRenderer(this, 0, 64);
        Shoulder2.addBox(0F, 0F, 0F, 10, 10, 10);
        Shoulder2.setRotationPoint(-18F, -18F, -4F);
        Shoulder2.setTextureSize(128, 128);
        Shoulder2.mirror = true;
        setRotation(Shoulder2, 0F, 0F, 0F);
        Foot1 = new ModelRenderer(this, 40, 64);
        Foot1.addBox(0F, 34F, 0F, 10, 8, 10);
        Foot1.setRotationPoint(8F, -18F, -4F);
        Foot1.setTextureSize(128, 128);
        Foot1.mirror = true;
        setRotation(Foot1, 0F, 0F, 0F);
        Foot2 = new ModelRenderer(this, 40, 64);
        Foot2.addBox(0F, 34F, 0F, 10, 8, 10);
        Foot2.setRotationPoint(-18F, -18F, -4F);
        Foot2.setTextureSize(128, 128);
        Foot2.mirror = true;
        setRotation(Foot2, 0F, 0F, 0F);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Booty.render(f5);
        Head.render(f5);
        Body.render(f5);
        Arm1.render(f5);
        Arm2.render(f5);
        Shoulder1.render(f5);
        Shoulder2.render(f5);
        Foot1.render(f5);
        Foot2.render(f5);

    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.Arm2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.Arm1.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
        this.Shoulder2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.Shoulder1.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
        this.Foot2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.Foot1.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;

    }
}