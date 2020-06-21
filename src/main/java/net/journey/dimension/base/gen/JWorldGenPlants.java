package net.journey.dimension.base.gen;

import net.journey.api.block.GroundPredicate;
import net.journey.blocks.base.JBlockDoublePlant;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * Generates plants on the world surface.
 * <p>
 * Works perfectly with double plants.
 * <p>
 * !!! IMPORTANT !!!
 * Position is already optimized and randomized.
 * Pass chunk start pos here (similar to WorldGenMinable)
 */
public class JWorldGenPlants extends WorldGenerator {
	private final IBlockState plantState;
	private final int attempts;
	/**
	 * This is an additional ground predicate, that will check plant after plant's internal ground predicate.
	 * You can use it in situations, when plant can be planted on every grass block, but, for example, we need to spawn it only on silver grass blocks.
	 */
	private final GroundPredicate additionalGroundPredicate;

	public JWorldGenPlants(Block flower, GroundPredicate additionalGroundPredicate) {
		this(flower, additionalGroundPredicate, 5);
	}

	public JWorldGenPlants(Block flower, GroundPredicate additionalGroundPredicate, int genAttempts) {
		this.plantState = flower.getDefaultState();
		this.additionalGroundPredicate = additionalGroundPredicate;
		this.attempts = Math.max(genAttempts, 0);
	}

	/**
	 * Tries to generate a single plant.
	 */
	@Override
	public boolean generate(@NotNull World w, @NotNull Random rand, @NotNull BlockPos chunkStart) {
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

		boolean placed = false;
		for (int i = 0; i < attempts; i++) {
			pos.setPos(chunkStart);
			pos = WorldGenAPI.findPosAboveSurface(w, WorldGenAPI.optimizeAndRandomize(pos, rand));
			if (plantState.getBlock().canPlaceBlockAt(w, pos)) {
				pos.move(EnumFacing.DOWN);

				if (additionalGroundPredicate.testGround(w, pos, w.getBlockState(pos), EnumFacing.UP)) {
					pos.move(EnumFacing.UP);

					if (plantState.getBlock() instanceof JBlockDoublePlant) {
						((JBlockDoublePlant) plantState.getBlock()).placeAt(w, pos, 2 | 16);
					} else {
						setBlockAndNotifyAdequately(w, pos, plantState);
					}

					placed = true;
				}
			}

		}

		return placed;
	}
}