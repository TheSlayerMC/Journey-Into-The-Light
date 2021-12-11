package net.jitl.common.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import net.jitl.init.JBlocks;
import net.jitl.init.JTreeDecorators;
import net.minecraft.block.VineBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class CharredBrushTreeDecorator extends TreeDecorator {
    public static final Codec<CharredBrushTreeDecorator> CODEC;
    public static final CharredBrushTreeDecorator INSTANCE = new CharredBrushTreeDecorator();

    protected TreeDecoratorType<?> type() {
        return JTreeDecorators.CHARRED_BRUSH_TREE_DECORATOR.get();
    }

    public void place(ISeedReader seedReader_, Random random_, List<BlockPos> list_, List<BlockPos> list1_, Set<BlockPos> set_, MutableBoundingBox mutableBoundingBox_) {
        list1_.forEach((blockPos1_) -> {
            int chance = 5;
            if (random_.nextInt(chance) == 0) {
                BlockPos blockpos = blockPos1_.west();
                if (Feature.isAir(seedReader_, blockpos)) {
                    this.addHangingVine(seedReader_, random_, blockpos, VineBlock.EAST, set_, mutableBoundingBox_);
                }
            }

            if (random_.nextInt(chance) == 0) {
                BlockPos blockpos1 = blockPos1_.east();
                if (Feature.isAir(seedReader_, blockpos1)) {
                    this.addHangingVine(seedReader_, random_, blockpos1, VineBlock.WEST, set_, mutableBoundingBox_);
                }
            }

            if (random_.nextInt(chance) == 0) {
                BlockPos blockpos2 = blockPos1_.north();
                if (Feature.isAir(seedReader_, blockpos2)) {
                    this.addHangingVine(seedReader_, random_, blockpos2, VineBlock.SOUTH, set_, mutableBoundingBox_);
                }
            }

            if (random_.nextInt(chance) == 0) {
                BlockPos blockpos3 = blockPos1_.south();
                if (Feature.isAir(seedReader_, blockpos3)) {
                    this.addHangingVine(seedReader_, random_, blockpos3, VineBlock.NORTH, set_, mutableBoundingBox_);
                }
            }
        });
    }

    private void addHangingVine(IWorldGenerationReader worldGenerationReader_, Random random, BlockPos blockPos_, BooleanProperty booleanProperty_, Set<BlockPos> set_, MutableBoundingBox mutableBoundingBox_) {
        this.placeBrush(worldGenerationReader_, blockPos_, booleanProperty_, set_, mutableBoundingBox_);
        int i = random.nextInt(3) + 2;

        for (BlockPos blockpos = blockPos_.below(); Feature.isAir(worldGenerationReader_, blockpos) && i > 0; --i) {
            this.placeBrush(worldGenerationReader_, blockpos, booleanProperty_, set_, mutableBoundingBox_);
            blockpos = blockpos.below();
        }
    }

    protected void placeBrush(IWorldWriter worldWriter_, BlockPos blockPos_, BooleanProperty booleanProperty_, Set<BlockPos> set_, MutableBoundingBox mutableBoundingBox_) {
        this.setBlock(worldWriter_, blockPos_, JBlocks.CHARRED_BRUSH.defaultBlockState().setValue(booleanProperty_, true), set_, mutableBoundingBox_);
    }

    static {
        CODEC = Codec.unit(() -> INSTANCE);
    }
}