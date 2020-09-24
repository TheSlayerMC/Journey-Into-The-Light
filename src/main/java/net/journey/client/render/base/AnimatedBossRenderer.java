package net.journey.client.render.base;

import net.journey.util.EnumHexColor;
import net.journey.util.JourneyBossStatus;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.slayer.api.entity.EntityEssenceBoss;
import ru.timeconqueror.timecore.api.animation.AnimationProvider;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;

import java.util.function.Function;

public class AnimatedBossRenderer<T extends EntityEssenceBoss & AnimationProvider<T>> extends AnimatedMobRenderer<T> {

	private String bar;
	public EnumHexColor stringHexColor;
	public EnumHexColor stringOutlineColor;


	public AnimatedBossRenderer(RenderManager rendererManager, ResourceLocation entityModelLocation, ResourceLocation texture) {
		super(rendererManager, entityModelLocation, texture);
	}

	public AnimatedBossRenderer(RenderManager rendererManager, TimeEntityModel entityModel, ResourceLocation texture) {
		super(rendererManager, entityModel, texture);
	}

	public AnimatedBossRenderer(RenderManager rendererManager, TimeEntityModel entityModelIn, float shadowSizeIn, ResourceLocation texture) {
		super(rendererManager, entityModelIn, shadowSizeIn, texture);
	}

	public AnimatedBossRenderer(RenderManager rendererManager, TimeEntityModel entityModelIn, float shadowSizeIn, Function<T, ResourceLocation> entityTextureSupplier) {
		super(rendererManager, entityModelIn, shadowSizeIn, entityTextureSupplier);
	}

	public AnimatedBossRenderer setBossString(String bar, EnumHexColor bossTextColor, EnumHexColor textOutlineColor) {
		this.bar = bar;
		this.stringHexColor = bossTextColor;
		this.stringOutlineColor = textOutlineColor;
		return this;
	}

	@Override
	public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
		EntityPlayer player = (EntityPlayer) entity.getAttackTarget();

		if (player != null) {
			JourneyBossStatus.setStatus(entity, bar, stringHexColor, stringOutlineColor);
		}
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
}
