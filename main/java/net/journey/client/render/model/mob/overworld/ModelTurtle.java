package net.journey.client.render.model.mob.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelTurtle extends ModelBase {

	ModelRenderer bod;
	ModelRenderer bod2;
	ModelRenderer head;
	ModelRenderer back1;
	ModelRenderer back2;
	ModelRenderer back3;
	ModelRenderer back4;
	ModelRenderer fll;
	ModelRenderer bll;
	ModelRenderer brl;
	ModelRenderer frl;

	public ModelTurtle() {
		textureWidth = 64;
		textureHeight = 32;

		bod = new ModelRenderer(this, 0, 0);
		bod.addBox(0F, 0F, 0F, 9, 6, 11);
		bod.setRotationPoint(-4F, 15F, -5F);
		bod.setTextureSize(64, 32);
		bod.mirror = true;
		setRotation(bod, 0F, 0F, 0F);
		bod2 = new ModelRenderer(this, 0, 17);
		bod2.addBox(0F, 0F, 0F, 11, 2, 13);
		bod2.setRotationPoint(-5F, 16F, -6F);
		bod2.setTextureSize(64, 32);
		bod2.mirror = true;
		setRotation(bod2, 0F, 0F, 0F);
		head = new ModelRenderer(this, 48, 24);
		head.addBox(-2F, -2F, -4F, 4, 3, 4);
		head.setRotationPoint(0.5F, 19F, -5F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		back1 = new ModelRenderer(this, 51, 0);
		back1.addBox(0F, 0F, 0F, 3, 1, 3);
		back1.setRotationPoint(1F, 14F, 2F);
		back1.setTextureSize(64, 32);
		back1.mirror = true;
		setRotation(back1, 0F, 0F, 0F);
		back2 = new ModelRenderer(this, 51, 0);
		back2.addBox(0F, 0F, 0F, 3, 1, 3);
		back2.setRotationPoint(-3F, 14F, 2F);
		back2.setTextureSize(64, 32);
		back2.mirror = true;
		setRotation(back2, 0F, 0F, 0F);
		back3 = new ModelRenderer(this, 51, 0);
		back3.addBox(0F, 0F, 0F, 3, 1, 3);
		back3.setRotationPoint(1F, 14F, -4F);
		back3.setTextureSize(64, 32);
		back3.mirror = true;
		setRotation(back3, 0F, 0F, 0F);
		back4 = new ModelRenderer(this, 51, 0);
		back4.addBox(0F, 0F, 0F, 3, 1, 3);
		back4.setRotationPoint(-3F, 14F, -4F);
		back4.setTextureSize(64, 32);
		back4.mirror = true;
		setRotation(back4, 0F, 0F, 0F);
		fll = new ModelRenderer(this, 42, 0);
		fll.addBox(0F, 0F, 0F, 2, 4, 2);
		fll.setRotationPoint(2.9F, 20F, -4F);
		fll.setTextureSize(64, 32);
		fll.mirror = true;
		setRotation(fll, 0F, 0F, 0F);
		bll = new ModelRenderer(this, 42, 0);
		bll.addBox(0F, 0F, 0F, 2, 4, 2);
		bll.setRotationPoint(2.9F, 20F, 3F);
		bll.setTextureSize(64, 32);
		bll.mirror = true;
		setRotation(bll, 0F, 0F, 0F);
		brl = new ModelRenderer(this, 42, 0);
		brl.addBox(0F, 0F, 0F, 2, 4, 2);
		brl.setRotationPoint(-3.9F, 20F, 3F);
		brl.setTextureSize(64, 32);
		brl.mirror = true;
		setRotation(brl, 0F, 0F, 0F);
		frl = new ModelRenderer(this, 42, 0);
		frl.addBox(0F, 0F, 0F, 2, 4, 2);
		frl.setRotationPoint(-3.9F, 20F, -4F);
		frl.setTextureSize(64, 32);
		frl.mirror = true;
		setRotation(frl, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bod.render(f5);
		bod2.render(f5);
		head.render(f5);
		back1.render(f5);
		back2.render(f5);
		back3.render(f5);
		back4.render(f5);
		fll.render(f5);
		bll.render(f5);
		brl.render(f5);
		frl.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		this.head.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.head.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.frl.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.fll.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.brl.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.bll.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	}
}