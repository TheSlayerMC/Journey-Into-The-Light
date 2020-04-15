package net.journey.client.render.model.mob.frozen;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFrozenFrostbiter extends ModelBase {

    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer ice4;
    ModelRenderer ice3;
    ModelRenderer ice2;
    ModelRenderer ice1;
    ModelRenderer icicle;

    public ModelFrozenFrostbiter() {
        textureWidth = 64;
        textureHeight = 64;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 6, 8, 8);
        head.setRotationPoint(1F, 0F, -1F);
        head.setTextureSize(64, 64);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        body = new ModelRenderer(this, 16, 16);
        body.addBox(-4F, 0F, -2F, 2, 8, 2);
        body.setRotationPoint(3F, 0F, 0F);
        body.setTextureSize(64, 64);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        ice4 = new ModelRenderer(this, 8, 16);
        ice4.addBox(-3F, -2F, -2F, 2, 10, 2);
        ice4.setRotationPoint(-2F, 8F, 0F);
        ice4.setTextureSize(64, 64);
        ice4.mirror = true;
        setRotation(ice4, 0F, 0F, 0F);
        ice3 = new ModelRenderer(this, 8, 16);
        ice3.addBox(-1F, -2F, -2F, 2, 10, 2);
        ice3.setRotationPoint(4F, 8F, 0F);
        ice3.setTextureSize(64, 64);
        ice3.mirror = true;
        setRotation(ice3, 0F, 0F, 0F);
        ice2 = new ModelRenderer(this, 0, 16);
        ice2.addBox(-2F, 0F, -2F, 2, 10, 2);
        ice2.setRotationPoint(1F, 4F, -6F);
        ice2.setTextureSize(64, 64);
        ice2.mirror = true;
        setRotation(ice2, 0F, 0F, 0F);
        ice1 = new ModelRenderer(this, 0, 16);
        ice1.addBox(-2F, 0F, -2F, 2, 10, 2);
        ice1.setRotationPoint(1F, 4F, 6F);
        ice1.setTextureSize(64, 64);
        ice1.mirror = true;
        setRotation(ice1, 0F, 0F, 0F);
        icicle = new ModelRenderer(this, 0, 28);
        icicle.addBox(-4F, 0F, -4F, 6, 4, 8);
        icicle.setRotationPoint(1F, 0F, -1F);
        icicle.setTextureSize(64, 64);
        icicle.mirror = true;
        setRotation(icicle, 0F, 0F, 0F);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        head.render(f5);
        body.render(f5);
        ice4.render(f5);
        ice3.render(f5);
        ice2.render(f5);
        ice1.render(f5);
        icicle.render(f5);

    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
    }

}