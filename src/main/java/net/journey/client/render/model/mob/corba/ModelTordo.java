package net.journey.client.render.model.mob.corba;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelTordo extends ModelBase {


    private ModelRenderer Head;
    private ModelRenderer Body;
    private ModelRenderer RightArm;
    private ModelRenderer LeftArm;
    private ModelRenderer RightLeg;
    private ModelRenderer LeftLeg;


    public ModelTordo() {
        textureWidth = 96;
        textureHeight = 32;
        Head = new ModelRenderer(this, 0, 0);
        Head.addBox(-4F, -8F, -4F, 8, 12, 8);
        Head.setRotationPoint(0F, -8F, -6F);
        Head.setTextureSize(96, 32);
        setRotation(Head, 0F, 0F, 0F);
        Body = new ModelRenderer(this, 32, 16);
        Body.addBox(-4F, 0F, -2F, 8, 12, 4);
        Body.setRotationPoint(0F, -11F, 0F);
        Body.setTextureSize(96, 32);
        setRotation(Body, 0F, 0F, 0F);
        RightArm = new ModelRenderer(this, 56, 0);
        RightArm.addBox(-1F, -2F, -1F, 2, 30, 2);
        RightArm.setRotationPoint(-5F, -9F, -2F);
        RightArm.setTextureSize(96, 32);
        setRotation(RightArm, 0F, 0F, 0F);
        LeftArm = new ModelRenderer(this, 56, 0);
        LeftArm.addBox(-1F, -2F, -1F, 2, 30, 2);
        LeftArm.setRotationPoint(5F, -9.266666F, -2F);
        LeftArm.setTextureSize(96, 32);
        setRotation(LeftArm, 0F, 0F, 0F);
        RightLeg = new ModelRenderer(this, 76, 0);
        RightLeg.addBox(-1F, 0F, -1F, 2, 23, 2);
        RightLeg.setRotationPoint(-2F, 1F, 0F);
        RightLeg.setTextureSize(96, 32);
        setRotation(RightLeg, 0F, 0F, 0F);
        LeftLeg = new ModelRenderer(this, 84, 0);
        LeftLeg.addBox(-1F, 0F, -1F, 2, 23, 2);
        LeftLeg.setRotationPoint(2F, 1F, 0F);
        LeftLeg.setTextureSize(96, 32);
        setRotation(LeftLeg, 0F, 0F, 0F);

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
        Head.render(f5);
        Body.render(f5);
        RightArm.render(f5);
        LeftArm.render(f5);
        RightLeg.render(f5);
        LeftLeg.render(f5);
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.Head.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.Head.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.LeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.RightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        this.LeftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        this.RightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
    }
}