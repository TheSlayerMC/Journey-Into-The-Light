package net.journey.util;

import net.journey.entity.mob.boiling.*;
import net.journey.entity.mob.boiling.npc.*;
import net.journey.entity.mob.boss.*;
import net.journey.entity.mob.cloudia.*;
import net.journey.entity.mob.cloudia.npc.*;
import net.journey.entity.mob.corba.*;
import net.journey.entity.mob.corba.npc.*;
import net.journey.entity.mob.depths.*;
import net.journey.entity.mob.depths.npc.*;
import net.journey.entity.mob.end.*;
import net.journey.entity.mob.euca.*;
import net.journey.entity.mob.euca.npc.*;
import net.journey.entity.mob.frozen.*;
import net.journey.entity.mob.frozen.npc.*;
import net.journey.entity.mob.nether.EntityHellCow;
import net.journey.entity.mob.nether.EntityHellbot;
import net.journey.entity.mob.nether.EntityLavasnake;
import net.journey.entity.mob.nether.EntityMiniGhast;
import net.journey.entity.mob.nether.EntityReaper;
import net.journey.entity.mob.nether.EntityWitherspine;
import net.journey.entity.mob.overworld.*;
import net.journey.entity.mob.overworld.EntityWraith;
import net.journey.entity.mob.overworld.cold.EntityBlizzard;
import net.journey.entity.mob.overworld.jungle.EntityJungleGolem;
import net.journey.entity.mob.overworld.npc.*;
import net.journey.entity.mob.overworld.underground.*;
import net.journey.entity.mob.overworld.underground.npc.EntityRockiteGolem;
import net.journey.entity.mob.pet.EntityFerret;
import net.journey.entity.mob.pet.EntityShiverwolf;
import net.journey.entity.mob.pet.EntityTameRoc;
import net.journey.entity.mob.terrania.mob.EntityPurplian;
import net.journey.entity.mob.terrania.mob.EntityTerraScatterer;
import net.journey.entity.mob.terrania.mob.EntityTerragrow;
import net.journey.entity.mob.terrania.mob.EntityTerralight;
import net.journey.entity.mob.terrania.mob.EntityTerrashroom;
import net.journey.entity.mob.terrania.mob.EntityTerraslug;
import net.journey.entity.mob.terrania.npc.EntityTerranianEnchanter;
import net.journey.entity.mob.terrania.npc.EntityTerranianTrader;
import net.journey.entity.projectile.*;
import net.slayer.api.SlayerAPI;

public class EntityRegistry {

	public static void init() {
		registerProjectiles();
		registerEntitys();
		registerEndMobs();
		registerOverworldMobs();
		registerNetherMobs();
		registerBPMobs();
		registerFLMobs();
		registerEucaMobs();
		registerDepthsMobs();
		registerCorbaMobs();
		registerCloudiaMobs();
		registerTerraniaMobs();
		registerNPCs();
		registerBosses();
	}

