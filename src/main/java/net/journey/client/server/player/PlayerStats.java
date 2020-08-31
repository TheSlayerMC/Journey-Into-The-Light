package net.journey.client.server.player;

import net.journey.client.server.EssenceBar;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;

public class PlayerStats implements IPlayerStats {

	private int sentacoinValue = 0;

	public static NBTTagCompound writeToNBT(PlayerStats stats) {
		NBTTagCompound compound = new NBTTagCompound();
		compound.setInteger("sentacoin_value", stats.sentacoinValue);
		return compound;
	}

	public static void readFromNBT(PlayerStats stats, NBTTagCompound tag) {
		stats.sentacoinValue = tag.getInteger("sentacoin_value");
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
	public void update() {
		//addSentacoin(1);
		//sentacoinValue = getSentacoinValue();
	}

	public static void readFromOldNBT(PlayerStats stats, NBTTagInt tag) {
		stats.sentacoinValue = tag.getInt();
	}
}