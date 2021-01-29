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
import net.jitl.init.JourneyBiomeRegistry;
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

public class EucaBiomeProvider extends BiomeProvider {

    private static final EucaBiomeProvider.Noise DEFAULT_NOISE_PARAMETERS = new EucaBiomeProvider.Noise(-7, ImmutableList.of(1.0D, 1.0D));
    public static final MapCodec<EucaBiomeProvider> DIRECT_CODEC = RecordCodecBuilder.mapCodec((builder) -> {
        return builder.group(Codec.LONG.fieldOf("seed").forGetter((eucaProvider6) -> {
            return eucaProvider6.seed;
        }), RecordCodecBuilder.<Pair<Biome.Attributes, Supplier<Biome>>>create((biomeAttributes1) -> {
            return biomeAttributes1.group(Biome.Attributes.CODEC.fieldOf("parameters").forGetter(Pair::getFirst), Biome.CODEC.fieldOf("biome").forGetter(Pair::getSecond)).apply(biomeAttributes1, Pair::of);
        }).listOf().fieldOf("biomes").forGetter((eucaProvider5) -> {
            return eucaProvider5.parameters;
        }), EucaBiomeProvider.Noise.CODEC.fieldOf("temperature_noise").forGetter((eucaProvider4) -> {
            return eucaProvider4.temperatureParams;
        }), EucaBiomeProvider.Noise.CODEC.fieldOf("humidity_noise").forGetter((eucaProvider3) -> {
            return eucaProvider3.humidityParams;
        }), EucaBiomeProvider.Noise.CODEC.fieldOf("altitude_noise").forGetter((eucaProvider2) -> {
            return eucaProvider2.altitudeParams;
        }), EucaBiomeProvider.Noise.CODEC.fieldOf("weirdness_noise").forGetter((eucaProvider1) -> {
            return eucaProvider1.weirdnessParams;
        })).apply(builder, EucaBiomeProvider::new);
    });
    public static final Codec<EucaBiomeProvider> CODEC = Codec.mapEither(EucaBiomeProvider.DefaultBuilder.CODEC, DIRECT_CODEC).xmap((either) -> {
        return either.map(EucaBiomeProvider.DefaultBuilder::biomeSource, Function.identity());
    }, (eucaProvider) -> {
        return eucaProvider.preset().map(Either::<EucaBiomeProvider.DefaultBuilder, EucaBiomeProvider>left).orElseGet(() -> {
            return Either.right(eucaProvider);
        });
    }).codec();
    private final EucaBiomeProvider.Noise temperatureParams;
    private final EucaBiomeProvider.Noise humidityParams;
    private final EucaBiomeProvider.Noise altitudeParams;
    private final EucaBiomeProvider.Noise weirdnessParams;
    private final MaxMinNoiseMixer temperatureNoise;
    private final MaxMinNoiseMixer humidityNoise;
    private final MaxMinNoiseMixer altitudeNoise;
    private final MaxMinNoiseMixer weirdnessNoise;
    private final List<Pair<Biome.Attributes, Supplier<Biome>>> parameters;
    private final boolean useY;
    private final long seed;
    private final Optional<Pair<Registry<Biome>, EucaBiomeProvider.Preset>> preset;

    private EucaBiomeProvider(long seed, List<Pair<Biome.Attributes, Supplier<Biome>>> biomeAttributes, Optional<Pair<Registry<Biome>, EucaBiomeProvider.Preset>> eucaProviderPreset) {
        this(seed, biomeAttributes, DEFAULT_NOISE_PARAMETERS, DEFAULT_NOISE_PARAMETERS, DEFAULT_NOISE_PARAMETERS, DEFAULT_NOISE_PARAMETERS, eucaProviderPreset);
    }

    private EucaBiomeProvider(long seed, List<Pair<Biome.Attributes, Supplier<Biome>>> biomeAttributes, EucaBiomeProvider.Noise temperatureNoise, EucaBiomeProvider.Noise humidityNoise, EucaBiomeProvider.Noise altitudeNoise, EucaBiomeProvider.Noise weirdnessNoise) {
        this(seed, biomeAttributes, temperatureNoise, humidityNoise, altitudeNoise, weirdnessNoise, Optional.empty());
    }

    private EucaBiomeProvider(long seed, List<Pair<Biome.Attributes, Supplier<Biome>>> biomeAttributes, EucaBiomeProvider.Noise temperatureNoise, EucaBiomeProvider.Noise humidityNoise, EucaBiomeProvider.Noise altitudeNoise, EucaBiomeProvider.Noise weirdnessNoise, Optional<Pair<Registry<Biome>, EucaBiomeProvider.Preset>> eucaProviderPreset) {
        super(biomeAttributes.stream().map(Pair::getSecond));
        this.seed = seed;
        this.preset = eucaProviderPreset;
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
        return new EucaBiomeProvider(seed, this.parameters, this.temperatureParams, this.humidityParams, this.altitudeParams, this.weirdnessParams, this.preset);
    }

