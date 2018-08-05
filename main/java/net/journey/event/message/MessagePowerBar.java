package net.journey.event.message;

import io.netty.buffer.ByteBuf;
import net.journey.JITL;
import net.journey.client.server.BarTickHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessagePowerBar implements IMessage {
	
	public int amount;
	public boolean shouldRegen;

	public MessagePowerBar() { }
	
	public MessagePowerBar(int amount, boolean shouldRegen) {
		this.amount = amount;
		this.shouldRegen = shouldRegen;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		amount = buf.readInt();
		shouldRegen = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(amount);
		buf.writeBoolean(shouldRegen);
	}
	
	public static class PowerHandler implements IMessageHandler<MessagePowerBar, IMessage> {

		@Override
		public IMessage onMessage(MessagePowerBar message, MessageContext ctx) {

			JITL.proxy.updatePower(message.amount);
			return null;
		}
	}
}
