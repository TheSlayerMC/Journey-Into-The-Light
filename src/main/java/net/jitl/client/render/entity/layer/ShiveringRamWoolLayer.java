package net.jitl.client.render.entity.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.jitl.JITL;
import net.jitl.client.render.model.frozen.ShiveringRamModel;
import net.jitl.client.render.model.frozen.ShiveringRamWoolModel;
import net.jitl.common.entity.frozen.ShiveringRamEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShiveringRamWoolLayer extends LayerRenderer<ShiveringRamEntity, ShiveringRamModel<ShiveringRamEntity>> {
    private static final ResourceLocation SHEEP_FUR_LOCATION = JITL.rl("textures/entity/frozen/shivering_ram_wool.png");
    private final ShiveringRamWoolModel<ShiveringRamEntity> model = new ShiveringRamWoolModel<>();

    public ShiveringRamWoolLayer(IEntityRenderer<ShiveringRamEntity, ShiveringRamModel<ShiveringRamEntity>> rendererIn) {
        super(rendererIn);
    }

    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, ShiveringRamEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.isSheared() && !entitylivingbaseIn.isInvisible()) {
            coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, SHEEP_FUR_LOCATION, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1.0F, 1.0F, 1.0F);
        }
    }
}