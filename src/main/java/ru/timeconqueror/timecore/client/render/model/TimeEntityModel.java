package ru.timeconqueror.timecore.client.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;

public class TimeEntityModel extends ModelBase {
	private final TimeModel model;
	private float scale;

	public TimeEntityModel(TimeModel model) {
		this.model = model;
		boxList.addAll(model.boxList);
		model.boxList.clear();
	}

	/**
	 * Sets custom scale for the model.
	 * <p>
	 * Should only be called once and before first render frame,
	 * otherwise you'll see unexpected render behaviour.
	 */
	public TimeEntityModel setScaleMultiplier(float scaleMultiplier) {
		model.setScaleMultiplier(scaleMultiplier);
		scale = scaleMultiplier;
		return this;
	}

	public float getScaleMultiplier() {
		return scale;
	}

	/**
	 * Renders model with provided scale.
	 *
	 * @param initialScale controls initial scale settings of the model.
	 *                     Once you provided some number as initial scale,
	 *                     you should always provide this particular number,
	 *                     otherwise you'll see unexpected render behaviour.
	 */
	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float initialScale) {
		GlStateManager.translate(0, 1.501F, 0);//Mojang, WHY???
		if (entityIn instanceof EntityAgeable) {
			if (((EntityAgeable) entityIn).isChild()) {
				model.setScaleMultiplier(this.getScaleMultiplier() / 2);
			}
		}
		model.render(initialScale);
		GlStateManager.translate(0, -1.501F, 0);
	}

	public TimeModel getBaseModel() {
		return model;
	}
}