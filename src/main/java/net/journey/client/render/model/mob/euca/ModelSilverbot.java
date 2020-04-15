package net.journey.client.render.model.mob.euca;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSilverbot extends ModelBase {

    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;

    public ModelSilverbot() {

        textureWidth = 64;
        textureHeight = 64;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 4, 8);
        head.setRotationPoint(0F, 12F, 0F);
        head.setTextureSize(64, 64);
        setRotation(head, 0F, 0F, 0F);
        body = new ModelRenderer(this, 16, 16);
        body.addBox(-4F, 0F, -2F, 8, 8, 4);
        body.setRotationPoint(0F, 8F, 0F);
        body.setTextureSize(64, 64);
        setRotation(body, 0F, 0F, 0F);
        rightarm = new ModelRenderer(this, 40, 16);
        rightarm.addBox(-3F, -2F, -2F, 3, 10, 4);
        rightarm.setRotationPoint(-4F, 10F, 0F);
        rightarm.setTextureSize(64, 64);
        setRotation(rightarm, 0F, 0F, 0F);
        leftarm = new ModelRenderer(this, 40, 16);
        leftarm.addBox(-1F, -2F, -2F, 3, 10, 4);
        leftarm.setRotationPoint(5F, 10F, 0F);
        leftarm.setTextureSize(64, 64);
        setRotation(leftarm, 0F, 0F, 0F);
        rightleg = new ModelRenderer(this, 0, 16);
        rightleg.addBox(-2F, 0F, -2F, 4, 8, 4);
        rightleg.setRotationPoint(-2F, 16F, 0F);
        rightleg.setTextureSize(64, 64);
        setRotation(rightleg, 0F, 0F, 0F);
        leftleg = new ModelRenderer(this, 0, 16);
        leftleg.addBox(-2F, 0F, -2F, 4, 8, 4);
        leftleg.setRotationPoint(2F, 16F, 0F);
        leftleg.setTextureSize(64, 64);
        setRotation(leftleg, 0F, 0F, 0F);

    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
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

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.head.rotateAngleY = f4 / (180F / (float) Math.PI);
        this.rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.leftleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
        this.rightarm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.leftarm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;

    }
}