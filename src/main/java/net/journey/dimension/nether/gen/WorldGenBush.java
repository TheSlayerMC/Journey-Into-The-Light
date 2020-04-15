package net.journey.dimension.nether.gen;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.block.BlockModBush;

import java.util.Random;

public class WorldGenBush extends WorldGenerator {

    public Block top;
    public Block bush;

    public WorldGenBush(Block bush, Block top) {
        this.top = top;
        this.bush = bush;
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
                            top &&
                    bush.canPlaceBlockAt(worldIn, blockpos)) {
                worldIn.setBlockState(blockpos,
                        bush.getDefaultState().withProperty(BlockModBush.AGE, 2));
            }
        }
        return true;
    }
}