package net.jitl.common.world.gen.features;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class JGlowstoneBlobFeature extends Feature<NoneFeatureConfiguration> {

	private final Block placementBlock;
	private final Block blockToPlace;

	public JGlowstoneBlobFeature(Block placementBlock, Block blockToPlace, Codec<NoneFeatureConfiguration> codec) {
		super(codec);
		this.placementBlock = placementBlock;
		this.blockToPlace = blockToPlace;
	}

	public boolean place(WorldGenLevel reader, @NotNull ChunkGenerator generator, @NotNull Random rand, @NotNull BlockPos pos, @NotNull NoneFeatureConfiguration config) {
		if (!reader.isEmptyBlock(pos)) {
			return false;
		} else {
			BlockState blockstate = reader.getBlockState(pos.above());
			if (!blockstate.is(placementBlock)) {
				return false;
			} else {
				reader.setBlock(pos, blockToPlace.defaultBlockState(), 2);

				for (int i = 0; i < 1500; ++i) {
					BlockPos blockpos = pos.offset(rand.nextInt(8) - rand.nextInt(8), -rand.nextInt(12), rand.nextInt(8) - rand.nextInt(8));
					if (reader.getBlockState(blockpos).isAir(reader, blockpos)) {
						int j = 0;

						for (Direction direction : Direction.values()) {
							if (reader.getBlockState(blockpos.relative(direction)).is(blockToPlace)) {
								++j;
							}

							if (j > 1) {
								break;
							}
						}

						if (j == 1) {
							reader.setBlock(blockpos, blockToPlace.defaultBlockState(), 2);
						}
					}
				}

				return true;
			}
		}
	}
}
