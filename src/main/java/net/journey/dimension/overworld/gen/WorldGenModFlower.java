package net.journey.dimension.overworld.gen;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

/**
 * !!! IMPORTANT !!!
 * Already fixed postition to avoid overpopulation
 * Pass chunk start here (simiar to WorldGenMinable)
 */
@Deprecated
public class WorldGenModFlower extends WorldGenerator {

    private final boolean onSurface;
    private final Block flower;
    private final Block grass;

    public WorldGenModFlower(Block flower, Block grass) {
        this(flower, grass, true);
    }

    /**
     * @param flower    - flower
     * @param grass     - grass
     * @param onSurface - using top solid block
     */
    public WorldGenModFlower(Block flower, Block grass, boolean onSurface) {
        this.flower = flower;
        this.grass = grass;
        this.onSurface = onSurface;
    }


    /**
     * Generates single flower
     */
    @Override
    public boolean generate(World w, Random rand, BlockPos chunkStart) {
        BlockPos offset = WorldGenAPI.createRandom(chunkStart.getX(), 0, w.getHeight(), chunkStart.getZ(), rand, 10);

        if (onSurface)
            offset = w.getPrecipitationHeight(offset);

        for (int i = 0; i < 64; i++) {
            BlockPos copy = offset.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (w.isAirBlock(copy) && w.getBlockState(copy.down()).getBlock() == grass && flower.canPlaceBlockAt(w, copy)) {
                setBlockAndNotifyAdequately(w, copy, flower.getDefaultState());
                return true;
            }
        }

        return false;
    }
}