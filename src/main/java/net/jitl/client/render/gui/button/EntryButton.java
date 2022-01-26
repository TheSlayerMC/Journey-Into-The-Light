package net.jitl.client.render.gui.button;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.render.screen.LoreScrollEntryScreen;
import net.jitl.client.util.RenderUtils;
import net.jitl.common.scroll.ScrollCategory;
import net.jitl.common.scroll.ScrollEntry;
import net.jitl.core.JITL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

public class EntryButton extends Button {
    protected static final ResourceLocation BUTTON_TEXTURE = JITL.rl("textures/gui/gui_scroll_base.png");
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
    public ScrollCategory category;
    protected boolean hovered;

    public EntryButton(int id, int x, int y, int widthIn, int heightIn, ScrollEntry entry, ScrollCategory category) {
        super(x, y, widthIn, heightIn, new TranslatableComponent(""), null);
        this.id = id;
        this.enabled = true;
        this.visible = true;
        this.x = x;
        this.y = y;
        this.initialX = this.x;
        this.initialY = this.y;
        this.width = widthIn;
        this.height = heightIn;
        this.entry = entry;
        this.category = category;
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

    public void drawButton(PoseStack poseStack, Minecraft mc, int mouseX, int mouseY, float partialTicks, ItemRenderer itemRender) {
        if (this.visible) {
            RenderSystem.setShader(GameRenderer::getPositionTexColorNormalShader);
            RenderSystem.setShaderTexture(0, BUTTON_TEXTURE);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int i = this.getHoverState(this.hovered);
            RenderSystem.enableBlend();
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            RenderUtils.blit(poseStack, x, y, width, height, 106F, 0, i * 32, 32, 32, 32);

            poseStack.pushPose();
            //RenderHelper.enableGUIStandardItemLighting();
            poseStack.translate((float) x + 2, (float) y + 2, 32.0F);
            this.setBlitOffset(200);
            itemRender.blitOffset = 200.0F;
            poseStack.scale(1F, 1F, 1F);
            itemRender.renderGuiItem(entry.getDisplayedItem(), 0, 0);
            this.setBlitOffset(0);
            itemRender.blitOffset = 0.0F;
            //RenderHelper.disableStandardItemLighting();
            poseStack.popPose();
            this.mouseDragged(mc, mouseX, mouseY);
        }
    }

    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
    }

    @Override
    public boolean mouseReleased(double mouseX_, double mouseY_, int button_) {
        Minecraft mc = Minecraft.getInstance();
        mc.setScreen(new LoreScrollEntryScreen(category, entry));
        return true;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return this.enabled && this.visible && mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
    }

    @Override
    public boolean isMouseOver(double mouseX_, double mouseY_) {
        return this.hovered;
    }

    @Override
    public int getWidth() {
        return width;
    }
}
