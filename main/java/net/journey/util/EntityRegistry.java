package net.journey.util;

import net.journey.entity.item.EntityObsidianBoat;
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
import net.journey.entity.mob.nether.EntityHellTurtle;
import net.journey.entity.mob.nether.EntityHellbot;
import net.journey.entity.mob.nether.EntityInfernoBlaze;
import net.journey.entity.mob.nether.EntityLavasnake;
import net.journey.entity.mob.nether.EntityMiniGhast;
import net.journey.entity.mob.nether.EntityReaper;
import net.journey.entity.mob.nether.EntityWitherspine;
import net.journey.entity.mob.overworld.*;
import net.journey.entity.mob.overworld.EntityWraith;
import net.journey.entity.mob.overworld.cold.EntityBlizzard;
import net.journey.entity.mob.overworld.jungle.EntityJungleGolem;
import net.journey.entity.mob.overworld.jungle.EntityJungleSpider;
import net.journey.entity.mob.overworld.jungle.EntityJungleTurtle;
import net.journey.entity.mob.overworld.npc.*;
import net.journey.entity.mob.overworld.underground.*;
import net.journey.entity.mob.overworld.underground.npc.EntityRockiteGolem;
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
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.slayer.api.SlayerAPI;

public class EntityRegistry {


	public static EntityEntry[] initProjectiles() {
        // Projectiles
        EntityEntry[] JourneyProjectiles = {
		SlayerAPI.registerProjectile(EntityBasicProjectile.class, "basic"),
		SlayerAPI.registerProjectile(EntityBouncingProjectile.class, "bouncing"),
		SlayerAPI.registerProjectile(EntityChaosProjectile.class, "chaos"),
		SlayerAPI.registerProjectile(EntityDoomsBringer.class, "doom"),
		SlayerAPI.registerProjectile(EntityConjuring.class, "conjuring"),
		SlayerAPI.registerProjectile(EntityEnlightenment.class, "enlightment"),
		SlayerAPI.registerProjectile(EntityGreenpace.class, "greenpace"),
		SlayerAPI.registerProjectile(EntityWizardsStar.class, "wizardsstar"),
		SlayerAPI.registerProjectile(EntityMagmaFireball.class, "magmaball"),
		SlayerAPI.registerProjectile(EntityFlameArrow.class, "flamearrow"),
		SlayerAPI.registerProjectile(EntityFireBall.class, "fireball"),
		SlayerAPI.registerProjectile(EntityIceBall.class, "iceball"),
		SlayerAPI.registerProjectile(EntityTempleBall.class, "templeball"),
		SlayerAPI.registerProjectile(EntityLightningBall.class, "lightningball"),
		SlayerAPI.registerProjectile(EntityEssenceArrow.class, "essencearrow"),
		SlayerAPI.registerProjectile(EntityPoisonArrow.class, "poisonarrow"),
		SlayerAPI.registerProjectile(EntityDarknessArrow.class, "darknessarrow"),
		SlayerAPI.registerProjectile(EntityFrozenArrow.class, "frozenarrow"),
		SlayerAPI.registerProjectile(EntityRockProjectile.class, "rockchunk"),
		SlayerAPI.registerProjectile(EntityNetherPlasma.class, "netherplasma"),
		SlayerAPI.registerProjectile(EntityOceanPlasma.class, "oceanplasma"),
		SlayerAPI.registerProjectile(EntityForestPlasma.class, "forestplasma"),
		SlayerAPI.registerProjectile(EntityBoilingPiercer.class, "boilingpiercer"),
		SlayerAPI.registerProjectile(EntityNethicPiercer.class, "nethicpiercer"),
		SlayerAPI.registerProjectile(EntityFrozenPiercer.class, "frozenpiercer"),
		SlayerAPI.registerProjectile(EntityEucaPiercer.class, "eucapiercer"),
		SlayerAPI.registerProjectile(EntityDepthsPiercer.class, "depthspiercer"),
		SlayerAPI.registerProjectile(EntityCorbaPiercer.class, "corbapiercer"),
		SlayerAPI.registerProjectile(EntityFrostbittenPiercer.class, "frostbittenpiercer"),
		SlayerAPI.registerProjectile(EntityFrostyPiercer.class, "frostypiercer"),
		SlayerAPI.registerProjectile(EntitySunsetPiercer.class, "sunsetpiercer"),
		SlayerAPI.registerProjectile(EntitySkyPiercer.class, "skypiercer"),
		SlayerAPI.registerProjectile(EntityMoltenKnife.class, "moltenknife"),
		SlayerAPI.registerProjectile(EntityAquaticKnife.class, "aquaticknife"),
		SlayerAPI.registerProjectile(EntityBloodKnife.class, "bloodknife"),
		SlayerAPI.registerProjectile(EntityCharredKnife.class, "charredknife"),
		SlayerAPI.registerProjectile(EntitySizzlingKnife.class, "sizzlingknife"),
		SlayerAPI.registerProjectile(EntityBubbleProjectile.class, "bubbleprojectile"),
		SlayerAPI.registerProjectile(EntityDetractor.class, "detractor"),
		SlayerAPI.registerProjectile(EntityRock.class, "rock"),
		SlayerAPI.registerProjectile(EntityOvergrown.class, "overgrown"),
		SlayerAPI.registerProjectile(EntityShimmererProjectile.class, "shimmererprojectile"),
		SlayerAPI.registerProjectile(EntityDemonicBomb.class, "demonicbomb"),
		SlayerAPI.registerProjectile(EntityFireBomb.class, "firebomb")
        };

        return JourneyProjectiles;
        }
	public static EntityEntry[] initMobs() {
        EntityEntry[] JourneyMobs = {
        		//END
        	SlayerAPI.buildEntityEntry(EntityEnderLeaper.class, "enderleaper", "Ender Leaper", 0x440089, 0xBC00BC),
        	SlayerAPI.buildEntityEntry(EntityEnderCrawler.class, "endercrawler", "Ender Crawler", 0x440089, 0xBC00BC),
        		//NETHER
    		SlayerAPI.buildEntityEntry(EntityHellbot.class, "hellbot", "Hellbot", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityWitherspine.class, "witherspine", "Witherspine", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityLavasnake.class, "lavasnake", "Lava Snake", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityHellCow.class, "hellcow", "Hell Cow", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityReaper.class, "reaper", "Reaper", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityMiniGhast.class, "minighast", "Mini-Ghast", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityInfernoBlaze.class, "infernoblaze", "Inferno Blaze", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityHellTurtle.class, "hellturtle", "Hell Turtle", 0x7c4c2c, 0x26b530),
        		//OVERWORLD
    		SlayerAPI.buildEntityEntry(EntityRobot.class, "robot", "Robot", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntitySpyclops.class, "spyclops", "Spyclopse", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntitySmallHongo.class, "smallhongo", "Small Hongo", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityMediumHongo.class, "mediumhongo", "Medium Hongo", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityBigHongo.class, "bighongo", "Big Hongo", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntitySandCrawler.class, "sandcrawler", "Sand Crawler", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityFireMage.class, "firemage", "Fire Mage", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityIceMage.class, "icemage", "Ice Mage", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityCaveMage.class, "cavemage", "Cave Mage", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityCavurn.class, "cavurn", "Cavurn", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityCaveling.class, "caveling", "Caveling", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityWraith.class, "wraith", "Wraith", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityStonewalker.class, "stonewalker", "Stone Walker", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityTurducken.class, "turducken", "Turducken", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityFloro.class, "floro", "Floro", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntitySpectre.class, "spectre", "Spectre", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityGreenHonglow.class, "greenhonglow", "Green Honglow", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityHonglow.class, "redhonglow", "Red Honglow", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityBlueHonglow.class, "bluehonglow", "Blue Honglow", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntitySwampFly.class, "swampfly", "Swamp Fly", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityBoom.class, "boomboom", "Boom Boom", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityBlizzard.class, "blizzard", "Blizzard", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityDunewerm.class, "dunewerm", "Dunewerm", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityJungleGolem.class, "junglegolem", "Jungle Golem", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityJungleTurtle.class, "jungleturtle", "Jungle Turtle", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityJungleSpider.class, "jungleSpider", "Jungle Spider", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityTameRoc.class, "tameroc", "Roc", 0x7c4c2c, 0x26b530),
    		//BP
    		SlayerAPI.buildEntityEntry(EntityMagmaBlaze.class, "magmablaze", "Magma Blaze", 0xff7800, 0xffa800),
    		SlayerAPI.buildEntityEntry(EntityBurningLight.class, "burninglight", "Burning Light", 0xff7800, 0xffa800),
    		SlayerAPI.buildEntityEntry(EntityFrightener.class, "frightener", "Frightener", 0xff7800, 0xffa800),
    		SlayerAPI.buildEntityEntry(EntityHellwing.class, "hellwing", "Hellwing", 0xff7800, 0xffa800),
    		SlayerAPI.buildEntityEntry(EntityObserver.class, "observer", "Observer", 0xff7800, 0xffa800),
    		SlayerAPI.buildEntityEntry(EntityScreamer.class, "screamer", "Screamer", 0xff7800, 0xffa800),
    		SlayerAPI.buildEntityEntry(EntityPhoenix.class, "phoenix", "Phoenix", 0xff7800, 0xffa800),
    		//FL
    		SlayerAPI.buildEntityEntry(EntityShatterer.class, "shatterer", "Shatterer", 0x00d8ff, 0xd8f9ff),
    		SlayerAPI.buildEntityEntry(EntityFrozenTroll.class, "frozentroll", "Frozen Troll", 0x00d8ff, 0xd8f9ff),
    		SlayerAPI.buildEntityEntry(EntityPermafraust.class, "permafraust", "Permafraust", 0x00d8ff, 0xd8f9ff),
    		SlayerAPI.buildEntityEntry(EntityShiveringBushwalker.class, "shiveringbushwalker", "Shivering Bushwalker", 0x00d8ff, 0xd8f9ff),
    		SlayerAPI.buildEntityEntry(EntityShiverwolf.class, "shiverwolf", "Shiver Wolf", 0x00d8ff, 0xd8f9ff),
    		SlayerAPI.buildEntityEntry(EntityShiveringShrieker.class, "shiveringshrieker", "Sjoveromg Shrieker", 0x00d8ff, 0xd8f9ff),
    		SlayerAPI.buildEntityEntry(EntityFrozenFrostbiter.class, "frozenfrostbiter", "Frozen Frost Biter", 0x00d8ff, 0xd8f9ff),
    		SlayerAPI.buildEntityEntry(EntityCrystalCluster.class, "crystalcluster", "Crystal Cluster", 0x00d8ff, 0xd8f9ff),
    		SlayerAPI.buildEntityEntry(EntityShiverwing.class, "shiverwing", "Shiverwing", 0x00d8ff, 0xd8f9ff),
    		//EUCA
    		//SlayerAPI.buildEntityEntry(EntityInsecto.class, "insecto");
    		//SlayerAPI.buildEntityEntry(EntityPsyollom.class, "psyollom");
    		//SlayerAPI.buildEntityEntry(EntityEucaFighter.class, "eucaFighter");
    		//SlayerAPI.buildEntityEntry(EntityEucaHopper.class, "eucaHopper");
    		SlayerAPI.buildEntityEntry(EntityEucaCharger.class, "eucacharger", "Euca Charger", 0xffba00, 0xe0e0e0),
    		SlayerAPI.buildEntityEntry(EntityDynaster.class, "dynaster", "Dynaster", 0xffba00, 0xe0e0e0),
    		//SlayerAPI.buildEntityEntry(EntityGolder.class, "golder", 0xffba00, 0xe0e0e0),
    		SlayerAPI.buildEntityEntry(EntityGolditeMage.class, "golditemage", "Goldite Mage", 0xffba00, 0xe0e0e0),
    		SlayerAPI.buildEntityEntry(EntitySilverbot.class, "silverbot", "Silver Bot", 0xffba00, 0xe0e0e0),
    		SlayerAPI.buildEntityEntry(EntityGoldbot.class, "goldbot", "Gold Bot", 0xffba00, 0xe0e0e0),
    		SlayerAPI.buildEntityEntry(EntityShimmerer.class, "shimmerer", "Shimmerer", 0xffba00, 0xe0e0e0),
    		SlayerAPI.buildEntityEntry(EntityGoldwing.class, "goldwing", "Goldwing", 0xffba00, 0xe0e0e0),
    		//DEPTH
    		SlayerAPI.buildEntityEntry(EntitySpikedBeast.class, "spikedbeast", "Spiked Beast", 0x003CA5, 0x0098A3),
    		SlayerAPI.buildEntityEntry(EntityDarknessCrawler.class, "darknesscrawler", "Darkness Crawler0", 0x003CA5, 0x0098A3),
    		SlayerAPI.buildEntityEntry(EntityDepthsBeast.class, "depthsbeast", "Deapths Beast", 0x003CA5, 0x0098A3),
    		SlayerAPI.buildEntityEntry(EntityDepthsHunter.class, "depthshunter", "Deapther Hunter", 0x003CA5, 0x0098A3),
    		SlayerAPI.buildEntityEntry(EntityRoc.class, "roc", "Rox", 0x003CA5, 0x0098A3),
    		SlayerAPI.buildEntityEntry(EntityDarkener.class, "darkener", "Darkener", 0x003CA5, 0x0098A3),
    		SlayerAPI.buildEntityEntry(EntityLightener.class, "lightener", "Lightener", 0x003CA5, 0x0098A3),
    		SlayerAPI.buildEntityEntry(EntityDarkSorcerer.class, "darksorcerer", "Dark Sorcerer", 0x003CA5, 0x0098A3),
    		//CORBA
    		SlayerAPI.buildEntityEntry(EntityOverseer.class, "overseer", "Overseer", 0x1e8c00, 0x36ff00),
    		SlayerAPI.buildEntityEntry(EntityOverseerElder.class, "overseerelder", "Overseer Elder", 0x1e8c00, 0x36ff00),
    		SlayerAPI.buildEntityEntry(EntitySurfaceSeer.class, "surfaceseer", "Surface Seer", 0x1e8c00, 0x36ff00),
    		SlayerAPI.buildEntityEntry(EntityTreeGolem.class, "treegolem", "Tree Golem", 0x1e8c00, 0x36ff00),
    		SlayerAPI.buildEntityEntry(EntityWoodCreature.class, "woodcreature", "Wood Creature", 0x1e8c00, 0x36ff00),
    		SlayerAPI.buildEntityEntry(EntityNatureMage.class, "naturemage", "Nature Mage", 0x1e8c00, 0x36ff00),
    		SlayerAPI.buildEntityEntry(EntityLeafBlower.class, "leafblower", "Leaf Blower", 0x1e8c00, 0x36ff00),
    		SlayerAPI.buildEntityEntry(EntityWoodpecker.class, "woodpecker", "Woodpecker", 0x1e8c00, 0x36ff00),
    		//CLOUDIA
    		SlayerAPI.buildEntityEntry(EntityCloudGhost.class, "cloudghost", "Cloud Ghost", 0xa87abd, 0x9000ff),
    		SlayerAPI.buildEntityEntry(EntityCloudFlyer.class, "cloudflyer", "Cloud Flyer", 0xa87abd, 0x9000ff),
    		SlayerAPI.buildEntityEntry(EntityStarlightGolem.class, "starlightgolem", "Starlight Golem", 0xa87abd, 0x9000ff),
    		SlayerAPI.buildEntityEntry(EntityStarlightTransporter.class, "starlighttransporter", "Starlight Transporter", 0xa87abd, 0x9000ff),
    		SlayerAPI.buildEntityEntry(EntityStarlightWalker.class, "starlightwalker", "Starlight Walker", 0xa87abd, 0x9000ff),
    		SlayerAPI.buildEntityEntry(EntitySkyEel.class, "skyeel", "Sky Eel", 0xa87abd, 0x9000ff),
    		//NPC
    		SlayerAPI.buildEntityEntry(EntityStarlightVillager.class, "starlightvillager", "Starlight Villager", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityStarlightBlacksmith.class, "starlightblacksmith", "Starlight Blacksmith", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityMage.class, "mage", "Mage", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityBlacksmith.class, "blacksmith", "Blacksmith", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityFrozenMerchant.class, "frozenmerchant", "Frozen Merchant", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityEscapedConvict.class, "escapedconvict", "Escaped Convict", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityStaringGuardian.class, "staringguardian", "Staring Guardian", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityTordo.class, "tordo", "Tordo", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityRedTordo.class, "redtordo", "Red Tordo", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityBoilTrader.class, "boiltrader", "Boil Trader", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityAlloyMender.class, "alloymender", "Alloy Mender", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityTerranianTrader.class, "terraniantrader", "Terranian Trader", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityOvergrownMerchant.class, "overgrownmerchant", "Overgrown Merchant", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityTerranianEnchanter.class, "terranianenchanter", "Terranian Enchanter", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityRockiteGolem.class, "rockitegolem", "Rockite Golem", 0x00FF8C, 0x00F6FF),
    		//BOSS
    		SlayerAPI.buildEntityEntry(EntitySoulWatcher.class, "soulwatcher", "Soul Watcher", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntityNetherBeast.class, "beastofthenether", "Nether Beast", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntityWitheringBeast.class, "witheringbeast", "Withering Beast", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntityCalcia.class, "calcia", "Calcia", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntityTempleGuardian.class, "templeguardian", "Temple Guardian", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntityFourfa.class, "fourfa", "Fourfa", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntityBlazier.class, "blazier", "Blazier", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntitySentryKing.class, "sentryking", "Sentry King", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntityThunderbird.class, "thunderbird", "Thunderbird", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntityLogger.class, "logger", "Logger", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntityCorallator.class, "corallator", "Corallator", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntitySkyStalker.class, "skystalker", "Sky Stalker", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntityEudor.class, "eudor", "Eudor", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntityScale.class, "scale", "Scale", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntityTerranianProtector.class, "terranianprotector", "Terraian Protector", 0x000000, 0x9B0000),
    		//Terrania
    		SlayerAPI.buildEntityEntry(EntityTerralight.class, "terralight", "Terralight", 0x7813ff, 0xff58f5),
    		SlayerAPI.buildEntityEntry(EntityTerraScatterer.class, "terrascatterer", "Terra Scatterer", 0x7813ff, 0xff58f5),
    		SlayerAPI.buildEntityEntry(EntityPurplian.class, "purplian", "Purplian", 0x7813ff, 0xff58f5),
    		SlayerAPI.buildEntityEntry(EntityTerraslug.class, "terraslug", "Purple Slug", 0x7813ff, 0xff58f5),
    		SlayerAPI.buildEntityEntry(EntityTerragrow.class, "terragrow", "Terragrow", 0x7813ff, 0xff58f5),
    		SlayerAPI.buildEntityEntry(EntityTerrashroom.class, "terrashroom", "Terrashroom", 0x7813ff, 0xff58f5),
    		//OBSIDIANBOAT
    		SlayerAPI.buildEntityEntryNoEgg(EntityObsidianBoat.class, "obsidianboat", 16)
        };

        return JourneyMobs;
        }

}