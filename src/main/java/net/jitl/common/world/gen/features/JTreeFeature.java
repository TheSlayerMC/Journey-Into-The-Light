package net.jitl.common.world.gen.features;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import net.jitl.common.world.gen.features.featureconfig.JBaseTreeFeatureConfig;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.shapes.BitSetDiscreteVoxelShape;
import net.minecraft.world.phys.shapes.DiscreteVoxelShape;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelWriter;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.LevelSimulatedRW;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;

import java.util.*;

//TODO: create custom codec for ground block and dirt block
public class JTreeFeature extends Feature<JBaseTreeFeatureConfig> {
    public JTreeFeature(Codec<JBaseTreeFeatureConfig> codec_) {
        super(codec_);
    }

    public static boolean isFree(LevelSimulatedReader worldGenerationBaseReader_, BlockPos blockPos_) {
        return validTreePos(worldGenerationBaseReader_, blockPos_) || worldGenerationBaseReader_.isStateAtPosition(blockPos_, (blockState7_) -> {
            return blockState7_.is(BlockTags.LOGS);
        });
    }

    private static boolean isVine(LevelSimulatedReader worldGenerationBaseReader_, BlockPos blockPos_) {
        return worldGenerationBaseReader_.isStateAtPosition(blockPos_, (blockState6_) -> {
            return blockState6_.is(Blocks.VINE);
        });
    }

    private static boolean isBlockWater(LevelSimulatedReader worldGenerationBaseReader_, BlockPos blockPos_) {
        return worldGenerationBaseReader_.isStateAtPosition(blockPos_, (blockState5_) -> {
            return blockState5_.is(Blocks.WATER);
        });
    }

    public static boolean isAirOrLeaves(LevelSimulatedReader worldGenerationBaseReader_, BlockPos blockPos_) {
        return worldGenerationBaseReader_.isStateAtPosition(blockPos_, (blockState4_) -> {
            return blockState4_.isAir() || blockState4_.is(BlockTags.LEAVES);
        });
    }

    private static boolean isGroundBlock(LevelSimulatedReader worldGenerationBaseReader_, BlockPos blockPos_, JBaseTreeFeatureConfig config, Random random) {
        return worldGenerationBaseReader_.isStateAtPosition(blockPos_, (blockState3_) ->
                blockState3_ == config.dirtProvider.getState(random, blockPos_));
    }

    private static boolean isReplaceablePlant(LevelSimulatedReader worldGenerationBaseReader_, BlockPos blockPos_) {
        return worldGenerationBaseReader_.isStateAtPosition(blockPos_, (blockState2_) -> {
            Material material = blockState2_.getMaterial();
            return material == Material.REPLACEABLE_PLANT;
        });
    }

    public static void setBlockKnownShape(LevelWriter worldWriter_, BlockPos blockPos_, BlockState blockState_) {
        worldWriter_.setBlock(blockPos_, blockState_, 19);
    }

    public static boolean validTreePos(LevelSimulatedReader worldGenerationBaseReader_, BlockPos blockPos_) {
        return isAirOrLeaves(worldGenerationBaseReader_, blockPos_) || isReplaceablePlant(worldGenerationBaseReader_, blockPos_) || isBlockWater(worldGenerationBaseReader_, blockPos_);
    }

