package net.journey.init.blocks;

import net.journey.blocks.*;
import net.journey.blocks.base.BlockModFire;
import net.journey.blocks.base.BlockModOre;
import net.journey.blocks.base.BlockNetherFlower;
import net.journey.blocks.machines.*;
import net.journey.blocks.meta.BlockMetaBase;
import net.journey.blocks.portal.*;
import net.journey.dimension.boil.trees.WorldGenBoilTree1;
import net.journey.dimension.corba.gen.trees.WorldGenCorbaSmallTree;
import net.journey.dimension.euca.gen.trees.WorldGenEucaTree;
import net.journey.dimension.frozen.gen.WorldGenIceTree;
import net.journey.dimension.nether.gen.trees.WorldGenBleedheartTree0;
import net.journey.dimension.terrania.gen.trees.WorldGenTerraniaSmallTree;
import net.journey.init.JourneyTabs;
import net.journey.init.items.JourneyConsumables;
import net.journey.init.items.JourneyItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.EnumToolType;
import net.slayer.api.block.*;

import java.util.ArrayList;

public class JourneyBlocks {

    public static ArrayList<String> blockName = new ArrayList<>();//TODO is it really needed?
    public static ArrayList<Block> blocks = new ArrayList<>();

    public static BlockMod sapphireOre;
    public static BlockMod lavaRock;
    public static BlockMod shadiumOre;
    public static BlockMod luniumOre;
    public static BlockMod hellstoneOre;
    public static BlockMod ashualOre;
    public static BlockMod blaziumOre;
    public static BlockMod celestiumOre;
    public static BlockMod mekyumOre;
    public static BlockMod koriteOre;
    public static BlockMod storonOre;
    public static BlockMod flairiumOre;
    public static BlockMod desOre;
    public static BlockMod iridiumOre;
    public static BlockMod enderilliumOre;
    public static BlockMod gorbiteOre;
    public static BlockMod orbaditeOre;
    public static BlockMod luniteOre;
    public static BlockMod firestoneOre;

    public static BlockMod sapphireBlock;
    public static BlockMod shadiumBlock;
    public static BlockMod luniumBlock;
    public static BlockMod hellstoneBlock;
    public static BlockMod ashualBlock;
    public static BlockMod blaziumBlock;
    public static BlockMod celestiumBlock;
    public static BlockMod mekyumBlock;
    public static BlockMod koriteBlock;
    public static BlockMod storonBlock;
    public static BlockMod flairiumBlock;
    public static BlockMod desBlock;
    public static BlockMod enderilliumBlock;
    public static BlockMod gorbiteBlock;
    public static BlockMod orbaditeBlock;
    public static BlockMod luniteBlock;
    public static BlockMod iridiumBlock;
    public static BlockMod nethicGemstoneBlock;

    public static BlockMod bleedstone;
    public static BlockMod smithstone;
    public static BlockMod mageWall;

    public static BlockMod bloodRock;
    public static BlockMod bloodRune;
    public static BlockMod carvedBloodRock;
    public static BlockMod bloodPillar;
    public static BlockMod bloodBricks;
    public static BlockMod obelisk;
    public static BlockMod bloodLamp;
    public static BlockMod bloodCatalyst;

    public static BlockModLog witherwoodLog;
    public static BlockMod witherwoodLeaves;
    public static BlockMod sizzlerWoodLeaves;
    public static BlockModLog sizzlerWoodLog;
    public static BlockNetherFlower deathGrass;
    public static BlockNetherFlower netherweed;
    public static BlockNetherFlower hellBell;
    public static BlockMod hellshroom;

    public static BlockMod earthenNetherLeaves;
    public static BlockModLog earthenNetherLog;
    public static BlockMod earthenNetherPlanks;

    public static BlockMod earthenNetherTallGrass;
    public static BlockMod earthenNetherShortGrass;
    public static BlockMod earthenNetherFlower;

    public static BlockMod heatSoil;
    public static BlockMod earthenNetherrack;
    public static BlockModGravity heatSand;
    public static BlockModGravity sizzleSand;

    public static BlockMod igniterOn;
    public static BlockMod igniter;
    public static BlockMod greenGemBlock;
    public static BlockMod purpleGemBlock;
    public static BlockMod blueGemBlock;
    public static BlockMod yellowGemBlock;

    public static BlockModBush sizzleberryBush;
    public static BlockModBush bradberryBush;
    public static BlockModBush tangleberryBush;
    public static BlockModBush juiceberryBush;
    public static BlockModBush bogberryBush;

    public static BlockMod nethicanSludge;

    public static BlockMod eucaSapling;
    public static BlockMod boilSapling;
    public static BlockMod depthsSapling;
    public static BlockMod corbaSapling;
    public static BlockMod netherSapling;
    public static BlockMod frozenSapling;
    public static BlockMod frozenIceSapling;
    public static BlockMod terraniaSapling;

    public static BlockMod eucaGrass;
    public static BlockMod eucaSilverGrass;
    public static BlockMod eucaDirt;
    public static BlockMod eucaStone;
    public static BlockModLog eucaGoldLog;
    public static BlockMod goldEucaPlank;
    public static BlockModStairs eucaGoldStairs;
    public static BlockMod eucaGoldLeaves;
    public static BlockMod eucaSilverLeaves;
    public static BlockMod eucaLightGreenLeaves;
    public static BlockMod eucaDarkGreenLeaves;

    public static BlockMod eucaBricks;
    public static BlockMod eucaTile;
    public static BlockMod depthsDirt;
    public static BlockMod depthsGrass;
    public static BlockMod depthsStone;
    public static BlockModLog depthsLog;
    public static BlockMod depthsPlank;
    public static BlockModStairs depthsStairs;
    public static BlockMod depthsLeaves;
    public static BlockMod darkBrick;
    public static BlockMod darkFloor;
    public static BlockMod darkShingle;
    public static BlockMod depthsShingle;
    public static BlockMod depthsCarpet;
    public static BlockMod depthsBookshelf;
    public static BlockMod depthsBrick;
    public static BlockMod depthsGlass;
    public static BlockMod depthsCobblestone;
    public static BlockMod depthsPillar;
    public static BlockMod depthsLantern;
    public static BlockModBars depthsGate;
    public static BlockMod depthsLock;
    public static BlockMod depthsTile;
    public static BlockModDoor depthsDoor;

    public static BlockMod iceLeaves;
    public static BlockMod frozenDirt;
    public static BlockMod frozenStone;
    public static BlockMod frozenGrass;
    public static BlockMod frozenLeaves;
    public static BlockModLog frozenBark;
    public static BlockMod frozenPlanks;

    public static BlockMod withanDirt;
    public static BlockMod withanRock;
    public static BlockMod withanGrass;
    public static BlockMod withanLeaves;
    public static BlockModLog withanBark;
    public static BlockMod withanRockReinforced;
    public static BlockMod withanLamp;
    public static BlockMod withanLight;

    public static BlockMod corbaStone;
    public static BlockMod corbaGrass;
    public static BlockModLog corbaLog;
    public static BlockMod corbaLeaves;
    public static BlockMod corbaPillar;
    public static BlockMod corbaPlank;
    public static BlockMod corbaLamp;
    public static BlockMod corbaSentryBrick;

    public static BlockMod boilingLamp;
    public static BlockMod blazierBricks;
    public static BlockMod ashBlock;
    public static BlockMod hotBlock;
    public static BlockMod rubble;
    public static BlockMetaBase brisonblocks;
    public static BlockMod boilingBars;
    public static BlockMod burningLeaves;
    public static BlockModLog boilingLog;

