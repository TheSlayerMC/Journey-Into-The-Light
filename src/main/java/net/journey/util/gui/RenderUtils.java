package net.journey.util.gui;

import net.journey.client.util.Rectangle;
import net.journey.util.EnumHexColor;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import org.lwjgl.opengl.GL11;

public class RenderUtils {

	public static void drawTexturedQuad(int x, int y, int requestedWidth, int requestedHeight, int textureX, int textureY, int textureWidth, int textureHeight, float zLevel) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(x + 0, y + requestedHeight, zLevel).tex((float) (textureX + 0) * 0.00390625F, (float) (textureY + textureHeight) * 0.00390625F).endVertex();
		bufferbuilder.pos(x + requestedWidth, y + requestedHeight, zLevel).tex((float) (textureX + textureWidth) * 0.00390625F, (float) (textureY + textureHeight) * 0.00390625F).endVertex();
		bufferbuilder.pos(x + requestedWidth, y + 0, zLevel).tex((float) (textureX + textureWidth) * 0.00390625F, (float) (textureY + 0) * 0.00390625F).endVertex();
		bufferbuilder.pos(x + 0, y + 0, zLevel).tex((float) (textureX + 0) * 0.00390625F, (float) (textureY + 0) * 0.00390625F).endVertex();
		tessellator.draw();
	}

	public static void drawCenteredStringWithCustomScale(FontRenderer fontRendererIn, String text, int x, int y, int zLevel, int color, float scaleFactor, int availableHeight, boolean hasShadow) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x - fontRendererIn.getStringWidth(text) / 2 * scaleFactor, y + (availableHeight / 2) + (fontRendererIn.FONT_HEIGHT * scaleFactor > 1 ? -1 * fontRendererIn.FONT_HEIGHT * scaleFactor : fontRendererIn.FONT_HEIGHT * scaleFactor) * 0.5, zLevel);
		GlStateManager.scale(scaleFactor, scaleFactor, 1);
		if (hasShadow) {
			fontRendererIn.drawStringWithShadow(text, 0, 0, color);
		} else {
			fontRendererIn.drawString(text, 0, 0, color);
		}
		GlStateManager.popMatrix();
	}

	public static void drawCenteredOutlinedStringWithCustomScale(FontRenderer fontRendererIn, String text, int x, int y, int zLevel, int color, int outlineColor, float scaleFactor, int availableHeight) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x - fontRendererIn.getStringWidth(text) / 2 * scaleFactor, y + (availableHeight / 2) + (fontRendererIn.FONT_HEIGHT * scaleFactor > 1 ? -1 * fontRendererIn.FONT_HEIGHT * scaleFactor : fontRendererIn.FONT_HEIGHT * scaleFactor) * 0.5, zLevel);
		GlStateManager.scale(scaleFactor, scaleFactor, 1);

		fontRendererIn.drawString(text, 0, 0, color);
		fontRendererIn.drawString(text, 0 + 1, 0, outlineColor);
		fontRendererIn.drawString(text, 0 - 1, 0, outlineColor);
		fontRendererIn.drawString(text, 0, 0 + 1, outlineColor);
		fontRendererIn.drawString(text, 0, 0 - 1, outlineColor);
		fontRendererIn.drawString(text, 0, 0, color);

		GlStateManager.popMatrix();
	}

	public void drawOutlinedCenteredString(FontRenderer fontRenderer, int x, int y, String text, EnumHexColor textColor, EnumHexColor outlineColor) {
		int j = x, k = y;
		drawCenteredString(fontRenderer, j + 1, k, text, outlineColor);
		drawCenteredString(fontRenderer, j - 1, k, text, outlineColor);
		drawCenteredString(fontRenderer, j, k + 1, text, outlineColor);
		drawCenteredString(fontRenderer, j, k - 1, text, outlineColor);
		drawCenteredString(fontRenderer, j, k, text, textColor);
	}

	public void drawOutlinedCenteredI18nString(FontRenderer fontRenderer, int x, int y, String text, EnumHexColor textColor, EnumHexColor outlineColor) {
		int j = x, k = y;
		drawCenteredString(fontRenderer, j + 1, k, I18n.format(text), outlineColor);
		drawCenteredString(fontRenderer, j - 1, k, I18n.format(text), outlineColor);
		drawCenteredString(fontRenderer, j, k + 1, I18n.format(text), outlineColor);
		drawCenteredString(fontRenderer, j, k - 1, I18n.format(text), outlineColor);
		drawCenteredString(fontRenderer, j, k, text, textColor);
	}

	public void drawOutlinedString(FontRenderer fontRenderer, int x, int y, String text, EnumHexColor textColor, EnumHexColor outlineColor) {
		int j = x, k = y;
		fontRenderer.drawString(text, j + 1, k, outlineColor.getInt());
		fontRenderer.drawString(text, j - 1, k, outlineColor.getInt());
		fontRenderer.drawString(text, j, k + 1, outlineColor.getInt());
		fontRenderer.drawString(text, j, k - 1, outlineColor.getInt());
		fontRenderer.drawString(text, j, k, textColor.getInt());
	}

	public void drawOutlinedI18nString(FontRenderer fontRenderer, int x, int y, String text, EnumHexColor textColor, EnumHexColor outlineColor) {
		int j = x, k = y;
		fontRenderer.drawString(I18n.format(text), j + 1, k, outlineColor.getInt());
		fontRenderer.drawString(I18n.format(text), j - 1, k, outlineColor.getInt());
		fontRenderer.drawString(I18n.format(text), j, k + 1, outlineColor.getInt());
		fontRenderer.drawString(I18n.format(text), j, k - 1, outlineColor.getInt());
		fontRenderer.drawString(I18n.format(text), j, k, textColor.getInt());
	}

	public void drawCenteredI18nStringWithShadow(FontRenderer fontRenderer, int x, int y, String text, EnumHexColor color) {
		this.drawCenteredStringWithShadow(fontRenderer, x, y, I18n.format(text), color);
	}

	public void drawCenteredStringWithShadow(FontRenderer fontRendererIn, int x, int y, String text, EnumHexColor color) {
		fontRendererIn.drawStringWithShadow(text, (float) (x - fontRendererIn.getStringWidth(text) / 2), (float) y, color.getInt());
	}

	public void drawCenteredString(FontRenderer fontRendererIn, int x, int y, String text, EnumHexColor color) {
		fontRendererIn.drawString(text, (x - fontRendererIn.getStringWidth(text) / 2), y, color.getInt());
	}

	public static void drawRect(Rectangle rectangle, int argbColor, float alpha) {
		Gui.drawRect(rectangle.getLeft(), rectangle.getTop(), rectangle.getRight(), rectangle.getBottom(), argbColor);
		GlStateManager.color(1, 1, 1, alpha);
	}

	public static int getRed(int argb) {
		return argb >> 16 & 0xFF;
	}

	public static int getGreen(int argb) {
		return argb >> 8 & 0xFF;
	}

	public static int getBlue(int argb) {
		return argb & 0xFF;
	}

	public static int getAlpha(int argb) {
		return argb >> 24 & 0xFF;
	}
}
