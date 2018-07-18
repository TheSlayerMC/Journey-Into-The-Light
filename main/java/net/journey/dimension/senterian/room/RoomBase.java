package net.journey.dimension.senterian.room;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.ChunkPrimer;

public abstract class RoomBase {

    public abstract boolean generate(ChunkPrimer chunk, Random rand, BlockPos pos);

}