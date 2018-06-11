package net.journey.items;

import net.journey.JourneyBlocks;
import net.journey.JourneyCrops;
import net.journey.blocks.BlockEucaPumpkin;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.item.ItemModFood;

public class ItemBleedheart extends ItemModFood {

	public ItemBleedheart(String name, String f, int food, float sat, boolean wolfFood) {
		super(name, f, food, sat, wolfFood);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack stack = playerIn.getHeldItemMainhand();
		if (!playerIn.canPlayerEdit(pos.offset(side), side, stack)) {
			return EnumActionResult.FAIL;
		} else {
			IBlockState iblockstate = worldIn.getBlockState(pos);
			Block block = iblockstate.getBlock();

			if (block == JourneyBlocks.sizzlerWoodLog) {
				if (side == EnumFacing.DOWN) {
					return EnumActionResult.FAIL;
				}

				if (side == EnumFacing.UP) {
					return EnumActionResult.FAIL;
				}

				pos = pos.offset(side);

				if (worldIn.isAirBlock(pos)) {
					IBlockState iblockstate1 = JourneyCrops.bleedheartFruit.getDefaultState();
					worldIn.setBlockState(pos, iblockstate1, 2);

					if (!playerIn.capabilities.isCreativeMode) {
						stack.shrink(1);
					}
				}
				return EnumActionResult.SUCCESS;
			}
			return EnumActionResult.FAIL;
		}
	}
}
