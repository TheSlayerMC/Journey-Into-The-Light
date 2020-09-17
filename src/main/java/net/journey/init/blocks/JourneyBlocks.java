package net.journey.init.blocks;

import net.journey.api.block.GroundPredicate;
import net.journey.blocks.*;
import net.journey.blocks.base.*;
import net.journey.blocks.containers.*;
import net.journey.blocks.meta.BlockMetaBase;
import net.journey.blocks.plant.*;
import net.journey.blocks.portal.*;
import net.journey.common.knowledge.EnumKnowledgeType;
import net.journey.dimension.boil.trees.WorldGenBoilTree1;
import net.journey.dimension.corba.gen.trees.WorldGenCorbaSmallTree;
import net.journey.dimension.corba.gen.trees.WorldGenCorbaSwampTree;
import net.journey.dimension.depths.gen.WorldGenDepthsTree;
import net.journey.dimension.euca.gen.trees.WorldGenEucaTree;
import net.journey.dimension.frozen.gen.WorldGenFrozenTree;
import net.journey.dimension.frozen.gen.trees.WorldGenIceTree;
import net.journey.dimension.nether.gen.trees.WorldGenBleedheartTree0;
import net.journey.dimension.nether.gen.trees.WorldGenEarthenTree;
import net.journey.dimension.terrania.gen.shroom.WorldGenTerrashroom;
import net.journey.dimension.terrania.gen.trees.WorldGenTerraniaTree;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneyTabs;
import net.journey.init.items.JourneyConsumables;
import net.journey.init.items.JourneyItems;
import net.journey.util.Initializer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.EnumToolType;
import net.slayer.api.block.*;

import java.util.ArrayList;

public class JourneyBlocks {
    public static ArrayList<Block> blocks = new ArrayList<>();
    public static ArrayList<Item> itemBlocks = new ArrayList<>();

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
    public static BlockMod frostGemBlock;

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

    public static JBlockLog witherwoodLog;
    public static BlockMod witherwoodLeaves;
    public static BlockMod sizzlerWoodLeaves;
    public static JBlockLog sizzlerWoodLog;
    public static JBlockTallGrass deathGrass;
    public static JBlockFlower netherweed;
    public static JBlockFlower hellBell;
    public static JBlockDoublePlant hellshroom;

    public static BlockMod earthenNetherLeaves;
    public static JBlockLog earthenNetherLog;
    public static BlockMod earthenNetherPlanks;

    public static JBlockTallGrass earthenTallNethigrass;
    public static JBlockTallGrass earthenShortNethigrass;
    public static JBlockFlower earthenHeatflower;

    public static BlockMod heatSoil;
    public static BlockMod earthenNetherrack;
    public static BlockModGravity heatSand;
    public static BlockModGravity volcanicSand;

    public static BlockMod igniterOn;
    public static BlockMod igniter;
    public static BlockMod greenGemBlock;
    public static BlockMod purpleGemBlock;
    public static BlockMod blueGemBlock;
    public static BlockMod yellowGemBlock;

    public static JBlockTNT magicExplosive;

    public static BlockMod ironLootBox;
    public static BlockMod goldLootBox;
    public static BlockMod diamondLootBox;
    public static BlockMod overgrownLootBox;

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
    public static BlockMod bogwoodSapling;
    public static BlockMod netherSapling;
    public static BlockMod EARTHEN_SAPLING;
    public static BlockMod frozenSapling;
    public static BlockMod frozenIceSapling;

    public static BlockMod eucaGrass;
    public static BlockMod eucaSilverGrass;
    public static BlockMod eucaGolditeGrass;
    public static BlockMod eucaDirt;
    public static BlockMod eucaGolditeDirt;
    public static BlockMod eucaStone;
    public static JBlockLog eucaGoldLog;
    public static BlockMod goldEucaPlank;
    public static BlockModStairs eucaGoldStairs;
    public static BlockMod eucaGoldLeaves;
    public static BlockMod eucaSilverLeaves;
    public static BlockMod eucaLightGreenLeaves;
    public static BlockMod eucaDarkGreenLeaves;

    public static BlockObelisk ANCIENT_OBELISK;
    public static BlockMod ANCIENT_SOCKET;
    
    public static BlockMod ANCIENT_STONE;
    public static BlockMod ANCIENT_CATALYST;
    public static BlockMod ANCIENT_RUNIC_STONE_0;
    public static BlockMod ANCIENT_RUNIC_STONE_1;
    public static BlockMod ANCIENT_RUNIC_STONE_2;
    public static BlockMod ANCIENT_RUNIC_STONE_3;
    
    public static JBlockLog GOLDITE_OAK_LOG;
    
    public static BlockMod eucaBricks;
    public static BlockMod eucaTile;
    
    public static BlockMod EUCA_SQUARE_DUNGEON_BRICKS;
    public static BlockModStairs EUCA_DUNGEON_BRICK_STAIRS;
    public static BlockMod EUCA_DUNGEON_BRICKS;
    public static BlockMod EUCA_SQUARE_RUNIC_BRICKS;
    public static BlockMod EUCA_RUNIC_BRICKS;
    public static BlockMod EUCA_RUNIC_LAMP;
    public static BlockMod EUCA_DUNGEON_TILE;
    public static BlockMod EUCA_GOLD_STONE;
    
    public static BlockMod depthsDirt;
    public static BlockMod depthsGrass;
    public static BlockMod depthsStone;
    public static JBlockLog depthsLog;
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
    public static BlockMod depthsLamp;
    public static JBlockPane depthsGate;
    public static BlockMod depthsLock;
    public static BlockMod depthsTile;
    public static BlockModDoor depthsDoor;

    public static BlockMod iceLeaves;
    public static BlockMod frozenDirt;
    public static BlockMod frozenStone;
    public static BlockMod frozenGrass;
    public static BlockMod frozenLeaves;
    public static JBlockLog frozenLog;
    public static BlockMod frozenPlanks;

    public static BlockMod withanDirt;
    public static BlockMod withanRock;
    public static BlockMod withanGrass;
    public static BlockMod withanLeaves;
    public static JBlockLog withanBark;
    public static BlockMod withanRockReinforced;
    public static BlockMod withanLamp;
    public static BlockMod withanLight;

    public static BlockMod corbaCrackedBricks;
    public static BlockMod corbaDarkBricks;
    public static BlockMod corbaBricks;
    public static BlockMod corbaLightBricks;
    
    public static BlockMod corbaStone;
    public static BlockMod corbaCobblestone;
    public static JBlockWall corbaWall;
    public static BlockMod corbaGrass;
    public static JBlockLog corbaLog;
    public static BlockMod corbaLeaves;
    public static BlockMod corbaPillar;
    public static BlockMod corbaPlank;
    public static BlockMod corbaLamp;
    public static BlockMod corbaSentryBrick;
    public static BlockMod corbaGrassPath;

    public static BlockMod taintedMud;
    public static BlockMod driedMud;
    public static JBlockLog bogwoodLog;
    public static BlockModLeaves bogwoodLeaves;
    public static JBlockDoublePlant bogweed;

    public static JBlockWaterLily swampLily;

    public static JBlockFungalShelf swamp_shelf;
    public static JBlockMushroom bogshroomsSmall;
    public static JBlockMushroom bogshroomTall;

    public static JBlockMushroom enchantedShroomsSmall;
    public static JBlockMushroom enchantedShroomTall;

    public static JBlockSlab corbaPlankSlab;
    public static JBlockSlab corbaPlankDoubleSlab;

    public static BlockModStairs corbaBrickStairs;
    public static BlockModStairs corbaPlankStairs;

    public static BlockSlime slime;

    public static BlockMod totemBase;
    public static BlockMod totemAngry;
    public static BlockMod totemHappy;
    public static BlockMod totemScared;
    public static BlockMod totemSad;

    public static BlockMod boilingLamp;
    public static BlockMod blazierBricks;
    public static BlockMod ashBlock;
    public static BlockMod hotBlock;
    public static BlockMod charredGrass;
    public static BlockMod rubble;
    public static BlockMod scorchedRubble;
    public static Block scorchedCactus;

