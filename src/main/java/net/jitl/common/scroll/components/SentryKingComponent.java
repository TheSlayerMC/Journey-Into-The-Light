package net.jitl.common.scroll.components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.jitl.common.scroll.IDescComponent;
import net.jitl.core.JITL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;

public class SentryKingComponent implements IDescComponent {
    private static final ResourceLocation textureLocation = JITL.rl("textures/gui/scroll_components/sentry_king.png");

    @Override
    public int getContentPartHeight() {
        return 64;
    }

    @Override
    public void drawContentPart(PoseStack matrixStack, int x0, int y0, int width) {
        Minecraft minecraft = Minecraft.getInstance();
        int width1 = width;
        int height = width;
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, textureLocation);
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(0.0D, height, -90.0D).uv(0.0F, 1.0F).endVertex();
        bufferbuilder.vertex(width1, height, -90.0D).uv(1.0F, 1.0F).endVertex();
        bufferbuilder.vertex(width1, 0.0D, -90.0D).uv(1.0F, 0.0F).endVertex();
        bufferbuilder.vertex(0.0D, 0.0D, -90.0D).uv(0.0F, 0.0F).endVertex();
        tesselator.end();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void determineContentPartHeight(int width) {
    }
}
