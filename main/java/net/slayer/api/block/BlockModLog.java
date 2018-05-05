package net.slayer.api.block;

import net.minecraft.world.IBlockAccess;
import net.slayer.api.EnumMaterialTypes;

public class BlockModLog extends BlockMod {

	public BlockModLog(String name, String finalName) {
		super(EnumMaterialTypes.WOOD, name, finalName, 3.0F);
	}

	@Override
	public boolean isWood(IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public boolean canSustainLeaves(IBlockAccess world, BlockPos pos) {
		return true;
	}
}