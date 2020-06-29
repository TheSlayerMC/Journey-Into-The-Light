package net.journey.dimension.base.gen;

import net.minecraft.block.Block;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenSingleBlock extends WorldGenerator {

	protected Block block;
	protected int maxLevel;
	protected int genRate;

	public WorldGenSingleBlock(Block block, int maxLevel, int genRate) {
		this.block = block;
		this.maxLevel = maxLevel;
		this.genRate = genRate;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		pos = WorldGenAPI.optimizeAndRandomize(pos, rand);
		pos = WorldGenAPI.changeHeight(pos, rand.nextInt(maxLevel + 1));

		if (pos.getY() >= 110 && rand.nextInt(genRate) != 0) return false;

		if (world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP) && block.canPlaceBlockAt(world, pos) && rand.nextInt(genRate) == 0) {
			setBlockAndNotifyAdequately(world, pos, block.getDefaultState());
			return true;
		}

        return false;
    }
}
