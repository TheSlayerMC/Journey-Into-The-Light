package net.journey.client.render.model.mob.frozen;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFrozenTroll extends ModelBase {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer ear1;
    ModelRenderer ear2;
    ModelRenderer beard;

    public ModelFrozenTroll() {
        textureWidth = 64;
        textureHeight = 64;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        head.setRotationPoint(0F, 11F, 0F);
        head.setTextureSize(64, 64);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        body = new ModelRenderer(this, 16, 16);
        body.addBox(-4F, 0F, -2F, 8, 12, 4);
        body.setRotationPoint(0F, 7F, 0F);
        body.setTextureSize(64, 64);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        rightarm = new ModelRenderer(this, 40, 16);
        rightarm.addBox(-3F, -2F, -2F, 4, 10, 4);
        rightarm.setRotationPoint(-5F, 11F, 0F);
        rightarm.setTextureSize(64, 64);
        rightarm.mirror = true;
        setRotation(rightarm, 0F, 0F, 0F);
        leftarm = new ModelRenderer(this, 40, 16);
        leftarm.addBox(-1F, -2F, -2F, 4, 10, 4);
        leftarm.setRotationPoint(5F, 11F, 0F);
        leftarm.setTextureSize(64, 64);
        leftarm.mirror = true;
        setRotation(leftarm, 0F, 0F, 0F);
        rightleg = new ModelRenderer(this, 0, 16);
        rightleg.addBox(-2F, 0F, -2F, 4, 5, 4);
        rightleg.setRotationPoint(-2F, 19F, 0F);
        rightleg.setTextureSize(64, 64);
        rightleg.mirror = true;
        setRotation(rightleg, 0F, 0F, 0F);
        leftleg = new ModelRenderer(this, 0, 16);
        leftleg.addBox(-2F, 0F, -2F, 4, 5, 4);
        leftleg.setRotationPoint(2F, 19F, 0F);
        leftleg.setTextureSize(64, 64);
        leftleg.mirror = true;
        setRotation(leftleg, 0F, 0F, 0F);
        ear1 = new ModelRenderer(this, 0, 0);
        ear1.addBox(0F, -11F, 0F, 2, 2, 1);
        ear1.setRotationPoint(3F, 2F, -2F);
        ear1.setTextureSize(64, 64);
        ear1.mirror = true;
        setRotation(ear1, 0F, 0F, 0F);
        ear2 = new ModelRenderer(this, 0, 0);
        ear2.addBox(0F, -11F, 0F, 2, 2, 1);
        ear2.setRotationPoint(-5F, 2F, -2F);
        ear2.setTextureSize(64, 64);
        ear2.mirror = true;
        setRotation(ear2, 0F, 0F, 0F);
        beard = new ModelRenderer(this, 40, 33);
        beard.addBox(0F, 0F, 0F, 6, 6, 2);
        beard.setRotationPoint(-3F, 0F, -4F);
        beard.setTextureSize(64, 64);
        beard.mirror = true;
        setRotation(beard, 0F, 0F, 0F);
        head.addChild(beard);
        head.addChild(ear1);
        head.addChild(ear2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        head.render(f5);
        body.render(f5);
        rightarm.render(f5);
        leftarm.render(f5);
        rightleg.render(f5);
        leftleg.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        this.rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 2.0F * f1 * 0.5F;
        this.leftleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        this.rightarm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.leftarm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
        this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
        this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
    }
}