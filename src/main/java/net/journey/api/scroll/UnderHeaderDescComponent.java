package net.journey.api.scroll;

import net.journey.JITL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

/*
 * Code by TimeConqueror
 */
public class UnderHeaderDescComponent implements IDescComponent {

    private static final String type = "under_header";

    private static final int arrowWidth = 86;
    private static final int arrowHeight = 59;
    private static final int logoWidth = 64;
    private static final int logoHeight = 64;
    private static final int requestedHeight = 24;

    private ResourceLocation content;
    private int contentHeight;

    public UnderHeaderDescComponent() {
        this.content = new ResourceLocation(JITL.MOD_ID, "textures/gui/gui_scroll_base.png");
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getContentPartHeight() {
        return contentHeight;
    }

    @Override
    public Object getContentPart() {
        return content;
    }

    @Override
    public void drawContentPart(int x0, int y0, int width) {

        int indentFromLogo = 4;
        drawImage(x0 + 1 + (int) ((double) width / 2 - ((double) logoWidth * requestedHeight / logoHeight) / 2), y0, 192, 0, logoWidth * requestedHeight / logoHeight, requestedHeight, logoWidth, logoHeight);
        int lastWidth = width - logoWidth * requestedHeight / logoHeight;

        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();

        drawImage(x0, y0, 1, 136, (int) ((double) lastWidth / 1.85 - indentFromLogo) + 2, requestedHeight, arrowWidth, arrowHeight);
        drawImage(x0 + width - (int) ((double) lastWidth / 1.85 - indentFromLogo), y0, 127, 136, (int) ((double) lastWidth / 1.85 - indentFromLogo) + 2, requestedHeight, arrowWidth, arrowHeight);
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    protected void drawImage(int x, int y, int textureX, int textureY, int requestedWidth, int requestedHeight, int realWidth, int realHeight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.content);
        float zLevel = 200.0F;

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 0);
        GlStateManager.scale((double) requestedWidth / realWidth, (double) requestedHeight / realHeight, 0);
        drawTexturedModalRect(0, -2, textureX, textureY, realWidth, realHeight, zLevel);
        GlStateManager.popMatrix();
    }

    private void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height, float zLevel) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(x + 0, y + height, zLevel).tex((float) (textureX + 0) * 0.00390625F, (float) (textureY + height) * 0.00390625F).endVertex();
        bufferbuilder.pos(x + width, y + height, zLevel).tex((float) (textureX + width) * 0.00390625F, (float) (textureY + height) * 0.00390625F).endVertex();
        bufferbuilder.pos(x + width, y + 0, zLevel).tex((float) (textureX + width) * 0.00390625F, (float) (textureY + 0) * 0.00390625F).endVertex();
        bufferbuilder.pos(x + 0, y + 0, zLevel).tex((float) (textureX + 0) * 0.00390625F, (float) (textureY + 0) * 0.00390625F).endVertex();
        tessellator.draw();
    }

    @Override
    public void determineContentPartHeight(int width) {
        contentHeight = requestedHeight - 1;
    }
}
