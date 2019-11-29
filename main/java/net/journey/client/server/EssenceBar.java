package net.journey.client.server;

import net.journey.JITL;
import net.journey.essence.MessageEssenceBar;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.MathHelper;

public class EssenceBar implements IEssence {

	private int tickDelay = 40;
	private int max = 10; 
	private int essenceValue = 0;
	private int regenValue = 0;

	@Override
	public void useEssence(EntityPlayer player, int points) {
		 if (player.capabilities.isCreativeMode) return;
	        set(getEssence() - points);
	        sendPacket(player);
	}

	@Override
	public void fill(EntityPlayer player, int points) {
       /* int prev = getEssence();
        
        set(prev + points);
        if(prev != getEssence()) sendPacket(player);*/
		essenceValue += 1;
	}

	@Override
	public void regen(EntityPlayer player) {
		int delay = tickDelay;
        if(delay-- <= 0) delay = 500;
        if(delay >= 0) fill(player, 1);
		if(getEssence() > 10) set(10);
       // essenceValue = 0;
        System.out.println(getEssence());
	}

	@Override
	public void set(int points) {
        essenceValue = MathHelper.clamp(points, 0, getMaxEssence());
	}

	@Override
	public int getEssence() {
		return essenceValue;
	}

	@Override
	public int getMaxEssence() {
		return max;
	}

	@Override
	public void setMaxEssence(int max) {
		if(max < 0) throw new IllegalArgumentException("Max of essence cn't be less then null!");

		this.max = max;
	}

	@Override
	public int getRegenDelay() {
		return tickDelay;
	}

	@Override
	public void setRegenDelay(int delay) {
		//if(delay < 1)  throw new IllegalArgumentException("Tick delay beetween regen can't be less than one!");
		//tickDelay = delay;
	}

	private void sendPacket(EntityPlayer player) {
		if(player instanceof EntityPlayerMP)
			JITL.network.sendTo(new MessageEssenceBar(this), (EntityPlayerMP) player);
	}
}