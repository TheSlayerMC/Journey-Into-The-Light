package net.jitl.client.render.entity.layer;

import net.jitl.JITL;
import net.jitl.client.render.model.MiniBoomModel;
import net.jitl.common.entity.pet.MiniBoomEntity;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MiniBoomChargeLayer extends EnergySwirlLayer<MiniBoomEntity, MiniBoomModel<MiniBoomEntity>> {

    private static final ResourceLocation POWER_LOCATION = JITL.rl("textures/entity/overworld/boomboom_swell.png");

    private final MiniBoomModel<MiniBoomEntity> model = new MiniBoomModel(2.0F);

    public MiniBoomChargeLayer(RenderLayerParent<MiniBoomEntity, MiniBoomModel<MiniBoomEntity>> e) {
        super(e);
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
