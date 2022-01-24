package net.jitl.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ISkyRenderHandler;

@OnlyIn(Dist.CLIENT)
public class FrozenSkyRenderer implements ISkyRenderHandler {
    private final TextureManager textureManager;

    private final VertexFormat skyFormat = DefaultVertexFormat.POSITION;
    private VertexBuffer skyBuffer;

    public FrozenSkyRenderer() {
        textureManager = Minecraft.getInstance().textureManager;
        createLightSky();
    }

    @Override
    public void render(int ticks, float partialTicks, PoseStack matrixStack, ClientLevel world, Minecraft mc) {
        Matrix4f projectionMatrix = RenderSystem.getProjectionMatrix();
        RenderSystem.disableTexture();
        Vec3 worldSkyColor = world.getSkyColor(Minecraft.getInstance().gameRenderer.getMainCamera().getPosition(), partialTicks);
        float x = (float) worldSkyColor.x;
        float y = (float) worldSkyColor.y;
        float z = (float) worldSkyColor.z;
        FogRenderer.levelFogColor();
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        RenderSystem.depthMask(false);
        FogRenderer.levelFogColor();
        RenderSystem.setShaderColor(x, y, z, 1.0F);
        ShaderInstance shaderinstance = RenderSystem.getShader();
        this.skyBuffer.drawWithShader(matrixStack.last().pose(), projectionMatrix, shaderinstance);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        float[] sunRiseRGBA = world.effects().getSunriseColor(world.getTimeOfDay(partialTicks), partialTicks);
        if (sunRiseRGBA != null) {
            RenderSystem.setShader(GameRenderer::getPositionColorShader);
            RenderSystem.disableTexture();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            matrixStack.pushPose();
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
            float celestialAngle = Mth.sin(world.getSunAngle(partialTicks)) < 0.0F ? 180.0F : 0.0F;
            matrixStack.mulPose(Vector3f.ZP.rotationDegrees(celestialAngle));
            matrixStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
            float sunRed = sunRiseRGBA[0];
            float sunGreen = sunRiseRGBA[1];
            float sunBlue = sunRiseRGBA[2];
            Matrix4f matrix4f = matrixStack.last().pose();
            bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
            bufferbuilder.vertex(matrix4f, 0.0F, 100.0F, 0.0F).color(sunRed, sunGreen, sunBlue, sunRiseRGBA[3]).endVertex();
            int i = 16;

            for (int j = 0; j <= 16; ++j) {
                float f7 = (float) j * ((float) Math.PI * 2F) / 16.0F;
                float f8 = Mth.sin(f7);
                float f9 = Mth.cos(f7);
                bufferbuilder.vertex(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * sunRiseRGBA[3]).color(sunRiseRGBA[0], sunRiseRGBA[1], sunRiseRGBA[2], 0.0F).endVertex();
            }

            bufferbuilder.end();
            BufferUploader.end(bufferbuilder);
            matrixStack.popPose();
        }

        if (world.effects().hasGround()) {
            RenderSystem.setShaderColor(x * 0.2F + 0.04F, y * 0.2F + 0.04F, z * 0.6F + 0.1F, 1.0F);
        } else {
            RenderSystem.setShaderColor(x, y, z, 1.0F);
        }

        RenderSystem.enableTexture();
        RenderSystem.depthMask(true);
    }

    private void createLightSky() {
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();
        if (this.skyBuffer != null) {
            this.skyBuffer.close();
        }
        this.skyBuffer = new VertexBuffer();

        drawSkyHemisphere(bufferbuilder, 16.0F);
        bufferbuilder.end();
        this.skyBuffer.upload(bufferbuilder);
    }

    private void drawSkyHemisphere(BufferBuilder bufferBuilder, float pY) {
        float f = Math.signum(pY) * 512.0F;
        RenderSystem.setShader(GameRenderer::getPositionShader);
        bufferBuilder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION);
        bufferBuilder.vertex(0.0D, pY, 0.0D).endVertex();

        for (int i = -180; i <= 180; i += 45) {
            bufferBuilder.vertex(
                    f * Mth.cos((float) i * ((float) Math.PI / 180F)),
                    pY,
                    512.0F * Mth.sin((float) i * ((float) Math.PI / 180F))).endVertex();
        }
    }
}