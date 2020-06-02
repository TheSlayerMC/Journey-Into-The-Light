package net.journey.items.interactive;

import net.journey.blocks.BlockAncientCatalyst;
import net.journey.init.JourneySounds;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.items.base.JItem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemAncientCatalyst extends JItem {

	public ItemAncientCatalyst(String name, String f) {
		super(name, f);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		ItemStack itemstack = player.getHeldItem(hand);
		if (player.canPlayerEdit(pos.offset(facing), facing, itemstack) && iblockstate.getBlock() == JourneyBlocks.ANCIENT_OBELISK) {
            if (worldIn.isRemote) {
                return EnumActionResult.SUCCESS;
            } else {
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
                BlockPattern.PatternHelper blockpattern$patternhelper = BlockAncientCatalyst.getOrCreatepattern().match(worldIn, pos.add(0, -4, 0));

                if (blockpattern$patternhelper != null) {
                    BlockPos blockpos = blockpattern$patternhelper.getFrontTopLeft().add(-1, 0, -1);
                    worldIn.setBlockState(blockpos.add(0, 0, 0), Blocks.AIR.getDefaultState(), 2);
                    worldIn.createExplosion(player, blockpos.getX(), blockpos.getY(), blockpos.getZ(), 10F, false);
                    worldIn.playSound(null, pos, JourneySounds.OBELISK_OPEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
                return EnumActionResult.SUCCESS;
            }
        } else {
            return EnumActionResult.FAIL;
        }
    }
}