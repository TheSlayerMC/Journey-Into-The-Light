package net.journey.entity.mob.pet;

import net.journey.entity.base.EntityAttributesHelper;
import net.journey.init.JourneySounds;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityJourneyPet;

public class EntityTameRoc extends EntityJourneyPet {

	public EntityTameRoc(World worldIn) {
		super(worldIn);
	}

	public EntityTameRoc(World worldIn, EntityPlayer owner) {
		super(worldIn, owner);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		EntityAttributesHelper.setMaxHealth(this, 60);
		EntityAttributesHelper.setAttackDamage(this, 7);
	}

	@Override
	public SoundEvent setLivingSound() {
		return JourneySounds.BIRD;
	}

	@Override
	public SoundEvent setHurtSound() {
		return JourneySounds.BIRD_HURT;
	}

	@Override
	public SoundEvent setDeathSound() {
		return JourneySounds.BIRD_DEATH;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}
}