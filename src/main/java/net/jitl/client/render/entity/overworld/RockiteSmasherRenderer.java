package net.jitl.client.render.entity.overworld;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.render.JModelLayers;
import net.jitl.client.render.model.overworld.RockiteSmasherModel;
import net.jitl.common.entity.overworld.RockiteSmasherEntity;
import net.jitl.core.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class RockiteSmasherRenderer extends MobRenderer<RockiteSmasherEntity, RockiteSmasherModel<RockiteSmasherEntity>> {

    public RockiteSmasherRenderer(EntityRendererProvider.Context context) {
        super(context, new RockiteSmasherModel<>(context.bakeLayer(JModelLayers.ROCKITE_SMASHER_LAYER)), 0.7F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull RockiteSmasherEntity entityIn) {
        return JITL.rl("textures/entity/overworld/rockite_smasher.png");
    }

    @Override
    protected void scale(RockiteSmasherEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        float f = 1.5F;
        matrixStackIn.scale(f, f, f);
    }
}
