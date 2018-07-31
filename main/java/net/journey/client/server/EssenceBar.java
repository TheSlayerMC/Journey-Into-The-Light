package net.journey.client.server;

public class EssenceBar implements IEssence {

	private int essenceValue = 10;
	
	@Override
	public boolean useEssence(int points) {
		if(essenceValue < points) 
			return false;
		essenceValue -= points;
		return true;
	}

	@Override
	public void addEssence(int points) {
		essenceValue += points;
		if(essenceValue > 10) essenceValue = 10;
	}

	@Override
	public void setEssence(int essence) {
		essenceValue = essence;
	}

	@Override
	public int getEssenceValue() {
		return essenceValue;
	}

	@Override
	public void update() {
		if(essenceValue > 10) essenceValue = 10;		
	}
	
	@Override
	public void regen() {
		essenceValue += 1;
	}
}