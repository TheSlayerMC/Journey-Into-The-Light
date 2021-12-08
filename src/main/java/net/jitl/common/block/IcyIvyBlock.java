package net.jitl.common.block;

import net.jitl.init.JBlocks;
import net.jitl.init.JParticleManager;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class IcyIvyBlock extends AbstractBodyPlantBlock {
    public static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public IcyIvyBlock(AbstractBlock.Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false);
    }

    @Override
    public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, LivingEntity entity) {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(@NotNull BlockState stateIn, @NotNull World worldIn, @NotNull BlockPos pos, Random rand) {
        if (rand.nextInt(8) == 0) {
            double d0 = (double) pos.getX() + rand.nextDouble();
            double d1 = (double) pos.getY() - 0.05D;
            double d2 = (double) pos.getZ() + rand.nextDouble();
            worldIn.addParticle(JParticleManager.CAVE_VINE.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.relative(this.growthDirection.getOpposite());
        BlockState blockstate = worldIn.getBlockState(blockpos);
        Block block = blockstate.getBlock();
        if (!this.canAttachToBlock(block)) {
            return false;
        } else {
            return block == this.getHeadBlock() || block == this.getBodyBlock() || blockstate.getMaterial().isSolid();
        }
    }

    @Override
    protected @NotNull AbstractTopPlantBlock getHeadBlock() {
        return JBlocks.ICY_IVY;
    }
}
