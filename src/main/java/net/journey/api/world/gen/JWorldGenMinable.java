package net.journey.api.world.gen;

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

import java.util.Random;
import java.util.function.Predicate;

/**
 * MinableGen with already randomized position.
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

    public static JWorldGenMinable create(Block ore, int blockCount, int maxY) {
        return create(ore.getDefaultState(), blockCount, maxY);
    }

    public static JWorldGenMinable create(IBlockState oreState, int blockCount, int maxY) {
        return create(oreState, blockCount, 1, maxY, new StonePredicate());
    }

    public static JWorldGenMinable create(Block ore, int blockCount, int maxY, Block replaceBlock) {
        return create(ore.getDefaultState(), blockCount, maxY, replaceBlock);
    }

    public static JWorldGenMinable create(IBlockState oreState, int blockCount, int maxY, Block replaceBlock) {
        return create(oreState, blockCount, 1, maxY, replaceBlock);
    }

    public static JWorldGenMinable create(IBlockState oreState, int blockCount, int minY, int maxY, Block replaceBlock) {
        return create(oreState, blockCount, minY, maxY, BlockStateMatcher.forBlock(replaceBlock));
    }

    public static JWorldGenMinable create(IBlockState oreState, int blockCount, int maxY, Predicate<IBlockState> placementPredicate) {
        return create(oreState, blockCount, 1, maxY, placementPredicate);
    }

    public static JWorldGenMinable create(IBlockState oreState, int blockCount, int minY, int maxY, Predicate<IBlockState> placementPredicate) {
        return new JWorldGenMinable(oreState, blockCount, minY, maxY, placementPredicate);
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
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
