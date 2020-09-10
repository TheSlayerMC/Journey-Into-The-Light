package net.journey.client.render.mob.base;

import net.journey.client.render.base.AnimatedMobRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.util.ResourceLocation;
import ru.timeconqueror.timecore.api.animation.AnimationProvider;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;
import ru.timeconqueror.timecore.util.ObjectUtils;

import java.util.function.Function;

public class AnimatedAgeableMobRenderer<T extends EntityAgeable & AnimationProvider<T>> extends AnimatedMobRenderer<T> {
	public AnimatedAgeableMobRenderer(RenderManager rendererManager, ResourceLocation entityModelLocation, ResourceLocation texture) {
		super(rendererManager, entityModelLocation, texture);
	}

	public AnimatedAgeableMobRenderer(RenderManager rendererManager, TimeEntityModel entityModel, ResourceLocation texture) {
		super(rendererManager, entityModel, texture);
	}

	public AnimatedAgeableMobRenderer(RenderManager rendererManager, TimeEntityModel entityModelIn, float shadowSizeIn, ResourceLocation texture) {
		super(rendererManager, entityModelIn, shadowSizeIn, texture);
	}

	public AnimatedAgeableMobRenderer(RenderManager rendererManager, TimeEntityModel entityModelIn, float shadowSizeIn, Function<T, ResourceLocation> entityTextureSupplier) {
		super(rendererManager, entityModelIn, shadowSizeIn, entityTextureSupplier);
	}

	@Override
	public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
		if (entity.isChild()) {
			TimeEntityModel model = getMainModel();

			ObjectUtils.doIfNotNull(model.getBaseModel().getPieces(), renderers -> renderers.forEach(renderer -> renderer.setCustomScaleFactor(0.5F, 0.5F, 0.5F)));
		}

		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
}
