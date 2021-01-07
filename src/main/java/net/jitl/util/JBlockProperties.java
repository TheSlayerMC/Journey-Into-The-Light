package net.jitl.util;

import net.jitl.init.JSoundTypes;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import ru.timeconqueror.timecore.api.registry.util.BlockPropsFactory;

public class JBlockProperties {

	public static final BlockPropsFactory STONE_PROPS = new BlockPropsFactory(() -> AbstractBlock.Properties.of
			(Material.STONE)
			.sound(SoundType.STONE)
			.harvestTool(ToolType.PICKAXE)
			.requiresCorrectToolForDrops()
			.strength(1.5F, 6.0F));
	public static final BlockPropsFactory BASALT_PROPS = new BlockPropsFactory(() -> AbstractBlock.Properties.of
			(Material.STONE)
			.sound(SoundType.BASALT)
			.harvestTool(ToolType.PICKAXE)
			.requiresCorrectToolForDrops()
			.strength(1.5F, 6.0F));
	public static final BlockPropsFactory METAL_PROPS = new BlockPropsFactory(() -> AbstractBlock.Properties.of
			(Material.METAL)
			.sound(SoundType.METAL)
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
	public static final BlockPropsFactory LUNIUM_ORE_PROPS = new BlockPropsFactory(() -> AbstractBlock.Properties.of
			(Material.STONE)
			.sound(JSoundTypes.LUNIUM_ORE)
			.harvestTool(ToolType.PICKAXE)
			.requiresCorrectToolForDrops()
			.strength(3.0F, 3.0F));
	public static final BlockPropsFactory SHADIUM_ORE_PROPS = new BlockPropsFactory(() -> AbstractBlock.Properties.of
			(Material.STONE)
			.sound(JSoundTypes.SHADIUM_ORE)
			.harvestTool(ToolType.PICKAXE)
			.requiresCorrectToolForDrops()
			.strength(3.0F, 3.0F));
	public static final BlockPropsFactory BRICK_PROPS = new BlockPropsFactory(() -> AbstractBlock.Properties.of
			(Material.STONE)
			.sound(JSoundTypes.BRICK)
			.harvestTool(ToolType.PICKAXE)
			.requiresCorrectToolForDrops()
			.strength(1.5F, 6.0F));
	public static final BlockPropsFactory NETHER_BASALT_ORE_PROPS = new BlockPropsFactory(() -> AbstractBlock.Properties.of
			(Material.STONE)
			.sound(SoundType.BASALT)
			.harvestTool(ToolType.PICKAXE)
			.requiresCorrectToolForDrops()
			.strength(3.0F, 3.0F));
	public static final BlockPropsFactory NETHER_NETHERRACK_ORE_PROPS = new BlockPropsFactory(() -> AbstractBlock.Properties.of
			(Material.STONE)
			.sound(SoundType.NETHER_ORE)
			.harvestTool(ToolType.PICKAXE)
			.requiresCorrectToolForDrops()
			.strength(3.0F, 3.0F));
	public static final BlockPropsFactory MUD_PROPS = new BlockPropsFactory(() -> AbstractBlock.Properties.of
			(Material.DIRT)
			.sound(JSoundTypes.MUD)
			.harvestTool(ToolType.SHOVEL)
			.strength(0.5F));
	public static final BlockPropsFactory LUNIUM_BLOCK_PROPS = new BlockPropsFactory(() -> AbstractBlock.Properties.of
			(Material.METAL)
			.sound(JSoundTypes.LUNIUM_BLOCK)
			.harvestTool(ToolType.PICKAXE)
			.requiresCorrectToolForDrops()
			.strength(1.5F, 6.0F));
	public static final BlockPropsFactory SHADIUM_BLOCK_PROPS = new BlockPropsFactory(() -> AbstractBlock.Properties.of
			(Material.METAL)
			.sound(JSoundTypes.SHADIUM_BLOCK)
			.harvestTool(ToolType.PICKAXE)
			.requiresCorrectToolForDrops()
			.strength(1.5F, 6.0F));
	public static final BlockPropsFactory BERRY_BUSH_PROPS = new BlockPropsFactory(() -> AbstractBlock.Properties.of
			(Material.PLANT)
			.sound(SoundType.SWEET_BERRY_BUSH)
			.randomTicks()
			.noCollission());
	public static final BlockPropsFactory SPAWNER_PROPS = new BlockPropsFactory(() -> AbstractBlock.Properties.of
			(Material.STONE)
			.sound(SoundType.METAL)
			.requiresCorrectToolForDrops()
			//.isViewBlocking(() -> (Blocks::never))
			.strength(1.5F, 6.0F));
	public static final BlockPropsFactory GLOWSHROOM_PROPS = new BlockPropsFactory(() -> AbstractBlock.Properties.of
			(Material.REPLACEABLE_PLANT)
			.sound(SoundType.SWEET_BERRY_BUSH)
			.lightLevel((state) -> 9)
			.noCollission());
	public static final BlockPropsFactory PORTAL = new BlockPropsFactory(() -> AbstractBlock.Properties.of
			(Material.PORTAL)
			.sound(SoundType.GLASS)
			.randomTicks()
			.noCollission()
			.strength(-1.0F));
	public static final BlockPropsFactory CAVE_VINE_PROPS = new BlockPropsFactory(() -> AbstractBlock.Properties.of
			(Material.REPLACEABLE_PLANT)
			.sound(SoundType.SWEET_BERRY_BUSH)
			.lightLevel((state) -> 3)
			.noCollission());
}
