package net.journey.dimension.nether.biomes;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockNetherrack;
import net.minecraft.block.BlockSoulSand;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

import java.util.Random;

public class NetherBiomeForestEdge extends NetherBiomeForest {
    public NetherBiomeForestEdge(String name) {
        super(name);
    }

    @Override
    public void genFloorObjects(Chunk chunk, BlockPos pos, Random random) {
        Block ground = chunk.getBlockState(pos).getBlock();
        if (random.nextFloat() <= plantDensity && ground instanceof BlockNetherrack) {
            if (JourneyBlocks.deathGrass != Blocks.AIR && random.nextInt(16) == 0)
                chunk.setBlockState(pos.up(), JourneyBlocks.deathGrass.getDefaultState());

        } else if (random.nextFloat() <= plantDensity && ground instanceof BlockSoulSand) {
            if (JourneyBlocks.hellshroom != Blocks.AIR && random.nextInt(16) == 0)
                chunk.setBlockState(pos.up(), JourneyBlocks.hellshroom.getDefaultState());

        } else if (random.nextFloat() <= plantDensity && ground == JourneyBlocks.heatSoil) {
            if (JourneyBlocks.deathGrass != Blocks.AIR && random.nextInt(6) != 0)
                chunk.setBlockState(pos.up(), JourneyBlocks.deathGrass.getDefaultState());
        }
    }

    @Override
    public void genSurfColumn(Chunk chunk, BlockPos pos, Random random) {
        if (chunk.getBlockState(pos).getBlock() == Blocks.NETHERRACK)
            if (JourneyBlocks.heatSoil != Blocks.AIR) {
                switch (random.nextInt(3)) {
                    case 0:
                        chunk.setBlockState(pos, Blocks.NETHERRACK.getDefaultState());
                        break;
                    case 1:
                        chunk.setBlockState(pos, JourneyBlocks.heatSoil.getDefaultState());
                        break;
                    case 2:
                        chunk.setBlockState(pos, Blocks.SOUL_SAND.getDefaultState());
                        break;
                }
            } else {
                if (random.nextInt(3) == 0)
                    chunk.setBlockState(pos, JourneyBlocks.heatSoil.getDefaultState());
            }
    }
}