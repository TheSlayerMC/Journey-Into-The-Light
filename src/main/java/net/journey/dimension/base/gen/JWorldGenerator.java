package net.journey.dimension.base.gen;

import net.journey.util.RandHelper;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class JWorldGenerator extends WorldGenerator {

	@Override
	public boolean generate(World world, Random random, BlockPos blockPos) {
		return true;
	}

	public IBlockState getRandomState(Random rand, IBlockState... states) {
		return RandHelper.chooseEqual(rand, states);
	}

	public Block getRandomBlock(Random rand, Block... blocks) {
		return RandHelper.chooseEqual(rand, blocks);
	}

	public void setDefaultState(World world, BlockPos pos, Block block) {
		this.setBlockAndNotifyAdequately(world, pos, block.getDefaultState());
	}

	public void setStateWithFacing(World world, BlockPos pos, Block block, PropertyDirection directionProp, EnumFacing facing) {
		setBlockAndNotifyAdequately(world, pos, block.getDefaultState().withProperty(directionProp, facing));
	}

	public EnumFacing getRandomHorizontalDirection(Random random) {
		return RandHelper.chooseEqual(random,
				EnumFacing.NORTH,
				EnumFacing.SOUTH,
				EnumFacing.EAST,
				EnumFacing.WEST);
	}

	public EnumFacing getRandomDirection(Random random) {
		return RandHelper.chooseEqual(random,
				EnumFacing.NORTH,
				EnumFacing.SOUTH,
				EnumFacing.EAST,
				EnumFacing.WEST,
				EnumFacing.UP,
				EnumFacing.DOWN);
	}
}
