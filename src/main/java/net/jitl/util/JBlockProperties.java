package net.jitl.util;

import net.jitl.init.JSoundTypes;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import ru.timeconqueror.timecore.api.registry.util.BlockPropsFactory;

public class JBlockProperties {

    public static final BlockPropsFactory GRASS_PROPS = new BlockPropsFactory(() -> Properties.of
            (Material.GRASS)
            .sound(SoundType.GRASS)
            .harvestTool(ToolType.SHOVEL)
            .randomTicks()
            .strength(0.6F));

    public static final BlockPropsFactory GRASSY_PERMAFROST_PROPS = new BlockPropsFactory(() -> Properties.of
            (Material.GRASS)
            .sound(JSoundTypes.GRASSY_PERMAFROST)
            .harvestTool(ToolType.SHOVEL)
            .randomTicks()
            .strength(1.2F));

    public static final BlockPropsFactory CRUMBLED_PERMAFROST_PROPS = new BlockPropsFactory(() -> Properties.of
            (Material.DIRT)
            .sound(JSoundTypes.CRUMBLED_PERMAFROST)
            .harvestTool(ToolType.SHOVEL)
            .strength(1.1F));


    public static final BlockPropsFactory PERMAFROST_PROPS = new BlockPropsFactory(() -> Properties.of
            (Material.STONE)
            .sound(JSoundTypes.PERMAFROST)
            .harvestTool(ToolType.PICKAXE)
            .requiresCorrectToolForDrops()
            .strength(1.75F, 7.0F));

    public static final BlockPropsFactory STONE_PROPS = new BlockPropsFactory(() -> Properties.of
            (Material.STONE)
            .sound(SoundType.STONE)
            .harvestTool(ToolType.PICKAXE)
            .requiresCorrectToolForDrops()
            .strength(1.5F, 6.0F));

	public static final BlockPropsFactory STONE_MODEL_PROPS = new BlockPropsFactory(() -> Properties.of
					(Material.STONE)
			.sound(SoundType.STONE)
			.harvestTool(ToolType.PICKAXE)
			.requiresCorrectToolForDrops()
			.noOcclusion()
			.strength(1.5F, 6.0F));

    public static final BlockPropsFactory LEAVES_PROPS = new BlockPropsFactory(() -> Properties.of
            (Material.LEAVES)
			.sound(SoundType.GRASS)
			.requiresCorrectToolForDrops()
			.noOcclusion()
			.isSuffocating(JBlockProperties::never)
			.isViewBlocking(JBlockProperties::never)
			.strength(0.2F, 0.1F));

	public static final BlockPropsFactory LOG_PROPS = new BlockPropsFactory(() -> Properties.of(Material.WOOD, (state5) -> {
		return state5.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.COLOR_BROWN : MaterialColor.TERRACOTTA_BROWN;
	})
			.sound(SoundType.WOOD)
			.harvestTool(ToolType.AXE)
			.strength(2F, 6.0F));

