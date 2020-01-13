package net.journey.dimension.boil.gen;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.util.EnumTypeHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

public class WorldGenBoilingLamp extends WorldGenerator {

    public boolean locationIsValidSpawn(World w, int x, int y, int z) {
        ArrayList<IBlockState> illegalStates = new ArrayList<>();

        BlockPos start = new BlockPos(x + 8, y, z + 8);

        BlockPos.getAllInBoxMutable(start, start.add(-11, 0, -11))
                .forEach(mutableBlockPos -> {
                    IBlockState state = w.getBlockState(mutableBlockPos);
                    if (state.getBlock() != JourneyBlocks.hotBlock) {
                        illegalStates.add(state);
                        return;
                    }
                });

        return illegalStates.isEmpty();
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        int i = pos.getX(), j = pos.getY(), k = pos.getZ();
        if (locationIsValidSpawn(world, i, j, k)) return true;
        world.setBlockState(new BlockPos(i + 0, j + 0, k + 1), JourneyBlocks.sizzlingPost.getDefaultState());
        world.setBlockState(new BlockPos(i + 1, j + 0, k + 0), JourneyBlocks.sizzlingPost.getDefaultState());
        world.setBlockState(new BlockPos(i + 1, j + 0, k + 1), JourneyBlocks.brisonblocks.getStateFromMeta(0));
        world.setBlockState(new BlockPos(i + 1, j + 0, k + 2), JourneyBlocks.sizzlingPost.getDefaultState());
        world.setBlockState(new BlockPos(i + 1, j + 1, k + 1), JourneyBlocks.sizzlingPost.getDefaultState());
        world.setBlockState(new BlockPos(i + 1, j + 2, k + 1), JourneyBlocks.sizzlingPost.getDefaultState());
        world.setBlockState(new BlockPos(i + 1, j + 3, k + 1), JourneyBlocks.boilingLamp.getDefaultState());
        world.setBlockState(new BlockPos(i + 2, j + 0, k + 1), JourneyBlocks.sizzlingPost.getDefaultState());
        return true;
    }
}