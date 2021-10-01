package net.jitl.common.dimension;

import net.jitl.JITL;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class Dimensions {

    public static final RegistryKey<World> EUCA = RegistryKey.create(Registry.DIMENSION_REGISTRY, JITL.rl("euca"));
    public static final RegistryKey<World> FROZEN_LANDS = RegistryKey.create(Registry.DIMENSION_REGISTRY, JITL.rl("frozen"));

}