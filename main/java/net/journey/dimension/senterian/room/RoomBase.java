package net.journey.dimension.senterian.room;

import java.util.Random;

import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class RoomBase {

    public abstract boolean generate(ChunkPrimer chunk, Random rand, BlockPos pos);

}