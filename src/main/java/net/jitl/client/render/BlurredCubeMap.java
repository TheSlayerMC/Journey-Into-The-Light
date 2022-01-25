package net.jitl.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BlurredCubeMap {
    private static final int SIDES = 6;
    private static float panoramaTimer;
    private static final ResourceLocation[] images = new ResourceLocation[6];
    private final ResourceLocation backgroundTexture;

    public BlurredCubeMap(ResourceLocation baseImageLocation_) {
        Minecraft mc = Minecraft.getInstance();
        DynamicTexture viewportTexture = new DynamicTexture(256, 256, true);
        backgroundTexture = mc.getTextureManager().register("background", viewportTexture);
        for (int i = 0; i < 6; ++i) {
            images[i] = new ResourceLocation(baseImageLocation_.getNamespace(), baseImageLocation_.getPath() + "_" + i + ".png");
        }
    }

    private static void drawPanorama(int mouseX, int mouseY, float partialTicks) {
        Minecraft mc = Minecraft.getInstance();
        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        Matrix4f matrix4f = Matrix4f.perspective(85.0D, (float) mc.getWindow().getWidth() / (float) mc.getWindow().getHeight(), 0.05F, 10.0F);
        RenderSystem.backupProjectionMatrix();
        RenderSystem.setProjectionMatrix(matrix4f);
        PoseStack posestack = RenderSystem.getModelViewStack();
        posestack.pushPose();
        posestack.setIdentity();
        posestack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
        RenderSystem.applyModelViewMatrix();
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        RenderSystem.disableCull();
        RenderSystem.depthMask(false);
        RenderSystem.defaultBlendFunc();

        for (int j = 0; j < 64; ++j) {
            posestack.pushPose();
            float f = ((float) (j % 8) / 8.0F - 0.5F) / 64.0F;
            float f1 = ((float) (j / 8) / 8.0F - 0.5F) / 64.0F;
            posestack.translate(f, f1, 0.0F);
            posestack.mulPose(Vector3f.XP.rotationDegrees(Mth.sin(panoramaTimer / 400.0F) * 25.0F + 20.0F));
            posestack.mulPose(Vector3f.YP.rotationDegrees(-panoramaTimer * 0.1F));

            for (int k = 0; k < 6; ++k) {
                posestack.pushPose();

                if (k == 1) {
                    posestack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
                }

                if (k == 2) {
                    posestack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
                }

                if (k == 3) {
                    posestack.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
                }

                if (k == 4) {
                    posestack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
                }

                if (k == 5) {
                    posestack.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
                }

                RenderSystem.setShaderTexture(0, images[k]);
                bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
                int l = 255 / (j + 1);
                bufferbuilder.vertex(-1.0D, -1.0D, 1.0D).uv(0.0F, 0.0F).color(255, 255, 255, l).endVertex();
                bufferbuilder.vertex(1.0D, -1.0D, 1.0D).uv(1.0F, 0.0F).color(255, 255, 255, l).endVertex();
                bufferbuilder.vertex(1.0D, 1.0D, 1.0D).uv(1.0F, 1.0F).color(255, 255, 255, l).endVertex();
                bufferbuilder.vertex(-1.0D, 1.0D, 1.0D).uv(0.0F, 1.0F).color(255, 255, 255, l).endVertex();
                tessellator.end();
                posestack.popPose();
            }
            posestack.popPose();
            RenderSystem.applyModelViewMatrix();
            RenderSystem.colorMask(true, true, true, false);
        }
        RenderSystem.colorMask(true, true, true, true);
        RenderSystem.restoreProjectionMatrix();
        posestack.popPose();
        RenderSystem.applyModelViewMatrix();
        RenderSystem.depthMask(true);
        RenderSystem.enableCull();
        RenderSystem.enableDepthTest();
    }

    private void rotateAndBlurSkybox() {
        Minecraft mc = Minecraft.getInstance();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, backgroundTexture);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.texParameter(3553, 10241, 9729);
        RenderSystem.texParameter(3553, 10240, 9729);
        GlStateManager._glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, 256, 256);
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
                GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
                GlStateManager.DestFactor.ZERO);
        RenderSystem.colorMask(true, true, true, false);
        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        for (int j = 0; j < 3; ++j) {
            float f = 1.0F / (float) (j + 1);
            assert mc.screen != null;
            int k = mc.screen.width;
            int l = mc.screen.height;
            float f1 = (float) (j - 1) / 256.0F;
            bufferbuilder.vertex(k, l, mc.screen.getBlitOffset()).uv(0.0F + f1, (float) 1.0D)
                    .color(1.0F, 1.0F, 1.0F, f).endVertex();
            bufferbuilder.vertex(k, 0.0D, mc.screen.getBlitOffset()).uv(1.0F + f1, (float) 1.0D)
                    .color(1.0F, 1.0F, 1.0F, f).endVertex();
            bufferbuilder.vertex(0.0D, 0.0D, mc.screen.getBlitOffset()).uv(1.0F + f1, (float) 0.0D)
                    .color(1.0F, 1.0F, 1.0F, f).endVertex();
            bufferbuilder.vertex(0.0D, l, mc.screen.getBlitOffset()).uv(0.0F + f1, (float) 0.0D)
                    .color(1.0F, 1.0F, 1.0F, f).endVertex();
        }
        tessellator.end();
        RenderSystem.colorMask(true, true, true, true);
    }

    public void renderSkybox(int mouseX, int mouseY, float partialTicks) {
        Minecraft mc = Minecraft.getInstance();
        mc.getMainRenderTarget().unbindWrite();
        panoramaTimer += partialTicks;
        RenderSystem.viewport(0, 0, 256, 256);
        drawPanorama(mouseX, mouseY, partialTicks);
        this.rotateAndBlurSkybox();
        this.rotateAndBlurSkybox();
        this.rotateAndBlurSkybox();
        this.rotateAndBlurSkybox();
        this.rotateAndBlurSkybox();
        this.rotateAndBlurSkybox();
        this.rotateAndBlurSkybox();
        mc.getMainRenderTarget().bindWrite(true);
        int width = mc.getWindow().getScreenWidth();
        int height = mc.getWindow().getScreenHeight();

        RenderSystem.viewport(0, 0, width, height);
        float f = 120.0F / (float) (Math.max(width, height));
        float f1 = (float) height * f / 256.0F;
        float f2 = (float) width * f / 256.0F;
        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
        assert mc.screen != null;
        bufferbuilder.vertex(0.0D, height, mc.screen.getBlitOffset()).uv(0.5F - f1, 0.5F + f2)
                .color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
        bufferbuilder.vertex(width, height, mc.screen.getBlitOffset()).uv(0.5F - f1, 0.5F - f2)
                .color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
        bufferbuilder.vertex(width, 0.0D, mc.screen.getBlitOffset()).uv(0.5F + f1, 0.5F - f2)
                .color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
        bufferbuilder.vertex(0.0D, 0.0D, mc.screen.getBlitOffset()).uv(0.5F + f1, 0.5F + f2)
                .color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
        tessellator.end();
    }
}