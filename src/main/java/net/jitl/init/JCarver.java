package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.world.gen.carver.BoilingCaveCarver;
import net.jitl.common.world.gen.carver.FrozenCaveCarver;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import ru.timeconqueror.timecore.api.registry.SimpleForgeRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

public class JCarver {

    @AutoRegistrable
    private static final SimpleForgeRegister<WorldCarver<?>> REGISTER = new SimpleForgeRegister<>(ForgeRegistries.WORLD_CARVERS, JITL.MODID);

    public static final RegistryObject<WorldCarver<ProbabilityFeatureConfiguration>> BOILING_CARVER = REGISTER.register("boiling_carver", () -> new BoilingCaveCarver(ProbabilityFeatureConfiguration.CODEC));

    public static final RegistryObject<WorldCarver<ProbabilityFeatureConfiguration>> FROZEN_CARVER = REGISTER.register("frozen_carver", () -> new FrozenCaveCarver(ProbabilityFeatureConfiguration.CODEC));

}
