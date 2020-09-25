package net.journey.init.items;

import net.journey.dimension.base.DimensionHelper;
import net.journey.entity.mob.boss.*;
import net.journey.entity.mob.pet.EntityEucaHopper;
import net.journey.entity.mob.pet.EntityPetRobot;
import net.journey.entity.mob.pet.EntityShiverwolf;
import net.journey.entity.mob.pet.EntityTamedRoc;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.journey.init.JourneyTabs;
import net.journey.init.Registrar;
import net.journey.items.*;
import net.journey.items.base.JItem;
import net.journey.items.bauble.*;
import net.journey.items.bauble.buddies.ItemBuddySoul;
import net.journey.items.bauble.ring.*;
import net.journey.items.interactive.*;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.util.EnumHelper;

public class JourneyItems {

	public static final EntityEquipmentSlot HEAD = EntityEquipmentSlot.HEAD, BODY = EntityEquipmentSlot.CHEST, LEGS = EntityEquipmentSlot.LEGS, BOOTS = EntityEquipmentSlot.FEET;

	//TODO: make a new system. durability, damage, efficiency, etc. should be static values for each tool material, with an alogorithm that changes each value based on tool type.
	//overworld
	public static ToolMaterial shadiumSwordMat = addToolMaterial(2210, 13F, 8.5F, true);
	public static ToolMaterial luniumSwordMat = addToolMaterial(2210, 13F, 8.5F, true);
	public static ToolMaterial sapphiretoolSwordMat = addToolMaterial(1561, 13F, 8, true);

	//nether
	public static ToolMaterial hellstoneSwordMat = addToolMaterial(2356, 13F, 8.5F, true);

	//euca
	public static ToolMaterial mekyumSwordMat = addToolMaterial(3120, 13F, 11.5F, true);
    public static ToolMaterial storonSwordMat = addToolMaterial(3120, 13F, 11.5F, true);
    public static ToolMaterial koriteSwordMat = addToolMaterial(3120, 13F, 11.5F, true);
	public static ToolMaterial celestiumSwordMat = addToolMaterial(3120, 13F, 11.5F, true);

	//depths
	public static ToolMaterial flairiumSwordMat = addToolMaterial(3120, 14.5F, 12, true);
	public static ToolMaterial desSwordMat = addToolMaterial(3120, 15F, 13, true);

	//corba
	public static ToolMaterial gorbiteSwordMat = addToolMaterial(3120, 13F, 16.5F, true);
	public static ToolMaterial orbaditeSwordMat = addToolMaterial(3120, 13F, 16.5F, true);

	public static ToolMaterial sapphiretool = addToolMaterial(1461, 10F, 8F, true);
	public static ToolMaterial lunium = addToolMaterial(1490, 12F, 8.5F, true);
	public static ToolMaterial shadium = addToolMaterial(1521, 12F, 8.5F, true);
	public static ToolMaterial hellstone = addToolMaterial(1432, 15F, 8.5F, true);
	public static ToolMaterial nethic = addToolMaterial(481, 21F, 8.0F, true);
	public static ToolMaterial celestium = addToolMaterial(1621, 18F, 11.5F, true);
	public static ToolMaterial mekyum = addToolMaterial(1621, 18F, 11.5F, true);
	public static ToolMaterial storon = addToolMaterial(1621, 18F, 11.5F, true);
	public static ToolMaterial korite = addToolMaterial(1621, 18F, 11.5F, true);
	public static ToolMaterial flairium = addToolMaterial(2130, 21F, 14.5F, true);
	public static ToolMaterial des = addToolMaterial(2130, 21F, 13F, true);
	public static ToolMaterial orbadite = addToolMaterial(3142, 28F, 16.5F, true);
	public static ToolMaterial gorbite = addToolMaterial(3142, 28F, 16.5F, true);
	public static ToolMaterial hoeoflife = addToolMaterial(126, 28F, 5F, true);
	public static ToolMaterial hoeofearth = addToolMaterial(512, 28F, 7F, true);
	public static ToolMaterial hellstoneMulti = addToolMaterial(3000, 10F, 6.5F, false);
	public static ToolMaterial flairiumMulti = addToolMaterial(3000, 16F, 12.5F, false);
	public static ToolMaterial luniumMulti = addToolMaterial(3000, 7F, 6.5F, false);
	public static ToolMaterial celestiumMulti = addToolMaterial(3000, 13F, 9.5F, false);
	public static ToolMaterial mekyumMulti = addToolMaterial(3000, 13F, 9.5F, false);
	public static ToolMaterial storonMulti = addToolMaterial(3000, 13F, 9.5F, false);
	public static ToolMaterial koriteMulti = addToolMaterial(3000, 13F, 9.5F, false);
	public static ToolMaterial shadiumMulti = addToolMaterial(3000, 9F, 6.5F, false);
	public static ToolMaterial sapphireMulti = addToolMaterial(3000, 7F, 6.0F, false);
	public static ToolMaterial orbaditeMulti = addToolMaterial(3000, 20F, 14.5F, false);
	public static ToolMaterial gorbiteMulti = addToolMaterial(3000, 20F, 14.5F, false);
	public static ToolMaterial desMulti = addToolMaterial(3000, 16F, 11F, false);
	public static ToolMaterial smeltingMulti = addToolMaterial(3000, 8F, 5F, false);
	public static ToolMaterial SLIMY_PICK_MATERIAL = addToolMaterial(3000, 2F, 3F, true);
	public static ToolMaterial BEDROCK_SHOVEL_MATERIAL = addToolMaterial(1000, 4F, 5.5F, false);

	//TODO MATERIALS
	public static ToolMaterial woodMulti = addToolMaterial(60, 2F, 5F, false);
	public static ToolMaterial stoneMulti = addToolMaterial(132, 3F, 5F, false);
	public static ToolMaterial ironMulti = addToolMaterial(251, 4F, 5F, false);
	public static ToolMaterial goldMulti = addToolMaterial(33, 5F, 5F, false);
	public static ToolMaterial diamondMulti = addToolMaterial(1562, 6F, 5F, false);

	public static Item hellstoneIngot;
	public static Item shadiumIngot;
    public static Item celestiumIngot;
    public static Item mekyumIngot;
    public static Item koriteIngot;
    public static Item storonIngot;
    public static Item luniumIngot;
    public static Item flairiumIngot;
    public static Item ash;
	public static Item sapphire;
	public static Item blazium;
	public static Item enderilliumShard;
	public static Item orbaditeIngot;
	public static Item gorbiteGem;
	public static Item desIngot;
	public static Item darkGem;

