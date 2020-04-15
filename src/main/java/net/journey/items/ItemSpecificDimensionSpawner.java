package net.journey.items;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.JourneyTabs;
import net.journey.entity.mob.boss.EntityBlazier;
import net.journey.entity.mob.boss.EntityCorallator;
import net.journey.entity.mob.boss.EntityEudor;
import net.journey.entity.mob.boss.EntityLogger;
import net.journey.entity.mob.boss.EntityScale;
import net.journey.entity.mob.boss.EntitySentryKing;
import net.journey.entity.mob.boss.EntitySkyStalker;
import net.journey.entity.mob.boss.EntitySoulWatcher;
import net.journey.entity.mob.boss.EntityTerranianProtector;
import net.journey.entity.mob.boss.EntityThunderbird;
import net.journey.entity.mob.pet.EntityTameRoc;
import net.journey.entity.projectile.EntityLightningBall;
import net.journey.util.LangHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.SlayerAPI.Colour;
import net.slayer.api.item.ItemMod;

public class ItemSpecificDimensionSpawner extends ItemMod {

	private int ticks;
	public String dimName;
	public int dimID;
	
	public ItemSpecificDimensionSpawner(int dimID, String name, String f, String dimName) {
		super(name, f, JourneyTabs.spawners);
		setMaxStackSize(1);
		this.dimID = dimID;
		this.dimName = dimName;
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer p, World w, BlockPos pos, EnumHand hand, EnumFacing fa, float par8, float par9, float par10) {
		ItemStack i = p.getHeldItem(hand);
		Item item = i.getItem();
		if(!w.isRemote) {
			if(w.provider.getDimension() == dimID) {
				EntityBlazier blaze = new EntityBlazier(w);
				EntitySoulWatcher soul = new EntitySoulWatcher(w);
				EntitySentryKing sentry = new EntitySentryKing(w);
				EntitySkyStalker sky = new EntitySkyStalker(w);
				EntityTameRoc roc = new EntityTameRoc(w, p);
				EntityScale scale = new EntityScale(w);
				EntityCorallator corallator = new EntityCorallator(w);
				EntityLogger logger = new EntityLogger(w);
				EntityThunderbird thunder = new EntityThunderbird(w);
				EntityTerranianProtector terrastar = new EntityTerranianProtector(w);
				if(item == JourneyItems.blazierOrb) {			
				    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
					SlayerAPI.sendMessageToAll("You're playing with hot fire. It's not too late to turn back...", true);
				    blaze.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
				    w.spawnEntity(blaze);
				}
				if(item == JourneyItems.rocSpawnEgg) {
				    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
					roc.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
					w.spawnEntity(roc);
					//p.triggerAchievement(JourneyAchievements.achievementRoc);
				}
				if(item == JourneyItems.sentryKingOrb) {
					SlayerAPI.sendMessageToAll("It sucked all the energy out of this world, don't let it suck the energy out of you...", true);
				    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
					sentry.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
					w.spawnEntity(sentry);
				}
				if(item == JourneyItems.mysteriousDisk) {
					SlayerAPI.sendMessageToAll("The purple flying fish monster is not happy...", true);
				    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
					sky.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
					w.spawnEntity(sky);
				}
				if(item == JourneyItems.corallatorOrb) {
					SlayerAPI.sendMessageToAll("You will regret this mistake for the rest of your life - if you'll still have one, that is...", true);
				    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
					corallator.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
					w.spawnEntity(corallator);
				}
				if(item == JourneyItems.loggerOrb) {
					SlayerAPI.sendMessageToAll("You'll get chopped to pieces with this one...", true);
				    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
					logger.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
					w.spawnEntity(logger);
				}
				if(item == JourneyItems.scaleOrb) {
					SlayerAPI.sendMessageToAll("(W.I.P) The blue blubby fish monster has been summoned!", true);
				    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
					scale.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
					w.spawnEntity(scale);
				}
				EntityEudor eudor = new EntityEudor(w);
				if(item == JourneyItems.eudorOrb){
				    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
					SlayerAPI.sendMessageToAll("The King has been summoned", true);
					eudor.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
					w.spawnEntity(eudor);
				}
				if(item == JourneyItems.thunderbirdOrb) {
					SlayerAPI.sendMessageToAll("The thunderbird is not pleased with its awakening...", true);
				    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
					thunder.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
					w.spawnEntity(thunder);
				}
				if(item == JourneyItems.enchantedTerrastar) {
					SlayerAPI.sendMessageToAll("It's sole purpose was to protect this land. Why would you try to destroy it?", true);
				    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
					terrastar.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
					w.spawnEntity(terrastar);
					
				}
				if(!p.capabilities.isCreativeMode) i.shrink(1);
			} else {
				SlayerAPI.addChatMessage(p, Colour.GREEN + "Cannot be called upon unless in the " + dimName + " dimension.");
			}
		}
		return EnumActionResult.PASS;
	}

	@Override
	public void addInformation(ItemStack i, World worldIn, List<String> list, ITooltipFlag flagIn) {
		Item item = i.getItem();
		if(item == JourneyItems.blazierOrb) list.add(LangHelper.setBossSpawner("Blazier"));
		if(item == JourneyItems.sentryKingOrb) list.add(LangHelper.setBossSpawner("Sentry King"));
		if(item == JourneyItems.mysteriousDisk) list.add(LangHelper.setBossSpawner("Sky Stalker"));
		if(item == JourneyItems.corallatorOrb) list.add(LangHelper.setBossSpawner("Corallator"));
		if(item == JourneyItems.loggerOrb) list.add(LangHelper.setBossSpawner("Logger"));
		if(item == JourneyItems.scaleOrb) list.add(LangHelper.setBossSpawner("Sclae"));
		if(item == JourneyItems.thunderbirdOrb) list.add(LangHelper.setBossSpawner("Thunderbird"));
		if(item == JourneyItems.enchantedTerrastar) list.add(LangHelper.setBossSpawner("Terranian Protector"));
		if(item == JourneyItems.rocSpawnEgg) list.add(LangHelper.setPetSpawner("Roc"));
		if(item == JourneyItems.rocSpawnEgg) list.add(SlayerAPI.Colour.DARK_GREEN + "More powerful than a dog, less cuddly.");
		
	}
}