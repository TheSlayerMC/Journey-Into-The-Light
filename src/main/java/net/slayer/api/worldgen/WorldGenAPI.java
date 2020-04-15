package net.slayer.api.worldgen;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class WorldGenAPI {

    private static Random r = new Random();

    public static boolean isValidLocationToSpawn(int x, int y, int z, World w, Block b) {
        for (int i = 0; i < x; i++) {
            for (int l = 0; l < z; l++) {
                if (w.getBlockState(new BlockPos(x + i, y, z + l)) != b.getDefaultState()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isBlockTop(int x, int y, int z, Block grass, World w) {
        if (w.getBlockState(new BlockPos(x, y, z)).getBlock() != grass)
            return false;

        AtomicBoolean result = new AtomicBoolean(true);

        BlockPos.getAllInBoxMutable(x, y + 1, z, x, y + 5, z).forEach(pos -> {
            if (!w.isAirBlock(pos)) {
                result.set(false);
                return;
            }
        });

        return result.get();
    }

    public static Block getEucaLeaves() {
        int i = r.nextInt(2);
        return i == 0 ? JourneyBlocks.eucaSilverLeaves : JourneyBlocks.eucaGoldLeaves;
    }

    public static Block getEucaLog() {
        return JourneyBlocks.eucaGoldLog;
    }

    public static Block getCorbaLog() {
        return JourneyBlocks.corbaLog;
    }

    public static Block getTerranianLog() {
        return JourneyBlocks.terranianLog;
    }

    public static Block getTerraniaLeaves() {
        return JourneyBlocks.terraniaLeaves;
    }

    public static Block getCorbaLeaves() {
        return JourneyBlocks.corbaLeaves;
    }

    public static Block getTerraniaVine() {
        return JourneyBlocks.terraniaVine;
    }

    public static boolean isAirBlocks(World w, int size, int x, int y, int z) {
        boolean is = false;
        for (int x1 = 0; x1 < size; x1++) {
            for (int y1 = 0; y1 < size; y1++) {
                for (int z1 = 0; z1 < size; z1++) {
                    if (w.getBlockState(new BlockPos(x + x1, y + y1, z + z1)) == Blocks.AIR.getDefaultState()) {
                        is = true;
                        break;
                    }
                }
            }
        }
        return is;
    }

    public static void addSpawner(World w, int x, int y, int z, String mobName) {
        w.setBlockState(new BlockPos(x, y, z), Blocks.MOB_SPAWNER.getDefaultState(), 2);
        TileEntityMobSpawner spawner = (TileEntityMobSpawner) w.getTileEntity(new BlockPos(x, y, z));
        spawner.getSpawnerBaseLogic().setEntityId(new ResourceLocation(mobName));
    }

    public static void addCube(int size, World w, int x, int y, int z, Block b) {
        for (int x1 = 0; x1 < size; x1++) {
            for (int z1 = 0; z1 < size; z1++) {
                for (int y1 = 0; y1 < size; y1++) {
                    w.setBlockState(new BlockPos(x + x1, y + y1, z + z1), b.getDefaultState());
                }
            }
        }
    }

    public static void addMetadataCube(int size, World w, int x, int y, int z, Block b, int metadata) {
        for (int x1 = 0; x1 < size; x1++) {
            for (int z1 = 0; z1 < size; z1++) {
                for (int y1 = 0; y1 < size; y1++) {
                    w.setBlockState(new BlockPos(x + x1, y + y1, z + z1), b.getStateFromMeta(metadata), 2);
                }
            }
        }
    }

    public static void addBlock(World w, int x, int y, int z, Block b) {
        addCube(1, w, x, y + 1, z, b);
    }

    public static void addHollowCube(int size, World w, int x, int y, int z, Block b) {
        for (int x1 = 0; x1 < size; x1++) {
            for (int z1 = 0; z1 < size; z1++) {
                for (int y1 = 0; y1 < size; y1++) {
                    w.setBlockState(new BlockPos(x + x1, y + y1 + 1, z + z1), b.getDefaultState());
                }
            }
        }

        for (int x1 = 0; x1 < size - 2; x1++) {
            for (int z1 = 0; z1 < size - 2; z1++) {
                for (int y1 = 0; y1 < size - 2; y1++) {
                    w.setBlockState(new BlockPos(x + x1 + 1, y + y1 + 2, z + z1 + 1), Blocks.AIR.getDefaultState());
                }
            }
        }
    }

    public static void addRectangle(int east, int south, int height, World w, int x, int y, int z, Block b) {
        for (int x1 = 0; x1 < east; x1++) {
            for (int z1 = 0; z1 < south; z1++) {
                for (int y1 = 0; y1 < height; y1++) {
                    w.setBlockState(new BlockPos(x + x1, y + y1, z + z1), b.getDefaultState());
                }
            }
        }
    }

    public static void addCornerlessRectangle(int east, int south, int height, World w, int x, int y, int z, Block b) {
        addRectangle(east, south, height, w, x, y, z, b);
        addRectangle(1, 1, height, w, x, y, z, Blocks.AIR);
        addRectangle(1, 1, height, w, x + east - 1, y, z, Blocks.AIR);
        addRectangle(1, 1, height, w, x, y, z + south - 1, Blocks.AIR);
        addRectangle(1, 1, height, w, x + east - 1, y, z + south - 1, Blocks.AIR);
    }

    public static void addRectangleWithMetadata(int east, int south, int height, World w, int x, int y, int z, Block b, int meta) {
        for (int x1 = 0; x1 < east; x1++) {
            for (int z1 = 0; z1 < south; z1++) {
                for (int y1 = 0; y1 < height; y1++) {
                    w.setBlockState(new BlockPos(x + x1, y + y1, z + z1), b.getStateFromMeta(meta), 2);
                }
            }
        }
    }

    public static void addCornerlessRectangleWithMetadata(int east, int south, int height, World w, int x, int y, int z, Block b, int meta) {
        addRectangleWithMetadata(east, south, height, w, x, y, z, b, meta);
        addRectangleWithMetadata(1, 1, height, w, x, y, z, Blocks.AIR, meta);
        addRectangleWithMetadata(1, 1, height, w, x + east - 1, y, z, Blocks.AIR, meta);
        addRectangleWithMetadata(1, 1, height, w, x, y, z + south - 1, Blocks.AIR, meta);
        addRectangleWithMetadata(1, 1, height, w, x + east - 1, y, z + south - 1, Blocks.AIR, meta);
    }

    public static void placeChestWithContents(World w, int x, int y, int z, int meta, boolean trapped, ItemStack... is) {
        Random r = new Random();
        if (trapped) w.setBlockState(new BlockPos(x, y, z), Blocks.TRAPPED_CHEST.getStateFromMeta(meta), 2);
        else w.setBlockState(new BlockPos(x, y, z), Blocks.CHEST.getStateFromMeta(meta), 2);
        TileEntityChest chest = (TileEntityChest) w.getTileEntity(new BlockPos(x, y, z));
        if (!w.isRemote && chest != null && is != null) {
            for (int i = 0; i < r.nextInt(27); i++) {
                ItemStack it = is[r.nextInt(is.length)];
                if (r.nextInt(2) == 0) chest.setInventorySlotContents(i, it);
            }
        }
    }

    public static void placeModdedChestWithContents(World w, int x, int y, int z, int meta, int amountOfItems, Block c, ItemStack... is) {
        Random r = new Random();
        w.setBlockState(new BlockPos(x, y, z), c.getStateFromMeta(meta), 2);
        TileEntityChest chest = (TileEntityChest) w.getTileEntity(new BlockPos(x, y, z));
        if (!w.isRemote && chest != null && is != null) {
            for (int i = 0; i < chest.getSizeInventory(); i++) {
                ItemStack it = is[r.nextInt(is.length)];
                chest.setInventorySlotContents(chest.getSizeInventory(), it);
            }
        }
    }

    public static void addHollowRectangle(int east, int south, int height, World w, int x, int y, int z, Block b) {
        for (int x1 = 0; x1 < east; x1++) {
            for (int z1 = 0; z1 < south; z1++) {
                for (int y1 = 0; y1 < height; y1++) {
                    w.setBlockState(new BlockPos(x + x1, y + y1, z + z1), b.getDefaultState());
                }
            }
        }

        for (int x1 = 0; x1 < east; x1++) {
            for (int z1 = 0; z1 < south; z1++) {
                for (int y1 = 0; y1 < height - 2; y1++) {
                    w.setBlockState(new BlockPos(x + x1 + 1, y + y1 + 1, z + z1 + 1), Blocks.AIR.getDefaultState());
                }
            }
        }
    }

    public static void addFilledHollowRectangle(int east, int south, int height, World w, int x, int y, int z, Block b, Block fill) {
        for (int x1 = 0; x1 < east; x1++) {
            for (int z1 = 0; z1 < south; z1++) {
                for (int y1 = 0; y1 < height; y1++) {
                    w.setBlockState(new BlockPos(x + x1, y + y1, z + z1), b.getDefaultState());
                }
            }
        }

        for (int x1 = 0; x1 < east; x1++) {
            for (int z1 = 0; z1 < south; z1++) {
                for (int y1 = 0; y1 < height - 2; y1++) {
                    w.setBlockState(new BlockPos(x + x1 + 1, y + y1 + 1, z + z1 + 1), fill.getDefaultState());
                }
            }
        }
    }

    public static void addSphere(World w, int size, int x, int y, int z, Block b) {
        int XLength = x - size;
        int XHeight = x + size;
        int ZLength = z - size;
        int ZHeight = z + size;
        double realSize = size / 2;
        double sizeOfSphere = realSize * realSize;
        for (int i = XLength; i < XHeight; i++) {
            for (int j = y - size; j < y + size; j++) {
                for (int k = ZLength; k < ZHeight; k++) {
                    double dx = i - x;
                    double dy = j - y;
                    double dz = k - z;
                    if (dx * dx * 0.7 + dy * dy * 0.7 + dz * dz * 0.7 < sizeOfSphere) {
                        w.setBlockState(new BlockPos(i, j + size + 3, k), b.getDefaultState());
                    }
                }
            }
        }
    }

    public static void addSphere(World w, int size, int x, int y, int z, Block bottom, Block top) {
        int XLength = x - size;
        int XHeight = x + size;
        int ZLength = z - size;
        int ZHeight = z + size;
        double realSize = size / 2;
        double sizeOfSphere = realSize * realSize;
        for (int i = XLength; i < XHeight; i++) {
            for (int j = y - size; j < y + size; j++) {
                for (int k = ZLength; k < ZHeight; k++) {
                    double dx = i - x;
                    double dy = j - y;
                    double dz = k - z;
                    if (dx * dx * 0.7 + dy * dy * 0.8 + dz * dz * 0.7 < sizeOfSphere) {
                        w.setBlockState(new BlockPos(i, j + size + 3, k), bottom.getDefaultState());
                        w.setBlockState(new BlockPos(i, j + size + 4, k), top.getDefaultState());
                    }
                }
            }
        }
    }

    public static void addWorldSphere(World w, int size, int x, int y, int z, Block stone, Block dirt, Block grass) {
        int XLength = x - size;
        int XHeight = x + size;
        int ZLength = z - size;
        int ZHeight = z + size;
        double realSize = size / 2;
        double sizeOfSphere = realSize * realSize;
        for (int i = XLength; i < XHeight; i++) {
            for (int j = y - size; j < y + size; j++) {
                for (int k = ZLength; k < ZHeight; k++) {
                    double dx = i - x;
                    double dy = j - y;
                    double dz = k - z;
                    if (dx * dx * 0.7 + dy * dy * 0.9 + dz * dz * 0.7 < sizeOfSphere) {
                        w.setBlockState(new BlockPos(i, j + size + 2, k), stone.getDefaultState());
                        w.setBlockState(new BlockPos(i, j + size + 3, k), dirt.getDefaultState());
                        w.setBlockState(new BlockPos(i, j + size + 4, k), grass.getDefaultState());
                    }
                }
            }
        }
    }

    public static void addOreWorldSphere(World w, int size, int x, int y, int z, Block stone, Block dirt, Block grass, int chance, Block... ores) {
        ArrayList<Block> block = new ArrayList<Block>();
        for (Block b : ores) block.add(b);
        int XLength = x - size;
        int XHeight = x + size;
        int ZLength = z - size;
        int ZHeight = z + size;
        double realSize = size / 2;
        double sizeOfSphere = realSize * realSize;
        for (int i = XLength; i < XHeight; i++) {
            for (int j = y - size; j < y + size; j++) {
                for (int k = ZLength; k < ZHeight; k++) {
                    double dx = i - x;
                    double dy = j - y;
                    double dz = k - z;
                    if (dx * dx * 0.7 + dy * dy * 0.9 + dz * dz * 0.7 < sizeOfSphere) {
                        w.setBlockState(new BlockPos(i, j + size + 2, k), stone.getDefaultState());
                        w.setBlockState(new BlockPos(i, j + size + 3, k), dirt.getDefaultState());
                        w.setBlockState(new BlockPos(i, j + size + 4, k), grass.getDefaultState());
                    }
                    if (w.getBlockState(new BlockPos(i, j, k)) == stone) {
                        if (r.nextInt(chance) == 0 && block != null)
                            w.setBlockState(new BlockPos(i, j, k), block.get(r.nextInt(block.size())).getDefaultState());
                    }
                }
            }
        }
    }

    public static void addCone(World w, int height, Random r, int x, int y, int z, Block b) {
        int height1 = r.nextInt(4) + height;
        for (int i = 0; i < height1; i++) placeFlatCircle(w, x, y + i, z, height1 - i, b);
    }

    public static void placeFlatCircle(World par1World, int x, int y, int z, int radius, Block block) {
        for (float i = 0; i < radius; i += 0.5) {
            for (float j = 0; j < 2 * Math.PI * i; j += 0.5)
                par1World.setBlockState(new BlockPos((int) Math.floor(x + Math.sin(j) * i), y, (int) Math.floor(z + Math.cos(j) * i)), block.getDefaultState());
        }
    }

    public static void placeCircle(World world, Block block, int x, int y, int z, int radius) {
        for (float i = 0; i < radius; i += 0.5) {
            for (float j = 0; j < 2 * Math.PI * i; j += 0.5) {
                world.setBlockState(new BlockPos((int) Math.floor(x + Math.sin(j) * i), y, (int) Math.floor(z + Math.cos(j) * i)), block.getDefaultState());
                if (r.nextInt(6) == 0)
                    world.setBlockState(new BlockPos((int) Math.floor(x + Math.sin(j) * i) + r.nextInt(2) - r.nextInt(2), y + 1, (int) Math.floor(z + Math.cos(j) * i) + r.nextInt(2) - r.nextInt(2)), block.getDefaultState());
                if (r.nextInt(6) == 0)
                    world.setBlockState(new BlockPos((int) Math.floor(x + Math.sin(j) * i) + r.nextInt(2) - r.nextInt(2), y - 1, (int) Math.floor(z + Math.cos(j) * i) + r.nextInt(2) - r.nextInt(2)), block.getDefaultState());
            }
        }
    }

    /**
     * Check if have square of blocks
     *
     * @param w      - world
     * @param start  - start position
     * @param radius - radius of blocks
     * @param block  - searching block
     */
    public static boolean checkRadius(World w, BlockPos start, int radius, Block block) {
        AtomicBoolean result = new AtomicBoolean(true);

        BlockPos.getAllInBoxMutable(start.add(radius / -2, 0, radius / -2), start.add(radius / 2, 0, radius / 2))
                .forEach(mutableBlockPos -> {
                    IBlockState state = w.getBlockState(mutableBlockPos);
                    if (state.getBlock() != block) {
                        result.set(false);
                        return;
                    }
                });

        return result.get();
    }

    /**
     * Creates random destributed block pos in current chunk. Uses offset to prevent overpopulation
     *
     * @param x    - x coords
     * @param maxY - max y cord
     * @param z    - z cord
     * @param r    - random
     * @return
     */
    public static BlockPos createRandom(int x, int maxY, int z, Random r) {
        return createRandom(x, 0, maxY, z, r, 16);
    }

    /**
     * Creates random destributed block pos in current chunk. Uses offset to prevent overpopulation
     *
     * @param x      - x cord
     * @param minY   - min Y (inclusive)
     * @param maxY   - max Y (inclusive)
     * @param z      - z cord
     * @param r      - random
     * @param offset - vanilla offset
     */
    public static BlockPos createRandom(int x, int minY, int maxY, int z, Random r, int offset) {
        return new BlockPos(
                x + r.nextInt(offset) + 8,
                r.nextInt(maxY - minY) + minY,
                z + +r.nextInt(offset) + 8);
    }
}