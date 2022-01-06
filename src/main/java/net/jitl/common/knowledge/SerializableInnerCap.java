package net.jitl.common.knowledge;

import io.netty.buffer.Unpooled;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.common.util.INBTSerializable;

import Tag;

public abstract class SerializableInnerCap<NBT extends Tag, T extends SerializableInnerCap<NBT, T>> implements INBTSerializable<NBT> {
	public abstract void writeToBuffer(FriendlyByteBuf buffer);

	public abstract void readFromBuffer(FriendlyByteBuf buffer);

	public void copy(T to) {
		FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
		writeToBuffer(buffer);

		to.readFromBuffer(buffer);
	}
}