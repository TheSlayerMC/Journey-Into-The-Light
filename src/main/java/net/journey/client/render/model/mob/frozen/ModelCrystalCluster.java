package net.journey.client.render.model.mob.frozen;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelCrystalCluster extends ModelBase {

    ModelRenderer head;
    ModelRenderer head1;
    ModelRenderer head2;

    public ModelCrystalCluster() {
        textureWidth = 64;
        textureHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        head.setRotationPoint(0F, 12F, 0F);
        head.setTextureSize(64, 32);
        setRotation(head, 0F, 0F, 0F);
        head1 = new ModelRenderer(this, 0, 16);
        head1.addBox(2F, 0F, 2F, 4, 4, 4);
        head1.setRotationPoint(5F, 7F, -4F);
        head1.setTextureSize(64, 32);
        setRotation(head, 0F, -0.6108652F, 0F);
        head2 = new ModelRenderer(this, 0, 16);
        head2.addBox(2F, 0F, -2F, 4, 4, 4);
        head2.setRotationPoint(-8F, 1F, 0F);
        head2.setTextureSize(64, 32);
        setRotation(head, 0F, 0.6160289F, 0.4089647F);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.depthMask(true);
        GL11.glEnable(GL11.GL_ALPHA_TEST);

        head.render(f5);
        head1.render(f5);
        head2.render(f5);

        GlStateManager.popMatrix();
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}