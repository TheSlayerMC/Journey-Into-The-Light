package net.jitl.client.render.tile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.jitl.JITL;
import net.jitl.client.render.JRenderTypes;
import net.jitl.common.tile.LaserEmitterTile;
import net.jitl.util.VecUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.client.model.animation.Animation;

public class LaserEmitterTER extends TileEntityRenderer<LaserEmitterTile> {
    public LaserEmitterTER(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(LaserEmitterTile tile, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        World world = tile.getLevel();

        if (world != null) {
            float gameTime = Animation.getWorldTime(world, partialTicks) * 20;

//            float angleDegrees = 0;
            float angleDegrees = world.getGameTime() % 360 * 2;
            int distance = 20;
            Vector3f end = new Vector3f(distance, 0.5F, 0);
            end.transform(Vector3f.YP.rotationDegrees(angleDegrees));

            BlockPos pos = tile.getBlockPos();
            Vector3d posVec = VecUtils.vec3d(pos).add(0.5, 0.5, 0.5);

            Vector3d endNormalized = new Vector3d(end).normalize();

            BlockRayTraceResult rayTraceResult = world.clip(new RayTraceContext(posVec.add(endNormalized.x, 0, endNormalized.z), posVec.add(end.x(), end.y(), end.z()), RayTraceContext.BlockMode.VISUAL, RayTraceContext.FluidMode.NONE, null));
            Vector3d endPos = rayTraceResult.getLocation();

            renderBeam(pos, gameTime, matrixStackIn, bufferIn, new Vector3d(0.5, 0.5, 0.5), endPos.subtract(VecUtils.vec3d(pos)));
        }
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
