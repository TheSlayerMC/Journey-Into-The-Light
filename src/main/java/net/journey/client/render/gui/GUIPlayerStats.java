package net.journey.client.render.gui;

import org.lwjgl.opengl.GL11;

import net.journey.JITL;
import net.journey.util.ContainerEmpty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipeList;
import net.slayer.api.SlayerAPI;
import net.slayer.api.client.gui.GuiModVillager;

public class GUIPlayerStats extends GuiContainer {

	private ResourceLocation knowledge_sprite = new ResourceLocation(JITL.MOD_ID, "textures/gui/knowledge/knowledge_sprites.png");
	private ResourceLocation background = new ResourceLocation(JITL.MOD_ID, "textures/gui/stats.png");
	
    private GUIPlayerStats.PageButton nextButton;
    private GUIPlayerStats.PageButton previousButton;
    
    private int pageNumber = 1;
	
	public GUIPlayerStats() {
		super(new ContainerEmpty());
	}
	
	@Override
	public void initGui() {
		super.initGui();
		int var1 = (this.width - this.xSize) / 2;
        int var2 = (this.height - this.ySize) / 2;
        this.buttonList.add(this.nextButton = new GUIPlayerStats.PageButton(1, var1 + 120 + 27, var2 + 24 - 1, true));
        this.buttonList.add(this.previousButton = new GUIPlayerStats.PageButton(2, var1 + 36 - 19, var2 + 24 - 1, false));
        this.nextButton.enabled = false;
        this.previousButton.enabled = false;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(background);
		xSize = 242;
		ySize = 204;
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
		if(pageNumber == 1) {
			page1();
		} 
		if(pageNumber == 2) {
			page2();
		}
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
	
	public void page2() {
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

        public PageButton(int par1, int par2, int par3, boolean par4) {
            super(par1, par2, par3, 12, 19, "");
            this.rev = par4;
        }

        @Override
        public void drawButton(Minecraft mc, int x, int y, float f) {
            if(this.visible) {
                mc.getTextureManager().bindTexture(new ResourceLocation(JITL.MOD_ID, "textures/gui/stats.png"));
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
            }
        }
    }
}