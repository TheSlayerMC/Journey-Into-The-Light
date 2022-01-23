package net.jitl.client.util;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;

import java.util.function.BiConsumer;

/**
 * Copy of {@link net.minecraft.client.gui.GuiComponent} with static context
 */

//TODO: merge with JRenderUtils (or vice-versa)
public class RenderUtils {

    public static void drawCenteredStringWithCustomScale(PoseStack poseStack, Font fontRendererIn, String text, int x, int y, int zLevel, int color, float scaleFactor, int availableHeight, boolean hasShadow) {
        poseStack.pushPose();
        poseStack.translate(x - fontRendererIn.width(text) / 2 * scaleFactor, y + (availableHeight / 2) + (fontRendererIn.lineHeight * scaleFactor > 1 ? -1 * fontRendererIn.lineHeight * scaleFactor : fontRendererIn.lineHeight * scaleFactor) * 0.5, zLevel);
        poseStack.scale(scaleFactor, scaleFactor, 1);
        if (hasShadow) {
            fontRendererIn.drawShadow(poseStack, text, 0, 0, color);
        } else {
            fontRendererIn.draw(poseStack, text, 0, 0, color);
        }
        poseStack.popPose();
    }

    public static void rectangle(PoseStack poseStack, Rectangle rectangle, int argbColor) {
        fill(poseStack, rectangle.left(), rectangle.top(), rectangle.right(), rectangle.bottom(), argbColor);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static void fill(PoseStack matrixStack, int minX, int minY, int maxX, int maxY, int colorIn) {
        innerFill(matrixStack.last().pose(), minX, minY, maxX, maxY, colorIn);
    }

    private static void innerFill(Matrix4f matrix, int minX, int minY, int maxX, int maxY, int colorIn) {
        if (minX < maxX) {
            int i = minX;
            minX = maxX;
            maxX = i;
        }

        if (minY < maxY) {
            int j = minY;
            minY = maxY;
            maxY = j;
        }

        float f3 = (float) (colorIn >> 24 & 255) / 255.0F;
        float f = (float) (colorIn >> 16 & 255) / 255.0F;
        float f1 = (float) (colorIn >> 8 & 255) / 255.0F;
        float f2 = (float) (colorIn & 255) / 255.0F;
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        bufferbuilder.vertex(matrix, (float) minX, (float) maxY, 0.0F).color(f, f1, f2, f3).endVertex();
        bufferbuilder.vertex(matrix, (float) maxX, (float) maxY, 0.0F).color(f, f1, f2, f3).endVertex();
        bufferbuilder.vertex(matrix, (float) maxX, (float) minY, 0.0F).color(f, f1, f2, f3).endVertex();
        bufferbuilder.vertex(matrix, (float) minX, (float) minY, 0.0F).color(f, f1, f2, f3).endVertex();
        bufferbuilder.end();
        BufferUploader.end(bufferbuilder);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public void fillGradient(PoseStack matrixStack, int x1, int y1, int x2, int y2, int colorFrom, int colorTo, int blitOffset) {
        RenderSystem.disableTexture();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        fillGradient(matrixStack.last().pose(), bufferbuilder, x1, y1, x2, y2, blitOffset, colorFrom, colorTo);
        tessellator.end();
        RenderSystem.disableBlend();
        RenderSystem.enableTexture();
    }

    public static void fillGradient(Matrix4f matrix, BufferBuilder builder, int x1, int y1, int x2, int y2, int z, int colorA, int colorB) {
        float f = (float) (colorA >> 24 & 255) / 255.0F;
        float f1 = (float) (colorA >> 16 & 255) / 255.0F;
        float f2 = (float) (colorA >> 8 & 255) / 255.0F;
        float f3 = (float) (colorA & 255) / 255.0F;
        float f4 = (float) (colorB >> 24 & 255) / 255.0F;
        float f5 = (float) (colorB >> 16 & 255) / 255.0F;
        float f6 = (float) (colorB >> 8 & 255) / 255.0F;
        float f7 = (float) (colorB & 255) / 255.0F;
        builder.vertex(matrix, (float) x2, (float) y1, (float) z).color(f1, f2, f3, f).endVertex();
        builder.vertex(matrix, (float) x1, (float) y1, (float) z).color(f1, f2, f3, f).endVertex();
        builder.vertex(matrix, (float) x1, (float) y2, (float) z).color(f5, f6, f7, f4).endVertex();
        builder.vertex(matrix, (float) x2, (float) y2, (float) z).color(f5, f6, f7, f4).endVertex();
    }

    public static void drawCenteredString(PoseStack matrixStack, Font fontRenderer, String fontIn, float x, float y, int red, int green, int blue, int alpha) {
        int color = Math.max(4, alpha) << 24 | red << 16 | green << 8 | blue;
        fontRenderer.drawShadow(matrixStack, fontIn, x - fontRenderer.width(fontIn) / 2F, y, color);
    }

    public static void drawCenteredString(PoseStack matrixStack, Font fontRenderer, Component fontIn, float x, float y, int red, int green, int blue, int alpha) {
        FormattedCharSequence ireorderingprocessor = fontIn.getVisualOrderText();
        int color = Math.max(4, alpha) << 24 | red << 16 | green << 8 | blue;
        fontRenderer.drawShadow(matrixStack, ireorderingprocessor, x - fontRenderer.width(ireorderingprocessor) / 2F, y, color);
    }

    public static void blitOutlineBlack(int width, int height, BiConsumer<Integer, Integer> boxXYConsumer) {
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        boxXYConsumer.accept(width + 1, height);
        boxXYConsumer.accept(width - 1, height);
        boxXYConsumer.accept(width, height + 1);
        boxXYConsumer.accept(width, height - 1);
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        boxXYConsumer.accept(width, height);
    }

    public static void blit(PoseStack matrixStack, int x, int y, int blitOffset, int width, int height, TextureAtlasSprite sprite) {
        innerBlit(matrixStack.last().pose(), x, x + width, y, y + height, blitOffset, sprite.getU0(), sprite.getU1(), sprite.getV0(), sprite.getV1());
    }

    public static void blit(PoseStack matrixStack, int x, int y, int uOffset, int vOffset, int uWidth, int vHeight, int blitOffset) {
        blit(matrixStack, x, y, blitOffset, (float) uOffset, (float) vOffset, uWidth, vHeight, 256, 256);
    }

    public static void blit(PoseStack matrixStack, int x, int y, int blitOffset, float uOffset, float vOffset, int uWidth, int vHeight, int textureHeight, int textureWidth) {
        innerBlit(matrixStack, x, x + uWidth, y, y + vHeight, blitOffset, uWidth, vHeight, uOffset, vOffset, textureWidth, textureHeight);
    }

    public static void blit(PoseStack matrixStack, int x, int y, int width, int height, float uOffset, float vOffset, int uWidth, int vHeight, int textureWidth, int textureHeight) {
        innerBlit(matrixStack, x, x + width, y, y + height, 0, uWidth, vHeight, uOffset, vOffset, textureWidth, textureHeight);
    }

    public static void blit(PoseStack matrixStack, int x, int y, float uOffset, float vOffset, int width, int height, int textureWidth, int textureHeight) {
        blit(matrixStack, x, y, width, height, uOffset, vOffset, width, height, textureWidth, textureHeight);
    }

    private static void innerBlit(PoseStack matrixStack, int x1, int x2, int y1, int y2, int blitOffset, int uWidth, int vHeight, float uOffset, float vOffset, int textureWidth, int textureHeight) {
        innerBlit(matrixStack.last().pose(), x1, x2, y1, y2, blitOffset, (uOffset + 0.0F) / (float) textureWidth, (uOffset + (float) uWidth) / (float) textureWidth, (vOffset + 0.0F) / (float) textureHeight, (vOffset + (float) vHeight) / (float) textureHeight);
    }

    private static void innerBlit(Matrix4f matrix, int x1, int x2, int y1, int y2, int blitOffset, float minU, float maxU, float minV, float maxV) {
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(matrix, (float) x1, (float) y2, (float) blitOffset).uv(minU, maxV).endVertex();
        bufferbuilder.vertex(matrix, (float) x2, (float) y2, (float) blitOffset).uv(maxU, maxV).endVertex();
        bufferbuilder.vertex(matrix, (float) x2, (float) y1, (float) blitOffset).uv(maxU, minV).endVertex();
        bufferbuilder.vertex(matrix, (float) x1, (float) y1, (float) blitOffset).uv(minU, minV).endVertex();
        bufferbuilder.end();
        BufferUploader.end(bufferbuilder);
    }
}