    public static BlockMod scorchedStalagmiteLarge;
    public static BlockMod scorchedStalagmiteMedium;
    public static BlockMod scorchedStalagmiteSmall;
    public static BlockMod scorchedStalagmiteTiny;

    public static BlockMetaBase brisonblocks;
    public static JBlockPane boilingBars;
    public static BlockMod burningLeaves;
    public static JBlockLog boilingLog;
    public static BlockMod boilCobble;
    public static BlockMod boilPillar;
    public static BlockMod boilShingle;
    public static BlockMod boilSquareBrick;
    public static BlockMod boilBricks;
    public static JBlockDoublePlant tallCrumblingPlant;
    public static JBlockDoublePlant tallMoltenPlant;
    public static JBlockFlower crumblingPlant;
    public static JBlockFlower lavaBloom;
    public static JBlockTallGrass crispGrass;

    public static JBlockPlant boilingBloom;
    public static JBlockPlant boilingStem;

    public static BlockMod cloudiaDirt;
    public static BlockMod cloudiaGrass;
    public static JBlockLog cloudiaLog;
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
    public static JBlockTallGrass cloudiaTallGrass;
    public static JBlockFlower cloudiaFlower;

    public static BlockMod swampLamp;
    public static BlockMod terralightLamp;

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

    public static BlockBossSpawner bossSpawner;

    public static BlockMod nethicDungeonBricks;
    public static JBlockPane boilChain;
    public static JBlockPane boilGate;
    public static Block boilLock;

    public static JBlockPane darklyGate;
    public static BlockMod darklyLock;

    public static BlockModFence corbaPost;
    public static BlockModFence sizzlingPost;

    public static BlockMod senterianBricks;
    public static BlockMod senterianRock;
    public static BlockMod senterianCarvedRock;
    public static BlockMod senterianFloor;
    public static BlockMod senterianGlass;
    public static JBlockPane senterianBars;
    public static BlockMod senterianLightLamp;
    public static BlockMod senterianMellowLamp;
    public static BlockMod senterianGuardianLamp;
    public static BlockModFence senterianPost;
    public static BlockSenterianAltar senterianAltar;
    public static BlockMod SENTRY_LOCK;
    
    public static BlockMod LAMENT_NETHER;
    public static BlockMod LAMENT_BOIL;
    public static BlockMod LAMENT_EUCA;
    public static BlockMod LAMENT_DEPTHS;
    public static BlockMod LAMENT_CORBA;
    public static BlockMod LAMENT_TERRANIA;
    public static BlockMod LAMENT_CLOUDIA;
    public static BlockMod LAMENT_SENTRY;
    public static BlockMod LAMENT;

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
    public static BlockCaveVine caveVine;
    public static BlockMod mossyEssenceStone;

    public static BlockMod dungeonBricks;
    public static BlockMod dungeonCrackedBricks;
    public static BlockMod dungeonChiseledBricks;
    public static BlockMod dungeonCarvedBricks;
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
    public static JBlockLog terranianLog;
    public static BlockMod terranianGrass;
    public static BlockMod terranianStone;
    public static JBlockPane terranianBars;
    public static BlockMod terranianDarkPanels;
    public static BlockMod terranianPanels;
    public static JBlockTallGrass terranianTallgrass;
    public static JBlockMushroom terramushroom;
    public static JBlockDoublePlant tallTerramushroom;
    public static BlockTerranianFlower terranianFlower;
    public static BlockMod terraniaLamp;
    public static BlockMod terrashroomBlockPink;
    public static BlockMod terrashroomBlockPurple;
    public static BlockMod terrashroomStem;

    public static BlockMod hotBrick;
    public static BlockModFence hotBrick_fence;
    public static BlockModStairs hotBrick_stairs;

    public static BlockMod hotGlass;
    public static BlockMod smoothGlass;

    public static JBlockDoublePlant darkbloom;
    public static BlockMod depthsLights;

    public static BlockTallGlowshroom tallGlowshroomRed;
    public static BlockTallGlowshroom tallGlowshroomGreen;
    public static BlockTallGlowshroom tallGlowshroomBlue;

    public static BlockGlowshroom glowshroomRed;
    public static BlockGlowshroom glowshroomGreen;
    public static BlockGlowshroom glowshroomBlue;

    public static JBlockDoublePlant hellThorn;
    public static BlockMod hellThornRoot;
    public static BlockMod nethicGrass;

    public static JBlockDoublePlant tallGolditeStalks;
    public static JBlockFlower golditeBulb;
    public static JBlockFlower golditeFlower;
    public static JBlockFlower golditeStalks;

    public static JBlockDoublePlant tallGoldenStalks;
    public static JBlockFlower goldenBulb;
    public static JBlockFlower goldenBloom;
    public static JBlockFlower goldenStalks;

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

    public static BlockModFlower eucaSilverTallGrass;
    public static BlockModFlower eucaSilverGoldFlower;
    public static BlockModFlower eucaSilverShortGrass;
    public static BlockModFlower eucaSilverSprouts;

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
    public static BlockCrafting stoneCraftingTable;
    public static BlockSummoningTable summoningTable;
    public static BlockMod bossAltar;

    public static BlockModSpawner observerSpawner;
    public static BlockModSpawner screamerSpawner;
    public static BlockModSpawner hellwingSpawner;
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
    public static BlockModSpawner miniGhastSpawner;

    public static BlockMod elderBlock;
    public static BlockMod ancientMachineBlock;

    public static BlockNetherFurnace netherFurnace;
    public static BlockNetherFurnace netherFurnaceActive;

    public static BlockMod SENTERIAN_HEART_BLOCK;
    public static BlockMod SENTERIAN_HEART_BLOCK_WEAK;
    public static BlockMod SENTERIAN_HEART_EYE;

    public static BlockIncubator INCUBATOR;
    public static BlockIncubator INCUBATOR_LIT;

    public static BlockMod terraniaSapling;

    public static JBlockFlower depthsCrystal;

    public static JBlockFluid senterianAcid;

