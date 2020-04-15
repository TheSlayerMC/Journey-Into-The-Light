package net.slayer.api.client;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.tileentity.TileEntityModFurnace;
import net.slayer.api.entity.tileentity.container.ContainerModFurnace;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiModFurnace extends GuiContainer {

    private TileEntityModFurnace tileFurnace;
    private String name, texture;

    public GuiModFurnace(InventoryPlayer par1InventoryPlayer, TileEntityModFurnace par2TileEntityFurnace, String name, String texture, boolean fuel) {
        super(new ContainerModFurnace(par1InventoryPlayer, par2TileEntityFurnace, fuel));
        this.tileFurnace = par2TileEntityFurnace;
        this.name = name;
        this.texture = texture;
    }

    public GuiModFurnace(InventoryPlayer par1InventoryPlayer, TileEntityModFurnace par2TileEntityFurnace, String name, boolean fuel) {
        this(par1InventoryPlayer, par2TileEntityFurnace, name, name.toLowerCase(), fuel);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
        String s = name;
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        s = "Inventory";
        this.fontRenderer.drawString(s, 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int p_146j976_3_) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(SlayerAPI.PREFIX + "textures/gui/" + texture + ".png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;

        if (this.tileFurnace.isBurning()) {
            i1 = getBurnLeftScaled(12);
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }

        i1 = getCookProgressScaled(24);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
    }

    private int getCookProgressScaled(int pixels) {
        int i = this.tileFurnace.getField(2);
        int j = this.tileFurnace.getField(3);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    private int getBurnLeftScaled(int pixels) {
        int i = this.tileFurnace.getField(1);
        if (i == 0) i = 200;
        return this.tileFurnace.getField(0) * pixels / i;
    }
}