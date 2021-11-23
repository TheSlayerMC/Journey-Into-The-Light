package net.jitl.client.render.entity;

import net.jitl.JITL;
import net.jitl.client.render.JEntityRenderRegistry;
import net.jitl.common.entity.overworld.IllagerMechEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.animation.renderer.AnimatedLivingEntityRenderer;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;

@OnlyIn(Dist.CLIENT)
public class IllagerMechRenderer extends AnimatedLivingEntityRenderer<IllagerMechEntity, TimeEntityModel<IllagerMechEntity>> {
    public IllagerMechRenderer(EntityRendererManager rendererManager) {
        super(rendererManager, JEntityRenderRegistry.illagerMechModel, 0.5F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull IllagerMechEntity entity) {
        return new ResourceLocation(JITL.MODID, "textures/entity/overworld/illager_mech.png");
    }
}