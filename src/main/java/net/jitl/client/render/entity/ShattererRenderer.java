package net.jitl.client.render.entity;

import net.jitl.client.render.JModelLayers;
import net.jitl.client.render.model.frozen.ShattererModel;
import net.jitl.common.entity.frozen.ShattererEntity;
import net.jitl.core.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ShattererRenderer extends MobRenderer<ShattererEntity, ShattererModel<ShattererEntity>> {

    public ShattererRenderer(EntityRendererProvider.Context context) {
        super(context, new ShattererModel(context.bakeLayer(JModelLayers.SHATTERER_MODEL_LAYER)), 0.5F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull ShattererEntity entity) {
        return new ResourceLocation(JITL.MODID, "textures/entity/frozen/shatterer.png");
    }
}