    public static void init() {

        iridiumOre = new BlockModOre("iridiumOre", "Iridium Ore", true).setHarvestLevel(EnumToolType.DIAMOND_PICK);

        sapphireOre = new BlockModOre("sapphireOre", "Sapphire Ore", false).setHarvestLevel(EnumToolType.DIAMOND_PICK);
        lavaRock = new BlockMod("lavaRock", "Lava Rock").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        shadiumOre = new BlockModOre("shadiumOre", "Shadium Ore", false).setHarvestLevel(EnumToolType.DIAMOND_PICK);
        luniumOre = new BlockModOre("luniumOre", "Lunium Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        hellstoneOre = new BlockModOre("hellstoneOre", "Hellstone Ore", false)
                .setHarvestLevel(EnumToolType.DIAMOND_PICK);
        ashualOre = new BlockModOre("ashualOre", "Ashual Ore", false).applyKnowledge(EnumKnowledgeType.BOIL, 2);
        blaziumOre = new BlockModOre("blaziumOre", "Blazium Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK).applyKnowledge(EnumKnowledgeType.BOIL, 1);
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

        sapphireBlock = new BlockModOre("sapphireBlock", "Sapphire Block").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        shadiumBlock = new BlockModOre("shadiumBlock", "Shadium Block").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        luniumBlock = new BlockModOre("luniumBlock", "Lunium Block").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        hellstoneBlock = new BlockModOre("hellstoneBlock", "Hellstone Block").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        ashualBlock = new BlockModOre("ashualBlock", "Ashual Block");
        blaziumBlock = new BlockModOre("blaziumBlock", "Blazium Block").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        celestiumBlock = new BlockModOre("celestiumBlock", "Celestium Block").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        mekyumBlock = new BlockModOre("mekyumBlock", "Mekyum Block").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        koriteBlock = new BlockModOre("koriteBlock", "Korite Block").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        storonBlock = new BlockModOre("storonBlock", "Storon Block").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        flairiumBlock = new BlockModOre("flairiumBlock", "Flairium Block").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        desBlock = new BlockModOre("desBlock", "Des Block").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        enderilliumBlock = new BlockModOre("enderilliumBlock", "Enderillium Block").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        gorbiteBlock = new BlockModOre("gorbiteBlock", "Gorbite Block").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        orbaditeBlock = new BlockModOre("orbaditeBlock", "Orbadite Block").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        luniteBlock = new BlockModOre("luniteBlock", "Lunite Block").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        iridiumBlock = new BlockModOre("iridiumBlock", "Iridium Block").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        nethicGemstoneBlock = new BlockModOre("nethicGemstoneBlock", "Nethic Gemstone Block").setHarvestLevel(EnumToolType.DIAMOND_PICK);
        frostGemBlock = new BlockMod("frost_gem_block", "Frost Gem Block").setHarvestLevel(EnumToolType.IRON_PICK);

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
        obelisk = (BlockMod) new BlockMod("obelisk", "Obelisk").setLightLevel(0.5F);
        bloodLamp = (BlockMod) new BlockMod("bloodLamp", "Blood Lamp").setLightLevel(2.0F);
        bloodCatalyst = (BlockMod) new BlockMod("bloodCatalyst", "Blood Catalyst").setLightLevel(0.1F);

        ANCIENT_OBELISK = (BlockObelisk) new BlockObelisk("ancient_obelisk", "Ancient Obelisk").setLightLevel(0.2F).setResistance(100000F).setBlockUnbreakable();
        ANCIENT_SOCKET = (BlockAncientSocket) new BlockAncientSocket("ancient_socket", "Ancient Socket").setResistance(100000F).setBlockUnbreakable();
        
        ANCIENT_CATALYST = (BlockAncientCatalyst) new BlockAncientCatalyst("ancient_catalyst", "Ancient Catalyst").setResistance(100000F).setBlockUnbreakable();
        ANCIENT_STONE = (BlockMod) new BlockMod("ancient_stone", "Ancient Stone").setResistance(100000F).setBlockUnbreakable();
        ANCIENT_RUNIC_STONE_0 = (BlockMod) new BlockMod("ancient_stone_runic_0", "Ancient Runic Stone").setResistance(100000F).setBlockUnbreakable().setLightLevel(0.2F);
        ANCIENT_RUNIC_STONE_1 = (BlockMod) new BlockMod("ancient_stone_runic_1", "Ancient Runic Stone").setResistance(100000F).setBlockUnbreakable().setLightLevel(0.2F);
        ANCIENT_RUNIC_STONE_2 = (BlockMod) new BlockMod("ancient_stone_runic_2", "Ancient Runic Stone").setResistance(100000F).setBlockUnbreakable().setLightLevel(0.2F);
        ANCIENT_RUNIC_STONE_3 = (BlockMod) new BlockMod("ancient_stone_runic_3", "Ancient Runic Stone").setResistance(100000F).setBlockUnbreakable().setLightLevel(0.2F);

        witherwoodLog = new JBlockLog("witherwoodLog", "Witherwood Log");
        witherwoodLeaves = new BlockModLeaves("witherwoodLeaves", "Witherwood Leaves", 1.0F);
        sizzlerWoodLeaves = new BlockModLeaves("sizzlerWoodLeaves", "Deadblood Leaves", 2.0F, netherSapling);
        sizzlerWoodLog = new JBlockLog("sizzlerWoodLog", "Deadblood Log");
        deathGrass = (JBlockTallGrass) new JBlockTallGrass("death_grass", "Death Grass").setGroundPredicate(GroundPredicate.NETHER);
        netherweed = (JBlockFlower) new JBlockFlower("netherweed", "Netherweed").setGroundPredicate(GroundPredicate.NETHER);
        hellBell = (JBlockFlower) new JBlockFlower("hell_bell", "Hell Bell").disableOffset().setGroundPredicate(GroundPredicate.NETHER).setBoundingBox(JBlockFlower.BIG_PLANT_BB);
        hellshroom = (JBlockDoublePlant) new JBlockDoublePlant("hellshroom", "Hellshroom").setGroundPredicate(GroundPredicate.NETHER);

        earthenNetherLeaves = new BlockModLeaves("earthenNetherLeaves", "Earthenwood Leaves", 2.0F, EARTHEN_SAPLING);
        earthenNetherLog = new JBlockLog("earthenNetherLog", "Earthenwood Log");
        earthenNetherPlanks = new BlockMod(EnumMaterialTypes.WOOD, "earthenNetherPlank", "Earthenwood Planks", 1.0F);
        earthenTallNethigrass = (JBlockTallGrass) new JBlockTallGrass("earthen_tall_nethigrass", "Earthen Tall Nethigrass").setGroundPredicate(GroundPredicate.NETHER).setBoundingBox(JBlockPlant.SHORT_GRASS_BB);
        earthenShortNethigrass = (JBlockTallGrass) new JBlockTallGrass("earthen_short_nethigrass", "Earthen Short Nethigrass").setGroundPredicate(GroundPredicate.NETHER).setBoundingBox(Block.FULL_BLOCK_AABB);
        earthenHeatflower = (JBlockFlower) new JBlockFlower("earthen_heatflower", "Earthen Heatflower").setGroundPredicate(GroundPredicate.NETHER);

        heatSoil = new BlockMod(EnumMaterialTypes.DIRT, "heatSoil", "Nethic Soil", 0.5F);
        earthenNetherrack = new BlockMod(EnumMaterialTypes.STONE, "earthenNetherrack", "Earthen Netherrack", 2.0F);
        heatSand = new BlockModGravity(EnumMaterialTypes.SAND, "heatSand", "Heat Sand", 0.5F).setFireSource(true);
        volcanicSand = new BlockModGravity(EnumMaterialTypes.SAND, "volcanic_sand", "Volcanic Sand", 0.5F).setFireSource(true);

        igniterOn = (BlockMod) new BlockIgniter("igniterOn", "Redstone Igniter").setCreativeTab(null);
        igniter = new BlockIgniter("igniter", "Redstone Igniter");
        greenGemBlock = new BlockMod("greenGemBlock", "Green Gem Block");
        purpleGemBlock = new BlockMod("purpleGemBlock", "Purple Gem Block");
        blueGemBlock = new BlockMod("blueGemBlock", "Blue Gem Block");
        yellowGemBlock = new BlockMod("yellowGemBlock", "Yellow Gem Block");
        ironLootBox = new JBlockRandomLoot("iron_loot_box", "Iron Loot Box", JourneyLootTables.LOOT_BASIC);
        goldLootBox = new JBlockRandomLoot("gold_loot_box", "Gold Loot Box", JourneyLootTables.LOOT_GOLD);
        diamondLootBox = new JBlockRandomLoot("diamond_loot_box", "Diamond Loot Box", JourneyLootTables.LOOT_DIAMOND);
        overgrownLootBox = new JBlockRandomLoot("overgrown_loot_box", "Overgrown Loot Box", JourneyLootTables.LOOT_OVERGROWN);

        magicExplosive = new JBlockTNT("magic_explosive", "Magic Explosive", 10.0F);

        nethicanSludge = new BlockMod(EnumMaterialTypes.SLIME, "nethicanSludge", "Nethican Sludge", 1.0F);

        eucaSapling = new BlockModSapling("eucaSapling", "Euca Sapling", new WorldGenEucaTree());
        boilSapling = new BlockModSapling("boilSapling", "Boiling Sapling", new WorldGenBoilTree1());
        corbaSapling = new BlockModSapling("corbaSapling", "Corba Sapling", new WorldGenCorbaSmallTree());
        bogwoodSapling = new BlockModSapling("bogwood_sapling", "Bogwood Sapling", new WorldGenCorbaSwampTree());
        depthsSapling = new BlockModSapling("depthsSapling", "Depths Sapling", new WorldGenDepthsTree());
        netherSapling = new BlockModSapling("netherSapling", "Deadblood Sapling", new WorldGenBleedheartTree0());
        EARTHEN_SAPLING = new BlockModSapling("earthenSapling", "Earthen Sapling", new WorldGenEarthenTree());
        frozenIceSapling = new BlockModSapling("frozenIceSapling", "Ice Sapling", new WorldGenIceTree());
        frozenSapling = new BlockModSapling("frozenSapling", "Frozen Sapling", new WorldGenFrozenTree());

        eucaDirt = new BlockMod(EnumMaterialTypes.DIRT, "eucaDirt", "Euca Dirt", 2.0F);
        eucaGrass = new BlockModGrass(eucaDirt, "eucaGrass", "Euca Grass", 2.0F);
        eucaGolditeGrass = new BlockModGrass(eucaGolditeDirt, "goldite_grass", "Goldite Grass", 2.0F);
        eucaGolditeDirt = new BlockMod(EnumMaterialTypes.DIRT, "goldite_dirt", "Goldite Dirt", 2.0F);
        eucaSilverGrass = new BlockModGrass(eucaDirt, "eucaSilverGrass", "Euca Grass", 2.0F);
        eucaStone = new BlockMod("eucaStone", "Euca Stone", 2.0F);
        eucaGoldLog = new JBlockLog("eucaGoldLog", "Golden Euca Log");
        goldEucaPlank = new BlockMod(EnumMaterialTypes.WOOD, "goldEucaPlank", "Golden Euca Plank", 1.0F);
        eucaGoldStairs = new BlockModStairs(eucaGoldLog, "eucaGoldStairs", "Golden Euca Stairs");
        eucaGoldLeaves = new BlockModLeaves("eucaGoldLeaves", "Golden Euca Leaves", 1.0F, eucaSapling);
        eucaSilverLeaves = new BlockModLeaves("eucaSilverLeaves", "Silver Euca Leaves", 1.0F, eucaSapling);
        eucaLightGreenLeaves = new BlockModLeaves("eucaLightGreenLeaves", "Light Green Euca Leaves", 1.0F);
        eucaDarkGreenLeaves = new BlockModLeaves("eucaDarkGreenLeaves", "Dark Green Euca Leaves", 1.0F);

        GOLDITE_OAK_LOG = new JBlockLog("goldite_oak_log", "Goldite Oak Log");

        eucaBricks = new BlockMod("eucaBricks", "Euca Bricks", 2.0F);
        eucaTile = new BlockMod("eucaTile", "Euca Tile", 2.0F);

        EUCA_SQUARE_DUNGEON_BRICKS = new BlockMod("euca_square_dungeon_bricks", "Euca Dungeon Bricks");
        EUCA_DUNGEON_BRICKS = new BlockMod("euca_dungeon_bricks", "Euca Dungeon Bricks");
        EUCA_DUNGEON_BRICK_STAIRS = new BlockModStairs(EUCA_DUNGEON_BRICKS, "euca_dungeon_brick_stairs", "Euca Dungeon Brick Stairs");
        EUCA_SQUARE_RUNIC_BRICKS = (BlockMod) new BlockMod("euca_square_runic_bricks", "Euca Dungeon Bricks").setLightLevel(0.2F);
        EUCA_RUNIC_BRICKS = (BlockMod) new BlockMod("euca_runic_bricks", "Euca Dungeon Bricks").setLightLevel(0.2F);
        EUCA_RUNIC_LAMP = (BlockMod) new BlockMod("euca_runic_lamp", "Euca Dungeon Bricks").setLightLevel(0.5F);
        EUCA_DUNGEON_TILE = new BlockMod("euca_dungeon_tile", "Euca Dungeon Tile");
        EUCA_GOLD_STONE = new BlockMod("euca_gold_stone", "Euca Gold Stone");

        depthsDirt = new BlockMod(EnumMaterialTypes.DIRT, "depthsDirt", "Depths Dirt", 2.0F);
        depthsGrass = new BlockModGrass(depthsDirt, "depthsGrass", "Depths Grass", 2.0F);
        depthsStone = new BlockMod("depthsStone", "Depths Stone", 2.0F);
        depthsLog = new JBlockLog("depthsLog", "Depths Log");
        depthsPlank = new BlockMod(EnumMaterialTypes.WOOD, "depthsPlank", "Depths Plank", 1.0F);
        depthsStairs = new BlockModStairs(depthsPlank, "depthsStairs", "Depths Stairs");
        depthsLeaves = new BlockDepthsLeaves("depthsLeaves", "Depths Leaves", 1.0F, depthsSapling);
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
        depthsLamp = (BlockMod) new BlockMod("depths_lamp", "Depths Lamp", 1.0F).setLightLevel(1);
        depthsGate = new Initializer<>(new JBlockPane(EnumMaterialTypes.STONE, "depthsGate", "Depths Gate", true)).apply(pane -> pane.setHardness(1.0F));
        depthsLock = new BlockLock("depthsLock", "Depths Lock", JourneyItems.depthsKey);
        depthsDoor = new BlockModDoor("depthsDoor", "Depths Door", Material.WOOD, 2.0F);

        iceLeaves = new BlockDepthsLeaves("iceLeaves", "Ice Leaves", 1.0F, frozenIceSapling);
        frozenDirt = new BlockMod(EnumMaterialTypes.DIRT, "frozenDirt", "Frozen Dirt", 2.0F);
        frozenStone = new BlockMod(EnumMaterialTypes.STONE, "frozenStone", "Frozen Stone", 5.0F);
        frozenGrass = new BlockModGrass(frozenDirt, "frozenGrass", "Frozen Grass", 2.0F);
        frozenLeaves = new BlockModLeaves("frozenLeaves", "Frozen Leaves", 0.5F, frozenSapling).setFrozenPlant();
        frozenLog = new JBlockLog("frozenLog", "Frozen Log");
        frozenPlanks = new BlockMod(EnumMaterialTypes.WOOD, "frozenPlanks", "Frozen Planks", 0.5F);

        corbaCrackedBricks = new BlockMod("corba_cracked_bricks", "Corba Stone");
        corbaDarkBricks = new BlockMod("corba_dark_bricks", "Corba Stone");
        corbaBricks = new BlockMod("corba_bricks", "Corba Stone");
        corbaLightBricks = new BlockMod("corba_light_bricks", "Corba Stone");

        corbaBrickStairs = new BlockModStairs(corbaBricks, "corba_brick_stairs", "Corba Brick Stairs");

        
        corbaGrassPath = new JBlockGrassPath("corbaGrassPath", "Corba Grass Path", corbaStone);
        corbaStone = new BlockMod("corbaStone", "Corba Stone");
        corbaCobblestone = new BlockMod("corba_cobblestone", "Corba Cobblestone");
        corbaWall = new JBlockWall("corba_wall", "Corba Wall", corbaCobblestone);
        corbaGrass = new BlockModGrass(null, "corbaGrass", "Corba Grass", 0.5F).setGrassPath(corbaGrassPath);
        corbaLog = new JBlockLog("corbaLog", "Corba Log");
        corbaLeaves = new BlockModLeaves("corbaLeaves", "Corba Leaves", 0.2F, corbaSapling);
        corbaPillar = new BlockMod("corbaPillar", "Corba Pillar");
        corbaPlank = new BlockMod(EnumMaterialTypes.WOOD, "corbaPlank", "Corba Plank", 0.5F);
        corbaLamp = (BlockMod) new BlockMod(EnumMaterialTypes.GLASS, "corbaLamp", "Corba Lamp", 0.1F)
                .setLightLevel(1.0F).setCreativeTab(JourneyTabs.DECORATION);
        corbaSentryBrick = new BlockMod("corbaSentryBrick", "Corba Sentry Brick");

        taintedMud = new JBlockMud("tainted_mud", "Tainted Mud");
        driedMud = new BlockMod(EnumMaterialTypes.DIRT, "dried_mud", "Dried Mud", 1.0F);

        bogwoodLog = new JBlockLog("bogwood_log", "Bogwood Log");
        bogwoodLeaves = new BlockModLeaves("bogwood_leaves", "Bogwood Leaves", 1.0F, bogwoodSapling);

        bogweed = (JBlockDoublePlant) new JBlockDoublePlant("bogweed", "Bogweed").setGroundPredicate(GroundPredicate.CORBA_MUD);
        swampLily = new JBlockWaterLily("swamp_lily", "Swamp lily");
        swamp_shelf = new JBlockFungalShelf("swamp_shelf", "Swamp Shelf");
        bogshroomsSmall = (JBlockMushroom) new JBlockMushroom("bog_shrooms_small", "Small Bogshrooms").setGroundPredicate(GroundPredicate.COMMON_AND_CORBA_GRASS).setLightLevel(0.3F);
        bogshroomTall = (JBlockMushroom) new JBlockMushroom("bog_shroom_tall", "Tall Bogshroom").setGroundPredicate(GroundPredicate.COMMON_AND_CORBA_GRASS).setLightLevel(0.6F);

        corbaPlankSlab = new JBlockSlab(Material.WOOD, "corba_plank_slab", "Corba Plank Slab", false);
        corbaPlankDoubleSlab = new JBlockSlab(Material.WOOD, "corba_plank_double_slab", "Corba Plank Double Slab", true);

        corbaPlankStairs = new BlockModStairs(corbaPlank, "corba_plank_stairs", "Corba Plank Stairs");

        slime = new BlockSlime(EnumMaterialTypes.SLIME, "slime", "Slime", 0.1F);

        totemBase = (BlockMod) new BlockMod(EnumMaterialTypes.WOOD, "totem_base", "Totem Base", 1.0F).setBlockUnbreakable().setResistance(100000F);
        totemHappy = (BlockMod) new JBlockFacing(EnumMaterialTypes.WOOD, "totem_happy", "Happy Totem", 1.0F).setBlockUnbreakable().setResistance(100000F);
        totemSad = (BlockMod) new JBlockFacing(EnumMaterialTypes.WOOD, "totem_sad", "Sad Totem", 1.0F).setBlockUnbreakable().setResistance(100000F);
        totemAngry = (BlockMod) new JBlockFacing(EnumMaterialTypes.WOOD, "totem_angry", "Angry Totem", 1.0F).setBlockUnbreakable().setResistance(100000F);
        totemScared = (BlockMod) new JBlockFacing(EnumMaterialTypes.WOOD, "totem_scared", "Scared Totem", 1.0F).setBlockUnbreakable().setResistance(100000F);


        boilingLamp = (BlockMod) new BlockMod(EnumMaterialTypes.GLASS, "boilingLamp", "Boiling Lamp", 0.1F)
                .setLightLevel(1.0F).setCreativeTab(JourneyTabs.DECORATION);
        blazierBricks = new BlockMod("blazierBricks", "Blazier Bricks");
        ashBlock = new BlockMod("ashBlock", "Ash");
        hotBlock = new BlockHotBlock(ashBlock, "hotGround", "Hot Ground", 2.0F);
        rubble = new BlockMod("rubble", "Rubble", 1.0F);
        scorchedRubble = new BlockMod("scorched_rubble", "Scorched Rubble", 3.0F).setFireSource(true);

        scorchedCactus = new JBlockCactus("scorched_cactus", "Scorched Cactus").setGroundPredicate(GroundPredicate.CACTUS_AND_BOILING_SANDS);

        scorchedStalagmiteLarge = new JBlockStalagmite("scorched_stalagmite_large", "Scorched Stalagmite");
        scorchedStalagmiteMedium = new JBlockStalagmite("scorched_stalagmite_med", "Scorched Stalagmite");
        scorchedStalagmiteSmall = new JBlockStalagmite("scorched_stalagmite_small", "Scorched Stalagmite");
        scorchedStalagmiteTiny = new JBlockStalagmite("scorched_stalagmite_tiny", "Scorched Stalagmite");

        charredGrass = new BlockHotBlock(rubble, "charred_grass", "Charred Grass", 2.0F);

        boilCobble = new BlockMod("boil_cobble", "Boil Cobble");
        boilPillar = new BlockMod("boil_pillar", "Boil Pillar");
        boilShingle = new BlockMod("boil_shingle", "Boil Shingle");
        boilSquareBrick = new BlockMod("boil_square_brick", "Boil Square Brick");
        boilBricks = new BlockMod("boil_bricks", "Boil Bricks");
        tallCrumblingPlant = (JBlockDoublePlant) new JBlockDoublePlant("tall_crumbling_plant", "Tall Crumbling Plant").setGroundPredicate(GroundPredicate.COMMON_AND_BOILING_SANDS);
        tallMoltenPlant = (JBlockDoublePlant) new JBlockDoublePlant("tall_molten_plant", "Tall Molten Plant").setGroundPredicate(GroundPredicate.COMMON_AND_BOILING_SANDS);
        crumblingPlant = (JBlockFlower) new JBlockFlower("crumbling_plant", "Crumbling Plant").setGroundPredicate(GroundPredicate.COMMON_AND_BOILING_SANDS).enableDamageOnContact();
        lavaBloom = (JBlockFlower) new JBlockFlower("lava_bloom", "Lava Bloom").setGroundPredicate(GroundPredicate.COMMON_AND_BOILING_SANDS).enableDamageOnContact();
        crispGrass = (JBlockTallGrass) new JBlockTallGrass("crisp_grass", "Crisp Grass").setGroundPredicate(GroundPredicate.COMMON_AND_BOILING_SANDS).enableDamageOnContact().setBoundingBox(JBlockPlant.SHORT_GRASS_BB);

        brisonblocks = new BlockMetaBase("brison", Material.ROCK, "brison");
        boilingBars = new Initializer<>(new JBlockPane(EnumMaterialTypes.STONE, "boilingBars", "Brison Bars", true)).apply(pane -> pane.setHardness(5.0F));
        burningLeaves = new BlockModLeaves("burningLeaves", "Burning Leaves", 0.5F, boilSapling).setBurningPlant();
        boilingLog = new JBlockLog("boilingLog", "Boiling Log");

        cloudiaDirt = new BlockMod(EnumMaterialTypes.DIRT, "cloudiaDirt", "Cloudia Dirt", 2.0F);
        cloudiaGrass = new BlockModGrass(cloudiaDirt, "cloudiaGrass", "Cloudia Grass", 2.0F);
        cloudiaLog = new JBlockLog("cloudiaLog", "Cloudia Log");
        cloudiaRock = new BlockMod("cloudiaRock", "Cloudia Rock");
        cloudiaPillar = new BlockMod("cloudiaPillar", "Cloudia Pillar");
        cloudiaPlanks = new BlockMod(EnumMaterialTypes.WOOD, "cloudwoodPlanks", "Cloudwood Planks", 1.0F);
        cloudiaBrick = (BlockMod) new BlockMod("cloudiaBrick", "Cloudia Brick").setLightLevel(0.5F);
        cloudiaWall = (BlockMod) new BlockMod("cloudiaWall", "Cloudia Wall").setLightLevel(0.5F);
        cloudiaTile = (BlockMod) new BlockMod("cloudiaTile", "Cloudia Tile").setLightLevel(0.5F);
        cloudiaLeaves = (BlockMod) new BlockModLeaves("cloudiaLeaves", "Cloudia Leaves", 0.5F).setLightLevel(2);
        cloudiaLamp = (BlockMod) new BlockMod(EnumMaterialTypes.GLASS, "cloudiaLamp", "Cloudia Lamp", 0.1F)
                .setLightLevel(1.0F).setCreativeTab(JourneyTabs.DECORATION);
        cloudiaCobblestone = new BlockMod("cloudiaCobblestone", "Cloudia Cobblestone");
        cloudiaBrickStairs = new BlockModStairs(cloudiaBrick, "cloudiaBrickStairs", "Cloudia Brick Stairs");
        cloudiaTileStairs = new BlockModStairs(cloudiaTile, "cloudiaTileStairs", "Cloudia Tile Stairs");
        // cloudiaPost = new BlockCloudiaPost("cloudiaPost");
        pinkCloudiaCloud = new BlockCloud("pinkCloudiaCloud", "Pink Cloudia Cloud");
        blueCloudiaCloud = new BlockCloud("blueCloudiaCloud", "Blue Cloudia Cloud");
        lightBlueCloudiaCloud = new BlockCloud("lightBlueCloudiaCloud", "Light Blue Cloudia Cloud");
        cloudiaTallGrass = (JBlockTallGrass) new JBlockTallGrass("cloudia_tall_grass", "Cloudia Tall Grass").setGroundPredicate(GroundPredicate.COMMON_AND_CLOUDIA_GRASS);
        cloudiaFlower = (JBlockFlower) new JBlockFlower("cloudia_flower", "Cloudia Flower").setGroundPredicate(GroundPredicate.COMMON_AND_CLOUDIA_GRASS);

        swampLamp = new BlockSwampLamp("swampLamp", "Swamp Fly Bottle", 0);
        terralightLamp = new BlockTerraLamp("terralight_lamp", "Terralight Lamp", 0);

        journeyChest = new BlockJourneyChest("journey_chest", "Journey Chest", BlockJourneyChest.Type.JOURNEY);
        netherChest = new BlockJourneyChest("nether_chest", "Nether Chest", BlockJourneyChest.Type.NETHER);
        boilChest = new BlockJourneyChest("boil_chest", "Boil Chest", BlockJourneyChest.Type.BOIL);
        eucaChest = new BlockJourneyChest("euca_chest", "Euca Chest", BlockJourneyChest.Type.EUCA);
        frozenChest = new BlockJourneyChest("frozen_chest", "Frozen Chest", BlockJourneyChest.Type.FROZEN);
        depthsChest = new BlockJourneyChest("depths_chest", "Depths Chest", BlockJourneyChest.Type.DEPTHS);
        corbaChest = new BlockJourneyChest("corba_chest", "Corba Chest", BlockJourneyChest.Type.CORBA);
        terraniaChest = new BlockJourneyChest("terrania_chest", "Terrania Chest", BlockJourneyChest.Type.TERRA);
        cloudiaChest = new BlockJourneyChest("cloudia_chest", "Cloudia Chest", BlockJourneyChest.Type.CLOUDIA);

        lockedChest = (BlockJourneyChest) new BlockJourneyChest("locked_chest", "Locked Chest", BlockJourneyChest.Type.JOURNEY, true, JourneyItems.chestKey).setResistance(10000F).setBlockUnbreakable();

        nethicDungeonBricks = new BlockMod("nethicDungeonBricks", "Nethic Dungeon Bricks");
        boilChain = new Initializer<>(new JBlockPane(EnumMaterialTypes.STONE, "boilChain", "Boil Chain", false)).apply(pane -> pane.setBlockUnbreakable().setResistance(6000000.0F));
        boilGate = new Initializer<>(new JBlockPane(EnumMaterialTypes.STONE, "boilGate", "Boil Gate", false)).apply(pane -> pane.setBlockUnbreakable().setResistance(6000000.0F));
        boilLock = new BlockLock("boilLock", "Boil Lock", JourneyItems.boilKey).setBlockUnbreakable()
                .setResistance(10000000.0F);

        darklyGate = new Initializer<>(new JBlockPane(EnumMaterialTypes.STONE, "darklyGate", "Darkly Gate", false)).apply(pane -> pane.setBlockUnbreakable().setResistance(6000000.0F));
        darklyLock = (BlockMod) new BlockLock("darklyLock", "Darkly Lock", JourneyItems.darkKey).setBlockUnbreakable()
                .setResistance(10000000.0F);

        corbaPost = new BlockModFence(pinkCloudiaCloud, "corbaPost", "Corba Post");
        sizzlingPost = new BlockModFence(pinkCloudiaCloud, "sizzlingPost", "Sizzling Post");

        senterianBricks = (BlockMod) new BlockMod("senterianBricks", "Senterian Bricks").setBlockUnbreakable().setResistance(10000000.0F);
        senterianRock = (BlockMod) new BlockMod("senterianRock", "Senterian Rock").setBlockUnbreakable().setResistance(10000000.0F);
        senterianCarvedRock = (BlockMod) new BlockMod("senterianCarvedRock", "Senterian Carved Rock").setBlockUnbreakable().setResistance(10000000.0F);
        senterianFloor = (BlockMod) new BlockMod("senterianFloor", "Senterian Floor").setBlockUnbreakable().setResistance(10000000.0F);
        senterianGlass = (BlockJoinedGlass) new BlockJoinedGlass("senterianGlass", "Senterian Glass").setBlockUnbreakable().setResistance(10000000.0F);
        senterianBars = new Initializer<>(new JBlockPane(EnumMaterialTypes.STONE, "senterianBars", "Senterian Bars", false)).apply(pane -> pane.setBlockUnbreakable().setResistance(6000000.0F));
        senterianLightLamp = (BlockMod) new BlockMod(EnumMaterialTypes.GLASS, "senterianLightLamp", "Senterian Light Lamp", 0.1F).setLightLevel(0.5F).setCreativeTab(JourneyTabs.DECORATION).setBlockUnbreakable().setResistance(10000000.0F);
        senterianMellowLamp = (BlockMod) new BlockMod(EnumMaterialTypes.GLASS, "senterianMellowLamp", "Senterian Mellow Lamp", 0.1F).setLightLevel(0.5F).setCreativeTab(JourneyTabs.DECORATION).setBlockUnbreakable().setResistance(10000000.0F);
        senterianGuardianLamp = (BlockMod) new BlockMod(EnumMaterialTypes.GLASS, "senterianGuardianLamp", "Senterian Guardian Lamp", 0.1F).setLightLevel(0.5F).setCreativeTab(JourneyTabs.DECORATION).setBlockUnbreakable().setResistance(10000000.0F);
        senterianPost = (BlockModFence) new BlockModFence(senterianBricks, "senterianPost", "Senterian Post").setBlockUnbreakable().setResistance(10000000.0F);
        senterianAltar = (BlockSenterianAltar) new BlockSenterianAltar("senterianAltar", "Senterian Altar").setBlockUnbreakable().setResistance(1000000.0F);
        SENTRY_LOCK = (BlockMod) new BlockLock("sentry_lock", "Sentry Lock", JourneyItems.sentryKey).setBlockUnbreakable().setResistance(1000000.0F);

        LAMENT_NETHER = (BlockMod) new BlockLament("lament_nether", "Lament Box of Hell", JourneyItems.lamentDeconstructorNether).setBlockUnbreakable().setResistance(10000000.0F);
        LAMENT_BOIL = (BlockMod) new BlockLament("lament_boil", "Lament Box of Fire", JourneyItems.lamentDeconstructorBoil).setBlockUnbreakable().setResistance(10000000.0F);
        LAMENT_EUCA = (BlockMod) new BlockLament("lament_euca", "Lament Box of Light", JourneyItems.lamentDeconstructorEuca).setBlockUnbreakable().setResistance(10000000.0F);
        LAMENT_DEPTHS = (BlockMod) new BlockLament("lament_depths", "Lament Box of Darkness", JourneyItems.lamentDeconstructorDepths).setBlockUnbreakable().setResistance(10000000.0F);

        LAMENT_CORBA = (BlockMod) new BlockLament("lament_corba", "Lament Box of Decay", JourneyItems.lamentDeconstructorCorba).setBlockUnbreakable().setResistance(10000000.0F);
        LAMENT_TERRANIA = (BlockMod) new BlockLament("lament_terrania", "Lament Box of Life", JourneyItems.lamentDeconstructorTerrania).setBlockUnbreakable().setResistance(10000000.0F);
        LAMENT_CLOUDIA = (BlockMod) new BlockLament("lament_cloudia", "Lament Box of Heaven", JourneyItems.lamentDeconstructorCloudia).setBlockUnbreakable().setResistance(10000000.0F);
        LAMENT_SENTRY = (BlockMod) new BlockLament("lament_sentry", "Lament Box of Death", JourneyItems.lamentDeconstructorSentry).setBlockUnbreakable().setResistance(10000000.0F);

        LAMENT = (BlockMod) new BlockMod("lament", "Deconstructed Lament").setBlockUnbreakable().setResistance(10000000.0F);

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
        cloudiaPortal = new BlockCloudiaPortal("cloudiaPortal");
        cloudiaPortalFrame = new BlockMod("cloudiaPortalFrame", "Cloudia Portal Frame", 3.0F);

        depthsLights = (BlockMod) new BlockMod(EnumMaterialTypes.GLASS, "depthsLight", "Depths Light", 0.5F)
                .setLightLevel(0.6F);

        fire = new BlockModFire("fire", "Essential Fire");

        caveVine = new BlockCaveVine("cavevine", "Cave Vine");

        mossyEssenceStone = new BlockMod("mossyEssenceStone", "Mossy Essence Stone", 2.0F);

        dungeonBricks = (BlockMod) new BlockMod(EnumMaterialTypes.STONE, "dungeonBrick", "Dungeon Brick", -1F)
                .setResistance(10000F);
        dungeonCrackedBricks = (BlockMod) new BlockMod(EnumMaterialTypes.STONE, "dungeonCrackedBrick",
                "Dungeon Cracked Bricks", -1F).setResistance(10000F);
        dungeonChiseledBricks = (BlockMod) new BlockMod(EnumMaterialTypes.STONE, "dungeonChiseledBrick",
                "Dungeon Chiseled Bricks", -1F).setResistance(10000F);
        dungeonCarvedBricks = (BlockMod) new BlockMod(EnumMaterialTypes.STONE, "dungeonBrickCarved",
                "Dungeon Carved Bricks", -1F).setResistance(10000F);
        dungeonLamp = (BlockMod) new BlockMod(EnumMaterialTypes.STONE, "dungeonLamp", "Dungeon Lamp", -1F)
                .setLightLevel(0.5F).setResistance(10000F);

        dungeonBrickStairs = (BlockModStairs) new BlockModStairs(dungeonBricks, "dungeonBrickStairs",
                "Dungeon Brick Stairs").setResistance(10000F);
        dungeonCrackedBrickStairs = (BlockModStairs) new BlockModStairs(dungeonCrackedBricks,
                "dungeonCrackedBrickStairs", "Cracked Dungeon Brick Stairs").setResistance(10000F);
        dungeonChisledBrickStairs = (BlockModStairs) new BlockModStairs(dungeonChiseledBricks,
                "dungeonChiseledBrickStairs", "Chiseled Dungeon Brick Stairs").setResistance(10000F);
        dungeonBrickCarvedStairs = (BlockModStairs) new BlockModStairs(dungeonCarvedBricks, "dungeonBrickCarvedStairs",
                "Carved Dungeon Brick Stairs").setResistance(10000F);
        dungeonLampStairs = (BlockModStairs) new BlockModStairs(dungeonLamp, "dungeonLampStairs", "Dungeon Lamp Stairs",
                true).setResistance(10000F);

        dungeonBrickFence = (BlockModFence) new BlockModFence(dungeonBricks, "dungeonBrickFence", "Dungeon Brick Fence")
                .setResistance(10000F);
        dungeonCrackedBrickFence = (BlockModFence) new BlockModFence(dungeonCrackedBricks, "dungeonCrackedBrickFence",
                "Cracked Dungeon Brick Fence").setResistance(10000F);
        dungeonChisledBrickFence = (BlockModFence) new BlockModFence(dungeonChiseledBricks, "dungeonChiseledBrickFence",
                "Chiseled Dungeon Brick Fence").setResistance(10000F);
        dungeonBrickCarvedFence = (BlockModFence) new BlockModFence(dungeonCarvedBricks, "dungeonBrickCarvedFence",
                "Carved Dungeon Brick Fence").setResistance(10000F);
        cloudiaPost = new BlockModFence(pinkCloudiaCloud, "cloudiaPost", "Cloudia Post");
        terranianPost = new BlockModFence(pinkCloudiaCloud, "terranianPost", "Terranian Post");
        dungeonLampFence = new BlockModFence(dungeonLamp, "dungeonLampFence", "Dungeon Lamp Fence", true);

        terraniaLeaves = new BlockModLeaves("terranianLeaves", "Terrania Leaves", 1.0F, terraniaSapling);
        terraniaVine = new BlockModVine("terranianVine", "Terrania Vine", 12);
        terranianDirt = new BlockMod(EnumMaterialTypes.DIRT, "terranianDirt", "Terranian Dirt", 2.0F);
        terranianLog = new JBlockLog("terranianLog", "Terranian Log");
        terranianGrass = new BlockModGrass(terranianDirt, "terranianGrass", "Terranian Grass", 2.0F);
        terranianStone = new BlockMod("terranianStone", "Terranian Stone", 2.0F);
        terranianBars = Initializer.of(new JBlockPane(EnumMaterialTypes.STONE, "terranianBars", "Terranian Bars", true)).apply(pane -> pane.setHardness(5.0F));
        terranianDarkPanels = new BlockMod("terranianDarkPanels", "Terranian Dark Panels", 2.0F);
        terranianPanels = new BlockMod("terranianPanels", "Terranian Panels", 2.0F);
        terranianTallgrass = (JBlockTallGrass) new JBlockTallGrass("terranianTallgrass", "Terranian Tallgrass").setGroundPredicate(GroundPredicate.COMMON_AND_TERRANIA_GRASS);
        terramushroom = new JBlockMushroom("terramushroom", "Terranian Shroom")
                .setBigMushroomGenerator(JBlockMushroom.BigMushroomGenerator.randomGenerator(new WorldGenTerrashroom(JourneyBlocks.terrashroomBlockPurple), new WorldGenTerrashroom(JourneyBlocks.terrashroomBlockPink)));
        tallTerramushroom = (JBlockDoublePlant) new JBlockDoublePlant("tallterramushroom", "Tall Terranian Shroom").setGroundPredicate(GroundPredicate.COMMON_AND_TERRANIA_GRASS).setLightLevel(0.6F);
        terranianFlower = (BlockTerranianFlower) new BlockTerranianFlower("terranian_flower", "Terranian Flower").setGroundPredicate(GroundPredicate.COMMON_AND_TERRANIA_GRASS);
        terraniaLamp = (BlockMod) new BlockMod(EnumMaterialTypes.GLASS, "terraniaLamp", "Terrania Lamp", 0.1F).setLightLevel(0.7F).setCreativeTab(JourneyTabs.DECORATION);

        enchantedShroomsSmall = (JBlockMushroom) new JBlockMushroom("enchanted_shrooms_small", "Enchanted Shrooms").setGroundPredicate(GroundPredicate.COMMON_AND_TERRANIA_GRASS).setLightLevel(0.3F);
        enchantedShroomTall = (JBlockMushroom) new JBlockMushroom("enchanted_shroom_tall", "Tall Enchanted Shroom").setGroundPredicate(GroundPredicate.COMMON_AND_TERRANIA_GRASS).setLightLevel(0.3F);

        terrashroomBlockPink = new BlockJourneyMushroom(EnumMaterialTypes.WOOD, "terrashroomBlockPink", "Terrashroom Block", 0.5F, terramushroom);
        terrashroomBlockPurple = new BlockJourneyMushroom(EnumMaterialTypes.WOOD, "terrashroomBlockPurple", "Terrashroom Block", 0.5F, terramushroom);

        terraniaSapling = new BlockModSapling("terraniaSapling", "Terrania Sapling", new WorldGenTerraniaTree(true, 5, 10, terranianLog.getDefaultState(), terraniaLeaves.getDefaultState()));

        hotBrick = new BlockMod("hotBrick", "Hot Brick", 2.0F);
        hotBrick_fence = new BlockModFence(hotBrick, "hotBrickFence", "Hot Brick Fence");
        hotBrick_stairs = new BlockModStairs(hotBrick, "hotBrickStairs", "Hot Brick Stairs");

        hotGlass = new BlockJoinedGlass("hotGlass", "Hot Glass");
        smoothGlass = new BlockJoinedGlass("smoothGlass", "Smooth Glass");

        darkbloom = (JBlockDoublePlant) new JBlockDoublePlant("darkbloom", "Darkbloom").setGroundPredicate(GroundPredicate.COMMON_AND_DEPTHS_GRASS);
        hellThorn = (JBlockDoublePlant) new JBlockDoublePlant("hell_thorn", "Hell Thorn").setGroundPredicate(GroundPredicate.NETHER).setBoundingBox(JBlockDoublePlant.DOUBLE_BIG_PLANT_BB);
        hellThornRoot = new BlockRoot("hellThornRoot", "HellThornRoot");
        nethicGrass = new BlockModGrass(null, "nethicGrass", "Nethic Grass", 2.0F);

        tallGlowshroomRed = new BlockTallGlowshroom("tall_glowshroom_red", "Red Tall Glowshroom");
        tallGlowshroomGreen = new BlockTallGlowshroom("tall_glowshroom_green", "Green Tall Glowshroom");
        tallGlowshroomBlue = new BlockTallGlowshroom("tall_glowshroom_blue", "Blue Tall Glowshroom");
        glowshroomRed = new BlockGlowshroom("glowshroom_red", "Red Glowshroom");
        glowshroomGreen = new BlockGlowshroom("glowshroom_green", "Green Glowshroom");
        glowshroomBlue = new BlockGlowshroom("glowshroom_blue", "Blue Glowshroom");

        tallGoldenStalks = (JBlockDoublePlant) new JBlockDoublePlant("tall_golden_stalks", "Tall Golden Stalks", JourneyTabs.DECORATION).setGroundPredicate(GroundPredicate.COMMON_AND_EUCA_GOLD_GRASS);
        goldenBulb = (JBlockFlower) new JBlockFlower("goldenBulb", "Golden Bulb").setGroundPredicate(GroundPredicate.COMMON_AND_EUCA_GOLD_GRASS);
        goldenBloom = (JBlockFlower) new JBlockFlower("goldenBloom", "Golden Bloom").setGroundPredicate(GroundPredicate.COMMON_AND_EUCA_GOLD_GRASS);
        goldenStalks = (JBlockFlower) new JBlockFlower("goldenStalks", "Golden Stalks").setGroundPredicate(GroundPredicate.COMMON_AND_EUCA_GOLD_GRASS);

        tallGolditeStalks = (JBlockDoublePlant) new JBlockDoublePlant("tall_goldite_stalks", "Tall Goldite Stalks", JourneyTabs.DECORATION).setGroundPredicate(GroundPredicate.COMMON_AND_EUCA_GOLDITE_GRASS);
        golditeBulb = (JBlockFlower) new JBlockFlower("goldite_bulb", "Goldite Bulb").setGroundPredicate(GroundPredicate.COMMON_AND_EUCA_GOLDITE_GRASS);
        golditeFlower = (JBlockFlower) new JBlockFlower("goldite_flower", "Goldite Sunflower").setGroundPredicate(GroundPredicate.COMMON_AND_EUCA_GOLDITE_GRASS);
        golditeStalks = (JBlockFlower) new JBlockFlower("goldite_stalks", "Goldite Stalks").setGroundPredicate(GroundPredicate.COMMON_AND_EUCA_GOLDITE_GRASS);

        workshopCarpet = new BlockMod(EnumMaterialTypes.WOOL, "workshopCarpet", "Workshop Carpet", 0.5F);
        frozenGlass = new BlockJoinedGlass("frozenGlass", "Frozen Glass");
        // frozenPane = new BlockJoinedPane("frozenGlass");
        frozenLamp = (BlockMod) new BlockMod(EnumMaterialTypes.GLASS, "frozenLamp", "Frozen Lamp", 0.5F).setLightLevel(1.0F).setCreativeTab(JourneyTabs.DECORATION);
        iceStone = (BlockMod) new BlockMod(EnumMaterialTypes.GLASS, "iceStone", "Ice Stone", 0.5F).setLightLevel(1.0F).setCreativeTab(JourneyTabs.DECORATION);
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

        eucaSilverTallGrass = new BlockModFlower("euca_silver_tall_grass", "Silver Tall Grass", true);
        eucaSilverShortGrass = new BlockModFlower("euca_silver_short_grass", "Silver Short Grass", true);
        eucaSilverGoldFlower = new BlockModFlower("euca_silver_gold_flower", "Gold Flower");
        eucaSilverSprouts = new BlockModFlower("euca_silver_sprouts", "Silver Sprouts", true);

        frozenFlower = new BlockModFlower("frozenFlower", "Frozen Flower");
        depthsFlower = new BlockModFlower("depthsFlower", "Depths Flower");
        depthsBlueFlower = new BlockModFlower("depthsBlueFlower", "Depths Blue Flower");
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
        bossSpawner = new BlockBossSpawner("bossBlock", "Boss Spawner");
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
                .setLightLevel(1).setCreativeTab(JourneyTabs.DECORATION);

        stoneCraftingTable = new BlockCrafting("stoneCraftingTable", "Stone Crafting Table");
        summoningTable = new BlockSummoningTable("summoningTable", "Summoning Table");

        observerSpawner = new BlockModSpawner("observerSpawner", "Observer Spawner", "observer");
        screamerSpawner = new BlockModSpawner("screamerSpawner", "Screamer Spawner", "screamer");
        hellwingSpawner = new BlockModSpawner("hellwingSpawner", "Hellwing Spawner", "hellwing");

        goldbotSpawner = new BlockModSpawner("goldbotSpawner", "Goldbot Spawner", "goldbot");
        silverbotSpawner = new BlockModSpawner("silverbotSpawner", "Silverbot Spawner", "silverbot");

        hellbotSpawner = new BlockModSpawner("hellbotSpawner", "Hellbot Spawner", "hellbot");
        miniGhastSpawner = new BlockModSpawner("miniGhastSpawner", "Mini Ghast Spawner", "miniGhast");

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

        SENTERIAN_HEART_BLOCK = new BlockMod("senterian_heart_block", "Senterian Heart Block");
        SENTERIAN_HEART_BLOCK_WEAK = new BlockMod("senterian_heart_block_weak", "Senterian Heart Block");
        SENTERIAN_HEART_EYE = new BlockHeartEye("senterian_heart_eye", "Eye of the Heart");
        
        INCUBATOR = new BlockIncubator("incubator", "Incubator", false);
        INCUBATOR_LIT = new BlockIncubator("incubator_lit", "Incubator", true);

        elderBlock = new BlockChangeable("elderBlock", "Elder Block", JourneyItems.elderKey, JourneyBlocks.overseerElderSpawner);
        ancientMachineBlock = new BlockAncientBlock("ancientMachineBlock", "Ancient Machine Block");

        sizzleberryBush = new BlockModBush("sizzleberryBush", "Sizzleberry Bush", JourneyConsumables.sizzleberry, true);
        bradberryBush = new BlockModBush("bradberryBush", "Bradberry Bush", JourneyConsumables.bradberry, false);
        tangleberryBush = new BlockModBush("tangleberryBush", "Tangleberry Bush", JourneyConsumables.tangleberry,
                false);
        juiceberryBush = new BlockModBush("juiceberryBush", "Juiceberry Bush", JourneyConsumables.juiceberry, false);
        bogberryBush = new BlockModBush("bogberryBush", "Bogberry Bush", JourneyConsumables.bogberry, false);

        depthsCrystal = (JBlockFlower) new JBlockFlower(EnumMaterialTypes.GLASS, "depths_crystal", "Depths Crystal", JourneyTabs.DECORATION).setGroundPredicate(GroundPredicate.COMMON_AND_DEPTHS_GRASS).setRenderLayer(BlockRenderLayer.TRANSLUCENT);
        senterianAcid = new JBlockFluid("senterian_acid", JourneyFluids.FLUID_ACID, Material.LAVA);
    }
}