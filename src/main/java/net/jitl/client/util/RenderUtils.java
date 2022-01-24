package net.jitl.client.util;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * Copy of {@link net.minecraft.client.gui.GuiComponent} with static context
 */

public class RenderUtils {
    private static final Minecraft minecraft = Minecraft.getInstance();
    private static final int width = Minecraft.getInstance().getWindow().getGuiScaledWidth();
    private static final int height = Minecraft.getInstance().getWindow().getGuiScaledHeight();
    protected static Font font = Minecraft.getInstance().font;
    protected static ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

    public static void drawCenteredStringWithCustomScale(PoseStack poseStack, Font fontRendererIn, String text, int x, int y, int zLevel, int color, float scaleFactor, int availableHeight, boolean hasShadow) {
        poseStack.pushPose();
        poseStack.translate(x - fontRendererIn.width(text) / 2 * scaleFactor, y + (availableHeight / 2) + (fontRendererIn.lineHeight * scaleFactor > 1 ? -1 * fontRendererIn.lineHeight * scaleFactor : fontRendererIn.lineHeight * scaleFactor) * 0.5, zLevel);
        poseStack.scale(scaleFactor, scaleFactor, 1);
        if (hasShadow) {
            fontRendererIn.drawShadow(poseStack, text, 0, 0, color);
        } else {
            fontRendererIn.draw(poseStack, text, 0, 0, color);
        }
        poseStack.popPose();
    }

