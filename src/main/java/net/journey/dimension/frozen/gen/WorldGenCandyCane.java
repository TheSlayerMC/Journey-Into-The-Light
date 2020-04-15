package net.journey.dimension.frozen.gen;

import net.journey.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenCandyCane extends WorldGenerator {

    @Override
    public boolean generate(World w, Random r, BlockPos pos) {
        int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        switch (r.nextInt(4)) {
            case 0:
                WorldGenAPI.addRectangle(1, 1, 5, w, x, y + 1, z, JourneyBlocks.candyCane);
                WorldGenAPI.addRectangle(2, 1, 1, w, x + 1, y + 6, z, JourneyBlocks.candyCane);
                WorldGenAPI.addRectangle(1, 1, 2, w, x + 3, y + 4, z, JourneyBlocks.candyCane);
                break;
            case 1:
                WorldGenAPI.addRectangle(1, 1, 5, w, x, y + 1, z, JourneyBlocks.candyCane);
                WorldGenAPI.addRectangle(1, 2, 1, w, x, y + 6, z + 1, JourneyBlocks.candyCane);
                WorldGenAPI.addRectangle(1, 1, 2, w, x, y + 4, z + 3, JourneyBlocks.candyCane);
                break;
            case 2:
                WorldGenAPI.addRectangle(1, 1, 5, w, x, y + 1, z, JourneyBlocks.candyCane);
                WorldGenAPI.addRectangle(2, 1, 1, w, x - 2, y + 6, z, JourneyBlocks.candyCane);
                WorldGenAPI.addRectangle(1, 1, 2, w, x - 3, y + 4, z, JourneyBlocks.candyCane);
                break;
            case 3:
                WorldGenAPI.addRectangle(1, 1, 5, w, x, y + 1, z, JourneyBlocks.candyCane);
                WorldGenAPI.addRectangle(1, 2, 1, w, x, y + 6, z - 2, JourneyBlocks.candyCane);
                WorldGenAPI.addRectangle(1, 1, 2, w, x, y + 4, z - 3, JourneyBlocks.candyCane);
                break;
        }
        return true;
    }
}