package net.journey.client.render.gui.base;

import com.google.common.collect.Lists;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

import java.io.IOException;
import java.util.List;

public class JGuiInteractiveMerchant extends GuiScreen {

	protected List<GuiButton> buttonList = Lists.newArrayList();

	public JGuiInteractiveMerchant() {
		super();
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void initGui() {
		super.initGui();

		int topButtonPos = this.height / 2 - 64;
		int width = this.width / 2 - 100;

		this.buttonList.add(new GuiButton(156, width, topButtonPos, I18n.format("dialogue option 1")));
		this.buttonList.add(new GuiButton(156, width, topButtonPos + 32, I18n.format("dialogue option 2")));
		this.buttonList.add(new GuiButton(156, width, topButtonPos + 64, I18n.format("dialogue option 3")));
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
	}

	@Override
	public void drawScreen(int x, int y, float partialTicks) {
		super.drawScreen(x, y, partialTicks);
		int j;
		for (j = 0; j < this.buttonList.size(); ++j) {
			this.buttonList.get(j).drawButton(this.mc, x, y, partialTicks);
		}
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}
}
