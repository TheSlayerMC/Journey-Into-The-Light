package net.journey.client.render.gui;

import net.journey.blocks.tileentity.TileEntityIncubator;
import net.journey.blocks.tileentity.container.ContainerIncubator;
import net.journey.client.render.gui.base.JGuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

@SideOnly(Side.CLIENT)
public class GuiIncubator extends JGuiContainer {

	private static final ResourceLocation Incubator_GUI_TEXTURES = new ResourceLocation(SlayerAPI.PREFIX + "textures/gui/incubator.png");
	private final InventoryPlayer playerInventory;
	private final IInventory tileIncubator;

	public GuiIncubator(InventoryPlayer playerInv, IInventory incubatorInv) {
		super(new ContainerIncubator(playerInv, incubatorInv));
		this.playerInventory = playerInv;
		this.tileIncubator = incubatorInv;
	}


	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = this.tileIncubator.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
		this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(Incubator_GUI_TEXTURES);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

		if (TileEntityIncubator.isBurning(this.tileIncubator)) {
			int k = this.getBurnLeftScaled(13);
			this.drawTexturedModalRect(i + 57, j + 36 + 13 - k, 176, 12 - k, 14, k + 1);
		}

		int l = this.getCookProgressScaled(24);
		this.drawTexturedModalRect(i + 80, j + 35, 176, 14, l + 1, 16);
	}

	private int getCookProgressScaled(int pixels) {
		int i = this.tileIncubator.getField(2);
		int j = this.tileIncubator.getField(3);
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}

	private int getBurnLeftScaled(int pixels) {
		int i = this.tileIncubator.getField(1);

		if (i == 0) {
			i = 200;
		}

		return this.tileIncubator.getField(0) * pixels / i;
	}
}