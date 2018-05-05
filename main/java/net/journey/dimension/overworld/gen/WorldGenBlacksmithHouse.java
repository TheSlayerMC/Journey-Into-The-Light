package net.journey.dimension.overworld.gen;

import java.util.Random;

import net.journey.entity.mob.overworld.npc.EntityBlacksmith;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

public class WorldGenBlacksmithHouse extends WorldGenerator {

	@Override
	public boolean generate(World w, Random r, BlockPos pos) {
		int x = pos.getX(), y = pos.getY() - 1, z = pos.getZ();
		WorldGenAPI.addRectangle(11, 11, 1, w, x - 1, y + 1, z - 1, Blocks.grass);
		WorldGenAPI.addRectangle(11, 11, 4, w, x - 1, y - 3, z - 1, Blocks.dirt);
		WorldGenAPI.addHollowCube(9, w, x, y, z, Blocks.cobblestone);
		WorldGenAPI.addRectangle(9, 9, 4, w, x, y + 6, z, Blocks.air);
		WorldGenAPI.addRectangle(9, 9, 1, w, x, y + 5, z, Blocks.cobblestone);
		WorldGenAPI.addRectangleWithMetadata(1, 7, 1, w, x, y + 3, z + 1, Blocks.glass, 2);
		WorldGenAPI.addRectangleWithMetadata(1, 3, 2, w, x, y + 3, z + 3, Blocks.glass, 2);
		WorldGenAPI.addRectangleWithMetadata(1, 7, 1, w, x + 8, y + 3, z + 1, Blocks.glass, 2);
		WorldGenAPI.addRectangleWithMetadata(1, 3, 2, w, x + 8, y + 3, z + 3, Blocks.glass, 2);
		WorldGenAPI.addRectangleWithMetadata(7, 1, 1, w, x + 1, y + 3, z, Blocks.glass, 2);
		WorldGenAPI.addRectangleWithMetadata(3, 1, 2, w, x + 3, y + 3, z, Blocks.glass, 2);
		WorldGenAPI.addRectangleWithMetadata(7, 1, 1, w, x + 1, y + 3, z + 8, Blocks.glass, 2);
		WorldGenAPI.addRectangleWithMetadata(3, 1, 2, w, x + 3, y + 3, z + 8, Blocks.glass, 2);
		ItemDoor.placeDoor(w, new BlockPos(x, y + 2, z + 4), EnumFacing.EAST, Blocks.oak_door);
		ItemDoor.placeDoor(w, new BlockPos(x + 8, y + 2, z + 4), EnumFacing.WEST, Blocks.oak_door);
		ItemDoor.placeDoor(w, new BlockPos(x + 4, y + 2, z), EnumFacing.SOUTH, Blocks.oak_door);
		ItemDoor.placeDoor(w, new BlockPos(x + 4, y + 2, z + 8), EnumFacing.NORTH, Blocks.oak_door);
		w.setBlockState(new BlockPos(x + 1, y + 4, z + 2), Blocks.torch.getStateFromMeta(1), 2);
		w.setBlockState(new BlockPos(x + 1, y + 4, z + 6), Blocks.torch.getStateFromMeta(1), 2);
		w.setBlockState(new BlockPos(x + 7, y + 4, z + 2), Blocks.torch.getStateFromMeta(2), 2);
		w.setBlockState(new BlockPos(x + 7, y + 4, z + 6), Blocks.torch.getStateFromMeta(2), 2);
		WorldGenAPI.addRectangle(2, 1, 1, w, x + 1, y + 2, z + 1, Blocks.oak_stairs);
		WorldGenAPI.addRectangle(2, 1, 1, w, x + 6, y + 2, z + 1, Blocks.oak_stairs);
		WorldGenAPI.addRectangleWithMetadata(2, 1, 1, w, x + 1, y + 2, z + 7, Blocks.oak_stairs, 2);
		WorldGenAPI.addRectangleWithMetadata(2, 1, 1, w, x + 6, y + 2, z + 7, Blocks.oak_stairs, 2);
		w.setBlockState(new BlockPos(x + 1, y + 2, z + 2), Blocks.oak_stairs.getStateFromMeta(1), 2);
		w.setBlockState(new BlockPos(x + 7, y + 2, z + 2), Blocks.oak_stairs.getStateFromMeta(0), 2);
		w.setBlockState(new BlockPos(x + 1, y + 2, z + 6), Blocks.oak_stairs.getStateFromMeta(1), 2);
		w.setBlockState(new BlockPos(x + 7, y + 2, z + 6), Blocks.oak_stairs.getStateFromMeta(0), 2);
		WorldGenAPI.addBlock(w, x + 2, y + 1, z + 2, Blocks.oak_fence);
		WorldGenAPI.addBlock(w, x + 6, y + 1, z + 6, Blocks.oak_fence);
		WorldGenAPI.addBlock(w, x + 6, y + 1, z + 2, Blocks.oak_fence);
		WorldGenAPI.addBlock(w, x + 2, y + 1, z + 6, Blocks.oak_fence);
		WorldGenAPI.addBlock(w, x + 2, y + 2, z + 2, Blocks.wooden_pressure_plate);
		WorldGenAPI.addBlock(w, x + 6, y + 2, z + 6, Blocks.wooden_pressure_plate);
		WorldGenAPI.addBlock(w, x + 6, y + 2, z + 2, Blocks.wooden_pressure_plate);
		WorldGenAPI.addBlock(w, x + 2, y + 2, z + 6, Blocks.wooden_pressure_plate);
		WorldGenAPI.addBlock(w, x, y + 5, z, Blocks.cobblestone);
		WorldGenAPI.addBlock(w, x + 2, y + 5, z, Blocks.cobblestone);
		WorldGenAPI.addBlock(w, x + 4, y + 5, z, Blocks.cobblestone);
		WorldGenAPI.addBlock(w, x + 6, y + 5, z, Blocks.cobblestone);
		WorldGenAPI.addBlock(w, x + 8, y + 5, z, Blocks.cobblestone);
		WorldGenAPI.addBlock(w, x, y + 5, z + 2, Blocks.cobblestone);
		WorldGenAPI.addBlock(w, x, y + 5, z + 4, Blocks.cobblestone);
		WorldGenAPI.addBlock(w, x, y + 5, z + 6, Blocks.cobblestone);
		WorldGenAPI.addBlock(w, x, y + 5, z + 8, Blocks.cobblestone);
		WorldGenAPI.addBlock(w, x + 2, y + 5, z + 8, Blocks.cobblestone);
		WorldGenAPI.addBlock(w, x + 4, y + 5, z + 8, Blocks.cobblestone);
		WorldGenAPI.addBlock(w, x + 6, y + 5, z + 8, Blocks.cobblestone);
		WorldGenAPI.addBlock(w, x + 8, y + 5, z + 8, Blocks.cobblestone);
		WorldGenAPI.addBlock(w, x + 8, y + 5, z + 2, Blocks.cobblestone);
		WorldGenAPI.addBlock(w, x + 8, y + 5, z + 4, Blocks.cobblestone);
		WorldGenAPI.addBlock(w, x + 8, y + 5, z + 6, Blocks.cobblestone);
		WorldGenAPI.addBlock(w, x + 8, y + 5, z + 8, Blocks.cobblestone);
		
		if(!w.isRemote) {
			EntityBlacksmith smith = new EntityBlacksmith(w);
			smith.setLocationAndAngles(x + 4, y + 2, z + 4, 0.0F, 0.0F);
			w.spawnEntityInWorld(smith);
		}
		return true;
	}
}