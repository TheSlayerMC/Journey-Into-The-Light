package net.journey.items.interactive;

import net.journey.items.base.JItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityJourneyPet;

public class ItemSpawnerEgg extends JItem {

	private final Class<? extends EntityJourneyPet> pet;

	public ItemSpawnerEgg(Class<? extends EntityJourneyPet> pet) {
		this.pet = pet;
		setMaxStackSize(1);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		try {
			EntityJourneyPet spawn = pet.getConstructor(World.class, EntityPlayer.class).newInstance(worldIn, player);
			if (!worldIn.isRemote) {
				player.getHeldItem(hand).shrink(1);
				spawn.setLocationAndAngles(pos.getX() + 0.5F, pos.up(1).getY(), pos.getZ() + 0.5F, 0.0F, 0.0F);
				worldIn.spawnEntity(spawn);
				return EnumActionResult.SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EnumActionResult.PASS;
	}
}