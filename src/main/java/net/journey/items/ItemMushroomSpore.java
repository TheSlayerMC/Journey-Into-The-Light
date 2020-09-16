package net.journey.items;

import net.journey.init.blocks.JourneyBlocks;
import net.journey.items.base.JItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemMushroomSpore extends JItem {

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		ItemStack itemstack = player.getHeldItem(hand);

		if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack)) {
			return EnumActionResult.FAIL;

		} else {

			IBlockState iblockstate = worldIn.getBlockState(pos);
			Block block = iblockstate.getBlock();

			if (block instanceof BlockLog) {

				if (facing == EnumFacing.DOWN || facing == EnumFacing.UP) {
					return EnumActionResult.FAIL;
				}

				pos = pos.offset(facing);

				if (worldIn.isAirBlock(pos)) {

					IBlockState iblockstate1 = JourneyBlocks.swamp_shelf.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, 0, player, hand);
					worldIn.setBlockState(pos, iblockstate1, 10);

					if (!player.capabilities.isCreativeMode) {
						itemstack.shrink(1);
					}

					worldIn.playSound(player, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.0F, 2.0F);

					return EnumActionResult.SUCCESS;
				}
			}
			return EnumActionResult.PASS;
		}
	}
}
