package net.journey.dimension.euca.gen;

import net.journey.api.block.base.JBlockDoublePlant;
import net.journey.api.block.base.JBlockPlant;
import net.journey.blocks.BlockEucaPumpkin;
import net.journey.blocks.plant.BlockTallGlowshroom;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.MathUtils;
import net.journey.util.RandHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenTallGoldenStalks extends WorldGenerator {

    @Override
    public boolean generate(World worldIn, Random r, BlockPos b) {
        boolean generated = false;
        BlockPos offset = WorldGenAPI.createRandom(b.getX(), 0, worldIn.getHeight(), b.getZ(), r, 10);
    	
        int coercedY = MathUtils.coerceInRange(WorldGenAPI.findPosAboveSurface(worldIn, offset).getY(), 1, 151);
        int genY = r.nextInt(coercedY) + 1;
        BlockPos copy = offset.add(r.nextInt(8) - r.nextInt(8), r.nextInt(4) - r.nextInt(4), r.nextInt(8) - r.nextInt(8));

	    JBlockDoublePlant block = JourneyBlocks.tallGoldenStalks;

        if (!worldIn.getBlockState(offset).getMaterial().isLiquid()
                && worldIn.getBlockState(offset.down()).getBlock() == JourneyBlocks.eucaGrass
                && block.canPlaceBlockAt(worldIn, offset)) {

            block.placeAt(worldIn, offset, 2 | 16);
            generated = true;
        }

        return generated;
    }
}