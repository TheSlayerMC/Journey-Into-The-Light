package net.journey.blocks;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.block.BlockMod;

public class BlockCatalyst extends BlockMod {

	public Item blood;
	
	public BlockCatalyst(String name, String finalName, Item blood) {
		super(name, finalName);
		this.blood = blood;
		this.setTickRandomly(true);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		float 
		x = hitX,
		y = hitY,
		z = hitZ;
		float 
		r = 0.0F, 
		g = 0.0F, 
		b = 0.0F;
		
		if(!worldIn.isRemote && playerIn.getHeldItem(hand.MAIN_HAND) != null && playerIn.getHeldItem(hand.MAIN_HAND).getItem() == blood && worldIn
				.getBlockState(
						// base
						pos.add(hitX, hitY - 1, hitZ))
				.getBlock() == JourneyBlocks.bloodRock
				&& worldIn.getBlockState(pos.add(hitX, hitY - 1, hitZ - 1)).getBlock() == JourneyBlocks.bloodRock
				&& worldIn.getBlockState(pos.add(hitX, hitY - 1, hitZ + 1)).getBlock() == JourneyBlocks.bloodRock
				&& worldIn.getBlockState(pos.add(hitX - 1, hitY - 1, hitZ)).getBlock() == JourneyBlocks.bloodRock
				&& worldIn.getBlockState(pos.add(hitX + 1, hitY - 1, hitZ)).getBlock() == JourneyBlocks.bloodRock
				&& worldIn.getBlockState(

						pos.add(hitX - 2, hitY - 1, hitZ)).getBlock() == JourneyBlocks.bloodRune
				&& worldIn.getBlockState(pos.add(hitX + 2, hitY - 1, hitZ)).getBlock() == JourneyBlocks.bloodRune
				&& worldIn.getBlockState(pos.add(hitX, hitY - 1, hitZ - 2)).getBlock() == JourneyBlocks.bloodRune
				&& worldIn.getBlockState(pos.add(hitX, hitY - 1, hitZ + 2)).getBlock() == JourneyBlocks.bloodRune
				&& worldIn.getBlockState(

						pos.add(hitX - 1, hitY - 1, hitZ + 2)).getBlock() == JourneyBlocks.bloodRock
				&& worldIn.getBlockState(pos.add(hitX - 1, hitY - 1, hitZ - 2)).getBlock() == JourneyBlocks.bloodRock
				&& worldIn.getBlockState(pos.add(hitX + 1, hitY - 1, hitZ + 2)).getBlock() == JourneyBlocks.bloodRock
				&& worldIn.getBlockState(pos.add(hitX + 1, hitY - 1, hitZ - 2)).getBlock() == JourneyBlocks.bloodRock
				&& worldIn.getBlockState(

						pos.add(hitX - 2, hitY - 1, hitZ - 1)).getBlock() == JourneyBlocks.bloodRock
				&& worldIn.getBlockState(pos.add(hitX + 2, hitY - 1, hitZ - 1)).getBlock() == JourneyBlocks.bloodRock
				&& worldIn.getBlockState(pos.add(hitX + 2, hitY - 1, hitZ + 1)).getBlock() == JourneyBlocks.bloodRock
				&& worldIn.getBlockState(pos.add(hitX - 2, hitY - 1, hitZ + 1)).getBlock() == JourneyBlocks.bloodRock
				&& worldIn.getBlockState(

						// pillars
						pos.add(hitX - 2, hitY, hitZ - 2)).getBlock() == JourneyBlocks.bloodPillar
				&& worldIn.getBlockState(pos.add(hitX + 2, hitY, hitZ + 2)).getBlock() == JourneyBlocks.bloodPillar
				&& worldIn.getBlockState(pos.add(hitX + 2, hitY, hitZ - 2)).getBlock() == JourneyBlocks.bloodPillar
				&& worldIn.getBlockState(pos.add(hitX - 2, hitY, hitZ + 2)).getBlock() == JourneyBlocks.bloodPillar
				&& worldIn.getBlockState(

						pos.add(hitX - 2, hitY + 1, hitZ - 2)).getBlock() == JourneyBlocks.bloodPillar
				&& worldIn.getBlockState(pos.add(hitX + 2, hitY + 1, hitZ + 2)).getBlock() == JourneyBlocks.bloodPillar
				&& worldIn.getBlockState(pos.add(hitX + 2, hitY + 1, hitZ - 2)).getBlock() == JourneyBlocks.bloodPillar
				&& worldIn.getBlockState(pos.add(hitX - 2, hitY + 1, hitZ + 2)).getBlock() == JourneyBlocks.bloodPillar
				&& worldIn.getBlockState(

						pos.add(hitX - 2, hitY + 2, hitZ - 2)).getBlock() == JourneyBlocks.carvedBloodRock
				&& worldIn.getBlockState(pos.add(hitX + 2, hitY + 2, hitZ + 2))
						.getBlock() == JourneyBlocks.carvedBloodRock
				&& worldIn.getBlockState(pos.add(hitX + 2, hitY + 2, hitZ - 2))
						.getBlock() == JourneyBlocks.carvedBloodRock
				&& worldIn.getBlockState(pos.add(hitX - 2, hitY + 2, hitZ + 2))
						.getBlock() == JourneyBlocks.carvedBloodRock
				&& worldIn.getBlockState(

						// top layer
						pos.add(hitX - 1, hitY + 2, hitZ - 1)).getBlock() == JourneyBlocks.bloodBricks
				&& worldIn.getBlockState(pos.add(hitX + 1, hitY + 2, hitZ + 1)).getBlock() == JourneyBlocks.bloodBricks
				&& worldIn.getBlockState(pos.add(hitX + 1, hitY + 2, hitZ - 1)).getBlock() == JourneyBlocks.bloodBricks
				&& worldIn.getBlockState(pos.add(hitX - 1, hitY + 2, hitZ + 1)).getBlock() == JourneyBlocks.bloodBricks
				&& worldIn.getBlockState(

						pos.add(hitX - 2, hitY + 2, hitZ - 1)).getBlock() == JourneyBlocks.bloodBricks
				&& worldIn.getBlockState(pos.add(hitX - 2, hitY + 2, hitZ + 1)).getBlock() == JourneyBlocks.bloodBricks
				&& worldIn.getBlockState(pos.add(hitX + 2, hitY + 2, hitZ - 1)).getBlock() == JourneyBlocks.bloodBricks
				&& worldIn.getBlockState(pos.add(hitX + 2, hitY + 2, hitZ + 1)).getBlock() == JourneyBlocks.bloodBricks
				&& worldIn.getBlockState(

						pos.add(hitX - 1, hitY + 2, hitZ - 2)).getBlock() == JourneyBlocks.bloodBricks
				&& worldIn.getBlockState(pos.add(hitX - 1, hitY + 2, hitZ + 2)).getBlock() == JourneyBlocks.bloodBricks
				&& worldIn.getBlockState(pos.add(hitX + 1, hitY + 2, hitZ - 2)).getBlock() == JourneyBlocks.bloodBricks
				&& worldIn.getBlockState(pos.add(hitX + 1, hitY + 2, hitZ + 2)).getBlock() == JourneyBlocks.bloodBricks
				&& worldIn.getBlockState(

						// roof
						pos.add(hitX - 1, hitY + 3, hitZ - 1)).getBlock() == JourneyBlocks.bloodBricks
				&& worldIn.getBlockState(pos.add(hitX + 1, hitY + 3, hitZ + 1)).getBlock() == JourneyBlocks.bloodBricks
				&& worldIn.getBlockState(pos.add(hitX + 1, hitY + 3, hitZ - 1)).getBlock() == JourneyBlocks.bloodBricks
				&& worldIn.getBlockState(pos.add(hitX - 1, hitY + 3, hitZ + 1)).getBlock() == JourneyBlocks.bloodBricks
				&& worldIn.getBlockState(

						pos.add(hitX - 1, hitY + 3, hitZ)).getBlock() == JourneyBlocks.bloodBricks
				&& worldIn.getBlockState(pos.add(hitX + 1, hitY + 3, hitZ)).getBlock() == JourneyBlocks.bloodBricks
				&& worldIn.getBlockState(pos.add(hitX, hitY + 3, hitZ - 1)).getBlock() == JourneyBlocks.bloodBricks
				&& worldIn.getBlockState(pos.add(hitX, hitY + 3, hitZ + 1)).getBlock() == JourneyBlocks.bloodBricks
				&& worldIn.getBlockState(

						pos.add(hitX, hitY + 2, hitZ)).getBlock() == JourneyBlocks.obelisk
				) { 
				worldIn.setBlockState(pos.add(0, 0, 0), JourneyBlocks.summoningTable.getDefaultState());
				playerIn.getHeldItem(hand.MAIN_HAND).shrink(1);
				worldIn.playSound(playerIn, x, (double) y + 0.5D, z, SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN,
						SoundCategory.BLOCKS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.735F);
				worldIn.spawnParticle(EnumParticleTypes.SPELL, x, y, z, 2.0F, 2.0F, 2.0F, 2);
		}

		return false;
	}
}
