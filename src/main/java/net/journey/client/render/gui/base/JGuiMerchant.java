package net.journey.client.render.gui.base;

import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.IMerchant;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.entity.tileentity.container.JContainerMerchant;

import java.awt.*;

@SideOnly(Side.CLIENT)
public class JGuiMerchant extends JGuiContainer {
	private final ResourceLocation texture;
	protected String guiTranslationKey;
	private final IMerchant merchant;
	private PageButton nextPageButton;
	private PageButton prevPageButton;
	private int selectedRecipeIndex = 0;

	private final int textColor;

	public JGuiMerchant(JContainerMerchant container, IMerchant merchant, String guiTranslationKey, ResourceLocation texture, Color textColor) {
		this(container, merchant, guiTranslationKey, texture, textColor.getRGB());
	}

	public JGuiMerchant(JContainerMerchant container, IMerchant merchant, String guiTranslationKey, ResourceLocation texture, int textColor) {
		super(container);
		this.merchant = merchant;
		this.texture = texture;
		this.guiTranslationKey = guiTranslationKey;
		this.textColor = textColor;
	}

	@Override
	public void initGui() {
		super.initGui();

		this.buttonList.add(this.nextPageButton = new PageButton(1, guiLeft + 120 + 27, guiTop + 24 - 1, true));
		this.buttonList.add(this.prevPageButton = new PageButton(2, guiLeft + 36 - 19, guiTop + 24 - 1, false));
		this.nextPageButton.enabled = false;
		this.prevPageButton.enabled = false;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int var1, int var2) {
		this.fontRenderer.drawString(I18n.format(guiTranslationKey), this.xSize / 2F - this.fontRenderer.getStringWidth(I18n.format(guiTranslationKey)) / 2F, 6, textColor, true);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, textColor, true);
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		MerchantRecipeList recipes = this.merchant.getRecipes(this.mc.player);
		if (recipes != null) {
			this.nextPageButton.enabled = this.selectedRecipeIndex < recipes.size() - 1;
			this.prevPageButton.enabled = this.selectedRecipeIndex > 0;
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		boolean flag = false;

		if (button == nextPageButton) {
			selectedRecipeIndex++;
			flag = true;
		} else if (button == prevPageButton) {
			selectedRecipeIndex--;
			flag = true;
		}

		if (flag) {
			((JContainerMerchant) inventorySlots).setCurrentRecipeIndex(selectedRecipeIndex);
			PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
			packetbuffer.writeInt(selectedRecipeIndex);
			mc.getConnection().sendPacket(new CPacketCustomPayload("MC|TrSel", packetbuffer));
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		mc.getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		MerchantRecipeList merchantRecipeList = merchant.getRecipes(this.mc.player);

		if (merchantRecipeList != null && !merchantRecipeList.isEmpty()) {

			MerchantRecipe merchantRecipe = merchantRecipeList.get(selectedRecipeIndex);

			if (merchantRecipe.isRecipeDisabled()) {
				mc.getTextureManager().bindTexture(texture);

				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.disableLighting();

				drawTexturedModalRect(this.guiLeft + 83, this.guiTop + 21, 212, 0, 28, 21);
				drawTexturedModalRect(this.guiLeft + 83, this.guiTop + 51, 212, 0, 28, 21);
			}
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		MerchantRecipeList merchantRecipeList = this.merchant.getRecipes(this.mc.player);

		if (merchantRecipeList != null && !merchantRecipeList.isEmpty()) {
			MerchantRecipe merchantrecipe = merchantRecipeList.get(selectedRecipeIndex);

			ItemStack firstItemToBuy = merchantrecipe.getItemToBuy();
			ItemStack secondItemToBuy = merchantrecipe.getSecondItemToBuy();
			ItemStack itemToSell = merchantrecipe.getItemToSell();

			GlStateManager.pushMatrix();
			RenderHelper.enableGUIStandardItemLighting();
			GlStateManager.disableLighting();
			GlStateManager.enableRescaleNormal();
			GlStateManager.enableColorMaterial();
			GlStateManager.enableLighting();

			this.itemRender.zLevel = 100.0F;
			this.itemRender.renderItemAndEffectIntoGUI(firstItemToBuy, guiLeft + 36, guiTop + 24);
			this.itemRender.renderItemOverlays(this.fontRenderer, firstItemToBuy, guiLeft + 36, guiTop + 24);

			if (!secondItemToBuy.isEmpty()) {
				this.itemRender.renderItemAndEffectIntoGUI(secondItemToBuy, guiLeft + 62, guiTop + 24);
				this.itemRender.renderItemOverlays(this.fontRenderer, secondItemToBuy, guiLeft + 62, guiTop + 24);
			}

			this.itemRender.renderItemAndEffectIntoGUI(itemToSell, guiLeft + 120, guiTop + 24);
			this.itemRender.renderItemOverlays(this.fontRenderer, itemToSell, guiLeft + 120, guiTop + 24);
			this.itemRender.zLevel = 0.0F;
			GlStateManager.disableLighting();

			if (!firstItemToBuy.isEmpty() && isPointInRegion(36, 24, 16, 16, mouseX, mouseY)) {
				this.renderToolTip(firstItemToBuy, mouseX, mouseY);
			} else if (!secondItemToBuy.isEmpty() && this.isPointInRegion(62, 24, 16, 16, mouseX, mouseY)) {
				this.renderToolTip(secondItemToBuy, mouseX, mouseY);
			} else if (!itemToSell.isEmpty() && this.isPointInRegion(120, 24, 16, 16, mouseX, mouseY)) {
				this.renderToolTip(itemToSell, mouseX, mouseY);
			} else if (merchantrecipe.isRecipeDisabled() && (this.isPointInRegion(83, 21, 28, 21, mouseX, mouseY) || this.isPointInRegion(83, 51, 28, 21, mouseX, mouseY))) {
				this.drawHoveringText(I18n.format("merchant.deprecated"), mouseX, mouseY);
			}

			GlStateManager.popMatrix();
			GlStateManager.enableLighting();
			GlStateManager.enableDepth();
			RenderHelper.enableStandardItemLighting();
		}
	}

	public IMerchant getMerchant() {
		return this.merchant;
	}

	@SideOnly(Side.CLIENT)
	private class PageButton extends GuiButton {
		private final boolean lookForward;

		public PageButton(int buttonId, int x, int y, boolean lookForward) {
			super(buttonId, x, y, 12, 19, "");
			this.lookForward = lookForward;
		}

		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
			if (this.visible) {
				mc.getTextureManager().bindTexture(texture);
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				boolean flag = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
				int i = 0;
				int j = 176;

				if (!this.enabled) {
					j += this.width * 2;
				} else if (flag) {
					j += this.width;
				}

				if (!this.lookForward) {
					i += this.height;
				}

				this.drawTexturedModalRect(this.x, this.y, j, i, this.width, this.height);
			}
		}
	}
}