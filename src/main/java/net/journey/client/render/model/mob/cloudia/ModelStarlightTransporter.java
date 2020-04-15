package net.journey.client.render.model.mob.cloudia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelStarlightTransporter extends ModelBase {

    ModelRenderer body;
    ModelRenderer arm1;
    ModelRenderer arm2;
    ModelRenderer arm3;
    ModelRenderer arm4;
    ModelRenderer head;
    ModelRenderer head0;
    ModelRenderer head1;
    ModelRenderer head2;
    ModelRenderer head3;

    public ModelStarlightTransporter() {
        textureWidth = 64;
        textureHeight = 32;

        body = new ModelRenderer(this, 0, 0);
        body.addBox(-4F, -8F, -4F, 8, 8, 8);
        body.setRotationPoint(0F, 18F, 0F);
        body.setTextureSize(64, 32);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        arm1 = new ModelRenderer(this, 40, 16);
        arm1.addBox(-1F, 0F, -2F, 4, 12, 4);
        arm1.setRotationPoint(5F, 12F, 0F);
        arm1.setTextureSize(64, 32);
        arm1.mirror = true;
        setRotation(arm1, 0F, 0F, 0F);
        arm2 = new ModelRenderer(this, 40, 16);
        arm2.addBox(-3F, 0F, -2F, 4, 12, 4);
        arm2.setRotationPoint(-5F, 12F, 0F);
        arm2.setTextureSize(64, 32);
        arm2.mirror = true;
        setRotation(arm2, 0F, 0F, 0F);
        arm3 = new ModelRenderer(this, 40, 16);
        arm3.addBox(-2F, 0F, 0F, 4, 12, 4);
        arm3.setRotationPoint(0F, 12F, 4F);
        arm3.setTextureSize(64, 32);
        arm3.mirror = true;
        setRotation(arm3, 0F, 0F, 0F);
        arm4 = new ModelRenderer(this, 40, 16);
        arm4.addBox(-2F, 0F, -3F, 4, 12, 4);
        arm4.setRotationPoint(0F, 12F, -5F);
        arm4.setTextureSize(64, 32);
        arm4.mirror = true;
        setRotation(arm4, 0F, 0F, 0F);
        head = new ModelRenderer(this, 0, 16);
        head.addBox(0F, 0F, 0F, 6, 6, 6);
        head.setRotationPoint(1F, 4F, -3F);
        head.setTextureSize(64, 32);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        head0 = new ModelRenderer(this, 0, 16);
        head0.addBox(0F, 0F, 0F, 6, 6, 6);
        head0.setRotationPoint(-3F, 4F, 1F);
        head0.setTextureSize(64, 32);
        head0.mirror = true;
        setRotation(head0, 0F, 0F, 0F);
        head1 = new ModelRenderer(this, 0, 16);
        head1.addBox(0F, 0F, 0F, 6, 6, 6);
        head1.setRotationPoint(-7F, 4F, -3F);
        head1.setTextureSize(64, 32);
        head1.mirror = true;
        setRotation(head1, 0F, 0F, 0F);
        head2 = new ModelRenderer(this, 0, 16);
        head2.addBox(0F, 0F, 0F, 6, 6, 6);
        head2.setRotationPoint(-3F, 4F, -7F);
        head2.setTextureSize(64, 32);
        head2.mirror = true;
        setRotation(head2, 0F, 0F, 0F);
        head3 = new ModelRenderer(this, 0, 16);
        head3.addBox(0F, 0F, 0F, 6, 6, 6);
        head3.setRotationPoint(-3F, -2F, -3F);
        head3.setTextureSize(64, 32);
        head3.mirror = true;
        setRotation(head3, 0F, 0F, 0F);

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
        head.render(f5);
        head0.render(f5);
        head1.render(f5);
        head2.render(f5);
        head3.render(f5);

    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.arm2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.arm1.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
        this.arm3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.arm4.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;

    }
}