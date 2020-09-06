package net.journey.common.capability;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.capabilities.Capability;

public abstract class SyncableStorage<T, IMPL> implements Capability.IStorage<T> {
	private final Class<IMPL> implClass;

	public SyncableStorage(Class<IMPL> implClass) {
		this.implClass = implClass;
	}

	protected IMPL validateDefaultImpl(T instance) {
		if (!(implClass.isInstance(instance))) {
			throw new IllegalArgumentException("Can't work with instance that isn't the default implementation");
		}

		return (IMPL) instance;
	}

	public abstract void writeToBuffer(T instance, PacketBuffer buffer);

	public abstract void readFromBuffer(T instance, PacketBuffer buffer);

	public abstract void copy(T from, T to);
}
