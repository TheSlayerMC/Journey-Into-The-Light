package net.jitl.client.render.tile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.jitl.JITL;
import net.jitl.client.Models;
import net.jitl.client.render.JRenderTypes;
import net.jitl.common.tile.LaserEmitterTile;
import net.jitl.util.VecUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.client.model.animation.Animation;
import ru.timeconqueror.timecore.client.render.model.TimeModel;

public class LaserEmitterTER extends TileEntityRenderer<LaserEmitterTile> {

    public LaserEmitterTER(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(LaserEmitterTile tile, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        World world = tile.getLevel();
        if (world == null) return;

        float gameTime = Animation.getWorldTime(world, partialTicks) * 20;
        float angleDegrees = gameTime % 360;
        Quaternion rotationQuaternion = Vector3f.YP.rotationDegrees(0);

        renderModel(Models.fullCube, JITL.rl("textures/block/laser_emitter.png"), rotationQuaternion, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);

        int maxDistance = 20;
        Vector3f beamEndVec = new Vector3f(maxDistance, 0.5F, 0);
        beamEndVec.transform(rotationQuaternion);

        BlockPos emitterPos = tile.getBlockPos();
        Vector3d posVec = VecUtils.vec3d(emitterPos).add(0.5, 0.5, 0.5);

        Vector3d endNormalized = new Vector3d(beamEndVec).normalize();

//        Vector3f testStart = new Vector3f(0.5F, 0, 0);
//        testStart.transform(rotationQuaternion);
//        testStart.add(0.5F, 0.5F, 0.5F);
//        IVertexBuilder buffer = bufferIn.getBuffer(RenderType.lines());
//        Matrix4f pose = matrixStackIn.last().pose();
//        buffer.vertex(pose, testStart.x(), testStart.y(), testStart.z()).color(255, 255, 255, 255).endVertex();
//        buffer.vertex(pose, testStart.x(),  testStart.y() + 16, testStart.z()).color(255, 255, 255, 255).endVertex();

        BlockRayTraceResult rayTraceResult = world.clip(new RayTraceContext(posVec.add(endNormalized.x, 0, endNormalized.z), posVec.add(beamEndVec.x(), beamEndVec.y(), beamEndVec.z()), RayTraceContext.BlockMode.VISUAL, RayTraceContext.FluidMode.NONE, null));
        Vector3d endPos = rayTraceResult.getLocation();

//        System.out.println("endPos.subtract(VecUtils.vec3d(emitterPos)) = " + endPos.subtract(VecUtils.vec3d(emitterPos)));
        renderBeam(emitterPos, gameTime, matrixStackIn, bufferIn, new Vector3d(0.5, 0.5, 0.5), endPos.subtract(VecUtils.vec3d(emitterPos)));
//        renderBeam(emitterPos, gameTime, matrixStackIn, bufferIn, new Vector3d(0.5, 0.5, 0.5), new Vector3d(20.5, 1, 0.5));
    }

    public static void renderModel(TimeModel model, ResourceLocation texture, Quaternion rotationQuaternion, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
        matrixStack.pushPose();

        matrixStack.translate(0.5F, 0, 0.5F);

        matrixStack.mulPose(rotationQuaternion);

        matrixStack.scale(-1, -1, 1);
        model.renderToBuffer(matrixStack, buffer.getBuffer(model.renderType(texture)), combinedLightIn, combinedOverlayIn, 1, 1, 1, 1);

        matrixStack.popPose();
    }

    public static void renderBeam(BlockPos pos, float gameTime, MatrixStack stack, IRenderTypeBuffer bufferIn, Vector3d startIn, Vector3d endIn) {
        Vector3f start = new Vector3f(startIn);
        Vector3f end = new Vector3f(endIn);
        IVertexBuilder bufferBuilder = bufferIn.getBuffer(JRenderTypes.laserBeam(JITL.rl("textures/tile/laser_beam.png")));

        MatrixStack.Entry last = stack.last();
        Matrix4f posMatrix = last.pose();

        ActiveRenderInfo activeRenderInfo = Minecraft.getInstance().gameRenderer.getMainCamera();
        Vector3d camera = activeRenderInfo.getPosition().subtract(pos.getX(), pos.getY(), pos.getZ());

        float radius = 3 / 16F + (float) Math.sin((float) Math.toRadians(gameTime % 360 / 10F)) / 64F;
        float texWidth = 32;

        float uPerBlock = 1 / (256 / texWidth * (radius * 2));
        float distance = (float) startIn.distanceTo(endIn);

        float startU = gameTime % 5 / 5F;
        float endU = startU + distance * uPerBlock;

        Vector3f startToCamera = new Vector3f(camera);
        startToCamera.sub(start);
        startToCamera.normalize();// vec from start to camera with length 1

        Vector3f perpendicular = end.copy();
        perpendicular.sub(start);
        perpendicular.normalize(); // vec from start to end with length 1

        perpendicular.cross(startToCamera);
        perpendicular.normalize();
        perpendicular.mul(radius); // perpendicular to beam and camera

//        Vector3d startToLeft = startToEnd.cross(startToCamera).normalize().scale(radius);
//        Vector3d startToRight = startToLeft.scale(-1);

        bufferBuilder.vertex(posMatrix, start.x() + perpendicular.x(), start.y() + perpendicular.y(), start.z() + perpendicular.z()).uv(endU, 0).endVertex();
        bufferBuilder.vertex(posMatrix, end.x() + perpendicular.x(), end.y() + perpendicular.y(), end.z() + perpendicular.z()).uv(startU, 0).endVertex();

        perpendicular.mul(-1);

        bufferBuilder.vertex(posMatrix, end.x() + perpendicular.x(), end.y() + perpendicular.y(), end.z() + perpendicular.z()).uv(startU, texWidth / 256F).endVertex();
        bufferBuilder.vertex(posMatrix, start.x() + perpendicular.x(), start.y() + perpendicular.y(), start.z() + perpendicular.z()).uv(endU, texWidth / 256F).endVertex();
    }

    @Override
    public boolean shouldRenderOffScreen(LaserEmitterTile te) {
        return true;
    }
}
