package net.jitl.client.render.entity;

import net.jitl.JITL;
import net.jitl.client.render.JEntityRenderRegistry;
import net.jitl.common.entity.overworld.GlumpEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.animation.renderer.AnimatedLivingEntityRenderer;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;

public class GlumpRenderer extends AnimatedLivingEntityRenderer<GlumpEntity, TimeEntityModel<GlumpEntity>> {
    public GlumpRenderer(EntityRendererManager rendererManager) {
        super(rendererManager, JEntityRenderRegistry.glumpModel, 0.5F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull GlumpEntity entity) {
        return new ResourceLocation(JITL.MODID, "textures/entity/overworld/glump.png");
    }
}