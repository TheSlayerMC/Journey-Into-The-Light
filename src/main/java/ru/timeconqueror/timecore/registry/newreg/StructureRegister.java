package ru.timeconqueror.timecore.registry.newreg;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import net.jitl.JITL;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class StructureRegister extends ForgeRegister<Structure<?>> {
    private final List<StructureInfo<?, ?>> structureInfoList = Collections.synchronizedList(new ArrayList<>());

    public StructureRegister(String modid) {
        super(ForgeRegistries.STRUCTURE_FEATURES, modid);
    }

    /**
     * Adds entry in provided {@code structureSup} to the queue, all entries from which will be registered later.
     * <p>
     * This method also returns {@link StructureRegisterChain} to provide extra methods, which you can apply to entry being registered.
     * All methods of {@link StructureRegisterChain} are optional.
     *
     * @param name               The block's name, will automatically have the modid as a namespace.
     * @param structureSup       A factory for the new structure, it should return a new instance every time it is called.
     * @param separationSettings separation settings for provided structure
     * @param configCodec        codec for provided structure
     * @param featureConfig      config for feature representation of this structure
     * @return A {@link StructureRegisterChain} for adding some extra stuff.
     * @see StructureRegisterChain
     */
    public <T extends IFeatureConfig, S extends Structure<T>> StructureRegisterChain<T, S> register(String name, Function<Codec<T>, S> structureSup, TimeStructureSeparationSettings separationSettings, Codec<T> configCodec, T featureConfig) {
        RegistryObject<S> holder = this.registerEntry(name, () -> structureSup.apply(configCodec));
        return new StructureRegisterChain<>(holder, separationSettings.toVanilla(holder.getId()), featureConfig);
    }

    /**
     * Adds entry in provided {@code structureSup} to the queue, all entries from which will be registered later.
     * <p>
     * This method also returns {@link StructureRegisterChain} to provide extra methods, which you can apply to entry being registered.
     * All methods of {@link StructureRegisterChain} are optional.
     *
     * @param name               The block's name, will automatically have the modid as a namespace.
     * @param structureSup       A factory for the new structure, it should return a new instance every time it is called.
     * @param separationSettings separation settings for provided structure
     * @param configCodec        codec for provided structure
     * @param featureConfig      config for feature representation of this structure
     * @return A {@link StructureRegisterChain} for adding some extra stuff.
     * @see StructureRegisterChain
     */
    public <T extends IFeatureConfig, S extends Structure<T>> StructureRegisterChain<T, S> register(String name, Function<Codec<T>, S> structureSup, StructureSeparationSettings separationSettings, Codec<T> configCodec, T featureConfig) {
        RegistryObject<S> holder = this.registerEntry(name, () -> structureSup.apply(configCodec));
        return new StructureRegisterChain<>(holder, separationSettings, featureConfig);
    }

    @Override
    public void regToBus(IEventBus bus) {
        super.regToBus(bus);

        bus.addListener(this::onCommonSetup);

        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        forgeBus.addListener(this::onBiomeLoad);
        forgeBus.addListener(this::onWorldLoad);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            structureInfoList.forEach(info -> {
                Structure<?> structure = info.structure();

                /*
                 * We need to add our structures into the map in Structure alongside vanilla
                 * structures or else it will cause errors. Called by registerStructure.
                 */
                Structure.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);

                if (info.props().transformSurroundingLand) {
                    Structure.NOISE_AFFECTING_FEATURES =
                            ImmutableList.<Structure<?>>builder()
                                    .addAll(Structure.NOISE_AFFECTING_FEATURES)
                                    .add(structure)
                                    .build(); // too heavy process, rewrite
                }
            });

            /*
             * Adds the structure's spacing into several places so that the structure's spacing remains
             * correct in any dimension or worldtype instead of not spawning.
             *
             * However, it seems it doesn't always work for code made dimensions as they read from
             * this list beforehand. Use the WorldEvent.Load event in StructureTutorialMain to add
             * the structure spacing from this list into that dimension.
             */
            DimensionStructuresSettings.DEFAULTS =
                    ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                            .putAll(DimensionStructuresSettings.DEFAULTS)
                            .putAll(structureInfoList.stream()
                                    .collect(Collectors.<StructureInfo<?, ?>, Structure<?>, StructureSeparationSettings>
                                            toMap(StructureInfo::structure, structureInfo -> structureInfo.separationSettings
                                    )))
                            .build();

            Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;

            structureInfoList.forEach(structureInfo -> {
                structureInfo.setFeatureReadyToLoad();
                Registry.register(registry, new ResourceLocation(JITL.MODID, "configured_" + structureInfo.regObject().getId()), structureInfo.getFeature());

                // Ok so, this part may be hard to grasp but basically, just add your structure to this to
                // prevent any sort of crash or issue with other mod's custom ChunkGenerators. If they use
                // FlatGenerationSettings.STRUCTURES in it and you don't add your structure to it, the game
                // could crash later when you attempt to add the StructureSeparationSettings to the dimension.
                //
                // (It would also crash with superflat worldtype if you omit the below line
                //  and attempt to add the structure's StructureSeparationSettings to the world)
                //
                // Note: If you want your structure to spawn in superflat, remove the FlatChunkGenerator check
                // in StructureTutorialMain.addDimensionalSpacing and then create a superflat world, exit it,
                // and re-enter it and your structures will be spawning. I could not figure out why it needs
                // the restart but honestly, superflat is really buggy and shouldn't be your main focus in my opinion.
                FlatGenerationSettings.STRUCTURE_FEATURES.put(structureInfo.structure(), structureInfo.getFeature());
            });
        });
    }

    /**
     * Here, we will use this to add our structure to all biomes.
     */
    private void onBiomeLoad(BiomeLoadingEvent event) {
        // Add our structure to all biomes including other modded biomes.
        // You can filter to certain biomes based on stuff like temperature, scale, precipitation, mod id.
        structureInfoList.forEach(structureInfo -> {
            if (structureInfo.props().biomePredicate.test(event)) {
                event.getGeneration().getStructures().add(structureInfo::getFeature);
            }
        });
    }

    /**
     * Will go into the world's chunkgenerator and manually add our structure spacing.
     * If the spacing is not added, the structure doesn't spawn.
     * <p>
     * Used for dimension blacklists for your structures.
     * (Don't forget to attempt to remove your structure too from
     * the map if you are blacklisting that dimension! It might have
     * your structure in it already.)
     * <p>
     * Basically use this to mark absolutely sure the chunkgeneration
     * can or cannot spawn your structure.
     */
    private void onWorldLoad(WorldEvent.Load event) {
        if (event.getWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) event.getWorld();

            ChunkGenerator generator = serverWorld.getChunkSource().getGenerator();

            // Prevent spawning our structure in Vanilla's superflat world as
            // people seem to want their superflat worlds free of modded structures.
            // Also that vanilla superflat is really tricky and buggy to work with in my experience.
            if (generator instanceof FlatChunkGenerator &&
                    serverWorld.dimension().equals(World.OVERWORLD)) {
                return;
            }

            DimensionStructuresSettings settings = generator.getSettings();

            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(settings.structureConfig());

            structureInfoList.forEach(structureInfo -> {
                if (structureInfo.props().dimensionPredicate.test(serverWorld)) {
                    tempMap.put(structureInfo.structure(), DimensionStructuresSettings.DEFAULTS.get(structureInfo.structure()));
                }
            });

            settings.structureConfig = tempMap;
        }
    }

    public class StructureRegisterChain<T extends IFeatureConfig, S extends Structure<T>> extends RegisterChain<S> {
        private final StructureHolder<T, S> holder;
        private final StructureInfo<T, S> info;

        protected StructureRegisterChain(RegistryObject<S> regObj, StructureSeparationSettings separationSettings, T featureConfig) {
            super(StructureRegister.this, regObj);

            this.info = new StructureInfo<>(regObj, separationSettings, s -> new StructureFeature<>(s, featureConfig));
            this.holder = new StructureHolder<>(regObj, info::getFeature);

            structureInfoList.add(info);
        }

        /**
         * Will add land at the base of the structure like it does for Villages and Outposts.
         * Doesn't work well on structure that have pieces stacked vertically or change in heights.
         */
        public StructureRegisterChain<T, S> transformsSurroundingLand() {
            this.info.props().transformSurroundingLand = true;
            return this;
        }

        /**
         * Controls, if feature can be generated in provided world.
         * By default it will be registered for all dimensions.
         */
        public StructureRegisterChain<T, S> setDimensionPredicate(Predicate<ServerWorld> worldPredicate) {
            this.info.props().dimensionPredicate = worldPredicate;
            return this;
        }

        /**
         * Controls, if feature can be generated in provided biome.
         * By default it will be registered for all biomes.
         */
        public StructureRegisterChain<T, S> setBiomePredicate(Predicate<BiomeLoadingEvent> biomePredicate) {
            this.info.props().biomePredicate = biomePredicate;
            return this;
        }

        /**
         * Returns holder, from which you can get both structure and structure feature.
         * Keep in mind, that structure feature will this registry name: "${your_mod_id}:configured_${structure_name}".
         * Example: "best_mod:configured_my_test_structure".
         */
        public StructureHolder<T, S> asHolder() {
            return holder;
        }
    }

    /**
     * Used for auto-generation of structure salt depending on hashcode of its registry key
     */
    public static class TimeStructureSeparationSettings {
        private final int spacing;
        private final int separation;

        private TimeStructureSeparationSettings(int spacing, int separation) {
            this.spacing = spacing;
            this.separation = separation;
        }

        /**
         * Creates new instance of TimeStructureSeparationSettings
         *
         * @param spacing    maximum distance apart in chunks between spawn attempts
         * @param separation minimum distance apart in chunks between spawn attempts
         */
        public static TimeStructureSeparationSettings create(int spacing, int separation) {
            if (spacing < separation) throw new IllegalArgumentException("Spacing can't be less than separation. ");
            return new TimeStructureSeparationSettings(spacing, separation);
        }

        private StructureSeparationSettings toVanilla(ResourceLocation structureName) {
            return new StructureSeparationSettings(spacing, separation, structureName.hashCode());
        }
    }

    public static class StructureHolder<T extends IFeatureConfig, S extends Structure<T>> {
        private final RegistryObject<S> registryObject;
        private final Supplier<StructureFeature<T, S>> structureFeature;

        public StructureHolder(RegistryObject<S> registryObject, Supplier<StructureFeature<T, S>> structureFeature) {
            this.registryObject = registryObject;
            this.structureFeature = structureFeature;
        }

        public RegistryObject<S> getRegistryObject() {
            return registryObject;
        }

        public S getStructure() {
            return registryObject.get();
        }

        public StructureFeature<T, S> getFeature() {
            return structureFeature.get();
        }
    }

    public static class StructureInfo<T extends IFeatureConfig, S extends Structure<T>> {
        private final RegistryObject<S> registryObject;
        private final Properties properties = new Properties();
        private final Lazy<StructureFeature<T, S>> feature;
        private boolean featureReadyToLoad;
        private final StructureSeparationSettings separationSettings;

        public StructureInfo(RegistryObject<S> registryObject, StructureSeparationSettings separationSettings, Function<S, StructureFeature<T, S>> structureFeatureFactory) {
            this.registryObject = registryObject;
            this.separationSettings = separationSettings;
            this.feature = () -> structureFeatureFactory.apply(registryObject.get());
        }

        public RegistryObject<S> regObject() {
            return registryObject;
        }

        public S structure() {
            return registryObject.get();
        }

        public Properties props() {
            return properties;
        }

        private void setFeatureReadyToLoad() {
            this.featureReadyToLoad = true;
        }

        public StructureFeature<T, S> getFeature() {
            if (!featureReadyToLoad) throw new IllegalStateException("Structure Feature hasn't been loaded yet");
            return feature.get();
        }
    }

    public static class Properties {
        /**
         * Will add land at the base of the structure like it does for Villages and Outposts.
         * Doesn't work well on structure that have pieces stacked vertically or change in heights.
         */
        private boolean transformSurroundingLand;

        private Predicate<BiomeLoadingEvent> biomePredicate = event -> true;
        private Predicate<ServerWorld> dimensionPredicate = event -> true;
    }
}
