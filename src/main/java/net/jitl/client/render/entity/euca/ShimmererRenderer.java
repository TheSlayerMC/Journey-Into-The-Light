package net.jitl.client.render.entity.euca;


import net.jitl.client.render.JModelLayers;
import net.jitl.client.render.model.euca.ShimmererModel;
import net.jitl.common.entity.euca.ShimmererEntity;
import net.jitl.core.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ShimmererRenderer extends MobRenderer<ShimmererEntity, ShimmererModel<ShimmererEntity>> {

    public ShimmererRenderer(EntityRendererProvider.Context context) {
        super(context, new ShimmererModel<>(context.bakeLayer(JModelLayers.SHIMMERER_MODEL_LAYER)), 0.7F);
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull ShimmererEntity entityIn) {
        return JITL.rl("textures/entity/euca/shimmerer.png");
    }
}
