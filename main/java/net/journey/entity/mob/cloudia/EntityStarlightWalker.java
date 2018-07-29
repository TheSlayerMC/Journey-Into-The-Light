package net.journey.entity.mob.cloudia;

import net.journey.JourneyItems;
import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;
import net.slayer.api.entity.EntityPeacefullUntillAttacked;

public class EntityStarlightWalker extends EntityModMob {

	public EntityStarlightWalker(World w) {
		super(w);
	}
	
	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.baseJourneyDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.cloudiaHealth;
	}

	@Override
	public EnumSounds setLivingSound() {
		return EnumSounds.EMPTY;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.IRON_GOLEM_HURT;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.IRON_GOLEM_DEATH;
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