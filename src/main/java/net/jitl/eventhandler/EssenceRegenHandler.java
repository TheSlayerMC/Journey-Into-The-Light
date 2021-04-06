package net.jitl.eventhandler;

import net.jitl.JITL;
import net.jitl.common.capability.JCapabilityProvider;
import net.jitl.common.capability.player.JPlayer;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class EssenceRegenHandler {

    @SubscribeEvent()
    public static void tick(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER) {
            JPlayer playerCapability = JPlayer.from(event.player);
            if (playerCapability != null) {
                playerCapability.essence.get().addEssence(0.003125F);
                playerCapability.detectAndSendChanges();
                JITL.LOGGER.info("Current essence " + playerCapability.essence.get().currentEssence.get());
            }
        }
    }
}
