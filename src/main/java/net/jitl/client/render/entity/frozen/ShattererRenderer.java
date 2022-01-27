package net.jitl.client.render.entity.frozen;

import net.jitl.client.render.JModelLayers;
import net.jitl.client.render.model.frozen.ShattererModel;
import net.jitl.common.entity.frozen.ShattererEntity;
import net.jitl.core.JITL;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ShattererRenderer extends MobRenderer<ShattererEntity, ShattererModel<ShattererEntity>> {

    public ShattererRenderer(EntityRendererProvider.Context context) {
        super(context, new ShattererModel(context.bakeLayer(JModelLayers.SHATTERER_MODEL_LAYER)), 0.5F);
    }

    @Nullable
    @Override
    protected RenderType getRenderType(@NotNull ShattererEntity livingEntity, boolean boolean_, boolean boolean1_, boolean boolean2_) {
        return RenderType.entityTranslucent(getTextureLocation(livingEntity));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull ShattererEntity entity) {
        return new ResourceLocation(JITL.MODID, "textures/entity/frozen/shatterer.png");
    }
}
