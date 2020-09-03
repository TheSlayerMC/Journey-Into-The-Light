package net.journey.client.render.gui;

import net.journey.util.Config;
import net.journey.util.ManagedProperty.BooleanManagedProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;

public class GuiButtonToggleMenu extends GuiButton {

	public GuiButtonToggleMenu(int x, int y) {
		super(137, x, y, 20, 20, "J");
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY) {
		BooleanManagedProperty propChangeMenu = Config.changeMainMenu;
		Boolean changeMainMenu = propChangeMenu.get();

		propChangeMenu.set(!changeMainMenu);

		if (changeMainMenu) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiMainMenu());
		} else {
			Minecraft.getMinecraft().displayGuiScreen(new JourneyMainMenu());
		}
	}
}
