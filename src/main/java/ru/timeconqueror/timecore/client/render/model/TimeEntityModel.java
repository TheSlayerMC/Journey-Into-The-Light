package ru.timeconqueror.timecore.client.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class TimeEntityModel extends ModelBase {
	private final TimeModel model;

	public TimeEntityModel(TimeModel model) {
		this.model = model;
		boxList.addAll(model.boxList);
		model.boxList.clear();
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		GlStateManager.translate(0, 1.501F, 0);//Mojang, WHY???
		model.render(scale);
		GlStateManager.translate(0, -1.501F, 0);
	}

	public TimeModel getBaseModel() {
		return model;
	}
}