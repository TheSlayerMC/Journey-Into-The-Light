package net.journey.entity.mob.euca;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntitySilverbot extends EntityModMob {

	public EntitySilverbot(World par1World) {
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
	public SoundEvent setLivingSound() {
		return JourneySounds.ROBOT;
	}

	@Override
	public SoundEvent setHurtSound() {
		return JourneySounds.ROBOT_HURT;
	}

	@Override
	public SoundEvent setDeathSound() {
		return JourneySounds.ROBOT_DEATH;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(32) == 0) dropItem(JourneyItems.silverClump, 1);
		super.dropFewItems(b, j);
		if(rand.nextInt(37) == 0) dropItem(JourneyItems.silverClump, 4);
		super.dropFewItems(b, j);
		if(rand.nextInt(60) == 0) dropItem(JourneyItems.gateKeys, 4);
		super.dropFewItems(b, j);
		if(rand.nextInt(60) == 0) dropItem(JourneyItems.metalDisk, 1);
		super.dropFewItems(b, j);
	}

	@Override
	public Item getItemDropped() {
		return null;
	}
}