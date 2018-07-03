package net.journey.dimension.overworld.gen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

public class WorldGenMerchant extends WorldGenerator {

	@Override
	public boolean generate(World w, Random r, BlockPos pos) {
		int x = pos.getX(), y = pos.getY() + 1, z = pos.getZ();
		WorldGenAPI.addRectangle(6, 5, 5, w, x, y, z, Blocks.PLANKS);
		WorldGenAPI.addRectangle(4, 4, 3, w, x + 1, y + 1, z + 1, Blocks.AIR);
		WorldGenAPI.addRectangle(4, 1, 3, w, x + 1, y + 1, z + 1, Blocks.BOOKSHELF);
		WorldGenAPI.addRectangle(4, 1, 1, w, x + 1, y + 1, z + 4, Blocks.PLANKS);
		WorldGenAPI.addRectangle(6, 1, 1, w, x, y + 3, z + 4, Blocks.OAK_FENCE);
		WorldGenAPI.addRectangle(6, 1, 1, w, x, y + 2, z + 4, Blocks.OAK_FENCE);
		WorldGenAPI.addRectangle(4, 1, 1, w, x + 1, y + 2, z + 4, Blocks.AIR);
		WorldGenAPI.addRectangle(6, 1, 2, w, x, y + 2, z + 3, Blocks.OAK_FENCE);
		WorldGenAPI.addRectangle(4, 1, 2, w, x + 1, y + 2, z + 3, Blocks.AIR);
		WorldGenAPI.addRectangle(6, 1, 1, w, x, y + 3, z + 2, Blocks.OAK_FENCE);
		WorldGenAPI.addRectangle(4, 1, 1, w, x + 1, y + 3, z + 2, Blocks.AIR);
		WorldGenAPI.addRectangle(6, 1, 4, w, x, y + 1, z + 5, Blocks.OAK_FENCE);
		WorldGenAPI.addRectangle(4, 1, 3, w, x + 1, y + 1, z + 5, Blocks.AIR);
		WorldGenAPI.addRectangle(6, 1, 1, w, x, y + 4, z + 6, Blocks.OAK_FENCE);
		WorldGenAPI.addRectangleWithMetadata(1, 2, 1, w, x, y + 5, z + 5, Blocks.CARPET, 0);
		WorldGenAPI.addRectangleWithMetadata(1, 2, 1, w, x + 2, y + 5, z + 5, Blocks.CARPET, 0);
		WorldGenAPI.addRectangleWithMetadata(1, 2, 1, w, x + 4, y + 5, z + 5, Blocks.CARPET, 0);
		WorldGenAPI.addRectangleWithMetadata(1, 2, 1, w, x + 1, y + 5, z + 5, Blocks.CARPET, 14);
		WorldGenAPI.addRectangleWithMetadata(1, 2, 1, w, x + 3, y + 5, z + 5, Blocks.CARPET, 14);
		WorldGenAPI.addRectangleWithMetadata(1, 2, 1, w, x + 5, y + 5, z + 5, Blocks.CARPET, 14);
		WorldGenAPI.addRectangleWithMetadata(1, 2, 1, w, x + 2, y + 1, z + 2, Blocks.CARPET, 0);
		WorldGenAPI.addRectangleWithMetadata(1, 2, 1, w, x + 4, y + 1, z + 2, Blocks.CARPET, 0);
		WorldGenAPI.addRectangleWithMetadata(1, 2, 1, w, x + 1, y + 1, z + 2, Blocks.CARPET, 14);
		WorldGenAPI.addRectangleWithMetadata(1, 2, 1, w, x + 3, y + 1, z + 2, Blocks.CARPET, 14);
		WorldGenAPI.addRectangleWithMetadata(1, 1, 1, w, x + 2, y + 2, z + 4, Blocks.CARPET, 0);
		WorldGenAPI.addRectangleWithMetadata(1, 1, 1, w, x + 4, y + 2, z + 4, Blocks.CARPET, 0);
		WorldGenAPI.addRectangleWithMetadata(1, 1, 1, w, x + 1, y + 2, z + 4, Blocks.CARPET, 14);
		WorldGenAPI.addRectangleWithMetadata(1, 1, 1, w, x + 3, y + 2, z + 4, Blocks.CARPET, 14);
		w.setBlockState(new BlockPos(x + 1, y + 3, z + 2), Blocks.TORCH.getStateFromMeta(3));
		w.setBlockState(new BlockPos(x + 4, y + 3, z + 2), Blocks.TORCH.getStateFromMeta(3));
		w.setBlockState(new BlockPos(x + 1, y + 1, z + 2), Blocks.CHEST.getStateFromMeta(3));
		w.setBlockState(new BlockPos(x + 4, y + 1, z + 2), Blocks.CHEST.getStateFromMeta(3));
		return true;
	}
}