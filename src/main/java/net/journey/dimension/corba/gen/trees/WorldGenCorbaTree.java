
package net.journey.dimension.corba.gen.trees;
import java.util.Random;

import net.journey.api.block.GroundPredicate;
import net.journey.dimension.base.gen.JWorldGenHugeTrees;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Plane;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenCorbaTree extends JWorldGenHugeTrees {
    private static final IBlockState LOG;
    private static final IBlockState LEAVES;

    public WorldGenCorbaTree(boolean notify) {
        super(notify, 6, 10, JourneyBlocks.corbaLog.getDefaultState(), JourneyBlocks.corbaLeaves.getDefaultState());
        setGroundPredicate(GroundPredicate.COMMON_AND_CORBA_GRASS);
    }

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int i = rand.nextInt(3) + rand.nextInt(4) + 7;
        int j = position.getX();
        int k = position.getY();
        int l = position.getZ();
        if (k >= 1 && k + i + 1 < 256) {
            BlockPos blockpos = position.down();
            IBlockState state = worldIn.getBlockState(blockpos);
            boolean isSoil = state.getBlock() == JourneyBlocks.corbaGrass;
            if (isSoil && position.getY() < worldIn.getHeight() - i - 1) {
                if (!this.placeTreeOfHeight(worldIn, position, i)) {
                    return false;
                } else {
                    this.onPlantGrow(worldIn, blockpos, position);
                    this.onPlantGrow(worldIn, blockpos.east(), position);
                    this.onPlantGrow(worldIn, blockpos.south(), position);
                    this.onPlantGrow(worldIn, blockpos.south().east(), position);
                    EnumFacing enumfacing = Plane.HORIZONTAL.random(rand);
                    int i1 = i - rand.nextInt(8);
                    int j1 = 2 - rand.nextInt(7);
                    int k1 = j;
                    int l1 = l;
                    int i2 = k + i - 1;

                    int k3;
                    int j4;
                    for(k3 = 0; k3 < i; ++k3) {
                        if (k3 >= i1 && j1 > 0) {
                            k1 += enumfacing.getXOffset();
                            l1 += enumfacing.getZOffset();
                            --j1;
                        }

                        j4 = k + k3;
                        BlockPos blockpos1 = new BlockPos(k1, j4, l1);
                        state = worldIn.getBlockState(blockpos1);
                        if (state.getBlock().isAir(state, worldIn, blockpos1) || state.getBlock().isLeaves(state, worldIn, blockpos1)) {
                            this.placeLogAt(worldIn, blockpos1);
                            this.placeLogAt(worldIn, blockpos1.east());
                            this.placeLogAt(worldIn, blockpos1.south());
                            this.placeLogAt(worldIn, blockpos1.east().south());
                        }
                    }

                    for(k3 = -2; k3 <= 0; ++k3) {
                        for(j4 = -2; j4 <= 0; ++j4) {
                            int k4 = -1;
                            this.placeLeafAt(worldIn, k1 + k3, i2 + k4, l1 + j4);
                            this.placeLeafAt(worldIn, 1 + k1 - k3, i2 + k4, l1 + j4);
                            this.placeLeafAt(worldIn, k1 + k3, i2 + k4, 1 + l1 - j4);
                            this.placeLeafAt(worldIn, 1 + k1 - k3, i2 + k4, 1 + l1 - j4);
                            if ((k3 > -2 || j4 > -1) && (k3 != -1 || j4 != -2)) {
                                k4 = 1;
                                this.placeLeafAt(worldIn, k1 + k3, i2 + k4, l1 + j4);
                                this.placeLeafAt(worldIn, 1 + k1 - k3, i2 + k4, l1 + j4);
                                this.placeLeafAt(worldIn, k1 + k3, i2 + k4, 1 + l1 - j4);
                                this.placeLeafAt(worldIn, 1 + k1 - k3, i2 + k4, 1 + l1 - j4);
                            }
                        }
                    }

                    if (rand.nextBoolean()) {
                        this.placeLeafAt(worldIn, k1, i2 + 2, l1);
                        this.placeLeafAt(worldIn, k1 + 1, i2 + 2, l1);
                        this.placeLeafAt(worldIn, k1 + 1, i2 + 2, l1 + 1);
                        this.placeLeafAt(worldIn, k1, i2 + 2, l1 + 1);
                    }

                    for(k3 = -3; k3 <= 4; ++k3) {
                        for(j4 = -3; j4 <= 4; ++j4) {
                            if ((k3 != -3 || j4 != -3) && (k3 != -3 || j4 != 4) && (k3 != 4 || j4 != -3) && (k3 != 4 || j4 != 4) && (Math.abs(k3) < 3 || Math.abs(j4) < 3)) {
                                this.placeLeafAt(worldIn, k1 + k3, i2, l1 + j4);
                            }
                        }
                    }

                    for(k3 = -1; k3 <= 2; ++k3) {
                        for(j4 = -1; j4 <= 2; ++j4) {
                            if ((k3 < 0 || k3 > 1 || j4 < 0 || j4 > 1) && rand.nextInt(3) <= 0) {
                                int l4 = rand.nextInt(3) + 2;

                                int k5;
                                for(k5 = 0; k5 < l4; ++k5) {
                                    this.placeLogAt(worldIn, new BlockPos(j + k3, i2 - k5 - 1, l + j4));
                                }

                                int l5;
                                for(k5 = -1; k5 <= 1; ++k5) {
                                    for(l5 = -1; l5 <= 1; ++l5) {
                                        this.placeLeafAt(worldIn, k1 + k3 + k5, i2, l1 + j4 + l5);
                                    }
                                }

                                for(k5 = -2; k5 <= 2; ++k5) {
                                    for(l5 = -2; l5 <= 2; ++l5) {
                                        if (Math.abs(k5) != 2 || Math.abs(l5) != 2) {
                                            this.placeLeafAt(worldIn, k1 + k3 + k5, i2 - 1, l1 + j4 + l5);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    return true;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean placeTreeOfHeight(World worldIn, BlockPos pos, int height) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        MutableBlockPos blockpos$mutableblockpos = new MutableBlockPos();

        for(int l = 0; l <= height + 1; ++l) {
            int i1 = 1;
            if (l == 0) {
                i1 = 0;
            }

            if (l >= height - 1) {
                i1 = 2;
            }

            for(int j1 = -i1; j1 <= i1; ++j1) {
                for(int k1 = -i1; k1 <= i1; ++k1) {
                    if (!this.isReplaceable(worldIn, blockpos$mutableblockpos.setPos(i + j1, j + l, k + k1))) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private void placeLogAt(World worldIn, BlockPos pos) {
        if (this.canGrowInto(worldIn.getBlockState(pos).getBlock())) {
            this.setBlockAndNotifyAdequately(worldIn, pos, LOG);
        }

    }

    private void placeLeafAt(World worldIn, int x, int y, int z) {
        BlockPos blockpos = new BlockPos(x, y, z);
        IBlockState state = worldIn.getBlockState(blockpos);
        if (state.getBlock().isAir(state, worldIn, blockpos)) {
            this.setBlockAndNotifyAdequately(worldIn, blockpos, LEAVES);
        }

    }

    private void onPlantGrow(World world, BlockPos pos, BlockPos source) {
        IBlockState state = world.getBlockState(pos);
        state.getBlock().onPlantGrow(state, world, pos, source);
    }

    static {
        LOG = JourneyBlocks.corbaLog.getDefaultState();
        LEAVES = JourneyBlocks.corbaLeaves.getDefaultState();
    }
}
