package net.journey.common.capability.innercaps;

import net.journey.api.capability.PlayerStats;
import net.journey.common.capability.InnerCapSerializer;
import net.journey.common.knowledge.EnumKnowledgeType;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;

public class PlayerStatsImpl implements PlayerStats {
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

	public void onTick() {
		//addSentacoin(1);
		//sentacoinValue = getSentacoinValue();
	}

	public static class Serializer extends InnerCapSerializer<PlayerStatsImpl, NBTTagCompound> {

		public NBTTagCompound writeToNBT(PlayerStatsImpl stats) {
			NBTTagCompound compound = new NBTTagCompound();
			compound.setInteger("sentacoin_value", stats.sentacoinValue);

			NBTTagCompound knowledgeTag = new NBTTagCompound();
			stats.knowledgeMap.forEach((type, storage) -> knowledgeTag.setTag(type.getName(), storage.serializeNBT()));
			compound.setTag("knowledge", knowledgeTag);

			return compound;
		}

		@Override
		protected void readFromNBTCasted(PlayerStatsImpl stats, NBTTagCompound tag) {
			stats.sentacoinValue = tag.getInteger("sentacoin_value");

			NBTTagCompound knowledgeMapTag = tag.getCompoundTag("knowledge");

			stats.knowledgeMap.forEach((type, storage) -> {
				NBTTagCompound knowledgeTag = knowledgeMapTag.getCompoundTag(type.getName());

				//noinspection ConstantConditions
				if (knowledgeTag != null) {
					storage.deserializeNBT(knowledgeTag);
				}
			});
		}
	}
}