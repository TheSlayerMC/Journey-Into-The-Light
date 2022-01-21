package net.jitl.core.init.world;

import com.mojang.serialization.Codec;
import net.jitl.core.JITL;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import ru.timeconqueror.timecore.api.registry.SimpleVanillaRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;
import ru.timeconqueror.timecore.api.registry.util.Promised;

public class JCarver {

    @AutoRegistrable
    private static final SimpleVanillaRegister<Codec<? extends CaveCarverConfiguration>> REGISTER = new SimpleVanillaRegister(JITL.MODID, Registry.CARVER);

    public static final Promised<Codec<? extends CaveCarverConfiguration>> BOILING_CAVE = REGISTER.register("boiling_cave", () -> CaveCarverConfiguration.CODEC);

    //public static final RegistryObject<WorldCarver<CaveCarverConfiguration>> FROZEN_CARVER = REGISTER.register("frozen_carver", () -> new FrozenCaveCarver(CaveCarverConfiguration.CODEC));

}
