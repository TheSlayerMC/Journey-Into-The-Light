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
        	SlayerAPI.buildEntityEntry(EntityEnderLeaper.class, "enderleaper", 0x440089, 0xBC00BC),
        	SlayerAPI.buildEntityEntry(EntityEnderCrawler.class, "endercrawler", 0x440089, 0xBC00BC),
        		//NETHER
    		SlayerAPI.buildEntityEntry(EntityHellbot.class, "hellbot", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityWitherspine.class, "witherspine", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityLavasnake.class, "lavasnake", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityHellCow.class, "hellcow", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityReaper.class, "reaper", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityMiniGhast.class, "minighast", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityInfernoBlaze.class, "infernoblaze", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityHellTurtle.class, "hellturtle", 0x7c4c2c, 0x26b530),
        		//OVERWORLD
    		SlayerAPI.buildEntityEntry(EntityRobot.class, "robot", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntitySpyclops.class, "spyclops", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntitySmallHongo.class, "smallhongo", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityMediumHongo.class, "mediumhongo", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityBigHongo.class, "bighongo", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntitySandCrawler.class, "sandcrawler", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityFireMage.class, "firemage", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityIceMage.class, "icemage", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityCaveMage.class, "cavemage", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityCavurn.class, "cavurn", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityCaveling.class, "caveling", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityWraith.class, "wraith", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityStonewalker.class, "stonewalker", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityTurducken.class, "turducken", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityFloro.class, "floro", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntitySpectre.class, "spectre", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityGreenHonglow.class, "greenhonglow", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityHonglow.class, "redhonglow", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityBlueHonglow.class, "bluehonglow", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntitySwampFly.class, "swampfly", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityBoom.class, "boomboom", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityBlizzard.class, "blizzard", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityDunewerm.class, "dunewerm", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityJungleGolem.class, "junglegolem", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityJungleTurtle.class, "jungleturtle", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityJungleSpider.class, "jungleSpider", 0x7c4c2c, 0x26b530),
    		SlayerAPI.buildEntityEntry(EntityTameRoc.class, "tameroc", 0x7c4c2c, 0x26b530),
    		//BP
    		//SlayerAPI.buildEntityEntry(EntityAshHoarder.class, "ashHoarder");
    		//SlayerAPI.buildEntityEntry(EntityBurntAsh.class, "burntAsh");
    		//SlayerAPI.buildEntityEntry(EntityMagmaGiant.class, "magmaGiant");
    		//SlayerAPI.buildEntityEntry(EntityCrisp.class, "crisp");
    		//SlayerAPI.buildEntityEntry(EntityExposedFlame.class, "exposedFlame");
    		SlayerAPI.buildEntityEntry(EntityMagmaBlaze.class, "magmablaze", 0xff7800, 0xffa800),
    		SlayerAPI.buildEntityEntry(EntityBurningLight.class, "burninglight", 0xff7800, 0xffa800),
    		SlayerAPI.buildEntityEntry(EntityFrightener.class, "frightener", 0xff7800, 0xffa800),
    		SlayerAPI.buildEntityEntry(EntityHellwing.class, "hellwing", 0xff7800, 0xffa800),
    		SlayerAPI.buildEntityEntry(EntityObserver.class, "observer", 0xff7800, 0xffa800),
    		SlayerAPI.buildEntityEntry(EntityScreamer.class, "screamer", 0xff7800, 0xffa800),
    		SlayerAPI.buildEntityEntry(EntityPhoenix.class, "phoenix", 0xff7800, 0xffa800),
    		//FL
    		SlayerAPI.buildEntityEntry(EntityShatterer.class, "shatterer", 0x00d8ff, 0xd8f9ff),
    		SlayerAPI.buildEntityEntry(EntityFrozenTroll.class, "frozentroll", 0x00d8ff, 0xd8f9ff),
    		SlayerAPI.buildEntityEntry(EntityPermafraust.class, "permafraust", 0x00d8ff, 0xd8f9ff),
    		SlayerAPI.buildEntityEntry(EntityShiveringBushwalker.class, "shiveringbushwalker", 0x00d8ff, 0xd8f9ff),
    		SlayerAPI.buildEntityEntry(EntityShiverwolf.class, "shiverwolf", 0x00d8ff, 0xd8f9ff),
    		SlayerAPI.buildEntityEntry(EntityShiveringShrieker.class, "shiveringshrieker", 0x00d8ff, 0xd8f9ff),
    		SlayerAPI.buildEntityEntry(EntityFrozenFrostbiter.class, "frozenfrostbiter", 0x00d8ff, 0xd8f9ff),
    		SlayerAPI.buildEntityEntry(EntityCrystalCluster.class, "crystalcluster", 0x00d8ff, 0xd8f9ff),
    		SlayerAPI.buildEntityEntry(EntityShiverwing.class, "shiverwing", 0x00d8ff, 0xd8f9ff),
    		//EUCA
    		//SlayerAPI.buildEntityEntry(EntityInsecto.class, "insecto");
    		//SlayerAPI.buildEntityEntry(EntityPsyollom.class, "psyollom");
    		//SlayerAPI.buildEntityEntry(EntityEucaFighter.class, "eucaFighter");
    		//SlayerAPI.buildEntityEntry(EntityEucaHopper.class, "eucaHopper");
    		SlayerAPI.buildEntityEntry(EntityEucaCharger.class, "eucacharger", 0xffba00, 0xe0e0e0),
    		SlayerAPI.buildEntityEntry(EntityDynaster.class, "dynaster", 0xffba00, 0xe0e0e0),
    		//SlayerAPI.buildEntityEntry(EntityGolder.class, "golder", 0xffba00, 0xe0e0e0),
    		SlayerAPI.buildEntityEntry(EntityGolditeMage.class, "golditemage", 0xffba00, 0xe0e0e0),
    		SlayerAPI.buildEntityEntry(EntitySilverbot.class, "silverbot", 0xffba00, 0xe0e0e0),
    		SlayerAPI.buildEntityEntry(EntityGoldbot.class, "goldbot", 0xffba00, 0xe0e0e0),
    		SlayerAPI.buildEntityEntry(EntityShimmerer.class, "shimmerer", 0xffba00, 0xe0e0e0),
    		SlayerAPI.buildEntityEntry(EntityGoldwing.class, "goldwing", 0xffba00, 0xe0e0e0),
    		//DEPTH
    		SlayerAPI.buildEntityEntry(EntitySpikedBeast.class, "spikedbeast", 0x003CA5, 0x0098A3),
    		SlayerAPI.buildEntityEntry(EntityDarknessCrawler.class, "darknesscrawler", 0x003CA5, 0x0098A3),
    		SlayerAPI.buildEntityEntry(EntityDepthsBeast.class, "depthsbeast", 0x003CA5, 0x0098A3),
    		SlayerAPI.buildEntityEntry(EntityDepthsHunter.class, "depthshunter", 0x003CA5, 0x0098A3),
    		SlayerAPI.buildEntityEntry(EntityRoc.class, "roc", 0x003CA5, 0x0098A3),
    		SlayerAPI.buildEntityEntry(EntityDarkener.class, "darkener", 0x003CA5, 0x0098A3),
    		SlayerAPI.buildEntityEntry(EntityLightener.class, "lightener", 0x003CA5, 0x0098A3),
    		SlayerAPI.buildEntityEntry(EntityDarkSorcerer.class, "darksorcerer", 0x003CA5, 0x0098A3),
    		//CORBA
    		SlayerAPI.buildEntityEntry(EntityOverseer.class, "overseer", 0x1e8c00, 0x36ff00),
    		SlayerAPI.buildEntityEntry(EntityOverseerElder.class, "overseerelder", 0x1e8c00, 0x36ff00),
    		SlayerAPI.buildEntityEntry(EntitySurfaceSeer.class, "surfaceseer", 0x1e8c00, 0x36ff00),
    		SlayerAPI.buildEntityEntry(EntityTreeGolem.class, "treegolem", 0x1e8c00, 0x36ff00),
    		SlayerAPI.buildEntityEntry(EntityWoodCreature.class, "woodcreature", 0x1e8c00, 0x36ff00),
    		SlayerAPI.buildEntityEntry(EntityNatureMage.class, "naturemage", 0x1e8c00, 0x36ff00),
    		SlayerAPI.buildEntityEntry(EntityLeafBlower.class, "leafblower", 0x1e8c00, 0x36ff00),
    		SlayerAPI.buildEntityEntry(EntityWoodpecker.class, "woodpecker", 0x1e8c00, 0x36ff00),
    		//CLOUDIA
    		SlayerAPI.buildEntityEntry(EntityCloudGhost.class, "cloudghost", 0xa87abd, 0x9000ff),
    		SlayerAPI.buildEntityEntry(EntityCloudFlyer.class, "cloudflyer", 0xa87abd, 0x9000ff),
    		SlayerAPI.buildEntityEntry(EntityStarlightGolem.class, "starlightgolem", 0xa87abd, 0x9000ff),
    		SlayerAPI.buildEntityEntry(EntityStarlightTransporter.class, "starlighttransporter", 0xa87abd, 0x9000ff),
    		SlayerAPI.buildEntityEntry(EntityStarlightWalker.class, "starlightwalker", 0xa87abd, 0x9000ff),
    		SlayerAPI.buildEntityEntry(EntitySkyEel.class, "skyeel", 0xa87abd, 0x9000ff),
    		//NPC
    		SlayerAPI.buildEntityEntry(EntityStarlightVillager.class, "starlightvillager", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityStarlightBlacksmith.class, "starlightblacksmith", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityMage.class, "mage", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityBlacksmith.class, "blacksmith", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityFrozenMerchant.class, "frozenmerchant", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityEscapedConvict.class, "escapedconvict", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityStaringGuardian.class, "staringguardian", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityTordo.class, "tordo", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityRedTordo.class, "redtordo", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityBoilTrader.class, "boiltrader", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityAlloyMender.class, "alloymender", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityTerranianTrader.class, "terraniantrader", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityOvergrownMerchant.class, "overgrownmerchant", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityTerranianEnchanter.class, "terranianenchanter", 0x00FF8C, 0x00F6FF),
    		SlayerAPI.buildEntityEntry(EntityRockiteGolem.class, "rockitegolem", 0x00FF8C, 0x00F6FF),
    		//BOSS
    		SlayerAPI.buildEntityEntry(EntitySoulWatcher.class, "soulwatcher", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntityNetherBeast.class, "beastofthenether", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntityWitheringBeast.class, "witheringbeast", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntityCalcia.class, "calcia", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntityTempleGuardian.class, "templeguardian", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntityFourfa.class, "fourfa", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntityBlazier.class, "blazier", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntitySentryKing.class, "sentryking", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntityThunderbird.class, "thunderbird", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntityLogger.class, "logger", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntityCorallator.class, "corallator", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntitySkyStalker.class, "skystalker", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntityEudor.class, "eudor", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntityScale.class, "scale", 0x000000, 0x9B0000),
    		SlayerAPI.buildEntityEntry(EntityTerranianProtector.class, "terranianprotector", 0x000000, 0x9B0000),
    		//SlayerAPI.buildEntityEntry(EntityWraith.class, "wraith");
    		//Terrania
    		SlayerAPI.buildEntityEntry(EntityTerralight.class, "terralight", 0x7813ff, 0xff58f5),
    		SlayerAPI.buildEntityEntry(EntityTerraScatterer.class, "terrascatterer", 0x7813ff, 0xff58f5),
    		SlayerAPI.buildEntityEntry(EntityPurplian.class, "purplian", 0x7813ff, 0xff58f5),
    		SlayerAPI.buildEntityEntry(EntityTerraslug.class, "terraslug", 0x7813ff, 0xff58f5),
    		SlayerAPI.buildEntityEntry(EntityTerragrow.class, "terragrow", 0x7813ff, 0xff58f5),
    		SlayerAPI.buildEntityEntry(EntityTerrashroom.class, "terrashroom", 0x7813ff, 0xff58f5),
    		//OBSIDIANBOAT
    		SlayerAPI.buildEntityEntryNoEgg(EntityObsidianBoat.class, "obsidianboat", 16)
        };

        return JourneyMobs;
        }

}