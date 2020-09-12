package net.journey.init.items;

import net.journey.entity.projectile.*;
import net.journey.entity.projectile.arrow.EntityEssenceArrow;
import net.journey.entity.projectile.knife.*;
import net.journey.entity.projectile.launcher.EntityEyeBlaster;
import net.journey.entity.projectile.launcher.EntityNetherPlasma;
import net.journey.entity.projectile.launcher.EntityRock;
import net.journey.entity.projectile.piercer.*;
import net.journey.entity.projectile.staff.*;
import net.journey.entity.projectile.throwable.EntityDemonicBomb;
import net.journey.entity.projectile.throwable.EntityFireBomb;
import net.journey.entity.projectile.throwable.EntityMagicBomb;
import net.journey.entity.projectile.throwable.EntityMagicPot;
import net.journey.enums.EnumSwordType;
import net.journey.init.JourneyTabs;
import net.journey.init.Registrar;
import net.journey.items.bows.ItemModBow;
import net.journey.items.interactive.ItemTeleport;
import net.journey.items.ranged.*;
import net.journey.items.swords.ItemBattleAxe;
import net.journey.items.swords.ItemBubbleSword;
import net.journey.items.swords.ItemLoggersSword;
import net.journey.util.JourneyToolMaterial;
import net.minecraft.item.Item;
import net.slayer.api.item.ItemModSword;

import java.util.EnumSet;

public class JourneyWeapons {

    public static Item demonicBomb;
    public static Item fireBomb;
	public static Item boilingPiercer;
	public static Item nethicPiercer;
	public static Item frozenPiercer;
	public static Item eucaPiercer;
	public static Item depthsPiercer;
	public static Item corbaPiercer;
	public static Item frostbittenPiercer;
	public static Item frostyPiercer;
	public static Item sunsetPiercer;
	public static Item skyPiercer;
	public static Item magicPotOfDestruction;
	public static Item magicBomb;
	public static Item essenceShuriken;

	public static Item moltenKnife;
	public static Item aquaticKnife;
	public static Item bloodKnife;
	public static Item charredKnife;
	public static Item sizzlingKnife;

	public static Item flameBow;
	public static Item poisonBow;
	public static Item darknessBow;
    public static Item frozenBow;
    public static Item staringBow;
    public static Item deathPiercerBow;
    public static Item fusionBow;
    public static Item springBow;
    public static Item starlightBow;
    public static Item wastefulBow;
    public static Item flamingBow;
    public static Item blazingBow;
    public static Item darkEnforcer;
    public static Item depthsBow;
    public static Item frostbittenBow;
    public static Item frostyBow;
    public static Item charredBow;
    public static Item fluffyBow;
    public static Item golemBow;
    public static Item loggersBow;
    public static Item overgrownBow;
    public static Item overseerBow;
    public static Item woodlandBow;
    public static Item rocsWing;
    public static Item scaleBow;
    public static Item mantleBow;
    public static Item coreExpender;
    public static Item royalBow;
    public static Item darkTerraBow;
    public static Item lavenderBow;
    public static Item terralightBow;
    public static Item terrianBow;

    public static Item fireWand;
    public static Item iceWand;
    public static Item lightningWand;

    public static Item creativeHammer;
    public static Item earthenHammer;
    public static Item flamingHammer;
    public static Item nethicHammer;
    public static Item withicHammer;
    public static Item royalHammer;
    public static Item overgrownHammer;
    public static Item rockyHammer;
    public static Item crystallizedHammer;

    public static Item rockyBattleaxe;
    public static Item crystalizedBattleaxe;
    public static Item backBiter;
    public static Item dawnBreaker;
    public static Item tempestBattleaxe;
    public static Item bronzedBattleaxe;
    public static Item celestiteBattleaxe;
    public static Item storumBattleaxe;
    public static Item celekiumBattleaxe;
    public static Item thunderbirdBattleaxe;

    public static ItemStaff staffOfCrystal;
    public static ItemStaff staffOfDivineStone;
    public static ItemStaff staffOfHellstone;
    public static ItemStaff doomsBringer;
    public static ItemStaff conjuringStaff;
    public static ItemStaff staffOfEnlightenment;
    public static ItemStaff staffOfGreenpace;
    public static ItemStaff wizardsStar;
    public static Item teleportationStaff;
    public static ItemStaff overgrownStaff;

    public static Item chaosCannon;
    public static Item rockLauncher;
    public static Item netherPlasma;
    public static Item oceanPlasma;
    public static Item forestPlasma;
    public static Item eyeBlaster;

