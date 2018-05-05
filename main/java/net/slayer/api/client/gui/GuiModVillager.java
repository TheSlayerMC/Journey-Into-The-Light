package net.slayer.api.client.gui;

import io.netty.buffer.Unpooled;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.journey.util.LangHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
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
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.tileentity.container.ContainerModVillager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class GuiModVillager extends GuiContainer {
	
	private static final Logger logger = LogManager.getLogger();
	private IMerchant theIMerchant;
	private GuiModVillager.ModMerchantButton nextRecipeButtonIndex;
	private GuiModVillager.ModMerchantButton previousRecipeButtonIndex;
	private int currentRecipeIndex = 0;
    protected String name;
	private final String texture;
	private boolean whiteName = false;
	private Object fontRendererObj;

	public GuiModVillager(ContainerModVillager container, IMerchant mer, String name, String tex) {
		super(container);
		this.theIMerchant = mer; 
		this.texture = tex;
		this.name = name;
	}
	
	public GuiModVillager(ContainerModVillager container, IMerchant mer, String name, String tex, boolean white) {
		super(container);
		this.theIMerchant = mer; 
		this.texture = tex;
		this.name = name;
		this.whiteName = white;
	}

	@Override
	public void initGui() {
		super.initGui();
		int var1 = (this.width - this.xSize) / 2;
		int var2 = (this.height - this.ySize) / 2;
		this.buttonList.add(this.nextRecipeButtonIndex = new GuiModVillager.ModMerchantButton(1, var1 + 120 + 27, var2 + 24 - 1, true, texture));
		this.buttonList.add(this.previousRecipeButtonIndex = new GuiModVillager.ModMerchantButton(2, var1 + 36 - 19, var2 + 24 - 1, false, texture));
		this.nextRecipeButtonIndex.enabled = false;
		this.previousRecipeButtonIndex.enabled = false;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int var1, int var2) {
		this.fontRenderer.drawString(LangHelper.getFormattedText(name), this.xSize / 2 - this.fontRenderer.getStringWidth(LangHelper.getFormattedText(name)) / 2, 6, whiteName ? 0xFFFFFF : 4210752);
		this.fontRenderer.drawString(LangHelper.getFormattedText("container.inventory"), 8, this.ySize - 96 + 2, whiteName ? 0xFFFFFF : 4210752);
	}
 
	@Override
	public void updateScreen() {
		super.updateScreen();
		MerchantRecipeList var1 = this.theIMerchant.getRecipes(this.mc.player);
		if(var1 != null) {
			this.nextRecipeButtonIndex.enabled = this.currentRecipeIndex < var1.size() - 1;
			this.previousRecipeButtonIndex.enabled = this.currentRecipeIndex > 0;
		}
	}
 
	@Override
	protected void actionPerformed(GuiButton var1) {
		boolean var2 = false;

		if(var1 == this.nextRecipeButtonIndex) {
			currentRecipeIndex++;
			var2 = true;
		}
		else if(var1 == this.previousRecipeButtonIndex) {
			currentRecipeIndex--;
			var2 = true;
		}

		if(var2) {
			((ContainerModVillager)this.inventorySlots).setCurrentRecipeIndex(this.currentRecipeIndex);
			ByteArrayOutputStream var3 = new ByteArrayOutputStream();
			DataOutputStream var4 = new DataOutputStream(var3);

			try {
				var4.writeInt(this.currentRecipeIndex);
	            PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
	            packetbuffer.writeInt(this.currentRecipeIndex);
				this.mc.getConnection().sendPacket(new CPacketCustomPayload("MC|TrSel", packetbuffer));
			} catch(Exception var6) {
				logger.error("Couldn\'t send trade info", var6);
			}
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(SlayerAPI.PREFIX + "textures/gui/" + texture + ".png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        MerchantRecipeList merchantrecipelist = this.theIMerchant.getRecipes(this.mc.player);

        if(merchantrecipelist != null && !merchantrecipelist.isEmpty()) {
            int i1 = this.currentRecipeIndex;
            MerchantRecipe merchantrecipe = merchantrecipelist.get(i1);

            if(merchantrecipe.isRecipeDisabled()) {
                this.mc.getTextureManager().bindTexture(new ResourceLocation(SlayerAPI.PREFIX + "textures/gui/" + texture + ".png"));
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glDisable(GL11.GL_LIGHTING);
                this.drawTexturedModalRect(this.guiLeft + 83, this.guiTop + 21, 212, 0, 28, 21);
                this.drawTexturedModalRect(this.guiLeft + 83, this.guiTop + 51, 212, 0, 28, 21);
            }
        }
    }
 
	@Override
    public void drawScreen(int mouseX, int mouseY, float par3) {
        super.drawScreen(mouseX, mouseY, par3);
        MerchantRecipeList merchantrecipelist = this.theIMerchant.getRecipes(this.mc.player);

        if (merchantrecipelist != null && !merchantrecipelist.isEmpty()) {
            int k = (this.width - this.xSize) / 2;
            int l = (this.height - this.ySize) / 2;
            int i1 = this.currentRecipeIndex;
            MerchantRecipe merchantrecipe = merchantrecipelist.get(i1);
            GL11.glPushMatrix();
            ItemStack itemstack = merchantrecipe.getItemToBuy();
            ItemStack itemstack1 = merchantrecipe.getSecondItemToBuy();
            ItemStack itemstack2 = merchantrecipe.getItemToSell();
            RenderHelper.enableGUIStandardItemLighting();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glEnable(GL11.GL_COLOR_MATERIAL);
            GL11.glEnable(GL11.GL_LIGHTING);
            this.itemRender.zLevel = 100.0F;
            this.itemRender.renderItemAndEffectIntoGUI(itemstack, k + 36, l + 24);
            this.itemRender.renderItemOverlays(this.fontRenderer, itemstack, k + 36, l + 24);

            if(itemstack1 != null) {
                this.itemRender.renderItemAndEffectIntoGUI(itemstack1, k + 62, l + 24);
                this.itemRender.renderItemOverlays(this.fontRenderer, itemstack1, k + 62, l + 24);
            }

            this.itemRender.renderItemAndEffectIntoGUI(itemstack2, k + 120, l + 24);
            this.itemRender.renderItemOverlays(this.fontRenderer, itemstack2, k + 120, l + 24);
            this.itemRender.zLevel = 0.0F;
            GlStateManager.disableLighting();
            if(this.isPointInRegion(36, 24, 16, 16, mouseX, mouseY) && itemstack != null) 
                this.renderToolTip(itemstack, mouseX, mouseY);        
            else if(itemstack1 != null && this.isPointInRegion(62, 24, 16, 16, mouseX, mouseY) && itemstack1 != null) 
                this.renderToolTip(itemstack1, mouseX, mouseY);      
            else if(itemstack2 != null && this.isPointInRegion(120, 24, 16, 16, mouseX, mouseY) && itemstack2 != null) 
                this.renderToolTip(itemstack2, mouseX, mouseY);     
            else if(merchantrecipe.isRecipeDisabled() && (this.isPointInRegion(83, 21, 28, 21, mouseX, mouseY) || this.isPointInRegion(83, 51, 28, 21, mouseX, mouseY))) 
                this.drawHoveringText(I18n.format("merchant.deprecated", new Object[0]), mouseX, mouseY);
            GL11.glPopMatrix();
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            RenderHelper.enableStandardItemLighting();
        }
    }

	public IMerchant getIMerchant() {
		return this.theIMerchant;
	}

	@SideOnly(Side.CLIENT)
	private static class ModMerchantButton extends GuiButton {
		private final boolean rev;
		private String texture;

		public ModMerchantButton(int par1, int par2, int par3, boolean par4, String tex) {
			super(par1, par2, par3, 12, 19, "");
			this.texture = tex;
			this.rev = par4;
		}

		@Override
		public void drawButton(Minecraft mc, int x, int y, float f) {
			if(this.visible) {
				mc.getTextureManager().bindTexture(new ResourceLocation(SlayerAPI.PREFIX + "textures/gui/" + texture + ".png"));
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				boolean flag = x >= this.x && y >= this.y && x < this.x + this.width && y < this.y + this.height;
				int k = 0;
				int l = 176;

				if(!this.enabled) {
					l += this.width * 2;
				}
				else if(flag) {
					l += this.width;
				}

				if(!this.rev) {
					k += this.height;
				}
				this.drawTexturedModalRect(this.x, this.y, l, k, this.width, this.height);
			}
		}
	}
}