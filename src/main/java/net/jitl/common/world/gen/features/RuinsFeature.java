package net.jitl.common.world.gen.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
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
	//public BlockState[] ruinBlockStates;

	public RuinsFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	public BlockState getRandomStates(Random rand) {
		return RandHelper.chooseEqually(rand, Blocks.SANDSTONE.defaultBlockState(), Blocks.CUT_SANDSTONE.defaultBlockState());
	}

	@Override
	public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		System.out.println("Hello");
		if (!IS_GRASS.test(reader.getBlockState(pos.below()))) {
			System.out.println("No grass");
			return false;
		} else {
			System.out.println("Generate");
			for (int j1 = 0; j1 < rand.nextInt(3) + 5 /* amount of columns */; j1++) {
				BlockPos placePos = pos.offset(rand.nextInt(SPREADING), 0, rand.nextInt(SPREADING));
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
