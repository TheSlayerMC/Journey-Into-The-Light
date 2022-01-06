package net.jitl.client.render.entity;

import net.jitl.JITL;
import net.jitl.client.render.JEntityRenderRegistry;
import net.jitl.common.entity.overworld.FloroEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.animation.renderer.AnimatedLivingEntityRenderer;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;

public class FloroRenderer extends AnimatedLivingEntityRenderer<FloroEntity, TimeEntityModel<FloroEntity>> {
    public FloroRenderer(EntityRenderDispatcher rendererManager) {
        super(rendererManager, JEntityRenderRegistry.floroModel.setScaleMultiplier(1.6F), 0.5F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull FloroEntity entity) {
        return new ResourceLocation(JITL.MODID, "textures/entity/overworld/floro.png");
    }
}