	public static void registerProjectiles() {
		SlayerAPI.registerProjectile(EntityBasicProjectile.class, "basic");
		SlayerAPI.registerProjectile(EntityBouncingProjectile.class, "bouncing");
		SlayerAPI.registerProjectile(EntityChaosProjectile.class, "chaos");
		SlayerAPI.registerProjectile(EntityDoomsBringer.class, "doom");
		SlayerAPI.registerProjectile(EntityConjuring.class, "conjuring");
		SlayerAPI.registerProjectile(EntityEnlightenment.class, "enlightment");
		SlayerAPI.registerProjectile(EntityGreenpace.class, "greenpace");
		SlayerAPI.registerProjectile(EntityWizardsStar.class, "wizardsstar");
		SlayerAPI.registerProjectile(EntityMagmaFireball.class, "magmaball");
		SlayerAPI.registerProjectile(EntityFlameArrow.class, "flamearrow");
		SlayerAPI.registerProjectile(EntityFireBall.class, "fireball");
		SlayerAPI.registerProjectile(EntityIceBall.class, "iceball");
		SlayerAPI.registerProjectile(EntityTempleBall.class, "templeball");
		SlayerAPI.registerProjectile(EntityLightningBall.class, "lightningball");
		SlayerAPI.registerProjectile(EntityPoisonArrow.class, "poisonarrow");
		SlayerAPI.registerProjectile(EntityDarknessArrow.class, "darknessarrow");
		SlayerAPI.registerProjectile(EntityFrozenArrow.class, "frozenarrow");
		SlayerAPI.registerProjectile(EntityRockProjectile.class, "rockchunk");
		SlayerAPI.registerProjectile(EntityNetherPlasma.class, "netherplasma");
		SlayerAPI.registerProjectile(EntityOceanPlasma.class, "oceanplasma");
		SlayerAPI.registerProjectile(EntityForestPlasma.class, "forestplasma");
		SlayerAPI.registerProjectile(EntityBoilingPiercer.class, "boilingpiercer");
		SlayerAPI.registerProjectile(EntityNethicPiercer.class, "nethicpiercer");
		SlayerAPI.registerProjectile(EntityFrozenPiercer.class, "frozenpiercer");
		SlayerAPI.registerProjectile(EntityEucaPiercer.class, "eucapiercer");
		SlayerAPI.registerProjectile(EntityDepthsPiercer.class, "depthspiercer");
		SlayerAPI.registerProjectile(EntityCorbaPiercer.class, "corbapiercer");
		SlayerAPI.registerProjectile(EntityFrostbittenPiercer.class, "frostbittenpiercer");
		SlayerAPI.registerProjectile(EntityFrostyPiercer.class, "frostypiercer");
		SlayerAPI.registerProjectile(EntityBubbleProjectile.class, "bubbleprojectile");
		SlayerAPI.registerProjectile(EntityDetractor.class, "detractor");
		
		//SlayerAPI.registerProjectile(EntityRoyalKnife.class, "royalKnife");
		
		SlayerAPI.registerProjectile(EntityPower.class, "power");
		SlayerAPI.registerProjectile(EntityShimmererProjectile.class, "shimmererprojectile");
		SlayerAPI.registerProjectile(EntityDemonicBomb.class, "demonicbomb");
		SlayerAPI.registerProjectile(EntityFireBomb.class, "firebomb");
	}

	public static void registerEndMobs() {
		SlayerAPI.registerEndMob(EntityEnderLeaper.class, "enderLeaper", "Ender Leaper");
		SlayerAPI.registerEndMob(EntityEnderCrawler.class, "enderCrawler", "Ender Crawler");
	}

	public static void registerNetherMobs() {
		SlayerAPI.registerNetherMob(EntityHellbot.class, "hellbot", "Hellbot");
		SlayerAPI.registerNetherMob(EntityWitherspine.class, "witherspine", "Witherspine");
		SlayerAPI.registerNetherMob(EntityLavasnake.class, "lavasnake", "Lavasnake");
		SlayerAPI.registerNetherMob(EntityHellCow.class, "hellCow", "Hell Cow");
		SlayerAPI.registerNetherMob(EntityReaper.class, "reaper", "Reaper");
		SlayerAPI.registerNetherMob(EntityMiniGhast.class, "miniGhast", "Mini Ghast");
	}

