package net.jitl.init;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.jitl.JITL;
import net.jitl.common.block.base.JBerryBushBlock;
import net.jitl.common.world.gen.features.featureconfig.EucaSpawnerFeatureConfig;
import net.jitl.common.world.gen.features.featureconfig.JBaseTreeFeatureConfig;
import net.jitl.common.world.gen.features.featureconfig.RuinsFeatureConfig;
import net.jitl.common.world.gen.foliageplacer.SphericalFoliagePlacer;
import net.jitl.common.world.gen.treedecorator.*;
import net.jitl.util.JRuleTests;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.ai.behavior.ShufflingList;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.PineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import ru.timeconqueror.timecore.api.registry.SimpleVanillaRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;
import ru.timeconqueror.timecore.api.registry.util.Promised;
import ru.timeconqueror.timecore.storage.Features;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SuppressWarnings("Convert2MethodRef")
public class JConfiguredFeatures {
    @AutoRegistrable
    private static final SimpleVanillaRegister<ConfiguredFeature<?, ?>> REGISTER = new SimpleVanillaRegister<ConfiguredFeature<?, ?>>(JITL.MODID, BuiltinRegistries.CONFIGURED_FEATURE);

    public static final Predicate<BiomeLoadingEvent> IN_NETHER = event -> event.getCategory() == Biome.BiomeCategory.NETHER;
    public static final Predicate<BiomeLoadingEvent> IN_END = event -> event.getCategory() == Biome.BiomeCategory.THEEND;
    public static final Predicate<BiomeLoadingEvent> IN_WARPED_FOREST = event -> Objects.equals(event.getName(), new ResourceLocation("warped_forest"));
    public static final Predicate<BiomeLoadingEvent> IN_CRIMSON_FOREST = event -> Objects.equals(event.getName(), new ResourceLocation("crimson_forest"));

    public static final Predicate<BiomeLoadingEvent> GOLDITE_GRAINS = event -> Objects.equals(event.getName(), JITL.rl("euca/euca_goldite_grains"));
    public static final Predicate<BiomeLoadingEvent> EUCA_GOLD_PLAINS = event -> Objects.equals(event.getName(), JITL.rl("euca/euca_plains"));
    public static final Predicate<BiomeLoadingEvent> EUCA_SILVER_PLAINS = event -> Objects.equals(event.getName(), JITL.rl("euca/euca_silver_plains"));

    public static final Predicate<BiomeLoadingEvent> FROZEN_WASTES = event -> Objects.equals(event.getName(), JITL.rl("frozen/frozen_wastes"));
    public static final Predicate<BiomeLoadingEvent> FROZEN_DYING_FORST = event -> Objects.equals(event.getName(), JITL.rl("frozen/dying_forest"));
    public static final Predicate<BiomeLoadingEvent> FROZEN_BITTERWOOD_FORST = event -> Objects.equals(event.getName(), JITL.rl("frozen/bitterwood_forest"));

    public static final Predicate<BiomeLoadingEvent> SCORCHED_WASTELAND = event -> Objects.equals(event.getName(), JITL.rl("boil/scorched_wastelands"));
    public static final Predicate<BiomeLoadingEvent> BOILING_SANDS = event -> Objects.equals(event.getName(), JITL.rl("boil/boiling_sands"));
    public static final Predicate<BiomeLoadingEvent> CHARRED_FIELDS = event -> Objects.equals(event.getName(), JITL.rl("boil/charred_fields"));
    public static final Predicate<BiomeLoadingEvent> BOILING_PLAINS = event -> Objects.equals(event.getName(), JITL.rl("boil/boil"));

    public static final Predicate<BiomeLoadingEvent> EUCA_BIOMES = GOLDITE_GRAINS.or(EUCA_GOLD_PLAINS).or(EUCA_SILVER_PLAINS);
    public static final Predicate<BiomeLoadingEvent> BOIL_FIRE_BIOMES = SCORCHED_WASTELAND.or(CHARRED_FIELDS).or(BOILING_PLAINS).or(BOILING_SANDS);
    public static final Predicate<BiomeLoadingEvent> FROZEN_BIOMES = FROZEN_WASTES.or(FROZEN_DYING_FORST).or(FROZEN_BITTERWOOD_FORST);

    public static final Predicate<BiomeLoadingEvent> COMMON_BIOMES = IN_NETHER.and(IN_END).negate();

