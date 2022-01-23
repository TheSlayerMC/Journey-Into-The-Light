package net.jitl.common.block.base;

import net.jitl.core.init.JTags;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

import java.util.Random;

public class JLeavesBlock extends LeavesBlock {

    public JLeavesBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random random) {
        if(!state.getValue(PERSISTENT) && state.getValue(DISTANCE) == 7) {
            //this.dropItem(state, worldIn, pos);
            //worldIn.removeBlock(pos, false);
            //FIXME still removes leaves
        }
    }

     public void dropItem(BlockState state, ServerLevel world, BlockPos pos) {
         state.spawnAfterBreak(world, pos, new ItemStack(Items.APPLE, 1));
     }

    @Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
        worldIn.setBlock(pos, updateDistance(state, worldIn, pos), 3);
    }

    private static BlockState updateDistance(BlockState state_, LevelAccessor level_, BlockPos pos_) {
        int i = 7;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for(Direction direction : Direction.values()) {
            pos.setWithOffset(pos_, direction);
            i = Math.min(i, getDistanceAt(level_.getBlockState(pos)) + 1);
            if(i == 1) {
                break;
            }
        }
        return state_.setValue(DISTANCE, Integer.valueOf(i));
    }

    private static int getDistanceAt(BlockState state) {
        if(state.is(JTags.JLOGS)) {
            return 0;
        } else {
            return state.getBlock() instanceof JLeavesBlock ? state.getValue(DISTANCE) : 7;
        }
    }
}
