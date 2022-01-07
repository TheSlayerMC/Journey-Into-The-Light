package net.jitl.client.render.entity;

import net.jitl.JITL;
import net.jitl.client.render.entity.layer.ShiveringRamWoolLayer;
import net.jitl.client.render.model.frozen.ShiveringRamModel;
import net.jitl.common.entity.frozen.ShiveringRamEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ShiveringRamRenderer extends MobRenderer<ShiveringRamEntity, ShiveringRamModel<ShiveringRamEntity>> {

    public ShiveringRamRenderer(EntityRendererProvider.Context context) {
        super(context, new ShiveringRamModel(), 0.5F);
        this.addLayer(new ShiveringRamWoolLayer(this));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull ShiveringRamEntity entity) {
        return new ResourceLocation(JITL.MODID, "textures/entity/frozen/shivering_ram.png");
    }
}
