package net.jitl.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.JITL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
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

    //private static final ResourceLocation CLOUDS_LOCATION = JITL.rl("textures/environment/boil_clouds.png");

    private final VertexFormat skyFormat = DefaultVertexFormats.POSITION;
    private VertexBuffer skyBuffer;

    public BoilSkyRenderer() {
        textureManager = Minecraft.getInstance().textureManager;
        createLightSky();
    }

    @Override
    public void render(int ticks, float partialTicks, MatrixStack matrixStack, ClientWorld world, Minecraft mc) {
        RenderSystem.disableTexture();
        Vector3d worldSkyColor = world.getSkyColor(Minecraft.getInstance().gameRenderer.getMainCamera().getBlockPosition(), partialTicks);
        float x = (float) worldSkyColor.x;
        float y = (float) worldSkyColor.y;
        float z = (float) worldSkyColor.z;
        FogRenderer.levelFogColor();
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuilder();
        RenderSystem.depthMask(false);
        RenderSystem.enableFog();
        RenderSystem.color3f(x, y, z);
        this.skyBuffer.bind();
        this.skyFormat.setupBufferState(0L);
        this.skyBuffer.draw(matrixStack.last().pose(), 7);
        VertexBuffer.unbind();
        this.skyFormat.clearBufferState();
        RenderSystem.disableFog();
        RenderSystem.disableAlphaTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        float[] sunRiseRGBA = world.effects().getSunriseColor(world.getTimeOfDay(partialTicks), partialTicks);
        if (sunRiseRGBA != null) {
            RenderSystem.disableTexture();
            RenderSystem.shadeModel(7425);
            matrixStack.pushPose();
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
            float celestialAngle = MathHelper.sin(world.getSunAngle(partialTicks)) < 0.0F ? 180.0F : 0.0F;
            matrixStack.mulPose(Vector3f.ZP.rotationDegrees(celestialAngle));
            matrixStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
            float sunRed = sunRiseRGBA[0];
            float sunGreen = sunRiseRGBA[1];
            float sunBlue = sunRiseRGBA[2];
            Matrix4f matrix4f = matrixStack.last().pose();
            bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
            bufferbuilder.vertex(matrix4f, 0.0F, 100.0F, 0.0F).color(sunRed, sunGreen, sunBlue, sunRiseRGBA[3]).endVertex();
            int i = 16;

            for (int j = 0; j <= 16; ++j) {
                float f7 = (float) j * ((float) Math.PI * 2F) / 16.0F;
                float f8 = MathHelper.sin(f7);
                float f9 = MathHelper.cos(f7);
                bufferbuilder.vertex(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * sunRiseRGBA[3]).color(sunRiseRGBA[0], sunRiseRGBA[1], sunRiseRGBA[2], 0.0F).endVertex();
            }

            bufferbuilder.end();
            WorldVertexBufferUploader.end(bufferbuilder);
            matrixStack.popPose();
            RenderSystem.shadeModel(7424);
        }
        RenderSystem.enableTexture();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

        matrixStack.pushPose();
        /**
         * This code determines the current angle of the sun and moon and determines whether they should be visible or not.
         */
        float timeFactor = world.getDayTime() % 72000;
        float sunOpacity = 1.0F;
        float moonOpacity = 0.0F;
        if (timeFactor > 71400) {
            timeFactor -= 71400;
            sunOpacity = timeFactor * 0.005F;
            moonOpacity = 1.0F - timeFactor * 0.005F;
        } else if (timeFactor > 38400) {
            timeFactor -= 38400;
            sunOpacity = 1.0F - timeFactor * 0.005F;
            moonOpacity = timeFactor * 0.005F;
        }

        renderSky(matrixStack);

        Matrix4f matrix4f1 = matrixStack.last().pose();
        float f12;
        f12 = 30.0F;

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, sunOpacity);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(world.getTimeOfDay(partialTicks) * 360.0F));

        //SUN BEGIN
        textureManager.bind(SUN_LOCATION);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, -f12).uv(0.0F, 0.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, 100.0F, -f12).uv(1.0F, 0.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, 100.0F, f12).uv(1.0F, 1.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, f12).uv(0.0F, 1.0F).endVertex();
        bufferbuilder.end();
        WorldVertexBufferUploader.end(bufferbuilder);

        //SUN END

        //EUCA MOON BEGIN
        f12 = 2F;

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, sunOpacity);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(45.0F));
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(world.getTimeOfDay(partialTicks) * 120.0F));

        textureManager.bind(EUCA_MOON_LOCATION);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, -f12).uv(0.0F, 0.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, 100.0F, -f12).uv(1.0F, 0.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, 100.0F, f12).uv(1.0F, 1.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, f12).uv(0.0F, 1.0F).endVertex();
        bufferbuilder.end();
        WorldVertexBufferUploader.end(bufferbuilder);
        //EUCA MOON END

        //CORBA MOON BEGIN
        f12 = 0.5F;

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, sunOpacity);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(45.0F));
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(world.getTimeOfDay(partialTicks) * 360.0F));

        textureManager.bind(CORBA_MOON_LOCATION);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, -f12).uv(0.0F, 0.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, 100.0F, -f12).uv(1.0F, 0.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, 100.0F, f12).uv(1.0F, 1.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, f12).uv(0.0F, 1.0F).endVertex();
        bufferbuilder.end();
        WorldVertexBufferUploader.end(bufferbuilder);
        //CORBA MOON END

        RenderSystem.disableTexture();

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableFog();
        matrixStack.popPose();
        RenderSystem.disableTexture();
        RenderSystem.color3f(0.0F, 0.0F, 0.0F);

        if (world.effects().hasGround()) {
            RenderSystem.color3f(x * 0.2F + 0.04F, y * 0.2F + 0.04F, z * 0.6F + 0.1F);
        } else {
            RenderSystem.color3f(x, y, z);
        }

        RenderSystem.enableTexture();
        RenderSystem.depthMask(true);
        RenderSystem.disableFog();
    }

    private void createLightSky() {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        if (this.skyBuffer != null) {
            this.skyBuffer.close();
        }

        this.skyBuffer = new VertexBuffer(this.skyFormat);
        this.drawSkyHemisphere(bufferbuilder, 16.0F, false);
        bufferbuilder.end();
        this.skyBuffer.upload(bufferbuilder);
    }

    private void renderSky(MatrixStack matrixStackIn) {
        RenderSystem.disableAlphaTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(false);
        this.textureManager.bind(SKY_LOCATION);
        Tessellator tessellator = Tessellator.getInstance();
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
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
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
        RenderSystem.enableAlphaTest();
    }

    private void drawSkyHemisphere(BufferBuilder p_174968_1_, float p_174968_2_, boolean p_174968_3_) {
        int i = 64;
        int j = 6;
        p_174968_1_.begin(7, DefaultVertexFormats.POSITION);

        for (int k = -384; k <= 384; k += 64) {
            for (int l = -384; l <= 384; l += 64) {
                float f = (float) k;
                float f1 = (float) (k + 64);
                if (p_174968_3_) {
                    f1 = (float) k;
                    f = (float) (k + 64);
                }

                p_174968_1_.vertex(f, p_174968_2_, l).endVertex();
                p_174968_1_.vertex(f1, p_174968_2_, l).endVertex();
                p_174968_1_.vertex(f1, p_174968_2_, l + 64).endVertex();
                p_174968_1_.vertex(f, p_174968_2_, l + 64).endVertex();
            }
        }

    }
}