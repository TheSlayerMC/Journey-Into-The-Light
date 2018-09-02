package net.journey.dimension.corba.gen.trees;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.util.enums.EnumTypeLogs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.slayer.api.block.BlockModLog;

public class WorldGenCorbaSpruceTree extends WorldGenAbstractTree {

	private static IBlockState log = JourneyBlocks.logs.getDefaultState().withProperty(BlockModLog.VARIANT, EnumTypeLogs.EnumType.CORBA_LOG);
	private Block leaves = JourneyBlocks.corbaLeaves;

	public WorldGenCorbaSpruceTree() {
		super(false);
		Random r = new Random();
	}

	@Override
	public boolean generate(World w, Random r, BlockPos pos) {
		int x = pos.getX(), y = pos.getY(), z = pos.getZ();
		int l = r.nextInt(5) + 7;
		int i1 = l - r.nextInt(2) - 3;
		int j1 = l - i1;
		int k1 = 1 + r.nextInt(j1 + 1);
		boolean flag = true;

		if (y >= 1 && y + l + 1 <= 256) {
			int i2;
			int j2;
			int i3;

			for (int l1 = y; l1 <= y + 1 + l && flag; ++l1) {
				boolean flag1 = true;

				if (l1 - y < i1) {
					i3 = 0;
				} else {
					i3 = k1;
				}

				for (i2 = x - i3; i2 <= x + i3 && flag; ++i2) {
					for (j2 = z - i3; j2 <= z + i3 && flag; ++j2) {
						if (l1 >= 0 && l1 < 256) {
							Block block = w.getBlockState(new BlockPos(i2, l1, j2)).getBlock();

							if (!this.isReplaceable(w, new BlockPos(i2, l1, j2))) {
								flag = false;
							}
						} else {
							flag = false;
						}
					}
				}
			}

			if (!flag) {
				return false;
			} else {
				Block block1 = w.getBlockState(new BlockPos(x, y - 1, z)).getBlock();

				boolean isSoil = block1.canSustainPlant(w.getBlockState(new BlockPos(pos)), w,
						new BlockPos(x, y - 1, z), EnumFacing.UP,
						(BlockSapling) Blocks.SAPLING);
				if (isSoil && y < 256 - l - 1) {
					block1.onPlantGrow(w.getBlockState(new BlockPos(pos)), w,
							new BlockPos(x, y - 1, z),
							new BlockPos(x, y, z));
					i3 = 0;

					for (i2 = y + l; i2 >= y + i1; --i2) {
						for (j2 = x - i3; j2 <= x + i3; ++j2) {
							int j3 = j2 - x;

							for (int k2 = z - i3; k2 <= z + i3; ++k2) {
								int l2 = k2 - z;

								if ((Math.abs(j3) != i3 || Math.abs(l2) != i3 || i3 <= 0)
										&& w.getBlockState(new BlockPos(j2, i2, k2)).getBlock().canBeReplacedByLeaves(
												w.getBlockState(new BlockPos(pos)), w, new BlockPos(j2, i2, k2))) {
									this.setBlockAndNotifyAdequately(w, new BlockPos(j2, i2, k2),
											leaves.getDefaultState());
								}
							}
						}

						if (i3 >= 1 && i2 == y + i1 + 1) {
							--i3;
						} else if (i3 < k1) {
							++i3;
						}
					}

					for (i2 = 0; i2 < l - 1; ++i2) {
						Block block2 = w.getBlockState(new BlockPos(x, y + i2, z))
								.getBlock();

						if (block2.isAir(w.getBlockState(new BlockPos(pos)), w,
								new BlockPos(x, y + i2, z))
								|| block2.isLeaves(w.getBlockState(new BlockPos(pos)), w,
										new BlockPos(x, y + i2, z))) {
							this.setBlockAndNotifyAdequately(w, new BlockPos(x, y + i2, z),
									log);
						}
					}

					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}
}