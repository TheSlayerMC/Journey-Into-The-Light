package net.journey.client.server;

import net.minecraft.entity.player.EntityPlayer;

public interface IEssence {

	public boolean useEssence(int points);
	public void addEssence(int points);
	public void setEssence(int essence);
	public int getEssenceValue();
	public void update();
	public void regen();
	
	public EntityPlayer getPlayer(EntityPlayer player);
}