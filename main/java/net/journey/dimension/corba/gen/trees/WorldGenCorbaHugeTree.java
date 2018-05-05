package net.journey.dimension.corba.gen.trees;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenCorbaHugeTree extends WorldGenAbstractTree {

	protected final int baseHeight;
	protected Block leaves = JourneyBlocks.corbaLeaves;
	protected int maxHeight;

	public WorldGenCorbaHugeTree(boolean b, int h, int mh) {
		super(b);
		Random r = new Random();
		this.baseHeight = h;
		this.maxHeight = mh;
	}

	protected int setHeight(Random r) {
		int i = r.nextInt(3) + this.baseHeight;
		if(this.maxHeight > 1) {
			i += r.nextInt(this.maxHeight);
		}
		return i;
	}

	private boolean idk(World w, Random r, int x, int y, int z, int m) {
		boolean flag = true;

		if (y >= 1 && y + m + 1 <= 256) {
			for (int i1 = y; i1 <= y + 1 + m; ++i1) {
				byte b0 = 2;

				if (i1 == y) {
					b0 = 1;
				}

				if (i1 >= y + 1 + m - 2) {
					b0 = 2;
				}

				for (int j1 = x - b0; j1 <= x + b0 && flag; ++j1) {
					for (int k1 = z - b0; k1 <= z + b0 && flag; ++k1) {
						if (i1 >= 0 && i1 < 256) {
							Block block = w.getBlockState(new BlockPos(j1, i1, k1)).getBlock();

							if (!this.isReplaceable(w, new BlockPos(j1, i1, k1))) {
								flag = false;
							}
						} else {
							flag = false;
						}
					}
				}
			}
			return flag;
		} else {
			return false;
		}
	}

	private boolean grow(World w, Random r, int x, int y, int z) {
		Block block = w.getBlockState(new BlockPos(x, y - 1, z)).getBlock();

		boolean isSoil = block.canSustainPlant(w, new BlockPos(x, y - 1, z), EnumFacing.UP, (BlockSapling)Blocks.sapling);
		if (isSoil && y >= 2) {
			onPlantGrow(w, x, y - 1, z, x, y, z);
			onPlantGrow(w, x + 1, y - 1, z, x, y, z);
			onPlantGrow(w, x, y - 1, z + 1, x, y, z);
			onPlantGrow(w, x + 1, y - 1, z + 1, x, y, z);
			return true;
		} else {
			return false;
		}
	}

	protected boolean gen(World w, Random r, int x, int y, int z, int m) {
		return this.idk(w, r, x, y, z, m) && this.grow(w, r, x, y, z);
	}

	protected void grow3(World w, int x, int y, int z, int m, Random r) {
		int i1 = m * m;

		for (int j1 = x - m; j1 <= x + m + 1; ++j1) {
			int k1 = j1 - x;

			for (int l1 = z - m; l1 <= z + m + 1; ++l1) {
				int i2 = l1 - z;
				int j2 = k1 - 1;
				int k2 = i2 - 1;

				if (k1 * k1 + i2 * i2 <= i1 || j2 * j2 + k2 * k2 <= i1 || k1 * k1 + k2 * k2 <= i1 || j2 * j2 + i2 * i2 <= i1) {
					Block block = w.getBlockState(new BlockPos(j1, y, l1)).getBlock();

					if (block.isAir(w, new BlockPos(j1, y, l1)) || block.isLeaves(w, new BlockPos(j1, y, l1))) {
						this.setBlockAndNotifyAdequately(w, new BlockPos(j1, y, l1), leaves.getDefaultState());
					}
				}
			}
		}
	}

	protected void grow2(World w, int x, int y, int z, int m, Random r) {
		int i1 = m * m;

		for (int j1 = x - m; j1 <= x + m; ++j1) {
			int k1 = j1 - x;

			for (int l1 = z - m; l1 <= z + m; ++l1) {
				int i2 = l1 - z;

				if (k1 * k1 + i2 * i2 <= i1) {
					Block block = w.getBlockState(new BlockPos(j1, y, l1)).getBlock();

					if (block.isAir(w, new BlockPos(j1, y, l1)) || block.isLeaves(w, new BlockPos(j1, y, l1))) {
						this.setBlockAndNotifyAdequately(w, new BlockPos(j1, y, l1), leaves.getDefaultState());
					}
				}
			}
		}
	}

	private void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ) {
		world.getBlockState(new BlockPos(x, y, z)).getBlock().onPlantGrow(world, new BlockPos(x, y, z), new BlockPos(sourceX, sourceY, sourceZ));
	}

	@Override
	public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
		return false;
	}
}