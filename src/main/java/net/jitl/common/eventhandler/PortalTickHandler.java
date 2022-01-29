package net.jitl.common.eventhandler;

import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.capability.player.data.Portal;
import net.jitl.core.JITL;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class PortalTickHandler {

    @SubscribeEvent()
    public static void tick(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER) {
            JPlayer playerCapability = JPlayer.from(event.player);
            if (playerCapability != null) {
                Portal portal = playerCapability.portal;
                portal.onTick();
                playerCapability.detectAndSendChanges();
            }
        }
    }
}