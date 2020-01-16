package net.journey.dimension.overworld.gen;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.block.BlockModFlower;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

/**
 * !!! IMPORTANT !!!
 * Already fixed postition to avoid overpopulation
 * Pass chunk start here (simiar to WorldGenMinable)
 */
public class WorldGenModFlower extends WorldGenerator {

    private BlockModFlower flower;
    private Block grass;

    public WorldGenModFlower(BlockModFlower b, Block grass) {
        this.flower = b;
        this.grass = grass;
    }

    @Override
    public boolean generate(World w, Random rand, BlockPos chunkStart) {
        BlockPos offset = WorldGenAPI.createRandom(chunkStart.getX(), 0, 1, chunkStart.getZ(), rand, 10);
        offset = w.getPrecipitationHeight(offset);

        for (int i = 0; i < 64; i++) {
            BlockPos copy = offset.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (w.isAirBlock(copy) && w.getBlockState(copy.down()).getBlock() == grass && flower.canPlaceBlockAt(w, copy)) {
                w.setBlockState(copy, flower.getDefaultState(), 2);
            }
        }

        return false;
    }
}