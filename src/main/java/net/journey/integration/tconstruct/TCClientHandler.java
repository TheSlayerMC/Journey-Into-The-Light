package net.journey.integration.tconstruct;

import net.journey.JITL;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TCClientHandler {

	@SubscribeEvent
	public static void registerFluidModels(ModelRegistryEvent event) {
		JITL.proxy.registerFluidModels(TCFluids.CELESTIUM);
	}
}
