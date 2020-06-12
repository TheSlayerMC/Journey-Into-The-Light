package net.journey.dimension.base.gen;

import net.journey.api.block.GroundPredicate;
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
 * !!! IMPORTANT !!!
 * Position is already optimized and randomized.
 * Pass chunk start pos here (similar to WorldGenMinable)
 */
public class JWorldGenFlowers extends WorldGenerator {
	private final IBlockState flowerState;
	private final int attempts;
	private final GroundPredicate groundPredicate;

	public JWorldGenFlowers(Block flower, GroundPredicate groundPredicate) {
		this(flower, groundPredicate, 5);
	}

	public JWorldGenFlowers(Block flower, GroundPredicate groundPredicate, int genAttempts) {
		this.flowerState = flower.getDefaultState();
		this.groundPredicate = groundPredicate;
		this.attempts = Math.max(genAttempts, 0);
	}

	/**
	 * Tries to generate a single flower
	 */
	@Override
	public boolean generate(@NotNull World w, @NotNull Random rand, @NotNull BlockPos chunkStart) {
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

		boolean placed = false;
		//System.out.println("Start for " + chunkStart);
		for (int i = 0; i < attempts; i++) {
			pos.setPos(chunkStart);
			pos = WorldGenAPI.findPosAboveSurface(w, WorldGenAPI.optimizeAndRandomize(pos, rand));

//            boolean canPlaceBlock = false;
//            boolean testGround = false;
			if (flowerState.getBlock().canPlaceBlockAt(w, pos)) {
//                canPlaceBlock = true;

				pos.move(EnumFacing.DOWN);

				if (groundPredicate.testGround(w, pos, w.getBlockState(pos), EnumFacing.UP)) {
//                    testGround = true;
					pos.move(EnumFacing.UP);
					setBlockAndNotifyAdequately(w, pos, flowerState);

					placed = true;
				}
			}

//            if(flowerState.getBlock() == JourneyBlocks.eucaTallGrass) System.out.println("EucaTallGrass: can place block: " + canPlaceBlock + ",pos: " + pos  +", replace: " + w.getBlockState(pos) +", ground: " + w.getBlockState(pos.down()) + ", testground: " + testGround);
		}

		return placed;
	}
}