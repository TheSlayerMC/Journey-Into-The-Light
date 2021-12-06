package net.jitl.api.block;

import net.jitl.init.JBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

import java.util.Objects;
import java.util.function.Predicate;

public interface GroundPredicate {

    GroundPredicate ANY = (world, groundPos, blockState, plantDirection) -> true;
    /**
     * Any ground is accepted, but it should have solid side at the place direction.
     */
    GroundPredicate SOLID_SIDE = (world, groundPos, blockState, placeDirection) -> blockState.isFaceSturdy(world, groundPos, placeDirection);
    /**
     * Default version for plants. Simulates BlockBush#canSustainBush
     */
    GroundPredicate GRASS_BLOCK = SOLID_SIDE.and(blockPredicate(block ->
            block == Blocks.GRASS ||
                    block == Blocks.DIRT ||
                    block == Blocks.FARMLAND));

    GroundPredicate EUCA_GRASS_BLOCKS = SOLID_SIDE.and(blockPredicate(block ->
            block == JBlocks.GOLDITE_GRASS_BLOCK || block == JBlocks.EUCA_SILVER_GRASS_BLOCK || block == JBlocks.EUCA_GOLD_GRASS_BLOCK));

    GroundPredicate FROZEN_GRASS_BLOCK = SOLID_SIDE.and(blockPredicate(block ->
            block == JBlocks.GRASSY_PERMAFROST));

    GroundPredicate BOILING_LAND = SOLID_SIDE.and(blockPredicate(block ->
            block == JBlocks.VOLCANIC_SAND ||
                    block == JBlocks.CHARRED_GRASS ||
                    block == JBlocks.HOT_GROUND ||
                    block == JBlocks.SCORCHED_RUBBLE
    ));

    GroundPredicate UNDERGROUND = SOLID_SIDE.and(blockPredicate(block ->
            block == Blocks.STONE ||
                    block == Blocks.COBBLESTONE ||
                    block == Blocks.ANDESITE ||
                    block == Blocks.GRANITE ||
                    block == Blocks.DIORITE ||
                    block == Blocks.DIRT ||
                    block == JBlocks.ASH_BLOCK));

    GroundPredicate SAND = SOLID_SIDE.and(blockPredicate(block ->
            block == Blocks.SAND));

    GroundPredicate NETHER = SOLID_SIDE.and(blockPredicate(block ->
            block == Blocks.NETHERRACK ||
                    block == Blocks.SOUL_SAND ||
                    block == Blocks.BASALT ||
                    block == Blocks.BLACKSTONE ||
                    block == Blocks.CRIMSON_NYLIUM ||
                    block == Blocks.WARPED_NYLIUM));

    GroundPredicate WATER = blockPredicate(block ->
            block == Blocks.WATER);

    static GroundPredicate blockPredicate(Predicate<Block> blockPredicate) {
        return (world, groundPos, groundState, plantDirection) -> blockPredicate.test(groundState.getBlock());
    }

    static GroundPredicate blockStatePredicate(Predicate<BlockState> blockStatePredicate) {
        return (world, groundPos, groundState, plantDirection) -> blockStatePredicate.test(groundState);
    }

    /**
     * Tests ground state for sustaining plant or smth else.
     * <p>
     * !!! Don't check here for plant block, it can be not placed yet.
     *
     * @param groundPos      position of ground state
     * @param blockState     state of the ground
     * @param plantDirection direction of plant, regarding to ground position.
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    boolean testGround(IWorldReader world, BlockPos groundPos, BlockState blockState, Direction plantDirection);

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