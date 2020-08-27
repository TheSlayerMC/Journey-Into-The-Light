package net.journey.client.render.gui;

import org.lwjgl.opengl.GL11;

import net.journey.JITL;
import net.journey.util.ContainerEmpty;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.util.ResourceLocation;

public class GUIPlayerStats extends GuiContainer {

	public GUIPlayerStats() {
		super(new ContainerEmpty());
	}

	private ResourceLocation knowledge_sprite = new ResourceLocation(JITL.MOD_ID, "textures/gui/knowledge/knowledge_sprites.png");
	private ResourceLocation background = new ResourceLocation(JITL.MOD_ID, "textures/gui/stats.png");

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(background);
		xSize = 242;
		ySize = 204;
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
		page1();
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		//page1();
	}
	
	public void page1() {
		int height = 43;
		int x = 9;
		int h = 9;
		drawSprite(x, h, 0, 74, "Sentacoins:");
		drawKnowledgeSprite(126, h, 32, 10, 0.50F, "Overworld");
		h += height;
		drawKnowledgeSprite(x, h, 64, 10, 0.30F, "Nether");
		drawKnowledgeSprite(126, h, 96, 10, 0.75F, "End");
		h += height;
		drawKnowledgeSprite(x, h, 128, 10, 0.30F, "Boiling Point");
		drawKnowledgeSprite(126, h, 160, 10, 0.75F, "Frozen Lands");
		h+= height - 2;
		drawKnowledgeSprite(x, h, 192, 10, 0.10F, "Euca");
		drawKnowledgeSprite(126, h, 224, 10, 0.60F, "The Depths");
	}
	
	public void drawSprite(int x, int y, int spriteX, int spriteY, String s) {
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		mc.getTextureManager().bindTexture(knowledge_sprite);
		drawTexturedModalRect(k + x, l + y, spriteX, spriteY, 32, 32);
		fontRenderer.drawString(s, k + x + 35, l + y + 5, 4210752);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
	
	public void drawKnowledgeSprite(int x, int y, int spriteX, int spriteY, float percent, String s) {
		drawSprite(x, y, spriteX, spriteY, s);
		int progressBarSize = 65;
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		mc.getTextureManager().bindTexture(knowledge_sprite);
		int xpLevel = (int)(percent * (float)(progressBarSize));
		drawTexturedModalRect(k + x + 35, l + y + 15, 0, 5, 65, 5);
		drawTexturedModalRect(k + x + 35, l + y + 15, 0, 0, xpLevel, 5);
	}
}