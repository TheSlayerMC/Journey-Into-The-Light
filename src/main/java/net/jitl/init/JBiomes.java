package net.jitl.init;

import net.jitl.JITL;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class JBiomes {

	public static RegistryKey<Biome> test_biome = register("test_biome");

	private static RegistryKey<Biome> register(String name) {
		return RegistryKey.create(Registry.BIOME_REGISTRY, JITL.rl(name));
	}
}
