package net.jitl.common.dimension;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Function3;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.jitl.JITL;
import net.jitl.init.JBiomeRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeRegistry;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.MaxMinNoiseMixer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class FrozenBiomeProvider extends BiomeProvider {

    //TODO: Remove this class, it's useless for now
    private static final FrozenBiomeProvider.Noise DEFAULT_NOISE_PARAMETERS = new FrozenBiomeProvider.Noise(-7, ImmutableList.of(1.0D, 1.0D));
    public static final MapCodec<FrozenBiomeProvider> DIRECT_CODEC = RecordCodecBuilder.mapCodec((builder) -> {
        return builder.group(Codec.LONG.fieldOf("seed").forGetter((frozenProvider6) -> {
            return frozenProvider6.seed;
        }), RecordCodecBuilder.<Pair<Biome.Attributes, Supplier<Biome>>>create((biomeAttributes1) -> {
            return biomeAttributes1.group(Biome.Attributes.CODEC.fieldOf("parameters").forGetter(Pair::getFirst), Biome.CODEC.fieldOf("biome").forGetter(Pair::getSecond)).apply(biomeAttributes1, Pair::of);
        }).listOf().fieldOf("biomes").forGetter((frozenProvider5) -> {
            return frozenProvider5.parameters;
        }), FrozenBiomeProvider.Noise.CODEC.fieldOf("temperature_noise").forGetter((frozenProvider4) -> {
            return frozenProvider4.temperatureParams;
        }), FrozenBiomeProvider.Noise.CODEC.fieldOf("humidity_noise").forGetter((frozenProvider3) -> {
            return frozenProvider3.humidityParams;
        }), FrozenBiomeProvider.Noise.CODEC.fieldOf("altitude_noise").forGetter((frozenProvider2) -> {
            return frozenProvider2.altitudeParams;
        }), FrozenBiomeProvider.Noise.CODEC.fieldOf("weirdness_noise").forGetter((frozenProvider1) -> {
            return frozenProvider1.weirdnessParams;
        })).apply(builder, FrozenBiomeProvider::new);
    });
    public static final Codec<FrozenBiomeProvider> CODEC = Codec.mapEither(FrozenBiomeProvider.DefaultBuilder.CODEC, DIRECT_CODEC).xmap((either) -> {
        return either.map(FrozenBiomeProvider.DefaultBuilder::biomeSource, Function.identity());
    }, (frozenProvider) -> {
        return frozenProvider.preset().map(Either::<FrozenBiomeProvider.DefaultBuilder, FrozenBiomeProvider>left).orElseGet(() -> {
            return Either.right(frozenProvider);
        });
    }).codec();
    private final FrozenBiomeProvider.Noise temperatureParams;
    private final FrozenBiomeProvider.Noise humidityParams;
    private final FrozenBiomeProvider.Noise altitudeParams;
    private final FrozenBiomeProvider.Noise weirdnessParams;
    private final MaxMinNoiseMixer temperatureNoise;
    private final MaxMinNoiseMixer humidityNoise;
    private final MaxMinNoiseMixer altitudeNoise;
    private final MaxMinNoiseMixer weirdnessNoise;
    private final List<Pair<Biome.Attributes, Supplier<Biome>>> parameters;
    private final boolean useY;
    private final long seed;
    private final Optional<Pair<Registry<Biome>, FrozenBiomeProvider.Preset>> preset;

    private FrozenBiomeProvider(long seed, List<Pair<Biome.Attributes, Supplier<Biome>>> biomeAttributes, Optional<Pair<Registry<Biome>, FrozenBiomeProvider.Preset>> frozenProviderPreset) {
        this(seed, biomeAttributes, DEFAULT_NOISE_PARAMETERS, DEFAULT_NOISE_PARAMETERS, DEFAULT_NOISE_PARAMETERS, DEFAULT_NOISE_PARAMETERS, frozenProviderPreset);
    }

    private FrozenBiomeProvider(long seed, List<Pair<Biome.Attributes, Supplier<Biome>>> biomeAttributes, FrozenBiomeProvider.Noise temperatureNoise, FrozenBiomeProvider.Noise humidityNoise, FrozenBiomeProvider.Noise altitudeNoise, FrozenBiomeProvider.Noise weirdnessNoise) {
        this(seed, biomeAttributes, temperatureNoise, humidityNoise, altitudeNoise, weirdnessNoise, Optional.empty());
    }

    private FrozenBiomeProvider(long seed, List<Pair<Biome.Attributes, Supplier<Biome>>> biomeAttributes, FrozenBiomeProvider.Noise temperatureNoise, FrozenBiomeProvider.Noise humidityNoise, FrozenBiomeProvider.Noise altitudeNoise, FrozenBiomeProvider.Noise weirdnessNoise, Optional<Pair<Registry<Biome>, FrozenBiomeProvider.Preset>> frozenProviderPreset) {
        super(biomeAttributes.stream().map(Pair::getSecond));
        this.seed = seed;
        this.preset = frozenProviderPreset;
        this.temperatureParams = temperatureNoise;
        this.humidityParams = humidityNoise;
        this.altitudeParams = altitudeNoise;
        this.weirdnessParams = weirdnessNoise;
        this.temperatureNoise = MaxMinNoiseMixer.create(new SharedSeedRandom(seed), temperatureNoise.firstOctave(), temperatureNoise.amplitudes());
        this.humidityNoise = MaxMinNoiseMixer.create(new SharedSeedRandom(seed + 1L), humidityNoise.firstOctave(), humidityNoise.amplitudes());
        this.altitudeNoise = MaxMinNoiseMixer.create(new SharedSeedRandom(seed + 2L), altitudeNoise.firstOctave(), altitudeNoise.amplitudes());
        this.weirdnessNoise = MaxMinNoiseMixer.create(new SharedSeedRandom(seed + 3L), weirdnessNoise.firstOctave(), weirdnessNoise.amplitudes());
        this.parameters = biomeAttributes;
        this.useY = false;
    }

    protected Codec<? extends BiomeProvider> codec() {
        return CODEC;
    }

    @OnlyIn(Dist.CLIENT)
    public BiomeProvider withSeed(long seed) {
        return new FrozenBiomeProvider(seed, this.parameters, this.temperatureParams, this.humidityParams, this.altitudeParams, this.weirdnessParams, this.preset);
    }

    private Optional<FrozenBiomeProvider.DefaultBuilder> preset() {
        return this.preset.map((registryPresetPair) -> {
            return new FrozenBiomeProvider.DefaultBuilder(registryPresetPair.getSecond(), registryPresetPair.getFirst(), this.seed);
        });
    }

    public Biome getNoiseBiome(int x, int y, int z) {
        int i = this.useY ? y : 0;
        Biome.Attributes biome$attributes = new Biome.Attributes((float) this.temperatureNoise.getValue(x, i, z), (float) this.humidityNoise.getValue(x, i, z), (float) this.altitudeNoise.getValue(x, i, z), (float) this.weirdnessNoise.getValue(x, i, z), 0.0F);
        return this.parameters.stream().min(Comparator.comparing((attributeBiomePair) -> {
            return attributeBiomePair.getFirst().fitness(biome$attributes);
        })).map(Pair::getSecond).map(Supplier::get).orElse(BiomeRegistry.THE_VOID);
    }

    public boolean stable(long seed) {
        return this.seed == seed && this.preset.isPresent() && Objects.equals(this.preset.get().getSecond(), Preset.frozen);
    }

    static final class DefaultBuilder {
        public static final MapCodec<FrozenBiomeProvider.DefaultBuilder> CODEC = RecordCodecBuilder.mapCodec((builder) -> {
            return builder.group(ResourceLocation.CODEC.flatXmap((id) -> {
                return Optional.ofNullable(FrozenBiomeProvider.Preset.BY_NAME.get(id)).map(DataResult::success).orElseGet(() -> {
                    return DataResult.error("Unknown preset: " + id);
                });
            }, (preset1) -> {
                return DataResult.success(preset1.name);
            }).fieldOf("preset").stable().forGetter(FrozenBiomeProvider.DefaultBuilder::preset), RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter(FrozenBiomeProvider.DefaultBuilder::biomes), Codec.LONG.fieldOf("seed").stable().forGetter(FrozenBiomeProvider.DefaultBuilder::seed)).apply(builder, builder.stable(FrozenBiomeProvider.DefaultBuilder::new));
        });
        private final FrozenBiomeProvider.Preset preset;
        private final Registry<Biome> biomes;
        private final long seed;

        private DefaultBuilder(FrozenBiomeProvider.Preset preset, Registry<Biome> lookupRegistry, long seed) {
            this.preset = preset;
            this.biomes = lookupRegistry;
            this.seed = seed;
        }

        public FrozenBiomeProvider.Preset preset() {
            return this.preset;
        }

        public Registry<Biome> biomes() {
            return this.biomes;
        }

        public long seed() {
            return this.seed;
        }

        public FrozenBiomeProvider biomeSource() {
            return this.preset.biomeSource(this.biomes, this.seed);
        }
    }

    static class Noise {
        private final int firstOctave;
        private final DoubleList amplitudes;
        public static final Codec<FrozenBiomeProvider.Noise> CODEC = RecordCodecBuilder.create((builder) -> {
            return builder.group(Codec.INT.fieldOf("firstOctave").forGetter(FrozenBiomeProvider.Noise::firstOctave), Codec.DOUBLE.listOf().fieldOf("amplitudes").forGetter(FrozenBiomeProvider.Noise::amplitudes)).apply(builder, FrozenBiomeProvider.Noise::new);
        });

        public Noise(int numOctaves, List<Double> amplitudes) {
            this.firstOctave = numOctaves;
            this.amplitudes = new DoubleArrayList(amplitudes);
        }

        public int firstOctave() {
            return this.firstOctave;
        }

        public DoubleList amplitudes() {
            return this.amplitudes;
        }
    }

    public static class Preset {
        private static final Map<ResourceLocation, FrozenBiomeProvider.Preset> BY_NAME = Maps.newHashMap();
        public static final FrozenBiomeProvider.Preset frozen = new FrozenBiomeProvider.Preset(JITL.rl("frozen"), (preset, lookupRegistry1, seed1) -> {
            return new FrozenBiomeProvider(seed1, ImmutableList.of(Pair.of(new Biome.Attributes(0.0F, 0.0F, 0.0F, 0.0F, 0.0F), () -> {
                return lookupRegistry1.get(JBiomeRegistry.FROZEN_WASTES.getRegistryName());
            })), Optional.of(Pair.of(lookupRegistry1, preset)));
        });
        private final ResourceLocation name;
        private final Function3<FrozenBiomeProvider.Preset, Registry<Biome>, Long, FrozenBiomeProvider> biomeSource;

        public Preset(ResourceLocation id, Function3<FrozenBiomeProvider.Preset, Registry<Biome>, Long, FrozenBiomeProvider> frozenProviderFunction) {
            this.name = id;
            this.biomeSource = frozenProviderFunction;
            BY_NAME.put(id, this);
        }

        public FrozenBiomeProvider biomeSource(Registry<Biome> lookupRegistry, long seed) {
            return this.biomeSource.apply(this, lookupRegistry, seed);
        }
    }
}
