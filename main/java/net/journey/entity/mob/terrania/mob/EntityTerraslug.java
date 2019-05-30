package net.journey.entity.mob.terrania.mob;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityTerraslug extends EntityModMob {

	public EntityTerraslug(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(0.1F, 0.1F);
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.baseJourneyDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.terraniaHealth;
	}
	
	@Override
	public SoundEvent getAmbientSound() {
		return JourneySounds.TERRA_SLUG;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource sourceIn) {
		return JourneySounds.TERRA_SLUG_HURT;
	}

	@Override
	public SoundEvent getDeathSound() {
		return JourneySounds.TERRA_SLUG_DEATH;
	}

	@Override
	public Item getDropItem() {
		return JourneyItems.slugSlime;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(1) == 0) dropItem(JourneyItems.slugSlime, 1);
		if(rand.nextInt(3) == 0) dropItem(JourneyItems.slugSlime, 2);
		
	}
}