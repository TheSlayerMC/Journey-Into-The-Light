package net.journey.dimension.overworld.gen;

import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenSmallGlowshrooms extends WorldGenerator {

    @Override
    public boolean generate(World w, Random r, BlockPos pos) {
        pos = WorldGenAPI.getPosWithHeight(pos, r.nextInt(WorldGenAPI.findPosAboveSurface(w, pos).getY() - 5) + 1);

        Block shroom;
        switch (r.nextInt(3)) {
            case 0:
                shroom = JourneyBlocks.glowshroomGreen;
                break;
            case 1:
                shroom = JourneyBlocks.glowshroomRed;
                break;
            default:
                shroom = JourneyBlocks.glowshroomBlue;
                break;
        }

        if (!w.getBlockState(pos).getMaterial().isLiquid()
                && w.getBlockState(pos.down()).getBlock() == Blocks.STONE
                && shroom.canPlaceBlockAt(w, pos)) {

            setBlockAndNotifyAdequately(w, pos, shroom.getDefaultState());
            return true;
        }
        return false;
    }
}