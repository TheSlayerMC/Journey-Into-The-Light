package net.journey.proxy;

import net.journey.JourneyBlocks;
import net.journey.JourneyChestGenerator;
import net.journey.JourneyCrops;
import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.JourneyTabs;
import net.journey.blocks.tileentity.TileEntityGrindstone;
import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.journey.blocks.tileentity.TileEntityNetherFurnace;
import net.journey.blocks.tileentity.TileEntitySummoningTable;
import net.journey.blocks.tileentity.TileEntityTrap;
import net.journey.client.server.BarTickHandler;
import net.journey.client.server.EssenceBar;
import net.journey.client.server.EssenceStorage;
import net.journey.client.server.IEssence;
import net.journey.dimension.DimensionCommand;
import net.journey.dimension.DimensionHelper;
import net.journey.dimension.WorldGenJourney;
import net.journey.dimension.nether.WorldGeneratorNether;
import net.journey.dimension.nether.biomes.BiomeRegistry;
import net.journey.enums.EnumParticlesClasses;
import net.journey.event.ArmorAbilityEvent;
import net.journey.event.NetherEvent;
import net.journey.event.PlayerEvent;
import net.journey.misc.JourneyCommands;
import net.journey.util.Config;
import net.journey.util.EntityRegistry;
import net.journey.util.JourneyFuelHandler;
import net.journey.util.LangRegistry;
import net.journey.util.recipes.JourneyBlockRecipes;
import net.journey.util.recipes.JourneyMaterialRecipes;
import net.journey.util.recipes.JourneyMiscRecipes;
import net.journey.util.recipes.JourneySmeltingRecipes;
import net.journey.util.recipes.JourneyWeaponRecipes;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.slayer.api.SlayerAPI;

public class CommonProxy {

	public void registerClient() { }
	public void clientInit(FMLInitializationEvent event) { }
	public void clientPreInit() { }
	public void registerSounds() { }
	public void spawnParticle(EnumParticlesClasses particle, World worldObj, double x, double y, double z, boolean b) { }

	public void preInit(FMLPreInitializationEvent event) {
		Config.init(event);
		NetherEvent.init();
		JourneySounds.init();
		JourneyCrops.init();
		JourneyItems.init();
		JourneyBlocks.init();
		BiomeRegistry.init();
		EntityRegistry.init();
		JourneyChestGenerator.init();
		//JourneyAchievements.init()
		JourneyMaterialRecipes.init();
		JourneyBlockRecipes.init();
		JourneyMiscRecipes.init();
		JourneyWeaponRecipes.init();
		WorldGeneratorNether.updateGenSettings();
		
		addOreDictionary();
		SlayerAPI.registerEvent(new ArmorAbilityEvent());
		SlayerAPI.registerEvent(new PlayerEvent());
		GameRegistry.registerFuelHandler(new JourneyFuelHandler());
		MinecraftForge.addGrassSeed(new ItemStack(JourneyCrops.tomatoSeeds), 5);
		//FMLCommonHandler.instance().bus().register(new JourneyAdvancementEvent());
		DimensionHelper.init();
		DimensionHelper.addSpawns();
		CapabilityManager.INSTANCE.register(IEssence.class, new EssenceStorage(), EssenceBar.class);

		SlayerAPI.registerEvent(new BarTickHandler());
		MinecraftForge.EVENT_BUS.register(BarTickHandler.class);
		MinecraftForge.EVENT_BUS.register(NetherEvent.class);
		


		if(SlayerAPI.DEVMODE) LangRegistry.instance.register();
	}
	
	public void init(FMLInitializationEvent event) {
		JourneyTabs.init();
		GameRegistry.registerWorldGenerator(new WorldGenJourney(), 2);
		SlayerAPI.registerEvent(new PlayerEvent());
		JourneySmeltingRecipes.initSmeltingCrafting();
	}

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) { }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) { }
    
	public void registerItemRenderer(Item itemBlock, int i, String name) { }
	
	public void registerEntityRenderer(Entity entity, int i, String name) { }
	
	public void registerVariantRenderer(Item item, int meta, String name, String id) {}
	
	public void postInit(FMLPostInitializationEvent event) { }
	
	public void serverStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(new JourneyCommands());
		event.registerServerCommand(new DimensionCommand());
	}
	
	private void addOreDictionary() { }
	
}