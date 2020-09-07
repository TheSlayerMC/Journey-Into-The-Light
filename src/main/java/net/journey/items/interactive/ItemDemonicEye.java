package net.journey.items.interactive;

import net.journey.blocks.portal.BlockCorbaPortalFrame;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.items.base.JItem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemDemonicEye extends JItem {
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		ItemStack itemstack = player.getHeldItem(hand);
		if (player.canPlayerEdit(pos.offset(facing), facing, itemstack) && iblockstate.getBlock() == JourneyBlocks.corbaPortalFrame && !iblockstate.getValue(BlockCorbaPortalFrame.EYE)) {
			if (worldIn.isRemote) {
				return EnumActionResult.SUCCESS;
			} else {
				worldIn.setBlockState(pos, iblockstate.withProperty(BlockCorbaPortalFrame.EYE, true), 2);
				worldIn.updateComparatorOutputLevel(pos, JourneyBlocks.corbaPortalFrame);
				itemstack.shrink(1);

				for (int i = 0; i < 16; ++i) {
					double d0 = (float) pos.getX() + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F;
					double d1 = (float) pos.getY() + 0.8125F;
                    double d2 = (float) pos.getZ() + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F;
                    double d3 = 0.0D;
                    double d4 = 0.0D;
                    double d5 = 0.0D;
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                }

                worldIn.playSound(null, pos, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                BlockPattern.PatternHelper blockpattern$patternhelper = BlockCorbaPortalFrame.getOrCreatePortalShape().match(worldIn, pos);

                if (blockpattern$patternhelper != null) {
                    BlockPos blockpos = blockpattern$patternhelper.getFrontTopLeft().add(-3, 0, -3);

                    for (int j = 0; j < 3; ++j) {
                        for (int k = 0; k < 3; ++k) {
                            worldIn.setBlockState(blockpos.add(j, 0, k), JourneyBlocks.corbaPortal.getDefaultState(), 2);
                        }
                    }
                    worldIn.playBroadcastSound(1038, blockpos.add(1, 0, 1), 0);
                }
                return EnumActionResult.SUCCESS;
            }
        } else {
            return EnumActionResult.FAIL;
        }
    }
}