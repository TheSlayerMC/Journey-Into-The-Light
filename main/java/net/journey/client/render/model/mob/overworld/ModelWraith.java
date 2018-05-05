package net.journey.client.render.model.mob.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelWraith extends ModelBase {

	ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer rightarmsleeve;
    ModelRenderer leftarmsleeve;

	public ModelWraith() {
	      textureWidth = 64;
	      textureHeight = 64;
	    
	      head = new ModelRenderer(this, 0, 0);
	      head.addBox(-4F, -8F, -4F, 8, 8, 8);
	      head.setRotationPoint(0F, 0F, 0F);
	      head.setTextureSize(64, 64);
	      head.mirror = true;
	      setRotation(head, 0F, 0F, 0F);
	      body = new ModelRenderer(this, 16, 16);
	      body.addBox(-4F, 0F, -2F, 8, 20, 4);
	      body.setRotationPoint(0F, 0F, 0F);
	      body.setTextureSize(64, 64);
	      body.mirror = true;
	      setRotation(body, 0F, 0F, 0F);
	      rightarm = new ModelRenderer(this, 40, 16);
	      rightarm.addBox(-3F, -2F, -2F, 2, 12, 2);
	      rightarm.setRotationPoint(-3F, 2F, 0F);
	      rightarm.setTextureSize(64, 64);
	      rightarm.mirror = true;
	      setRotation(rightarm, -1.570796F, 0F, 0F);
	      leftarm = new ModelRenderer(this, 40, 16);
	      leftarm.addBox(-1F, -2F, -2F, 2, 12, 2);
	      leftarm.setRotationPoint(5F, 2F, 0F);
	      leftarm.setTextureSize(64, 64);
	      leftarm.mirror = true;
	      setRotation(leftarm, -1.570796F, 0F, 0F);
	      rightleg = new ModelRenderer(this, 0, 16);
	      rightleg.addBox(-1F, 0F, -1F, 2, 12, 2);
	      rightleg.setRotationPoint(-2F, 12F, 0F);
	      rightleg.setTextureSize(64, 64);
	      rightleg.mirror = true;
	      setRotation(rightleg, 0F, 0F, 0F);
	      leftleg = new ModelRenderer(this, 0, 16);
	      leftleg.addBox(-1F, 0F, -1F, 2, 12, 2);
	      leftleg.setRotationPoint(2F, 12F, 0F);
	      leftleg.setTextureSize(64, 64);
	      leftleg.mirror = true;
	      setRotation(leftleg, 0F, 0F, 0F);
	      rightarmsleeve = new ModelRenderer(this, 16, 40);
	      rightarmsleeve.addBox(0F, 0F, 0F, 4, 10, 8);
	      rightarmsleeve.setRotationPoint(-8F, 0F, -6F);
	      rightarmsleeve.setTextureSize(64, 64);
	      rightarmsleeve.mirror = true;
	      setRotation(rightarmsleeve, 0F, 0F, 0F);
	      leftarmsleeve = new ModelRenderer(this, 40, 30);
	      leftarmsleeve.addBox(0F, 0F, 0F, 4, 10, 8);
	      leftarmsleeve.setRotationPoint(4F, 0F, -6F);
	      leftarmsleeve.setTextureSize(64, 64);
	      leftarmsleeve.mirror = true;
	      setRotation(leftarmsleeve, 0F, 0F, 0F);

	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    head.render(f5);
	    body.render(f5);
	    rightarm.render(f5);
	    leftarm.render(f5);
	    rightleg.render(f5);
	    leftleg.render(f5);
	    rightarmsleeve.render(f5);
	    leftarmsleeve.render(f5);

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		this.head.rotateAngleY = f4 / (180F / (float)Math.PI);
		this.head.rotateAngleX = f5 / (180F / (float)Math.PI);
    	this.rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * f1;
    	this.leftleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
	}
}