package net.journey.dimension.nether.biomes;

import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

import java.util.Random;

public class NetherBiomeHeatSands extends NetherBiome {
    public NetherBiomeHeatSands(String name) {
        super(name);
    }

    @Override
    public void genFloorObjects(Chunk chunk, BlockPos pos, Random random) {
        Block ground = chunk.getBlockState(pos).getBlock();
//        if (random.nextFloat() <= plantDensity && ground == JourneyBlocks.heatSand) {
//            if (JourneyBlocks.hellThornTop != Blocks.AIR && random.nextInt(16) == 0)
//                chunk.setBlockState(pos.up(), JourneyBlocks.hellThornTop.getDefaultState());
//        }
    }

    @Override
    public void genSurfColumn(Chunk chunk, BlockPos pos, Random random) {
        for (int i = 1; i < 1 + random.nextInt(3); i++) {
            BlockPos p2 = pos.down(i);
            if (p2.getY() > -1 && chunk.getBlockState(p2).getBlock() == Blocks.NETHERRACK)
                if (chunk.getBlockState(p2.down()).getBlock() == Blocks.AIR)
                    chunk.setBlockState(p2, Blocks.NETHERRACK.getDefaultState());
                else
                    chunk.setBlockState(p2, JourneyBlocks.heatSand.getDefaultState());
        }
        if (chunk.getBlockState(pos).getBlock() == Blocks.NETHERRACK)
            chunk.setBlockState(pos, JourneyBlocks.heatSand.getDefaultState());
    }
}
