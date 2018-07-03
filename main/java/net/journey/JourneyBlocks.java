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
import net.journey.blocks.machines.BlockTrophyTable;
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
import net.minecraftforge.fml.common.Mod.Instance;
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

	public static Block cobaltOre;
	public static Block rubyOre;
	public static Block adamantineOre;
	public static Block mythrilOre;
	public static Block verditeOre;
	public static Block sapphireOre;
	public static Block lavaRock;
	public static Block shadiumOre;
	public static Block luniumOre;
	public static Block hellstoneOre;
	public static Block ashualOre;
	public static Block blaziumOre;
	public static Block celestiumOre;
	public static Block mekyumOre;
	public static Block koriteOre;
	public static Block storonOre;
	public static Block flairiumOre;
	public static Block desOre;
	public static Block enderilliumOre;
	public static Block gorbiteOre;
	public static Block orbaditeOre;
	public static Block luniteOre;
	public static Block firestoneOre;

	public static Block sapphireBlock;
	public static Block shadiumBlock;
	public static Block luniumBlock;
	public static Block hellstoneBlock;
	public static Block ashualBlock;
	public static Block blaziumBlock;
	public static Block celestiumBlock;
	public static Block mekyumBlock;
	public static Block koriteBlock;
	public static Block storonBlock;
	public static Block flairiumBlock;
	public static Block desBlock;
	public static Block enderilliumBlock;
	public static Block gorbiteBlock;
	public static Block orbaditeBlock;
	public static Block luniteBlock;

	public static Block witherwoodLog;
	public static Block witherwoodLeaves;
	public static Block sizzlerWoodLeaves;
	public static Block sizzlerWoodLog;
	public static BlockNetherFlower deathGrass;
	public static BlockNetherFlower hellBell;
	public static Block hellshroom;
	public static Block heatSoil;

	public static Block igniterOn;
	public static Block igniter;
	public static Block greenGemBlock;
	public static Block purpleGemBlock;
	public static Block blueGemBlock;
	public static Block yellowGemBlock;

	@SuppressWarnings("")
	/*public static Block sizzleberryBush = new BlockModBush("sizzleberryBush", "Sizzleberry Bush", JourneyItems.sizzleberry, true);
	public static Block bradberryBush = new BlockModBush("bradberryBush", "Bradberry Bush", JourneyItems.bradberry, false);
	public static Block tangleberryBush = new BlockModBush("tangleberryBush", "Tangleberry Bush", JourneyItems.tangleberry, false);
	public static Block juiceberryBush = new BlockModBush("juiceberryBush", "Juiceberry Bush", JourneyItems.juiceberry, false);
	public static Block bogberryBush = new BlockModBush("bogberryBush", "Bogberry Bush", JourneyItems.bogberry, false);*/

	public static Block nethicanSludge;

	public static Block eucaGrass;
	public static Block eucaStone;
	public static Block eucaGoldLog;
	public static Block goldEucaPlank;
	public static Block eucaGoldStairs;
	public static Block eucaGoldLeaves;
	public static Block eucaSilverLeaves;
	public static Block eucaSapling;
	public static Block eucaBricks;
	public static Block eucaTile;

	public static BlockMod depthsDirt;
	public static Block depthsGrass;
	public static Block depthsStone;
	public static Block depthsLog;
	public static Block depthsPlank;
	public static Block depthsStairs;
	public static Block depthsLeaves;
	public static Block darkBrick;
	public static Block darkFloor;
	public static Block darkShingle;

	public static Block iceLeaves;
	public static BlockMod frozenDirt;
	public static BlockMod frozenStone;
	public static Block frozenGrass;
	public static Block frozenLeaves;
	public static Block frozenBark;
	public static Block frozenPlanks;

	public static BlockMod withanDirt;
	public static BlockMod withanRock;
	public static Block withanGrass;
	public static Block withanLeaves;
	public static Block withanBark;
	public static Block withanRockReinforced;
	public static Block withanLamp;
	public static Block withanLight; 

	public static Block goldenGrass;
	public static BlockMod goldenStone;

	public static BlockMod corbaStone;
	public static Block corbaGrass;
	public static Block corbaLog;
	public static Block corbaLeaves;
	public static Block corbaPillar;
	public static Block corbaPlank;
	public static Block corbaLamp;
	public static Block corbaSentryBrick;

	public static Block boilingLamp;
	public static Block blazierBricks; 
	public static BlockMod ashBlock;
	public static Block hotBlock;
	public static Block brisonStone;
	public static Block darkBrisonBrick;
	public static Block redBrisonBrick;
	public static Block smallBrisonBrick;
	public static Block boilingBars;
	public static Block burningLeaves;
	public static Block boilingLog; 

	public static BlockMod cloudiaDirt;
	public static Block cloudiaGrass;
	public static Block cloudiaLog; 
	public static Block cloudiaRock; 
	public static Block cloudiaBrick; 
	public static Block cloudiaWall;  
	public static Block cloudiaTile;
	public static Block cloudiaLeaves;
	public static Block cloudiaLamp;
	//public static Block cloudiaPost = new BlockCloudiaPost("cloudiaPost");
	public static Block pinkCloudiaCloud;
	public static Block blueCloudiaCloud;
	public static Block lightBlueCloudiaCloud;
	public static BlockModFlower cloudiaTallGrass;
	public static BlockModFlower cloudiaFlower;

	public static Block swampLamp;

	public static Block frozenChest;
	public static Block netherChest;
	public static Block boilingChest;
	public static Block eucaChest;
	public static Block depthsChest;
	public static Block corbaChest;
	public static Block cloudiaChest;
	public static Block terraniaChest;

	public static Block nethicDungeonBricks;
	public static Block boilChain;
	public static Block boilGate;
	public static Block boilLock;

	public static Block darklyGate;
	public static Block darklyLock;

	public static Block corbaPost;
	public static Block sizzlingPost;

	public static Block senterianBricks;
	public static Block senterianRock;
	public static Block senterianCarvedRock;
	public static Block senterianFloor;
	public static Block senterianBars;
	public static Block senterianLightLamp;
	public static Block senterianMellowLamp;
	public static Block senterianGuardianLamp;
	public static Block senterianPost;

	public static Block trophy;
	public static Block trophySoul;
	public static Block trophyBlaze;
	public static Block trophyEudor;
	public static Block trophyCor;
	public static Block trophyScale;
	public static Block trophyRoc;
	public static Block trophySentry;
	public static Block trophyLogger;
	public static Block trophyTerra;
	public static Block trophyStalk;
	public static Block trophyNetherBeast;
	public static Block trophyWitherBeast;

	/*public static BlockMod wastelandsStone = new BlockWastelandsStone("wastelandsStone");
	public static Block wastelandsGrass = new BlockModGrass((BlockMod)null, "wastelandsGrass", 2.0F);
	public static Block wastelandsLog = new BlockModLog("wastelandsLog");
	public static Block wastelandsBranches = new BlockModLeaves("wastelandsBranches", 2.0F).setStepSound(EnumMaterialTypes.WOOD.getSound());*/

	public static BlockWitherPortal witherPortal;
	public static Block witherPortalFrame;

	public static BlockEucaPortal eucaPortal;
	public static Block eucaPortalFrame;

	public static BlockGoldenPortal goldenPortal;
	public static Block goldenPortalFrame;

	public static BlockDepthsPortal depthsPortal;
	public static Block depthsPortalFrame;

	public static BlockBoilPortal boilPortal;
	public static Block boilPortalFrame;

	public static BlockFrozenPortal frozenPortal;
	public static Block frozenPortalFrame;

	public static BlockCorbaPortal corbaPortal;
	public static Block corbaPortalFrame;

	public static BlockTerraniaPortal terraniaPortal;
	public static Block terraniaPortalFrame;

	public static BlockSenterianPortal senterianPortal;
	public static Block senterianPortalFrame;
	//public static BlockWastelandsPortal wastelandsPortal = (BlockWastelandsPortal)new BlockWastelandsPortal("wastelandsPortal");
	//public static Block wastelandsPortalFrame = new BlockMod("wastelandsPortalFrame");
	public static BlockCloudiaPortal cloudiaPortal;
	public static Block cloudiaPortalFrame;

	public static Block depthsLights;

	public static Block fire;

	public static Block mossyEssenceStone;

	public static Block dungeonBrick;
	public static Block dungeonCrackedBrick;
	public static Block dungeonChisledBrick;
	public static Block dungeonBrickCarved;
	public static Block dungeonLamp;

	public static Block dungeonBrickStairs;
	public static Block dungeonCrackedBrickStairs;
	public static Block dungeonChisledBrickStairs;
	public static Block dungeonBrickCarvedStairs;
	public static Block dungeonLampStairs;

	public static Block dungeonBrickFence;
	public static Block dungeonCrackedBrickFence;
	public static Block dungeonChisledBrickFence;
	public static Block dungeonBrickCarvedFence;
	public static Block cloudiaPost;
	public static Block terranianPost;
	public static Block dungeonLampFence;

	public static Block terraniaLeaves;
	public static Block terraniaVine;
	public static BlockMod terranianDirt;
	public static Block terranianLog;
	public static Block terranianGrass;
	public static Block terranianStone;
	public static Block terranianBars;
	public static Block terranianDarkPanels;
	public static Block terranianPanels;
	public static BlockModFlower terranianTallgrass;
	public static Block terragrow;
	public static Block terraniaLamp;

	public static Block hotBrick;
	public static Block hotBrick_fence;
	public static Block hotBrick_stairs;

	public static Block hotGlass;
	public static Block smoothGlass;
	//public static Block hotGlassPane = new BlockJoinedPane("hotGlass");
	//public static Block smoothGlassPane = new BlockJoinedPane("smoothGlass");

	//public static Block witheringBeastStatue = new BlockStatue("witheringBeastStatue", new ModelWitheringBeastStatue(), EnumSounds.WITHER);
	//public static Block netherBeastStatue = new BlockStatue("netherBeastStatue", new ModelNetherBeastStatue(), EnumSounds.NETHER_BEAST);
	//public static Block calciaStatue = new BlockStatue("calciaStatue", new ModelCalciaStatue(), EnumSounds.CALCIA);
	//public static Block eudorStatue = new BlockStatue("eudorStatue", new ModelEudorStatue(), EnumSounds.CALCIA);
	//public static Block enderChampionStatue = new BlockStatue("enderChampionStatue", new ModelEnderChampionStatue());
	//public static Block wraithStatue = new BlockStatue("wraithStatue", new ModelWraithStatue());

	public static Block darkbloomTop;
	public static Block darkbloomBottom;

	public static Block redGlowshroomTop;
	public static Block redGlowshroomBottom;

	public static Block hellThornTop;
	public static Block hellThornBottom;
	public static Block hellThornRoot;
	public static Block nethicGrass;

	public static Block greenGlowshroomTop;
	public static Block greenGlowshroomBottom;
	public static Block blueGlowshroomTop;
	public static Block blueGlowshroomBottom;

	public static Block goldenStalksTop;
	public static Block goldenStalksBottom;
	public static BlockModFlower goldenBulb;
	public static BlockModFlower goldenBloom;
	public static BlockModFlower goldenStalks;

	public static Block workshopCarpet;
	public static Block frozenGlass;
	//public static Block frozenPane = new BlockJoinedPane("frozenGlass");
	public static Block frozenLamp;
	public static Block workshopStone;
	public static Block workshopStoneStair;
	public static Block workshopStoneFence;
	public static BlockModDoor frozenDoor;
	public static Block candyCane;
	public static Block frozenBrick;
	public static Block icicle;
	public static BlockModFlower frostberryThorn;
	public static BlockModFlower iceBud;
	public static BlockModFlower frozenBlooms;
	public static BlockModFlower permaFlower;
	public static BlockModFlower shiverFlower;
	public static BlockModFlower iceBush;
	public static BlockMod iceLog;
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

	public static Block grindstone;
	public static Block blueGems;
	public static Block redGems;

	public static Block blackBlock;
	public static Block whiteBlock;

	public static Block eucaPumpkin;
	public static Block cloud;

	public static Block corbaLadder;
	public static Block largeNetherBrick;
	public static Block compactNetherBrick;
	public static Block nethicLamp;

	public static Block knowledgeTable;
	public static Block summoningTable;
	public static Block trophyTable;
	public static Block bossAltar;

	public static Block observerSpawner;
	public static Block screamerSpawner;
	public static Block goldbotSpawner;
	public static Block silverbotSpawner;
	public static Block hellbotSpawner;
	public static Block purplianSpawner;
	public static Block starlightGolemSpawner;
	public static Block starlightTransporterSpawner;
	public static Block sentarAlter;
	public static Block sorcererSpawner;
	public static Block frostbiterSpawner;
	public static Block overseerSpawner;
	public static Block overseerElderSpawner;

	public static Block elderBlock;

	public static Block netherFurnace;
	public static Block netherFurnaceActive;

	/**
	public static Block boilingFurnace = new BlockNetherFurnace("boilingFurnace", "Boiling Furnace", false);
	public static Block boilingFurnaceActive = new BlockNetherFurnace("boilingFurnaceActive", "Boiling Furnace", true).setLightLevel(1).setCreativeTab(null);
	 */

	public static void init() {
		cobaltOre = new BlockModOre("cobaltOre", "cobalt Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
		rubyOre = new BlockModOre("rubyOre", "Ruby Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
		adamantineOre = new BlockModOre("adamantineOre", "Adamantine Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
		mythrilOre = new BlockModOre("amethystOre", "Amethyst Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
		verditeOre = new BlockModOre("verditeOre", "Verdite Ore").setHarvestLevel(EnumToolType.STONE_PICK);
		sapphireOre = new BlockModOre("sapphireOre", "Sapphire Ore").setHarvestLevel(EnumToolType.IRON_PICK);
		lavaRock = new BlockMod("lavaRock", "Lava Rock").setHarvestLevel(EnumToolType.DIAMOND_PICK);
		shadiumOre = new BlockModOre("shadiumOre", "Shadium Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
		luniumOre = new BlockModOre("luniumOre", "Lunium Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
		hellstoneOre = new BlockModOre("hellstoneOre", "Bloodcrust Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
		ashualOre = new BlockModOre("ashualOre", "Ashual Ore");
		blaziumOre = new BlockModOre("blaziumOre", "Blazium Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
		celestiumOre = new BlockModOre("celestiumOre", "Celestium Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
		mekyumOre = new BlockModOre("mekyumOre", "Mekyum Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
		koriteOre = new BlockModOre("koriteOre", "Korite Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
		storonOre = new BlockModOre("storonOre", "Storon Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
		flairiumOre = new BlockModOre("flairiumOre", "Flairium Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK).setLightLevel(0.5F);
		desOre = new BlockModOre("desOre", "Des Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK).setLightLevel(0.5F);
		enderilliumOre = new BlockModOre("enderilliumOre", "Enderillium Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
		gorbiteOre = new BlockModOre("gorbiteOre", "Gorbite Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
		orbaditeOre = new BlockModOre("orbaditeOre", "Orbadite Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
		luniteOre = new BlockModOre("luniteOre", "Lunite Ore").setHarvestLevel(EnumToolType.DIAMOND_PICK);
		firestoneOre = new BlockModOre("firestoneOre", "Firestone Ores").setHarvestLevel(EnumToolType.DIAMOND_PICK);

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
		enderilliumBlock = new BlockModOre("enderilliumBlock", "Enderillium Block").setHarvestLevel(EnumToolType.STONE_PICK);
		gorbiteBlock = new BlockModOre("gorbiteBlock", "Gorbite Block").setHarvestLevel(EnumToolType.STONE_PICK);
		orbaditeBlock = new BlockModOre("orbaditeBlock", "Orbadite Block").setHarvestLevel(EnumToolType.STONE_PICK);
		luniteBlock = new BlockModOre("luniteBlock", "Lunite Block").setHarvestLevel(EnumToolType.STONE_PICK);

		witherwoodLog = new BlockModLog("witherwoodLog", "Witherwood Log");
		witherwoodLeaves = new BlockModLeaves("witherwoodLeaves", "Witherwood Leaves", 1.0F);
		sizzlerWoodLeaves = new BlockModLeaves("sizzlerWoodLeaves", "Deadblood Leaves", 2.0F);
		sizzlerWoodLog = new BlockModLog("sizzlerWoodLog", "Deadblood Bark");
		deathGrass = new BlockNetherFlower("deathGrass", "Death Grass");
		hellBell = new BlockNetherFlower("hellBell", "Hell Bell");
		hellshroom = new BlockNetherPlant("hellshroom", "Hellshroom");
		heatSoil = new BlockMod(EnumMaterialTypes.DIRT, "heatSoil", "Nethic Soil", 2.0F);

		igniterOn = new BlockIgniter("igniterOn", "Redstone Igniter");
		igniter = new BlockIgniter("igniter", "Redstone Igniter");
		greenGemBlock = new BlockMod("greenGemBlock", "Green Gem Block");
		purpleGemBlock = new BlockMod("purpleGemBlock", "Purple Gem Block");
		blueGemBlock = new BlockMod("blueGemBlock", "Blue Gem Block");
		yellowGemBlock = new BlockMod("yellowGemBlock", "Yellow Gem Block");

		/* sizzleberryBush = new BlockModBush("sizzleberryBush", "Sizzleberry Bush", JourneyItems.sizzleberry, true);
	 	bradberryBush = new BlockModBush("bradberryBush", "Bradberry Bush", JourneyItems.bradberry, false);
	 	tangleberryBush = new BlockModBush("tangleberryBush", "Tangleberry Bush", JourneyItems.tangleberry, false);
	 	juiceberryBush = new BlockModBush("juiceberryBush", "Juiceberry Bush", JourneyItems.juiceberry, false);
	 	bogberryBush = new BlockModBush("bogberryBush", "Bogberry Bush", JourneyItems.bogberry, false);*/

		nethicanSludge = new BlockMod(EnumMaterialTypes.SLIME, "nethicanSludge", "Nethican Sludge", 1.0F);

		eucaGrass = new BlockModGrass(null, "eucaGrass", "Euca Grass", 2.0F);
		eucaStone = new BlockMod("eucaStone", "Euca Stone", 2.0F);
		eucaGoldLog = new BlockModLog("eucaGoldLog", "Golden Euca Log");
		goldEucaPlank = new BlockMod(EnumMaterialTypes.WOOD, "goldEucaPlank", "Golden Euca Plank", 1.0F);
		eucaGoldStairs = new BlockModStairs(eucaGoldLog, "eucaGoldStairs", "Golden Euca Stairs");
		eucaGoldLeaves = new BlockModLeaves("eucaGoldLeaves", "Golden Euca Leaves", 1.0F);
		eucaSilverLeaves = new BlockModLeaves("eucaSilverLeaves", "Silver Euca Leaves", 1.0F);
		eucaSapling = new BlockEucaSapling("eucaSapling", "Euca Sapling");
		eucaBricks = new BlockMod("eucaBricks", "Euca Bricks", 2.0F);
		eucaTile = new BlockMod("eucaTile", "Euca Tile", 2.0F);

		depthsDirt = new BlockMod(EnumMaterialTypes.DIRT, "depthsDirt", "Depths Dirt", 2.0F);
		depthsGrass = new BlockModGrass(depthsDirt, "depthsGrass", "Depths Grass", 2.0F);
		depthsStone = new BlockMod("depthsStone", "Depths Stone", 2.0F);
		depthsLog = new BlockModLog("depthsLog", "Depths Log");
		depthsPlank = new BlockMod(EnumMaterialTypes.WOOD, "depthsPlank", "Depths Plank", 1.0F);
		depthsStairs = new BlockModStairs(depthsPlank, "depthsStairs", "Depths Stairs");
		depthsLeaves = new BlockDepthsLeaves("depthsLeaves", "Depths Leaves", 1.0F);
		darkBrick = new BlockMod("darkBrick", "Dark Brick", 2.0F).setBlockUnbreakable().setResistance(10000000.0F);
		darkFloor = new BlockMod("darkFloor", "Dark Floor", 2.0F).setBlockUnbreakable().setResistance(10000000.0F);
		darkShingle = new BlockMod("darkShingle", "Dark Shingle", 2.0F).setBlockUnbreakable().setResistance(10000000.0F);

		iceLeaves = new BlockDepthsLeaves("iceLeaves", "Ice Leaves", 1.0F);
		frozenDirt = new BlockMod(EnumMaterialTypes.DIRT, "frozenDirt", "Frozen Dirt", 2.0F);
		frozenStone = new BlockMod(EnumMaterialTypes.STONE, "frozenStone", "Frozen Stone", 5.0F);
		frozenGrass = new BlockModGrass(frozenDirt, "frozenGrass", "Frozen Grass", 2.0F);
		frozenLeaves = new BlockModLeaves("frozenLeaves", "Frozen Leaves", 0.5F).setFrozenPlant().setLightLevel(1);
		frozenBark = new BlockModLog("frozenBark", "Frozen Bark");
		frozenPlanks = new BlockMod(EnumMaterialTypes.WOOD, "frozenPlanks", "Frozen Planks", 0.5F);

		withanDirt = new BlockMod(EnumMaterialTypes.DIRT, "withanDirt", "Withan Dirt", 2.0F);
		withanRock = new BlockMod(EnumMaterialTypes.STONE, "withanRock", "Withan Rock", 5.0F);
		withanGrass = new BlockModGrass(withanDirt, "withanGrass", "Withan Grass", 2.0F);
		withanLeaves = new BlockModLeaves("withanLeaves", "Withering Leaves", 0.5F);
		withanBark = new BlockModLog("withanBark", "Withering Bark");
		withanRockReinforced = new BlockMod("reinforcedWithan", "Withan Rock").setResistance(10000000000F).setBlockUnbreakable();
		withanLamp = new BlockMod(EnumMaterialTypes.GLASS, "withanLamp", "Withan Lamp", 0.1F).setLightLevel(1.0F).setCreativeTab(JourneyTabs.decoration);
		withanLight = new BlockMod(EnumMaterialTypes.GLASS, "withanLight", "Withan Light", 0.1F).setLightLevel(1.0F).setCreativeTab(JourneyTabs.decoration); 

		goldenGrass = new BlockModGrass((BlockMod)null, "goldenGrass", "Golden Grass", 0.5F);
		goldenStone = new BlockMod("goldenStone", "Golden Stone");

		corbaStone = new BlockMod("corbaStone", "Corba Stone");
		corbaGrass = new BlockModGrass((BlockMod)null, "corbaGrass", "Corba Grass", 0.5F);
		corbaLog = new BlockModLog("corbaLog", "Corba Log");
		corbaLeaves = new BlockModLeaves("corbaLeaves", "Corba Leaves", 0.2F);
		corbaPillar = new BlockMod("corbaPillar", "Corba Pillar");
		corbaPlank = new BlockMod(EnumMaterialTypes.WOOD, "corbaPlank", "Corba Plank", 0.5F);
		corbaLamp = new BlockMod(EnumMaterialTypes.GLASS, "corbaLamp", "Corba Lamp", 0.1F).setLightLevel(1.0F).setCreativeTab(JourneyTabs.decoration);
		corbaSentryBrick = new BlockMod("corbaSentryBrick", "Corba Sentry Brick");

		boilingLamp = new BlockMod(EnumMaterialTypes.GLASS, "boilingLamp", "Boiling Lamp", 0.1F).setLightLevel(1.0F).setCreativeTab(JourneyTabs.decoration);
		blazierBricks = new BlockMod("blazierBricks", "Blazier Bricks"); 
		ashBlock = new BlockMod("ashBlock", "Ash");
		hotBlock = new BlockModGrass(ashBlock, "hotGround", "Hot Ground", 2.0F);
		brisonStone = new BlockMod("brisonStone", "Brison Stone", 2.0F);
		darkBrisonBrick = new BlockMod("darkBrisonBrick", "Dark Brison Brick", 2.0F);
		redBrisonBrick = new BlockMod("redBrisonBrick", "Red Brison Brick", 2.0F);
		smallBrisonBrick = new BlockMod("smallBrisonBrick", "Small Brison Brick", 2.0F);
		boilingBars = new BlockModBars("boilingBars", "Brison Bars", 5.0F);
		burningLeaves = new BlockModLeaves("burningLeaves", "Burning Leaves", 0.5F).setBurningPlant();
		boilingLog = new BlockModLog("boilingLog", "Boiling Log"); 

		cloudiaDirt = new BlockMod(EnumMaterialTypes.DIRT, "cloudiaDirt", "Cloudia Dirt", 2.0F);
		cloudiaGrass = new BlockModGrass(cloudiaDirt, "cloudiaGrass", "Cloudia Grass", 2.0F);
		cloudiaLog = new BlockModLog("cloudiaLog", "Cloudia Log"); 
		cloudiaRock = new BlockMod("cloudiaRock", "Cloudia Rock"); 
		cloudiaBrick = new BlockMod("cloudiaBrick", "Cloudia Brick").setLightLevel(0.5F); 
		cloudiaWall = new BlockMod("cloudiaWall", "Cloudia Wall").setLightLevel(0.5F);  
		cloudiaTile = new BlockMod("cloudiaTile", "Cloudia Tile").setLightLevel(0.5F); 
		cloudiaLeaves = new BlockModLeaves("cloudiaLeaves", "Cloudia Leaves", 0.5F).setLightLevel(2);
		cloudiaLamp = new BlockMod(EnumMaterialTypes.GLASS, "cloudiaLamp", "Cloudia Lamp", 0.1F).setLightLevel(1.0F).setCreativeTab(JourneyTabs.decoration);
		// cloudiaPost = new BlockCloudiaPost("cloudiaPost");
		pinkCloudiaCloud = new BlockCloud("pinkCloudiaCloud", "Pink Cloudia Cloud");
		blueCloudiaCloud = new BlockCloud("blueCloudiaCloud", "Blue Cloudia Cloud");
		lightBlueCloudiaCloud = new BlockCloud("lightBlueCloudiaCloud", "Light Blue Cloudia Cloud");
		cloudiaTallGrass = new BlockModFlower("cloudiaTallGrass", "Cloudia Tall Grass");
		cloudiaFlower = new BlockModFlower("cloudiaFlower", "Cloudia Flower");

		swampLamp = new BlockSwampLamp("swampLamp", "Swamp Fly Bottle", 0).setLightLevel(1);

		frozenChest = new BlockJourneyChest("frozenChest", "Frozen Chest");
		netherChest = new BlockJourneyChest("netherChest", "Nether Chest");
		boilingChest = new BlockJourneyChest("boilingChest", "Boiling Chest");
		eucaChest = new BlockJourneyChest("eucaChest", "Euca Chest");
		depthsChest = new BlockJourneyChest("depthsChest", "Depths Chest");
		corbaChest = new BlockJourneyChest("corbaChest", "Corba Chest");
		cloudiaChest = new BlockJourneyChest("cloudiaChest", "Cloudia Chest");
		terraniaChest = new BlockJourneyChest("terraniaChest", "Terrania Chest");

		nethicDungeonBricks = new BlockMod("nethicDungeonBricks", "Nethic Dungeon Bricks");
		boilChain = new BlockModBars("boilChain", "Boil Chain", 5.0F).setBlockUnbreakable().setResistance(10000000.0F);
		boilGate = new BlockModBars("boilGate", "Boil Gate", 5.0F).setBlockUnbreakable().setResistance(10000000.0F);
		boilLock = new BlockLock("boilLock", "Boil Lock", JourneyItems.boilKey).setBlockUnbreakable().setResistance(10000000.0F);

		darklyGate = new BlockModBars("darklyGate", "Darkly Gate", 5.0F).setBlockUnbreakable().setResistance(10000000.0F);
		darklyLock = new BlockLock("darklyLock", "Darkly Lock", JourneyItems.darkKey).setBlockUnbreakable().setResistance(10000000.0F);

		corbaPost = new BlockModFence(pinkCloudiaCloud, "corbaPost", "Corba Post");
		sizzlingPost = new BlockModFence(pinkCloudiaCloud, "sizzlingPost", "Sizzling Post");

		senterianBricks = new BlockMod("senterianBricks", "Senterian Bricks").setBlockUnbreakable().setResistance(10000000.0F);
		senterianRock = new BlockMod("senterianRock", "Senterian Rock").setBlockUnbreakable().setResistance(10000000.0F);
		senterianCarvedRock = new BlockMod("senterianCarvedRock", "Senterian Carved Rock").setBlockUnbreakable().setResistance(10000000.0F);
		senterianFloor = new BlockMod("senterianFloor", "Senterian Floor").setBlockUnbreakable().setResistance(10000000.0F);
		senterianBars = new BlockModBars("senterianBars", "Senterian Bars", 5.0F).setBlockUnbreakable().setResistance(10000000.0F);
		senterianLightLamp = new BlockMod(EnumMaterialTypes.GLASS, "senterianLightLamp", "Senterian Light Lamp", 0.1F).setLightLevel(0.5F).setCreativeTab(JourneyTabs.decoration).setBlockUnbreakable().setResistance(10000000.0F);
		senterianMellowLamp = new BlockMod(EnumMaterialTypes.GLASS, "senterianMellowLamp", "Senterian Mellow Lamp", 0.1F).setLightLevel(0.5F).setCreativeTab(JourneyTabs.decoration).setBlockUnbreakable().setResistance(10000000.0F);
		senterianGuardianLamp = new BlockMod(EnumMaterialTypes.GLASS, "senterianGuardianLamp", "Senterian Guardian Lamp", 0.1F).setLightLevel(0.5F).setCreativeTab(JourneyTabs.decoration).setBlockUnbreakable().setResistance(10000000.0F);
		senterianPost = new BlockModFence(senterianBricks, "senterianPost", "Senterian Post").setBlockUnbreakable().setResistance(10000000.0F);

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

		/*Mod wastelandsStone = new BlockWastelandsStone("wastelandsStone");
	 wastelandsGrass = new BlockModGrass((BlockMod)null, "wastelandsGrass", 2.0F);
	 wastelandsLog = new BlockModLog("wastelandsLog");
	 wastelandsBranches = new BlockModLeaves("wastelandsBranches", 2.0F).setStepSound(EnumMaterialTypes.WOOD.getSound());*/

		witherPortal = new BlockWitherPortal("witherPortal", "Withanian Portal");
		witherPortalFrame = new BlockWitherFrame("witherPortalFrame", "Withanian Portal Frame");

		eucaPortal = new BlockEucaPortal("eucaPortal");
		eucaPortalFrame = new BlockMod("eucaPortalFrame", "Euca Portal Frame", 3.0F);

		goldenPortal = new BlockGoldenPortal("goldenPortal");
		goldenPortalFrame = new BlockMod("goldenPortalFrame", "Golden Portal Frame", 3.0F);

		depthsPortal = new BlockDepthsPortal("depthsPortal");
		depthsPortalFrame = new BlockMod("depthsPortalFrame", "Depths Portal Frame", 3.0F);

		boilPortal = new BlockBoilPortal("boilingPortal");
		boilPortalFrame = new BlockMod("boilingPortalFrame", "Boiling Portal Frame", 3.0F);

		frozenPortal = new BlockFrozenPortal("frozenPortal");
		frozenPortalFrame = new BlockMod("frozenPortalFrame", "Frozen Portal Frame", 3.0F);

		corbaPortal = new BlockCorbaPortal("corbaPortal", "Corba Portal");
		corbaPortalFrame = new BlockCorbaPortalFrame("corbaPortalFrame", "Corba Portal Frame");

		terraniaPortal = new BlockTerraniaPortal("terraniaPortal");
		terraniaPortalFrame = new BlockMod("terraniaPortalFrame", "Terrania Portal Frame", 3.0F);

		senterianPortal = (BlockSenterianPortal)new BlockSenterianPortal("senterianPortal", "senterianPortal").setCreativeTab(null);
		senterianPortalFrame = new BlockSenterianPortalFrame("senterianPortalFrame", "Senterian Portal Frame").setCreativeTab(null);
		//WastelandsPortal wastelandsPortal = (BlockWastelandsPortal)new BlockWastelandsPortal("wastelandsPortal");
		// wastelandsPortalFrame = new BlockMod("wastelandsPortalFrame");
		cloudiaPortal = new BlockCloudiaPortal("cloudiaPortal");
		cloudiaPortalFrame = new BlockMod("cloudiaPortalFrame", "Cloudia Portal Frame", 3.0F);

		depthsLights = new BlockMod(EnumMaterialTypes.GLASS, "depthsLight", "Depths Light", 0.5F).setLightLevel(1.0F);

		fire = new BlockModFire("fire", "Essential Fire");

		mossyEssenceStone = new BlockMod("mossyEssenceStone", "Mossy Essence Stone", 2.0F);

		dungeonBrick = new BlockDungeonBlock("dungeonBrick", "Dungeon Brick");
		dungeonCrackedBrick = new BlockDungeonBlock("dungeonCrackedBrick", "Dungeon Cracked Brick");
		dungeonChisledBrick = new BlockDungeonBlock("dungeonChiseledBrick", "Dungeon Chiseled Brick");
		dungeonBrickCarved = new BlockDungeonBlock("dungeonBrickCarved", "Dungeon Carved Brick");
		dungeonLamp = new BlockDungeonBlock("dungeonLamp", "Dungeon Lamp", true).setLightLevel(0.5F).setCreativeTab(JourneyTabs.decoration);

		dungeonBrickStairs = new BlockModStairs(dungeonBrick, "dungeonBrickStairs", "Dungeon Brick Stairs");
		dungeonCrackedBrickStairs = new BlockModStairs(dungeonCrackedBrick, "dungeonCrackedBrickStairs", "Cracked Dungeon Brick Stairs");
		dungeonChisledBrickStairs = new BlockModStairs(dungeonChisledBrick, "dungeonChiseledBrickStairs", "Chiseled Dungeon Brick Stairs");
		dungeonBrickCarvedStairs = new BlockModStairs(dungeonBrickCarved, "dungeonBrickCarvedStairs", "Carved Dungeon Brick Stairs");
		dungeonLampStairs = new BlockModStairs(dungeonLamp, "dungeonLampStairs", "Dungeon Lamp Stairs", true);

		dungeonBrickFence = new BlockModFence(dungeonBrick, "dungeonBrickFence", "Dungeon Brick Fence");
		dungeonCrackedBrickFence = new BlockModFence(dungeonCrackedBrick, "dungeonCrackedBrickFence", "Cracked Dungeon Brick Fence");
		dungeonChisledBrickFence = new BlockModFence(dungeonChisledBrick, "dungeonChiseledBrickFence", "Chiseled Dungeon Brick Fence");
		dungeonBrickCarvedFence = new BlockModFence(dungeonBrickCarved, "dungeonBrickCarvedFence", "Carved Dungeon Brick Fence");
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
		terranianTallgrass = new BlockModFlower("terranianTallgrass", "Terranian Tallgrass");
		terragrow = new BlockTerraFlower("terragrow", "Terranian Flower");
		terraniaLamp = new BlockMod(EnumMaterialTypes.GLASS, "terraniaLamp", "Terrania Lamp", 0.1F).setLightLevel(1.0F).setCreativeTab(JourneyTabs.decoration);

		hotBrick = new BlockMod("hotBrick", "Hot Brick", 2.0F);
		hotBrick_fence = new BlockModFence(hotBrick, "hotBrickFence", "Hot Brick Fence");
		hotBrick_stairs = new BlockModStairs(hotBrick, "hotBrickStairs", "Hot Brick Stairs");

		hotGlass = new BlockJoinedGlass("hotGlass", "Hot Glass");
		smoothGlass = new BlockJoinedGlass("smoothGlass", "Smooth Glass");
		// hotGlassPane = new BlockJoinedPane("hotGlass");
		// smoothGlassPane = new BlockJoinedPane("smoothGlass");

		// witheringBeastStatue = new BlockStatue("witheringBeastStatue", new ModelWitheringBeastStatue(), EnumSounds.WITHER);
		// netherBeastStatue = new BlockStatue("netherBeastStatue", new ModelNetherBeastStatue(), EnumSounds.NETHER_BEAST);
		// calciaStatue = new BlockStatue("calciaStatue", new ModelCalciaStatue(), EnumSounds.CALCIA);
		// eudorStatue = new BlockStatue("eudorStatue", new ModelEudorStatue(), EnumSounds.CALCIA);
		// enderChampionStatue = new BlockStatue("enderChampionStatue", new ModelEnderChampionStatue());
		// wraithStatue = new BlockStatue("wraithStatue", new ModelWraithStatue());

		darkbloomTop = new BlockDarkbloom("darkbloomTop", "Darkbloom", true);
		darkbloomBottom = new BlockDarkbloom("darkbloomBottom", "Darkbloom", false);

		redGlowshroomTop = new BlockGlowshroom("redGlowshroomTop", "Red Glowshroom", true);
		redGlowshroomBottom = new BlockGlowshroom("redGlowshroomBottom", "Red Glowshroom", false);

		hellThornTop = new BlockHellThorn("hellThornTop", "Hell Thorn", true);
		hellThornBottom = new BlockHellThorn("hellThornBottom", "Hell Thorn", false);
		hellThornRoot = new BlockRoot("hellThornRoot", "HellThornRoot");
		nethicGrass = new BlockModGrass(null, "nethicGrass", "Nethic Grass", 2.0F);

		greenGlowshroomTop = new BlockGlowshroom("greenGlowshroomTop", "Green Glowshroom", true);
		greenGlowshroomBottom = new BlockGlowshroom("greenGlowshroomBottom", "Green Glowshroom", false);
		blueGlowshroomTop = new BlockGlowshroom("blueGlowshroomTop", "Blue Glowshroom", true);
		blueGlowshroomBottom = new BlockGlowshroom("blueGlowshroomBottom", "Blue Glowshroom", false);

		goldenStalksTop = new BlockStalks("goldenStalksTop", "Golden Stalks", true).setCreativeTab(null);
		goldenStalksBottom = new BlockStalks("goldenStalksBottom", "Golden Stalks", false).setCreativeTab(null);
		goldenBulb = (BlockModFlower) new BlockModFlower("goldenBulb", "Golden Bulb").setCreativeTab(null);
		goldenBloom = (BlockModFlower) new BlockModFlower("goldenBloom", "Golden Bloom").setCreativeTab(null);
		goldenStalks = (BlockModFlower) new BlockModFlower("goldenStalks", "Golden Stalks").setCreativeTab(null);

		workshopCarpet = new BlockMod(EnumMaterialTypes.WOOL, "workshopCarpet", "Workshop Carpet", 0.5F);
		frozenGlass = new BlockJoinedGlass("frozenGlass", "Frozen Glass");
		// frozenPane = new BlockJoinedPane("frozenGlass");
		frozenLamp = new BlockMod(EnumMaterialTypes.GLASS, "frozenLamp", "Frozen Lamp", 0.5F).setLightLevel(1.0F).setCreativeTab(JourneyTabs.decoration);
		workshopStone = new BlockMod(EnumMaterialTypes.STONE, "workshopStone", "Workshop Stone", 0.5F);
		workshopStoneStair = new BlockModStairs(workshopStone, "workshopStairs", "Workshop Stairs");
		workshopStoneFence = new BlockModFence(workshopStone, "workshopStoneFence", "Workshop Fence");
		frozenDoor = new BlockModDoor(EnumMaterialTypes.STONE, 5.0F, "frozenDoorBlock", "Workshop Fence");
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

		eucaTallGrass = new BlockModFlower("eucaTallGrass", "Euca Tall Grass");
		eucaTallFlowers = new BlockModFlower("eucaTallFlowers", "Euca Tall Flowers");
		eucaBlueFlower = new BlockModFlower("eucaBlueFlower", "Euca Blue Flower");
		frozenFlower = new BlockModFlower("frozenFlower", "Frozen Flower");
		depthsFlower = new BlockModFlower("depthsFlower", "Depths Flower");
		depthsBlueFlower = new BlockModFlower("depthsBlueFlower", "Depths Blue Flower").setLightLevel(0.625F);
		corbaFlower = new BlockModFlower("corbaFlower", "Corba Flower");
		corbaTallGrass = new BlockModFlower("corbaTallGrass", "Corba Tall Grass");
		corbaSpeckledFlower = new BlockModFlower("corbaSpeckledFlower", "Speckled Flower");
		corbaLightPurpleFlower = new BlockModFlower("corbaLightPurpleFlower", "Light Purple Flower");
		corbaRedFlower = new BlockModFlower("corbaRedFlower", "Red Flower");
		corbaBlueFlower = new BlockModFlower("corbaBlueFlower", "Blue Flower");
		corbaDarkPurpleFlower = new BlockModFlower("corbaDarkPurpleFlower", "Dark Purple Flower");
		flameFlower = new BlockModFlower("flameFlower", "Flame Flower");
		infernoPlant = new BlockModFlower("infernoPlant", "Inferno Plant");
		burntGrass = new BlockModFlower("burntGrass", "Burnt Grass");

		grindstone = new BlockGrindstone("grindstone");
		blueGems = new BlockGemBlock("blueGems", "Blue Gems", false);
		redGems = new BlockGemBlock("redGems", "Red Gems", true);

		blackBlock = new BlockMod(EnumMaterialTypes.STONE, "blackBlock", "Black Block", 0.5F);
		whiteBlock = new BlockMod(EnumMaterialTypes.STONE, "whiteBlock", "White Block", 0.5F);

		eucaPumpkin = new BlockEucaPumpkin("eucaPumpkin", "Euca Pumpkin");
		cloud = new BlockCloud("cloud", "Cloud");

		corbaLadder = new BlockModLadder("corbaLadder", "Corba Ladder", 0);
		largeNetherBrick = new BlockMod("largeNetherBrick", "Large Nether Brick");
		compactNetherBrick = new BlockMod("compactNetherBrick", "Compact Nether Brick").setBlockUnbreakable().setResistance(10000000.0F);
		nethicLamp = new BlockMod(EnumMaterialTypes.GLASS, "nethicLamp", "Nethic Lamp", 0.1F).setLightLevel(1).setCreativeTab(JourneyTabs.decoration);

		knowledgeTable = new BlockKnowledgeTable("knowledgeTable", "Knowledge Table").setCreativeTab(null);
		summoningTable = new BlockSummoningTable("summoningTable", "Summoning Table");
		trophyTable = new BlockTrophyTable("trophyTable", "Trophy Table");
		bossAltar = new BlockMod("bossAltar", "Boss Altar");

		observerSpawner = new BlockModSpawner("observerSpawner", "Observer Spawner", "observer");
		screamerSpawner = new BlockModSpawner("screamerSpawner", "Screamer Spawner", "screamer");

		goldbotSpawner = new BlockModSpawner("goldbotSpawner", "Goldbot Spawner", "goldbot");
		silverbotSpawner = new BlockModSpawner("silverbotSpawner", "Silverbot Spawner", "silverbot");

		hellbotSpawner = new BlockModSpawner("hellbotSpawner", "Hellbot Spawner", "hellbot");

		purplianSpawner = new BlockModSpawner("purplianSpawner", "Purplian Spawner", "purplian");
		starlightGolemSpawner = new BlockModSpawner("starlightGolemSpawner", "Starlight Golem Spawner", "starlightGolem");
		starlightTransporterSpawner = new BlockModSpawner("starlightTransporterSpawner", "Starlight Transporter Spawner", "starlightTransporter");
		sentarAlter = new BlockMod("sentarAlter", "Sentar Alter");
		sorcererSpawner = new BlockModSpawner("sorcererSpawner", "Sorcerer Spawner", "darkSorcerer");
		frostbiterSpawner = new BlockModSpawner("frostbiterSpawner", "Frostbiter Spawner", "frozenFrostbiter");
		overseerSpawner = new BlockModSpawner("overseerSpawner", "Overseer Spawner", "overseer");
		overseerElderSpawner = new BlockModSpawner("overseerElderSpawner", "Overseer Elder Spawner", "overseerElder");

		elderBlock = new BlockChangeable("elderBlock", "Elder Block", JourneyItems.elderKey, JourneyBlocks.overseerElderSpawner);

		netherFurnace = new BlockNetherFurnace("netherFurnace", "Nethic Furnace", false);
		netherFurnaceActive = new BlockNetherFurnace("netherFurnaceActive", "Nethic Furnace", true).setLightLevel(0.7F).setCreativeTab(null);
	}


	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		for(int i = 0; i < blocks.size(); i++)
			event.getRegistry().registerAll(blocks.get(i));
	}
}