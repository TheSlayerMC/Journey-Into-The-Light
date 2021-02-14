package net.jitl.common.world.gen.features.euca;

import com.mojang.serialization.Codec;
import net.jitl.common.world.gen.features.featureconfig.EucaTreeFeatureConfig;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class EucaTreeFeature extends Feature<EucaTreeFeatureConfig> {
    public EucaTreeFeature(Codec<EucaTreeFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(ISeedReader reader, ChunkGenerator generator, Random random, BlockPos pos, EucaTreeFeatureConfig config) {
        if (!config.spawnBlock.test(reader.getBlockState(pos.below()), random)) {
            return false;
        } else {
            int treeHeight = random.nextInt(config.minHeight) + random.nextInt(2) + config.maxHeight;

            int xPos = pos.getX();
            int zPos = pos.getZ();
            int yPos = reader.getHeight(Heightmap.Type.WORLD_SURFACE_WG, xPos, zPos);
            BlockPos.Mutable stumpPos = pos.mutable();
            stumpPos.set(xPos, yPos, zPos);

            int stumpHeight = random.nextInt(2) + 2;

            for (int i = 0; i < stumpHeight; i++) {
                placeStumps(reader, stumpPos, random, config);
            }

            for (int i = 0; i < treeHeight; i++) {
                placeLog(reader, stumpPos, random, config);
            }

            BlockPos.Mutable leafPos = stumpPos.move(Direction.DOWN);

            for (int j5 = 0; j5 <= 10; ++j5) {
                for (int l5 = 0; l5 <= 10; ++l5) {
                    createCrown(reader, leafPos, random, config);
                }
            }
            System.out.println("X:" + xPos + ", Y:" + yPos + ", Z:" + zPos);
            return true;
        }
    }

    private void placeLog(ISeedReader reader, BlockPos.Mutable pos, Random rand, EucaTreeFeatureConfig config) {
        setBlock(reader, pos.move(Direction.UP), config.logBlock.getState(rand, pos));
    }

    private void placeStumps(ISeedReader reader, BlockPos.Mutable logPos, Random rand, EucaTreeFeatureConfig config) {
        setBlock(reader, logPos.move(Direction.UP).offset(Direction.EAST.getNormal()), config.logBlock.getState(rand, logPos));
        setBlock(reader, logPos.offset(Direction.WEST.getNormal()), config.logBlock.getState(rand, logPos));
        setBlock(reader, logPos.offset(Direction.NORTH.getNormal()), config.logBlock.getState(rand, logPos));
        setBlock(reader, logPos.offset(Direction.SOUTH.getNormal()), config.logBlock.getState(rand, logPos));
    }

    private void createCrown(ISeedReader reader, BlockPos pos, Random rand, EucaTreeFeatureConfig config) {
        int size = config.leafSize;
        pos = pos.offset(Direction.UP.getNormal());
        for (byte x = 0; x <= size; x++) {
            for (byte y = 0; y <= size; y++) {
                for (byte z = 0; z <= size; z++) {

                    int distance;

                    if (x >= y & x >= z) distance = x + (Math.max(y, z) >> 1) + (Math.min(y, z) >> 1);

                    else if (y >= x & y >= z) distance = y + (Math.max(x, z) >> 1) + (Math.min(x, z) >> 1);

                    else distance = z + (Math.max(x, y) >> 1) + (Math.min(x, y) >> 1);

                    if (distance <= size) {
                        placeLeaves(reader, pos.offset(+x, +y, +z), rand, config);
                        placeLeaves(reader, pos.offset(+x, +y, -z), rand, config);
                        placeLeaves(reader, pos.offset(-x, +y, +z), rand, config);
                        placeLeaves(reader, pos.offset(-x, +y, -z), rand, config);
                        placeLeaves(reader, pos.offset(+x, -y, +z), rand, config);
                        placeLeaves(reader, pos.offset(+x, -y, -z), rand, config);
                        placeLeaves(reader, pos.offset(-x, -y, +z), rand, config);
                        placeLeaves(reader, pos.offset(-x, -y, -z), rand, config);
                    }
                }
            }
        }
    }

    private void placeLeaves(ISeedReader reader, BlockPos pos, Random rand, EucaTreeFeatureConfig config) {
        setBlock(reader, pos, config.leafBlock.getState(rand, pos));
    }
}
