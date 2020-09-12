package net.journey.client.render.gui.base.dialogue;

import net.journey.client.render.gui.base.JGuiScreen;
import net.journey.dialogue.Dialogue;
import net.journey.dialogue.DialogueNode;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;

import java.util.List;

public class GuiDialogue extends JGuiScreen {
	private final Dialogue dialogue;

	//TODO make constant variables private static final
	//TODO make nonconstant variables private final
	int guiWidth = 300;
	int guiHeight = 200;

	int guiLeft;
	int guiTop;
	int guiRight;
	int guiBottom;

	int indent = 6;

	int mobIconWidth = 80;
	int mobIconHeight = 80;
	int mobIconRight;
	int mobIconLeft;
	int mobIconTop;
	int mobIconBottom;

	int horizontalSinglePart;

	int optionsLeft;
	int optionsTop;

	public GuiDialogue(Dialogue dialogue) {
		this.dialogue = dialogue;
	}

	@Override
	public void initGui() {
		super.initGui();

		guiLeft = centerX - guiWidth / 2;
		guiTop = centerY - guiHeight / 2;
		guiRight = guiLeft + guiWidth;
		guiBottom = guiTop + guiHeight;
		mobIconRight = guiRight - indent;
		mobIconLeft = mobIconRight - mobIconWidth;
		mobIconTop = guiTop + indent;
		mobIconBottom = mobIconTop + mobIconHeight;
		horizontalSinglePart = (guiWidth - indent * 2) / 7;
		optionsLeft = guiLeft + horizontalSinglePart;
		optionsTop = mobIconBottom + indent;
		initOptionButtons();
	}

	private void initOptionButtons() {
		int allHeight = guiBottom - indent - optionsTop;
		int buttonHeight = 20;

		DialogueNode node = dialogue.getCurrentNode();
		List<DialogueNode.Option> options = node.getOptions();

		int optionsCenterY = optionsTop + allHeight / 2;
		int indentCount = options.size() - 1;
		int minimalIndent = 1;

		int allIndent = (allHeight - buttonHeight * options.size()) / indentCount;
		int indent = allIndent / indentCount;
		indent = Math.min(Math.max(indent, minimalIndent), this.indent); // when options don't fit the space

		int startY = optionsCenterY - buttonHeight * options.size() / 2 - indent * (indentCount / 2);

		int incrementor = buttonHeight + indent;

		int x = centerX - 200 /*default button width*/ / 2;

		for (DialogueNode.Option option : options) {
			buttonList.add(new GuiOptionButton(option, buttonList.size(), x, startY));
			startY += incrementor;
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDebugLayout(mouseX, mouseY, partialTicks);

		drawEntity(mobIconRight - mobIconWidth / 2, (int) (mobIconBottom - mobIconHeight / 5F), mouseX, mouseY, dialogue.getNpc());

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	private void drawDebugLayout(int mouseX, int mouseY, float partialTicks) {
		drawRect(guiLeft, guiTop, guiRight, guiBottom, 0xFF8851FF); // whole gui

		drawRect(mobIconLeft, mobIconTop, mobIconRight, mobIconBottom, 0xFF194378); // mob icon background

		drawRect(guiLeft + indent, mobIconTop, mobIconLeft - indent, mobIconBottom, 0xFF963232); // mob text background

		drawRect(optionsLeft, optionsTop, guiRight - horizontalSinglePart, guiBottom - indent, 0xFF554887); // options background
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
}
