package net.jitl.util;

import net.jitl.init.JBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.template.BlockStateMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;

import java.util.function.Supplier;

public class JRuleTests {

	public static RuleTest GRASS = new BlockStateMatchRuleTest(Blocks.GRASS_BLOCK.defaultBlockState());
	public static RuleTest SAND = new BlockStateMatchRuleTest(Blocks.SAND.defaultBlockState());

    public static RuleTest STONE = new BlockStateMatchRuleTest(Blocks.STONE.defaultBlockState());
    public static RuleTest NETHERRACK = new BlockStateMatchRuleTest(Blocks.NETHERRACK.defaultBlockState());
    public static RuleTest BASALT = new BlockStateMatchRuleTest(Blocks.BASALT.defaultBlockState());
    public static RuleTest END_STONE = new BlockStateMatchRuleTest(Blocks.END_STONE.defaultBlockState());

    public static Supplier<RuleTest> STONE_EUCA = () -> new BlockStateMatchRuleTest(JBlocks.GOLDITE_STONE.defaultBlockState());
    public static Supplier<RuleTest> GOLD_GRASS_EUCA = () -> new BlockStateMatchRuleTest(JBlocks.EUCA_GOLD_GRASS_BLOCK.defaultBlockState());
    public static Supplier<RuleTest> SILVER_GRASS_EUCA = () -> new BlockStateMatchRuleTest(JBlocks.EUCA_SILVER_GRASS_BLOCK.defaultBlockState());
    public static Supplier<RuleTest> GOLDITE_GRASS_EUCA = () -> new BlockStateMatchRuleTest(JBlocks.GOLDITE_GRASS_BLOCK.defaultBlockState());

    public static Supplier<RuleTest> STONE_FROZEN = () -> new BlockStateMatchRuleTest(JBlocks.PERMAFROST.defaultBlockState());

    public static Supplier<RuleTest> FROZEN_GRASS_BLOCK = () -> new BlockStateMatchRuleTest(JBlocks.GRASSY_PERMAFROST.defaultBlockState());

}
