package net.journey.dimension.corba.gen.trees;

import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class WorldGenCorbaSwampTree extends WorldGenAbstractTree {

    private static IBlockState TRUNK;
    private static IBlockState LEAF;

    public WorldGenCorbaSwampTree() {
        super(false);
    }

    public WorldGenCorbaSwampTree(boolean doBlockNotify) {
        super(doBlockNotify);
        TRUNK = JourneyBlocks.bogwoodLog.getDefaultState();
        LEAF = JourneyBlocks.bogwoodLeaves.getDefaultState();
    }

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int i = rand.nextInt(2) + rand.nextInt(2) + 4;
        boolean flag = true;
        if (position.getY() >= 1 && position.getY() + i + 1 <= 256) {
            int k2;
            for(int j = position.getY(); j <= position.getY() + 1 + i; ++j) {
                int k = 1;
                if (j == position.getY()) {
                    k = 0;
                }

                if (j >= position.getY() + 1 + i - 2) {
                    k = 2;
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for(int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
                    for(k2 = position.getZ() - k; k2 <= position.getZ() + k && flag; ++k2) {
                        if (j >= 0 && j < 256) {
                            if (!this.isReplaceable(worldIn, blockpos$mutableblockpos.setPos(l, j, k2))) {
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
                boolean isSoil = state.getBlock().canSustainPlant(state, worldIn, down, EnumFacing.UP, (BlockSapling) Blocks.SAPLING);
                if (isSoil && position.getY() < worldIn.getHeight() - i - 1) {
                    state.getBlock().onPlantGrow(state, worldIn, down, position);
                    EnumFacing enumfacing = EnumFacing.Plane.HORIZONTAL.random(rand);
                    k2 = i - rand.nextInt(10) - 1;
                    int l2 = 10 - rand.nextInt(6);
                    int i3 = position.getX();
                    int j1 = position.getZ();
                    int k1 = 0;

                    int k3;
                    for(int l1 = 0; l1 < i; ++l1) {
                        k3 = position.getY() + l1;
                        if (l1 >= k2 && l2 > 0) {
                            i3 += enumfacing.getXOffset();
                            j1 += enumfacing.getZOffset();
                            --l2;
                        }

                        BlockPos blockpos = new BlockPos(i3, k3, j1);
                        state = worldIn.getBlockState(blockpos);
                        if (state.getBlock().isAir(state, worldIn, blockpos) || state.getBlock().isLeaves(state, worldIn, blockpos)) {
                            this.placeLogAt(worldIn, blockpos);
                            k1 = k3;
                        }
                    }

                    BlockPos blockpos2 = new BlockPos(i3, k1, j1);

                    int l3;
                    for(k3 = -5; k3 <= 5; ++k3) {
                        for(l3 = -5; l3 <= 5; ++l3) {
                            if (Math.abs(k3) != 5 || Math.abs(l3) != 5) {
                                this.placeLeafAt(worldIn, blockpos2.add(k3, 0, l3));
                            }
                        }
                    }

                    blockpos2 = blockpos2.up();

                    for(k3 = -1; k3 <= 1; ++k3) {
                        for(l3 = -1; l3 <= 1; ++l3) {
                            this.placeLeafAt(worldIn, blockpos2.add(k3, 0, l3));
                        }
                    }

                    this.placeLeafAt(worldIn, blockpos2.east(2));
                    this.placeLeafAt(worldIn, blockpos2.west(2));
                    this.placeLeafAt(worldIn, blockpos2.south(2));
                    this.placeLeafAt(worldIn, blockpos2.north(2));
                    i3 = position.getX();
                    j1 = position.getZ();
                    EnumFacing enumfacing1 = EnumFacing.Plane.HORIZONTAL.random(rand);
                    if (enumfacing1 != enumfacing) {
                        l3 = k2 - rand.nextInt(6) - 1;
                        int k4 = 1 + rand.nextInt(7);
                        k1 = 0;

                        int j5;
                        for(int l4 = l3; l4 < i && k4 > 0; --k4) {
                            if (l4 >= 1) {
                                j5 = position.getY() + l4;
                                i3 += enumfacing1.getXOffset();
                                j1 += enumfacing1.getZOffset();
                                BlockPos blockpos1 = new BlockPos(i3, j5, j1);
                                state = worldIn.getBlockState(blockpos1);
                                if (state.getBlock().isAir(state, worldIn, blockpos1) || state.getBlock().isLeaves(state, worldIn, blockpos1)) {
                                    this.placeLogAt(worldIn, blockpos1);
                                    k1 = j5;
                                }
                            }

                            ++l4;
                        }

                        if (k1 > 0) {
                            BlockPos blockpos3 = new BlockPos(i3, k1, j1);

                            int l5;
                            for(j5 = -2; j5 <= 2; ++j5) {
                                for(l5 = -2; l5 <= 2; ++l5) {
                                    if (Math.abs(j5) != 2 || Math.abs(l5) != 2) {
                                        this.placeLeafAt(worldIn, blockpos3.add(j5, 0, l5));
                                    }
                                }
                            }

                            blockpos3 = blockpos3.up();

                            for(j5 = -1; j5 <= 1; ++j5) {
                                for(l5 = -1; l5 <= 1; ++l5) {
                                    this.placeLeafAt(worldIn, blockpos3.add(j5, 0, l5));
                                }
                            }
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

    private void placeLogAt(World worldIn, BlockPos pos) {
        this.setBlockAndNotifyAdequately(worldIn, pos, TRUNK);
    }

    private void placeLeafAt(World worldIn, BlockPos pos) {
        IBlockState state = worldIn.getBlockState(pos);
        if (state.getBlock().isAir(state, worldIn, pos) || state.getBlock().isLeaves(state, worldIn, pos)) {
            this.setBlockAndNotifyAdequately(worldIn, pos, LEAF);
        }

    }

    static {
        
    }
}
