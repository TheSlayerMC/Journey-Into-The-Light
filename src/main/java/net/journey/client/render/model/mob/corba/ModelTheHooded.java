package net.journey.client.render.model.mob.corba;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelTheHooded extends ModelBase {


    public ModelRenderer head;
    public ModelRenderer arm1;
    public ModelRenderer arm2;
    public ModelRenderer armconnect;
    public ModelRenderer leg1;
    public ModelRenderer torso;
    public ModelRenderer clothing;
    public ModelRenderer leg2;
    public ModelRenderer hood;


    public ModelTheHooded() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.torso = new ModelRenderer(this, 16, 20);
        this.torso.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.torso.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);
        this.clothing = new ModelRenderer(this, 0, 38);
        this.clothing.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.clothing.addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, 0.5F);
        this.arm2 = new ModelRenderer(this, 44, 0);
        this.arm2.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.arm2.addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotation(arm2, -0.7499679795819634F, 0.0F, 0.0F);
        this.armconnect = new ModelRenderer(this, 40, 16);
        this.armconnect.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.armconnect.addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, 0.0F);
        this.setRotation(armconnect, -0.7499679795819634F, 0.0F, 0.0F);
        this.leg1 = new ModelRenderer(this, 0, 22);
        this.leg1.mirror = true;
        this.leg1.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.arm1 = new ModelRenderer(this, 44, 0);
        this.arm1.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.arm1.addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotation(arm1, -0.7499679795819634F, 0.0F, 0.0F);
        this.hood = new ModelRenderer(this, 32, 40);
        this.hood.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.hood.addBox(-5.0F, -10.0F, -5.0F, 10, 12, 10, 0.0F);
        this.leg2 = new ModelRenderer(this, 0, 22);
        this.leg2.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-4.0F, -8.1F, -4.0F, 8, 8, 8, 0.0F);

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
        this.torso.render(f5);
        this.clothing.render(f5);
        this.arm2.render(f5);
        this.armconnect.render(f5);
        this.leg1.render(f5);
        this.arm1.render(f5);
        this.hood.render(f5);
        this.leg2.render(f5);
        this.head.render(f5);
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.head.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.head.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.hood.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.hood.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
    }
}