package net.jitl.client.eventhandler;

import net.jitl.client.render.screen.ScreenPlayerStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;

public class KeybindEventHandler {

    public static KeyBinding keyStats;

    public static void registerKeys(FMLClientSetupEvent event) {
        keyStats = new KeyBinding("Open Journey Stats", GLFW.GLFW_KEY_J, "JITL Keys");//TODO I18n

        ClientRegistry.registerKeyBinding(keyStats);
    }

    static void onKeyPressed(InputEvent.KeyInputEvent event) {
        if (keyStats.isDown()) {
            if (Minecraft.getInstance().screen == null) {
                Minecraft.getInstance().setScreen(new ScreenPlayerStats(Minecraft.getInstance().player.inventory));
            }
        }
    }
}
