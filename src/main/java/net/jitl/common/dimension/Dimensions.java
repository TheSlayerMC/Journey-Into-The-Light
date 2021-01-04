package net.jitl.common.dimension;

import net.jitl.JITL;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class Dimensions {

    public static final RegistryKey<World> EUCA = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(JITL.MODID + ":euca"));

}