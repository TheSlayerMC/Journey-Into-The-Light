package net.jitl.client.render.tile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.jitl.client.render.JModelLayers;
import net.jitl.common.tile.ObeliskTile;
import net.jitl.core.JITL;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ObeliskTER implements BlockEntityRenderer<ObeliskTile> {

    //FIXME lighting is too dark.
    public static final ResourceLocation OBELISK_LOCATION = JITL.rl("textures/models/block/obelisk.png");

    private final ModelPart top;
    private final ModelPart bottom;

    public ObeliskTER(BlockEntityRendererProvider.Context context) {
        this.top = context.bakeLayer(JModelLayers.OBELISK_TOP_MODEL_LAYER);
        this.bottom = context.bakeLayer(JModelLayers.OBELISK_TOP_MODEL_LAYER);
    }

    @Override
    public void render(ObeliskTile tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        float timeD = (float) (360.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) / 16;
        float yOffset = 1.0F;
        renderPart(matrixStackIn, top, bufferIn, timeD, combinedLightIn, combinedOverlayIn, yOffset);
        renderPart(matrixStackIn, bottom, bufferIn, -timeD, combinedLightIn, combinedOverlayIn, 0);
    }

    //TODO: emissive layer
    public void renderPart(PoseStack matrixStackIn, ModelPart part, MultiBufferSource bufferIn, float timeD, int lightL, int overlayL, float yOffset) {
        matrixStackIn.pushPose();
        matrixStackIn.translate(0.5F, 0.5F + yOffset, 0.5F);
        matrixStackIn.mulPose(Vector3f.YP.rotation(timeD));
        VertexConsumer ivertexbuilder = bufferIn.getBuffer(RenderType.entitySolid(OBELISK_LOCATION));
        part.render(matrixStackIn, ivertexbuilder, lightL, overlayL);
        matrixStackIn.popPose();
    }
}
