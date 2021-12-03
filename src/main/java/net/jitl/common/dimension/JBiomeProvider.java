package net.jitl.common.dimension;

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
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class JBiomeProvider extends BiomeProvider {

    public static final MapCodec<JBiomeProvider> DIRECT_CODEC = RecordCodecBuilder.mapCodec((builder) ->
            builder.group(Codec.LONG.fieldOf("seed").forGetter((baseProvider6) ->
                    baseProvider6.seed), RecordCodecBuilder.<Pair<Biome.Attributes, Supplier<Biome>>>create((biomeAttributes1) ->
                    biomeAttributes1.group(Biome.Attributes.CODEC.fieldOf("parameters").forGetter(Pair::getFirst), Biome.CODEC.fieldOf("biome").forGetter(Pair::getSecond))
                            .apply(biomeAttributes1, Pair::of)).listOf().fieldOf("biomes").forGetter((baseProvider5) ->
                    baseProvider5.parameters), Noise.CODEC.fieldOf("temperature_noise").forGetter((baseProvider4) ->
                    baseProvider4.temperatureParams), Noise.CODEC.fieldOf("humidity_noise").forGetter((baseProvider3) ->
                    baseProvider3.humidityParams), Noise.CODEC.fieldOf("altitude_noise").forGetter((baseProvider2) ->
                    baseProvider2.altitudeParams), Noise.CODEC.fieldOf("weirdness_noise").forGetter((baseProvider1) ->
                    baseProvider1.weirdnessParams)).apply(builder, JBiomeProvider::new));
    public static final Codec<JBiomeProvider> CODEC = Codec.mapEither(JBiomeProvider.DefaultBuilder.CODEC, DIRECT_CODEC).xmap((either) ->
            either.map(DefaultBuilder::biomeSource, Function.identity()), (baseProvider) ->
            baseProvider.preset().map(Either::<DefaultBuilder, JBiomeProvider>left).orElseGet(() -> {
                return Either.right(baseProvider);
            })).codec();
    private final JBiomeProvider.Noise temperatureParams;
    private final JBiomeProvider.Noise humidityParams;
    private final JBiomeProvider.Noise altitudeParams;
    private final JBiomeProvider.Noise weirdnessParams;
    private final MaxMinNoiseMixer temperatureNoise;
    private final MaxMinNoiseMixer humidityNoise;
    private final MaxMinNoiseMixer altitudeNoise;
    private final MaxMinNoiseMixer weirdnessNoise;
    private final List<Pair<Biome.Attributes, Supplier<Biome>>> parameters;
    private final boolean useY;
    private final long seed;
    private final Optional<Pair<Registry<Biome>, JBiomeProvider.Preset>> preset;

    private JBiomeProvider(long seed, List<Pair<Biome.Attributes, Supplier<Biome>>> biomeAttributes, JBiomeProvider.Noise temperatureNoise, JBiomeProvider.Noise humidityNoise, JBiomeProvider.Noise altitudeNoise, JBiomeProvider.Noise weirdnessNoise) {
        this(seed, biomeAttributes, temperatureNoise, humidityNoise, altitudeNoise, weirdnessNoise, Optional.empty());
    }

    private JBiomeProvider(long seed, List<Pair<Biome.Attributes, Supplier<Biome>>> biomeAttributes, JBiomeProvider.Noise temperatureNoise, JBiomeProvider.Noise humidityNoise, JBiomeProvider.Noise altitudeNoise, JBiomeProvider.Noise weirdnessNoise, Optional<Pair<Registry<Biome>, JBiomeProvider.Preset>> baseProviderPreset) {
        super(biomeAttributes.stream().map(Pair::getSecond));
        this.seed = seed;
        this.preset = baseProviderPreset;
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

    @Override
    protected @NotNull Codec<? extends BiomeProvider> codec() {
        return CODEC;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public @NotNull BiomeProvider withSeed(long seed) {
        return new JBiomeProvider(seed, this.parameters, this.temperatureParams, this.humidityParams, this.altitudeParams, this.weirdnessParams, this.preset);
    }

    private Optional<JBiomeProvider.DefaultBuilder> preset() {
        return this.preset.map((registryPresetPair) -> new DefaultBuilder(registryPresetPair.getSecond(), registryPresetPair.getFirst(), this.seed));
    }

    @Override
    public @NotNull Biome getNoiseBiome(int x, int y, int z) {
        int i = this.useY ? y : 0;
        Biome.Attributes biome$attributes = new Biome.Attributes((float) this.temperatureNoise.getValue(x, i, z), (float) this.humidityNoise.getValue(x, i, z), (float) this.altitudeNoise.getValue(x, i, z), (float) this.weirdnessNoise.getValue(x, i, z), 0.0F);
        return this.parameters.stream().min(Comparator.comparing((attributeBiomePair) ->
                attributeBiomePair.getFirst().fitness(biome$attributes))).map(Pair::getSecond).map(Supplier::get).orElse(BiomeRegistry.THE_VOID);
    }

    static final class DefaultBuilder {
        public static final MapCodec<JBiomeProvider.DefaultBuilder> CODEC = RecordCodecBuilder.mapCodec((builder) ->
                builder.group(ResourceLocation.CODEC.flatXmap((id) ->
                        Optional.ofNullable(Preset.BY_NAME.get(id)).map(DataResult::success).orElseGet(() ->
                                DataResult.error("Unknown preset: " + id)), (preset1) ->
                        DataResult.success(preset1.name)).fieldOf("preset").stable().forGetter(DefaultBuilder::preset), RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter(DefaultBuilder::biomes), Codec.LONG.fieldOf("seed").stable().forGetter(DefaultBuilder::seed)).apply(builder, builder.stable(DefaultBuilder::new)));
        private final JBiomeProvider.Preset preset;
        private final Registry<Biome> biomes;
        private final long seed;

        private DefaultBuilder(JBiomeProvider.Preset preset, Registry<Biome> lookupRegistry, long seed) {
            this.preset = preset;
            this.biomes = lookupRegistry;
            this.seed = seed;
        }

        public JBiomeProvider.Preset preset() {
            return this.preset;
        }

        public Registry<Biome> biomes() {
            return this.biomes;
        }

        public long seed() {
            return this.seed;
        }

        public JBiomeProvider biomeSource() {
            return this.preset.biomeSource(this.biomes, this.seed);
        }
    }

    static class Noise {
        private final int firstOctave;
        private final DoubleList amplitudes;
        public static final Codec<JBiomeProvider.Noise> CODEC = RecordCodecBuilder.create((builder) ->
                builder.group(Codec.INT.fieldOf("firstOctave").forGetter(Noise::firstOctave), Codec.DOUBLE.listOf().fieldOf("amplitudes").forGetter(Noise::amplitudes)).apply(builder, Noise::new));

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
        private static final Map<ResourceLocation, JBiomeProvider.Preset> BY_NAME = Maps.newHashMap();
        private final ResourceLocation name;
        private final Function3<JBiomeProvider.Preset, Registry<Biome>, Long, JBiomeProvider> biomeSource;

        public Preset(ResourceLocation id, Function3<JBiomeProvider.Preset, Registry<Biome>, Long, JBiomeProvider> baseProviderFunction) {
            this.name = id;
            this.biomeSource = baseProviderFunction;
            BY_NAME.put(id, this);
        }

        public JBiomeProvider biomeSource(Registry<Biome> lookupRegistry, long seed) {
            return this.biomeSource.apply(this, lookupRegistry, seed);
        }
    }
}
