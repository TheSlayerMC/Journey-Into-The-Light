package net.journey.entity.mob.euca;

import net.journey.JourneyConsumables;
import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.JourneyWeapons;
import net.journey.entity.MobStats;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityPeacefullUntillAttacked;

public class EntityGoldwing extends EntityPeacefullUntillAttacked {

	public EntityGoldwing(World w) {
		super(w);
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.highJourneyDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.eucaHealth;
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
	public Item getItemDropped() {
		return JourneyConsumables.rocMeat;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(2) == 0) dropItem(JourneyItems.rocFeather, 4);
		if(rand.nextInt(1) == 0) dropItem(JourneyItems.rocFeather, 2);
		if(rand.nextInt(4) == 0) dropItem(JourneyConsumables.rocMeat, 1);
		if(rand.nextInt(6) == 0) dropItem(JourneyConsumables.rocMeat, 2);
		super.dropFewItems(b, j);
	}

}