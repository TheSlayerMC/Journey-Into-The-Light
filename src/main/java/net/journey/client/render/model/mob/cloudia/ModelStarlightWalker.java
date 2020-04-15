package net.journey.client.render.model.mob.cloudia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelStarlightWalker extends ModelBase {
    ModelRenderer head;
    ModelRenderer leg3;
    ModelRenderer leg4;

    public ModelStarlightWalker() {

        textureWidth = 64;
        textureHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        head.setRotationPoint(0F, 16F, -4F);
        head.setTextureSize(64, 32);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        leg3 = new ModelRenderer(this, 0, 16);
        leg3.addBox(-2F, 0F, -2F, 4, 12, 4);
        leg3.setRotationPoint(-3F, 12F, -4F);
        leg3.setTextureSize(64, 32);
        leg3.mirror = true;
        setRotation(leg3, 0F, 0F, 0.0349066F);
        leg4 = new ModelRenderer(this, 0, 16);
        leg4.addBox(-2F, 0F, -2F, 4, 12, 4);
        leg4.setRotationPoint(3F, 12F, -4F);
        leg4.setTextureSize(64, 32);
        leg4.mirror = true;
        setRotation(leg4, 0F, 0F, -0.0349066F);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        head.render(f5);
        leg3.render(f5);
        leg4.render(f5);

    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
    }

}