    public static BlockMod cloudiaDirt;
    public static BlockMod cloudiaGrass;
    public static BlockModLog cloudiaLog;
    public static BlockMod cloudiaPlanks;
    public static BlockMod cloudiaPillar;
    public static BlockMod cloudiaRock;
    public static BlockMod cloudiaBrick;
    public static BlockMod cloudiaWall;
    public static BlockMod cloudiaTile;
    public static BlockMod cloudiaLeaves;
    public static BlockMod cloudiaLamp;
    public static BlockMod cloudiaCobblestone;
    public static BlockModStairs cloudiaBrickStairs;
    public static BlockModStairs cloudiaTileStairs;
    // public static BlockMod cloudiaPost = new BlockCloudiaPost("cloudiaPost");
    public static BlockMod pinkCloudiaCloud;
    public static BlockMod blueCloudiaCloud;
    public static BlockMod lightBlueCloudiaCloud;
    public static BlockModFlower cloudiaTallGrass;
    public static BlockModFlower cloudiaFlower;

    public static BlockMod swampLamp;

    // public static BlockMod frozenChest;
    // public static BlockMod netherChest;
    // public static BlockMod boilingChest;
    // public static BlockMod eucaChest;
    // public static BlockMod depthsChest;
    // public static BlockMod corbaChest;
    // public static BlockMod cloudiaChest;
    // public static BlockMod terraniaChest;
    public static BlockJourneyChest journeyChest;
    public static BlockJourneyChest netherChest;
    public static BlockJourneyChest boilChest;
    public static BlockJourneyChest eucaChest;
    public static BlockJourneyChest frozenChest;
    public static BlockJourneyChest depthsChest;
    public static BlockJourneyChest corbaChest;
    public static BlockJourneyChest terraniaChest;
    public static BlockJourneyChest cloudiaChest;
    public static BlockJourneyChest lockedChest;


    public static BlockMod nethicDungeonBricks;
    public static BlockMod boilChain;
    public static BlockMod boilGate;
    public static Block boilLock;

    public static BlockMod darklyGate;
    public static BlockMod darklyLock;

    public static BlockModFence corbaPost;
    public static BlockModFence sizzlingPost;

    public static BlockMod senterianBricks;
    public static BlockMod senterianRock;
    public static BlockMod senterianCarvedRock;
    public static BlockMod senterianFloor;
    public static BlockMod senterianGlass;
    public static BlockMod senterianBars;
    public static BlockMod senterianLightLamp;
    public static BlockMod senterianMellowLamp;
    public static BlockMod senterianGuardianLamp;
    public static BlockModFence senterianPost;

    public static BlockMod trophy;
    public static BlockMod trophySoul;
    public static BlockMod trophyBlaze;
    public static BlockMod trophyEudor;
    public static BlockMod trophyCor;
    public static BlockMod trophyScale;
    public static BlockMod trophyRoc;
    public static BlockMod trophySentry;
    public static BlockMod trophyLogger;
    public static BlockMod trophyTerra;
    public static BlockMod trophyStalk;
    public static BlockMod trophyNetherBeast;
    public static BlockMod trophyWitherBeast;

    public static BlockEucaPortal eucaPortal;
    public static BlockMod eucaPortalFrame;

    public static BlockDepthsPortal depthsPortal;
    public static BlockMod depthsPortalFrame;

    public static BlockBoilPortal boilPortal;
    public static BlockMod boilPortalFrame;

    public static BlockFrozenPortal frozenPortal;
    public static BlockMod frozenPortalFrame;

    public static BlockCorbaPortal corbaPortal;
    public static BlockMod corbaPortalFrame;

    public static BlockTerraniaPortal terraniaPortal;
    public static BlockMod terraniaPortalFrame;

    public static BlockSenterianPortal senterianPortal;
    public static BlockMod senterianPortalFrame;

    public static BlockCloudiaPortal cloudiaPortal;
    public static BlockMod cloudiaPortalFrame;

    public static BlockModFire fire;
    public static BlockMod caveVine;
    public static BlockMod mossyEssenceStone;

    public static BlockMod dungeonBrick;
    public static BlockMod dungeonCrackedBrick;
    public static BlockMod dungeonChisledBrick;
    public static BlockMod dungeonBrickCarved;
    public static BlockMod dungeonLamp;

    public static BlockModStairs dungeonBrickStairs;
    public static BlockModStairs dungeonCrackedBrickStairs;
    public static BlockModStairs dungeonChisledBrickStairs;
    public static BlockModStairs dungeonBrickCarvedStairs;
    public static BlockModStairs dungeonLampStairs;

    public static BlockModFence dungeonBrickFence;
    public static BlockModFence dungeonCrackedBrickFence;
    public static BlockModFence dungeonChisledBrickFence;
    public static BlockModFence dungeonBrickCarvedFence;
    public static BlockModFence cloudiaPost;
    public static BlockModFence terranianPost;
    public static BlockModFence dungeonLampFence;

    public static BlockMod terraniaLeaves;
    public static BlockMod terraniaVine;
    public static BlockMod terranianDirt;
    public static BlockModLog terranianLog;
    public static BlockMod terranianGrass;
    public static BlockMod terranianStone;
    public static BlockMod terranianBars;
    public static BlockMod terranianDarkPanels;
    public static BlockMod terranianPanels;
    public static BlockModFlower terranianTallgrass;
    public static BlockModFlower terramushroom;
    public static BlockModFlower tallterramushroom;
    public static BlockMod terragrow;
    public static BlockMod terraniaLamp;
    public static BlockMod terrashroomBlockPink;
    public static BlockMod terrashroomBlockPurple;
    public static BlockMod terrashroomStem;

    public static BlockMod hotBrick;
    public static BlockModFence hotBrick_fence;
    public static BlockModStairs hotBrick_stairs;

    public static BlockMod hotGlass;
    public static BlockMod smoothGlass;

    public static BlockDarkbloom darkbloomTop;
    public static BlockDarkbloom darkbloomBottom;
    public static BlockMod depthsLights;

    public static BlockGlowshroom redGlowshroomTop;
    public static BlockGlowshroom redGlowshroomBottom;

    public static BlockHellThorn hellThornTop;
    public static BlockHellThorn hellThornBottom;
    public static BlockMod hellThornRoot;
    public static BlockMod nethicGrass;

    public static BlockGlowshroom greenGlowshroomTop;
    public static BlockGlowshroom greenGlowshroomBottom;
    public static BlockGlowshroom blueGlowshroomTop;
    public static BlockGlowshroom blueGlowshroomBottom;

    public static BlockStalks goldenStalksTop;
    public static BlockStalks goldenStalksBottom;
    public static BlockModFlower goldenBulb;
    public static BlockModFlower goldenBloom;
    public static BlockModFlower goldenStalks;

    public static BlockMod workshopCarpet;
    public static BlockMod frozenGlass;
    public static BlockMod frozenLamp;
    public static BlockMod iceStone;
    public static BlockMod workshopStone;
    public static BlockModStairs workshopStoneStair;
    public static BlockModFence workshopStoneFence;
    public static BlockModDoor frozenDoor;
    public static BlockMod candyCane;
    public static BlockMod frozenBrick;
    public static BlockMod icicle;
    public static BlockModFlower frostberryThorn;
    public static BlockModFlower iceBud;
    public static BlockModFlower frozenBlooms;
    public static BlockModFlower permaFlower;
    public static BlockModFlower shiverFlower;
    public static BlockModFlower iceBush;
    public static BlockIceLog iceLog;
    public static BlockMod brittleIce;

    public static BlockModFlower eucaTallGrass;
    public static BlockModFlower eucaTallFlowers;
    public static BlockModFlower eucaBlueFlower;
    public static BlockModFlower frozenFlower;
    public static BlockModFlower depthsFlower;
    public static BlockModFlower depthsBlueFlower;
    public static BlockModFlower corbaFlower;
    public static BlockModFlower corbaTallGrass;
    public static BlockModFlower corbaSpeckledFlower;
    public static BlockModFlower corbaLightPurpleFlower;
    public static BlockModFlower corbaRedFlower;
    public static BlockModFlower corbaBlueFlower;
    public static BlockModFlower corbaDarkPurpleFlower;
    public static BlockModFlower flameFlower;
    public static BlockModFlower infernoPlant;
    public static BlockModFlower burntGrass;

