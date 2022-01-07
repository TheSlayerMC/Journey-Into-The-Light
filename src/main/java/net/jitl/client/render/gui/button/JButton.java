package net.jitl.client.render.gui.button;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.JITL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JButton extends Button {

	public static final ResourceLocation BUTTONS_NORMAL = JITL.rl("textures/gui/widgets_normal.png");
	public static final ResourceLocation BUTTONS_MIRRORED = JITL.rl("textures/gui/widgets_mirrored.png");

	public boolean isMirrored;

	public JButton(int x, int y, int width, int height, Component title, OnPress pressedAction, boolean isMirrored) {
		super(x, y, width, height, title, pressedAction);
		this.isMirrored = isMirrored;
	}

	public JButton(int x, int y, int width, int height, Component title, OnPress pressedAction, OnTooltip onTooltip, boolean isMirrored) {
		super(x, y, width, height, title, pressedAction, onTooltip);
		this.isMirrored = isMirrored;
	}

	@Override
	public void renderButton(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		if (this.visible) {
			Minecraft minecraft = Minecraft.getInstance();
			Font fontrenderer = minecraft.font;
			RenderSystem.setShader(GameRenderer::getPositionTexShader);
			if (!this.isMirrored) {
				RenderSystem.setShaderTexture(0, BUTTONS_NORMAL);
			} else {
				RenderSystem.setShaderTexture(0, BUTTONS_MIRRORED);
			}
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
			int i = this.getYImage(this.isHoveredOrFocused());
			RenderSystem.enableBlend();
			RenderSystem.defaultBlendFunc();
			RenderSystem.enableDepthTest();

			this.blit(matrixStack, this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
			this.blit(matrixStack, this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);

			this.renderBg(matrixStack, minecraft, mouseX, mouseY);

			int j = 14737632;

			if (getFGColor() != 0) {
				j = getFGColor();
			} else if (!this.isFocused()) {
				j = 10526880;
			} else if (this.isHovered) {
				j = 16777120;
			}
			drawCenteredString(matrixStack, fontrenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | Mth.ceil(this.alpha * 255.0F) << 24);
		}
	}
}
