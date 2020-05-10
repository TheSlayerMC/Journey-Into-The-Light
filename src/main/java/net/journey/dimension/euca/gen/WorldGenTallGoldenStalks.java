package net.journey.dimension.euca.gen;

import net.journey.api.block.base.JBlockDoublePlant;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.MathUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenTallGoldenStalks extends WorldGenerator {

    @Override
    public boolean generate(World w, Random r, BlockPos zeroPos) {
        boolean generated = false;
        BlockPos genPos = WorldGenAPI.optimizeAndRandomize(zeroPos, r);

        int coercedY = MathUtils.coerceInRange(WorldGenAPI.findPosAboveSurface(w, genPos).getY(), 1, 200);
        int genY = r.nextInt(coercedY) + 1;
        genPos = WorldGenAPI.getPosWithHeight(genPos, genY);

        JBlockDoublePlant block = JourneyBlocks.tallGoldenStalks;

        if (!w.getBlockState(genPos).getMaterial().isLiquid()
                && w.getBlockState(genPos.down()).getBlock() == JourneyBlocks.eucaGrass
                && block.canPlaceBlockAt(w, genPos)) {

            block.placeAt(w, genPos, 2 | 16);
            generated = true;
        }

        return generated;
    }
}