    public static void rectangle(PoseStack poseStack, Rectangle rectangle, int argbColor) {
        fill(poseStack, rectangle.left(), rectangle.top(), rectangle.right(), rectangle.bottom(), argbColor);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static void fill(PoseStack matrixStack, int minX, int minY, int maxX, int maxY, int colorIn) {
        innerFill(matrixStack.last().pose(), minX, minY, maxX, maxY, colorIn);
    }

    private static void innerFill(Matrix4f matrix, int minX, int minY, int maxX, int maxY, int colorIn) {
        if (minX < maxX) {
            int i = minX;
            minX = maxX;
            maxX = i;
        }

        if (minY < maxY) {
            int j = minY;
            minY = maxY;
            maxY = j;
        }

        float f3 = (float) (colorIn >> 24 & 255) / 255.0F;
        float f = (float) (colorIn >> 16 & 255) / 255.0F;
        float f1 = (float) (colorIn >> 8 & 255) / 255.0F;
        float f2 = (float) (colorIn & 255) / 255.0F;
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        bufferbuilder.vertex(matrix, (float) minX, (float) maxY, 0.0F).color(f, f1, f2, f3).endVertex();
        bufferbuilder.vertex(matrix, (float) maxX, (float) maxY, 0.0F).color(f, f1, f2, f3).endVertex();
        bufferbuilder.vertex(matrix, (float) maxX, (float) minY, 0.0F).color(f, f1, f2, f3).endVertex();
        bufferbuilder.vertex(matrix, (float) minX, (float) minY, 0.0F).color(f, f1, f2, f3).endVertex();
        bufferbuilder.end();
        BufferUploader.end(bufferbuilder);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public void fillGradient(PoseStack matrixStack, int x1, int y1, int x2, int y2, int colorFrom, int colorTo, int blitOffset) {
        RenderSystem.disableTexture();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        fillGradient(matrixStack.last().pose(), bufferbuilder, x1, y1, x2, y2, blitOffset, colorFrom, colorTo);
        tessellator.end();
        RenderSystem.disableBlend();
        RenderSystem.enableTexture();
    }

    public static void fillGradient(Matrix4f matrix, BufferBuilder builder, int x1, int y1, int x2, int y2, int z, int colorA, int colorB) {
        float f = (float) (colorA >> 24 & 255) / 255.0F;
        float f1 = (float) (colorA >> 16 & 255) / 255.0F;
        float f2 = (float) (colorA >> 8 & 255) / 255.0F;
        float f3 = (float) (colorA & 255) / 255.0F;
        float f4 = (float) (colorB >> 24 & 255) / 255.0F;
        float f5 = (float) (colorB >> 16 & 255) / 255.0F;
        float f6 = (float) (colorB >> 8 & 255) / 255.0F;
        float f7 = (float) (colorB & 255) / 255.0F;
        builder.vertex(matrix, (float) x2, (float) y1, (float) z).color(f1, f2, f3, f).endVertex();
        builder.vertex(matrix, (float) x1, (float) y1, (float) z).color(f1, f2, f3, f).endVertex();
        builder.vertex(matrix, (float) x1, (float) y2, (float) z).color(f5, f6, f7, f4).endVertex();
        builder.vertex(matrix, (float) x2, (float) y2, (float) z).color(f5, f6, f7, f4).endVertex();
    }

    public static void drawCenteredString(PoseStack matrixStack, Font fontRenderer, String fontIn, float x, float y, int red, int green, int blue, int alpha) {
        int color = Math.max(4, alpha) << 24 | red << 16 | green << 8 | blue;
        fontRenderer.drawShadow(matrixStack, fontIn, x - fontRenderer.width(fontIn) / 2F, y, color);
    }

    public static void drawCenteredString(PoseStack matrixStack, Font fontRenderer, Component fontIn, float x, float y, int red, int green, int blue, int alpha) {
        FormattedCharSequence ireorderingprocessor = fontIn.getVisualOrderText();
        int color = Math.max(4, alpha) << 24 | red << 16 | green << 8 | blue;
        fontRenderer.drawShadow(matrixStack, ireorderingprocessor, x - fontRenderer.width(ireorderingprocessor) / 2F, y, color);
    }

    public static void blitOutlineBlack(int width, int height, BiConsumer<Integer, Integer> boxXYConsumer) {
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        boxXYConsumer.accept(width + 1, height);
        boxXYConsumer.accept(width - 1, height);
        boxXYConsumer.accept(width, height + 1);
        boxXYConsumer.accept(width, height - 1);
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        boxXYConsumer.accept(width, height);
    }

    public static void blit(PoseStack matrixStack, int x, int y, int blitOffset, int width, int height, TextureAtlasSprite sprite) {
        innerBlit(matrixStack.last().pose(), x, x + width, y, y + height, blitOffset, sprite.getU0(), sprite.getU1(), sprite.getV0(), sprite.getV1());
    }

    public static void blit(PoseStack matrixStack, int x, int y, int uOffset, int vOffset, int uWidth, int vHeight, int blitOffset) {
        blit(matrixStack, x, y, blitOffset, (float) uOffset, (float) vOffset, uWidth, vHeight, 256, 256);
    }

    public static void blit(PoseStack matrixStack, int x, int y, int blitOffset, float uOffset, float vOffset, int uWidth, int vHeight, int textureHeight, int textureWidth) {
        innerBlit(matrixStack, x, x + uWidth, y, y + vHeight, blitOffset, uWidth, vHeight, uOffset, vOffset, textureWidth, textureHeight);
    }

    public static void blit(PoseStack matrixStack, int x, int y, int width, int height, float uOffset, float vOffset, int uWidth, int vHeight, int textureWidth, int textureHeight) {
        innerBlit(matrixStack, x, x + width, y, y + height, 0, uWidth, vHeight, uOffset, vOffset, textureWidth, textureHeight);
    }

    public static void blit(PoseStack matrixStack, int x, int y, float uOffset, float vOffset, int width, int height, int textureWidth, int textureHeight) {
        blit(matrixStack, x, y, width, height, uOffset, vOffset, width, height, textureWidth, textureHeight);
    }

    private static void innerBlit(PoseStack matrixStack, int x1, int x2, int y1, int y2, int blitOffset, int uWidth, int vHeight, float uOffset, float vOffset, int textureWidth, int textureHeight) {
        innerBlit(matrixStack.last().pose(), x1, x2, y1, y2, blitOffset, (uOffset + 0.0F) / (float) textureWidth, (uOffset + (float) uWidth) / (float) textureWidth, (vOffset + 0.0F) / (float) textureHeight, (vOffset + (float) vHeight) / (float) textureHeight);
    }

    private static void innerBlit(Matrix4f matrix, int x1, int x2, int y1, int y2, int blitOffset, float minU, float maxU, float minV, float maxV) {
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(matrix, (float) x1, (float) y2, (float) blitOffset).uv(minU, maxV).endVertex();
        bufferbuilder.vertex(matrix, (float) x2, (float) y2, (float) blitOffset).uv(maxU, maxV).endVertex();
        bufferbuilder.vertex(matrix, (float) x2, (float) y1, (float) blitOffset).uv(maxU, minV).endVertex();
        bufferbuilder.vertex(matrix, (float) x1, (float) y1, (float) blitOffset).uv(minU, minV).endVertex();
        bufferbuilder.end();
        BufferUploader.end(bufferbuilder);
    }

    public static void renderTooltip(PoseStack matrixStack, Component text, int mouseX, int mouseY, int textWidth, int textHeight) {
        renderComponentTooltip(matrixStack, Collections.singletonList(text), mouseX, mouseY, textWidth, textHeight);
    }

    public static void renderComponentTooltip(PoseStack matrixStack_, List<Component> textComponentList, int mouseX, int mouseY, int textWidth, int textHeight) {
        Font font = Minecraft.getInstance().font;
        renderWrappedToolTip(matrixStack_, textComponentList, mouseX, mouseY, textWidth, textHeight, font);
    }

    public static void renderWrappedToolTip(PoseStack matrixStack, List<Component> tooltips, int mouseX, int mouseY, int textWidth, int textHeight, Font font) {
        renderTooltip(matrixStack, tooltips, Optional.empty(), mouseX, mouseY, font);
    }

    private static Font tooltipFont = null;
    private static ItemStack tooltipStack = ItemStack.EMPTY;

    protected static void renderTooltip(PoseStack poseStack_, ItemStack itemStack_, int mouseX_, int mouseY_) {
        tooltipStack = itemStack_;
        renderTooltip(poseStack_, getTooltipFromItem(itemStack_), itemStack_.getTooltipImage(), mouseX_, mouseY_);
        tooltipStack = ItemStack.EMPTY;
    }

    public void renderTooltip(PoseStack poseStack, List<Component> textComponents, Optional<TooltipComponent> tooltipComponent, int x, int y, ItemStack stack) {
        renderTooltip(poseStack, textComponents, tooltipComponent, x, y, null, stack);
    }

    public static void renderTooltip(PoseStack poseStack, List<Component> textComponents, Optional<TooltipComponent> tooltipComponent, int x, int y, @Nullable Font font) {
        renderTooltip(poseStack, textComponents, tooltipComponent, x, y, font, ItemStack.EMPTY);
    }

    public static void renderTooltip(PoseStack poseStack, List<Component> textComponents, Optional<TooltipComponent> tooltipComponent, int x, int y, @Nullable Font font, ItemStack stack) {
        tooltipFont = font;
        tooltipStack = stack;
        renderTooltip(poseStack, textComponents, tooltipComponent, x, y);
        tooltipFont = null;
        tooltipStack = ItemStack.EMPTY;
    }

    public static void renderTooltip(PoseStack poseStack_, List<Component> tooltips_, Optional<TooltipComponent> visualTooltipComponent_, int mouseX_, int mouseY_) {
        List<ClientTooltipComponent> list = net.minecraftforge.client.ForgeHooksClient.gatherTooltipComponents(tooltipStack, tooltips_, visualTooltipComponent_, mouseX_, width, height, tooltipFont, font);
        renderTooltipInternal(poseStack_, list, mouseX_, mouseY_);
    }

    public static List<Component> getTooltipFromItem(ItemStack itemStack_) {
        return itemStack_.getTooltipLines(minecraft.player, minecraft.options.advancedItemTooltips ? TooltipFlag.Default.ADVANCED : TooltipFlag.Default.NORMAL);
    }

    public void renderTooltip(PoseStack poseStack_, Component text_, int mouseX_, int mouseY_) {
        renderTooltip(poseStack_, Arrays.asList(text_.getVisualOrderText()), mouseX_, mouseY_);
    }

    public static void renderComponentTooltip(PoseStack poseStack_, List<Component> tooltips_, int mouseX_, int mouseY_) {
        List<ClientTooltipComponent> components = net.minecraftforge.client.ForgeHooksClient.gatherTooltipComponents(tooltipStack, tooltips_, mouseX_, width, height, tooltipFont, font);
        renderTooltipInternal(poseStack_, components, mouseX_, mouseY_);
    }

    public void renderComponentTooltip(PoseStack poseStack, List<? extends net.minecraft.network.chat.FormattedText> tooltips, int mouseX, int mouseY, ItemStack stack) {
        this.renderComponentTooltip(poseStack, tooltips, mouseX, mouseY, null, stack);
    }

    public void renderComponentTooltip(PoseStack poseStack, List<? extends net.minecraft.network.chat.FormattedText> tooltips, int mouseX, int mouseY, @Nullable Font font) {
        this.renderComponentTooltip(poseStack, tooltips, mouseX, mouseY, font, ItemStack.EMPTY);
    }

    public void renderComponentTooltip(PoseStack poseStack, List<? extends net.minecraft.network.chat.FormattedText> tooltips, int mouseX, int mouseY, @Nullable Font font, ItemStack stack) {
        tooltipFont = font;
        tooltipStack = stack;
        List<ClientTooltipComponent> components = net.minecraftforge.client.ForgeHooksClient.gatherTooltipComponents(stack, tooltips, mouseX, width, height, tooltipFont, font);
        renderTooltipInternal(poseStack, components, mouseX, mouseY);
        tooltipFont = null;
        tooltipStack = ItemStack.EMPTY;
    }

    public static void renderTooltip(PoseStack poseStack_, List<? extends FormattedCharSequence> tooltips_, int mouseX_, int mouseY_) {
        renderTooltipInternal(poseStack_, tooltips_.stream().map(ClientTooltipComponent::create).collect(Collectors.toList()), mouseX_, mouseY_);
    }

    public static void renderTooltip(PoseStack poseStack, List<? extends FormattedCharSequence> lines, int x, int y, Font font) {
        tooltipFont = font;
        renderTooltip(poseStack, lines, x, y);
        tooltipFont = null;
    }

    private static void renderTooltipInternal(PoseStack poseStack_, List<ClientTooltipComponent> clientTooltipComponents_, int mouseX_, int mouseY_) {
        if (!clientTooltipComponents_.isEmpty()) {
            net.minecraftforge.client.event.RenderTooltipEvent.Pre preEvent = net.minecraftforge.client.ForgeHooksClient.onRenderTooltipPre(tooltipStack, poseStack_, mouseX_, mouseY_, width, height, clientTooltipComponents_, tooltipFont, font);
            if (preEvent.isCanceled()) return;
            int i = 0;
            int j = clientTooltipComponents_.size() == 1 ? -2 : 0;

            for (ClientTooltipComponent clienttooltipcomponent : clientTooltipComponents_) {
                int k = clienttooltipcomponent.getWidth(preEvent.getFont());
                if (k > i) {
                    i = k;
                }

                j += clienttooltipcomponent.getHeight();
            }

            int j2 = preEvent.getX() + 12;
            int k2 = preEvent.getY() - 12;
            if (j2 + i > width) {
                j2 -= 28 + i;
            }

            if (k2 + j + 6 > height) {
                k2 = height - j - 6;
            }

            poseStack_.pushPose();
            float f = itemRenderer.blitOffset;
            itemRenderer.blitOffset = 400.0F;
            Tesselator tesselator = Tesselator.getInstance();
            BufferBuilder bufferbuilder = tesselator.getBuilder();
            RenderSystem.setShader(GameRenderer::getPositionColorShader);
            bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
            Matrix4f matrix4f = poseStack_.last().pose();
            net.minecraftforge.client.event.RenderTooltipEvent.Color colorEvent = net.minecraftforge.client.ForgeHooksClient.onRenderTooltipColor(tooltipStack, poseStack_, j2, k2, preEvent.getFont(), clientTooltipComponents_);
            fillGradient(matrix4f, bufferbuilder, j2 - 3, k2 - 4, j2 + i + 3, k2 - 3, 400, colorEvent.getBackgroundStart(), colorEvent.getBackgroundStart());
            fillGradient(matrix4f, bufferbuilder, j2 - 3, k2 + j + 3, j2 + i + 3, k2 + j + 4, 400, colorEvent.getBackgroundEnd(), colorEvent.getBackgroundEnd());
            fillGradient(matrix4f, bufferbuilder, j2 - 3, k2 - 3, j2 + i + 3, k2 + j + 3, 400, colorEvent.getBackgroundStart(), colorEvent.getBackgroundEnd());
            fillGradient(matrix4f, bufferbuilder, j2 - 4, k2 - 3, j2 - 3, k2 + j + 3, 400, colorEvent.getBackgroundStart(), colorEvent.getBackgroundEnd());
            fillGradient(matrix4f, bufferbuilder, j2 + i + 3, k2 - 3, j2 + i + 4, k2 + j + 3, 400, colorEvent.getBackgroundStart(), colorEvent.getBackgroundEnd());
            fillGradient(matrix4f, bufferbuilder, j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + j + 3 - 1, 400, colorEvent.getBorderStart(), colorEvent.getBorderEnd());
            fillGradient(matrix4f, bufferbuilder, j2 + i + 2, k2 - 3 + 1, j2 + i + 3, k2 + j + 3 - 1, 400, colorEvent.getBorderStart(), colorEvent.getBorderEnd());
            fillGradient(matrix4f, bufferbuilder, j2 - 3, k2 - 3, j2 + i + 3, k2 - 3 + 1, 400, colorEvent.getBorderStart(), colorEvent.getBorderStart());
            fillGradient(matrix4f, bufferbuilder, j2 - 3, k2 + j + 2, j2 + i + 3, k2 + j + 3, 400, colorEvent.getBorderEnd(), colorEvent.getBorderEnd());
            RenderSystem.enableDepthTest();
            RenderSystem.disableTexture();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            bufferbuilder.end();
            BufferUploader.end(bufferbuilder);
            RenderSystem.disableBlend();
            RenderSystem.enableTexture();
            MultiBufferSource.BufferSource multibuffersource$buffersource = MultiBufferSource.immediate(Tesselator.getInstance().getBuilder());
            poseStack_.translate(0.0D, 0.0D, 400.0D);
            int l1 = k2;

            for (int i2 = 0; i2 < clientTooltipComponents_.size(); ++i2) {
                ClientTooltipComponent clienttooltipcomponent1 = clientTooltipComponents_.get(i2);
                clienttooltipcomponent1.renderText(preEvent.getFont(), j2, l1, matrix4f, multibuffersource$buffersource);
                l1 += clienttooltipcomponent1.getHeight() + (i2 == 0 ? 2 : 0);
            }

            multibuffersource$buffersource.endBatch();
            poseStack_.popPose();
            l1 = k2;

            for (int l2 = 0; l2 < clientTooltipComponents_.size(); ++l2) {
                ClientTooltipComponent clienttooltipcomponent2 = clientTooltipComponents_.get(l2);
                clienttooltipcomponent2.renderImage(preEvent.getFont(), j2, l1, poseStack_, itemRenderer, 400);
                l1 += clienttooltipcomponent2.getHeight() + (l2 == 0 ? 2 : 0);
            }

            itemRenderer.blitOffset = f;
        }
    }

    public static void renderTextureOverlay(ResourceLocation textureLocation, float alpha) {
        Minecraft minecraft = Minecraft.getInstance();
        int width = minecraft.getWindow().getGuiScaledWidth();
        int height = minecraft.getWindow().getGuiScaledHeight();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);
        RenderSystem.setShaderTexture(0, textureLocation);
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(0.0D, height, -90.0D).uv(0.0F, 1.0F).endVertex();
        bufferbuilder.vertex(width, height, -90.0D).uv(1.0F, 1.0F).endVertex();
        bufferbuilder.vertex(width, 0.0D, -90.0D).uv(1.0F, 0.0F).endVertex();
        bufferbuilder.vertex(0.0D, 0.0D, -90.0D).uv(0.0F, 0.0F).endVertex();
        tesselator.end();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
