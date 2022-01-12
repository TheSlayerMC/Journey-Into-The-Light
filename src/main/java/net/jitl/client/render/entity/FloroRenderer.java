package net.jitl.client.render.entity;

import net.jitl.JITL;
import net.jitl.client.render.JEntityRenderRegistry;
import net.jitl.common.entity.overworld.FloroEntity;
import net.jitl.init.client.JsonModels;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.animation.renderer.AnimatedLivingEntityRenderer;
import ru.timeconqueror.timecore.animation.renderer.ModelConfiguration;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;

public class FloroRenderer extends AnimatedLivingEntityRenderer<FloroEntity, TimeEntityModel<FloroEntity>> {
    public FloroRenderer(EntityRendererProvider.Context context) {
        super(context, new TimeEntityModel<>(ModelConfiguration.builder(JsonModels.FLORO).scaled(1.6F).build()), 0.5F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull FloroEntity entity) {
        return new ResourceLocation(JITL.MODID, "textures/entity/overworld/floro.png");
    }
}