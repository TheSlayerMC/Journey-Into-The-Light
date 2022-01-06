package net.jitl.client.render.entity.base;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import org.jetbrains.annotations.NotNull;

import Entity;

public class Entity2DRenderer<T extends Entity> extends EntityRenderer<T> {
    private final RenderType renderType;
    private final float scale;
    private final boolean fullBright;

    public Entity2DRenderer(EntityRenderDispatcher renderManager, ResourceLocation texture, boolean fullBright) {
        this(renderManager, texture, 1F, fullBright);
    }

    public Entity2DRenderer(EntityRenderDispatcher renderManager, ResourceLocation texture, float scaleFactor, boolean fullBright) {
        super(renderManager);
        this.renderType = RenderType.entityCutoutNoCull(texture);
        this.scale = scaleFactor;
        this.fullBright = fullBright;
    }

    @Override //copypaste from DragonFireballRenderer except for custom scale
    public void render(@NotNull T entityIn, float entityYaw, float partialTicks, @NotNull PoseStack matrixStackIn, @NotNull MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        matrixStackIn.scale(scale, scale, scale);
        matrixStackIn.mulPose(this.entityRenderDispatcher.cameraOrientation());
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F));
        matrixStackIn.translate(0, 0.5D, 0);

        PoseStack.Pose lastMatrix = matrixStackIn.last();
        Matrix4f pose = lastMatrix.pose();
        Matrix3f normal = lastMatrix.normal();
        VertexConsumer ivertexbuilder = bufferIn.getBuffer(renderType);
        int packedLightLevel;
        if (fullBright) {
            packedLightLevel = 15728880;
        } else {
            packedLightLevel = packedLightIn;
        }
        vertex(ivertexbuilder, pose, normal, packedLightLevel, 0, 0, 0, 1);
        vertex(ivertexbuilder, pose, normal, packedLightLevel, 1, 0, 1, 1);
        vertex(ivertexbuilder, pose, normal, packedLightLevel, 1, 1, 1, 0);
        vertex(ivertexbuilder, pose, normal, packedLightLevel, 0, 1, 0, 0);

        matrixStackIn.popPose();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    //copypaste from DragonFireballRenderer
    private static void vertex(VertexConsumer builder, Matrix4f pose, Matrix3f normal, int lightmapUV, float x, float y, int u, int v) {
        builder.vertex(pose, x - 0.5F, y - 0.5F, 0.0F).color(255, 255, 255, 255).uv((float) u, (float) v).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(lightmapUV).normal(normal, 0.0F, 1.0F, 0.0F).endVertex();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Entity entityIn) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}