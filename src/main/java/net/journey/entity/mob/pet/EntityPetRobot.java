package net.journey.entity.mob.pet;

import net.journey.entity.MobStats;
import net.journey.entity.base.EntityAttributesHelper;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityJourneyPet;

public class EntityPetRobot extends EntityJourneyPet {

	public EntityPetRobot(World w) {
		super(w);
		setSize(0.5F, 1.0F);
	}

	public EntityPetRobot(World worldIn, EntityPlayer owner) {
		super(worldIn, owner);
		setSize(0.5F, 1.0F);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		EntityAttributesHelper.setMaxHealth(this, MobStats.PET_ROBOT_HEALTH);
		EntityAttributesHelper.setAttackDamage(this, MobStats.PET_ROBOT_DAMAGE);
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