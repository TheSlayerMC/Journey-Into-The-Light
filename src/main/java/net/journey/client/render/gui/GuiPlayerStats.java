package net.journey.client.render.gui;

import net.journey.JITL;
import net.journey.api.capability.PlayerStats;
import net.journey.common.capability.JCapabilityManager;
import net.journey.common.knowledge.EnumKnowledgeType;
import net.journey.util.ContainerEmpty;
import net.journey.util.EnumHexColor;
import net.journey.util.gui.TextRenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiPlayerStats extends GuiContainer {

	private final ResourceLocation knowledge_sprite = JITL.rl("textures/gui/knowledge/knowledge_sprites.png");
	private final ResourceLocation background = JITL.rl("textures/gui/stats.png");

	private GuiPlayerStats.PageButton nextButton;
	private GuiPlayerStats.PageButton previousButton;
	private final PlayerStats stats;

	private int pageNumber = 0;
	private final int coinAmount = 0;

	public GuiPlayerStats() {
		super(new ContainerEmpty());
		this.xSize = 242;
		this.ySize = 204;
		this.stats = JCapabilityManager.asJourneyPlayer(Minecraft.getMinecraft().player).getPlayerStats();
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return true;
	}

	@Override
	public void initGui() {
		super.initGui();
		int w = (this.width - this.xSize) / 2;
		int h = (this.height - this.ySize) / 2;
		this.buttonList.add(this.nextButton = new GuiPlayerStats.PageButton(1, w + 134, h + 180, true));
		this.buttonList.add(this.previousButton = new GuiPlayerStats.PageButton(2, w + 100, h + 180, false));
		this.nextButton.enabled = false;
		this.previousButton.enabled = false;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		mc.getTextureManager().bindTexture(background);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
		switch(pageNumber) {
		case 0:
			page1();
			break;
		case 1:
			page2();
			break;
		default:
			break;
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	public void page1() {
		int height = 43;
		int x = 9;
		int h = 9;
		drawSprite(x, h, 0, 74, "Sentacoins:");
		drawKnowledgeSprite(126, h, 32, 10, EnumKnowledgeType.OVERWORLD, "Overworld");

		h += height;

		drawKnowledgeSprite(126, h, 160, 10, EnumKnowledgeType.FROZEN, "Frozen Lands");
		drawKnowledgeSprite(x, h, 64, 10, EnumKnowledgeType.NETHER, "Nether");

		h += height;

		drawKnowledgeSprite(126, h, 96, 10, EnumKnowledgeType.END, "End");
		drawKnowledgeSprite(x, h, 128, 10, EnumKnowledgeType.BOIL, "Boiling Point");

		h += height - 2;

		drawKnowledgeSprite(x, h, 192, 10, EnumKnowledgeType.EUCA, "Euca");
		drawKnowledgeSprite(126, h, 224, 10, EnumKnowledgeType.DEPTHS, "The Depths");
	}

	public void page2() {
		int height = 43;
		int x = 9;
		int h = 9;

		drawKnowledgeSprite(x, h, 0, 42, EnumKnowledgeType.CORBA, "Corba");
		drawKnowledgeSprite(126, h, 32, 42, EnumKnowledgeType.TERRANIA, "Terrania");

		h += height;

		drawKnowledgeSprite(x, h, 64, 42, EnumKnowledgeType.CLOUDIA, "Cloudia");
		drawKnowledgeSprite(126, h, 96, 42, EnumKnowledgeType.SENTERIAN, "Senterain");
		/*h += height;
		drawKnowledgeSprite(x, h, 128, 10, 0.30F, "SDHFSDH");
		drawKnowledgeSprite(126, h, 160, 10, 0.75F, "Frozen Lands");
		h+= height - 2;
		drawKnowledgeSprite(x, h, 192, 10, 0.10F, "Euca");
		drawKnowledgeSprite(126, h, 224, 10, 0.60F, "The Depths");*/
	}

	public void drawSprite(int x, int y, int spriteX, int spriteY, String s) {
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.enableAlpha();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		mc.getTextureManager().bindTexture(background);
		drawTexturedModalRect(k + x - 4, l + y - 4, 138, 212, 115, 40);
		mc.getTextureManager().bindTexture(knowledge_sprite);
		drawTexturedModalRect(k + x, l + y, spriteX, spriteY, 32, 32);
		fontRenderer.drawString(s, k + x + 35, l + y + 5, 4210752);
		if (s == "Sentacoins:") {
			fontRenderer.drawString("x" + stats.getSentacoinValue(), k + x + 35, l + y + 15, 4210752);
		}
		GlStateManager.disableAlpha();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}

	public void drawKnowledgeSprite(int x, int y, int spriteX, int spriteY, EnumKnowledgeType type, String s) {
		drawSprite(x, y, spriteX, spriteY, s);
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.enableAlpha();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int progressBarSize = 65;
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		mc.getTextureManager().bindTexture(knowledge_sprite);

		PlayerStats.KnowledgeStorage knowledge = stats.getKnowledge(type);

		float percents = knowledge.getAmountOnCurrentLevel() / knowledge.getLevelCapacity(knowledge.getLevelCount());
		int width = (int) (percents * progressBarSize);

		int progressBarX = k + x + 35, progressBarY = l + y + 15;

		drawTexturedModalRect(progressBarX, progressBarY, 0, 5, progressBarSize, 5);
		drawTexturedModalRect(progressBarX, progressBarY, 0, 0, width, 5);

		TextRenderUtils TextRenderUtils = new TextRenderUtils();
		int lvX = progressBarX + 29, lvY = progressBarY - 1;

		TextRenderUtils.drawBoldString(fontRenderer, knowledge.getLevelCount() > 10 ? lvX - 2 : knowledge.getLevelCount() > 100 ? lvX - 4 : lvX, lvY, "" + knowledge.getLevelCount(), EnumHexColorHelper.LIGHT_BLUE);
		GlStateManager.disableAlpha();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		this.nextButton.enabled = pageNumber < 1;
		this.previousButton.enabled = pageNumber > 0;
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if(button == this.nextButton) {
			this.pageNumber++;
		} else if (button == this.previousButton) {
			this.pageNumber--;
		}
	}

	private static class PageButton extends GuiButton {
		private final boolean rev;

		public PageButton(int buttonId, int x, int y, boolean rev) {
			super(buttonId, x, y, 12, 19, "");
			this.rev = rev;
		}

		@Override
		public void drawButton(Minecraft mc, int x, int y, float f) {
			mc.getTextureManager().bindTexture(JITL.rl("textures/gui/stats.png"));
			GlStateManager.pushMatrix();
			GlStateManager.enableBlend();
			GlStateManager.enableAlpha();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			height = 15;
			boolean flag = x >= this.x && y >= this.y && x < this.x + this.width && y < this.y + this.height;
			int k = 217;
			int l = 4;

			if (!this.enabled) {
				l += this.width * 2;
			} else if (flag) {
				l += this.width;
			}

			if (!this.rev) {
				k += this.height;
			}
			this.drawTexturedModalRect(this.x, this.y, l, k, this.width, this.height);
			GlStateManager.disableAlpha();
			GlStateManager.disableBlend();
			GlStateManager.popMatrix();
		}
	}
}