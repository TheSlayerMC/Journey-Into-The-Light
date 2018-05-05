package net.journey.blocks.crop;

import java.util.List;
import java.util.Random;

import net.journey.JourneyCrops;
import net.journey.JourneyItems;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.slayer.api.block.BlockModCrop;

public class BlockAirRootCrop extends BlockModCrop {

	public BlockAirRootCrop(String name) {
		super(name);
	}

	@Override
	public PropertyInteger getAge() {
		return PropertyInteger.create("age", 0, 4);
	}
	
	@Override
	public Item getSeed() {
		return JourneyCrops.airRootSeed;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0F, 0F, 0F, 1F, 1F, 1F);
	}
    
	@Override
	public Item getCrop() {
		return JourneyItems.airMelon;
	}
	
	@Override
    public int quantityDropped(Random random) {
        return 1 + random.nextInt(2);
    }

	@Override
	public int getStages() {
		return 4;
	}
}