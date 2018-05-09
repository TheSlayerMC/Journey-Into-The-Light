package net.journey.proxy;

import net.journey.JourneyAchievements;
import net.journey.JourneyBlocks;
import net.journey.JourneyChestGenerator;
import net.journey.JourneyCrops;
import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.achievement.event.JourneyDungeonEvent;
import net.journey.achievement.event.JourneySapphireEvent;
import net.journey.achievement.event.JourneySapphireSwordEvent;
import net.journey.blocks.tileentity.TileEntityGrindstone;
import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.journey.blocks.tileentity.TileEntityKnowledgeTable;
import net.journey.blocks.tileentity.TileEntityNetherFurnace;
import net.journey.blocks.tileentity.TileEntitySenterianPortal;
import net.journey.blocks.tileentity.TileEntitySummoningTable;
import net.journey.blocks.tileentity.TileEntityTrophyTable;
import net.journey.client.BarTickHandler;
import net.journey.dimension.DimensionHelper;
import net.journey.dimension.WorldGenEssence;
import net.journey.enums.EnumParticlesClasses;
import net.journey.event.ArmorAbilityEvent;
import net.journey.event.PlayerEvent;
import net.journey.misc.DimensionCommand;
import net.journey.misc.EssenceCommands;
import net.journey.util.Config;
import net.journey.util.EntityRegistry;
import net.journey.util.JourneyFuelHandler;
import net.journey.util.LangRegistry;
import net.journey.util.recipes.JourneyBlockRecipes;
import net.journey.util.recipes.JourneyMaterialRecipes;
import net.journey.util.recipes.JourneyMiscRecipes;
import net.journey.util.recipes.JourneySmeltingRecipes;
import net.journey.util.recipes.JourneyWeaponRecipes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeHell;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.slayer.api.SlayerAPI;

public class CommonProxy {

	public void updateDarkEnergy(int amount) { }
	public void updateEssence(int amount) { }
	public void updatePower(int amount) { }
	public void registerClient() { }
	public void clientInit(FMLInitializationEvent event) { }
	public void clientPreInit() { }
	public void registerSounds() { }
	public void spawnParticle(EnumParticlesClasses particle, World worldObj, double x, double y, double z, boolean b) { }
	public void registerModModels() { }
	
	public void registerItem(RegistryEvent.Register<Item> event) { }
	
	public void preInit(FMLPreInitializationEvent event) {
		Config.init(event);
		//Essence.tropicalWater.setBlock(EssenceBlocks.tropicalWater);
		EntityRegistry.init();
		JourneyChestGenerator.init();
		//JourneyAchievements.init();
		JourneyMaterialRecipes.init();
		JourneyBlockRecipes.init();
		JourneyMiscRecipes.init();
		JourneySmeltingRecipes.init();
		JourneyWeaponRecipes.init();
		
		DimensionHelper.init();
		//BiomeDictionary.addTypes(new BiomeHell(), Type.NETHER);
		DimensionHelper.addSpawns();
		
		JourneyTabs.init();
		
		if(SlayerAPI.DEVMODE) LangRegistry.instance.register();
		addOreDictionary();
		SlayerAPI.registerEvent(new ArmorAbilityEvent());
		SlayerAPI.registerEvent(new PlayerEvent());
		SlayerAPI.registerEvent(new BarTickHandler());
		GameRegistry.registerTileEntity(TileEntityGrindstone.class, "grindstone");
		GameRegistry.registerTileEntity(TileEntityKnowledgeTable.class, "Knowledge Table");
		GameRegistry.registerTileEntity(TileEntitySummoningTable.class, "Summoning Table");
		GameRegistry.registerTileEntity(TileEntityTrophyTable.class, "Trophy Table");
		GameRegistry.registerTileEntity(TileEntityJourneyChest.class, "Journey Chest");
		GameRegistry.registerTileEntity(TileEntityNetherFurnace.class, "Nether Furnace");
		GameRegistry.registerTileEntity(TileEntitySenterianPortal.class, "Senterian Labyrinth Portal");
		GameRegistry.registerFuelHandler(new JourneyFuelHandler());
		//SlayerAPI.addMapGen(MapGenBoilVillage.Start.class, "Boil Village");
		//SlayerAPI.addMapGen(MapGenBoilBridge.Start.class, "Boil Bridge");
		//StructureBoilBridgePieces.registerBoilFortressPieces();
		//StructureBoilVillagePieces.registerVillagePieces();
		MinecraftForge.addGrassSeed(new ItemStack(JourneyCrops.tomatoSeeds), 5);
		FMLCommonHandler.instance().bus().register(new JourneySapphireSwordEvent());
		FMLCommonHandler.instance().bus().register(new JourneySapphireEvent());
		FMLCommonHandler.instance().bus().register(new JourneyDungeonEvent());
	}
	
	public void init(FMLInitializationEvent event) {
		GameRegistry.registerWorldGenerator(new WorldGenEssence(), 2);
		SlayerAPI.registerEvent(new PlayerEvent());
	}
	
	public void postInit(FMLPostInitializationEvent event) {
	}
	
	public void serverStarting(FMLServerStartingEvent event) {
		SlayerAPI.registerCommand(new EssenceCommands());
		SlayerAPI.registerCommand(new DimensionCommand());
	}
	
	private void addOreDictionary() {
		OreDictionary.registerOre("oreAshual", JourneyBlocks.ashualOre);
		OreDictionary.registerOre("oreCelestium", JourneyBlocks.celestiumOre);
		OreDictionary.registerOre("oreLunium", JourneyBlocks.luniumOre);
		OreDictionary.registerOre("oreShadium", JourneyBlocks.shadiumOre);
		OreDictionary.registerOre("oreFlairium", JourneyBlocks.flairiumOre);
		OreDictionary.registerOre("oreSapphire", JourneyBlocks.sapphireOre);
		OreDictionary.registerOre("ash", JourneyItems.ash);
		OreDictionary.registerOre("ingotCelestium", JourneyItems.celestiumIngot);
		OreDictionary.registerOre("ingotLunium", JourneyItems.luniumIngot);
		OreDictionary.registerOre("ingotShadium", JourneyItems.shadiumIngot);
		OreDictionary.registerOre("ingotFlairium", JourneyItems.flairiumIngot);
		OreDictionary.registerOre("gemSapphire", JourneyItems.sapphire);
		OreDictionary.registerOre("gemBlazium", JourneyItems.blazium);
	}
}