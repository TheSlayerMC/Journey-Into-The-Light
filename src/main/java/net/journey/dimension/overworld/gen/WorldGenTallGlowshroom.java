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
        pos = WorldGenAPI.optimize(pos);
        boolean generated = false;

        for (int i = 0; i < 64; i++) {
            BlockPos genPos = WorldGenAPI.randomize(pos, r);
            genPos = WorldGenAPI.getPosWithHeight(genPos, r.nextInt(WorldGenAPI.findPosAboveSurface(w, genPos).getY()) + 1);

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

            if (!w.getBlockState(genPos).getMaterial().isLiquid()
                    && w.getBlockState(genPos.down()).getBlock() == Blocks.STONE
                    && block.canPlaceBlockAt(w, genPos)) {

                block.placeAt(w, genPos, 2 | 16);
                generated = true;
            }
        }

        return generated;
    }
}