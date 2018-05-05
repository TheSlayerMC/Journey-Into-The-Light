package net.journey.items;

import net.journey.JourneyBlocks;
import net.journey.blocks.BlockSenterianPortalFrame;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.slayer.api.item.ItemMod;

public class ItemSentryEye extends ItemMod {

	public ItemSentryEye(String name, String f) {
		super(name, f);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
		IBlockState iblockstate = worldIn.getBlockState(pos);

		if (playerIn.canPlayerEdit(pos.offset(side), side, stack) && iblockstate.getBlock() == JourneyBlocks.senterianPortalFrame && !iblockstate.getValue(BlockSenterianPortalFrame.EYE).booleanValue()) {
			if (worldIn.isRemote) {
				return true;
			} else {
				worldIn.setBlockState(pos, iblockstate.withProperty(BlockSenterianPortalFrame.EYE, Boolean.valueOf(true)), 2);
				worldIn.updateComparatorOutputLevel(pos, JourneyBlocks.senterianPortalFrame);
				--stack.stackSize;

				for (int i = 0; i < 16; ++i) {
					double d0 = (float)pos.getX() + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F;
					double d1 = (float)pos.getY() + 0.8125F;
					double d2 = (float)pos.getZ() + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F;
					double d3 = 0.0D;
					double d4 = 0.0D;
					double d5 = 0.0D;
					worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, d3, d4, d5, new int[0]);
				}

				EnumFacing enumfacing1 = iblockstate.getValue(BlockSenterianPortalFrame.FACING);
				int l = 0;
				int j = 0;
				boolean flag1 = false;
				boolean flag = true;
				EnumFacing enumfacing2 = enumfacing1.rotateY();

				for (int k = -2; k <= 2; ++k) {
					BlockPos blockpos2 = pos.offset(enumfacing2, k);
					IBlockState iblockstate1 = worldIn.getBlockState(blockpos2);

					if (iblockstate1.getBlock() == JourneyBlocks.senterianPortalFrame) {
						if (!iblockstate1.getValue(BlockSenterianPortalFrame.EYE).booleanValue()) {
							flag = false;
							break;
						}

						j = k;

						if (!flag1) {
							l = k;
							flag1 = true;
						}
					}
				}

				if (flag && j == l + 2) {
					BlockPos blockpos1 = pos.offset(enumfacing1, 4);
					int i1;

					for (i1 = l; i1 <= j; ++i1) {
						BlockPos blockpos3 = blockpos1.offset(enumfacing2, i1);
						IBlockState iblockstate3 = worldIn.getBlockState(blockpos3);

						if (iblockstate3.getBlock() != JourneyBlocks.senterianPortalFrame || !iblockstate3.getValue(BlockSenterianPortalFrame.EYE).booleanValue()) {
							flag = false;
							break;
						}
					}

					int j1;
					BlockPos blockpos4;

					for (i1 = l - 1; i1 <= j + 1; i1 += 4) {
						blockpos1 = pos.offset(enumfacing2, i1);

						for (j1 = 1; j1 <= 3; ++j1) {
							blockpos4 = blockpos1.offset(enumfacing1, j1);
							IBlockState iblockstate2 = worldIn.getBlockState(blockpos4);

							if (iblockstate2.getBlock() != JourneyBlocks.senterianPortalFrame || !iblockstate2.getValue(BlockSenterianPortalFrame.EYE).booleanValue()) {
								flag = false;
								break;
							}
						}
					}

					if (flag) {
						for (i1 = l; i1 <= j; ++i1) {
							blockpos1 = pos.offset(enumfacing2, i1);

							for (j1 = 1; j1 <= 3; ++j1) {
								blockpos4 = blockpos1.offset(enumfacing1, j1);
								worldIn.setBlockState(blockpos4, JourneyBlocks.senterianPortal.getDefaultState(), 2);
							}
						}
					}
				}
				return true;
			}
		} else {
			return false;
		}
	}
}