package net.jitl.init;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.jitl.JITL;
import net.jitl.common.world.gen.features.featureconfig.RuinsFeatureConfig;
import net.jitl.util.JRuleTests;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.loot.LootTables;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedList;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.template.RuleTest;
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
                                    JRuleTests.GRASS_DEFAULT,
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
                                    JRuleTests.SAND_DEFAULT,
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

    public static final Promised<? extends ConfiguredFeature<?, ?>> SAPPHIRE_ORE =
            REGISTER.register("sapphire_ore",
                    Decoration.UNDERGROUND_ORES,
                    defaultOreFeature(() -> JBlocks.SAPPHIRE_ORE.defaultBlockState(), JRuleTests.STONE_DEFAULT, 7, 24, 2))
                    .setBiomePredicate(COMMON_BIOMES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> LUNIUM_ORE =
            REGISTER.register("lunium_ore",
                    Decoration.UNDERGROUND_ORES,
                    defaultOreFeature(() -> JBlocks.LUNIUM_ORE.defaultBlockState(), JRuleTests.STONE_DEFAULT, 5, 16, 1))
                    .setBiomePredicate(COMMON_BIOMES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> SHADIUM_ORE =
            REGISTER.register("shadium_ore",
                    Decoration.UNDERGROUND_ORES,
                    defaultOreFeature(() -> JBlocks.SHADIUM_ORE.defaultBlockState(), JRuleTests.STONE_DEFAULT, 3, 10, 1))
                    .setBiomePredicate(COMMON_BIOMES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> IRIDIUM_ORE =
            REGISTER.register("iridium_ore",
                    Decoration.UNDERGROUND_ORES,
                    defaultOreFeature(() -> JBlocks.IRIDIUM_ORE.defaultBlockState(), JRuleTests.STONE_DEFAULT, 7, 10, 16))
                    .setBiomePredicate(COMMON_BIOMES)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> BLOODCRUST_ORE =
            REGISTER.register("bloodcrust_ore",
                    Decoration.UNDERGROUND_ORES,
                    netherOreFeature(() -> JBlocks.BLOODCRUST_ORE.defaultBlockState(), JRuleTests.STONE_NETHERRACK, 10, 10))
                    .setBiomePredicate(IN_NETHER)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> FIRESTONE_ORE =
            REGISTER.register("firestone_ore",
                    Decoration.UNDERGROUND_ORES,
                    netherOreFeature(() -> JBlocks.FIRESTONE_ORE.defaultBlockState(), JRuleTests.STONE_BASALT, 10, 24))
                    .setBiomePredicate(IN_NETHER)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> LAVA_ROCK_CLUMP =
            REGISTER.register("lava_rock_clump",
					Decoration.UNDERGROUND_ORES,
					netherOreFeature(() -> JBlocks.BLOOD_ROCK.defaultBlockState(), JRuleTests.STONE_NETHERRACK, 10, 24))
                    .setBiomePredicate(IN_NETHER)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> CRIMSON_QUARTZ_ORE =
            REGISTER.register("crimson_quartz_ore",
                    Decoration.UNDERGROUND_ORES,
                    netherOreFeature(() -> JBlocks.CRIMSON_QUARTZ_ORE.defaultBlockState(), JRuleTests.STONE_NETHERRACK, 10, 10))
                    .setBiomePredicate(IN_CRIMSON_FOREST)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> WARPED_QUARTZ_ORE =
            REGISTER.register("crimson_quartz_ore",
                    Decoration.UNDERGROUND_ORES,
                    netherOreFeature(() -> JBlocks.WARPED_QUARTZ_ORE.defaultBlockState(), JRuleTests.STONE_NETHERRACK, 10, 10))
                    .setBiomePredicate(IN_WARPED_FOREST)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> ENDERILLIUM_ORE =
            REGISTER.register("enderillium_ore",
                    Decoration.UNDERGROUND_ORES,
                    defaultOreFeature(() -> JBlocks.ENDERILLIUM_ORE.defaultBlockState(), JRuleTests.STONE_END, 12, 128, 20))
                    .setBiomePredicate(IN_END)
                    .asPromise();

    public static final Promised<? extends ConfiguredFeature<?, ?>> MUD_DISK =
            REGISTER.register("mud_disk",
                    Decoration.UNDERGROUND_ORES,
                    defaultDiskFeature(() -> JBlocks.BLOCK_OF_MUD.defaultBlockState(), Blocks.DIRT.defaultBlockState(), 2, 4, 1))
                    .setBiomePredicate(COMMON_BIOMES)
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