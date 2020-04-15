package net.journey.client.render.model.mob.depths;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelRoc extends ModelBase {

    ModelRenderer Body;
    ModelRenderer Neck;
    ModelRenderer Head;
    ModelRenderer Beak;
    ModelRenderer Feet1;
    ModelRenderer Feet2;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Feather;
    ModelRenderer Feather1;
    ModelRenderer Feather2;

    public ModelRoc() {
        textureWidth = 64;
        textureHeight = 64;

        Body = new ModelRenderer(this, 32, 0);
        Body.addBox(-4F, -8F, -4F, 8, 6, 8);
        Body.setRotationPoint(0F, 21F, 0F);
        Body.setTextureSize(64, 64);
        Body.mirror = true;
        setRotation(Body, 0F, 0F, 0F);
        Neck = new ModelRenderer(this, 44, 21);
        Neck.addBox(0F, 0F, 0F, 2, 7, 2);
        Neck.setRotationPoint(-1F, 9F, -6F);
        Neck.setTextureSize(64, 64);
        Neck.mirror = true;
        setRotation(Neck, 0.1858931F, 0F, 0F);
        Head = new ModelRenderer(this, 0, 0);
        Head.addBox(-3F, -6F, -5F, 6, 6, 8);
        Head.setRotationPoint(0F, 10F, -5F);
        Head.setTextureSize(64, 64);
        Head.mirror = true;
        setRotation(Head, 0F, 0F, 0F);
        Beak = new ModelRenderer(this, 43, 32);
        Beak.addBox(-3F, -2F, -9F, 6, 2, 4);
        Beak.setRotationPoint(0F, 10F, -5F);
        Beak.setTextureSize(64, 64);
        Beak.mirror = true;
        setRotation(Beak, 0F, 0F, 0F);
        Feet1 = new ModelRenderer(this, 44, 16);
        Feet1.addBox(0F, 5F, -2F, 3, 0, 2);
        Feet1.setRotationPoint(-3F, 19F, -1F);
        Feet1.setTextureSize(64, 64);
        Feet1.mirror = true;
        setRotation(Feet1, 0F, 0F, 0F);
        Feet2 = new ModelRenderer(this, 44, 16);
        Feet2.addBox(-2F, 5F, -2F, 3, 0, 2);
        Feet2.setRotationPoint(2F, 19F, -1F);
        Feet2.setTextureSize(64, 64);
        Feet2.mirror = true;
        setRotation(Feet2, 0F, 0F, 0F);
        Leg1 = new ModelRenderer(this, 56, 24);
        Leg1.addBox(0F, 0F, 0F, 1, 5, 1);
        Leg1.setRotationPoint(-2F, 19F, -1F);
        Leg1.setTextureSize(64, 64);
        Leg1.mirror = true;
        setRotation(Leg1, 0F, 0F, 0F);
        Leg2 = new ModelRenderer(this, 56, 24);
        Leg2.addBox(-1F, 0F, 0F, 1, 5, 1);
        Leg2.setRotationPoint(2F, 19F, -1F);
        Leg2.setTextureSize(64, 64);
        Leg2.mirror = true;
        setRotation(Leg2, 0F, 0F, 0F);
        Feather = new ModelRenderer(this, 1, 16);
        Feather.addBox(0F, 0F, 0F, 19, 14, 0);
        Feather.setRotationPoint(-9F, 1F, 4F);
        Feather.setTextureSize(64, 64);
        Feather.mirror = true;
        setRotation(Feather, 0F, 0F, 0F);
        Feather1 = new ModelRenderer(this, 1, 16);
        Feather1.addBox(0F, 0F, 0F, 19, 14, 0);
        Feather1.setRotationPoint(4F, 1F, 5F);
        Feather1.setTextureSize(64, 64);
        Feather1.mirror = true;
        setRotation(Feather1, -0.0698132F, 0F, 0.6981317F);
        Feather2 = new ModelRenderer(this, 1, 16);
        Feather2.addBox(0F, 0F, 0F, 19, 14, 0);
        Feather2.setRotationPoint(-18F, 12.5F, 5F);
        Feather2.setTextureSize(64, 64);
        Feather2.mirror = true;
        setRotation(Feather2, -0.0698132F, 0F, -0.6981317F);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Body.render(f5);
        Neck.render(f5);
        Head.render(f5);
        Beak.render(f5);
        Feet1.render(f5);
        Feet2.render(f5);
        Leg1.render(f5);
        Leg2.render(f5);
        Feather.render(f5);
        Feather1.render(f5);
        Feather2.render(f5);

    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.Head.rotateAngleY = f4 / (180F / (float) Math.PI);
        this.Beak.rotateAngleY = f4 / (180F / (float) Math.PI);
        this.Leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.Leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
        this.Feet2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.Feet1.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;

    }
}