package net.jitl.common.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import net.jitl.common.block.base.AttachedBlock;
import net.jitl.init.JBlocks;
import net.jitl.init.JTreeDecorators;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.List;
import java.util.Random;
import java.util.Set;

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
    public void place(ISeedReader seedReader_, Random random_, List<BlockPos> list_, List<BlockPos> list1_, Set<BlockPos> set_, MutableBoundingBox mutableBoundingBox_) {
        if (!(random_.nextFloat() >= this.probability)) {
            int i = list_.get(0).getY();
            list_.stream().filter((blockPos_) -> blockPos_.getY() - i <= 10).forEach((blockPos1_) -> {
                for (Direction direction : Direction.Plane.HORIZONTAL) {
                    if (random_.nextFloat() <= 0.25F) {
                        Direction direction1 = direction.getOpposite();
                        BlockPos blockpos = blockPos1_.offset(direction1.getStepX(), 0, direction1.getStepZ());
                        if (Feature.isAir(seedReader_, blockpos)) {
                            BlockState blockstate = JBlocks.ICE_SHROOM_SHELF.defaultBlockState().setValue(AttachedBlock.FACING, direction.getOpposite());
                            this.setBlock(seedReader_, blockpos, blockstate, set_, mutableBoundingBox_);
                        }
                    }
                }

            });
        }
    }
}