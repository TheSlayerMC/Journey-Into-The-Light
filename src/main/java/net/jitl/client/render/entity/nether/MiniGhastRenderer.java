package net.jitl.client.render.entity.nether;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.render.JModelLayers;
import net.jitl.common.entity.nether.MiniGhastEntity;
import net.jitl.core.JITL;
import net.minecraft.client.model.GhastModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class MiniGhastRenderer extends MobRenderer<MiniGhastEntity, GhastModel<MiniGhastEntity>> {

    public MiniGhastRenderer(EntityRendererProvider.Context context) {
        super(context, new GhastModel<>(context.bakeLayer(JModelLayers.MINI_GHAST_LAYER)), 0.5F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull MiniGhastEntity entity) {
        return new ResourceLocation(JITL.MODID, "textures/entity/nether/mini_ghast.png");
    }

    @Override
    protected void scale(MiniGhastEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        float f = 0.5F;
        matrixStackIn.scale(f, f, f);
    }
}
