package net.journey.entity.mob.euca;

import net.journey.JourneyConsumables;
import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityPsyollom extends EntityModMob {

	public static final int ENTITY_TYPE = 25;
	
	public EntityPsyollom(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(1.7F, 2.7F);
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
		return JourneySounds.PSYOLLOM;
	}

	@Override
	public SoundEvent setHurtSound() {
		return JourneySounds.PSYOLLOM_HURT;
	}

	@Override
	public SoundEvent setDeathSound() {
		return JourneySounds.PSYOLLOM_HURT;
	}
	
	@Override
	public Item getItemDropped() {
		return JourneyConsumables.eucaMeat;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(70) == 0) dropItem(JourneyItems.eucaTablet, 1);
		super.dropFewItems(b, j);
	}
}