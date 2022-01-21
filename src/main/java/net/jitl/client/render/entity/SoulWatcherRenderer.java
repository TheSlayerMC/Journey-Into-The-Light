package net.jitl.client.render.entity;

import net.jitl.common.entity.nether.SoulWatcherEntity;
import net.jitl.core.JITL;
import net.jitl.core.init.client.JsonModels;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import ru.timeconqueror.timecore.animation.renderer.AnimatedLivingEntityRenderer;
import ru.timeconqueror.timecore.animation.renderer.ModelConfiguration;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;

public class SoulWatcherRenderer extends AnimatedLivingEntityRenderer<SoulWatcherEntity, TimeEntityModel<SoulWatcherEntity>> {

    public SoulWatcherRenderer(EntityRendererProvider.Context context) {
        super(context, new TimeEntityModel<>(ModelConfiguration.builder(JsonModels.SOUL_WATCHER).build()), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(SoulWatcherEntity entityIn) {
        return new ResourceLocation(JITL.MODID, "textures/entity/nether/soul_watcher.png");
    }
}
