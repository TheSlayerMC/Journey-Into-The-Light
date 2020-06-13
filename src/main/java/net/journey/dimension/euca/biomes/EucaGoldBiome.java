package net.journey.dimension.euca.biomes;

import net.journey.api.block.GroundPredicate;
import net.journey.dimension.base.gen.JWorldGenPlants;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class EucaGoldBiome extends EucaBiome {
	private final GroundPredicate EUCA_GRASS_GROUND = GroundPredicate.SOLID_SIDE.and(GroundPredicate.blockPredicate(block -> block == JourneyBlocks.eucaGrass));

	private final JWorldGenPlants EUCA_TALL_GRASS = new JWorldGenPlants(JourneyBlocks.eucaTallGrass, EUCA_GRASS_GROUND, 50);
	private final JWorldGenPlants EUCA_TALL_FLOWERS = new JWorldGenPlants(JourneyBlocks.eucaTallFlowers, EUCA_GRASS_GROUND);
	private final JWorldGenPlants EUCA_BLUE_FLOWER = new JWorldGenPlants(JourneyBlocks.eucaBlueFlower, EUCA_GRASS_GROUND);
	private final JWorldGenPlants GOLDEN_STALKS = new JWorldGenPlants(JourneyBlocks.goldenStalks, EUCA_GRASS_GROUND);
	private final JWorldGenPlants GOLDEN_BULB = new JWorldGenPlants(JourneyBlocks.goldenBulb, EUCA_GRASS_GROUND);
	private final JWorldGenPlants GOLDEN_BLOOM = new JWorldGenPlants(JourneyBlocks.goldenBloom, EUCA_GRASS_GROUND);

	private final WorldGenerator[] FLOWERS = new WorldGenerator[]{EUCA_TALL_GRASS, EUCA_TALL_FLOWERS, EUCA_BLUE_FLOWER, GOLDEN_BLOOM, GOLDEN_BULB, GOLDEN_STALKS};

	public EucaGoldBiome(BiomeProperties properties, IBlockState topBlock, IBlockState fillerBlock) {
		super(properties, topBlock, fillerBlock);
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos chunkStart) {
		for (WorldGenerator flowerGen : FLOWERS) {
			flowerGen.generate(worldIn, rand, chunkStart);
		}
	}
}
