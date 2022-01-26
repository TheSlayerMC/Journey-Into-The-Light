package net.jitl.common.knowledge;

import net.jitl.common.helper.EnumKnowledgeType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

import java.util.HashMap;

public class PlayerStatsImpl extends SerializableInnerCap<CompoundTag, PlayerStatsImpl> implements PlayerStats {

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
		getKnowledge(type).add(amount, type);
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
	public CompoundTag serializeNBT() {
		CompoundTag compound = new CompoundTag();
		compound.putInt("sentacoin_value", sentacoinValue);

		CompoundTag knowledgeTag = new CompoundTag();
		knowledgeMap.forEach((type, storage) -> knowledgeTag.put(type.getName(), storage.serializeNBT()));
		compound.put("knowledge", knowledgeTag);

		return compound;
	}

	@Override
	public void deserializeNBT(CompoundTag tag) {
		sentacoinValue = tag.getInt("sentacoin_value");

		CompoundTag knowledgeMapTag = tag.getCompound("knowledge");

		knowledgeMap.forEach((type, storage) -> {
			CompoundTag knowledgeTag = knowledgeMapTag.getCompound(type.getName());

			//noinspection ConstantConditions
			if (knowledgeTag != null) {
				storage.deserializeNBT(knowledgeTag);
			}
		});
	}

	@Override
	public void writeToBuffer(FriendlyByteBuf buffer) {
		buffer.writeInt(sentacoinValue);

		buffer.writeInt(knowledgeMap.size());
		knowledgeMap.forEach((type, storage) -> {
			EnumKnowledgeType.writeToBuffer(type, buffer);
			storage.writeToBuffer(buffer);
		});
	}

	@Override
	public void readFromBuffer(FriendlyByteBuf buffer) {
		sentacoinValue = buffer.readInt();

		int size = buffer.readInt();
		for (int i = 0; i < size; i++) {
			EnumKnowledgeType type = EnumKnowledgeType.readFromBuffer(buffer);
			knowledgeMap.get(type).readFromBuffer(buffer);
		}
	}


}