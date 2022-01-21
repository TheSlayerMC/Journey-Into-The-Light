package net.jitl.common.eventhandler;

import net.jitl.core.JITL;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class EssenceTickHandler {

    @SubscribeEvent()
    public static void tick(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER) {
            //FIXME uncomment when player cap is fixed
            /*if (Essence.getMaxEssence(event.player) > 0) {
                JPlayer playerCapability = JPlayer.from(event.player);
                if (playerCapability != null) {
                    Essence essence = playerCapability.essence;
                    if (essence.isRegenReady()) {
                        essence.addEssence(event.player, (float) Objects.requireNonNull(event.player.getAttribute(JAttributes.ESSENCE_REGEN_SPEED.get())).getValue());
                    } else {
                        essence.setBurnout(essence.getBurnout() - 0.1F);
                    }
                    playerCapability.detectAndSendChanges();
                }
            }*/
        }
    }
}
