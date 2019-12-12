package net.journey.items;

import net.journey.JourneyTabs;
import net.journey.blocks.BlockGlowshroom;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.block.BlockMod;
import net.slayer.api.item.ItemMod;

public class ItemGlowshroomBlock extends ItemMod {

	BlockGlowshroom shroombottom;
	BlockGlowshroom shroomtop;

	public ItemGlowshroomBlock(String name, String finalName) {
		super(name, finalName);
		this.setCreativeTab(JourneyTabs.decoration);
	}

	public ItemGlowshroomBlock(String name, String finalName, BlockGlowshroom shroombottom, BlockGlowshroom shroomtop) {
		super(name, finalName);
		this.shroombottom = shroombottom;
		this.shroomtop = shroomtop;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (facing != EnumFacing.UP) {
			return EnumActionResult.FAIL;
		} else {
			IBlockState iblockstate = worldIn.getBlockState(pos);
			Block block = iblockstate.getBlock();

			if (!block.isReplaceable(worldIn, pos)) {
				pos = pos.offset(facing);
			}

			ItemStack itemstack = player.getHeldItem(hand);
			//if (player.canPlayerEdit(pos, facing, itemstack) && this.shroombottom.canPlaceBlockAt(worldIn, pos) && this.shroomtop.canPlaceBlockAt(worldIn, pos)) {
			worldIn.setBlockState(new BlockPos(hitX, hitY, hitZ), shroombottom.getDefaultState());
			worldIn.setBlockState(new BlockPos(hitX, hitY + 1, hitZ), shroomtop.getDefaultState());
			SoundType soundtype = worldIn.getBlockState(pos).getBlock().getSoundType(worldIn.getBlockState(pos), worldIn, pos, player);
			worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
			itemstack.shrink(1);
			return EnumActionResult.SUCCESS;
		}// else {
		//	return EnumActionResult.FAIL;
		//}
	}
}
