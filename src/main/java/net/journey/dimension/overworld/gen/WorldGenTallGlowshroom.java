package net.journey.dimension.overworld.gen;

import net.journey.blocks.plant.BlockTallGlowshroom;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenTallGlowshroom extends WorldGenerator {

    @Override
    public boolean generate(World w, Random r, BlockPos pos) {
        pos = WorldGenAPI.getPosWithHeight(pos, r.nextInt(WorldGenAPI.findPosAboveSurface(w, pos).getY() - 5) + 1);
        BlockTallGlowshroom block;
        switch (r.nextInt(3)) {
            case 0:
                block = JourneyBlocks.tallGlowshroomGreen;
                break;
            case 1:
                block = JourneyBlocks.tallGlowshroomRed;
                break;
            default:
                block = JourneyBlocks.tallGlowshroomBlue;
                break;
        }

        if (!w.getBlockState(pos).getMaterial().isLiquid()
                && w.getBlockState(pos.down()).getBlock() == Blocks.STONE
                && block.canPlaceBlockAt(w, pos)) {

            block.placeAt(w, pos, 2 | 16);
            return true;
        }

        return false;
    }
}