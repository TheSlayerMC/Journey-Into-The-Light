package net.jitl.common.world.gen.features.boil;

import com.mojang.serialization.Codec;
import net.jitl.init.JBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class ScorchedCactusFeature extends Feature<NoFeatureConfig> {

    public ScorchedCactusFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        if (reader.getBlockState(pos.below()) != JBlocks.VOLCANIC_SAND.defaultBlockState()) {
            return false;
        } else {

            BlockPos.Mutable placePos = pos.mutable();

            int xPos = pos.getX();
            int zPos = pos.getZ();
            int yPos = reader.getHeight(Heightmap.Type.WORLD_SURFACE_WG, xPos, zPos);

            placePos.set(xPos, yPos - 1, zPos);

            BlockPos blockPos = reader.getHeightmapPos(Heightmap.Type.WORLD_SURFACE_WG, pos);

            for(int k = 0; k < 10; k++) {
                int height = 3 + rand.nextInt(4);

                int xSpread = 10;
                int zSpread = 10;
                placePos.setWithOffset(blockPos, rand.nextInt(xSpread + 10), 0, rand.nextInt(zSpread + 10));

                if (reader.getBlockState(placePos.above()) == Blocks.AIR.defaultBlockState() && reader.getBlockState(placePos) == JBlocks.VOLCANIC_SAND.defaultBlockState()) {
                    for (int i = 0; i < height; i++) {
                        setBlock(reader, placePos.move(Direction.UP), JBlocks.SCORCHED_CACTUS.defaultBlockState());
                    }
                }
            }
            return true;
        }
    }
}
