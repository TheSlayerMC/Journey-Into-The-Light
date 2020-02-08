package net.journey.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.block.BlockModLog;

public class BlockIceLog extends BlockModLog {

	public BlockIceLog() {
		super("iceLog", "Ice Log");
		isOpaque = false;
		isNormalCube = false;
		setLightOpacity(3);
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess iba, BlockPos pos, EnumFacing side){
		IBlockState iblockstate = iba.getBlockState(pos.offset(side));
		Block block = iblockstate.getBlock();
		if(blockState != iblockstate)  return true;
		if(block == this)  return false;
		
		return block == this ? false : super.shouldSideBeRendered(blockState, iba, pos, side);
	}
}