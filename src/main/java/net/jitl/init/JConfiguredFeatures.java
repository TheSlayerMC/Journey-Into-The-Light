package net.jitl.init;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.jitl.JITL;
import net.jitl.common.world.gen.features.featureconfig.EucaSpawnerFeatureConfig;
import net.jitl.common.world.gen.features.featureconfig.EucaTreeFeatureConfig;
import net.jitl.common.world.gen.features.featureconfig.RuinsFeatureConfig;
import net.jitl.common.world.gen.treedecorator.FrozenTreeDecorator;
import net.jitl.util.JRuleTests;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.loot.LootTables;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedList;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.PineFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import ru.timeconqueror.timecore.api.registry.ConfiguredFeatureRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;
import ru.timeconqueror.timecore.api.registry.util.Promised;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SuppressWarnings("Convert2MethodRef")
public class JConfiguredFeatures {
    @AutoRegistrable
    private static final ConfiguredFeatureRegister REGISTER = new ConfiguredFeatureRegister(JITL.MODID);

    public static final Predicate<BiomeLoadingEvent> IN_NETHER = event -> event.getCategory() == Biome.Category.NETHER;
    public static final Predicate<BiomeLoadingEvent> IN_END = event -> event.getCategory() == Biome.Category.THEEND;
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

    public static final Predicate<BiomeLoadingEvent> COMMON_BIOMES = IN_NETHER.and(IN_END).negate();

