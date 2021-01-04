package net.jitl.client.render.entity.base;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import org.jetbrains.annotations.NotNull;

public class Entity2DRenderer<T extends Entity> extends EntityRenderer<T> {

    public ResourceLocation texture;
    private final float scale;

    public Entity2DRenderer(EntityRendererManager renderManager, ResourceLocation texture) {
        this(renderManager, texture, 1F);
    }

    public Entity2DRenderer(EntityRendererManager renderManager, ResourceLocation texture, float scaleFactor) {
        super(renderManager);
        this.texture = texture;
        scale = scaleFactor;
    }

    public void renderEntity(T projectile, @NotNull MatrixStack matrixStackIn, @NotNull IRenderTypeBuffer bufferIn) {
        if (projectile.tickCount >= 2 || !(this.entityRenderDispatcher.camera.getEntity().distanceToSqr(projectile) < 12.25D)) {
            matrixStackIn.pushPose();
            matrixStackIn.scale(scale * 0.5F, scale * 0.5F, scale * 0.5F);
            matrixStackIn.mulPose(this.entityRenderDispatcher.cameraOrientation());
            matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F));
            bufferIn.getBuffer(RenderType.cutout());
            bufferIn.getBuffer(RenderType.text(texture));
            matrixStackIn.popPose();
        }
    }

    @Override
    public void render(@NotNull T entityIn, float entityYaw, float partialTicks, @NotNull MatrixStack matrixStackIn, @NotNull IRenderTypeBuffer bufferIn, int packedLightIn) {
        this.renderEntity(entityIn, matrixStackIn, bufferIn);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Entity entityIn) {
        return texture;
    }
}