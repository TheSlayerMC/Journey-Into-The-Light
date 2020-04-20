package net.journey.dimension.terrania.gen.shroom;

import net.journey.blocks.plant.BlockJourneyMushroom;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenTerrashroom extends WorldGenerator {
    /**
     * The mushroom type. 0 for brown, 1 for red.
     */
    private final Block mushroomType;

    public WorldGenTerrashroom(Block p_i46449_1_) {
        super(true);
        this.mushroomType = p_i46449_1_;
    }

    public WorldGenTerrashroom() {
        super(false);
        this.mushroomType = null;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        Block block = this.mushroomType;

        if (block == null) {
            block = rand.nextBoolean() ? JourneyBlocks.terrashroomBlockPink : JourneyBlocks.terrashroomBlockPurple;
        }

        int i = rand.nextInt(3) + 2;

        if (rand.nextInt(12) == 0) {
            i *= 2;
        }

        boolean flag = true;

        if (position.getY() >= 1 && position.getY() + i + 1 < 256) {
            for (int j = position.getY(); j <= position.getY() + 1 + i; ++j) {
                int k = 3;

                if (j <= position.getY() + 3) {
                    k = 0;
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
                    for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
                        if (j >= 0 && j < 256) {
                            IBlockState state = worldIn.getBlockState(blockpos$mutableblockpos.setPos(l, j, i1));

                            if (!state.getBlock().isAir(state, worldIn, blockpos$mutableblockpos)
                                    && !state.getBlock().isLeaves(state, worldIn, blockpos$mutableblockpos)) {
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
                Block block1 = worldIn.getBlockState(position.down()).getBlock();

                if (block1 != JourneyBlocks.terranianGrass && block1 != Blocks.MYCELIUM) {
                    return false;
                } else {
                    int k2 = position.getY() + i;

                    if (block == JourneyBlocks.terrashroomBlockPurple) {
                        k2 = position.getY() + i - rand.nextInt(5);
                    }

                    if (block == JourneyBlocks.terrashroomBlockPink) {
                        k2 = position.getY() + i - rand.nextInt(2);
                    }

                    for (int l2 = k2; l2 <= position.getY() + i; ++l2) {
                        int j3 = 1;

                        if (l2 < position.getY() + i) {
                            ++j3;
                        }

                        if (block == JourneyBlocks.terrashroomBlockPink) {
                            j3 = 2;
                        }

                        int k3 = position.getX() - j3;
                        int l3 = position.getX() + j3;
                        int j1 = position.getZ() - j3;
                        int k1 = position.getZ() + j3;

                        for (int l1 = k3; l1 <= l3; ++l1) {
                            for (int i2 = j1; i2 <= k1; ++i2) {
                                int j2 = 5;

                                if (l1 == k3) {
                                    --j2;
                                } else if (l1 == l3) {
                                    ++j2;
                                }

                                if (i2 == j1) {
                                    j2 -= 3;
                                } else if (i2 == k1) {
                                    j2 += 3;
                                }

                                BlockJourneyMushroom.EnumType BlockJourneyMushroom$enumtype = BlockJourneyMushroom.EnumType
                                        .byMetadata(j2);

                                if (block == JourneyBlocks.terrashroomBlockPink || l2 < position.getY() + i) {
                                    if ((l1 == k3 || l1 == l3) && (i2 == j1 || i2 == k1)) {
                                        continue;
                                    }

                                    if (l1 == position.getX() - (j3 - 1) && i2 == j1) {
                                        BlockJourneyMushroom$enumtype = BlockJourneyMushroom.EnumType.NORTH_WEST;
                                    }

                                    if (l1 == k3 && i2 == position.getZ() - (j3 - 1)) {
                                        BlockJourneyMushroom$enumtype = BlockJourneyMushroom.EnumType.NORTH_WEST;
                                    }

                                    if (l1 == position.getX() + (j3 - 1) && i2 == j1) {
                                        BlockJourneyMushroom$enumtype = BlockJourneyMushroom.EnumType.NORTH_EAST;
                                    }

                                    if (l1 == l3 && i2 == position.getZ() - (j3 - 1)) {
                                        BlockJourneyMushroom$enumtype = BlockJourneyMushroom.EnumType.NORTH_EAST;
                                    }

                                    if (l1 == position.getX() - (j3 - 1) && i2 == k1) {
                                        BlockJourneyMushroom$enumtype = BlockJourneyMushroom.EnumType.SOUTH_WEST;
                                    }

                                    if (l1 == k3 && i2 == position.getZ() + (j3 - 1)) {
                                        BlockJourneyMushroom$enumtype = BlockJourneyMushroom.EnumType.SOUTH_WEST;
                                    }

                                    if (l1 == position.getX() + (j3 - 1) && i2 == k1) {
                                        BlockJourneyMushroom$enumtype = BlockJourneyMushroom.EnumType.SOUTH_EAST;
                                    }

                                    if (l1 == l3 && i2 == position.getZ() + (j3 - 1)) {
                                        BlockJourneyMushroom$enumtype = BlockJourneyMushroom.EnumType.SOUTH_EAST;
                                    }
                                }

                                if (BlockJourneyMushroom$enumtype == BlockJourneyMushroom.EnumType.CENTER
                                        && l2 < position.getY() + i) {
                                    BlockJourneyMushroom$enumtype = BlockJourneyMushroom.EnumType.ALL_INSIDE;
                                }

                                if (position.getY() >= position.getY() + i - 1
                                        || BlockJourneyMushroom$enumtype != BlockJourneyMushroom.EnumType.ALL_INSIDE) {
                                    BlockPos blockpos = new BlockPos(l1, l2, i2);
                                    IBlockState state = worldIn.getBlockState(blockpos);

                                    if (state.getBlock().canBeReplacedByLeaves(state, worldIn, blockpos)) {
                                        this.setBlockAndNotifyAdequately(worldIn, blockpos, block.getDefaultState()
                                                .withProperty(BlockJourneyMushroom.VARIANT, BlockJourneyMushroom$enumtype));
                                    }
                                }
                            }
                        }
                    }

                    for (int i3 = 0; i3 < i; ++i3) {
                        IBlockState iblockstate = worldIn.getBlockState(position.up(i3));

                        if (iblockstate.getBlock().canBeReplacedByLeaves(iblockstate, worldIn, position.up(i3))) {
                            this.setBlockAndNotifyAdequately(worldIn, position.up(i3), block.getDefaultState()
                                    .withProperty(BlockJourneyMushroom.VARIANT, BlockJourneyMushroom.EnumType.STEM));
                        }
                    }

                    return true;
                }
            }
        } else {
            return false;
        }
    }
}