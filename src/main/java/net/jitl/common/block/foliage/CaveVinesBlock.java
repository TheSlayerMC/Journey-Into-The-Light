package net.jitl.common.block.foliage;

import net.jitl.core.init.JBlocks;
import net.jitl.core.init.JParticleManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class CaveVinesBlock extends GrowingPlantBodyBlock {
    public static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public CaveVinesBlock(Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false);
    }

    @Override
    public boolean isLadder(BlockState state, LevelReader world, BlockPos pos, LivingEntity entity) {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(@NotNull BlockState stateIn, @NotNull Level worldIn, @NotNull BlockPos pos, Random rand) {
        if (rand.nextInt(8) == 0) {
            double d0 = (double) pos.getX() + rand.nextDouble();
            double d1 = (double) pos.getY() - 0.05D;
            double d2 = (double) pos.getZ() + rand.nextDouble();
            worldIn.addParticle(JParticleManager.CAVE_VINE.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.relative(this.growthDirection.getOpposite());
        BlockState blockstate = worldIn.getBlockState(blockpos);
        Block block = blockstate.getBlock();
        if (!this.canAttachTo(blockstate)) {
            return false;
        } else {
            return block == this.getHeadBlock() || block == this.getBodyBlock() || blockstate.getMaterial().isSolid();
        }
    }

    @Override
    protected @NotNull GrowingPlantHeadBlock getHeadBlock() {
        return JBlocks.DEEPVINE;
    }
}
