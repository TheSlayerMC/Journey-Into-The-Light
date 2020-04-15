package net.journey.client.render.model.mob.boil;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFrightener extends ModelBiped {

    ModelRenderer head1;
    ModelRenderer head;
    ModelRenderer head2;
    ModelRenderer body;
    ModelRenderer rightleg;
    ModelRenderer leftleg;

    public ModelFrightener() {
        textureWidth = 64;
        textureHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -7F, -4F, 8, 8, 8);
        head.setRotationPoint(0F, -1F, 0F);
        head.setTextureSize(64, 32);
        setRotation(head, 0F, 0F, 0F);
        head1 = new ModelRenderer(this, 0, 0);
        head1.addBox(-8F, -1F, -4F, 8, 8, 8);
        head1.setRotationPoint(-4F, 1F, 0F);
        head1.setTextureSize(64, 32);
        setRotation(head1, 0F, 0F, 0F);
        head2 = new ModelRenderer(this, 0, 0);
        head2.addBox(0F, -1F, -4F, 8, 8, 8);
        head2.setRotationPoint(4F, 1F, 0F);
        head2.setTextureSize(64, 32);
        setRotation(head2, 0F, 0F, 0F);
        body = new ModelRenderer(this, 16, 16);
        body.addBox(-4F, 0F, -2F, 8, 12, 4);
        body.setRotationPoint(0F, 0F, 0F);
        body.setTextureSize(64, 32);
        setRotation(body, 0F, 0F, 0F);
        rightleg = new ModelRenderer(this, 0, 16);
        rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
        rightleg.setRotationPoint(-2F, 12F, 0F);
        rightleg.setTextureSize(64, 32);
        setRotation(rightleg, 0F, 0F, 0F);
        leftleg = new ModelRenderer(this, 0, 16);
        leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
        leftleg.setRotationPoint(2F, 12F, 0F);
        leftleg.setTextureSize(64, 32);
        setRotation(leftleg, 0F, 0F, 0F);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        head1.render(f5);
        head.render(f5);
        head2.render(f5);
        body.render(f5);
        rightleg.render(f5);
        leftleg.render(f5);

    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.head.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.head.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.head1.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.head1.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.head2.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.head2.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        this.rightleg.rotateAngleY = 0.0F;
        this.leftleg.rotateAngleY = 0.0F;
        float f6;
        float f7;

        this.body.rotateAngleX = 0.0F;
        this.rightleg.rotationPointZ = 0.1F;
        this.leftleg.rotationPointZ = 0.1F;
        this.rightleg.rotationPointY = 12.0F;
        this.leftleg.rotationPointY = 12.0F;
        this.head.rotationPointY = -1.0F;
        this.head1.rotationPointY = -1.0F;
        this.head2.rotationPointY = -1.0F;

    }
}