package net.jitl.client.render.gui.dialogue;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.dialogue.ClientDialogueNode;
import net.jitl.client.render.gui.base.JScreen;
import net.jitl.client.render.gui.button.NoTextureButton;
import net.jitl.client.util.Rectangle;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DialogueScreen extends JScreen {

    private static final int INDENT = 6;
    private static final int INDENT_OFFSET = 8;

    private final ClientDialogueNode node;

    private Rectangle guiRect;
    private Rectangle mobIconRect;
    private Rectangle optionsRect;
    private Rectangle mobTextRect;

    public DialogueScreen(ClientDialogueNode node) {
        super(new TranslatableComponent("test"));
        this.node = node;
    }

    @Override
    public void init() {
        super.init();

        int guiWidth = 300;
        int guiHeight = 200;

        int mobIconWidth = 80;
        int mobIconHeight = 80;

        guiRect = Rectangle.createWithWidthHeight(centerX - guiWidth / 2, centerY - guiHeight / 2, guiWidth, guiHeight);
        int mobIconRight = guiRect.right() - INDENT;
        int mobIconTop = guiRect.top() + INDENT;
        mobIconRect = new Rectangle(mobIconRight - mobIconWidth, guiRect.top() + INDENT, mobIconRight, mobIconTop + mobIconHeight);

        int horizontalSinglePart = (INDENT * guiWidth);

        optionsRect = new Rectangle(guiRect.left() + horizontalSinglePart, mobIconRect.bottom() + INDENT, guiRect.right() - horizontalSinglePart, guiRect.bottom() - INDENT);
        mobTextRect = new Rectangle(guiRect.left() + INDENT, mobIconRect.top(), mobIconRect.left() - INDENT, mobIconRect.bottom());

        initOptionButtons();
    }

    private void initOptionButtons() {
        int allHeight = guiRect.bottom() - INDENT - optionsRect.top();
        int buttonHeight = 20;

        List<String> options = node.getOptionTextKeys();

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

        int x = mobTextRect.left() + INDENT * -(INDENT_OFFSET);

        for (String option : options) {
            addWidget(new NoTextureButton(x, startY, new TranslatableComponent(option)));
            startY += incrementor;
        }
    }

    @Override
    public void render(@NotNull PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        renderDebugLayout(mouseX, mouseY, partialTicks);

        renderMobText();
        renderEntity(width / (INDENT_OFFSET) * 6, (int) (mobIconRect.bottom() - mobIconRect.height() * -3.75F), mouseX, mouseY, node.getNpc());

        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    private void renderMobText() {
        //TODO: find out how spit string is done
        Component text = new TranslatableComponent(ChatFormatting.YELLOW + "" + ChatFormatting.ITALIC + node.getTextKey());
        font.drawWordWrap(text, mobTextRect.left() + INDENT * -(INDENT_OFFSET), mobTextRect.top() + INDENT + 48, Math.max(mobTextRect.width(), 2), 0xFFFFFF);
    }

    private void renderDebugLayout(int mouseX, int mouseY, float partialTicks) {
        //TODO: add back RenderUtils
        //RenderUtils.drawRect(guiRect, 0xFF8851FF); // whole gui

        //RenderUtils.drawRect(mobIconRect, 0xFF194378); // mob icon background

        //RenderUtils.drawRect(mobTextRect, 0xFF963232); // mob text background

        //RenderUtils.drawRect(optionsRect, 0x75000000); // options background
    }

    public static void renderEntity(int posX, int posY, float mouseX, float mouseY, LivingEntity entity) {
        float scaleFactor = (float) entity.getBoundingBox().maxY / 1.8F /*height of npc */;
        scaleFactor = Math.max(scaleFactor, 0.5F); // make it so very small mobs won't be super big

        int adaptiveScale = (int) (164 /*scale for player */ / scaleFactor);

        int playerEyeHeight = (int) entity.getEyeHeight() * (int) (adaptiveScale * 2.0F); // eye height of player in pixels of inventory gui
        float eyeOffset = playerEyeHeight * entity.getEyeHeight() / (1.65F * scaleFactor) /* eye height of player in blocks */;

        GlStateManager._color4f(1, 1, 1, 1);
        InventoryScreen.renderEntityInInventory(posX, posY, adaptiveScale, posX - mouseX, posY - mouseY - eyeOffset, entity);
    }
}
