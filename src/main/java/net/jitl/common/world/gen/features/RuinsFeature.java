package net.jitl.common.world.gen.features;

import com.mojang.serialization.Codec;
import net.jitl.common.world.gen.features.featureconfig.RuinsFeatureConfig;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class RuinsFeature extends Feature<RuinsFeatureConfig> {
	/**
	 * Maximum amount of space between blocks
	 */

	public RuinsFeature(Codec<RuinsFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(ISeedReader reader, @NotNull ChunkGenerator generator, @NotNull Random rand, BlockPos pos, @NotNull RuinsFeatureConfig config) {
		if (!config.spawnBlock.test(reader.getBlockState(pos.below()), rand)) {
			return false;
		} else {
			for (int j1 = 0; j1 < rand.nextInt(3) + 5 /* amount of columns */; j1++) {
				/*
				 * gets X pos and adds spreading
				 */
				int xSpreading = pos.getX() + rand.nextInt(config.maxSpreading);

				/*
				 * gets Z pos and adds spreading
				 */
				int zSpreading = pos.getZ() + rand.nextInt(config.maxSpreading);

				/*
				 * gets Y pos by getting world surface heightmap
				 */
				int yPos = reader.getHeight(Heightmap.Type.WORLD_SURFACE_WG, xSpreading, zSpreading);

				/*
				 * combines spreading pos and heightmap pos to create new block position
				 */
				BlockPos placePos = new BlockPos(xSpreading, yPos, zSpreading);

				int height = 1 + rand.nextInt(config.maxHeight);
				for (int j = 0; j < height; j++) {
					reader.setBlock(placePos, config.ruinedBlocksProvider.getState(rand, placePos), 2);
					placePos = placePos.above();
				}
			}
			return true;
		}
	}
}
