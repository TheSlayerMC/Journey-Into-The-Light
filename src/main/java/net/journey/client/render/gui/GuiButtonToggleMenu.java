package net.journey.client.render.gui;

import net.journey.JITL;
import net.journey.util.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.common.config.ConfigManager;

public class GuiButtonToggleMenu extends GuiButton {

	public GuiButtonToggleMenu(int x, int y) {
		super(137, x, y, 20, 20, "J");
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY) {
		boolean menuEnabled = Config.changeMainMenu;

		if (menuEnabled) {
			menuEnabled = false;
			Minecraft.getMinecraft().displayGuiScreen(new GuiMainMenu());
		}

		if (!menuEnabled) {
			menuEnabled = true;
			Minecraft.getMinecraft().displayGuiScreen(new JourneyMainMenu());
		}

		ConfigManager.sync(JITL.MOD_ID, net.minecraftforge.common.config.Config.Type.INSTANCE);
	}
}
