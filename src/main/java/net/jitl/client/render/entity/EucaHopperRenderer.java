package net.jitl.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.render.JModelLayers;
import net.jitl.client.render.model.euca.EucaHopperModel;
import net.jitl.common.entity.euca.EucaHopperEntity;
import net.jitl.core.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class EucaHopperRenderer extends MobRenderer<EucaHopperEntity, EucaHopperModel<EucaHopperEntity>> {

    public EucaHopperRenderer(EntityRendererProvider.Context context) {
        super(context, new EucaHopperModel<>(context.bakeLayer(JModelLayers.EUCA_HOPPER_MODEL_LAYER)), 0.5F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull EucaHopperEntity entity) {
        return new ResourceLocation(JITL.MODID, "textures/entity/euca/euca_hopper.png");
    }

    @Override
    protected void scale(EucaHopperEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        float f = 1.25F;
        matrixStackIn.scale(f, f, f);
    }
}
