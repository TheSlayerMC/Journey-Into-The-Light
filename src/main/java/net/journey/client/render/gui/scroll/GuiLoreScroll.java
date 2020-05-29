package net.journey.client.render.gui.scroll;

import net.journey.JITL;
import net.journey.api.scroll.ScrollAPI;
import net.journey.api.scroll.ScrollCategory;
import net.journey.api.scroll.ScrollEntry;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.config.GuiUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/*
 * Code by TimeConqueror
 */
public class GuiLoreScroll extends GuiScreen {
    private static final ResourceLocation BG = new ResourceLocation(JITL.MOD_ID, "textures/gui/gui_scroll_base.png");
    public ScrollCategory currentCategory;
    protected HashMap<Integer, GuiEntryButton> entryButtonList = new HashMap<>();
    //For Dragging Menu
    private int draggingCategoryWidth;
    private int draggingCategoryHeight;
    private int left;
    private int right;
    private int top;
    private int bottom;
    private int mouseX;
    private int mouseY;
    private float initialMouseClickY = -2.0F;
    private float initialMouseClickX = -2.0F;
    private float scrollDistance;
    private float scrollDistanceX;
    private float scrollFactor;
    private float scrollFactorX;
    private int categorySize;
    //For CategoryButtonList
    private int entryButtonSize = 20;
    private int buttonHeight = 20;
    private int buttonWidth = 60;
    private int border = 5;
    private int buttonListLeft;
    private int buttonListRight;
    private int buttonListTop;
    private int buttonListBottom;
    private float blInitialMouseClickY = -2.0F;
    private float blscrollFactor;
    private float blscrollDistance;
    private int catButtonsHeight;

    public GuiLoreScroll(String categoryName) {
        this.currentCategory = ScrollAPI.getCategoryByName(categoryName);
        categorySize = currentCategory.getCategorySize() * 256;
    }

    public GuiLoreScroll() {
        currentCategory = ScrollAPI.getFirstCategory();
        categorySize = currentCategory.getCategorySize() * 256;
    }

