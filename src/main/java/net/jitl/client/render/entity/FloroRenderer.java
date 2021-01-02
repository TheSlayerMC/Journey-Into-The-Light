package net.jitl.client.render.entity;

import net.jitl.JITL;
import net.jitl.client.render.JEntityRenderRegistry;
import net.jitl.common.entity.FloroEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import ru.timeconqueror.timecore.animation.renderer.AnimatedLivingEntityRenderer;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;

public class FloroRenderer extends AnimatedLivingEntityRenderer<FloroEntity, TimeEntityModel<FloroEntity>> {
    public FloroRenderer(EntityRendererManager rendererManager) {
        super(rendererManager, JEntityRenderRegistry.floroModel.setScaleMultiplier(1.6F), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(FloroEntity entity) {
        return new ResourceLocation(JITL.MODID, "textures/entity/floro.png");
    }
}