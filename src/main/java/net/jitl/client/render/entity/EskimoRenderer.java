package net.jitl.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.render.JModelLayers;
import net.jitl.client.render.model.frozen.EskimoModel;
import net.jitl.common.entity.frozen.EskimoEntity;
import net.jitl.core.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EskimoRenderer extends MobRenderer<EskimoEntity, EskimoModel<EskimoEntity>> {

    private static final ResourceLocation ESKIMO_BASE_SKIN = JITL.rl("textures/entity/frozen/eskimo_base.png");

    public EskimoRenderer(EntityRendererProvider.Context context) {
        super(context, new EskimoModel<>(context.bakeLayer(JModelLayers.ESKIMO_MODEL_LAYER)), 0.5F);
        this.addLayer(new CustomHeadLayer<>(this, context.getModelSet()));
        //this.addLayer(new EskimoLevelPendantLayer(this, p_i50954_2_, "eskimo"));
        this.addLayer(new CrossedArmsItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(EskimoEntity p_110775_1_) {
        return ESKIMO_BASE_SKIN;
    }

    @Override
    protected void scale(EskimoEntity entity, PoseStack poseStack, float ticks) {
        float scale = 0.9375F;
        if (entity.isBaby()) {
            scale = (float) ((double) scale * 0.5D);
            this.shadowRadius = 0.25F;
        } else {
            this.shadowRadius = 0.5F;
        }

        poseStack.scale(scale, scale, scale);
    }
}
