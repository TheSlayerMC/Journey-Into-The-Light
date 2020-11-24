package net.jitl.client.render.gui.button;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.jitl.JITL;
import net.jitl.config.JClientConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class ToggleMenuButton extends JImageButton {

	JClientConfig clientConfig = JClientConfig.INSTANCE;
	private final boolean menuEnabled = clientConfig.GUI_CATEGORY.isJITLMenuEnabled();

	private static final ITextComponent ENABLE_JITL_MENU = new TranslationTextComponent("jitl.tooltip.enable_menu");
	private static final ITextComponent DISABLE_JITL_MENU = new TranslationTextComponent("jitl.tooltip.disable_menu");

	public ToggleMenuButton(int xIn, int yIn, IPressable onPressIn) {
		super(xIn, yIn, 20, 20, 0, 0, 20, JITL.rl("textures/gui/title/menu_toggle.png"), 20, 40, onPressIn);
	}


	@Override
	public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		super.renderButton(matrixStack, mouseX, mouseY, partialTicks);
		if (isHovered()) {
			ITextComponent textkey = menuEnabled ? DISABLE_JITL_MENU : ENABLE_JITL_MENU;
			renderTextOverlay(matrixStack, mouseX, mouseY, textkey);
		}
	}

	private void renderTextOverlay(MatrixStack matrixStack, int mouseX, int mouseY, ITextComponent theme) {
		drawCenteredString(matrixStack, Minecraft.getInstance().font, TextFormatting.BOLD + theme.getString(), mouseX + 64, mouseY + 8, 0xaefeff);
	}

	@Override
	public void onRelease(double mouseX, double mouseY) {
		//TODO: add config toggle (might not be necessary if IPressable field in constructor toggles config option)
	}
}
