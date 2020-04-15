package net.journey.client.server;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class RenderBar {


    @SubscribeEvent
    public void onTick(PlayerTickEvent event) {
        if (event.phase == Phase.END) {
            if (event.player != null) {
                IEssence mana = event.player.getCapability(EssenceProvider.ESSENCE_CAP, null);
                mana.update();
            }
        }
    }
}