    /**
     * Called when placing the tree feature.
     */
    private boolean doPlace(LevelSimulatedRW generationReader, Random rand, BlockPos positionIn, Set<BlockPos> set_, Set<BlockPos> set1_, BoundingBox boundingBoxIn, JBaseTreeFeatureConfig configIn) {
        int i = configIn.trunkPlacer.getTreeHeight(rand);
        int j = configIn.foliagePlacer.foliageHeight(rand, i, configIn);
        int k = i - j;
        int l = configIn.foliagePlacer.foliageRadius(rand, k);
        BlockPos blockpos;
        blockpos = positionIn;

        if (blockpos.getY() >= 1 && blockpos.getY() + i + 1 <= 256) {
            if (!isGroundBlock(generationReader, blockpos.below(), configIn, rand)) {
                return false;
            } else {
                OptionalInt optionalint = configIn.minimumSize.minClippedHeight();
                int l1 = this.getMaxFreeTreeHeight(generationReader, i, blockpos, configIn);
                if (l1 >= i || optionalint.isPresent() && l1 >= optionalint.getAsInt()) {
                    List<FoliagePlacer.FoliageAttachment> list = configIn.trunkPlacer.placeTrunk(generationReader, rand, l1, blockpos, set_, boundingBoxIn, configIn);
                    list.forEach((foliage_) -> {
                        configIn.foliagePlacer.createFoliage(generationReader, rand, configIn, l1, foliage_, j, l, set1_, boundingBoxIn);
                    });
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    private int getMaxFreeTreeHeight(LevelSimulatedReader worldGenerationBaseReader_, int int_, BlockPos blockPos_, JBaseTreeFeatureConfig baseFrozenTreeFetureConfig_) {
        BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();

        for (int i = 0; i <= int_ + 1; ++i) {
            int j = baseFrozenTreeFetureConfig_.minimumSize.getSizeAtHeight(int_, i);

            for (int k = -j; k <= j; ++k) {
                for (int l = -j; l <= j; ++l) {
                    blockpos$mutable.setWithOffset(blockPos_, k, i, l);
                    if (!isFree(worldGenerationBaseReader_, blockpos$mutable) || !baseFrozenTreeFetureConfig_.ignoreVines && isVine(worldGenerationBaseReader_, blockpos$mutable)) {
                        return i - 2;
                    }
                }
            }
        }

        return int_;
    }

    @Override
    protected void setBlock(LevelWriter worldIn, BlockPos pos, BlockState state) {
        setBlockKnownShape(worldIn, pos, state);
    }

    @Override
    public final boolean place(WorldGenLevel reader, ChunkGenerator generator, Random rand, BlockPos pos, JBaseTreeFeatureConfig config) {
        Set<BlockPos> set = Sets.newHashSet();
        Set<BlockPos> set1 = Sets.newHashSet();
        Set<BlockPos> set2 = Sets.newHashSet();
        BoundingBox mutableboundingbox = BoundingBox.getUnknownBox();
        boolean flag = this.doPlace(reader, rand, pos, set, set1, mutableboundingbox, config);
        if (mutableboundingbox.x0 <= mutableboundingbox.x1 && flag && !set.isEmpty()) {
            if (!config.decorators.isEmpty()) {
                List<BlockPos> list = Lists.newArrayList(set);
                List<BlockPos> list1 = Lists.newArrayList(set1);
                list.sort(Comparator.comparingInt(Vec3i::getY));
                list1.sort(Comparator.comparingInt(Vec3i::getY));
                config.decorators.forEach((treeDecorator_) -> {
                    treeDecorator_.place(reader, rand, list, list1, set2, mutableboundingbox);
                });
            }

            DiscreteVoxelShape voxelshapepart = this.updateLeaves(reader, mutableboundingbox, set, set2);
            StructureTemplate.updateShapeAtEdge(reader, 3, voxelshapepart, mutableboundingbox.x0, mutableboundingbox.y0, mutableboundingbox.z0);
            return true;
        } else {
            return false;
        }
    }

    private DiscreteVoxelShape updateLeaves(LevelAccessor world_, BoundingBox mutableBoundingBox_, Set<BlockPos> set_, Set<BlockPos> set1_) {
        List<Set<BlockPos>> list = Lists.newArrayList();
        DiscreteVoxelShape voxelshapepart = new BitSetDiscreteVoxelShape(mutableBoundingBox_.getXSpan(), mutableBoundingBox_.getYSpan(), mutableBoundingBox_.getZSpan());
        int i = 6;

        for (int j = 0; j < 6; ++j) {
            list.add(Sets.newHashSet());
        }

        BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();

        for (BlockPos blockpos : Lists.newArrayList(set1_)) {
            if (mutableBoundingBox_.isInside(blockpos)) {
                voxelshapepart.setFull(blockpos.getX() - mutableBoundingBox_.x0, blockpos.getY() - mutableBoundingBox_.y0, blockpos.getZ() - mutableBoundingBox_.z0, true, true);
            }
        }

        for (BlockPos blockpos1 : Lists.newArrayList(set_)) {
            if (mutableBoundingBox_.isInside(blockpos1)) {
                voxelshapepart.setFull(blockpos1.getX() - mutableBoundingBox_.x0, blockpos1.getY() - mutableBoundingBox_.y0, blockpos1.getZ() - mutableBoundingBox_.z0, true, true);
            }

            for (Direction direction : Direction.values()) {
                blockpos$mutable.setWithOffset(blockpos1, direction);
                if (!set_.contains(blockpos$mutable)) {
                    BlockState blockstate = world_.getBlockState(blockpos$mutable);
                    if (blockstate.hasProperty(BlockStateProperties.DISTANCE)) {
                        list.get(0).add(blockpos$mutable.immutable());
                        setBlockKnownShape(world_, blockpos$mutable, blockstate.setValue(BlockStateProperties.DISTANCE, Integer.valueOf(1)));
                        if (mutableBoundingBox_.isInside(blockpos$mutable)) {
                            voxelshapepart.setFull(blockpos$mutable.getX() - mutableBoundingBox_.x0, blockpos$mutable.getY() - mutableBoundingBox_.y0, blockpos$mutable.getZ() - mutableBoundingBox_.z0, true, true);
                        }
                    }
                }
            }
        }

        for (int l = 1; l < 6; ++l) {
            Set<BlockPos> set = list.get(l - 1);
            Set<BlockPos> set1 = list.get(l);

            for (BlockPos blockpos2 : set) {
                if (mutableBoundingBox_.isInside(blockpos2)) {
                    voxelshapepart.setFull(blockpos2.getX() - mutableBoundingBox_.x0, blockpos2.getY() - mutableBoundingBox_.y0, blockpos2.getZ() - mutableBoundingBox_.z0, true, true);
                }

                for (Direction direction1 : Direction.values()) {
                    blockpos$mutable.setWithOffset(blockpos2, direction1);
                    if (!set.contains(blockpos$mutable) && !set1.contains(blockpos$mutable)) {
                        BlockState blockstate1 = world_.getBlockState(blockpos$mutable);
                        if (blockstate1.hasProperty(BlockStateProperties.DISTANCE)) {
                            int k = blockstate1.getValue(BlockStateProperties.DISTANCE);
                            if (k > l + 1) {
                                BlockState blockstate2 = blockstate1.setValue(BlockStateProperties.DISTANCE, Integer.valueOf(l + 1));
                                setBlockKnownShape(world_, blockpos$mutable, blockstate2);
                                if (mutableBoundingBox_.isInside(blockpos$mutable)) {
                                    voxelshapepart.setFull(blockpos$mutable.getX() - mutableBoundingBox_.x0, blockpos$mutable.getY() - mutableBoundingBox_.y0, blockpos$mutable.getZ() - mutableBoundingBox_.z0, true, true);
                                }

                                set1.add(blockpos$mutable.immutable());
                            }
                        }
                    }
                }
            }
        }

        return voxelshapepart;
    }
}