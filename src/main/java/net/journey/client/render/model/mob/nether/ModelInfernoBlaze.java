package net.journey.client.render.model.mob.nether;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelInfernoBlaze extends ModelBase {

    public ModelRenderer head;
    public ModelRenderer shield2;
    public ModelRenderer shield2_1;
    public ModelRenderer shield2_2;
    public ModelRenderer shield2_3;

    public ModelInfernoBlaze() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.shield2_2 = new ModelRenderer(this, 26, 32);
        this.shield2_2.setRotationPoint(0.0F, -1.46F, 0.0F);
        this.shield2_2.addBox(-5.0F, 0.0F, -10.0F, 10, 20, 2, 0.0F);
        this.setRotateAngle(shield2_2, -0.2792526803190927F, 0.0F, 0.0F);

        this.shield2_1 = new ModelRenderer(this, 0, 32);
        this.shield2_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shield2_1.addBox(8.0F, -1.46F, -5.0F, 2, 20, 10, 0.0F);
        this.setRotateAngle(shield2_1, 0.0F, 0.0F, -0.2792526803190927F);

        this.shield2_3 = new ModelRenderer(this, 26, 32);
        this.shield2_3.setRotationPoint(0.0F, -1.46F, 0.0F);
        this.shield2_3.addBox(-5.0F, 0.0F, 8.0F, 10, 20, 2, 0.0F);
        this.setRotateAngle(shield2_3, 0.2792526803190927F, 0.0F, 0.0F);

        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, -6.0F, 0.0F);
        this.head.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);

        this.shield2 = new ModelRenderer(this, 0, 32);
        this.shield2.setRotationPoint(0.0F, -1.46F, 0.0F);
        this.shield2.addBox(-10.0F, 0.0F, -5.0F, 2, 20, 10, 0.0F);
        this.setRotateAngle(shield2, 0.0F, 0.0F, 0.2792526803190927F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.shield2_2.render(f5);
        this.shield2_1.render(f5);
        this.shield2_3.render(f5);
        this.head.render(f5);
        this.shield2.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        float f = ageInTicks * (float) Math.PI * -0.1F;

        f = 0.47123894F + ageInTicks * (float) Math.PI * -0.05F;

        for (int k = 8; k < 12; ++k) {
            this.shield2.rotationPointY = 0.0F + MathHelper.cos(((float) k * 1.5F + ageInTicks) * 0.5F);
            this.shield2.rotationPointX = MathHelper.cos(f) * 5.0F;
            this.shield2.rotationPointZ = MathHelper.sin(f) * 5.0F;
            ++f;
        }

        for (int k = 8; k < 12; ++k) {
            this.shield2_1.rotationPointY = 0.0F + MathHelper.cos(((float) k * 1.5F + ageInTicks) * 0.5F);
            this.shield2_1.rotationPointX = MathHelper.cos(f) * 5.0F;
            this.shield2_1.rotationPointZ = MathHelper.sin(f) * 5.0F;
            ++f;
        }

        for (int k = 8; k < 12; ++k) {
            this.shield2_2.rotationPointY = 0.0F + MathHelper.cos(((float) k * 1.5F + ageInTicks) * 0.5F);
            this.shield2_2.rotationPointX = MathHelper.cos(f) * 5.0F;
            this.shield2_2.rotationPointZ = MathHelper.sin(f) * 5.0F;
            ++f;
        }

        for (int k = 8; k < 12; ++k) {
            this.shield2_3.rotationPointY = 0.0F + MathHelper.cos(((float) k * 1.5F + ageInTicks) * 0.5F);
            this.shield2_3.rotationPointX = MathHelper.cos(f) * 5.0F;
            this.shield2_3.rotationPointZ = MathHelper.sin(f) * 5.0F;
            ++f;
        }

        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.head.rotateAngleX = headPitch * 0.017453292F;
    }
}
