package net.journey.integration;

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
		}
	}

	public static void onPreInit(FMLPreInitializationEvent event) {
		if (IS_TCONSTRUCT_LOADED) {
			TCMaterials.preInit(event);
		}
	}
}
