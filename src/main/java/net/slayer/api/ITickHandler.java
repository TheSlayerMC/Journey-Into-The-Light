package net.slayer.api;

import net.minecraftforge.fml.common.gameevent.TickEvent;

public interface ITickHandler {

    void renderTick(TickEvent.RenderTickEvent event);

    void playerTick(TickEvent.PlayerTickEvent event);

    void serverTick(TickEvent.ServerTickEvent event);

    void worldTick(TickEvent.WorldTickEvent event);
}