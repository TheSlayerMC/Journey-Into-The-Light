package net.journey.client.render.gui.tablet;

import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import net.journey.JITL;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.journey.api.item.scroll.IDescComponent;
import net.journey.api.item.scroll.ScrollEntry;
import net.journey.util.GuiUtils;;

/*
 * Code by TimeConqueror
 */
public class GuiLoreScrollEntry extends GuiScreen {

    private static ResourceLocation BG = new ResourceLocation(JITL.MOD_ID, "textures/gui/gui_scroll_base.png");
    private final int headerHeight = 30;
    private ScrollEntry ScrollEntry;
    private String parentCategoryName;
    private int guiWidth;
    private int guiHeight;
    private int guix0;
    private int guiy0;
    private int mouseX;
    private int mouseY;
    private int entryWidth;
    private int top;
    private int bottom;
    private int left;
    private float initialMouseClickY = -2.0F;
    private float scrollDistance;
    private int selectedIndex = -1;
    private long lastClickTime = 0L;
    private float scrollFactor;
    private boolean highlightSelected = true;

    public GuiLoreScrollEntry(String parentCategoryName, ScrollEntry ScrollEntry) {
        this.ScrollEntry = ScrollEntry;
        this.parentCategoryName = parentCategoryName;
    }

    /**
     * Draw anything special on the screen. GL_SCISSOR is enabled for anything that
     * is rendered outside of the view box. Do not mess with SCISSOR unless you support this.
     */
    private void drawScreen(int mouseX, int mouseY) {
    }

    /**
     * Returns count of parts in Entry's description.
     */
    private int getContentPartCount() {
        return ScrollEntry.getDesc().size();
    }

    /**
     * Returns full height of all parts.
     */
    private int getContentHeight() {
        int conHeight = this.headerHeight;
        for (IDescComponent part : ScrollEntry.getDesc()) {
            conHeight += part.getContentPartHeight();
        }
        return conHeight;
    }

    /**
     * Returns height of one existing part.
     */
    private int getContentPartHeight(int index) {
        return ScrollEntry.getDesc().get(index).getContentPartHeight();
    }

    /**
     * Initializes all parts' height. Must be called before calling drawing content!
     */
    private void determineAllContentPartHeight(int width) {
        for (IDescComponent part : ScrollEntry.getDesc()) {
            part.determineContentPartHeight(width);
        }
    }

    /**
     * Draws header and comment, if entry has it.
     */
    private void drawHeader(int maxX, int y0, Tessellator tess) {
        if (ScrollEntry.hasComment()) {
            GuiUtils.drawCenteredStringWithCustomScale(fontRenderer, ScrollEntry.getTitle(), left + (maxX - left) / 2 + 1, y0, (int) zLevel, 0xFFFFFF, 1.5F, headerHeight - 5);
            GuiUtils.drawCenteredStringWithCustomScale(fontRenderer, ScrollEntry.getComment(), left + (maxX - left) / 2 + 1, y0 + (int) ((float) fontRenderer.FONT_HEIGHT * 0.7), (int) zLevel, 0xFFFFFF, 1F, headerHeight + 5);
        } else {
            GuiUtils.drawCenteredStringWithCustomScale(fontRenderer, ScrollEntry.getTitle(), left + (maxX - left) / 2 + 1, y0, (int) zLevel, 0xFFFFFF, 1.2F, headerHeight);
        }
    }

    /**
     * Draws Content Part.
     */
    private void drawContentPart(int partIdx, int contentRight, int partTop, int partBuffer, Tessellator tess) {
        ScrollEntry.getDesc().get(partIdx).drawContentPart(this.left + 2, partTop, contentRight);
    }

