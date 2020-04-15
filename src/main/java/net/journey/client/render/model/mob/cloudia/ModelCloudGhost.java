package net.journey.client.render.model.mob.cloudia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCloudGhost extends ModelBase {


    private ModelRenderer Head;
    private ModelRenderer Body;
    private ModelRenderer RightArm;
    private ModelRenderer LeftArm;
    private ModelRenderer RightLeg;
    private ModelRenderer LeftLeg;


    public ModelCloudGhost() {
        {
            textureWidth = 64;
            textureHeight = 64;

            Body = new ModelRenderer(this, 32, 16);
            Body.addBox(-4F, 0F, -2F, 8, 14, 4);
            Body.setRotationPoint(0F, -14F, 0F);
            Body.setTextureSize(64, 64);
            setRotation(Body, 0.5585054F, 0F, 0F);
            LeftArm = new ModelRenderer(this, 48, 0);
            LeftArm.addBox(-1F, -2F, -1F, 3, 12, 4);
            LeftArm.setRotationPoint(5F, -12F, -1F);
            LeftArm.setTextureSize(64, 64);
            setRotation(LeftArm, -1.396263F, 0F, 0F);
            RightArm = new ModelRenderer(this, 48, 0);
            RightArm.addBox(0F, 0F, 0F, 3, 12, 4);
            RightArm.setRotationPoint(-7F, -14F, 3F);
            RightArm.setTextureSize(64, 64);
            setRotation(RightArm, -1.396263F, 0F, 0F);
        }

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
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        super.render(entity, f, f1, f2, f3, f4, f5);
        Body.render(f5);
        LeftArm.render(f5);
        RightArm.render(f5);

    }
}