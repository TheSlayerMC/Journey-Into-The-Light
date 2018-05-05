package net.slayer.api;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemStack;

public class GUIHelper extends Gui {

	private void drawItemOrBlock(ItemStack stack, Minecraft mc, int x, int y) {
		/*RenderItem itemRender = new RenderItem();
		if(stack != null && stack.getItem() != null) {
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glScaled(2, 2, 0);
			GL11.glPushMatrix();
			this.zLevel = 50.0F;
			itemRender.zLevel = 50.0F;
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			RenderHelper.enableGUIStandardItemLighting();
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			itemRender.renderItemIntoGUI(mc.fontRendererObj, mc.renderEngine, stack, x, y);
			RenderHelper.disableStandardItemLighting();
			itemRender.zLevel = 0.0F;
			this.zLevel = 0.0F;
			GL11.glPopMatrix();
		}*/
	}
}