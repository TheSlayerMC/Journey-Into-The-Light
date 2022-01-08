package net.jitl.client.render.entity;

import net.jitl.JITL;
import net.jitl.client.render.model.frozen.ShattererModel;
import net.jitl.common.entity.frozen.ShattererEntity;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ShattererRenderer extends MobRenderer<ShattererEntity, ShattererModel<ShattererEntity>> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(JITL.rl("shatterer"), "main");

    public ShattererRenderer(EntityRendererProvider.Context context) {
        super(context, new ShattererModel(context.bakeLayer(LAYER_LOCATION)), 0.5F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull ShattererEntity entity) {
        return new ResourceLocation(JITL.MODID, "textures/entity/frozen/shatterer.png");
    }
}
