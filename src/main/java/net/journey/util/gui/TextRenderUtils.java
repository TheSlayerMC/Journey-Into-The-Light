package net.journey.util.gui;

import net.journey.util.EnumHexColorHelper;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;

public class TextRenderUtils {

	public TextRenderUtils() {
	}

	public void drawOutlinedCenteredString(FontRenderer fontRenderer, int x, int y, String text, EnumHexColorHelper textColor, EnumHexColorHelper outlineColor) {
		int j = x, k = y;
		drawCenteredString(fontRenderer, j + 1, k, I18n.format(text), outlineColor);
		drawCenteredString(fontRenderer, j - 1, k, I18n.format(text), outlineColor);
		drawCenteredString(fontRenderer, j, k + 1, I18n.format(text), outlineColor);
		drawCenteredString(fontRenderer, j, k - 1, I18n.format(text), outlineColor);
		drawCenteredString(fontRenderer, j, k, text, textColor);
	}

	public void drawOutlinedCenteredI18nString(FontRenderer fontRenderer, int x, int y, String text, EnumHexColorHelper textColor, EnumHexColorHelper outlineColor) {
		int j = x, k = y;
		drawCenteredString(fontRenderer, j + 1, k, I18n.format(text), outlineColor);
		drawCenteredString(fontRenderer, j - 1, k, I18n.format(text), outlineColor);
		drawCenteredString(fontRenderer, j, k + 1, I18n.format(text), outlineColor);
		drawCenteredString(fontRenderer, j, k - 1, I18n.format(text), outlineColor);
		drawCenteredString(fontRenderer, j, k, text, textColor);
	}

	public void drawOutlinedString(FontRenderer fontRenderer, int x, int y, String text, EnumHexColorHelper textColor, EnumHexColorHelper outlineColor) {
		int j = x, k = y;
		fontRenderer.drawString(text, j + 1, k, outlineColor.getInt());
		fontRenderer.drawString(text, j - 1, k, outlineColor.getInt());
		fontRenderer.drawString(text, j, k + 1, outlineColor.getInt());
		fontRenderer.drawString(text, j, k - 1, outlineColor.getInt());
		fontRenderer.drawString(text, j, k, textColor.getInt());
	}

	public void drawOutlinedI18nString(FontRenderer fontRenderer, int x, int y, String text, EnumHexColorHelper textColor, EnumHexColorHelper outlineColor) {
		int j = x, k = y;
		fontRenderer.drawString(I18n.format(text), j + 1, k, outlineColor.getInt());
		fontRenderer.drawString(I18n.format(text), j - 1, k, outlineColor.getInt());
		fontRenderer.drawString(I18n.format(text), j, k + 1, outlineColor.getInt());
		fontRenderer.drawString(I18n.format(text), j, k - 1, outlineColor.getInt());
		fontRenderer.drawString(I18n.format(text), j, k, textColor.getInt());
	}

	public void drawCenteredI18nString(FontRenderer fontRenderer, int x, int y, String text, EnumHexColorHelper color) {
		this.drawCenteredString(fontRenderer, x, y, I18n.format(text), color);
	}

	public void drawCenteredString(FontRenderer fontRendererIn, int x, int y, String text, EnumHexColorHelper color) {
		fontRendererIn.drawStringWithShadow(text, (float) (x - fontRendererIn.getStringWidth(text) / 2), (float) y, color.getInt());
	}
}
