package net.journey.client.render.gui.base.dialogue;

import net.journey.util.EnumHexColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;

public class GuiOptionButton extends GuiButton {

	public GuiOptionButton(String optionTextKey, int buttonId, int x, int y) {
		super(buttonId, x, y, I18n.format(optionTextKey));
	}

	public GuiOptionButton(String optionTextKey, int buttonId, int x, int y, int widthIn, int heightIn) {
		super(buttonId, x, y, widthIn, heightIn, I18n.format(optionTextKey));
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		if (this.visible) {
			FontRenderer fontrenderer = mc.fontRenderer;
			this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
			int j = EnumHexColor.WHITE.getInt();
			if (this.packedFGColour != 0) {
				j = this.packedFGColour;
			} else if (!this.enabled) {
				j = EnumHexColor.LIGHT_BLUE.getInt();
			} else if (this.hovered) {
				j = EnumHexColor.YELLOW.getInt();
			}
			this.drawString(fontrenderer, this.displayString, this.x + this.width / this.x - 38, this.y + (this.height - 8) / 2, j);
		}
	}
}
