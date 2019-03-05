package net.journey.blocks;

import net.journey.JourneyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.block.BlockMod;
import net.slayer.api.block.BlockModGrass;

public class BlockBrittleIce extends BlockModGrass {

	protected BlockMod dirt; 
	protected String tex;

	public BlockBrittleIce(BlockMod dirt, String name, String finalName, float hardness) {
		super(dirt, name, finalName, hardness);
		this.dirt = dirt;
		this.isNormalCube = false;
		this.isOpaque = false;
		setCreativeTab(JourneyTabs.blocks);
		setTickRandomly(true);
        this.slipperiness = 0.98F;
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
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	@SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
        Block block = iblockstate.getBlock();
        return block == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }
}