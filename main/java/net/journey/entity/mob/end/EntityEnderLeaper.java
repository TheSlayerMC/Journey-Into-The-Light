package net.journey.entity.mob.end;

import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityEnderLeaper extends EntityModMob {

	public EntityEnderLeaper(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(0.7F, 1.2F);
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.baseJourneyDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.eucaHealth;
	}

	@Override
	public SoundEvent getAmbientSound() {
		return JourneySounds.SHIMMERER;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource sourceIn) {
		return JourneySounds.SHIMMERER_HURT;
	}

	@Override
	public SoundEvent getDeathSound() {
		return JourneySounds.SHIMMERER_DEATH;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		
	}

	@Override
	public Item getDropItem() {
		return null;
	}
}