package net.jitl.common.world.gen.features;

import com.mojang.serialization.Codec;
import net.jitl.common.world.gen.features.featureconfig.RuinsFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class RuinsFeature extends Feature<RuinsFeatureConfig> {

	public RuinsFeature(Codec<RuinsFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(ISeedReader reader, @NotNull ChunkGenerator generator, @NotNull Random rand, BlockPos pos, @NotNull RuinsFeatureConfig config) {
		if (!config.spawnBlock.test(reader.getBlockState(pos.below()), rand)) {
			return false;
		} else {
			BlockPos.Mutable placePos = pos.mutable();
			int columns = rand.nextInt(config.maxColumns) + 5;
			for (int j1 = 0; j1 < columns; j1++) {
				int xPos = pos.getX() + rand.nextInt(config.maxSpreading);
				int zPos = pos.getZ() + rand.nextInt(config.maxSpreading);
				int yPos = reader.getHeight(Heightmap.Type.WORLD_SURFACE_WG, xPos, zPos);
				int height = 1 + rand.nextInt(config.maxHeight);
				placePos.set(xPos, yPos, zPos);
				for (int i = 0; i < height; ++i) {
					reader.setBlock(placePos, config.ruinedBlocksProvider.getState(rand, placePos), 2);
					placePos.move(Direction.UP);
				}
				if (rand.nextInt(4) == 0) {
					BlockPos chestPos = new BlockPos(pos.getX(), yPos, pos.getZ());
					if (config.spawnBlock.test(reader.getBlockState(chestPos.below()), rand) && reader.getBlockState(chestPos).getBlock().is(Blocks.AIR)) {
						BlockState chestState = Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(rand));
						reader.setBlock(chestPos, chestState, 2);
						LockableLootTileEntity.setLootTable(reader, rand, chestPos, config.lootWeightedList.getOne(rand));
					}
				}
			}
			return true;
		}
	}
}
