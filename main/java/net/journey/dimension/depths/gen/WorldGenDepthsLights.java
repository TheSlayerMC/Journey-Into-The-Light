package net.journey.dimension.depths.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenDepthsLights extends WorldGenerator {

	@Override
	public boolean generate(World w, Random r, BlockPos pos) {
		if(!w.isAirBlock(pos)) {
			return false;
		}
		else if (w.getBlockState(pos.up()).getBlock() != JourneyBlocks.depthsStone) {
			return false;
		} else {
			w.setBlockState(pos, JourneyBlocks.depthsLights.getDefaultState(), 2);
			for(int i = 0; i < 1500; i++) {
				BlockPos blockpos1 = pos.add(r.nextInt(8) - r.nextInt(8), -r.nextInt(12), r.nextInt(8) - r.nextInt(8));
				if(w.getBlockState(blockpos1).getBlock().getMaterial(null) == Material.AIR) {
					int j = 0;
					EnumFacing[] aenumfacing = EnumFacing.values();
					int k = aenumfacing.length;
					for(int l = 0; l < k; l++) {
						EnumFacing enumfacing = aenumfacing[l];
						if (w.getBlockState(blockpos1.offset(enumfacing)).getBlock() == JourneyBlocks.depthsLights) j++;
						if(j > 1) break;
					}
					if(j == 1) w.setBlockState(blockpos1, JourneyBlocks.depthsLights.getDefaultState(), 2);
				}
			}
			return true;
		}
	}

	public static class WorldGendepthsLights2 extends WorldGenerator {

		@Override
		public boolean generate(World w, Random r, BlockPos pos)  {
			if(!w.isAirBlock(pos)) {
				return false;
			}
			else if (w.getBlockState(pos.up()).getBlock() != JourneyBlocks.depthsStone) {
				return false;
			} else {
				w.setBlockState(pos, JourneyBlocks.depthsLights.getDefaultState(), 2);
				for(int i = 0; i < 1500; ++i) {
					BlockPos blockpos1 = pos.add(r.nextInt(8) - r.nextInt(8), r.nextInt(12), r.nextInt(8) - r.nextInt(8));
					if(w.getBlockState(blockpos1).getBlock().getMaterial(null) == Material.AIR) {
						int j = 0;
						EnumFacing[] aenumfacing = EnumFacing.values();
						int k = aenumfacing.length;
						for(int l = 0; l < k; l++) {
							EnumFacing enumfacing = aenumfacing[l];
							if (w.getBlockState(blockpos1.offset(enumfacing)).getBlock() == JourneyBlocks.depthsLights) j++;
							if (j > 1) break;
						}
						if(j == 1) w.setBlockState(blockpos1, JourneyBlocks.depthsLights.getDefaultState(), 2);
					}
				}
				return true;
			}
		}
	}
}