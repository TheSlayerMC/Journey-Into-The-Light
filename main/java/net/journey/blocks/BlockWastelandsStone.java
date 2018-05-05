package net.journey.blocks;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.slayer.api.block.BlockMod;

public class BlockWastelandsStone extends BlockMod {

	public BlockWastelandsStone(String name, String f) {
		super(name, f);
	}
	
	/*@Override
	public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
		return pos.getY() & 0xAE9000;
	}*/
}