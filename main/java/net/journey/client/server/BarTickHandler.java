package net.journey.client.server;

import static net.journey.essence.EssenceProvider.ESSENCE_CAP;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class BarTickHandler {

	@SubscribeEvent
	public void onTick(PlayerTickEvent event) {
		if (event.phase == Phase.END) {
			getEssence(event.player).regen(event.player);
		}
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		refillEssence(event.player);
	}

	@SubscribeEvent
	public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
		drainEssence(event.player);
	}

	@SubscribeEvent
	public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
		refillEssence(event.player);
	}

	@SubscribeEvent
	public void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
		refillEssence(event.player);
		event.player.addExperienceLevel(0);
	}

	private void drainEssence(EntityPlayer player) {
		IEssence essence = getEssence(player);
		essence.useEssence(player, essence.getEssence());
	}


	public static IEssence getEssence(Entity entity){
		return entity.getCapability(ESSENCE_CAP, null);
	}

	private void refillEssence(EntityPlayer player) {
		IEssence essence = getEssence(player);
		essence.fill(player, essence.getMaxEssence());

		if (!player.isServerWorld()) {
			EssenceRenderer.percantage = 100 * (essence.getEssence() / essence.getMaxEssence());
		}
	}
}
