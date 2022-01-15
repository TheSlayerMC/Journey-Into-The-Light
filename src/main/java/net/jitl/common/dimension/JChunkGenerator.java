package net.jitl.common.dimension;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryLookupCodec;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class JChunkGenerator extends NoiseBasedChunkGenerator {
    public static final Codec<JChunkGenerator> CODEC =
            RecordCodecBuilder.create((instance_) ->
                    instance_.group(RegistryLookupCodec.create(Registry.NOISE_REGISTRY)
                                            .forGetter((noiseBasedChunkGenerator_) -> noiseBasedChunkGenerator_.noises),
                                    BiomeSource.CODEC.fieldOf("biome_source")
                                            .forGetter((noiseBasedChunkGenerator1_) -> noiseBasedChunkGenerator1_.biomeSource),
                                    Codec.LONG.fieldOf("seed")
                                            .orElseGet(SeedBearer::getSeed)
                                            .forGetter((noiseBasedChunkGenerator2_) -> noiseBasedChunkGenerator2_.seed),
                                    NoiseGeneratorSettings.CODEC.fieldOf("settings")
                                            .forGetter((noiseBasedChunkGenerator3_) -> noiseBasedChunkGenerator3_.settings))
                            .apply(instance_, instance_.stable(JChunkGenerator::new)));

    public JChunkGenerator(Registry<NormalNoise.NoiseParameters> noiseRegistry1_, BiomeSource biomeSource1_, long seed1_, Supplier<NoiseGeneratorSettings> settings1_) {
        super(noiseRegistry1_, biomeSource1_, seed1_, settings1_);
    }

    @Override
    protected @NotNull Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public @NotNull ChunkGenerator withSeed(long seed) {
        return new JChunkGenerator(this.noises, this.biomeSource.withSeed(seed), seed, this.settings);
    }
}
