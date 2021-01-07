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

public class CaveVinesTopBlock extends AbstractTopPlantBlock {
    protected static final VoxelShape SHAPE = Block.box(4.0D, 9.0D, 4.0D, 12.0D, 16.0D, 12.0D);

    public CaveVinesTopBlock(AbstractBlock.Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false, 0.1D);
    }

    /**
     * Used to determine how much to grow the plant when using bonemeal. Kelp always returns 1, where as the nether vines
     * return a random value at least 1.
     */
    @Override
    protected int getBlocksToGrowWhenBonemealed(@NotNull Random rand) {
        return PlantBlockHelper.getBlocksToGrowWhenBonemealed(rand);
    }

    @Override
    public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, LivingEntity entity) {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
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
        return JBlocks.CAVE_VINES_PLANT;
    }

    @Override
    protected boolean canGrowInto(@NotNull BlockState state) {
        return PlantBlockHelper.isValidGrowthState(state);
    }
}
