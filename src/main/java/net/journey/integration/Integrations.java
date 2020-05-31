package net.journey.integration;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class Integrations {
	public static final boolean IS_JER_LOADED = Loader.isModLoaded("jeresources");

	public static void onInit(FMLInitializationEvent event) {
		if (IS_JER_LOADED) {
			JERIntegration.onInit(event);
		}
	}
}
