package net.jitl.init.world;

import net.jitl.JITL;
import net.jitl.common.world.gen.carver.BoilingCaveCarver;
import net.jitl.common.world.gen.carver.FrozenCaveCarver;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.timeconqueror.timecore.api.registry.SimpleForgeRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

public class JCarver {

    @AutoRegistrable
    private static final SimpleForgeRegister<WorldCarver<?>> REGISTER = new SimpleForgeRegister<>(ForgeRegistries.WORLD_CARVERS, JITL.MODID);

    public static final RegistryObject<WorldCarver<JCaveCarverConfiguration>> BOILING_CAVE = REGISTER.register("boiling_cave", () -> new BoilingCaveCarver(JCaveCarverConfiguration.CODEC));

    public static final RegistryObject<WorldCarver<CaveCarverConfiguration>> FROZEN_CARVER = REGISTER.register("frozen_carver", () -> new FrozenCaveCarver(CaveCarverConfiguration.CODEC));

}