    public static BlockGrindstone grindstone;
    public static BlockCloudAltar cloudAltar;
    public static BlockMod blueGems;
    public static BlockMod redGems;

    public static BlockMod blackBlock;
    public static BlockMod whiteBlock;

    public static BlockMod eucaPumpkin;
    public static BlockMod cloud;

    public static BlockMod corbaLadder;
    public static BlockMod largeNetherBrick;
    public static BlockMod compactNetherBrick;
    public static BlockMod nethicLamp;

    public static BlockMod knowledgeTable;
    public static BlockMod stoneCraftingTable;
    public static BlockSummoningTable summoningTable;
    public static BlockMod bossAltar;

    public static BlockModSpawner observerSpawner;
    public static BlockModSpawner screamerSpawner;
    public static BlockModSpawner goldbotSpawner;
    public static BlockModSpawner silverbotSpawner;
    public static BlockModSpawner hellbotSpawner;
    public static BlockModSpawner purplianSpawner;
    public static BlockModSpawner starlightGolemSpawner;
    public static BlockModSpawner starlightTransporterSpawner;
    // public static BlockMod sentarAlter;
    public static BlockModSpawner sorcererSpawner;
    public static BlockModSpawner frostbiterSpawner;
    public static BlockModSpawner overseerSpawner;
    public static BlockModSpawner overseerElderSpawner;

    public static BlockMod elderBlock;
    public static BlockMod ancientMachineBlock;

    public static BlockNetherFurnace netherFurnace;
    public static BlockNetherFurnace netherFurnaceActive;

