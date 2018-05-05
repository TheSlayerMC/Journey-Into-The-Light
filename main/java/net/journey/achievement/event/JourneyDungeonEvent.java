package net.journey.achievement.event;

import net.journey.JourneyAchievements;
import net.journey.JourneyItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class JourneyDungeonEvent {
	
	@SubscribeEvent
	public void onGemMineEvent(PlayerEvent.ItemPickupEvent e) {
		if(e.pickedUp.getItem().isItemEqual(new ItemStack(JourneyItems.greenGem)))
			e.player.addStat(JourneyAchievements.achievementGem, 1); {
		}
		if(e.pickedUp.getItem().isItemEqual(new ItemStack(JourneyItems.blueGem)))
			e.player.addStat(JourneyAchievements.achievementGem, 1); {
		}
		if(e.pickedUp.getItem().isItemEqual(new ItemStack(JourneyItems.yellowGem)))
			e.player.addStat(JourneyAchievements.achievementGem, 1); {
		}
		if(e.pickedUp.getItem().isItemEqual(new ItemStack(JourneyItems.purpleGem)))
			e.player.addStat(JourneyAchievements.achievementGem, 1); {
		}
	}
}
