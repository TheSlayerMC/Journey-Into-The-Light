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

public class NetherBiomeForest extends NetherBiome {
	public NetherBiomeForest(String name) {
		super(name);
	}

	@Override
	public void genFloorObjects(Chunk chunk, BlockPos pos, Random random) {
		Block ground = chunk.getBlockState(pos).getBlock();
		if (random.nextFloat() <= plantDensity && ground instanceof BlockNetherrack) {
			if (JourneyBlocks.hellThornTop != Blocks.AIR && random.nextInt(16) == 0)
				chunk.setBlockState(pos.up(), JourneyBlocks.hellThornTop.getDefaultState());
			else if (JourneyBlocks.deathGrass != Blocks.AIR && random.nextInt(4) == 0)
				chunk.setBlockState(pos.up(), JourneyBlocks.deathGrass.getDefaultState());

		}
		if (random.nextFloat() <= plantDensity && ground == JourneyBlocks.heatSoil) {
			if (JourneyBlocks.deathGrass != Blocks.AIR && random.nextInt(3) != 0)
				chunk.setBlockState(pos.up(), JourneyBlocks.deathGrass.getDefaultState());
		}
	}
	
	/*@Override
	public void genCeilObjects(Chunk chunk, BlockPos pos, Random random) {
		if (random.nextFloat() <= plantDensity && JNWorldGenerator.hasThornGen && random.nextInt(8) == 0
				&& random.nextDouble() * 4D + 0.5 < getFeatureNoise(pos, chunk.x, chunk.z))
			JNWorldGenerator.thornGen.generate(chunk, pos.down(), random);
	}*/

	@Override
	public void genSurfColumn(Chunk chunk, BlockPos pos, Random random) {
		if (chunk.getBlockState(pos).getBlock() == Blocks.NETHERRACK)
			if (JourneyBlocks.heatSoil != Blocks.AIR) {
				switch (random.nextInt(2)) {
				case 0:
					chunk.setBlockState(pos, Blocks.NETHERRACK.getDefaultState());
					break;
				case 1:
					chunk.setBlockState(pos, JourneyBlocks.heatSoil.getDefaultState());
					break;
				}
			} else {
				if (random.nextInt(9) == 0)
					chunk.setBlockState(pos, Blocks.NETHERRACK.getDefaultState());
			}
	}
}
