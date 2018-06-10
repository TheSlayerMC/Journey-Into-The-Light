package net.journey;

import java.util.ArrayList;

import net.journey.blocks.BlockBrittleIce;
import net.journey.blocks.BlockChangeable;
import net.journey.blocks.BlockCloud;
import net.journey.blocks.BlockCorbaPortalFrame;
import net.journey.blocks.BlockDarkbloom;
import net.journey.blocks.BlockDepthsLeaves;
import net.journey.blocks.BlockDungeonBlock;
import net.journey.blocks.BlockEucaPumpkin;
import net.journey.blocks.BlockEucaSapling;
import net.journey.blocks.BlockGemBlock;
import net.journey.blocks.BlockGlowshroom;
import net.journey.blocks.BlockHellThorn;
import net.journey.blocks.BlockIceLog;
import net.journey.blocks.BlockIcicle;
import net.journey.blocks.BlockJoinedGlass;
import net.journey.blocks.BlockLock;
import net.journey.blocks.BlockRoot;
import net.journey.blocks.BlockSenterianPortalFrame;
import net.journey.blocks.BlockStalks;
import net.journey.blocks.BlockSwampLamp;
import net.journey.blocks.BlockTerraFlower;
import net.journey.blocks.BlockTrophy;
import net.journey.blocks.BlockWitherFrame;
import net.journey.blocks.base.BlockModFire;
import net.journey.blocks.base.BlockModOre;
import net.journey.blocks.base.BlockNetherFlower;
import net.journey.blocks.base.BlockNetherPlant;
import net.journey.blocks.crop.base.BlockFruitCrop;
import net.journey.blocks.machines.BlockGrindstone;
import net.journey.blocks.machines.BlockIgniter;
import net.journey.blocks.machines.BlockJourneyChest;
import net.journey.blocks.machines.BlockKnowledgeTable;
import net.journey.blocks.machines.BlockSummoningTable;
import net.journey.blocks.portal.BlockBoilPortal;
import net.journey.blocks.portal.BlockCloudiaPortal;
import net.journey.blocks.portal.BlockCorbaPortal;
import net.journey.blocks.portal.BlockDepthsPortal;
import net.journey.blocks.portal.BlockEucaPortal;
import net.journey.blocks.portal.BlockFrozenPortal;
import net.journey.blocks.portal.BlockGoldenPortal;
import net.journey.blocks.portal.BlockSenterianPortal;
import net.journey.blocks.portal.BlockTerraniaPortal;
import net.journey.blocks.portal.BlockWitherPortal;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.EnumToolType;
import net.slayer.api.SlayerAPI;
import net.slayer.api.block.BlockMod;
import net.slayer.api.block.BlockModBars;
import net.slayer.api.block.BlockModDoor;
import net.slayer.api.block.BlockModFence;
import net.slayer.api.block.BlockModFlower;
import net.slayer.api.block.BlockModGrass;
import net.slayer.api.block.BlockModLadder;
import net.slayer.api.block.BlockModLeaves;
import net.slayer.api.block.BlockModLog;
import net.slayer.api.block.BlockModSpawner;
import net.slayer.api.block.BlockModStairs;
import net.slayer.api.block.BlockModVine;
import net.slayer.api.block.BlockNetherFurnace;

@EventBusSubscriber(modid=SlayerAPI.MOD_ID)
public class JourneyBlocks {

	public static ArrayList<String> blockName = new ArrayList<String>();
	public static ArrayList<Block> blocks = new ArrayList<Block>();
	
