package net.journey.dimension.wither.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenWitherLights extends WorldGenerator {

	@Override
	public boolean generate(World w, Random r, BlockPos pos) {
		if(!w.isAirBlock(pos)) {
			return false;
		}
		else if (w.getBlockState(pos.up()).getBlock() != JourneyBlocks.withanRockReinforced) {
			return false;
		} else {
			w.setBlockState(pos, JourneyBlocks.withanLight.getDefaultState(), 2);
			for(int i = 0; i < 1500; i++) {
				BlockPos blockpos1 = pos.add(r.nextInt(8) - r.nextInt(8), -r.nextInt(12), r.nextInt(8) - r.nextInt(8));
				if(w.getBlockState(blockpos1).getBlock().getMaterial() == Material.air) {
					int j = 0;
					EnumFacing[] aenumfacing = EnumFacing.values();
					int k = aenumfacing.length;
					for(int l = 0; l < k; l++) {
						EnumFacing enumfacing = aenumfacing[l];
						if (w.getBlockState(blockpos1.offset(enumfacing)).getBlock() == JourneyBlocks.withanLight) j++;
						if(j > 1) break;
					}
					if(j == 1) w.setBlockState(blockpos1, JourneyBlocks.withanLight.getDefaultState(), 2);
				}
			}
			return true;
		}
	}

	public static class WorldGenwithanLight2 extends WorldGenerator {

		@Override
		public boolean generate(World w, Random r, BlockPos pos)  {
			if(!w.isAirBlock(pos)) {
				return false;
			}
			else if (w.getBlockState(pos.up()).getBlock() != JourneyBlocks.withanRockReinforced) {
				return false;
			} else {
				w.setBlockState(pos, JourneyBlocks.withanLight.getDefaultState(), 2);
				for(int i = 0; i < 1500; ++i) {
					BlockPos blockpos1 = pos.add(r.nextInt(8) - r.nextInt(8), r.nextInt(12), r.nextInt(8) - r.nextInt(8));
					if(w.getBlockState(blockpos1).getBlock().getMaterial() == Material.air) {
						int j = 0;
						EnumFacing[] aenumfacing = EnumFacing.values();
						int k = aenumfacing.length;
						for(int l = 0; l < k; l++) {
							EnumFacing enumfacing = aenumfacing[l];
							if (w.getBlockState(blockpos1.offset(enumfacing)).getBlock() == JourneyBlocks.withanLight) j++;
							if (j > 1) break;
						}
						if(j == 1) w.setBlockState(blockpos1, JourneyBlocks.withanLight.getDefaultState(), 2);
					}
				}
				return true;
			}
		}
	}
}