	public static Item ancientEyeOfOpening;

	public static Item lamentDeconstructorNether;
	public static Item lamentDeconstructorBoil;
	public static Item lamentDeconstructorEuca;
	public static Item lamentDeconstructorDepths;
	public static Item lamentDeconstructorCorba;
	public static Item lamentDeconstructorTerrania;
	public static Item lamentDeconstructorCloudia;
	public static Item lamentDeconstructorSentry;
	public static Item sentryKey;

	public static Item sentryDisk;
	public static Item senterianSoul;
	public static Item sentryObserver;

	public static Item hatefulHeart;
	public static Item sentientPinionBlood;
	public static Item sentientPinionDestruction;
	public static Item sentientPinionLight;
	public static Item darkMatterGemstone;

	public static Item sentriumCrystal;
	public static Item melliumCrystal;

	public static Item sentryNodeBlood;
	public static Item sentryNodeDestruction;
	public static Item sentryNodeLight;
	public static Item sentryRock;

	public static Item obsidianRod;

	public static Item ancientPiece1;
	public static Item ancientPiece2;
	public static Item ancientPiece3;
	public static Item ancientPiece4;

	public static Item bileVial;

	public static Item bleedstone;
	public static Item smithstone;

	public static Item bleedstoneDust;
	public static Item smithstoneDust;

	public static Item soulstone;

	public static Item elderKey;
	public static Item boilPowder;
	public static Item blazingFireball;
	public static Item hellTurtleShell;
	public static Item sizzlingEye;

	public static Item sentryEye;
	public static Item boilingSkull;
	public static Item overgrownNatureBall;
    public static Item overseeingTablet;
    public static Item overseeingEye;
    public static Item darkCrystal;
    public static Item blankKnowledge;
    public static Item darkOrb;
    public static Item depthsFlake;
    public static Item beastlyStomach;
    public static Item rocFeather;
    public static Item coldClump;
    public static Item crystalEye;
    public static Item crystalFlake;
    public static Item freezingTablet;
    public static Item frostFlake;
    public static Item frostGem;
    public static Item frozenIceball;
    public static Item snowsheet;
    public static Item gateKeys;
    public static Item goldClump;
    public static Item silverClump;
	public static Item golderDust;
	public static Item shimmerDust;
	public static Item royalDisk;
    public static Item metalDisk;
    public static Item demonicDust;
    public static Item demonicBone;
    public static Item withicDust;
    public static Item cloudiaOrb;
    public static Item fluffyFeather;
    public static Item golemChunk;
    public static Item luniteChunk;
    public static Item corbaStick;
    public static Item spyclopseEye;
    public static Item caveCrystal;
    public static Item caveDust;
    public static Item stoneClump;
    public static Item enchantedLeaf;
    public static Item baseEssence;
    public static Item cloudPuff;
    public static Item collectorRock;
    public static Item natureTablet;
    public static Item horn;
    public static Item scale;
    public static Item reinforcedStoneIngot;
    public static Item reinforcedCrystalIngot;
    public static Item crystalBall;
    public static Item stoneStick;
    public static Item darkTerrarianSoil;
    public static Item earthenCrystal;
    public static Item lightTerrarianSoil;
    public static Item terrastar;
    public static Item purplePowder;
    public static Item hellShards;
	public static Item hellcrustIngot;
	public static Item flamingSprocket;
	public static Item flamingSpring;
	public static Item blood;
	public static Item concentratedBlood;
	public static Item snakeSkin;
	public static Item withicSpine;
	public static Item lostSoul;
	public static Item withicSoul;
	public static Item eucaPortalPiece;
	public static Item eucaPortalPiece1;
	public static Item eucaPortalPiece0;
	public static Item flamingHide;
	public static Item boilKey;
	public static Item darkKey;
	public static Item depthsKey;

	public static Item hellstoneDust;
	public static Item shadiumDust;
	public static Item celestiumDust;
	public static Item luniumDust;
	public static Item flairiumDust;
    public static Item ashDust;
    public static Item sapphireDust;
    public static Item enderilliumDust;
    public static Item gorbiteDust;
    public static Item orbaditeDust;
    public static Item diamondDust;
    public static Item goldDust;
    public static Item ironDust;

    public static Item hellstoneClump;
    public static Item shadiumClump;
    public static Item luniumClump;
    public static Item spawnerClump;
    public static Item spawnerBar;

    public static Item flameCoin;
    public static Item essenceDetractor;
    public static Item essenceAttractor;

    public static Item rockChunk;
    public static Item rockShard;
    public static Item slugSlime;

    public static Item eucaPortalGem;
    public static Item depthsPortalGem;
    public static Item corbaPortalGem;
    public static Item terraniaPortalGem;
    public static Item cloudiaPortalGem;
    //public static Item wastelandPortalGem;

    public static Item calciaOrb;
    public static Item netherBeastOrb;
    public static Item witheringBeastOrb;
    public static Item eudorOrb;
    public static Item blazierOrb;
    public static Item soulWatcherOrb;
    public static Item sentryKingOrb;
    public static Item loggerOrb;
    public static Item thunderbirdOrb;
    public static Item mysteriousDisk;
    public static Item corallatorOrb;
    public static Item scaleOrb;
    public static Item enchantedTerrastar;
    public static Item weakEssencePotion;
    public static Item strongEssencePotion;
    public static Item essenceArrow;
    public static Item tippedEssenceArrow;
    public static Item greenGem;
    public static Item purpleGem;
    public static Item blueGem;
    public static Item yellowGem;
    public static Item eucaTablet;
    public static Item wandBase;
    public static Item staffBase;
    public static Item frostyGift;
    public static Item firestoneClump;

    public static Item underwaterWorldRecord;
    public static Item blueWaterRecord;
    public static Item raceStarRecord;
    public static Item compBeginsRecord;
    public static Item deepBlueRecord;
	public static Item raceShoreRecord;
	public static Item untitledDisc;
	public static Item bogRecord;
    public static Item cloudsRecord;
    public static Item stalactiteRecord;
    public static Item cavernRecord;
    public static Item goldRecord;
	public static Item gateRecord;
	public static Item journeyRecord;
	public static Item sizzleRecord;
	public static Item fireflyRecord;
	public static Item snowbellsRecord;

	public static Item demonicEye;

	public static Item iridium;

