package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.dimension.EucaBiomeProvider;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class JourneyBiomeRegistry {

    public static Biome EUCA_PLAINS;
    public static Biome EUCA_SILVER_PLAINS;

    private static RegistryKey<Biome> registerBiome(String name) {
        return RegistryKey.create(Registry.BIOME_REGISTRY, JITL.rl(name));
    }

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
        EUCA_PLAINS.setRegistryName(JITL.rl("euca/euca_plains"));
        event.getRegistry().register(EUCA_PLAINS);

        EUCA_SILVER_PLAINS.setRegistryName(JITL.rl("euca/euca_silver_plains"));
        event.getRegistry().register(EUCA_SILVER_PLAINS);
    }

    public static void registerProviders() {
        Registry.register(Registry.BIOME_SOURCE, JITL.rl("euca_provider"), EucaBiomeProvider.CODEC);
    }
}
