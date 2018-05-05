package net.journey.client.render.model.mob.terrania.mob;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelTerraScatterer - Dizzlepop12
 * Created using Tabula 5.1.0
 */
public class ModelTerragrow extends ModelBase {
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer body;
    public ModelRenderer neck;
    public ModelRenderer head;
    public ModelRenderer flower1;
    public ModelRenderer flower2;

    public ModelTerragrow() {
    	this.textureWidth = 64;
        this.textureHeight = 64;
        this.body = new ModelRenderer(this, 0, 40);
        this.body.setRotationPoint(-4.0F, 11.0F, -5.0F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 8, 8, 10, 0.0F);
        this.flower1 = new ModelRenderer(this, 0, 0);
        this.flower1.setRotationPoint(-2.5F, 3.0F, 3.5F);
        this.flower1.addBox(0.0F, 0.0F, 0.0F, 8, 8, 0, 0.0F);
        this.setRotateAngle(flower1, -0.0F, 0.8726646259971648F, 0.0F);
        this.leg2 = new ModelRenderer(this, 0, 26);
        this.leg2.setRotationPoint(-4.0F, 16.0F, -4.0F);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 8, 4, 0.0F);
        this.leg4 = new ModelRenderer(this, 0, 26);
        this.leg4.setRotationPoint(4.0F, 16.0F, 4.0F);
        this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 8, 4, 0.0F);
        this.neck = new ModelRenderer(this, 40, 40);
        this.neck.setRotationPoint(-3.5F, 8.0F, -7.5F);
        this.neck.addBox(0.0F, 0.0F, 0.0F, 7, 6, 4, 0.0F);
        this.flower2 = new ModelRenderer(this, 0, 0);
        this.flower2.setRotationPoint(-2.5F, 3.0F, -2.3F);
        this.flower2.addBox(0.0F, 0.0F, 0.0F, 8, 8, 0, 0.0F);
        this.setRotateAngle(flower2, -0.0F, -0.8744099552491591F, 0.0F);
        this.head = new ModelRenderer(this, 32, 0);
        this.head.setRotationPoint(-0.0F, 12.0F, -7.0F);
        this.head.addBox(-3.0F, -6.0F, -5.0F, 6, 6, 6, 0.0F);
        this.leg3 = new ModelRenderer(this, 0, 26);
        this.leg3.setRotationPoint(-4.0F, 16.0F, 4.0F);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 8, 4, 0.0F);
        this.leg1 = new ModelRenderer(this, 0, 26);
        this.leg1.setRotationPoint(4.0F, 16.0F, -4.0F);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 8, 4, 0.0F);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.head.render(f5);
        this.neck.render(f5);
        this.leg2.render(f5);
        this.leg1.render(f5);
        this.body.render(f5);
        this.leg4.render(f5);
        this.leg3.render(f5);
        this.flower1.render(f5);
        this.flower2.render(f5);
    }
    
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
	}
	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.head.rotateAngleY = f4 / (180F / (float)Math.PI);
		this.head.rotateAngleX = f5 / (180F / (float)Math.PI);
		this.leg1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * f1;
		this.leg2.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * f1;
		this.leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
	}
    
}
