package net.journey.dimension.base.gen;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenMelon extends WorldGenerator {

    public Block top;
    public Block bush;

    public WorldGenMelon(World w, Random rand, BlockPos pos, Block bush, Block top) {
        this.top = top;
        this.bush = bush;
        generate(w, rand, pos);
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        for (int i = 0; i < 64; ++i) {
            BlockPos blockpos = position.add(
                    rand.nextInt(8)
                            - rand.nextInt(8),
                    rand.nextInt(4)
                            - rand.nextInt(4),
                    rand.nextInt(8)
                            - rand.nextInt(8));
            if (worldIn.isAirBlock(blockpos) &&
                    worldIn.getBlockState(blockpos.down()).getBlock() ==
                            top) ;
        }
        return true;
    }
}