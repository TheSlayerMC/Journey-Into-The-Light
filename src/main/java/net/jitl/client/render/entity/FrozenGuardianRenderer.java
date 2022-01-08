package net.jitl.client.render.entity;


import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.JITL;
import net.jitl.client.render.model.frozen.FrozenGuardianModel;
import net.jitl.common.entity.frozen.FrozenGuardianEntity;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class FrozenGuardianRenderer extends MobRenderer<FrozenGuardianEntity, FrozenGuardianModel<FrozenGuardianEntity>> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(JITL.rl("frozen_guardian"), "main");


    public FrozenGuardianRenderer(EntityRendererProvider.Context context) {
        super(context, new FrozenGuardianModel(context.bakeLayer(LAYER_LOCATION)), 0.5F);
        this.addLayer(new ItemInHandLayer<>(this));
    }

    @Override
    public void render(FrozenGuardianEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

        matrixStackIn.pushPose();

        float f = Mth.cos(entityIn.tickCount * 1.085F);
        float idle = (0.065F + 0.05F * f) * (float) Math.PI;
        matrixStackIn.translate(0, idle, 0);

        matrixStackIn.popPose();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull FrozenGuardianEntity entity) {
        boolean isActivated = entity.isActivated();
        if (isActivated) {
            return new ResourceLocation(JITL.MODID, "textures/entity/frozen/frozen_guardian_awake.png");
        } else {
            return new ResourceLocation(JITL.MODID, "textures/entity/frozen/frozen_guardian_asleep.png");
        }
    }
}
