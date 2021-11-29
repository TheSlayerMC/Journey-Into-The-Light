package net.jitl.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.jitl.JITL;
import net.jitl.client.render.model.EskimoModel;
import net.jitl.common.entity.frozen.EskimoEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EskimoRenderer extends MobRenderer<EskimoEntity, EskimoModel<EskimoEntity>> {
    private static final ResourceLocation ESKIMO_BASE_SKIN = JITL.rl("textures/entity/frozen/eskimo_base.png");

    public EskimoRenderer(EntityRendererManager p_i50954_1_) {
        super(p_i50954_1_, new EskimoModel(0.0F), 0.5F);
        this.addLayer(new HeadLayer(this));
        //this.addLayer(new EskimoLevelPendantLayer(this, p_i50954_2_, "eskimo"));
        this.addLayer(new CrossedArmsItemLayer(this));
    }

    public ResourceLocation getTextureLocation(EskimoEntity p_110775_1_) {
        return ESKIMO_BASE_SKIN;
    }

    protected void scale(EskimoEntity p_225620_1_, MatrixStack p_225620_2_, float p_225620_3_) {
        float lvt_4_1_ = 0.9375F;
        if (p_225620_1_.isBaby()) {
            lvt_4_1_ = (float) ((double) lvt_4_1_ * 0.5D);
            this.shadowRadius = 0.25F;
        } else {
            this.shadowRadius = 0.5F;
        }

        p_225620_2_.scale(lvt_4_1_, lvt_4_1_, lvt_4_1_);
    }
}
