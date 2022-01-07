package net.jitl.common.world.gen.features.boil;

import com.mojang.serialization.Codec;
import net.jitl.init.JBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;
import ru.timeconqueror.timecore.api.util.GenHelper;

import java.util.Random;

public class TorridCrystalFeature extends Feature<BlockStateConfiguration> {
    public TorridCrystalFeature(Codec<BlockStateConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<BlockStateConfiguration> context) {
        BlockPos pos = context.origin();
        WorldGenLevel reader = context.level();
        Random rand = context.random();
        if (!(reader.getBlockState(pos.below()).getMaterial().isSolid())) {
            return false;
        } else {
            if (rand.nextInt(50) == 0) {
                int height = 6;
                int radius = rand.nextInt(2) + 2;
                GenHelper.genFilledCircle(radius, Direction.Axis.Y, (blockPos1) -> {
                    BlockPos.MutableBlockPos placePos = blockPos1.mutable();

                    int xPos = pos.getX();
                    int zPos = pos.getZ();
                    int yPos = reader.getHeight(Heightmap.Types.WORLD_SURFACE_WG, xPos, zPos);

                    placePos.move(xPos, yPos - 5, zPos);

                    for (int l = 0; l < height; ++l) {
                        placePos.move(Direction.UP);
                        reader.setBlock(placePos, JBlocks.TORRID_CRYSTAL.defaultBlockState(), 1);
                    }
                });
                return true;
            }
            return false;
        }
    }
}
