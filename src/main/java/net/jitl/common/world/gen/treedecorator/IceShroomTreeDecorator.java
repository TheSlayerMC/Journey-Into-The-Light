package net.jitl.common.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import net.jitl.common.block.base.AttachedBlock;
import net.jitl.init.JBlocks;
import net.jitl.init.world.JTreeDecorators;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;

public class IceShroomTreeDecorator extends TreeDecorator {

    public static final Codec<IceShroomTreeDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(IceShroomTreeDecorator::new,
            (decorator) -> decorator.probability).codec();
    private final float probability;

    public IceShroomTreeDecorator(float float_) {
        this.probability = float_;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return JTreeDecorators.ICE_SHROOM_TREE_DECORATOR.get();
    }

    @Override
    public void place(LevelSimulatedReader level_, BiConsumer<BlockPos, BlockState> blockSetter_, Random random_, List<BlockPos> logPositions_, List<BlockPos> leafPositions_) {

    }

    public void place(WorldGenLevel seedReader_, Random random_, List<BlockPos> list_, List<BlockPos> list1_, Set<BlockPos> set_, BoundingBox mutableBoundingBox_) {
        if (!(random_.nextFloat() >= this.probability)) {
            int i = list_.get(0).getY();
            list_.stream().filter((blockPos_) -> blockPos_.getY() - i <= 10).forEach((blockPos1_) -> {
                for (Direction direction : Direction.Plane.HORIZONTAL) {
                    if (random_.nextFloat() <= 0.25F) {
                        Direction direction1 = direction.getOpposite();
                        BlockPos blockpos = blockPos1_.offset(direction1.getStepX(), 0, direction1.getStepZ());
                        if (Feature.isAir(seedReader_, blockpos)) {
                            BlockState blockstate = JBlocks.ICE_SHROOM_SHELF.defaultBlockState().setValue(AttachedBlock.FACING, direction.getOpposite());
                            //FIXME this.setBlock(seedReader_, blockpos, blockstate, set_, mutableBoundingBox_);
                        }
                    }
                }

            });
        }
    }
}