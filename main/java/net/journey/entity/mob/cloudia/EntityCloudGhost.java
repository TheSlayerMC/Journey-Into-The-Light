package net.journey.entity.mob.cloudia;

import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityCloudGhost extends EntityModMob{

	public EntityCloudGhost(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(0.7F, 2.4F);
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.hardestJourneyDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.cloudiaHealth;
	}

	@Override
	public SoundEvent getAmbientSound() {
		return JourneySounds.SPIKED_BEAST;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource sourceIn) {
		return JourneySounds.SPIKED_BEAST_HURT;
	}

	@Override
	public SoundEvent getDeathSound() {
		return JourneySounds.SPIKED_BEAST_HURT;
	}
	
	@Override
	public Item getDropItem() {
		return null;
	}
}