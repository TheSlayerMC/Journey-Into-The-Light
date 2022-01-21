package net.jitl.common.block;

import net.jitl.core.init.JBlocks;
import net.jitl.core.init.JParticleManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class CaveVinesTopBlock extends GrowingPlantHeadBlock {
    protected static final VoxelShape SHAPE = Block.box(4.0D, 9.0D, 4.0D, 12.0D, 16.0D, 12.0D);

    public CaveVinesTopBlock(Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false, 0.1D);
    }

    /**
     * Used to determine how much to grow the plant when using bonemeal. Kelp always returns 1, where as the nether vines
     * return a random value at least 1.
     */
    @Override
    protected int getBlocksToGrowWhenBonemealed(@NotNull Random rand) {
        return NetherVines.getBlocksToGrowWhenBonemealed(rand);
    }

    @Override
    public boolean isLadder(BlockState state, LevelReader world, BlockPos pos, LivingEntity entity) {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(8) == 0) {
            BlockPos blockpos = pos.below();
            if (worldIn.isEmptyBlock(blockpos)) {
                double d0 = (double) pos.getX() + rand.nextDouble();
                double d1 = (double) pos.getY() - 0.05D;
                double d2 = (double) pos.getZ() + rand.nextDouble();
                worldIn.addParticle(JParticleManager.CAVE_VINE.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected @NotNull Block getBodyBlock() {
        return JBlocks.DEEPVINE_PLANT;
    }

    @Override
    protected boolean canGrowInto(@NotNull BlockState state) {
        return NetherVines.isValidGrowthState(state);
    }
}
