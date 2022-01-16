package net.jitl.util;

import net.jitl.init.JBlocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockStateMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.function.Supplier;

public class JRuleTests {

	public static RuleTest GRASS = new BlockStateMatchTest(Blocks.GRASS_BLOCK.defaultBlockState());
	public static RuleTest SAND = new BlockStateMatchTest(Blocks.SAND.defaultBlockState());

    public static RuleTest STONE = new BlockStateMatchTest(Blocks.STONE.defaultBlockState());
    public static RuleTest NETHERRACK = new BlockStateMatchTest(Blocks.NETHERRACK.defaultBlockState());
    public static RuleTest BASALT = new BlockStateMatchTest(Blocks.BASALT.defaultBlockState());
    public static RuleTest END_STONE = new BlockStateMatchTest(Blocks.END_STONE.defaultBlockState());

    public static Supplier<RuleTest> STONE_EUCA = () -> new BlockStateMatchTest(JBlocks.GOLDITE_STONE.defaultBlockState());
    public static Supplier<RuleTest> GOLD_GRASS_EUCA = () -> new BlockStateMatchTest(JBlocks.EUCA_GOLD_GRASS_BLOCK.defaultBlockState());
    public static Supplier<RuleTest> GOLDITE_GRASS_EUCA = () -> new BlockStateMatchTest(JBlocks.GOLDITE_GRASS_BLOCK.defaultBlockState());

    public static Supplier<RuleTest> STONE_FROZEN = () -> new BlockStateMatchTest(JBlocks.PERMAFROST.defaultBlockState());

    public static Supplier<RuleTest> FROZEN_GRASS_BLOCK = () -> new BlockStateMatchTest(JBlocks.GRASSY_PERMAFROST.defaultBlockState());

    public static Supplier<RuleTest> VOLCANIC_SAND = () -> new BlockStateMatchTest(JBlocks.VOLCANIC_SAND.defaultBlockState());
    public static Supplier<RuleTest> ASH = () -> new BlockStateMatchTest(JBlocks.ASH_BLOCK.defaultBlockState());


}
