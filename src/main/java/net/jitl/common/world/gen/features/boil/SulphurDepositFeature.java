package net.jitl.common.world.gen.features.boil;

import com.mojang.serialization.Codec;
import net.jitl.common.block.base.AttachedBlock;
import net.jitl.init.JBlocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.Random;

public class SulphurDepositFeature extends Feature<BlockStateConfiguration> {
    public SulphurDepositFeature(Codec<BlockStateConfiguration> codec_) {
        super(codec_);
    }

    @Override
    public boolean place(WorldGenLevel reader, ChunkGenerator generator, Random rand, BlockPos pos, BlockStateConfiguration config) {
        if (reader.getBlockState(pos.below()) != JBlocks.VOLCANIC_SAND.defaultBlockState()) {
            return false;
        } else {
            if (pos.getY() > 1) {
                if (reader.isEmptyBlock(pos.below())) {
                    return false;
                }
            }

            if (pos.getY() <= 1) {
                return false;
            }

            BlockPos.MutableBlockPos placePos = pos.mutable();

            int xPos = pos.getX();
            int zPos = pos.getZ();
            int yPos = reader.getHeight(Heightmap.Types.WORLD_SURFACE_WG, xPos, zPos);

            placePos.set(xPos, yPos - 1, zPos);

            BlockPos blockPos = reader.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, pos);

            for (int l = 0; l < 2; ++l) {
                placePos.setWithOffset(blockPos, rand.nextInt(10 + 10), 0, rand.nextInt(10 + 10));

                int i = rand.nextInt(3);
                int j = rand.nextInt(3);
                int k = rand.nextInt(3);
                float f = (float) (i + j + k) * 0.333F + 0.5F;

                for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-i, -j, -k), pos.offset(i, j, k))) {
                    if (blockpos.distSqr(pos) <= (double) (f * f)) {
                        reader.setBlock(blockpos, config.state, 4);
                        for (Direction direction : Direction.values()) {
                            BlockPos blockpos1 = blockPos.relative(direction);
                            if (reader.getBlockState(blockpos1) == Blocks.AIR.defaultBlockState()) {
                                BlockState blockstate1 = JBlocks.SULPHUR_CRYSTAL.defaultBlockState().setValue(AttachedBlock.FACING, direction);
                                if (reader.getBlockState(blockPos) == config.state) {
                                    reader.setBlock(blockpos1, blockstate1, 2);
                                }
                            }
                        }
                    }
                }

                pos = pos.offset(-1 + rand.nextInt(2), -rand.nextInt(2), -1 + rand.nextInt(2));
            }
            return true;
        }
    }
}