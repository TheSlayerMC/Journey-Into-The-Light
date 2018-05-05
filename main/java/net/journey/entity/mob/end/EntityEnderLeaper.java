package net.journey.entity.mob.end;

import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityEnderLeaper extends EntityModMob {

	public EntityEnderLeaper(World par1World) {
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
	public EnumSounds setLivingSound() {
		return EnumSounds.SHIMMERER;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.SHIMMERER_HURT;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.SHIMMERER_DEATH;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		
	}

	@Override
	public Item getItemDropped() {
		return null;
	}
}