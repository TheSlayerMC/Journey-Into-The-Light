package net.jitl.common.world.gen.features.boil;

import com.mojang.serialization.Codec;
import net.jitl.init.JBlocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import ru.timeconqueror.timecore.api.util.GenHelper;

import java.util.Random;

public class TorridCrystalFeature extends Feature<BlockStateFeatureConfig> {
    public TorridCrystalFeature(Codec<BlockStateFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, BlockStateFeatureConfig config) {
        if (!(reader.getBlockState(pos.below()).getMaterial().isSolid())) {
            return false;
        } else {
            if (rand.nextInt(50) == 0) {
                int height = 6;
                int radius = rand.nextInt(2) + 2;
                GenHelper.genFilledCircle(radius, Direction.Axis.Y, (blockPos1) -> {
                    BlockPos.Mutable placePos = blockPos1.mutable();

                    int xPos = pos.getX();
                    int zPos = pos.getZ();
                    int yPos = reader.getHeight(Heightmap.Type.WORLD_SURFACE_WG, xPos, zPos);

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
