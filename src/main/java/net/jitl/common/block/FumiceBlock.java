package net.jitl.common.block;

import com.google.common.collect.Lists;
import net.jitl.init.JBlocks;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Queue;

public class FumiceBlock extends Block {

    public FumiceBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    @Override
    public void onPlace(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!oldState.is(state.getBlock())) {
            this.tryAbsorbLava(worldIn, pos);
        }
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        this.tryAbsorbLava(worldIn, pos);
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
    }

    protected void tryAbsorbLava(World worldIn, BlockPos pos) {
        if (this.removeLavaBreadthFirstSearch(worldIn, pos)) {
            worldIn.setBlock(pos, JBlocks.FILLED_FUMICE.defaultBlockState(), 2);
            worldIn.levelEvent(2001, pos, Block.getId(Blocks.LAVA.defaultBlockState()));
        }
    }

    private boolean removeLavaBreadthFirstSearch(World worldIn, BlockPos pos) {
        Queue<Tuple<BlockPos, Integer>> queue = Lists.newLinkedList();
        queue.add(new Tuple<>(pos, 0));
        int i = 0;

        while (!queue.isEmpty()) {
            Tuple<BlockPos, Integer> tuple = queue.poll();
            BlockPos blockpos = tuple.getA();
            int j = tuple.getB();

            for (Direction direction : Direction.values()) {
                BlockPos blockpos1 = blockpos.relative(direction);
                BlockState blockstate = worldIn.getBlockState(blockpos1);
                FluidState fluidstate = worldIn.getFluidState(blockpos1);
                Material material = blockstate.getMaterial();
                if (fluidstate.is(FluidTags.LAVA)) {
                    if (blockstate.getBlock() instanceof IBucketPickupHandler && ((IBucketPickupHandler) blockstate.getBlock()).takeLiquid(worldIn, blockpos1, blockstate) != Fluids.EMPTY) {
                        ++i;
                        if (j < 6) {
                            queue.add(new Tuple<>(blockpos1, j + 1));
                        }
                    } else if (blockstate.getBlock() instanceof FlowingFluidBlock) {
                        worldIn.setBlock(blockpos1, Blocks.AIR.defaultBlockState(), 3);
                        ++i;
                        if (j < 6) {
                            queue.add(new Tuple<>(blockpos1, j + 1));
                        }
                    }
                }
            }

            if (i > 64) {
                break;
            }
        }

        return i > 0;
    }
}