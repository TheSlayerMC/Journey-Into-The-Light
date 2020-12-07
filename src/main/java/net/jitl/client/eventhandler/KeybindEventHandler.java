package net.jitl.client.eventhandler;

import net.jitl.client.render.screen.ScreenPlayerStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeybindEventHandler {

    public static KeyBinding STATS;

    public static void registerKeys() {
        STATS = new KeyBinding("Open Journey Stats", 74, "JITL Keys");

        MinecraftForge.EVENT_BUS.register(new KeybindEventHandler());
        ClientRegistry.registerKeyBinding(STATS);
    }

    @SubscribeEvent
    public void pressKey(InputEvent.KeyInputEvent event) {
        if(STATS.isDown()) {
            if(Minecraft.getInstance().screen == null) {
                Minecraft.getInstance().setScreen(new ScreenPlayerStats(Minecraft.getInstance().player.inventory));
            }
        }
    }

}
