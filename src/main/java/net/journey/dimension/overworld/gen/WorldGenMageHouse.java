package net.journey.dimension.overworld.gen;

import net.journey.entity.mob.overworld.npc.EntityMage;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenMageHouse extends WorldGenerator {

    @Override
    public boolean generate(World w, Random r, BlockPos pos) {
        int x = pos.getX(), y = pos.getY() - 1, z = pos.getZ();
        WorldGenAPI.addRectangle(11, 11, 1, w, x - 1, y + 1, z - 1, Blocks.GRASS);
        WorldGenAPI.addRectangle(11, 11, 4, w, x - 1, y - 3, z - 1, Blocks.DIRT);
        WorldGenAPI.addHollowCube(9, w, x, y, z, JourneyBlocks.mageWall);
        WorldGenAPI.addRectangle(9, 9, 4, w, x, y + 6, z, Blocks.AIR);
        WorldGenAPI.addRectangle(9, 9, 1, w, x, y + 5, z, JourneyBlocks.mageWall);
        WorldGenAPI.addRectangleWithMetadata(1, 7, 1, w, x, y + 3, z + 1, Blocks.STAINED_GLASS, 2);
        WorldGenAPI.addRectangleWithMetadata(1, 3, 2, w, x, y + 3, z + 3, Blocks.STAINED_GLASS, 2);
        WorldGenAPI.addRectangleWithMetadata(1, 7, 1, w, x + 8, y + 3, z + 1, Blocks.STAINED_GLASS, 2);
        WorldGenAPI.addRectangleWithMetadata(1, 3, 2, w, x + 8, y + 3, z + 3, Blocks.STAINED_GLASS, 2);
        WorldGenAPI.addRectangleWithMetadata(7, 1, 1, w, x + 1, y + 3, z, Blocks.STAINED_GLASS, 2);
        WorldGenAPI.addRectangleWithMetadata(3, 1, 2, w, x + 3, y + 3, z, Blocks.STAINED_GLASS, 2);
        WorldGenAPI.addRectangleWithMetadata(7, 1, 1, w, x + 1, y + 3, z + 8, Blocks.STAINED_GLASS, 2);
        WorldGenAPI.addRectangleWithMetadata(3, 1, 2, w, x + 3, y + 3, z + 8, Blocks.STAINED_GLASS, 2);
        ItemDoor.placeDoor(w, new BlockPos(x, y + 2, z + 4), EnumFacing.EAST, Blocks.OAK_DOOR, true);
        ItemDoor.placeDoor(w, new BlockPos(x + 8, y + 2, z + 4), EnumFacing.WEST, Blocks.OAK_DOOR, true);
        ItemDoor.placeDoor(w, new BlockPos(x + 4, y + 2, z), EnumFacing.SOUTH, Blocks.OAK_DOOR, true);
        ItemDoor.placeDoor(w, new BlockPos(x + 4, y + 2, z + 8), EnumFacing.NORTH, Blocks.OAK_DOOR, true);
        setBlockAndNotifyAdequately(w, new BlockPos(x + 1, y + 4, z + 2), Blocks.TORCH.getStateFromMeta(1));
        setBlockAndNotifyAdequately(w, new BlockPos(x + 1, y + 4, z + 6), Blocks.TORCH.getStateFromMeta(1));
        setBlockAndNotifyAdequately(w, new BlockPos(x + 7, y + 4, z + 2), Blocks.TORCH.getStateFromMeta(2));
        setBlockAndNotifyAdequately(w, new BlockPos(x + 7, y + 4, z + 6), Blocks.TORCH.getStateFromMeta(2));
        WorldGenAPI.addRectangle(2, 1, 1, w, x + 1, y + 2, z + 1, Blocks.OAK_STAIRS);
        WorldGenAPI.addRectangle(2, 1, 1, w, x + 6, y + 2, z + 1, Blocks.OAK_STAIRS);
        WorldGenAPI.addRectangleWithMetadata(2, 1, 1, w, x + 1, y + 2, z + 7, Blocks.OAK_STAIRS, 2);
        WorldGenAPI.addRectangleWithMetadata(2, 1, 1, w, x + 6, y + 2, z + 7, Blocks.OAK_STAIRS, 2);
        setBlockAndNotifyAdequately(w, new BlockPos(x + 1, y + 2, z + 2), Blocks.OAK_STAIRS.getStateFromMeta(1));
        setBlockAndNotifyAdequately(w, new BlockPos(x + 7, y + 2, z + 2), Blocks.OAK_STAIRS.getStateFromMeta(0));
        setBlockAndNotifyAdequately(w, new BlockPos(x + 1, y + 2, z + 6), Blocks.OAK_STAIRS.getStateFromMeta(1));
        setBlockAndNotifyAdequately(w, new BlockPos(x + 7, y + 2, z + 6), Blocks.OAK_STAIRS.getStateFromMeta(0));
        WorldGenAPI.addBlock(w, x + 2, y + 1, z + 2, Blocks.OAK_FENCE);
        WorldGenAPI.addBlock(w, x + 6, y + 1, z + 6, Blocks.OAK_FENCE);
        WorldGenAPI.addBlock(w, x + 6, y + 1, z + 2, Blocks.OAK_FENCE);
        WorldGenAPI.addBlock(w, x + 2, y + 1, z + 6, Blocks.OAK_FENCE);
        WorldGenAPI.addBlock(w, x + 2, y + 2, z + 2, Blocks.WOODEN_PRESSURE_PLATE);
        WorldGenAPI.addBlock(w, x + 6, y + 2, z + 6, Blocks.WOODEN_PRESSURE_PLATE);
        WorldGenAPI.addBlock(w, x + 6, y + 2, z + 2, Blocks.WOODEN_PRESSURE_PLATE);
        WorldGenAPI.addBlock(w, x + 2, y + 2, z + 6, Blocks.WOODEN_PRESSURE_PLATE);
        WorldGenAPI.addBlock(w, x, y + 5, z, JourneyBlocks.mageWall);
        WorldGenAPI.addBlock(w, x + 2, y + 5, z, JourneyBlocks.mageWall);
        WorldGenAPI.addBlock(w, x + 4, y + 5, z, JourneyBlocks.mageWall);
        WorldGenAPI.addBlock(w, x + 6, y + 5, z, JourneyBlocks.mageWall);
        WorldGenAPI.addBlock(w, x + 8, y + 5, z, JourneyBlocks.mageWall);
        WorldGenAPI.addBlock(w, x, y + 5, z + 2, JourneyBlocks.mageWall);
        WorldGenAPI.addBlock(w, x, y + 5, z + 4, JourneyBlocks.mageWall);
        WorldGenAPI.addBlock(w, x, y + 5, z + 6, JourneyBlocks.mageWall);
        WorldGenAPI.addBlock(w, x, y + 5, z + 8, JourneyBlocks.mageWall);
        WorldGenAPI.addBlock(w, x + 2, y + 5, z + 8, JourneyBlocks.mageWall);
        WorldGenAPI.addBlock(w, x + 4, y + 5, z + 8, JourneyBlocks.mageWall);
        WorldGenAPI.addBlock(w, x + 6, y + 5, z + 8, JourneyBlocks.mageWall);
        WorldGenAPI.addBlock(w, x + 8, y + 5, z + 8, JourneyBlocks.mageWall);
        WorldGenAPI.addBlock(w, x + 8, y + 5, z + 2, JourneyBlocks.mageWall);
        WorldGenAPI.addBlock(w, x + 8, y + 5, z + 4, JourneyBlocks.mageWall);
        WorldGenAPI.addBlock(w, x + 8, y + 5, z + 6, JourneyBlocks.mageWall);
        WorldGenAPI.addBlock(w, x + 8, y + 5, z + 8, JourneyBlocks.mageWall);

        if (!w.isRemote) {
            EntityMage mage = new EntityMage(w);
            mage.setLocationAndAngles(x + 4, y + 2, z + 4, 0.0F, 0.0F);
            w.spawnEntity(mage);
        }
        return true;
    }
}