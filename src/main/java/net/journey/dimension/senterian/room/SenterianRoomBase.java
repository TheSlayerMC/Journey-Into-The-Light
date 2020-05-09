package net.journey.dimension.senterian.room;

import net.journey.api.world.gen.TECompatibleChunkPrimer;
import net.journey.api.world.gen.TECompatibleChunkPrimer.TileEntityInitializer;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public abstract class SenterianRoomBase {

    public abstract boolean generate(TECompatibleChunkPrimer chunk, Random rand, int x, int y, int z);

    public void setBlock(TECompatibleChunkPrimer chunk, int x, int y, int z, Block block) {
        chunk.setBlockState(x, y, z, block.getDefaultState());
    }

    public void setBlock(TECompatibleChunkPrimer chunk, int x, int y, int z, IBlockState block) {
        chunk.setBlockState(x, y, z, block);
    }

    public void initTileEntity(TECompatibleChunkPrimer chunk, int x, int y, int z, TileEntityInitializer<?> initializer) {
        chunk.addTileEntityInitializer(new BlockPos(x, y, z), initializer);
    }
}