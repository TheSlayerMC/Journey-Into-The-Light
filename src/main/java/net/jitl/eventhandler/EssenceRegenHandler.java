package net.jitl.eventhandler;

import net.jitl.JITL;
import net.jitl.common.capability.JCapabilityProvider;
import net.jitl.common.capability.essence.IEssenceCapability;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class EssenceRegenHandler {

    @SubscribeEvent()
    public static void tick(TickEvent.PlayerTickEvent event) {
        if (event.player instanceof ServerPlayerEntity) {
            ServerPlayerEntity entity = (ServerPlayerEntity) event.player;
            Optional<IEssenceCapability> essenceCapability = entity.getCapability(JCapabilityProvider.ESSENCE).resolve();
            essenceCapability.ifPresent(iEssenceCapability -> iEssenceCapability.onTick(entity));
        }
    }
}
