package net.jitl.init.world;

import net.jitl.JITL;
import net.jitl.common.dimension.JBiomeProvider;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class JBiomeRegistry {

    public static Biome EUCA_PLAINS;
    public static Biome EUCA_GOLDITE_GRAINS;
    public static Biome EUCA_SILVER_PLAINS;

    public static Biome DEPTHS;

    public static Biome FROZEN_WASTES;
    public static Biome FROZEN_DYING_FOREST;
    public static Biome FROZEN_BITTERWOOD_FOREST;

    public static Biome BOILING_PLAINS;
    public static Biome BOILING_SANDS;
    public static Biome CHARRED_FIELDS;
    public static Biome SCORCHED_WASTELANDS;


    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
        EUCA_PLAINS.setRegistryName(JITL.rl("euca/euca_plains"));
        event.getRegistry().register(EUCA_PLAINS);

        EUCA_GOLDITE_GRAINS.setRegistryName(JITL.rl("euca/euca_goldite_grains"));
        event.getRegistry().register(EUCA_GOLDITE_GRAINS);

        EUCA_SILVER_PLAINS.setRegistryName(JITL.rl("euca/euca_silver_plains"));
        event.getRegistry().register(EUCA_SILVER_PLAINS);

        FROZEN_WASTES.setRegistryName(JITL.rl("frozen/frozen_wastes"));
        event.getRegistry().register(FROZEN_WASTES);

        FROZEN_DYING_FOREST.setRegistryName(JITL.rl("frozen/dying_forest"));
        event.getRegistry().register(FROZEN_DYING_FOREST);

        FROZEN_BITTERWOOD_FOREST.setRegistryName(JITL.rl("frozen/bitterwood_forest"));
        event.getRegistry().register(FROZEN_BITTERWOOD_FOREST);

        DEPTHS.setRegistryName(JITL.rl("depths/depths"));
        event.getRegistry().register(DEPTHS);

        BOILING_PLAINS.setRegistryName(JITL.rl("boil/boil"));
        event.getRegistry().register(BOILING_PLAINS);

        BOILING_SANDS.setRegistryName(JITL.rl("boil/boiling_sands"));
        event.getRegistry().register(BOILING_SANDS);

        CHARRED_FIELDS.setRegistryName(JITL.rl("boil/charred_fields"));
        event.getRegistry().register(CHARRED_FIELDS);

        SCORCHED_WASTELANDS.setRegistryName(JITL.rl("boil/scorched_wastelands"));
        event.getRegistry().register(SCORCHED_WASTELANDS);
    }

    public static void registerProviders() {
        Registry.register(Registry.BIOME_SOURCE, JITL.rl("base_biome_provider"), JBiomeProvider.CODEC);
        //Registry.register(Registry.BIOME_SOURCE, JITL.rl("frozen_provider"), FrozenBiomeProvider.CODEC);
    }
}
