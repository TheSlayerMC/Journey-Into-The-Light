package net.jitl.client.render.entity.overworld;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
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

    @Override
    protected void setupRotations(RockiteSmasherEntity entityLiving, PoseStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStack, ageInTicks, rotationYaw, partialTicks);
        if (!((double)entityLiving.animationSpeed < 0.01D)) {
            float f = 13.0F;
            float f1 = entityLiving.animationPosition - entityLiving.animationSpeed * (1.0F - partialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            matrixStack.mulPose(Vector3f.ZP.rotationDegrees(6.5F * f2));
        }
    }
}
