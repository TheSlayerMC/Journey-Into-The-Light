package net.journey.dimension.euca.biomes;

import net.journey.api.block.GroundPredicate;
import net.journey.dimension.base.gen.JWorldGenPlants;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class EucaSilverBiome extends EucaBiome {

	private final JWorldGenPlants SILVER_TALL_GRASS = new JWorldGenPlants(JourneyBlocks.eucaSilverTallGrass, EUCA_SILVER_GRASS_GROUND, 25);
	private final JWorldGenPlants SILVER_SPROUT = new JWorldGenPlants(JourneyBlocks.eucaSilverSprouts, EUCA_SILVER_GRASS_GROUND);
	private final JWorldGenPlants SILVER_GOLD_FLOWER = new JWorldGenPlants(JourneyBlocks.eucaSilverGoldFlower, EUCA_SILVER_GRASS_GROUND);
	private final JWorldGenPlants SILVER_SHORT_GRASS = new JWorldGenPlants(JourneyBlocks.eucaSilverShortGrass, EUCA_SILVER_GRASS_GROUND, 25);

	private final WorldGenerator[] FLOWERS = new WorldGenerator[]{SILVER_TALL_GRASS, SILVER_SHORT_GRASS, SILVER_SPROUT, SILVER_GOLD_FLOWER};

	public EucaSilverBiome(BiomeProperties properties, IBlockState topBlock, IBlockState fillerBlock) {
		super(properties, topBlock, fillerBlock);
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos chunkStart) {
		for (WorldGenerator flowerGen : FLOWERS) {
			flowerGen.generate(worldIn, rand, chunkStart);
		}
	}
}
