package net.journey.client.render.model.mob.overworld.desert;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSandCrawler extends ModelBase {

    ModelRenderer body1;
    ModelRenderer body2;
    ModelRenderer body3;
    ModelRenderer neck;
    ModelRenderer tophead;
    ModelRenderer bottomhead;
    ModelRenderer leftarm;
    ModelRenderer rightarm;
    ModelRenderer midleft;
    ModelRenderer leftleft;
    ModelRenderer rightleft;
    ModelRenderer midright;
    ModelRenderer leftright;
    ModelRenderer rightright;

    public ModelSandCrawler() {
        textureWidth = 128;
        textureHeight = 64;

        body1 = new ModelRenderer(this, 0, 0);
        body1.addBox(0F, 0F, 0F, 10, 12, 17);
        body1.setRotationPoint(-5F, 6F, -7F);
        body1.setTextureSize(128, 64);
        body1.mirror = true;
        setRotation(body1, -0.418879F, 0F, 0F);
        body2 = new ModelRenderer(this, 79, 1);
        body2.addBox(0F, 0F, 0F, 9, 8, 15);
        body2.setRotationPoint(-4.5F, 13F, 6F);
        body2.setTextureSize(128, 64);
        body2.mirror = true;
        setRotation(body2, -0.122173F, 0F, 0F);
        body3 = new ModelRenderer(this, 82, 25);
        body3.addBox(0F, 0F, 0F, 8, 6, 14);
        body3.setRotationPoint(-4.1F, 16F, 19F);
        body3.setTextureSize(128, 64);
        body3.mirror = true;
        setRotation(body3, 0.0174533F, -0.0349066F, 0F);
        neck = new ModelRenderer(this, 54, 1);
        neck.addBox(0F, 0F, 0F, 8, 6, 4);
        neck.setRotationPoint(-4F, 6F, -10F);
        neck.setTextureSize(128, 64);
        neck.mirror = true;
        setRotation(neck, -0.2617994F, 0F, 0F);
        tophead = new ModelRenderer(this, 41, 30);
        tophead.addBox(0F, 0F, -10F, 10, 6, 10);
        tophead.setRotationPoint(0F, 4F, -8F);
        tophead.setTextureSize(128, 64);
        tophead.mirror = true;
        setRotation(tophead, -0.0523599F, 0.7679449F, -0.0523599F);
        bottomhead = new ModelRenderer(this, 41, 47);
        bottomhead.addBox(0F, 0F, -10F, 10, 3, 10);
        bottomhead.setRotationPoint(0F, 9F, -9F);
        bottomhead.setTextureSize(128, 64);
        bottomhead.mirror = true;
        setRotation(bottomhead, 0.0523599F, 0.7679449F, 0.0523599F);
        leftarm = new ModelRenderer(this, 1, 47);
        leftarm.addBox(0F, -2F, -12F, 5, 4, 12);
        leftarm.setRotationPoint(5F, 13F, -4F);
        leftarm.setTextureSize(128, 64);
        leftarm.mirror = true;
        setRotation(leftarm, 0.8028515F, -0.2792527F, 0F);
        rightarm = new ModelRenderer(this, 1, 47);
        rightarm.addBox(-5F, -2F, -12F, 5, 4, 12);
        rightarm.setRotationPoint(-5F, 13F, -4F);
        rightarm.setTextureSize(128, 64);
        rightarm.mirror = true;
        setRotation(rightarm, 0.8028515F, 0.2792527F, 0F);
        midleft = new ModelRenderer(this, 0, 32);
        midleft.addBox(0F, 5.6F, -19F, 4, 3, 11);
        midleft.setRotationPoint(5F, 13F, -4F);
        midleft.setTextureSize(128, 64);
        midleft.mirror = true;
        setRotation(midleft, 0.1396263F, -0.3141593F, 0F);
        leftleft = new ModelRenderer(this, 84, 50);
        leftleft.addBox(1F, 6F, -18F, 2, 3, 7);
        leftleft.setRotationPoint(5F, 13F, -4F);
        leftleft.setTextureSize(128, 64);
        leftleft.mirror = true;
        setRotation(leftleft, 0.122173F, -0.5235988F, 0F);
        rightleft = new ModelRenderer(this, 84, 50);
        rightleft.addBox(1F, 6F, -17F, 2, 3, 7);
        rightleft.setRotationPoint(5F, 13F, -4F);
        rightleft.setTextureSize(128, 64);
        rightleft.mirror = true;
        setRotation(rightleft, 0.122173F, -0.1047198F, 0F);
        midright = new ModelRenderer(this, 0, 32);
        midright.addBox(-4.5F, 5F, -19F, 4, 3, 11);
        midright.setRotationPoint(-5F, 13F, -4F);
        midright.setTextureSize(128, 64);
        midright.mirror = true;
        setRotation(midright, 0.1396263F, 0.2792527F, 0F);
        leftright = new ModelRenderer(this, 84, 50);
        leftright.addBox(-4F, 5.5F, -16F, 2, 3, 7);
        leftright.setRotationPoint(-5.5F, 13F, -4F);
        leftright.setTextureSize(128, 64);
        leftright.mirror = true;
        setRotation(leftright, 0.122173F, -0.0872665F, 0F);
        rightright = new ModelRenderer(this, 84, 50);
        rightright.addBox(-3F, 5F, -18F, 2, 3, 7);
        rightright.setRotationPoint(-5F, 13F, -4F);
        rightright.setTextureSize(128, 64);
        rightright.mirror = true;
        setRotation(rightright, 0.122173F, 0.5934119F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        body1.render(f5);
        body2.render(f5);
        body3.render(f5);
        neck.render(f5);
        tophead.render(f5);
        bottomhead.render(f5);
        leftarm.render(f5);
        rightarm.render(f5);
        midleft.render(f5);
        leftleft.render(f5);
        rightleft.render(f5);
        midright.render(f5);
        leftright.render(f5);
        rightright.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.bottomhead.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.bottomhead.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.tophead.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.tophead.rotateAngleX = par5 / (180F / (float) Math.PI);

        float f = 0.4F;

        this.leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * f * par2 + 0.8F;
        this.rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * f * par2 + 0.8F;

        this.midleft.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * f * par2;
        this.midright.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * f * par2;

        this.leftleft.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * f * par2;
        this.leftright.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * f * par2;

        this.rightleft.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * f * par2;
        this.rightright.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * f * par2;
    }
}