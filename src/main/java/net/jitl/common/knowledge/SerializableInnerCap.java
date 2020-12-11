package net.jitl.common.knowledge;

import io.netty.buffer.Unpooled;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.util.INBTSerializable;

public abstract class SerializableInnerCap<NBT extends INBT, T extends SerializableInnerCap<NBT, T>> implements INBTSerializable<NBT> {
	public abstract void writeToBuffer(PacketBuffer buffer);

	public abstract void readFromBuffer(PacketBuffer buffer);

	public void copy(T to) {
		PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());
		writeToBuffer(buffer);

		to.readFromBuffer(buffer);
	}
}