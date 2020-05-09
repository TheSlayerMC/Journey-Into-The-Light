package net.journey.dimension.senterian.room;

import net.journey.api.world.gen.TECompatibleChunkPrimer;
import net.journey.init.blocks.JourneyBlocks;

import java.util.Random;

public class SenterianCeiling extends SenterianRoomBase {

    public SenterianCeiling() {
    }

    @Override
    public boolean generate(TECompatibleChunkPrimer world, Random rand, int x, int y, int z) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                this.setBlock(world, i, y, j, JourneyBlocks.senterianFloor);
            }
        }
        return true;
    }
}