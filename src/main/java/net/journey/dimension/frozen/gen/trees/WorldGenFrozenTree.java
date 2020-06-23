//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package net.journey.dimension.frozen.gen.trees;

import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class WorldGenFrozenTree extends WorldGenAbstractTree {
    private static final IBlockState TRUNK;
    private static final IBlockState LEAF;

    static {
        TRUNK = JourneyBlocks.frozenLog.getDefaultState();
        LEAF = JourneyBlocks.frozenLeaves.getDefaultState();
    }

    public WorldGenFrozenTree(boolean notify) {
        super(notify);
    }

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int i = rand.nextInt(16) + 9;
        int j = 1 + rand.nextInt(4);
        int k = i - j;
        int l = 2 + rand.nextInt(4);
        boolean flag = true;
        if (position.getY() >= 1 && position.getY() + i + 1 <= worldIn.getHeight()) {
            int j3;
            for (int i1 = position.getY(); i1 <= position.getY() + 1 + i && flag; ++i1) {
                int j1;
                if (i1 - position.getY() < j) {
                    j1 = 0;
                } else {
                    j1 = l;
                }

                MutableBlockPos blockpos$mutableblockpos = new MutableBlockPos();

                for (j3 = position.getX() - j1; j3 <= position.getX() + j1 && flag; ++j3) {
                    for (int l1 = position.getZ() - j1; l1 <= position.getZ() + j1 && flag; ++l1) {
                        if (i1 >= 0 && i1 < worldIn.getHeight()) {
                            IBlockState state = worldIn.getBlockState(blockpos$mutableblockpos.setPos(j3, i1, l1));
                            if (!state.getBlock().isAir(state, worldIn, blockpos$mutableblockpos.setPos(j3, i1, l1)) && !state.getBlock().isLeaves(state, worldIn, blockpos$mutableblockpos.setPos(j3, i1, l1))) {
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
                BlockPos down = position.down();
                IBlockState state = worldIn.getBlockState(down);
                if (state.getBlock() == JourneyBlocks.frozenGrass && position.getY() < worldIn.getHeight() - i - 1) {
                    state.getBlock().onPlantGrow(state, worldIn, down, position);
                    int i3 = rand.nextInt(2);
                    j3 = 1;
                    int k3 = 0;

                    int j4;
                    int i4;
                    for (i4 = 0; i4 <= k; ++i4) {
                        j4 = position.getY() + i - i4;

                        for (int i2 = position.getX() - i3; i2 <= position.getX() + i3; ++i2) {
                            int j2 = i2 - position.getX();

                            for (int k2 = position.getZ() - i3; k2 <= position.getZ() + i3; ++k2) {
                                int l2 = k2 - position.getZ();
                                if (Math.abs(j2) != i3 || Math.abs(l2) != i3 || i3 <= 0) {
                                    BlockPos blockpos = new BlockPos(i2, j4, k2);
                                    state = worldIn.getBlockState(blockpos);
                                    if (state.getBlock().canBeReplacedByLeaves(state, worldIn, blockpos)) {
                                        this.setBlockAndNotifyAdequately(worldIn, blockpos, LEAF);
                                    }
                                }
                            }
                        }

                        if (i3 >= j3) {
                            i3 = k3;
                            k3 = 1;
                            ++j3;
                            if (j3 > l) {
                                j3 = l;
                            }
                        } else {
                            ++i3;
                        }
                    }

                    i4 = rand.nextInt(3);

                    for (j4 = 0; j4 < i - i4; ++j4) {
                        BlockPos upN = position.up(j4);
                        state = worldIn.getBlockState(upN);
                        if (state.getBlock().isAir(state, worldIn, upN) || state.getBlock().isLeaves(state, worldIn, upN)) {
                            this.setBlockAndNotifyAdequately(worldIn, position.up(j4), TRUNK);
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
