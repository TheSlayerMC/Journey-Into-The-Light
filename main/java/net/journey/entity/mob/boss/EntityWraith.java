package net.journey.entity.mob.boss;

import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.item.Item;
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
	public EnumSounds setLivingSound() {
		return EnumSounds.EMPTY;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.EMPTY;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.EMPTY;
	}

	@Override
	public Item getItemDropped() {
		return null;
	}
}