	public static void registerOverworldMobs() {
		SlayerAPI.registerOverworldMob(EntityRobot.class, "robot", "Robot");
		SlayerAPI.registerOverworldMob(EntitySpyclops.class, "spyclops", "Spyclopse");
		SlayerAPI.registerOverworldMob(EntitySmallHongo.class, "smallHongo", "Small Hongo");
		SlayerAPI.registerOverworldMob(EntityMediumHongo.class, "mediumHongo", "Medium Hongo");
		SlayerAPI.registerOverworldMob(EntityBigHongo.class, "bigHongo", "Big Hongo");
		SlayerAPI.registerOverworldMob(EntitySandCrawler.class, "sandCrawler", "Sand Crawler");
		SlayerAPI.registerOverworldMob(EntityFireMage.class, "fireMage", "Fire Mage");
		SlayerAPI.registerOverworldMob(EntityIceMage.class, "iceMage", "Ice Mage");
		SlayerAPI.registerOverworldMob(EntityCaveMage.class, "caveMage", "Cave Mage");
		SlayerAPI.registerOverworldMob(EntityCavurn.class, "cavurn", "Cavurn");
		SlayerAPI.registerOverworldMob(EntityCaveling.class, "caveling", "Caveling");
		SlayerAPI.registerOverworldMob(EntityWraith.class, "wraith", "Wraith");
		SlayerAPI.registerOverworldMob(EntityStonewalker.class, "stonewalker", "Stonewalker");
		SlayerAPI.registerOverworldMob(EntityTurducken.class, "turducken", "Turducken");
		SlayerAPI.registerOverworldMob(EntityFloro.class, "floro", "Floro");
		SlayerAPI.registerOverworldMob(EntitySpectre.class, "spectre", "Spectre");
		SlayerAPI.registerOverworldMob(EntityGreenHonglow.class, "greenHonglow", "Green Honglow");
		SlayerAPI.registerOverworldMob(EntityHonglow.class, "redHonglow", "Red Honglow");
		SlayerAPI.registerOverworldMob(EntityBlueHonglow.class, "blueHonglow", "Blue Honglow");
		SlayerAPI.registerOverworldMob(EntitySwampFly.class, "swampFly", "Swamp Fly");
		SlayerAPI.registerOverworldMob(EntityBoom.class, "boomBoom", "Boom Boom");
		SlayerAPI.registerOverworldMob(EntityBlizzard.class, "blizzard", "Blizzard");
		SlayerAPI.registerOverworldMob(EntityDunewerm.class, "dunewerm", "Dunewerm");
		
		SlayerAPI.registerOverworldMob(EntityJungleGolem.class, "jungleGolem", "Jungle Golem");
		SlayerAPI.registerOverworldMob(EntityFerret.class, "ferret", "Ferret");
		SlayerAPI.registerOverworldMob(EntityTameRoc.class, "tameRoc", "Tame Roc");
	}

	public static void registerBPMobs() {
		//SlayerAPI.registerBPMob(EntityAshHoarder.class, "ashHoarder");
		//SlayerAPI.registerBPMob(EntityBurntAsh.class, "burntAsh");
		//SlayerAPI.registerBPMob(EntityMagmaGiant.class, "magmaGiant");
		//SlayerAPI.registerBPMob(EntityCrisp.class, "crisp");
		//SlayerAPI.registerBPMob(EntityExposedFlame.class, "exposedFlame");
		SlayerAPI.registerBPMob(EntityMagmaBlaze.class, "magmaBlaze", "Magma Blaze");
		SlayerAPI.registerBPMob(EntityBurningLight.class, "burningLight", "Burning Light");
		SlayerAPI.registerBPMob(EntityFrightener.class, "frightener", "Frightener");
		SlayerAPI.registerBPMob(EntityHellwing.class, "hellwing", "Hellwing");
		SlayerAPI.registerBPMob(EntityObserver.class, "observer", "Observer");
		SlayerAPI.registerBPMob(EntityScreamer.class, "screamer", "Screamer");
		SlayerAPI.registerBPMob(EntityPhoenix.class, "phoenix", "Phoenix");
	}

	public static void registerFLMobs() {
		SlayerAPI.registerFLMob(EntityShatterer.class, "shatterer", "Shatterer");
		SlayerAPI.registerFLMob(EntityFrozenTroll.class, "frozenTroll", "Frozen Troll");
		SlayerAPI.registerFLMob(EntityPermafraust.class, "permafraust", "Permafraust");
		SlayerAPI.registerFLMob(EntityShiveringBushwalker.class, "shiveringBushwalker", "Shivering Bushwalker");
		SlayerAPI.registerFLMob(EntityShiverwolf.class, "shiverwolf", "Shiverwolf");
		SlayerAPI.registerFLMob(EntityShiveringShrieker.class, "shiveringShrieker", "Shivering Shrieker");
		SlayerAPI.registerFLMob(EntityFrozenFrostbiter.class, "frozenFrostbiter", "Frozen Frostbiter");
		SlayerAPI.registerFLMob(EntityCrystalCluster.class, "crystalCluster", "Crystal Cluster");
		SlayerAPI.registerFLMob(EntityShiverwing.class, "shiverwing", "Shiverwing");
	}

