package net.slayer.api;

import net.minecraftforge.fml.common.gameevent.TickEvent;

public abstract interface ITickHandler {
	
	public abstract void renderTick(TickEvent.RenderTickEvent event);
	
	public abstract void playerTick(TickEvent.PlayerTickEvent event);
	
	public abstract void serverTick(TickEvent.ServerTickEvent event);
	
	public abstract void worldTick(TickEvent.WorldTickEvent event);
}