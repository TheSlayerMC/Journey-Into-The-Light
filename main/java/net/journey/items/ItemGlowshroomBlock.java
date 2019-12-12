package net.journey.items;

import net.journey.JourneyBlocks;
import net.journey.JourneyTabs;
import net.journey.blocks.BlockGlowshroom;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
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

	private Block shroombottom;
	private Block shroomtop;

	public ItemGlowshroomBlock(String name, String finalName, Block shroombottom, Block shroomtop) {
		super(name, finalName);
		this.shroombottom = shroombottom;
		this.shroomtop = shroomtop;
		this.setCreativeTab(JourneyTabs.decoration);
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
			//worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), shroombottom.getDefaultState());
			//worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), shroomtop.getDefaultState());
			SoundType soundtype = worldIn.getBlockState(pos).getBlock().getSoundType(worldIn.getBlockState(pos), worldIn, pos, player);
			worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
			itemstack.shrink(1);
			return EnumActionResult.SUCCESS;
		}
	}
}