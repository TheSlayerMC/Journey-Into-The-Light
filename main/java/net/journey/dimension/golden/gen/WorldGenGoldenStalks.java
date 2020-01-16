package net.journey.dimension.golden.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

public class WorldGenGoldenStalks extends WorldGenerator {

    private final Block top = JourneyBlocks.goldenStalksTop;
    private final Block bottom = JourneyBlocks.goldenStalksBottom;
    private final Block grass = JourneyBlocks.goldenGrass;

    private final int frequency = 128;

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {

        BlockPos offset = WorldGenAPI.createRandom(position.getX(), 0, 1, position.getZ(), rand, 10);
        offset = worldIn.getPrecipitationHeight(offset);

        for (int i = 0; i < frequency; ++i) {
            // top solid block
            BlockPos pos = worldIn.getPrecipitationHeight(offset);

            if (pos.getY() < worldIn.getHeight() - 1
                    && worldIn.isAirBlock(pos)
                    && worldIn.isAirBlock(pos.up())
                    && worldIn.getBlockState(pos.down()).getBlock() == grass) {

                worldIn.setBlockState(pos, bottom.getDefaultState(), 2);
                worldIn.setBlockState(pos.up(), top.getDefaultState(), 2);
                return true;
            }
        }

        return false;
    }
}
