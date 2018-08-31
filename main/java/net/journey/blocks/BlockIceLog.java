package net.journey.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.block.BlockModLog;

public class BlockIceLog extends BlockModLog {

	public BlockIceLog() {
		super("iceLog", "Ice Log", Material.WOOD);
		isOpaque = false;
		isNormalCube = false;
		setLightOpacity(3);
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess iba, BlockPos pos, EnumFacing side){
		Block block = iba.getBlockState(pos).getBlock();
		return block == this && (side == EnumFacing.DOWN || side == EnumFacing.UP) ? false : super.shouldSideBeRendered(blockState, iba, pos, side);
	}
}