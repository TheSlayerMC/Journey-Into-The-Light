package net.journey.dimension.base.gen;

import net.journey.util.MathUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.slayer.api.worldgen.WorldGenAPI;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.function.Predicate;

/**
 * MinableGen with already randomized and optimized position.
 */
public class JWorldGenMinable extends WorldGenMinable {
    /**
     * Max generation Y, excluded
     */
    private int maxY;
    /**
     * Max generation Y, included
     */
    private int minY;

    public JWorldGenMinable(IBlockState state, int blockCount, int minY, int maxY, Predicate<IBlockState> placementPredicate) {
        super(state, blockCount, placementPredicate::test);
        this.minY = MathUtils.coerceInRange(minY, 1, 255);
        this.maxY = MathUtils.coerceInRange(maxY, 1, 255);

        if (this.minY > this.maxY) {
            int t = this.maxY;
            this.maxY = this.minY;
            this.minY = t;
        } else if (this.minY == this.maxY) {
            if (this.maxY == 255) {
                this.minY--;
            } else {
                this.maxY++;
            }
        }
    }

    /**
     * Creates ore generator with provided settings.
     * <p>
     * In addition it will be generated:
     * <ol>
     *     <li>replacing {@link Blocks#STONE}</li>
     *     <li>from 1 to {@code maxY}, maxY is excluded</li>
     * </ol>
     *
     * @param ore        ore to be generated
     * @param blockCount count of ore to be generated
     * @param maxY       height to which ore can be generated, maxY is excluded!
     */
    public static JWorldGenMinable create(Block ore, int blockCount, int maxY) {
        return create(ore.getDefaultState(), blockCount, maxY);
    }

    /**
     * Creates ore generator with provided settings.
     * <p>
     * In addition it will be generated:
     * <ol>
     *     <li>replacing {@link Blocks#STONE}</li>
     *     <li>from 1 to {@code maxY}, maxY is excluded</li>
     * </ol>
     *
     * @param oreState   ore to be generated
     * @param blockCount count of ore to be generated
     * @param maxY       height to which ore can be generated, maxY is excluded!
     */
    public static JWorldGenMinable create(IBlockState oreState, int blockCount, int maxY) {
        return create(oreState, blockCount, 1, maxY, new StonePredicate());
    }

    /**
     * Creates ore generator with provided settings.
     * <p>
     * In addition it will be generated:
     * <ol>
     *    <li>from 1 to {@code maxY}, maxY is excluded</li>
     * </ol>
     *
     * @param ore          ore to be generated
     * @param blockCount   count of ore to be generated
     * @param maxY         height to which ore can be generated, maxY is excluded!
     * @param replaceBlock block which ore can replace while generating
     */
    public static JWorldGenMinable create(Block ore, int blockCount, int maxY, Block replaceBlock) {
        return create(ore.getDefaultState(), blockCount, maxY, replaceBlock);
    }

    /**
     * Creates ore generator with provided settings.
     * <p>
     * In addition it will be generated:
     * <ol>
     *    <li>from 1 to {@code maxY}, maxY is excluded</li>
     * </ol>
     *
     * @param oreState     ore to be generated
     * @param blockCount   count of ore to be generated
     * @param maxY         height to which ore can be generated, maxY is excluded!
     * @param replaceBlock block which ore can replace while generating
     */
    public static JWorldGenMinable create(IBlockState oreState, int blockCount, int maxY, Block replaceBlock) {
        return create(oreState, blockCount, 1, maxY, replaceBlock);
    }

    /**
     * Creates ore generator with provided settings.
     *
     * @param oreState     ore to be generated
     * @param blockCount   count of ore to be generated
     * @param minY         height from which ore can be generated, is included.
     * @param maxY         height to which ore can be generated, maxY is excluded!
     * @param replaceBlock block which ore can replace while generating
     */
    public static JWorldGenMinable create(IBlockState oreState, int blockCount, int minY, int maxY, Block replaceBlock) {
        return create(oreState, blockCount, minY, maxY, BlockStateMatcher.forBlock(replaceBlock));
    }

    /**
     * Creates ore generator with provided settings.
     * <p>
     * In addition it will be generated:
     * <ol>
     *    <li>from 1 to {@code maxY}, maxY is excluded</li>
     * </ol>
     *
     * @param oreState           ore to be generated
     * @param blockCount         count of ore to be generated
     * @param maxY               height to which ore can be generated, maxY is excluded!
     * @param placementPredicate controls, what blocks can be replaced with this ore while generating
     */
    public static JWorldGenMinable create(IBlockState oreState, int blockCount, int maxY, Predicate<IBlockState> placementPredicate) {
        return create(oreState, blockCount, 1, maxY, placementPredicate);
    }

    /**
     * Creates ore generator with provided settings.
     *
     * @param oreState           ore to be generated
     * @param blockCount         count of ore to be generated
     * @param minY               height from which ore can be generated, is included.
     * @param maxY               height to which ore can be generated, maxY is excluded!
     * @param placementPredicate controls, what blocks can be replaced with this ore while generating
     */
    public static JWorldGenMinable create(IBlockState oreState, int blockCount, int minY, int maxY, Predicate<IBlockState> placementPredicate) {
	    return new JWorldGenMinable(oreState, blockCount, minY, maxY, placementPredicate);
    }

	@Override
	public boolean generate(@NotNull World worldIn, Random rand, @NotNull BlockPos position) {
		position = WorldGenAPI.getPosWithHeight(position, minY + rand.nextInt(maxY - minY));

		return super.generate(worldIn, rand, WorldGenAPI.randomize(position, rand));
	}

	private static class StonePredicate implements Predicate<IBlockState> {
		private StonePredicate() {
		}

		public boolean test(IBlockState state) {
			if (state != null && state.getBlock() == Blocks.STONE) {
                BlockStone.EnumType type = state.getValue(BlockStone.VARIANT);
                return type.isNatural();
            } else {
                return false;
            }
        }
    }
}
