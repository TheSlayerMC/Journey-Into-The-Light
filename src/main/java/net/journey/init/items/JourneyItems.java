package net.journey.init.items;

import net.journey.dimension.base.DimensionHelper;
import net.journey.entity.mob.boss.*;
import net.journey.entity.mob.pet.EntityEucaHopper;
import net.journey.entity.mob.pet.EntityPetRobot;
import net.journey.entity.mob.pet.EntityShiverwolf;
import net.journey.entity.mob.pet.EntityTameRoc;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.journey.items.*;
import net.journey.items.base.JItem;
import net.journey.items.interactive.*;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;

public class JourneyItems {

    public static final EntityEquipmentSlot HEAD = EntityEquipmentSlot.HEAD, BODY = EntityEquipmentSlot.CHEST, LEGS = EntityEquipmentSlot.LEGS, BOOTS = EntityEquipmentSlot.FEET;
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

    public static ToolMaterial sapphiretool = addToolMaterial(1461, 10F, 5F, true);
    public static ToolMaterial lunium = addToolMaterial(1490, 12F, 5F, true);
    public static ToolMaterial shadium = addToolMaterial(1521, 12F, 5F, true);
    public static ToolMaterial hellstone = addToolMaterial(1432, 15F, 5F, true);
    public static ToolMaterial nethic = addToolMaterial(481, 21F, 5F, true);
    public static ToolMaterial celestium = addToolMaterial(1621, 18F, 5F, true);
    public static ToolMaterial mekyum = addToolMaterial(1621, 18F, 5F, true);
    public static ToolMaterial storon = addToolMaterial(1621, 18F, 5F, true);
    public static ToolMaterial korite = addToolMaterial(1621, 18F, 5F, true);
    public static ToolMaterial flairium = addToolMaterial(2130, 21F, 5F, true);
    public static ToolMaterial des = addToolMaterial(2130, 21F, 5F, true);
    public static ToolMaterial orbadite = addToolMaterial(3142, 28F, 5F, true);
    public static ToolMaterial gorbite = addToolMaterial(3142, 28F, 5F, true);
    public static ToolMaterial hoeoflife = addToolMaterial(126, 28F, 5F, true);
    public static ToolMaterial hoeofearth = addToolMaterial(512, 28F, 5F, true);
    public static ToolMaterial hellstoneMulti = addToolMaterial(3000, 10F, 5F, false);
    public static ToolMaterial flairiumMulti = addToolMaterial(3000, 16F, 5F, false);
    public static ToolMaterial luniumMulti = addToolMaterial(3000, 7F, 5F, false);
    public static ToolMaterial celestiumMulti = addToolMaterial(3000, 13F, 5F, false);
    public static ToolMaterial mekyumMulti = addToolMaterial(3000, 13F, 5F, false);
    public static ToolMaterial storonMulti = addToolMaterial(3000, 13F, 5F, false);
    public static ToolMaterial koriteMulti = addToolMaterial(3000, 13F, 5F, false);
    public static ToolMaterial shadiumMulti = addToolMaterial(3000, 9F, 5F, false);
    public static ToolMaterial sapphireMulti = addToolMaterial(3000, 7F, 5F, false);
    public static ToolMaterial orbaditeMulti = addToolMaterial(3000, 20F, 5F, false);
    public static ToolMaterial gorbiteMulti = addToolMaterial(3000, 20F, 5F, false);
    public static ToolMaterial desMulti = addToolMaterial(3000, 16F, 5F, false);
    public static ToolMaterial smeltingMulti = addToolMaterial(3000, 8F, 5F, false);
    public static ToolMaterial SLIMY_PICK_MATERIAL = addToolMaterial(3000, 2F, 5F, true);
    public static ToolMaterial BEDROCK_SHOVEL_MATERIAL = addToolMaterial(1000, 4F, 5F, false);
    
