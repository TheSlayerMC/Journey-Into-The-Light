package net.jitl.util;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.ITextProperties;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.util.Collections;
import java.util.List;

public class JRenderUtils {

	public static class Text {
		public static void renderTooltip(MatrixStack matrixStack, ITextComponent text, int mouseX, int mouseY, int textWidth, int textHeight) {
			renderComponentTooltip(matrixStack, Collections.singletonList(text), mouseX, mouseY, textWidth, textHeight);
		}

		public static void renderComponentTooltip(MatrixStack matrixStack_, List<ITextComponent> textComponentList, int mouseX, int mouseY, int textWidth, int textHeight) {
			FontRenderer font = Minecraft.getInstance().font;
			renderWrappedToolTip(matrixStack_, textComponentList, mouseX, mouseY, textWidth, textHeight, font);
		}

		public static void renderWrappedToolTip(MatrixStack matrixStack, List<? extends ITextProperties> tooltips, int mouseX, int mouseY, int textWidth, int textHeight, FontRenderer font) {
			GuiUtils.drawHoveringText(matrixStack, tooltips, mouseX, mouseY, textWidth, textHeight, -1, font);
		}
	}
}
