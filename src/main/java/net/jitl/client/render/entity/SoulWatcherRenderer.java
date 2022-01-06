package net.jitl.client.render.entity;

import net.jitl.JITL;
import net.jitl.client.render.JEntityRenderRegistry;
import net.jitl.common.entity.nether.SoulWatcherEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import ru.timeconqueror.timecore.animation.renderer.AnimatedLivingEntityRenderer;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;

public class SoulWatcherRenderer extends AnimatedLivingEntityRenderer<SoulWatcherEntity, TimeEntityModel<SoulWatcherEntity>> {

    public SoulWatcherRenderer(EntityRenderDispatcher rendererManager) {
        super(rendererManager, JEntityRenderRegistry.soulWatcherModel, 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(SoulWatcherEntity entityIn) {
        return new ResourceLocation(JITL.MODID, "textures/entity/nether/soul_watcher.png");
    }
}
