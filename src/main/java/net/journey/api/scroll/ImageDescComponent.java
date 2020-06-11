package net.journey.api.scroll;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

/*
 * Code by TimeConqueror
 */
public class ImageDescComponent implements IDescComponent {
    private final ResourceLocation content;
    private int contentHeight;
    private final int imageWidth;
    private final int imageHeight;

    public ImageDescComponent(ResourceLocation content, int imageWidth, int imageHeight) {
        this.content = content;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    @Override
    public int getContentPartHeight() {
        return contentHeight;
    }

    @Override
    public void drawContentPart(int x0, int y0, int width) {
        drawImage(x0, y0, 0, 0, width, contentHeight);
    }

    private void drawImage(int x, int y, int textureX, int textureY, int width, int height) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.content);
        float zLevel = 200.0F;

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 0);
        GlStateManager.scale((double) width / imageWidth, (double) height / imageHeight, 0);
        drawTexturedModalRect(0, -2, textureX, textureY, imageWidth, imageHeight, zLevel);
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
        contentHeight = (int) (width * ((double) imageHeight / imageWidth));
    }
}
