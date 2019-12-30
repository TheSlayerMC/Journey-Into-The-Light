package net.journey.dimension.golden.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenGoldenStalks extends WorldGenerator {

    private final Block top = JourneyBlocks.goldenStalksTop;
    private final Block bottom = JourneyBlocks.goldenStalksBottom;
    private final Block grass = JourneyBlocks.goldenGrass;

    private final int frequency = 128;

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        boolean flag = false;

        for (int i = 0; i < frequency; ++i) {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (blockpos.getY() < worldIn.getHeight() - 1
                    && worldIn.isAirBlock(blockpos)
                    && worldIn.isAirBlock(blockpos.up())
                    && worldIn.getBlockState(blockpos.down()).getBlock() == grass) {

                worldIn.setBlockState(blockpos, bottom.getDefaultState(), 2);
                worldIn.setBlockState(blockpos.up(), top.getDefaultState(), 2);
                flag = true;
            }
        }

        return flag;
    }
}
