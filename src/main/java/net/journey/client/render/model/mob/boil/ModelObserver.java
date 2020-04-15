package net.journey.client.render.model.mob.boil;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelObserver extends ModelBiped {

    ModelRenderer head;
    ModelRenderer head1;
    ModelRenderer head2;
    ModelRenderer arm1;
    ModelRenderer arm2;

    public ModelObserver() {
        textureWidth = 64;
        textureHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-3.5F, -3.5F, -3.5F, 7, 7, 7);
        head.setRotationPoint(0F, 4F, 0F);
        head.setTextureSize(64, 32);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        head1 = new ModelRenderer(this, 0, 0);
        head1.addBox(-4.5F, -2.5F, -3.5F, 7, 7, 7);
        head1.setRotationPoint(1F, -9F, 0F);
        head1.setTextureSize(64, 32);
        head1.mirror = true;
        setRotation(head1, 0F, 0F, 0F);
        head2 = new ModelRenderer(this, 0, 0);
        head2.addBox(3.5F, 3.5F, 3.5F, 7, 7, 7);
        head2.setRotationPoint(-7F, 9F, -7F);
        head2.setTextureSize(64, 32);
        head2.mirror = true;
        setRotation(head2, 0F, 0F, 0F);
        arm1 = new ModelRenderer(this, 14, 14);
        arm1.addBox(-1F, -4.5F, -1F, 2, 5, 2);
        arm1.setRotationPoint(0F, 0F, 0F);
        arm1.setTextureSize(64, 32);
        arm1.mirror = true;
        setRotation(arm1, 0F, 0F, 0F);
        arm2 = new ModelRenderer(this, 14, 14);
        arm2.addBox(-1F, -1F, -1F, 2, 5, 2);
        arm2.setRotationPoint(0F, 8.5F, 0F);
        arm2.setTextureSize(64, 32);
        arm2.mirror = true;
        setRotation(arm2, 0F, 0F, 0F);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        head.render(f5);
        head1.render(f5);
        head2.render(f5);
        arm1.render(f5);
        arm2.render(f5);

    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {

    }
}