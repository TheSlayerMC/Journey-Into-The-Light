package net.journey;

import net.journey.blocks.tileentity.TileEntityHandler;
import net.journey.client.api.IHasModel;
import net.journey.init.JourneyEnchantments;
import net.journey.init.JourneySounds;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyItems;
import net.journey.util.EntityRegistry;
import net.journey.util.handler.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.registries.IForgeRegistry;
import net.slayer.api.SlayerAPI;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = SlayerAPI.MOD_ID)
public class Registries {

    public static final List<SoundEvent> SOUNDS = new ArrayList<>();

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (int i = 0; i < JourneyItems.items.size(); i++)
            event.getRegistry().registerAll(JourneyItems.items.get(i));

        LogHelper.info("Successfully Registered " + JourneyItems.items.size() + " Items");
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for (int i = 0; i < JourneyBlocks.blocks.size(); i++)
            event.getRegistry().registerAll(JourneyBlocks.blocks.get(i));

        LogHelper.info("Successfully Registered " + JourneyBlocks.blocks.size() + " Blocks");

        TileEntityHandler.register();
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Block block : JourneyBlocks.blocks)
            if (block instanceof IHasModel)
                ((IHasModel) block).registerModels(event);
        for (Item item : JourneyItems.items)
            if (item instanceof IHasModel)
                ((IHasModel) item).registerModels(event);
    }

    @SubscribeEvent
    public static void registerEnities(RegistryEvent.Register<EntityEntry> event) {
        IForgeRegistry<EntityEntry> registry = event.getRegistry();

        EntityEntry[] mobs = EntityRegistry.initMobs();
        EntityEntry[] projectiles = EntityRegistry.initProjectiles();

        registry.registerAll(mobs);
        registry.registerAll(projectiles);

        LogHelper.info("Successfully Registered " + mobs.length + " Mobs");
        LogHelper.info("Successfully Registered " + EntityRegistry.initProjectiles().length + " Projectiles");

    }

    @SubscribeEvent
    public void onSoundRegistry(Register<SoundEvent> event) {
        JourneySounds.init();

        for (SoundEvent sound : SOUNDS) {
            event.getRegistry().register(sound);
        }
        SOUNDS.clear();

        LogHelper.info("Successfully Registered " + SOUNDS.size() + " Sounds");
    }

    @SubscribeEvent
    public void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        for (int i = 0; i < JourneyEnchantments.enchantments.size(); i++)
            event.getRegistry().registerAll(JourneyEnchantments.enchantments.get(i));

        LogHelper.info("Successfully Registered " + JourneyEnchantments.enchantments.size() + " Enchantments");
    }
}