package net.jitl.client.render.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.JITL;
import net.jitl.client.render.JModelLayers;
import net.jitl.client.render.model.frozen.ShiveringRamModel;
import net.jitl.client.render.model.frozen.ShiveringRamWoolModel;
import net.jitl.common.entity.frozen.ShiveringRamEntity;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShiveringRamWoolLayer extends RenderLayer<ShiveringRamEntity, ShiveringRamModel<ShiveringRamEntity>> {
    private static final ResourceLocation SHEEP_FUR_LOCATION = JITL.rl("textures/entity/frozen/shivering_ram_wool.png");

    private final ShiveringRamWoolModel<ShiveringRamEntity> model;

    public ShiveringRamWoolLayer(RenderLayerParent<ShiveringRamEntity, ShiveringRamModel<ShiveringRamEntity>> rendererIn, EntityModelSet loader) {
        super(rendererIn);
        model = new ShiveringRamWoolModel<>(loader.bakeLayer(JModelLayers.SHIVERING_RAM_WOOL_LAYER));
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, ShiveringRamEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.isSheared() && !entitylivingbaseIn.isInvisible()) {
            coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, SHEEP_FUR_LOCATION, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1.0F, 1.0F, 1.0F);
        }
    }
}