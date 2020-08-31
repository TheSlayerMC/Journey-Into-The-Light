package net.journey.client.server;

import net.journey.JITL;
import net.journey.client.server.player.PlayerStatsProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BarTickHandler {

	public static final ResourceLocation ESSENCE_CAP = new ResourceLocation(JITL.MOD_ID, "essence_mana");
	public static final ResourceLocation PLAYER_STATS_CAP = new ResourceLocation(JITL.MOD_ID, "player_stats");

	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof EntityPlayer) {
			event.addCapability(ESSENCE_CAP, new EssenceProvider());
			event.addCapability(PLAYER_STATS_CAP, new PlayerStatsProvider());
		}
	}
}