package net.journey.proxy;

import net.journey.client.server.*;
import net.journey.command.DimensionCommand;
import net.journey.command.JourneyCommands;
import net.journey.dimension.base.DimensionHelper;
import net.journey.dimension.base.WorldGenJourney;
import net.journey.dimension.nether.JNWorldGenerator;
import net.journey.dimension.nether.biomes.BiomeRegister;
import net.journey.enums.EnumParticlesClasses;
import net.journey.event.ArmorAbilityEvent;
import net.journey.event.NetherEvent;
import net.journey.event.PlayerEvent;
import net.journey.event.VanillaFixEvent;
import net.journey.init.JourneyEnchantments;
import net.journey.init.JourneySounds;
import net.journey.init.JourneyTabs;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.common.JourneyCrops;
import net.journey.init.items.JourneyArmory;
import net.journey.init.items.JourneyConsumables;
import net.journey.init.items.JourneyItems;
import net.journey.init.items.JourneyWeapons;
import net.journey.util.Config;
import net.journey.util.JourneyFuelHandler;
import net.journey.util.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.slayer.api.SlayerAPI;

public class CommonProxy {

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
    }

    public void registerClient() {
    }

    public void clientInit(FMLInitializationEvent event) {
    }

    public void clientPreInit() {
    }

    public void clientPostInit() {
    }

    public void registerSounds() {
    }

    public void registerTEISR() {
    }

    public void spawnParticle(EnumParticlesClasses particle, World worldObj, double x, double y, double z, boolean b) {
    }

    public void spawnOreParticle(World worldObj, double x, double y, double z, float r, float g, float b) {
    }

    public void spawnParticle(EnumParticlesClasses particle, World worldObj, double x, double y, double z, double x1, double y2, double z2) {
    }

    public EntityPlayer getPlayer() {
        return null;
    }

    public void preInit(FMLPreInitializationEvent event) {
    	ObfuscationReflectionHelper.setPrivateValue((Class)RangedAttribute.class, SharedMonsterAttributes.MAX_HEALTH, Double.MAX_VALUE, 1);
        Config.init(event);
        NetherEvent.init();

        JourneySounds.init();
        JourneyItems.init();
        JourneyConsumables.init();
        JourneyBlocks.init();
        JourneyCrops.init();
        JourneyWeapons.init();
        JourneyArmory.init();
        BiomeRegister.registerBiomes();
        Config.postBiomeInit();
        JNWorldGenerator.updateGenSettings();

        //JourneyAchievements.init()

        addOreDictionary();
        SlayerAPI.registerEventListener(new NetherEvent());
        SlayerAPI.registerEventListener(new ArmorAbilityEvent());
        SlayerAPI.registerEventListener(new PlayerEvent());
        SlayerAPI.registerEventListener(new JourneyFuelHandler());
        SlayerAPI.registerEventListener(new VanillaFixEvent());
        SlayerAPI.registerEventListener(new JourneyEnchantments());
        MinecraftForge.addGrassSeed(new ItemStack(JourneyCrops.tomatoSeeds), 5);
        //FMLCommonHandler.instance().bus().register(new JourneyAdvancementEvent());
        DimensionHelper.init();
        DimensionHelper.addSpawns();
        SlayerAPI.registerEventListener(new BarTickHandler());
        SlayerAPI.registerEventListener(new RenderBar());
        CapabilityManager.INSTANCE.register(IEssence.class, new EssenceStorage(), EssenceBar.class);

        if (SlayerAPI.DEVMODE) LangRegistry.instance.register();
    }

    public void init(FMLInitializationEvent event) {
        JourneyTabs.init();
        GameRegistry.registerWorldGenerator(new WorldGenJourney(), 2);
        SlayerAPI.registerEventListener(new PlayerEvent());
    }

    public void registerEntityRenderer(Entity entity, int i, String name) {
    }

    public void registerVariantRenderer(Item item, int meta, String name, String id) {
    }

    public void postInit(FMLPostInitializationEvent event) {

    }

    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new JourneyCommands());
        event.registerServerCommand(new DimensionCommand());
    }

    private void addOreDictionary() {
    }

}