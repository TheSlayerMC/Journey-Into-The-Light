package net.jitl.client.render.entity.overworld;


import net.jitl.client.render.JModelLayers;
import net.jitl.client.render.model.HongoModel;
import net.jitl.common.entity.overworld.HongoEntity;
import net.jitl.core.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class HongoRenderer extends MobRenderer<HongoEntity, HongoModel<HongoEntity>> {


    public HongoRenderer(EntityRendererProvider.Context context) {
        super(context, new HongoModel<>(context.bakeLayer(JModelLayers.HONGO_MODEL_LAYER)), 0.7F);
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull HongoEntity entityIn) {
        return JITL.rl("textures/entity/overworld/hongo.png");
    }
}
