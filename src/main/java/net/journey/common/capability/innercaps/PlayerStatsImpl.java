package net.journey.common.capability.innercaps;

import net.journey.api.capability.PlayerStats;
import net.journey.common.capability.InnerCapSerializer;
import net.journey.common.knowledge.EnumPlayerStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;

public class PlayerStatsImpl implements PlayerStats {

	private int sentacoinValue = 0;

	private float overworldXP = 0;
	private int overworldLevel = 0;

	@Override
	public void addExperience(EnumPlayerStats stat, float amount, EntityPlayer p) {
		if (stat == EnumPlayerStats.OVERWORLD) {
			int value = Integer.MAX_VALUE - overworldLevel;
			if (amount > value) amount = value;
			overworldXP += (float) amount / (float) normalCap();
			for (overworldLevel += amount; overworldXP >= 1.0F; overworldXP /= (float) normalCap()) {
				overworldXP = (overworldXP - 1.0F) * (float) 18;
				addLevel(EnumPlayerStats.OVERWORLD, 1, p);
			}
		}
	}

	@Override
	public void addLevel(EnumPlayerStats stat, int amount, EntityPlayer p) {
		if(stat == EnumPlayerStats.OVERWORLD) {
			overworldLevel += amount;
		}
	}

	@Override
	public float getPlayerXP(EnumPlayerStats stat) {
		switch (stat) {
			case OVERWORLD:
				return overworldXP;
			default:
				break;
		}
		return 0F;
	}

	@Override
	public int getPlayerLevel(EnumPlayerStats stat) {
		switch (stat) {
			case OVERWORLD:
				return overworldLevel;
			default:
				break;
		}
		return 0;
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

	public static void readFromOldNBT(PlayerStatsImpl stats, NBTTagInt tag) {
		stats.sentacoinValue = tag.getInt();
	}

	public int normalCap() {
		return 23;
	}

	public static class Serializer extends InnerCapSerializer<PlayerStatsImpl, NBTTagCompound> {

		public NBTTagCompound writeToNBT(PlayerStatsImpl stats) {
			NBTTagCompound compound = new NBTTagCompound();
			compound.setInteger("sentacoin_value", stats.sentacoinValue);

			compound.setFloat("overworld_xp", stats.overworldXP);
			compound.setInteger("overworld_level", stats.overworldLevel);

			return compound;
		}

		@Override
		protected void readFromNBTCasted(PlayerStatsImpl stats, NBTTagCompound tag) {
			stats.sentacoinValue = tag.getInteger("sentacoin_value");

			stats.overworldLevel = tag.getInteger("overworld_level");
			stats.overworldXP = tag.getFloat("overworld_xp");
		}
	}
}