    public static void init() {

        iridiumOre = new BlockModOre("iridiumOre", "Iridium Ore", true).setHarvestLevel(EnumToolType.DIAMOND_PICK);

        sapphireOre = new BlockModOre("sapphireOre", "Sapphire Ore", false).setHarvestLevel(EnumToolType.DIAMOND_PICK);
        lavaRock = new BlockMod("lavaRock", "Lava Rock").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        shadiumOre = new BlockModOre("shadiumOre", "Shadium Ore", false).setHarvestLevel(EnumToolType.DIAMOND_PICK);
        luniumOre = new BlockModOre("luniumOre", "Lunium Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        hellstoneOre = new BlockModOre("hellstoneOre", "Hellstone Ore", false)
                .setHarvestLevel(EnumToolType.DIAMOND_PICK);
        ashualOre = new BlockModOre("ashualOre", "Ashual Ore", false);
        blaziumOre = new BlockModOre("blaziumOre", "Blazium Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        celestiumOre = new BlockModOre("celestiumOre", "Celestium Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        mekyumOre = new BlockModOre("mekyumOre", "Mekyum Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        koriteOre = new BlockModOre("koriteOre", "Korite Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        storonOre = new BlockModOre("storonOre", "Storon Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        flairiumOre = (BlockMod) new BlockModOre("flairiumOre", "Flairium Ore")
                .setHarvestLevel(EnumToolType.DIAMOND_PICK).setLightLevel(0.5F);
        desOre = (BlockMod) new BlockModOre("desOre", "Des Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK)
                .setLightLevel(0.5F);
        enderilliumOre = new BlockModOre("enderilliumOre", "Enderillium Ore")
                .setHarvestLevel(EnumToolType.DIAMOND_PICK);
        gorbiteOre = new BlockModOre("gorbiteOre", "Gorbite Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        orbaditeOre = new BlockModOre("orbaditeOre", "Orbadite Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        luniteOre = new BlockModOre("luniteOre", "Lunite Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        firestoneOre = new BlockModOre("firestoneOre", "Firestone Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);

        sapphireBlock = new BlockModOre("sapphireBlock", "Sapphire Block").setHarvestLevel(EnumToolType.STONE_PICK);
        shadiumBlock = new BlockModOre("shadiumBlock", "Shadium Block").setHarvestLevel(EnumToolType.STONE_PICK);
        luniumBlock = new BlockModOre("luniumBlock", "Lunium Block").setHarvestLevel(EnumToolType.STONE_PICK);
        hellstoneBlock = new BlockModOre("hellstoneBlock", "Hellstone Block").setHarvestLevel(EnumToolType.STONE_PICK);
        ashualBlock = new BlockModOre("ashualBlock", "Ashual Block");
        blaziumBlock = new BlockModOre("blaziumBlock", "Blazium Block").setHarvestLevel(EnumToolType.STONE_PICK);
        celestiumBlock = new BlockModOre("celestiumBlock", "Celestium Block").setHarvestLevel(EnumToolType.STONE_PICK);
        mekyumBlock = new BlockModOre("mekyumBlock", "Mekyum Block").setHarvestLevel(EnumToolType.STONE_PICK);
        koriteBlock = new BlockModOre("koriteBlock", "Korite Block").setHarvestLevel(EnumToolType.STONE_PICK);
        storonBlock = new BlockModOre("storonBlock", "Storon Block").setHarvestLevel(EnumToolType.STONE_PICK);
        flairiumBlock = new BlockModOre("flairiumBlock", "Flairium Block").setHarvestLevel(EnumToolType.STONE_PICK);
        desBlock = new BlockModOre("desBlock", "Des Block").setHarvestLevel(EnumToolType.STONE_PICK);
        enderilliumBlock = new BlockModOre("enderilliumBlock", "Enderillium Block")
                .setHarvestLevel(EnumToolType.STONE_PICK);
        gorbiteBlock = new BlockModOre("gorbiteBlock", "Gorbite Block").setHarvestLevel(EnumToolType.STONE_PICK);
        orbaditeBlock = new BlockModOre("orbaditeBlock", "Orbadite Block").setHarvestLevel(EnumToolType.STONE_PICK);
        luniteBlock = new BlockModOre("luniteBlock", "Lunite Block").setHarvestLevel(EnumToolType.STONE_PICK);
        iridiumBlock = new BlockModOre("iridiumBlock", "Iridium Block").setHarvestLevel(EnumToolType.IRON_PICK);
        nethicGemstoneBlock = new BlockModOre("nethicGemstoneBlock", "Nethic Gemstone Block").setHarvestLevel(EnumToolType.STONE_PICK);

        mageWall = new BlockMod("magewall", "Mage Wall");
        bleedstone = (BlockMod) new BlockModOre("bleedstoneOre", "Bleedstone", false)
                .setHarvestLevel(EnumToolType.DIAMOND_PICK).setLightLevel(10.5F);
        smithstone = (BlockMod) new BlockModOre("smithstoneOre", "Smithstone", false)
                .setHarvestLevel(EnumToolType.DIAMOND_PICK).setLightLevel(10.5F);

        bloodRock = new BlockMod("bloodRock", "Blood Rock");
        bloodBricks = new BlockMod("bloodBricks", "Blood Bricks");
        carvedBloodRock = new BlockMod("carvedBloodRock", "Carved Blood Rock");
        bloodRune = new BlockMod("bloodRune", "Blood Rune");
        bloodPillar = new BlockMod("bloodPillar", "Blood Pillar");
        obelisk = (BlockMod) new BlockObelisk("obelisk", "Obelisk").setLightLevel(0.5F);
        bloodLamp = (BlockMod) new BlockMod("bloodLamp", "Blood Lamp").setLightLevel(2.0F);
        bloodCatalyst = (BlockMod) new BlockMod("bloodCatalyst", "Blood Catalyst").setLightLevel(0.1F);

        witherwoodLog = new BlockModLog("witherwoodLog", "Witherwood Log");
        witherwoodLeaves = new BlockModLeaves("witherwoodLeaves", "Witherwood Leaves", 1.0F);
        sizzlerWoodLeaves = new BlockModLeaves("sizzlerWoodLeaves", "Deadblood Leaves", 2.0F);
        sizzlerWoodLog = new BlockModLog("sizzlerWoodLog", "Deadblood Bark");
        deathGrass = new BlockNetherFlower("deathGrass", "Death Grass", true);
        netherweed = new BlockNetherFlower("netherWeeds", "Netherweed", true);
        hellBell = new BlockNetherFlower("hellBell", "Hell Bell");
        hellshroom = new BlockNetherFlower("hellshroom", "Hellshroom");

        earthenNetherLeaves = new BlockModLeaves("earthenNetherLeaves", "Earthenwood Leaves", 2.0F);
        earthenNetherLog = new BlockModLog("earthenNetherLog", "Earthenwood Log");
        earthenNetherPlanks = new BlockMod(EnumMaterialTypes.WOOD, "earthenNetherPlank", "Earthenwood Planks", 1.0F);
        earthenNetherTallGrass = new BlockNetherFlower("earthenNetherTallGrass", "Earthen Nethigrass", true);
        earthenNetherShortGrass = new BlockNetherFlower("earthenNetherShortGrass", "Earthen Nethigrass", true);
        earthenNetherFlower = new BlockNetherFlower("earthenNetherFlower", "Earthen Heatflower");

        heatSoil = new BlockMod(EnumMaterialTypes.DIRT, "heatSoil", "Nethic Soil", 0.5F);
        earthenNetherrack = new BlockMod(EnumMaterialTypes.STONE, "earthenNetherrack", "Earthen Netherrack", 2.0F);
        heatSand = new BlockModGravity(EnumMaterialTypes.SAND, "heatSand", "Heat Sand", 0.5F);
        //sizzleSand = new BlockModGravity(EnumMaterialTypes.SAND, "sizzleSand", "Sizzle Sand", 0.5F);

        igniterOn = (BlockMod) new BlockIgniter("igniterOn", "Redstone Igniter").setCreativeTab(null);
        igniter = new BlockIgniter("igniter", "Redstone Igniter");
        greenGemBlock = new BlockMod("greenGemBlock", "Green Gem Block");
        purpleGemBlock = new BlockMod("purpleGemBlock", "Purple Gem Block");
        blueGemBlock = new BlockMod("blueGemBlock", "Blue Gem Block");
        yellowGemBlock = new BlockMod("yellowGemBlock", "Yellow Gem Block");

        nethicanSludge = new BlockMod(EnumMaterialTypes.SLIME, "nethicanSludge", "Nethican Sludge", 1.0F);

        eucaSapling = new BlockModSapling("eucaSapling", "Euca Sapling", new WorldGenEucaTree());
        boilSapling = new BlockModSapling("boilSapling", "Boiling Sapling", new WorldGenBoilTree1());
        corbaSapling = new BlockModSapling("corbaSapling", "Corba Sapling", new WorldGenCorbaSmallTree());
        depthsSapling = new BlockModSapling("depthsSapling", "Depths Sapling", new WorldGenTerraniaSmallTree());
        terraniaSapling = new BlockModSapling("terraniaSapling", "Terrania Sapling", new WorldGenEucaTree());
        netherSapling = new BlockModSapling("netherSapling", "Deadblood Sapling", new WorldGenBleedheartTree0());
        frozenIceSapling = new BlockModSapling("frozenIceSapling", "Ice Sapling", new WorldGenIceTree());
        //frozenSapling = new BlockModSapling("frozenSapling", "Frozen Sapling", new WorldGenFrozenTree(false, false));

        eucaDirt = new BlockMod(EnumMaterialTypes.DIRT, "eucaDirt", "Euca Dirt", 2.0F);
        eucaGrass = new BlockModGrass(eucaDirt, "eucaGrass", "Euca Grass", 2.0F);
        eucaSilverGrass = new BlockModGrass(eucaDirt, "eucaSilverGrass", "Euca Grass", 2.0F);
        eucaStone = new BlockMod("eucaStone", "Euca Stone", 2.0F);
        eucaGoldLog = new BlockModLog("eucaGoldLog", "Golden Euca Log");
        goldEucaPlank = new BlockMod(EnumMaterialTypes.WOOD, "goldEucaPlank", "Golden Euca Plank", 1.0F);
        eucaGoldStairs = new BlockModStairs(eucaGoldLog, "eucaGoldStairs", "Golden Euca Stairs");
        eucaGoldLeaves = new BlockModLeaves("eucaGoldLeaves", "Golden Euca Leaves", 1.0F);
        eucaSilverLeaves = new BlockModLeaves("eucaSilverLeaves", "Silver Euca Leaves", 1.0F);
        eucaLightGreenLeaves = new BlockModLeaves("eucaLightGreenLeaves", "Light Green Euca Leaves", 1.0F);
        eucaDarkGreenLeaves = new BlockModLeaves("eucaDarkGreenLeaves", "Dark Green Euca Leaves", 1.0F);

        eucaBricks = new BlockMod("eucaBricks", "Euca Bricks", 2.0F);
        eucaTile = new BlockMod("eucaTile", "Euca Tile", 2.0F);
        depthsDirt = new BlockMod(EnumMaterialTypes.DIRT, "depthsDirt", "Depths Dirt", 2.0F);
        depthsGrass = new BlockModGrass(depthsDirt, "depthsGrass", "Depths Grass", 2.0F);
        depthsStone = new BlockMod("depthsStone", "Depths Stone", 2.0F);
        depthsLog = new BlockModLog("depthsLog", "Depths Log");
        depthsPlank = new BlockMod(EnumMaterialTypes.WOOD, "depthsPlank", "Depths Plank", 1.0F);
        depthsStairs = new BlockModStairs(depthsPlank, "depthsStairs", "Depths Stairs");
        depthsLeaves = new BlockDepthsLeaves("depthsLeaves", "Depths Leaves", 1.0F);
        darkBrick = (BlockMod) new BlockMod("darkBrick", "Dark Brick", 2.0F).setBlockUnbreakable()
                .setResistance(10000000.0F);
        darkFloor = (BlockMod) new BlockMod("darkFloor", "Dark Floor", 2.0F).setBlockUnbreakable()
                .setResistance(10000000.0F);
        darkShingle = (BlockMod) new BlockMod("darkShingle", "Dark Shingle", 2.0F).setBlockUnbreakable()
                .setResistance(10000000.0F);

        depthsCarpet = new BlockMod("depthsCarpet", "Depths Carpet");
        depthsBookshelf = new BlockMod("depthsBookshelf", "Depths Bookshelf");
        depthsBrick = new BlockMod("depthsBrick", "Depths Brick");
        depthsGlass = new BlockModGlass("depthsGlass", "Depths Glass", 1.0F);
        depthsShingle = new BlockMod("depthsShingle", "Depths Shingle", 1.0F);
        depthsCobblestone = new BlockMod("depthsCobblestone", "Depths Cobblestone", 1.0F);
        depthsTile = new BlockMod("depthsTile", "Depths Tile", 1.0F);
        depthsPillar = new BlockMod("depthsPillar", "Depths Pillar", 1.0F);
        depthsLantern = (BlockMod) new BlockMod("depthsLantern", "Depths Lantern", 1.0F).setLightLevel(1);
        depthsGate = new BlockModBars("depthsGate", "Depths Gate", 1.0F);
        depthsLock = new BlockLock("depthsLock", "Depths Lock", JourneyItems.depthsKey);
        depthsDoor = new BlockModDoor("depthsDoor", "Depths Door", Material.WOOD, 2.0F);

        iceLeaves = new BlockDepthsLeaves("iceLeaves", "Ice Leaves", 1.0F);
        frozenDirt = new BlockMod(EnumMaterialTypes.DIRT, "frozenDirt", "Frozen Dirt", 2.0F);
        frozenStone = new BlockMod(EnumMaterialTypes.STONE, "frozenStone", "Frozen Stone", 5.0F);
        frozenGrass = new BlockModGrass(frozenDirt, "frozenGrass", "Frozen Grass", 2.0F);
        frozenLeaves = (BlockMod) new BlockModLeaves("frozenLeaves", "Frozen Leaves", 0.5F).setFrozenPlant()
                .setLightLevel(1);
        frozenBark = new BlockModLog("frozenBark", "Frozen Bark");
        frozenPlanks = new BlockMod(EnumMaterialTypes.WOOD, "frozenPlanks", "Frozen Planks", 0.5F);

        corbaStone = new BlockMod("corbaStone", "Corba Stone");
        corbaGrass = new BlockModGrass(null, "corbaGrass", "Corba Grass", 0.5F);
        corbaLog = new BlockModLog("corbaLog", "Corba Log");
        corbaLeaves = new BlockModLeaves("corbaLeaves", "Corba Leaves", 0.2F);
        corbaPillar = new BlockMod("corbaPillar", "Corba Pillar");
        corbaPlank = new BlockMod(EnumMaterialTypes.WOOD, "corbaPlank", "Corba Plank", 0.5F);
        corbaLamp = (BlockMod) new BlockMod(EnumMaterialTypes.GLASS, "corbaLamp", "Corba Lamp", 0.1F)
                .setLightLevel(1.0F).setCreativeTab(JourneyTabs.decoration);
        corbaSentryBrick = new BlockMod("corbaSentryBrick", "Corba Sentry Brick");

        boilingLamp = (BlockMod) new BlockMod(EnumMaterialTypes.GLASS, "boilingLamp", "Boiling Lamp", 0.1F)
                .setLightLevel(1.0F).setCreativeTab(JourneyTabs.decoration);
        blazierBricks = new BlockMod("blazierBricks", "Blazier Bricks");
        ashBlock = new BlockMod("ashBlock", "Ash");
        hotBlock = new BlockModGrass(ashBlock, "hotGround", "Hot Ground", 2.0F);
        rubble = new BlockMod("rubble", "Rubble", 2.0F);
        /*
         * brisonStone = new BlockMod("brisonStone", "Brison Stone", 2.0F);
         * darkBrisonBrick = new BlockMod("darkBrisonBrick",
         * "Dark Brison Brick", 2.0F); redBrisonBrick = new
         * BlockMod("redBrisonBrick", "Red Brison Brick", 2.0F);
         * smallBrisonBrick = new BlockMod("smallBrisonBrick",
         * "Small Brison Brick", 2.0F);
         */

        brisonblocks = new BlockMetaBase("brison", Material.ROCK, "brison");
        boilingBars = new BlockModBars("boilingBars", "Brison Bars", 5.0F);
        burningLeaves = new BlockModLeaves("burningLeaves", "Burning Leaves", 0.5F).setBurningPlant();
        boilingLog = new BlockModLog("boilingLog", "Boiling Log");

        cloudiaDirt = new BlockMod(EnumMaterialTypes.DIRT, "cloudiaDirt", "Cloudia Dirt", 2.0F);
        cloudiaGrass = new BlockModGrass(cloudiaDirt, "cloudiaGrass", "Cloudia Grass", 2.0F);
        cloudiaLog = new BlockModLog("cloudiaLog", "Cloudia Log");
        cloudiaRock = new BlockMod("cloudiaRock", "Cloudia Rock");
        cloudiaPillar = new BlockMod("cloudiaPillar", "Cloudia Pillar");
        cloudiaPlanks = new BlockMod(EnumMaterialTypes.WOOD, "cloudwoodPlanks", "Cloudwood Planks", 1.0F);
        cloudiaBrick = (BlockMod) new BlockMod("cloudiaBrick", "Cloudia Brick").setLightLevel(0.5F);
        cloudiaWall = (BlockMod) new BlockMod("cloudiaWall", "Cloudia Wall").setLightLevel(0.5F);
        cloudiaTile = (BlockMod) new BlockMod("cloudiaTile", "Cloudia Tile").setLightLevel(0.5F);
        cloudiaLeaves = (BlockMod) new BlockModLeaves("cloudiaLeaves", "Cloudia Leaves", 0.5F).setLightLevel(2);
        cloudiaLamp = (BlockMod) new BlockMod(EnumMaterialTypes.GLASS, "cloudiaLamp", "Cloudia Lamp", 0.1F)
                .setLightLevel(1.0F).setCreativeTab(JourneyTabs.decoration);
        cloudiaCobblestone = new BlockMod("cloudiaCobblestone", "Cloudia Cobblestone");
        cloudiaBrickStairs = new BlockModStairs(cloudiaBrick, "cloudiaBrickStairs", "Cloudia Brick Stairs");
        cloudiaTileStairs = new BlockModStairs(cloudiaTile, "cloudiaTileStairs", "Cloudia Tile Stairs");
        // cloudiaPost = new BlockCloudiaPost("cloudiaPost");
        pinkCloudiaCloud = new BlockCloud("pinkCloudiaCloud", "Pink Cloudia Cloud");
        blueCloudiaCloud = new BlockCloud("blueCloudiaCloud", "Blue Cloudia Cloud");
        lightBlueCloudiaCloud = new BlockCloud("lightBlueCloudiaCloud", "Light Blue Cloudia Cloud");
        cloudiaTallGrass = new BlockModFlower("cloudiaTallGrass", "Cloudia Tall Grass");
        cloudiaFlower = new BlockModFlower("cloudiaFlower", "Cloudia Flower");

        swampLamp = (BlockMod) new BlockSwampLamp("swampLamp", "Swamp Fly Bottle", 0).setLightLevel(1);

        /*
         * frozenChest = new BlockJourneyChest("frozenChest", "Frozen Chest",
         * BlockJourneyChest.Type.FROZEN); netherChest = new
         * BlockJourneyChest("netherChest", "Nether Chest",
         * BlockJourneyChest.Type.NETHER); boilingChest = new
         * BlockJourneyChest("boilingChest", "Boiling Chest",
         * BlockJourneyChest.Type.BOIL); eucaChest = new
         * BlockJourneyChest("eucaChest", "Euca Chest",
         * BlockJourneyChest.Type.EUCA); depthsChest = new
         * BlockJourneyChest("depthsChest", "Depths Chest",
         * BlockJourneyChest.Type.DEPTHS); corbaChest = new
         * BlockJourneyChest("corbaChest", "Corba Chest",
         * BlockJourneyChest.Type.CORBA); cloudiaChest = new
         * BlockJourneyChest("cloudiaChest", "Cloudia Chest",
         * BlockJourneyChest.Type.CLOUDIA); terraniaChest = new
         * BlockJourneyChest("terraniaChest", "Terrania Chest",
         * BlockJourneyChest.Type.TERRA);
         */

        journeyChest = new BlockJourneyChest("journeychest", "Journey Chest", BlockJourneyChest.Type.JOURNEY);
        netherChest = new BlockJourneyChest("netherchest", "Nether Chest", BlockJourneyChest.Type.NETHER);
        boilChest = new BlockJourneyChest("boilchest", "Boil Chest", BlockJourneyChest.Type.BOIL);
        eucaChest = new BlockJourneyChest("eucachest", "Euca Chest", BlockJourneyChest.Type.EUCA);
        frozenChest = new BlockJourneyChest("frozenchest", "Frozen Chest", BlockJourneyChest.Type.FROZEN);
        depthsChest = new BlockJourneyChest("depthschest", "Depths Chest", BlockJourneyChest.Type.DEPTHS);
        corbaChest = new BlockJourneyChest("corbachest", "Corba Chest", BlockJourneyChest.Type.CORBA);
        terraniaChest = new BlockJourneyChest("terraniachest", "Terrania Chest", BlockJourneyChest.Type.TERRA);
        cloudiaChest = new BlockJourneyChest("cloudiachest", "Cloudia Chest", BlockJourneyChest.Type.CLOUDIA);

        lockedChest = new BlockJourneyChest("lockedchest", "Locked Chest", BlockJourneyChest.Type.JOURNEY, true, JourneyItems.boilKey);

        nethicDungeonBricks = new BlockMod("nethicDungeonBricks", "Nethic Dungeon Bricks");
        boilChain = (BlockMod) new BlockModBars("boilChain", "Boil Chain", 5.0F).setBlockUnbreakable()
                .setResistance(10000000.0F);
        boilGate = (BlockMod) new BlockModBars("boilGate", "Boil Gate", 5.0F).setBlockUnbreakable()
                .setResistance(10000000.0F);
        boilLock = new BlockLock("boilLock", "Boil Lock", JourneyItems.boilKey).setBlockUnbreakable()
                .setResistance(10000000.0F);

        darklyGate = (BlockMod) new BlockModBars("darklyGate", "Darkly Gate", 5.0F).setBlockUnbreakable()
                .setResistance(10000000.0F);
        darklyLock = (BlockMod) new BlockLock("darklyLock", "Darkly Lock", JourneyItems.darkKey).setBlockUnbreakable()
                .setResistance(10000000.0F);

        corbaPost = new BlockModFence(pinkCloudiaCloud, "corbaPost", "Corba Post");
        sizzlingPost = new BlockModFence(pinkCloudiaCloud, "sizzlingPost", "Sizzling Post");

        senterianBricks = (BlockMod) new BlockMod("senterianBricks", "Senterian Bricks").setBlockUnbreakable()
                .setResistance(10000000.0F);
        senterianRock = (BlockMod) new BlockMod("senterianRock", "Senterian Rock").setBlockUnbreakable()
                .setResistance(10000000.0F);
        senterianCarvedRock = (BlockMod) new BlockMod("senterianCarvedRock", "Senterian Carved Rock")
                .setBlockUnbreakable().setResistance(10000000.0F);
        senterianFloor = (BlockMod) new BlockMod("senterianFloor", "Senterian Floor").setBlockUnbreakable()
                .setResistance(10000000.0F);
        senterianGlass = (BlockJoinedGlass) new BlockJoinedGlass("senterianGlass", "Senterian Glass")
                .setBlockUnbreakable().setResistance(10000000.0F);
        senterianBars = (BlockMod) new BlockModBars("senterianBars", "Senterian Bars", 5.0F).setBlockUnbreakable()
                .setResistance(10000000.0F);
        senterianLightLamp = (BlockMod) new BlockMod(EnumMaterialTypes.GLASS, "senterianLightLamp",
                "Senterian Light Lamp", 0.1F).setLightLevel(0.5F).setCreativeTab(JourneyTabs.decoration)
                .setBlockUnbreakable().setResistance(10000000.0F);
        senterianMellowLamp = (BlockMod) new BlockMod(EnumMaterialTypes.GLASS, "senterianMellowLamp",
                "Senterian Mellow Lamp", 0.1F).setLightLevel(0.5F).setCreativeTab(JourneyTabs.decoration)
                .setBlockUnbreakable().setResistance(10000000.0F);
        senterianGuardianLamp = (BlockMod) new BlockMod(EnumMaterialTypes.GLASS, "senterianGuardianLamp",
                "Senterian Guardian Lamp", 0.1F).setLightLevel(0.5F).setCreativeTab(JourneyTabs.decoration)
                .setBlockUnbreakable().setResistance(10000000.0F);
        senterianPost = (BlockModFence) new BlockModFence(senterianBricks, "senterianPost", "Senterian Post")
                .setBlockUnbreakable().setResistance(10000000.0F);

        trophy = new BlockTrophy("trophy", "Trophy");
        trophySoul = new BlockTrophy("trophySoul", "Soul Watcher Trophy");
        trophyBlaze = new BlockTrophy("trophyBlaze", "Blazier Trophy");
        trophyEudor = new BlockTrophy("trophyEudor", "Eudor Trophy");
        trophyCor = new BlockTrophy("trophyCor", "Corallator Trophy");
        trophyScale = new BlockTrophy("trophyScale", "Scale Trophy");
        trophyRoc = new BlockTrophy("trophyRoc", "Thunderbird Trophy");
        trophySentry = new BlockTrophy("trophySentry", "Sentry King Trophy");
        trophyLogger = new BlockTrophy("trophyLogger", "Logger Trophy");
        trophyTerra = new BlockTrophy("trophyTerra", "Terranian Protector Trophy");
        trophyStalk = new BlockTrophy("trophyStalk", "Sky Stalker Trophy");
        trophyNetherBeast = new BlockTrophy("trophyNetherBeast", "Nether Beast Trophy");
        trophyWitherBeast = new BlockTrophy("trophyWitherBeast", "Withering Beast Trophy");

        /*
         * Mod wastelandsStone = new BlockWastelandsStone("wastelandsStone");
         * wastelandsGrass = new BlockModGrass((BlockMod)null,
         * "wastelandsGrass", 2.0F); wastelandsLog = new
         * BlockModLog("wastelandsLog"); wastelandsBranches = new
         * BlockModLeaves("wastelandsBranches",
         * 2.0F).setStepSound(EnumMaterialTypes.WOOD.getSound());
         */

        eucaPortal = new BlockEucaPortal("eucaPortal");
        eucaPortalFrame = new BlockMod("eucaPortalFrame", "Euca Portal Frame", 3.0F);

        depthsPortal = new BlockDepthsPortal("depthsPortal", "Depths Portal");
        depthsPortalFrame = new BlockDepthsPortalFrame("depthsPortalFrame", "Depths Portal Frame");

        boilPortal = new BlockBoilPortal("boilingPortal");
        boilPortalFrame = new BlockMod("boilingPortalFrame", "Boiling Portal Frame", 3.0F);

        frozenPortal = new BlockFrozenPortal("frozenPortal");
        frozenPortalFrame = new BlockMod("frozenPortalFrame", "Frozen Portal Frame", 3.0F);

        corbaPortal = new BlockCorbaPortal("corbaPortal", "Corba Portal");
        corbaPortalFrame = new BlockCorbaPortalFrame("corbaPortalFrame", "Corba Portal Frame");

        terraniaPortal = new BlockTerraniaPortal("terraniaPortal");
        terraniaPortalFrame = new BlockMod("terraniaPortalFrame", "Terrania Portal Frame", 3.0F);

        senterianPortal = new BlockSenterianPortal("senterianPortal", "senterianPortal");
        senterianPortalFrame = new BlockSenterianPortalFrame("senterianPortalFrame",
                "Senterian Portal Frame");
        // WastelandsPortal wastelandsPortal = (BlockWastelandsPortal)new
        // BlockWastelandsPortal("wastelandsPortal");
        // wastelandsPortalFrame = new BlockMod("wastelandsPortalFrame");
        cloudiaPortal = new BlockCloudiaPortal("cloudiaPortal");
        cloudiaPortalFrame = new BlockMod("cloudiaPortalFrame", "Cloudia Portal Frame", 3.0F);

        depthsLights = (BlockMod) new BlockMod(EnumMaterialTypes.GLASS, "depthsLight", "Depths Light", 0.5F)
                .setLightLevel(1.0F);

        fire = new BlockModFire("fire", "Essential Fire");

        caveVine = new BlockCaveVine("cavevine", "Cave Vine");

        mossyEssenceStone = new BlockMod("mossyEssenceStone", "Mossy Essence Stone", 2.0F);

        dungeonBrick = (BlockMod) new BlockMod(EnumMaterialTypes.STONE, "dungeonBrick", "Dungeon Brick", -1F)
                .setResistance(10000F);
        dungeonCrackedBrick = (BlockMod) new BlockMod(EnumMaterialTypes.STONE, "dungeonCrackedBrick",
                "Dungeon Cracked Brick", -1F).setResistance(10000F);
        dungeonChisledBrick = (BlockMod) new BlockMod(EnumMaterialTypes.STONE, "dungeonChiseledBrick",
                "Dungeon Chiseled Brick", -1F).setResistance(10000F);
        dungeonBrickCarved = (BlockMod) new BlockMod(EnumMaterialTypes.STONE, "dungeonBrickCarved",
                "Dungeon Carved Brick", -1F).setResistance(10000F);
        dungeonLamp = (BlockMod) new BlockMod(EnumMaterialTypes.STONE, "dungeonLamp", "Dungeon Lamp", -1F)
                .setLightLevel(0.5F).setResistance(10000F);

        dungeonBrickStairs = (BlockModStairs) new BlockModStairs(dungeonBrick, "dungeonBrickStairs",
                "Dungeon Brick Stairs").setResistance(10000F);
        dungeonCrackedBrickStairs = (BlockModStairs) new BlockModStairs(dungeonCrackedBrick,
                "dungeonCrackedBrickStairs", "Cracked Dungeon Brick Stairs").setResistance(10000F);
        dungeonChisledBrickStairs = (BlockModStairs) new BlockModStairs(dungeonChisledBrick,
                "dungeonChiseledBrickStairs", "Chiseled Dungeon Brick Stairs").setResistance(10000F);
        dungeonBrickCarvedStairs = (BlockModStairs) new BlockModStairs(dungeonBrickCarved, "dungeonBrickCarvedStairs",
                "Carved Dungeon Brick Stairs").setResistance(10000F);
        dungeonLampStairs = (BlockModStairs) new BlockModStairs(dungeonLamp, "dungeonLampStairs", "Dungeon Lamp Stairs",
                true).setResistance(10000F);

        dungeonBrickFence = (BlockModFence) new BlockModFence(dungeonBrick, "dungeonBrickFence", "Dungeon Brick Fence")
                .setResistance(10000F);
        dungeonCrackedBrickFence = (BlockModFence) new BlockModFence(dungeonCrackedBrick, "dungeonCrackedBrickFence",
                "Cracked Dungeon Brick Fence").setResistance(10000F);
        dungeonChisledBrickFence = (BlockModFence) new BlockModFence(dungeonChisledBrick, "dungeonChiseledBrickFence",
                "Chiseled Dungeon Brick Fence").setResistance(10000F);
        dungeonBrickCarvedFence = (BlockModFence) new BlockModFence(dungeonBrickCarved, "dungeonBrickCarvedFence",
                "Carved Dungeon Brick Fence").setResistance(10000F);
        cloudiaPost = new BlockModFence(pinkCloudiaCloud, "cloudiaPost", "Cloudia Post");
        terranianPost = new BlockModFence(pinkCloudiaCloud, "terranianPost", "Terranian Post");
        dungeonLampFence = new BlockModFence(dungeonLamp, "dungeonLampFence", "Dungeon Lamp Fence", true);

        terraniaLeaves = new BlockModLeaves("terranianLeaves", "Terrania Leaves", 1.0F);
        terraniaVine = new BlockModVine("terranianVine", "Terrania Vine", 12);
        terranianDirt = new BlockMod(EnumMaterialTypes.DIRT, "terranianDirt", "Terranian Dirt", 2.0F);
        terranianLog = new BlockModLog("terranianLog", "Terranian Log");
        terranianGrass = new BlockModGrass(terranianDirt, "terranianGrass", "Terranian Grass", 2.0F);
        terranianStone = new BlockMod("terranianStone", "Terranian Stone", 2.0F);
        terranianBars = new BlockModBars("terranianBars", "Terranian Bars", 5.0F);
        terranianDarkPanels = new BlockMod("terranianDarkPanels", "Terranian Dark Panels", 2.0F);
        terranianPanels = new BlockMod("terranianPanels", "Terranian Panels", 2.0F);
        terranianTallgrass = new BlockModFlower("terranianTallgrass", "Terranian Tallgrass", true);
        terramushroom = new BlockModMushroom("terramushroom", "Terranian Shroom").setLightLevel(5.0F);
        tallterramushroom = new BlockModFlower("tallterramushroom", "Tall Terranian Shroom").setLightLevel(5.0F);
        terragrow = new BlockTerraFlower("terragrow", "Terranian Flower");
        terraniaLamp = (BlockMod) new BlockMod(EnumMaterialTypes.GLASS, "terraniaLamp", "Terrania Lamp", 0.1F)
                .setLightLevel(1.0F).setCreativeTab(JourneyTabs.decoration);

        terrashroomBlockPink = new BlockJourneyMushroom(EnumMaterialTypes.WOOD, "terrashroomBlockPink",
                "Terrashroom Block", 0.5F, terramushroom);
        terrashroomBlockPurple = new BlockJourneyMushroom(EnumMaterialTypes.WOOD, "terrashroomBlockPurple",
                "Terrashroom Block", 0.5F, terramushroom);
        // terrashroomStem = new
        // BlockMod(EnumMaterialTypes.WOOD,"terrashroomStem", "Terrashroom
        // Stem", 0.5F);

        hotBrick = new BlockMod("hotBrick", "Hot Brick", 2.0F);
        hotBrick_fence = new BlockModFence(hotBrick, "hotBrickFence", "Hot Brick Fence");
        hotBrick_stairs = new BlockModStairs(hotBrick, "hotBrickStairs", "Hot Brick Stairs");

        hotGlass = new BlockJoinedGlass("hotGlass", "Hot Glass");
        smoothGlass = new BlockJoinedGlass("smoothGlass", "Smooth Glass");
        // hotGlassPane = new BlockJoinedPane("hotGlass");
        // smoothGlassPane = new BlockJoinedPane("smoothGlass");

        // witheringBeastStatue = new BlockStatue("witheringBeastStatue", new
        // ModelWitheringBeastStatue(), EnumSounds.WITHER);
        // netherBeastStatue = new BlockStatue("netherBeastStatue", new
        // ModelNetherBeastStatue(), EnumSounds.NETHER_BEAST);
        // calciaStatue = new BlockStatue("calciaStatue", new
        // ModelCalciaStatue(), EnumSounds.CALCIA);
        // eudorStatue = new BlockStatue("eudorStatue", new ModelEudorStatue(),
        // EnumSounds.CALCIA);
        // enderChampionStatue = new BlockStatue("enderChampionStatue", new
        // ModelEnderChampionStatue());
        // wraithStatue = new BlockStatue("wraithStatue", new
        // ModelWraithStatue());

        darkbloomTop = new BlockDarkbloom("darkbloomTop", "Darkbloom", true);
        darkbloomBottom = new BlockDarkbloom("darkbloomBottom", "Darkbloom", false);

        hellThornTop = new BlockHellThorn("hellThornTop", "Hell Thorn", true);
        hellThornBottom = new BlockHellThorn("hellThornBottom", "Hell Thorn", false);
        hellThornRoot = new BlockRoot("hellThornRoot", "HellThornRoot");
        nethicGrass = new BlockModGrass(null, "nethicGrass", "Nethic Grass", 2.0F);

        redGlowshroomTop = new BlockGlowshroom("redGlowshroomTop", "Red Glowshroom", true);
        redGlowshroomBottom = new BlockGlowshroom("redGlowshroomBottom", "Red Glowshroom", false);
        greenGlowshroomTop = new BlockGlowshroom("greenGlowshroomTop", "Green Glowshroom", true);
        greenGlowshroomBottom = new BlockGlowshroom("greenGlowshroomBottom", "Green Glowshroom", false);
        blueGlowshroomTop = new BlockGlowshroom("blueGlowshroomTop", "Blue Glowshroom", true);
        blueGlowshroomBottom = new BlockGlowshroom("blueGlowshroomBottom", "Blue Glowshroom", false);

        goldenStalksTop = (BlockStalks) new BlockStalks("goldenStalksTop", "Golden Stalks", true).setCreativeTab(null);
        goldenStalksBottom = (BlockStalks) new BlockStalks("goldenStalksBottom", "Golden Stalks", false)
                .setCreativeTab(null);
        goldenBulb = (BlockModFlower) new BlockModFlower("goldenBulb", "Golden Bulb").setCreativeTab(null);
        goldenBloom = (BlockModFlower) new BlockModFlower("goldenBloom", "Golden Bloom").setCreativeTab(null);
        goldenStalks = (BlockModFlower) new BlockModFlower("goldenStalks", "Golden Stalks").setCreativeTab(null);

        workshopCarpet = new BlockMod(EnumMaterialTypes.WOOL, "workshopCarpet", "Workshop Carpet", 0.5F);
        frozenGlass = new BlockJoinedGlass("frozenGlass", "Frozen Glass");
        // frozenPane = new BlockJoinedPane("frozenGlass");
        frozenLamp = (BlockMod) new BlockMod(EnumMaterialTypes.GLASS, "frozenLamp", "Frozen Lamp", 0.5F).setLightLevel(1.0F).setCreativeTab(JourneyTabs.decoration);
        iceStone = (BlockMod) new BlockMod(EnumMaterialTypes.GLASS, "iceStone", "Ice Stone", 0.5F).setLightLevel(1.0F).setCreativeTab(JourneyTabs.decoration);
        workshopStone = new BlockMod(EnumMaterialTypes.STONE, "workshopStone", "Workshop Stone", 0.5F);
        workshopStoneStair = new BlockModStairs(workshopStone, "workshopStairs", "Workshop Stairs");
        workshopStoneFence = new BlockModFence(workshopStone, "workshopStoneFence", "Workshop Fence");
        frozenDoor = new BlockModDoor("frozenDoorBlock", "Workshop Fence", Material.WOOD, 5F);
        candyCane = new BlockMod(EnumMaterialTypes.WOOD, "candyCane", "Candy Cane", 2.0F);
        frozenBrick = new BlockMod("frozenBricks", "Frozen Bricks");
        icicle = new BlockIcicle("icicle", "Icicle");
        frostberryThorn = new BlockModFlower("frostberryThorn", "Frostberry Thorn").setContactDamage().setFrozenPlant();
        iceBud = new BlockModFlower("iceBud", "Ice Bud").setFrozenPlant();
        frozenBlooms = new BlockModFlower("frozenBlooms", "Frozen Blooms").setFrozenPlant();
        permaFlower = new BlockModFlower("permaFlower", "Perma Flower").setFrozenPlant();
        shiverFlower = new BlockModFlower("shiverFlower", "Shiver Flower").setFrozenPlant();
        iceBush = new BlockModFlower("iceBush", "Ice Bush").setFrozenPlant();
        iceLog = new BlockIceLog();
        brittleIce = new BlockBrittleIce(JourneyBlocks.brittleIce, "brittleIce", "Brittle Ice", 2);

        eucaTallGrass = new BlockModFlower("eucaTallGrass", "Euca Tall Grass", true);
        eucaTallFlowers = new BlockModFlower("eucaTallFlowers", "Euca Tall Flowers");
        eucaBlueFlower = new BlockModFlower("eucaBlueFlower", "Euca Blue Flower");
        frozenFlower = new BlockModFlower("frozenFlower", "Frozen Flower");
        depthsFlower = new BlockModFlower("depthsFlower", "Depths Flower");
        depthsBlueFlower = new BlockModFlower("depthsBlueFlower", "Depths Blue Flower").setLightLevel(0.625F);
        corbaFlower = new BlockModFlower("corbaFlower", "Corba Flower");
        corbaTallGrass = new BlockModFlower("corbaTallGrass", "Corba Tall Grass", true);
        corbaSpeckledFlower = new BlockModFlower("corbaSpeckledFlower", "Speckled Flower");
        corbaLightPurpleFlower = new BlockModFlower("corbaLightPurpleFlower", "Light Purple Flower");
        corbaRedFlower = new BlockModFlower("corbaRedFlower", "Red Flower");
        corbaBlueFlower = new BlockModFlower("corbaBlueFlower", "Blue Flower");
        corbaDarkPurpleFlower = new BlockModFlower("corbaDarkPurpleFlower", "Dark Purple Flower");
        flameFlower = new BlockModFlower("flameFlower", "Flame Flower");
        infernoPlant = new BlockModFlower("infernoPlant", "Inferno Plant");
        burntGrass = new BlockModFlower("burntGrass", "Burnt Grass", true);

        grindstone = new BlockGrindstone("grindstone", "Grindstone");
        cloudAltar = new BlockCloudAltar("cloudaltar", "Cloud Altar");
        blueGems = new BlockGemBlock("blueGems", "Blue Gems", false);
        redGems = new BlockGemBlock("redGems", "Red Gems", true);

        blackBlock = new BlockMod(EnumMaterialTypes.STONE, "blackBlock", "Black Block", 0.5F);
        whiteBlock = new BlockMod(EnumMaterialTypes.STONE, "whiteBlock", "White Block", 0.5F);

        eucaPumpkin = new BlockEucaPumpkin("eucaPumpkin", "Euca Pumpkin");
        cloud = new BlockCloud("cloud", "Cloud");

        corbaLadder = new BlockModLadder("corbaLadder", "Corba Ladder", 0);
        largeNetherBrick = new BlockMod("largeNetherBrick", "Large Nether Brick");
        compactNetherBrick = (BlockMod) new BlockMod("compactNetherBrick", "Compact Nether Brick").setBlockUnbreakable()
                .setResistance(10000000.0F);
        nethicLamp = (BlockMod) new BlockMod(EnumMaterialTypes.GLASS, "nethicLamp", "Nethic Lamp", 0.1F)
                .setLightLevel(1).setCreativeTab(JourneyTabs.decoration);

        // stoneCraftingTable = (BlockMod) new
        // BlockCrafting("stoneCraftingTable", "Stone Crafting
        // Table").setCreativeTab(JourneyTabs.machineBlocks);
        summoningTable = new BlockSummoningTable("summoningTable", "Summoning Table");

        observerSpawner = new BlockModSpawner("observerSpawner", "Observer Spawner", "observer");
        screamerSpawner = new BlockModSpawner("screamerSpawner", "Screamer Spawner", "screamer");

        goldbotSpawner = new BlockModSpawner("goldbotSpawner", "Goldbot Spawner", "goldbot");
        silverbotSpawner = new BlockModSpawner("silverbotSpawner", "Silverbot Spawner", "silverbot");

        hellbotSpawner = new BlockModSpawner("hellbotSpawner", "Hellbot Spawner", "hellbot");

        purplianSpawner = new BlockModSpawner("purplianSpawner", "Purplian Spawner", "purplian");
        starlightGolemSpawner = new BlockModSpawner("starlightGolemSpawner", "Starlight Golem Spawner",
                "starlightGolem");
        starlightTransporterSpawner = new BlockModSpawner("starlightTransporterSpawner",
                "Starlight Transporter Spawner", "starlightTransporter");
        // sentarAlter = new BlockMod("sentarAlter", "Sentar Alter");
        sorcererSpawner = new BlockModSpawner("sorcererSpawner", "Sorcerer Spawner", "darkSorcerer");
        frostbiterSpawner = new BlockModSpawner("frostbiterSpawner", "Frostbiter Spawner", "frozenFrostbiter");
        overseerSpawner = new BlockModSpawner("overseerSpawner", "Overseer Spawner", "overseer");
        overseerElderSpawner = new BlockModSpawner("overseerElderSpawner", "Overseer Elder Spawner", "overseerElder");

        elderBlock = new BlockChangeable("elderBlock", "Elder Block", JourneyItems.elderKey,
                JourneyBlocks.overseerElderSpawner);
        ancientMachineBlock = new BlockAncientBlock("ancientMachineBlock", "Ancient Machine Block");

        netherFurnace = new BlockNetherFurnace("netherFurnace", "Nethic Furnace", false);
        netherFurnaceActive = (BlockNetherFurnace) new BlockNetherFurnace("netherFurnaceActive", "Nethic Furnace", true)
                .setLightLevel(0.7F).setCreativeTab(null);

        sizzleberryBush = new BlockModBush("sizzleberryBush", "Sizzleberry Bush", JourneyConsumables.sizzleberry, true);
        bradberryBush = new BlockModBush("bradberryBush", "Bradberry Bush", JourneyConsumables.bradberry, false);
        tangleberryBush = new BlockModBush("tangleberryBush", "Tangleberry Bush", JourneyConsumables.tangleberry,
                false);
        juiceberryBush = new BlockModBush("juiceberryBush", "Juiceberry Bush", JourneyConsumables.juiceberry, false);
        bogberryBush = new BlockModBush("bogberryBush", "Bogberry Bush", JourneyConsumables.bogberry, false);
    }
}