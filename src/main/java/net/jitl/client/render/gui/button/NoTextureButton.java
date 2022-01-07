package net.jitl.client.render.gui.button;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.common.helper.ArgbColor;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class NoTextureButton extends AbstractWidget {
    public NoTextureButton(int x, int y, int width, int height, Component title) {
        super(x, y, width, height, title);
    }

    public NoTextureButton(int x, int y, Component title) {
        super(x, y, 276, 20, title);
    }

    @Override
    public void renderButton(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {
            Minecraft mc = Minecraft.getInstance();
            Font fontrenderer = mc.font;
            isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width
                    && mouseY < this.y + this.height;
            int j = ArgbColor.from(ChatFormatting.WHITE);

            if (this.packedFGColor != 0) {
                j = this.packedFGColor;
            } else if (!this.active) {
                j = ArgbColor.from(ChatFormatting.BLUE);
            } else if (this.isHovered) {
                j = ArgbColor.from(ChatFormatting.YELLOW);
            }
            drawString(matrixStack, fontrenderer, this.getMessage(), this.x + this.width / this.x, this.y + (this.height - 8) / 2, j);
        }
    }

    @Override
    public void updateNarration(@NotNull NarrationElementOutput narrationElementOutput_) {

    }
}
