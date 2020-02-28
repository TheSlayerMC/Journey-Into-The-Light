package net.journey.entity.mob.frozen;

import net.journey.JourneyConsumables;
import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityPeacefullUntillAttacked;

public class EntityShiverwing extends EntityPeacefullUntillAttacked {

	public EntityShiverwing(World w) {
		super(w);
		setSize(1.0F, 1.0F);
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.ShiverwingDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.ShiverwingHealth;
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
	
	@Override
	public boolean getCanSpawnHere() {
		return this.posY < 60.0D && super.getCanSpawnHere();
	}
}