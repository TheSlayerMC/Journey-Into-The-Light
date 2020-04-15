package net.journey.dimension;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeGenJourney extends Biome {

    private final BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
    protected WorldGenerator basicTree;
    protected WorldGenerator basicLargeTree;
    protected WorldGenerator bush;
    protected WorldGenerator bloom;
    protected WorldGenerator flower;

    protected Chunk chunk;

    public BiomeGenJourney(String name) {
        super(new BiomeProperties(name));
    }

    public BiomeGenJourney(BiomeProperties properties) {
        super(properties);
    }

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
        this.chunk = worldIn.getChunk(pos);

        generate(worldIn, rand, chunk.x, chunk.z, 4, 13, 80 + 13, bloom, basicLargeTree);

        generate(worldIn, rand, chunk.x, chunk.z, 10, 13, 80 + 13, bush, flower);

        generate(worldIn, rand, chunk.x, chunk.z, 100, 13, 80 + 13, bush);

        generate(worldIn, rand, chunk.x, chunk.z, 10, 55, 55 + 20, basicTree);

    }

    protected void generate(World world, Random random, int chunkX, int chunkZ, int tries, int minY, int maxY, WorldGenerator... generators) {
        if (generators != null && generators.length > 0) {
            int height = getHeightOrThrow(minY, maxY);
            for (int i = 0; i < tries; i++) {
                for (WorldGenerator generator : generators) {
                    if (generator != null) {
                        pos.setPos(chunkX * 16 + random.nextInt(16) + 8, minY + random.nextInt(height + 1), chunkZ * 16 + random.nextInt(16) + 8);
                        generator.generate(world, random, pos);
                    }
                }
            }
        }
    }

    private int getHeightOrThrow(int minY, int maxY) {
        int height = maxY - minY;
        if (height <= 0) {
            throw new IllegalArgumentException("Max Y equals or less min Y");
        }
        if (minY < 0 || maxY > 256)
            throw new IllegalArgumentException("Ore Generated Out of Bounds");

        return height;
    }

}