package net.journey.client;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeyInputHandler {

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {
		//if(Keybinding.stats.isPressed())
		//	Essence.packetHandler.sendToServer(new PacketOpenGui().setID(GuiIDs.STATS.ordinal()));
		
	}
}