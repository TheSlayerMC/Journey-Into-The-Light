package net.journey.client.render.model.mob.corba;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelLeafBlower extends ModelBase {


	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer stick4;
	private ModelRenderer stick3;
	private ModelRenderer[] sticks = new ModelRenderer[4];
	private ModelRenderer leaves;

	public ModelLeafBlower() {
		textureWidth = 64;
		textureHeight = 64;
		for(int i = 0; i < this.sticks.length; i++) {
			this.sticks[i] = new ModelRenderer(this, 0, 16);
			this.sticks[i].addBox(-2F, 0F, -2F, 2, 13, 2);
		}
		
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -8F, -4F, 8, 8, 8);
		head.setRotationPoint(0F, 0F, -1F);
		head.setTextureSize(64, 64);
		setRotation(head, 0F, 0F, 0F);
		body = new ModelRenderer(this, 16, 16);
		body.addBox(-4F, 0F, -2F, 2, 8, 2);
		body.setRotationPoint(3F, 0F, 0F);
		body.setTextureSize(64, 64);
		setRotation(body, 0F, 0F, 0F);
		stick4 = new ModelRenderer(this, 32, 16);
		stick4.addBox(-3F, -2F, -2F, 2, 24, 2);
		stick4.setRotationPoint(-2F, 2F, 0F);
		stick4.setTextureSize(64, 64);
		setRotation(stick4, 0F, 0F, 0F);
		stick3 = new ModelRenderer(this, 32, 16);
		stick3.addBox(-1F, -2F, -2F, 2, 24, 2);
		stick3.setRotationPoint(4F, 2F, 0F);
		stick3.setTextureSize(64, 64);
		setRotation(stick3, 0F, 0F, 0F);
		leaves = new ModelRenderer(this, 0, 32);
		leaves.addBox(-4F, 0F, -4F, 8, 10, 8);
		leaves.setRotationPoint(0F, 0F, -1F);
		leaves.setTextureSize(64, 64);
		setRotation(leaves, 0F, 0F, 0F);
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
		head.render(f5);
		body.render(f5);
		stick4.render(f5);
		stick3.render(f5);
		leaves.render(f5);
		for(int i = 0; i < sticks.length; i++) this.sticks[i].render(f5);
	}

	@Override
	public void setRotationAngles(float f, float f2, float f3, float f4, float f5, float f7, Entity e) {
		this.stick3.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f2;
		this.stick4.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f2;
		float f6 = f3 * (float)Math.PI * -0.1F;
		int i;

		for(i = 0; i < 1; i++) {
			this.sticks[i].rotationPointY = -5.0F + MathHelper.cos((i * 3 + f3) * 0.25F);
			this.sticks[i].rotationPointX = MathHelper.cos(f6) * 15.0F;
			this.sticks[i].rotationPointZ = MathHelper.sin(f6) * 15.0F;
			f6++;
		}

		f6 = ((float)Math.PI / 4F) + f3 * (float)Math.PI * 0.03F;

		for(i = 1; i < 2; i++) {
			this.sticks[i].rotationPointY = 2.0F + MathHelper.cos((i * 2 + f3) * 0.25F);
			this.sticks[i].rotationPointX = MathHelper.cos(f6) * 9.0F;
			this.sticks[i].rotationPointZ = MathHelper.sin(f6) * 9.0F;
			f6++;
		}

		f6 = 0.47123894F + f3 * (float)Math.PI * -0.05F;

		for(i = 2; i < 4; i++) {
			this.sticks[i].rotationPointY = 11.0F + MathHelper.cos((i * 1.5F + f3) * 0.5F);
			this.sticks[i].rotationPointX = MathHelper.cos(f6) * 12.0F;
			this.sticks[i].rotationPointZ = MathHelper.sin(f6) * 12.0F;
			f6++;
		}
	}
}