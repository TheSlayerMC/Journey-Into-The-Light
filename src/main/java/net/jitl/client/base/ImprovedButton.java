package net.jitl.client.base;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class ImprovedButton extends Button {
    @Nullable
    public ResourceLocation textureLocation = AbstractWidget.WIDGETS_LOCATION;
    public Alignment alignment = Alignment.CENTER;

    public ImprovedButton(int x_, int y_, int width_, int height_, Component message_, OnPress onPress_) {
        super(x_, y_, width_, height_, message_, onPress_);
    }

    public ImprovedButton(int x_, int y_, int width_, int height_, Component message_, OnPress onPress_, OnTooltip onTooltip_) {
        super(x_, y_, width_, height_, message_, onPress_, onTooltip_);
    }

    @Override
    protected void renderBg(PoseStack poseStack_, Minecraft minecraft_, int mouseX_, int mouseY_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
        int i = this.getYImage(this.isHoveredOrFocused());
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        if (textureLocation != null) {
            RenderSystem.setShaderTexture(0, textureLocation);
            this.blit(poseStack_, this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
            this.blit(poseStack_, this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
        }
        super.renderBg(poseStack_, minecraft_, mouseX_, mouseY_);
    }

    //copypaste from AbstractWidget#renderButton but with custom textureLocation and string centering
    @Override
    public void renderButton(PoseStack poseStack_, int mouseX_, int mouseY_, float partialTick_) {
        Minecraft minecraft = Minecraft.getInstance();
        renderBg(poseStack_, minecraft, mouseX_, mouseY_);
        Font font = minecraft.font;

        int color = getFGColor() | Mth.ceil(this.alpha * 255.0F) << 24;
        Component message = getMessage();
        int x = this.x;
        if (alignment == Alignment.CENTER) {
            x = this.x + width / 2 - font.width(message) / 2;
        } else if (alignment == Alignment.RIGHT) {
            x = this.x + width - font.width(message);
        }

        drawString(poseStack_, font, message, x, y + (height - 8) / 2, color);
    }
}
