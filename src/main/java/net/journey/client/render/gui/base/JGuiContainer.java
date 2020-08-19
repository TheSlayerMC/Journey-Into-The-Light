package net.journey.client.render.gui.base;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

public class JGuiContainer extends GuiContainer {

	public JGuiContainer(Container inventorySlotsIn) {
		super(inventorySlotsIn);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float v, int i, int i1) {
	}

	@Override
	public void drawScreen(int x, int y, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(x, y, partialTicks);
		this.renderHoveredToolTip(x, y);
	}
}
