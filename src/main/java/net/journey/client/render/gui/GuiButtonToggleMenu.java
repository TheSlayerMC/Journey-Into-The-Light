package net.journey.client.render.gui;

import net.journey.util.Config;
import net.journey.util.ManagedProperty.BooleanManagedProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;

public class GuiButtonToggleMenu extends GuiButton {

	public GuiButtonToggleMenu(int x, int y) {
		super(137, x, y, 20, 20, "");
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		super.drawButton(mc, mouseX, mouseY, partialTicks);
		if (this.visible) {
			this.renderButtonTexture(mc);
			if (this.hovered) {
				BooleanManagedProperty propChangeMenu = Config.changeMainMenu;
				boolean changeMainMenu = propChangeMenu.get();
				String textKey = changeMainMenu ? "journey.menu.theme.mc" : "journey.menu.theme.jitl";
				renderTextOverlay(mc, mouseX, mouseY, textKey);
			}
		}
	}

	private void renderButtonTexture(Minecraft mc) {
		mc.getTextureManager().bindTexture(GuiJourneyButton.GOLD_BUTTON_TEXTURES);
		int textureStart = 146;
		if (hovered) textureStart += this.height;
		this.drawTexturedModalRect(this.x, this.y, 0, textureStart, this.width, this.height);
	}

	private void renderTextOverlay(Minecraft mc, int mouseX, int mouseY, String theme) {
		this.drawCenteredString(mc.fontRenderer, TextFormatting.BOLD + I18n.format(theme), mouseX + 64, mouseY + 8, 0xaefeff);
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY) {
		BooleanManagedProperty propChangeMenu = Config.changeMainMenu;
		Boolean changeMainMenu = propChangeMenu.get();
		propChangeMenu.set(!changeMainMenu);

		GuiMainMenu menu = changeMainMenu ? new GuiMainMenu() : new JourneyMainMenu();

		Minecraft.getMinecraft().displayGuiScreen(menu);
	}
}
