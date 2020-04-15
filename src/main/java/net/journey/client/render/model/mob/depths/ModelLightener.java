package net.journey.client.render.model.mob.depths;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLightener extends ModelBase {

    ModelRenderer body;
    ModelRenderer arm1;
    ModelRenderer arm2;
    ModelRenderer arm3;
    ModelRenderer arm4;
    ModelRenderer eye1;
    ModelRenderer eye2;
    ModelRenderer eye3;
    ModelRenderer eye4;

    public ModelLightener() {
        textureWidth = 64;
        textureHeight = 32;

        body = new ModelRenderer(this, 30, 0);
        body.addBox(-3F, -3F, -3F, 8, 8, 8);
        body.setRotationPoint(0F, -8F, -1F);
        body.setTextureSize(64, 32);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        arm1 = new ModelRenderer(this, 0, 14);
        arm1.addBox(-4F, -1F, -1F, 6, 2, 2);
        arm1.setRotationPoint(-3F, 5F, 0F);
        arm1.setTextureSize(64, 32);
        arm1.mirror = true;
        setRotation(arm1, 0F, 0F, 0F);
        arm2 = new ModelRenderer(this, 14, 14);
        arm2.addBox(-1F, -4F, -1F, 2, 6, 2);
        arm2.setRotationPoint(1F, 1F, 0F);
        arm2.setTextureSize(64, 32);
        arm2.mirror = true;
        setRotation(arm2, 0F, 0F, 0F);
        arm3 = new ModelRenderer(this, 0, 14);
        arm3.addBox(-1F, -1F, -1F, 6, 2, 2);
        arm3.setRotationPoint(8F, 5F, 0F);
        arm3.setTextureSize(64, 32);
        arm3.mirror = true;
        setRotation(arm3, 3.141593F, 3.141593F, 0F);
        arm4 = new ModelRenderer(this, 14, 14);
        arm4.addBox(-1F, -1F, -1F, 2, 6, 2);
        arm4.setRotationPoint(1F, 9F, 0F);
        arm4.setTextureSize(64, 32);
        arm4.mirror = true;
        setRotation(arm4, 0F, 0F, 0F);
        eye1 = new ModelRenderer(this, 30, 16);
        eye1.addBox(-3F, -3F, -3F, 6, 6, 6);
        eye1.setRotationPoint(1F, 5F, 0F);
        eye1.setTextureSize(64, 32);
        eye1.mirror = true;
        setRotation(eye1, 0F, 0F, 0F);
        eye2 = new ModelRenderer(this, 30, 0);
        eye2.addBox(-4F, -4F, -4F, 8, 8, 8);
        eye2.setRotationPoint(13F, 5F, 0F);
        eye2.setTextureSize(64, 32);
        eye2.mirror = true;
        setRotation(eye2, 0F, 0F, 0F);
        eye3 = new ModelRenderer(this, 30, 0);
        eye3.addBox(-8F, -4F, -3F, 8, 8, 8);
        eye3.setRotationPoint(-7F, 5F, -1F);
        eye3.setTextureSize(64, 32);
        eye3.mirror = true;
        setRotation(eye3, 0F, 0F, 0F);
        eye4 = new ModelRenderer(this, 30, 0);
        eye4.addBox(0F, 0F, 0F, 8, 8, 8);
        eye4.setRotationPoint(-3F, 13F, -4F);
        eye4.setTextureSize(64, 32);
        eye4.mirror = true;
        setRotation(eye4, 0F, 0F, 0F);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        body.render(f5);
        arm1.render(f5);
        arm2.render(f5);
        arm3.render(f5);
        arm4.render(f5);
        eye1.render(f5);
        eye2.render(f5);
        eye3.render(f5);
        eye4.render(f5);

    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);

    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;

    }
}