package net.journey.client.render.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelSenterianAltar extends ModelBase {

	private ModelRenderer Shape1;
	private ModelRenderer Shape2;
	private ModelRenderer Shape3;
	private ModelRenderer Shape4;
	private ModelRenderer Shape5;
	private ModelRenderer Shape6;
	private ModelRenderer Shape7;
	private ModelRenderer Shape8;
	private ModelRenderer Shape9;
	private ModelRenderer Shape10;
	private ModelRenderer Shape11;
	private ModelRenderer Shape12;
	private ModelRenderer Shape13;
	private ModelRenderer Shape14;
	private ModelRenderer orb;

	public ModelSenterianAltar() {
		textureWidth = 64;
		textureHeight = 64;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 16, 2, 16);
		Shape1.setRotationPoint(-8F, 22F, -8F);
		Shape1.setTextureSize(64, 64);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 30);
		Shape2.addBox(0F, 0F, 0F, 2, 14, 2);
		Shape2.setRotationPoint(-8F, 8F, 6F);
		Shape2.setTextureSize(64, 64);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 30);
		Shape3.addBox(0F, 0F, 0F, 2, 14, 2);
		Shape3.setRotationPoint(6F, 8F, 6F);
		Shape3.setTextureSize(64, 64);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 0, 30);
		Shape4.addBox(0F, 0F, 0F, 2, 14, 2);
		Shape4.setRotationPoint(-8F, 8F, -8F);
		Shape4.setTextureSize(64, 64);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 0, 30);
		Shape5.addBox(0F, 0F, 0F, 2, 14, 2);
		Shape5.setRotationPoint(6F, 8F, -8F);
		Shape5.setTextureSize(64, 64);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 16, 29);
		Shape6.addBox(0F, 0F, 0F, 6, 7, 6);
		Shape6.setRotationPoint(-3F, 15F, -3F);
		Shape6.setTextureSize(64, 64);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 16, 20);
		Shape7.addBox(0F, 0F, 0F, 6, 1, 1);
		Shape7.setRotationPoint(-3F, 14F, -3F);
		Shape7.setTextureSize(64, 64);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 16, 20);
		Shape8.addBox(0F, 0F, 0F, 6, 1, 1);
		Shape8.setRotationPoint(-3F, 14F, 2F);
		Shape8.setTextureSize(64, 64);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		Shape9 = new ModelRenderer(this, 16, 23);
		Shape9.addBox(0F, 0F, 0F, 1, 1, 4);
		Shape9.setRotationPoint(2F, 14F, -2F);
		Shape9.setTextureSize(64, 64);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);
		Shape10 = new ModelRenderer(this, 16, 23);
		Shape10.addBox(0F, 0F, 0F, 1, 1, 4);
		Shape10.setRotationPoint(-3F, 14F, -2F);
		Shape10.setTextureSize(64, 64);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0F, 0F);
		Shape11 = new ModelRenderer(this, 32, 48);
		Shape11.addBox(0F, 0F, 0F, 3, 3, 3);
		Shape11.setRotationPoint(-8.1F, 7.9F, 5.1F);
		Shape11.setTextureSize(64, 64);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0F, 0F);
		Shape12 = new ModelRenderer(this, 32, 48);
		Shape12.addBox(0F, 0F, 0F, 3, 3, 3);
		Shape12.setRotationPoint(5.1F, 7.9F, 5.1F);
		Shape12.setTextureSize(64, 64);
		Shape12.mirror = true;
		setRotation(Shape12, 0F, 0F, 0F);
		Shape13 = new ModelRenderer(this, 32, 48);
		Shape13.addBox(0F, 0F, 0F, 3, 3, 3);
		Shape13.setRotationPoint(5.1F, 7.9F, -8.1F);
		Shape13.setTextureSize(64, 64);
		Shape13.mirror = true;
		setRotation(Shape13, 0F, 0F, 0F);
		Shape14 = new ModelRenderer(this, 32, 48);
		Shape14.addBox(0F, 0F, 0F, 3, 3, 3);
		Shape14.setRotationPoint(-8.1F, 7.9F, -8.1F);
		Shape14.setTextureSize(64, 64);
		Shape14.mirror = true;
		setRotation(Shape14, 0F, 0F, 0F);
		orb = new ModelRenderer(this, 0, 0);
		orb.addBox(0F, 0F, 0F, 4, 2, 4);
		orb.setRotationPoint(-2F, 12.5F, -2F);
		orb.setTextureSize(64, 64);
		orb.mirror = true;
		setRotation(orb, 0F, 0F, 0F);
	}

	public void render(float f5, boolean orbIn) {
        float timeD = (float) (360.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) * 8;

		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
		Shape9.render(f5);
		Shape10.render(f5);
		GlStateManager.pushMatrix();
		if(orbIn)
			GlStateManager.rotate(timeD, 0.0F, 1.0F, 0.0F);
		Shape11.render(f5);
		Shape12.render(f5);
		Shape13.render(f5);
		Shape14.render(f5);
		GlStateManager.popMatrix();
		if(orbIn) orb.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}