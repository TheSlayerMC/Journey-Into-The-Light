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

public class ScorchedStalagmiteFeature extends Feature<NoFeatureConfig> {
    public ScorchedStalagmiteFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        if (reader.getBlockState(pos.below()) != JBlocks.SCORCHED_RUBBLE.defaultBlockState()) {
            return false;
        } else {


            BlockPos.Mutable placePos = pos.mutable();

            int xPos = pos.getX();
            int zPos = pos.getZ();
            int yPos = reader.getHeight(Heightmap.Type.WORLD_SURFACE_WG, xPos, zPos);

            placePos.set(xPos, yPos - 1, zPos);

            BlockPos blockPos = reader.getHeightmapPos(Heightmap.Type.WORLD_SURFACE_WG, pos);

            for(int k = 0; k < 30; k++) {
                int bottomHeight = 1 + rand.nextInt(4);
                int largeHeight = 1 + rand.nextInt(5);
                int medHeight = 1 + rand.nextInt(6);
                int smallHeight = 1 + rand.nextInt(7);
                int tinyHeight = 1 + rand.nextInt(8);
                int xSpread = 10;
                int zSpread = 10;
                placePos.setWithOffset(blockPos, rand.nextInt(xSpread + 10), 0, rand.nextInt(zSpread + 10));

                if (reader.getBlockState(placePos.above()) == Blocks.AIR.defaultBlockState() && reader.getBlockState(placePos) == JBlocks.SCORCHED_RUBBLE.defaultBlockState()) {
                    for (int i = 0; i < bottomHeight; i++) {
                        setBlock(reader, placePos.move(Direction.UP), JBlocks.SCORCHED_RUBBLE.defaultBlockState());
                    }
                    for (int i = 0; i < largeHeight; i++) {
                        setBlock(reader, placePos.move(Direction.UP), JBlocks.SCORCHED_STALAGMITE_LARGE.defaultBlockState());
                    }
                    for (int i = 0; i < medHeight; i++) {
                        setBlock(reader, placePos.move(Direction.UP), JBlocks.SCORCHED_STALAGMITE_MED.defaultBlockState());
                    }
                    for (int i = 0; i < smallHeight; i++) {
                        setBlock(reader, placePos.move(Direction.UP), JBlocks.SCORCHED_STALAGMITE_SMALL.defaultBlockState());
                    }
                    for (int i = 0; i < tinyHeight; i++) {
                        setBlock(reader, placePos.move(Direction.UP), JBlocks.SCORCHED_STALAGMITE_TINY.defaultBlockState());
                    }
                }
            }
            return true;
        }
    }
}
