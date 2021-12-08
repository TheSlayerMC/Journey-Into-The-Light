package net.jitl.common.block;

import net.jitl.init.JBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IWorldReader;
import org.jetbrains.annotations.NotNull;

public class IcyIvyBlock extends AbstractBodyPlantBlock {
    public static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public IcyIvyBlock(AbstractBlock.Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false);
    }

    @Override
    public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, LivingEntity entity) {
        return true;
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.relative(this.growthDirection.getOpposite());
        BlockState blockstate = worldIn.getBlockState(blockpos);
        Block block = blockstate.getBlock();
        if (!this.canAttachToBlock(block)) {
            return false;
        } else {
            return block == this.getHeadBlock() || block == this.getBodyBlock() || blockstate.getMaterial().isSolid() || block == JBlocks.FROZEN_LEAVES;
        }
    }

    @Override
    protected @NotNull AbstractTopPlantBlock getHeadBlock() {
        return JBlocks.ICY_IVY;
    }
}
