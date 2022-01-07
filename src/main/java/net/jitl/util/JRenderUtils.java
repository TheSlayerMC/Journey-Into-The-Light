package net.jitl.util;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraftforge.client.gui.GuiUtils;

import java.util.Collections;
import java.util.List;

public class JRenderUtils {

	public static class Text {
		public static void renderTooltip(PoseStack matrixStack, Component text, int mouseX, int mouseY, int textWidth, int textHeight) {
			renderComponentTooltip(matrixStack, Collections.singletonList(text), mouseX, mouseY, textWidth, textHeight);
		}

		public static void renderComponentTooltip(PoseStack matrixStack_, List<Component> textComponentList, int mouseX, int mouseY, int textWidth, int textHeight) {
			Font font = Minecraft.getInstance().font;
			renderWrappedToolTip(matrixStack_, textComponentList, mouseX, mouseY, textWidth, textHeight, font);
		}

		public static void renderWrappedToolTip(PoseStack matrixStack, List<? extends FormattedText> tooltips, int mouseX, int mouseY, int textWidth, int textHeight, Font font) {
			GuiUtils.drawHoveringText(matrixStack, tooltips, mouseX, mouseY, textWidth, textHeight, -1, font);
			//FIXME drawHoveringText method is gone
		}
	}
}
