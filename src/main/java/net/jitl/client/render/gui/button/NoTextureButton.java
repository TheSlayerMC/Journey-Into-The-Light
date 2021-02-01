package net.jitl.client.render.gui.button;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.jitl.common.helper.ArgbColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

public class NoTextureButton extends Widget {
    public NoTextureButton(int x, int y, int width, int height, ITextComponent title) {
        super(x, y, width, height, title);
    }

    public NoTextureButton(int x, int y, ITextComponent title) {
        super(x, y, 276, 20, title);
    }

    @Override
    public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {
            Minecraft mc = Minecraft.getInstance();
            FontRenderer fontrenderer = mc.font;
            isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width
                    && mouseY < this.y + this.height;
            int j = ArgbColor.from(TextFormatting.WHITE);

            if (this.packedFGColor != 0) {
                j = this.packedFGColor;
            } else if (!this.active) {
                j = ArgbColor.from(TextFormatting.BLUE);
            } else if (this.isHovered) {
                j = ArgbColor.from(TextFormatting.YELLOW);
            }
            drawString(matrixStack, fontrenderer, this.getMessage(), this.x + this.width / this.x, this.y + (this.height - 8) / 2, j);
        }
    }
}
