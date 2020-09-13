package net.journey.common.knowledge;

import net.journey.JITL;
import net.journey.api.capability.JourneyPlayer;
import net.journey.api.capability.PlayerStats;
import net.journey.common.capability.JCapabilityManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = JITL.MOD_ID)
public class JourneyKnowledgeEventListener {

	@SubscribeEvent
	public static void onBlockHarvested(HarvestDropsEvent event) {
		if(event.getHarvester() != null && event.getHarvester() instanceof EntityPlayer && event.getHarvester().getHeldItemMainhand() != null) {
			EntityPlayer player = event.getHarvester();
			JourneyPlayer journeyPlayer = JCapabilityManager.asJourneyPlayer(player);
			PlayerStats stats = journeyPlayer.getPlayerStats();
			
			//Can use this to add knowledge depending on the block harvested
			
			journeyPlayer.sendUpdates();
		}
	}

	@SubscribeEvent
	public void onMobKilled(LivingDeathEvent event) {
		if(event.getSource().getTrueSource() instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)event.getSource().getTrueSource();
			if(player.getHeldItemMainhand() != null) {
				JourneyPlayer journeyPlayer = JCapabilityManager.asJourneyPlayer(player);
				PlayerStats stats = journeyPlayer.getPlayerStats();
				
				//Can use this to add knowledge depending on the entity killed
				
				journeyPlayer.sendUpdates();
			}
		}
	}

	@SubscribeEvent
	public void onMobDrop(LivingDropsEvent event){
		if(event.getSource().getTrueSource() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.getSource().getTrueSource();
			if(player.getHeldItemMainhand() != null) {
				JourneyPlayer journeyPlayer = JCapabilityManager.asJourneyPlayer(player);
				PlayerStats stats = journeyPlayer.getPlayerStats();
				
				//Can use this to change what mobs drop depending on knowledge level
				
				journeyPlayer.sendUpdates();
			}
		}
	}

	@SubscribeEvent
	public static void onTick(TickEvent.PlayerTickEvent event) {
		EntityPlayer player = event.player;
		int x = MathHelper.floor(player.posX);
		int y = MathHelper.floor(player.posY);
		int z = MathHelper.floor(player.posZ);

		//Im sure there would be a good use for this too
	}
}