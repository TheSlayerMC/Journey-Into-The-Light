package net.jitl.client.render.entity;

import net.jitl.JITL;
import net.jitl.client.render.model.frozen.ShattererModel;
import net.jitl.common.entity.frozen.ShattererEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ShattererRenderer extends MobRenderer<ShattererEntity, ShattererModel<ShattererEntity>> {

    private final int time;

    public ShattererRenderer(EntityRenderDispatcher renderManagerIn) {
        super(renderManagerIn, new ShattererModel(), 0.5F);
        this.time = 0;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull ShattererEntity entity) {
        return new ResourceLocation(JITL.MODID, "textures/entity/frozen/shatterer.png");
    }

    /*@Nullable
    @Override
    protected RenderType getRenderType(@NotNull ShattererEntity livingEntity, boolean boolean_, boolean boolean1_, boolean boolean2_) {
        return JRenderTypes.transparentCutout(getTextureLocation(livingEntity));
    }*/
}
