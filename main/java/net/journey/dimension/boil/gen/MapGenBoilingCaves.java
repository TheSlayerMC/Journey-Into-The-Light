package net.journey.dimension.boil.gen;

import java.util.Random;

import com.google.common.base.Objects;

import net.journey.JourneyBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenBase;

public class MapGenBoilingCaves extends MapGenBase {

    protected void func_180703_a(long p_180703_1_, int p_180703_3_, int p_180703_4_, ChunkPrimer p_180703_5_, double p_180703_6_, double p_180703_8_, double p_180703_10_) {
        this.func_180702_a(p_180703_1_, p_180703_3_, p_180703_4_, p_180703_5_, p_180703_6_, p_180703_8_, p_180703_10_, 1.0F + this.rand.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
    }

    protected void func_180702_a(long p_180702_1_, int p_180702_3_, int p_180702_4_, ChunkPrimer p_180702_5_, double p_180702_6_, double p_180702_8_, double p_180702_10_, float p_180702_12_, float p_180702_13_, float p_180702_14_, int p_180702_15_, int p_180702_16_, double p_180702_17_) {
        double d4 = p_180702_3_ * 16 + 8;
        double d5 = p_180702_4_ * 16 + 8;
        float f3 = 0.0F;
        float f4 = 0.0F;
        Random random = new Random(p_180702_1_);

        if (p_180702_16_ <= 0)
        {
            int j1 = this.range * 16 - 16;
            p_180702_16_ = j1 - random.nextInt(j1 / 4);
        }

        boolean flag2 = false;

        if (p_180702_15_ == -1)
        {
            p_180702_15_ = p_180702_16_ / 2;
            flag2 = true;
        }

        int k1 = random.nextInt(p_180702_16_ / 2) + p_180702_16_ / 4;

        for (boolean flag = random.nextInt(6) == 0; p_180702_15_ < p_180702_16_; ++p_180702_15_)
        {
            double d6 = 1.5D + (double)(MathHelper.sin(p_180702_15_ * (float)Math.PI / p_180702_16_) * p_180702_12_ * 1.0F);
            double d7 = d6 * p_180702_17_;
            float f5 = MathHelper.cos(p_180702_14_);
            float f6 = MathHelper.sin(p_180702_14_);
            p_180702_6_ += (double)(MathHelper.cos(p_180702_13_) * f5);
            p_180702_8_ += f6;
            p_180702_10_ += (double)(MathHelper.sin(p_180702_13_) * f5);

            if (flag)
            {
                p_180702_14_ *= 0.92F;
            }
            else
            {
                p_180702_14_ *= 0.7F;
            }

            p_180702_14_ += f4 * 0.1F;
            p_180702_13_ += f3 * 0.1F;
            f4 *= 0.9F;
            f3 *= 0.75F;
            f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            f3 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;

            if (!flag2 && p_180702_15_ == k1 && p_180702_12_ > 1.0F && p_180702_16_ > 0)
            {
                this.func_180702_a(random.nextLong(), p_180702_3_, p_180702_4_, p_180702_5_, p_180702_6_, p_180702_8_, p_180702_10_, random.nextFloat() * 0.5F + 0.5F, p_180702_13_ - ((float)Math.PI / 2F), p_180702_14_ / 3.0F, p_180702_15_, p_180702_16_, 1.0D);
                this.func_180702_a(random.nextLong(), p_180702_3_, p_180702_4_, p_180702_5_, p_180702_6_, p_180702_8_, p_180702_10_, random.nextFloat() * 0.5F + 0.5F, p_180702_13_ + ((float)Math.PI / 2F), p_180702_14_ / 3.0F, p_180702_15_, p_180702_16_, 1.0D);
                return;
            }

            if (flag2 || random.nextInt(4) != 0)
            {
                double d8 = p_180702_6_ - d4;
                double d9 = p_180702_10_ - d5;
                double d10 = p_180702_16_ - p_180702_15_;
                double d11 = p_180702_12_ + 2.0F + 16.0F;

                if (d8 * d8 + d9 * d9 - d10 * d10 > d11 * d11)
                {
                    return;
                }

                if (p_180702_6_ >= d4 - 16.0D - d6 * 2.0D && p_180702_10_ >= d5 - 16.0D - d6 * 2.0D && p_180702_6_ <= d4 + 16.0D + d6 * 2.0D && p_180702_10_ <= d5 + 16.0D + d6 * 2.0D)
                {
                    int k3 = MathHelper.floor_double(p_180702_6_ - d6) - p_180702_3_ * 16 - 1;
                    int l1 = MathHelper.floor_double(p_180702_6_ + d6) - p_180702_3_ * 16 + 1;
                    int l3 = MathHelper.floor_double(p_180702_8_ - d7) - 1;
                    int i2 = MathHelper.floor_double(p_180702_8_ + d7) + 1;
                    int i4 = MathHelper.floor_double(p_180702_10_ - d6) - p_180702_4_ * 16 - 1;
                    int j2 = MathHelper.floor_double(p_180702_10_ + d6) - p_180702_4_ * 16 + 1;

                    if (k3 < 0)
                    {
                        k3 = 0;
                    }

                    if (l1 > 16)
                    {
                        l1 = 16;
                    }

                    if (l3 < 1)
                    {
                        l3 = 1;
                    }

                    if (i2 > 248)
                    {
                        i2 = 248;
                    }

                    if (i4 < 0)
                    {
                        i4 = 0;
                    }

                    if (j2 > 16)
                    {
                        j2 = 16;
                    }

                    boolean flag3 = false;
                    int k2;

                    for (k2 = k3; !flag3 && k2 < l1; ++k2)
                    {
                        for (int l2 = i4; !flag3 && l2 < j2; ++l2)
                        {
                            for (int i3 = i2 + 1; !flag3 && i3 >= l3 - 1; --i3)
                            {
                                if (i3 >= 0 && i3 < 256)
                                {
                                    IBlockState iblockstate = p_180702_5_.getBlockState(k2, i3, l2);

                                    if (isOceanBlock(p_180702_5_, k2, i3, l2, p_180702_3_, p_180702_4_))
                                    {
                                        flag3 = true;
                                    }

                                    if (i3 != l3 - 1 && k2 != k3 && k2 != l1 - 1 && l2 != i4 && l2 != j2 - 1)
                                    {
                                        i3 = l3;
                                    }
                                }
                            }
                        }
                    }

                    if (!flag3)
                    {
                        for (k2 = k3; k2 < l1; ++k2)
                        {
                            double d14 = (k2 + p_180702_3_ * 16 + 0.5D - p_180702_6_) / d6;

                            for (int j4 = i4; j4 < j2; ++j4)
                            {
                                double d12 = (j4 + p_180702_4_ * 16 + 0.5D - p_180702_10_) / d6;
                                boolean flag1 = false;

                                if (d14 * d14 + d12 * d12 < 1.0D)
                                {
                                    for (int j3 = i2; j3 > l3; --j3)
                                    {
                                        double d13 = (j3 - 1 + 0.5D - p_180702_8_) / d7;

                                        if (d13 > -0.7D && d14 * d14 + d13 * d13 + d12 * d12 < 1.0D)
                                        {
                                            IBlockState iblockstate1 = p_180702_5_.getBlockState(k2, j3, j4);
                                            IBlockState iblockstate2 = (IBlockState)Objects.firstNonNull(p_180702_5_.getBlockState(k2, j3 + 1, j4), Blocks.AIR.getDefaultState());

                                            if (isTopBlock(p_180702_5_, k2, j3, j4, p_180702_3_, p_180702_4_))
                                            {
                                                flag1 = true;
                                            }
                                            digBlock(p_180702_5_, k2, j3, j4, p_180702_3_, p_180702_4_, flag1, iblockstate1, iblockstate2);
                                        }
                                    }
                                }
                            }
                        }

                        if (flag2)
                        {
                            break;
                        }
                    }
                }
            }
        }
    }

    protected boolean func_175793_a(IBlockState p_175793_1_, IBlockState p_175793_2_) {
        return p_175793_1_.getBlock() == JourneyBlocks.ashBlock ? true : (p_175793_1_.getBlock() == JourneyBlocks.hotBlock ? true : (p_175793_1_.getBlock() == JourneyBlocks.hotBlock ? true : false));
    }

    protected void func_180701_a(World worldIn, int p_180701_2_, int p_180701_3_, int p_180701_4_, int p_180701_5_, ChunkPrimer p_180701_6_)
    {
        int i1 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(15) + 1) + 1);

