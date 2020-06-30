package ru.timeconqueror.timecore.client.render.model;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import ru.timeconqueror.timecore.api.animation.AnimationProvider;

public abstract class TimeEntityRenderer<T extends EntityLiving & AnimationProvider<T>> extends RenderLiving<T> {
	public TimeEntityRenderer(RenderManager rendererManager, TimeEntityModel entityModelIn, float shadowSizeIn) {
		super(rendererManager, entityModelIn, shadowSizeIn);
	}

	@Override
	public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
		entity.getActionManager().getAnimationManager().applyAnimations((TimeEntityModel) getMainModel());
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
}
