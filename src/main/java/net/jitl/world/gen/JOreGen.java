package net.jitl.world.gen;

import net.jitl.registry.JBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JOreGen {

	private static final RuleTest STONE = new BlockMatchRuleTest(Blocks.STONE);

	public static final ConfiguredFeature<?, ?> ORE_SAPPHIRE = Feature.ORE.configured(new OreFeatureConfig(STONE, JBlocks.SAPPHIRE_ORE.defaultBlockState(), 4)).range(24).chance(16).count(4);

	public static void register(IEventBus bus) {
		DeferredRegister.create(ForgeRegistries.FEATURES, "gen_sapphire_ore").register(bus);
	}
}
