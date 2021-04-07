package net.jitl.common.eventhandler;

import net.jitl.JITL;
import net.jitl.common.capability.player.JPlayer;
import net.jitl.init.JAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class EssenceTickHandler {

    @SubscribeEvent()
    public static void tick(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER) {
            JPlayer playerCapability = JPlayer.from(event.player);
            if (playerCapability != null) {
                playerCapability.essence.get().addEssence(0.012125F);
                JITL.LOGGER.info(playerCapability.essence.get().getMaxEssence());
                playerCapability.essence.get().setMaxEssence((float) GlobalEntityTypeAttributes.getSupplier(EntityType.PLAYER).getValue(JAttributes.MAX_ESSENCE.get()));
                playerCapability.detectAndSendChanges();
            }
        }
    }
}
