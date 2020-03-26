package net.journey.entity.mob.cloudia;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityPeacefullUntillAttacked;

public class EntityStarlightTransporter extends EntityPeacefullUntillAttacked {

	public EntityStarlightTransporter(World w) {
		super(w);
		setSize(1.5F, 1.7F);
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.StarlightTransporterDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.StarlightTransporterHealth;
	}

	@Override
	public SoundEvent setLivingSound() {
		return JourneySounds.BUSH;
	}

	@Override
	public SoundEvent setHurtSound() {
		return JourneySounds.BUSH_HURT;
	}

	@Override
	public SoundEvent setDeathSound() {
		return JourneySounds.BUSH_DEATH;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(25) == 0) dropItem(JourneyItems.cloudiaOrb, 1);
		super.dropFewItems(b, j);
		
	}

	@Override
	public Item getItemDropped() {
		return JourneyItems.cloudiaOrb;
	}

}