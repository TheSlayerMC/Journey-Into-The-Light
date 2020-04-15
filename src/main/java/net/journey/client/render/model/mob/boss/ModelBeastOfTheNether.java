package net.journey.client.render.model.mob.boss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBeastOfTheNether extends ModelBase {

    public ModelRenderer body1;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg1a;
    public ModelRenderer leg2_1;
    public ModelRenderer head;
    public ModelRenderer horn1;
    public ModelRenderer horn2;
    public ModelRenderer body2;
    //public ModelRenderer snout;

    public ModelBeastOfTheNether() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 5.0F, -8.0F);
        this.head.addBox(-4.0F, 0.0F, -4.2F, 8, 8, 6, 0.0F);
        this.body1 = new ModelRenderer(this, 18, 16);
        this.body1.setRotationPoint(0.0F, 15.1F, -1.0F);
        this.body1.addBox(-6.0F, -10.0F, -7.0F, 12, 12, 10, 0.0F);
        this.setRotation(body1, -0.136659280431156F, 0.0F, 0.0F);
        /** this.snout = new ModelRenderer(this, 5, 9);
         this.snout.setRotationPoint(0.0F, 5.0F, -8.0F);
         this.snout.addBox(-3.0F, 5.0F, -5.0F, 6, 3, 2, 0.0F); */
        this.leg2 = new ModelRenderer(this, 45, 45);
        this.leg2.setRotationPoint(4.6F, 12.0F, -5.2F);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.horn2 = new ModelRenderer(this, 40, 0);
        this.horn2.setRotationPoint(0.0F, 5.0F, -8.0F);
        this.horn2.addBox(3.0F, -4.0F, -4.0F, 2, 6, 2, 0.0F);
        this.setRotation(horn2, 0.41887902047863906F, 0.0F, 0.0F);
        this.body2 = new ModelRenderer(this, 0, 40);
        this.body2.setRotationPoint(-5.0F, 6.5F, 0.0F);
        this.body2.addBox(0.0F, 0.0F, 0.0F, 10, 10, 11, 0.0F);
        this.setRotation(body2, -0.12409290981679683F, 0.0F, 0.0F);
        this.leg1 = new ModelRenderer(this, 0, 16);
        this.leg1.setRotationPoint(4.0F, 12.0F, 7.0F);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.leg1a = new ModelRenderer(this, 0, 16);
        this.leg1a.setRotationPoint(-4.0F, 12.0F, 7.0F);
        this.leg1a.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.leg2_1 = new ModelRenderer(this, 45, 45);
        this.leg2_1.setRotationPoint(-4.6F, 12.0F, -5.2F);
        this.leg2_1.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.horn1 = new ModelRenderer(this, 40, 0);
        this.horn1.setRotationPoint(0.0F, 5.0F, -8.0F);
        this.horn1.addBox(-5.0F, -4.0F, -4.0F, 2, 6, 2, 0.0F);
        this.setRotation(horn1, 0.41887902047863906F, 0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
        this.body1.render(f5);
        //this.snout.render(f5);
        this.leg2.render(f5);
        this.horn2.render(f5);
        this.body2.render(f5);
        this.leg1.render(f5);
        this.leg1a.render(f5);
        this.leg2_1.render(f5);
        this.horn1.render(f5);
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
        //this.snout.rotateAngleY = par4 / (180F / (float)Math.PI);
        //this.snout.rotateAngleX = par5 / (180F / (float)Math.PI);
        this.horn1.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.horn1.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.horn2.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.horn2.rotateAngleX = par5 / (180F / (float) Math.PI);

        this.leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 2.0F * par2 * 0.5F;
        this.leg1a.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.leg2_1.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
    }
}