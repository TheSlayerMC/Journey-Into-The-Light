package net.journey.common.capability.innercaps;

import net.journey.api.capability.PlayerStats;
import net.journey.common.capability.SerializableInnerCap;
import net.journey.common.knowledge.EnumKnowledgeType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

import java.util.HashMap;

public class PlayerStatsImpl extends SerializableInnerCap<NBTTagCompound, PlayerStatsImpl> implements PlayerStats {
	private final HashMap<EnumKnowledgeType, KnowledgeStorageImpl> knowledgeMap;
	private int sentacoinValue = 0;

	public PlayerStatsImpl() {
		knowledgeMap = new HashMap<>(EnumKnowledgeType.values().length, 1);

		for (EnumKnowledgeType type : EnumKnowledgeType.values()) {
			knowledgeMap.put(type, new KnowledgeStorageImpl());
		}
	}

	@Override
	public void addKnowledge(EnumKnowledgeType type, float amount) {
		getKnowledge(type).add(amount);
	}

	@Override
	public float removeKnowledge(EnumKnowledgeType type, float amount) {
		return getKnowledge(type).remove(amount);
	}

	@Override
	public KnowledgeStorageImpl getKnowledge(EnumKnowledgeType type) {
		return knowledgeMap.get(type);
	}

	@Override
	public void addSentacoin(int amount) {
		sentacoinValue += amount;
	}

	@Override
	public int getSentacoinValue() {
		return sentacoinValue;
	}

	@Override
	public boolean useCoins(int amount) {
		if (sentacoinValue < amount)
			return false;
		sentacoinValue -= amount;
		return true;
	}
	
	public void onTick() {
		
	}

	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound compound = new NBTTagCompound();
		compound.setInteger("sentacoin_value", sentacoinValue);

		NBTTagCompound knowledgeTag = new NBTTagCompound();
		knowledgeMap.forEach((type, storage) -> knowledgeTag.setTag(type.getName(), storage.serializeNBT()));
		compound.setTag("knowledge", knowledgeTag);

		return compound;
	}

	@Override
	public void deserializeNBT(NBTTagCompound tag) {
		sentacoinValue = tag.getInteger("sentacoin_value");

		NBTTagCompound knowledgeMapTag = tag.getCompoundTag("knowledge");

		knowledgeMap.forEach((type, storage) -> {
			NBTTagCompound knowledgeTag = knowledgeMapTag.getCompoundTag(type.getName());

			//noinspection ConstantConditions
			if (knowledgeTag != null) {
				storage.deserializeNBT(knowledgeTag);
			}
		});
	}

	@Override
	public void writeToBuffer(PacketBuffer buffer) {
		buffer.writeInt(sentacoinValue);

		buffer.writeInt(knowledgeMap.size());
		knowledgeMap.forEach((type, storage) -> {
			EnumKnowledgeType.writeToBuffer(type, buffer);
			storage.writeToBuffer(buffer);
		});
	}

	@Override
	public void readFromBuffer(PacketBuffer buffer) {
		sentacoinValue = buffer.readInt();

		int size = buffer.readInt();
		for (int i = 0; i < size; i++) {
			EnumKnowledgeType type = EnumKnowledgeType.readFromBuffer(buffer);
			knowledgeMap.get(type).readFromBuffer(buffer);
		}
	}


}