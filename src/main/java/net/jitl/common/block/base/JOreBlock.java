package net.jitl.common.block.base;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelReader;
import org.jetbrains.annotations.NotNull;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class JOreBlock extends OreBlock {

    /**
     * The amount of experienced dropped by the ore
     * Is set by {@link #setExpDrop(int)}
     */
    public int expDrop;

    public JOreBlock(Properties properties) {
        super(properties);
    }

    /**
     * Gets experience dropped by the block, depending on the number set by {@link #setExpDrop(int)}
     * If the number represented by {@link #expDrop} is 0 or less, it is ignored and no XP will be dropped.
     * If the same number is greater than 0, it will choose a random number between the number specified, and the number specified + 4.
     * Example:
     * If the {@link #expDrop} integer is 0 or smaller, no experience will be dropped by the block.
     * If the integer is, for example, 4; it will drop a random number of experience between 4 and 8.
     * <p>
     * If the tool being used on the block has the Silktouch enchantment, it will drop no experience.
     */
    @Override
    public int getExpDrop(@NotNull BlockState state, @NotNull LevelReader world, @NotNull BlockPos pos, int fortune, int silktouch) {
        if (expDrop > 0) {
            expDrop = Mth.nextInt(RANDOM, expDrop, expDrop + 4);
        } else {
            expDrop = 0;
        }
        return silktouch == 0 ? Math.max(expDrop, 0) : 0;
    }

    /**
     * Helper method to set the experience dropped by the ore, via the block registry
     *
     * @param minExpDrop The minimum XP dropped by the block
     */
    public JOreBlock setExpDrop(int minExpDrop) {
        this.expDrop = minExpDrop;
        return this;
    }
}