    @Override
    public void initGui() {
        int widthRectCount = (width - (width <= 720 ? 12 : 48)) / 32;
        int heightRectCount = (height - (height <= 480 ? 12 : 48)) / 32;

        int w = widthRectCount * 32;
        int h = heightRectCount * 32;

        int x0 = width / 2 - w / 2;
        int y0 = height / 2 - h / 2;

        for (int i = 0; i < ScrollAPI.categoryList.size() * 1000; i += 1000) {
            ScrollCategory category = ScrollAPI.getCategoryByIndex(i / 1000);
            addButton(new GuiCategoryButton(i, x0 + 25, y0 + 20 + i * buttonHeight + i * border, buttonWidth, buttonHeight, category.getCategoryName()));

            for (int j = 0; j < category.getEntryList().size(); j++) {
                ScrollEntry entry = ScrollAPI.getEntryByIndex(category, j);
                addEntryButton(new GuiEntryButton(i + j, entry.getxCoord(), entry.getyCoord(), entryButtonSize, entryButtonSize, entry, category.getCategoryName()));
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        drawDefaultBackground();

        categorySize = currentCategory.getCategorySize() * 256;
        mc.getTextureManager().bindTexture(BG);
        int widthRectCount = (width - (width <= 720 ? 12 : 48)) / 32;
        int heightRectCount = (height - (height <= 480 ? 12 : 48)) / 32;

        int w = widthRectCount * 32;
        int h = heightRectCount * 32;

        int x0 = width / 2 - w / 2;
        int y0 = height / 2 - h / 2;

        for (int x = 0; x < widthRectCount; x++) {
            for (int y = 0; y < heightRectCount; y++) {
                int textureX = x == 0 ? 0 : (x == widthRectCount - 1 ? 64 : 32);
                int textureY = y == 0 ? 0 : (y == heightRectCount - 1 ? 64 : 32);
                drawTexturedModalRect(x0 + x * 32, y0 + y * 32, textureX, textureY, 32, 32);
            }
        }

        drawTexturedModalRect(x0 + 93, y0 + 15, 100, 32, 5, 1);
        for (int y = 0; y < heightRectCount - 1; y++) {
            drawTexturedModalRect(x0 + 93, y0 + y * 32 + 16, 100, 0, 5, 33);
        }

        this.left = x0 + 98;
        this.top = y0 + 17;
        this.draggingCategoryWidth = w - left - 1;
        this.draggingCategoryHeight = h - 37;
        this.bottom = top + draggingCategoryHeight;
        this.right = left + draggingCategoryWidth;

        this.mouseX = mouseX;
        this.mouseY = mouseY;

        boolean isDraggingMenuHovering = mouseX >= this.left && mouseX <= right && mouseY >= this.top && mouseY <= this.bottom;
        int viewHeight = this.bottom - this.top;

        if (Mouse.isButtonDown(0)) {
            if (this.initialMouseClickX == -1.0F) {
                if (isDraggingMenuHovering) {
                    entryButtonList.forEach((key, entryButton) -> {
                                if (entryButton.id >= ScrollAPI.getIndexOfCategory(currentCategory) * 1000 && entryButton.id < 1000 + ScrollAPI.getIndexOfCategory(currentCategory) * 1000) {
                                    if (entryButton.mousePressed(mc, mouseX, mouseY)) {
                                        entryButton.mouseReleased(mouseX, mouseY);
                                    }
                                }
                            }
                    );
                    this.scrollFactorX = 1.0F;
                    this.initialMouseClickX = mouseX;
                } else {
                    this.initialMouseClickX = -2.0F;
                }
            } else if (this.initialMouseClickX >= 0.0F) {
                this.scrollDistanceX -= ((float) mouseX - this.initialMouseClickX) * this.scrollFactorX;
                this.initialMouseClickX = (float) mouseX;
            }
        } else {
            this.initialMouseClickX = -1.0F;
        }

        if (Mouse.isButtonDown(0)) {
            if (this.initialMouseClickY == -1.0F) {
                if (isDraggingMenuHovering) {
                    this.scrollFactor = 1.0F;
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

        this.applyScrollLimitsForDraggingMenu();

        ScaledResolution res = new ScaledResolution(mc);
        double scaleW = mc.displayWidth / res.getScaledWidth_double();
        double scaleH = mc.displayHeight / res.getScaledHeight_double();
        GlStateManager.pushMatrix();
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor((int) (left * scaleW), (int) (mc.displayHeight - (bottom * scaleH)),
                (int) (draggingCategoryWidth * scaleW), (int) (viewHeight * scaleH));

        int baseY = this.top - (int) this.scrollDistance;
        int baseX = this.left - (int) this.scrollDistanceX;

        mc.getTextureManager().bindTexture(currentCategory.getBackgroundTexture());

        for (int x = 0; x <= categorySize; x += 256) {
            for (int y = 0; y <= categorySize; y += 256) {
                if (baseX + x + 256 >= left && baseX + x <= right && baseY + y + 256 >= top && baseY + y <= bottom) {
                    drawTexturedModalRect(baseX + x, baseY + y, 0, 0, 256, 256);
                }
            }
        }

        for (int i = ScrollAPI.getIndexOfCategory(currentCategory) * 1000; i < currentCategory.getEntryCount() + ScrollAPI.getIndexOfCategory(currentCategory) * 1000; i++) {
            entryButtonList.get(i).x = baseX + entryButtonList.get(i).initialX;
            entryButtonList.get(i).y = baseY + entryButtonList.get(i).initialY;
            if (entryButtonList.get(i).x + entryButtonSize >= left && entryButtonList.get(i).x <= right &&
                    entryButtonList.get(i).y + entryButtonSize >= top && entryButtonList.get(i).y <= bottom) {
                entryButtonList.get(i).drawButton(mc, mouseX, mouseY, partialTicks, itemRender);
            }
        }

        GlStateManager.disableDepth();

        GlStateManager.enableTexture2D();
        GlStateManager.shadeModel(GL11.GL_FLAT);
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        GlStateManager.popMatrix();

        drawCategoryButtons(x0, y0, mouseX, mouseY, partialTicks);

        entryButtonList.forEach((k, button) -> {
            if (button.hovered)
                renderEntryInfo(fontRenderer, button);
        });

        super.drawScreen(mouseX, mouseY, partialTicks);

    }

    private void drawCategoryButtons(int x0, int y0, int mouseX, int mouseY, float partialTicks) {
        buttonListLeft = x0 + 25;
        buttonListRight = buttonListLeft + buttonWidth;
        buttonListTop = y0 + 20;
        buttonListBottom = buttonListTop + draggingCategoryHeight;
        int scrollButtonWidth = 3;
        int scrollButtonRightTop = x0 + 93;
        int scrollButtonLeftTop = scrollButtonRightTop - scrollButtonWidth;

        boolean isCatButtonListHovering = (mouseX >= buttonListLeft && mouseX <= buttonListRight && mouseY >= buttonListTop && mouseY <= buttonListBottom) || (mouseX >= scrollButtonLeftTop && mouseX <= scrollButtonRightTop);
        int listViewHeight = buttonListBottom - buttonListTop;

        if (Mouse.isButtonDown(0)) {
            if (this.blInitialMouseClickY == -1.0F) {
                if (isCatButtonListHovering) {
                    for (GuiButton button : buttonList) {
                        if (button.mousePressed(mc, mouseX, mouseY)) {
                            currentCategory = ScrollAPI.getCategoryByName(button.displayString);
                        }
                    }

                    if (mouseX >= scrollButtonLeftTop && mouseX <= scrollButtonRightTop) {
                        this.blscrollFactor = -1.0F;
                        int scrollHeight = catButtonsHeight - listViewHeight - border;
                        if (scrollHeight < 1) scrollHeight = 1;

                        int var13 = (int) ((float) (listViewHeight * listViewHeight) / (float) catButtonsHeight);

                        if (var13 < 32) var13 = 32;
                        if (var13 > listViewHeight - border * 2)
                            var13 = listViewHeight - border * 2;

                        this.blscrollFactor /= (float) (listViewHeight - var13) / (float) scrollHeight;

                    } else {
                        this.blscrollFactor = 1.0F;
                    }

                    this.blInitialMouseClickY = mouseY;
                } else {
                    this.blInitialMouseClickY = -2.0F;
                }
            } else if (this.blInitialMouseClickY >= 0.0F) {
                this.blscrollDistance -= ((float) mouseY - this.blInitialMouseClickY) * this.blscrollFactor;
                this.blInitialMouseClickY = (float) mouseY;
            }
        } else {
            this.blInitialMouseClickY = -1.0F;
        }

        this.applyScrollLimitsForCatButtons();

        Tessellator tess = Tessellator.getInstance();
        BufferBuilder worldr = tess.getBuffer();

        ScaledResolution res = new ScaledResolution(mc);
        double scaleW = mc.displayWidth / res.getScaledWidth_double();
        double scaleH = mc.displayHeight / res.getScaledHeight_double();
        GlStateManager.pushMatrix();
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor((int) (buttonListLeft * scaleW), (int) (mc.displayHeight - (buttonListBottom * scaleH)),
                (int) ((buttonListRight - buttonListLeft + 68) * scaleW), (int) (listViewHeight * scaleH));

        int baseY = this.buttonListTop - (int) this.blscrollDistance;

        for (int i = 0; i < buttonList.size(); i++) {
            buttonList.get(i).y = baseY + i * buttonHeight + i * border;
            if (buttonList.get(i).y <= buttonListBottom && buttonList.get(i).y + buttonHeight >= buttonListTop) {
                ((GuiCategoryButton) buttonList.get(i)).drawButton(mouseX, mouseY, partialTicks);
            }
        }

        GlStateManager.disableDepth();

        int extraHeight = catButtonsHeight + ScrollAPI.categoryList.size() * border - listViewHeight;
        if (extraHeight > 0) {
            int height = (listViewHeight * listViewHeight) / catButtonsHeight;
            if (height < 32) {
                height = 32;
            }
            if (height > listViewHeight - border * 2) {
                height = listViewHeight - border * 2;
            }
            int barTop = (int) this.blscrollDistance * (listViewHeight - height) / extraHeight + this.buttonListTop;
            if (barTop < buttonListTop) {
                barTop = buttonListTop;
            }

            GlStateManager.disableTexture2D();
            worldr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
            GlStateManager.enableAlpha();
            worldr.pos(scrollButtonLeftTop, this.buttonListBottom, 0.0D).tex(0.0D, 1.0D).color(0x00, 0x00, 0x00, 0x10).endVertex();
            worldr.pos(scrollButtonRightTop, this.buttonListBottom, 0.0D).tex(1.0D, 1.0D).color(0x00, 0x00, 0x00, 0x10).endVertex();
            worldr.pos(scrollButtonRightTop, this.buttonListTop, 0.0D).tex(1.0D, 0.0D).color(0x00, 0x00, 0x00, 0x10).endVertex();
            worldr.pos(scrollButtonLeftTop, this.buttonListTop, 0.0D).tex(0.0D, 0.0D).color(0x00, 0x00, 0x00, 0x10).endVertex();
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

        GlStateManager.disableDepth();

        GlStateManager.enableTexture2D();
        GlStateManager.shadeModel(GL11.GL_FLAT);
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        GlStateManager.popMatrix();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    private void applyScrollLimitsForDraggingMenu() {
        int hiddenHeight = categorySize - (this.bottom - this.top);
        if (hiddenHeight < 0) {
            hiddenHeight /= 2;
        }

        if (this.scrollDistance < 0.0F) {
            this.scrollDistance = 0.0F;
        }

        if (this.scrollDistance > (float) hiddenHeight) {
            this.scrollDistance = (float) hiddenHeight;
        }

        int hiddenWidth = categorySize - (this.right - this.left);

        if (hiddenWidth < 0) {
            hiddenWidth /= 2;
        }

        if (this.scrollDistanceX < 0.0F) {
            this.scrollDistanceX = 0.0F;
        }

        if (this.scrollDistanceX > (float) hiddenWidth) {
            this.scrollDistanceX = (float) hiddenWidth;
        }
    }

    private void applyScrollLimitsForCatButtons() {
        int blHiddenHeight = catButtonsHeight + (ScrollAPI.categoryList.size() - 1) * border - (buttonListBottom - buttonListTop - 4);
        if (blHiddenHeight < 0) {
            blHiddenHeight /= 2;
        }

        if (this.blscrollDistance < 0.0F) {
            this.blscrollDistance = 0.0F;
        }

        if (this.blscrollDistance > (float) blHiddenHeight) {
            this.blscrollDistance = (float) blHiddenHeight;
        }
    }

    @Override
    public void handleMouseInput() throws IOException {

        super.handleMouseInput();
        boolean isHovering = mouseX >= this.buttonListLeft && mouseX <= this.buttonListLeft + this.buttonWidth &&
                mouseY >= this.buttonListTop && mouseY <= this.buttonListBottom;
        if (!isHovering)
            return;

        int scroll = Mouse.getEventDWheel();
        if (scroll != 0) {
            this.blscrollDistance += (-1 * scroll / 120.0F) * 10;
        }
    }

    protected <T extends GuiEntryButton> T addEntryButton(T buttonIn) {
        entryButtonList.put(buttonIn.id, buttonIn);
        return buttonIn;
    }

    private void renderEntryInfo(FontRenderer font, GuiEntryButton button) {

        List<String> textLines = new ArrayList<>();
        textLines.add(TextFormatting.AQUA + button.entry.getTitle());
        textLines.add(button.entry.getComment());

        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        int tooltipTextWidth = 0;

        for (String textLine : textLines) {
            int textLineWidth = font.getStringWidth(textLine);

            if (textLineWidth > tooltipTextWidth) {
                tooltipTextWidth = textLineWidth;
            }
        }

        boolean needsWrap = false;

        int titleLinesCount = 1;
        int tooltipX = mouseX + 12;
        if (tooltipX + tooltipTextWidth + 4 > width) {
            tooltipX = mouseX - 16 - tooltipTextWidth;
            if (tooltipX < 4) // if the tooltip doesn't fit on the screen
            {
                if (mouseX > width / 2) {
                    tooltipTextWidth = mouseX - 12 - 8;
                } else {
                    tooltipTextWidth = width - 16 - mouseX;
                }
                needsWrap = true;
            }
        }

        if (needsWrap) {
            int wrappedTooltipWidth = 0;
            List<String> wrappedTextLines = new ArrayList<>();
            for (int i = 0; i < textLines.size(); i++) {
                String textLine = textLines.get(i);
                List<String> wrappedLine = font.listFormattedStringToWidth(textLine, tooltipTextWidth);
                if (i == 0) {
                    titleLinesCount = wrappedLine.size();
                }

                for (String line : wrappedLine) {
                    int lineWidth = font.getStringWidth(line);
                    if (lineWidth > wrappedTooltipWidth) {
                        wrappedTooltipWidth = lineWidth;
                    }
                    wrappedTextLines.add(line);
                }
            }
            tooltipTextWidth = wrappedTooltipWidth;
            textLines = wrappedTextLines;

            if (mouseX > width / 2) {
                tooltipX = mouseX - 16 - tooltipTextWidth;
            } else {
                tooltipX = mouseX + 12;
            }
        }

        int tooltipY = mouseY - 12;
        int tooltipHeight = 8;

        if (textLines.size() > 1) {
            tooltipHeight += (textLines.size() - 1) * 10;
            if (textLines.size() > titleLinesCount) {
                tooltipHeight += 2; // gap between title lines and next lines
            }
        }

        if (tooltipY < 4) {
            tooltipY = 4;
        } else if (tooltipY + tooltipHeight + 4 > height) {
            tooltipY = height - tooltipHeight - 4;
        }

        final int zLevel = 300;
        int backgroundColor = 0xF0000814;
        int borderColorStart = 0xFF05409C;
        int borderColorEnd = (borderColorStart & 0xFEFEFE) >> 1 | borderColorStart & 0xFF000000;
        GuiUtils.drawGradientRect(zLevel, tooltipX - 3, tooltipY - 4, tooltipX + tooltipTextWidth + 3, tooltipY - 3, backgroundColor, backgroundColor);
        GuiUtils.drawGradientRect(zLevel, tooltipX - 3, tooltipY + tooltipHeight + 3, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 4, backgroundColor, backgroundColor);
        GuiUtils.drawGradientRect(zLevel, tooltipX - 3, tooltipY - 3, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3, backgroundColor, backgroundColor);
        GuiUtils.drawGradientRect(zLevel, tooltipX - 4, tooltipY - 3, tooltipX - 3, tooltipY + tooltipHeight + 3, backgroundColor, backgroundColor);
        GuiUtils.drawGradientRect(zLevel, tooltipX + tooltipTextWidth + 3, tooltipY - 3, tooltipX + tooltipTextWidth + 4, tooltipY + tooltipHeight + 3, backgroundColor, backgroundColor);
        GuiUtils.drawGradientRect(zLevel, tooltipX - 3, tooltipY - 3 + 1, tooltipX - 3 + 1, tooltipY + tooltipHeight + 3 - 1, borderColorStart, borderColorEnd);
        GuiUtils.drawGradientRect(zLevel, tooltipX + tooltipTextWidth + 2, tooltipY - 3 + 1, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3 - 1, borderColorStart, borderColorEnd);
        GuiUtils.drawGradientRect(zLevel, tooltipX - 3, tooltipY - 3, tooltipX + tooltipTextWidth + 3, tooltipY - 3 + 1, borderColorStart, borderColorStart);
        GuiUtils.drawGradientRect(zLevel, tooltipX - 3, tooltipY + tooltipHeight + 2, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3, borderColorEnd, borderColorEnd);

        int tooltipTop = tooltipY;

        for (int lineNumber = 0; lineNumber < textLines.size(); ++lineNumber) {
            String line = textLines.get(lineNumber);
            font.drawStringWithShadow(line, (float) tooltipX, (float) tooltipY, -1);

            if (lineNumber + 1 == titleLinesCount) {
                tooltipY += 2;
            }

            tooltipY += 10;
        }

        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enableRescaleNormal();
    }
}

