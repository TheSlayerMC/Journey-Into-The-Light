package net.journey.client.render.model.mob.boss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelLogger extends ModelBase {

    ModelRenderer head;
    ModelRenderer head1;
    ModelRenderer head2;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer shoulder2;
    ModelRenderer Shoulder1;
    ModelRenderer foot1;
    ModelRenderer foot2;

    public ModelLogger() {
        textureWidth = 128;
        textureHeight = 64;

        head = new ModelRenderer(this, 64, 0);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        head.setRotationPoint(0F, 0F, 0F);
        head.setTextureSize(128, 64);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        head1 = new ModelRenderer(this, 32, 0);
        head1.addBox(-8F, -4F, -4F, 8, 8, 8);
        head1.setRotationPoint(-15F, 1F, 0F);
        head1.setTextureSize(128, 64);
        head1.mirror = true;
        setRotation(head1, 0F, 0F, 0F);
        head2 = new ModelRenderer(this, 32, 0);
        head2.addBox(0F, -4F, -4F, 8, 8, 8);
        head2.setRotationPoint(15F, 1F, 0F);
        head2.setTextureSize(128, 64);
        head2.mirror = true;
        setRotation(head2, 0F, 0F, 0F);
        body = new ModelRenderer(this, 16, 16);
        body.addBox(-4F, 0F, -2F, 8, 6, 4);
        body.setRotationPoint(0F, 0F, -1F);
        body.setTextureSize(128, 64);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        rightarm = new ModelRenderer(this, 40, 24);
        rightarm.addBox(-3F, -2F, -2F, 4, 4, 4);
        rightarm.setRotationPoint(-5F, 2F, 0F);
        rightarm.setTextureSize(128, 64);
        rightarm.mirror = true;
        setRotation(rightarm, 0F, 0F, 0F);
        leftarm = new ModelRenderer(this, 40, 16);
        leftarm.addBox(-1F, -2F, -2F, 4, 4, 4);
        leftarm.setRotationPoint(5F, 2F, 0F);
        leftarm.setTextureSize(128, 64);
        leftarm.mirror = true;
        setRotation(leftarm, 0F, 0F, 0F);
        rightleg = new ModelRenderer(this, 56, 16);
        rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
        rightleg.setRotationPoint(-12F, 5F, 0F);
        rightleg.setTextureSize(128, 64);
        rightleg.mirror = true;
        setRotation(rightleg, 0F, 0F, 0F);
        leftleg = new ModelRenderer(this, 0, 16);
        leftleg.addBox(0F, 0F, 0F, 4, 12, 4);
        leftleg.setRotationPoint(10F, 5F, -2F);
        leftleg.setTextureSize(128, 64);
        leftleg.mirror = true;
        setRotation(leftleg, 0F, 0F, 0F);
        shoulder2 = new ModelRenderer(this, 96, 16);
        shoulder2.addBox(-3F, 0F, -3F, 7, 7, 7);
        shoulder2.setRotationPoint(11F, -2F, -1F);
        shoulder2.setTextureSize(128, 64);
        shoulder2.mirror = true;
        setRotation(shoulder2, 0F, 0F, 0F);
        Shoulder1 = new ModelRenderer(this, 96, 0);
        Shoulder1.addBox(-4F, 0F, -3F, 7, 7, 7);
        Shoulder1.setRotationPoint(-11F, -2F, -1F);
        Shoulder1.setTextureSize(128, 64);
        Shoulder1.mirror = true;
        setRotation(Shoulder1, 0F, 0F, 0F);
        foot1 = new ModelRenderer(this, 0, 32);
        foot1.addBox(0F, 0F, 0F, 6, 7, 6);
        foot1.setRotationPoint(-15F, 17F, -3F);
        foot1.setTextureSize(128, 64);
        foot1.mirror = true;
        setRotation(foot1, 0F, 0F, 0F);
        foot2 = new ModelRenderer(this, 24, 32);
        foot2.addBox(0F, 0F, 0F, 6, 7, 6);
        foot2.setRotationPoint(9F, 17F, -3F);
        foot2.setTextureSize(128, 64);
        foot2.mirror = true;
        setRotation(foot2, 0F, 0F, 0F);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        head.render(f5);
        head1.render(f5);
        head2.render(f5);
        body.render(f5);
        rightarm.render(f5);
        leftarm.render(f5);
        rightleg.render(f5);
        leftleg.render(f5);
        shoulder2.render(f5);
        Shoulder1.render(f5);
        foot1.render(f5);
        foot2.render(f5);

    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;

    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.foot1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.foot2.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
        this.Shoulder1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.shoulder2.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
        this.rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.leftleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
    }
}