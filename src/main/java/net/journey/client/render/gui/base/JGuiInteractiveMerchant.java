package net.journey.client.render.gui.base;

import com.google.common.collect.Lists;
import net.journey.JITL;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

import java.io.IOException;
import java.util.List;

public class JGuiInteractiveMerchant extends GuiScreen {

	protected List<GuiButton> buttonList = Lists.newArrayList();

	public ResourceLocation npcTexture = JITL.rl("textures/gui/characters/test.png");

	public JGuiInteractiveMerchant() {
		super();
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void initGui() {
		super.initGui();

		int topButtonPos = this.height / 2 + 64;
		int width = this.width / 2 - 100;

		this.buttonList.add(new GuiButton(156, width, topButtonPos, I18n.format("What?")));
		this.buttonList.add(new GuiButton(156, width, topButtonPos + 32, I18n.format("What? (but more aggressively)")));
		this.buttonList.add(new GuiButton(156, width, topButtonPos + 64, I18n.format("What? (but you're crying and peeing your pants)")));
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
	}

	@Override
	public void drawScreen(int x, int y, float partialTicks) {
		super.drawScreen(x, y, partialTicks);
		drawButtons(x, y, partialTicks);
		drawCharacterTexture();
		drawCharacterTopicString();
		drawCharacterNameString();
	}

	public void drawButtons(int x, int y, float partialTicks) {
		int j;
		for (j = 0; j < this.buttonList.size(); ++j) {
			this.buttonList.get(j).drawButton(this.mc, x, y, partialTicks);
		}
	}

	public void drawCharacterTexture() {
		mc.getTextureManager().bindTexture(npcTexture);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		drawScaledCustomSizeModalRect(this.width / 2 - 94, this.height / 2 - 148, 0, 0, 256, 256, 192, 192, 256, 256);
	}

	public void drawCharacterTopicString() {
		drawCenteredString(mc.getRenderManager().getFontRenderer(), TextFormatting.ITALIC + I18n.format("'IJEIHFIEHFVIVHEIJFAP CEFJ OIJAOCE JVJOIWJEOFJ OCJAOIJECOIJOIEJOI JAO'"), this.width / 2, this.height / 2 + 32, 0xffffff);
	}

	public void drawCharacterNameString() {
		drawCenteredString(mc.getRenderManager().getFontRenderer(), TextFormatting.BOLD + I18n.format("The Architect"), this.width / 2, this.height / 2 - 142, 0xffffff);
	}

	public void drawCenteredString(FontRenderer fontRenderer, String text, int x, int y, int color) {
		fontRenderer.drawStringWithShadow(text, (float) (x - fontRenderer.getStringWidth(text) / 2), (float) y, color);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}
}
