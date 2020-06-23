package net.journey.dimension.euca.biomes;

import net.journey.dimension.base.gen.JWorldGenPlants;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class EucaGolditeGrainsBiome extends EucaBiome {

	private final JWorldGenPlants EUCA_TALL_STALKS = new JWorldGenPlants(JourneyBlocks.tallGolditeStalks, EUCA_GOLDITE_GRASS_GROUND, 25);
	private final JWorldGenPlants EUCA_STALKS = new JWorldGenPlants(JourneyBlocks.golditeStalks, EUCA_GOLDITE_GRASS_GROUND, 50);
	private final JWorldGenPlants EUCA_FLOWER = new JWorldGenPlants(JourneyBlocks.golditeBulb, EUCA_GOLDITE_GRASS_GROUND, 5);
	private final JWorldGenPlants EUCA_BULB = new JWorldGenPlants(JourneyBlocks.golditeFlower, EUCA_GOLDITE_GRASS_GROUND, 5);

	private final WorldGenerator[] FLOWERS = new WorldGenerator[]{EUCA_TALL_STALKS, EUCA_STALKS};

	public EucaGolditeGrainsBiome(BiomeProperties properties, IBlockState topBlock, IBlockState fillerBlock) {
		super(properties, topBlock, fillerBlock);
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos chunkStart) {
		for (WorldGenerator flowerGen : FLOWERS) {
			flowerGen.generate(worldIn, rand, chunkStart);
		}
	}
}