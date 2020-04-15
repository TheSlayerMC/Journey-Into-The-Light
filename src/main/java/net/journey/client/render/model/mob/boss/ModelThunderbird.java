package net.journey.client.render.model.mob.boss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelThunderbird extends ModelBase {

    ModelRenderer body;
    ModelRenderer head;
    ModelRenderer beak;
    ModelRenderer wingL;
    ModelRenderer wingR;
    ModelRenderer back;

    public ModelThunderbird() {
        textureWidth = 128;
        textureHeight = 64;

        body = new ModelRenderer(this, 0, 0);
        body.addBox(-2F, -2F, 0F, 4, 3, 14);
        body.setRotationPoint(0F, 20F, -7F);
        body.setTextureSize(64, 32);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        head = new ModelRenderer(this, 0, 18);
        head.addBox(-1.5F, -1.5F, -3F, 3, 3, 3);
        head.setRotationPoint(0F, 18F, -6F);
        head.setTextureSize(64, 32);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        beak = new ModelRenderer(this, 0, 24);
        beak.addBox(-1.5F, 0.5F, -5F, 3, 1, 2);
        beak.setRotationPoint(0F, 18F, -6F);
        beak.setTextureSize(64, 32);
        beak.mirror = true;
        setRotation(beak, 0F, 0F, 0F);
        wingL = new ModelRenderer(this, 23, 0);
        wingL.addBox(0F, 0F, 0F, 8, 0, 10);
        wingL.setRotationPoint(2F, 19F, -3F);
        wingL.setTextureSize(64, 32);
        wingL.mirror = true;
        setRotation(wingL, 0F, 0F, 0F);
        wingR = new ModelRenderer(this, 23, 0);
        wingR.addBox(-8F, 0F, 0F, 8, 0, 10);
        wingR.setRotationPoint(-2F, 19F, -3F);
        wingR.setTextureSize(64, 32);
        wingR.mirror = true;
        setRotation(wingR, 0F, 0F, 0F);
        back = new ModelRenderer(this, 0, 40);
        back.addBox(-1.5F, -1F, 0F, 3, 2, 6);
        back.setRotationPoint(0F, 19.5F, 7F);
        back.setTextureSize(64, 32);
        back.mirror = true;
        setRotation(back, 0F, 0F, 0F);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        body.render(f5);
        head.render(f5);
        beak.render(f5);
        wingL.render(f5);
        wingR.render(f5);
        back.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;

    }
}