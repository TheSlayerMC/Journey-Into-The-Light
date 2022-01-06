package net.jitl.common.scroll;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.jitl.JITL;
import net.minecraft.client.Minecraft;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.resources.ResourceLocation;

import ResourceLocation;

/*
 * Code by TimeConqueror
 */
public class UnderHeaderDescComponent implements IDescComponent {
    private static final int arrowWidth = 86;
    private static final int arrowHeight = 59;
    private static final int logoWidth = 64;
    private static final int logoHeight = 64;
    private static final int requestedHeight = 24;

    private final ResourceLocation content;
    private int contentHeight;

    public UnderHeaderDescComponent() {
        this.content = JITL.rl("textures/gui/scroll_base.png");
    }

    @Override
    public int getContentPartHeight() {
        return contentHeight;
    }

    //TODO: remove GLStateManager methods, change to new render system
    @Override
    public void drawContentPart(PoseStack matrixStack, int x0, int y0, int width) {
        GlStateManager._pushMatrix();
        GlStateManager._enableBlend();
        GlStateManager._enableAlphaTest();

        int indentFromLogo = 4;
        drawImage(x0 + 1 + (int) ((double) width / 2 - ((double) logoWidth * requestedHeight / logoHeight) / 2), y0, 192, 0, logoWidth * requestedHeight / logoHeight, requestedHeight, logoWidth, logoHeight);
        int lastWidth = width - logoWidth * requestedHeight / logoHeight;

        drawImage(x0, y0, 1, 136, (int) ((double) lastWidth / 1.85 - indentFromLogo) + 2, requestedHeight, arrowWidth, arrowHeight);
        drawImage(x0 + width - (int) ((double) lastWidth / 1.85 - indentFromLogo), y0, 127, 136, (int) ((double) lastWidth / 1.85 - indentFromLogo) + 2, requestedHeight, arrowWidth, arrowHeight);
        GlStateManager._disableAlphaTest();
        GlStateManager._disableBlend();
        GlStateManager._popMatrix();
    }

    protected void drawImage(int x, int y, int textureX, int textureY, int requestedWidth, int requestedHeight, int realWidth, int realHeight) {
        Minecraft.getInstance().textureManager.bind(this.content);
        float zLevel = 200.0F;

        GlStateManager._pushMatrix();
        GlStateManager._translatef(x, y, 0);
        GlStateManager._scaled((double) requestedWidth / realWidth, (double) requestedHeight / realHeight, 0);
        drawTexturedModalRect(0, -2, textureX, textureY, realWidth, realHeight, zLevel);
        GlStateManager._popMatrix();
    }

    private void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height, float zLevel) {
        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        bufferbuilder.begin(7, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(x + 0, y + height, zLevel).uv((float) (textureX + 0) * 0.00390625F, (float) (textureY + height) * 0.00390625F).endVertex();
        bufferbuilder.vertex(x + width, y + height, zLevel).uv((float) (textureX + width) * 0.00390625F, (float) (textureY + height) * 0.00390625F).endVertex();
        bufferbuilder.vertex(x + width, y + 0, zLevel).uv((float) (textureX + width) * 0.00390625F, (float) (textureY + 0) * 0.00390625F).endVertex();
        bufferbuilder.vertex(x + 0, y + 0, zLevel).uv((float) (textureX + 0) * 0.00390625F, (float) (textureY + 0) * 0.00390625F).endVertex();
        tessellator.end();
    }

    @Override
    public void determineContentPartHeight(int width) {
        contentHeight = requestedHeight - 1;
    }
}