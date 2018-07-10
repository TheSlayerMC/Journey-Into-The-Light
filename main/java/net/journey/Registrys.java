package net.journey;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.slayer.api.SlayerAPI;

@EventBusSubscriber(modid=SlayerAPI.MOD_ID)
public class Registrys {

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		for(int i = 0; i < JourneyItems.items.size(); i++)
			event.getRegistry().registerAll(JourneyItems.items.get(i));
		JourneyBlocks.registerItemBlocks(event.getRegistry());
	}
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		for(int i = 0; i < JourneyBlocks.blocks.size(); i++)
			event.getRegistry().registerAll(JourneyBlocks.blocks.get(i));
	}
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		JourneyBlocks.registerBlockModels();
		JourneyItems.registerItemModels();
	}
}