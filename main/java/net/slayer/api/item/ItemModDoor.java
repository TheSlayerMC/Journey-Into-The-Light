package net.slayer.api.item;

import net.journey.JourneyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.block.BlockModDoor;

public class ItemModDoor extends ItemMod {

	private Block door;

	public ItemModDoor(BlockModDoor block, String name, String f) {
		super(name, f);
		this.door = block;
		setCreativeTab(JourneyTabs.blocks);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(side != EnumFacing.UP) return EnumActionResult.FAIL;
		else {
			IBlockState iblockstate = worldIn.getBlockState(pos);
			Block block = iblockstate.getBlock();
            ItemStack stack = player.getHeldItem(hand);

			if(!block.isReplaceable(worldIn, pos)) pos = pos.offset(side);
			if(!player.canPlayerEdit(pos, side, stack)) return EnumActionResult.FAIL;
			else if(!this.door.canPlaceBlockAt(worldIn, pos)) return EnumActionResult.FAIL;
			else {
				ItemDoor.placeDoor(worldIn, pos, EnumFacing.fromAngle(player.rotationYaw), this.door, true);
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
		}
	}
}