package net.journey.client.handler;

import net.journey.client.render.gui.GuiPlayerStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.input.Keyboard;

@Mod.EventBusSubscriber(Side.CLIENT)
public class KeyInputHandler {
	private static final KeyBinding KEY_OPEN_STATS = new KeyBinding("Open Journey Stats", Keyboard.KEY_P, "JITL Keys");

	public static void init() {
		ClientRegistry.registerKeyBinding(KEY_OPEN_STATS);
	}

	@SubscribeEvent
	public static void onKeyInput(KeyInputEvent event) {
		if (KEY_OPEN_STATS.isPressed()) {
			if (Minecraft.getMinecraft().currentScreen == null) {
				Minecraft.getMinecraft().displayGuiScreen(new GuiPlayerStats());
			}
		}
	}
}