	public static void registerEucaMobs() {
		//SlayerAPI.registerEucaMob(EntityInsecto.class, "insecto");
		//SlayerAPI.registerEucaMob(EntityPsyollom.class, "psyollom");
		//SlayerAPI.registerEucaMob(EntityEucaFighter.class, "eucaFighter");
		//SlayerAPI.registerEucaMob(EntityEucaHopper.class, "eucaHopper");
		SlayerAPI.registerEucaMob(EntityEucaCharger.class, "eucaCharger", "Euca Charger");
		SlayerAPI.registerEucaMob(EntityDynaster.class, "dynaster", "Dynaster");
		//SlayerAPI.registerEucaMob(EntityGolder.class, "golder", "Golder");
		SlayerAPI.registerEucaMob(EntityGolditeMage.class, "golditeMage", "Goldite Mage");
		SlayerAPI.registerEucaMob(EntitySilverbot.class, "silverbot", "Silverbot");
		SlayerAPI.registerEucaMob(EntityGoldbot.class, "goldbot", "Goldbot");
		SlayerAPI.registerEucaMob(EntityShimmerer.class, "shimmerer", "Shimmerer");
		SlayerAPI.registerEucaMob(EntityGoldwing.class, "goldwing", "Goldwing");
	}

	public static void registerDepthsMobs() {
		SlayerAPI.registerDepthsMob(EntitySpikedBeast.class, "spikedBeast", "Spiked Beast");
		SlayerAPI.registerDepthsMob(EntityDarknessCrawler.class, "darknessCrawler", "Darkness Crawler");
		SlayerAPI.registerDepthsMob(EntityDepthsBeast.class, "depthsBeast", "Depths Beast");
		SlayerAPI.registerDepthsMob(EntityDepthsHunter.class, "depthsHunter", "Depths Hunter");
		SlayerAPI.registerDepthsMob(EntityRoc.class, "roc", "Roc");
		SlayerAPI.registerDepthsMob(EntityDarkener.class, "darkener", "Darkener");
		SlayerAPI.registerDepthsMob(EntityLightener.class, "lightener", "Lightener");
		SlayerAPI.registerDepthsMob(EntityDarkSorcerer.class, "darkSorcerer", "Dark Sorcerer");
	}

	public static void registerCorbaMobs() {
		SlayerAPI.registerCorbaMob(EntityOverseer.class, "overseer", "Overseer");
		SlayerAPI.registerCorbaMob(EntityOverseerElder.class, "overseerElder", "Overseer Elder");
		SlayerAPI.registerCorbaMob(EntitySurfaceSeer.class, "surfaceSeer", "Surface Seer");
		SlayerAPI.registerCorbaMob(EntityTreeGolem.class, "treeGolem", "Tree Golem");
		SlayerAPI.registerCorbaMob(EntityWoodCreature.class, "woodCreature", "Wood Creature");
		SlayerAPI.registerCorbaMob(EntityNatureMage.class, "natureMage", "Nature Mage");
		SlayerAPI.registerCorbaMob(EntityLeafBlower.class, "leafBlower", "Leaf Blower");
		SlayerAPI.registerCorbaMob(EntityWoodpecker.class, "woodpecker", "Woodpecker");
	}

	public static void registerCloudiaMobs() {
		SlayerAPI.registerCloudiaMob(EntityCloudGhost.class, "cloudGhost", "Cloud Ghost");
		SlayerAPI.registerCloudiaMob(EntityCloudFlyer.class, "cloudFlyer", "Cloud Flyer");
		SlayerAPI.registerCloudiaMob(EntityStarlightGolem.class, "starlightGolem", "Starlight Golem");
		SlayerAPI.registerCloudiaMob(EntityStarlightTransporter.class, "starlightTransporter", "Starlight Transporter");
		SlayerAPI.registerCloudiaMob(EntityStarlightWalker.class, "starlightWalker", "Starlight Walker");
		SlayerAPI.registerCloudiaMob(EntitySkyEel.class, "skyEel", "Sky Eel");
	}

