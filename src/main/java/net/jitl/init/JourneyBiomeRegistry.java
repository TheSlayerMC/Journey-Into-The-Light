package net.jitl.init;

import com.mojang.serialization.Codec;
import net.jitl.JITL;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;

public class JourneyBiomeRegistry {

    public static final RegistryKey<Biome> EUCA_PLAINS = registerBiome("euca/euca_plains");
    public static final RegistryKey<Biome> EUCA_SILVER_PLAINS = registerBiome("euca/euca_silver_plains");

    public static final RegistryKey<Codec<? extends BiomeProvider>> EUCA_BIOME_PROVIDER = RegistryKey.create(Registry.BIOME_SOURCE_REGISTRY, JITL.rl("euca_provider"));

    private static RegistryKey<Biome> registerBiome(String name) {
        return RegistryKey.create(Registry.BIOME_REGISTRY, JITL.rl(name));
    }
}
