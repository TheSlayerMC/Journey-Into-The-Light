package net.jitl.client.render.entity;


import net.jitl.JITL;
import net.jitl.client.render.entity.layer.FrozenTrollHeldItemLayer;
import net.jitl.client.render.model.frozen.FrozenTrollModel;
import net.jitl.common.entity.frozen.FrozenTrollEntity;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class FrozenTrollRenderer extends MobRenderer<FrozenTrollEntity, FrozenTrollModel<FrozenTrollEntity>> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(JITL.rl("frozen_troll"), "main");

    public FrozenTrollRenderer(EntityRendererProvider.Context context) {
        super(context, new FrozenTrollModel(context.bakeLayer(LAYER_LOCATION)), 0.5F);
        this.addLayer(new FrozenTrollHeldItemLayer<>(this));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull FrozenTrollEntity entity) {
        boolean isAngry = entity.isAngry();
        if (isAngry) {
            return new ResourceLocation(JITL.MODID, "textures/entity/frozen/frozen_troll_angry.png");
        } else {
            return new ResourceLocation(JITL.MODID, "textures/entity/frozen/frozen_troll_lookin_cute.png");
        }
    }
}