        if (this.rand.nextInt(7) != 0)
        {
            i1 = 0;
        }

        for (int j1 = 0; j1 < i1; ++j1)
        {
            double d0 = p_180701_2_ * 16 + this.rand.nextInt(16);
            double d1 = this.rand.nextInt(this.rand.nextInt(120) + 8);
            double d2 = p_180701_3_ * 16 + this.rand.nextInt(16);
            int k1 = 1;

            if (this.rand.nextInt(4) == 0)
            {
                this.func_180703_a(this.rand.nextLong(), p_180701_4_, p_180701_5_, p_180701_6_, d0, d1, d2);
                k1 += this.rand.nextInt(4);
            }

            for (int l1 = 0; l1 < k1; ++l1)
            {
                float f = this.rand.nextFloat() * (float)Math.PI * 2.0F;
                float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float f2 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();

                if (this.rand.nextInt(10) == 0)
                {
                    f2 *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0F + 1.0F;
                }

                this.func_180702_a(this.rand.nextLong(), p_180701_4_, p_180701_5_, p_180701_6_, d0, d1, d2, f2, f, f1, 0, 0, 1.0D);
            }
        }
    }

    protected boolean isOceanBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ) {
        net.minecraft.block.Block block = data.getBlockState(x, y, z).getBlock();
        return block== Blocks.flowing_water || block == Blocks.water;
    }

    private boolean isExceptionBiome(net.minecraft.world.biome.BiomeGenBase biome) {
        if (biome == net.minecraft.world.biome.BiomeGenBase.beach) return true;
        if (biome == net.minecraft.world.biome.BiomeGenBase.desert) return true;
        return false;
    }

    private boolean isTopBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ) {
        net.minecraft.world.biome.BiomeGenBase biome = worldObj.getBiomeGenForCoords(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
        IBlockState state = data.getBlockState(x, y, z);
        return (isExceptionBiome(biome) ? state.getBlock() == JourneyBlocks.hotBlock : state.getBlock() == biome.topBlock);
    }

    protected void digBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop, IBlockState state, IBlockState up) {
        net.minecraft.world.biome.BiomeGenBase biome = worldObj.getBiomeGenForCoords(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
        IBlockState top = biome.topBlock;
        IBlockState filler = biome.fillerBlock;

        if (this.func_175793_a(state, up) || state.getBlock() == top.getBlock() || state.getBlock() == filler.getBlock()) {
            if (y < 10) {
                data.setBlockState(x, y, z, Blocks.LAVA.getDefaultState());
            } else {
                data.setBlockState(x, y, z, Blocks.AIR.getDefaultState());

                if (foundTop && data.getBlockState(x, y - 1, z).getBlock() == filler.getBlock())
                {
                    data.setBlockState(x, y - 1, z, top.getBlock().getDefaultState());
                }
            }
        }
    }
}