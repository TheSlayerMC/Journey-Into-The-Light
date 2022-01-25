package net.jitl.core.init.world;

import com.google.common.collect.ImmutableList;
import net.jitl.core.JITL;
import net.jitl.core.init.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import ru.timeconqueror.timecore.api.registry.PlacedFeatureRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;
import ru.timeconqueror.timecore.api.registry.util.Promised;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class JPlacedFeatures {
    @AutoRegistrable
    public static final PlacedFeatureRegister REGISTER = new PlacedFeatureRegister(JITL.MODID);

    public static final Promised<? extends PlacedFeature> TARTBERRY_BUSH = REGISTER.register(
                    "tartberry_bush",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.TARTBERRY_BUSH.get()
                            .placed(patch(5, 14, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)))
            .allowedInBiomes(BiomePredicate.IN_FORESTS)
            .asPromise();

    public static final Promised<? extends PlacedFeature> DEEPVINE = REGISTER.register(
                    "deepvine",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.DEEPVINE.get()
                            .placed(undergroundCeilingPatch(75, Blocks.DEEPSLATE)))
            .asPromise();

    public static final Promised<? extends PlacedFeature> TALL_GLOWSHROOMS = REGISTER.register(
                    "tall_glowshrooms",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.TALL_GLOWSHROOMS.get()
                            .placed(undergroundFloorPatch(4, Blocks.DEEPSLATE)))
            .asPromise();

    public static final Promised<? extends PlacedFeature> SMALL_GLOWSHROOMS = REGISTER.register(
                    "small_glowshrooms",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.SMALL_GLOWSHROOMS.get()
                            .placed(undergroundFloorPatch(4, Blocks.DEEPSLATE)))
            .asPromise();

    public static final Promised<? extends PlacedFeature> DEFAULT_OVERWORLD_RUINS = REGISTER.register(
                    "default_overworld_ruins",
                    GenerationStep.Decoration.SURFACE_STRUCTURES,
                    () -> JConfiguredFeatures.DEFAULT_OVERWORLD_RUINS.get()
                            .placed(rarePatch(10, 7)))
            .asPromise();

    public static final Promised<? extends PlacedFeature> DESERT_OVERWORLD_RUINS = REGISTER.register(
                    "desert_overworld_ruins",
                    GenerationStep.Decoration.SURFACE_STRUCTURES,
                    () -> JConfiguredFeatures.DESERT_OVERWORLD_RUINS.get()
                            .placed(rarePatch(10, 7)))
            .allowedInBiomes(BiomePredicate.IN_DESERT)
            .asPromise();


    public static final Promised<? extends PlacedFeature> ORE_LUNIUM = REGISTER.register(
                    "ore_lunium",
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    () -> JConfiguredFeatures.LUNIUM_ORE.get()
                            .placed(rareOrePlacement(
                                    8,
                                    HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(15)))))
            .asPromise();

    public static final Promised<? extends PlacedFeature> ORE_LUNIUM_LOWER = REGISTER.register(
                    "ore_lunium_lower",
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    () -> JConfiguredFeatures.LUNIUM_ORE_BURIED.get()
                            .placed(rareOrePlacement(
                                    4,
                                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))))
            .asPromise();

    public static final Promised<? extends PlacedFeature> ORE_SAPPHIRE = REGISTER.register(
                    "ore_sapphire",
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    () -> JConfiguredFeatures.SAPPHIRE_ORE.get()
                            .placed(rareOrePlacement(
                                    7,
                                    HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(15)))))
            .asPromise();

    public static final Promised<? extends PlacedFeature> ORE_SAPPHIRE_LOWER = REGISTER.register(
                    "ore_sapphire_lower",
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    () -> JConfiguredFeatures.SAPPHIRE_ORE_BURIED.get()
                            .placed(rareOrePlacement(
                                    4,
                                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))))
            .asPromise();

    public static final Promised<? extends PlacedFeature> ORE_SHADIUM = REGISTER.register(
                    "ore_shadium",
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    () -> JConfiguredFeatures.SHADIUM_ORE.get()
                            .placed(rareOrePlacement(
                                    7,
                                    HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(15)))))
            .asPromise();

    public static final Promised<? extends PlacedFeature> ORE_SHADIUM_LOWER = REGISTER.register(
                    "ore_shadium_lower",
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    () -> JConfiguredFeatures.SHADIUM_ORE_BURIED.get()
                            .placed(rareOrePlacement(
                                    4,
                                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))))
            .asPromise();

    public static final Promised<? extends PlacedFeature> ORE_IRIDIUM = REGISTER.register(
                    "ore_iridium",
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    () -> JConfiguredFeatures.IRIDIUM_ORE.get()
                            .placed(rareOrePlacement(
                                    7,
                                    HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(15)))))
            .asPromise();

    //TODO: adjust ore chances
    public static final Promised<? extends PlacedFeature> ORE_IRIDIUM_LOWER = REGISTER.register(
                    "ore_iridium_lower",
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    () -> JConfiguredFeatures.IRIDIUM_ORE_BURIED.get()
                            .placed(rareOrePlacement(
                                    4,
                                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))))
            .asPromise();

    public static final Promised<? extends PlacedFeature> BOIL_STALAGMITE = REGISTER.register(
                    "boil_stalagmite",
                    GenerationStep.Decoration.SURFACE_STRUCTURES,
                    () -> JConfiguredFeatures.SCORCHED_STALAGMITE.get()
                            .placed(patch(75, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)))
            .allowedInBiomes(BiomePredicate.SCORCHED_WASTELAND)
            .asPromise();


    public static final Promised<? extends PlacedFeature> SCORCHED_CACTUS = REGISTER.register(
                    "scorched_cactus",
                    GenerationStep.Decoration.SURFACE_STRUCTURES,
                    () -> JConfiguredFeatures.SCORCHED_CACTUS.get()
                            .placed(patch(5, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)))
            .allowedInBiomes(BiomePredicate.BOILING_SANDS)
            .asPromise();

    public static final Promised<? extends PlacedFeature> SULPHUR_CRYSTAL = REGISTER.register(
                    "sulphur_crystal",
                    GenerationStep.Decoration.SURFACE_STRUCTURES,
                    () -> JConfiguredFeatures.SULPHUR_CRYSTAL.get()
                            .placed(patch(1, 8, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)))
            .allowedInBiomes(BiomePredicate.BOILING_SANDS)
            .asPromise();

    public static final Promised<? extends PlacedFeature> ICE_SPIKE = REGISTER.register(
                    "ice_spike",
                    GenerationStep.Decoration.RAW_GENERATION,
                    () -> JConfiguredFeatures.ICE_SPIKE.get()
                            .placed(patch(3, 5, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)))
            .allowedInBiomes(BiomePredicate.FROZEN_WASTES)
            .asPromise();

    public static final Promised<? extends PlacedFeature> SULPHUR_DEPOSIT = REGISTER.register(
                    "sulphur_deposit",
                    GenerationStep.Decoration.SURFACE_STRUCTURES,
                    () -> JConfiguredFeatures.SULPHUR_DEPOSIT.get()
                            .placed(patch(2, 5, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)))
            .allowedInBiomes(BiomePredicate.BOILING_SANDS)
            .asPromise();

    public static final Promised<? extends PlacedFeature> BOILING_FIRE = REGISTER.register(
                    "boiling_fire",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.BOILING_FIRE.get()
                            .placed(surfaceFloorPatch(1, 6, JBlocks.HOT_GROUND, JBlocks.SCORCHED_RUBBLE, JBlocks.CHARRED_GRASS, JBlocks.VOLCANIC_SAND)))
            .allowedInBiomes(BiomePredicate.BOIL_FIRE_BIOMES)
            .asPromise();

    public static final Promised<? extends PlacedFeature> BOIL_SANDS_VEG = REGISTER.register(
                    "boil_sands_veg",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.BOIL_SANDS_VEG.get()
                            .placed(patch(6, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)))
            .allowedInBiomes(BiomePredicate.BOILING_SANDS)
            .asPromise();

    public static final Promised<? extends PlacedFeature> BOIL_VEG = REGISTER.register(
                    "boil_veg",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.BOIL_VEG.get()
                            .placed(patch(2, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)))
            .allowedInBiomes(BiomePredicate.BOILING_PLAINS)
            .asPromise();

    public static final Promised<? extends PlacedFeature> CHARRED_FIELDS_VEG = REGISTER.register(
                    "charred_fields_veg",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.CHARRED_FIELDS_VEG.get()
                            .placed(patch(2, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)))
            .allowedInBiomes(BiomePredicate.CHARRED_FIELDS)
            .asPromise();

    public static final Promised<? extends PlacedFeature> FLAME_BULB = REGISTER.register(
                    "flame_bulb",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.FLAME_BULB.get()
                            .placed(patch(5, 14, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)))
            .allowedInBiomes(BiomePredicate.CHARRED_FIELDS)
            .asPromise();

    public static final Promised<? extends PlacedFeature> DYING_BURNED_TREE = REGISTER.register(
                    "dying_burned_tree",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.DYING_BURNED_TREE.get()
                            .placed(treePlacement(PlacementUtils.countExtra(3, 0.1F, 1), JBlocks.CHARRED_SAPLING))) //FIXME: replace block with sapling when they're added
            .allowedInBiomes(BiomePredicate.BOILING_SANDS)
            .asPromise();

    public static final Promised<? extends PlacedFeature> LARGE_BURNED_TREE = REGISTER.register(
                    "large_burned_tree",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.LARGE_CHARRED_TREE.get()
                            .placed(treePlacement(PlacementUtils.countExtra(4, 0.1F, 1), JBlocks.BURNED_SAPLING)))
            .allowedInBiomes(BiomePredicate.CHARRED_FIELDS)
            .asPromise();

    public static final Promised<? extends PlacedFeature> MEDIUM_BURNED_TREE = REGISTER.register(
                    "medium_burned_tree",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.MEDIUM_BURNED_TREE.get()
                            .placed(treePlacement(PlacementUtils.countExtra(16, 0.1F, 1), JBlocks.BURNED_SAPLING)))
            .allowedInBiomes(BiomePredicate.CHARRED_FIELDS)
            .asPromise();

    public static final Promised<? extends PlacedFeature> SMALL_BURNED_TREE = REGISTER.register(
                    "small_burned_tree",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.SMALL_BURNED_TREE.get()
                            .placed(treePlacement(PlacementUtils.countExtra(16, 0.1F, 1), JBlocks.BURNED_SAPLING)))
            .allowedInBiomes(BiomePredicate.CHARRED_FIELDS)
            .asPromise();

    public static final Promised<? extends PlacedFeature> LARGE_FROZEN_TREE = REGISTER.register(
                    "large_frozen_tree",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.LARGE_FROZEN_TREE.get()
                            .placed(treePlacement(PlacementUtils.countExtra(4, 0.1F, 1), JBlocks.FROSTWOOD_SAPLING)))
            .allowedInBiomes(BiomePredicate.FROZEN_DYING_FORST)
            .asPromise();

    public static final Promised<? extends PlacedFeature> MEDIUM_FROZEN_TREE = REGISTER.register(
                    "medium_frozen_tree",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.MEDIUM_FROZEN_TREE.get()
                            .placed(treePlacement(PlacementUtils.countExtra(16, 0.1F, 1), JBlocks.FROSTWOOD_SAPLING)))
            .allowedInBiomes(BiomePredicate.FROZEN_DYING_FORST)
            .asPromise();

    public static final Promised<? extends PlacedFeature> SMALL_FROZEN_TREE = REGISTER.register(
                    "small_frozen_tree",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.SMALL_FROZEN_TREE.get()
                            .placed(treePlacement(PlacementUtils.countExtra(16, 0.1F, 1), JBlocks.FROSTWOOD_SAPLING)))
            .allowedInBiomes(BiomePredicate.FROZEN_DYING_FORST)
            .asPromise();

    public static final Promised<? extends PlacedFeature> LARGE_BITTERWOOD_TREE = REGISTER.register(
                    "large_bitterwood_tree",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.LARGE_FROZEN_BITTERWOOOD_TREE.get()
                            .placed(treePlacement(PlacementUtils.countExtra(4, 0.1F, 1), JBlocks.BITTERWOOD_SAPLING)))
            .allowedInBiomes(BiomePredicate.FROZEN_BITTERWOOD_FORST)
            .asPromise();

    public static final Promised<? extends PlacedFeature> MEDIUM_BITTERWOOD_TREE = REGISTER.register(
                    "medium_bitterwood_tree",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.MEDIUM_FROZEN_BITTERWOOOD_TREE.get()
                            .placed(treePlacement(PlacementUtils.countExtra(16, 0.1F, 1), JBlocks.BITTERWOOD_SAPLING)))
            .allowedInBiomes(BiomePredicate.FROZEN_BITTERWOOD_FORST)
            .asPromise();

    public static final Promised<? extends PlacedFeature> SMALL_BITTERWOOD_TREE = REGISTER.register(
                    "small_bitterwood_tree",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.SMALL_FROZEN_BITTERWOOOD_TREE.get()
                            .placed(treePlacement(PlacementUtils.countExtra(16, 0.1F, 1), JBlocks.BITTERWOOD_SAPLING)))
            .allowedInBiomes(BiomePredicate.FROZEN_BITTERWOOD_FORST)
            .asPromise();

    public static final Promised<? extends PlacedFeature> FROZEN_VEG = REGISTER.register(
                    "frozen_veg",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.FROZEN_VEG.get()
                            .placed(patch(12, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)))
            .allowedInBiomes(BiomePredicate.FROZEN_BIOMES)
            .asPromise();

    public static final Promised<? extends PlacedFeature> FROZEN_FLOWERS = REGISTER.register(
                    "frozen_flowers",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.FROZEN_FLOWERS.get()
                            .placed(patch(12, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)))
            .allowedInBiomes(BiomePredicate.FROZEN_BIOMES)
            .asPromise();

    public static final Promised<? extends PlacedFeature> EUCA_GOLD_TREE = REGISTER.register(
                    "euca_gold_tree",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.EUCA_GOLD_TREES.get()
                            .placed(treePlacement(PlacementUtils.countExtra(4, 0.1F, 1), JBlocks.EUCA_GOLD_SAPLING)))
            .allowedInBiomes(BiomePredicate.EUCA_GOLD_PLAINS)
            .asPromise();

    public static final Promised<? extends PlacedFeature> EUCA_GREEN_GOLD_TREE = REGISTER.register(
                    "euca_green_gold_tree",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.EUCA_GREEN_TREES.get()
                            .placed(treePlacement(PlacementUtils.countExtra(4, 0.1F, 1), JBlocks.EUCA_BROWN_SAPLING)))
            .allowedInBiomes(BiomePredicate.EUCA_GOLD_PLAINS)
            .asPromise();

    public static final Promised<? extends PlacedFeature> EUCA_GREEN_TREE = REGISTER.register(
                    "euca_green_tree",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.EUCA_GREEN_TREES.get()
                            .placed(treePlacement(PlacementUtils.countExtra(1, 0.01F, 1), JBlocks.EUCA_BROWN_SAPLING)))
            .allowedInBiomes(BiomePredicate.GOLDITE_GRAINS)
            .asPromise();

    public static final Promised<? extends PlacedFeature> GOLD_BOT_SPAWNER = REGISTER.register(
                    "gold_bot_spawner",
                    GenerationStep.Decoration.SURFACE_STRUCTURES,
                    () -> JConfiguredFeatures.EUCA_GOLDBOT_SAPAWNER.get()
                            .placed(rarePatch(1, 20)))
            .allowedInBiomes(BiomePredicate.EUCA_GOLD_PLAINS)
            .asPromise();

    public static final Promised<? extends PlacedFeature> GOLDITE_VEG = REGISTER.register(
                    "goldite_veg",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.GOLDITE_VEG.get()
                            .placed(patch(12, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)))
            .allowedInBiomes(BiomePredicate.GOLDITE_GRAINS)
            .asPromise();

    public static final Promised<? extends PlacedFeature> EUCA_WATER = REGISTER.register(
                    "euca_water",
                    GenerationStep.Decoration.FLUID_SPRINGS,
                    () -> JConfiguredFeatures.EUCA_WATER.get()
                            .placed(patch(100, 8, PlacementUtils.FULL_RANGE)))
            .allowedInBiomes(BiomePredicate.EUCA_BIOMES)
            .asPromise();

    public static final Promised<? extends PlacedFeature> GOLD_VEG = REGISTER.register(
                    "gold_veg",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.GOLD_VEG.get()
                            .placed(patch(6, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)))
            .allowedInBiomes(BiomePredicate.EUCA_GOLD_PLAINS)
            .asPromise();

    public static final Promised<? extends PlacedFeature> ORE_MEKYUM = REGISTER.register(
                    "ore_mekyum",
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    () -> JConfiguredFeatures.MEKYUM_ORE.get()
                            .placed(orePlacement(CountPlacement.of(5),
                                    HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(128)))))
            .allowedInBiomes(BiomePredicate.EUCA_GOLD_PLAINS)
            .asPromise();

    public static final Promised<? extends PlacedFeature> ORE_STORON = REGISTER.register(
                    "ore_storon",
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    () -> JConfiguredFeatures.STORON_ORE.get()
                            .placed(orePlacement(CountPlacement.of(5),
                                    HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(128)))))
            .allowedInBiomes(BiomePredicate.EUCA_GOLD_PLAINS)
            .asPromise();

    public static final Promised<? extends PlacedFeature> ORE_CELESTIUM = REGISTER.register(
                    "ore_celestium",
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    () -> JConfiguredFeatures.CELESTIUM_ORE.get()
                            .placed(orePlacement(CountPlacement.of(5),
                                    HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(128)))))
            .allowedInBiomes(BiomePredicate.GOLDITE_GRAINS)
            .asPromise();

    public static final Promised<? extends PlacedFeature> ORE_KORITE = REGISTER.register(
                    "ore_korite",
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    () -> JConfiguredFeatures.KORITE_ORE.get()
                            .placed(orePlacement(CountPlacement.of(5),
                                    HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(128)))))
            .allowedInBiomes(BiomePredicate.GOLDITE_GRAINS)
            .asPromise();

    public static final Promised<? extends PlacedFeature> ORE_ASHUAL = REGISTER.register(
                    "ore_ashual",
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    () -> JConfiguredFeatures.ASHUAL_ORE.get()
                            .placed(orePlacement(CountPlacement.of(12),
                                    HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(256)))))
            .allowedInBiomes(BiomePredicate.BOIL_FIRE_BIOMES)
            .asPromise();

    public static final Promised<? extends PlacedFeature> ORE_BLAZIUM = REGISTER.register(
                    "ore_blazium",
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    () -> JConfiguredFeatures.BLAZIUM_ORE.get()
                            .placed(orePlacement(CountPlacement.of(12),
                                    HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(256)))))
            .allowedInBiomes(BiomePredicate.BOIL_FIRE_BIOMES)
            .asPromise();

    public static final Promised<? extends PlacedFeature> EUCA_BOULDER = REGISTER.register(
                    "euca_boulder",
                    GenerationStep.Decoration.RAW_GENERATION,
                    () -> JConfiguredFeatures.EUCA_BOULDER.get()
                            .placed(patch(2, 5, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)))
            .allowedInBiomes(BiomePredicate.EUCA_BIOMES)
            .asPromise();

    public static final Promised<? extends PlacedFeature> GLOWING_FUNGI = REGISTER.register(
                    "glowing_fungi",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.GLOWING_FUNGI.get()
                            .placed(undergroundFloorPatch(4, Blocks.STONE, Blocks.DEEPSLATE)))
            .asPromise();

    private static List<PlacementModifier> patch(int count, PlacementModifier placementModifier) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                placementModifier,
                BiomeFilter.biome());
    }

    private static List<PlacementModifier> patch(int count, int chance, PlacementModifier placementModifier) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                placementModifier,
                RarityFilter.onAverageOnceEvery(chance),
                BiomeFilter.biome());
    }

    private static List<PlacementModifier> undergroundCeilingPatch(int count, Block blockPredicatte) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.matchesBlock(blockPredicatte, Vec3i.ZERO), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
                BiomeFilter.biome());
    }

    private static List<PlacementModifier> undergroundFloorPatch(int count, Block blockPredicatte) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.matchesBlock(blockPredicatte, Vec3i.ZERO), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                RandomOffsetPlacement.vertical(ConstantInt.of(1)),
                BiomeFilter.biome());
    }

    private static List<PlacementModifier> undergroundFloorPatch(int count, Block ... blockPredicatte) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.matchesBlocks(List.of(blockPredicatte), Vec3i.ZERO), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                RandomOffsetPlacement.vertical(ConstantInt.of(1)),
                BiomeFilter.biome());
    }

    private static List<PlacementModifier> surfaceFloorPatch(int count, Block blockPredicatte) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.matchesBlock(blockPredicatte, Vec3i.ZERO), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                RandomOffsetPlacement.vertical(ConstantInt.of(1)),
                BiomeFilter.biome());
    }

    private static List<PlacementModifier> surfaceFloorPatch(int count, int chance, Block... blockPredicatte) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.matchesBlocks(List.of(blockPredicatte), Vec3i.ZERO), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                RandomOffsetPlacement.vertical(ConstantInt.of(1)),
                RarityFilter.onAverageOnceEvery(chance),
                BiomeFilter.biome());
    }

    private static List<PlacementModifier> surfaceFloorPatch(int count, Block... blockPredicatte) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.matchesBlocks(List.of(blockPredicatte), Vec3i.ZERO), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                RandomOffsetPlacement.vertical(ConstantInt.of(1)),
                BiomeFilter.biome());
    }

    private static List<PlacementModifier> rareUndergroundFloorPatch(int count, int chance, Block blockPredicatte) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.matchesBlock(blockPredicatte, Vec3i.ZERO), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                RandomOffsetPlacement.vertical(ConstantInt.of(1)),
                RarityFilter.onAverageOnceEvery(chance),
                BiomeFilter.biome());
    }

    private static List<PlacementModifier> rarePatch(int count, int chance) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                PlacementUtils.FULL_RANGE,
                BiomeFilter.biome(),
                RarityFilter.onAverageOnceEvery(chance));
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier countModifier_, PlacementModifier heightModifier_) {
        return List.of(countModifier_, InSquarePlacement.spread(), heightModifier_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int count_, PlacementModifier heightModifier_) {
        return orePlacement(CountPlacement.of(count_), heightModifier_);
    }

    private static List<PlacementModifier> rareOrePlacement(int chance_, PlacementModifier heightModifier_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(chance_), heightModifier_);
    }

    public static final PlacementModifier TREE_THRESHOLD = SurfaceWaterDepthFilter.forMaxDepth(0);

    private static ImmutableList.Builder<PlacementModifier> treePlacementBase(PlacementModifier countModifier_) {
        return ImmutableList.<PlacementModifier>builder().add(countModifier_).add(InSquarePlacement.spread()).add(TREE_THRESHOLD).add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR).add(BiomeFilter.biome()).add();
    }

    public static List<PlacementModifier> treePlacement(PlacementModifier modifier_) {
        return treePlacementBase(modifier_).build();
    }

    public static List<PlacementModifier> treePlacement(PlacementModifier modifier_, Block block_) {
        return treePlacementBase(modifier_).add(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(block_.defaultBlockState(), BlockPos.ZERO))).build();
    }

    public static class BiomePredicate {
        public static final Predicate<BiomeLoadingEvent> IN_DRIPSTONE_CAVES = event -> Objects.equals(event.getName(), new ResourceLocation("dripstone_caves"));
        public static final Predicate<BiomeLoadingEvent> IN_FORESTS = event -> event.getCategory() == Biome.BiomeCategory.FOREST;
        public static final Predicate<BiomeLoadingEvent> IN_NETHER = event -> event.getCategory() == Biome.BiomeCategory.NETHER;
        public static final Predicate<BiomeLoadingEvent> IN_END = event -> event.getCategory() == Biome.BiomeCategory.THEEND;
        public static final Predicate<BiomeLoadingEvent> IN_DESERT = event -> Objects.equals(event.getName(), new ResourceLocation("desert"));
        public static final Predicate<BiomeLoadingEvent> IN_WARPED_FOREST = event -> Objects.equals(event.getName(), new ResourceLocation("warped_forest"));
        public static final Predicate<BiomeLoadingEvent> IN_CRIMSON_FOREST = event -> Objects.equals(event.getName(), new ResourceLocation("crimson_forest"));

        public static final Predicate<BiomeLoadingEvent> GOLDITE_GRAINS = event -> Objects.equals(event.getName(), JITL.rl("euca/euca_goldite_grains"));
        public static final Predicate<BiomeLoadingEvent> EUCA_GOLD_PLAINS = event -> Objects.equals(event.getName(), JITL.rl("euca/euca_plains"));

        public static final Predicate<BiomeLoadingEvent> FROZEN_WASTES = event -> Objects.equals(event.getName(), JITL.rl("frozen/frozen_wastes"));
        public static final Predicate<BiomeLoadingEvent> FROZEN_DYING_FORST = event -> Objects.equals(event.getName(), JITL.rl("frozen/dying_forest"));
        public static final Predicate<BiomeLoadingEvent> FROZEN_BITTERWOOD_FORST = event -> Objects.equals(event.getName(), JITL.rl("frozen/bitterwood_forest"));

        public static final Predicate<BiomeLoadingEvent> SCORCHED_WASTELAND = event -> Objects.equals(event.getName(), JITL.rl("boil/scorched_wastelands"));
        public static final Predicate<BiomeLoadingEvent> BOILING_SANDS = event -> Objects.equals(event.getName(), JITL.rl("boil/boiling_sands"));
        public static final Predicate<BiomeLoadingEvent> CHARRED_FIELDS = event -> Objects.equals(event.getName(), JITL.rl("boil/charred_fields"));
        public static final Predicate<BiomeLoadingEvent> BOILING_PLAINS = event -> Objects.equals(event.getName(), JITL.rl("boil/boil"));

        public static final Predicate<BiomeLoadingEvent> EUCA_BIOMES = GOLDITE_GRAINS.or(EUCA_GOLD_PLAINS);
        public static final Predicate<BiomeLoadingEvent> BOIL_FIRE_BIOMES = SCORCHED_WASTELAND.or(CHARRED_FIELDS).or(BOILING_PLAINS).or(BOILING_SANDS);
        public static final Predicate<BiomeLoadingEvent> FROZEN_BIOMES = FROZEN_WASTES.or(FROZEN_DYING_FORST).or(FROZEN_BITTERWOOD_FORST);

        public static final Predicate<BiomeLoadingEvent> COMMON_BIOMES = IN_NETHER.and(IN_END).negate();
    }
}
