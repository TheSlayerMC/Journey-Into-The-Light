package net.journey.dimension.corba.gen.trees;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenCorbaSpruceTree extends WorldGenAbstractTree {

	private Block log = JourneyBlocks.corbaLog;
	private Block leaves = JourneyBlocks.corbaLeaves;
	
    public WorldGenCorbaSpruceTree()
    {
        super(false);
        Random r = new Random();
    }

    public boolean generate(World w, Random r, BlockPos pos) {
    	int p_76484_3_ = pos.getX(), p_76484_4_ = pos.getY(), p_76484_5_ = pos.getZ();
        int l = r.nextInt(5) + 7;
        int i1 = l - r.nextInt(2) - 3;
        int j1 = l - i1;
        int k1 = 1 + r.nextInt(j1 + 1);
        boolean flag = true;

        if (p_76484_4_ >= 1 && p_76484_4_ + l + 1 <= 256)
        {
            int i2;
            int j2;
            int i3;

            for (int l1 = p_76484_4_; l1 <= p_76484_4_ + 1 + l && flag; ++l1)
            {
                boolean flag1 = true;

                if (l1 - p_76484_4_ < i1)
                {
                    i3 = 0;
                }
                else
                {
                    i3 = k1;
                }

                for (i2 = p_76484_3_ - i3; i2 <= p_76484_3_ + i3 && flag; ++i2)
                {
                    for (j2 = p_76484_5_ - i3; j2 <= p_76484_5_ + i3 && flag; ++j2)
                    {
                        if (l1 >= 0 && l1 < 256)
                        {
                            Block block = w.getBlockState(new BlockPos(i2, l1, j2)).getBlock();

                            if (!this.isReplaceable(w, new BlockPos(i2, l1, j2)))
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
                Block block1 = w.getBlockState(new BlockPos(p_76484_3_, p_76484_4_ - 1, p_76484_5_)).getBlock();

                boolean isSoil = block1.canSustainPlant(w.getBlockState(new BlockPos(pos)), w, new BlockPos(p_76484_3_, p_76484_4_ - 1, p_76484_5_), EnumFacing.UP, (BlockSapling)Blocks.SAPLING);
                if (isSoil && p_76484_4_ < 256 - l - 1)
                {
                    block1.onPlantGrow(w.getBlockState(new BlockPos(pos)), w, new BlockPos(p_76484_3_, p_76484_4_ - 1, p_76484_5_), new BlockPos(p_76484_3_, p_76484_4_, p_76484_5_));
                    i3 = 0;

                    for (i2 = p_76484_4_ + l; i2 >= p_76484_4_ + i1; --i2)
                    {
                        for (j2 = p_76484_3_ - i3; j2 <= p_76484_3_ + i3; ++j2)
                        {
                            int j3 = j2 - p_76484_3_;

                            for (int k2 = p_76484_5_ - i3; k2 <= p_76484_5_ + i3; ++k2)
                            {
                                int l2 = k2 - p_76484_5_;

                                if ((Math.abs(j3) != i3 || Math.abs(l2) != i3 || i3 <= 0) && w.getBlockState(new BlockPos(j2, i2, k2)).getBlock().canBeReplacedByLeaves(w.getBlockState(new BlockPos(pos)), w, new BlockPos(j2, i2, k2)))
                                {
                                    this.setBlockAndNotifyAdequately(w, new BlockPos(j2, i2, k2), leaves.getDefaultState());
                                }
                            }
                        }

                        if (i3 >= 1 && i2 == p_76484_4_ + i1 + 1)
                        {
                            --i3;
                        }
                        else if (i3 < k1)
                        {
                            ++i3;
                        }
                    }

                    for (i2 = 0; i2 < l - 1; ++i2)
                    {
                        Block block2 = w.getBlockState(new BlockPos(p_76484_3_, p_76484_4_ + i2, p_76484_5_)).getBlock();

                        if (block2.isAir(w.getBlockState(new BlockPos(pos)), w, new BlockPos(p_76484_3_, p_76484_4_ + i2, p_76484_5_)) || block2.isLeaves(w.getBlockState(new BlockPos(pos)), w, new BlockPos(p_76484_3_, p_76484_4_ + i2, p_76484_5_)))
                        {
                            this.setBlockAndNotifyAdequately(w, new BlockPos(p_76484_3_, p_76484_4_ + i2, p_76484_5_), log.getDefaultState());
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