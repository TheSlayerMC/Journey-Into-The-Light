package net.journey.api.block;

import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.function.Predicate;

public interface GroundPredicate {
	/**
	 * Any ground is accepted, but it should have solid side at the plant direction.
	 */
	GroundPredicate SOLID_SIDE = (world, groundPos, groundState, plantDirection) -> groundState.isSideSolid(world, groundPos, plantDirection);
	/**
	 * Default version for plants. Simulates BlockBush#canSustainBush
	 */
	GroundPredicate GRASS_BLOCK = SOLID_SIDE.and(blockPredicate(block -> block == Blocks.GRASS || block == Blocks.DIRT || block == Blocks.FARMLAND));
	GroundPredicate SAND = SOLID_SIDE.and(blockPredicate(block -> block == Blocks.SAND));
	GroundPredicate NETHER = SOLID_SIDE.and(blockPredicate(block -> block == Blocks.NETHERRACK || block == Blocks.SOUL_SAND || block == JourneyBlocks.heatSoil || block == JourneyBlocks.heatSand || block == JourneyBlocks.earthenNetherrack || block == JourneyBlocks.volcanicSand));

	GroundPredicate COMMON_AND_BOILING_SANDS = SAND.or(blockPredicate(block -> block == JourneyBlocks.volcanicSand));
	GroundPredicate COMMON_AND_CLOUDIA_GRASS = GRASS_BLOCK.or(blockPredicate(block -> block == JourneyBlocks.cloudiaGrass || block == JourneyBlocks.cloudiaDirt));
	GroundPredicate COMMON_AND_TERRANIA_GRASS = GRASS_BLOCK.or(blockPredicate(block -> block == JourneyBlocks.terranianGrass || block == JourneyBlocks.terranianDirt));
	GroundPredicate COMMON_AND_CORBA_GRASS = GRASS_BLOCK.or(blockPredicate(block -> block == JourneyBlocks.corbaGrass || block == JourneyBlocks.taintedMud));

	static GroundPredicate blockPredicate(Predicate<Block> blockPredicate) {
		return (world, groundPos, groundState, plantDirection) -> blockPredicate.test(groundState.getBlock());
	}

	static GroundPredicate blockStatePredicate(Predicate<IBlockState> blockStatePredicate) {
		return (world, groundPos, groundState, plantDirection) -> blockStatePredicate.test(groundState);
	}

	/**
	 * Test ground state for sustaining plant.
	 * <p>
	 * !!! Don't check here for plant block, it can be not placed yet.
	 *
	 * @param groundPos      position of ground state
	 * @param groundState    state of the ground
	 * @param plantDirection direction of plant, regarding to ground position.
	 * @return {@code true} if the input argument matches the predicate,
	 * otherwise {@code false}
	 */
	boolean testGround(World world, BlockPos groundPos, IBlockState groundState, EnumFacing plantDirection);

	/**
	 * Returns a composed predicate that represents a short-circuiting logical
	 * AND of this predicate and another.  When evaluating the composed
	 * predicate, if this predicate is {@code false}, then the {@code other}
	 * predicate is not evaluated.
	 *
	 * <p>Any exceptions thrown during evaluation of either predicate are relayed
	 * to the caller; if evaluation of this predicate throws an exception, the
	 * {@code other} predicate will not be evaluated.
	 *
	 * @param other a predicate that will be logically-ANDed with this
	 *              predicate
	 * @return a composed predicate that represents the short-circuiting logical
	 * AND of this predicate and the {@code other} predicate
	 * @throws NullPointerException if other is null
	 */
	default GroundPredicate and(GroundPredicate other) {
		Objects.requireNonNull(other);
		return (world, pos, groundState, plantDirection) -> testGround(world, pos, groundState, plantDirection) && other.testGround(world, pos, groundState, plantDirection);
	}

	/**
	 * Returns a composed predicate that represents a short-circuiting logical
	 * OR of this predicate and another.  When evaluating the composed
	 * predicate, if this predicate is {@code true}, then the {@code other}
	 * predicate is not evaluated.
	 *
	 * <p>Any exceptions thrown during evaluation of either predicate are relayed
	 * to the caller; if evaluation of this predicate throws an exception, the
	 * {@code other} predicate will not be evaluated.
	 *
	 * @param other a predicate that will be logically-ORed with this
	 *              predicate
	 * @return a composed predicate that represents the short-circuiting logical
	 * OR of this predicate and the {@code other} predicate
	 * @throws NullPointerException if other is null
	 */
	default GroundPredicate or(GroundPredicate other) {
		Objects.requireNonNull(other);
		return (world, pos, groundState, plantDirection) -> testGround(world, pos, groundState, plantDirection) || other.testGround(world, pos, groundState, plantDirection);
	}
}