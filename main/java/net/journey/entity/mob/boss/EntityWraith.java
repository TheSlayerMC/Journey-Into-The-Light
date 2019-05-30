package net.journey.entity.mob.boss;

import net.journey.entity.MobStats;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityEssenceBoss;

public class EntityWraith extends EntityEssenceBoss {

	public EntityWraith(World par1World) {
		super(par1World);
		addAttackingAI();
	}
	
	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.wraithDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.wraithHealth;
	}

	@Override
	public SoundEvent getAmbientSound() {
		return null;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource sourceIn) {
		return null;
	}

	@Override
	public SoundEvent getDeathSound() {
		return null;
	}

	@Override
	public Item getDropItem() {
		return null;
	}
}