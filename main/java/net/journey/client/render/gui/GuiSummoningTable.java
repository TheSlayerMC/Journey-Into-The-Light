package net.journey.client.render.gui;

import io.netty.buffer.Unpooled;

import java.io.IOException;

import net.journey.blocks.tileentity.TileEntitySummoningTable;
import net.journey.blocks.tileentity.container.ContainerSummoningTable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

import org.lwjgl.opengl.GL11;

public class GuiSummoningTable extends GuiContainer {

	private GuiSummoningTable.CraftButton button;
	private TileEntitySummoningTable table;

	public GuiSummoningTable(InventoryPlayer inventory, TileEntitySummoningTable table, World w) {
		super(new ContainerSummoningTable(inventory, table, w));
		this.table = table;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = "journey.summoningTable";
		this.fontRenderer.drawString(I18n.format(s), this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 0xFFFFFF);
		this.fontRenderer.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 0xFFFFFF);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(new ResourceLocation(SlayerAPI.PREFIX + "textures/gui/summoning.png"));
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

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		//table.update();
		PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
		this.mc.getConnection().sendPacket(new CPacketCustomPayload("Journey|SUMMONING", packetbuffer));
	}

	@SideOnly(Side.CLIENT)
	private class CraftButton extends GuiButton {

		public CraftButton(int x, int y) {
			super(1, x, y, 18, 12, "");
		}

		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float ticks) {
			mc.getTextureManager().bindTexture(new ResourceLocation(SlayerAPI.PREFIX + "textures/gui/summoning.png"));
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			boolean flag = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
			int k = 0, l = 176;
			if(flag) {
				mc.getTextureManager().bindTexture(new ResourceLocation(SlayerAPI.PREFIX + "textures/gui/summoning.png"));
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				this.drawTexturedModalRect(this.x, this.y, l, 12, 18, 12);
				drawHoveringText("Craft boss spawner", mouseX, mouseY);
				k += 12;
			}
			this.drawTexturedModalRect(this.x, this.y, l, k, 18, 12);
		}
	}
}