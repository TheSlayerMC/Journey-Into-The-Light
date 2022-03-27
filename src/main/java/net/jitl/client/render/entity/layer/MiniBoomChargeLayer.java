package net.jitl.client.render.entity.layer;

import net.jitl.client.render.JModelLayers;
import net.jitl.client.render.model.overworld.BoomModel;
import net.jitl.common.entity.pet.MiniBoomEntity;
import net.jitl.core.JITL;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MiniBoomChargeLayer extends EnergySwirlLayer<MiniBoomEntity, BoomModel<MiniBoomEntity>> {

    private static final ResourceLocation POWER_LOCATION = JITL.rl("textures/entity/overworld/boomboom_swell.png");

    private final BoomModel<MiniBoomEntity> model;

    public MiniBoomChargeLayer(RenderLayerParent<MiniBoomEntity, BoomModel<MiniBoomEntity>> e, EntityModelSet loader) {
        super(e);
        model = new BoomModel<>(loader.bakeLayer(JModelLayers.MINI_BOOM_CHARGED_LAYER));
    }

    protected float xOffset(float o) {
        return o * 0.01F;
    }

    protected ResourceLocation getTextureLocation() {
        return POWER_LOCATION;
    }

    protected EntityModel<MiniBoomEntity> model() {
        return this.model;
    }
}
