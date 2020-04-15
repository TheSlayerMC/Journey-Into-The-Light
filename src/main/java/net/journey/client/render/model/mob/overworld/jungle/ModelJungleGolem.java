package net.journey.client.render.model.mob.overworld.jungle;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * woodGolem - Dizzlepop12
 * Created using Tabula 5.1.0
 */
public class ModelJungleGolem extends ModelBase {
    public ModelRenderer foot1;
    public ModelRenderer foot2;
    public ModelRenderer body1;
    public ModelRenderer body2;
    public ModelRenderer body3;
    public ModelRenderer bodyMain;
    public ModelRenderer arm1;
    public ModelRenderer fist1;
    public ModelRenderer arm2;
    public ModelRenderer fist2;
    public ModelRenderer face1;
    public ModelRenderer head;
    public ModelRenderer face2;
    public ModelRenderer face3;
    public ModelRenderer face4;
    public ModelRenderer face5;
    public ModelRenderer face6;
    public ModelRenderer face7;
    public ModelRenderer face8;
    public ModelRenderer face9;
    public ModelRenderer face10;
    public ModelRenderer face11;

    public ModelJungleGolem() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        
        this.body1 = new ModelRenderer(this, 0, 50);
        this.body1.setRotationPoint(-8.0F, 9.0F, -3.0F);
        this.body1.addBox(0.0F, 0.0F, 0.0F, 16, 3, 5, 0.0F);
        
