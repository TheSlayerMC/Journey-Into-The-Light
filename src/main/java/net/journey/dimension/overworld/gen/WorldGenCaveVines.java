package net.journey.dimension.overworld.gen;

import com.google.common.primitives.Ints;

import net.journey.blocks.plant.BlockCaveVine;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.core.MathUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenCaveVines extends WorldGenerator {
    private static final int[] AGES = Ints.toArray(BlockCaveVine.AGE.getAllowedValues());

    @Override
    public boolean generate(World w, Random r, BlockPos zeroPos) {
        boolean generated = false;
        BlockPos vineGroupPos = WorldGenAPI.optimizeAndRandomize(zeroPos, r);

        int coercedY = MathUtils.coerceInRange(WorldGenAPI.findPosAboveSurface(w, vineGroupPos).getY(), 1, 60);
        int genY = r.nextInt(coercedY) + 1;
        vineGroupPos = WorldGenAPI.getPosWithHeight(vineGroupPos, genY);

        //attempts to gen around one group
        for (int j = 0; j < 16; ++j) {
            BlockPos vinePos = vineGroupPos.add(r.nextInt(4) - r.nextInt(4), r.nextInt(4) - 2, r.nextInt(4) - r.nextInt(4));
            if (w.isAirBlock(vinePos) && JourneyBlocks.caveVine.canPlaceBlockAt(w, vinePos)) {
                setBlockAndNotifyAdequately(w, vinePos, JourneyBlocks.caveVine.getDefaultState().withProperty(BlockCaveVine.AGE, AGES[r.nextInt(AGES.length)]));
                generated = true;
            }
        }

        return generated;
    }
}