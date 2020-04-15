package net.slayer.api.block;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemMod;

public class BlockModDoor extends BlockDoor  {

	
    public BlockModDoor(String name, String finalName, Material materialIn, float hardness) {
        super(materialIn); 
        setTranslationKey(name);
        setHardness(hardness);
        JourneyBlocks.blocks.add(this);
		JourneyBlocks.blockName.add(SlayerAPI.PREFIX + name);
		setRegistryName(SlayerAPI.MOD_ID, name);
		JourneyItems.items.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
            EnumFacing facing, float hitX, float hitY, float hitZ) {
        BlockPos blockpos = state.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER ? pos : pos.down();
        IBlockState iblockstate = pos.equals(blockpos) ? state : worldIn.getBlockState(blockpos);

        if (iblockstate.getBlock() != this) {
            return false;
        } else {
            state = iblockstate.cycleProperty(OPEN);
            worldIn.setBlockState(blockpos, state, 10);
            worldIn.markBlockRangeForRenderUpdate(blockpos, pos);
            worldIn.playEvent(player, ((Boolean) state.getValue(OPEN)).booleanValue() ? 1005 : 1011, pos, 0);
            return true;
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return (state.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER) ? Item.getItemFromBlock(this) :
                Items.AIR;
    }
}
