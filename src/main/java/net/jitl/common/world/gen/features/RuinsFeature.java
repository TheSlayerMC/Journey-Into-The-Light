package net.jitl.common.world.gen.features;

import com.mojang.serialization.Codec;
import net.jitl.common.world.gen.features.featureconfig.RuinsFeatureConfig;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.LockableLootTileEntity;
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
			BlockPos.Mutable placePos = pos.mutable();
			for (int j1 = 0; j1 < rand.nextInt(3) + 5 /* amount of columns */; j1++) {

				int xSpreading = rand.nextInt(config.maxSpreading);
				int zSpreading = rand.nextInt(config.maxSpreading);
				int yPos = reader.getHeight(Heightmap.Type.WORLD_SURFACE_WG, pos.getX() + xSpreading, pos.getZ() + zSpreading);
				int height = 1 + rand.nextInt(config.maxHeight);

				for (int i = 0; i < height; ++i) {
					placePos.set(pos);
					placePos.move(xSpreading, yPos, zSpreading);
					placePos.setY(i);
					reader.setBlock(placePos, config.ruinedBlocksProvider.getState(rand, placePos), 2);
				}

				if (rand.nextInt(2) == 0) {
					BlockPos chestPos = new BlockPos(pos.getX(), yPos, pos.getZ());
					if (config.spawnBlock.test(reader.getBlockState(chestPos.below()), rand) && reader.getBlockState(chestPos).getBlock().is(Blocks.AIR)) {
						reader.setBlock(chestPos, Blocks.CHEST.defaultBlockState(), 2);
						LockableLootTileEntity.setLootTable(reader, rand, chestPos, config.lootWeightedList.getOne(rand));
					}
				}
			}
			return true;
		}
	}
}
