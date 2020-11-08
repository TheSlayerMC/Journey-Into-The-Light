package net.jitl.util;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import ru.timeconqueror.timecore.registry.BlockPropsFactory;

public class JBlockProperties {

	public static final BlockPropsFactory STONE_PROPS = new BlockPropsFactory(() -> AbstractBlock.Properties.of
			(Material.STONE)
			.sound(SoundType.STONE)
			.harvestTool(ToolType.PICKAXE)
			.requiresCorrectToolForDrops()
			.strength(1.5F, 6.0F));
	public static final BlockPropsFactory WOOD_PROPS = new BlockPropsFactory(() -> AbstractBlock.Properties.of
			(Material.WOOD)
			.sound(SoundType.WOOD)
			.harvestTool(ToolType.AXE)
			.strength(2.0F, 3.0F));
	public static final BlockPropsFactory SAND_PROPS = new BlockPropsFactory(() -> AbstractBlock.Properties.of
			(Material.SAND)
			.sound(SoundType.SAND)
			.harvestTool(ToolType.SHOVEL)
			.strength(0.5F));
	public static final BlockPropsFactory DIRT_PROPS = new BlockPropsFactory(() -> AbstractBlock.Properties.of
			(Material.DIRT)
			.sound(SoundType.GRAVEL)
			.harvestTool(ToolType.SHOVEL)
			.strength(0.5F));
	public static final BlockPropsFactory ORE_PROPS = new BlockPropsFactory(() -> AbstractBlock.Properties.of
			(Material.STONE)
			.sound(SoundType.STONE)
			.harvestTool(ToolType.PICKAXE)
			.requiresCorrectToolForDrops()
			.strength(3.0F, 3.0F));
}
