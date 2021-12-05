package net.jitl.common.world.gen.features.boil;

import com.mojang.serialization.Codec;
import net.jitl.JITL;
import net.jitl.init.JBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class SulphurDepositFeature extends Feature<BlockStateFeatureConfig> {
    public SulphurDepositFeature(Codec<BlockStateFeatureConfig> codec_) {
        super(codec_);
    }

    @Override
    public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, BlockStateFeatureConfig config) {
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

            BlockPos.Mutable placePos = pos.mutable();

            int xPos = pos.getX();
            int zPos = pos.getZ();
            int yPos = reader.getHeight(Heightmap.Type.WORLD_SURFACE_WG, xPos, zPos);

            placePos.set(xPos, yPos - 1, zPos);

            BlockPos blockPos = reader.getHeightmapPos(Heightmap.Type.WORLD_SURFACE_WG, pos);

            for (int l = 0; l < 32; ++l) {
                placePos.setWithOffset(blockPos, rand.nextInt(10 + 10), 0, rand.nextInt(10 + 10));

                JITL.LOGGER.info("should be generating");
                int i = rand.nextInt(6);
                int j = rand.nextInt(6);
                int k = rand.nextInt(6);
                float f = (float) (i + j + k) * 0.333F + 0.5F;

                for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-i, -j, -k), pos.offset(i, j, k))) {
                    if (blockpos.distSqr(pos) <= (double) (f * f)) {
                        reader.setBlock(blockpos, config.state, 4);
                    }
                }

                pos = pos.offset(-1 + rand.nextInt(2), -rand.nextInt(2), -1 + rand.nextInt(2));
            }
            return true;
        }
    }
}