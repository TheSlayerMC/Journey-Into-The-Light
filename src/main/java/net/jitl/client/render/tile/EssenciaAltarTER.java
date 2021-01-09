package net.jitl.client.render.tile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.jitl.JITL;
import net.jitl.client.render.JRenderTypes;
import net.jitl.common.tile.EssenciaAltarTile;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.vector.Matrix4f;

public class EssenciaAltarTER extends TileEntityRenderer<EssenciaAltarTile> {

    public EssenciaAltarTER(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(EssenciaAltarTile tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        RenderType rtEssenciaAltarSide = JRenderTypes.fullbrightCutout(JITL.rl("textures/tile/charge_indicator_essencia_altar.png"));

        matrixStackIn.pushPose();

        IVertexBuilder builder = bufferIn.getBuffer(rtEssenciaAltarSide);
        float offset = 0.002F;
        verticalQuad(builder, matrixStackIn, 0, 1, 1, 1, false, 1 + offset, false); //to south
        verticalQuad(builder, matrixStackIn, 0, 1, 1, 1, false, -offset, true); //to north
        verticalQuad(builder, matrixStackIn, 0, 1, 1, 1, true, -offset, false); //to west
        verticalQuad(builder, matrixStackIn, 0, 1, 1, 1, true, 1 + offset, true); //to east

        RenderType rtEssenciaAltarTop = JRenderTypes.fullbrightCutout(JITL.rl("textures/tile/charge_indicator_essencia_altar_top.png"));
        builder = bufferIn.getBuffer(rtEssenciaAltarTop);
        facedTopHorizontalQuad(builder, matrixStackIn, 0, 0, 1, 1, 1 + offset);

        matrixStackIn.popPose();
    }

    public static void verticalQuad(IVertexBuilder builder, MatrixStack matrixStack, float x0, float y0, float width, float height, boolean rotateToZ, float offset, boolean invertRenderOrder) {
        Matrix4f pose = matrixStack.last().pose();

        if (invertRenderOrder) {
            builder.vertex(pose, rotateToZ ? offset : x0 + width, y0, rotateToZ ? x0 + width : offset).uv(1, 0).endVertex();
            builder.vertex(pose, rotateToZ ? offset : x0 + width, y0 - height, rotateToZ ? x0 + width : offset).uv(1, 1).endVertex();
            builder.vertex(pose, rotateToZ ? offset : x0, y0 - height, rotateToZ ? x0 : offset).uv(0, 1).endVertex();
            builder.vertex(pose, rotateToZ ? offset : x0, y0, rotateToZ ? x0 : offset).uv(0, 0).endVertex();
        } else {
            builder.vertex(pose, rotateToZ ? offset : x0, y0, rotateToZ ? x0 : offset).uv(0, 0).endVertex();
            builder.vertex(pose, rotateToZ ? offset : x0, y0 - height, rotateToZ ? x0 : offset).uv(0, 1).endVertex();
            builder.vertex(pose, rotateToZ ? offset : x0 + width, y0 - height, rotateToZ ? x0 + width : offset).uv(1, 1).endVertex();
            builder.vertex(pose, rotateToZ ? offset : x0 + width, y0, rotateToZ ? x0 + width : offset).uv(1, 0).endVertex();
        }
    }

    public static void facedTopHorizontalQuad(IVertexBuilder builder, MatrixStack matrixStack, float x0, float z0, float width, float height, float y) {
        Matrix4f pose = matrixStack.last().pose();

        builder.vertex(pose, x0, y, z0).uv(0, 0).endVertex();
        builder.vertex(pose, x0, y, z0 + height).uv(0, 1).endVertex();
        builder.vertex(pose, x0 + width, y, z0 + height).uv(1, 1).endVertex();
        builder.vertex(pose, x0 + width, y, z0).uv(1, 0).endVertex();
    }
}
