package net.journey.client.render.gui.scroll;

import net.journey.JITL;
import net.journey.api.scroll.ScrollEntry;
import net.journey.util.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;

/*
 * Code by TimeConqueror
 */
public class GuiEntryButton extends Gui {
    protected static final ResourceLocation BUTTON_TEXTURE = new ResourceLocation(JITL.MOD_ID, "textures/gui/gui_scroll_base.png");
    /**
     * Button width in pixels
     */
    public int width;
    /**
     * Button height in pixels
     */
    public int height;
    /**
     * The x position of this control.
     */
    public int x;
    /**
     * The y position of this control.
     */
    public int y;
    /**
     * The string displayed on this control.
     */
    public int id;
    /**
     * True if this control is enabled, false to disable.
     */
    public boolean enabled;
    /**
     * Hides the button completely if false.
     */
    public boolean visible;
    public ScrollEntry entry;
    public int initialX;
    public int initialY;
    public String tabletCategoryName;
    protected boolean hovered;


    public GuiEntryButton(int buttonId, int x, int y, int widthIn, int heightIn, ScrollEntry entry, String tabletCategoryName) {
        this.enabled = true;
        this.visible = true;
        this.id = buttonId;
        this.x = x;
        this.y = y;
        this.initialX = this.x;
        this.initialY = this.y;
        this.width = widthIn;
        this.height = heightIn;
        this.entry = entry;
        this.tabletCategoryName = tabletCategoryName;
    }

    /**
     * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this button and 2 if it IS hovering over
     * this button.
     */
    protected int getHoverState(boolean mouseOver) {
        int i = 0;

        if (mouseOver) {
            i = 1;
        }

        return i;
    }

    /**
     * Draws this button to the screen.
     */

    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks, RenderItem itemRender) {
        if (this.visible) {
            mc.getTextureManager().bindTexture(BUTTON_TEXTURE);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int i = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            RenderUtils.drawTexturedQuad(x, y, width, height, 106, i * 32, 32, 32, 32);

            GlStateManager.pushMatrix();
            RenderHelper.enableGUIStandardItemLighting();
            GlStateManager.translate((float) x + 2, (float) y + 2, 32.0F);
            this.zLevel = 200.0F;
            itemRender.zLevel = 200.0F;
            GlStateManager.scale(1F, 1F, 1F);
            itemRender.renderItemAndEffectIntoGUI(entry.getDisplayedItem(), 0, 0);
            this.zLevel = 0.0F;
            itemRender.zLevel = 0.0F;
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popMatrix();
            this.mouseDragged(mc, mouseX, mouseY);
        }
    }

    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
    }

    public void mouseReleased(int mouseX, int mouseY) {
        Minecraft mc = Minecraft.getMinecraft();
        mc.displayGuiScreen(new GuiLoreScrollEntry(tabletCategoryName, entry));
    }

    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        return this.enabled && this.visible && mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
    }

    /**
     * Whether the mouse cursor is currently over the button.
     */
    public boolean isMouseOver() {
        return this.hovered;
    }

    public void drawButtonForegroundLayer(int mouseX, int mouseY) {
    }

    public void playPressSound(SoundHandler soundHandlerIn) {
        soundHandlerIn.playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }

    public int getButtonWidth() {
        return this.width;
    }

    public void onMouseHovering() {

    }
}
