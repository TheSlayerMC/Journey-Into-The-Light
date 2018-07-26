package net.journey.blocks.machines;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.slayer.api.block.BlockMod;

public class BlockIgniter extends BlockMod {
			
	public BlockIgniter(String name, String finalName) {
		super(name, finalName);
		this.setTickRandomly(true);
	}
	
	@Override
	public boolean isFireSource(World w, BlockPos pos, EnumFacing face) {
		return true;
	}
	
	@Override
	public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}
	
	@Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            if (worldIn.isBlockPowered(pos)) {
            	worldIn.playSound((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
            	worldIn.setBlockState(pos.up(), Blocks.FIRE.getDefaultState());
            	worldIn.setBlockState(pos, JourneyBlocks.igniterOn.getDefaultState());
            }
			if (!worldIn.isBlockPowered(pos)) {
				worldIn.setBlockToAir(pos.up());
            	worldIn.setBlockState(pos, JourneyBlocks.igniter.getDefaultState());
			}
        }
    }
	
	@Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if(!worldIn.isRemote) {
			if(worldIn.isBlockPowered(pos)) {
            	worldIn.playSound((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
				worldIn.setBlockState(pos.up(), Blocks.FIRE.getDefaultState());
            	worldIn.setBlockState(pos, JourneyBlocks.igniterOn.getDefaultState());
			}
			if (!worldIn.isBlockPowered(pos)) {
				worldIn.setBlockToAir(pos.up());
            	worldIn.setBlockState(pos, JourneyBlocks.igniter.getDefaultState());
			}
		}
	}
	
	@Override
	public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) { }
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			if (!worldIn.isBlockPowered(pos)) {
				worldIn.setBlockToAir(pos.up());
            	worldIn.setBlockState(pos, JourneyBlocks.igniter.getDefaultState());
			}
		}
	}
}