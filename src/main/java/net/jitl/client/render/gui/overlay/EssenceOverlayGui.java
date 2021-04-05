package net.jitl.client.render.gui.overlay;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.JITL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;

public class EssenceOverlayGui extends AbstractGui {
    private float percent;
    Minecraft minecraft = Minecraft.getInstance();

    public void render(MatrixStack matrixStack) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bind(JITL.tl("gui/essence").fullLocation());
        this.blit(matrixStack, 0, 0, 0, 64, 64, 5);

        int i = (int) (percent * 64.0F);
        if (i > 0) {
            this.blit(matrixStack, 0, 0, 0, 64, i, 5);
        }
    }
}
