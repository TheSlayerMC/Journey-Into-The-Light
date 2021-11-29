package net.jitl.client.util;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.text.ITextComponent;

import java.util.function.BiConsumer;

/**
 * Copy of {@link net.minecraft.client.gui.AbstractGui} with static context
 */
public class RenderUtils {

    private int blitOffset;

    public static void fill(MatrixStack matrixStack, int minX, int minY, int maxX, int maxY, int colorIn) {
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
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuilder();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.vertex(matrix, (float) minX, (float) maxY, 0.0F).color(f, f1, f2, f3).endVertex();
        bufferbuilder.vertex(matrix, (float) maxX, (float) maxY, 0.0F).color(f, f1, f2, f3).endVertex();
        bufferbuilder.vertex(matrix, (float) maxX, (float) minY, 0.0F).color(f, f1, f2, f3).endVertex();
        bufferbuilder.vertex(matrix, (float) minX, (float) minY, 0.0F).color(f, f1, f2, f3).endVertex();
        bufferbuilder.end();
        WorldVertexBufferUploader.end(bufferbuilder);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public void fillGradient(MatrixStack matrixStack, int x1, int y1, int x2, int y2, int colorFrom, int colorTo) {
        RenderSystem.disableTexture();
        RenderSystem.enableBlend();
        RenderSystem.disableAlphaTest();
        RenderSystem.defaultBlendFunc();
        RenderSystem.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        fillGradient(matrixStack.last().pose(), bufferbuilder, x1, y1, x2, y2, this.blitOffset, colorFrom, colorTo);
        tessellator.end();
        RenderSystem.shadeModel(7424);
        RenderSystem.disableBlend();
        RenderSystem.enableAlphaTest();
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

    public static void drawCenteredString(MatrixStack matrixStack, FontRenderer fontRenderer, String fontIn, float x, float y, int red, int green, int blue, int alpha) {
        int color = Math.max(4, alpha) << 24 | red << 16 | green << 8 | blue;
        fontRenderer.drawShadow(matrixStack, fontIn, x - fontRenderer.width(fontIn) / 2F, y, color);
    }

    public static void drawCenteredString(MatrixStack matrixStack, FontRenderer fontRenderer, ITextComponent fontIn, float x, float y, int red, int green, int blue, int alpha) {
        IReorderingProcessor ireorderingprocessor = fontIn.getVisualOrderText();
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

    public static void blit(MatrixStack matrixStack, int x, int y, int blitOffset, int width, int height, TextureAtlasSprite sprite) {
        innerBlit(matrixStack.last().pose(), x, x + width, y, y + height, blitOffset, sprite.getU0(), sprite.getU1(), sprite.getV0(), sprite.getV1());
    }

    public static void blit(MatrixStack matrixStack, int x, int y, int uOffset, int vOffset, int uWidth, int vHeight, int blitOffset) {
        blit(matrixStack, x, y, blitOffset, (float) uOffset, (float) vOffset, uWidth, vHeight, 256, 256);
    }

    public static void blit(MatrixStack matrixStack, int x, int y, int blitOffset, float uOffset, float vOffset, int uWidth, int vHeight, int textureHeight, int textureWidth) {
        innerBlit(matrixStack, x, x + uWidth, y, y + vHeight, blitOffset, uWidth, vHeight, uOffset, vOffset, textureWidth, textureHeight);
    }

    public static void blit(MatrixStack matrixStack, int x, int y, int width, int height, float uOffset, float vOffset, int uWidth, int vHeight, int textureWidth, int textureHeight) {
        innerBlit(matrixStack, x, x + width, y, y + height, 0, uWidth, vHeight, uOffset, vOffset, textureWidth, textureHeight);
    }

    public static void blit(MatrixStack matrixStack, int x, int y, float uOffset, float vOffset, int width, int height, int textureWidth, int textureHeight) {
        blit(matrixStack, x, y, width, height, uOffset, vOffset, width, height, textureWidth, textureHeight);
    }

    private static void innerBlit(MatrixStack matrixStack, int x1, int x2, int y1, int y2, int blitOffset, int uWidth, int vHeight, float uOffset, float vOffset, int textureWidth, int textureHeight) {
        innerBlit(matrixStack.last().pose(), x1, x2, y1, y2, blitOffset, (uOffset + 0.0F) / (float) textureWidth, (uOffset + (float) uWidth) / (float) textureWidth, (vOffset + 0.0F) / (float) textureHeight, (vOffset + (float) vHeight) / (float) textureHeight);
    }

    private static void innerBlit(Matrix4f matrix, int x1, int x2, int y1, int y2, int blitOffset, float minU, float maxU, float minV, float maxV) {
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuilder();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.vertex(matrix, (float) x1, (float) y2, (float) blitOffset).uv(minU, maxV).endVertex();
        bufferbuilder.vertex(matrix, (float) x2, (float) y2, (float) blitOffset).uv(maxU, maxV).endVertex();
        bufferbuilder.vertex(matrix, (float) x2, (float) y1, (float) blitOffset).uv(maxU, minV).endVertex();
        bufferbuilder.vertex(matrix, (float) x1, (float) y1, (float) blitOffset).uv(minU, minV).endVertex();
        bufferbuilder.end();
        RenderSystem.enableAlphaTest();
        WorldVertexBufferUploader.end(bufferbuilder);
    }
}
