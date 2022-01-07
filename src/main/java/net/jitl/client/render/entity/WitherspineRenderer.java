package net.jitl.client.render.entity;

import net.jitl.JITL;
import net.jitl.client.render.JEntityRenderRegistry;
import net.jitl.common.entity.nether.WitherspineEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.animation.renderer.AnimatedLivingEntityRenderer;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;

public class WitherspineRenderer extends AnimatedLivingEntityRenderer<WitherspineEntity, TimeEntityModel<WitherspineEntity>> {
    public WitherspineRenderer(EntityRendererProvider.Context context) {
        super(context, JEntityRenderRegistry.witherspineModel, 0.5F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull WitherspineEntity entity) {
        return new ResourceLocation(JITL.MODID, "textures/entity/nether/witherspine.png");
    }
}