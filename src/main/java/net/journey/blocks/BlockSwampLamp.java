package net.journey.blocks;

import net.journey.JourneyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;

public class BlockSwampLamp extends BlockMod {

	protected String tex;

	public BlockSwampLamp(String name, String finalName, float hardness) {
		super(EnumMaterialTypes.GLASS, name, finalName, hardness);
		this.isNormalCube = false;
		this.isOpaque = false;
		setCreativeTab(JourneyTabs.decoration);
		setTickRandomly(true);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		float f = 0.0625F;
		return new AxisAlignedBB(pos.getX() + (5 * f), pos.getY(), pos.getZ() + (5 * f), pos.getX() + 1 - (5 * f), pos.getY() + 1.2D - (10 * f), pos.getZ() + 1 - (5 * f));
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		float f = 0.0625F;
		return new AxisAlignedBB(pos.getX() + (5 * f), pos.getY(), pos.getZ() + (5 * f), pos.getX() + 1 - (5 * f), pos.getY() + 1.2D - (10 * f), pos.getZ() + 1 - (5 * f));
	}

	@Override 
    public boolean isOpaqueCube(IBlockState state) {
    	return false;
    }
    
    @Override
    public boolean isNormalCube(IBlockState state){
		return false;
    }
    
    @Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}
    
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess iba, BlockPos pos, EnumFacing side){
		Block block = iba.getBlockState(pos).getBlock();
		return block == this ? false : super.shouldSideBeRendered(blockState, iba, pos, side);
	}
}