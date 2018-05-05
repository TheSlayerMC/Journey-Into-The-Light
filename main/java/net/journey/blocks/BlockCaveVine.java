package net.journey.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.slayer.api.block.BlockModVine;

public class BlockCaveVine extends BlockModVine {

	public BlockCaveVine(String name, String f) {
		super(name, f, 2);
		setLightLevel(0.6F);
	}
	
	@Override
	public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos)  {
		return 1000;
	}
}