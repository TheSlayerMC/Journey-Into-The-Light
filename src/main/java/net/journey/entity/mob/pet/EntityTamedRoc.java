package net.journey.entity.mob.pet;

import net.journey.entity.MobStats;
import net.journey.entity.base.EntityAttributesHelper;
import net.journey.init.JourneySounds;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityJourneyPet;

public class EntityTamedRoc extends EntityJourneyPet {

	public EntityTamedRoc(World worldIn) {
		super(worldIn);
	}

	public EntityTamedRoc(World worldIn, EntityPlayer owner) {
		super(worldIn, owner);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		EntityAttributesHelper.setMaxHealth(this, MobStats.TAMED_ROC_HEALTH);
		EntityAttributesHelper.setAttackDamage(this, MobStats.TAMED_ROC_DAMAGE);
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