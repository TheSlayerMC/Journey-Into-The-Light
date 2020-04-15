package net.journey.client.render.model.mob.euca;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDynaster extends ModelBase {

    private ModelRenderer Head;
    private ModelRenderer head;
    private ModelRenderer hover1;
    private ModelRenderer hover2;
    private ModelRenderer hover3;
    private ModelRenderer hover4;


    public ModelDynaster() {
        textureWidth = 64;
        textureHeight = 64;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        head.setRotationPoint(0F, 16F, 0F);
        head.setTextureSize(64, 64);
        setRotation(head, 0F, 0F, 0F);
        hover1 = new ModelRenderer(this, 0, 16);
        hover1.addBox(-7F, 0F, -1F, 8, 6, 8);
        hover1.setRotationPoint(-3F, 16F, 3F);
        hover1.setTextureSize(64, 64);
        setRotation(hover1, 0.3490659F, 0F, 0F);
        hover2 = new ModelRenderer(this, 0, 16);
        hover2.addBox(-1F, 0F, -1F, 8, 6, 8);
        hover2.setRotationPoint(3F, 16F, 3F);
        hover2.setTextureSize(64, 64);
        setRotation(hover2, 0.3490659F, 0F, 0F);
        hover3 = new ModelRenderer(this, 0, 16);
        hover3.addBox(0F, 0F, -7F, 8, 6, 8);
        hover3.setRotationPoint(2F, 16F, -3F);
        hover3.setTextureSize(64, 64);
        setRotation(hover3, 0.3490659F, 0F, 0F);
        hover4 = new ModelRenderer(this, 0, 16);
        hover4.addBox(-7F, 0F, -7F, 8, 6, 8);
        hover4.setRotationPoint(-3F, 16F, -3F);
        hover4.setTextureSize(64, 64);
        setRotation(hover4, 0.3490659F, 0F, 0F);

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
        head.render(f5);
        hover1.render(f5);
        hover2.render(f5);
        hover3.render(f5);
        hover4.render(f5);
    }
}