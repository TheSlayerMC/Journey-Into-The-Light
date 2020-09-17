package net.journey;

import net.journey.init.JourneyEnchantments;
import net.journey.init.Registrar;
import net.journey.init.TileEntityHandler;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = JITL.MOD_ID)
public class Registries {


	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		for (Item item : Registrar.JOURNEY_ITEMS) {
			event.getRegistry().register(item);
		}
		for (Item item : JourneyBlocks.itemBlocks) {
			event.getRegistry().register(item);
		}
		JITL.LOGGER.info("Successfully registered " + Registrar.JOURNEY_ITEMS.size() + " items");
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		for (Block block : JourneyBlocks.blocks) {
			event.getRegistry().register(block);
		}
		JITL.LOGGER.info("Successfully registered " + JourneyBlocks.blocks.size() + " blocks");

		TileEntityHandler.register();
	}

	@SubscribeEvent
	public static void registerEnchantments(RegistryEvent.Register<Enchantment> e) {
		IForgeRegistry<Enchantment> enchant = e.getRegistry();

		enchant.register(JourneyEnchantments.hotTouch);

		JITL.LOGGER.info("Successfully registered 1 Enchantments");
	}
}