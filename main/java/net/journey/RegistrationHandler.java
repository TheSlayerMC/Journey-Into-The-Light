package net.journey;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.slayer.api.SlayerAPI;

@Mod.EventBusSubscriber(modid = SlayerAPI.MOD_ID)
public class RegistrationHandler {
	
	public static final Set<Item> ITEMS = new HashSet<Item>();
	public static final Set<Block> BLOCKS = new HashSet<Block>();

	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		final ArrayList<Item> items = JourneyItems.items;
		
		for(final Item item : items) {
			registry.register(item);
			ITEMS.add(item);
		}
	}
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		final IForgeRegistry<Block> registry = event.getRegistry();
		final ArrayList<Block> blocks = JourneyBlocks.blocks;
		
		for(final Block block : blocks) {
			registry.register(block);
			BLOCKS.add(block);
		}
	}
}