	public static final BlockPropsFactory BASALT_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.STONE)
			.sound(SoundType.BASALT)
			.harvestTool(ToolType.PICKAXE)
			.requiresCorrectToolForDrops()
			.strength(1.5F, 6.0F));

	public static final BlockPropsFactory METAL_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.METAL)
			.sound(SoundType.METAL)
			.harvestTool(ToolType.PICKAXE)
			.requiresCorrectToolForDrops()
			.strength(1.5F, 6.0F));

	public static final BlockPropsFactory WOOD_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.WOOD)
			.sound(SoundType.WOOD)
			.harvestTool(ToolType.AXE)
			.strength(2.0F, 3.0F));

	public static final BlockPropsFactory SAND_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.SAND)
			.sound(SoundType.SAND)
			.harvestTool(ToolType.SHOVEL)
			.strength(0.5F));

	public static final BlockPropsFactory DIRT_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.DIRT)
			.sound(SoundType.GRAVEL)
			.harvestTool(ToolType.SHOVEL)
			.strength(0.5F));

	public static final BlockPropsFactory HOLD_FIRE = new BlockPropsFactory(() -> Properties.of(Material.DIRT)
			.sound(SoundType.STONE)
			.harvestTool(ToolType.SHOVEL)
			.strength(0.5F));

	public static final BlockPropsFactory HOLD_FIRE_SAND = new BlockPropsFactory(() -> Properties.of(Material.DIRT)
			.sound(SoundType.SAND)
			.harvestTool(ToolType.SHOVEL)
			.strength(0.5F));

	public static final BlockPropsFactory ORE_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.STONE)
			.sound(SoundType.STONE)
			.harvestTool(ToolType.PICKAXE)
			.requiresCorrectToolForDrops()
			.strength(3.0F, 3.0F));

	public static final BlockPropsFactory LUNIUM_ORE_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.STONE)
			.sound(JSoundTypes.LUNIUM_ORE)
			.harvestTool(ToolType.PICKAXE)
			.requiresCorrectToolForDrops()
			.strength(3.0F, 3.0F));

	public static final BlockPropsFactory SHADIUM_ORE_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.STONE)
			.sound(JSoundTypes.SHADIUM_ORE)
			.harvestTool(ToolType.PICKAXE)
			.requiresCorrectToolForDrops()
			.strength(3.0F, 3.0F));

	public static final BlockPropsFactory BRICK_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.STONE)
			.sound(JSoundTypes.BRICK)
			.harvestTool(ToolType.PICKAXE)
			.requiresCorrectToolForDrops()
			.strength(1.5F, 6.0F));

	public static final BlockPropsFactory NETHER_BASALT_ORE_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.STONE)
			.sound(SoundType.BASALT)
			.harvestTool(ToolType.PICKAXE)
			.requiresCorrectToolForDrops()
			.strength(3.0F, 3.0F));

	public static final BlockPropsFactory NETHER_NETHERRACK_ORE_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.STONE)
			.sound(SoundType.NETHER_ORE)
			.harvestTool(ToolType.PICKAXE)
			.requiresCorrectToolForDrops()
			.strength(3.0F, 3.0F));

	public static final BlockPropsFactory MUD_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.DIRT)
			.sound(JSoundTypes.MUD)
			.harvestTool(ToolType.SHOVEL)
			.strength(0.5F));

	public static final BlockPropsFactory LUNIUM_BLOCK_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.METAL)
			.sound(JSoundTypes.LUNIUM_BLOCK)
			.harvestTool(ToolType.PICKAXE)
			.requiresCorrectToolForDrops()
			.strength(1.5F, 6.0F));

	public static final BlockPropsFactory DREADIRON_BLOCK_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.METAL)
			.sound(SoundType.NETHERITE_BLOCK)
			.harvestTool(ToolType.PICKAXE)
			.requiresCorrectToolForDrops()
			.strength(50.0F, 1200.0F));

	public static final BlockPropsFactory SHADIUM_BLOCK_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.METAL)
			.sound(JSoundTypes.SHADIUM_BLOCK)
			.harvestTool(ToolType.PICKAXE)
			.requiresCorrectToolForDrops()
			.strength(1.5F, 6.0F));

	public static final BlockPropsFactory BERRY_BUSH_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.PLANT)
			.sound(SoundType.SWEET_BERRY_BUSH)
			.randomTicks()
			.noCollission());

	public static final BlockPropsFactory SPAWNER_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.STONE)
			.sound(SoundType.METAL)
			.requiresCorrectToolForDrops()
			.noOcclusion()
			.isViewBlocking(JBlockProperties::never)
			.strength(1.5F, 6.0F));

	public static final BlockPropsFactory ICE_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.ICE)
			.sound(SoundType.GLASS)
			.requiresCorrectToolForDrops()
			.noOcclusion()
			.isViewBlocking(JBlockProperties::never)
			.strength(0.5F, 0.2F));

	public static final BlockPropsFactory ICE_CRYSTAL_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.ICE)
			.sound(JSoundTypes.ICE_CRYSTAL)
			.requiresCorrectToolForDrops()
			.noOcclusion()
			.isViewBlocking(JBlockProperties::never)
			.strength(0.5F, 0.2F));

	public static final BlockPropsFactory GLOWSHROOM_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.REPLACEABLE_PLANT)
			.sound(SoundType.SWEET_BERRY_BUSH)
			.lightLevel((state) -> 5)
			.noCollission());

	public static final BlockPropsFactory GLOW_BLOCK = new BlockPropsFactory(() -> Properties.of
			(Material.GLASS)
			.sound(SoundType.GLASS)
			.lightLevel((state) -> 5));

	public static final BlockPropsFactory PLANT_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.REPLACEABLE_PLANT)
			.sound(SoundType.SWEET_BERRY_BUSH)
			.noCollission());

	public static final BlockPropsFactory FIRE_PLANT_PROPS = new BlockPropsFactory(() -> Properties.of
					(Material.REPLACEABLE_FIREPROOF_PLANT)
			.sound(SoundType.SWEET_BERRY_BUSH)
			.noCollission());

	public static final BlockPropsFactory CROP_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.PLANT)
			.sound(SoundType.CROP)
			.noCollission()
			.instabreak()
			.randomTicks());

	public static final BlockPropsFactory PORTAL = new BlockPropsFactory(() -> Properties.of
			(Material.PORTAL)
			.sound(SoundType.GLASS)
			.randomTicks()
			.noCollission()
			.isViewBlocking(JBlockProperties::never)
			.strength(-1.0F));

	public static final BlockPropsFactory CAVE_VINE_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.REPLACEABLE_PLANT)
			.sound(SoundType.SWEET_BERRY_BUSH)
			.lightLevel((state) -> 3)
			.noCollission());

	public static final BlockPropsFactory FUMICE_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.NETHER_WOOD)
			.sound(JSoundTypes.FUMICE)
			.harvestTool(ToolType.AXE)
			.strength(2.0F, 3.0F));

	private static boolean never(BlockState state, IBlockReader reader, BlockPos pos) {
		return false;
	}

	public static final BlockPropsFactory UNBREAKABLE_UTILITY = new BlockPropsFactory(() -> Properties.of(Material.BARRIER).noOcclusion().noCollission().noDrops());
}
