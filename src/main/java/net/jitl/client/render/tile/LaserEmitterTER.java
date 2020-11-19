package net.jitl.client.render.tile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.jitl.JITL;
import net.jitl.client.render.JRenderTypes;
import net.jitl.common.tile.LaserEmitterTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.model.animation.Animation;

public class LaserEmitterTER extends TileEntityRenderer<LaserEmitterTile> {
    public LaserEmitterTER(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(LaserEmitterTile tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        float gameTime = Animation.getWorldTime(tileEntityIn.getLevel(), partialTicks) * 20;
        renderBeam(tileEntityIn.getBlockPos(), gameTime, matrixStackIn, bufferIn, new Vector3d(0.5, 0.5, 0.5), new Vector3d(20, 0.5, 20));
    }

    public static void renderBeam(BlockPos pos, float gameTime, MatrixStack stack, IRenderTypeBuffer bufferIn, Vector3d start, Vector3d end) {
        IVertexBuilder bufferBuilder = bufferIn.getBuffer(JRenderTypes.laserBeam(JITL.rl("textures/tile/laser_beam.png")));

        MatrixStack.Entry last = stack.last();
        Matrix4f posMatrix = last.pose();

        ActiveRenderInfo activeRenderInfo = Minecraft.getInstance().gameRenderer.getMainCamera();
        Vector3d camera = activeRenderInfo.getPosition().subtract(pos.getX(), pos.getY(), pos.getZ());

        float radius = 3 / 16F + (float) Math.sin((float) Math.toRadians(gameTime % (360 * 1) / 10F)) / 64F;
        float texWidth = 32;

        float uPerBlock = 1 / (256 / texWidth * (radius * 2));
        float distance = (float) start.distanceTo(end);

        float startU = gameTime % 5 / 5F;
        float endU = startU + distance * uPerBlock;

        Vector3d startToEnd = end.subtract(start).normalize();
        Vector3d startToCamera = camera.subtract(start).normalize();

        Vector3d startToLeft = startToEnd.cross(startToCamera).normalize().scale(radius);
        Vector3d startToRight = startToLeft.scale(-1);

        bufferBuilder.vertex(posMatrix, ((float) (start.x + startToLeft.x)), ((float) (start.y + startToLeft.y)), ((float) (start.z + startToLeft.z))).uv(endU, 0).endVertex();
        bufferBuilder.vertex(posMatrix, ((float) (end.x + startToLeft.x)), ((float) (end.y + startToLeft.y)), ((float) (end.z + startToLeft.z))).uv(startU, 0).endVertex();
        bufferBuilder.vertex(posMatrix, ((float) (end.x + startToRight.x)), ((float) (start.y + startToRight.y)), ((float) (end.z + startToRight.z))).uv(startU, texWidth / 256F).endVertex();
        bufferBuilder.vertex(posMatrix, ((float) (start.x + startToRight.x)), ((float) (start.y + startToRight.y)), ((float) (start.z + startToRight.z))).uv(endU, texWidth / 256F).endVertex();
    }

    @Override
    public boolean shouldRenderOffScreen(LaserEmitterTile te) {
        return true;
    }
}
