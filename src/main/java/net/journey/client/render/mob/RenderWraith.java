package net.journey.client.render.mob;

import net.journey.client.render.base.RenderModMob;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;

public class RenderWraith extends RenderModMob {

    public RenderWraith(ModelBase model, ResourceLocation tex) {
        super(model, tex);
        this.addLayer(new LayerHeldItem(this));
    }
}
