package net.journey.init;

import net.journey.entity.item.EntityMagicExplosive;
import net.journey.entity.item.EntityObsidianBoat;
import net.journey.entity.mob.boiling.*;
import net.journey.entity.mob.boiling.npc.EntityBoilTrader;
import net.journey.entity.mob.boiling.npc.EntityEscapedConvict;
import net.journey.entity.mob.boss.*;
import net.journey.entity.mob.cloudia.*;
import net.journey.entity.mob.cloudia.npc.EntityStarlightBlacksmith;
import net.journey.entity.mob.cloudia.npc.EntityStarlightVillager;
import net.journey.entity.mob.corba.*;
import net.journey.entity.mob.corba.npc.EntityOvergrownMerchant;
import net.journey.entity.mob.corba.npc.EntityRedTordo;
import net.journey.entity.mob.corba.npc.EntityTheHooded;
import net.journey.entity.mob.corba.npc.EntityTordo;
import net.journey.entity.mob.depths.*;
import net.journey.entity.mob.depths.npc.EntityStaringGuardian;
import net.journey.entity.mob.end.EntityEnderCrawler;
import net.journey.entity.mob.end.EntityEnderLeaper;
import net.journey.entity.mob.euca.*;
import net.journey.entity.mob.euca.npc.EntityAlloyMender;
import net.journey.entity.mob.frozen.*;
import net.journey.entity.mob.frozen.npc.EntityFrozenMerchant;
import net.journey.entity.mob.nether.*;
import net.journey.entity.mob.overworld.EntityWraith;
import net.journey.entity.mob.overworld.*;
import net.journey.entity.mob.overworld.cold.EntityBlizzard;
import net.journey.entity.mob.overworld.jungle.EntityJungleGolem;
import net.journey.entity.mob.overworld.jungle.EntityJungleSpider;
import net.journey.entity.mob.overworld.jungle.EntityJungleTurtle;
import net.journey.entity.mob.overworld.npc.EntityBlacksmith;
import net.journey.entity.mob.overworld.npc.EntityMage;
import net.journey.entity.mob.overworld.underground.*;
import net.journey.entity.mob.overworld.underground.npc.EntityRockiteGolem;
import net.journey.entity.mob.pet.EntityEucaHopper;
import net.journey.entity.mob.pet.EntityPetRobot;
import net.journey.entity.mob.pet.EntityShiverwolf;
import net.journey.entity.mob.pet.EntityTameRoc;
import net.journey.entity.mob.senterian.mob.*;
import net.journey.entity.mob.terrania.mob.*;
import net.journey.entity.mob.terrania.npc.EntityTerranianEnchanter;
import net.journey.entity.mob.terrania.npc.EntityTerranianTrader;
import net.journey.entity.projectile.*;
import net.journey.entity.projectile.arrow.*;
import net.journey.entity.projectile.knife.*;
import net.journey.entity.projectile.launcher.*;
import net.journey.entity.projectile.piercer.*;
import net.journey.entity.projectile.staff.*;
import net.journey.entity.projectile.throwable.EntityDemonicBomb;
import net.journey.entity.projectile.throwable.EntityFireBomb;
import net.journey.entity.projectile.throwable.EntityMagicBomb;
import net.journey.entity.projectile.throwable.EntityMagicPot;
import net.journey.entity.util.EntityBossCrystal;
import net.journey.util.handler.LogHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.slayer.api.SlayerAPI;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Mod.EventBusSubscriber
public class EntityRegistry {
	/**
	 * Stores data only during game loading.
	 */
	private static List<EntityEntry> MOB_ENTRIES;

	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
		EntityRegistry.registerProjectiles(event);
		EntityRegistry.registerMobs(event);
	}

	public static void onLoadComplete(FMLLoadCompleteEvent event) {
		MOB_ENTRIES = null;
	}

	private static void registerProjectiles(RegistryEvent.Register<EntityEntry> event) {
		EntityEntry[] projectilesArray = {
				SlayerAPI.buildProjectileEntry(EntityDamagingProjectile.class, "basic"),
				SlayerAPI.buildProjectileEntry(EntityBouncingProjectile.class, "bouncing"),
				SlayerAPI.buildProjectileEntry(EntityChaosProjectile.class, "chaos"),
				SlayerAPI.buildProjectileEntry(EntityDoomsBringer.class, "doom"),
				SlayerAPI.buildProjectileEntry(EntityConjuring.class, "conjuring"),
				SlayerAPI.buildProjectileEntry(EntityEnlightenment.class, "enlightment"),
				SlayerAPI.buildProjectileEntry(EntityGreenpace.class, "greenpace"),
				SlayerAPI.buildProjectileEntry(EntityWizardsStar.class, "wizardsstar"),
				SlayerAPI.buildProjectileEntry(EntitySentryKingGrenade.class, "sentry_king_grenade"),
				SlayerAPI.buildProjectileEntry(EntityMagmaFireball.class, "magmaball"),
				SlayerAPI.buildProjectileEntry(EntityFlameArrow.class, "flamearrow"),
				SlayerAPI.buildProjectileEntry(EntityFireBall.class, "fireball"),
				SlayerAPI.buildProjectileEntry(EntityIceBall.class, "iceball"),
				SlayerAPI.buildProjectileEntry(EntityTempleBall.class, "templeball"),
				SlayerAPI.buildProjectileEntry(EntityLightningBall.class, "lightningball"),
				SlayerAPI.buildProjectileEntry(EntityEssenceArrow.class, "essencearrow"),
				SlayerAPI.buildProjectileEntry(EntityPoisonArrow.class, "poisonarrow"),
				SlayerAPI.buildProjectileEntry(EntityDarknessArrow.class, "darknessarrow"),
				SlayerAPI.buildProjectileEntry(EntityFrozenArrow.class, "frozenarrow"),
				SlayerAPI.buildProjectileEntry(EntityRockProjectile.class, "rockchunk"),
				SlayerAPI.buildProjectileEntry(EntityNetherPlasma.class, "netherplasma"),
				SlayerAPI.buildProjectileEntry(EntityOceanPlasma.class, "oceanplasma"),
				SlayerAPI.buildProjectileEntry(EntityForestPlasma.class, "forestplasma"),
				SlayerAPI.buildProjectileEntry(EntityBoilingPiercer.class, "boilingpiercer"),
				SlayerAPI.buildProjectileEntry(EntityNethicPiercer.class, "nethicpiercer"),
				SlayerAPI.buildProjectileEntry(EntityFrozenPiercer.class, "frozenpiercer"),
				SlayerAPI.buildProjectileEntry(EntityEucaPiercer.class, "eucapiercer"),
				SlayerAPI.buildProjectileEntry(EntityDepthsPiercer.class, "depthspiercer"),
				SlayerAPI.buildProjectileEntry(EntityCorbaPiercer.class, "corbapiercer"),
				SlayerAPI.buildProjectileEntry(EntityFrostbittenPiercer.class, "frostbittenpiercer"),
				SlayerAPI.buildProjectileEntry(EntityFrostyPiercer.class, "frostypiercer"),
				SlayerAPI.buildProjectileEntry(EntitySunsetPiercer.class, "sunsetpiercer"),
				SlayerAPI.buildProjectileEntry(EntitySkyPiercer.class, "skypiercer"),
				SlayerAPI.buildProjectileEntry(EntityMagicPot.class, "magicpot"),
				SlayerAPI.buildProjectileEntry(EntityMoltenKnife.class, "moltenknife"),
				SlayerAPI.buildProjectileEntry(EntityAquaticKnife.class, "aquaticknife"),
				SlayerAPI.buildProjectileEntry(EntityBloodKnife.class, "bloodknife"),
				SlayerAPI.buildProjectileEntry(EntityCharredKnife.class, "charredknife"),
				SlayerAPI.buildProjectileEntry(EntitySizzlingKnife.class, "sizzlingknife"),
				SlayerAPI.buildProjectileEntry(EntityBubbleProjectile.class, "bubbleprojectile"),
				SlayerAPI.buildProjectileEntry(EntityDetractor.class, "detractor"),
				SlayerAPI.buildProjectileEntry(EntityRock.class, "rock"),
				SlayerAPI.buildProjectileEntry(EntityOvergrown.class, "overgrown"),
				SlayerAPI.buildProjectileEntry(EntityShimmererProjectile.class, "shimmererprojectile"),
				SlayerAPI.buildProjectileEntry(EntityDemonicBomb.class, "demonicbomb"),
				SlayerAPI.buildProjectileEntry(EntityFireBomb.class, "firebomb"),
				SlayerAPI.buildProjectileEntry(EntityFloroDirtProjectile.class, "florowater"),
				SlayerAPI.buildProjectileEntry(EntityFrozenSnowball.class, "frozensnowball"),
				SlayerAPI.buildProjectileEntry(EntityMagicBomb.class, "magicbomb"),
				SlayerAPI.buildProjectileEntry(EntityEssenceShuriken.class, "essence_shuriken"),
				SlayerAPI.buildProjectileEntry(EntityWithic.class, "withic"),
				SlayerAPI.buildProjectileEntry(EntityEarthen.class, "earthen"),
				SlayerAPI.buildProjectileEntry(EntityHellstone.class, "hellstone")
		};

		event.getRegistry().registerAll(projectilesArray);
		LogHelper.info("Successfully registered " + projectilesArray.length + " projectiles");
	}

	private static void registerMobs(RegistryEvent.Register<EntityEntry> event) {
		EntityEntry[] entries = new EntityEntry[]{
				//END
				SlayerAPI.buildMobEntry(EntityEnderLeaper.class, "enderleaper", "Ender Leaper", 0x440089, 0xBC00BC),
				SlayerAPI.buildMobEntry(EntityEnderCrawler.class, "endercrawler", "Ender Crawler", 0x440089, 0xBC00BC),
				//NETHER
				SlayerAPI.buildMobEntry(EntityHellbot.class, "hellbot", "Hellbot", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityWitherspine.class, "witherspine", "Witherspine", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityLavasnake.class, "lavasnake", "Lava Snake", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityHellCow.class, "hellcow", "Hell Cow", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityReaper.class, "reaper", "Reaper", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityMiniGhast.class, "minighast", "Mini-Ghast", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityInfernoBlaze.class, "infernoblaze", "Inferno Blaze", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityHellTurtle.class, "hellturtle", "Hell Turtle", 0x7c4c2c, 0x26b530),
				//OVERWORLD
				SlayerAPI.buildMobEntry(EntityRobot.class, "robot", "Robot", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntitySpyclops.class, "spyclops", "Spyclopse", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntitySmallHongo.class, "smallhongo", "Small Hongo", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityMediumHongo.class, "mediumhongo", "Medium Hongo", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityBigHongo.class, "bighongo", "Big Hongo", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntitySandCrawler.class, "sandcrawler", "Sand Crawler", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityFireMage.class, "firemage", "Fire Mage", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityIceMage.class, "icemage", "Ice Mage", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityCaveMage.class, "cavemage", "Cave Mage", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityCavurn.class, "cavurn", "Cavurn", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityCaveling.class, "caveling", "Caveling", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityWraith.class, "wraith", "Wraith", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityStonewalker.class, "stonewalker", "Stone Walker", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityTurducken.class, "turducken", "Turducken", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityFloro.class, "floro", "Floro", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntitySpectre.class, "spectre", "Spectre", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityGreenHonglow.class, "greenhonglow", "Green Honglow", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityHonglow.class, "redhonglow", "Red Honglow", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityBlueHonglow.class, "bluehonglow", "Blue Honglow", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntitySwampFly.class, "swampfly", "Swamp Fly", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityBoom.class, "boomboom", "Boom Boom", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityBlizzard.class, "blizzard", "Blizzard", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityDuneworm.class, "dunewerm", "Dunewerm", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityJungleGolem.class, "junglegolem", "Jungle Golem", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityRockiteSmasher.class, "rockitesmasher", "Rockite Smasher", 0x7c4c2c, 0x26b530),

				SlayerAPI.buildMobEntry(EntityJungleTurtle.class, "jungleturtle", "Jungle Turtle", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityJungleSpider.class, "jungleSpider", "Jungle Spider", 0x7c4c2c, 0x26b530),
				SlayerAPI.buildMobEntry(EntityTameRoc.class, "tameroc", "Roc", 0x7c4c2c, 0x26b530),
				
				SlayerAPI.buildMobEntry(EntityPetRobot.class, "petrobot", "Pet Robot", 0x7c4c2c, 0x26b530),

				//BP
				SlayerAPI.buildMobEntry(EntityMagmaBlaze.class, "magmablaze", "Magma Blaze", 0xff7800, 0xffa800),
				SlayerAPI.buildMobEntry(EntityBurningLight.class, "burninglight", "Burning Light", 0xff7800, 0xffa800),
				SlayerAPI.buildMobEntry(EntityFrightener.class, "frightener", "Frightener", 0xff7800, 0xffa800),
				SlayerAPI.buildMobEntry(EntityHellwing.class, "hellwing", "Hellwing", 0xff7800, 0xffa800),
				SlayerAPI.buildMobEntry(EntityObserver.class, "observer", "Observer", 0xff7800, 0xffa800),
				SlayerAPI.buildMobEntry(EntityScreamer.class, "screamer", "Screamer", 0xff7800, 0xffa800),
				SlayerAPI.buildMobEntry(EntityFlameLotus.class, "flamelotus", "Flame Lotus", 0xff7800, 0xffa800),
				//FL
				SlayerAPI.buildMobEntry(EntityShatterer.class, "shatterer", "Shatterer", 0x00d8ff, 0xd8f9ff),
				SlayerAPI.buildMobEntry(EntityFrozenTroll.class, "frozentroll", "Frozen Troll", 0x00d8ff, 0xd8f9ff),
				SlayerAPI.buildMobEntry(EntityIceman.class, "iceman", "Ice Man", 0x00d8ff, 0xd8f9ff),
				SlayerAPI.buildMobEntry(EntityPermafraust.class, "permafraust", "Permafraust", 0x00d8ff, 0xd8f9ff),
				SlayerAPI.buildMobEntry(EntityShiveringBushwalker.class, "shiveringbushwalker", "Shivering Bushwalker", 0x00d8ff, 0xd8f9ff),
				SlayerAPI.buildMobEntry(EntityShiverwolf.class, "shiverwolf", "Shiver Wolf", 0x00d8ff, 0xd8f9ff),
				SlayerAPI.buildMobEntry(EntityShiveringShrieker.class, "shiveringshrieker", "Sjoveromg Shrieker", 0x00d8ff, 0xd8f9ff),
				SlayerAPI.buildMobEntry(EntityFrozenFrostbiter.class, "frozenfrostbiter", "Frozen Frost Biter", 0x00d8ff, 0xd8f9ff),
				SlayerAPI.buildMobEntry(EntityCrystalCluster.class, "crystalcluster", "Crystal Cluster", 0x00d8ff, 0xd8f9ff),
				SlayerAPI.buildMobEntry(EntityIceGolem.class, "icegolem", "Ice Golem", 0x00d8ff, 0xd8f9ff),
				//EUCA
				//SlayerAPI.buildMobEntry(EntityInsecto.class, "insecto");
				//SlayerAPI.buildMobEntry(EntityPsyollom.class, "psyollom");
				//SlayerAPI.buildMobEntry(EntityEucaFighter.class, "eucaFighter");
				SlayerAPI.buildMobEntry(EntityEucaHopper.class, "eucaHopper", "Euca Hopper", 0xffba00, 0xe0e0e0),
				SlayerAPI.buildMobEntry(EntityEucaCharger.class, "eucacharger", "Euca Charger", 0xffba00, 0xe0e0e0),
				SlayerAPI.buildMobEntry(EntityDynaster.class, "dynaster", "Dynaster", 0xffba00, 0xe0e0e0),
				SlayerAPI.buildMobEntry(EntityGolder.class, "golder", "Golder", 0xffba00, 0xe0e0e0),
				SlayerAPI.buildMobEntry(EntityGolditeMage.class, "golditemage", "Goldite Mage", 0xffba00, 0xe0e0e0),
				SlayerAPI.buildMobEntry(EntitySilverbot.class, "silverbot", "Silver Bot", 0xffba00, 0xe0e0e0),
				SlayerAPI.buildMobEntry(EntityGoldbot.class, "goldbot", "Gold Bot", 0xffba00, 0xe0e0e0),
				SlayerAPI.buildMobEntry(EntityShimmerer.class, "shimmerer", "Shimmerer", 0xffba00, 0xe0e0e0),
				//DEPTH
				SlayerAPI.buildMobEntry(EntitySpikedBeast.class, "spikedbeast", "Spiked Beast", 0x003CA5, 0x0098A3),
				SlayerAPI.buildMobEntry(EntityDarknessCrawler.class, "darknesscrawler", "Darkness Crawler0", 0x003CA5, 0x0098A3),
				SlayerAPI.buildMobEntry(EntityDepthsBeast.class, "depthsbeast", "Deapths Beast", 0x003CA5, 0x0098A3),
				SlayerAPI.buildMobEntry(EntityDepthsHunter.class, "depthshunter", "Deapther Hunter", 0x003CA5, 0x0098A3),
				SlayerAPI.buildMobEntry(EntityDarkener.class, "darkener", "Darkener", 0x003CA5, 0x0098A3),
				SlayerAPI.buildMobEntry(EntityLightener.class, "lightener", "Lightener", 0x003CA5, 0x0098A3),
				SlayerAPI.buildMobEntry(EntityDarkSorcerer.class, "darksorcerer", "Dark Sorcerer", 0x003CA5, 0x0098A3),
				//CORBA
				SlayerAPI.buildMobEntry(EntityOverseer.class, "overseer", "Overseer", 0x1e8c00, 0x36ff00),
				SlayerAPI.buildMobEntry(EntityOverseerElder.class, "overseerelder", "Overseer Elder", 0x1e8c00, 0x36ff00),
				SlayerAPI.buildMobEntry(EntitySurfaceSeer.class, "surfaceseer", "Surface Seer", 0x1e8c00, 0x36ff00),
				SlayerAPI.buildMobEntry(EntityTreeGolem.class, "treegolem", "Tree Golem", 0x1e8c00, 0x36ff00),
				SlayerAPI.buildMobEntry(EntityWoodCreature.class, "woodcreature", "Wood Creature", 0x1e8c00, 0x36ff00),
				SlayerAPI.buildMobEntry(EntityNatureMage.class, "naturemage", "Nature Mage", 0x1e8c00, 0x36ff00),
				SlayerAPI.buildMobEntry(EntityLeafBlower.class, "leafblower", "Leaf Blower", 0x1e8c00, 0x36ff00),
				//CLOUDIA
				SlayerAPI.buildMobEntry(EntityCloudGhost.class, "cloudghost", "Cloud Ghost", 0xa87abd, 0x9000ff),
				SlayerAPI.buildMobEntry(EntityStarlightGolem.class, "starlightgolem", "Starlight Golem", 0xa87abd, 0x9000ff),
				SlayerAPI.buildMobEntry(EntityStarlightTransporter.class, "starlighttransporter", "Starlight Transporter", 0xa87abd, 0x9000ff),
				SlayerAPI.buildMobEntry(EntityStarlightWalker.class, "starlightwalker", "Starlight Walker", 0xa87abd, 0x9000ff),
				SlayerAPI.buildMobEntry(EntitySkyEel.class, "skyeel", "Sky Eel", 0xa87abd, 0x9000ff),
				SlayerAPI.buildMobEntry(EntityCloudFlower.class, "cloudflower", "Cloud Flower", 0xa87abd, 0x9000ff),
				//NPC
				SlayerAPI.buildMobEntry(EntityStarlightVillager.class, "starlightvillager", "Starlight Villager", 0x00FF8C, 0x00F6FF),
				SlayerAPI.buildMobEntry(EntityStarlightBlacksmith.class, "starlightblacksmith", "Starlight Blacksmith", 0x00FF8C, 0x00F6FF),
				SlayerAPI.buildMobEntry(EntityMage.class, "mage", "Mage", 0x00FF8C, 0x00F6FF),
				SlayerAPI.buildMobEntry(EntityBlacksmith.class, "blacksmith", "Blacksmith", 0x00FF8C, 0x00F6FF),
				SlayerAPI.buildMobEntry(EntityFrozenMerchant.class, "frozenmerchant", "Frozen Merchant", 0x00FF8C, 0x00F6FF),
				SlayerAPI.buildMobEntry(EntityEscapedConvict.class, "escapedconvict", "Escaped Convict", 0x00FF8C, 0x00F6FF),
				SlayerAPI.buildMobEntry(EntityStaringGuardian.class, "staringguardian", "Staring Guardian", 0x00FF8C, 0x00F6FF),
				SlayerAPI.buildMobEntry(EntityTordo.class, "tordo", "Tordo", 0x00FF8C, 0x00F6FF),
				SlayerAPI.buildMobEntry(EntityRedTordo.class, "redtordo", "Red Tordo", 0x00FF8C, 0x00F6FF),
				SlayerAPI.buildMobEntry(EntityBoilTrader.class, "boiltrader", "Boil Trader", 0x00FF8C, 0x00F6FF),
				SlayerAPI.buildMobEntry(EntityAlloyMender.class, "alloymender", "Alloy Mender", 0x00FF8C, 0x00F6FF),
				SlayerAPI.buildMobEntry(EntityTerranianTrader.class, "terraniantrader", "Terranian Trader", 0x00FF8C, 0x00F6FF),
				SlayerAPI.buildMobEntry(EntityOvergrownMerchant.class, "overgrownmerchant", "Overgrown Merchant", 0x00FF8C, 0x00F6FF),
				SlayerAPI.buildMobEntry(EntityTerranianEnchanter.class, "terranianenchanter", "Terranian Enchanter", 0x00FF8C, 0x00F6FF),
				SlayerAPI.buildMobEntry(EntityRockiteGolem.class, "rockitegolem", "Rockite Golem", 0x00FF8C, 0x00F6FF),
				SlayerAPI.buildMobEntry(EntityTheHooded.class, "thehooded", "The Hooded", 0x00FF8C, 0x00F6FF),
				//BOSS
				SlayerAPI.buildMobEntry(EntitySoulWatcher.class, "soulwatcher", "Soul Watcher", 0x000000, 0x9B0000),
				SlayerAPI.buildMobEntry(EntityNetherBeast.class, "beastofthenether", "Nether Beast", 0x000000, 0x9B0000),
				SlayerAPI.buildMobEntry(EntityWitheringBeast.class, "witheringbeast", "Withering Beast", 0x000000, 0x9B0000),
				SlayerAPI.buildMobEntry(EntityCalcia.class, "calcia", "Calcia", 0x000000, 0x9B0000),
				SlayerAPI.buildMobEntry(EntityTempleGuardian.class, "templeguardian", "Temple Guardian", 0x000000, 0x9B0000),
				SlayerAPI.buildMobEntry(EntityFourfa.class, "fourfa", "Fourfa", 0x000000, 0x9B0000),
				SlayerAPI.buildMobEntry(EntityBlazier.class, "blazier", "Blazier", 0x000000, 0x9B0000),
				SlayerAPI.buildMobEntry(EntitySentryKing.class, "sentryking", "Sentry King", 0x000000, 0x9B0000),
				SlayerAPI.buildMobEntry(EntityThunderbird.class, "thunderbird", "Thunderbird", 0x000000, 0x9B0000),
				SlayerAPI.buildMobEntry(EntityLogger.class, "logger", "Logger", 0x000000, 0x9B0000),
				SlayerAPI.buildMobEntry(EntityCorallator.class, "corallator", "Corallator", 0x000000, 0x9B0000),
				SlayerAPI.buildMobEntry(EntitySkyStalker.class, "skystalker", "Sky Stalker", 0x000000, 0x9B0000),
				SlayerAPI.buildMobEntry(EntityEudor.class, "eudor", "Eudor", 0x000000, 0x9B0000),
				SlayerAPI.buildMobEntry(EntityScale.class, "scale", "Scale", 0x000000, 0x9B0000),
				SlayerAPI.buildMobEntry(EntityTerranianProtector.class, "terranianprotector", "Terraian Protector", 0x000000, 0x9B0000),
				SlayerAPI.buildMobEntry(EntitySentryHeart.class, "sentryheart", "Sentry Heart", 0x000000, 0x9B0000),
				SlayerAPI.buildMobEntry(EntityGuardianOfDestruction.class, "guardianofdestruction", "Guardian of Destruction", 0x000000, 0x9B0000),
				//Terrania
				SlayerAPI.buildMobEntry(EntityTerralight.class, "terralight", "Terralight", 0x7813ff, 0xff58f5),
				SlayerAPI.buildMobEntry(EntityTerraScatterer.class, "terrascatterer", "Terra Scatterer", 0x7813ff, 0xff58f5),
				SlayerAPI.buildMobEntry(EntityPurplian.class, "purplian", "Purplian", 0x7813ff, 0xff58f5),
				SlayerAPI.buildMobEntry(EntityTerraslug.class, "terraslug", "Purple Slug", 0x7813ff, 0xff58f5),
				SlayerAPI.buildMobEntry(EntityTerragrow.class, "terragrow", "Terragrow", 0x7813ff, 0xff58f5),
				SlayerAPI.buildMobEntry(EntityTerrashroom.class, "terrashroom", "Terrashroom", 0x7813ff, 0xff58f5),
				SlayerAPI.buildMobEntry(EntityAranaKing.class, "aranaking", "Arana King", 0x7813ff, 0xff58f5),
				//Senterian
				SlayerAPI.buildMobEntry(EntitySentryBlock.class, "sentryblock", "Sentry Block", 0x584040, 0x1C1C1C),
				SlayerAPI.buildMobEntry(EntitySentryLord.class, "sentrylord", "Sentry Lord", 0x584040, 0x1C1C1C),
				SlayerAPI.buildMobEntry(EntitySentryStalker.class, "sentrystalker", "Sentry Stalker", 0x584040, 0x1C1C1C),
				SlayerAPI.buildMobEntry(EntitySentryWalker.class, "sentrywalker", "Sentry Walker", 0x584040, 0x1C1C1C),
				SlayerAPI.buildMobEntry(EntityMiniSentryLord.class, "miniSentrylord", "Mini Sentry Lord", 0x584040, 0x1C1C1C),
				SlayerAPI.buildMobEntry(EntityMiniSentryStalker.class, "miniSentrystalker", "Mini Sentry Stalker", 0x584040, 0x1C1C1C),
				SlayerAPI.buildMobEntry(EntityMiniSentryWalker.class, "miniSentrywalker", "Mini Sentry Walker", 0x584040, 0x1C1C1C),
				//OBSIDIANBOAT
				SlayerAPI.buildMobEntryNoEgg(EntityObsidianBoat.class, "obsidianboat"),
				//TNT
				SlayerAPI.buildMobEntryNoEgg(EntityMagicExplosive.class, "magic_explosive"),
				//UTIL
				SlayerAPI.buildMobEntryNoEgg(EntityBossCrystal.class, "boss_crystal"),
		};

		event.getRegistry().registerAll(entries);
		LogHelper.info("Successfully registered " + entries.length + " mobs");

		MOB_ENTRIES = Arrays.asList(entries);
	}

	/**
	 * This method should be called only during game loading.
	 */
	public static List<EntityEntry> getMobEntries() {
		if (MOB_ENTRIES != null) {
			return Collections.unmodifiableList(MOB_ENTRIES);
		}

		throw new IllegalStateException("Method is called too late, mob entries are already deleted.");
	}
}