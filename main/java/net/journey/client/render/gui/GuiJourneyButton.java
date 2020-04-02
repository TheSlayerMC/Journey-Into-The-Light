package net.journey.client.render.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

@SideOnly(Side.CLIENT)
public class GuiJourneyButton extends GuiButton {

	public boolean isReversed;
	
	protected static final ResourceLocation GOLD_BUTTON_TEXTURES = new ResourceLocation(
			SlayerAPI.PREFIX + "textures/gui/title/widgets.png");
	protected static final ResourceLocation REVERSE_BUTTON_TEXTURES = new ResourceLocation(
			SlayerAPI.PREFIX + "textures/gui/title/widgets_mirrored.png");

	public GuiJourneyButton(int buttonId, int x, int y, String buttonText, Boolean isFlipped) {
		super(buttonId, x, y, buttonText);
		this.isReversed = isFlipped;
	}
	
	public GuiJourneyButton(int buttonId, int x, int y, String buttonText) {
		super(buttonId, x, y, buttonText);
	}

	public GuiJourneyButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);

	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		if (this.visible) {
			FontRenderer fontrenderer = mc.fontRenderer;
			if(this.isReversed == false) {
				mc.getTextureManager().bindTexture(GOLD_BUTTON_TEXTURES);
			}
				else if (this.isReversed == true){
					mc.getTextureManager().bindTexture(REVERSE_BUTTON_TEXTURES); {
				}
			}

			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width
					&& mouseY < this.y + this.height;
			int i = this.getHoverState(this.hovered);
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
					GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
					GlStateManager.DestFactor.ZERO);
			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA,
					GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			this.drawTexturedModalRect(this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
			this.drawTexturedModalRect(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20,
					this.width / 2, this.height);
			this.mouseDragged(mc, mouseX, mouseY);
			int j = 14737632;

			if (packedFGColour != 0) {
				j = packedFGColour;
			} else if (!this.enabled) {
				j = 10526880;
			} else if (this.hovered) {
				j = 16777120;
			}

			this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 2,
					this.y + (this.height - 8) / 2, j);
		}
	}
}