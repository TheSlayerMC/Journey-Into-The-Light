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
import org.jetbrains.annotations.ApiStatus;

public class S2CSyncJourneyCap implements IMessage {
	private JourneyPlayer playerCap;
	private ByteBuf clientBuf;

	@ApiStatus.Internal
	public S2CSyncJourneyCap() {
	}

	public S2CSyncJourneyCap(JourneyPlayer playerCap) {
		this.playerCap = playerCap;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);

		JourneyPlayerImpl.Serializer.INSTANCE.writeToBuffer(playerCap, buffer);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.clientBuf = buf.copy();
	}

	public static class Handler implements IMessageHandler<S2CSyncJourneyCap, IMessage> {

		@Override
		public IMessage onMessage(S2CSyncJourneyCap message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				PacketBuffer packetBuffer = new PacketBuffer(Unpooled.wrappedBuffer(message.clientBuf));

				JourneyPlayer journeyPlayer = JCapabilityManager.asJourneyPlayer(Minecraft.getMinecraft().player);
				JourneyPlayerImpl.Serializer.INSTANCE.readFromBuffer(journeyPlayer, packetBuffer);
			});

			return null;
		}
	}
}