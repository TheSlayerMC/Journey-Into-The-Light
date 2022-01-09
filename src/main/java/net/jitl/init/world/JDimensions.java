package net.jitl.init.world;

import net.jitl.JITL;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class JDimensions {
	public static final ResourceKey<DimensionType> EUCA = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, JITL.rl("euca"));
	public static final ResourceKey<Level> EUCA_WORLD = ResourceKey.create(Registry.DIMENSION_REGISTRY, JITL.rl("euca"));

	public static final ResourceKey<DimensionType> FROZEN = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, JITL.rl("frozen"));
	public static final ResourceKey<Level> FROZEN_WORLD = ResourceKey.create(Registry.DIMENSION_REGISTRY, JITL.rl("frozen"));

	public static final ResourceKey<DimensionType> DEPTHS = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, JITL.rl("depths"));
	public static final ResourceKey<Level> DEPTHS_WORLD = ResourceKey.create(Registry.DIMENSION_REGISTRY, JITL.rl("depths"));

	public static final ResourceKey<DimensionType> BOIL = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, JITL.rl("boil"));
	public static final ResourceKey<Level> BOIL_WORLD = ResourceKey.create(Registry.DIMENSION_REGISTRY, JITL.rl("boil"));
}