        this.face1 = new ModelRenderer(this, 0, 0);
        this.face1.setRotationPoint(-0.0F, -8.0F, 0.0F);
        this.face1.addBox(-5.0F, -8.0F, -7.0F, 10, 1, 1, 0.0F);
        this.body2 = new ModelRenderer(this, 0, 50);
        this.body2.setRotationPoint(-7.0F, 5.0F, -2.3F);
        this.body2.addBox(0.0F, 0.0F, 0.0F, 14, 5, 3, 0.0F);
        this.body3 = new ModelRenderer(this, 0, 50);
        this.body3.setRotationPoint(-4.0F, -3.0F, -2.0F);
        this.body3.addBox(0.0F, 0.0F, 0.0F, 8, 8, 2, 0.0F);
        this.foot2 = new ModelRenderer(this, 16, 24);
        this.foot2.setRotationPoint(5.0F, 12.0F, -0.0F);
        this.foot2.addBox(-4.0F, 0.0F, -4.0F, 8, 12, 7, 0.0F);
        this.head = new ModelRenderer(this, 20, 0);
        this.head.setRotationPoint(-0.0F, -8.0F, 0.0F);
        this.head.addBox(-5.0F, -8.0F, -6.0F, 10, 10, 6, 0.0F);
        this.arm2 = new ModelRenderer(this, 48, 20);
        this.arm2.setRotationPoint(12.0F, -10.0F, 1.0F);
        this.arm2.addBox(-2.0F, 0.0F, -2.0F, 4, 26, 4, 0.0F);
        this.face8 = new ModelRenderer(this, 0, 0);
        this.face8.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.face8.addBox(1.0F, -1.0F, -7.0F, 1, 1, 1, 0.0F);
        this.foot1 = new ModelRenderer(this, 16, 24);
        this.foot1.setRotationPoint(-5.0F, 12.0F, -0.0F);
        this.foot1.addBox(-4.0F, 0.0F, -4.0F, 8, 12, 7, 0.0F);
        this.face5 = new ModelRenderer(this, 0, 0);
        this.face5.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.face5.addBox(3.0F, -5.0F, -7.0F, 1, 1, 1, 0.0F);
        this.face7 = new ModelRenderer(this, 0, 0);
        this.face7.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.face7.addBox(-4.0F, -2.0F, -7.0F, 2, 1, 1, 0.0F);
        this.fist1 = new ModelRenderer(this, 8, 48);
        this.fist1.setRotationPoint(-12.0F, -10.0F, 1.0F);
        this.fist1.addBox(-3.0F, 26.0F, -3.0F, 6, 8, 6, 0.0F);
        this.arm1 = new ModelRenderer(this, 48, 20);
        this.arm1.setRotationPoint(-12.0F, -10.0F, 1.0F);
        this.arm1.addBox(-2.0F, 0.0F, -2.0F, 4, 26, 4, 0.0F);
        this.face11 = new ModelRenderer(this, 0, 0);
        this.face11.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.face11.addBox(-4.0F, -7.0F, -7.0F, 1, 1, 1, 0.0F);
        this.bodyMain = new ModelRenderer(this, 0, 44);
        this.bodyMain.setRotationPoint(-10.0F, -11.6F, -3.5F);
        this.bodyMain.addBox(0.0F, 0.0F, 0.0F, 20, 12, 6, 0.0F);
        this.setRotateAngle(bodyMain, 0.06283185307179587F, 0.0F, 0.0F);
        this.face4 = new ModelRenderer(this, 52, 34);
        this.face4.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.face4.addBox(-5.0F, -7.0F, -7.0F, 1, 7, 1, 0.0F);
        this.face6 = new ModelRenderer(this, 0, 0);
        this.face6.setRotationPoint(-0.0F, -8.0F, 0.0F);
        this.face6.addBox(-4.0F, -4.0F, -7.0F, 8, 2, 1, 0.0F);
        this.face2 = new ModelRenderer(this, 0, 0);
        this.face2.setRotationPoint(-0.0F, -8.0F, 0.0F);
        this.face2.addBox(-5.0F, -0.0F, -7.0F, 10, 2, 1, 0.0F);
        this.face10 = new ModelRenderer(this, 0, 0);
        this.face10.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.face10.addBox(-1.0F, -7.0F, -7.0F, 2, 3, 1, 0.0F);
        this.fist2 = new ModelRenderer(this, 8, 48);
        this.fist2.setRotationPoint(12.0F, -10.0F, 1.0F);
        this.fist2.addBox(-3.0F, 26.0F, -3.0F, 6, 8, 6, 0.0F);
        this.face9 = new ModelRenderer(this, 0, 0);
        this.face9.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.face9.addBox(3.0F, -1.0F, -7.0F, 1, 1, 1, 0.0F);
        this.face3 = new ModelRenderer(this, 0, 0);
        this.face3.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.face3.addBox(4.0F, -7.0F, -7.0F, 1, 7, 1, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.body1.render(f5);
        this.face1.render(f5);
        this.body2.render(f5);
        this.body3.render(f5);
        this.foot2.render(f5);
        this.head.render(f5);
        this.arm2.render(f5);
        this.face8.render(f5);
        this.foot1.render(f5);
        this.face5.render(f5);
        this.face7.render(f5);
        this.fist1.render(f5);
        this.arm1.render(f5);
        this.face11.render(f5);
        this.bodyMain.render(f5);
        this.face4.render(f5);
        this.face6.render(f5);
        this.face2.render(f5);
        this.face10.render(f5);
        this.fist2.render(f5);
        this.face9.render(f5);
        this.face3.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
	@Override
	public void setRotationAngles(float f, float f2, float f3, float f4, float f5, float f7, Entity e) {
    	super.setRotationAngles(f, f2, f3, f4, f5, f7, e);
		this.head.rotateAngleY = f4 / (180F / (float)Math.PI);
		this.head.rotateAngleX = f5 / (180F / (float)Math.PI);
		this.face1.rotateAngleY = f4 / (180F / (float)Math.PI);
		this.face1.rotateAngleX = f5 / (180F / (float)Math.PI);
		this.face2.rotateAngleY = f4 / (180F / (float)Math.PI);
		this.face2.rotateAngleX = f5 / (180F / (float)Math.PI);
		this.face3.rotateAngleY = f4 / (180F / (float)Math.PI);
		this.face3.rotateAngleX = f5 / (180F / (float)Math.PI);
		this.face4.rotateAngleY = f4 / (180F / (float)Math.PI);
		this.face4.rotateAngleX = f5 / (180F / (float)Math.PI);
		this.face5.rotateAngleY = f4 / (180F / (float)Math.PI);
		this.face5.rotateAngleX = f5 / (180F / (float)Math.PI);
		this.face6.rotateAngleY = f4 / (180F / (float)Math.PI);
		this.face6.rotateAngleX = f5 / (180F / (float)Math.PI);
		this.face7.rotateAngleY = f4 / (180F / (float)Math.PI);
		this.face7.rotateAngleX = f5 / (180F / (float)Math.PI);
		this.face8.rotateAngleY = f4 / (180F / (float)Math.PI);
		this.face8.rotateAngleX = f5 / (180F / (float)Math.PI);
		this.face9.rotateAngleY = f4 / (180F / (float)Math.PI);
		this.face9.rotateAngleX = f5 / (180F / (float)Math.PI);
		this.face10.rotateAngleY = f4 / (180F / (float)Math.PI);
		this.face10.rotateAngleX = f5 / (180F / (float)Math.PI);
		this.face11.rotateAngleY = f4 / (180F / (float)Math.PI);
		this.face11.rotateAngleX = f5 / (180F / (float)Math.PI);
    	this.arm2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * f2;
    	this.arm1.rotateAngleX = MathHelper.cos(f * 0.6662F) * f2;
    	this.foot2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * f2;
    	this.foot1.rotateAngleX = MathHelper.cos(f * 0.6662F) * f2;
    	this.fist2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * f2;
    	this.fist1.rotateAngleX = MathHelper.cos(f * 0.6662F) * f2;
    }
}
