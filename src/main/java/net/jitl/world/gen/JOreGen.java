package net.jitl.world.gen;

import net.jitl.JITL;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;

public class JOreGen {

	private static final RuleTest STONE = new BlockMatchRuleTest(Blocks.STONE);

	public static void init() {
		registerConfigFeatures();
	}

	private static void registerConfigFeatures() {
		Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(JITL.MODID, "sapphire_ore_feature"), defaultOreFeature(
				Blocks.DIAMOND_ORE.defaultBlockState(),
				STONE,
				12,
				128,
				20));
	}

	private static ConfiguredFeature<?, ?> defaultOreFeature(BlockState ore, RuleTest spawnBlock, int size, int range, int count) {
		return Feature.ORE.configured(new OreFeatureConfig(spawnBlock, ore, size)).range(range).squared().count(count);
	}
}