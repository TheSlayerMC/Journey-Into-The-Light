package net.journey.entity.mob.cloudia;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityPeacefullUntillAttacked;

public class EntityStarlightTransporter extends EntityPeacefullUntillAttacked {

	public EntityStarlightTransporter(World w) {
		super(w);
		this.setSize((float) 1.5, 2);
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
		return JourneySounds.BUSH;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource sourceIn) {
		return JourneySounds.BUSH_HURT;
	}

	@Override
	public SoundEvent getDeathSound() {
		return JourneySounds.BUSH_DEATH;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(30) == 0) dropItem(JourneyItems.cloudiaOrb, 1);
		super.dropFewItems(b, j);
		
	}

	@Override
	public Item getDropItem() {
		return JourneyItems.cloudiaOrb;
	}

}