	public static Item lootPouch;
	public static Item lootPouchGold;
	public static Item lootPouchDiamond;
	public static Item balmyTeardrop;
	public static Item charmOfVineStranding;
	public static Item charmOfWaterBending;
	public static Item cloudBuckler;
	public static Item cursedTome;
	public static Item deathCap;
	public static Item demonicSkull;
	public static Item essenceBuckler;
	public static Item essenceCatalyst;
	public static Item fieryStabber;
	public static Item glacialBlade;
	public static Item glossyEye;
	public static Item heartStone;
	public static Item lightBuckler;
	public static Item magicDust;
	public static Item bogshroomShelfSpore;

	public static Item moonOfEternalNight;
	public static Item nethicObstructor;
	public static Item slimeBerry;
	public static Item slimun;
	public static Item terraBuckler;
	public static Item withicObstructor;
	public static Item xpCatalyst;
	public static Item aquastone;
	public static Item pottery_shard;
	public static Item volcanicStone;
	public static Item chestKey;

	public static Item petFood;
	public static Item tamedRobotSpawnEgg;
	public static Item robotEgg;
	public static Item tamedEucaHopperSpawnEgg;
	public static Item eucaHopperEgg;
	public static Item tamedShiverwolfSpawnEgg;
	public static Item shiverwolfEgg;
	public static Item tamedRocSpawnEgg;
	public static Item rocEgg;

	public static ItemLoreScroll loreScroll;
	public static ItemTestBug debugItem;

	//public static Item obsidianBoat;

	//public static Item infernoshield;

	public static Item dynasterAmulet;
	public static Item aquaticAmulet;
	public static Item magmaAmulet;
	public static Item iceAmulet;
	public static Item emptyAmulet;
	public static Item skullOfDecay;
	public static Item minersPearl;
	public static Item magicThreadOfTethering;
	public static Item iceFlake;
	public static Item strengthTotem;
	public static Item heartContainerSmall;
	public static Item heartContainerMedium;
	public static Item heartContainerLarge;
	public static Item heartContainerUltimate;
	public static Item heartContainerSentry;

	public static Item blindnessRing;
	public static Item harmRing;
	public static Item miningFatigueRing;
	public static Item nauseaRing;
	public static Item poisonRing;
	public static Item slownessRing;
	public static Item witherRing;

	public static Item soulBuddy;

	public static Item luckyCharm;

	public static Item swampLily;

	public static Item sentacoin;
	public static Item sentacoinBag;

