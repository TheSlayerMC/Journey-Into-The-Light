package net.journey.client.render.model.mob.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFloro extends ModelBase {
	
	ModelRenderer plant1;
    ModelRenderer plant2;
    ModelRenderer pedal1;
    ModelRenderer pedal2;
    ModelRenderer pedal3;
    ModelRenderer pedal4;
    ModelRenderer pedal5;
    ModelRenderer pedal6;
    ModelRenderer pedal7;
    ModelRenderer pedal8;
    ModelRenderer head;
    ModelRenderer stem;
    ModelRenderer foot1;
    ModelRenderer foot2;
    ModelRenderer foot3;
    ModelRenderer foot4;

	public ModelFloro() {
		textureWidth = 64;
	    textureHeight = 64;
	    
	      plant1 = new ModelRenderer(this, 0, 44);
	      plant1.addBox(0F, 0F, 0F, 14, 6, 14);
	      plant1.setRotationPoint(-6.5F, 7F, -6F);
	      plant1.setTextureSize(64, 64);
	      plant1.mirror = true;
	      setRotation(plant1, 0F, 0F, 0F);
	      plant2 = new ModelRenderer(this, 28, 12);
	      plant2.addBox(0F, 0F, 0F, 10, 6, 8);
	      plant2.setRotationPoint(-4.5F, 13F, -3F);
	      plant2.setTextureSize(64, 64);
	      plant2.mirror = true;
	      setRotation(plant2, 0F, 0F, 0F);
	      pedal1 = new ModelRenderer(this, 0, 14);
	      pedal1.addBox(0F, 0F, 0F, 0, 8, 14);
	      pedal1.setRotationPoint(15F, 5F, -6F);
	      pedal1.setTextureSize(64, 64);
	      pedal1.mirror = true;
	      setRotation(pedal1, 0F, 0F, 1.22173F);
	      pedal2 = new ModelRenderer(this, 0, 14);
	      pedal2.addBox(0F, 0F, 0F, 0, 8, 14);
	      pedal2.setRotationPoint(-14F, 5F, -6F);
	      pedal2.setTextureSize(64, 64);
	      pedal2.mirror = true;
	      setRotation(pedal2, 0F, 0F, -1.22173F);
	      pedal3 = new ModelRenderer(this, 41, 46);
	      pedal3.addBox(0F, 0F, 0F, 14, 0, 8);
	      pedal3.setRotationPoint(-6.5F, 5F, -13F);
	      pedal3.setTextureSize(64, 64);
	      pedal3.mirror = true;
	      setRotation(pedal3, -0.3490659F, 0F, 0F);
	      pedal4 = new ModelRenderer(this, -8, 20);
	      pedal4.addBox(0F, 0F, 0F, 14, 0, 8);
	      pedal4.setRotationPoint(-6.5F, 8F, 7F);
	      pedal4.setTextureSize(64, 64);
	      pedal4.mirror = true;
	      setRotation(pedal4, 0.3490659F, 0F, 0F);
	      pedal5 = new ModelRenderer(this, 0, 14);
	      pedal5.addBox(0F, 0F, 0F, 0, 8, 14);
	      pedal5.setRotationPoint(6F, 8F, -15F);
	      pedal5.setTextureSize(64, 64);
	      pedal5.mirror = true;
	      setRotation(pedal5, 0.7504916F, 0F, 1.570796F);
	      pedal6 = new ModelRenderer(this, 0, 14);
	      pedal6.addBox(0F, 0F, 0F, 0, 8, 14);
	      pedal6.setRotationPoint(-15F, 8F, -4F);
	      pedal6.setTextureSize(64, 64);
	      pedal6.mirror = true;
	      setRotation(pedal6, 2.443461F, 0F, 1.570796F);
	      pedal7 = new ModelRenderer(this, 0, 14);
	      pedal7.addBox(0F, 0F, 0F, 0, 8, 14);
	      pedal7.setRotationPoint(15F, 8F, 7F);
	      pedal7.setTextureSize(64, 64);
	      pedal7.mirror = true;
	      setRotation(pedal7, -0.8620274F, 0F, 1.570796F);
	      pedal8 = new ModelRenderer(this, 0, 14);
	      pedal8.addBox(0F, 0F, 0F, 0, 8, 14);
	      pedal8.setRotationPoint(-6F, 8F, 16F);
	      pedal8.setTextureSize(64, 64);
	      pedal8.mirror = true;
	      setRotation(pedal8, -2.443461F, 0F, 1.570796F);
	      head = new ModelRenderer(this, 0, 0);
	      head.addBox(-4F, -8F, -4F, 8, 8, 8);
	      head.setRotationPoint(0.5F, -1F, 1F);
	      head.setTextureSize(64, 64);
	      head.mirror = true;
	      setRotation(head, 0F, 0F, 0F);
	      stem = new ModelRenderer(this, 34, 0);
	      stem.addBox(0F, 0F, 0F, 4, 8, 4);
	      stem.setRotationPoint(-1.5F, -1F, -1F);
	      stem.setTextureSize(64, 64);
	      stem.mirror = true;
	      setRotation(stem, 0F, 0F, 0F);
	      foot1 = new ModelRenderer(this, 28, 26);
	      foot1.addBox(-3F, 0F, -3F, 6, 8, 6);
	      foot1.setRotationPoint(5.5F, 16F, -4F);
	      foot1.setTextureSize(64, 64);
	      foot1.mirror = true;
	      setRotation(foot1, 0F, 0F, 0F);
	      foot2 = new ModelRenderer(this, 28, 26);
	      foot2.addBox(-3F, 0F, -3F, 6, 8, 6);
	      foot2.setRotationPoint(5.5F, 16F, 6F);
	      foot2.setTextureSize(64, 64);
	      foot2.mirror = true;
	      setRotation(foot2, 0F, 0F, 0F);
	      foot3 = new ModelRenderer(this, 28, 26);
	      foot3.addBox(-3F, 0F, -3F, 6, 8, 6);
	      foot3.setRotationPoint(-4.5F, 16F, 6F);
	      foot3.setTextureSize(64, 64);
	      foot3.mirror = true;
	      setRotation(foot3, 0F, 0F, 0F);
	      foot4 = new ModelRenderer(this, 28, 26);
	      foot4.addBox(-3F, 0F, -3F, 6, 8, 6);
	      foot4.setRotationPoint(-4.5F, 16F, -4F);
	      foot4.setTextureSize(64, 64);
	      foot4.mirror = true;
	      setRotation(foot4, 0F, 0F, 0F);

	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		plant1.render(f5);
	    plant2.render(f5);
	    pedal1.render(f5);
	    pedal2.render(f5);
	    pedal3.render(f5);
	    pedal4.render(f5);
	    pedal5.render(f5);
	    pedal6.render(f5);
	    pedal7.render(f5);
	    pedal8.render(f5);
	    head.render(f5);
	    stem.render(f5);
	    foot1.render(f5);
	    foot2.render(f5);
	    foot3.render(f5);
	    foot4.render(f5);

	}

    @Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
    	super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.head.rotateAngleX = f1 / (180F / (float)Math.PI);
    	this.foot1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * f1;
    	this.foot2.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
    	this.foot3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * f1;
    	this.foot4.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
    }

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;

	}
}