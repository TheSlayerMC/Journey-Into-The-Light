package net.journey.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public abstract class BasePacket implements IMessage {
	@Override
	public final void toBytes(ByteBuf buf) {
		write(new PacketBuffer(buf));
	}

	@Override
	public final void fromBytes(ByteBuf buf) {
		read(new PacketBuffer(buf));
	}

	public abstract void write(PacketBuffer buffer);

	public abstract void read(PacketBuffer buffer);
}
