package net.journey.achievement.event;

import net.journey.JourneyAchievements;
import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.slayer.api.SlayerAPI;

public class JourneySapphireSwordEvent {

	@SubscribeEvent
	public void onCraftSwordEvent(PlayerEvent.ItemCraftedEvent e) {
		if(e.crafting.getItem().equals(JourneyItems.sapphireSword)) {
			e.player.addStat(JourneyAchievements.achievementSapphireSword, 1);
		}
	}
	
	@SubscribeEvent
	public void onCraftTrophieEvent(PlayerEvent.ItemCraftedEvent e) {
		if(e.crafting.getItem().equals(SlayerAPI.toItem(JourneyBlocks.trophy))) {
			e.player.addStat(JourneyAchievements.achievementTrophy, 1);
		}
	}
}
