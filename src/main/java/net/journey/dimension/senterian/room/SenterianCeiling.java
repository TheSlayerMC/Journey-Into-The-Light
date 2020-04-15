package net.journey.dimension.senterian.room;

import net.journey.JourneyBlocks;
import net.journey.dimension.senterian.SenterianChunkPrimer;

import java.util.Random;

public class SenterianCeiling extends SenterianRoomBase {

    public SenterianCeiling() {
    }

    @Override
    public boolean generate(SenterianChunkPrimer world, Random rand, int x, int y, int z) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                this.setBlock(world, i, y, j, JourneyBlocks.senterianFloor);
            }
        }
        return true;
    }
}