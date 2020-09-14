package net.journey.common.knowledge;

import net.journey.JITL;
import net.journey.api.capability.JourneyPlayer;
import net.journey.api.capability.PlayerStats;
import net.journey.common.capability.JCapabilityManager;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class JourneyKnowledgeEventListener {

	@SubscribeEvent
	public void onBlockHarvested(HarvestDropsEvent event) {
		if (event.getHarvester() != null && event.getHarvester() instanceof EntityPlayer) {
			EntityPlayer player = event.getHarvester();
			JourneyPlayer journeyPlayer = JCapabilityManager.asJourneyPlayer(player);
			PlayerStats stats = journeyPlayer.getPlayerStats();
			//Can use this to add knowledge depending on the block harvested

			addKnowledgeFromBlock(event, stats, Blocks.DIAMOND_ORE, EnumKnowledgeType.OVERWORLD, 10);
			addKnowledgeFromBlock(event, stats, Blocks.IRON_ORE, EnumKnowledgeType.OVERWORLD, 5);
			addKnowledgeFromBlock(event, stats, Blocks.COAL_ORE, EnumKnowledgeType.OVERWORLD, 1);

			journeyPlayer.sendUpdates();
		}
	}

	@SubscribeEvent
	public void onMobKilled(LivingDeathEvent event) {
		if (event.getSource().getTrueSource() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
			JourneyPlayer journeyPlayer = JCapabilityManager.asJourneyPlayer(player);
			PlayerStats stats = journeyPlayer.getPlayerStats();

			//Can use this to add knowledge depending on the entity killed
			JITL.LOGGER.info("Uhhhhh");
			addKnowledgeFromMob(event, stats, EntitySkeleton.class, EnumKnowledgeType.OVERWORLD, 1);
			addKnowledgeFromMob(event, stats, EntityZombie.class, EnumKnowledgeType.OVERWORLD, 1);
			addKnowledgeFromMob(event, stats, EntitySpider.class, EnumKnowledgeType.OVERWORLD, 1);
			addKnowledgeFromMob(event, stats, EntityCreeper.class, EnumKnowledgeType.OVERWORLD, 1);
			addKnowledgeFromMob(event, stats, EntityEnderman.class, EnumKnowledgeType.OVERWORLD, 2);

			journeyPlayer.sendUpdates();
		}
	}

	@SubscribeEvent
	public void onMobDrop(LivingDropsEvent event) {
		if (event.getSource().getTrueSource() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
			JourneyPlayer journeyPlayer = JCapabilityManager.asJourneyPlayer(player);
			PlayerStats stats = journeyPlayer.getPlayerStats();

			//Can use this to change what mobs drop depending on knowledge level

			journeyPlayer.sendUpdates();
		}
	}

	@SubscribeEvent
	public void onTick(TickEvent.PlayerTickEvent event) {
		EntityPlayer player = event.player;
		int x = MathHelper.floor(player.posX);
		int y = MathHelper.floor(player.posY);
		int z = MathHelper.floor(player.posZ);

		//Im sure there would be a good use for this too
	}

	public void addKnowledgeFromBlock(HarvestDropsEvent event, PlayerStats stats, Block block, EnumKnowledgeType knowledgeType, int knowledge) {
		IBlockState harvestedState = event.getState();
		if (harvestedState.getBlock() == block) {
			stats.addKnowledge(knowledgeType, knowledge);
		}
	}

	public void addKnowledgeFromMob(LivingDeathEvent event, PlayerStats stats, Class<? extends EntityLivingBase> entityLivingBase, EnumKnowledgeType knowledgeType, int knowledge) {
		EntityLivingBase killedEntity = event.getEntityLiving();
		if (killedEntity.getClass() == entityLivingBase) {
			stats.addKnowledge(knowledgeType, knowledge);
			JITL.LOGGER.info("AHHHHH");
		}
	}
}