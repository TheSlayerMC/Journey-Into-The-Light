package net.jitl.client.eventhandler;

import net.jitl.client.render.screen.ScreenPlayerStats;
import net.jitl.network.JPacketHandler;
import net.jitl.network.KeyPressedPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;

public class KeybindEventHandler {

    public static KeyBinding keyStats;
    private static final Minecraft MINECRAFT = Minecraft.getInstance();

    public static void registerKeys(FMLClientSetupEvent event) {
        keyStats = new KeyBinding("Open Journey Stats", GLFW.GLFW_KEY_J, "JITL Keys");//TODO I18n

        ClientRegistry.registerKeyBinding(keyStats);
    }

    static void onKeyPressed(InputEvent.KeyInputEvent event) {
        InputMappings.Input key = InputMappings.getKey(event.getKey(), event.getScanCode());
        if (MINECRAFT.screen == null) {
            assert MINECRAFT.player != null;
            if (event.getAction() == GLFW.GLFW_PRESS) {
                if (key == keyStats.getKey()) {
                    System.out.println("Stats");
                    MINECRAFT.setScreen(new ScreenPlayerStats(MINECRAFT.player.inventory));
                } else if (key == MINECRAFT.options.keyJump.getKey() && !MINECRAFT.player.isOnGround() && !MINECRAFT.player.isCreative()) {
                    System.out.println("Jump");
                    Vector3d move = MINECRAFT.player.getDeltaMovement();
                    JPacketHandler.INSTANCE.sendToServer(new KeyPressedPacket(Math.atan2(move.z(), move.x())));
                }
            }
        }
    }
}
