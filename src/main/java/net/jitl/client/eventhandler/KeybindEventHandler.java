package net.jitl.client.eventhandler;

import com.mojang.blaze3d.platform.InputConstants;
import net.jitl.client.render.screen.ScreenPlayerStats;
import net.jitl.core.network.JPacketHandler;
import net.jitl.core.network.KeyPressedPacket;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;

public class KeybindEventHandler {

    public static KeyMapping keyStats;
    public static KeyMapping keyArmor;
    public static KeyMapping keyAmulet;
    private static final Minecraft MINECRAFT = Minecraft.getInstance();

    public static void registerKeys(FMLClientSetupEvent event) {
        keyStats = new KeyMapping("Open Journey Stats", GLFW.GLFW_KEY_J, I18n.get("jitl.keys"));
        keyArmor = new KeyMapping("Use Armor Ability", GLFW.GLFW_KEY_C, I18n.get("jitl.keys"));
        keyAmulet = new KeyMapping("Use Amulet Ability", GLFW.GLFW_KEY_V, I18n.get("jitl.keys"));

        ClientRegistry.registerKeyBinding(keyStats);
        ClientRegistry.registerKeyBinding(keyArmor);
        ClientRegistry.registerKeyBinding(keyAmulet);
    }

    public static void onKeyPressed(InputEvent.KeyInputEvent event) {
        InputConstants.Key key = InputConstants.getKey(event.getKey(), event.getScanCode());
        if (MINECRAFT.screen == null) {
            assert MINECRAFT.player != null;
            int action = event.getAction();
            if (action == GLFW.GLFW_PRESS) {
                if (key == keyStats.getKey()) {
                    MINECRAFT.setScreen(new ScreenPlayerStats(MINECRAFT.player.getInventory()));
                } else {
                    handleAbilityKeys(key, action);
                }
            } else if (action == GLFW.GLFW_RELEASE) {
                handleAbilityKeys(key, action);
            }
        }
    }

    static void handleAbilityKeys(InputConstants.Key input, int action) {
        boolean key = input == keyAmulet.getKey();
        if (key || input == keyArmor.getKey()) {
            JPacketHandler.INSTANCE.sendToServer(new KeyPressedPacket(key, action == GLFW.GLFW_PRESS));
        }
    }
}
