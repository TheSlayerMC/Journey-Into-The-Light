package net.journey.dimension.corba.gen.trees;

import net.journey.api.block.GroundPredicate;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenCorbaHauntedTree extends WorldGenAbstractTree {

	protected GroundPredicate groundPredicate = GroundPredicate.COMMON_AND_CORBA_GRASS;

	private final int treeHeight = 10;

	public WorldGenCorbaHauntedTree() {
		super(false);
	}

	public WorldGenCorbaHauntedTree(boolean notify) {
		super(notify);
	}

	@Override
	public boolean generate(World world, Random random, BlockPos blockPos) {
		int treeHeight = random.nextInt(2) + random.nextInt(2) + this.treeHeight;

		BlockPos soilPos = blockPos.down();
		IBlockState soilState = world.getBlockState(soilPos);
		boolean isSoil = groundPredicate.testGround(world, soilPos, soilState, EnumFacing.UP);

		BlockPos.MutableBlockPos basePos = new BlockPos.MutableBlockPos(WorldGenAPI.findPosAboveSurface(world, blockPos)).move(EnumFacing.DOWN);

		if (isSoil) {
			int stumpHeight = (random.nextInt(2) + 3) - 2;

			for (int i = 0; i < stumpHeight; i++) {
				placeLowerStumps(world, basePos);
			}

			for (int i = 0; i < stumpHeight; i++) {
				placeStumps(world, basePos);
			}

			BlockPos.MutableBlockPos trunkPos = basePos.move(EnumFacing.DOWN, 3);

			for (int i = 0; i < treeHeight; i++) {
				placeLog(world, trunkPos);
			}

			return true;
		}
		return false;
	}

	private void placeLog(World world, BlockPos.MutableBlockPos logPos) {
		setBlockAndNotifyAdequately(world, logPos.move(EnumFacing.UP).offset(EnumFacing.EAST), JourneyBlocks.bogwoodLog.getDefaultState());
		setBlockAndNotifyAdequately(world, logPos.offset(EnumFacing.WEST), JourneyBlocks.bogwoodLog.getDefaultState());
		setBlockAndNotifyAdequately(world, logPos.offset(EnumFacing.NORTH), JourneyBlocks.bogwoodLog.getDefaultState());
		setBlockAndNotifyAdequately(world, logPos.offset(EnumFacing.SOUTH), JourneyBlocks.bogwoodLog.getDefaultState());
	}

	private void placeStumps(World world, BlockPos.MutableBlockPos logPos) {
		setBlockAndNotifyAdequately(world, logPos.move(EnumFacing.UP).offset(EnumFacing.EAST, 2).offset(EnumFacing.SOUTH).offset(EnumFacing.WEST), JourneyBlocks.bogwoodLog.getDefaultState());
		setBlockAndNotifyAdequately(world, logPos.offset(EnumFacing.WEST, 2).offset(EnumFacing.NORTH).offset(EnumFacing.EAST), JourneyBlocks.bogwoodLog.getDefaultState());
		setBlockAndNotifyAdequately(world, logPos.offset(EnumFacing.EAST, 2).offset(EnumFacing.NORTH).offset(EnumFacing.WEST), JourneyBlocks.bogwoodLog.getDefaultState());
		setBlockAndNotifyAdequately(world, logPos.offset(EnumFacing.WEST, 2).offset(EnumFacing.SOUTH).offset(EnumFacing.EAST), JourneyBlocks.bogwoodLog.getDefaultState());
	}

	private void placeLowerStumps(World world, BlockPos.MutableBlockPos logPos) {
		setBlockAndNotifyAdequately(world, logPos.offset(EnumFacing.EAST, 2), JourneyBlocks.bogwoodLog.getDefaultState());
		setBlockAndNotifyAdequately(world, logPos.offset(EnumFacing.WEST, 2), JourneyBlocks.bogwoodLog.getDefaultState());
		setBlockAndNotifyAdequately(world, logPos.offset(EnumFacing.NORTH, 2), JourneyBlocks.bogwoodLog.getDefaultState());
		setBlockAndNotifyAdequately(world, logPos.offset(EnumFacing.SOUTH, 2), JourneyBlocks.bogwoodLog.getDefaultState());
	}

	private void placeStems(World world, BlockPos.MutableBlockPos logPos, EnumFacing direction) {
		setBlockAndNotifyAdequately(world, logPos.move(direction, 1).move(EnumFacing.UP, 1).offset(EnumFacing.DOWN, 5), JourneyBlocks.bogwoodLog.getDefaultState());
	}
}
