package ru.timeconqueror.timecore.client.render.model;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.util.vector.Vector3f;

import java.util.List;

public class TimeModelRenderer extends ModelRenderer {
	private final ScaleFactor scaleFactor = new ScaleFactor();
	public List<TimeModelBox> cubes;
	public Vector3f startRotationAngles;

	public TimeModelRenderer(TimeModel model, Vector3f rotationAngles, String name, @NotNull List<TimeModelBox> cubes, boolean neverRender) {
		super(model, name);
		startRotationAngles = rotationAngles;
		this.rotateAngleX = rotationAngles.getX();
		this.rotateAngleY = rotationAngles.getY();
		this.rotateAngleZ = rotationAngles.getZ();
		this.showModel = !neverRender;
		this.cubes = cubes;
	}

	@Override
	public void render(float initialScale) {
		if (!this.isHidden) {
			if (this.showModel) {
				if (!this.compiled) {
					this.compileDisplayList(initialScale);
				}

				GlStateManager.pushMatrix();

				Vector3f scale = scaleFactor.getScale();
				GlStateManager.translate(this.offsetX * initialScale * scale.x, this.offsetY * initialScale * scale.y, this.offsetZ * initialScale * scale.z);

				applyRotations(initialScale);
				draw(initialScale);

				GlStateManager.popMatrix();
			}
		}

		resetData();
	}

	@Override
	public void renderWithRotation(float initialScale) {
		if (!this.isHidden) {
			if (this.showModel) {
				if (!this.compiled) {
					this.compileDisplayList(initialScale);
				}

				GlStateManager.pushMatrix();
				applyRotations(initialScale);

				GlStateManager.callList(this.displayList);
				GlStateManager.popMatrix();
			}
		}
	}

	private void draw(float initialScale) {
		Vector3f scale = scaleFactor.getScale();
		GlStateManager.scale(scale.x, scale.y, scale.z);
		GlStateManager.callList(this.displayList);
		if (this.childModels != null) {
			for (ModelRenderer childModel : this.childModels) {
				childModel.render(initialScale);
			}
		}
		GlStateManager.scale(1 / scale.x, 1 / scale.y, 1 / scale.z);
	}

	@Override
	public void postRender(float initialScale) {
		if (!this.isHidden) {
			if (this.showModel) {
				if (!this.compiled) {
					this.compileDisplayList(initialScale);
				}

				applyRotations(initialScale);
			}
		}
	}

	private void applyRotations(float initialScale) {
		if (rotationPointX != 0 || rotationPointY != 0 || rotationPointZ != 0) {
			GlStateManager.translate(this.rotationPointX * initialScale, this.rotationPointY * initialScale, this.rotationPointZ * initialScale);
		}

		if (this.rotateAngleZ != 0.0F) {
			GlStateManager.rotate(this.rotateAngleZ, 0.0F, 0.0F, 1.0F);
		}

		if (this.rotateAngleY != 0.0F) {
			GlStateManager.rotate(this.rotateAngleY, 0.0F, 1.0F, 0.0F);
		}

		if (this.rotateAngleX != 0.0F) {
			GlStateManager.rotate(this.rotateAngleX, 1.0F, 0.0F, 0.0F);
		}
	}

	private void resetData() {
		rotateAngleX = startRotationAngles.getX();
		rotateAngleY = startRotationAngles.getY();
		rotateAngleZ = startRotationAngles.getZ();

		offsetX = 0;
		offsetY = 0;
		offsetZ = 0;

		scaleFactor.reset();
	}

	/**
	 * Compiles a GL display list for this model
	 */
	@Override
	protected void compileDisplayList(float initialScale) {
		this.displayList = GLAllocation.generateDisplayLists(1);
		GlStateManager.glNewList(this.displayList, 4864);
		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();

		for (ModelBox modelBox : this.cubeList) {
			modelBox.render(bufferbuilder, initialScale);
		}

		for (TimeModelBox cube : this.cubes) {
			cube.render(bufferbuilder, initialScale);
		}

		GlStateManager.glEndList();
		this.compiled = true;
	}

	public void setAnimationScaleFactor(float scaleX, float scaleY, float scaleZ) {
		this.scaleFactor.setAnimationScaleFactor(scaleX, scaleY, scaleZ);
	}

	public void setCustomScaleFactor(float scaleX, float scaleY, float scaleZ) {
		this.scaleFactor.setCustomScaleFactor(scaleX, scaleY, scaleZ);
	}

	public Vector3f getScaleFactor() {
		return scaleFactor.getAnimationScaleFactor();
	}
}
