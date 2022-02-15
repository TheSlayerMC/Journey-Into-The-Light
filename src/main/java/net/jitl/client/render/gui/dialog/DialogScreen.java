package net.jitl.client.render.gui.dialog;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.base.Alignment;
import net.jitl.client.base.ImprovedButton;
import net.jitl.client.render.gui.base.JScreen;
import net.jitl.client.util.Rectangle;
import net.jitl.client.util.RenderUtils;
import net.jitl.common.dialog.ClientDialogPage;
import net.jitl.common.dialog.DialogCharacter;
import net.jitl.core.network.JPacketHandler;
import net.jitl.core.network.dialogue.CDialogPressOptionPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

//TODO add close dialog c2s-packet to make the server know
public class DialogScreen extends JScreen {

    private static final int INDENT = 6;
    private static final int PHRASE_INDENT = 45;

    private static int TICK = 0;
    private static int TEXT_COUNTER = 0;

    private final ClientDialogPage page;

    @Nullable
    private final DialogCharacter character;
    @Nullable
    private LivingEntity entity;

    private Rectangle mobIconRect;
    private Rectangle mobTextRect;
    private Rectangle mobInnerIconRect;
    private Rectangle optionsShadowRect;
    private Rectangle optionsRect;

    public DialogScreen(ClientDialogPage page, @Nullable DialogCharacter character) {
        super(TextComponent.EMPTY);
        this.page = page;
        this.character = character;
    }

    @Override
    public void init() {
        super.init();

        resetTextCounter();

        mobTextRect = new Rectangle(PHRASE_INDENT, INDENT, (int) (1.75 / 3F * width), centerY - 14 - INDENT - 16);
        mobIconRect = new Rectangle(mobTextRect.right(), (int) (1.2 / 4F * height), width, height);
        int mobMarginX = 2;
        int mobOffsetY = (int) (mobIconRect.height() * 1.5F / 4);
        mobInnerIconRect = Rectangle.fromWidthAndHeight(mobIconRect.left() + mobMarginX, mobIconRect.top() + mobOffsetY, mobIconRect.width() - mobMarginX * 2, mobIconRect.height());

        optionsRect = new Rectangle(mobTextRect.left(), centerY - 14, mobIconRect.left(), height - INDENT * 2);
        optionsShadowRect = new Rectangle(0, optionsRect.top(), width, optionsRect.bottom());

        ClientLevel level = Objects.requireNonNull(getMinecraft().level);
        this.entity = character != null ? character.entityType().create(level) : null;

        initOptionButtons();
    }

    private void initOptionButtons() {
        int allHeight = optionsRect.bottom() - INDENT - optionsRect.top();
        int buttonHeight = 20;

        List<String> options = page.options();

        int optionsCenterY = optionsRect.top() + allHeight / 2;
        int indentCount = options.size() - 1;
        int minimalIndent = 1;

        int indent;
        if (indentCount != 0) {
            int allIndent = (allHeight - buttonHeight * options.size()) / indentCount;
            indent = allIndent / indentCount;
        } else {
            indent = 0;
        }

        indent = Math.min(Math.max(indent, minimalIndent), INDENT); // when options don't fit the space
        int startY = optionsCenterY - buttonHeight * options.size() / 2 - indent * (indentCount / 2);
        int incrementor = buttonHeight + indent;

        for (int i = 0; i < options.size(); i++) {
            addRenderableWidget(new DialogButton(PHRASE_INDENT, startY, width - 8, buttonHeight, new TextComponent("> ").append(new TranslatableComponent(options.get(i))), i));
            startY += incrementor;
        }
    }

    @Override
    public void render(@NotNull PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        if (Minecraft.getInstance().options.renderDebug) {
            renderDebugLayout(matrixStack, mouseX, mouseY, partialTicks);
        }

        RenderUtils.rectangle(matrixStack, optionsShadowRect, 0x75000000);

        if (entity != null) {
            renderEntity(mouseX, mouseY, entity);
        }

        renderMobText();

        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    private void renderMobText() {
        String text = textCounter(I18n.get(page.text()));

        String[] lines = text.split("\n");
        int maxWidth = mobTextRect.width() - INDENT;

        int totalHeight = 0;
        for (String line : lines) {
            totalHeight += font.wordWrapHeight(line, maxWidth);
        }

        int yStart = mobTextRect.top() + Math.max(mobTextRect.bottom() - totalHeight - font.lineHeight, 0);

        for (String line : lines) {
            int h = font.wordWrapHeight(line, maxWidth);
            font.drawWordWrap(TextComponent.EMPTY.plainCopy().append(new TextComponent(line)).withStyle(ChatFormatting.YELLOW, ChatFormatting.ITALIC), mobTextRect.left(), yStart, maxWidth, 0xFFFFFF);
            yStart += h;
        }
    }

    private void renderDebugLayout(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        RenderUtils.rectangle(poseStack, optionsRect, 0xFF8851FF);
        RenderUtils.rectangle(poseStack, mobTextRect, 0xFF963232);
        RenderUtils.rectangle(poseStack, mobIconRect, 0xFF194378);
        RenderUtils.rectangle(poseStack, mobInnerIconRect, 0xFF33AA32);
    }

    public void renderEntity(float mouseX, float mouseY, LivingEntity entity) {
        float scale = 92;

        float scaledHeight = entity.getBbHeight() * scale; // standard scale

        if (scaledHeight > mobInnerIconRect.height()) {
            scale = scale * mobInnerIconRect.height() / scaledHeight;
        } else if (scaledHeight < mobInnerIconRect.height() * 2 / 3F) {
            scale = scale * (mobInnerIconRect.height() * 2 / 3F) / scaledHeight;
        }

        //refresh scaled dimensions
        float scaledWidth = entity.getBbWidth() * scale;

        if (scaledWidth > mobInnerIconRect.width()) {
            scale = scale * mobInnerIconRect.width() / scaledWidth;
        }

        //refresh scaled dimensions
        scaledHeight = entity.getBbHeight() * scale;

        int scaledEyeHeight = (int) (entity.getEyeHeight() * scale);
        int renderX = mobInnerIconRect.left() + mobInnerIconRect.width() / 2;
        int renderY = (int) (mobInnerIconRect.top() + scaledHeight);

        // scale = 1 -> the whole entity is in one pixel
        InventoryScreen.renderEntityInInventory(renderX, renderY, (int) scale, renderX - mouseX, renderY - mouseY - scaledEyeHeight, entity);
    }

    private String textCounter(String input) {
        if (TICK >= 0 && TICK <= input.length()) {
            int i = TEXT_COUNTER;

            String subStr = String.valueOf(input.subSequence(0, i));

            TEXT_COUNTER++;
            TICK++;

            return subStr;
        } else {
            if (TICK > input.length()) {
                TICK = input.length() + 1;
            }
            return input;
        }
    }

    private static void resetTextCounter() {
        TEXT_COUNTER = 0;
        TICK = 0;
    }

    private static class DialogButton extends ImprovedButton {
        public DialogButton(int x_, int y_, int width_, int height_, Component message_, int optionIndex) {
            super(x_, y_, width_, height_, message_, button_ -> JPacketHandler.INSTANCE.sendToServer(new CDialogPressOptionPacket(optionIndex)));
            textureLocation = null;
            alignment = Alignment.LEFT;
        }

        @Override
        public void onPress() {
            super.onPress();
            resetTextCounter();
        }

        @Override
        public int getFGColor() {
            return isHoveredOrFocused() ? Objects.requireNonNull(ChatFormatting.YELLOW.getColor()) : super.getFGColor();
        }
    }
}
