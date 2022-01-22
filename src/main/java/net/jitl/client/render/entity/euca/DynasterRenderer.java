package net.jitl.client.render.entity.euca;

import net.jitl.client.render.JModelLayers;
import net.jitl.client.render.model.euca.DynasterModel;
import net.jitl.common.entity.euca.DynasterEntity;
import net.jitl.core.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class DynasterRenderer extends MobRenderer<DynasterEntity, DynasterModel<DynasterEntity>> {

    public DynasterRenderer(EntityRendererProvider.Context context) {
        super(context, new DynasterModel<>(context.bakeLayer(JModelLayers.DYNASTER_MODEL_LAYER)), 0.4F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull DynasterEntity entity) {
        return new ResourceLocation(JITL.MODID, "textures/entity/euca/dynaster.png");
    }
}
