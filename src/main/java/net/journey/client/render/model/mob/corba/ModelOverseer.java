package net.journey.client.render.model.mob.corba;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelOverseer extends ModelBase {

    private ModelRenderer Head;

    public ModelOverseer() {
        textureWidth = 64;
        textureHeight = 32;
        Head = new ModelRenderer(this, 0, 0);
        Head.addBox(0F, 0F, 0F, 16, 16, 16);
        Head.setRotationPoint(-8F, -7F, -8F);
        Head.setTextureSize(64, 32);
        Head.mirror = true;
        setRotation(Head, 0F, 0F, 0F);
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
        Head.render(f5);
    }
}