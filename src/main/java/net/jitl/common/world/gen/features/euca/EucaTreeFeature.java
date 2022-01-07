package net.jitl.common.world.gen.features.euca;

import com.mojang.serialization.Codec;
import net.jitl.common.world.gen.features.featureconfig.EucaTreeFeatureConfig;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class EucaTreeFeature extends Feature<EucaTreeFeatureConfig> {
    public EucaTreeFeature(Codec<EucaTreeFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<EucaTreeFeatureConfig> context) {
        BlockPos pos = context.origin();
        WorldGenLevel reader = context.level();
        Random random = context.random();

        int xPos = pos.getX() + random.nextInt(6) - random.nextInt(4);
        int zPos = pos.getZ() + random.nextInt(6) - random.nextInt(4);
        int yPos = reader.getHeight(Heightmap.Types.WORLD_SURFACE_WG, xPos, zPos) - 1;

        if (!context.config().spawnBlock.test(reader.getBlockState(pos.below()), random)) {
            return false;
        } else {
            int treeHeight = random.nextInt(context.config().minHeight) + random.nextInt(2) + context.config().maxHeight;
            BlockPos.MutableBlockPos stumpPos = pos.mutable();
            stumpPos.set(xPos, yPos, zPos);

            int stumpHeight = random.nextInt(2) + 2;

            for (int i = 0; i < stumpHeight; i++) {
                placeStumps(reader, stumpPos, random, context.config());
            }

            for (int i = 0; i < treeHeight; i++) {
                placeLog(reader, stumpPos, random, context.config());
            }

            BlockPos.MutableBlockPos leafPos = stumpPos.move(Direction.DOWN);
            createCrown(reader, leafPos, random, context.config());
            return true;
        }
    }

    private void placeLog(WorldGenLevel reader, BlockPos.MutableBlockPos pos, Random rand, EucaTreeFeatureConfig config) {
        setBlock(reader, pos.move(Direction.UP), config.logBlock.getState(rand, pos));
    }

    private void placeStumps(WorldGenLevel reader, BlockPos.MutableBlockPos logPos, Random rand, EucaTreeFeatureConfig config) {
        setBlock(reader, logPos.move(Direction.UP).offset(Direction.EAST.getNormal()), config.logBlock.getState(rand, logPos));
        setBlock(reader, logPos.offset(Direction.WEST.getNormal()), config.logBlock.getState(rand, logPos));
        setBlock(reader, logPos.offset(Direction.NORTH.getNormal()), config.logBlock.getState(rand, logPos));
        setBlock(reader, logPos.offset(Direction.SOUTH.getNormal()), config.logBlock.getState(rand, logPos));
    }

    private void createCrown(WorldGenLevel reader, BlockPos pos, Random rand, EucaTreeFeatureConfig config) {
        int size = config.leafSize;
        pos = pos.offset(Direction.UP.getNormal());
        for (byte x = 0; x <= size; x++) {
            for (byte y = 0; y <= size; y++) {
                for (byte z = 0; z <= size; z++) {

                    int distance;

                    if (x >= y & x >= z) {
                        distance = x + (Math.max(y, z) >> 1) + (Math.min(y, z) >> 1);
                    } else if (y >= x & y >= z) {
                        distance = y + (Math.max(x, z) >> 1) + (Math.min(x, z) >> 1);
                    } else {
                        distance = z + (Math.max(x, y) >> 1) + (Math.min(x, y) >> 1);
                    }

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

    private void placeLeaves(WorldGenLevel reader, BlockPos pos, Random rand, EucaTreeFeatureConfig config) {
        if(reader.getBlockState(pos).getBlock() == Blocks.AIR)
            setBlock(reader, pos, config.leafBlock.getState(rand, pos));
    }
}
