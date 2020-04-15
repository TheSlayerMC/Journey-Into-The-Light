package net.journey.dimension.cloudia.zone;

import net.journey.dimension.cloudia.CloudiaChunkPrimer;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

import java.util.Random;

public abstract class CloudiaZoneBase {

    public abstract boolean generate(CloudiaChunkPrimer chunk, Random rand, int x, int y, int z);

    public void setBlock(CloudiaChunkPrimer chunk, int x, int y, int z, Block block) {
        chunk.setBlockState(x, y, z, block.getDefaultState());
    }

    public void setBlock(CloudiaChunkPrimer chunk, int x, int y, int z, IBlockState block) {
        chunk.setBlockState(x, y, z, block);
    }
}