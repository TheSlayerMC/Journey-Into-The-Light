package net.journey.dimension.boil.gen;

import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenVolcano extends WorldGenerator {

    private static Random r = new Random();
    public static int height = r.nextInt(25) + 15;

    @Override
    public boolean generate(World w, Random r, BlockPos pos) {
        int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        WorldGenAPI.addCone(w, height, r, x, y, z, JourneyBlocks.ashBlock);
        WorldGenAPI.addRectangle(4, 4, height, w, x - 2, y + 1, z - 2, Blocks.LAVA);
        return true;
    }
}