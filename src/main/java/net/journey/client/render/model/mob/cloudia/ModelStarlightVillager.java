package net.journey.client.render.model.mob.cloudia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelStarlightVillager extends ModelBase {
    ModelRenderer head;
    ModelRenderer leftarm;
    ModelRenderer rightarm;
    ModelRenderer leftfoot;
    ModelRenderer rightfoot;

    public ModelStarlightVillager() {
        textureWidth = 128;
        textureHeight = 128;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(0F, 0F, 0F, 16, 16, 16);
        head.setRotationPoint(-8F, -13F, -8F);
        head.setTextureSize(128, 128);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        leftarm = new ModelRenderer(this, 32, 32);
        leftarm.addBox(0F, 0F, -4F, 8, 24, 8);
        leftarm.setRotationPoint(8F, -10F, 0F);
        leftarm.setTextureSize(128, 128);
        leftarm.mirror = true;
        setRotation(leftarm, 0F, 0F, 0F);
        rightarm = new ModelRenderer(this, 64, 32);
        rightarm.addBox(-8F, 0F, -4F, 8, 24, 8);
        rightarm.setRotationPoint(-8F, -10F, 0F);
        rightarm.setTextureSize(128, 128);
        rightarm.mirror = true;
        setRotation(rightarm, 0F, 0F, 0F);
        leftfoot = new ModelRenderer(this, 0, 84);
        leftfoot.addBox(-1F, 24F, -5F, 10, 10, 10);
        leftfoot.setRotationPoint(8F, -10F, 0F);
        leftfoot.setTextureSize(128, 128);
        leftfoot.mirror = true;
        setRotation(leftfoot, 0F, 0F, 0F);
        rightfoot = new ModelRenderer(this, 0, 64);
        rightfoot.addBox(-9F, 24F, -5F, 10, 10, 10);
        rightfoot.setRotationPoint(-8F, -10F, 0F);
        rightfoot.setTextureSize(128, 128);
        rightfoot.mirror = true;
        setRotation(rightfoot, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        head.render(f5);
        leftarm.render(f5);
        rightarm.render(f5);
        leftfoot.render(f5);
        rightfoot.render(f5);

    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.leftarm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.rightarm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
        this.leftfoot.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.rightfoot.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
    }

}