    private void applyScrollLimits() {
        int listHeight = this.getContentHeight() - (this.bottom - this.top - 4);

        if (listHeight < 0) {
            listHeight /= 2;
        }

        if (this.scrollDistance < 0.0F) {
            this.scrollDistance = 0.0F;
        }

        if (this.scrollDistance > (float) listHeight) {
            this.scrollDistance = (float) listHeight;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();

        mc.getTextureManager().bindTexture(BG);

        int heightRectCount = (height - (height <= 480 ? 12 : 48)) / 32;
        int widthRectCount = height <= 480 ? 6 : 10;

        this.guiWidth = widthRectCount * 32;
        this.guiHeight = heightRectCount * 32;

        this.guix0 = width / 2 - guiWidth / 2;
        this.guiy0 = height / 2 - guiHeight / 2;

        for (int x = 0; x < widthRectCount; x++) {
            for (int y = 0; y < heightRectCount; y++) {
                int textureX = x == 0 ? 0 : (x == widthRectCount - 1 ? 64 : 32);
                int textureY = y == 0 ? 0 : (y == heightRectCount - 1 ? 64 : 32);
                super.drawTexturedModalRect(guix0 + x * 32, guiy0 + y * 32, textureX, textureY, 32, 32);
            }
        }

        drawScrollingContent(mouseX, mouseY, partialTicks);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void drawScrollingContent(int mouseX, int mouseY, float partialTicks) {

        int indent = 17;
        this.left = guix0 + indent + 4;
        this.top = guiy0 + indent;
        this.entryWidth = guiWidth - indent * 2;
        int entryHeight = guiHeight - indent * 2;
        this.bottom = top + entryHeight;

        this.mouseX = mouseX;
        this.mouseY = mouseY;

        boolean isHovering = mouseX >= this.left && mouseX <= this.left + this.entryWidth && mouseY >= this.top && mouseY <= this.bottom;
        int entryLength = this.getContentPartCount();
        int scrollButtonWidth = 3;
        int scrollButtonRightTop = this.left + this.entryWidth;
        int scrollButtonLeftTop = scrollButtonRightTop - scrollButtonWidth;
        int contentRightTop = scrollButtonLeftTop - 5;

        determineAllContentPartHeight(contentRightTop - this.left - 4);

        int viewHeight = this.bottom - this.top;
        int border = 4;

        if (Mouse.isButtonDown(0)) {
            if (this.initialMouseClickY == -1.0F) {
                if (isHovering) {
                    int mouseListY = mouseY - this.top - this.headerHeight + (int) this.scrollDistance - border;
                    if (mouseX >= scrollButtonLeftTop && mouseX <= scrollButtonRightTop) {
                        this.scrollFactor = -1.0F;
                        int scrollHeight = this.getContentHeight() - viewHeight - border;
                        if (scrollHeight < 1) scrollHeight = 1;

                        int var13 = (int) ((float) (viewHeight * viewHeight) / (float) this.getContentHeight());

                        if (var13 < 32) var13 = 32;
                        if (var13 > viewHeight - border * 2)
                            var13 = viewHeight - border * 2;

                        this.scrollFactor /= (float) (viewHeight - var13) / (float) scrollHeight;

                    } else {
                        this.scrollFactor = 1.0F;
                    }

                    this.initialMouseClickY = mouseY;
                } else {
                    this.initialMouseClickY = -2.0F;
                }
            } else if (this.initialMouseClickY >= 0.0F) {
                this.scrollDistance -= ((float) mouseY - this.initialMouseClickY) * this.scrollFactor;
                this.initialMouseClickY = (float) mouseY;
            }
        } else {
            this.initialMouseClickY = -1.0F;
        }

        this.applyScrollLimits();

        Tessellator tess = Tessellator.getInstance();
        BufferBuilder worldr = tess.getBuffer();

        ScaledResolution res = new ScaledResolution(mc);
        double scaleW = mc.displayWidth / res.getScaledWidth_double();
        double scaleH = mc.displayHeight / res.getScaledHeight_double();
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor((int) (left * scaleW), (int) (mc.displayHeight - (bottom * scaleH)), (int) (entryWidth * scaleW), (int) (viewHeight * scaleH));

        int baseY = this.top + border - (int) this.scrollDistance;
        int indentY = 0;

        GlStateManager.color(1F, 1F, 1F, 1F);

        for (int partIdx = 0; partIdx < entryLength; ++partIdx) {
            int partTop = baseY + this.headerHeight + indentY;
            int partBuffer = getContentPartHeight(partIdx) - border;

            if (baseY + headerHeight >= top) {
                drawHeader(contentRightTop, baseY, tess);
            }

            if (partTop <= this.bottom && partTop + partBuffer >= this.top) {
                int min = this.left;
                int max = contentRightTop;

                /* FOR DEBUG:
                 * Draws borders and background of each part.
                 *
                 * */
//                    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
//                    GlStateManager.disableTexture2D();
//                    worldr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
//                    worldr.pos(min, partTop + partBuffer + 2, 0).tex(0, 1).color(0x80, 0x80, 0x80, 0xFF).endVertex();
//                    worldr.pos(max, partTop + partBuffer + 2, 0).tex(1, 1).color(0x80, 0x80, 0x80, 0xFF).endVertex();
//                    worldr.pos(max, partTop - 2, 0).tex(1, 0).color(0x80, 0x80, 0x80, 0xFF).endVertex();
//                    worldr.pos(min, partTop - 2, 0).tex(0, 0).color(0x80, 0x80, 0x80, 0xFF).endVertex();
//                    worldr.pos(min + 1, partTop + partBuffer + 1, 0).tex(0, 1).color(0x00, 0x00, 0x00, 0xFF).endVertex();
//                    worldr.pos(max - 1, partTop + partBuffer + 1, 0).tex(1, 1).color(0x00, 0x00, 0x00, 0xFF).endVertex();
//                    worldr.pos(max - 1, partTop - 1, 0).tex(1, 0).color(0x00, 0x00, 0x00, 0xFF).endVertex();
//                    worldr.pos(min + 1, partTop - 1, 0).tex(0, 0).color(0x00, 0x00, 0x00, 0xFF).endVertex();
//                    tess.draw();
//                    GlStateManager.enableTexture2D();

                this.drawContentPart(partIdx, max - min - 4, partTop, partBuffer, tess);
            }
            indentY += ScrollEntry.getDesc().get(partIdx).getContentPartHeight();
        }

        GlStateManager.disableDepth();

        int extraHeight = (this.getContentHeight() + border) - viewHeight;
        if (extraHeight > 0) {
            int height = (viewHeight * viewHeight) / this.getContentHeight();
            if (height < 32) {
                height = 32;
            }
            if (height > viewHeight - border * 2) {
                height = viewHeight - border * 2;
            }
            int barTop = (int) this.scrollDistance * (viewHeight - height) / extraHeight + this.top;
            if (barTop < this.top) {
                barTop = this.top;
            }

            GlStateManager.disableTexture2D();
            worldr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
            GlStateManager.enableAlpha();
            worldr.pos(scrollButtonLeftTop, this.bottom, 0.0D).tex(0.0D, 1.0D).color(0x00, 0x00, 0x00, 0x10).endVertex();
            worldr.pos(scrollButtonRightTop, this.bottom, 0.0D).tex(1.0D, 1.0D).color(0x00, 0x00, 0x00, 0x10).endVertex();
            worldr.pos(scrollButtonRightTop, this.top, 0.0D).tex(1.0D, 0.0D).color(0x00, 0x00, 0x00, 0x10).endVertex();
            worldr.pos(scrollButtonLeftTop, this.top, 0.0D).tex(0.0D, 0.0D).color(0x00, 0x00, 0x00, 0x10).endVertex();
            tess.draw();
            GlStateManager.disableAlpha();
            worldr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldr.pos(scrollButtonLeftTop, barTop + height, 0.0D).tex(0.0D, 1.0D).color(0x51, 0xAF, 0xBA, 0xFF).endVertex();
            worldr.pos(scrollButtonRightTop, barTop + height, 0.0D).tex(1.0D, 1.0D).color(0x51, 0xAF, 0xBA, 0xFF).endVertex();
            worldr.pos(scrollButtonRightTop, barTop, 0.0D).tex(1.0D, 0.0D).color(0x51, 0xAF, 0xBA, 0xFF).endVertex();
            worldr.pos(scrollButtonLeftTop, barTop, 0.0D).tex(0.0D, 0.0D).color(0x51, 0xAF, 0xBA, 0xFF).endVertex();
            tess.draw();
            worldr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldr.pos(scrollButtonLeftTop, barTop + height - 1, 0.0D).tex(0.0D, 1.0D).color(0x6C, 0xCA, 0xD5, 0xFF).endVertex();
            worldr.pos(scrollButtonRightTop - 1, barTop + height - 1, 0.0D).tex(1.0D, 1.0D).color(0x6C, 0xCA, 0xD5, 0xFF).endVertex();
            worldr.pos(scrollButtonRightTop - 1, barTop, 0.0D).tex(1.0D, 0.0D).color(0x6C, 0xCA, 0xD5, 0xFF).endVertex();
            worldr.pos(scrollButtonLeftTop, barTop, 0.0D).tex(0.0D, 0.0D).color(0x6C, 0xCA, 0xD5, 0xFF).endVertex();
            tess.draw();
        }

        this.drawScreen(mouseX, mouseY);
        GlStateManager.enableTexture2D();
        GlStateManager.shadeModel(GL11.GL_FLAT);
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    @Override
    public void handleMouseInput() {
        boolean isHovering = mouseX >= this.left && mouseX <= this.left + this.entryWidth &&
                mouseY >= this.top && mouseY <= this.bottom;
        if (!isHovering)
            return;

        int scroll = Mouse.getEventDWheel();
        if (scroll != 0) {
            this.scrollDistance += (-1 * scroll / 120.0F) * 10;
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == 1) {
            this.mc.displayGuiScreen(null);
            this.mc.displayGuiScreen(new GuiLoreScroll(parentCategoryName));
        }
    }
}
