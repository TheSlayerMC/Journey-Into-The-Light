package net.jitl.init;

import com.mojang.serialization.Codec;
import net.jitl.JITL;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class JourneyBiomeRegistry {

    public static Biome EUCA_PLAINS;
    public static Biome EUCA_SILVER_PLAINS;

    public static final RegistryKey<Codec<? extends BiomeProvider>> EUCA_BIOME_PROVIDER = RegistryKey.create(Registry.BIOME_SOURCE_REGISTRY, JITL.rl("euca_provider"));


    public static void registerProviders() {

    }

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
        EUCA_PLAINS.setRegistryName(JITL.rl("euca/euca_plains"));
        event.getRegistry().register(EUCA_PLAINS);

        EUCA_SILVER_PLAINS.setRegistryName(JITL.rl("euca/euca_silver_plains"));
        event.getRegistry().register(EUCA_SILVER_PLAINS);
    }
}
