package net.journey.dimension.euca.biomes;

import net.journey.dimension.base.biome.EnumBiomeColor;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class EucaGoldBiome extends EucaBiome {

	private final JWorldGenPlants EUCA_TALL_GRASS = new JWorldGenPlants(JourneyBlocks.eucaTallGrass, EUCA_GRASS_GROUND, 75);
	private final JWorldGenPlants EUCA_TALL_FLOWERS = new JWorldGenPlants(JourneyBlocks.eucaTallFlowers, EUCA_GRASS_GROUND);
	private final JWorldGenPlants EUCA_BLUE_FLOWER = new JWorldGenPlants(JourneyBlocks.eucaBlueFlower, EUCA_GRASS_GROUND);
	private final JWorldGenPlants GOLDEN_STALKS = new JWorldGenPlants(JourneyBlocks.goldenStalks, EUCA_GRASS_GROUND, 25);
	private final JWorldGenPlants GOLDEN_BULB = new JWorldGenPlants(JourneyBlocks.goldenBulb, EUCA_GRASS_GROUND);
	private final JWorldGenPlants GOLDEN_BLOOM = new JWorldGenPlants(JourneyBlocks.goldenBloom, EUCA_GRASS_GROUND);

	private final WorldGenerator[] FLOWERS = new WorldGenerator[]{EUCA_TALL_GRASS, EUCA_TALL_FLOWERS, EUCA_BLUE_FLOWER, GOLDEN_BLOOM, GOLDEN_BULB, GOLDEN_STALKS};

	public EucaGoldBiome(BiomeProperties properties, IBlockState topBlock, IBlockState fillerBlock) {
		super(properties, topBlock, fillerBlock);
		decorator.treesPerChunk = 2;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos) {
		return EnumBiomeColor.EUCA_GOLD.getInt();
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos chunkStart) {
		for (WorldGenerator flowerGen : FLOWERS) {
			flowerGen.generate(worldIn, rand, chunkStart);
		}
		super.decorate(worldIn, rand, chunkStart);
	}

	@Override
	public @NotNull WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		Block leaves = RandHelper.chooseEqual(rand, JourneyBlocks.eucaGoldLeaves, JourneyBlocks.eucaSilverLeaves);
		WorldGenAbstractTree tree = new WorldGenEucaTree(true, JourneyBlocks.eucaGoldLog, leaves, 5, (rand.nextInt(2) + 3));
		return tree;
	}
}
