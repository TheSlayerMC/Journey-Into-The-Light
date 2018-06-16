package net.journey.blocks.base;

import net.journey.JourneyBlocks;
import net.journey.JourneyTabs;
import net.minecraft.block.BlockRail;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockModRail extends BlockRail {

	private boolean power;
	private float speed;
	
	public BlockModRail(String name, boolean isPowered, float speed) {
		setCreativeTab(JourneyTabs.blocks);
		setUnlocalizedName(name);
		JourneyBlocks.blocks.add(this);
		JourneyBlocks.blockName.add(name);
		power = isPowered;
		this.speed = speed;
		setRegistryName(name);
	}
	
	@Override
	public float getRailMaxSpeed(World world, EntityMinecart cart, BlockPos pos) {
		return speed;
	}
}