package net.journey.client.server.player;

import net.journey.client.server.EssenceBar;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;

public class PlayerStats implements IPlayerStats {

	private int sentacoinValue = 0;

	private float overworldXP = 0;
	private int overworldLevel = 0;

	public static NBTTagCompound writeToNBT(PlayerStats stats) {
		NBTTagCompound compound = new NBTTagCompound();
		compound.setInteger("sentacoin_value", stats.sentacoinValue);

		compound.setFloat("overworld_xp", stats.overworldXP);
		compound.setInteger("overworld_level", stats.overworldLevel);

		return compound;
	}

	public static void readFromNBT(PlayerStats stats, NBTTagCompound tag) {
		stats.sentacoinValue = tag.getInteger("sentacoin_value");

		stats.overworldLevel = tag.getInteger("overworld_level");
		stats.overworldXP = tag.getFloat("overworld_xp");
	}

	@Override
	public void addExperience(EnumPlayerStats stat, int amount, EntityPlayer p) {
		if(stat == EnumPlayerStats.OVERWORLD) {
			int value = Integer.MAX_VALUE - overworldLevel;
			if(amount > value)  amount = value; 
			overworldXP += (float)amount / (float)normalCap();
			for(overworldLevel += amount; overworldXP >= 1.0F; overworldXP /= (float)normalCap()) {
				overworldXP = (overworldXP - 1.0F) * (float)18;
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
		switch(stat) { 
		case OVERWORLD:
			return overworldXP;
		default:
			break;
		}
		return 0F;
	}

	@Override
	public int getPlayerLevel(EnumPlayerStats stat) {
		switch(stat) { 
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

	@Override
	public void update() {
		//addSentacoin(1);
		//sentacoinValue = getSentacoinValue();
	}

	public static void readFromOldNBT(PlayerStats stats, NBTTagInt tag) {
		stats.sentacoinValue = tag.getInt();
	}

	public int normalCap(){
		return 23;
	}
}