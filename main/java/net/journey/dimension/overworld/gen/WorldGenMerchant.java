package net.journey.dimension.overworld.gen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

public class WorldGenMerchant extends WorldGenerator {

	@Override
	public boolean generate(World w, Random r, BlockPos pos) {
		int x = pos.getX(), y = pos.getY() + 1, z = pos.getZ();
		WorldGenAPI.addRectangle(6, 5, 5, w, x, y, z, Blocks.planks);
		WorldGenAPI.addRectangle(4, 4, 3, w, x + 1, y + 1, z + 1, Blocks.air);
		WorldGenAPI.addRectangle(4, 1, 3, w, x + 1, y + 1, z + 1, Blocks.bookshelf);
		WorldGenAPI.addRectangle(4, 1, 1, w, x + 1, y + 1, z + 4, Blocks.planks);
		WorldGenAPI.addRectangle(6, 1, 1, w, x, y + 3, z + 4, Blocks.oak_fence);
		WorldGenAPI.addRectangle(6, 1, 1, w, x, y + 2, z + 4, Blocks.oak_fence);
		WorldGenAPI.addRectangle(4, 1, 1, w, x + 1, y + 2, z + 4, Blocks.air);
		WorldGenAPI.addRectangle(6, 1, 2, w, x, y + 2, z + 3, Blocks.oak_fence);
		WorldGenAPI.addRectangle(4, 1, 2, w, x + 1, y + 2, z + 3, Blocks.air);
		WorldGenAPI.addRectangle(6, 1, 1, w, x, y + 3, z + 2, Blocks.oak_fence);
		WorldGenAPI.addRectangle(4, 1, 1, w, x + 1, y + 3, z + 2, Blocks.air);
		WorldGenAPI.addRectangle(6, 1, 4, w, x, y + 1, z + 5, Blocks.oak_fence);
		WorldGenAPI.addRectangle(4, 1, 3, w, x + 1, y + 1, z + 5, Blocks.air);
		WorldGenAPI.addRectangle(6, 1, 1, w, x, y + 4, z + 6, Blocks.oak_fence);
		WorldGenAPI.addRectangleWithMetadata(1, 2, 1, w, x, y + 5, z + 5, Blocks.carpet, 0);
		WorldGenAPI.addRectangleWithMetadata(1, 2, 1, w, x + 2, y + 5, z + 5, Blocks.carpet, 0);
		WorldGenAPI.addRectangleWithMetadata(1, 2, 1, w, x + 4, y + 5, z + 5, Blocks.carpet, 0);
		WorldGenAPI.addRectangleWithMetadata(1, 2, 1, w, x + 1, y + 5, z + 5, Blocks.carpet, 14);
		WorldGenAPI.addRectangleWithMetadata(1, 2, 1, w, x + 3, y + 5, z + 5, Blocks.carpet, 14);
		WorldGenAPI.addRectangleWithMetadata(1, 2, 1, w, x + 5, y + 5, z + 5, Blocks.carpet, 14);
		WorldGenAPI.addRectangleWithMetadata(1, 2, 1, w, x + 2, y + 1, z + 2, Blocks.carpet, 0);
		WorldGenAPI.addRectangleWithMetadata(1, 2, 1, w, x + 4, y + 1, z + 2, Blocks.carpet, 0);
		WorldGenAPI.addRectangleWithMetadata(1, 2, 1, w, x + 1, y + 1, z + 2, Blocks.carpet, 14);
		WorldGenAPI.addRectangleWithMetadata(1, 2, 1, w, x + 3, y + 1, z + 2, Blocks.carpet, 14);
		WorldGenAPI.addRectangleWithMetadata(1, 1, 1, w, x + 2, y + 2, z + 4, Blocks.carpet, 0);
		WorldGenAPI.addRectangleWithMetadata(1, 1, 1, w, x + 4, y + 2, z + 4, Blocks.carpet, 0);
		WorldGenAPI.addRectangleWithMetadata(1, 1, 1, w, x + 1, y + 2, z + 4, Blocks.carpet, 14);
		WorldGenAPI.addRectangleWithMetadata(1, 1, 1, w, x + 3, y + 2, z + 4, Blocks.carpet, 14);
		w.setBlockState(new BlockPos(x + 1, y + 3, z + 2), Blocks.torch.getStateFromMeta(3));
		w.setBlockState(new BlockPos(x + 4, y + 3, z + 2), Blocks.torch.getStateFromMeta(3));
		w.setBlockState(new BlockPos(x + 1, y + 1, z + 2), Blocks.chest.getStateFromMeta(3));
		w.setBlockState(new BlockPos(x + 4, y + 1, z + 2), Blocks.chest.getStateFromMeta(3));
		return true;
	}
}