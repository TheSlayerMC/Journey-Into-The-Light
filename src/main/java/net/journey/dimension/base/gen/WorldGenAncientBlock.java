package net.journey.dimension.base.gen;

import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenAncientBlock extends WorldGenerator {

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        pos = WorldGenAPI.getPosWithHeight(pos, rand.nextInt(254 + 1));

        if (world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP)
                && JourneyBlocks.ancientMachineBlock.canPlaceBlockAt(world, pos)) {
            setBlockAndNotifyAdequately(world, pos, JourneyBlocks.ancientMachineBlock.getDefaultState());
            return true;
        }

        return false;
    }
}
