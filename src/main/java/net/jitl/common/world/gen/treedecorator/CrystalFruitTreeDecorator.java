package net.jitl.common.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import net.jitl.init.JBlocks;
import net.jitl.init.JTreeDecorators;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class CrystalFruitTreeDecorator extends TreeDecorator {
    public static final Codec<CrystalFruitTreeDecorator> CODEC;
    public static final CrystalFruitTreeDecorator INSTANCE = new CrystalFruitTreeDecorator();

    @Override
    protected TreeDecoratorType<?> type() {
        return JTreeDecorators.CRYSTAL_FRUIT_DECORATOR.get();
    }

    @Override
    public void place(ISeedReader seedReader_, Random random_, List<BlockPos> list_, List<BlockPos> list1_, Set<BlockPos> set_, MutableBoundingBox mutableBoundingBox_) {
        list1_.forEach((blockPos1_) -> {
            if (random_.nextInt(4) == 0) {
                BlockPos blockpos = blockPos1_.below();
                if (Feature.isAir(seedReader_, blockpos)) {
                    BlockPos.Mutable mutable = blockpos.mutable();
                    addHangingVine(seedReader_, mutable, set_, mutableBoundingBox_);
                }
            }
        });
    }

    private void addHangingVine(IWorldGenerationReader world, BlockPos.Mutable mutable, Set<BlockPos> set_, MutableBoundingBox mutableBoundingBox_) {
        for (int i = 0; i <= 5; ++i) {
            if (i == 5) {
                this.setBlock(world, mutable, JBlocks.CRYSTAL_FRUIT.defaultBlockState(), set_, mutableBoundingBox_);
                break;
            }
            world.setBlock(mutable, JBlocks.ICY_IVY_PLANT.defaultBlockState(), 2);

            mutable.move(Direction.DOWN);
        }
    }

    static {
        CODEC = Codec.unit(() -> INSTANCE);
    }
}