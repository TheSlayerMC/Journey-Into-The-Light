package net.jitl.init.internal;

import net.jitl.init.JCarver;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.carver.ICarverConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class JConfiguredCarvers {

    public static final ConfiguredCarver<ProbabilityConfig> BOILING_CAVE = register("boiling_cave", JCarver.BOILING_CARVER.get().configured(new ProbabilityConfig(0.2F)));

    private static <WC extends ICarverConfig> ConfiguredCarver<WC> register(String string_, ConfiguredCarver<WC> configuredCarver_) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_CARVER, string_, configuredCarver_);
    }
}
