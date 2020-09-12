package net.journey.client.render.gui.base;

import net.minecraft.client.gui.GuiScreen;

public class JGuiScreen extends GuiScreen {
	protected int centerX;
	protected int centerY;

	@Override
	public void initGui() {
		super.initGui();

		centerX = width / 2;
		centerY = height / 2;
	}
}
