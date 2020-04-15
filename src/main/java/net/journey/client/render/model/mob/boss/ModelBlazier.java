package net.journey.client.render.model.mob.boss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBlazier extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;


	private ModelRenderer[] sticks = new ModelRenderer[8];

	public ModelBlazier() {
		textureWidth = 128;
		textureHeight = 128;
		for(int i = 0; i < this.sticks.length; i++) {
			this.sticks[i] = new ModelRenderer(this, 56, 16);
			this.sticks[i].addBox(0.0F, 0.0F, 0.0F, 4, 12, 4);
		}

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -8F, -4F, 8, 8, 8);
		head.setRotationPoint(1F, -5F, 1F);
		head.setTextureSize(128, 128);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		body = new ModelRenderer(this, 16, 16);
		body.addBox(-4F, 0F, -2F, 10, 12, 10);
		body.setRotationPoint(0F, -5F, -2F);
		body.setTextureSize(128, 128);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		head.render(f5);
		body.render(f5);
		for(int i = 0; i < sticks.length; i++) this.sticks[i].render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f2, float f3, float f4, float f5, float f7, Entity e) {
		float f6 = f3 * (float)Math.PI * -0.1F;
		int i;

		for(i = 0; i < 1; i++) {
			this.sticks[i].rotationPointY = -5.0F + MathHelper.cos((i * 3 + f3) * 0.55F);
			this.sticks[i].rotationPointX = MathHelper.cos(f6) * 15.0F;
			this.sticks[i].rotationPointZ = MathHelper.sin(f6) * 15.0F;
			f6++;
		}

		f6 = ((float)Math.PI / 4F) + f3 * (float)Math.PI * 0.03F;

		for(i = 1; i < 4; i++) {
			this.sticks[i].rotationPointY = 2.0F + MathHelper.cos((i * 2 + f3) * 0.25F);
			this.sticks[i].rotationPointX = MathHelper.cos(f6) * 9.0F;
			this.sticks[i].rotationPointZ = MathHelper.sin(f6) * 9.0F;
			f6++;
		}

		f6 = 0.47123894F + f3 * (float)Math.PI * -0.05F;

		for(i = 4; i < 8; i++) {
			this.sticks[i].rotationPointY = 11.0F + MathHelper.cos((i * 1.5F + f3) * 0.5F);
			this.sticks[i].rotationPointX = MathHelper.cos(f6) * 12.0F;
			this.sticks[i].rotationPointZ = MathHelper.sin(f6) * 12.0F;
			f6++;
		}
	}
}
