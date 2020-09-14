package net.journey.client.render.gui.base.dialogue;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;

public class GuiOptionButton extends GuiButton {

	public GuiOptionButton(String optionTextKey, int buttonId, int x, int y) {
		super(buttonId, x, y, I18n.format(optionTextKey));
	}

	public GuiOptionButton(String optionTextKey, int buttonId, int x, int y, int widthIn, int heightIn) {
		super(buttonId, x, y, widthIn, heightIn, I18n.format(optionTextKey));
	}
}
