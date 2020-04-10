package net.journey.dimension.overworld.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.blocks.BlockCaveVine;
import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.block.BlockModBush;
import net.slayer.api.block.BlockModVine;

public class WorldGenCaveVines extends WorldGenerator {
	
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		for (int i = 0; i < 64; ++i) {
            BlockPos blockpos = position.add(rand.nextInt(4) - rand.nextInt(4), 0, rand.nextInt(4) - rand.nextInt(4));
            if (worldIn.isAirBlock(blockpos) && 
            	worldIn.getBlockState(blockpos.up()).getBlock() == Blocks.STONE && 
            	BlockCaveVine.canPlaceBelow(worldIn, position)) { 
            	setBlockAndNotifyAdequately(worldIn, blockpos, JourneyBlocks.caveVine.getDefaultState());
			}
		}
		return true;
	}
}