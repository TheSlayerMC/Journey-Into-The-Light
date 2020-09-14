package net.journey.common.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.journey.api.capability.JourneyPlayer;
import net.journey.common.capability.JCapabilityManager;
import net.journey.common.capability.JourneyPlayerImpl;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class S2CSyncJourneyCapMsg extends BaseMsg {
	private JourneyPlayer playerCap;
	private ByteBuf clientBuf;

	@Deprecated // is called via reflection, not for direct use
	public S2CSyncJourneyCapMsg() {
	}

	public S2CSyncJourneyCapMsg(JourneyPlayer playerCap) {
		this.playerCap = playerCap;
	}

	@Override
	public void write(PacketBuffer buffer) {
		JourneyPlayerImpl.Serializer.INSTANCE.writeToBuffer(playerCap, buffer);
	}

	@Override
	public void read(PacketBuffer buffer) {
		this.clientBuf = buffer.copy();
	}

	public static class Handler implements IMessageHandler<S2CSyncJourneyCapMsg, IMessage> {

		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(S2CSyncJourneyCapMsg message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				PacketBuffer packetBuffer = new PacketBuffer(Unpooled.wrappedBuffer(message.clientBuf));

				JourneyPlayer journeyPlayer = JCapabilityManager.asJourneyPlayer(Minecraft.getMinecraft().player);
				JourneyPlayerImpl.Serializer.INSTANCE.readFromBuffer(journeyPlayer, packetBuffer);
			});

			return null;
		}
	}
}