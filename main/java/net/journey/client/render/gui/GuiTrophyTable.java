package net.journey.client.render.gui;

import net.journey.blocks.tileentity.TileEntityTrophyTable;
import net.journey.blocks.tileentity.container.ContainerTrophy;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;

import org.lwjgl.opengl.GL11;

public class GuiTrophyTable extends GuiContainer {
	
	private TileEntityTrophyTable table;

	public GuiTrophyTable(InventoryPlayer inventory, TileEntityTrophyTable table, World w) {
		super(new ContainerTrophy(inventory, table, w));
		this.table = table;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = "essence.trophyTable";
		this.fontRenderer.drawString(I18n.format(s), this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 0xFFFFFF);
		this.fontRenderer.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 0xFFFFFF);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(new ResourceLocation(SlayerAPI.PREFIX + "textures/gui/trophy.png"));
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}

	@Override
	public void initGui() {
		super.initGui();
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		//this.buttonList.add(button = new GuiSummoningTable.CraftButton(x + 79, y + 55));
	}
}