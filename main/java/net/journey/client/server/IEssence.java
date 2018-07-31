package net.journey.client.server;

public interface IEssence {

	public boolean useEssence(int points);
	public void addEssence(int points);
	public void setEssence(int essence);
	public int getEssenceValue();
	public void update();
	public void regen();
}