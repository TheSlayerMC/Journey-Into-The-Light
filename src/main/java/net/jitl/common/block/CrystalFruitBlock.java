package net.jitl.common.block;

import net.jitl.init.JBlocks;
import net.jitl.init.JParticleManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class CrystalFruitBlock extends Block {
    public CrystalFruitBlock(Properties properties) {
        super(properties);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(@NotNull BlockState stateIn, @NotNull World worldIn, @NotNull BlockPos pos, Random rand) {
        if (rand.nextInt(2) == 0) {
            double d0 = (double) pos.getX() + rand.nextDouble();
            double d1 = (double) pos.getY() - 0.04D;
            double d2 = (double) pos.getZ() + rand.nextDouble();
            worldIn.addParticle(JParticleManager.CRYSTAL_FRUIT.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (!worldIn.isAreaLoaded(pos, 1)) {
            return;
        }
        if (!state.canSurvive(worldIn, pos)) {
            worldIn.destroyBlock(pos, true);
        }
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (!stateIn.canSurvive(worldIn, currentPos)) {
            worldIn.getBlockTicks().scheduleTick(currentPos, this, 1);
        }
        return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.above();
        BlockState blockstate = worldIn.getBlockState(blockpos);
        Block block = blockstate.getBlock();
        return block == JBlocks.ICY_IVY || block == JBlocks.ICY_IVY_PLANT || blockstate.getMaterial().isSolid();
    }
}
