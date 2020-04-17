package net.journey.client.render.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSenterianAltar extends ModelBase {
	public ModelRenderer Shape1;
	public ModelRenderer Shape2;
	public ModelRenderer Shape3;
	public ModelRenderer Shape4;
	public ModelRenderer Shape5;
	public ModelRenderer Shape6;
	public ModelRenderer Shape7;
	public ModelRenderer Shape8;
	public ModelRenderer Shape9;
	public ModelRenderer Shape10;
	public ModelRenderer Shape11;
	public ModelRenderer Shape12;
	public ModelRenderer Shape13;
	public ModelRenderer Shape14;

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
	}

	public void render(float f5) {
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
		Shape11.render(f5);
		Shape12.render(f5);
		Shape13.render(f5);
		Shape14.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}