	public static void registerNPCs() {
		SlayerAPI.registerNPC(EntityStarlightVillager.class, "starlightVillager", "Starlight Villager");
		SlayerAPI.registerNPC(EntityStarlightBlacksmith.class, "starlightBlacksmith", "Starlight Blacksmith");
		SlayerAPI.registerNPC(EntityMage.class, "mage", "Mage");
		SlayerAPI.registerNPC(EntityBlacksmith.class, "blacksmith", "Blacksmith");
		SlayerAPI.registerNPC(EntityFrozenMerchant.class, "frozenMerchant", "Frozen Merchant");
		SlayerAPI.registerNPC(EntityEscapedConvict.class, "escapedConvict", "Escaped Convict");
		SlayerAPI.registerNPC(EntityStaringGuardian.class, "staringGuardian", "Staring Guardian");
		SlayerAPI.registerNPC(EntityTordo.class, "tordo", "Tordo");
		SlayerAPI.registerNPC(EntityRedTordo.class, "redTordo", "Red Tordo");
		SlayerAPI.registerNPC(EntityBoilTrader.class, "boilTrader", "Boil Trader");
		SlayerAPI.registerNPC(EntityAlloyMender.class, "alloyMender", "Alloy Mender");
		SlayerAPI.registerNPC(EntityTerranianTrader.class, "terranianTrader", "Terranian Trader");
		SlayerAPI.registerNPC(EntityOvergrownMerchant.class, "overgrownMurchant", "Overgrown Merchant");
		SlayerAPI.registerNPC(EntityTerranianEnchanter.class, "terranianEnchanter", "Terranian Enchanter");
		SlayerAPI.registerNPC(EntityRockiteGolem.class, "rockiteGolem", "Rockite Golem");
	}

	public static void registerBosses() {
		SlayerAPI.registerBossMob(EntitySoulWatcher.class, "soulWatcher", "Soul Watcher");
		SlayerAPI.registerBossMob(EntityNetherBeast.class, "beastOfTheNether", "Beast of the Nether");
		SlayerAPI.registerBossMob(EntityWitheringBeast.class, "witheringBeast", "Withering Beast");
		SlayerAPI.registerBossMob(EntityCalcia.class, "calcia", "Calcia");
		SlayerAPI.registerBossMob(EntityTempleGuardian.class, "templeGuardian", "Temple Guardian");
		SlayerAPI.registerBossMob(EntityFourfa.class, "fourfa", "Fourfa");
		SlayerAPI.registerBossMob(EntityBlazier.class, "blazier", "Blazier");
		SlayerAPI.registerBossMob(EntitySentryKing.class, "sentryKing", "Sentry King");
		SlayerAPI.registerBossMob(EntityThunderbird.class, "thunderbird", "Thunderbird");
		SlayerAPI.registerBossMob(EntityLogger.class, "logger", "Logger");
		SlayerAPI.registerBossMob(EntityCorallator.class, "corallator", "Corrallator");
		SlayerAPI.registerBossMob(EntitySkyStalker.class, "skyStalker", "Sky Stalker");
		SlayerAPI.registerBossMob(EntityEudor.class, "eudor", "Eudor");
		SlayerAPI.registerBossMob(EntityScale.class, "scale", "Scale");
		SlayerAPI.registerBossMob(EntityTerranianProtector.class, "terranianProtector", "Terranian Protector");
		//SlayerAPI.registerBossMob(EntityWraith.class, "wraith");
	}
	
	public static void registerTerraniaMobs() {
		SlayerAPI.registerTerraniaMob(EntityTerralight.class, "terralight", "Terralight");
		SlayerAPI.registerTerraniaMob(EntityTerraScatterer.class, "terraScatterer", "Terra Scatterer");
		SlayerAPI.registerTerraniaMob(EntityPurplian.class, "purplian", "Purplian");
		SlayerAPI.registerTerraniaMob(EntityTerraslug.class, "terraslug", "Terraslug");
		SlayerAPI.registerTerraniaMob(EntityTerragrow.class, "terragrow", "Terragrow");
		SlayerAPI.registerTerraniaMob(EntityTerrashroom.class, "terrashroom", "Terrashroom");
	}

	public static void registerEntitys() {
		
	}
}