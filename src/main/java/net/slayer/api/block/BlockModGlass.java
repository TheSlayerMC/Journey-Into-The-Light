package net.slayer.api.block;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;

public class BlockModGlass extends BlockMod {

	public BlockModGlass(String name, String finalName, float hardness) {
		super(EnumMaterialTypes.GLASS, name, finalName, hardness);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
        Block block = iblockstate.getBlock();
        if(this.getMaterial(getDefaultState()) == Material.GLASS) {
            if(blockState != iblockstate) {
                return true;
            }
            if(block == this) {
                return false;
            }
        }
        return block == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	}
}