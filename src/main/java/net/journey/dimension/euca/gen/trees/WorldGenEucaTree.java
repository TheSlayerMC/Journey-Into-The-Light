package net.journey.dimension.euca.gen.trees;

import net.journey.api.block.GroundPredicate;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenEucaTree extends WorldGenerator {

	protected GroundPredicate groundPredicate = GroundPredicate.COMMON_AND_EUCA_GRASS;

	private Block leaves;
	private Block log;

	private final int treeHeight;
	private final int leafSize;

	public WorldGenEucaTree() {
		super(false);
		leaves = JourneyBlocks.eucaGoldLeaves;
		log = JourneyBlocks.eucaGoldLog;
		treeHeight = 7;
		leafSize = 6;
	}

	public WorldGenEucaTree(boolean doBlockNotify, Block log, Block leaves, int treeHeight, int leafSize) {
		super(doBlockNotify);
		this.leaves = leaves;
		this.log = log;
		this.treeHeight = treeHeight;
		this.leafSize = leafSize;
	}

	public WorldGenEucaTree setLeaves(Block leaves) {
		this.leaves = leaves;
		return this;
	}

	public WorldGenEucaTree setLog(Block log) {
		this.log = log;
		return this;
	}

	@Override
	public boolean generate(World world, Random random, BlockPos blockPos) {
		int treeHeight = random.nextInt(2) + random.nextInt(2) + this.treeHeight;

		BlockPos soilPos = blockPos.down();
		IBlockState soilState = world.getBlockState(soilPos);
		boolean isSoil = groundPredicate.testGround(world, soilPos, soilState, EnumFacing.UP);

		BlockPos.MutableBlockPos basePos = new BlockPos.MutableBlockPos(WorldGenAPI.findPosAboveSurface(world, blockPos)).move(EnumFacing.DOWN);
		BlockPos.MutableBlockPos logPos = basePos;

		if (isSoil) {

			BlockPos.MutableBlockPos stumpPos = basePos;

			int stumpHeight = random.nextInt(2) + 2;

			for (int i = 0; i < stumpHeight; i++) {
				placeStumps(world, stumpPos);
			}

			for (int i = 0; i < treeHeight; i++) {
				placeLog(world, logPos);
			}

			BlockPos.MutableBlockPos leafPos = logPos.move(EnumFacing.DOWN);

			for (int j5 = 0; j5 <= 10; ++j5) {
				for (int l5 = 0; l5 <= 10; ++l5) {
					createCrown(world, leafPos, leafSize);
				}
			}
		}
		return true;
	}

	private void placeLog(World world, BlockPos.MutableBlockPos pos) {
		setBlockAndNotifyAdequately(world, pos.move(EnumFacing.UP), log.getDefaultState());
	}

	private void placeStumps(World world, BlockPos.MutableBlockPos logPos) {
		setBlockAndNotifyAdequately(world, logPos.move(EnumFacing.UP).offset(EnumFacing.EAST), log.getDefaultState());
		setBlockAndNotifyAdequately(world, logPos.offset(EnumFacing.WEST), log.getDefaultState());
		setBlockAndNotifyAdequately(world, logPos.offset(EnumFacing.NORTH), log.getDefaultState());
		setBlockAndNotifyAdequately(world, logPos.offset(EnumFacing.SOUTH), log.getDefaultState());
	}

	private void createCrown(World world, BlockPos pos, int size) {
		pos = pos.offset(EnumFacing.UP, 2);
		for (byte x = 0; x <= size; x++) {
			for (byte y = 0; y <= size; y++) {
				for (byte z = 0; z <= size; z++) {

					int distance;

					if (x >= y & x >= z) distance = x + (Math.max(y, z) >> 1) + (Math.min(y, z) >> 1);

					else if (y >= x & y >= z) distance = y + (Math.max(x, z) >> 1) + (Math.min(x, z) >> 1);

					else distance = z + (Math.max(x, y) >> 1) + (Math.min(x, y) >> 1);

					if (distance <= size) {
						placeLeaves(world, pos.add(+x, +y, +z));
						placeLeaves(world, pos.add(+x, +y, -z));
						placeLeaves(world, pos.add(-x, +y, +z));
						placeLeaves(world, pos.add(-x, +y, -z));
						placeLeaves(world, pos.add(+x, -y, +z));
						placeLeaves(world, pos.add(+x, -y, -z));
						placeLeaves(world, pos.add(-x, -y, +z));
						placeLeaves(world, pos.add(-x, -y, -z));
					}
				}
			}
		}
	}

	private void placeLeaves(World world, BlockPos pos) {
		setBlockAndNotifyAdequately(world, pos, leaves.getDefaultState());
	}
}