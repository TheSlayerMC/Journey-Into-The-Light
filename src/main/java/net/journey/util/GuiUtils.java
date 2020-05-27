package net.journey.util;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

/*
 * Code by TimeConqueror
 */
public class GuiUtils {

    public static void drawTexturedQuad(int x, int y, int requestedWidth, int requestedHeight, int textureX, int textureY, int textureWidth, int textureHeight, float zLevel) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double) (x + 0), (double) (y + requestedHeight), (double) zLevel).tex((double) ((float) (textureX + 0) * 0.00390625F), (double) ((float) (textureY + textureHeight) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double) (x + requestedWidth), (double) (y + requestedHeight), (double) zLevel).tex((double) ((float) (textureX + textureWidth) * 0.00390625F), (double) ((float) (textureY + textureHeight) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double) (x + requestedWidth), (double) (y + 0), (double) zLevel).tex((double) ((float) (textureX + textureWidth) * 0.00390625F), (double) ((float) (textureY + 0) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double) (x + 0), (double) (y + 0), (double) zLevel).tex((double) ((float) (textureX + 0) * 0.00390625F), (double) ((float) (textureY + 0) * 0.00390625F)).endVertex();
        tessellator.draw();
    }

    public static void drawCenteredStringWithCustomScale(FontRenderer fontRendererIn, String text, int x, int y, int zLevel, int color, float scaleFactor, int availableHeight) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x - fontRendererIn.getStringWidth(text) / 2 * scaleFactor, y + (availableHeight / 2) + (fontRendererIn.FONT_HEIGHT * scaleFactor > 1 ? -1 * fontRendererIn.FONT_HEIGHT * scaleFactor : fontRendererIn.FONT_HEIGHT * scaleFactor) * 0.5, zLevel);
        GlStateManager.scale(scaleFactor, scaleFactor, 1);
        fontRendererIn.drawStringWithShadow(text, 0, 0, color);
        GlStateManager.popMatrix();
    }
}
