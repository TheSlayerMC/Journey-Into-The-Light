package net.jitl.core.init.world;

import net.jitl.common.world.gen.carver.BoilCaveCarver;
import net.jitl.common.world.gen.carver.FrozenCaveCarver;
import net.jitl.core.JITL;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.timeconqueror.timecore.api.registry.SimpleForgeRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

public class JCarver {

    @AutoRegistrable
    private static final SimpleForgeRegister<WorldCarver<?>> REGISTER = new SimpleForgeRegister<>(ForgeRegistries.WORLD_CARVERS, JITL.MODID);

    public static final RegistryObject<WorldCarver<CaveCarverConfiguration>> FROZEN_CARVER = REGISTER.register("frozen_cave", () -> new FrozenCaveCarver(CaveCarverConfiguration.CODEC));
    public static final RegistryObject<WorldCarver<CaveCarverConfiguration>> BOIL_CARVER = REGISTER.register("boil_cave", () -> new BoilCaveCarver(CaveCarverConfiguration.CODEC));
}
