package net.journey.entity.mob.depths;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityPeacefullUntillAttacked;

public class EntityRoc extends EntityPeacefullUntillAttacked {
	
	public EntityRoc(World w) {
		super(w);
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.mediumJourneyDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.depthsHealth;
	}

	@Override
	public SoundEvent getAmbientSound() {
		return JourneySounds.BIRD;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource sourceIn) {
		return JourneySounds.BIRD_HURT;
	}

	@Override
	public SoundEvent getDeathSound() {
		return JourneySounds.BIRD_DEATH;
	}

	@Override
	public Item getDropItem() {
		return JourneyItems.rocMeat;
	}
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(2) == 0) dropItem(JourneyItems.rocFeather, 4);
		if(rand.nextInt(1) == 0) dropItem(JourneyItems.rocFeather, 2);
		if(rand.nextInt(4) == 0) dropItem(JourneyItems.rocMeat, 1);
		if(rand.nextInt(6) == 0) dropItem(JourneyItems.rocMeat, 2);
		super.dropFewItems(b, j);
	}
}