package net.journey.blocks.crop;

import java.util.Random;

import net.journey.JourneyConsumables;
import net.journey.JourneyCrops;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.block.BlockModCrop;

public class BlockFloroCrop extends BlockModCrop {

	public BlockFloroCrop(String name) {
		super(name);
	}

	@Override
	public PropertyInteger getAge() {
		return PropertyInteger.create("age", 0, 6);
	}
	
	@Override
	public Item getSeed() {
		return JourneyCrops.floroSeeds;
	}

	@Override
	public Item getCrop() {
		return JourneyConsumables.floroPedal;
	}

	@Override
	public int getStages() {
		return 6;
	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return false;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return false;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		
	}
}