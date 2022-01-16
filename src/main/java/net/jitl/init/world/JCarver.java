package net.jitl.init.world;

import com.mojang.serialization.Codec;
import net.jitl.JITL;
import net.jitl.common.world.gen.carver.BoilingCaveCarver;
import net.jitl.common.world.gen.carver.FrozenCaveCarver;
import net.minecraft.core.Registry;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraftforge.registries.RegistryObject;
import ru.timeconqueror.timecore.api.registry.SimpleForgeRegister;
import ru.timeconqueror.timecore.api.registry.SimpleVanillaRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;
import ru.timeconqueror.timecore.api.registry.util.Promised;

public class JCarver {

    @AutoRegistrable
    private static final SimpleVanillaRegister<Codec<? extends JCaveCarverConfiguration>> REGISTER = new SimpleVanillaRegister(JITL.MODID, Registry.CARVER);

    public static final Promised<Codec<? extends JCaveCarverConfiguration>> BOILING_CAVE = REGISTER.register("boiling_cave", () -> (JCaveCarverConfiguration.CODEC));

    //public static final RegistryObject<WorldCarver<CaveCarverConfiguration>> FROZEN_CARVER = REGISTER.register("frozen_carver", () -> new FrozenCaveCarver(CaveCarverConfiguration.CODEC));

}
