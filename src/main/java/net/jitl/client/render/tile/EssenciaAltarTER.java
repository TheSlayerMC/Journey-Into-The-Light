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
import net.minecraft.util.math.vector.Vector3f;
import ru.timeconqueror.timecore.api.util.HorizontalDirection;

import java.util.Random;

public class EssenciaAltarTER extends TileEntityRenderer<EssenciaAltarTile> {
    private static final Random RANDOM = new Random();

    public EssenciaAltarTER(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(EssenciaAltarTile tile, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (!tile.isActivated()) return;

        RenderType rtEssenciaAltarSide = JRenderTypes.fullbrightCutout(JITL.rl("textures/tile/charge_indicator_essencia_altar.png"));//fixme move to static

        matrixStackIn.pushPose();

        IVertexBuilder builder = bufferIn.getBuffer(rtEssenciaAltarSide);
        float offset = 0.002F;
        verticalQuad(builder, matrixStackIn, 0, 1, 1, 1, false, 1 + offset, false); //to south
        verticalQuad(builder, matrixStackIn, 0, 1, 1, 1, false, -offset, true); //to north
        verticalQuad(builder, matrixStackIn, 0, 1, 1, 1, true, -offset, false); //to west
        verticalQuad(builder, matrixStackIn, 0, 1, 1, 1, true, 1 + offset, true); //to east

        RenderType rtEssenciaAltarTop = JRenderTypes.fullbrightCutout(JITL.rl("textures/tile/charge_indicator_essencia_altar_top.png"));//fixme move to static
        builder = bufferIn.getBuffer(rtEssenciaAltarTop);
        facedTopHorizontalQuad(builder, matrixStackIn, 0, 0, 1, 1, 1 + offset);

        RenderType rtConnector = JRenderTypes.fullbrightCutout(JITL.rl("textures/tile/charge_indicator_runic_connector.png"));//fixme move to static
        builder = bufferIn.getBuffer(rtConnector);

        float v = getLength(tile, HorizontalDirection.SOUTH);
        facedTopPercentedQuad(builder, matrixStackIn, 0, 1 + 1 / 16F, 1, v, offset, false);//south
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90));
        v = getLength(tile, HorizontalDirection.EAST);
        facedTopPercentedQuad(builder, matrixStackIn, -1, 1 + 1 / 16F, 1, v, offset, false);//east
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90));
        v = getLength(tile, HorizontalDirection.NORTH);
        facedTopPercentedQuad(builder, matrixStackIn, -1, 1 / 16F, 1, v, offset, false);//north
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90));
        v = getLength(tile, HorizontalDirection.WEST);
        facedTopPercentedQuad(builder, matrixStackIn, 0, 1 / 16F, 1, v, offset, false);//west

        matrixStackIn.popPose();
    }

    private float getLength(EssenciaAltarTile tile, HorizontalDirection direction) {
        EssenciaAltarTile.Path path = tile.getPath(direction);
        float length = path.getCurrentLength();

        RANDOM.setSeed(tile.getRandomSeed());
        float decrement = length < 4 && path.isFullLength() ? RANDOM.nextFloat() / 3 : 0;
        return Math.max(0, Math.min(length, 3) - decrement) + 0.1F/*needed to set light to the last brick*/;
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

    public static void facedTopPercentedQuad(IVertexBuilder builder, MatrixStack matrixStack, float x0, float z0, float width, float height, float y, boolean invertDirection) {
        Matrix4f pose = matrixStack.last().pose();


        int pixelHeight = (int) (height * 16);
        pixelHeight -= pixelHeight % 2 == 1 ? 1 : 0;
        float v = pixelHeight / 16F;

        builder.vertex(pose, x0, y, z0).uv(0, 0).endVertex();
        builder.vertex(pose, x0, y, z0 + v).uv(0, v).endVertex();
        builder.vertex(pose, x0 + width, y, z0 + v).uv(1, v).endVertex();
        builder.vertex(pose, x0 + width, y, z0).uv(1, 0).endVertex();
    }
}
