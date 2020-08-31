package net.journey.client.server;

import net.journey.client.server.player.IPlayerStats;
import net.journey.client.server.player.PlayerStatsProvider;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class RenderBar {


    @SubscribeEvent
    public void onTick(PlayerTickEvent event) {
        if (event.phase == Phase.END) {
            if (event.player != null) {
                IEssence mana = event.player.getCapability(EssenceProvider.ESSENCE_CAP, null);
    			IPlayerStats stats = event.player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAP, null);
                mana.update();
                stats.update();
            }
        }
    }
}