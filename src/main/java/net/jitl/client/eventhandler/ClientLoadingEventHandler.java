package net.jitl.client.eventhandler;

import net.jitl.client.Models;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientLoadingEventHandler {
    public static void regToBus(IEventBus modBus, IEventBus forgeBus) {
        modBus.addListener(ClientLoadingEventHandler::onClientSetup);
        Models.regToBus(modBus, forgeBus);
    }

    private static void onClientSetup(FMLClientSetupEvent event) {
        KeybindEventHandler.registerKeys(event);
    }
}
