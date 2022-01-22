package net.jitl.client.render.entity.euca;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.render.JModelLayers;
import net.jitl.client.render.model.euca.EucaChargerModel;
import net.jitl.common.entity.euca.EucaChargerEntity;
import net.jitl.core.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class EucaChargerRenderer extends MobRenderer<EucaChargerEntity, EucaChargerModel<EucaChargerEntity>> {

    public EucaChargerRenderer(EntityRendererProvider.Context context) {
        super(context, new EucaChargerModel<>(context.bakeLayer(JModelLayers.EUCA_CHARGER_MODEL_LAYER)), 0.4F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull EucaChargerEntity entity) {
        return new ResourceLocation(JITL.MODID, "textures/entity/euca/euca_charger.png");
    }

    @Override
    protected void scale(EucaChargerEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        float f = 1.5F;
        matrixStackIn.scale(f, f, f);
    }
}
