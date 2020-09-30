package net.journey.dimension.euca.biomes;

import net.journey.api.block.GroundPredicate;
import net.journey.dimension.base.gen.JWorldGenPlants;
import net.journey.dimension.euca.gen.trees.WorldGenEucaTree;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.RandHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import org.jetbrains.annotations.NotNull;

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
		decorator.treesPerChunk = 2;
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos chunkStart) {
		for (WorldGenerator flowerGen : FLOWERS) {
			int j = rand.nextInt(16) + 8;
			int k = rand.nextInt(16) + 8;
			int l = rand.nextInt(worldIn.getHeight(chunkStart.add(j, 0, k)).getY() + 8);
			flowerGen.generate(worldIn, rand, chunkStart.add(j, l, k));
		}
		super.decorate(worldIn, rand, chunkStart);
	}

	@Override
	public @NotNull WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		Block leaves = RandHelper.chooseEqual(rand, JourneyBlocks.eucaGoldLeaves, JourneyBlocks.eucaSilverLeaves);
		WorldGenAbstractTree tree = new WorldGenEucaTree(true, JourneyBlocks.eucaGoldLog, leaves, 6, (rand.nextInt(2) + 3));
		return tree;
	}
}
