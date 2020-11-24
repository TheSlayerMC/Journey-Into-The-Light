package net.jitl.client.render.gui.button;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.jitl.JITL;
import net.jitl.config.JClientConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Collections;
import java.util.List;

public class ToggleMenuButton extends JImageButton {

	JClientConfig clientConfig = JClientConfig.INSTANCE;
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
			ITextComponent textkey = menuEnabled ? DISABLE_JITL_MENU : ENABLE_JITL_MENU;
			renderTooltip(matrixStack, textkey, 16, 0);
			//renderTextOverlay(matrixStack, mouseX, mouseY, textkey);
		}
	}

	public void renderTooltip(MatrixStack matrixStack, ITextComponent text, int mouseX, int mouseY) {
		this.renderComponentTooltip(matrixStack, Collections.singletonList(text), mouseX, mouseY);
	}

	public void renderComponentTooltip(MatrixStack matrixStack_, List<ITextComponent> list_, int int_, int int1_) {
		FontRenderer font = Minecraft.getInstance().font;
		this.renderWrappedToolTip(matrixStack_, list_, int_, int1_, font);
	}

	public void renderWrappedToolTip(MatrixStack matrixStack, List<? extends net.minecraft.util.text.ITextProperties> tooltips, int mouseX, int mouseY, FontRenderer font) {
		net.minecraftforge.fml.client.gui.GuiUtils.drawHoveringText(matrixStack, tooltips, mouseX, mouseY, TEXT_WIDTH, TEXT_HEIGHT, -1, font);
	}
}
