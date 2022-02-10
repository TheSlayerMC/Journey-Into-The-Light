package net.jitl.client.eventhandler;

import com.mojang.blaze3d.platform.InputConstants;
import net.jitl.client.render.screen.ScreenPlayerStats;
import net.jitl.core.network.JPacketHandler;
import net.jitl.core.network.KeyPressedPacket;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;

import static net.jitl.client.eventhandler.IsometricCameraHandler.handleIsometricCameraKeys;

public class KeybindEventHandler {

    public static KeyMapping keyStats;
    public static KeyMapping keyArmor;
    public static KeyMapping keyAmulet;

    public static KeyMapping keyIsometricView;
    public static KeyMapping keyLockPerspective;
    public static KeyMapping keyMoveCameraUp;
    public static KeyMapping keyMoveCameraDown;
    public static KeyMapping keyMoveCameraLeft;
    public static KeyMapping keyMoveCameraRight;
    public static KeyMapping keyRotateCameraClockwise;
    public static KeyMapping keyRotateCameraCounterClockwise;
    public static KeyMapping keyResetRotation;
    public static KeyMapping keyResetCameraPosition;
    public static KeyMapping keyResetAll;
    public static KeyMapping keyCycleSnapAngle;

    private static final Minecraft MINECRAFT = Minecraft.getInstance();

    public static void registerKeys(FMLClientSetupEvent event) {
        keyStats = new KeyMapping("Open Journey Stats", GLFW.GLFW_KEY_J, I18n.get("jitl.keys"));
        keyArmor = new KeyMapping("Use Armor Ability", GLFW.GLFW_KEY_C, I18n.get("jitl.keys"));
        keyAmulet = new KeyMapping("Use Amulet Ability", GLFW.GLFW_KEY_V, I18n.get("jitl.keys"));

        keyIsometricView = new KeyMapping("Toggle Isometric Camera", GLFW.GLFW_KEY_EQUAL, I18n.get("jitl.keys"));
        keyLockPerspective = new KeyMapping("Lock Isometric Perspective", GLFW.GLFW_KEY_DELETE, I18n.get("jitl.keys"));
        keyMoveCameraUp = new KeyMapping("Move Isometric Camera Up", GLFW.GLFW_KEY_UP, I18n.get("jitl.keys"));
        keyMoveCameraDown = new KeyMapping("Move Isometric Camera Down", GLFW.GLFW_KEY_DOWN, I18n.get("jitl.keys"));
        keyMoveCameraLeft = new KeyMapping("Move Isometric Camera Left", GLFW.GLFW_KEY_LEFT, I18n.get("jitl.keys"));
        keyMoveCameraRight = new KeyMapping("Move Isometric Camera Right", GLFW.GLFW_KEY_RIGHT, I18n.get("jitl.keys"));
        keyRotateCameraClockwise = new KeyMapping("Rotate Camera Clockwise", GLFW.GLFW_KEY_RIGHT_BRACKET, I18n.get("jitl.keys"));
        keyRotateCameraCounterClockwise = new KeyMapping("Rotate Camera Counter-Clockwise", GLFW.GLFW_KEY_LEFT_BRACKET, I18n.get("jitl.keys"));
        keyResetRotation = new KeyMapping("Reset Camera Rotation", GLFW.GLFW_KEY_END, I18n.get("jitl.keys"));
        keyResetCameraPosition = new KeyMapping("Reset Camera Position", GLFW.GLFW_KEY_PAGE_DOWN, I18n.get("jitl.keys"));
        keyResetAll = new KeyMapping("Reset All Isometric Camera Settings", GLFW.GLFW_KEY_BACKSLASH, I18n.get("jitl.keys"));
        keyCycleSnapAngle = new KeyMapping("Cycle through isometric angles", GLFW.GLFW_KEY_MINUS, I18n.get("jitl.keys"));

        ClientRegistry.registerKeyBinding(keyStats);
        ClientRegistry.registerKeyBinding(keyArmor);
        ClientRegistry.registerKeyBinding(keyAmulet);

        ClientRegistry.registerKeyBinding(keyIsometricView);
        ClientRegistry.registerKeyBinding(keyLockPerspective);
        ClientRegistry.registerKeyBinding(keyMoveCameraUp);
        ClientRegistry.registerKeyBinding(keyMoveCameraDown);
        ClientRegistry.registerKeyBinding(keyMoveCameraLeft);
        ClientRegistry.registerKeyBinding(keyMoveCameraRight);
        ClientRegistry.registerKeyBinding(keyRotateCameraClockwise);
        ClientRegistry.registerKeyBinding(keyRotateCameraCounterClockwise);
        ClientRegistry.registerKeyBinding(keyResetRotation);
        ClientRegistry.registerKeyBinding(keyResetAll);
        ClientRegistry.registerKeyBinding(keyCycleSnapAngle);
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
                    handleIsometricCameraKeys(key, action);
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
