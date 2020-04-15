package net.journey.client.render.model.mob.nether;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelHellCow - Dizzlepop12 and Mojang
 * Created using Tabula 5.1.0
 */
public class ModelHellCow extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer udder;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer head;
    public ModelRenderer horn2_a;
    public ModelRenderer horn1_b;
    public ModelRenderer horn1_a;
    public ModelRenderer horn3_a;
    public ModelRenderer horn3_b;
    public ModelRenderer horn2_b;

    public ModelHellCow() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.horn1_a = new ModelRenderer(this, 22, 50);
        this.horn1_a.setRotationPoint(0.0F, 4.0F, -8.0F);
        this.horn1_a.addBox(-6.0F, -5.0F, -4.0F, 2, 5, 2, 0.0F);
        this.body = new ModelRenderer(this, 18, 4);
        this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
        this.body.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 10, 0.0F);
        this.setRotateAngle(body, 1.5707963267948966F, 0.0F, 0.0F);
        this.leg4 = new ModelRenderer(this, 0, 16);
        this.leg4.setRotationPoint(-4.0F, 12.0F, -6.0F);
        this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.leg3 = new ModelRenderer(this, 0, 16);
        this.leg3.setRotationPoint(-4.0F, 12.0F, 7.0F);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.leg2 = new ModelRenderer(this, 0, 16);
        this.leg2.setRotationPoint(4.0F, 12.0F, -6.0F);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.horn2_b = new ModelRenderer(this, 22, 40);
        this.horn2_b.setRotationPoint(0.0F, 4.0F, -8.2F);
        this.horn2_b.addBox(4.0F, -8.7F, -5.2F, 2, 5, 2, 0.0F);
        this.setRotateAngle(horn2_b, -0.3490658503988659F, 0.0F, 0.0F);
        this.leg1 = new ModelRenderer(this, 0, 16);
        this.leg1.setRotationPoint(4.0F, 12.0F, 7.0F);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 4.0F, -8.0F);
        this.head.addBox(-4.0F, -4.0F, -6.0F, 8, 8, 6, 0.0F);
        this.horn2_a = new ModelRenderer(this, 22, 40);
        this.horn2_a.setRotationPoint(0.0F, 4.0F, -8.2F);
        this.horn2_a.addBox(-6.0F, -8.7F, -5.4F, 2, 5, 2, 0.0F);
        this.setRotateAngle(horn2_a, -0.3490658503988659F, 0.0F, 0.0F);
        this.horn3_b = new ModelRenderer(this, 40, 40);
        this.horn3_b.setRotationPoint(0.0F, 4.0F, -8.0F);
        this.horn3_b.addBox(4.5F, -10.1F, -7.8F, 1, 5, 1, 0.0F);
        this.setRotateAngle(horn3_b, -0.7330382858376184F, 0.0F, 0.0F);
        this.udder = new ModelRenderer(this, 52, 0);
        this.udder.setRotationPoint(0.0F, 5.0F, 2.0F);
        this.udder.addBox(-2.0F, 2.0F, -8.0F, 4, 6, 1, 0.0F);
        this.setRotateAngle(udder, 1.5707963267948966F, 0.0F, 0.0F);
        this.horn1_b = new ModelRenderer(this, 22, 50);
        this.horn1_b.setRotationPoint(0.0F, 4.0F, -8.0F);
        this.horn1_b.addBox(4.0F, -5.0F, -4.0F, 2, 5, 2, 0.0F);
        this.horn3_a = new ModelRenderer(this, 40, 40);
        this.horn3_a.setRotationPoint(0.0F, 4.0F, -8.0F);
        this.horn3_a.addBox(-5.6F, -10.1F, -7.8F, 1, 5, 1, 0.0F);
        this.setRotateAngle(horn3_a, -0.7330382858376184F, 0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.horn1_a.render(f5);
        this.body.render(f5);
        this.leg4.render(f5);
        this.leg3.render(f5);
        this.leg2.render(f5);
        this.horn2_b.render(f5);
        this.leg1.render(f5);
        this.head.render(f5);
        this.horn2_a.render(f5);
        this.horn3_b.render(f5);
        this.udder.render(f5);
        this.horn1_b.render(f5);
        this.horn3_a.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;

    }
    
    @Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
    	super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		//this.head.rotateAngleY = f3 / 57.29578f;
		//this.head.rotateAngleX= f4 / 57.29578f;
		//this.horn1_a.rotateAngleY = f3 / 57.29578f;
		//this.horn1_a.rotateAngleX= f4 / 57.29578f;
    	//this.horn2_a.rotateAngleY = f3 / 57.29578f;
    	//this.horn2_a.rotateAngleX= f4 / 57.29578f;
    	//this.horn3_a.rotateAngleY = f3 / 57.29578f;
    	//this.horn3_a.rotateAngleX= f4 / 57.29578f;
    	//this.horn1_b.rotateAngleY = f3 / 57.29578f;
		//this.horn1_b.rotateAngleX= f4 / 57.29578f;
    	//this.horn2_b.rotateAngleY = f3 / 57.29578f;
    	//this.horn2_b.rotateAngleX= f4 / 57.29578f;
    	//this.horn3_b.rotateAngleY = f3 / 57.29578f;
		//this.horn3_b.rotateAngleX= f4 / 57.29578f;
    	this.leg1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * f1;
    	this.leg3.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
    	this.leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * f1;
    	this.leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
    }
}
