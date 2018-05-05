package net.journey.dimension.corba.gen.trees;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenCorbaSpruceTree1 extends WorldGenAbstractTree {

	private Block log = JourneyBlocks.corbaLog;
	private Block leaves = JourneyBlocks.corbaLeaves;

    public WorldGenCorbaSpruceTree1() {
        super(false);
    }

    public boolean generate(World p_76484_1_, Random p_76484_2_, BlockPos pos) {
    	int p_76484_3_ = pos.getX(), p_76484_4_ = pos.getY(), p_76484_5_ = pos.getZ();
        int l = p_76484_2_.nextInt(4) + 6;
        int i1 = 1 + p_76484_2_.nextInt(2);
        int j1 = l - i1;
        int k1 = 2 + p_76484_2_.nextInt(2);
        boolean flag = true;

        if (p_76484_4_ >= 1 && p_76484_4_ + l + 1 <= 256)
        {
            int i2;
            int l3;

            for (int l1 = p_76484_4_; l1 <= p_76484_4_ + 1 + l && flag; ++l1)
            {
                boolean flag1 = true;

                if (l1 - p_76484_4_ < i1)
                {
                    l3 = 0;
                }
                else
                {
                    l3 = k1;
                }

                for (i2 = p_76484_3_ - l3; i2 <= p_76484_3_ + l3 && flag; ++i2)
                {
                    for (int j2 = p_76484_5_ - l3; j2 <= p_76484_5_ + l3 && flag; ++j2)
                    {
                        if (l1 >= 0 && l1 < 256)
                        {
                            Block block = p_76484_1_.getBlockState(new BlockPos(i2, l1, j2)).getBlock();

                            if (!block.isAir(p_76484_1_, new BlockPos(i2, l1, j2)) && !block.isLeaves(p_76484_1_, new BlockPos(i2, l1, j2)))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                Block block1 = p_76484_1_.getBlockState(new BlockPos(p_76484_3_, p_76484_4_ - 1, p_76484_5_)).getBlock();

                boolean isSoil = block1.canSustainPlant(p_76484_1_, new BlockPos(p_76484_3_, p_76484_4_ - 1, p_76484_5_), EnumFacing.UP, (BlockSapling)Blocks.sapling);
                if (isSoil && p_76484_4_ < 256 - l - 1)
                {
                    block1.onPlantGrow(p_76484_1_, new BlockPos(p_76484_3_, p_76484_4_ - 1, p_76484_5_), new BlockPos(p_76484_3_, p_76484_4_, p_76484_5_));
                    l3 = p_76484_2_.nextInt(2);
                    i2 = 1;
                    byte b0 = 0;
                    int k2;
                    int i4;

                    for (i4 = 0; i4 <= j1; ++i4)
                    {
                        k2 = p_76484_4_ + l - i4;

                        for (int l2 = p_76484_3_ - l3; l2 <= p_76484_3_ + l3; ++l2)
                        {
                            int i3 = l2 - p_76484_3_;

                            for (int j3 = p_76484_5_ - l3; j3 <= p_76484_5_ + l3; ++j3)
                            {
                                int k3 = j3 - p_76484_5_;

                                if ((Math.abs(i3) != l3 || Math.abs(k3) != l3 || l3 <= 0) && p_76484_1_.getBlockState(new BlockPos(l2, k2, j3)).getBlock().canBeReplacedByLeaves(p_76484_1_, new BlockPos(l2, k2, j3)))
                                {
                                    this.setBlockAndNotifyAdequately(p_76484_1_, new BlockPos(l2, k2, j3), leaves.getDefaultState());
                                }
                            }
                        }

                        if (l3 >= i2)
                        {
                            l3 = b0;
                            b0 = 1;
                            ++i2;

                            if (i2 > k1)
                            {
                                i2 = k1;
                            }
                        }
                        else
                        {
                            ++l3;
                        }
                    }

                    i4 = p_76484_2_.nextInt(3);

                    for (k2 = 0; k2 < l - i4; ++k2)
                    {
                        Block block2 = p_76484_1_.getBlockState(new BlockPos(p_76484_3_, p_76484_4_ + k2, p_76484_5_)).getBlock();

                        if (block2.isAir(p_76484_1_, new BlockPos(p_76484_3_, p_76484_4_ + k2, p_76484_5_)) || block2.isLeaves(p_76484_1_, new BlockPos(p_76484_3_, p_76484_4_ + k2, p_76484_5_)))
                        {
                            this.setBlockAndNotifyAdequately(p_76484_1_, new BlockPos(p_76484_3_, p_76484_4_ + k2, p_76484_5_), log.getDefaultState());
                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }
}