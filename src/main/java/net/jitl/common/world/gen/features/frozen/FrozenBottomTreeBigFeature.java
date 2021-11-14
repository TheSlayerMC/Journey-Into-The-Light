package net.jitl.common.world.gen.features.frozen;

import com.mojang.serialization.Codec;
import net.jitl.common.world.gen.features.featureconfig.FrozenBottomTreeBigConfig;
import net.jitl.init.JBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class FrozenBottomTreeBigFeature extends Feature<FrozenBottomTreeBigConfig> {
    
    public FrozenBottomTreeBigFeature(Codec<FrozenBottomTreeBigConfig> codec) {
        super(codec);
    }


    @Override
    public boolean place(ISeedReader reader, ChunkGenerator generator, Random random, BlockPos pos, FrozenBottomTreeBigConfig config) {
        int xPos = pos.getX() + random.nextInt(6) - random.nextInt(4);
        int zPos = pos.getZ() + random.nextInt(6) - random.nextInt(4);
        int yPos = reader.getHeight(Heightmap.Type.WORLD_SURFACE_WG, xPos, zPos) - 1;

        if (!config.spawnBlock.test(reader.getBlockState(pos.below()), random)) {
            return false;
        } else {
            //GEN CODE
            return true;
        }
    }

    public void addRectangle(int east, int south, int height, int x, int y, int z, Block b, ISeedReader reader) {
        for (int x1 = 0; x1 < east; x1++) {
            for (int z1 = 0; z1 < south; z1++) {
                for (int y1 = 0; y1 < height; y1++) {
                    setBlock(reader, new BlockPos(x + x1, y + y1, z + z1), b.defaultBlockState());
                }
            }
        }
    }

    public void placeTrunk(ISeedReader reader, BlockPos pos) {
        int xPos = pos.getX();
        int zPos = pos.getZ();
        int yPos = pos.getY();
    }

    public void placeLeaves(ISeedReader reader, BlockPos pos) {
        int xPos = pos.getX();
        int zPos = pos.getZ();
        int yPos = pos.getY();
    }
}
