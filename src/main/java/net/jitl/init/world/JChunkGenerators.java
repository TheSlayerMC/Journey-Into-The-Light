package net.jitl.init.world;

import com.mojang.serialization.Codec;
import net.jitl.JITL;
import net.jitl.common.dimension.JChunkGenerator;
import net.minecraft.core.Registry;
import net.minecraft.world.level.chunk.ChunkGenerator;
import ru.timeconqueror.timecore.api.registry.SimpleVanillaRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;
import ru.timeconqueror.timecore.api.registry.util.Promised;

public class JChunkGenerators {

    @AutoRegistrable
    private static final SimpleVanillaRegister<Codec<? extends ChunkGenerator>> REGISTER = new SimpleVanillaRegister<>(JITL.MODID, Registry.CHUNK_GENERATOR);

    public static final Promised<Codec<? extends ChunkGenerator>> JITL_CHUNK_GENERATOR = REGISTER.register("chunk_generator", () -> JChunkGenerator.CODEC);
}
