package net.journey.dimension.nether.biomes;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.dimension.nether.JNWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockNetherWart;
import net.minecraft.block.BlockNetherrack;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

public class NetherBiomeEarthen extends NetherBiome {
	public NetherBiomeEarthen(String name) {
		super(name);
	}

	@Override
	public void genFloorObjects(Chunk chunk, BlockPos pos, Random random) {
		Block ground = chunk.getBlockState(pos).getBlock();
		if (random.nextFloat() <= plantDensity && ground instanceof BlockNetherrack) {
			if (JourneyBlocks.earthenNetherTallGrass != Blocks.AIR && random.nextInt(16) == 0)
				chunk.setBlockState(pos.up(), JourneyBlocks.earthenNetherTallGrass.getDefaultState());

		}
		if (random.nextFloat() <= plantDensity && ground == Blocks.GRASS) {
			if (Blocks.TALLGRASS != Blocks.AIR && random.nextInt(6) == 0)
				chunk.setBlockState(pos.up(), Blocks.TALLGRASS.getStateFromMeta(random.nextInt(3)));

		}
		if (random.nextFloat() <= plantDensity && ground == JourneyBlocks.earthenNetherrack) {
			if (JourneyBlocks.earthenNetherShortGrass != Blocks.AIR && random.nextInt(6) != 0)
				chunk.setBlockState(pos.up(), JourneyBlocks.earthenNetherShortGrass.getDefaultState());
			else if (JourneyBlocks.earthenNetherTallGrass != Blocks.AIR && random.nextInt(6) != 0)
				chunk.setBlockState(pos.up(), JourneyBlocks.earthenNetherTallGrass.getDefaultState());
			else if (JourneyBlocks.earthenNetherFlower != Blocks.AIR && random.nextInt(16) != 0)
				chunk.setBlockState(pos.up(), JourneyBlocks.earthenNetherFlower.getDefaultState());
		}
	}
	@Override
	public void genSurfColumn(Chunk chunk, BlockPos pos, Random random) {
		if (chunk.getBlockState(pos).getBlock() == Blocks.NETHERRACK)
			if (JourneyBlocks.earthenNetherrack != Blocks.AIR) {
				switch (random.nextInt(3)) {
				case 0:
					chunk.setBlockState(pos, Blocks.NETHERRACK.getDefaultState());
					break;
				case 1:
					chunk.setBlockState(pos, JourneyBlocks.earthenNetherrack.getDefaultState());
					break;
				case 2:
					chunk.setBlockState(pos, Blocks.GRASS.getDefaultState());
					break;
				}
			} else {
				if (random.nextInt(3) == 0)
					chunk.setBlockState(pos, Blocks.NETHERRACK.getDefaultState());
			}
	}
}
