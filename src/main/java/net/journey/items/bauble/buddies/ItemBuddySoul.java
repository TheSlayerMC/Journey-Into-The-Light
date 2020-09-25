package net.journey.items.bauble.buddies;

import net.journey.entity.mob.pet.EntityEucaHopper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class ItemBuddySoul extends ItemBuddyBase {

	@Override
	public EntityLivingBase setBuddy(EntityLivingBase buddy, World world) {
		return new EntityEucaHopper(world);
	}
}

