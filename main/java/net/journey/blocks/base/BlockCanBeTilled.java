package net.journey.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;

public class BlockCanBeTilled extends BlockMod {

	Block farmland;
	public BlockCanBeTilled(EnumMaterialTypes types, String name, String finalName, float hardness, Block farmland) {
		super(types, name, finalName, hardness);
		this.farmland = farmland;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!(worldIn.isRemote) && playerIn.getHeldItem(hand) != null &&  playerIn.getHeldItem(hand).getItem() instanceof ItemHoe) {
			worldIn.setBlockState(new BlockPos(0, 0, 0), farmland.getDefaultState());
			return true;
		}
		return false;
	}
}
