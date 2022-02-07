package net.jitl.client.render.entity.overworld;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.render.JModelLayers;
import net.jitl.client.render.model.base.HoodedVillagerModel;
import net.jitl.common.entity.frozen.EskimoEntity;
import net.jitl.common.entity.overworld.MageEntity;
import net.jitl.core.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class MageRenderer extends MobRenderer<MageEntity, HoodedVillagerModel<MageEntity>> {

    private static final ResourceLocation MAGE_BASE_SKIN = JITL.rl("textures/entity/overworld/mage.png");

    public MageRenderer(EntityRendererProvider.Context context) {
        super(context, new HoodedVillagerModel<>(context.bakeLayer(JModelLayers.MAGE_MODEL_LAYER)), 0.5F);
        this.addLayer(new CustomHeadLayer<>(this, context.getModelSet()));
        this.addLayer(new CrossedArmsItemLayer<>(this));
    }

    @Override
    @NotNull
    public ResourceLocation getTextureLocation(MageEntity entity) {
        return MAGE_BASE_SKIN;
    }

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
