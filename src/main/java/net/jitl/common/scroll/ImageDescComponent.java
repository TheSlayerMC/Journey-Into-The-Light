package net.jitl.common.scroll;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.resources.ResourceLocation;

import ResourceLocation;

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
    public void drawContentPart(PoseStack matrixStack, int x0, int y0, int width) {
        drawImage(x0, y0, 0, 0, width, contentHeight);
    }

    //TODO: remove GLStateManager methods. change to new render system
    private void drawImage(int x, int y, int textureX, int textureY, int width, int height) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getInstance().textureManager.bind(this.content);
        float zLevel = 200.0F;

        GlStateManager._scaled((double) width / imageWidth, (double) height / imageHeight, 0);
        drawTexturedModalRect(0, -2, textureX, textureY, imageWidth, imageHeight, zLevel);
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
        contentHeight = (int) (width * ((double) imageHeight / imageWidth));
    }
}