	/*public static final Block amethystOre = new BlockModOre("amethystOre", "Amethyst Ore").setHarvestLevel(EnumToolType.IRON_PICK);
	public static final Block amethystOre = new BlockModOre("amethystOre", "Amethyst Ore").setHarvestLevel(EnumToolType.IRON_PICK);
	public static final Block amethystOre = new BlockModOre("amethystOre", "Amethyst Ore").setHarvestLevel(EnumToolType.IRON_PICK); */
	public static final Block cobaltOre = new BlockModOre("cobaltOre", "cobalt Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
	public static final Block rubyOre = new BlockModOre("rubyOre", "Ruby Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
	public static final Block adamantineOre = new BlockModOre("adamantineOre", "Adamantine Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
	public static final Block mythrilOre = new BlockModOre("amethystOre", "Amethyst Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
	public static final Block verditeOre = new BlockModOre("verditeOre", "Verdite Ore").setHarvestLevel(EnumToolType.STONE_PICK);
	public static final Block sapphireOre = new BlockModOre("sapphireOre", "Sapphire Ore").setHarvestLevel(EnumToolType.IRON_PICK);
	public static final Block lavaRock = new BlockMod("lavaRock", "Lava Rock").setHarvestLevel(EnumToolType.DIAMOND_PICK);
	public static final Block shadiumOre = new BlockModOre("shadiumOre", "Shadium Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
	public static final Block luniumOre = new BlockModOre("luniumOre", "Lunium Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
	public static final Block hellstoneOre = new BlockModOre("hellstoneOre", "Bloodcrust Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
	public static final Block ashualOre = new BlockModOre("ashualOre", "Ashual Ore");
	public static final Block blaziumOre = new BlockModOre("blaziumOre", "Blazium Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
	public static final Block celestiumOre = new BlockModOre("celestiumOre", "Celestium Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
	public static final Block mekyumOre = new BlockModOre("mekyumOre", "Mekyum Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
	public static final Block koriteOre = new BlockModOre("koriteOre", "Korite Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
	public static final Block storonOre = new BlockModOre("storonOre", "Storon Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
	public static final Block flairiumOre = new BlockModOre("flairiumOre", "Flairium Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK).setLightLevel(0.5F);
	public static final Block desOre = new BlockModOre("desOre", "Des Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK).setLightLevel(0.5F);
	public static final Block enderilliumOre = new BlockModOre("enderilliumOre", "Enderillium Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
	public static final Block gorbiteOre = new BlockModOre("gorbiteOre", "Gorbite Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
	public static final Block orbaditeOre = new BlockModOre("orbaditeOre", "Orbadite Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
	public static final Block luniteOre = new BlockModOre("luniteOre", "Lunite Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
	public static final Block firestoneOre = new BlockModOre("firestoneOre", "Firestone Ores").setHarvestLevel(EnumToolType.DIAMOND_PICK);

	public static final Block sapphireBlock = new BlockModOre("sapphireBlock", "Sapphire Block").setHarvestLevel(EnumToolType.STONE_PICK);
	public static final Block shadiumBlock = new BlockModOre("shadiumBlock", "Shadium Block").setHarvestLevel(EnumToolType.STONE_PICK);
	public static final Block luniumBlock = new BlockModOre("luniumBlock", "Lunium Block").setHarvestLevel(EnumToolType.STONE_PICK);
	public static final Block hellstoneBlock = new BlockModOre("hellstoneBlock", "Hellstone Block").setHarvestLevel(EnumToolType.STONE_PICK);
	public static final Block ashualBlock = new BlockModOre("ashualBlock", "Ashual Block");
	public static final Block blaziumBlock = new BlockModOre("blaziumBlock", "Blazium Block").setHarvestLevel(EnumToolType.STONE_PICK);
	public static final Block celestiumBlock = new BlockModOre("celestiumBlock", "Celestium Block").setHarvestLevel(EnumToolType.STONE_PICK);
	public static final Block mekyumBlock = new BlockModOre("mekyumBlock", "Mekyum Block").setHarvestLevel(EnumToolType.STONE_PICK);
	public static final Block koriteBlock = new BlockModOre("koriteBlock", "Korite Block").setHarvestLevel(EnumToolType.STONE_PICK);
	public static final Block storonBlock = new BlockModOre("storonBlock", "Storon Block").setHarvestLevel(EnumToolType.STONE_PICK);
	public static final Block flairiumBlock = new BlockModOre("flairiumBlock", "Flairium Block").setHarvestLevel(EnumToolType.STONE_PICK);
	public static final Block desBlock = new BlockModOre("desBlock", "Des Block").setHarvestLevel(EnumToolType.STONE_PICK);
	public static final Block enderilliumBlock = new BlockModOre("enderilliumBlock", "Enderillium Block").setHarvestLevel(EnumToolType.STONE_PICK);
	public static final Block gorbiteBlock = new BlockModOre("gorbiteBlock", "Gorbite Block").setHarvestLevel(EnumToolType.STONE_PICK);
	public static final Block orbaditeBlock = new BlockModOre("orbaditeBlock", "Orbadite Block").setHarvestLevel(EnumToolType.STONE_PICK);
	public static final Block luniteBlock = new BlockModOre("luniteBlock", "Lunite Block").setHarvestLevel(EnumToolType.STONE_PICK);

	public static final Block witherwoodLog = new BlockModLog("witherwoodLog", "Witherwood Log");
	public static final Block witherwoodLeaves = new BlockModLeaves("witherwoodLeaves", "Witherwood Leaves", 1.0F);
	public static final Block sizzlerWoodLeaves = new BlockModLeaves("sizzlerWoodLeaves", "Deadblood Leaves", 2.0F);
	public static final Block sizzlerWoodLog = new BlockModLog("sizzlerWoodLog", "Deadblood Bark");
	public static final BlockNetherFlower deathGrass = new BlockNetherFlower("deathGrass", "Death Grass");
	public static final BlockNetherFlower hellBell = new BlockNetherFlower("hellBell", "Hell Bell");
	public static final Block hellshroom = new BlockNetherPlant("hellshroom", "Hellshroom");
	public static final Block bleedheartFruit = new BlockFruitCrop("bleedheartFruit");
	public static final Block heatSoil = new BlockMod(EnumMaterialTypes.DIRT, "heatSoil", "Nethic Soil", 2.0F);
	
	public static final Block igniterOn = new BlockIgniter("igniterOn", "Redstone Igniter");
	public static final Block igniter = new BlockIgniter("igniter", "Redstone Igniter");
	public static final Block greenGemBlock = new BlockMod("greenGemBlock", "Green Gem Block");
	public static final Block purpleGemBlock = new BlockMod("purpleGemBlock", "Purple Gem Block");
	public static final Block blueGemBlock = new BlockMod("blueGemBlock", "Blue Gem Block");
	public static final Block yellowGemBlock = new BlockMod("yellowGemBlock", "Yellow Gem Block");
	
	@SuppressWarnings("")
	/*public static final Block sizzleberryBush = new BlockModBush("sizzleberryBush", "Sizzleberry Bush", JourneyItems.sizzleberry, true);
	public static final Block bradberryBush = new BlockModBush("bradberryBush", "Bradberry Bush", JourneyItems.bradberry, false);
	public static final Block tangleberryBush = new BlockModBush("tangleberryBush", "Tangleberry Bush", JourneyItems.tangleberry, false);
	public static final Block juiceberryBush = new BlockModBush("juiceberryBush", "Juiceberry Bush", JourneyItems.juiceberry, false);
	public static final Block bogberryBush = new BlockModBush("bogberryBush", "Bogberry Bush", JourneyItems.bogberry, false);*/
	
	public static final Block nethicanSludge = new BlockMod(EnumMaterialTypes.SLIME, "nethicanSludge", "Nethican Sludge", 1.0F);
	
	public static final Block eucaGrass = new BlockModGrass(null, "eucaGrass", "Euca Grass", 2.0F);
	public static final Block eucaStone = new BlockMod("eucaStone", "Euca Stone", 2.0F);
	public static final Block eucaGoldLog = new BlockModLog("eucaGoldLog", "Golden Euca Log");
	public static final Block goldEucaPlank = new BlockMod(EnumMaterialTypes.WOOD, "goldEucaPlank", "Golden Euca Plank", 1.0F);
	public static final Block eucaGoldStairs = new BlockModStairs(eucaGoldLog, "eucaGoldStairs", "Golden Euca Stairs");
	public static final Block eucaGoldLeaves = new BlockModLeaves("eucaGoldLeaves", "Golden Euca Leaves", 1.0F);
	public static final Block eucaSilverLeaves = new BlockModLeaves("eucaSilverLeaves", "Silver Euca Leaves", 1.0F);
	public static final Block eucaSapling = new BlockEucaSapling("eucaSapling", "Euca Sapling");
	public static final Block eucaBricks = new BlockMod("eucaBricks", "Euca Bricks", 2.0F);
	public static final Block eucaTile = new BlockMod("eucaTile", "Euca Tile", 2.0F);
	
	public static final BlockMod depthsDirt = new BlockMod(EnumMaterialTypes.DIRT, "depthsDirt", "Depths Dirt", 2.0F);
	public static final Block depthsGrass = new BlockModGrass(depthsDirt, "depthsGrass", "Depths Grass", 2.0F);
	public static final Block depthsStone = new BlockMod("depthsStone", "Depths Stone", 2.0F);
	public static final Block depthsLog = new BlockModLog("depthsLog", "Depths Log");
	public static final Block depthsPlank = new BlockMod(EnumMaterialTypes.WOOD, "depthsPlank", "Depths Plank", 1.0F);
	public static final Block depthsStairs = new BlockModStairs(depthsPlank, "depthsStairs", "Depths Stairs");
	public static final Block depthsLeaves = new BlockDepthsLeaves("depthsLeaves", "Depths Leaves", 1.0F);
	public static final Block darkBrick = new BlockMod("darkBrick", "Dark Brick", 2.0F).setBlockUnbreakable().setResistance(10000000.0F);
	public static final Block darkFloor = new BlockMod("darkFloor", "Dark Floor", 2.0F).setBlockUnbreakable().setResistance(10000000.0F);
	//public static final Block darkTile = new BlockMod("darkTile", "Dark Tile", 2.0F).setBlockUnbreakable();
	public static final Block darkShingle = new BlockMod("darkShingle", "Dark Shingle", 2.0F).setBlockUnbreakable().setResistance(10000000.0F);
	
	public static final Block iceLeaves = new BlockDepthsLeaves("iceLeaves", "Ice Leaves", 1.0F);
	

	//public static final BlockMod godDirt = new BlockMod(EnumMaterialTypes.DIRT, "godDirt", 2.0F);
	//public static final Block godGrass = new BlockModGrass(godDirt, "godGrass", 2.0F);
	//public static final Block godStone = new BlockMod("godStone", 2.0F);
	
	public static final BlockMod frozenDirt = new BlockMod(EnumMaterialTypes.DIRT, "frozenDirt", "Frozen Dirt", 2.0F);
	public static final BlockMod frozenStone = new BlockMod(EnumMaterialTypes.STONE, "frozenStone", "Frozen Stone", 5.0F);
	public static final Block frozenGrass = new BlockModGrass(frozenDirt, "frozenGrass", "Frozen Grass", 2.0F);
	public static final Block frozenLeaves = new BlockModLeaves("frozenLeaves", "Frozen Leaves", 0.5F).setFrozenPlant().setLightLevel(1);
	public static final Block frozenBark = new BlockModLog("frozenBark", "Frozen Bark");
	public static final Block frozenPlanks = new BlockMod(EnumMaterialTypes.WOOD, "frozenPlanks", "Frozen Planks", 0.5F);
	
	public static final BlockMod withanDirt = new BlockMod(EnumMaterialTypes.DIRT, "withanDirt", "Withan Dirt", 2.0F);
	public static final BlockMod withanRock = new BlockMod(EnumMaterialTypes.STONE, "withanRock", "Withan Rock", 5.0F);
	public static final Block withanGrass = new BlockModGrass(withanDirt, "withanGrass", "Withan Grass", 2.0F);
	public static final Block withanLeaves = new BlockModLeaves("withanLeaves", "Withering Leaves", 0.5F);
	public static final Block withanBark = new BlockModLog("withanBark", "Withering Bark");
	public static final Block withanRockReinforced = new BlockMod("reinforcedWithan", "Withan Rock").setResistance(10000000000F).setBlockUnbreakable();
	public static final Block withanLamp = new BlockMod(EnumMaterialTypes.GLASS, "withanLamp", "Withan Lamp", 0.1F).setLightLevel(1.0F).setCreativeTab(JourneyTabs.decoration);
	public static final Block withanLight = new BlockMod(EnumMaterialTypes.GLASS, "withanLight", "Withan Light", 0.1F).setLightLevel(1.0F).setCreativeTab(JourneyTabs.decoration); 
	
	public static final Block goldenGrass = new BlockModGrass((BlockMod)null, "goldenGrass", "Golden Grass", 0.5F);
	public static final BlockMod goldenStone = new BlockMod("goldenStone", "Golden Stone");
	
	public static final BlockMod corbaStone = new BlockMod("corbaStone", "Corba Stone");
	public static final Block corbaGrass = new BlockModGrass((BlockMod)null, "corbaGrass", "Corba Grass", 0.5F);
	public static final Block corbaLog = new BlockModLog("corbaLog", "Corba Log");
	public static final Block corbaLeaves = new BlockModLeaves("corbaLeaves", "Corba Leaves", 0.2F);
	public static final Block corbaPillar = new BlockMod("corbaPillar", "Corba Pillar");
	public static final Block corbaPlank = new BlockMod(EnumMaterialTypes.WOOD, "corbaPlank", "Corba Plank", 0.5F);
	public static final Block corbaLamp = new BlockMod(EnumMaterialTypes.GLASS, "corbaLamp", "Corba Lamp", 0.1F).setLightLevel(1.0F).setCreativeTab(JourneyTabs.decoration);
	public static final Block corbaSentryBrick = new BlockMod("corbaSentryBrick", "Corba Sentry Brick");
	
	public static final Block boilingLamp = new BlockMod(EnumMaterialTypes.GLASS, "boilingLamp", "Boiling Lamp", 0.1F).setLightLevel(1.0F).setCreativeTab(JourneyTabs.decoration);
	public static final Block blazierBricks = new BlockMod("blazierBricks", "Blazier Bricks"); 
	public static final BlockMod ashBlock = new BlockMod("ashBlock", "Ash");
	public static final Block hotBlock = new BlockModGrass(ashBlock, "hotGround", "Hot Ground", 2.0F);
	public static final Block brisonStone = new BlockMod("brisonStone", "Brison Stone", 2.0F);
	public static final Block darkBrisonBrick = new BlockMod("darkBrisonBrick", "Dark Brison Brick", 2.0F);
	public static final Block redBrisonBrick = new BlockMod("redBrisonBrick", "Red Brison Brick", 2.0F);
	public static final Block smallBrisonBrick = new BlockMod("smallBrisonBrick", "Small Brison Brick", 2.0F);
	public static final Block boilingBars = new BlockModBars("boilingBars", "Brison Bars", 5.0F);
	public static final Block burningLeaves = new BlockModLeaves("burningLeaves", "Burning Leaves", 0.5F).setBurningPlant();
	public static final Block boilingLog = new BlockModLog("boilingLog", "Boiling Log"); 
	
	public static final BlockMod cloudiaDirt = new BlockMod(EnumMaterialTypes.DIRT, "cloudiaDirt", "Cloudia Dirt", 2.0F);
	public static final Block cloudiaGrass = new BlockModGrass(cloudiaDirt, "cloudiaGrass", "Cloudia Grass", 2.0F);
	public static final Block cloudiaLog = new BlockModLog("cloudiaLog", "Cloudia Log"); 
	public static final Block cloudiaRock = new BlockMod("cloudiaRock", "Cloudia Rock"); 
	public static final Block cloudiaBrick = new BlockMod("cloudiaBrick", "Cloudia Brick").setLightLevel(0.5F); 
	public static final Block cloudiaWall = new BlockMod("cloudiaWall", "Cloudia Wall").setLightLevel(0.5F);  
	public static final Block cloudiaTile = new BlockMod("cloudiaTile", "Cloudia Tile").setLightLevel(0.5F); 
	public static final Block cloudiaLeaves = new BlockModLeaves("cloudiaLeaves", "Cloudia Leaves", 0.5F).setLightLevel(2);
	public static final Block cloudiaLamp = new BlockMod(EnumMaterialTypes.GLASS, "cloudiaLamp", "Cloudia Lamp", 0.1F).setLightLevel(1.0F).setCreativeTab(JourneyTabs.decoration);
	//public static final Block cloudiaPost = new BlockCloudiaPost("cloudiaPost");
	public static final Block pinkCloudiaCloud = new BlockCloud("pinkCloudiaCloud", "Pink Cloudia Cloud");
	public static final Block blueCloudiaCloud = new BlockCloud("blueCloudiaCloud", "Blue Cloudia Cloud");
	public static final Block lightBlueCloudiaCloud = new BlockCloud("lightBlueCloudiaCloud", "Light Blue Cloudia Cloud");
	public static final BlockModFlower cloudiaTallGrass = new BlockModFlower("cloudiaTallGrass", "Cloudia Tall Grass");
	public static final BlockModFlower cloudiaFlower = new BlockModFlower("cloudiaFlower", "Cloudia Flower");
	
	public static final Block swampLamp = new BlockSwampLamp("swampLamp", "Swamp Fly Bottle", 0).setLightLevel(1);

	public static final Block frozenChest = new BlockJourneyChest("frozenChest", "Frozen Chest");
	public static final Block netherChest = new BlockJourneyChest("netherChest", "Nether Chest");
	public static final Block boilingChest = new BlockJourneyChest("boilingChest", "Boiling Chest");
	public static final Block eucaChest = new BlockJourneyChest("eucaChest", "Euca Chest");
	public static final Block depthsChest = new BlockJourneyChest("depthsChest", "Depths Chest");
	public static final Block corbaChest = new BlockJourneyChest("corbaChest", "Corba Chest");
	public static final Block cloudiaChest = new BlockJourneyChest("cloudiaChest", "Cloudia Chest");
	public static final Block terraniaChest = new BlockJourneyChest("terraniaChest", "Terrania Chest");

	public static final Block nethicDungeonBricks = new BlockMod("nethicDungeonBricks", "Nethic Dungeon Bricks");
	public static final Block boilChain = new BlockModBars("boilChain", "Boil Chain", 5.0F).setBlockUnbreakable().setResistance(10000000.0F);
	public static final Block boilGate = new BlockModBars("boilGate", "Boil Gate", 5.0F).setBlockUnbreakable().setResistance(10000000.0F);
	public static final Block boilLock = new BlockLock("boilLock", "Boil Lock", JourneyItems.boilKey).setBlockUnbreakable().setResistance(10000000.0F);

	public static final Block darklyGate = new BlockModBars("darklyGate", "Darkly Gate", 5.0F).setBlockUnbreakable().setResistance(10000000.0F);
	public static final Block darklyLock = new BlockLock("darklyLock", "Darkly Lock", JourneyItems.darkKey).setBlockUnbreakable().setResistance(10000000.0F);
	
	public static final Block corbaPost = new BlockModFence(pinkCloudiaCloud, "corbaPost", "Corba Post");
	public static final Block sizzlingPost = new BlockModFence(pinkCloudiaCloud, "sizzlingPost", "Sizzling Post");
	
	public static final Block senterianBricks = new BlockMod("senterianBricks", "Senterian Bricks").setBlockUnbreakable().setResistance(10000000.0F);
	public static final Block senterianRock = new BlockMod("senterianRock", "Senterian Rock").setBlockUnbreakable().setResistance(10000000.0F);
	public static final Block senterianCarvedRock = new BlockMod("senterianCarvedRock", "Senterian Carved Rock").setBlockUnbreakable().setResistance(10000000.0F);
	public static final Block senterianFloor = new BlockMod("senterianFloor", "Senterian Floor").setBlockUnbreakable().setResistance(10000000.0F);
	public static final Block senterianBars = new BlockModBars("senterianBars", "Senterian Bars", 5.0F).setBlockUnbreakable().setResistance(10000000.0F);
	public static final Block senterianLightLamp = new BlockMod(EnumMaterialTypes.GLASS, "senterianLightLamp", "Senterian Light Lamp", 0.1F).setLightLevel(0.5F).setCreativeTab(JourneyTabs.decoration).setBlockUnbreakable().setResistance(10000000.0F);
	public static final Block senterianMellowLamp = new BlockMod(EnumMaterialTypes.GLASS, "senterianMellowLamp", "Senterian Mellow Lamp", 0.1F).setLightLevel(0.5F).setCreativeTab(JourneyTabs.decoration).setBlockUnbreakable().setResistance(10000000.0F);
	public static final Block senterianGuardianLamp = new BlockMod(EnumMaterialTypes.GLASS, "senterianGuardianLamp", "Senterian Guardian Lamp", 0.1F).setLightLevel(0.5F).setCreativeTab(JourneyTabs.decoration).setBlockUnbreakable().setResistance(10000000.0F);
	public static final Block senterianPost = new BlockModFence(senterianBricks, "senterianPost", "Senterian Post").setBlockUnbreakable().setResistance(10000000.0F);
	
	public static final Block trophy = new BlockTrophy("trophy", "Trophy");
	public static final Block trophySoul = new BlockTrophy("trophySoul", "Soul Watcher Trophy");
	public static final Block trophyBlaze = new BlockTrophy("trophyBlaze", "Blazier Trophy");
	public static final Block trophyEudor = new BlockTrophy("trophyEudor", "Eudor Trophy");
	public static final Block trophyCor = new BlockTrophy("trophyCor", "Corallator Trophy");
	public static final Block trophyScale = new BlockTrophy("trophyScale", "Scale Trophy");
	public static final Block trophyRoc = new BlockTrophy("trophyRoc", "Thunderbird Trophy");
	public static final Block trophySentry = new BlockTrophy("trophySentry", "Sentry King Trophy");
	public static final Block trophyLogger = new BlockTrophy("trophyLogger", "Logger Trophy");
	public static final Block trophyTerra = new BlockTrophy("trophyTerra", "Terranian Protector Trophy");
	public static final Block trophyStalk = new BlockTrophy("trophyStalk", "Sky Stalker Trophy");
	public static final Block trophyNetherBeast = new BlockTrophy("trophyNetherBeast", "Nether Beast Trophy");
	public static final Block trophyWitherBeast = new BlockTrophy("trophyWitherBeast", "Withering Beast Trophy");
	
	/*public static final BlockMod wastelandsStone = new BlockWastelandsStone("wastelandsStone");
	public static final Block wastelandsGrass = new BlockModGrass((BlockMod)null, "wastelandsGrass", 2.0F);
	public static final Block wastelandsLog = new BlockModLog("wastelandsLog");
	public static final Block wastelandsBranches = new BlockModLeaves("wastelandsBranches", 2.0F).setStepSound(EnumMaterialTypes.WOOD.getSound());*/

	public static final BlockWitherPortal witherPortal = new BlockWitherPortal("witherPortal", "Withanian Portal");
	public static final Block witherPortalFrame = new BlockWitherFrame("witherPortalFrame", "Withanian Portal Frame");
	
	public static final BlockEucaPortal eucaPortal = new BlockEucaPortal("eucaPortal");
	public static final Block eucaPortalFrame = new BlockMod("eucaPortalFrame", "Euca Portal Frame", 3.0F);
	
	public static final BlockGoldenPortal goldenPortal = new BlockGoldenPortal("goldenPortal");
	public static final Block goldenPortalFrame = new BlockMod("goldenPortalFrame", "Golden Portal Frame", 3.0F);
	
	public static final BlockDepthsPortal depthsPortal = new BlockDepthsPortal("depthsPortal");
	public static final Block depthsPortalFrame = new BlockMod("depthsPortalFrame", "Depths Portal Frame", 3.0F);
	
	public static final BlockBoilPortal boilPortal = new BlockBoilPortal("boilingPortal");
	public static final Block boilPortalFrame = new BlockMod("boilingPortalFrame", "Boiling Portal Frame", 3.0F);
	
	public static final BlockFrozenPortal frozenPortal = new BlockFrozenPortal("frozenPortal");
	public static final Block frozenPortalFrame = new BlockMod("frozenPortalFrame", "Frozen Portal Frame", 3.0F);
	
	public static final BlockCorbaPortal corbaPortal = new BlockCorbaPortal("corbaPortal", "Corba Portal");
	public static final Block corbaPortalFrame = new BlockCorbaPortalFrame("corbaPortalFrame", "Corba Portal Frame");
	
	public static final BlockTerraniaPortal terraniaPortal = new BlockTerraniaPortal("terraniaPortal");
	public static final Block terraniaPortalFrame = new BlockMod("terraniaPortalFrame", "Terrania Portal Frame", 3.0F);
	
	public static final BlockSenterianPortal senterianPortal = (BlockSenterianPortal)new BlockSenterianPortal("senterianPortal", "senterianPortal").setCreativeTab(null);
	public static final Block senterianPortalFrame = new BlockSenterianPortalFrame("senterianPortalFrame", "Senterian Portal Frame").setCreativeTab(null);
	//public static final BlockWastelandsPortal wastelandsPortal = (BlockWastelandsPortal)new BlockWastelandsPortal("wastelandsPortal");
	//public static final Block wastelandsPortalFrame = new BlockMod("wastelandsPortalFrame");
	public static final BlockCloudiaPortal cloudiaPortal = new BlockCloudiaPortal("cloudiaPortal");
	public static final Block cloudiaPortalFrame = new BlockMod("cloudiaPortalFrame", "Cloudia Portal Frame", 3.0F);
	
	public static final Block depthsLights = new BlockMod(EnumMaterialTypes.GLASS, "depthsLight", "Depths Light", 0.5F).setLightLevel(1.0F);
	
	public static final Block fire = new BlockModFire("fire", "Essential Fire");
	
	public static final Block mossyEssenceStone = new BlockMod("mossyEssenceStone", "Mossy Essence Stone", 2.0F);

	public static final Block dungeonBrick = new BlockDungeonBlock("dungeonBrick", "Dungeon Brick");
	public static final Block dungeonCrackedBrick = new BlockDungeonBlock("dungeonCrackedBrick", "Dungeon Cracked Brick");
	public static final Block dungeonChisledBrick = new BlockDungeonBlock("dungeonChiseledBrick", "Dungeon Chiseled Brick");
	public static final Block dungeonBrickCarved = new BlockDungeonBlock("dungeonBrickCarved", "Dungeon Carved Brick");
	public static final Block dungeonLamp = new BlockDungeonBlock("dungeonLamp", "Dungeon Lamp", true).setLightLevel(0.5F).setCreativeTab(JourneyTabs.decoration);
	
	public static final Block dungeonBrickStairs = new BlockModStairs(dungeonBrick, "dungeonBrickStairs", "Dungeon Brick Stairs");
	public static final Block dungeonCrackedBrickStairs = new BlockModStairs(dungeonCrackedBrick, "dungeonCrackedBrickStairs", "Cracked Dungeon Brick Stairs");
	public static final Block dungeonChisledBrickStairs = new BlockModStairs(dungeonChisledBrick, "dungeonChiseledBrickStairs", "Chiseled Dungeon Brick Stairs");
	public static final Block dungeonBrickCarvedStairs = new BlockModStairs(dungeonBrickCarved, "dungeonBrickCarvedStairs", "Carved Dungeon Brick Stairs");
	public static final Block dungeonLampStairs = new BlockModStairs(dungeonLamp, "dungeonLampStairs", "Dungeon Lamp Stairs", true);

	public static final Block dungeonBrickFence = new BlockModFence(dungeonBrick, "dungeonBrickFence", "Dungeon Brick Fence");
	public static final Block dungeonCrackedBrickFence = new BlockModFence(dungeonCrackedBrick, "dungeonCrackedBrickFence", "Cracked Dungeon Brick Fence");
	public static final Block dungeonChisledBrickFence = new BlockModFence(dungeonChisledBrick, "dungeonChiseledBrickFence", "Chiseled Dungeon Brick Fence");
	public static final Block dungeonBrickCarvedFence = new BlockModFence(dungeonBrickCarved, "dungeonBrickCarvedFence", "Carved Dungeon Brick Fence");
	public static final Block cloudiaPost = new BlockModFence(pinkCloudiaCloud, "cloudiaPost", "Cloudia Post");
	public static final Block terranianPost = new BlockModFence(pinkCloudiaCloud, "terranianPost", "Terranian Post");
	public static final Block dungeonLampFence = new BlockModFence(dungeonLamp, "dungeonLampFence", "Dungeon Lamp Fence", true);
	
	public static final Block terraniaLeaves = new BlockModLeaves("terranianLeaves", "Terrania Leaves", 1.0F);
	public static final Block terraniaVine = new BlockModVine("terranianVine", "Terrania Vine", 12);
	public static final BlockMod terranianDirt = new BlockMod(EnumMaterialTypes.DIRT, "terranianDirt", "Terranian Dirt", 2.0F);
	public static final Block terranianLog = new BlockModLog("terranianLog", "Terranian Log");
	public static final Block terranianGrass = new BlockModGrass(terranianDirt, "terranianGrass", "Terranian Grass", 2.0F);
	public static final Block terranianStone = new BlockMod("terranianStone", "Terranian Stone", 2.0F);
	public static final Block terranianBars = new BlockModBars("terranianBars", "Terranian Bars", 5.0F);
	public static final Block terranianDarkPanels = new BlockMod("terranianDarkPanels", "Terranian Dark Panels", 2.0F);
	public static final Block terranianPanels = new BlockMod("terranianPanels", "Terranian Panels", 2.0F);
	public static final BlockModFlower terranianTallgrass = new BlockModFlower("terranianTallgrass", "Terranian Tallgrass");
	public static final Block terragrow = new BlockTerraFlower("terragrow", "Terranian Flower");
	public static final Block terraniaLamp = new BlockMod(EnumMaterialTypes.GLASS, "terraniaLamp", "Terrania Lamp", 0.1F).setLightLevel(1.0F).setCreativeTab(JourneyTabs.decoration);
	
	public static final Block hotBrick = new BlockMod("hotBrick", "Hot Brick", 2.0F);
	public static final Block hotBrick_fence = new BlockModFence(hotBrick, "hotBrickFence", "Hot Brick Fence");
	public static final Block hotBrick_stairs = new BlockModStairs(hotBrick, "hotBrickStairs", "Hor Brick Stairs");

	public static final Block hotGlass = new BlockJoinedGlass("hotGlass", "Hot Glass");
	public static final Block smoothGlass = new BlockJoinedGlass("smoothGlass", "Smooth Glass");
	//public static final Block hotGlassPane = new BlockJoinedPane("hotGlass");
	//public static final Block smoothGlassPane = new BlockJoinedPane("smoothGlass");
	
	//public static final Block witheringBeastStatue = new BlockStatue("witheringBeastStatue", new ModelWitheringBeastStatue(), EnumSounds.WITHER);
	//public static final Block netherBeastStatue = new BlockStatue("netherBeastStatue", new ModelNetherBeastStatue(), EnumSounds.NETHER_BEAST);
	//public static final Block calciaStatue = new BlockStatue("calciaStatue", new ModelCalciaStatue(), EnumSounds.CALCIA);
	//public static final Block eudorStatue = new BlockStatue("eudorStatue", new ModelEudorStatue(), EnumSounds.CALCIA);
	//public static final Block enderChampionStatue = new BlockStatue("enderChampionStatue", new ModelEnderChampionStatue());
	//public static final Block wraithStatue = new BlockStatue("wraithStatue", new ModelWraithStatue());
	
	public static final Block darkbloomTop = new BlockDarkbloom("darkbloomTop", "Darkbloom", true);
	public static final Block darkbloomBottom = new BlockDarkbloom("darkbloomBottom", "Darkbloom", false);
	
	public static final Block redGlowshroomTop = new BlockGlowshroom("redGlowshroomTop", "Red Glowshroom", true);
	public static final Block redGlowshroomBottom = new BlockGlowshroom("redGlowshroomBottom", "Red Glowshroom", false);
	
	public static final Block hellThornTop = new BlockHellThorn("hellThornTop", "Hell Thorn", true);
	public static final Block hellThornBottom = new BlockHellThorn("hellThornBottom", "Hell Thorn", false);
	public static final Block hellThornRoot = new BlockRoot("hellThornRoot", "HellThornRoot");
	public static final Block nethicGrass = new BlockModGrass(null, "nethicGrass", "Nethic Grass", 2.0F);
	
	public static final Block greenGlowshroomTop = new BlockGlowshroom("greenGlowshroomTop", "Green Glowshroom", true);
	public static final Block greenGlowshroomBottom = new BlockGlowshroom("greenGlowshroomBottom", "Green Glowshroom", false);
	public static final Block blueGlowshroomTop = new BlockGlowshroom("blueGlowshroomTop", "Blue Glowshroom", true);
	public static final Block blueGlowshroomBottom = new BlockGlowshroom("blueGlowshroomBottom", "Blue Glowshroom", false);
	
	public static final Block goldenStalksTop = new BlockStalks("goldenStalksTop", "Golden Stalks", true).setCreativeTab(null);
	public static final Block goldenStalksBottom = new BlockStalks("goldenStalksBottom", "Golden Stalks", false).setCreativeTab(null);
	public static final BlockModFlower goldenBulb = (BlockModFlower) new BlockModFlower("goldenBulb", "Golden Bulb").setCreativeTab(null);
	public static final BlockModFlower goldenBloom = (BlockModFlower) new BlockModFlower("goldenBloom", "Golden Bloom").setCreativeTab(null);
	public static final BlockModFlower goldenStalks = (BlockModFlower) new BlockModFlower("goldenStalks", "Golden Stalks").setCreativeTab(null);
		
	public static final Block workshopCarpet = new BlockMod(EnumMaterialTypes.WOOL, "workshopCarpet", "Workshop Carpet", 0.5F);
	public static final Block frozenGlass = new BlockJoinedGlass("frozenGlass", "Frozen Glass");
	//public static final Block frozenPane = new BlockJoinedPane("frozenGlass");
	public static final Block frozenLamp = new BlockMod(EnumMaterialTypes.GLASS, "frozenLamp", "Frozen Lamp", 0.5F).setLightLevel(1.0F).setCreativeTab(JourneyTabs.decoration);
	public static final Block workshopStone = new BlockMod(EnumMaterialTypes.STONE, "workshopStone", "Workshop Stone", 0.5F);
	public static final Block workshopStoneStair = new BlockModStairs(workshopStone, "workshopStairs", "Workshop Stairs");
	public static final Block workshopStoneFence = new BlockModFence(workshopStone, "workshopStoneFence", "Workshop Fence");
	public static final BlockModDoor frozenDoor = new BlockModDoor(EnumMaterialTypes.STONE, 5.0F, "frozenDoorBlock", "Workshop Fence");
	public static final Block candyCane = new BlockMod(EnumMaterialTypes.WOOD, "candyCane", "Candy Cane", 2.0F);
	public static final Block frozenBrick = new BlockMod("frozenBricks", "Frozen Bricks");
	public static final Block icicle = new BlockIcicle("icicle", "Icicle");
	public static final BlockModFlower frostberryThorn = new BlockModFlower("frostberryThorn", "Frostberry Thorn").setContactDamage().setFrozenPlant();
	public static final BlockModFlower iceBud = new BlockModFlower("iceBud", "Ice Bud").setFrozenPlant();
	public static final BlockModFlower frozenBlooms = new BlockModFlower("frozenBlooms", "Frozen Blooms").setFrozenPlant();
	public static final BlockModFlower permaFlower = new BlockModFlower("permaFlower", "Perma Flower").setFrozenPlant();
	public static final BlockModFlower shiverFlower = new BlockModFlower("shiverFlower", "Shiver Flower").setFrozenPlant();
	public static final BlockModFlower iceBush = new BlockModFlower("iceBush", "Ice Bush").setFrozenPlant();
	public static final BlockMod iceLog = new BlockIceLog();
	public static final BlockMod brittleIce = new BlockBrittleIce(JourneyBlocks.brittleIce, "brittleIce", "Brittle Ice", 2);
	
	public static final BlockModFlower eucaTallGrass = new BlockModFlower("eucaTallGrass", "Euca Tall Grass");
	public static final BlockModFlower eucaTallFlowers = new BlockModFlower("eucaTallFlowers", "Euca Tall Flowers");
	public static final BlockModFlower eucaBlueFlower = new BlockModFlower("eucaBlueFlower", "Euca Blue Flower");
	public static final BlockModFlower frozenFlower = new BlockModFlower("frozenFlower", "Frozen Flower");
	public static final BlockModFlower depthsFlower = new BlockModFlower("depthsFlower", "Depths Flower");
	public static final BlockModFlower depthsBlueFlower = new BlockModFlower("depthsBlueFlower", "Depths Blue Flower").setLightLevel(0.625F);
	public static final BlockModFlower corbaFlower = new BlockModFlower("corbaFlower", "Corba Flower");
	public static final BlockModFlower corbaTallGrass = new BlockModFlower("corbaTallGrass", "Corba Tall Grass");
	public static final BlockModFlower corbaSpeckledFlower = new BlockModFlower("corbaSpeckledFlower", "Speckled Flower");
	public static final BlockModFlower corbaLightPurpleFlower = new BlockModFlower("corbaLightPurpleFlower", "Light Purple Flower");
	public static final BlockModFlower corbaRedFlower = new BlockModFlower("corbaRedFlower", "Red Flower");
	public static final BlockModFlower corbaBlueFlower = new BlockModFlower("corbaBlueFlower", "Blue Flower");
	public static final BlockModFlower corbaDarkPurpleFlower = new BlockModFlower("corbaDarkPurpleFlower", "Dark Purple Flower");
	public static final BlockModFlower flameFlower = new BlockModFlower("flameFlower", "Flame Flower");
	public static final BlockModFlower infernoPlant = new BlockModFlower("infernoPlant", "Inferno Plant");
	public static final BlockModFlower burntGrass = new BlockModFlower("burntGrass", "Burnt Grass");
	
	public static final Block grindstone = new BlockGrindstone("grindstone");
	public static final Block blueGems = new BlockGemBlock("blueGems", "Blue Gems", false);
	public static final Block redGems = new BlockGemBlock("redGems", "Red Gems", true);
	
	public static final Block blackBlock = new BlockMod(EnumMaterialTypes.STONE, "blackBlock", "Black Block", 0.5F);
	public static final Block whiteBlock = new BlockMod(EnumMaterialTypes.STONE, "whiteBlock", "White Block", 0.5F);

	public static final Block eucaPumpkin = new BlockEucaPumpkin("eucaPumpkin", "Euca Pumpkin");
	public static final Block cloud = new BlockCloud("cloud", "Cloud");
	
	public static final Block corbaLadder = new BlockModLadder("corbaLadder", "Corba Ladder", 0);
	public static final Block largeNetherBrick = new BlockMod("largeNetherBrick", "Large Nether Brick");
	public static final Block compactNetherBrick = new BlockMod("compactNetherBrick", "Compact Nether Brick").setBlockUnbreakable().setResistance(10000000.0F);
	public static final Block nethicLamp = new BlockMod(EnumMaterialTypes.GLASS, "nethicLamp", "Nethic Lamp", 0.1F).setLightLevel(1).setCreativeTab(JourneyTabs.decoration);
	
	public static final Block knowledgeTable = new BlockKnowledgeTable("knowledgeTable", "Knowledge Table").setCreativeTab(null);
	public static final Block summoningTable = new BlockSummoningTable("summoningTable", "Summoning Table");
	//public static final Block trophyTable = new BlockTrophyTable("trophyTable", "Trophy Table");
	public static final Block bossAltar = new BlockMod("bossAltar", "Boss Altar");
	
	public static final Block observerSpawner = new BlockModSpawner("observerSpawner", "Observer Spawner", "observer");
	public static final Block screamerSpawner = new BlockModSpawner("screamerSpawner", "Screamer Spawner", "screamer");
	
	public static final Block goldbotSpawner = new BlockModSpawner("goldbotSpawner", "Goldbot Spawner", "goldbot");
	public static final Block silverbotSpawner = new BlockModSpawner("silverbotSpawner", "Silverbot Spawner", "silverbot");
	
	public static final Block hellbotSpawner = new BlockModSpawner("hellbotSpawner", "Hellbot Spawner", "hellbot");
	
	public static final Block purplianSpawner = new BlockModSpawner("purplianSpawner", "Purplian Spawner", "purplian");
	
	public static final Block starlightGolemSpawner = new BlockModSpawner("starlightGolemSpawner", "Starlight Golem Spawner", "starlightGolem");
	public static final Block starlightTransporterSpawner = new BlockModSpawner("starlightTransporterSpawner", "Starlight Transporter Spawner", "starlightTransporter");

	public static final Block sentarAlter = new BlockMod("sentarAlter", "Sentar Alter");
	
	public static final Block sorcererSpawner = new BlockModSpawner("sorcererSpawner", "Sorcerer Spawner", "darkSorcerer");
	public static final Block frostbiterSpawner = new BlockModSpawner("frostbiterSpawner", "Frostbiter Spawner", "frozenFrostbiter");
	
	public static final Block overseerSpawner = new BlockModSpawner("overseerSpawner", "Overseer Spawner", "overseer");
	public static final Block overseerElderSpawner = new BlockModSpawner("overseerElderSpawner", "Overseer Elder Spawner", "overseerElder");
	
	public static final Block elderBlock = new BlockChangeable("elderBlock", "Elder Block", JourneyItems.elderKey, JourneyBlocks.overseerElderSpawner);
	
	public static final Block netherFurnace = new BlockNetherFurnace("netherFurnace", "Nethic Furnace", false);
	public static final Block netherFurnaceActive = new BlockNetherFurnace("netherFurnaceActive", "Nethic Furnace", true).setLightLevel(0.7F).setCreativeTab(null);

	/**
	public static final Block boilingFurnace = new BlockNetherFurnace("boilingFurnace", "Boiling Furnace", false);
	public static final Block boilingFurnaceActive = new BlockNetherFurnace("boilingFurnaceActive", "Boiling Furnace", true).setLightLevel(1).setCreativeTab(null);
	*/
	
	//public static final Block tropicalWater = new BlockTropicalWater("tropicalWater");
	
	//public static final Block bowCraftingTable = new BlockBowCrafter("bowCraftingTable");
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		for(Block b : blocks)
			event.getRegistry().registerAll(b);
	}
}