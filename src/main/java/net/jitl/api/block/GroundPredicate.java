package net.jitl.api.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFaceBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.function.Predicate;

public interface GroundPredicate {
    GroundPredicate ANY = (world, groundPos, horizontalFaceBlock, plantDirection) -> true;
    /**
     * Any ground is accepted, but it should have solid side at the place direction.
     */
    GroundPredicate SOLID_SIDE = (world, groundPos, horizontalFaceBlock, placeDirection) -> HorizontalFaceBlock.canAttach(world, groundPos, placeDirection);
    /**
     * Default version for plants. Simulates BlockBush#canSustainBush
     */
    GroundPredicate GRASS_BLOCK = SOLID_SIDE.and(blockPredicate(block ->
            block == Blocks.GRASS ||
                    block == Blocks.DIRT ||
                    block == Blocks.FARMLAND));

    GroundPredicate UNDERGROUND = SOLID_SIDE.and(blockPredicate(block ->
            block == Blocks.STONE ||
                    block == Blocks.COBBLESTONE ||
                    block == Blocks.ANDESITE ||
                    block == Blocks.GRANITE ||
                    block == Blocks.DIORITE ||
                    block == Blocks.DIRT));

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
        return (world, groundPos, groundState, plantDirection) -> blockStatePredicate.test(groundState.defaultBlockState());
    }

    /**
     * Tests ground state for sustaining plant or smth else.
     * <p>
     * !!! Don't check here for plant block, it can be not placed yet.
     *
     * @param groundPos           position of ground state
     * @param horizontalFaceBlock state of the ground
     * @param plantDirection      direction of plant, regarding to ground position.
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    boolean testGround(World world, BlockPos groundPos, HorizontalFaceBlock horizontalFaceBlock, Direction plantDirection);

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