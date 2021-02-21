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
import net.minecraft.client.renderer.RenderType;
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

//TODO optimize - TimeCore- add registry for buffers to endBatch them in WorldRenderer to heavily increase FPS
public class LaserEmitterTER extends TileEntityRenderer<LaserEmitterTile> {
    private static final RenderType TYPE_LASER_BEAM = JRenderTypes.laserBeam(JITL.rl("textures/tile/laser_beam.png"));
    private static final Vector3d BLOCK_CENTER = new Vector3d(0.5, 0.5, 0.5);

    public LaserEmitterTER(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(LaserEmitterTile tile, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        World world = tile.getLevel();
        if (world == null) return;

        float gameTime = Animation.getWorldTime(world, partialTicks) * 20;
        float angleDegrees = tile.getLaserAngle() + partialTicks;
        Quaternion rotationQuaternion = Vector3f.YP.rotationDegrees(angleDegrees);

        renderModel(Models.fullCube, JITL.rl("textures/block/laser_emitter.png"), rotationQuaternion, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);

        int maxDistance = 20;
        Vector3f beamEndVec = new Vector3f(maxDistance, 0.5F, 0);
        beamEndVec.transform(rotationQuaternion);

        BlockPos emitterPos = tile.getBlockPos();
        Vector3d posVec = VecUtils.vec3d(emitterPos);

        Vector3f beamStartVec = new Vector3f(0.5F, 0, 0);
        beamStartVec.transform(rotationQuaternion);
        VecUtils.addToFirst(beamStartVec, BLOCK_CENTER);

        Vector3f blockSide = new Vector3f(1F, 0, 0);
        blockSide.transform(rotationQuaternion);
        VecUtils.cubify(blockSide, 1, 1, 1);
        VecUtils.addToFirst(blockSide, BLOCK_CENTER);

        BlockRayTraceResult rayTraceResult = world.clip(new RayTraceContext(VecUtils.add(posVec, blockSide), VecUtils.add(posVec, beamEndVec), RayTraceContext.BlockMode.VISUAL, RayTraceContext.FluidMode.NONE, null));
        Vector3d endPos = rayTraceResult.getLocation();

        renderBeam(emitterPos, gameTime, matrixStackIn, bufferIn, VecUtils.vec3d(beamStartVec), endPos.subtract(posVec));
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

        IVertexBuilder laserBeamBuffer = bufferIn.getBuffer(TYPE_LASER_BEAM);

        MatrixStack.Entry last = stack.last();
        Matrix4f posMatrix = last.pose();

        ActiveRenderInfo activeRenderInfo = Minecraft.getInstance().gameRenderer.getMainCamera();
        Vector3d camera = activeRenderInfo.getPosition().subtract(pos.getX(), pos.getY(), pos.getZ());

        float speed = 4;
        float amplitude = 21; //bigger - lesser
        float radius = 2.5F / 16F + (float) Math.abs(Math.sin(Math.toRadians(gameTime % 360) * speed) / amplitude);
        float textHeight = 9;

        float uPerBlock = 1 / (128 / textHeight * (radius * 2));
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

        laserBeamBuffer.vertex(posMatrix, start.x() + perpendicular.x(), start.y() + perpendicular.y(), start.z() + perpendicular.z()).uv(endU, 0).endVertex();
        laserBeamBuffer.vertex(posMatrix, end.x() + perpendicular.x(), end.y() + perpendicular.y(), end.z() + perpendicular.z()).uv(startU, 0).endVertex();

        perpendicular.mul(-1);

        laserBeamBuffer.vertex(posMatrix, end.x() + perpendicular.x(), end.y() + perpendicular.y(), end.z() + perpendicular.z()).uv(startU, textHeight / 128F).endVertex();
        laserBeamBuffer.vertex(posMatrix, start.x() + perpendicular.x(), start.y() + perpendicular.y(), start.z() + perpendicular.z()).uv(endU, textHeight / 128F).endVertex();

        // Ends part

        Vector3f toCamera = new Vector3f(camera);
        toCamera.sub(start);
        toCamera.normalize();

        VecUtils.addToFirst(start, new Vector3d(toCamera.x() * 0.2F, toCamera.y() * 0.2F, toCamera.z() * 0.2F));

        Vector3f leftPerpendicular = toCamera.copy();
        Vector3f beamDirection = end.copy();
        beamDirection.sub(start);
        leftPerpendicular.cross(beamDirection);
        leftPerpendicular.normalize();

        Vector3f upPerpendicular = leftPerpendicular.copy();
        upPerpendicular.cross(toCamera);
        upPerpendicular.normalize();

        float decreased = radius - 0.01F; /*small radius decrease to make more hi-res spark texture be mixed with beam itself */
        leftPerpendicular.mul(decreased);
        upPerpendicular.mul(decreased);

        int sparkTexIndex = 1;
        float sparkSize = 19 / 128F;
        float sparkOffset = 1 / 128F;
        float sparkTexU0 = sparkSize * sparkTexIndex + sparkOffset;
        float sparkTexU1 = sparkTexU0 + (sparkSize - sparkOffset);
        float sparkTexV0 = 9 / 128F + sparkOffset;
        float spartTexV1 = sparkTexV0 + (sparkSize - sparkOffset);
        laserBeamBuffer.vertex(posMatrix, start.x() + leftPerpendicular.x() + upPerpendicular.x(), start.y() + leftPerpendicular.y() + upPerpendicular.y(), start.z() + leftPerpendicular.z() + upPerpendicular.z()).uv(sparkTexU0, sparkTexV0).endVertex();
        laserBeamBuffer.vertex(posMatrix, start.x() + leftPerpendicular.x() - upPerpendicular.x(), start.y() + leftPerpendicular.y() - upPerpendicular.y(), start.z() + leftPerpendicular.z() - upPerpendicular.z()).uv(sparkTexU0, spartTexV1).endVertex();
        laserBeamBuffer.vertex(posMatrix, start.x() - leftPerpendicular.x() - upPerpendicular.x(), start.y() - leftPerpendicular.y() - upPerpendicular.y(), start.z() - leftPerpendicular.z() - upPerpendicular.z()).uv(sparkTexU1, spartTexV1).endVertex();
        laserBeamBuffer.vertex(posMatrix, start.x() - leftPerpendicular.x() + upPerpendicular.x(), start.y() - leftPerpendicular.y() + upPerpendicular.y(), start.z() - leftPerpendicular.z() + upPerpendicular.z()).uv(sparkTexU1, sparkTexV0).endVertex();
    }

    @Override
    public boolean shouldRenderOffScreen(LaserEmitterTile te) {
        return true;
    }
}
