package net.jitl.common.world.gen.features.euca;

import com.mojang.serialization.Codec;
import net.jitl.core.init.JBlocks;
import net.jitl.core.util.JRuleTests;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class EucaBotSpawner extends Feature<NoneFeatureConfiguration> {

    public EucaBotSpawner(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos pos = context.origin();
        WorldGenLevel reader = context.level();
        Random random = context.random();
        int minHeight = 18;
        int maxHeight = 7;
        Block spawnerBlock = JBlocks.GOLD_BOT_SPAWNER;
        int xPos = pos.getX();
        int zPos = pos.getZ();
        int yPos = reader.getHeight(Heightmap.Types.WORLD_SURFACE_WG, xPos, zPos);

        BlockPos placement = new BlockPos(xPos, yPos, zPos);
        if(reader.getBlockState(placement.below()) == JBlocks.GOLDITE_GRASS_BLOCK.defaultBlockState() ||
                reader.getBlockState(placement.below()) == JBlocks.EUCA_GOLD_GRASS_BLOCK.defaultBlockState()) {
            int height = random.nextInt(minHeight) + random.nextInt(maxHeight) + 3;
            for (int i = 0; i < height; i++) {
                placeShaft(reader, placement.above(i));
            }
            BlockPos spawnerPos = new BlockPos(xPos, yPos + height, zPos);
            setBlock(reader, spawnerPos, JBlocks.EUCA_BRICK.defaultBlockState());
            spawnerPos = spawnerPos.above(1);
            setBlock(reader, spawnerPos, spawnerBlock.defaultBlockState());
            spawnerPos = spawnerPos.above(1);
            setBlock(reader, spawnerPos, spawnerBlock.defaultBlockState());
            addRectangle(7, 3, 1, xPos - 3, yPos + height, zPos - 1, JBlocks.EUCA_TILE, reader);
            addRectangle(3, 7, 1, xPos - 1, yPos + height, zPos - 3, JBlocks.EUCA_TILE, reader);
            addRectangle(5, 5, 1, xPos - 2, yPos + height, zPos - 2, JBlocks.EUCA_TILE, reader);
            return true;
        } else {
            return false;
        }
    }

    public void addRectangle(int east, int south, int height, int x, int y, int z, Block b, WorldGenLevel reader) {
        for (int x1 = 0; x1 < east; x1++) {
            for (int z1 = 0; z1 < south; z1++) {
                for (int y1 = 0; y1 < height; y1++) {
                    setBlock(reader, new BlockPos(x + x1, y + y1, z + z1), b.defaultBlockState());
                }
            }
        }
    }

    public void placeShaft(WorldGenLevel reader, BlockPos pos) {
        int xPos = pos.getX();
        int zPos = pos.getZ();
        int yPos = pos.getY();
        setBlock(reader, new BlockPos(xPos + 1, yPos, zPos), JBlocks.EUCA_BRICK.defaultBlockState());
        setBlock(reader, new BlockPos(xPos - 1, yPos, zPos), JBlocks.EUCA_BRICK.defaultBlockState());
        setBlock(reader, new BlockPos(xPos, yPos, zPos + 1), JBlocks.EUCA_BRICK.defaultBlockState());
        setBlock(reader, new BlockPos(xPos, yPos, zPos - 1), JBlocks.EUCA_BRICK.defaultBlockState());
    }
}
