package net.journey.essence;

import io.netty.buffer.ByteBuf;
import net.journey.JITL;
import net.journey.client.server.BarTickHandler;
import net.journey.client.server.EssenceRenderer;
import net.journey.client.server.IEssence;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageEssenceBar implements IMessage {

	private int delay;
	private int essence;
	private int max;

	public MessageEssenceBar() { }

	public MessageEssenceBar(IEssence essence) {
		if(essence == null)
			return;

		delay = essence.getRegenDelay();
		this.essence = essence.getEssence();
		max = essence.getMaxEssence();
	}

	@Override
	public void fromBytes(ByteBuf byteBuf) {
		max = byteBuf.readInt();
		essence = byteBuf.readInt();
		delay = byteBuf.readInt();
	}

	@Override
	public void toBytes(ByteBuf byteBuf) {
		byteBuf.writeInt(max);
		byteBuf.writeInt(essence);
		byteBuf.writeInt(delay);
	}
	
	public static class Handler implements IMessageHandler<MessageEssenceBar, IMessage>{

		@Override
		public IMessage onMessage(MessageEssenceBar msg, MessageContext ctx) {

			if (ctx.side == Side.CLIENT) {
				IEssence capability = BarTickHandler.getEssence(JITL.proxy.getPlayer());
				capability.setMaxEssence(msg.max);
				capability.setRegenDelay(msg.delay);
				capability.set(msg.essence);
			}

			EssenceRenderer.percantage = (msg.essence / msg.max) * 100;
			return null;
		}
		
	}
}