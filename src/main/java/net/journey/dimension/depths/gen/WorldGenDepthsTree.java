package net.journey.dimension.depths.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenDepthsTree extends WorldGenAbstractTree {

	private final int minTreeHeight;
	private final int metaWood;
	private final int metaLeaves;
	private static final Random rand = new Random();

	public WorldGenDepthsTree(boolean var1) {
		this(var1, rand.nextInt(10) + 10, 0, 0);
	}

	public WorldGenDepthsTree(boolean var1, int var2, int var3, int var4) {
		super(var1);
		this.minTreeHeight = var2;
		this.metaWood = var3;
		this.metaLeaves = var4;
	}

	@Override
	public boolean generate(World world, Random r, BlockPos pos) {
		int i = pos.getX() - 6, j = pos.getY() - 1, k = pos.getZ() - 6;

		this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 2, k + 1), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 2, k + 3), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 3, k + 1), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 3, k + 2), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 3, k + 3), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 4, k + 1), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 4, k + 2), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 4, k + 3), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 5, k + 1), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 5, k + 2), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 5, k + 3), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 6, k + 1), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 6, k + 2), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 6, k + 3), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k + 4), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 1), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 2), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 3), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 4), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k + 1), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k + 2), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k + 3), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k + 4), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 5, k), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 5, k + 1), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 5, k + 2), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 5, k + 3), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 5, k + 4), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 6, k), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 6, k + 1), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 6, k + 2), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 6, k + 3), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 6, k + 4), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j, k + 2), JourneyBlocks.depthsLog.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 1, k + 2), JourneyBlocks.depthsLog.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 2, k + 2), JourneyBlocks.depthsLog.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 3, k), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 3, k + 1), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 3, k + 2), JourneyBlocks.depthsLog.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 3, k + 3), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 3, k + 4), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 1), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 2), JourneyBlocks.depthsLog.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 3), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 4), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 5, k), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 5, k + 1), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 5, k + 2), JourneyBlocks.depthsLights.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 5, k + 3), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 5, k + 4), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 6, k), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 6, k + 1), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 6, k + 2), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 6, k + 3), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 6, k + 4), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 2, k), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 2, k + 4), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 3, k), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 3, k + 1), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 3, k + 2), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 3, k + 3), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 3, k + 4), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 4, k), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 4, k + 1), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 4, k + 2), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 4, k + 3), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 4, k + 4), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 5, k), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 5, k + 1), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 5, k + 2), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 5, k + 3), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 5, k + 4), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 6, k), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 6, k + 1), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 6, k + 2), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 6, k + 3), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 6, k + 4), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 2, k + 1), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 2, k + 3), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 3, k + 1), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 3, k + 2), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 3, k + 3), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 4, k + 1), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 4, k + 2), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 4, k + 3), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 5, k + 1), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 5, k + 2), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 5, k + 3), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 6, k + 1), JourneyBlocks.depthsLeaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 6, k + 2), JourneyBlocks.depthsLeaves.getDefaultState());
		return true;

	}
}