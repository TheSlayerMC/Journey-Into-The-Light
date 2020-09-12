package net.journey.client.render.gui.base.dialogue;

import net.journey.dialogue.DialogueNode;
import net.minecraft.client.gui.GuiButton;

public class GuiOptionButton extends GuiButton {
	private final DialogueNode.Option option;

	public GuiOptionButton(DialogueNode.Option option, int buttonId, int x, int y) {
		super(buttonId, x, y, option.getText());

		this.option = option;
	}

	public GuiOptionButton(DialogueNode.Option option, int buttonId, int x, int y, int widthIn, int heightIn) {
		super(buttonId, x, y, widthIn, heightIn, option.getText());

		this.option = option;
	}
}