    //TODO MATERIALS
    public static ToolMaterial woodMulti = addToolMaterial(60, 2F, 5F, false);
    public static ToolMaterial stoneMulti = addToolMaterial(132, 3F, 5F, false);
    public static ToolMaterial ironMulti = addToolMaterial(251, 4F, 5F, false);
    public static ToolMaterial goldMulti = addToolMaterial(33, 5F, 5F, false);
    public static ToolMaterial diamondMulti = addToolMaterial(1562, 6F, 5F, false);
    public static ArrayList<Item> items = new ArrayList<>();
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
    
    public static Item ANCIENT_EYE_OF_OPENING;
    
    public static Item LAMENT_DECONSTRUCTOR_NETHER;
    public static Item LAMENT_DECONSTRUCTOR_BOIL;
    public static Item LAMENT_DECONSTRUCTOR_EUCA;
    public static Item LAMENT_DECONSTRUCTOR_DEPTHS;
    public static Item LAMENT_DECONSTRUCTOR_CORBA;
    public static Item LAMENT_DECONSTRUCTOR_TERRANIA;
    public static Item LAMENT_DECONSTRUCTOR_CLOUDIA;
    public static Item LAMENT_DECONSTRUCTOR_SENTRY;
    public static Item SENTRY_KEY;
    
    public static Item SENTRY_DISK;
    public static Item SENTERIAN_SOUL;
    public static Item SENTRY_OBSERVER;
    
    public static Item HATEFUL_HEART;
    public static Item SENTIENT_PINION_BLOOD;
    public static Item SENTIENT_PINION_DESTRUCTION;
    public static Item SENTIENT_PINION_LIGHT;
    public static Item DARK_MATTER_GEMSTONE;
    
    public static Item SENTRIUM_CRYSTAL;
    public static Item MELLIUM_CRYSTAL;
    
    public static Item SENTRY_NODE_BLOOD;
    public static Item SENTRY_NODE_DESTRUCTION;
    public static Item SENTRY_NODE_LIGHT;
    public static Item SENTRY_ROCK;

    public static Item obsidianRod;

    public static Item ancientPiece_1;
    public static Item ancientPiece_2;
    public static Item ancientPiece_3;
    public static Item ancientPiece_4;

    public static Item bleedstone;
    public static Item smithstone;

    public static Item bleedstonedust;
    public static Item smithstonedust;

    public static Item nethicgemstone;

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
    public static Item shimmerdust;
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
    public static Item eucaPortalPiece_1;
    public static Item eucaPortalPiece_0;
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
    public static Item untitled_disc;
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
    
    public static Item LOOT_POUCH;
    public static Item BALMY_TEARDROP;
    public static Item CHARM_OF_VINE_STRANDING;
    public static Item CHARM_OF_WATER_BENDING;
    public static Item CLOUD_BUCKLER;
    public static Item CURSED_TOMB;
    public static Item DEATH_CAP;
    public static Item DEMONIC_SKULL;
    public static Item ESSENCE_BUCKLER;
    public static Item ESSENCE_CATALYST;
    public static Item FIERY_STABBER;
    public static Item GLACIAL_BLADE;
    public static Item GLOSSY_EYE;
    public static Item HEART_STONE;
    public static Item LIGHT_BUCKLER;
    public static Item MAGIC_DUST;

    public static Item MOON_OF_ETERNAL_NIGHT;
    public static Item NETHIC_OBSTRUCTOR;
    public static Item SLIME_BERRY;
    public static Item SLIMUN;
    public static Item TERRA_BUCKLER;
    public static Item WITHIC_OBSTRUCTOR;
    public static Item XP_CATALYST;
    public static Item AQUASTONE;
    public static Item VOLCANIC_STONE;
    public static Item CHEST_KEY;

    public static Item PET_FOOD;
    public static Item TAMED_ROBOT_SPAWN_EGG;
    public static Item ROBOT_EGG;
    public static Item TAMED_EUCA_HOPPER_SPAWN_EGG;
    public static Item EUCA_HOPPER_EGG;
    public static Item TAMED_SHIVERWOLF_SPAWN_EGG;
    public static Item SHIVERWOLF_EGG;
    public static Item TAMED_ROC_SPAWN_EGG;
    public static Item ROC_EGG;

