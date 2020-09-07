package net.journey.items;

import net.journey.blocks.base.JBlockWaterLily;
import net.journey.init.JourneyTabs;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.items.base.JItem;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class JItemWaterLily extends JItem {

	public JItemWaterLily() {
		setCreativeTab(JourneyTabs.DECORATION);
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);
		if (raytraceresult == null) {
			return new ActionResult<>(EnumActionResult.PASS, itemstack);
		} else {
			if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK) {
				BlockPos groundPos = raytraceresult.getBlockPos();
				if (!worldIn.isBlockModifiable(playerIn, groundPos) || !playerIn.canPlayerEdit(groundPos.offset(raytraceresult.sideHit), raytraceresult.sideHit, itemstack)) {
					return new ActionResult<>(EnumActionResult.FAIL, itemstack);
				}

				BlockPos placePos = groundPos.up();

				JBlockWaterLily swampLily = JourneyBlocks.swampLily;
				if (swampLily.canPlaceBlockAt(worldIn, placePos)) {
					worldIn.setBlockState(placePos, swampLily.getDefaultState());
					if (playerIn instanceof EntityPlayerMP) {
						CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) playerIn, placePos, itemstack);
					}

					if (!playerIn.capabilities.isCreativeMode) {
						itemstack.shrink(1);
					}

					worldIn.playSound(playerIn, groundPos, SoundEvents.BLOCK_WATERLILY_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
					return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
				}
			}

			return new ActionResult<>(EnumActionResult.FAIL, itemstack);
		}
	}
}
