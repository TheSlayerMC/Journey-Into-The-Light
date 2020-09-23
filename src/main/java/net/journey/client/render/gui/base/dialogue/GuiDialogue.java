package net.journey.client.render.gui.base.dialogue;

import net.journey.client.render.gui.base.JGuiScreen;
import net.journey.client.util.Rectangle;
import net.journey.common.network.NetworkHandler;
import net.journey.common.network.dialogue.C2SChosenOptionMsg;
import net.journey.dialogue.ClientDialogueNode;
import net.journey.util.gui.RenderUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public class GuiDialogue extends JGuiScreen {
	private static final int INDENT = 6;

	private final ClientDialogueNode node;

	private Rectangle guiRect;
	private Rectangle mobIconRect;
	private Rectangle optionsRect;
	private Rectangle mobTextRect;

	public GuiDialogue(ClientDialogueNode node) {
		this.node = node;
	}

	@Override
	public void initGui() {
		super.initGui();

		int guiWidth = 300;
		int guiHeight = 200;

		int mobIconWidth = 80;
		int mobIconHeight = 80;

		guiRect = Rectangle.createWithWidthHeight(centerX - guiWidth / 2, centerY - guiHeight / 2, guiWidth, guiHeight);
		int mobIconRight = guiRect.getRight() - INDENT;
		int mobIconTop = guiRect.getTop() + INDENT;
		mobIconRect = new Rectangle(mobIconRight - mobIconWidth, guiRect.getTop() + INDENT, mobIconRight, mobIconTop + mobIconHeight);

		int horizontalSinglePart = (guiWidth - INDENT * 2) / 7;

		optionsRect = new Rectangle(guiRect.getLeft() + horizontalSinglePart, mobIconRect.getBottom() + INDENT, guiRect.getRight() - horizontalSinglePart, guiRect.getBottom() - INDENT);
		mobTextRect = new Rectangle(guiRect.getLeft() + INDENT, mobIconRect.getTop(), mobIconRect.getLeft() - INDENT, mobIconRect.getBottom());

		initOptionButtons();
	}

	private void initOptionButtons() {
		int allHeight = guiRect.getBottom() - INDENT - optionsRect.getTop();
		int buttonHeight = 20;

		List<String> options = node.getOptionTextKeys();

		int optionsCenterY = optionsRect.getTop() + allHeight / 2;
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

		int x = centerX - 200 /*default button width*/ / 2;

		for (int i = 0; i < options.size(); i++) {
			addButton(new GuiOptionButton(options.get(i), i, x, startY));
			startY += incrementor;
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		drawDebugLayout(mouseX, mouseY, partialTicks);

		drawMobText();
		drawEntity(mobIconRect.getRight() - mobIconRect.getWidth() / 2, (int) (mobIconRect.getBottom() - mobIconRect.getHeight() / 5F), mouseX, mouseY, node.getNpc());

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	private void drawMobText() {
		String text = TextFormatting.YELLOW + "" + TextFormatting.ITALIC + I18n.format(node.getTextKey());
		fontRenderer.drawSplitString(text, mobTextRect.getLeft() + INDENT, mobTextRect.getTop() + INDENT + 48, Math.max(mobTextRect.getWidth() - INDENT * 2, 2), 0xFFFFFF);
	}

	private void drawDebugLayout(int mouseX, int mouseY, float partialTicks) {
		RenderUtils.drawRect(guiRect, 0xFF8851FF); // whole gui

		RenderUtils.drawRect(mobIconRect, 0xFF194378); // mob icon background

		RenderUtils.drawRect(mobTextRect, 0xFF963232); // mob text background

		RenderUtils.drawRect(optionsRect, 0xFF554887); // options background
	}

	public static void drawEntity(int posX, int posY, float mouseX, float mouseY, EntityLivingBase entity) {
		float scaleFactor = entity.height / 1.8F /*height of player */;
		scaleFactor = Math.max(scaleFactor, 0.5F); // make it so very small mobs won't be super big

		int adaptiveScale = (int) (30 /*scale for player */ / scaleFactor);

		int playerEyeHeight = 49; // eye height of player in pixels of inventory gui
		float eyeOffset = playerEyeHeight * entity.getEyeHeight() / (1.62F * scaleFactor) /* eye height of player in blocks */;

		GlStateManager.color(1, 1, 1, 1);
		GuiInventory.drawEntityOnScreen(posX, posY, adaptiveScale, posX - mouseX, posY - mouseY - eyeOffset, entity);
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if (button instanceof GuiOptionButton) {
			NetworkHandler.INSTANCE.sendToServer(new C2SChosenOptionMsg(button.id));
		}
	}
}
