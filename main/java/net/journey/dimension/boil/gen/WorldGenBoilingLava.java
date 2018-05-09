package net.journey.dimension.boil.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBoilingLava extends WorldGenerator {
	
	private Block gen;

	public WorldGenBoilingLava(Block gen) {
		this.gen = gen;
	}

	@Override
	public boolean generate(World par1World, Random par2Random, BlockPos pos) {
		int par3 = pos.getX(), par4 = pos.getY(), par5 = pos.getZ();
		par3 -= 8;

		for(par5 -= 8; par4 > 5 && par1World.isAirBlock(pos); --par4) {
			;
		}

		if(par4 <= 4) {
			return false;
		} else {
			par4 -= 4;
			boolean[] aboolean = new boolean[2048];
			int l = par2Random.nextInt(4) + 4;
			int i1;

			for(i1 = 0; i1 < l; ++i1) {
				double d0 = par2Random.nextDouble() * 6.0D + 3.0D;
				double d1 = par2Random.nextDouble() * 4.0D + 2.0D;
				double d2 = par2Random.nextDouble() * 6.0D + 3.0D;
				double d3 = par2Random.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
				double d4 = par2Random.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
				double d5 = par2Random.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

				for(int k1 = 1; k1 < 15; ++k1) {
					for(int l1 = 1; l1 < 15; ++l1) {
						for(int i2 = 1; i2 < 7; ++i2) {
							double d6 = (k1 - d3) / (d0 / 2.0D);
							double d7 = (i2 - d4) / (d1 / 2.0D);
							double d8 = (l1 - d5) / (d2 / 2.0D);
							double d9 = d6 * d6 + d7 * d7 + d8 * d8;

							if(d9 < 1.0D) {
								aboolean[(k1 * 16 + l1) * 8 + i2] = true;
							}
						}
					}
				}
			}

			int j1;
			int j2;
			boolean flag;

			for(i1 = 0; i1 < 16; ++i1) {
				for(j2 = 0; j2 < 16; ++j2) {
					for(j1 = 0; j1 < 8; ++j1) {
						flag = !aboolean[(i1 * 16 + j2) * 8 + j1] && (i1 < 15 && aboolean[((i1 + 1) * 16 + j2) * 8 + j1] || i1 > 0 && aboolean[((i1 - 1) * 16 + j2) * 8 + j1] || j2 < 15 && aboolean[(i1 * 16 + j2 + 1) * 8 + j1] || j2 > 0 && aboolean[(i1 * 16 + (j2 - 1)) * 8 + j1] || j1 < 7 && aboolean[(i1 * 16 + j2) * 8 + j1 + 1] || j1 > 0 && aboolean[(i1 * 16 + j2) * 8 + (j1 - 1)]);

						if(flag) {
							Material material = par1World.getBlockState(new BlockPos(par3 + i1, par4 + j1, par5 + j2)).getBlock().getMaterial(null);

							if(j1 >= 4 && material.isLiquid()) {
								return false;
							}

							if(j1 < 4 && !material.isSolid() && par1World.getBlockState(new BlockPos(par3 + i1, par4 + j1, par5 + j2)) != this.gen) {
								return false;
							}
						}
					}
				}
			}

			for(i1 = 0; i1 < 16; ++i1) {
				for(j2 = 0; j2 < 16; ++j2) {
					for(j1 = 0; j1 < 8; ++j1) {
						if(aboolean[(i1 * 16 + j2) * 8 + j1]) {
							par1World.setBlockState(new BlockPos(par3 + i1, par4 + j1, par5 + j2), j1 >= 4 ? Blocks.AIR.getDefaultState() : this.gen.getDefaultState(), 2);
						}
					}
				}
			}
			
			for(i1 = 0; i1 < 16; ++i1) {
				for(j2 = 0; j2 < 16; ++j2) {
					for(j1 = 0; j1 < 8; ++j1) {
						flag = !aboolean[(i1 * 16 + j2) * 8 + j1] && (i1 < 15 && aboolean[((i1 + 1) * 16 + j2) * 8 + j1] || i1 > 0 && aboolean[((i1 - 1) * 16 + j2) * 8 + j1] || j2 < 15 && aboolean[(i1 * 16 + j2 + 1) * 8 + j1] || j2 > 0 && aboolean[(i1 * 16 + (j2 - 1)) * 8 + j1] || j1 < 7 && aboolean[(i1 * 16 + j2) * 8 + j1 + 1] || j1 > 0 && aboolean[(i1 * 16 + j2) * 8 + (j1 - 1)]);

						if(flag && (j1 < 4 || par2Random.nextInt(2) != 0) && par1World.getBlockState(new BlockPos(par3 + i1, par4 + j1, par5 + j2)).getBlock().getMaterial(null).isSolid()) {
							par1World.setBlockState(new BlockPos(par3 + i1, par4 + j1, par5 + j2), JourneyBlocks.ashBlock.getDefaultState(), 2);
						}
					}
				}
			}
			return true;
		}
	}
}