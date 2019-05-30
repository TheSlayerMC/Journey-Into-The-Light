package net.journey.entity.mob.frozen;

import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityShiveringShrieker extends EntityModMob {

	public EntityShiveringShrieker(World par1World) {
		super(par1World);
		addAttackingAI();
		this.setSize(0.65F, 1F);
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.baseJourneyDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.frozenHealth;
	}

	@Override
	public SoundEvent getAmbientSound() {
		return JourneySounds.SMALL_HONGO;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource sourceIn) {
		return JourneySounds.SMALL_HONGO_HURT;
	}

	@Override
	public SoundEvent getDeathSound() {
		return JourneySounds.SMALL_HONGO_HURT;
	}
	
	@Override
	public boolean getCanSpawnHere() {
		return this.posY < 60.0D && super.getCanSpawnHere();
	}
	
	@Override
	public Item getDropItem() {
		return null;
	}
}