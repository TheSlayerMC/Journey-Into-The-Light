package net.journey.dimension.nether.gen;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenNetherFlower extends WorldGenerator {

    Block block;

    public WorldGenNetherFlower(World w, Random rand, BlockPos pos, Block block) {
        this.block = block;
        generate(w, rand, pos);
    }

    @Override
    public boolean generate(World w, Random rand, BlockPos pos) {
        for (int i = 0; i < 64; ++i) {
            BlockPos blockpos = pos.add(
                    rand.nextInt(8)
                            - rand.nextInt(8),
                    rand.nextInt(4)
                            - rand.nextInt(4),
                    rand.nextInt(8)
                            - rand.nextInt(8));
            if (w.isAirBlock(blockpos) && w.getBlockState(blockpos.down()).getBlock() == JourneyBlocks.heatSoil && block.canPlaceBlockAt(w, blockpos)) {
                w.setBlockState(blockpos, block.getDefaultState(), 2);
            }
        }
        return true;
    }
}