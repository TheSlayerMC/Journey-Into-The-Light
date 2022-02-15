package net.jitl.client.render.gui.button;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.util.RenderUtils;
import net.jitl.core.JITL;
import net.jitl.core.config.JClientConfig;
import net.jitl.core.config.JConfigs;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class ToggleMenuButton extends JImageButton {

	JClientConfig clientConfig = JConfigs.CLIENT;
	private final boolean menuEnabled = clientConfig.guiCategory.isJITLMenuEnabled();

	private static final int TEXT_WIDTH = 128;
	private static final int TEXT_HEIGHT = 8;

	private static final Component ENABLE_JITL_MENU = new TranslatableComponent("jitl.tooltip.enable_menu");
	private static final Component DISABLE_JITL_MENU = new TranslatableComponent("jitl.tooltip.disable_menu");

	public ToggleMenuButton(int xIn, int yIn, OnPress onPressIn) {
		super(xIn, yIn, 20, 20, 0, 0, 20, JITL.rl("textures/gui/title/menu_toggle.png"), 20, 40, onPressIn);
	}

	@Override
	public void renderButton(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		super.renderButton(matrixStack, mouseX, mouseY, partialTicks);
		if (isHovered) {
            Component textKey = menuEnabled ? DISABLE_JITL_MENU : ENABLE_JITL_MENU;
            RenderUtils.renderTooltip(matrixStack, textKey, 16, 18, TEXT_WIDTH, TEXT_HEIGHT);
        }
	}
}
