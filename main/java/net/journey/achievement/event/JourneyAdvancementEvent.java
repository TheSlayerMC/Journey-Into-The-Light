package net.journey.achievement.event;

import net.journey.JourneyAdvancements;
import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.JourneyWeapons;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.slayer.api.SlayerAPI;

public class JourneyAdvancementEvent {

	@SubscribeEvent
	public void onGemMineEvent(PlayerEvent.ItemPickupEvent e) {
		if(e.getOriginalEntity().getItem().isItemEqual(new ItemStack(JourneyItems.greenGem))) {
			e.player.addStat(JourneyAdvancements.achievementGem, 1); 
		}
		if(e.getOriginalEntity().getItem().isItemEqual(new ItemStack(JourneyItems.blueGem))) {
			e.player.addStat(JourneyAdvancements.achievementGem, 1); 
		}
		if(e.getOriginalEntity().getItem().isItemEqual(new ItemStack(JourneyItems.yellowGem))) {
			e.player.addStat(JourneyAdvancements.achievementGem, 1); 
		}
		if(e.getOriginalEntity().getItem().isItemEqual(new ItemStack(JourneyItems.purpleGem))) {
			e.player.addStat(JourneyAdvancements.achievementGem, 1); 
		}
		if(e.getOriginalEntity().getItem().isItemEqual(new ItemStack(JourneyItems.sapphire))) {
			e.player.addStat(JourneyAdvancements.achievementOre, 1);
		}
	}

	@SubscribeEvent
	public void onCraftSwordEvent(PlayerEvent.ItemCraftedEvent e) {
		if(e.crafting.getItem().equals(JourneyWeapons.sapphireSword)) {
			e.player.addStat(JourneyAdvancements.achievementSapphireSword, 1);
		}
	}

	@SubscribeEvent
	public void onCraftTrophieEvent(PlayerEvent.ItemCraftedEvent e) {
		if(e.crafting.getItem().equals(SlayerAPI.toItem(JourneyBlocks.trophy))) {
			e.player.addStat(JourneyAdvancements.achievementTrophy, 1);
		}
	}
}
