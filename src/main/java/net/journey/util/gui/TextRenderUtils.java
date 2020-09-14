package net.journey.util.gui;

import net.journey.util.EnumHexColorHelper;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;

public class TextRenderUtils {

	public TextRenderUtils() {
	}

	public void drawBoldString(FontRenderer fontRenderer, int x, int y, String text, EnumHexColorHelper color) {
		int j = x, k = y;
		fontRenderer.drawString(text, j + 1, k, 0);
		fontRenderer.drawString(text, j - 1, k, 0);
		fontRenderer.drawString(text, j, k + 1, 0);
		fontRenderer.drawString(text, j, k - 1, 0);
		fontRenderer.drawString(text, j, k, color.getInt());
	}

	public void drawBoldI18nString(FontRenderer fontRenderer, int x, int y, String text, EnumHexColorHelper color) {
		int j = x, k = y;
		fontRenderer.drawString(I18n.format(text), j + 1, k, 0);
		fontRenderer.drawString(I18n.format(text), j - 1, k, 0);
		fontRenderer.drawString(I18n.format(text), j, k + 1, 0);
		fontRenderer.drawString(I18n.format(text), j, k - 1, 0);
		fontRenderer.drawString(I18n.format(text), j, k, color.getInt());
	}

	public void drawCenteredI18nString(FontRenderer fontRenderer, int x, int y, String text, EnumHexColorHelper color) {
		this.drawCenteredString(fontRenderer, x, y, I18n.format(text), color);
	}

	public void drawCenteredString(FontRenderer fontRendererIn, int x, int y, String text, EnumHexColorHelper color) {
		fontRendererIn.drawStringWithShadow(text, (float) (x - fontRendererIn.getStringWidth(text) / 2), (float) y, color.getInt());
	}
}
