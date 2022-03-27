package net.jitl.client.render.entity.overworld;

import net.jitl.client.render.JModelLayers;
import net.jitl.client.render.model.overworld.BrownHongoModel;
import net.jitl.common.entity.overworld.BrownHongoEntity;
import net.jitl.core.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class BrownHongoRenderer extends MobRenderer<BrownHongoEntity, BrownHongoModel<BrownHongoEntity>> {

    public BrownHongoRenderer(EntityRendererProvider.Context context) {
        super(context, new BrownHongoModel<>(context.bakeLayer(JModelLayers.BROWN_HONGO_MODEL_LAYER)), 0.7F);
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull BrownHongoEntity entityIn) {
        return JITL.rl("textures/entity/overworld/brown_hongo.png");
    }
}
