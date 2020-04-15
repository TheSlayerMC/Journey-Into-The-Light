package net.journey.client.render.model.mob.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBoomBoom extends ModelBase {

    private ModelRenderer head;
    private ModelRenderer body;
    private ModelRenderer rightarm;
    private ModelRenderer leftarm;
    private ModelRenderer rightleg;
    private ModelRenderer leftleg;
    private ModelRenderer leftlegBack;
    private ModelRenderer rightlegBack;

    public ModelBoomBoom() {
        textureWidth = 64;
        textureHeight = 64;
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -4F, -4F, 8, 8, 8);
        head.setRotationPoint(0F, -8F, 0F);
        head.setTextureSize(64, 64);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        body = new ModelRenderer(this, 0, 31);
        body.addBox(0F, 0F, 0F, 16, 16, 16);
        body.setRotationPoint(-8F, -4F, -8F);
        body.setTextureSize(64, 64);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        rightarm = new ModelRenderer(this, 40, 1);
        rightarm.addBox(0F, 0F, 0F, 4, 21, 4);
        rightarm.setRotationPoint(-12F, -4F, -1F);
        rightarm.setTextureSize(64, 64);
        rightarm.mirror = true;
        setRotation(rightarm, 0F, 0F, 0F);
        leftarm = new ModelRenderer(this, 40, 1);
        leftarm.addBox(0F, 0F, 0F, 4, 21, 4);
        leftarm.setRotationPoint(8F, -4F, -1F);
        leftarm.setTextureSize(64, 64);
        leftarm.mirror = true;
        setRotation(leftarm, 0F, 0F, 0F);
        rightleg = new ModelRenderer(this, 0, 16);
        rightleg.addBox(0F, 0F, 0F, 3, 12, 3);
        rightleg.setRotationPoint(-7F, 12F, -7F);
        rightleg.setTextureSize(64, 64);
        rightleg.mirror = true;
        setRotation(rightleg, 0F, 0F, 0F);
        leftleg = new ModelRenderer(this, 0, 16);
        leftleg.addBox(0F, 0F, 0F, 3, 12, 3);
        leftleg.setRotationPoint(4F, 12F, -7F);
        leftleg.setTextureSize(64, 64);
        leftleg.mirror = true;
        setRotation(leftleg, 0F, 0F, 0F);
        leftlegBack = new ModelRenderer(this, 0, 16);
        leftlegBack.addBox(0F, 0F, 0F, 3, 12, 3);
        leftlegBack.setRotationPoint(4F, 12F, 4F);
        leftlegBack.setTextureSize(64, 64);
        leftlegBack.mirror = true;
        setRotation(leftlegBack, 0F, 0F, 0F);
        rightlegBack = new ModelRenderer(this, 0, 16);
        rightlegBack.addBox(0F, 0F, 0F, 3, 12, 3);
        rightlegBack.setRotationPoint(-7F, 12F, 4F);
        rightlegBack.setTextureSize(64, 64);
        rightlegBack.mirror = true;
        setRotation(rightlegBack, 0F, 0F, 0F);
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
        leftlegBack.render(f5);
        rightlegBack.render(f5);
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
        this.rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        this.rightlegBack.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        this.leftlegBack.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 2.0F * par2 * 0.5F;
        this.leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
    }
}