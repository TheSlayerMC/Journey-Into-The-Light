package net.jitl.common.block.trees;

import net.jitl.common.world.gen.features.featureconfig.JBaseTreeFeatureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class JTree {

    @Nullable
    protected abstract ConfiguredFeature<JBaseTreeFeatureConfig, ?> getConfiguredFeature(Random randomIn, boolean largeHive);

    public boolean growTree(ServerLevel worldIn, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, Random rand) {
        ConfiguredFeature<JBaseTreeFeatureConfig, ?> configuredfeature = this.getConfiguredFeature(rand, this.hasFlowers(worldIn, pos));
        if (configuredfeature == null) {
            return false;
        } else {
            worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 4);
            configuredfeature.config.setFromSapling();
            if (configuredfeature.place(worldIn, chunkGenerator, rand, pos)) {
                return true;
            } else {
                worldIn.setBlock(pos, state, 4);
                return false;
            }
        }
    }

    private boolean hasFlowers(LevelAccessor worldIn, BlockPos pos) {
        for (BlockPos blockpos : BlockPos.MutableBlockPos.betweenClosed(pos.below().north(2).west(2), pos.above().south(2).east(2))) {
            if (worldIn.getBlockState(blockpos).is(BlockTags.FLOWERS)) {
                return true;
            }
        }

        return false;
    }
}