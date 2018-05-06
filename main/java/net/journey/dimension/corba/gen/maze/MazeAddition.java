package net.journey.dimension.corba.gen.maze;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MazeAddition {

	public void setBlock(World w, int x, int y, int z, Block b) {
		w.setBlockState(new BlockPos(x, y, z), b.getDefaultState());
	}
	
	public void setBlock(World w, int x, int y, int z, Block b, int meta) {
		w.setBlockState(new BlockPos(x, y, z), b.getStateFromMeta(meta));
	}
}