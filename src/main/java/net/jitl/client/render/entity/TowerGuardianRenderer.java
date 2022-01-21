package net.jitl.client.render.entity;

import net.jitl.common.entity.overworld.TowerGuardianEntity;
import net.jitl.core.JITL;
import net.jitl.core.init.client.JsonModels;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.animation.renderer.AnimatedLivingEntityRenderer;
import ru.timeconqueror.timecore.animation.renderer.ModelConfiguration;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;

public class TowerGuardianRenderer extends AnimatedLivingEntityRenderer<TowerGuardianEntity, TimeEntityModel<TowerGuardianEntity>> {
	public TowerGuardianRenderer(EntityRendererProvider.Context context) {
		super(context, new TimeEntityModel<>(ModelConfiguration.builder(JsonModels.TOWER_GUARDIAN).build()), 0.5F);
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull TowerGuardianEntity entity) {
		return new ResourceLocation(JITL.MODID, "textures/entity/overworld/tower_guardian.png");
	}
}