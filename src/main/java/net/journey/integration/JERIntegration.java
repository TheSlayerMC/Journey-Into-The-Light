package net.journey.integration;

import jeresources.api.IJERAPI;
import jeresources.api.JERPlugin;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class JERIntegration {
	@JERPlugin
	public static IJERAPI jerAPI;

	public static void onInit(FMLInitializationEvent event) {
//		jerAPI.getMobRegistry().register(EntityRegistry);
	}
}
