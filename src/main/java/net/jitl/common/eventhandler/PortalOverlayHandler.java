package net.jitl.common.eventhandler;

import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.capability.player.data.Portal;
import net.jitl.core.JITL;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class PortalOverlayHandler {

    @SubscribeEvent
    public static void onPlayerUpdate(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving() instanceof Player player) {
            JPlayer playerCapability = JPlayer.from(player);
            if (playerCapability != null) {
                Portal portal = playerCapability.portal;
                portal.onTick();
                playerCapability.detectAndSendChanges();
            }
        }
    }
}