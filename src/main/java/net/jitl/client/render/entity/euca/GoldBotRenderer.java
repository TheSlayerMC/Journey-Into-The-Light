package net.jitl.client.render.entity.euca;

import net.jitl.client.render.JModelLayers;
import net.jitl.client.render.model.euca.GoldBotModel;
import net.jitl.common.entity.euca.GoldBotEntity;
import net.jitl.core.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class GoldBotRenderer extends MobRenderer<GoldBotEntity, GoldBotModel<GoldBotEntity>> {

    public GoldBotRenderer(EntityRendererProvider.Context context) {
        super(context, new GoldBotModel<>(context.bakeLayer(JModelLayers.GOLD_BOT_MODEL_LAYER)), 0.4F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull GoldBotEntity entity) {
        return new ResourceLocation(JITL.MODID, "textures/entity/euca/gold_bot.png");
    }

}
