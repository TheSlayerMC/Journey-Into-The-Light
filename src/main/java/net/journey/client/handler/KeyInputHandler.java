package net.journey.client.handler;

import org.lwjgl.input.Keyboard;

import net.journey.JITL;
import net.journey.client.render.gui.GUIPlayerStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeyInputHandler {

	private KeyBinding stats = new KeyBinding("Journey Stats", Keyboard.KEY_X, "Journey Keys");
	
	public KeyInputHandler() {
        ClientRegistry.registerKeyBinding(stats);
	}
	
    @SubscribeEvent
    public void onKeyInput(KeyInputEvent event) {
    	if(stats.isPressed()) {
    		if(Minecraft.getMinecraft().currentScreen == null) {
    			Minecraft.getMinecraft().displayGuiScreen(new GUIPlayerStats());
    		}
    	}
    }
}