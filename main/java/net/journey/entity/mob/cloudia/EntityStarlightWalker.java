package net.journey.entity.mob.cloudia;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityStarlightWalker extends EntityModMob {

	public EntityStarlightWalker(World w) {
		super(w);
		addAttackingAI();
	}
	
	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.veryHardJourneyDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.cloudiaHealth;
	}

	@Override
	public SoundEvent setLivingSound() {
		return JourneySounds.EMPTY;
	}

	@Override
	public SoundEvent setHurtSound() {
		return SoundEvents.ENTITY_IRONGOLEM_HURT;
	}

	@Override
	public SoundEvent setDeathSound() {
		return SoundEvents.ENTITY_IRONGOLEM_DEATH;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(30) == 0) dropItem(JourneyItems.cloudiaOrb, 1);
		super.dropFewItems(b, j);
		
	}

	@Override
	public Item getItemDropped() {
		return null;
	}

}