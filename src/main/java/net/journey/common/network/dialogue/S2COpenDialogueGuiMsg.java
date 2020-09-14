package net.journey.common.network.dialogue;

import net.journey.client.render.gui.base.dialogue.GuiDialogue;
import net.journey.common.network.BaseMsg;
import net.journey.common.network.BasicMsgHandler;
import net.journey.dialogue.ClientDialogueNode;
import net.journey.dialogue.DialogueNode;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class S2COpenDialogueGuiMsg extends BaseMsg {
	private DialogueNode node;
	private ResourceLocation npcKey;
	private ClientDialogueNode clientNode;

	@Deprecated // is called via reflection, not for direct use
	public S2COpenDialogueGuiMsg() {
	}

	public S2COpenDialogueGuiMsg(Class<? extends EntityLivingBase> npcClass, DialogueNode node) {
		this.node = node;
		this.npcKey = EntityList.getKey(npcClass);
	}

	@Override
	public void write(PacketBuffer buffer) {
		buffer.writeResourceLocation(npcKey);

		buffer.writeString(node.getTextKey());

		List<DialogueNode.Option> options = node.getOptions();
		buffer.writeInt(options.size());
		for (DialogueNode.Option option : options) {
			buffer.writeString(option.getText());
		}
	}

	@Override
	public void read(PacketBuffer buffer) {
		npcKey = buffer.readResourceLocation();

		String text = buffer.readString(Short.MAX_VALUE);

		int size = buffer.readInt();
		List<String> optionKeys = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			optionKeys.add(buffer.readString(Short.MAX_VALUE));
		}

		clientNode = new ClientDialogueNode(npcKey, text, optionKeys);
	}

	public static class Handler extends BasicMsgHandler<S2COpenDialogueGuiMsg, IMessage> {
		@Override
		@SideOnly(Side.CLIENT)
		protected void doOnMessage(S2COpenDialogueGuiMsg message, MessageContext ctx) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiDialogue(message.clientNode));
		}
	}
}
