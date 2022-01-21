package net.jitl.common.world.gen.treedecorator;

import com.mojang.serialization.Codec;
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

public class CrystalFruitTreeDecorator extends TreeDecorator {
    public static final Codec<CrystalFruitTreeDecorator> CODEC = Codec.intRange(0, 10).fieldOf("height").xmap(CrystalFruitTreeDecorator::new,
            (decorator) -> decorator.height).codec();

    private final int height;

    public CrystalFruitTreeDecorator(int height) {
        this.height = height;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return JTreeDecorators.CRYSTAL_FRUIT_DECORATOR.get();
    }

    @Override
    public void place(LevelSimulatedReader seedReader_, BiConsumer<BlockPos, BlockState> set_, Random random_, List<BlockPos> list_, List<BlockPos> list1_) {
        list1_.forEach((blockPos1_) -> {
            if (random_.nextInt(8) == 0) {
                BlockPos blockpos = blockPos1_.below();
                if (Feature.isAir(seedReader_, blockpos) && Feature.isAir(seedReader_, blockpos.below(height + 4))) {
                    BlockPos.MutableBlockPos mutable = blockpos.mutable();
                    addHangingVine(seedReader_, random_, mutable, set_);
                }
            }
        });
    }

    private void addHangingVine(LevelSimulatedReader world, Random random, BlockPos.MutableBlockPos mutable, BiConsumer<BlockPos, BlockState> set_) {
        int length = random.nextInt(4) + 2;
        for (int i = 0; i <= length; ++i) {
            if (i == length) {
                set_.accept(mutable, JBlocks.CRYSTAL_FRUIT.defaultBlockState());
                break;
            }
            set_.accept(mutable, JBlocks.ICY_IVY_PLANT.defaultBlockState());

            mutable.move(Direction.DOWN);
        }
    }
}