package net.jitl.client.render.gui.button;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JImageButton extends ImageButton {

	private final ResourceLocation resourceLocation;
	private final int xTexStart;
	private final int yTexStart;
	private final int yDiffTex;
	private final int textureWidth;
	private final int textureHeight;

	public JImageButton(int xIn, int yIn, int widthIn, int heightIn, int xTexStartIn, int yTexStartIn, int yDiffTextIn, ResourceLocation resourceLocationIn, OnPress onPressIn) {
		this(xIn, yIn, widthIn, heightIn, xTexStartIn, yTexStartIn, yDiffTextIn, resourceLocationIn, 256, 256, onPressIn);
	}

	public JImageButton(int xIn, int yIn, int widthIn, int heightIn, int xTexStartIn, int yTexStartIn, int yDiffTextIn, ResourceLocation resourceLocationIn, int int1_, int int_, OnPress onPressIn) {
		this(xIn, yIn, widthIn, heightIn, xTexStartIn, yTexStartIn, yDiffTextIn, resourceLocationIn, int1_, int_, onPressIn, TextComponent.EMPTY);
	}

	public JImageButton(int int1_, int int2_, int int3_, int int4_, int int5_, int int6_, int int7_, ResourceLocation resourceLocation_, int int8_, int int_, OnPress pressable_, Component textComponent_) {
		super(int1_, int2_, int3_, int4_, int5_, int6_, int7_, resourceLocation_, int8_, int_, pressable_, textComponent_);
		this.textureWidth = int8_;
		this.textureHeight = int_;
		this.xTexStart = int5_;
		this.yTexStart = int6_;
		this.yDiffTex = int7_;
		this.resourceLocation = resourceLocation_;
	}

	@Override
	public void setPosition(int xIn, int yIn) {
		super.setPosition(xIn, yIn);
	}

	@Override
	public void renderButton(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		if (this.visible) {
			Minecraft minecraft = Minecraft.getInstance();
			minecraft.getTextureManager().bind(resourceLocation);

			RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.alpha);

			this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width
					&& mouseY < this.y + this.height;

			int i = this.yTexStart;

			if (isHovered) {
				i += this.yDiffTex;
			}

			RenderSystem.enableBlend();
			RenderSystem.defaultBlendFunc();
			RenderSystem.enableDepthTest();

			this.blit(matrixStack, this.x, this.y, 0, i, this.width, this.height);

			blit(matrixStack, this.x, this.y, (float) this.xTexStart, (float) i, this.width, this.height, this.textureWidth, this.textureHeight);

			this.renderBg(matrixStack, minecraft, mouseX, mouseY);
		}
	}
}
