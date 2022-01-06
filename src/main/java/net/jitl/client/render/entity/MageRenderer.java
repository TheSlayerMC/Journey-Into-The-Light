package net.jitl.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.JITL;
import net.jitl.client.render.model.MageModel;
import net.jitl.common.entity.frozen.EskimoEntity;
import net.jitl.common.entity.overworld.MageEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import ResourceLocation;

@OnlyIn(Dist.CLIENT)
public class MageRenderer extends MobRenderer<MageEntity, MageModel<MageEntity>> {

    private static final ResourceLocation MAGE_BASE_SKIN = JITL.rl("textures/entity/overworld/mage.png");

    public MageRenderer(EntityRenderDispatcher p_i50954_1_) {
        super(p_i50954_1_, new MageModel(0.0F), 0.5F);
        this.addLayer(new CustomHeadLayer(this));
        this.addLayer(new CrossedArmsItemLayer(this));
    }

    public ResourceLocation getTextureLocation(MageEntity entity) {
        return MAGE_BASE_SKIN;
    }

    protected void scale(EskimoEntity e, PoseStack s, float f) {
        float lvt_4_1_ = 0.9375F;
        if (e.isBaby()) {
            lvt_4_1_ = (float) ((double) lvt_4_1_ * 0.5D);
            this.shadowRadius = 0.25F;
        } else {
            this.shadowRadius = 0.5F;
        }

        s.scale(lvt_4_1_, lvt_4_1_, lvt_4_1_);
    }
}
