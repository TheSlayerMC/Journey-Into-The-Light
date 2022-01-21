package net.jitl.core.util;

import net.jitl.core.init.JSoundTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import ru.timeconqueror.timecore.api.registry.util.BlockPropsFactory;

public class JBlockProperties {

    public static final BlockPropsFactory GRASS_PROPS = new BlockPropsFactory(() -> Properties.of
            (Material.GRASS)
            .sound(SoundType.GRASS)
            .randomTicks()
            .strength(0.6F));

    public static final BlockPropsFactory GRASSY_PERMAFROST_PROPS = new BlockPropsFactory(() -> Properties.of
            (Material.GRASS)
            .sound(JSoundTypes.GRASSY_PERMAFROST)
            .randomTicks()
            .strength(1.2F));

    public static final BlockPropsFactory CRUMBLED_PERMAFROST_PROPS = new BlockPropsFactory(() -> Properties.of
            (Material.DIRT)
            .sound(JSoundTypes.CRUMBLED_PERMAFROST)
			.strength(1.1F));


	public static final BlockPropsFactory PERMAFROST_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.STONE)
			.sound(JSoundTypes.PERMAFROST)
			.requiresCorrectToolForDrops()
			.strength(1.75F, 7.0F));

	public static final BlockPropsFactory STONE_PROPS = new BlockPropsFactory(() -> Properties.of
					(Material.STONE)
			.sound(SoundType.STONE)
			.requiresCorrectToolForDrops()
			.strength(1.5F, 6.0F));

	public static final BlockPropsFactory DEEPSLATE_PROPS = new BlockPropsFactory(() -> Properties.of
					(Material.STONE)
			.sound(SoundType.DEEPSLATE)
			.requiresCorrectToolForDrops()
			.strength(2.0F, 6.0F));

	public static final BlockPropsFactory CACTUS_PROPS = new BlockPropsFactory(() -> Properties.of
					(Material.CACTUS)
			.sound(SoundType.WOOL)
			.strength(0.4F));

	public static final BlockPropsFactory STONE_MODEL_PROPS = new BlockPropsFactory(() -> Properties.of
					(Material.STONE)
			.sound(SoundType.STONE)
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

	public static final BlockPropsFactory VINES_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.LEAVES)
			.sound(SoundType.GRASS)
			.noOcclusion()
			.noCollission()
			.isSuffocating(JBlockProperties::never)
			.isViewBlocking(JBlockProperties::never)
			.strength(0.1F, 0.1F));

	public static final BlockPropsFactory LOG_PROPS = new BlockPropsFactory(() -> Properties.of(Material.WOOD, (state5) -> {
		return state5.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.COLOR_BROWN : MaterialColor.TERRACOTTA_BROWN;
	})
			.sound(SoundType.WOOD)
			.strength(2F, 6.0F));

	public static final BlockPropsFactory BASALT_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.STONE)
			.sound(SoundType.BASALT)
			.requiresCorrectToolForDrops()
			.strength(1.5F, 6.0F));

	public static final BlockPropsFactory METAL_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.METAL)
			.sound(SoundType.METAL)
			.requiresCorrectToolForDrops()
			.strength(1.5F, 6.0F));

	public static final BlockPropsFactory WOOD_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.WOOD)
			.sound(SoundType.WOOD)
			.strength(2.0F, 3.0F));

	public static final BlockPropsFactory SHROOM_SHELF = new BlockPropsFactory(() -> Properties.of
			(Material.WOOD)
			.sound(SoundType.FUNGUS)
			.strength(0.2F, 0.1F));

	public static final BlockPropsFactory SAND_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.SAND)
			.sound(SoundType.SAND)
			.strength(0.5F));

	public static final BlockPropsFactory DIRT_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.DIRT)
			.sound(SoundType.GRAVEL)
			.strength(0.5F));

	public static final BlockPropsFactory HOLD_FIRE = new BlockPropsFactory(() -> Properties.of(Material.DIRT)
			.sound(SoundType.STONE)
			.strength(0.5F));

	public static final BlockPropsFactory HOLD_FIRE_SAND = new BlockPropsFactory(() -> Properties.of(Material.DIRT)
			.sound(SoundType.SAND)
			.strength(0.5F));

	public static final BlockPropsFactory ORE_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.STONE)
			.sound(SoundType.STONE)
			.requiresCorrectToolForDrops()
			.strength(3.0F, 3.0F));

	public static final BlockPropsFactory LUNIUM_ORE_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.STONE)
			.sound(JSoundTypes.LUNIUM_ORE)
			.requiresCorrectToolForDrops()
			.strength(3.0F, 3.0F));

	public static final BlockPropsFactory SHADIUM_ORE_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.STONE)
			.sound(JSoundTypes.SHADIUM_ORE)
			.requiresCorrectToolForDrops()
			.strength(3.0F, 3.0F));

	public static final BlockPropsFactory BRICK_PROPS = new BlockPropsFactory(() -> Properties.of
					(Material.STONE)
			.sound(JSoundTypes.BRICK)
			.requiresCorrectToolForDrops()
			.strength(1.5F, 6.0F));

	public static final BlockPropsFactory DUNGEON_BLOCK_PROPS = new BlockPropsFactory(() -> Properties.of
					(Material.STONE)
			.sound(JSoundTypes.BRICK)
			.requiresCorrectToolForDrops()
			.strength(-1F, 12000.0F));

	public static final BlockPropsFactory POTTERY_PROPS = new BlockPropsFactory(() -> Properties.of
					(Material.STONE)
			.sound(JSoundTypes.POTTERY)
			.strength(0.5F, 0.5F));

	public static final BlockPropsFactory NETHER_BASALT_ORE_PROPS = new BlockPropsFactory(() -> Properties.of
					(Material.STONE)
			.sound(SoundType.BASALT)
			.requiresCorrectToolForDrops()
			.strength(3.0F, 3.0F));

	public static final BlockPropsFactory NETHER_NETHERRACK_ORE_PROPS = new BlockPropsFactory(() -> Properties.of
					(Material.STONE)
			.sound(SoundType.NETHER_ORE)
			.requiresCorrectToolForDrops()
			.strength(3.0F, 3.0F));

	public static final BlockPropsFactory MUD_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.DIRT)
			.sound(JSoundTypes.MUD)
			.strength(0.5F));

	public static final BlockPropsFactory LUNIUM_BLOCK_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.METAL)
			.sound(JSoundTypes.LUNIUM_BLOCK)
			.requiresCorrectToolForDrops()
			.strength(1.5F, 6.0F));

	public static final BlockPropsFactory DREADIRON_BLOCK_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.METAL)
			.sound(SoundType.NETHERITE_BLOCK)
			.requiresCorrectToolForDrops()
			.strength(50.0F, 1200.0F));

	public static final BlockPropsFactory SHADIUM_BLOCK_PROPS = new BlockPropsFactory(() -> Properties.of
			(Material.METAL)
			.sound(JSoundTypes.SHADIUM_BLOCK)
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
            .noOcclusion()
            .sound(SoundType.GLASS)
            .lightLevel((state) -> 10));

    public static final BlockPropsFactory GLOW_PLANT_PROPS = new BlockPropsFactory(() -> Properties.of
            (Material.GLASS)
            .noOcclusion()
            .sound(JSoundTypes.CRYSTAL_FRUIT)
            .lightLevel((state) -> 10));

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
			.strength(2.0F, 3.0F));



	private static boolean never(BlockState state, BlockGetter reader, BlockPos pos) {
		return false;
	}

	public static final BlockPropsFactory UNBREAKABLE_UTILITY = new BlockPropsFactory(() -> Properties.of(Material.BARRIER).noOcclusion().noCollission().noDrops());
}
