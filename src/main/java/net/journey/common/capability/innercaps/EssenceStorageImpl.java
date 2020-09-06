package net.journey.common.capability.innercaps;

import net.journey.api.capability.EssenceStorage;
import net.journey.common.capability.SerializableInnerCap;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.network.PacketBuffer;

public class EssenceStorageImpl extends SerializableInnerCap<NBTBase, EssenceStorageImpl> implements EssenceStorage {
	private final int maxValue = 10;

	private int essenceValue = 0;
	private int regenCooldown = 0;

	@Override
	public boolean useEssence(int points) {
		if (essenceValue < points)
			return false;
		essenceValue -= points;
		return true;
	}

	@Override
	public void addEssence(int points) {
		essenceValue += points;

		coerceEssence(this);
	}

	@Override
	public int getEssenceValue() {
		return essenceValue;
	}

	@Override
	public int getMaxValue() {
		return maxValue;
	}

	@Override
	public boolean isFull() {
		return getEssenceValue() == getMaxValue();
	}

	@Override
	public void onTick() {
		if (regenCooldown-- <= 0) regenCooldown = 30;
		if (regenCooldown >= 30) regen();
	}

	@Override
	public void regen() {
		addEssence(1);
	}

	private static void coerceEssence(EssenceStorageImpl essenceBar) {
		essenceBar.essenceValue = Math.min(essenceBar.getEssenceValue(), essenceBar.getMaxValue());
	}

	@Override
	public NBTBase serializeNBT() {
		NBTTagCompound compound = new NBTTagCompound();
		compound.setInteger("regen_cooldown", regenCooldown);
		compound.setInteger("essence_value", essenceValue);
		return compound;
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		if (nbt instanceof NBTTagInt) {
			readFromOldNBT((NBTTagInt) nbt);
		} else {
			readFromNBT(((NBTTagCompound) nbt));
		}
	}

	private void readFromNBT(NBTTagCompound tag) {
		regenCooldown = tag.getInteger("regen_cooldown");
		essenceValue = tag.getInteger("essence_value");

		coerceEssence(this);
	}

	@Deprecated //remove in 1.13+
	private void readFromOldNBT(NBTTagInt tag) {
		essenceValue = tag.getInt();
	}

	@Override
	public void writeToBuffer(PacketBuffer buffer) {
		buffer.writeInt(regenCooldown);
		buffer.writeInt(essenceValue);
	}

	@Override
	public void readFromBuffer(PacketBuffer buffer) {
		regenCooldown = buffer.readInt();
		essenceValue = buffer.readInt();
	}
}