	public static ItemLoreScroll loreScroll;
	public static ItemTestBug debugItem;
    
    //public static Item obsidianBoat;

    //public static Item infernoshield;

    public static void init() {

	    hellstoneIngot = new JItem("hellstoneIngot", "Hellstone Ingot");
	    shadiumIngot = new JItem("shadiumIngot", "Shadium Ingot");
	    celestiumIngot = new JItem("celestiumIngot", "Celestium Ingot");
	    mekyumIngot = new JItem("mekyumIngot", "Mekyum Ingot");
	    koriteIngot = new JItem("koriteIngot", "Korite Ingot");
	    storonIngot = new JItem("storonIngot", "Storon Ingot");
	    luniumIngot = new JItem("luniumIngot", "Lunium Ingot");
	    flairiumIngot = new JItem("flairiumIngot", "Flairium Ingot");
	    ash = new JItem("ash", "Ash");
	    sapphire = new JItem("sapphire", "Sapphire Gem");
	    blazium = new JItem("blazium", "Blazium Gem");
	    enderilliumShard = new JItem("enderilliumShard", "Enderillium Shard");
	    orbaditeIngot = new JItem("orbaditeIngot", "Orbadite Ingot");
	    gorbiteGem = new JItem("gorbiteGem", "Gorbite Gem");
	    desIngot = new JItem("desIngot", "Des Ingot");
	    bleedstone = new JItem("bleedstone", "Bleedstone");
	    smithstone = new JItem("smithstone", "Smithstone");
	    nethicgemstone = new JItem("soulstone", "Soulstone");

	    ancientPiece_1 = new ItemAncientPiece("ancientpiece_1", "Ancient Piece");
	    ancientPiece_2 = new ItemAncientPiece("ancientpiece_2", "Ancient Shard");
	    ancientPiece_3 = new ItemAncientPiece("ancientpiece_3", "Ancient Chunk");
	    ancientPiece_4 = new ItemAncientPiece("ancientpiece_4", "Ancient Fragment");

	    spawnerBar = new JItem("spawnerBar", "Spawner Bar");
	    spawnerClump = new JItem("spawnerClump", "Spawner Clump");
	    hellstoneClump = new JItem("hellstoneClump", "Hellstone Clump");
	    shadiumClump = new JItem("shadiumClump", "Shadium Clump");
	    luniumClump = new JItem("luniumClump", "Lunium Clump");
	    elderKey = new JItem("elderKey", "Elder Key").setMaxStackSize(1);
	    boilPowder = new JItem("boilPowder", "Boiling Powder");
	    blazingFireball = new JItem("blazingFireball", "Blazing Fireball");
	    hellTurtleShell = new JItem("hellTurtleShell", "Hell Turtle Shell");
	    sizzlingEye = new JItem("sizzlingEye", "Sizzling Eye");
	    sentryEye = new ItemSentryEye("sentryEye", "Sentry Eye");
	    boilingSkull = new JItem("boilingskull", "Boiling Skull");
	    overgrownNatureBall = new JItem("overgrownNatureBall", "Overgrown Natureball");
	    overseeingTablet = new JItem("overseeingTablet", "Overseeing Tablet");
	    overseeingEye = new JItem("overseeingEye", "Overseeing Eye");
	    darkCrystal = new JItem("darkCrystal", "Dark Crystal");
	    darkOrb = new JItem("darkOrb", "Dark Orb");
	    depthsFlake = new JItem("depthsFlake", "Depths Flake");
	    beastlyStomach = new JItem("beastlyStomach", "Beastly Stomach");
	    rocFeather = new JItem("rocFeather", "Essence Feather");
	    coldClump = new JItem("coldClump", "Cold Clump");
	    crystalEye = new JItem("crystalEye", "Crystal Eye");
	    crystalFlake = new JItem("crystalFlake", "Crystal Flake");
	    freezingTablet = new JItem("freezingTablet", "Freezing Tablet");
	    frostFlake = new JItem("frostFlake", "Frost Flake");
	    frostGem = new JItem("frostGem", "Frost Gem");
	    frozenIceball = new JItem("frozenIceball", "Frozen Iceball");
	    snowsheet = new JItem("snowsheet", "Snowsheet");
	    gateKeys = new JItem("gateKeys", "Gate Keys");
	    goldClump = new JItem("goldClump", "Gold Clump");
	    silverClump = new JItem("silverClump", "Silver Clump");
	    golderDust = new JItem("golderDust", "Golder Dust");
	    shimmerdust = new JItem("shimmerdust", "Shimmer Dust");
	    royalDisk = new JItem("royalDisk", "Royal Disk");
	    metalDisk = new JItem("metalDisk", "Metal Disk");
	    demonicDust = new JItem("demonicDust", "Demonic Dust");
	    demonicBone = new JItem("demonicBone", "Demonic Bone");
	    withicDust = new JItem("withicDust", "Withic Dust");
	    cloudiaOrb = new JItem("cloudiaOrb", "Cloudia Orb");
	    fluffyFeather = new JItem("fluffyFeather", "Fluffy Feather");
	    golemChunk = new JItem("golemChunk", "Golem Chunk");
	    luniteChunk = new JItem("luniteChunk", "Lunite Chunk");
	    corbaStick = new JItem("corbaStick", "Corba Stick");
	    spyclopseEye = new JItem("spyclopseEye", "Spyclopse Eye");
	    caveCrystal = new JItem("caveCrystal", "Cave Crystal");
	    caveDust = new JItem("caveDust", "Cave Dust");
	    stoneClump = new JItem("stoneClump", "Stone Clump");
	    enchantedLeaf = new JItem("enchantedLeaf", "Enchanted Leaf");
	    baseEssence = new JItem("baseEssence", "Base Essence");
	    cloudPuff = new JItem("cloudPuff", "Cloud Puff");
	    collectorRock = new JItem("collectorRock", "Collector Rock");
	    natureTablet = new JItem("natureTablet", "Nature Tablet");
	    horn = new JItem("horn", "Horn");
	    scale = new JItem("scale", "Scale");
	    reinforcedStoneIngot = new JItem("reinforcedStoneIngot", "Reinforced Stone Ingot");
	    reinforcedCrystalIngot = new JItem("reinforcedCrystalIngot", "Reinforced Crystal Ingot");
	    crystalBall = new JItem("crystalBall", "Crystal Ball");
	    stoneStick = new JItem("stoneStick", "Stone Stick");
	    darkTerrarianSoil = new JItem("darkTerrarianSoil", "Dark Terrarian Soil");
	    earthenCrystal = new JItem("earthenCrystal", "Earthen Crystal");
	    lightTerrarianSoil = new JItem("lightTerrarianSoil", "Light Terrarian Soil");
	    terrastar = new JItem("terrastar", "Terrastar");
	    purplePowder = new JItem("purplePowder", "Purple Powder");
	    hellShards = new JItem("hellShards", "Hell Shards");
	    hellcrustIngot = new JItem("hellcrustIngot", "Hellcrust Ingot");
	    flamingSprocket = new JItem("flamingSprocket", "Flaming Sprocket");
	    flamingSpring = new JItem("flamingSpring", "Flaming Spring");
	    blood = new JItem("blood", "Blood");
	    concentratedBlood = new JItem("concentratedBlood", "Concentrated Blood");
	    snakeSkin = new JItem("snakeSkin", "Snake Skin");
	    withicSpine = new JItem("withicSpine", "Withic Spine");
	    lostSoul = new JItem("lostSoul", "Lost Soul");
	    withicSoul = new JItem("withicSoul", "Withic Soul");
	    eucaPortalPiece = new JItem("eucaPortalPiece", "Euca Portal Piece");
	    eucaPortalPiece_1 = new JItem("eucaPortalPiece_1", "Euca Portal Piece");
	    eucaPortalPiece_0 = new JItem("eucaPortalPiece_0", "Euca Portal Piece");
	    flamingHide = new JItem("flamingHide", "Flaming Hide");
	    boilKey = new JItem("boilKey", "Boil Key").setMaxStackSize(1).setMaxDamage(15);
	    darkKey = new JItem("darkkey", "Dark Key").setMaxStackSize(1);
	    depthsKey = new JItem("depthskey", "Depths Key").setMaxStackSize(1);

	    slugSlime = new JItem("slugSlime", "Slug Slime");
	    eucaPortalGem = new JItem("eucaPortalGem", "Euca Portal Gem");
	    depthsPortalGem = new JItem("depthsPortalGem", "Depths Portal Gem");
	    corbaPortalGem = new JItem("corbaPortalGem", "Corba Portal Gem");
	    terraniaPortalGem = new JItem("terraniaPortalGem", "Terrania Portal Gem");
	    cloudiaPortalGem = new JItem("cloudiaPortalGem", "Cloudia Portal Gem");
	    //wastelandPortalGem = new ItemMod("wastelandPortalGem");
	    greenGem = new JItem("greenGem", "Green Gem");
	    purpleGem = new JItem("purpleGem", "Purple Gem");
	    blueGem = new JItem("blueGem", "Blue Gem");
	    yellowGem = new JItem("yellowGem", "Yellow Gem");
	    eucaTablet = new JItem("eucaTablet", "Euca Tablet");
	    wandBase = new JItem("wandBase", "Wand Base");
	    staffBase = new JItem("staffBase", "Staff Base");
	    firestoneClump = new JItem("firestoneClump", "Firestone Clump");
	    iridium = new JItem("iridium", "Iridium");
	    hellstoneDust = new JItem("hellstoneDust", "hellstone Dust");
	    shadiumDust = new JItem("shadiumDust", "Shadium Dust");
	    celestiumDust = new JItem("celestiumDust", "Celestium Dust");
	    luniumDust = new JItem("luniumDust", "Lunium Dust");
	    flairiumDust = new JItem("flairiumDust", "Flairium Dust");
	    ashDust = new JItem("ashDust", "Ash Dust");
	    sapphireDust = new JItem("sapphireDust", "Sapphire Dust");
	    enderilliumDust = new JItem("enderilliumDust", "Enderillium Dust");
	    gorbiteDust = new JItem("gorbiteDust", "Gorbite Dust");
	    orbaditeDust = new JItem("orbaditeDust", "Orbadite Dust");
	    diamondDust = new JItem("diamondDust", "Diamond Dust");
	    goldDust = new JItem("goldDust", "Gold Dust");
	    ironDust = new JItem("ironDust", "Iron Dust");
	    bleedstonedust = new JItem("bleedstonedust", "Bleedstone Dust");
	    smithstonedust = new JItem("smithstonedust", "Smithstone Dust");

	    frostyGift = new ItemLoot("frostyGift", "Frosty Gift", JourneyLootTables.LOOT_PRESENT);
	    LOOT_POUCH = new ItemLoot("loot_pouch", "Loot Pouch", JourneyLootTables.VANILLA_SIMPLE_DUNGEON);

	    flameCoin = new ItemFlameCoin("flameCoin", "Flame Coin");

	    calciaOrb = new ItemBossSpawner("calciaOrb", "Calcia Orb", () -> DimensionType.NETHER, EntityCalcia.class).setBossSummonMessage("msg.journey.summon.calcia");
	    netherBeastOrb = new ItemBossSpawner("netherBeastOrb", "Nether Beast Orb", () -> DimensionType.NETHER, EntityNetherBeast.class).setBossSummonMessage("msg.journey.summon.nether_beast");
	    witheringBeastOrb = new ItemBossSpawner("witheringBeastOrb", "Withering Soul", () -> DimensionType.NETHER, EntityWitheringBeast.class).setBossSummonMessage("msg.journey.summon.withering_beast");
	    eudorOrb = new ItemBossSpawner("eudorOrb", "Valuable Crown", () -> DimensionHelper.EUCA_DIM, EntityEudor.class).setBossSummonMessage("msg.journey.summon.eudor");
	    blazierOrb = new ItemBossSpawner("blazierOrb", "Burning Fireball", () -> DimensionHelper.BOILING_DIM, EntityBlazier.class).setBossSummonMessage("msg.journey.summon.blazier");
	    soulWatcherOrb = new ItemBossSpawner("soulWatcherOrb", "Soulless Eye", () -> DimensionType.NETHER, EntitySoulWatcher.class).setBossSummonMessage("msg.journey.summon.soul_watcher");
	    sentryKingOrb = new ItemBossSpawner("sentryKingOrb", "Eye of the Sentry", () -> DimensionHelper.CORBA_DIM, EntitySentryKing.class).setBossSummonMessage("msg.journey.summon.sentry_king");
	    loggerOrb = new ItemBossSpawner("loggerOrb", "Enchanted Log", () -> DimensionHelper.CORBA_DIM, EntityLogger.class).setBossSummonMessage("msg.journey.summon.logger");
	    thunderbirdOrb = new ItemBossSpawner("thunderbirdOrb", "Leader's Pearl", () -> DimensionHelper.DEPTHS_DIM, EntityThunderbird.class).setBossSummonMessage("msg.journey.summon.thunderbird");
	    mysteriousDisk = new ItemBossSpawner("mysteriousDisk", "Mysterious Disk", () -> DimensionHelper.CLOUDIA_DIM, EntitySkyStalker.class).setBossSummonMessage("msg.journey.summon.sky_stalker");
	    corallatorOrb = new ItemBossSpawner("corallatorOrb", "Gem of Peculiar Smelting", () -> DimensionHelper.EUCA_DIM, EntityCorallator.class).setBossSummonMessage("msg.journey.summon.corallator");
	    scaleOrb = new ItemBossSpawner("scaleOrb", "Aquatic Egg", () -> DimensionHelper.DEPTHS_DIM, EntityScale.class).setBossSummonMessage("msg.journey.summon.scale");
	    enchantedTerrastar = new ItemBossSpawner("enchantedTerrastar", "Enchanted Terrastar", () -> DimensionHelper.TERRANIA_DIM, EntityTerranianProtector.class).setBossSummonMessage("msg.journey.summon.terranian_protector");

	    weakEssencePotion = new ItemEssencePotion("weakEssencePotion", "Weak Essence Potion", false);
	    strongEssencePotion = new ItemEssencePotion("strongEssencePotion", "Strong Essence Potion", true);
	    essenceArrow = new ItemEssenceArrow("essenceArrow", "Essence Arrow");

	    underwaterWorldRecord = new ItemModRecord("underwaterWorld", "Underwater World", JourneySounds.UNDERWATER_WORLD);
	    blueWaterRecord = new ItemModRecord("blueWater", "Blue Water", JourneySounds.BLUE_WATER);
	    raceStarRecord = new ItemModRecord("raceStar", "Race Star", JourneySounds.RACE_STAR);
	    compBeginsRecord = new ItemModRecord("compBegins", "Competition Begins", JourneySounds.COMPETITION_BEGINS);
	    deepBlueRecord = new ItemModRecord("deepBlue", "Deep Blue", JourneySounds.DEEP_BLUE);
        raceShoreRecord = new ItemModRecord("raceShore", "Race to Shore", JourneySounds.RACE_TO_SHORE);
        untitled_disc = new ItemModRecord("untitled_disc", "untitled_disc", JourneySounds.UNTITLED_DISK);
        bogRecord = new ItemModRecord("bog", "Bog", JourneySounds.CORBA_1);
        cloudsRecord = new ItemModRecord("clouds", "Clouds", JourneySounds.CLOUDIA_1);
        stalactiteRecord = new ItemModRecord("stalactite", "Stalactite", JourneySounds.DEPTHS_1);
        cavernRecord = new ItemModRecord("cavern", "Cavern", JourneySounds.DEPTHS_2);
        goldRecord = new ItemModRecord("gold", "Gold", JourneySounds.EUCA_1);
	    gateRecord = new ItemModRecord("gate", "Gate", JourneySounds.EUCA_2);
	    journeyRecord = new ItemModRecord("journey", "Journey Into the Light", JourneySounds.EUCA_3);
	    sizzleRecord = new ItemModRecord("sizzle", "Sizzle", JourneySounds.BOIL_1);
	    fireflyRecord = new ItemModRecord("firefly", "Firefly", JourneySounds.TERRANIA_1);
	    snowbellsRecord = new ItemModRecord("snowbells", "Snow Bells", JourneySounds.FROZEN_1);

	    demonicEye = new ItemDemonicEye("demonicEye", "Demonic Eye");
	    darkGem = new ItemDarkGem("darkGem", "Dark Gem");
	    ANCIENT_EYE_OF_OPENING = new ItemAncientCatalyst("ancient_eye_of_opening", "Ancient Eye of Opening");

	    LAMENT_DECONSTRUCTOR_NETHER = new JItem("lament_deconstructor_nether", "Lament Deconstructor of Hell");
	    LAMENT_DECONSTRUCTOR_BOIL = new JItem("lament_deconstructor_boil", "Lament Deconstructor of Fire");
	    LAMENT_DECONSTRUCTOR_EUCA = new JItem("lament_deconstructor_euca", "Lament Deconstructor of Light");
	    LAMENT_DECONSTRUCTOR_DEPTHS = new JItem("lament_deconstructor_depths", "Lament Deconstructor of Darkness");
	    LAMENT_DECONSTRUCTOR_CORBA = new JItem("lament_deconstructor_corba", "Lament Deconstructor of Decay");
	    LAMENT_DECONSTRUCTOR_TERRANIA = new JItem("lament_deconstructor_terrania", "Lament Deconstructor of Life");
	    LAMENT_DECONSTRUCTOR_CLOUDIA = new JItem("lament_deconstructor_cloudia", "Lament Deconstructor of Heavan");
	    LAMENT_DECONSTRUCTOR_SENTRY = new JItem("lament_deconstructor_sentry", "Lament Deconstructor of Death");
	    SENTRY_KEY = new JItem("sentry_key", "Sentry Key");

	    SENTRY_DISK = new JItem("sentry_disk", "Sentry Disk");
	    SENTERIAN_SOUL = new JItem("senterian_soul", "Senterian Soul");
	    SENTRY_OBSERVER = new JItem("sentry_observer", "Sentry Observer");

	    HATEFUL_HEART = new JItem("hateful_heart", "Hateful Heart");
	    DARK_MATTER_GEMSTONE = new JItem("dark_matter_gemstone", "Dark Matter Gemstone");
	    SENTIENT_PINION_BLOOD = new JItem("sentient_pinion_blood", "Sentient Pinion of Blood");
	    SENTIENT_PINION_DESTRUCTION = new JItem("sentient_pinion_destruction", "Sentient Pinion of Destruction");
	    SENTIENT_PINION_LIGHT = new JItem("sentient_pinion_light", "Sentient Pinion of Light");

	    SENTRIUM_CRYSTAL = new JItem("sentrium_crystal", "Sentrium Crystal");
	    MELLIUM_CRYSTAL = new JItem("mellium_crystal", "Mellium Crystal");
	    SENTRY_ROCK = new JItem("sentry_rock", "Sentry Rock");

	    SENTRY_NODE_BLOOD = new JItem("sentry_node_blood", "Sentry Node of Blood");
	    SENTRY_NODE_DESTRUCTION = new JItem("sentry_node_destruction", "Sentry Node of Destruction");
	    SENTRY_NODE_LIGHT = new JItem("sentry_node_light", "Sentient Node of Light");

	    obsidianRod = new JItem("obsidianRod", "Obsidian Rod");

	    BALMY_TEARDROP = new JItem("balmy_teardrop", "Balmy Teardrop");
	    CHARM_OF_VINE_STRANDING = new JItem("charm_of_vine_stranding", "Charm of Vinestranding");
	    CHARM_OF_WATER_BENDING = new ItemWaterbending("charm_of_water_bending", "Charm of Waterbending");
	    CLOUD_BUCKLER = new JItem("cloud_buckler", "Cloud Buckler");
	    CURSED_TOMB = new ItemCursedTomb("cursed_tomb", "Cursed Tomb");
	    DEATH_CAP = new JItem("death_cap", "Death Cap");
	    DEMONIC_SKULL = new JItem("demonic_skull", "Demonic Skull");
	    ESSENCE_BUCKLER = new JItem("essence_buckler", "Essence Buckler");
	    ESSENCE_CATALYST = new ItemAddEssence("essence_catalyst", "Essence Catalyst", 10);
	    FIERY_STABBER = new JItem("fiery_stabber", "Fiery Stabber");
	    GLACIAL_BLADE = new JItem("glacial_blade", "Glacial Blade");
	    GLOSSY_EYE = new JItem("glossy_eye", "Glossy Eye");
	    HEART_STONE = new ItemRestoreHealth("heart_stone", "Heart Stone", true);
	    LIGHT_BUCKLER = new JItem("light_buckler", "Light Buckler");
	    MAGIC_DUST = new JItem("magic_dust", "Magic Dust");
	    MOON_OF_ETERNAL_NIGHT = new ItemEternalNight("moon_of_eternal_night", "Moon of Eternal Night");
	    NETHIC_OBSTRUCTOR = new JItem("nethic_obstructor", "Nethic Obstructor");
	    SLIME_BERRY = new JItem("slime_berry", "Slimeberry");
	    SLIMUN = new JItem("slimun", "Slimun");
	    TERRA_BUCKLER = new JItem("terra_buckler", "Terra Buckler");
	    VOLCANIC_STONE = new JItem("volcanic_stone", "Volcanic Stone");
	    WITHIC_OBSTRUCTOR = new JItem("withic_obstructor", "Withic Obstructor");
	    XP_CATALYST = new ItemAddXP("xp_catalyst", "Experience Catalyst", 1);
	    CHEST_KEY = new JItem("chest_key", "Chest Key");
	    AQUASTONE = new JItem("aquastone", "Aquastone");

        PET_FOOD = new JItem("pet_food", "Pet Food");
        ROBOT_EGG = new JItem("robot_egg", "Robot Egg");
        TAMED_ROBOT_SPAWN_EGG = new ItemSpawnerEgg("robot_spawner_egg", "Incubated Robot Egg", EntityPetRobot.class);

	    EUCA_HOPPER_EGG = new JItem("euca_hopper_egg", "Euca Hopper Egg");
	    TAMED_EUCA_HOPPER_SPAWN_EGG = new ItemSpawnerEgg("euca_hopper_spawner_egg", "Incubated Euca Hopper Egg", EntityEucaHopper.class);

	    SHIVERWOLF_EGG = new JItem("shiverwolf_egg", "Shiverwolf Egg");
	    TAMED_SHIVERWOLF_SPAWN_EGG = new ItemSpawnerEgg("shiverwolf_spawner_egg", "Incubated Shiverwolf Egg", EntityShiverwolf.class);

	    ROC_EGG = new JItem("roc_egg", "Roc Egg");
	    TAMED_ROC_SPAWN_EGG = new ItemSpawnerEgg("roc_spawner_egg", "Incubated Roc Egg", EntityTameRoc.class);

	    loreScroll = new ItemLoreScroll("lore_scroll", "Lore Scroll");
	    debugItem = new ItemTestBug("test_bug", "Test Bug");

	    //obsidianBoat = new ItemObsidianBoat("obsidianboat", "Obsidian Boat");
	    //infernoshield = new ItemModShield("infernoshield", "Inferno Shield", 2, false);
    }

    public static ToolMaterial addToolMaterial(int uses, float efficiency, float dam, boolean breakable) {
        return EnumHelper.addToolMaterial("tool", 3, breakable ? uses : -1, efficiency, dam - 4, 30);
    }
}