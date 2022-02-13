package net.jitl.client.render.gui.dialog;

import com.mojang.blaze3d.systems.RenderSystem;
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
    private static final int INDENT_OFFSET = 8;

    private final ClientDialogPage page;

    @Nullable
    private final DialogCharacter character;
    @Nullable
    private LivingEntity entity;

    private Rectangle oldGuiRect;
    private Rectangle mobIconRect;
    private Rectangle mobTextRect;

    private Rectangle guiRect;
    private Rectangle optionsRect;

    public DialogScreen(ClientDialogPage page, @Nullable DialogCharacter character) {
        super(TextComponent.EMPTY);
        this.page = page;
        this.character = character;
    }

    @Override
    public void init() {
        super.init();

        int guiWidth = 300;
        int guiHeight = 200;

        int mobIconWidth = 80;

        oldGuiRect = Rectangle.fromWidthAndHeight(centerX - guiWidth / 2, centerY - guiHeight / 2, guiWidth, guiHeight);
        guiRect = Rectangle.fromWidthAndHeight(0, 0, width, height);
        int mobIconRight = oldGuiRect.right() - INDENT;
        int mobIconBottom = oldGuiRect.top() + INDENT + 80;

        optionsRect = new Rectangle(0, centerY - 14, width, height - INDENT * 2);
//        mobTextRect = new Rectangle(optionsRect.left() + INDENT, mobIconRect.top(), mobIconRect.left() - INDENT, mobIconRect.bottom());
        mobTextRect = new Rectangle(PHRASE_INDENT, INDENT, (int) (2 / 3F * width), optionsRect.top() - INDENT - 16);
        mobIconRect = new Rectangle(mobIconRight - mobIconWidth, oldGuiRect.top() + INDENT, mobIconRight, mobIconBottom);

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
        renderDebugLayout(matrixStack, mouseX, mouseY, partialTicks);

        if (entity != null) {
            renderEntity(width / (INDENT_OFFSET) * 6, (int) (mobIconRect.bottom() - mobIconRect.height() * -3.75F), mouseX, mouseY, entity);
        }

        RenderUtils.rectangle(matrixStack, optionsRect, 0x75000000); // options background

        renderMobText();

        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    private void renderMobText() {
        String text = I18n.get(page.text());

        String[] lines = text.split("\n");
        int maxWidth = mobTextRect.width() - INDENT;

        int totalHeight = 0;
        for (String line : lines) {
            totalHeight += font.wordWrapHeight(line, maxWidth);
        }

        int yStart = mobTextRect.top() + Math.max(mobTextRect.bottom() - totalHeight - font.lineHeight, 0);

        for (String line : lines) {
            int h = font.wordWrapHeight(line, maxWidth);
            font.drawWordWrap(TextComponent.EMPTY.plainCopy().append(new TextComponent(line)).withStyle(ChatFormatting.YELLOW), mobTextRect.left(), yStart, maxWidth, 0xFFFFFF);
            yStart += h;
        }
    }

    private void renderDebugLayout(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        RenderUtils.rectangle(poseStack, oldGuiRect, 0xFF8851FF); // whole gui
        RenderUtils.rectangle(poseStack, mobIconRect, 0xFF194378); // mob icon background
        RenderUtils.rectangle(poseStack, mobTextRect, 0xFF963232); // mob text background
    }

    public static void renderEntity(int posX, int posY, float mouseX, float mouseY, LivingEntity entity) {
        entity = Minecraft.getInstance().player;
        float scaleFactor = (float) entity.getBoundingBox().maxY / 1.8F /* height of npc */;
        scaleFactor = Math.max(scaleFactor, 0.5F); // make it so very small mobs won't be super big

        int adaptiveScale = (int) (164 /*scale for player */ / scaleFactor);

        int playerEyeHeight = (int) entity.getEyeHeight() * (int) (adaptiveScale * 2.0F); // eye height of player in pixels of inventory gui
        float eyeOffset = playerEyeHeight * entity.getEyeHeight() / (1.65F * scaleFactor) /* eye height of player in blocks */;

        RenderSystem.setShaderColor(1, 1, 1, 1);
        InventoryScreen.renderEntityInInventory(posX, posY, adaptiveScale, posX - mouseX, posY - mouseY - eyeOffset, entity);
    }

    private static class DialogButton extends ImprovedButton {
        public DialogButton(int x_, int y_, int width_, int height_, Component message_, int optionIndex) {
            super(x_, y_, width_, height_, message_, button_ -> JPacketHandler.INSTANCE.sendToServer(new CDialogPressOptionPacket(optionIndex)));
            textureLocation = null;
            alignment = Alignment.LEFT;
        }

        @Override
        public int getFGColor() {
            return isHoveredOrFocused() ? Objects.requireNonNull(ChatFormatting.YELLOW.getColor()) : super.getFGColor();
        }
    }
}
