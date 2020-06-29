package net.journey.dimension.overworld.gen;

import net.journey.blocks.plant.BlockTallGlowshroom;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.MathUtils;
import net.journey.util.RandHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenTallGlowshroom extends WorldGenerator {

    @Override
    public boolean generate(World w, Random r, BlockPos zeroPos) {
        boolean generated = false;
        BlockPos genPos = WorldGenAPI.optimizeAndRandomize(zeroPos, r);

        int coercedY = MathUtils.coerceInRange(WorldGenAPI.findPosAboveSurface(w, genPos).getY(), 1, 60);
        int genY = r.nextInt(coercedY) + 1;
        genPos = WorldGenAPI.changeHeight(genPos, genY);

	    BlockTallGlowshroom block = RandHelper.chooseEqual(r,
			    JourneyBlocks.tallGlowshroomRed,
			    JourneyBlocks.tallGlowshroomGreen,
			    JourneyBlocks.tallGlowshroomBlue);

        if (!w.getBlockState(genPos).getMaterial().isLiquid()
		        && (w.getBlockState(genPos.down()).getBlock() == Blocks.STONE || w.getBlockState(genPos.down()).getBlock() == JourneyBlocks.corbaStone)
		        && block.canPlaceBlockAt(w, genPos)) {

            block.placeAt(w, genPos, 2 | 16);
            generated = true;
        }

        return generated;
    }
}