    public static final Promised<? extends ConfiguredFeature<?, ?>> BRADBERRY_BUSH =
            REGISTER.register("patch_bradberry_bush", Decoration.VEGETAL_DECORATION, surfacePatchFeature(
                    () -> JBlocks.BRADBERRY_BUSH.defaultBlockState(),
                    () -> Blocks.GRASS_BLOCK.defaultBlockState(),
                    8))
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> DEFAULT_OVERWORLD_RUINS =
            REGISTER.register("default_overworld_ruins",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.RUINS.get()
                            .configured(new RuinsFeatureConfig(
                                    JRuleTests.GRASS,
                                    new WeightedStateProvider()
                                            .add(Blocks.STONE_BRICKS.defaultBlockState(), 6)
                                            .add(Blocks.CRACKED_STONE_BRICKS.defaultBlockState(), 5)
                                            .add(Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 4)
                                            .add(Blocks.MOSSY_COBBLESTONE.defaultBlockState(), 3)
                                            .add(Blocks.COBBLESTONE.defaultBlockState(), 4)
                                            .add(Blocks.CHISELED_STONE_BRICKS.defaultBlockState(), 2)
                                            .add(Blocks.INFESTED_COBBLESTONE.defaultBlockState(), 1)
                                            .add(Blocks.INFESTED_STONE_BRICKS.defaultBlockState(), 1)
                                            .add(Blocks.INFESTED_MOSSY_STONE_BRICKS.defaultBlockState(), 1)
                                            .add(Blocks.INFESTED_CRACKED_STONE_BRICKS.defaultBlockState(), 1),
                                    5,
                                    5,
                                    8,
                                    new ShufflingList.WeightedEntry<ResourceLocation>()
                                            .add(BuiltInLootTables.ABANDONED_MINESHAFT, 5)
                                            .add(BuiltInLootTables.SIMPLE_DUNGEON, 6)
                                            .add(BuiltInLootTables.STRONGHOLD_CORRIDOR, 2)))
                            .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE)
                            .chance(128))
                    .setBiomePredicate(COMMON_BIOMES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> DESERT_OVERWORLD_RUINS =
            REGISTER.register("desert_overworld_ruins",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.RUINS.get()
                            .configured(new RuinsFeatureConfig(
                                    JRuleTests.SAND,
                                    new WeightedStateProvider()
                                            .add(Blocks.SANDSTONE.defaultBlockState(), 3)
                                            .add(Blocks.CHISELED_SANDSTONE.defaultBlockState(), 1)
                                            .add(Blocks.CUT_SANDSTONE.defaultBlockState(), 2),
                                    5,
                                    5,
                                    8,
                                    new WeightedList<ResourceLocation>()
                                            .add(BuiltInLootTables.DESERT_PYRAMID, 4)
                                            .add(BuiltInLootTables.VILLAGE_DESERT_HOUSE, 6)
                                            .add(BuiltInLootTables.STRONGHOLD_CORRIDOR, 2)))
                            .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE)
                            .chance(128))
                    .setBiomePredicate(COMMON_BIOMES)
                    .asPromise();



    public static final Promised<? extends ConfiguredFeature<?, ?>> GOLDITE_TALL_FOLIAGE =
            REGISTER.register("goldite_tall_foliage", Decoration.VEGETAL_DECORATION, () -> Feature.RANDOM_PATCH
                    .configured((new RandomPatchConfiguration.GrassConfigurationBuilder(
                            new WeightedStateProvider()
                                    .add(JBlocks.GOLDITE_TALL_GRASS.defaultBlockState(), 1),
                            new DoublePlantPlacer()))
                            .tries(24)
                            .xspread(8)
                            .zspread(8)
                            .whitelist(ImmutableSet.of(
                                    JBlocks.GOLDITE_GRASS_BLOCK))
                            .noProjection()
                            .build())
                    .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared()
                    .range(250)
                    .count(64))
                    .setBiomePredicate(GOLDITE_GRAINS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> GOLDITE_FOLIAGE =
            REGISTER.register("goldite_foliage", Decoration.VEGETAL_DECORATION, () -> Feature.RANDOM_PATCH
                    .configured((new RandomPatchConfiguration.GrassConfigurationBuilder(
                            new WeightedStateProvider()
                                    .add(JBlocks.GOLDITE_BULB.defaultBlockState(), 1),
                            new SimpleBlockPlacer()))
                            .tries(24)
                            .xspread(7)
                            .zspread(7)
                            .whitelist(ImmutableSet.of(
                                    JBlocks.GOLDITE_GRASS_BLOCK))
                            .noProjection()
                            .build())
                    .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared()
                    .range(250)
                    .count(16))
                    .setBiomePredicate(GOLDITE_GRAINS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> GOLDITE_STALKS =
            REGISTER.register("goldite_stalks", Decoration.VEGETAL_DECORATION, () -> Feature.RANDOM_PATCH
                    .configured((new RandomPatchConfiguration.GrassConfigurationBuilder(
                            new WeightedStateProvider()
                                    .add(JBlocks.GOLDITE_STALKS.defaultBlockState(), 1),
                            new SimpleBlockPlacer()))
                            .tries(24)
                            .xspread(10)
                            .zspread(10)
                            .whitelist(ImmutableSet.of(
                                    JBlocks.GOLDITE_GRASS_BLOCK))
                            .noProjection()
                            .build())
                    .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared()
                    .range(250)
                    .count(64))
                    .setBiomePredicate(GOLDITE_GRAINS)
                    .asPromise();


    public static final Promised<? extends ConfiguredFeature<?, ?>> SILVER_GOLD_FOLIAGE =
            REGISTER.register("euca_silver_gold_foliage", Decoration.VEGETAL_DECORATION, () -> Feature.RANDOM_PATCH
                    .configured((new RandomPatchConfiguration.GrassConfigurationBuilder(
                            new WeightedStateProvider()
                                    .add(JBlocks.EUCA_SILVER_GOLD_FLOWER.defaultBlockState(), 1),
                            new SimpleBlockPlacer()))
                            .tries(24)
                            .xspread(10)
                            .zspread(10)
                            .whitelist(ImmutableSet.of(
                                    JBlocks.EUCA_SILVER_GRASS_BLOCK, JBlocks.EUCA_GOLD_GRASS_BLOCK))
                            .noProjection()
                            .build())
                    .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared()
                    .range(250)
                    .count(64))
                    .setBiomePredicate(EUCA_SILVER_PLAINS.and(EUCA_GOLD_PLAINS))
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> EUCA_SILVER_FOLIAGE =
            REGISTER.register("euca_silver_foliage", Decoration.VEGETAL_DECORATION, () -> Feature.RANDOM_PATCH
                    .configured((new RandomPatchConfiguration.GrassConfigurationBuilder(
                            new WeightedStateProvider()
                                    .add(JBlocks.EUCA_SILVER_TALL_GRASS.defaultBlockState(), 1)
                                    .add(JBlocks.EUCA_SILVER_SPROUTS.defaultBlockState(), 1)
                                    .add(JBlocks.EUCA_SILVER_SHORT_GRASS.defaultBlockState(), 1),
                            new SimpleBlockPlacer()))
                            .tries(24)
                            .xspread(6)
                            .zspread(6)
                            .whitelist(ImmutableSet.of(
                                    JBlocks.EUCA_SILVER_GRASS_BLOCK))
                            .noProjection()
                            .build())
                    .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared()
                    .range(250)
                    .count(64))
                    .setBiomePredicate(EUCA_SILVER_PLAINS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> EUCA_GOLD_FOLIAGE =
            REGISTER.register("euca_gold_foliage", Decoration.VEGETAL_DECORATION, () -> Feature.RANDOM_PATCH
                    .configured((new RandomPatchConfiguration.GrassConfigurationBuilder(
                            new WeightedStateProvider()
                                    .add(JBlocks.EUCA_TALL_FLOWERS.defaultBlockState(), 1)
                                    .add(JBlocks.EUCA_TALL_GRASS.defaultBlockState(), 1),
                            new SimpleBlockPlacer()))
                            .tries(24)
                            .xspread(7)
                            .zspread(7)
                            .whitelist(ImmutableSet.of(
                                    JBlocks.EUCA_GOLD_GRASS_BLOCK))
                            .noProjection()
                            .build())
                    .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared()
                    .range(250)
                    .count(64))
                    .setBiomePredicate(EUCA_GOLD_PLAINS)
                    .asPromise();


    public static final Promised<? extends ConfiguredFeature<?, ?>> GOLDITE_GOLD_FOLIAGE =
            REGISTER.register("euca_goldite_gold_foliage", Decoration.VEGETAL_DECORATION, () -> Feature.RANDOM_PATCH
                    .configured((new RandomPatchConfiguration.GrassConfigurationBuilder(
                            new WeightedStateProvider()
                                    .add(JBlocks.GOLDITE_STALKS.defaultBlockState(), 1)
                                    .add(JBlocks.GOLDITE_FLOWER.defaultBlockState(), 1),
                            new SimpleBlockPlacer()))
                            .tries(24)
                            .xspread(7)
                            .zspread(7)
                            .whitelist(ImmutableSet.of(
                                    JBlocks.GOLDITE_GRASS_BLOCK, JBlocks.EUCA_GOLD_GRASS_BLOCK))
                            .noProjection()
                            .build())
                    .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared()
                    .range(250)
                    .count(64))
                    .setBiomePredicate(GOLDITE_GRAINS.and(EUCA_GOLD_PLAINS))
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> TALL_GLOWSHROOMS =
            REGISTER.register("tall_glowshrooms",
                            Decoration.UNDERGROUND_DECORATION,
                            () -> Feature.RANDOM_PATCH
                                    .configured((new RandomPatchConfiguration.GrassConfigurationBuilder(
                                            new WeightedStateProvider()
                                                    .add(JBlocks.TALL_GREEN_GLOWSHROOM.defaultBlockState(), 1)
                                                    .add(JBlocks.TALL_BLUE_GLOWSHROOM.defaultBlockState(), 1)
                                                    .add(JBlocks.TALL_RED_GLOWSHROOM.defaultBlockState(), 1),
                                            new DoublePlantPlacer()))
                                            .tries(64)
                                            .xspread(6)
                                            .zspread(6)
                                            .whitelist(ImmutableSet.of(
                                                    Blocks.STONE,
                                                    Blocks.COBBLESTONE,
                                                    Blocks.MOSSY_COBBLESTONE,
                                                    Blocks.ANDESITE,
                                                    Blocks.GRANITE,
                                                    Blocks.DIORITE))
                                            .noProjection()
                                            .build())
                                    .range(55)
                                    .count(1))
                    .setBiomePredicate(COMMON_BIOMES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> SMALL_GLOWSHROOMS =
            REGISTER.register("small_glowshrooms",
                    Decoration.UNDERGROUND_DECORATION,
                    () -> Feature.RANDOM_PATCH
                            .configured((new RandomPatchConfiguration.GrassConfigurationBuilder(
                                    new WeightedStateProvider()
                                            .add(JBlocks.GREEN_GLOWSHROOM.defaultBlockState(), 1)
                                            .add(JBlocks.BLUE_GLOWSHROOM.defaultBlockState(), 1)
                                            .add(JBlocks.RED_GLOWSHROOM.defaultBlockState(), 1),
                                    new SimpleBlockPlacer()))
                                    .tries(128)
                                    .xspread(6)
                                    .zspread(6)
                                    .whitelist(ImmutableSet.of(
                                            Blocks.STONE,
                                            Blocks.COBBLESTONE,
                                            Blocks.MOSSY_COBBLESTONE,
                                            Blocks.ANDESITE,
                                            Blocks.GRANITE,
                                            Blocks.DIORITE))
                                    .noProjection()
                                    .build())
                            .range(55)
                            .count(1))
                    .setBiomePredicate(COMMON_BIOMES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> GLOWING_FUNGI =
            REGISTER.register("glowing_fungi",
                            Decoration.UNDERGROUND_DECORATION,
                            () -> Feature.RANDOM_PATCH
                                    .configured((new RandomPatchConfiguration.GrassConfigurationBuilder(
                                            new WeightedStateProvider()
                                                    .add(JBlocks.TALL_FUNGI.defaultBlockState(), 1),
                                            new SimpleBlockPlacer()))
                                            .tries(256)
                                            .xspread(0)
                                            .yspread(0)
                                            .zspread(0)
                                            .whitelist(ImmutableSet.of(
                                                    Blocks.STONE,
                                                    Blocks.COBBLESTONE,
                                                    Blocks.MOSSY_COBBLESTONE,
                                                    Blocks.ANDESITE,
                                                    Blocks.GRANITE,
                                                    Blocks.DIORITE))
                                            .noProjection()
                                            .build())
                                    .range(55)
                                    .count(100))
                    .setBiomePredicate(COMMON_BIOMES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> CAVE_VINES =
            REGISTER.register("cave_vines",
                    Decoration.UNDERGROUND_STRUCTURES,
                    () -> JFeatures.CAVE_VINES.get()
                            .configured(FeatureConfiguration.NONE)
                            .range(60)
                            .squared()
                            .chance(1)
                            .countRandom(32))
                    .setBiomePredicate(COMMON_BIOMES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> GLIMMER_ROOTS =
            REGISTER.register("glimmer_roots",
                    Decoration.UNDERGROUND_STRUCTURES,
                    () -> JFeatures.GLIMMER_ROOTS.get()
                            .configured(FeatureConfiguration.NONE)
                            .range(256)
                            .squared()
                            .countRandom(64))
                    .setBiomePredicate(EUCA_BIOMES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> EUCA_GOLD_TREES =
            REGISTER.register("euca_gold_tree",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.BASE_TREE.get()
                            .configured(new JBaseTreeFeatureConfig.Builder(
                                    new SimpleStateProvider(JBlocks.EUCA_GOLD_LOG.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.EUCA_SILVER_LEAVES.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.EUCA_GOLD_GRASS_BLOCK.defaultBlockState()),
                                    new SphericalFoliagePlacer(UniformInt.fixed(3), UniformInt.fixed(3), 1),
                                    new ForkingTrunkPlacer(4, 1, 6),
                                    new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().build())
                            .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE)
                            .chance(1).countRandom(5))
                    .setBiomePredicate(EUCA_GOLD_PLAINS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> EUCA_SILVER_TREES =
            REGISTER.register("euca_silver_tree",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.BASE_TREE.get()
                            .configured(new JBaseTreeFeatureConfig.Builder(
                                    new SimpleStateProvider(JBlocks.EUCA_SILVER_LOG.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.EUCA_GOLD_LEAVES.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.EUCA_SILVER_GRASS_BLOCK.defaultBlockState()),
                                    new SphericalFoliagePlacer(UniformInt.fixed(3), UniformInt.fixed(3), 1),
                                    new ForkingTrunkPlacer(4, 1, 6),
                                    new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().build())
                            .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE)
                            .chance(1).countRandom(5))
                    .setBiomePredicate(EUCA_SILVER_PLAINS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> EUCA_GOLD_GOLD_TREES =
            REGISTER.register("euca_gold_gold_tree",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.BASE_TREE.get()
                            .configured(new JBaseTreeFeatureConfig.Builder(
                                    new SimpleStateProvider(JBlocks.EUCA_GOLD_LOG.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.EUCA_GREEN_LEAVES.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.GOLDITE_GRASS_BLOCK.defaultBlockState()),
                                    new SphericalFoliagePlacer(UniformInt.fixed(3), UniformInt.fixed(3), 1),
                                    new ForkingTrunkPlacer(4, 1, 6),
                                    new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().build())
                            .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE)
                            .chance(1).countRandom(5))
                    .setBiomePredicate(GOLDITE_GRAINS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> EUCA_GREEN_TREES =
            REGISTER.register("euca_green_tree",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.BASE_TREE.get()
                            .configured(new JBaseTreeFeatureConfig.Builder(
                                    new SimpleStateProvider(JBlocks.EUCA_BROWN_LOG.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.EUCA_GREEN_LEAVES.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.GOLDITE_GRASS_BLOCK.defaultBlockState()),
                                    new SphericalFoliagePlacer(UniformInt.fixed(3), UniformInt.fixed(3), 1),
                                    new ForkingTrunkPlacer(4, 1, 6),
                                    new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().build())
                            .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE)
                            .chance(1).countRandom(5))
                    .setBiomePredicate(GOLDITE_GRAINS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> EUCA_SILVERBOT_SAPWNER =
            REGISTER.register("euca_silverbot_spawner",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.EUCA_BOT_SPAWNER.get()
                            .configured(new EucaSpawnerFeatureConfig(
                                    JRuleTests.SILVER_GRASS_EUCA.get(),
                                    new SimpleStateProvider(JBlocks.SILVER_BOT_SPAWNER.defaultBlockState()),
                                    13,
                                    7))
                            .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE)
                            .chance(50))
                    .setBiomePredicate(EUCA_SILVER_PLAINS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> EUCA_GOLDBOT_SAPWNER =
            REGISTER.register("euca_goldbot_spawner",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.EUCA_BOT_SPAWNER.get()
                            .configured(new EucaSpawnerFeatureConfig(
                                    JRuleTests.GOLD_GRASS_EUCA.get(),
                                    new SimpleStateProvider(JBlocks.GOLD_BOT_SPAWNER.defaultBlockState()),
                                    13,
                                    7))
                            .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE)
                            .chance(50))
                    .setBiomePredicate(EUCA_GOLD_PLAINS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> SMALL_FROZEN_TREE =
            REGISTER.register("small_frozen_tree",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.BASE_TREE.get()
                            .configured(new JBaseTreeFeatureConfig.Builder(
                                    new SimpleStateProvider(JBlocks.FROZEN_LOG.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.FROZEN_LEAVES.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.GRASSY_PERMAFROST.defaultBlockState()),
                                    new PineFoliagePlacer(UniformInt.fixed(3), UniformInt.fixed(1), UniformInt.fixed(2)),
                                    new ForkingTrunkPlacer(2, 1, 3),
                                    new TwoLayersFeatureSize(1, 1, 2)).ignoreVines()
                                    .decorators(ImmutableList.of(
                                            IcyBrushTreeDecorator.INSTANCE,
                                            new IceShroomTreeDecorator(0.2F))).build())
                            .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared())
                    .setBiomePredicate(FROZEN_DYING_FORST)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> MEDIUM_FROZEN_TREE =
            REGISTER.register("medium_frozen_tree",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.BASE_TREE.get()
                            .configured(new JBaseTreeFeatureConfig.Builder(
                                    new SimpleStateProvider(JBlocks.FROZEN_LOG.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.FROZEN_LEAVES.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.GRASSY_PERMAFROST.defaultBlockState()),
                                    new PineFoliagePlacer(UniformInt.fixed(3), UniformInt.fixed(1), UniformInt.fixed(2)),
                                    new FancyTrunkPlacer(10, 5, 5),
                                    new TwoLayersFeatureSize(1, 1, 2)).ignoreVines()
                                    .decorators(ImmutableList.of(
                                            IcyBrushTreeDecorator.INSTANCE,
                                            new IceShroomTreeDecorator(0.2F),
                                            new CrystalFruitTreeDecorator(4))).build())
                            .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared())
                    .setBiomePredicate(FROZEN_DYING_FORST.or(FROZEN_BITTERWOOD_FORST))
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> LARGE_FROZEN_TREE =
            REGISTER.register("large_frozen_tree",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.BASE_TREE.get()
                            .configured(new JBaseTreeFeatureConfig.Builder(
                                    new SimpleStateProvider(JBlocks.FROZEN_LOG.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.FROZEN_LEAVES.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.GRASSY_PERMAFROST.defaultBlockState()),
                                    new PineFoliagePlacer(UniformInt.fixed(3), UniformInt.fixed(1), UniformInt.fixed(2)),
                                    new FancyTrunkPlacer(15, 7, 7),
                                    new TwoLayersFeatureSize(1, 1, 2)).ignoreVines()
                                    .decorators(ImmutableList.of(
                                            IcyBrushTreeDecorator.INSTANCE,
                                            new IceShroomTreeDecorator(0.1F),
                                            new CrystalFruitTreeDecorator(1))).build())
                            .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared())
                    .setBiomePredicate(FROZEN_DYING_FORST.or(FROZEN_BITTERWOOD_FORST))
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> LARGE_FROZEN_BITTERWOOOD_TREE =
            REGISTER.register("large_frozen_bitterwood_tree",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.BASE_TREE.get()
                            .configured(new JBaseTreeFeatureConfig.Builder(
                                    new SimpleStateProvider(JBlocks.FROZEN_LOG.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.FROZEN_LEAVES.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.GRASSY_PERMAFROST.defaultBlockState()),
                                    new SpruceFoliagePlacer(UniformInt.fixed(3), UniformInt.fixed(1), UniformInt.fixed(2)),
                                    new GiantTrunkPlacer(15, 7, 7),
                                    new TwoLayersFeatureSize(1, 1, 2)).ignoreVines()
                                    .decorators(ImmutableList.of(
                                            new FrozenTreeDecorator(0.01F))).build())
                            .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared())
                    .setBiomePredicate(FROZEN_BITTERWOOD_FORST)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> MEDIUM_FROZEN_BITTERWOOOD_TREE =
            REGISTER.register("medium_frozen_bitterwood_tree",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.BASE_TREE.get()
                            .configured(new JBaseTreeFeatureConfig.Builder(
                                    new SimpleStateProvider(JBlocks.FROZEN_LOG.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.FROZEN_LEAVES.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.GRASSY_PERMAFROST.defaultBlockState()),
                                    new SpruceFoliagePlacer(UniformInt.fixed(3), UniformInt.fixed(1), UniformInt.fixed(2)),
                                    new StraightTrunkPlacer(10, 7, 7),
                                    new TwoLayersFeatureSize(1, 1, 2)).ignoreVines()
                                    .decorators(ImmutableList.of(
                                            new FrozenTreeDecorator(0.01F))).build())
                            .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared())
                    .setBiomePredicate(FROZEN_BITTERWOOD_FORST)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> SMALL_FROZEN_BITTERWOOOD_TREE =
            REGISTER.register("small_frozen_bitterwood_tree",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.BASE_TREE.get()
                            .configured(new JBaseTreeFeatureConfig.Builder(
                                    new SimpleStateProvider(JBlocks.FROZEN_LOG.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.FROZEN_LEAVES.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.GRASSY_PERMAFROST.defaultBlockState()),
                                    new SpruceFoliagePlacer(UniformInt.fixed(3), UniformInt.fixed(1), UniformInt.fixed(2)),
                                    new StraightTrunkPlacer(4, 2, 3),
                                    new TwoLayersFeatureSize(1, 1, 2)).ignoreVines()
                                    .decorators(ImmutableList.of(
                                            new FrozenTreeDecorator(0.01F))).build())
                            .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared())
                    .setBiomePredicate(FROZEN_BITTERWOOD_FORST)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> SCORCHED_STALAGMITE =
            REGISTER.register("scorched_stalagmite",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.SCORCHED_STALAGMITE.get()
                            .configured(FeatureConfiguration.NONE)
                            .range(256)
                            .squared()
                            .count(220)
                            .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared())
                    .setBiomePredicate(SCORCHED_WASTELAND)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> SCORCHED_CACTUS =
            REGISTER.register("scorched_cactus", Decoration.VEGETAL_DECORATION, () -> Feature.RANDOM_PATCH
                    .configured((new RandomPatchConfiguration.GrassConfigurationBuilder(
                            new SimpleStateProvider(JBlocks.SCORCHED_CACTUS.defaultBlockState()),
                            new ColumnPlacer(2, 4)))
                            .tries(4)
                            .xspread(16)
                            .zspread(16)
                            .whitelist(ImmutableSet.of(JBlocks.VOLCANIC_SAND))
                            .noProjection()
                            .build())
                    .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared()
                    .range(250)
                    .count(10))
                    .setBiomePredicate(BOILING_SANDS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> SULPHUR_DEPOSIT =
            REGISTER.register("sulphur_deposit",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.SULPHUR_DEPOSIT.get()
                            .configured(new BlockStateConfiguration(JBlocks.SULPHUR_ROCK.defaultBlockState()))
                            .range(256)
                            .decorated(Features.Decorators.HEIGHTMAP_SQUARE)
                            .squared()
                            .count(100))
                    .setBiomePredicate(BOILING_SANDS)
                    .asPromise();

    /*public static final Promised<? extends ConfiguredFeature<?, ?>> SULPHUR_CRYSTAL =
            REGISTER.register("sulphur_crystal",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.SULPHUR_CRYSTAL.get()
                            .configured(IFeatureConfig.NONE)
                            .range(256)
                            .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                            .squared()
                            .count(50))
                    .setBiomePredicate(BOILING_SANDS)
                    .asPromise();*/

    public static final Promised<? extends ConfiguredFeature<?, ?>> LARGE_BURNED_TREE =
            REGISTER.register("large_burned_tree",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.BASE_TREE.get()
                            .configured(new JBaseTreeFeatureConfig.Builder(
                                    new SimpleStateProvider(JBlocks.BURNED_BARK.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.CHARRED_LEAVES.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.CHARRED_GRASS.defaultBlockState()),
                                    new FancyFoliagePlacer(UniformInt.fixed(4), UniformInt.fixed(1), 2),
                                    new ForkingTrunkPlacer(5, 5, 5),
                                    new TwoLayersFeatureSize(1, 1, 2)).ignoreVines()
                                    .decorators(ImmutableList.of(
                                            CharredBrushTreeDecorator.INSTANCE))
                                    .build())
                            .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared())
                    .setBiomePredicate(CHARRED_FIELDS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> MEDIUM_BURNED_TREE =
            REGISTER.register("medium_burned_tree",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.BASE_TREE.get()
                            .configured(new JBaseTreeFeatureConfig.Builder(
                                    new SimpleStateProvider(JBlocks.BURNED_BARK.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.CHARRED_LEAVES.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.CHARRED_GRASS.defaultBlockState()),
                                    new FancyFoliagePlacer(UniformInt.fixed(3), UniformInt.fixed(1), 2),
                                    new ForkingTrunkPlacer(4, 4, 4),
                                    new TwoLayersFeatureSize(1, 1, 2)).ignoreVines()
                                    .decorators(ImmutableList.of(
                                            CharredBrushTreeDecorator.INSTANCE))
                                    .build())
                            .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared())
                    .setBiomePredicate(CHARRED_FIELDS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> SMALL_BURNED_TREE =
            REGISTER.register("small_burned_tree",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.BASE_TREE.get()
                            .configured(new JBaseTreeFeatureConfig.Builder(
                                    new SimpleStateProvider(JBlocks.BURNED_BARK.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.CHARRED_LEAVES.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.CHARRED_GRASS.defaultBlockState()),
                                    new FancyFoliagePlacer(UniformInt.fixed(2), UniformInt.fixed(1), 2),
                                    new ForkingTrunkPlacer(3, 3, 3),
                                    new TwoLayersFeatureSize(1, 1, 2)).ignoreVines()
                                    .decorators(ImmutableList.of(
                                            CharredBrushTreeDecorator.INSTANCE))
                                    .build())
                            .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared())
                    .setBiomePredicate(CHARRED_FIELDS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> DYING_BURNED_TREE =
            REGISTER.register("dying_burned_tree",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.BASE_TREE.get()
                            .configured(new JBaseTreeFeatureConfig.Builder(
                                    new SimpleStateProvider(JBlocks.BURNED_BARK.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.CHARRED_LEAVES.defaultBlockState()),
                                    new SimpleStateProvider(JBlocks.VOLCANIC_SAND.defaultBlockState()),
                                    new FancyFoliagePlacer(UniformInt.fixed(2), UniformInt.fixed(1), 2),
                                    new ForkingTrunkPlacer(2, 1, 1),
                                    new TwoLayersFeatureSize(1, 1, 2)).ignoreVines()
                                    .build())
                            .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared())
                    .setBiomePredicate(BOILING_SANDS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> ASHUAL_ORE =
            REGISTER.register("ashual_ore",
                    Decoration.UNDERGROUND_ORES,
                    defaultOreFeature(() -> JBlocks.ASHUAL_ORE.defaultBlockState(), JRuleTests.ASH, 7, 140, 10))
                    .setBiomePredicate(BOIL_FIRE_BIOMES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> BLAZIUM_ORE =
            REGISTER.register("blazium_ore",
                            Decoration.UNDERGROUND_ORES,
                            defaultOreFeature(() -> JBlocks.BLAZIUM_ORE.defaultBlockState(), JRuleTests.ASH, 7, 40, 10))
                    .setBiomePredicate(BOIL_FIRE_BIOMES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> SAPPHIRE_ORE =
            REGISTER.register("sapphire_ore",
                    Decoration.UNDERGROUND_ORES,
                    defaultOreFeature(() -> JBlocks.SAPPHIRE_ORE.defaultBlockState(), JRuleTests.STONE, 7, 24, 2))
                    .setBiomePredicate(COMMON_BIOMES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> LUNIUM_ORE =
            REGISTER.register("lunium_ore",
                    Decoration.UNDERGROUND_ORES,
                    defaultOreFeature(() -> JBlocks.LUNIUM_ORE.defaultBlockState(), JRuleTests.STONE, 5, 16, 1))
                    .setBiomePredicate(COMMON_BIOMES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> SHADIUM_ORE =
            REGISTER.register("shadium_ore",
                    Decoration.UNDERGROUND_ORES,
                    defaultOreFeature(() -> JBlocks.SHADIUM_ORE.defaultBlockState(), JRuleTests.STONE, 3, 10, 1))
                    .setBiomePredicate(COMMON_BIOMES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> IRIDIUM_ORE =
            REGISTER.register("iridium_ore",
                    Decoration.UNDERGROUND_ORES,
                    defaultOreFeature(() -> JBlocks.IRIDIUM_ORE.defaultBlockState(), JRuleTests.STONE, 7, 10, 16))
                    .setBiomePredicate(COMMON_BIOMES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> BLOODCRUST_ORE =
            REGISTER.register("bloodcrust_ore",
                    Decoration.UNDERGROUND_ORES,
                    netherOreFeature(() -> JBlocks.BLOODCRUST_ORE.defaultBlockState(), JRuleTests.NETHERRACK, 10, 10))
                    .setBiomePredicate(IN_NETHER)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> FIRESTONE_ORE =
            REGISTER.register("firestone_ore",
                    Decoration.UNDERGROUND_ORES,
                    netherOreFeature(() -> JBlocks.FIRESTONE_ORE.defaultBlockState(), JRuleTests.BASALT, 10, 24))
                    .setBiomePredicate(IN_NETHER)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> LAVA_ROCK_CLUMP =
            REGISTER.register("lava_rock_clump",
                    Decoration.UNDERGROUND_ORES,
                    netherOreFeature(() -> JBlocks.BLOOD_ROCK.defaultBlockState(), JRuleTests.NETHERRACK, 10, 24))
                    .setBiomePredicate(IN_NETHER)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> CRIMSON_QUARTZ_ORE =
            REGISTER.register("crimson_quartz_ore",
                    Decoration.UNDERGROUND_ORES,
                    netherOreFeature(() -> JBlocks.CRIMSON_QUARTZ_ORE.defaultBlockState(), JRuleTests.NETHERRACK, 10, 10))
                    .setBiomePredicate(IN_CRIMSON_FOREST)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> WARPED_QUARTZ_ORE =
            REGISTER.register("crimson_quartz_ore",
                    Decoration.UNDERGROUND_ORES,
                    netherOreFeature(() -> JBlocks.WARPED_QUARTZ_ORE.defaultBlockState(), JRuleTests.NETHERRACK, 10, 10))
                    .setBiomePredicate(IN_WARPED_FOREST)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> ENDERILLIUM_ORE =
            REGISTER.register("enderillium_ore",
                    Decoration.UNDERGROUND_ORES,
                    defaultOreFeature(() -> JBlocks.ENDERILLIUM_ORE.defaultBlockState(), JRuleTests.END_STONE, 12, 128, 20))
                    .setBiomePredicate(IN_END)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> MEKYUM_ORE =
            REGISTER.register("mekyum_ore",
                    Decoration.UNDERGROUND_ORES,
                    defaultOreFeature(() -> JBlocks.MEKYUM_ORE.defaultBlockState(), JRuleTests.STONE_EUCA, 12, 128, 20))
                    .setBiomePredicate(EUCA_SILVER_PLAINS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> STORON_ORE =
            REGISTER.register("storon_ore",
                    Decoration.UNDERGROUND_ORES,
                    defaultOreFeature(() -> JBlocks.STORON_ORE.defaultBlockState(), JRuleTests.STONE_EUCA, 12, 128, 20))
                    .setBiomePredicate(EUCA_GOLD_PLAINS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> CELESTIUM_ORE =
            REGISTER.register("celestium_ore",
                    Decoration.UNDERGROUND_ORES,
                    defaultOreFeature(() -> JBlocks.CELESTIUM_ORE.defaultBlockState(), JRuleTests.STONE_EUCA, 12, 128, 20))
                    .setBiomePredicate(GOLDITE_GRAINS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> KORITE_ORE =
            REGISTER.register("korite_ore",
                            Decoration.UNDERGROUND_ORES,
                            defaultOreFeature(() -> JBlocks.KORITE_ORE.defaultBlockState(), JRuleTests.STONE_EUCA, 12, 128, 20))
                    .setBiomePredicate(GOLDITE_GRAINS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> PERIDOT_ORE = //TODO: Tweak rarity and quantity
            REGISTER.register("peridot_ore",
                    Decoration.UNDERGROUND_ORES,
                    defaultOreFeature(() -> JBlocks.PERIDOT_ORE.defaultBlockState(), JRuleTests.STONE_FROZEN, 8, 64, 10))
                    .setBiomePredicate(FROZEN_DYING_FORST.or(FROZEN_BITTERWOOD_FORST).or(FROZEN_WASTES))
                    .asPromise();


    public static final Promised<? extends ConfiguredFeature<?, ?>> RIMESTONE_ORE = //TODO: Tweak rarity and quantity
            REGISTER.register("rimestone_ore",
                    Decoration.UNDERGROUND_ORES,
                    defaultOreFeature(() -> JBlocks.RIMESTONE_ORE.defaultBlockState(), JRuleTests.STONE_FROZEN, 4, 64, 5))
                    .setBiomePredicate(FROZEN_DYING_FORST.or(FROZEN_BITTERWOOD_FORST).or(FROZEN_WASTES))
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> MUD_DISK =
            REGISTER.register("mud_disk",
                    Decoration.UNDERGROUND_ORES,
                    defaultDiskFeature(() -> JBlocks.BLOCK_OF_MUD.defaultBlockState(), Blocks.DIRT.defaultBlockState(), 2, 4, 1))
                    .setBiomePredicate(COMMON_BIOMES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> FROZEN_ICE_SPIKE =
            REGISTER.register("frozen_ice_spike",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.FROZEN_ICE_SPIKE.get()
                            .configured(FeatureConfiguration.NONE)
                            .range(5)
                            .squared()
                            .chance(70)
                            .countRandom(32)
                            .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared())
                    .setBiomePredicate(FROZEN_WASTES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> FROZEN_PLANTS =
            REGISTER.register("frozen_plants", Decoration.VEGETAL_DECORATION, () -> Feature.RANDOM_PATCH
                    .configured((new RandomPatchConfiguration.GrassConfigurationBuilder(
                            new WeightedStateProvider()
                                    .add(JBlocks.ICE_BUSH.defaultBlockState(), 1)
                                    .add(JBlocks.FROSTBERRY_THORN.defaultBlockState(), 5),
                            new SimpleBlockPlacer()))
                            .tries(26)
                            .xspread(8)
                            .zspread(8)
                            .whitelist(ImmutableSet.of(
                                    JBlocks.GRASSY_PERMAFROST))
                            .noProjection()
                            .build())
                    .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared()
                    .range(250)
                    .count(64))
                    .setBiomePredicate(FROZEN_BIOMES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> FROZEN_FLOWERS =
            REGISTER.register("frozen_flowers", Decoration.VEGETAL_DECORATION, () -> Feature.RANDOM_PATCH
                    .configured((new RandomPatchConfiguration.GrassConfigurationBuilder(
                            new WeightedStateProvider()
                                    .add(JBlocks.FROZEN_BLOOM.defaultBlockState(), 5)
                                    .add(JBlocks.ICE_BUD.defaultBlockState(), 2),
                            new SimpleBlockPlacer()))
                            .tries(3)
                            .xspread(8)
                            .zspread(8)
                            .whitelist(ImmutableSet.of(
                                    JBlocks.GRASSY_PERMAFROST))
                            .noProjection()
                            .build())
                    .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared()
                    .range(250)
                    .count(50))
                    .setBiomePredicate(FROZEN_BIOMES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> CICLEBLOOM =
            REGISTER.register("ciclebloom", Decoration.VEGETAL_DECORATION, () -> Feature.RANDOM_PATCH
                    .configured((new RandomPatchConfiguration.GrassConfigurationBuilder(
                            new SimpleStateProvider(JBlocks.CICLEBLOOM.defaultBlockState()),
                            new SimpleBlockPlacer()))
                            .tries(2)
                            .xspread(10)
                            .zspread(10)
                            .whitelist(ImmutableSet.of(
                                    JBlocks.GRASSY_PERMAFROST))
                            .noProjection()
                            .build())
                            .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared()
                            .range(250)
                            .count(5))
                    .setBiomePredicate(FROZEN_DYING_FORST.or(FROZEN_BITTERWOOD_FORST))
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> REDCURRANT_BUSH =
            REGISTER.register("redcurrant_bush", Decoration.VEGETAL_DECORATION, () -> Feature.RANDOM_PATCH
                            .configured((new RandomPatchConfiguration.GrassConfigurationBuilder(
                                    new SimpleStateProvider(JBlocks.REDCURRANT_BUSH.defaultBlockState().setValue(JBerryBushBlock.AGE, 3)),
                                    new SimpleBlockPlacer()))
                                    .tries(1)
                                    .xspread(3)
                                    .zspread(3)
                                    .whitelist(ImmutableSet.of(
                                            JBlocks.GRASSY_PERMAFROST))
                                    .noProjection()
                                    .build())
                            .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared()
                            .range(250)
                            .count(1))
                    .setBiomePredicate(FROZEN_DYING_FORST.or(FROZEN_BITTERWOOD_FORST))
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> TALL_BOILING_SANDS_PLANTS =
            REGISTER.register("tall_boiling_sands_plants",
                            Decoration.VEGETAL_DECORATION,
                            () -> Feature.RANDOM_PATCH
                                    .configured((new RandomPatchConfiguration.GrassConfigurationBuilder(
                                            new WeightedStateProvider()
                                                    .add(JBlocks.TALL_MOLTEN_PLANT.defaultBlockState(), 1)
                                            .add(JBlocks.TALL_CRUMBLING_PINE.defaultBlockState(), 1),
                                    new DoublePlantPlacer()))
                                    .tries(64)
                                    .xspread(6)
                                    .zspread(6)
                                    .whitelist(ImmutableSet.of(
                                            JBlocks.VOLCANIC_SAND))
                                    .noProjection()
                                    .build())
                            .range(255)
                            .count(100)
                            .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared())
                    .setBiomePredicate(BOILING_SANDS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> TALL_BOIL_UNDERGROWTH =
            REGISTER.register("tall_boil_undergrowth",
                            Decoration.UNDERGROUND_DECORATION,
                            () -> Feature.RANDOM_PATCH
                                    .configured((new RandomPatchConfiguration.GrassConfigurationBuilder(
                                            new WeightedStateProvider()
                                                    .add(JBlocks.TALL_SIZZLESHROOM.defaultBlockState(), 1),
                                            new DoublePlantPlacer()))
                                            .tries(10)
                                            .xspread(6)
                                            .zspread(6)
                                            .noProjection()
                                            .build())
                                    .range(60)
                                    .count(10))
                    .setBiomePredicate(BOIL_FIRE_BIOMES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> BOILING_UNDERGROWTH =
            REGISTER.register("boiling_undergrowth", Decoration.VEGETAL_DECORATION, () -> Feature.RANDOM_PATCH
                            .configured((new RandomPatchConfiguration.GrassConfigurationBuilder(
                                    new WeightedStateProvider()
                                            .add(JBlocks.SIZZLESHROOM.defaultBlockState(), 1),
                                    new SimpleBlockPlacer()))
                                    .tries(10)
                                    .xspread(10)
                                    .zspread(10)
                                    .noProjection()
                                    .build())
                            .range(60)
                            .count(10))
                    .setBiomePredicate(BOIL_FIRE_BIOMES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> BOILING_SANDS_FLOWERS =
            REGISTER.register("boiling_sands_flowers", Decoration.VEGETAL_DECORATION, () -> Feature.RANDOM_PATCH
                            .configured((new RandomPatchConfiguration.GrassConfigurationBuilder(
                                    new WeightedStateProvider()
                                            .add(JBlocks.LAVA_BLOOM.defaultBlockState(), 1)
                                            .add(JBlocks.CRUMBLING_PINE.defaultBlockState(), 5)
                                            .add(JBlocks.CRISP_GRASS.defaultBlockState(), 8),
                                    new SimpleBlockPlacer()))
                                    .tries(8)
                                    .xspread(6)
                                    .zspread(6)
                                    .whitelist(ImmutableSet.of(
                                            JBlocks.VOLCANIC_SAND))
                                    .noProjection()
                                    .build())
                    .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared()
                    .range(250)
                    .count(100))
                    .setBiomePredicate(BOILING_SANDS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> CHARRED_FIELDS_FLOWERS =
            REGISTER.register("charred_fields_flowers", Decoration.VEGETAL_DECORATION, () -> Feature.RANDOM_PATCH
                    .configured((new RandomPatchConfiguration.GrassConfigurationBuilder(
                            new WeightedStateProvider()
                                    .add(JBlocks.CHARRED_WEEDS.defaultBlockState(), 1)
                                    .add(JBlocks.CHARRED_SHORT_GRASS.defaultBlockState(), 1),
                            new SimpleBlockPlacer()))
                            .tries(24)
                            .xspread(6)
                            .zspread(6)
                            .whitelist(ImmutableSet.of(
                                    JBlocks.CHARRED_GRASS))
                            .noProjection()
                            .build())
                    .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared()
                    .range(250)
                    .count(100))
                    .setBiomePredicate(CHARRED_FIELDS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> TALL_CHARRED_FIELDS_PLANTS =
            REGISTER.register("tall_charred_fields_plants",
                    Decoration.UNDERGROUND_DECORATION,
                    () -> Feature.RANDOM_PATCH
                            .configured((new RandomPatchConfiguration.GrassConfigurationBuilder(
                                    new WeightedStateProvider()
                                            .add(JBlocks.CHARRED_TALL_GRASS.defaultBlockState(), 1),
                                    new DoublePlantPlacer()))
                                    .tries(10)
                                    .xspread(6)
                                    .zspread(6)
                                    .whitelist(ImmutableSet.of(
                                            JBlocks.CHARRED_GRASS))
                                    .noProjection()
                                    .build())
                            .range(255)
                            .count(100)
                            .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared())
                    .setBiomePredicate(CHARRED_FIELDS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> BOILING_PLAINS_FLOWERS =
            REGISTER.register("boiling_plains_flowers", Decoration.VEGETAL_DECORATION, () -> Feature.RANDOM_PATCH
                    .configured((new RandomPatchConfiguration.GrassConfigurationBuilder(
                            new WeightedStateProvider()
                                    .add(JBlocks.INFERNO_BUSH.defaultBlockState(), 1)
                                    .add(JBlocks.FLAME_POD.defaultBlockState(), 1),
                            new SimpleBlockPlacer()))
                            .tries(6)
                            .xspread(6)
                            .zspread(6)
                            .whitelist(ImmutableSet.of(
                                    JBlocks.HOT_GROUND))
                            .noProjection()
                            .build())
                    .range(250)
                    .count(100)
                    .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared())
                    .setBiomePredicate(BOILING_PLAINS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> BOILING_FIRE =
            REGISTER.register("boiling_fire", Decoration.VEGETAL_DECORATION, () -> Feature.RANDOM_PATCH
                            .configured((new RandomPatchConfiguration.GrassConfigurationBuilder(
                                    new WeightedStateProvider()
                                            .add(Blocks.FIRE.defaultBlockState(), 1),
                                    new SimpleBlockPlacer()))
                                    .tries(20)
                                    .xspread(10)
                                    .zspread(10)
                                    .whitelist(ImmutableSet.of(
                                            JBlocks.HOT_GROUND,
                                            JBlocks.CHARRED_GRASS,
                                            JBlocks.SCORCHED_RUBBLE,
                                            JBlocks.VOLCANIC_SAND))
                                    .noProjection()
                                    .build())
                    .range(250)
                    .count(10))
                    .setBiomePredicate(BOIL_FIRE_BIOMES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> FLAME_BULB =
            REGISTER.register("flame_bulb",
                    Decoration.VEGETAL_DECORATION,
                    () -> JFeatures.FLAME_BULB.get()
                            .configured(FeatureConfiguration.NONE)
                            .range(256)
                            .squared()
                            .countRandom(64)
                            .decorated(Features.Decorators.HEIGHTMAP_WORLD_SURFACE).squared())
                    .setBiomePredicate(CHARRED_FIELDS)
                    .asPromise();

    /**
     * Creates an ore feature with basic parameters.
     *
     * @param oreSup     The supplier of the ore being generated
     * @param spawnBlock The RuleTest block that the ore can generate in
     * @param size       The maximum size of the ore vein
     * @param range      The maximum height the ore can generate within
     * @param count      (unsure) Possible count per chunk, or possibly extra vein size.
     * @return Returns a new Configured Ore Feature based on the params filled in the method
     */
    private static Supplier<ConfiguredFeature<?, ?>> defaultOreFeature(Supplier<BlockState> oreSup, RuleTest spawnBlock, int size, int range, int count) {
        return () -> Feature.ORE.configured(new OreConfiguration(spawnBlock, oreSup.get(), size)).range(range).squared().count(count);
    }

    private static Supplier<ConfiguredFeature<?, ?>> defaultOreFeature(Supplier<BlockState> oreSup, Supplier<RuleTest> spawnBlock, int size, int range, int count) {
        return () -> Feature.ORE.configured(new OreConfiguration(spawnBlock.get(), oreSup.get(), size)).range(range).squared().count(count);
    }

    private static Supplier<ConfiguredFeature<?, ?>> netherOreFeature(Supplier<BlockState> oreSup, RuleTest spawnBlock, int size, int count) {
        return () -> Feature.ORE.configured(new OreConfiguration(spawnBlock, oreSup.get(), size)).decorated(Features.Decorators.RANGE_10_20_ROOFED).squared().count(count);
    }

    private static Supplier<ConfiguredFeature<?, ?>> defaultDiskFeature(Supplier<BlockState> oreSup, BlockState spawnBlock, int baseValue, int spread, int halfHeight) {
        return () -> Feature.DISK.configured(
                new DiskConfiguration(oreSup.get(), UniformInt.of(baseValue, spread), halfHeight, ImmutableList.of(spawnBlock)))
                .decorated(Features.Decorators.TOP_SOLID_HEIGHTMAP_SQUARE);
    }

    private static Supplier<ConfiguredFeature<?, ?>> surfacePatchFeature(Supplier<BlockState> blockStateSupplier, Supplier<BlockState> surfaceStateSupplier, int tries) {
        return () -> Feature.RANDOM_PATCH.configured((
                new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(blockStateSupplier.get()), SimpleBlockPlacer.INSTANCE))
                .tries(tries)
                .whitelist(ImmutableSet.of(surfaceStateSupplier.get().getBlock()))
                .build());
    }
}