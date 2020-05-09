package net.journey.blocks;

import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.block.BlockMod;

public class BlockLament extends BlockMod {

	Item insert;
	private static BlockPattern pattern;

    public BlockLament(String name, String f, Item lament) {
        super(name, f, false);
        this.insert = lament;
    }

    public static BlockPattern getOrCreatepattern() {
        if (pattern == null) {
            pattern = FactoryBlockPattern.start().aisle(
            "?l????l?", 
            "l??????l", 
            "????????", 
            "????????", 
            "????????", 
            "????????", 
            "l??????l", 
            "?l????l?")
            		.where(
            '?', BlockWorldState.hasState(BlockStateMatcher.ANY)).where(
            'l', BlockWorldState.hasState(BlockStateMatcher.forBlock(JourneyBlocks.LAMENT))).build();
        }
        return pattern;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
    
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		float x = hitX, y = hitY, z = hitZ;
		if (playerIn.getHeldItem(EnumHand.MAIN_HAND) != null && playerIn.getHeldItem(EnumHand.MAIN_HAND).getItem() == insert) {
            worldIn.createExplosion(playerIn, pos.getX(), pos.getY(), pos.getZ(), 1F, false);
			worldIn.setBlockState(pos, JourneyBlocks.LAMENT.getDefaultState());
		}
		return true;
	}
}