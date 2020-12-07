package net.jitl.client.eventhandler;

import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class ClientEventHandler {
    public static void regToBus(IEventBus modBus, IEventBus forgeBus) {
        forgeBus.addListener(ClientEventHandler::onKeyEvent);
    }

    private static void onKeyEvent(InputEvent.KeyInputEvent event) {
        KeybindEventHandler.onKeyPressed(event);
    }
}