    private Optional<EucaBiomeProvider.DefaultBuilder> preset() {
        return this.preset.map((registryPresetPair) -> {
            return new EucaBiomeProvider.DefaultBuilder(registryPresetPair.getSecond(), registryPresetPair.getFirst(), this.seed);
        });
    }

    public Biome getNoiseBiome(int x, int y, int z) {
        int i = this.useY ? y : 0;
        Biome.Attributes biome$attributes = new Biome.Attributes((float)this.temperatureNoise.getValue((double)x, (double)i, (double)z), (float)this.humidityNoise.getValue((double)x, (double)i, (double)z), (float)this.altitudeNoise.getValue((double)x, (double)i, (double)z), (float)this.weirdnessNoise.getValue((double)x, (double)i, (double)z), 0.0F);
        return this.parameters.stream().min(Comparator.comparing((attributeBiomePair) -> {
            return attributeBiomePair.getFirst().fitness(biome$attributes);
        })).map(Pair::getSecond).map(Supplier::get).orElse(BiomeRegistry.THE_VOID);
    }

    public boolean stable(long seed) {
        return this.seed == seed && this.preset.isPresent() && Objects.equals(this.preset.get().getSecond(), Preset.EUCA);
    }

    static final class DefaultBuilder {
        public static final MapCodec<EucaBiomeProvider.DefaultBuilder> CODEC = RecordCodecBuilder.mapCodec((builder) -> {
            return builder.group(ResourceLocation.CODEC.flatXmap((id) -> {
                return Optional.ofNullable(EucaBiomeProvider.Preset.BY_NAME.get(id)).map(DataResult::success).orElseGet(() -> {
                    return DataResult.error("Unknown preset: " + id);
                });
            }, (preset1) -> {
                return DataResult.success(preset1.name);
            }).fieldOf("preset").stable().forGetter(EucaBiomeProvider.DefaultBuilder::preset), RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter(EucaBiomeProvider.DefaultBuilder::biomes), Codec.LONG.fieldOf("seed").stable().forGetter(EucaBiomeProvider.DefaultBuilder::seed)).apply(builder, builder.stable(EucaBiomeProvider.DefaultBuilder::new));
        });
        private final EucaBiomeProvider.Preset preset;
        private final Registry<Biome> biomes;
        private final long seed;

        private DefaultBuilder(EucaBiomeProvider.Preset preset, Registry<Biome> lookupRegistry, long seed) {
            this.preset = preset;
            this.biomes = lookupRegistry;
            this.seed = seed;
        }

        public EucaBiomeProvider.Preset preset() {
            return this.preset;
        }

        public Registry<Biome> biomes() {
            return this.biomes;
        }

        public long seed() {
            return this.seed;
        }

        public EucaBiomeProvider biomeSource() {
            return this.preset.biomeSource(this.biomes, this.seed);
        }
    }

    static class Noise {
        private final int firstOctave;
        private final DoubleList amplitudes;
        public static final Codec<EucaBiomeProvider.Noise> CODEC = RecordCodecBuilder.create((builder) -> {
            return builder.group(Codec.INT.fieldOf("firstOctave").forGetter(EucaBiomeProvider.Noise::firstOctave), Codec.DOUBLE.listOf().fieldOf("amplitudes").forGetter(EucaBiomeProvider.Noise::amplitudes)).apply(builder, EucaBiomeProvider.Noise::new);
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
        private static final Map<ResourceLocation, EucaBiomeProvider.Preset> BY_NAME = Maps.newHashMap();
        public static final EucaBiomeProvider.Preset EUCA = new EucaBiomeProvider.Preset(JITL.rl("euca"), (preset, lookupRegistry1, seed1) -> {
            return new EucaBiomeProvider(seed1, ImmutableList.of(Pair.of(new Biome.Attributes(0.0F, 0.0F, 0.0F, 0.0F, 0.0F), () -> {
                return lookupRegistry1.get(JourneyBiomeRegistry.EUCA_PLAINS.getRegistryName());
            }), Pair.of(new Biome.Attributes(0.0F, 0.0F, 0.0F, 0.0F, 0.0F), () -> {
                return lookupRegistry1.get(JourneyBiomeRegistry.EUCA_SILVER_PLAINS.getRegistryName());
            })), Optional.of(Pair.of(lookupRegistry1, preset)));
        });
        private final ResourceLocation name;
        private final Function3<EucaBiomeProvider.Preset, Registry<Biome>, Long, EucaBiomeProvider> biomeSource;

        public Preset(ResourceLocation id, Function3<EucaBiomeProvider.Preset, Registry<Biome>, Long, EucaBiomeProvider> eucaProviderFunction) {
            this.name = id;
            this.biomeSource = eucaProviderFunction;
            BY_NAME.put(id, this);
        }

        public EucaBiomeProvider biomeSource(Registry<Biome> lookupRegistry, long seed) {
            return this.biomeSource.apply(this, lookupRegistry, seed);
        }
    }
}
