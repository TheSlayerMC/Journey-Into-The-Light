package net.jitl.init.world;

import net.jitl.JITL;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
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

    //TODO: rename to "tartberry_bush"
    public static final Promised<? extends PlacedFeature> BRADBERRY_BUSH = REGISTER.register(
                    "bradberry_bush",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.BRADBERRY_BUSH.get()
                            .placed(patch(5)))
            .allowedInBiomes(BiomePredicate.IN_FORESTS)
            .asPromise();

    //TODO: rename to "deepvines"
    public static final Promised<? extends PlacedFeature> CAVE_VINES = REGISTER.register(
                    "cave_vines",
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    () -> JConfiguredFeatures.CAVE_VINES.get()
                            .placed(undergroundCeilingPatch(75, Blocks.DEEPSLATE)))
            .allowedInBiomes(BiomePredicate.IN_FORESTS)
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

    private static List<PlacementModifier> patch(int count) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                PlacementUtils.FULL_RANGE,
                BiomeFilter.biome());
    }

    private static List<PlacementModifier> undergroundCeilingPatch(int count, Block blockPredicatte) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.matchesBlock(blockPredicatte, Vec3i.ZERO), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome());
    }

    private static List<PlacementModifier> rarePatch(int count, int chance) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                PlacementUtils.FULL_RANGE,
                BiomeFilter.biome(),
                RarityFilter.onAverageOnceEvery(chance));
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
    }
}
