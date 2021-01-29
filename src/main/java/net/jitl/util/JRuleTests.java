package net.jitl.util;

import net.jitl.init.JBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.template.BlockStateMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;

public class JRuleTests {

	public static RuleTest GRASS_DEFAULT = new BlockStateMatchRuleTest(Blocks.GRASS_BLOCK.defaultBlockState());
	public static RuleTest SAND_DEFAULT = new BlockStateMatchRuleTest(Blocks.SAND.defaultBlockState());

	public static RuleTest STONE_DEFAULT = new BlockStateMatchRuleTest(Blocks.STONE.defaultBlockState());
	public static RuleTest STONE_NETHERRACK = new BlockStateMatchRuleTest(Blocks.NETHERRACK.defaultBlockState());
	public static RuleTest STONE_BASALT = new BlockStateMatchRuleTest(Blocks.BASALT.defaultBlockState());
	public static RuleTest STONE_END = new BlockStateMatchRuleTest(Blocks.END_STONE.defaultBlockState());

	public static RuleTest STONE_EUCA = new BlockStateMatchRuleTest(JBlocks.GOLDITE_STONE.defaultBlockState());

}
