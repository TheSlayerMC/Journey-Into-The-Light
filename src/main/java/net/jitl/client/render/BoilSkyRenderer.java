package net.jitl.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.jitl.JITL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ISkyRenderHandler;

@OnlyIn(Dist.CLIENT)
public class BoilSkyRenderer implements ISkyRenderHandler {

    private final TextureManager textureManager;

    private static final ResourceLocation SUN_LOCATION = JITL.rl("textures/environment/boil_sun.png");
    private static final ResourceLocation EUCA_MOON_LOCATION = JITL.rl("textures/environment/euca_moon.png");
    private static final ResourceLocation CORBA_MOON_LOCATION = JITL.rl("textures/environment/corba_moon.png");

    private static final ResourceLocation SKY_LOCATION = JITL.rl("textures/environment/boil_sky.png");

    private final VertexFormat skyFormat = DefaultVertexFormat.POSITION;
    private VertexBuffer skyBuffer;

    public BoilSkyRenderer() {
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
            bufferbuilder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
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
        RenderSystem.enableTexture();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

        matrixStack.pushPose();
        /**
         * This code determines the current angle of the sun and moon and determines whether they should be visible or not.
         */
        float timeFactor = world.getDayTime() % 72000;
        float sunOpacity = 1.0F;
        if (timeFactor > 71400) {
            timeFactor -= 71400;
            sunOpacity = timeFactor * 0.005F;
        } else if (timeFactor > 38400) {
            timeFactor -= 38400;
            sunOpacity = 1.0F - timeFactor * 0.005F;
        }

        renderSky(matrixStack);

        Matrix4f matrix4f1 = matrixStack.last().pose();

        //SUN BEGIN
        float f12;
        f12 = 60.0F;

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, sunOpacity);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(world.getTimeOfDay(partialTicks) * 360.0F));


        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, SUN_LOCATION);
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, -f12).uv(0.0F, 0.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, 100.0F, -f12).uv(1.0F, 0.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, 100.0F, f12).uv(1.0F, 1.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, f12).uv(0.0F, 1.0F).endVertex();
        bufferbuilder.end();
        BufferUploader.end(bufferbuilder);

        //SUN END

        //EUCA MOON BEGIN
        f12 = 2F;

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, sunOpacity);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(45.0F));
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(world.getTimeOfDay(partialTicks) * 120.0F));

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, EUCA_MOON_LOCATION);
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, -f12).uv(0.0F, 0.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, 100.0F, -f12).uv(1.0F, 0.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, 100.0F, f12).uv(1.0F, 1.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, f12).uv(0.0F, 1.0F).endVertex();
        bufferbuilder.end();
        BufferUploader.end(bufferbuilder);
        //EUCA MOON END

        //CORBA MOON BEGIN
        f12 = 0.5F;

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, sunOpacity);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(45.0F));
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(world.getTimeOfDay(partialTicks) * 360.0F));

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, CORBA_MOON_LOCATION);
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, -f12).uv(0.0F, 0.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, 100.0F, -f12).uv(1.0F, 0.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, 100.0F, f12).uv(1.0F, 1.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, f12).uv(0.0F, 1.0F).endVertex();
        bufferbuilder.end();
        BufferUploader.end(bufferbuilder);
        //CORBA MOON END

        RenderSystem.disableTexture();

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
        matrixStack.popPose();
        RenderSystem.disableTexture();
        RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);

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

    private void renderSky(PoseStack matrixStackIn) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(false);
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture(0, SKY_LOCATION);

        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();

        for (int i = 0; i < 6; ++i) {
            matrixStackIn.pushPose();
            if (i == 1) {
                matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(90.0F));
            }

            if (i == 2) {
                matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
            }

            if (i == 3) {
                matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(180.0F));
            }

            if (i == 4) {
                matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
            }

            if (i == 5) {
                matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(-90.0F));
            }

            Matrix4f matrix4f = matrixStackIn.last().pose();
            bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
            bufferbuilder.vertex(matrix4f, -100.0F, -100.0F, -100.0F).uv(0.0F, 0.0F).color(40, 40, 40, 255).endVertex();
            bufferbuilder.vertex(matrix4f, -100.0F, -100.0F, 100.0F).uv(0.0F, 16.0F).color(40, 40, 40, 255).endVertex();
            bufferbuilder.vertex(matrix4f, 100.0F, -100.0F, 100.0F).uv(16.0F, 16.0F).color(40, 40, 40, 255).endVertex();
            bufferbuilder.vertex(matrix4f, 100.0F, -100.0F, -100.0F).uv(16.0F, 0.0F).color(40, 40, 40, 255).endVertex();
            tessellator.end();
            matrixStackIn.popPose();
        }

        RenderSystem.depthMask(true);
        RenderSystem.enableTexture();

        RenderSystem.disableBlend();
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