package net.journey.client.server;

import net.minecraft.nbt.NBTBase;

public interface IEssence {

	public boolean useEssence(int points);
	public void addEssence(int points);
	public void setEssence(int essence);
	public int getEssenceValue();
	public void update();
	public void regen();
	public NBTBase writeNBT();
	public void readNBT(NBTBase nbt);

}