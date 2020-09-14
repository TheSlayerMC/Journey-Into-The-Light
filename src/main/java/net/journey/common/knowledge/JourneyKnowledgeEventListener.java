package net.journey.common.knowledge;

import java.util.Random;

import net.journey.api.capability.JourneyPlayer;
import net.journey.api.capability.PlayerStats;
import net.journey.common.capability.JCapabilityManager;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
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

			//OVERWORLD
			addKnowledgeFromBlock(event, stats, Blocks.EMERALD_ORE, EnumKnowledgeType.OVERWORLD, 6F, 3);
			addKnowledgeFromBlock(event, stats, Blocks.DIAMOND_ORE, EnumKnowledgeType.OVERWORLD, 6F, 3);
			addKnowledgeFromBlock(event, stats, Blocks.GOLD_ORE, EnumKnowledgeType.OVERWORLD, 3F, 2);
			addKnowledgeFromBlock(event, stats, Blocks.REDSTONE_ORE, EnumKnowledgeType.OVERWORLD, 3F, 2);
			addKnowledgeFromBlock(event, stats, Blocks.LAPIS_ORE, EnumKnowledgeType.OVERWORLD, 3F, 1);
			addKnowledgeFromBlock(event, stats, Blocks.IRON_ORE, EnumKnowledgeType.OVERWORLD, 2F, 1);
			addKnowledgeFromBlock(event, stats, Blocks.COAL_ORE, EnumKnowledgeType.OVERWORLD, 1F, 2);
			addKnowledgeFromBlock(event, stats, Blocks.STONE, EnumKnowledgeType.OVERWORLD, 0.02F, 0);
			addKnowledgeFromBlock(event, stats, JourneyBlocks.luniumOre, EnumKnowledgeType.OVERWORLD, 6F, 3);
			addKnowledgeFromBlock(event, stats, JourneyBlocks.sapphireOre, EnumKnowledgeType.OVERWORLD, 6F, 3);
			addKnowledgeFromBlock(event, stats, JourneyBlocks.shadiumOre, EnumKnowledgeType.OVERWORLD, 6F, 3);
			addKnowledgeFromBlock(event, stats, JourneyBlocks.iridiumOre, EnumKnowledgeType.OVERWORLD, 2F, 3);
			
			//NETHER
			addKnowledgeFromBlock(event, stats, Blocks.QUARTZ_ORE, EnumKnowledgeType.NETHER, 2F, 3);
			addKnowledgeFromBlock(event, stats, Blocks.GLOWSTONE, EnumKnowledgeType.NETHER, 2F, 3);
			addKnowledgeFromBlock(event, stats, Blocks.NETHERRACK, EnumKnowledgeType.NETHER, 0.02F, 0);
			addKnowledgeFromBlock(event, stats, JourneyBlocks.firestoneOre, EnumKnowledgeType.NETHER, 4F, 3);
			addKnowledgeFromBlock(event, stats, JourneyBlocks.firestoneOre, EnumKnowledgeType.NETHER, 4F, 3);
			addKnowledgeFromBlock(event, stats, JourneyBlocks.hellstoneOre, EnumKnowledgeType.NETHER, 3F, 3);

			//END
			addKnowledgeFromBlock(event, stats, JourneyBlocks.enderilliumOre, EnumKnowledgeType.END, 6F, 3);
			addKnowledgeFromBlock(event, stats, Blocks.END_STONE, EnumKnowledgeType.END, 0.02F, 0);

			//BOILING
			addKnowledgeFromBlock(event, stats, JourneyBlocks.ashualOre, EnumKnowledgeType.BOIL, 3F, 3);
			addKnowledgeFromBlock(event, stats, JourneyBlocks.blaziumOre, EnumKnowledgeType.BOIL, 4F, 3);
			addKnowledgeFromBlock(event, stats, JourneyBlocks.rubble, EnumKnowledgeType.BOIL, 0.02F, 0);
			addKnowledgeFromBlock(event, stats, JourneyBlocks.scorchedRubble, EnumKnowledgeType.BOIL, 0.02F, 0);
			addKnowledgeFromBlock(event, stats, JourneyBlocks.ashBlock, EnumKnowledgeType.BOIL, 0.02F, 0);

			//FROZEN

			//EUCA
			addKnowledgeFromBlock(event, stats, JourneyBlocks.mekyumOre, EnumKnowledgeType.EUCA, 3F, 3);
			addKnowledgeFromBlock(event, stats, JourneyBlocks.storonOre, EnumKnowledgeType.EUCA, 3F, 3);
			addKnowledgeFromBlock(event, stats, JourneyBlocks.koriteOre, EnumKnowledgeType.EUCA, 3F, 3);
			addKnowledgeFromBlock(event, stats, JourneyBlocks.celestiumOre, EnumKnowledgeType.EUCA, 3F, 3);
			addKnowledgeFromBlock(event, stats, JourneyBlocks.eucaStone, EnumKnowledgeType.EUCA, 0.02F, 0);

			//DEPTHS
			addKnowledgeFromBlock(event, stats, JourneyBlocks.flairiumOre, EnumKnowledgeType.DEPTHS, 5F, 3);
			addKnowledgeFromBlock(event, stats, JourneyBlocks.desOre, EnumKnowledgeType.DEPTHS, 5F, 3);
			addKnowledgeFromBlock(event, stats, JourneyBlocks.depthsStone, EnumKnowledgeType.DEPTHS, 0.2F, 0);

			//Corba
			addKnowledgeFromBlock(event, stats, JourneyBlocks.gorbiteOre, EnumKnowledgeType.CORBA, 5F, 3);
			addKnowledgeFromBlock(event, stats, JourneyBlocks.orbaditeOre, EnumKnowledgeType.CORBA, 5F, 3);
			addKnowledgeFromBlock(event, stats, JourneyBlocks.corbaStone, EnumKnowledgeType.CORBA, 0.2F, 0);
			
			//Cloudia
			addKnowledgeFromBlock(event, stats, JourneyBlocks.luniteOre, EnumKnowledgeType.CLOUDIA, 5F, 3);
			
			//Senterian
			
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
			addKnowledgeFromMob(event, stats, EntitySkeleton.class, EnumKnowledgeType.OVERWORLD, 1F, 3);
			addKnowledgeFromMob(event, stats, EntityZombie.class, EnumKnowledgeType.OVERWORLD, 1F, 3);
			addKnowledgeFromMob(event, stats, EntitySpider.class, EnumKnowledgeType.OVERWORLD, 1F, 3);
			addKnowledgeFromMob(event, stats, EntityCreeper.class, EnumKnowledgeType.OVERWORLD, 1F, 3);
			addKnowledgeFromMob(event, stats, EntityEnderman.class, EnumKnowledgeType.OVERWORLD, 2F, 3);

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

	public void addKnowledgeFromBlock(HarvestDropsEvent event, PlayerStats stats, Block block, EnumKnowledgeType knowledgeType, float knowledgeExp, int randExtra) {
		Random rand = new Random();
		IBlockState harvestedState = event.getState();
		if (harvestedState.getBlock() == block) {
			if(randExtra != 0) {//check is to stop positive bounds error
				stats.addKnowledge(knowledgeType, rand.nextInt(randExtra) + knowledgeExp); //for some reason its not random, its the total of the random + base number
			} else {
				stats.addKnowledge(knowledgeType, knowledgeExp);	
			}
		}
	}

	public void addKnowledgeFromMob(LivingDeathEvent event, PlayerStats stats, Class<? extends EntityLivingBase> entityLivingBase, EnumKnowledgeType knowledgeType, float knowledgeExp, int randExtra) {
		Random rand = new Random();
		EntityLivingBase killedEntity = event.getEntityLiving();
		if(killedEntity.getClass() == entityLivingBase) {
			if(randExtra != 0)
				stats.addKnowledge(knowledgeType, knowledgeExp + rand.nextInt(randExtra));
			else
				stats.addKnowledge(knowledgeType, knowledgeExp);		
		}
	}
}