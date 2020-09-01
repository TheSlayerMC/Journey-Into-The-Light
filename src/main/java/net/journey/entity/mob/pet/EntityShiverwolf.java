package net.journey.entity.mob.pet;

import net.journey.entity.MobStats;
import net.journey.entity.base.EntityAttributesHelper;
import net.journey.init.JourneySounds;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityJourneyPet;

public class EntityShiverwolf extends EntityJourneyPet {

	public EntityShiverwolf(World worldIn) {
		super(worldIn);
	}

	public EntityShiverwolf(World worldIn, EntityPlayer owner) {
		super(worldIn, owner);
		setSize(0.25F, 0.25F);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		EntityAttributesHelper.setMaxHealth(this, MobStats.SHIVERWOLF_HEALTH);
		EntityAttributesHelper.setAttackDamage(this, MobStats.SHIVERWOLF_DAMAGE);
	}

	@Override
	public SoundEvent setLivingSound() {
		return JourneySounds.EMPTY;
	}

	@Override
	public SoundEvent setHurtSound() {
		return JourneySounds.EMPTY;
	}

	@Override
	public SoundEvent setDeathSound() {
		return JourneySounds.EMPTY;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}
}