package net.journey.client.render.model.mob.euca;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelShimmerer extends ModelBase {

    ModelRenderer body;
    ModelRenderer arm1;
    ModelRenderer arm2;
    ModelRenderer arm3;
    ModelRenderer arm4;
    ModelRenderer arm5;
    ModelRenderer arm6;
    ModelRenderer arm7;
    ModelRenderer arm8;

    public ModelShimmerer() {

        textureWidth = 64;
        textureHeight = 32;

        body = new ModelRenderer(this, 0, 0);
        body.addBox(-3.5F, -3.5F, -3.5F, 7, 7, 7);
        body.setRotationPoint(0F, 4F, 0F);
        body.setTextureSize(64, 32);
        setRotation(body, 0F, 0F, 0F);
        arm1 = new ModelRenderer(this, 0, 14);
        arm1.addBox(-4.5F, -1F, -1F, 5, 2, 2);
        arm1.setRotationPoint(-4F, 4F, 0F);
        arm1.setTextureSize(64, 32);
        setRotation(arm1, 0F, 0F, 0F);
        arm2 = new ModelRenderer(this, 14, 14);
        arm2.addBox(-1F, -4.5F, -1F, 2, 5, 2);
        arm2.setRotationPoint(0F, 0F, 0F);
        arm2.setTextureSize(64, 32);
        setRotation(arm2, 0F, 0F, 0F);
        arm3 = new ModelRenderer(this, 0, 14);
        arm3.addBox(-1F, -1F, -1F, 5, 2, 2);
        arm3.setRotationPoint(4.5F, 4F, 0F);
        arm3.setTextureSize(64, 32);
        setRotation(arm3, 0F, 0F, 0F);
        arm4 = new ModelRenderer(this, 14, 14);
        arm4.addBox(-1F, -1F, -1F, 2, 5, 2);
        arm4.setRotationPoint(0F, 8.5F, 0F);
        arm4.setTextureSize(64, 32);
        setRotation(arm4, 0F, 0F, 0F);
        arm5 = new ModelRenderer(this, 0, 21);
        arm5.addBox(-1F, -1F, -6F, 2, 2, 7);
        arm5.setRotationPoint(-7.5F, 4F, 0F);
        arm5.setTextureSize(64, 32);
        setRotation(arm5, 0F, 0F, 0F);
        arm6 = new ModelRenderer(this, 0, 21);
        arm6.addBox(-1F, -1F, -6F, 2, 2, 7);
        arm6.setRotationPoint(0F, -3.5F, 0F);
        arm6.setTextureSize(64, 32);
        setRotation(arm6, 0F, 0F, 0F);
        arm7 = new ModelRenderer(this, 0, 21);
        arm7.addBox(-1F, -1F, -6F, 2, 2, 7);
        arm7.setRotationPoint(7.5F, 4F, 0F);
        arm7.setTextureSize(64, 32);
        setRotation(arm7, 0F, 0F, 0F);
        arm8 = new ModelRenderer(this, 0, 21);
        arm8.addBox(-1F, -1F, -6F, 2, 2, 7);
        arm8.setRotationPoint(0F, 11.5F, 0F);
        setRotation(arm8, 0F, 0F, 0F);

    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
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
        arm5.render(f5);
        arm6.render(f5);
        arm7.render(f5);
        arm8.render(f5);

    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, Entity par6Entity) {

        super.setRotationAngles(par1, par2, par3, par4, par5, par5, par6Entity);

    }
}