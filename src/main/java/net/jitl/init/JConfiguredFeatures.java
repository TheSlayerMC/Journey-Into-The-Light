package net.jitl.init;

import com.google.common.collect.ImmutableList;
import net.jitl.JITL;
import net.jitl.util.JRuleTests;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import ru.timeconqueror.timecore.registry.AutoRegistrable;
import ru.timeconqueror.timecore.registry.Promised;
import ru.timeconqueror.timecore.registry.newreg.ConfiguredFeatureRegister;

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
    public static final Predicate<BiomeLoadingEvent> COMMON_BIOMES = event -> event.getCategory() != Biome.Category.NETHER && event.getCategory() != Biome.Category.NETHER; //TODO rework


    public static final Promised<? extends ConfiguredFeature<?, ?>> OVERWORLD_RUINS =
            REGISTER.register("overworld_ruins",
                    Decoration.SURFACE_STRUCTURES,
                    () -> JFeatures.OVERWORLD_RUINS.get()
                            .configured(IFeatureConfig.NONE)
                            .decorated(Features.Placements.HEIGHTMAP_WORLD_SURFACE)
                            .chance(512))
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
}