    public static Item hellstoneSword;
    public static Item shadiumSword;
    public static Item celestiumSword;
    public static Item luniumSword;
    public static Item flairiumSword;
    public static Item desSword;
    public static Item sapphireSword;
    public static Item gorbiteSword;
    public static Item orbaditeSword;
    public static Item poisonSword;
    public static Item cloudSlicer;
    public static Item dragonsTooth;
    public static Item netherBeastSword;
    public static Item witheringBeastSword;
    public static Item calciaSword;
    public static Item championsSword;
    public static Item theWraith;
    public static Item bubbleSword;
    public static Item boilingBlade;
    public static Item loggersSword;
    public static Item naturesBlade;
    public static Item depthsDarksword;
    public static Item depthsSlayer;
    public static Item snowShoveler;
    public static Item frostySword;
    public static Item frostbittenSword;
    public static Item treeHugger;
    public static Item coreMender;
    public static Item royalBlade;
    public static Item royalStabber;
    public static Item rocSword;
    public static Item swordOfTheThunderbird;
    public static Item bloodwieldSword;
    public static Item charredBlade;
    public static Item sizzlerSword;
    public static Item fluffyBlade;
    public static Item golemSword;
    public static Item thunderblade;
    public static Item sentrySword;
    public static Item crystalBlade;
    public static Item starlightBlade;
    public static Item koriteSword;
    public static Item storonSword;
    public static Item mekyumSword;
    public static Item pedalSword;
    public static Item withicBlade;
    public static Item reinforcedCrystalSword;
    public static Item reinforcedStoneSword;
    public static Item terralightBlade;
    public static Item terranaSword;
    public static Item terrolicaSword;
    public static Item voliteSword;
    public static Item kingsSword;
    public static Item demonicSword;
    public static Item vinestrandBlade;
    public static Item darkPineSword;
    public static Item healersBlade;
    public static Item terronicBlade;
    public static Item developerSword;

