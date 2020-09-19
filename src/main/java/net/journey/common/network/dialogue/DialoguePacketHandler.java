package net.journey.common.network.dialogue;

import net.journey.common.network.BasicMsgHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.function.BiConsumer;

@SuppressWarnings("MissingMessageConstructor")
public class DialoguePacketHandler<REQ extends IMessage> extends BasicMsgHandler<REQ, IMessage> {
	private final BiConsumer<REQ, MessageContext> handler;

	public DialoguePacketHandler(BiConsumer<REQ, MessageContext> handler) {
		this.handler = handler;
	}

	@Override
	protected void doOnMessage(REQ message, MessageContext ctx) {
		handler.accept(message, ctx);
	}
}
