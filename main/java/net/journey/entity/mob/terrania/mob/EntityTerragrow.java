package net.journey.entity.mob.terrania.mob;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityTerragrow extends EntityModMob {

	public EntityTerragrow(World w) {
		super(w);
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.TerragrowDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.TerragrowHealth;
	}

	@Override
	public SoundEvent setLivingSound() {
		return JourneySounds.WRAITH;
	}

	@Override
	public SoundEvent setHurtSound() {
		return SoundEvents.ENTITY_CREEPER_HURT;
	}

	@Override
	public SoundEvent setDeathSound() {
		return JourneySounds.WRAITH_DEATH;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(15) == 0) dropItem(JourneyItems.earthenCrystal, 1);
		if(rand.nextInt(4) == 0) dropItem(JourneyItems.darkTerrarianSoil, 4);
		if(rand.nextInt(2) == 0) dropItem(JourneyItems.darkTerrarianSoil, 2);
		super.dropFewItems(b, j);
	}

	@Override
	public Item getItemDropped() {
		return null;
	}

}