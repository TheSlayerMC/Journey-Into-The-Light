package net.journey.integration.tconstruct;

import net.journey.JITL;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class TCClientHandler {

	@SubscribeEvent
	public void registerFluidModels(ModelRegistryEvent event) {
		JITL.proxy.registerFluidModels(TCFluids.CELESTIUM);
	}
}
