package net.journey.client;

import net.journey.util.ManaProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.slayer.api.SlayerAPI;

public class JourneyCapabilityHandler {
	
	public static final ResourceLocation MANA_CAP = new ResourceLocation(SlayerAPI.MOD_ID, "testmana");

	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent.Entity event) {
		if (!(event.getEntity() instanceof EntityPlayer))
			return;

		event.addCapability(MANA_CAP, new ManaProvider());
	}
}