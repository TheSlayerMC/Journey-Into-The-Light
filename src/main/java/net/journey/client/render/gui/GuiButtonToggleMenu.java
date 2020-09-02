package net.journey.client.render.gui;

import net.journey.util.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;

public class GuiButtonToggleMenu extends GuiButton {

	public GuiButtonToggleMenu(int x, int y) {
		super(137, x, y, 20, 20, "J");
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY) {
		boolean menu = Config.changeMainMenu;
		if (menu) {
			Minecraft.getMinecraft().displayGuiScreen(new JourneyMainMenu());
		}
		if (!menu) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiMainMenu());
		}
	}
}