	public static void init() {
		hellstoneIngot = Registrar.regAndSetupItem("hellstoneIngot", "Hellstone Ingot", new JItem());
		shadiumIngot = Registrar.regAndSetupItem("shadiumIngot", "Shadium Ingot", new JItem());
		celestiumIngot = Registrar.regAndSetupItem("celestiumIngot", "Celestium Ingot", new JItem());
		mekyumIngot = Registrar.regAndSetupItem("mekyumIngot", "Mekyum Ingot", new JItem());
		koriteIngot = Registrar.regAndSetupItem("koriteIngot", "Korite Ingot", new JItem());
		storonIngot = Registrar.regAndSetupItem("storonIngot", "Storon Ingot", new JItem());
		luniumIngot = Registrar.regAndSetupItem("luniumIngot", "Lunium Ingot", new JItem());
		flairiumIngot = Registrar.regAndSetupItem("flairiumIngot", "Flairium Ingot", new JItem());
		ash = Registrar.regAndSetupItem("ash", "Ash", new JItem());
		sapphire = Registrar.regAndSetupItem("sapphire", "Sapphire Gem", new JItem());
		blazium = Registrar.regAndSetupItem("blazium", "Blazium Gem", new JItem());
		enderilliumShard = Registrar.regAndSetupItem("enderilliumShard", "Enderillium Shard", new JItem());
		orbaditeIngot = Registrar.regAndSetupItem("orbaditeIngot", "Orbadite Ingot", new JItem());
		gorbiteGem = Registrar.regAndSetupItem("gorbiteGem", "Gorbite Gem", new JItem());
		desIngot = Registrar.regAndSetupItem("desIngot", "Des Ingot", new JItem());
		bleedstone = Registrar.regAndSetupItem("bleedstone", "Bleedstone", new JItem());
		smithstone = Registrar.regAndSetupItem("smithstone", "Smithstone", new JItem());
		soulstone = Registrar.regAndSetupItem("soulstone", "Soulstone", new JItem());

		ancientPiece1 = Registrar.regAndSetupItem("ancientpiece_1", "Ancient Piece", new ItemAncientPiece());
		ancientPiece2 = Registrar.regAndSetupItem("ancientpiece_2", "Ancient Shard", new ItemAncientPiece());
		ancientPiece3 = Registrar.regAndSetupItem("ancientpiece_3", "Ancient Chunk", new ItemAncientPiece());
		ancientPiece4 = Registrar.regAndSetupItem("ancientpiece_4", "Ancient Fragment", new ItemAncientPiece());

		bileVial = Registrar.regAndSetupItem("bile_vial", "Bile Vial", new JItem());

		spawnerBar = Registrar.regAndSetupItem("spawnerBar", "Spawner Bar", new JItem());
		spawnerClump = Registrar.regAndSetupItem("spawnerClump", "Spawner Clump", new JItem());
		hellstoneClump = Registrar.regAndSetupItem("hellstoneClump", "Hellstone Clump", new JItem());
		shadiumClump = Registrar.regAndSetupItem("shadiumClump", "Shadium Clump", new JItem());
		luniumClump = Registrar.regAndSetupItem("luniumClump", "Lunium Clump", new JItem());
		elderKey = Registrar.regAndSetupItem("elderKey", "Elder Key", new JItem().setMaxStackSize(1));
		boilPowder = Registrar.regAndSetupItem("boilPowder", "Boiling Powder", new JItem());
		blazingFireball = Registrar.regAndSetupItem("blazingFireball", "Blazing Fireball", new JItem());
		hellTurtleShell = Registrar.regAndSetupItem("hellTurtleShell", "Hell Turtle Shell", new JItem());
		sizzlingEye = Registrar.regAndSetupItem("sizzlingEye", "Sizzling Eye", new JItem());
		sentryEye = Registrar.regAndSetupItem("sentryEye", "Sentry Eye", new ItemSentryEye());
		boilingSkull = Registrar.regAndSetupItem("boilingskull", "Boiling Skull", new JItem());
		overgrownNatureBall = Registrar.regAndSetupItem("overgrownNatureBall", "Overgrown Natureball", new JItem());
		overseeingTablet = Registrar.regAndSetupItem("overseeingTablet", "Overseeing Tablet", new JItem());
		overseeingEye = Registrar.regAndSetupItem("overseeingEye", "Overseeing Eye", new JItem());
		darkCrystal = Registrar.regAndSetupItem("darkCrystal", "Dark Crystal", new JItem());
		darkOrb = Registrar.regAndSetupItem("darkOrb", "Dark Orb", new JItem());
		depthsFlake = Registrar.regAndSetupItem("depthsFlake", "Depths Flake", new JItem());
		beastlyStomach = Registrar.regAndSetupItem("beastlyStomach", "Beastly Stomach", new JItem());
		rocFeather = Registrar.regAndSetupItem("rocFeather", "Essence Feather", new JItem());
		coldClump = Registrar.regAndSetupItem("coldClump", "Cold Clump", new JItem());
		crystalEye = Registrar.regAndSetupItem("crystalEye", "Crystal Eye", new JItem());
		crystalFlake = Registrar.regAndSetupItem("crystalFlake", "Crystal Flake", new JItem());
		freezingTablet = Registrar.regAndSetupItem("freezingTablet", "Freezing Tablet", new JItem());
		frostFlake = Registrar.regAndSetupItem("frostFlake", "Frost Flake", new JItem());
		frostGem = Registrar.regAndSetupItem("frostGem", "Frost Gem", new JItem());
		frozenIceball = Registrar.regAndSetupItem("frozenIceball", "Frozen Iceball", new JItem());
		snowsheet = Registrar.regAndSetupItem("snowsheet", "Snowsheet", new JItem());
		gateKeys = Registrar.regAndSetupItem("gateKeys", "Gate Keys", new JItem());
		goldClump = Registrar.regAndSetupItem("goldClump", "Gold Clump", new JItem());
		silverClump = Registrar.regAndSetupItem("silverClump", "Silver Clump", new JItem());
		golderDust = Registrar.regAndSetupItem("golderDust", "Golder Dust", new JItem());
		shimmerDust = Registrar.regAndSetupItem("shimmerdust", "Shimmer Dust", new JItem());
		royalDisk = Registrar.regAndSetupItem("royalDisk", "Royal Disk", new JItem());
		metalDisk = Registrar.regAndSetupItem("metalDisk", "Metal Disk", new JItem());
		demonicDust = Registrar.regAndSetupItem("demonicDust", "Demonic Dust", new JItem());
		demonicBone = Registrar.regAndSetupItem("demonicBone", "Demonic Bone", new JItem());
		withicDust = Registrar.regAndSetupItem("withicDust", "Withic Dust", new JItem());
		cloudiaOrb = Registrar.regAndSetupItem("cloudiaOrb", "Cloudia Orb", new JItem());
		fluffyFeather = Registrar.regAndSetupItem("fluffyFeather", "Fluffy Feather", new JItem());
		golemChunk = Registrar.regAndSetupItem("golemChunk", "Golem Chunk", new JItem());
		luniteChunk = Registrar.regAndSetupItem("luniteChunk", "Lunite Chunk", new JItem());
		corbaStick = Registrar.regAndSetupItem("corbaStick", "Corba Stick", new JItem());
		spyclopseEye = Registrar.regAndSetupItem("spyclopseEye", "Spyclopse Eye", new JItem());
		caveCrystal = Registrar.regAndSetupItem("caveCrystal", "Cave Crystal", new JItem());
		caveDust = Registrar.regAndSetupItem("caveDust", "Cave Dust", new JItem());
		stoneClump = Registrar.regAndSetupItem("stoneClump", "Stone Clump", new JItem());
		enchantedLeaf = Registrar.regAndSetupItem("enchantedLeaf", "Enchanted Leaf", new JItem());
		baseEssence = Registrar.regAndSetupItem("baseEssence", "Base Essence", new JItem());
		cloudPuff = Registrar.regAndSetupItem("cloudPuff", "Cloud Puff", new JItem());
		collectorRock = Registrar.regAndSetupItem("collectorRock", "Collector Rock", new JItem());
		natureTablet = Registrar.regAndSetupItem("natureTablet", "Nature Tablet", new JItem());
		horn = Registrar.regAndSetupItem("horn", "Horn", new JItem());
		scale = Registrar.regAndSetupItem("scale", "Scale", new JItem());
		reinforcedStoneIngot = Registrar.regAndSetupItem("reinforcedStoneIngot", "Reinforced Stone Ingot", new JItem());
		reinforcedCrystalIngot = Registrar.regAndSetupItem("reinforcedCrystalIngot", "Reinforced Crystal Ingot", new JItem());
		crystalBall = Registrar.regAndSetupItem("crystalBall", "Crystal Ball", new JItem());
		stoneStick = Registrar.regAndSetupItem("stoneStick", "Stone Stick", new JItem());
		darkTerrarianSoil = Registrar.regAndSetupItem("darkTerrarianSoil", "Dark Terrarian Soil", new JItem());
		earthenCrystal = Registrar.regAndSetupItem("earthenCrystal", "Earthen Crystal", new JItem());
		lightTerrarianSoil = Registrar.regAndSetupItem("lightTerrarianSoil", "Light Terrarian Soil", new JItem());
		terrastar = Registrar.regAndSetupItem("terrastar", "Terrastar", new JItem());
		purplePowder = Registrar.regAndSetupItem("purplePowder", "Purple Powder", new JItem());
		hellShards = Registrar.regAndSetupItem("hellShards", "Hell Shards", new JItem());
		hellcrustIngot = Registrar.regAndSetupItem("hellcrustIngot", "Hellcrust Ingot", new JItem());
		flamingSprocket = Registrar.regAndSetupItem("flamingSprocket", "Flaming Sprocket", new JItem());
		flamingSpring = Registrar.regAndSetupItem("flamingSpring", "Flaming Spring", new JItem());
		blood = Registrar.regAndSetupItem("blood", "Blood", new JItem());
		concentratedBlood = Registrar.regAndSetupItem("concentratedBlood", "Concentrated Blood", new JItem());
		snakeSkin = Registrar.regAndSetupItem("snakeSkin", "Snake Skin", new JItem());
		withicSpine = Registrar.regAndSetupItem("withicSpine", "Withic Spine", new JItem());
		lostSoul = Registrar.regAndSetupItem("lostSoul", "Lost Soul", new JItem());
		withicSoul = Registrar.regAndSetupItem("withicSoul", "Withic Soul", new JItem());
		eucaPortalPiece = Registrar.regAndSetupItem("eucaPortalPiece", "Euca Portal Piece", new JItem());
		eucaPortalPiece1 = Registrar.regAndSetupItem("eucaPortalPiece_1", "Euca Portal Piece", new JItem());
		eucaPortalPiece0 = Registrar.regAndSetupItem("eucaPortalPiece_0", "Euca Portal Piece", new JItem());
		flamingHide = Registrar.regAndSetupItem("flamingHide", "Flaming Hide", new JItem());
		boilKey = Registrar.regAndSetupItem("boilKey", "Boil Key", new JItem().setMaxStackSize(1).setMaxDamage(15));
		darkKey = Registrar.regAndSetupItem("darkkey", "Dark Key", new JItem().setMaxStackSize(1));
		depthsKey = Registrar.regAndSetupItem("depthskey", "Depths Key", new JItem().setMaxStackSize(1));

		slugSlime = Registrar.regAndSetupItem("slugSlime", "Slug Slime", new JItem());
		eucaPortalGem = Registrar.regAndSetupItem("eucaPortalGem", "Euca Portal Gem", new JItem());
		depthsPortalGem = Registrar.regAndSetupItem("depthsPortalGem", "Depths Portal Gem", new JItem());
		corbaPortalGem = Registrar.regAndSetupItem("corbaPortalGem", "Corba Portal Gem", new JItem());
		terraniaPortalGem = Registrar.regAndSetupItem("terraniaPortalGem", "Terrania Portal Gem", new JItem());
		cloudiaPortalGem = Registrar.regAndSetupItem("cloudiaPortalGem", "Cloudia Portal Gem", new JItem());

		greenGem = Registrar.regAndSetupItem("greenGem", "Green Gem", new JItem());
		purpleGem = Registrar.regAndSetupItem("purpleGem", "Purple Gem", new JItem());
		blueGem = Registrar.regAndSetupItem("blueGem", "Blue Gem", new JItem());
		yellowGem = Registrar.regAndSetupItem("yellowGem", "Yellow Gem", new JItem());
		eucaTablet = Registrar.regAndSetupItem("eucaTablet", "Euca Tablet", new JItem());
		wandBase = Registrar.regAndSetupItem("wandBase", "Wand Base", new JItem());
		staffBase = Registrar.regAndSetupItem("staffBase", "Staff Base", new JItem());
		firestoneClump = Registrar.regAndSetupItem("firestoneClump", "Firestone Clump", new JItem());
		iridium = Registrar.regAndSetupItem("iridium", "Iridium", new JItem());
		hellstoneDust = Registrar.regAndSetupItem("hellstoneDust", "hellstone Dust", new JItem());
		shadiumDust = Registrar.regAndSetupItem("shadiumDust", "Shadium Dust", new JItem());
		celestiumDust = Registrar.regAndSetupItem("celestiumDust", "Celestium Dust", new JItem());
		luniumDust = Registrar.regAndSetupItem("luniumDust", "Lunium Dust", new JItem());
		flairiumDust = Registrar.regAndSetupItem("flairiumDust", "Flairium Dust", new JItem());
		ashDust = Registrar.regAndSetupItem("ashDust", "Ash Dust", new JItem());
		sapphireDust = Registrar.regAndSetupItem("sapphireDust", "Sapphire Dust", new JItem());
		enderilliumDust = Registrar.regAndSetupItem("enderilliumDust", "Enderillium Dust", new JItem());
		gorbiteDust = Registrar.regAndSetupItem("gorbiteDust", "Gorbite Dust", new JItem());
		orbaditeDust = Registrar.regAndSetupItem("orbaditeDust", "Orbadite Dust", new JItem());
		diamondDust = Registrar.regAndSetupItem("diamondDust", "Diamond Dust", new JItem());
		goldDust = Registrar.regAndSetupItem("goldDust", "Gold Dust", new JItem());
		goldDust = Registrar.regAndSetupItem("ironDust", "Iron Dust", new JItem());
		bleedstoneDust = Registrar.regAndSetupItem("bleedstonedust", "Bleedstone Dust", new JItem());
		smithstoneDust = Registrar.regAndSetupItem("smithstonedust", "Smithstone Dust", new JItem());

		frostyGift = Registrar.regAndSetupItem("frostyGift", "Frosty Gift", new ItemLoot(JourneyLootTables.LOOT_PRESENT), JourneyTabs.UTIL);
		lootPouch = Registrar.regAndSetupItem("loot_pouch", "Loot Pouch", new ItemLoot(JourneyLootTables.LOOT_BASIC).setTier(0), JourneyTabs.UTIL);
		lootPouchGold = Registrar.regAndSetupItem("loot_pouch_gold", "Loot Pouch", new ItemLoot(JourneyLootTables.LOOT_GOLD).setTier(1), JourneyTabs.UTIL);
		lootPouchDiamond = Registrar.regAndSetupItem("loot_pouch_diamond", "Loot Pouch", new ItemLoot(JourneyLootTables.LOOT_DIAMOND).setTier(2), JourneyTabs.UTIL);

		flameCoin = Registrar.regAndSetupItem("flameCoin", "Flame Coin", new ItemFlameCoin(), JourneyTabs.UTIL);

		calciaOrb = Registrar.regAndSetupItem("calciaOrb", "Calcia Orb", new ItemBossSpawner(() -> DimensionType.NETHER, EntityCalcia.class).setBossSummonMessage("msg.journey.summon.calcia"), JourneyTabs.SPAWNERS);
		netherBeastOrb = Registrar.regAndSetupItem("netherBeastOrb", "Nether Beast Orb", new ItemBossSpawner(() -> DimensionType.NETHER, EntityNetherBeast.class).setBossSummonMessage("msg.journey.summon.nether_beast"), JourneyTabs.SPAWNERS);
		witheringBeastOrb = Registrar.regAndSetupItem("witheringBeastOrb", "Withering Soul", new ItemBossSpawner(() -> DimensionType.NETHER, EntityWitheringBeast.class).setBossSummonMessage("msg.journey.summon.withering_beast"), JourneyTabs.SPAWNERS);
		eudorOrb = Registrar.regAndSetupItem("eudorOrb", "Valuable Crown", new ItemBossSpawner(() -> DimensionHelper.EUCA_DIM, EntityEudor.class).setBossSummonMessage("msg.journey.summon.eudor"), JourneyTabs.SPAWNERS);
		blazierOrb = Registrar.regAndSetupItem("blazierOrb", "Burning Fireball", new ItemBossSpawner(() -> DimensionHelper.BOILING_DIM, EntityBlazier.class).setBossSummonMessage("msg.journey.summon.blazier"), JourneyTabs.SPAWNERS);
		soulWatcherOrb = Registrar.regAndSetupItem("soulWatcherOrb", "Soulless Eye", new ItemBossSpawner(() -> DimensionType.NETHER, EntitySoulWatcher.class).setBossSummonMessage("msg.journey.summon.soul_watcher"), JourneyTabs.SPAWNERS);
		sentryKingOrb = Registrar.regAndSetupItem("sentryKingOrb", "Eye of the Sentry", new ItemBossSpawner(() -> DimensionHelper.CORBA_DIM, EntitySentryKing.class).setBossSummonMessage("msg.journey.summon.sentry_king"), JourneyTabs.SPAWNERS);
		loggerOrb = Registrar.regAndSetupItem("loggerOrb", "Enchanted Log", new ItemBossSpawner(() -> DimensionHelper.CORBA_DIM, EntityLogger.class).setBossSummonMessage("msg.journey.summon.logger"), JourneyTabs.SPAWNERS);
		thunderbirdOrb = Registrar.regAndSetupItem("thunderbirdOrb", "Leader's Pearl", new ItemBossSpawner(() -> DimensionHelper.DEPTHS_DIM, EntityThunderbird.class).setBossSummonMessage("msg.journey.summon.thunderbird"), JourneyTabs.SPAWNERS);
		mysteriousDisk = Registrar.regAndSetupItem("mysteriousDisk", "Mysterious Disk", new ItemBossSpawner(() -> DimensionHelper.CLOUDIA_DIM, EntitySkyStalker.class).setBossSummonMessage("msg.journey.summon.sky_stalker"), JourneyTabs.SPAWNERS);
		corallatorOrb = Registrar.regAndSetupItem("corallatorOrb", "Gem of Peculiar Smelting", new ItemBossSpawner(() -> DimensionHelper.EUCA_DIM, EntityCorallator.class).setBossSummonMessage("msg.journey.summon.corallator"), JourneyTabs.SPAWNERS);
		scaleOrb = Registrar.regAndSetupItem("scaleOrb", "Aquatic Egg", new ItemBossSpawner(() -> DimensionHelper.DEPTHS_DIM, EntityScale.class).setBossSummonMessage("msg.journey.summon.scale"), JourneyTabs.SPAWNERS);
		enchantedTerrastar = Registrar.regAndSetupItem("enchantedTerrastar", "Enchanted Terrastar", new ItemBossSpawner(() -> DimensionHelper.TERRANIA_DIM, EntityTerranianProtector.class).setBossSummonMessage("msg.journey.summon.terranian_protector"), JourneyTabs.SPAWNERS);

		weakEssencePotion = Registrar.regAndSetupItem("weakEssencePotion", "Weak Essence Potion", new ItemEssencePotion(false), JourneyTabs.UTIL);
		strongEssencePotion = Registrar.regAndSetupItem("strongEssencePotion", "Strong Essence Potion", new ItemEssencePotion(true), JourneyTabs.UTIL);
		essenceArrow = Registrar.regAndSetupItem("essenceArrow", "Essence Arrow", new ItemEssenceArrow(), JourneyTabs.WEAPONS);

		underwaterWorldRecord = Registrar.regAndSetupItem("underwaterWorldRecord", "Underwater World", new ItemModRecord(JourneySounds.UNDERWATER_WORLD), JourneyTabs.UTIL);
		blueWaterRecord = Registrar.regAndSetupItem("blueWaterRecord", "Blue Water", new ItemModRecord(JourneySounds.BLUE_WATER), JourneyTabs.UTIL);
		raceStarRecord = Registrar.regAndSetupItem("raceStarRecord", "Race Star", new ItemModRecord(JourneySounds.RACE_STAR), JourneyTabs.UTIL);
		compBeginsRecord = Registrar.regAndSetupItem("compBeginsRecord", "Competition Begins", new ItemModRecord(JourneySounds.COMPETITION_BEGINS), JourneyTabs.UTIL);
		deepBlueRecord = Registrar.regAndSetupItem("deepBlueRecord", "Deep Blue", new ItemModRecord(JourneySounds.DEEP_BLUE), JourneyTabs.UTIL);
		raceShoreRecord = Registrar.regAndSetupItem("raceShoreRecord", "Race to Shore", new ItemModRecord(JourneySounds.RACE_TO_SHORE), JourneyTabs.UTIL);
		untitledDisc = Registrar.regAndSetupItem("untitled_discRecord", "untitled_disc", new ItemModRecord(JourneySounds.UNTITLED_DISC), JourneyTabs.UTIL);
		bogRecord = Registrar.regAndSetupItem("bogRecord", "Bog", new ItemModRecord(JourneySounds.CORBA_1), JourneyTabs.UTIL);
		cloudsRecord = Registrar.regAndSetupItem("cloudsRecord", "Clouds", new ItemModRecord(JourneySounds.CLOUDIA_1), JourneyTabs.UTIL);
		stalactiteRecord = Registrar.regAndSetupItem("stalactiteRecord", "Stalactite", new ItemModRecord(JourneySounds.DEPTHS_1), JourneyTabs.UTIL);
		cavernRecord = Registrar.regAndSetupItem("cavernRecord", "Cavern", new ItemModRecord(JourneySounds.DEPTHS_2), JourneyTabs.UTIL);
		goldRecord = Registrar.regAndSetupItem("goldRecord", "Gold", new ItemModRecord(JourneySounds.EUCA_1), JourneyTabs.UTIL);
		gateRecord = Registrar.regAndSetupItem("gateRecord", "Gate", new ItemModRecord(JourneySounds.EUCA_2), JourneyTabs.UTIL);
		journeyRecord = Registrar.regAndSetupItem("journeyRecord", "Journey Into the Light", new ItemModRecord(JourneySounds.EUCA_3), JourneyTabs.UTIL);
		sizzleRecord = Registrar.regAndSetupItem("sizzleRecord", "Sizzle", new ItemModRecord(JourneySounds.BOIL_1), JourneyTabs.UTIL);
		fireflyRecord = Registrar.regAndSetupItem("fireflyRecord", "Firefly", new ItemModRecord(JourneySounds.TERRANIA_1), JourneyTabs.UTIL);
		snowbellsRecord = Registrar.regAndSetupItem("snowbellsRecord", "Snow Bells", new ItemModRecord(JourneySounds.FROZEN_1), JourneyTabs.UTIL);

		demonicEye = Registrar.regAndSetupItem("demonicEye", "Demonic Eye", new ItemDemonicEye());
		darkGem = Registrar.regAndSetupItem("darkGem", "Dark Gem", new ItemDarkGem());
		ancientEyeOfOpening = Registrar.regAndSetupItem("ancient_eye_of_opening", "Ancient Eye of Opening", new ItemAncientCatalyst());

		lamentDeconstructorNether = Registrar.regAndSetupItem("lament_deconstructor_nether", "Lament Deconstructor of Hell", new JItem());
		lamentDeconstructorBoil = Registrar.regAndSetupItem("lament_deconstructor_boil", "Lament Deconstructor of Fire", new JItem());
		lamentDeconstructorEuca = Registrar.regAndSetupItem("lament_deconstructor_euca", "Lament Deconstructor of Light", new JItem());
		lamentDeconstructorDepths = Registrar.regAndSetupItem("lament_deconstructor_depths", "Lament Deconstructor of Darkness", new JItem());
		lamentDeconstructorCorba = Registrar.regAndSetupItem("lament_deconstructor_corba", "Lament Deconstructor of Decay", new JItem());
		lamentDeconstructorTerrania = Registrar.regAndSetupItem("lament_deconstructor_terrania", "Lament Deconstructor of Life", new JItem());
		lamentDeconstructorCloudia = Registrar.regAndSetupItem("lament_deconstructor_cloudia", "Lament Deconstructor of Heaven", new JItem());
		lamentDeconstructorSentry = Registrar.regAndSetupItem("lament_deconstructor_sentry", "Lament Deconstructor of Death", new JItem());
		sentryKey = Registrar.regAndSetupItem("sentry_key", "Sentry Key", new JItem());

		sentryDisk = Registrar.regAndSetupItem("sentry_disk", "Sentry Disk", new JItem());
		senterianSoul = Registrar.regAndSetupItem("senterian_soul", "Senterian Soul", new JItem());
		sentryObserver = Registrar.regAndSetupItem("sentry_observer", "Sentry Observer", new JItem());

		hatefulHeart = Registrar.regAndSetupItem("hateful_heart", "Hateful Heart", new JItem());
		darkMatterGemstone = Registrar.regAndSetupItem("dark_matter_gemstone", "Dark Matter Gemstone", new JItem());
		sentientPinionBlood = Registrar.regAndSetupItem("sentient_pinion_blood", "Sentient Pinion of Blood", new JItem());
		sentientPinionDestruction = Registrar.regAndSetupItem("sentient_pinion_destruction", "Sentient Pinion of Destruction", new JItem());
		sentientPinionLight = Registrar.regAndSetupItem("sentient_pinion_light", "Sentient Pinion of Light", new JItem());

		sentriumCrystal = Registrar.regAndSetupItem("sentrium_crystal", "Sentrium Crystal", new JItem());
		melliumCrystal = Registrar.regAndSetupItem("mellium_crystal", "Mellium Crystal", new JItem());
		sentryRock = Registrar.regAndSetupItem("sentry_rock", "Sentry Rock", new JItem());

		sentryNodeBlood = Registrar.regAndSetupItem("sentry_node_blood", "Sentry Node of Blood", new JItem());
		sentryNodeDestruction = Registrar.regAndSetupItem("sentry_node_destruction", "Sentry Node of Destruction", new JItem());
		sentryNodeLight = Registrar.regAndSetupItem("sentry_node_light", "Sentient Node of Light", new JItem());

		obsidianRod = Registrar.regAndSetupItem("obsidianRod", "Obsidian Rod", new JItem());

		balmyTeardrop = Registrar.regAndSetupItem("balmy_teardrop", "Balmy Teardrop", new JItem());

		cloudBuckler = Registrar.regAndSetupItem("cloud_buckler", "Cloud Buckler", new JItem());
		cursedTome = Registrar.regAndSetupItem("cursed_tome", "Cursed Tome", new ItemCursedTomb(), JourneyTabs.WEAPONS);
		deathCap = Registrar.regAndSetupItem("death_cap", "Death Cap", new ItemDeathCap(), JourneyTabs.UTIL);
		demonicSkull = Registrar.regAndSetupItem("demonic_skull", "Demonic Skull", new JItem());
		essenceBuckler = Registrar.regAndSetupItem("essence_buckler", "Essence Buckler", new JItem());
		essenceCatalyst = Registrar.regAndSetupItem("essence_catalyst", "Essence Catalyst", new ItemAddEssence(10), JourneyTabs.UTIL);
		fieryStabber = Registrar.regAndSetupItem("fiery_stabber", "Fiery Stabber", new JItem());
		glacialBlade = Registrar.regAndSetupItem("glacial_blade", "Glacial Blade", new JItem());
		glossyEye = Registrar.regAndSetupItem("glossy_eye", "Glossy Eye", new JItem());
		heartStone = Registrar.regAndSetupItem("heart_stone", "Heart Stone", new ItemRestoreHealth(true), JourneyTabs.UTIL);
		lightBuckler = Registrar.regAndSetupItem("light_buckler", "Light Buckler", new JItem());
		magicDust = Registrar.regAndSetupItem("magic_dust", "Magic Dust", new JItem());

		bogshroomShelfSpore = Registrar.regAndSetupItem("bogshroom_shelf_spore", "Bogshroom Shelf Spore", new ItemMushroomSpore());

		moonOfEternalNight = Registrar.regAndSetupItem("moon_of_eternal_night", "Moon of Eternal Night", new ItemEternalNight(), JourneyTabs.UTIL);
		nethicObstructor = Registrar.regAndSetupItem("nethic_obstructor", "Nethic Obstructor", new JItem());
		slimeBerry = Registrar.regAndSetupItem("slime_berry", "Slimeberry", new JItem());
		slimun = Registrar.regAndSetupItem("slimun", "Slimun", new JItem());
		terraBuckler = Registrar.regAndSetupItem("terra_buckler", "Terra Buckler", new JItem());
		volcanicStone = Registrar.regAndSetupItem("volcanic_stone", "Volcanic Stone", new JItem());
		withicObstructor = Registrar.regAndSetupItem("withic_obstructor", "Withic Obstructor", new JItem());
		xpCatalyst = Registrar.regAndSetupItem("xp_catalyst", "Experience Catalyst", new ItemAddXP(1), JourneyTabs.UTIL);
		chestKey = Registrar.regAndSetupItem("chest_key", "Chest Key", new JItem());
		aquastone = Registrar.regAndSetupItem("aquastone", "Aquastone", new JItem());

		pottery_shard = Registrar.regAndSetupItem("pottery_shard", "Pottery Shard", new JItem());

		petFood = Registrar.regAndSetupItem("pet_food", "Pet Food", new JItem());
		robotEgg = Registrar.regAndSetupItem("robot_egg", "Robot Egg", new JItem());
		tamedRobotSpawnEgg = Registrar.regAndSetupItem("robot_spawner_egg", "Incubated Robot Egg", new ItemSpawnerEgg(EntityPetRobot.class), JourneyTabs.SPAWNERS);

		eucaHopperEgg = Registrar.regAndSetupItem("euca_hopper_egg", "Euca Hopper Egg", new JItem());
		tamedEucaHopperSpawnEgg = Registrar.regAndSetupItem("euca_hopper_spawner_egg", "Incubated Euca Hopper Egg", new ItemSpawnerEgg(EntityEucaHopper.class), JourneyTabs.SPAWNERS);

		shiverwolfEgg = Registrar.regAndSetupItem("shiverwolf_egg", "Shiverwolf Egg", new JItem());
		tamedShiverwolfSpawnEgg = Registrar.regAndSetupItem("shiverwolf_spawner_egg", "Incubated Shiverwolf Egg", new ItemSpawnerEgg(EntityShiverwolf.class), JourneyTabs.SPAWNERS);

		rocEgg = Registrar.regAndSetupItem("roc_egg", "Roc Egg", new JItem());
		tamedRocSpawnEgg = Registrar.regAndSetupItem("roc_spawner_egg", "Incubated Roc Egg", new ItemSpawnerEgg(EntityTamedRoc.class), JourneyTabs.SPAWNERS);

		loreScroll = Registrar.regAndSetupItem("lore_scroll", "Lore Scroll", new ItemLoreScroll());

		debugItem = Registrar.regAndSetupItem("test_bug", "Test Bug", new ItemTestBug(), JourneyTabs.UTIL);

		dynasterAmulet = Registrar.regAndSetupItem("amulet_dynaster", "Amulet of the Dynaster", new ItemDynasterAmulet(), JourneyTabs.UTIL);
		aquaticAmulet = Registrar.regAndSetupItem("amulet_aquatic", "Amulet of the Depths", new ItemAquaticAmulet(), JourneyTabs.UTIL);
		magmaAmulet = Registrar.regAndSetupItem("amulet_magma", "Amulet of Eternal Magma", new ItemMagmaAmulet(), JourneyTabs.UTIL);
		iceAmulet = Registrar.regAndSetupItem("amulet_ice", "Amulet of Glacial Bloodlust", new ItemIceAmulet(), JourneyTabs.UTIL);
		emptyAmulet = Registrar.regAndSetupItem("amulet_empty", "Empty Amulet", new JItem());


		skullOfDecay = Registrar.regAndSetupItem("skull_of_decay", "Skull Of Decay", new ItemSkullOfDecay(), JourneyTabs.UTIL);
		minersPearl = Registrar.regAndSetupItem("miners_pearl", "Miners Pearl", new ItemMinersPearl(), JourneyTabs.UTIL);
		magicThreadOfTethering = Registrar.regAndSetupItem("magic_thread_of_tethering", "Magic Thread of Tethering", new ItemStringOfTethering(), JourneyTabs.UTIL);
		iceFlake = Registrar.regAndSetupItem("iceflake", "Iceflake", new ItemIceflake(), JourneyTabs.UTIL);
		strengthTotem = Registrar.regAndSetupItem("totem_strength", "Totem of Impeccable Strength", new ItemStrengthTotem(), JourneyTabs.UTIL);
		charmOfVineStranding = Registrar.regAndSetupItem("charm_of_vine_stranding", "Charm of Vinestranding", new ItemVinestranding(), JourneyTabs.UTIL);
		charmOfWaterBending = Registrar.regAndSetupItem("charm_of_water_bending", "Charm of Waterbending", new ItemWaterbending(), JourneyTabs.UTIL);

		heartContainerSmall = Registrar.regAndSetupItem("heartSml", "Heart Container", new ItemHeartContainer(2), JourneyTabs.UTIL);
		heartContainerMedium = Registrar.regAndSetupItem("heartMed", "Heart Container", new ItemHeartContainer(6), JourneyTabs.UTIL);
		heartContainerLarge = Registrar.regAndSetupItem("heartLrg", "Heart Container", new ItemHeartContainer(10), JourneyTabs.UTIL);
		heartContainerUltimate = Registrar.regAndSetupItem("heartUlt", "Heart Container", new ItemHeartContainer(14), JourneyTabs.UTIL);
		heartContainerSentry = Registrar.regAndSetupItem("heartSen", "Heart of the Sentry", new ItemHeartContainer(20), JourneyTabs.UTIL);

		blindnessRing = Registrar.regAndSetupItem("ring_blindness", "Ring of Clear Vision", new ItemRingBlindness(), JourneyTabs.UTIL);
		harmRing = Registrar.regAndSetupItem("ring_harm", "Ring of Strong Immunity", new ItemRingHarm(), JourneyTabs.UTIL);
		miningFatigueRing = Registrar.regAndSetupItem("ring_mining_fatigue", "Ring of Great Spelunking", new ItemRingMiningFatigue(), JourneyTabs.UTIL);
		nauseaRing = Registrar.regAndSetupItem("ring_nausea", "Ring of Good Health", new ItemRingNausea(), JourneyTabs.UTIL);
		poisonRing = Registrar.regAndSetupItem("ring_poison", "Ring of Safe Living", new ItemRingPoison(), JourneyTabs.UTIL);
		slownessRing = Registrar.regAndSetupItem("ring_slowness", "Ring of Protected Movement", new ItemRingSlowness(), JourneyTabs.UTIL);
		witherRing = Registrar.regAndSetupItem("ring_wither", "Ring of Inverse Decay", new ItemRingWither(), JourneyTabs.UTIL);
		luckyCharm = Registrar.regAndSetupItem("charm_of_luck", "Lucky Charm", new ItemLuckyCharm(), JourneyTabs.UTIL);

		soulBuddy = Registrar.regAndSetupItem("soul_buddy", "Soul Buddy", new ItemBuddySoul(), JourneyTabs.UTIL);

		swampLily = Registrar.regAndSetupItem("swamp_lily_item", "Swamp Lily", new JItemWaterLily(), JourneyTabs.DECORATION);

		sentacoin = Registrar.regAndSetupItem("sentacoin", "Sentacoin", new JItem()).setCreativeTab(null);
		sentacoinBag = Registrar.regAndSetupItem("sentacoin_bag", "Sentacoin Bag", new JItem()).setCreativeTab(null);
	}

    public static ToolMaterial addToolMaterial(int uses, float efficiency, float dam, boolean breakable) {
        return EnumHelper.addToolMaterial("tool", 3, breakable ? uses : -1, efficiency, dam - 4, 30);
    }
}