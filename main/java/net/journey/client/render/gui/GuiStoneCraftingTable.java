package net.journey.client.render.gui;

import org.lwjgl.opengl.GL11;

import net.journey.blocks.machines.BlockStoneCraftingTable;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;

public class GuiStoneCraftingTable extends GuiContainer {
	
    public GuiStoneCraftingTable(InventoryPlayer inv, BlockStoneCraftingTable table, World w) {
        super(new ContainerWorkbench(inv, w, null));
    }

    @Override
    protected void drawGuiContainerForegroundLayer (int par1, int par2) {
        this.fontRenderer.drawString(I18n.format("container.crafting"), 28, 6, 421000);
        this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 421000);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer (float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(new ResourceLocation(SlayerAPI.PREFIX + "textures/gui/craftingBase.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}