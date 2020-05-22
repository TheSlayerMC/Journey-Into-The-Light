package net.journey.client.render.base;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.api.client.render.TimeEntityModel;
import ru.timeconqueror.timecore.api.client.render.TimeEntityRenderer;
import ru.timeconqueror.timecore.api.client.render.animation.IAnimationProvider;

import java.util.function.Function;

public class AnimatedMobRenderer<T extends EntityLiving & IAnimationProvider> extends TimeEntityRenderer<T> {
	private Function<T, ResourceLocation> entityTextureSupplier;

	public AnimatedMobRenderer(RenderManager rendererManager, TimeEntityModel entityModelIn, ResourceLocation texture) {
		this(rendererManager, entityModelIn, 0.5F, texture);
	}

	public AnimatedMobRenderer(RenderManager rendererManager, TimeEntityModel entityModelIn, float shadowSizeIn, ResourceLocation texture) {
		this(rendererManager, entityModelIn, shadowSizeIn, (e) -> texture);
	}

	public AnimatedMobRenderer(RenderManager rendererManager, TimeEntityModel entityModelIn, float shadowSizeIn, Function<T, ResourceLocation> entityTextureSupplier) {
		super(rendererManager, entityModelIn, shadowSizeIn);
		this.entityTextureSupplier = entityTextureSupplier;
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(@NotNull T entity) {
		return entityTextureSupplier.apply(entity);
	}
}
