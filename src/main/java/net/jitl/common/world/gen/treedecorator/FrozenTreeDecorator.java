package net.jitl.common.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import net.jitl.common.block.base.AttachedBlock;
import net.jitl.core.init.JBlocks;
import net.jitl.core.init.world.JTreeDecorators;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class FrozenTreeDecorator extends TreeDecorator {

    public static final Codec<FrozenTreeDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(FrozenTreeDecorator::new,
            (decorator) -> decorator.probability).codec();
    private final float probability;

    public FrozenTreeDecorator(float float_) {
        this.probability = float_;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return JTreeDecorators.FROZEN_DECORATOR.get();
    }

    @Override
    public void place(LevelSimulatedReader seedReader_, BiConsumer<BlockPos, BlockState> set_, Random random_, List<BlockPos> list_, List<BlockPos> list1_) {
        if (!(random_.nextFloat() >= this.probability)) {
            int i = list_.get(0).getY();
            list_.stream().filter((blockPos_) -> blockPos_.getY() - i <= 16).forEach((blockPos1_) -> {
                for (Direction direction : Direction.Plane.HORIZONTAL) {
                    if (random_.nextFloat() <= 0.25F) {
                        Direction direction1 = direction.getOpposite();
                        BlockPos blockpos = blockPos1_.offset(direction1.getStepX(), 0, direction1.getStepZ());
                        if (Feature.isAir(seedReader_, blockpos)) {
                            BlockState blockstate = JBlocks.FROST_CRYSTAL_LARGE.defaultBlockState().setValue(AttachedBlock.FACING, direction.getOpposite());
                            set_.accept(blockpos, blockstate);
                        }
                    }
                }

            });
        }
    }
}