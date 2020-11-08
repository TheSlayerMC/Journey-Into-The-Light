package net.jitl.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;

public class JOreBlock extends OreBlock {

    public int expDrop;

    public JOreBlock(Properties properties) {
        super(properties);
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        if (expDrop > 0) {
            expDrop = MathHelper.nextInt(RANDOM, expDrop, expDrop + 4);
        } else {
            expDrop = 0;
        }
        return silktouch == 0 ? Math.max(expDrop, 0) : 0;
    }

    public JOreBlock setExpDrop(int expDrop) {
        this.expDrop = expDrop;
        return this;
    }
}
