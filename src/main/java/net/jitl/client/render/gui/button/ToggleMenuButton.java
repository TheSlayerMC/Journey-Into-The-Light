package net.jitl.client.render.gui.button;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.jitl.JITL;
import net.jitl.config.JClientConfig;
import net.jitl.config.JConfigs;
import net.jitl.util.JRenderUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ToggleMenuButton extends JImageButton {

	JClientConfig clientConfig = JConfigs.CLIENT;
	private final boolean menuEnabled = clientConfig.GUI_CATEGORY.isJITLMenuEnabled();

	private static final int TEXT_WIDTH = 128;
	private static final int TEXT_HEIGHT = 8;

	private static final ITextComponent ENABLE_JITL_MENU = new TranslationTextComponent("jitl.tooltip.enable_menu");
	private static final ITextComponent DISABLE_JITL_MENU = new TranslationTextComponent("jitl.tooltip.disable_menu");

	public ToggleMenuButton(int xIn, int yIn, IPressable onPressIn) {
		super(xIn, yIn, 20, 20, 0, 0, 20, JITL.rl("textures/gui/title/menu_toggle.png"), 20, 40, onPressIn);
	}

	@Override
	public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		super.renderButton(matrixStack, mouseX, mouseY, partialTicks);
		if (isHovered()) {
			ITextComponent textKey = menuEnabled ? DISABLE_JITL_MENU : ENABLE_JITL_MENU;
			JRenderUtils.Text.renderTooltip(matrixStack, textKey, 16, 0, TEXT_WIDTH, TEXT_HEIGHT);
		}
	}
}