    public static final Promised<? extends ConfiguredFeature<?, ?>> BRADBERRY_BUSH =
            REGISTER.register("patch_bradberry_bush", Decoration.VEGETAL_DECORATION, surfacePatchFeature(
                    () -> JBlocks.BRADBERRY_BUSH.defaultBlockState(),
                    () -> Blocks.GRASS_BLOCK.defaultBlockState(),
                    8))
                    .setBiomePredicate(event -> event.getCategory() == Biome.Category.FOREST)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> DEFAULT_OVERWORLD_RUINS =
            REGISTER.register("default_overworld_ruins",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.RUINS.get()
                            .configured(new RuinsFeatureConfig(
                                    JRuleTests.GRASS,
                                    new WeightedBlockStateProvider()
                                            .add(Blocks.STONE_BRICKS.defaultBlockState(), 6)
                                            .add(Blocks.CRACKED_STONE_BRICKS.defaultBlockState(), 5)
                                            .add(Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 4)
                                            .add(Blocks.MOSSY_COBBLESTONE.defaultBlockState(), 3)
                                            .add(Blocks.COBBLESTONE.defaultBlockState(), 4)
                                            .add(Blocks.CHISELED_STONE_BRICKS.defaultBlockState(), 2)
                                            .add(Blocks.INFESTED_COBBLESTONE.defaultBlockState(), 2)
                                            .add(Blocks.INFESTED_STONE_BRICKS.defaultBlockState(), 2)
                                            .add(Blocks.INFESTED_MOSSY_STONE_BRICKS.defaultBlockState(), 2)
                                            .add(Blocks.INFESTED_CRACKED_STONE_BRICKS.defaultBlockState(), 2),
                                    5,
                                    5,
                                    new WeightedList<ResourceLocation>()
                                            .add(LootTables.ABANDONED_MINESHAFT, 5)
                                            .add(LootTables.SIMPLE_DUNGEON, 6)
                                            .add(LootTables.STRONGHOLD_CORRIDOR, 2)))
                            .decorated(Features.Placements.HEIGHTMAP_WORLD_SURFACE)
                            .chance(128))
                    .setBiomePredicate(COMMON_BIOMES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> DESERT_OVERWORLD_RUINS =
            REGISTER.register("desert_overworld_ruins",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.RUINS.get()
                            .configured(new RuinsFeatureConfig(
                                    JRuleTests.SAND,
                                    new WeightedBlockStateProvider()
                                            .add(Blocks.SANDSTONE.defaultBlockState(), 3)
                                            .add(Blocks.CHISELED_SANDSTONE.defaultBlockState(), 1)
                                            .add(Blocks.CUT_SANDSTONE.defaultBlockState(), 2),
                                    5,
                                    5,
                                    new WeightedList<ResourceLocation>()
                                            .add(LootTables.DESERT_PYRAMID, 4)
                                            .add(LootTables.VILLAGE_DESERT_HOUSE, 6)
                                            .add(LootTables.STRONGHOLD_CORRIDOR, 2)))
                            .decorated(Features.Placements.HEIGHTMAP_WORLD_SURFACE)
                            .chance(128))
                    .setBiomePredicate(COMMON_BIOMES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> TALL_GLOWSHROOMS =
            REGISTER.register("tall_glowshrooms",
                    Decoration.UNDERGROUND_DECORATION,
                    () -> Feature.RANDOM_PATCH
                            .configured((new BlockClusterFeatureConfig.Builder(
                                    new WeightedBlockStateProvider()
                                            .add(JBlocks.TALL_GREEN_GLOWSHROOM.defaultBlockState(), 1)
                                            .add(JBlocks.TALL_BLUE_GLOWSHROOM.defaultBlockState(), 1)
                                            .add(JBlocks.TALL_RED_GLOWSHROOM.defaultBlockState(), 1),
                                    new DoublePlantBlockPlacer()))
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

    public static final Promised<? extends ConfiguredFeature<?, ?>> GOLDITE_TALL_FOLIAGE =
            REGISTER.register("goldite_tall_foliage", Decoration.VEGETAL_DECORATION, () -> Feature.RANDOM_PATCH
                    .configured((new BlockClusterFeatureConfig.Builder(
                            new WeightedBlockStateProvider()
                                    .add(JBlocks.GOLDITE_TALL_GRASS.defaultBlockState(), 1),
                            new DoublePlantBlockPlacer()))
                            .tries(120)
                            .xspread(16)
                            .zspread(16)
                            .whitelist(ImmutableSet.of(
                                    JBlocks.GOLDITE_GRASS_BLOCK))
                            .noProjection()
                            .build())
                    .range(250)
                    .count(10))
                    .setBiomePredicate(GOLDITE_GRAINS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> GOLDITE_FOLIAGE =
            REGISTER.register("goldite_foliage", Decoration.VEGETAL_DECORATION, () -> Feature.RANDOM_PATCH
                    .configured((new BlockClusterFeatureConfig.Builder(
                            new WeightedBlockStateProvider()
                                    .add(JBlocks.GOLDITE_BULB.defaultBlockState(), 1),
                            new SimpleBlockPlacer()))
                            .tries(120)
                            .xspread(16)
                            .zspread(16)
                            .whitelist(ImmutableSet.of(
                                    JBlocks.GOLDITE_GRASS_BLOCK))
                            .noProjection()
                            .build())
                    .range(250)
                    .count(10))
                    .setBiomePredicate(GOLDITE_GRAINS)
                    .asPromise();


    public static final Promised<? extends ConfiguredFeature<?, ?>> SILVER_GOLD_FOLIAGE =
            REGISTER.register("euca_silver_gold_foliage", Decoration.VEGETAL_DECORATION, () -> Feature.RANDOM_PATCH
                    .configured((new BlockClusterFeatureConfig.Builder(
                            new WeightedBlockStateProvider()
                                    .add(JBlocks.EUCA_SILVER_GOLD_FLOWER.defaultBlockState(), 1),
                            new SimpleBlockPlacer()))
                            .tries(100)
                            .xspread(10)
                            .zspread(10)
                            .whitelist(ImmutableSet.of(
                                    JBlocks.EUCA_SILVER_GRASS_BLOCK, JBlocks.EUCA_GOLD_GRASS_BLOCK))
                            .noProjection()
                            .build())
                    .range(250)
                    .count(10))
                    .setBiomePredicate(EUCA_SILVER_PLAINS.and(EUCA_GOLD_PLAINS))
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> EUCA_SILVER_FOLIAGE =
            REGISTER.register("euca_silver_foliage", Decoration.VEGETAL_DECORATION, () -> Feature.RANDOM_PATCH
                    .configured((new BlockClusterFeatureConfig.Builder(
                            new WeightedBlockStateProvider()
                                    .add(JBlocks.EUCA_SILVER_TALL_GRASS.defaultBlockState(), 1)
                                    .add(JBlocks.EUCA_SILVER_SPROUTS.defaultBlockState(), 1)
                                    .add(JBlocks.EUCA_SILVER_SHORT_GRASS.defaultBlockState(), 1),
                            new SimpleBlockPlacer()))
                            .tries(100)
                            .xspread(6)
                            .zspread(6)
                            .whitelist(ImmutableSet.of(
                                    JBlocks.EUCA_SILVER_GRASS_BLOCK))
                            .noProjection()
                            .build())
                    .range(250)
                    .count(10))
                    .setBiomePredicate(EUCA_SILVER_PLAINS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> EUCA_GOLD_FOLIAGE =
            REGISTER.register("euca_gold_foliage", Decoration.VEGETAL_DECORATION, () -> Feature.RANDOM_PATCH
                    .configured((new BlockClusterFeatureConfig.Builder(
                            new WeightedBlockStateProvider()
                                    .add(JBlocks.EUCA_TALL_FLOWERS.defaultBlockState(), 1)
                                    .add(JBlocks.EUCA_TALL_GRASS.defaultBlockState(), 1),
                            new SimpleBlockPlacer()))
                            .tries(100)
                            .xspread(6)
                            .zspread(6)
                            .whitelist(ImmutableSet.of(
                                    JBlocks.EUCA_GOLD_GRASS_BLOCK))
                            .noProjection()
                            .build())
                    .range(250)
                    .count(10))
                    .setBiomePredicate(EUCA_GOLD_PLAINS)
                    .asPromise();


    public static final Promised<? extends ConfiguredFeature<?, ?>> GOLDITE_GOLD_FOLIAGE =
            REGISTER.register("euca_goldite_gold_foliage", Decoration.VEGETAL_DECORATION, () -> Feature.RANDOM_PATCH
                    .configured((new BlockClusterFeatureConfig.Builder(
                            new WeightedBlockStateProvider()
                                    .add(JBlocks.GOLDITE_STALKS.defaultBlockState(), 1)
                                    .add(JBlocks.GOLDITE_FLOWER.defaultBlockState(), 1),
                            new SimpleBlockPlacer()))
                            .tries(100)
                            .xspread(10)
                            .zspread(10)
                            .whitelist(ImmutableSet.of(
                                    JBlocks.GOLDITE_GRASS_BLOCK, JBlocks.EUCA_GOLD_GRASS_BLOCK))
                            .noProjection()
                            .build())
                    .range(250)
                    .count(10))
                    .setBiomePredicate(GOLDITE_GRAINS.and(EUCA_GOLD_PLAINS))
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> SMALL_GLOWSHROOMS =
            REGISTER.register("small_glowshrooms",
                    Decoration.UNDERGROUND_DECORATION,
                    () -> Feature.RANDOM_PATCH
                            .configured((new BlockClusterFeatureConfig.Builder(
                                    new WeightedBlockStateProvider()
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

    public static final Promised<? extends ConfiguredFeature<?, ?>> CAVE_VINES =
            REGISTER.register("cave_vines",
                    Decoration.UNDERGROUND_STRUCTURES,
                    () -> JFeatures.CAVE_VINES.get()
                            .configured(IFeatureConfig.NONE)
                            .range(60)
                            .squared()
                            .chance(1)
                            .countRandom(32))
                    .setBiomePredicate(COMMON_BIOMES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> EUCA_GOLD_TREES =
            REGISTER.register("euca_gold_tree",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.EUCA_TREE.get()
                            .configured(new EucaTreeFeatureConfig(
                                    JRuleTests.GOLD_GRASS_EUCA.get(),
                                    new SimpleBlockStateProvider(JBlocks.EUCA_SILVER_LEAVES.defaultBlockState()),
                                    new SimpleBlockStateProvider(JBlocks.EUCA_GOLD_LOG.defaultBlockState()),
                                    5,
                                    5,
                                    8))
                            .decorated(Features.Placements.HEIGHTMAP_WORLD_SURFACE)
                            .chance(3))
                    .setBiomePredicate(EUCA_GOLD_PLAINS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> EUCA_SILVER_TREES =
            REGISTER.register("euca_silver_tree",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.EUCA_TREE.get()
                            .configured(new EucaTreeFeatureConfig(
                                    JRuleTests.SILVER_GRASS_EUCA.get(),
                                    new SimpleBlockStateProvider(JBlocks.EUCA_GOLD_LEAVES.defaultBlockState()),
                                    new SimpleBlockStateProvider(JBlocks.EUCA_SILVER_LOG.defaultBlockState()),
                                    5,
                                    5,
                                    8))
                            .decorated(Features.Placements.HEIGHTMAP_WORLD_SURFACE)
                            .chance(3))
                    .setBiomePredicate(EUCA_SILVER_PLAINS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> EUCA_GOLD_GOLD_TREES =
            REGISTER.register("euca_gold_gold_tree",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.EUCA_TREE.get()
                            .configured(new EucaTreeFeatureConfig(
                                    JRuleTests.GOLDITE_GRASS_EUCA.get(),
                                    new SimpleBlockStateProvider(JBlocks.EUCA_GOLD_LEAVES.defaultBlockState()),
                                    new SimpleBlockStateProvider(JBlocks.EUCA_GOLD_LOG.defaultBlockState()),
                                    5,
                                    5,
                                    8))
                            .decorated(Features.Placements.HEIGHTMAP_WORLD_SURFACE)
                            .chance(2))
                    .setBiomePredicate(GOLDITE_GRAINS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> EUCA_GREEN_TREES =
            REGISTER.register("euca_green_tree",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.BASE_TREE.get()
                            .configured(new BaseTreeFeatureConfig.Builder(
                                    new SimpleBlockStateProvider(JBlocks.EUCA_BROWN_LOG.defaultBlockState()),
                                    new SimpleBlockStateProvider(JBlocks.EUCA_GREEN_LEAVES.defaultBlockState()),
                                    new FancyFoliagePlacer(FeatureSpread.fixed(3), FeatureSpread.fixed(1), 6),
                                    new MegaJungleTrunkPlacer(6, 3, 2),
                                    new TwoLayerFeature(1, 1, 2)).ignoreVines().build())
                            .decorated(Features.Placements.HEIGHTMAP_WORLD_SURFACE)
                            .chance(1).countRandom(5))
                    .setBiomePredicate(GOLDITE_GRAINS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> EUCA_SILVERBOT_SAPWNER =
            REGISTER.register("euca_silverbot_spawner",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.EUCA_BOT_SPAWNER.get()
                            .configured(new EucaSpawnerFeatureConfig(
                                    JRuleTests.SILVER_GRASS_EUCA.get(),
                                    new SimpleBlockStateProvider(JBlocks.SILVER_BOT_SPAWNER.defaultBlockState()),
                                    13,
                                    7))
                            .decorated(Features.Placements.HEIGHTMAP_WORLD_SURFACE)
                            .chance(50))
                    .setBiomePredicate(EUCA_SILVER_PLAINS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> EUCA_GOLDBOT_SAPWNER =
            REGISTER.register("euca_goldbot_spawner",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.EUCA_BOT_SPAWNER.get()
                            .configured(new EucaSpawnerFeatureConfig(
                                    JRuleTests.GOLD_GRASS_EUCA.get(),
                                    new SimpleBlockStateProvider(JBlocks.GOLD_BOT_SPAWNER.defaultBlockState()),
                                    13,
                                    7))
                            .decorated(Features.Placements.HEIGHTMAP_WORLD_SURFACE)
                            .chance(50))
                    .setBiomePredicate(EUCA_GOLD_PLAINS)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> SMALL_FROZEN_TREE =
            REGISTER.register("small_frozen_tree",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.BASE_TREE.get()
                            .configured(new BaseTreeFeatureConfig.Builder(
                                    new SimpleBlockStateProvider(JBlocks.FROZEN_LOG.defaultBlockState()),
                                    new SimpleBlockStateProvider(JBlocks.FROZEN_LEAVES.defaultBlockState()),
                                    new PineFoliagePlacer(FeatureSpread.fixed(3), FeatureSpread.fixed(1), FeatureSpread.fixed(2)),
                                    new ForkyTrunkPlacer(2, 1, 3),
                                    new TwoLayerFeature(1, 1, 2)).ignoreVines()
                                    .decorators(ImmutableList.of(new FrozenTreeDecorator(0.2F))).build())
                            .decorated(Features.Placements.HEIGHTMAP_WORLD_SURFACE).squared())
                    .setBiomePredicate(FROZEN_DYING_FORST)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> MEDIUM_FROZEN_TREE =
            REGISTER.register("medium_frozen_tree",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.BASE_TREE.get()
                            .configured(new BaseTreeFeatureConfig.Builder(
                                    new SimpleBlockStateProvider(JBlocks.FROZEN_LOG.defaultBlockState()),
                                    new SimpleBlockStateProvider(JBlocks.FROZEN_LEAVES.defaultBlockState()),
                                    new PineFoliagePlacer(FeatureSpread.fixed(3), FeatureSpread.fixed(1), FeatureSpread.fixed(2)),
                                    new FancyTrunkPlacer(10, 5, 5),
                                    new TwoLayerFeature(1, 1, 2)).ignoreVines()
                                    .decorators(ImmutableList.of(new FrozenTreeDecorator(0.2F))).build())
                            .decorated(Features.Placements.HEIGHTMAP_WORLD_SURFACE).squared())
                    .setBiomePredicate(FROZEN_DYING_FORST)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> LARGE_FROZEN_TREE =
            REGISTER.register("large_frozen_tree",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.BASE_TREE.get()
                            .configured(new BaseTreeFeatureConfig.Builder(
                                    new SimpleBlockStateProvider(JBlocks.FROZEN_LOG.defaultBlockState()),
                                    new SimpleBlockStateProvider(JBlocks.FROZEN_LEAVES.defaultBlockState()),
                                    new PineFoliagePlacer(FeatureSpread.fixed(3), FeatureSpread.fixed(1), FeatureSpread.fixed(2)),
                                    new FancyTrunkPlacer(15, 7, 7),
                                    new TwoLayerFeature(1, 1, 2)).ignoreVines()
                                    .decorators(ImmutableList.of(new FrozenTreeDecorator(0.2F))).build())
                            .decorated(Features.Placements.HEIGHTMAP_WORLD_SURFACE).squared())
                    .setBiomePredicate(FROZEN_DYING_FORST)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> LARGE_FROZEN_BITTERWOOOD_TREE =
            REGISTER.register("large_frozen_biterwood_tree",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.BASE_TREE.get()
                            .configured(new BaseTreeFeatureConfig.Builder(
                                    new SimpleBlockStateProvider(JBlocks.FROZEN_LOG.defaultBlockState()),
                                    new SimpleBlockStateProvider(JBlocks.FROZEN_LEAVES.defaultBlockState()),
                                    new SpruceFoliagePlacer(FeatureSpread.fixed(3), FeatureSpread.fixed(1), FeatureSpread.fixed(2)),
                                    new GiantTrunkPlacer(15, 7, 7),
                                    new TwoLayerFeature(1, 1, 2)).ignoreVines()
                                    .decorators(ImmutableList.of(new FrozenTreeDecorator(0.2F))).build())
                            .decorated(Features.Placements.HEIGHTMAP_WORLD_SURFACE).squared())
                    .setBiomePredicate(FROZEN_BITTERWOOD_FORST)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> MEDIUM_FROZEN_BITTERWOOOD_TREE =
            REGISTER.register("medium_frozen_biterwood_tree",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.BASE_TREE.get()
                            .configured(new BaseTreeFeatureConfig.Builder(
                                    new SimpleBlockStateProvider(JBlocks.FROZEN_LOG.defaultBlockState()),
                                    new SimpleBlockStateProvider(JBlocks.FROZEN_LEAVES.defaultBlockState()),
                                    new SpruceFoliagePlacer(FeatureSpread.fixed(3), FeatureSpread.fixed(1), FeatureSpread.fixed(2)),
                                    new StraightTrunkPlacer(10, 7, 7),
                                    new TwoLayerFeature(1, 1, 2)).ignoreVines()
                                    .decorators(ImmutableList.of(new FrozenTreeDecorator(0.2F))).build())
                            .decorated(Features.Placements.HEIGHTMAP_WORLD_SURFACE).squared())
                    .setBiomePredicate(FROZEN_BITTERWOOD_FORST)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> SMALL_FROZEN_BITTERWOOOD_TREE =
            REGISTER.register("small_frozen_biterwood_tree",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.BASE_TREE.get()
                            .configured(new BaseTreeFeatureConfig.Builder(
                                    new SimpleBlockStateProvider(JBlocks.FROZEN_LOG.defaultBlockState()),
                                    new SimpleBlockStateProvider(JBlocks.FROZEN_LEAVES.defaultBlockState()),
                                    new SpruceFoliagePlacer(FeatureSpread.fixed(3), FeatureSpread.fixed(1), FeatureSpread.fixed(2)),
                                    new StraightTrunkPlacer(4, 2, 3),
                                    new TwoLayerFeature(1, 1, 2)).ignoreVines()
                                    .decorators(ImmutableList.of(new FrozenTreeDecorator(0.2F))).build())
                            .decorated(Features.Placements.HEIGHTMAP_WORLD_SURFACE).squared())
                    .setBiomePredicate(FROZEN_BITTERWOOD_FORST)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> SCORCHED_STALAGMITE =
            REGISTER.register("scorched_stalagmite",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.SCORCHED_STALAGMITE.get()
                            .configured(IFeatureConfig.NONE)
                            .range(128)
                            .squared()
                            .count(220))
                    .setBiomePredicate(SCORCHED_WASTELAND)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> SCORCHED_CACTUS =
            REGISTER.register("scorched_cactus",
                            Decoration.SURFACE_STRUCTURES,
                            () -> JFeatures.SCORCHED_CACTUS.get()
                                    .configured(IFeatureConfig.NONE)
                                    .range(128)
                                    .squared()
                                    .count(100))
                    .setBiomePredicate(BOILING_SANDS)
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

    public static final Promised<? extends ConfiguredFeature<?, ?>> PERIDOT_ORE = //TODO: Tweak rarity and quantity
            REGISTER.register("peridot_ore",
                    Decoration.UNDERGROUND_ORES,
                    defaultOreFeature(() -> JBlocks.PERIDOT_ORE.defaultBlockState(), JRuleTests.STONE_FROZEN, 8, 64, 20))
                    .setBiomePredicate(FROZEN_DYING_FORST)
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
                            .configured(IFeatureConfig.NONE)
                            .range(5)
                            .squared()
                            .chance(70)
                            .countRandom(32))
                    .setBiomePredicate(FROZEN_WASTES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> FROZEN_PLANTS =
            REGISTER.register("frozen_flowers", Decoration.VEGETAL_DECORATION, () -> Feature.RANDOM_PATCH
                    .configured((new BlockClusterFeatureConfig.Builder(
                            new WeightedBlockStateProvider()
                                    //.add(JBlocks.FROSTBERRY_THORN.defaultBlockState(), 1)
                                    //.add(JBlocks.FROZEN_FLOWER.defaultBlockState(), 1)
                                    .add(JBlocks.ICE_BUSH.defaultBlockState(), 1),
                            //.add(JBlocks.ICE_BUD.defaultBlockState(), 1)
                            //.add(JBlocks.FROZEN_BLOOM.defaultBlockState(), 1),
                            new SimpleBlockPlacer()))
                            .tries(200)
                            .xspread(10)
                            .zspread(10)
                            .whitelist(ImmutableSet.of(
                                    JBlocks.GRASSY_PERMAFROST))
                            .noProjection()
                            .build())
                    .range(250)
                    .count(50))
                    .setBiomePredicate(FROZEN_DYING_FORST)
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
        return () -> Feature.ORE.configured(new OreFeatureConfig(spawnBlock, oreSup.get(), size)).range(range).squared().count(count);
    }

    private static Supplier<ConfiguredFeature<?, ?>> defaultOreFeature(Supplier<BlockState> oreSup, Supplier<RuleTest> spawnBlock, int size, int range, int count) {
        return () -> Feature.ORE.configured(new OreFeatureConfig(spawnBlock.get(), oreSup.get(), size)).range(range).squared().count(count);
    }

    private static Supplier<ConfiguredFeature<?, ?>> netherOreFeature(Supplier<BlockState> oreSup, RuleTest spawnBlock, int size, int count) {
        return () -> Feature.ORE.configured(new OreFeatureConfig(spawnBlock, oreSup.get(), size)).decorated(Features.Placements.RANGE_10_20_ROOFED).squared().count(count);
    }

    private static Supplier<ConfiguredFeature<?, ?>> defaultDiskFeature(Supplier<BlockState> oreSup, BlockState spawnBlock, int baseValue, int spread, int halfHeight) {
        return () -> Feature.DISK.configured(
                new SphereReplaceConfig(oreSup.get(), FeatureSpread.of(baseValue, spread), halfHeight, ImmutableList.of(spawnBlock)))
                .decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE);
    }

    private static Supplier<ConfiguredFeature<?, ?>> surfacePatchFeature(Supplier<BlockState> blockStateSupplier, Supplier<BlockState> surfaceStateSupplier, int tries) {
        return () -> Feature.RANDOM_PATCH.configured((
                new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(blockStateSupplier.get()), SimpleBlockPlacer.INSTANCE))
                .tries(tries)
                .whitelist(ImmutableSet.of(surfaceStateSupplier.get().getBlock()))
                .build());
    }
}