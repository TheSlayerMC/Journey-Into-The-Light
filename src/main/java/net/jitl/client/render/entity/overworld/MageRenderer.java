package net.jitl.client.render.entity.overworld;

import net.jitl.client.render.JModelLayers;
import net.jitl.client.render.model.base.HoodedVillagerModel;
import net.jitl.common.entity.overworld.MageEntity;
import net.jitl.core.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class MageRenderer extends MobRenderer<MageEntity, HoodedVillagerModel<MageEntity>> {

    private static final ResourceLocation MAGE_BASE_SKIN = JITL.rl("textures/entity/overworld/mage.png");

    public MageRenderer(EntityRendererProvider.Context context) {
        super(context, new HoodedVillagerModel<>(context.bakeLayer(JModelLayers.MAGE_MODEL_LAYER)), 0.5F);
        this.addLayer(new CustomHeadLayer<>(this, context.getModelSet()));
        this.addLayer(new CrossedArmsItemLayer<>(this));
    }

    @Override
    @NotNull
    public ResourceLocation getTextureLocation(MageEntity entity) {
        return MAGE_BASE_SKIN;
    }
}
