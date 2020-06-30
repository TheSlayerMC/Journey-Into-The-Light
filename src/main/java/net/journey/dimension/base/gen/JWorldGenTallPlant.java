package net.journey.dimension.base.gen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class JWorldGenTallPlant extends WorldGenerator {

	private final IBlockState plantBloom;
	private final IBlockState plantStem;

	public JWorldGenTallPlant(IBlockState bloom, IBlockState stem) {
		plantBloom = bloom;
		plantStem = stem;
	}

	@Override
	public boolean generate(World world, Random random, BlockPos blockPos) {
		BlockPos.MutableBlockPos placePos = new BlockPos.MutableBlockPos(WorldGenAPI.findPosAboveSurface(world, WorldGenAPI.optimizeAndRandomize(blockPos, random))).move(EnumFacing.DOWN);

		int bottomHeight = 1 + random.nextInt(5);

		if (world.getBlockState(placePos.up()) == Blocks.AIR.getDefaultState() && plantStem.getBlock().canPlaceBlockAt(world, placePos)) {
			for (int i = 0; i < bottomHeight; i++) {
				setBlockAndNotifyAdequately(world, placePos.move(EnumFacing.UP), plantStem);
			}
			setBlockAndNotifyAdequately(world, placePos.move(EnumFacing.UP), plantBloom);
		}
		return true;
	}
}
