package net.jitl.client.render.entity;


import net.jitl.JITL;
import net.jitl.client.render.model.FrozenTrollModel;
import net.jitl.common.entity.frozen.FrozenTrollEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class FrozenTrollRenderer extends MobRenderer<FrozenTrollEntity, FrozenTrollModel<FrozenTrollEntity>> {

    public FrozenTrollRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FrozenTrollModel(0.0F), 0.5F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull FrozenTrollEntity entity) {
        return new ResourceLocation(JITL.MODID, "textures/entity/frozen/frozen_troll.png");
    }
}
