package net.jitl.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.jitl.JITL;
import net.minecraft.client.CloudStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.ICloudRenderHandler;

public class BoilCloudsRenderer implements ICloudRenderHandler {
    private CloudStatus prevCloudsType;
    private Vec3 prevCloudColor = Vec3.ZERO;
    private VertexBuffer cloudBuffer;
    private int prevCloudX = Integer.MIN_VALUE;
    private int prevCloudY = Integer.MIN_VALUE;
    private int prevCloudZ = Integer.MIN_VALUE;
    private boolean generateClouds = true;

    private static final ResourceLocation CLOUDS_LOCATION = JITL.rl("textures/environment/boil_clouds.png");

    @Override
    public void render(int ticks, float partialTicks, PoseStack matrixStackIn, ClientLevel level, Minecraft minecraft, double viewEntityX, double viewEntityY, double viewEntityZ) {
        //FIXME: remove this if/when forge adds Matrix4f parameter
        Matrix4f projectionMatrix = RenderSystem.getProjectionMatrix();

        float f = level.effects().getCloudHeight();
        if (!Float.isNaN(f)) {
            RenderSystem.disableCull();
            RenderSystem.enableBlend();
            RenderSystem.enableDepthTest();
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            RenderSystem.depthMask(true);
            double d1 = ((float) ticks + partialTicks) * 0.3F;
            double d2 = (viewEntityX + d1) / 12.0D;
            double d3 = f - (float) viewEntityY + 0.33F;
            double d4 = viewEntityZ / 12.0D + (double) 0.33F;
            d2 = d2 - (double) (Mth.floor(d2 / 2048.0D) * 2048);
            d4 = d4 - (double) (Mth.floor(d4 / 2048.0D) * 2048);
            float f3 = (float) (d2 - (double) Mth.floor(d2));
            float f4 = (float) (d3 / 4.0D - (double) Mth.floor(d3 / 4.0D)) * 4.0F;
            float f5 = (float) (d4 - (double) Mth.floor(d4));
            Vec3 vector3d = level.getCloudColor(partialTicks);
            int i = (int) Math.floor(d2);
            int j = (int) Math.floor(d3 / 4.0D);
            int k = (int) Math.floor(d4);
            if (i != this.prevCloudX || j != this.prevCloudY || k != this.prevCloudZ || minecraft.options.getCloudsType() != this.prevCloudsType || this.prevCloudColor.distanceToSqr(vector3d) > 2.0E-4D) {
                this.prevCloudX = i;
                this.prevCloudY = j;
                this.prevCloudZ = k;
                prevCloudColor = vector3d;
                prevCloudsType = minecraft.options.getCloudsType();
                this.generateClouds = true;
            }

            if (this.generateClouds) {
                this.generateClouds = false;
                BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
                if (this.cloudBuffer != null) {
                    this.cloudBuffer.close();
                }

                this.cloudBuffer = new VertexBuffer();
                this.buildClouds(bufferbuilder, d2, d3, d4, vector3d);
                bufferbuilder.end();
                this.cloudBuffer.upload(bufferbuilder);
            }
            RenderSystem.setShader(GameRenderer::getPositionTexColorNormalShader);
            RenderSystem.setShaderTexture(0, CLOUDS_LOCATION);
            FogRenderer.levelFogColor();

            matrixStackIn.pushPose();
            matrixStackIn.scale(12.0F, 1.0F, 12.0F);
            matrixStackIn.translate(-f3, f4, -f5);
            if (this.cloudBuffer != null) {
                int i1 = prevCloudsType == CloudStatus.FANCY ? 0 : 1;

                for (int l = i1; l < 2; ++l) {
                    if (l == 0) {
                        RenderSystem.colorMask(false, false, false, false);
                    } else {
                        RenderSystem.colorMask(true, true, true, true);
                    }

                    ShaderInstance shaderinstance = RenderSystem.getShader();
                    this.cloudBuffer.drawWithShader(matrixStackIn.last().pose(), projectionMatrix, shaderinstance);
                }

                VertexBuffer.unbind();
                DefaultVertexFormat.POSITION_TEX_COLOR_NORMAL.clearBufferState();
            }

            matrixStackIn.popPose();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.enableCull();
            RenderSystem.disableBlend();
        }
    }

