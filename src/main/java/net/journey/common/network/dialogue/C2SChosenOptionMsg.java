package net.journey.common.network.dialogue;

import net.journey.common.JManagers;
import net.journey.common.network.BaseMsg;
import net.journey.common.network.BasicMsgHandler;
import net.journey.dialogue.DialogueSystemException;
import net.journey.util.NetworkUtils;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class C2SChosenOptionMsg extends BaseMsg {
	private int optionIndex;

	@Deprecated // is called via reflection, not for direct use
	public C2SChosenOptionMsg() {
	}

	public C2SChosenOptionMsg(int optionIndex) {
		this.optionIndex = optionIndex;
	}

	@Override
	protected void write(PacketBuffer buffer) {
		buffer.writeInt(optionIndex);
	}

	@Override
	protected void read(PacketBuffer buffer) {
		optionIndex = buffer.readInt();
	}

	public static class Handler extends BasicMsgHandler<C2SChosenOptionMsg, IMessage> {
		@Override
		protected void doOnMessage(C2SChosenOptionMsg message, MessageContext ctx) {
			try {
				JManagers.DIALOGUE_MANAGER.handleDialogueChosenOption(ctx.getServerHandler().player, message.optionIndex);
			} catch (DialogueSystemException e) {
				NetworkUtils.kickPlayer(ctx.getServerHandler().player, new TextComponentString(e.getLocalizedMessage()));
			}
		}
	}
}
