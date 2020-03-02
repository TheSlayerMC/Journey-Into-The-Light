package net.journey.blocks.base;

import net.journey.JourneyBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.slayer.api.block.BlockModFlower;

public class BlockNetherFlower extends BlockModFlower {

	public BlockNetherFlower(String name, String finalName) {
		super(name, finalName);
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return super.canPlaceBlockAt(worldIn, pos) && worldIn.getBlockState(pos.down()).getBlock() == Blocks.NETHERRACK
				|| worldIn.getBlockState(pos.down()).getBlock() == JourneyBlocks.heatSoil
				|| worldIn.getBlockState(pos.down()).getBlock() == JourneyBlocks.heatSand
				|| worldIn.getBlockState(pos.down()).getBlock() == JourneyBlocks.earthenNetherrack
				|| worldIn.getBlockState(pos.down()).getBlock() == Blocks.SOUL_SAND;
	}

	@Override 
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState b) {
			return worldIn.getBlockState(pos.down()).getBlock() == Blocks.NETHERRACK
					|| worldIn.getBlockState(pos.down()).getBlock() == JourneyBlocks.heatSoil
					|| worldIn.getBlockState(pos.down()).getBlock() == JourneyBlocks.heatSand
					|| worldIn.getBlockState(pos.down()).getBlock() == JourneyBlocks.earthenNetherrack
					|| worldIn.getBlockState(pos.down()).getBlock() == Blocks.SOUL_SAND;
	}

	@Override
	public BlockNetherFlower setLightLevel(float value) {
		this.lightValue = (int)(15.0F * value);
		return this;
	}
}