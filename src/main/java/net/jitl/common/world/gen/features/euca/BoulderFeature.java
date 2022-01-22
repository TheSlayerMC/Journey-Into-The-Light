package net.jitl.common.world.gen.features.euca;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;

import java.util.Random;

public class BoulderFeature extends Feature<BlockStateConfiguration> {
    public BoulderFeature(Codec<BlockStateConfiguration> codec_) {
        super(codec_);
    }

    @Override
    public boolean place(FeaturePlaceContext<BlockStateConfiguration> context_) {
        WorldGenLevel reader = context_.level();
        BlockPos pos = context_.origin();
        Random rand = context_.random();
        BlockStateConfiguration config = context_.config();

        if (reader.isEmptyBlock(pos.below())) {
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
                placePos.setWithOffset(blockPos, rand.nextInt(20), 0, rand.nextInt(20));

                int size = rand.nextInt(2) + 1;
                float f = (float) (size + size + size) * 0.333F + 0.5F;

                for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-size, -size, -size), pos.offset(size, size, size))) {
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