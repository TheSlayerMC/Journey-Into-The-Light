package net.journey.blocks.base;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.minecraft.block.BlockRail;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;

public class BlockModRail extends BlockRail {

	private boolean power;
	private float speed;
	
	public BlockModRail(String name, boolean isPowered, float speed) {
		setCreativeTab(JourneyTabs.blocks);
		setUnlocalizedName(SlayerAPI.PREFIX + name);
		JourneyBlocks.blocks.add(this);
		JourneyBlocks.blockName.add(SlayerAPI.PREFIX + name);
		power = isPowered;
		this.speed = speed;
		setRegistryName(SlayerAPI.MOD_ID, name);
		
		JourneyItems.items.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public float getRailMaxSpeed(World world, EntityMinecart cart, BlockPos pos) {
		return speed;
	}
}