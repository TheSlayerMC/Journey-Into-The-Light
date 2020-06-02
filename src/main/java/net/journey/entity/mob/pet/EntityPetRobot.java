package net.journey.entity.mob.pet;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityJourneyPet;

public class EntityPetRobot extends EntityJourneyPet {

	public EntityPetRobot(World w) {
		super(w);
		setSize(0.25F, 0.25F);
	}
	
	public EntityPetRobot(World worldIn, EntityPlayer owner) {
		super(worldIn, owner);
		setSize(0.25F, 0.25F);
	}

	@Override
	public SoundEvent setLivingSound() {
		return null;
	}

	@Override
	public SoundEvent setHurtSound() {
		return null;
	}

	@Override
	public SoundEvent setDeathSound() {
		return null;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}

}