    public static void init() {
	    poisonSword = Registrar.regAndSetupItem("poisonSword", "Poison Sword", new ItemModSword(EnumSwordType.POISON, JourneyToolMaterial.POISON_SWORD), JourneyTabs.WEAPONS);
	    cloudSlicer = Registrar.regAndSetupItem("cloudSlicer", "Cloud Slicer", new ItemModSword(JourneyToolMaterial.CLOUD_SLICER), JourneyTabs.WEAPONS);
	    dragonsTooth = Registrar.regAndSetupItem("dragonsTooth", "Dragons Tooth", new ItemModSword(JourneyToolMaterial.DRAGONS_TOOTH), JourneyTabs.WEAPONS);
	    netherBeastSword = Registrar.regAndSetupItem("netherBeastSword", "Netherbeast Sword", new ItemModSword(EnumSwordType.REGEN, JourneyToolMaterial.NETHER_BEAST_SWORD), JourneyTabs.WEAPONS);
	    witheringBeastSword = Registrar.regAndSetupItem("witheringBeastSword", "Witheringbeast Sword", new ItemModSword(EnumSwordType.WITHER, JourneyToolMaterial.WITHERING_BEAST_SWORD), JourneyTabs.WEAPONS);
	    calciaSword = Registrar.regAndSetupItem("calciaSword", "Calcia Sword", new ItemModSword(JourneyToolMaterial.CALCIA_SWORD), JourneyTabs.WEAPONS);
	    championsSword = Registrar.regAndSetupItem("championsSword", "Champions Sword", new ItemModSword(JourneyToolMaterial.CHAMPIONS_SWORD), JourneyTabs.WEAPONS);
	    theWraith = Registrar.regAndSetupItem("theWraith", "The Wraith", new ItemModSword(JourneyToolMaterial.THE_WRAITH), JourneyTabs.WEAPONS);
	    bubbleSword = Registrar.regAndSetupItem("bubbleSword", "Bubble Sword", new ItemBubbleSword(JourneyToolMaterial.BUBBLE_SWORD), JourneyTabs.WEAPONS);
	    boilingBlade = Registrar.regAndSetupItem("boilingBlade", "Boiling Blade", new ItemModSword(EnumSwordType.FIRE, JourneyToolMaterial.BOILING_BLADE), JourneyTabs.WEAPONS);
	    loggersSword = Registrar.regAndSetupItem("loggersSword", "Loggers Sword", new ItemLoggersSword(JourneyToolMaterial.LOGGERS_SWORD), JourneyTabs.WEAPONS);
	    naturesBlade = Registrar.regAndSetupItem("naturesBlade", "Natures Blade", new ItemModSword(EnumSwordType.STUN_WITHER, JourneyToolMaterial.NATURES_BLADE), JourneyTabs.WEAPONS);
	    depthsDarksword = Registrar.regAndSetupItem("depthsDarksword", "Depths Darksword", new ItemModSword(EnumSwordType.STUN_WITHER, JourneyToolMaterial.DEPTHS_DARKSWORD), JourneyTabs.WEAPONS);
	    depthsSlayer = Registrar.regAndSetupItem("depthsSlayer", "Depths Slayer", new ItemModSword(EnumSwordType.STUN, JourneyToolMaterial.DEPTHS_SLAYER), JourneyTabs.WEAPONS);
	    snowShoveler = Registrar.regAndSetupItem("snowShoveler", "Snow Shoveler", new ItemModSword(EnumSwordType.STUN_WITHER, JourneyToolMaterial.SNOW_SHOVELER), JourneyTabs.WEAPONS);
	    frostySword = Registrar.regAndSetupItem("frostySword", "Frosty Sword", new ItemModSword(EnumSwordType.STUN, JourneyToolMaterial.FROSTY_SWORD), JourneyTabs.WEAPONS);
	    frostbittenSword = Registrar.regAndSetupItem("frostbittenSword", "Frostbitten Sword", new ItemModSword(EnumSwordType.STUN, JourneyToolMaterial.FROSTBITTEN_SWORD), JourneyTabs.WEAPONS);
	    treeHugger = Registrar.regAndSetupItem("treeHugger", "Tree Hugger", new ItemModSword(EnumSwordType.POISON, JourneyToolMaterial.TREE_HUGGER), JourneyTabs.WEAPONS);
	    coreMender = Registrar.regAndSetupItem("coreMender", "Core Mender", new ItemModSword(EnumSwordType.FIRE, JourneyToolMaterial.CORE_MENDER), JourneyTabs.WEAPONS);
	    royalBlade = Registrar.regAndSetupItem("royalBlade", "Royal Blade", new ItemModSword(EnumSwordType.POISON, JourneyToolMaterial.ROYAL_BLADE), JourneyTabs.WEAPONS);
	    royalStabber = Registrar.regAndSetupItem("royalStabber", "Royal Stabber", new ItemModSword(EnumSwordType.POISON, JourneyToolMaterial.ROYAL_STABBER), JourneyTabs.WEAPONS);
	    rocSword = Registrar.regAndSetupItem("rocSword", "Roc Sword", new ItemModSword(JourneyToolMaterial.ROC_SWORD), JourneyTabs.WEAPONS);
	    swordOfTheThunderbird = Registrar.regAndSetupItem("swordOfTheThunderbird", "Sword of the Thunderbird", new ItemModSword(JourneyToolMaterial.SWORD_THUNDERBIRD), JourneyTabs.WEAPONS);
	    bloodwieldSword = Registrar.regAndSetupItem("bloodWieldSword", "Blood Wielder", new ItemModSword(EnumSwordType.HEALTH, JourneyToolMaterial.BLOODWIELD_SWORD, 1), JourneyTabs.WEAPONS);
	    charredBlade = Registrar.regAndSetupItem("charredBlade", "Charred Blade", new ItemModSword(EnumSwordType.WITHER, JourneyToolMaterial.CHARRED_BLADE), JourneyTabs.WEAPONS);
	    sizzlerSword = Registrar.regAndSetupItem("sizzlerSword", "Sizzler Sword", new ItemModSword(EnumSwordType.FIRE_WITHER, JourneyToolMaterial.SIZZLER_SWORD), JourneyTabs.WEAPONS);
	    fluffyBlade = Registrar.regAndSetupItem("fluffyBlade", "Fluffy Blade", new ItemModSword(EnumSwordType.NIGHTVISION_HEALTH, JourneyToolMaterial.FLUFFY_BLADE), JourneyTabs.WEAPONS);
	    golemSword = Registrar.regAndSetupItem("golemSword", "Golem Sword", new ItemModSword(JourneyToolMaterial.GOLEM_SWORD), JourneyTabs.WEAPONS);
	    thunderblade = Registrar.regAndSetupItem("thunderblade", "Thunder Blade", new ItemModSword(EnumSwordType.POISON_HEALTH, JourneyToolMaterial.THUNDERBLADE, 1.5F), JourneyTabs.WEAPONS);
	    sentrySword = Registrar.regAndSetupItem("sentrySword", "Sentry Sword", new ItemModSword(EnumSwordType.FIRE_HEALTH, JourneyToolMaterial.SENTRY_SWORD, 2), JourneyTabs.WEAPONS);
	    crystalBlade = Registrar.regAndSetupItem("crystalBlade", "Crystal Blade", new ItemModSword(JourneyToolMaterial.CRYSTAL_BLADE), JourneyTabs.WEAPONS);
	    starlightBlade = Registrar.regAndSetupItem("starlightBlade", "Starlight Blade", new ItemModSword(JourneyToolMaterial.STARLIGHT_BLADE), JourneyTabs.WEAPONS);
	    koriteSword = Registrar.regAndSetupItem("koriteSword", "Korite Sword", new ItemModSword(JourneyToolMaterial.KORITE_SWORD), JourneyTabs.WEAPONS);
	    storonSword = Registrar.regAndSetupItem("storonSword", "Storon Sword", new ItemModSword(JourneyToolMaterial.STORON_SWORD), JourneyTabs.WEAPONS);
	    mekyumSword = Registrar.regAndSetupItem("mekyumSword", "Mekyum Sword", new ItemModSword(JourneyToolMaterial.MEKYUM_SWORD), JourneyTabs.WEAPONS);
	    hellstoneSword = Registrar.regAndSetupItem("hellstoneSword", "Bloodcrust Sword", new ItemModSword(EnumSwordType.FIRE, JourneyToolMaterial.HELLSTONE_SWORD), JourneyTabs.WEAPONS);
	    shadiumSword = Registrar.regAndSetupItem("shadiumSword", "Shadium Sword", new ItemModSword(EnumSwordType.FREEZE, JourneyToolMaterial.SHADIUM_SWORD), JourneyTabs.WEAPONS);
	    celestiumSword = Registrar.regAndSetupItem("celestiumSword", "Celestium Sword", new ItemModSword(JourneyToolMaterial.CELESTIUM_SWORD), JourneyTabs.WEAPONS);
	    luniumSword = Registrar.regAndSetupItem("luniumSword", "Lunium Sword", new ItemModSword(JourneyToolMaterial.LUNIUM_SWORD), JourneyTabs.WEAPONS);
	    flairiumSword = Registrar.regAndSetupItem("flairiumSword", "Flairium Sword", new ItemModSword(EnumSwordType.FIRE, JourneyToolMaterial.FLAIRIUM_SWORD), JourneyTabs.WEAPONS);
	    desSword = Registrar.regAndSetupItem("desSword", "Des Sword", new ItemModSword(JourneyToolMaterial.DES_SWORD), JourneyTabs.WEAPONS);
	    sapphireSword = Registrar.regAndSetupItem("sapphireSword", "Sapphire Sword", new ItemModSword(JourneyToolMaterial.SAPPHIRE_SWORD), JourneyTabs.WEAPONS);
	    gorbiteSword = Registrar.regAndSetupItem("gorbiteSword", "Gorbite Sword", new ItemModSword(JourneyToolMaterial.GORBITE_SWORD), JourneyTabs.WEAPONS);
	    orbaditeSword = Registrar.regAndSetupItem("orbaditeSword", "Orbadite Sword", new ItemModSword(JourneyToolMaterial.ORBADITE_SWORD), JourneyTabs.WEAPONS);

	    pedalSword = Registrar.regAndSetupItem("pedalSword", "Pedal Sword", new ItemModSword(JourneyToolMaterial.PEDAL_SWORD), JourneyTabs.WEAPONS);
	    withicBlade = Registrar.regAndSetupItem("withicBlade", "Withic Blade", new ItemModSword(JourneyToolMaterial.WITHIC_BLADE), JourneyTabs.WEAPONS);
	    reinforcedCrystalSword = Registrar.regAndSetupItem("reinforcedCrystalSword", "Reinforced Crystal Sword", new ItemModSword(JourneyToolMaterial.RE_CRYSTAL_SWORD), JourneyTabs.WEAPONS);
	    reinforcedStoneSword = Registrar.regAndSetupItem("reinforcedStoneSword", "Reinforced Stone Sword", new ItemModSword(JourneyToolMaterial.RE_STONE_SWORD), JourneyTabs.WEAPONS);
	    terralightBlade = Registrar.regAndSetupItem("terralightBlade", "Terralight Blade", new ItemModSword(JourneyToolMaterial.TERRALIGHT_BLADE), JourneyTabs.WEAPONS);
	    terranaSword = Registrar.regAndSetupItem("terranaSword", "Terrana Sword", new ItemModSword(JourneyToolMaterial.TERRANA_SWORD), JourneyTabs.WEAPONS);
	    terrolicaSword = Registrar.regAndSetupItem("terrolicaSword", "Terrolica Sword", new ItemModSword(EnumSwordType.NIGHTVISION, JourneyToolMaterial.TERROLICA_SWORD), JourneyTabs.WEAPONS);
	    voliteSword = Registrar.regAndSetupItem("voliteSword", "Volite Sword", new ItemModSword(JourneyToolMaterial.VOLITE_SWORD), JourneyTabs.WEAPONS);
	    kingsSword = Registrar.regAndSetupItem("kingsSword", "Kings Sword", new ItemModSword(EnumSwordType.FIRE_HEALTH, JourneyToolMaterial.KINGS_SWORD, 1), JourneyTabs.WEAPONS);
	    demonicSword = Registrar.regAndSetupItem("demonicSword", "Wither Sword", new ItemModSword(EnumSwordType.WITHER, JourneyToolMaterial.DEMONIC_SWORD), JourneyTabs.WEAPONS);
	    vinestrandBlade = Registrar.regAndSetupItem("vinestrandBlade", "Vinestrand Blade", new ItemModSword(EnumSwordType.POISON, JourneyToolMaterial.VINESTRAND_BLADE), JourneyTabs.WEAPONS);
	    darkPineSword = Registrar.regAndSetupItem("darkPineSword", "Dark Pine Sword", new ItemModSword(EnumSwordType.WITHER, JourneyToolMaterial.DARK_PINE_SWORD), JourneyTabs.WEAPONS);
	    healersBlade = Registrar.regAndSetupItem("healersBlade", "Healers Blade", new ItemModSword(EnumSwordType.HEALTH, JourneyToolMaterial.HEALERS_BLADE, 1), JourneyTabs.WEAPONS);
	    terronicBlade = Registrar.regAndSetupItem("terronicBlade", "Terronic Blade", new ItemModSword(EnumSwordType.HEALTH, JourneyToolMaterial.TERRONIC_BLADE, 2), JourneyTabs.WEAPONS);
	    developerSword = Registrar.regAndSetupItem("developerSword", "Creative Sword", new ItemModSword(JourneyToolMaterial.DEVELOPER_SWORD), JourneyTabs.UTIL);

	    rockyBattleaxe = Registrar.regAndSetupItem("rockyBattleaxe", "Rocky Battleaxe", new ItemBattleAxe(JourneyToolMaterial.ROCKY_BATTLEAXE), JourneyTabs.WEAPONS);
	    crystalizedBattleaxe = Registrar.regAndSetupItem("crystalizedBattleaxe", "Crystalized Battleaxe", new ItemBattleAxe(JourneyToolMaterial.CRYSTAL_BATTLEAXE), JourneyTabs.WEAPONS);
	    backBiter = Registrar.regAndSetupItem("backBiter", "Back Biter", new ItemBattleAxe(JourneyToolMaterial.BACK_BITER), JourneyTabs.WEAPONS);
	    dawnBreaker = Registrar.regAndSetupItem("dawnBreaker", "Dawn Breaker", new ItemBattleAxe(JourneyToolMaterial.DAWN_BREAKER), JourneyTabs.WEAPONS);
	    tempestBattleaxe = Registrar.regAndSetupItem("tempestBattleaxe", "Tempest Battleaxe", new ItemBattleAxe(JourneyToolMaterial.TEMPEST_BATTLEAXE), JourneyTabs.WEAPONS);
	    bronzedBattleaxe = Registrar.regAndSetupItem("bronzedBattleaxe", "Bronzed Battleaxe", new ItemBattleAxe(JourneyToolMaterial.BRONZED_BATTLEAXE), JourneyTabs.WEAPONS);
		celestiteBattleaxe = Registrar.regAndSetupItem("celestiteBattleaxe", "Celestite Battleaxe", new ItemBattleAxe(JourneyToolMaterial.CELESTITE_BATTLEAXE), JourneyTabs.WEAPONS);
		storumBattleaxe = Registrar.regAndSetupItem("storumBattleaxe", "Storum Battleaxe", new ItemBattleAxe(JourneyToolMaterial.STORUM_BATTLEAXE), JourneyTabs.WEAPONS);
		celekiumBattleaxe = Registrar.regAndSetupItem("celekiumBattleaxe", "Celekium Battleaxe", new ItemBattleAxe(JourneyToolMaterial.CELEKIUM_BATTLEAXE), JourneyTabs.WEAPONS);
		thunderbirdBattleaxe = Registrar.regAndSetupItem("thunderbirdBattleaxe", "Thunderbird Battleaxe", new ItemBattleAxe(JourneyToolMaterial.THUNDERBIRD_BATTLEAXE), JourneyTabs.WEAPONS);

		staffOfCrystal = Registrar.regAndSetupItem("staffOfCrystal", "Staff of Crystal", new ItemStaff(3, 1000, 10, false, EntityIceBall.class), JourneyTabs.WEAPONS);
		staffOfDivineStone = Registrar.regAndSetupItem("staffOfDivineStone", "Staff of Divine Stone", new ItemStaff(3, 1000, 9, false, EntityRock.class), JourneyTabs.WEAPONS);
		staffOfHellstone = Registrar.regAndSetupItem("staffOfHellstone", "Staff of Hellstone", new ItemStaff(3, 1000, 7, false, EntityHellstone.class), JourneyTabs.WEAPONS);
		doomsBringer = Registrar.regAndSetupItem("doomsBringer", "Dooms Bringer", new ItemStaff(3, 1000, 12, false, EntityDoomsBringer.class), JourneyTabs.WEAPONS);
		conjuringStaff = Registrar.regAndSetupItem("conjuringStaff", "Conjuring Staff", new ItemStaff(3, 1000, 18, false, EntityConjuring.class), JourneyTabs.WEAPONS);
		staffOfEnlightenment = Registrar.regAndSetupItem("staffOfEnlightenment", "Staff Of Enlightenment", new ItemStaff(3, 1000, 14, false, EntityEnlightenment.class), JourneyTabs.WEAPONS);
		staffOfGreenpace = Registrar.regAndSetupItem("staffOfGreenpace", "Staff Of Greenpace", new ItemStaff(3, 1000, 10, false, EntityGreenpace.class), JourneyTabs.WEAPONS);
		wizardsStar = Registrar.regAndSetupItem("wizardsStar", "Wizards Star", new ItemStaff(3, 1000, 5, false, EntityWizardsStar.class), JourneyTabs.WEAPONS);
		teleportationStaff = Registrar.regAndSetupItem("teleportationStaff", "Teleportation Staff", new ItemTeleport(), JourneyTabs.UTIL);
		overgrownStaff = Registrar.regAndSetupItem("overgrownStaff", "Overgrown Staff", new ItemStaff(3, 1000, 5, false, EntityOvergrown.class), JourneyTabs.WEAPONS);
		fireWand = Registrar.regAndSetupItem("fireWand", "Fire Wand", new ItemWand(true, 5, 600, 5, false, EntityFireBall.class), JourneyTabs.WEAPONS);
		iceWand = Registrar.regAndSetupItem("iceWand", "Ice Wand", new ItemWand(true, 5, 600, 5, false, EntityIceBall.class), JourneyTabs.WEAPONS);
		lightningWand = Registrar.regAndSetupItem("lightningWand", "Lightning Wand", new ItemWand(true, 10, 600, 5, false, EntityLightningBall.class), JourneyTabs.WEAPONS);

		//Overworld
		flameBow = Registrar.regAndSetupItem("flameBow", "Flame Bow", new ItemModBow(2, 384, EnumSet.of(EntityEssenceArrow.BowEffects.FLAME_BOW), 72000), JourneyTabs.WEAPONS);


		poisonBow = Registrar.regAndSetupItem("poisonBow", "Poison Bow", new ItemModBow(2, 384, EnumSet.of(EntityEssenceArrow.BowEffects.POISON_BOW), 72000), JourneyTabs.WEAPONS);
		darknessBow = Registrar.regAndSetupItem("darknessBow", "Darkness Bow", new ItemModBow(2, 384, EnumSet.of(EntityEssenceArrow.BowEffects.DARKNESS_BOW), 72000), JourneyTabs.WEAPONS);
		frozenBow = Registrar.regAndSetupItem("frozenBow", "Frozen Bow", new ItemModBow(2, 384, EnumSet.of(EntityEssenceArrow.BowEffects.FROZEN_BOW), 72000), JourneyTabs.WEAPONS);

		//Frozen
		frostbittenBow = Registrar.regAndSetupItem("frostbittenBow", "Frostbitten Bow", new ItemModBow(2.5F, 384, EnumSet.of(EntityEssenceArrow.BowEffects.FROZEN_BOW), 64000), JourneyTabs.WEAPONS);
		frostyBow = Registrar.regAndSetupItem("frostyBow", "Frosty Bow", new ItemModBow(2.5F, 384, EnumSet.of(EntityEssenceArrow.BowEffects.FROZEN_BOW), 64000), JourneyTabs.WEAPONS);

		//Nether
		staringBow = Registrar.regAndSetupItem("staringBow", "Staring Bow", new ItemModBow(2.5F, 384, EnumSet.of(EntityEssenceArrow.BowEffects.FLAME_BOW), 56000), JourneyTabs.WEAPONS);

		//Boil
		charredBow = Registrar.regAndSetupItem("charredBow", "Charred Bow", new ItemModBow(2.75F, 384, EnumSet.of(EntityEssenceArrow.BowEffects.DARKNESS_BOW), 64000), JourneyTabs.WEAPONS);
		flamingBow = Registrar.regAndSetupItem("flamingBow", "Flaming Bow", new ItemModBow(2.75F, 384, EnumSet.of(EntityEssenceArrow.BowEffects.FLAME_BOW), 64000), JourneyTabs.WEAPONS);
		blazingBow = Registrar.regAndSetupItem("blazingBow", "Blazing Bow", new ItemModBow(2.75F, 384, EnumSet.of(EntityEssenceArrow.BowEffects.FLAME_BOW), 56000), JourneyTabs.WEAPONS);

		//Euca
		mantleBow = Registrar.regAndSetupItem("mantleBow", "Mantle Bow", new ItemModBow(3.5F, 384, EnumSet.of(EntityEssenceArrow.BowEffects.FLAME_BOW), 48000), JourneyTabs.WEAPONS);
		coreExpender = Registrar.regAndSetupItem("coreExpender", "Core Expender", new ItemModBow(2.5F, 384, EnumSet.of(
				EntityEssenceArrow.BowEffects.ESSENCE_BOW,
				EntityEssenceArrow.BowEffects.FLAME_BOW), 72000), JourneyTabs.WEAPONS);
		royalBow = Registrar.regAndSetupItem("royalBow", "Royal Bow", new ItemModBow(3.0F, 384, EnumSet.of(EntityEssenceArrow.BowEffects.DARKNESS_BOW), 64000), JourneyTabs.WEAPONS);

		//Depths
		darkEnforcer = Registrar.regAndSetupItem("darkEnforcer", "Dark Enforcer", new ItemModBow(5.0F, 384, EnumSet.of(EntityEssenceArrow.BowEffects.ESSENCE_BOW), 64000), JourneyTabs.WEAPONS);
		depthsBow = Registrar.regAndSetupItem("depthsBow", "Depths Bow", new ItemModBow(4.0F, 384, EnumSet.of(EntityEssenceArrow.BowEffects.ESSENCE_BOW), 32000), JourneyTabs.WEAPONS);
		rocsWing = Registrar.regAndSetupItem("rocsWing", "Roc's Wing", new ItemModBow(4.0F, 384, EnumSet.of(EntityEssenceArrow.BowEffects.DARKNESS_BOW), 54000), JourneyTabs.WEAPONS);
		scaleBow = Registrar.regAndSetupItem("scaleBow", "Scale Bow", new ItemModBow(4.0F, 384, EnumSet.of(EntityEssenceArrow.BowEffects.FROZEN_BOW), 54000), JourneyTabs.WEAPONS);

		//Corba
		loggersBow = Registrar.regAndSetupItem("loggersBow", "Logger's Bow", new ItemModBow(4.5F, 384, EnumSet.of(EntityEssenceArrow.BowEffects.POISON_BOW), 48000), JourneyTabs.WEAPONS);
		overgrownBow = Registrar.regAndSetupItem("overgrownBow", "Overgrown Bow", new ItemModBow(4.5F, 384, EnumSet.of(EntityEssenceArrow.BowEffects.POISON_BOW), 54000), JourneyTabs.WEAPONS);
		overseerBow = Registrar.regAndSetupItem("overseerBow", "Overseer Bow", new ItemModBow(4.5F, 384, EnumSet.of(EntityEssenceArrow.BowEffects.DARKNESS_BOW), 38000), JourneyTabs.WEAPONS);
		woodlandBow = Registrar.regAndSetupItem("woodlandBow", "Woodland Bow", new ItemModBow(4.5F, 384, EnumSet.of(EntityEssenceArrow.BowEffects.POISON_BOW), 54000), JourneyTabs.WEAPONS);

		//Terrania
		darkTerraBow = Registrar.regAndSetupItem("darkTerraBow", "Dark Terra Bow", new ItemModBow(5.0F, 384, EnumSet.of(EntityEssenceArrow.BowEffects.DARKNESS_BOW), 48000), JourneyTabs.WEAPONS);
		lavenderBow = Registrar.regAndSetupItem("lavenderBow", "Lavender Bow", new ItemModBow(5.0F, 384, EnumSet.of(EntityEssenceArrow.BowEffects.FROZEN_BOW), 36000), JourneyTabs.WEAPONS);
		terralightBow = Registrar.regAndSetupItem("terralightBow", "Terralight Bow", new ItemModBow(5.0F, 384, EnumSet.of(EntityEssenceArrow.BowEffects.ESSENCE_BOW), 32000), JourneyTabs.WEAPONS);
		terrianBow = Registrar.regAndSetupItem("terrianBow", "Terrian Bow", new ItemModBow(5.0F, 384, EnumSet.of(EntityEssenceArrow.BowEffects.POISON_BOW), 40000), JourneyTabs.WEAPONS);

		//Cloudia
		starlightBow = Registrar.regAndSetupItem("starlightBow", "Starlight Bow", new ItemModBow(5.5F, 384, EnumSet.of(EntityEssenceArrow.BowEffects.POISON_BOW), 24000), JourneyTabs.WEAPONS);
		fluffyBow = Registrar.regAndSetupItem("fluffyBow", "Fluffy Bow", new ItemModBow(5.5F, 384, EnumSet.of(EntityEssenceArrow.BowEffects.FROZEN_BOW), 18000), JourneyTabs.WEAPONS);
		golemBow = Registrar.regAndSetupItem("golemBow", "Golem Bow", new ItemModBow(5.5F, 384, EnumSet.of(EntityEssenceArrow.BowEffects.DARKNESS_BOW), 36000), JourneyTabs.WEAPONS);

		//Unobtainable
		deathPiercerBow = Registrar.regAndSetupItem("deathPiercerBow", "Death Piercer", new ItemModBow(3, 384, EnumSet.of(EntityEssenceArrow.BowEffects.DARKNESS_BOW), 72000), JourneyTabs.WEAPONS);
		fusionBow = Registrar.regAndSetupItem("fusionBow", "Fusion Bow", new ItemModBow(3, 384, EnumSet.of(EntityEssenceArrow.BowEffects.DARKNESS_BOW), 72000), JourneyTabs.WEAPONS);
		springBow = Registrar.regAndSetupItem("springBow", "Spring Bow", new ItemModBow(5, 384, EnumSet.of(EntityEssenceArrow.BowEffects.FLAME_BOW), 72000), JourneyTabs.WEAPONS);
		wastefulBow = Registrar.regAndSetupItem("wastefulBow", "Wasteful Bow", new ItemModBow(3, 384, EnumSet.of(EntityEssenceArrow.BowEffects.DOUBLE_ARROW), 72000), JourneyTabs.WEAPONS);

		creativeHammer = Registrar.regAndSetupItem("creative_hammer", "Hammer of Creative Spellbinding", new ItemCreativeHammer(JourneyToolMaterial.CREATIVE, false, EntityLightningBall.class), JourneyTabs.WEAPONS);
		earthenHammer = Registrar.regAndSetupItem("earthen_hammer", "Earthen Hammer", new ItemHammer(JourneyToolMaterial.EARTHEN_HAMMER, false, EntityEarthen.class, 4, 4, 642), JourneyTabs.WEAPONS);
		flamingHammer = Registrar.regAndSetupItem("flaming_hammer", "Flaming Hammer", new ItemHammer(JourneyToolMaterial.FLAMING_HAMMER, false, EntityFireBall.class, 10, 4, 1230), JourneyTabs.WEAPONS);
		nethicHammer = Registrar.regAndSetupItem("nethic_hammer", "Nethic Hammer", new ItemHammer(JourneyToolMaterial.NETHIC_HAMMER, false, EntityFireBall.class, 8, 4, 825), JourneyTabs.WEAPONS);
		withicHammer = Registrar.regAndSetupItem("withic_hammer", "Withic Hammer", new ItemHammer(JourneyToolMaterial.WITHIC_HAMMER, false, EntityWithic.class, 9, 4, 1230), JourneyTabs.WEAPONS);
		royalHammer = Registrar.regAndSetupItem("royal_hammer", "Royal Hammer", new ItemHammer(JourneyToolMaterial.ROYAL_HAMMER, false, EntityFireBall.class, 12, 4, 1320), JourneyTabs.WEAPONS);
		overgrownHammer = Registrar.regAndSetupItem("overgrown_hammer", "Overgrown Hammer", new ItemHammer(JourneyToolMaterial.OVERGROWN_HAMMER, false, EntityOvergrown.class, 12, 4, 1320), JourneyTabs.WEAPONS);
		rockyHammer = Registrar.regAndSetupItem("rocky_hammer", "Rocky Hammer", new ItemHammer(JourneyToolMaterial.ROCKY_HAMMER, false, EntityRock.class, 6, 4, 2230), JourneyTabs.WEAPONS);
		crystallizedHammer = Registrar.regAndSetupItem("crystallized_hammer", "Crystallized Hammer", new ItemHammer(JourneyToolMaterial.CRYSTALLIZED_HAMMER, false, EntityIceBall.class, 7, 4, 3320), JourneyTabs.WEAPONS);

		chaosCannon = Registrar.regAndSetupItem("chaosCannon", "Chaos Cannon", new ItemGun(6, "Shoots a bouncing projectile", null), JourneyTabs.WEAPONS);
		rockLauncher = Registrar.regAndSetupItem("rockLauncher", "Rock Launcher", new ItemGun(4, "Stuns mobs for 10 seconds", EntityRock.class), JourneyTabs.WEAPONS);
		netherPlasma = Registrar.regAndSetupItem("netherPlasma", "Nether Plasma", new ItemGun(10, "Burns mobs for 10 seconds", EntityNetherPlasma.class), JourneyTabs.WEAPONS);
		oceanPlasma = Registrar.regAndSetupItem("oceanPlasma", "Ocean Plasma", new ItemGun(4, "Harms mobs", EntityFloroDirtProjectile.class), JourneyTabs.WEAPONS);
		forestPlasma = Registrar.regAndSetupItem("forestPlasma", "Forest Plasma", new ItemGun(4, "Poisons Mobs for 10 seconds", EntityOvergrown.class), JourneyTabs.WEAPONS);
		eyeBlaster = Registrar.regAndSetupItem("eyeBlaster", "Eye Blaster", new ItemGun(12, "Harms and burns mobs for 10 seconds", EntityEyeBlaster.class), JourneyTabs.WEAPONS);

		demonicBomb = Registrar.regAndSetupItem("demonicBomb", "Demonic Bomb", new ItemThrowable(4F, EntityDemonicBomb.class), JourneyTabs.WEAPONS);
		fireBomb = Registrar.regAndSetupItem("fireBomb", "Fire Bomb", new ItemThrowable(6F, EntityFireBomb.class), JourneyTabs.WEAPONS);

	    boilingPiercer = Registrar.regAndSetupItem("boilingPiercer", "Boiling Piercer", new ItemPiercer(15F, 4, EntityBoilingPiercer.class), JourneyTabs.WEAPONS);
	    nethicPiercer = Registrar.regAndSetupItem("nethicPiercer", "Nethic Piercer", new ItemPiercer(11F, 3, EntityNethicPiercer.class), JourneyTabs.WEAPONS);
	    frozenPiercer = Registrar.regAndSetupItem("frozenPiercer", "Frozen Piercer", new ItemPiercer(9F, 2, EntityFrozenPiercer.class), JourneyTabs.WEAPONS);
	    eucaPiercer = Registrar.regAndSetupItem("eucaPiercer", "Euca Piercer", new ItemPiercer(14F, 4, EntityEucaPiercer.class), JourneyTabs.WEAPONS);
	    depthsPiercer = Registrar.regAndSetupItem("depthsPiercer", "Depths Piercer", new ItemPiercer(18F, 5, EntityDepthsPiercer.class), JourneyTabs.WEAPONS);
	    corbaPiercer = Registrar.regAndSetupItem("corbaPiercer", "Corba Piercer", new ItemPiercer(20F, 6, EntityCorbaPiercer.class), JourneyTabs.WEAPONS);
	    frostbittenPiercer = Registrar.regAndSetupItem("frostbittenPiercer", "Frostbitten Piercer", new ItemPiercer(12F, 2, EntityFrostbittenPiercer.class), JourneyTabs.WEAPONS);
	    frostyPiercer = Registrar.regAndSetupItem("frostyPiercer", "Frosty Piercer", new ItemPiercer(10F, 2, EntityFrostyPiercer.class), JourneyTabs.WEAPONS);
	    sunsetPiercer = Registrar.regAndSetupItem("sunsetPiercer", "Sunset Piercer", new ItemPiercer(7F, 1, EntitySunsetPiercer.class), JourneyTabs.WEAPONS);
	    skyPiercer = Registrar.regAndSetupItem("skyPiercer", "Sky Piercer", new ItemPiercer(29F, 6, EntitySkyPiercer.class), JourneyTabs.WEAPONS);
	    essenceShuriken = Registrar.regAndSetupItem("essence_shuriken", "Essence Shuriken", new ItemEssenceShuriken(), JourneyTabs.WEAPONS);
	    magicPotOfDestruction = Registrar.regAndSetupItem("magic_pot_of_destruction", "Magic Pot of Destruction", new ItemMagicPot(4F, 4, EntityMagicPot.class), JourneyTabs.WEAPONS);

	    magicBomb = Registrar.regAndSetupItem("magic_bomb", "Magic Bomb", new ItemThrowableArrow(1D, EntityMagicBomb.class, "Sticks to surfaces, explodes upon collision"), JourneyTabs.WEAPONS);
	    moltenKnife = Registrar.regAndSetupItem("moltenKnife", "Molten Knife", new ItemThrowableArrow(3D, EntityMoltenKnife.class, "Englufs enemies in flames for 5 seconds"), JourneyTabs.WEAPONS);
	    aquaticKnife = Registrar.regAndSetupItem("aquaticKnife", "Aquatic Knife", new ItemThrowableArrow(2D, EntityAquaticKnife.class, "Slows enemies for 5 seconds"), JourneyTabs.WEAPONS);
	    bloodKnife = Registrar.regAndSetupItem("bloodKnife", "Blood Knife", new ItemThrowableArrow(3D, EntityBloodKnife.class, "Stuns enemies for 10 seconds"), JourneyTabs.WEAPONS);
	    charredKnife = Registrar.regAndSetupItem("charredKnife", "Charred Knife", new ItemThrowableArrow(2D, EntityCharredKnife.class, "Poisons and englufs enemies in flames for 5 seconds"), JourneyTabs.WEAPONS);
	    sizzlingKnife = Registrar.regAndSetupItem("sizzlingKnife", "Sizzling Knife", new ItemThrowableArrow(4D, EntitySizzlingKnife.class, "Stuns and englufs enemies in flames for 10 seconds"), JourneyTabs.WEAPONS);
    }
}