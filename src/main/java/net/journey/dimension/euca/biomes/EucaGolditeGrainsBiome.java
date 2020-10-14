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

public class EucaGolditeGrainsBiome extends EucaBiome {

	private final JWorldGenPlants EUCA_TALL_STALKS = new JWorldGenPlants(JourneyBlocks.tallGolditeStalks, EUCA_GOLDITE_GRASS_GROUND, 25);
	private final JWorldGenPlants EUCA_STALKS = new JWorldGenPlants(JourneyBlocks.golditeStalks, EUCA_GOLDITE_GRASS_GROUND, 75);
	private final JWorldGenPlants EUCA_FLOWER = new JWorldGenPlants(JourneyBlocks.golditeBulb, EUCA_GOLDITE_GRASS_GROUND, 5);
	private final JWorldGenPlants EUCA_BULB = new JWorldGenPlants(JourneyBlocks.golditeFlower, EUCA_GOLDITE_GRASS_GROUND, 5);

	private final WorldGenerator[] FLOWERS = new WorldGenerator[]{EUCA_TALL_STALKS, EUCA_STALKS, EUCA_FLOWER, EUCA_BULB};

	public EucaGolditeGrainsBiome(BiomeProperties properties, IBlockState topBlock, IBlockState fillerBlock) {
		super(properties, topBlock, fillerBlock);
		decorator.treesPerChunk = 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos) {
		return EnumBiomeColor.EUCA_GOLDITE.getInt();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float par1) {
		return 0xd4ff82;
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
		Block leaves = RandHelper.chooseEqual(rand, JourneyBlocks.eucaLightGreenLeaves, JourneyBlocks.eucaDarkGreenLeaves, JourneyBlocks.eucaGoldLeaves);
		WorldGenAbstractTree tree = new WorldGenEucaTree(true, JourneyBlocks.GOLDITE_OAK_LOG, leaves, 5, (rand.nextInt(2) + 3));
		return tree;
	}
}