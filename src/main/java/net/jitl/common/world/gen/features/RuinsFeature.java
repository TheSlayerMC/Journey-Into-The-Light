package net.jitl.common.world.gen.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import ru.timeconqueror.timecore.util.RandHelper;

import java.util.Random;

public class RuinsFeature extends Feature<NoFeatureConfig> {
	/**
	 * Maximum amount of space between blocks
	 */
	private static final int SPREADING = 5;
	private static final BlockStateMatcher IS_GRASS = BlockStateMatcher.forBlock(Blocks.GRASS_BLOCK);

	public RuinsFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	/**
	 * Gets random BlockState for each block placed
	 */
	public BlockState getRandomStates(Random rand) {
		return RandHelper.chooseEqually(rand,
				Blocks.STONE_BRICKS.defaultBlockState(),
				Blocks.CRACKED_STONE_BRICKS.defaultBlockState(),
				Blocks.MOSSY_STONE_BRICKS.defaultBlockState());
	}

	@Override
	public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		if (!IS_GRASS.test(reader.getBlockState(pos.below()))) {
			return false;
		} else {
			for (int j1 = 0; j1 < rand.nextInt(3) + 5 /* amount of columns */; j1++) {
				/*
				 * gets X pos and adds spreading
				 */
				int xSpreading = pos.getX() + rand.nextInt(SPREADING);

				/*
				 * gets Z pos and adds spreading
				 */
				int zSpreading = pos.getZ() + rand.nextInt(SPREADING);

				/*
				 * gets Y pos by getting world surface heightmap
				 */
				int yPos = reader.getHeight(Heightmap.Type.WORLD_SURFACE_WG, xSpreading, zSpreading);

				/*
				 * combines spreading pos and heightmap pos to create new block position
				 */
				BlockPos placePos = new BlockPos(xSpreading, yPos, zSpreading);

				int height = 1 + rand.nextInt(5);
				for (int j = 0; j < height; j++) {
					reader.setBlock(placePos, getRandomStates(rand), 2);
					placePos = placePos.above();
				}
			}
			return true;
		}
	}
}
