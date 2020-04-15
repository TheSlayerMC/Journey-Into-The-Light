package net.journey.dimension.euca.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.blocks.BlockEucaPumpkin;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenEucaPumpkin extends WorldGenerator {

	@Override
	public boolean generate(World worldIn, Random r, BlockPos b) {
		for(int i = 0; i < 30; ++i) {
			BlockPos blockpos1 = b.add(r.nextInt(8) - r.nextInt(8), r.nextInt(4) - r.nextInt(4), r.nextInt(8) - r.nextInt(8));
			if(worldIn.isAirBlock(blockpos1) && worldIn.getBlockState(blockpos1.down()).getBlock() == JourneyBlocks.eucaGrass)
				worldIn.setBlockState(blockpos1, JourneyBlocks.eucaPumpkin.getDefaultState().withProperty(BlockEucaPumpkin.FACING, EnumFacing.Plane.HORIZONTAL.random(r)), 2);
		}
		return true;
	}
}