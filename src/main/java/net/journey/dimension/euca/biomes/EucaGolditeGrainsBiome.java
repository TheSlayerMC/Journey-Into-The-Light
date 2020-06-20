package net.journey.dimension.euca.biomes;

import java.util.Random;

import net.journey.api.block.GroundPredicate;
import net.journey.dimension.base.gen.JWorldGenPlants;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class EucaGolditeGrainsBiome extends EucaBiome {

	private final JWorldGenPlants EUCA_TALL_GRASS = new JWorldGenPlants(JourneyBlocks.tallGoldenStalks, EUCA_GRASS_GROUND, 75);
	private final JWorldGenPlants EUCA_TALL_FLOWERS = new JWorldGenPlants(JourneyBlocks.eucaTallGrass, EUCA_GRASS_GROUND, 50);
	
	private final WorldGenerator[] FLOWERS = new WorldGenerator[]{EUCA_TALL_GRASS, EUCA_TALL_FLOWERS};

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