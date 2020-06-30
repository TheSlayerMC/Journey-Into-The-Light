package net.journey.client.render.base;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.api.animation.AnimationProvider;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;
import ru.timeconqueror.timecore.client.render.model.TimeEntityRenderer;
import ru.timeconqueror.timecore.client.render.model.TimeModelLoader;

import java.util.function.Function;

public class AnimatedMobRenderer<T extends EntityLiving & AnimationProvider<T>> extends TimeEntityRenderer<T> {
	private final Function<T, ResourceLocation> entityTextureSupplier;

	public AnimatedMobRenderer(RenderManager rendererManager, ResourceLocation entityModelLocation, ResourceLocation texture) {
		this(rendererManager, TimeModelLoader.loadJsonEntityModel(entityModelLocation), texture);
	}

	public AnimatedMobRenderer(RenderManager rendererManager, TimeEntityModel entityModel, ResourceLocation texture) {
		this(rendererManager, entityModel, 0.5F, texture);
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