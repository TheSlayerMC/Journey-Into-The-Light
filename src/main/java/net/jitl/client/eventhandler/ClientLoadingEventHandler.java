package net.jitl.client.eventhandler;

import net.jitl.client.Models;
import net.jitl.client.world.FrozenRenderInfo;
import net.jitl.init.JDimensions;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ClientLoadingEventHandler {
    public static void regToBus(IEventBus modBus, IEventBus forgeBus) {
        modBus.addListener(ClientLoadingEventHandler::onClientSetup);
        Models.regToBus(modBus, forgeBus);
    }

    private static void onClientSetup(FMLClientSetupEvent event) {
        KeybindEventHandler.registerKeys(event);
        event.enqueueWork(() -> {
            DimensionRenderInfo.EFFECTS.put(JDimensions.FROZEN.location(), new FrozenRenderInfo());
        });
    }
}
