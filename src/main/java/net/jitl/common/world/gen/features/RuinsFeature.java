package net.jitl.common.world.gen.features;

import com.mojang.serialization.Codec;
import net.jitl.common.world.gen.features.featureconfig.RuinsFeatureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.Random;

public class RuinsFeature extends Feature<RuinsFeatureConfig> {

	public RuinsFeature(Codec<RuinsFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<RuinsFeatureConfig> context) {
		BlockPos pos = context.origin();
		WorldGenLevel reader = context.level();
		Random rand = context.random();
		RuinsFeatureConfig config = context.config();
		if (!config.spawnBlock.test(reader.getBlockState(pos.below()), rand)) {
			return false;
		} else {
			BlockPos.MutableBlockPos placePos = pos.mutable();
			int columns = rand.nextInt(config.maxColumns) + 5;
			for (int j1 = 0; j1 < columns; j1++) {
				int xPos = pos.getX() + rand.nextInt(config.maxSpreading);
				int zPos = pos.getZ() + rand.nextInt(config.maxSpreading);
				int yPos = reader.getHeight(Heightmap.Types.WORLD_SURFACE_WG, xPos, zPos);
				int height = 1 + rand.nextInt(config.maxHeight);
				placePos.set(xPos, yPos, zPos);
				for (int i = 0; i < height; ++i) {
					reader.setBlock(placePos, config.ruinedBlocksProvider.getState(rand, placePos), 2);
					placePos.move(Direction.UP);
				}
				if (rand.nextInt(4) == 0) {
                    BlockPos chestPos = new BlockPos(pos.getX(), yPos - 2, pos.getZ());
                    BlockPos dirtPos = new BlockPos(pos.getX(), yPos - 1, pos.getZ());
                    BlockPos spawnPos = new BlockPos(pos.getX(), yPos, pos.getZ());

                    if (config.spawnBlock.test(reader.getBlockState(spawnPos.below()), rand)) {
                        BlockState chestState = Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(rand));
                        reader.setBlock(dirtPos, Blocks.COARSE_DIRT.defaultBlockState(), 2);
                        reader.setBlock(chestPos, chestState, 2);
                        RandomizableContainerBlockEntity.setLootTable(reader, rand, chestPos, config.resourceLocation);
                    }
                }
			}
			return true;
		}
	}
}
