package net.journey.items;

import java.util.List;
import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.JourneyTabs;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemMod;

public class ItemFlameCoin extends ItemMod {

	public ItemFlameCoin(String name, String f) {
		super(name, f);
		setCreativeTab(JourneyTabs.util);
		setMaxStackSize(1);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World w, BlockPos pos, EnumHand hand, EnumFacing fa, float hitX, float hitY, float hitZ) {
		Random r = new Random();
		int x = pos.getX(), y = pos.getY(), z = pos.getZ();
		if(fa != EnumFacing.UP && w.getBlockState(pos.up()).getBlock() != Blocks.AIR){
			return EnumActionResult.FAIL;
		} else {
			Block block = w.getBlockState(pos).getBlock();
			if(block == JourneyBlocks.frozenPortalFrame ||block == JourneyBlocks.eucaPortalFrame || block == JourneyBlocks.depthsPortalFrame || block == JourneyBlocks.boilPortalFrame 
					 || block == JourneyBlocks.cloudiaPortalFrame || block == JourneyBlocks.terraniaPortalFrame){
				w.setBlockState(new BlockPos(pos.up()), JourneyBlocks.fire.getDefaultState(), 2);
				return EnumActionResult.SUCCESS;
			}
			else return EnumActionResult.FAIL;
		}
		//BARN
		/*WorldGenAPI.addRectangle(7, 5, 1, w, x, y, z, EssenceBlocks.greenCorbaPlank);
		WorldGenAPI.addRectangle(1, 5, 4, w, x, y + 1, z, EssenceBlocks.brownCorbaLog);
		WorldGenAPI.addRectangle(1, 3, 4, w, x, y + 1, z + 1, EssenceBlocks.greenCorbaLog);
		WorldGenAPI.addRectangle(1, 1, 3, w, x, y + 1, z + 2, Blocks.air);
		WorldGenAPI.addRectangle(5, 1, 1, w, x + 1, y + 1, z, EssenceBlocks.brownCorbaLog);
		WorldGenAPI.addRectangle(5, 1, 1, w, x + 1, y + 1, z + 4, EssenceBlocks.brownCorbaLog);
		WorldGenAPI.addRectangle(1, 5, 4, w, x + 6, y + 1, z, EssenceBlocks.brownCorbaLog);
		WorldGenAPI.addRectangle(1, 3, 3, w, x + 6, y + 2, z + 1, EssenceBlocks.greenCorbaLog);
		WorldGenAPI.addRectangle(5, 1, 3, w, x + 1, y + 2, z, EssenceBlocks.greenCorbaLog);
		WorldGenAPI.addRectangle(5, 1, 3, w, x + 1, y + 2, z + 4, EssenceBlocks.greenCorbaLog);
		WorldGenAPI.addRectangle(3, 1, 3, w, x + 2, y + 2, z, EssenceBlocks.brownCorbaPlank);
		WorldGenAPI.addRectangle(3, 1, 3, w, x + 2, y + 2, z + 4, EssenceBlocks.brownCorbaPlank);
		WorldGenAPI.addRectangle(3, 5, 1, w, x + 2, y + 3, z, Blocks.air);
		WorldGenAPI.addBlock(w, x + 1, y, z + 3, Blocks.hay_block);
		WorldGenAPI.addBlock(w, x + 5, y, z + 1, Blocks.hay_block);
		WorldGenAPI.addBlock(w, x + 5, y + 1, z + 1, Blocks.hay_block);
		WorldGenAPI.addBlock(w, x + 4, y, z + 1, Blocks.hay_block);
		WorldGenAPI.addBlock(w, x + 5, y - 1, z + 2, Blocks.hay_block);
		WorldGenAPI.addBlock(w, x + 5, y + 2, z + 1, Blocks.chest);
		WorldGenAPI.addRectangle(5, 3, 1, w, x + 1, y + 5, z + 1, EssenceBlocks.brownCorbaLog);
		
		//OTHER THING
		WorldGenAPI.addRectangle(10, 4, 1, w, x, y, z, EssenceBlocks.brownCorbaPlank);
		WorldGenAPI.addRectangle(6, 4, 1, w, x + 4, y, z + 4, EssenceBlocks.corbaStone);
		WorldGenAPI.addRectangle(9, 4, 1, w, x, y + 5, z, EssenceBlocks.brownCorbaLog);
		WorldGenAPI.addRectangle(5, 4, 1, w, x + 4, y + 5, z + 4, EssenceBlocks.brownCorbaLog);
		WorldGenAPI.addRectangle(10, 1, 4, w, x, y + 1, z, EssenceBlocks.brownCorbaPlank);
		WorldGenAPI.addRectangle(4, 1, 4, w, x, y + 1, z + 3, EssenceBlocks.brownCorbaPlank);
		WorldGenAPI.addRectangle(4, 1, 4, w, x, y + 1, z + 3, EssenceBlocks.brownCorbaPlank);
		WorldGenAPI.addRectangle(1, 8, 4, w, x + 9, y + 1, z, EssenceBlocks.brownCorbaPlank);*/

		//return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
		l.add(SlayerAPI.Colour.GREEN + "Key item used to light all portals.");
	}
}