    private void buildClouds(BufferBuilder bufferIn, double cloudsX, double cloudsY, double cloudsZ, Vec3 cloudsColor) {
        float f3 = (float) Mth.floor(cloudsX) * 0.00390625F;
        float f4 = (float) Mth.floor(cloudsZ) * 0.00390625F;
        float f5 = (float) cloudsColor.x;
        float f6 = (float) cloudsColor.y;
        float f7 = (float) cloudsColor.z;
        float f8 = f5 * 0.9F;
        float f9 = f6 * 0.9F;
        float f10 = f7 * 0.9F;
        float f11 = f5 * 0.7F;
        float f12 = f6 * 0.7F;
        float f13 = f7 * 0.7F;
        float f14 = f5 * 0.8F;
        float f15 = f6 * 0.8F;
        float f16 = f7 * 0.8F;
        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalShader);
        bufferIn.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR_NORMAL);
        float f17 = (float) Math.floor(cloudsY / 4.0D) * 4.0F;
        if (prevCloudsType == CloudStatus.FANCY) {
            for (int k = -3; k <= 4; ++k) {
                for (int l = -3; l <= 4; ++l) {
                    float f18 = (float) (k * 8);
                    float f19 = (float) (l * 8);
                    if (f17 > -5.0F) {
                        bufferIn.vertex((f18 + 0.0F), (f17 + 0.0F), (f19 + 8.0F)).uv((f18 + 0.0F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4).color(f11, f12, f13, 0.8F).normal(0.0F, -1.0F, 0.0F).endVertex();
                        bufferIn.vertex((f18 + 8.0F), (f17 + 0.0F), (f19 + 8.0F)).uv((f18 + 8.0F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4).color(f11, f12, f13, 0.8F).normal(0.0F, -1.0F, 0.0F).endVertex();
                        bufferIn.vertex((f18 + 8.0F), (f17 + 0.0F), (f19 + 0.0F)).uv((f18 + 8.0F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4).color(f11, f12, f13, 0.8F).normal(0.0F, -1.0F, 0.0F).endVertex();
                        bufferIn.vertex((f18 + 0.0F), (f17 + 0.0F), (f19 + 0.0F)).uv((f18 + 0.0F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4).color(f11, f12, f13, 0.8F).normal(0.0F, -1.0F, 0.0F).endVertex();
                    }

                    if (f17 <= 5.0F) {
                        bufferIn.vertex((f18 + 0.0F), (f17 + 4.0F - 9.765625E-4F), (f19 + 8.0F)).uv((f18 + 0.0F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4).color(f5, f6, f7, 0.8F).normal(0.0F, 1.0F, 0.0F).endVertex();
                        bufferIn.vertex((f18 + 8.0F), (f17 + 4.0F - 9.765625E-4F), (f19 + 8.0F)).uv((f18 + 8.0F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4).color(f5, f6, f7, 0.8F).normal(0.0F, 1.0F, 0.0F).endVertex();
                        bufferIn.vertex((f18 + 8.0F), (f17 + 4.0F - 9.765625E-4F), (f19 + 0.0F)).uv((f18 + 8.0F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4).color(f5, f6, f7, 0.8F).normal(0.0F, 1.0F, 0.0F).endVertex();
                        bufferIn.vertex((f18 + 0.0F), (f17 + 4.0F - 9.765625E-4F), (f19 + 0.0F)).uv((f18 + 0.0F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4).color(f5, f6, f7, 0.8F).normal(0.0F, 1.0F, 0.0F).endVertex();
                    }

                    if (k > -1) {
                        for (int i1 = 0; i1 < 8; ++i1) {
                            bufferIn.vertex((f18 + (float) i1 + 0.0F), (f17 + 0.0F), (f19 + 8.0F)).uv((f18 + (float) i1 + 0.5F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4).color(f8, f9, f10, 0.8F).normal(-1.0F, 0.0F, 0.0F).endVertex();
                            bufferIn.vertex((f18 + (float) i1 + 0.0F), (f17 + 4.0F), (f19 + 8.0F)).uv((f18 + (float) i1 + 0.5F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4).color(f8, f9, f10, 0.8F).normal(-1.0F, 0.0F, 0.0F).endVertex();
                            bufferIn.vertex((f18 + (float) i1 + 0.0F), (f17 + 4.0F), (f19 + 0.0F)).uv((f18 + (float) i1 + 0.5F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4).color(f8, f9, f10, 0.8F).normal(-1.0F, 0.0F, 0.0F).endVertex();
                            bufferIn.vertex((f18 + (float) i1 + 0.0F), (f17 + 0.0F), (f19 + 0.0F)).uv((f18 + (float) i1 + 0.5F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4).color(f8, f9, f10, 0.8F).normal(-1.0F, 0.0F, 0.0F).endVertex();
                        }
                    }

                    if (k <= 1) {
                        for (int j2 = 0; j2 < 8; ++j2) {
                            bufferIn.vertex((f18 + (float) j2 + 1.0F - 9.765625E-4F), (f17 + 0.0F), (f19 + 8.0F)).uv((f18 + (float) j2 + 0.5F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4).color(f8, f9, f10, 0.8F).normal(1.0F, 0.0F, 0.0F).endVertex();
                            bufferIn.vertex((f18 + (float) j2 + 1.0F - 9.765625E-4F), (f17 + 4.0F), (f19 + 8.0F)).uv((f18 + (float) j2 + 0.5F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4).color(f8, f9, f10, 0.8F).normal(1.0F, 0.0F, 0.0F).endVertex();
                            bufferIn.vertex((f18 + (float) j2 + 1.0F - 9.765625E-4F), (f17 + 4.0F), (f19 + 0.0F)).uv((f18 + (float) j2 + 0.5F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4).color(f8, f9, f10, 0.8F).normal(1.0F, 0.0F, 0.0F).endVertex();
                            bufferIn.vertex((f18 + (float) j2 + 1.0F - 9.765625E-4F), (f17 + 0.0F), (f19 + 0.0F)).uv((f18 + (float) j2 + 0.5F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4).color(f8, f9, f10, 0.8F).normal(1.0F, 0.0F, 0.0F).endVertex();
                        }
                    }

                    if (l > -1) {
                        for (int k2 = 0; k2 < 8; ++k2) {
                            bufferIn.vertex((f18 + 0.0F), (f17 + 4.0F), (f19 + (float) k2 + 0.0F)).uv((f18 + 0.0F) * 0.00390625F + f3, (f19 + (float) k2 + 0.5F) * 0.00390625F + f4).color(f14, f15, f16, 0.8F).normal(0.0F, 0.0F, -1.0F).endVertex();
                            bufferIn.vertex((f18 + 8.0F), (f17 + 4.0F), (f19 + (float) k2 + 0.0F)).uv((f18 + 8.0F) * 0.00390625F + f3, (f19 + (float) k2 + 0.5F) * 0.00390625F + f4).color(f14, f15, f16, 0.8F).normal(0.0F, 0.0F, -1.0F).endVertex();
                            bufferIn.vertex((f18 + 8.0F), (f17 + 0.0F), (f19 + (float) k2 + 0.0F)).uv((f18 + 8.0F) * 0.00390625F + f3, (f19 + (float) k2 + 0.5F) * 0.00390625F + f4).color(f14, f15, f16, 0.8F).normal(0.0F, 0.0F, -1.0F).endVertex();
                            bufferIn.vertex((f18 + 0.0F), (f17 + 0.0F), (f19 + (float) k2 + 0.0F)).uv((f18 + 0.0F) * 0.00390625F + f3, (f19 + (float) k2 + 0.5F) * 0.00390625F + f4).color(f14, f15, f16, 0.8F).normal(0.0F, 0.0F, -1.0F).endVertex();
                        }
                    }

                    if (l <= 1) {
                        for (int l2 = 0; l2 < 8; ++l2) {
                            bufferIn.vertex((f18 + 0.0F), (f17 + 4.0F), (f19 + (float) l2 + 1.0F - 9.765625E-4F)).uv((f18 + 0.0F) * 0.00390625F + f3, (f19 + (float) l2 + 0.5F) * 0.00390625F + f4).color(f14, f15, f16, 0.8F).normal(0.0F, 0.0F, 1.0F).endVertex();
                            bufferIn.vertex((f18 + 8.0F), (f17 + 4.0F), (f19 + (float) l2 + 1.0F - 9.765625E-4F)).uv((f18 + 8.0F) * 0.00390625F + f3, (f19 + (float) l2 + 0.5F) * 0.00390625F + f4).color(f14, f15, f16, 0.8F).normal(0.0F, 0.0F, 1.0F).endVertex();
                            bufferIn.vertex((f18 + 8.0F), (f17 + 0.0F), (f19 + (float) l2 + 1.0F - 9.765625E-4F)).uv((f18 + 8.0F) * 0.00390625F + f3, (f19 + (float) l2 + 0.5F) * 0.00390625F + f4).color(f14, f15, f16, 0.8F).normal(0.0F, 0.0F, 1.0F).endVertex();
                            bufferIn.vertex((f18 + 0.0F), (f17 + 0.0F), (f19 + (float) l2 + 1.0F - 9.765625E-4F)).uv((f18 + 0.0F) * 0.00390625F + f3, (f19 + (float) l2 + 0.5F) * 0.00390625F + f4).color(f14, f15, f16, 0.8F).normal(0.0F, 0.0F, 1.0F).endVertex();
                        }
                    }
                }
            }
        } else {
            for (int l1 = -32; l1 < 32; l1 += 32) {
                for (int i2 = -32; i2 < 32; i2 += 32) {
                    bufferIn.vertex((l1), f17, (i2 + 32)).uv((float) (l1) * 0.00390625F + f3, (float) (i2 + 32) * 0.00390625F + f4).color(f5, f6, f7, 0.8F).normal(0.0F, -1.0F, 0.0F).endVertex();
                    bufferIn.vertex((l1 + 32), f17, (i2 + 32)).uv((float) (l1 + 32) * 0.00390625F + f3, (float) (i2 + 32) * 0.00390625F + f4).color(f5, f6, f7, 0.8F).normal(0.0F, -1.0F, 0.0F).endVertex();
                    bufferIn.vertex((l1 + 32), f17, (i2)).uv((float) (l1 + 32) * 0.00390625F + f3, (float) (i2 + 0) * 0.00390625F + f4).color(f5, f6, f7, 0.8F).normal(0.0F, -1.0F, 0.0F).endVertex();
                    bufferIn.vertex((l1), f17, (i2)).uv((float) (l1 + 0) * 0.00390625F + f3, (float) (i2 + 0) * 0.00390625F + f4).color(f5, f6, f7, 0.8F).normal(0.0F, -1.0F, 0.0F).endVertex();
                }
            }
        }

    }
}
