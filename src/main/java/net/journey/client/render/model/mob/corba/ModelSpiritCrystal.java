package net.journey.client.render.model.mob.corba;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelSpiritCrystal extends ModelBase {
	private final ModelRenderer bone;

	public ModelSpiritCrystal() {
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 0, -8.0F, -26.0F, -8.0F, 16, 16, 16, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 56, 56, -10.0F, -24.0F, -10.0F, 8, 8, 8, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 48, -4.0F, -30.0F, -4.0F, 8, 8, 8, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 32, 32, -4.0F, -14.0F, -4.0F, 8, 8, 8, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 48, 0, -10.0F, -16.0F, 2.0F, 8, 8, 8, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 32, 48, 3.0F, -23.0F, 3.0F, 8, 8, 8, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 32, 2.0F, -17.0F, -10.0F, 8, 8, 8, 0.0F, false));
	}

	public void render(float f5) {
		bone.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}