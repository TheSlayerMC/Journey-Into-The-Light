package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.world.gen.carver.BoilingCaveCarver;
import net.jitl.common.world.gen.carver.FrozenCaveCarver;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import ru.timeconqueror.timecore.api.registry.SimpleForgeRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

public class JCarver {

    @AutoRegistrable
    private static final SimpleForgeRegister<WorldCarver<?>> REGISTER = new SimpleForgeRegister<>(ForgeRegistries.WORLD_CARVERS, JITL.MODID);

    public static final RegistryObject<WorldCarver<ProbabilityConfig>> BOILING_CARVER = REGISTER.register("boiling_carver", () -> new BoilingCaveCarver(ProbabilityConfig.CODEC));

    public static final RegistryObject<WorldCarver<ProbabilityConfig>> FROZEN_CARVER = REGISTER.register("frozen_carver", () -> new FrozenCaveCarver(ProbabilityConfig.CODEC));

}
