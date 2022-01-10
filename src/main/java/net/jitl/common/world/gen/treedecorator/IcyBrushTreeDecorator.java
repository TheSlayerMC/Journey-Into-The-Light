package net.jitl.common.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import net.jitl.init.JBlocks;
import net.jitl.init.world.JTreeDecorators;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class IcyBrushTreeDecorator extends TreeDecorator {
    public static final Codec<IcyBrushTreeDecorator> CODEC;
    public static final IcyBrushTreeDecorator INSTANCE = new IcyBrushTreeDecorator();

    protected TreeDecoratorType<?> type() {
        return JTreeDecorators.ICY_BRUSH_TREE_DECORATOR.get();
    }

    @Override
    public void place(LevelSimulatedReader seedReader_, BiConsumer<BlockPos, BlockState> set_, Random random_, List<BlockPos> list_, List<BlockPos> list1_) {
        list1_.forEach((blockPos1_) -> {
            int chance = 1;
            random_.nextInt(chance);
            BlockPos blockpos = blockPos1_.west();
            if (Feature.isAir(seedReader_, blockpos)) {
                this.addHangingVine(seedReader_, random_, blockpos, VineBlock.EAST, set_);
            }

            random_.nextInt(chance);
            BlockPos blockpos1 = blockPos1_.east();
            if (Feature.isAir(seedReader_, blockpos1)) {
                this.addHangingVine(seedReader_, random_, blockpos1, VineBlock.WEST, set_);
            }

            random_.nextInt(chance);
            BlockPos blockpos2 = blockPos1_.north();
            if (Feature.isAir(seedReader_, blockpos2)) {
                this.addHangingVine(seedReader_, random_, blockpos2, VineBlock.SOUTH, set_);
            }

            random_.nextInt(chance);
            BlockPos blockpos3 = blockPos1_.south();
            if (Feature.isAir(seedReader_, blockpos3)) {
                this.addHangingVine(seedReader_, random_, blockpos3, VineBlock.NORTH, set_);
            }
        });
    }

    private void addHangingVine(LevelSimulatedReader worldGenerationReader_, Random random, BlockPos blockPos_, BooleanProperty booleanProperty_, BiConsumer<BlockPos, BlockState> blockSetter_) {
        placeBrush(blockSetter_, blockPos_, booleanProperty_);
        int i = random.nextInt(4) + 3;

        for (BlockPos blockpos = blockPos_.below(); Feature.isAir(worldGenerationReader_, blockpos) && i > 0; --i) {
            placeBrush(blockSetter_, blockpos, booleanProperty_);
            blockpos = blockpos.below();
        }
    }

    protected void placeBrush(BiConsumer<BlockPos, BlockState> blockSetter_, BlockPos pos_, BooleanProperty sideProperty_) {
        blockSetter_.accept(pos_, JBlocks.ICY_BRUSH.defaultBlockState().setValue(sideProperty_, true));
    }

    static {
        CODEC = Codec.unit(() -> INSTANCE);
    }
}