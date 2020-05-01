package net.journey.dimension.euca.gen;

import net.journey.blocks.BlockEucaPumpkin;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenEucaPumpkin extends WorldGenerator {

    @Override
    public boolean generate(World worldIn, Random r, BlockPos b) {
    	
        BlockPos offset = WorldGenAPI.createRandom(b.getX(), 0, worldIn.getHeight(), b.getZ(), r, 10);

    	
        for(int i = 0; i < 30; ++i) {
            BlockPos copy = offset.add(r.nextInt(8) - r.nextInt(8), r.nextInt(4) - r.nextInt(4), r.nextInt(8) - r.nextInt(8));
            if (worldIn.isAirBlock(copy) && worldIn.getBlockState(copy.down()).getBlock() == JourneyBlocks.eucaGrass)
                worldIn.setBlockState(copy, JourneyBlocks.eucaPumpkin.getDefaultState().withProperty(BlockEucaPumpkin.FACING, EnumFacing.Plane.HORIZONTAL.random(r)), 2);
        }
        return true;
    }
}