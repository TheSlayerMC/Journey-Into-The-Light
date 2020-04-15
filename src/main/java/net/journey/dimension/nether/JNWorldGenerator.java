package net.journey.dimension.nether;

import net.journey.dimension.nether.biomes.BiomeRegister;
import net.journey.dimension.nether.biomes.NetherBiome;
import net.journey.dimension.nether.biomes.structure.IStructureWorld;
import net.journey.dimension.nether.biomes.structure.StructureThornRoot;
import net.journey.dimension.nether.noise.Dither;
import net.journey.dimension.nether.noise.WorleyNoiseIDDistorted3D;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.Config;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JNWorldGenerator {

    /* Created by paulevs, from the Better Nether mod
     * Big thanks to him*/

    public static StructureThornRoot thornGen = new StructureThornRoot();

    public static IStructureWorld[] globalStructuresLand;
    public static IStructureWorld[] globalStructuresLava;
    public static IStructureWorld[] globalStructuresCave;

    public static boolean hasCleaningPass = true;
    public static boolean hasThornGen = true;
    public static boolean enablePlayerDamage;
    public static boolean enableMobDamage;
    private static WorleyNoiseIDDistorted3D noise3d;
    private static WorleyNoiseIDDistorted3D subbiomesNoise;
    private static Dither dither;
    private static double biomeSizeXZ;
    private static double biomeSizeY;
    private static double subBiomeSize;
    private static float plantDensity = 1;
    private static Random coordinateRandom;

    public static void init(long seed) {
        noise3d = new WorleyNoiseIDDistorted3D(seed, BiomeRegister.biomeCount);
        subbiomesNoise = new WorleyNoiseIDDistorted3D(~seed, 256);
        dither = new Dither(seed);
        coordinateRandom = new Random();
    }

    public static void generate(World world, Chunk chunk, Random random) {
        NetherBiome biome;
        int id;
        if (!world.isRemote) {
            int sx = chunk.x << 4;
            int sz = chunk.z << 4;

            // Structure Generator
            coordinateRandom.setSeed((long) chunk.x * 341873128712L + (long) chunk.z * 132897987541L);
            if ((chunk.x & 31) == 0 && (chunk.z & 31) == 0 && coordinateRandom.nextBoolean()) {
                BlockPos start = new BlockPos(8, 32, 8);
                if (world.getBlockState(start.down()).getMaterial() == Material.LAVA) {
                    boolean generate = true;
                    for (int y = 0; y < 20; y++) {
                        if (chunk.getBlockState(start.up(y)).getBlock() != Blocks.AIR) {
                            generate = false;
                            break;
                        }
                    }
                    if (generate) {
                    }
                }
            }
            if (coordinateRandom.nextInt(16) == 0) {
                BlockPos start = new BlockPos(coordinateRandom.nextInt(16), 32 + coordinateRandom.nextInt(120 - 32),
                        coordinateRandom.nextInt(16));
                while (chunk.getBlockState(start).getBlock() != Blocks.AIR && start.getY() > 32) {
                    start = start.down();
                }
                start = downRay(chunk, start);
                if (start != null) {
                    boolean terrain = true;
                    for (int y = 1; y < 8; y++) {
                        if (chunk.getBlockState(start.up(y)).getBlock() != Blocks.AIR) {
                            terrain = false;
                            break;
                        }
                    }
                    if (terrain) {
                        if (globalStructuresLava.length > 0
                                && chunk.getBlockState(start).getMaterial() == Material.LAVA)
                            ;
                            // globalStructuresLava[coordinateRandom.nextInt(globalStructuresLava.length)].generate(chunk.getWorld(),
                            // start.add(sx, 1, sz), coordinateRandom);
                        else if (globalStructuresLand.length > 0)
                            globalStructuresLand[coordinateRandom.nextInt(globalStructuresLand.length)]
                                    .generateSurface(chunk.getWorld(), start.add(sx, 1, sz), coordinateRandom);// .generate(chunk.getWorld(),
                        // start.add(sx,
                        // 1,
                        // sz),
                        // coordinateRandom);
                    } else if (globalStructuresCave.length > 0) {
                        globalStructuresCave[coordinateRandom.nextInt(globalStructuresCave.length)]
                                .generateSubterrain(chunk.getWorld(), start.add(sx, 0, sz), coordinateRandom);
                    }
                }
            }

            // Total Populator
            for (int x = 0; x < 16; x++) {
                int wx = sx | x;
                for (int z = 0; z < 16; z++) {
                    int wz = sz | z;
                    for (int y = 5; y < 126; y++) {
                        if (chunk.getBlockState(x, y, z).isFullBlock()) {
                            id = getBiome(wx, y, wz);
                            biome = BiomeRegister.getBiomeID(id);
                            if (isEdge(id, wx, y, wz, biome.getEdgeSize()))
                                biome = biome.getEdge();
                            else
                                biome = biome.getSubBiome(wx, y, wz);

                            // Ground Generation
                            if (chunk.getBlockState(x, y + 1, z).getBlock() == Blocks.AIR) {
                                biome.genSurfColumn(chunk, new BlockPos(x, y, z), random);
                                if (random.nextFloat() <= plantDensity)
                                    biome.genFloorObjects(chunk, new BlockPos(x, y, z), random);
                            }

                            // Ceiling Generation
                            else if (chunk.getBlockState(x, y - 1, z).getBlock() == Blocks.AIR) {
                                if (random.nextFloat() <= plantDensity)
                                    biome.genCeilObjects(chunk, new BlockPos(x, y, z), random);
                            }

                            // Wall Generation
                            else if (x > 1 && z > 1 && x < 14 && z < 14) {
                                BlockPos origin = new BlockPos(x, y, z);
                                boolean bNorth = chunk.getBlockState(x + 1, y, z).getBlock() == Blocks.AIR;
                                boolean bSouth = chunk.getBlockState(x - 1, y, z).getBlock() == Blocks.AIR;
                                boolean bEast = chunk.getBlockState(x, y, z + 1).getBlock() == Blocks.AIR;
                                boolean bWest = chunk.getBlockState(x, y, z - 1).getBlock() == Blocks.AIR;
                                if (bNorth || bSouth || bEast || bWest) {
                                    BlockPos pos = null;
                                    if (bNorth)
                                        pos = origin.north();
                                    else if (bSouth)
                                        pos = origin.south();
                                    else if (bEast)
                                        pos = origin.east();
                                    else
                                        pos = origin.west();
                                    boolean bDown = chunk.getBlockState(pos.up()).getBlock() == Blocks.AIR;
                                    boolean bUp = chunk.getBlockState(pos.down()).getBlock() == Blocks.AIR;
                                    if (bDown && bUp) {
                                        if (random.nextFloat() <= plantDensity)
                                            biome.genWallObjects(chunk, origin, pos, random);
                                        if (y < 50
                                                && chunk.getBlockState(x, y, z).getBlock() instanceof BlockNetherBrick
                                                && random.nextInt(16) == 0)
                                            ;
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    private static boolean isEdge(int centerID, int x, int y, int z, int distance) {
        return distance > 0 && (centerID != getBiome(x + distance, y, z) || centerID != getBiome(x - distance, y, z)
                || centerID != getBiome(x, y + distance, z) || centerID != getBiome(x, y - distance, z)
                || centerID != getBiome(x, y, z + distance) || centerID != getBiome(x, y, z - distance));
    }

    private static int getBiome(int x, int y, int z) {
        double px = (double) dither.ditherX(x, y, z) * biomeSizeXZ;
        double py = (double) dither.ditherY(x, y, z) * biomeSizeY;
        double pz = (double) dither.ditherZ(x, y, z) * biomeSizeXZ;
        return noise3d.GetValue(px, py, pz);
    }

    public static void smoothChunk(Chunk chunk) {
        if (hasCleaningPass) {
            List<BlockPos> pos = new ArrayList<BlockPos>();
            for (int y = 32; y < 110; y++) {
                for (int x = 1; x < 15; x++)
                    for (int z = 1; z < 15; z++)
                        if (canReplace(chunk, x, y, z)) {
                            if (isAir(chunk, x - 1, y, z) && isAir(chunk, x + 1, y, z))
                                pos.add(new BlockPos(x, y, z));
                            else if (isAir(chunk, x, y - 1, z) && isAir(chunk, x, y + 1, z))
                                pos.add(new BlockPos(x, y, z));
                            else if (isAir(chunk, x, y, z - 1) && isAir(chunk, x, y, z + 1))
                                pos.add(new BlockPos(x, y, z));
                            else if (isAir(chunk, x - 1, y - 1, z - 1) && isAir(chunk, x + 1, y + 1, z + 1))
                                pos.add(new BlockPos(x, y, z));
                            else if (isAir(chunk, x + 1, y - 1, z - 1) && isAir(chunk, x - 1, y + 1, z + 1))
                                pos.add(new BlockPos(x, y, z));
                            else if (isAir(chunk, x + 1, y - 1, z + 1) && isAir(chunk, x - 1, y + 1, z - 1))
                                pos.add(new BlockPos(x, y, z));
                            else if (isAir(chunk, x - 1, y - 1, z + 1) && isAir(chunk, x + 1, y + 1, z - 1))
                                pos.add(new BlockPos(x, y, z));
                        }
                for (int i = 0; i < 16; i++) {
                    if (canReplace(chunk, i, y, 0) && isAir(chunk, i, y - 1, 0) && isAir(chunk, i, y + 1, 0))
                        pos.add(new BlockPos(i, y, 0));
                    if (canReplace(chunk, i, y, 15) && isAir(chunk, i, y - 1, 15) && isAir(chunk, i, y + 1, 15))
                        pos.add(new BlockPos(i, y, 15));
                }
                for (int i = 1; i < 15; i++) {
                    if (canReplace(chunk, 0, y, i) && isAir(chunk, 0, y - 1, i) && isAir(chunk, 0, y + 1, i))
                        pos.add(new BlockPos(0, y, i));
                    if (canReplace(chunk, 15, y, i) && isAir(chunk, 15, y - 1, i) && isAir(chunk, 15, y + 1, i))
                        pos.add(new BlockPos(15, y, i));
                }
            }
            for (BlockPos p : pos) {
                chunk.setBlockState(p, Blocks.AIR.getDefaultState());
            }
        }
    }

    private static boolean isAir(Chunk chunk, int x, int y, int z) {
        return chunk.getBlockState(x, y, z).getBlock() == Blocks.AIR;
    }

    private static boolean canReplace(Chunk chunk, int x, int y, int z) {
        return !isAir(chunk, x, y, z) && (chunk.getBlockState(x, y, z).getBlock() instanceof BlockNetherrack
                || chunk.getBlockState(x, y, z).getBlock() instanceof BlockSoulSand
                || chunk.getBlockState(x, y, z).getBlock() instanceof BlockGravel);
    }

    private static void spawnOre(IBlockState state, Chunk chunk, int x, int y, int z, Random random) {
        for (int i = 0; i < 6 + random.nextInt(11); i++) {
            int px = x + random.nextInt(3);
            int py = y + random.nextInt(3);
            int pz = z + random.nextInt(3);
            if (chunk.getBlockState(px, py, pz).getBlock() == Blocks.NETHERRACK) {
                chunk.setBlockState(new BlockPos(px, py, pz), state);
            }
        }
    }

    public static void updateGenSettings() {
        biomeSizeXZ = 1.0 / (double) Config.getBiomeSizeXZ();
        biomeSizeY = 1.0 / (double) Config.getBiomeSizeY();
        subBiomeSize = biomeSizeXZ * 3;
        hasCleaningPass = Config.hasCleaningPass();
        hasThornGen = JourneyBlocks.hellThornBottom != Blocks.AIR && JourneyBlocks.hellThornRoot != Blocks.AIR;

        globalStructuresLand = new IStructureWorld[]{};

        globalStructuresLava = new IStructureWorld[]{};

        globalStructuresCave = new IStructureWorld[]{};
    }

    private static BlockPos downRay(Chunk chunk, BlockPos start) {
        int dist = 0;
        Block b;
        for (int j = start.getY(); j > 31; j--) {
            b = chunk.getBlockState(start.getX(), j, start.getZ()).getBlock();
            if (b != Blocks.AIR && (b instanceof BlockNetherrack || b instanceof BlockSoulSand
                    || chunk.getBlockState(start.getX(), j, start.getZ()).getMaterial() == Material.LAVA)) {
                return new BlockPos(start.getX(), j, start.getZ());
            }
        }
        return null;
    }

    public static void setDensity(float density) {
        plantDensity = density;
    }

    public static int getSubBiome(int x, int y, int z, int count) {
        double px = (double) dither.ditherX(x, y, z) * subBiomeSize;
        double py = (double) dither.ditherY(x, y, z) * subBiomeSize;
        double pz = (double) dither.ditherZ(x, y, z) * subBiomeSize;
        return subbiomesNoise.GetValue(px, py, pz) % count;
    }

    public static NetherBiome getBiome(BlockPos pos) {
        int id = getBiome(pos.getX(), pos.getY(), pos.getZ());
        NetherBiome biome = BiomeRegister.getBiomeID(id);
        if (isEdge(id, pos.getX(), pos.getY(), pos.getZ(), biome.getEdgeSize()))
            biome = biome.getEdge();
        else
            biome = biome.getSubBiome(pos.getX(), pos.getY(), pos.getZ());
        return biome;
    }
}
