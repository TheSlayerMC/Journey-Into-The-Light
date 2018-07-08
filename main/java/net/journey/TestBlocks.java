package net.journey;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import net.slayer.api.block.BlockMod;

public class TestBlocks {
	
	public static BlockMod test = new BlockMod("test", "Test");
	
	public static void registerBlocks(IForgeRegistry<Block> registry) {
		System.out.print("Fucking Blocks Loading");
		registry.registerAll(
				test);
		System.out.print("The Fucking Blocks Loaded");
	}
	
	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		registry.registerAll(
				test.createItemBlock());
	}
}
