package net.journey.event.message;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageKnowledge implements IMessage {

	public MessageKnowledge() { }
	
	@Override
	public void fromBytes(ByteBuf buf) { }

	@Override
	public void toBytes(ByteBuf buf) { }
	
	public class KnowledgeHandler implements IMessageHandler<MessageKnowledge, IMessage> {

		@Override
		public IMessage onMessage(MessageKnowledge message, MessageContext ctx) {
			return null;
		}
	}
}
