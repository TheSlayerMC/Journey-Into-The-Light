package net.journey.integration;

import net.journey.JITL;
import net.journey.integration.jer.JERIntegration;
import net.journey.integration.tconstruct.TCMaterials;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Integrations {
	public static final boolean IS_JER_LOADED = Loader.isModLoaded("jeresources");
	public static final boolean IS_TCONSTRUCT_LOADED = Loader.isModLoaded("tconstruct");

	public static void onInit(FMLInitializationEvent event) {
		if (IS_JER_LOADED) {
			JERIntegration.onInit(event);
			JITL.LOGGER.info("Detected Just Enough Resources. Initializing compatibility with " + JITL.MOD_NAME);
		}
	}

	public static void onPreInit(FMLPreInitializationEvent event) {
		if (IS_TCONSTRUCT_LOADED) {
			TCMaterials.preInit(event);
			JITL.LOGGER.info("Detected Tinkers' Construct. Initializing compatibility with " + JITL.MOD_NAME);
		}
	}
}
