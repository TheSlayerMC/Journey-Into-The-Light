package net.journey.entity.mob.terrania.mob;

import net.journey.JourneyItems;
import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.item.Item;
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
	public EnumSounds setLivingSound() {
		return EnumSounds.SLUG;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.SLUG_HURT;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.SLUG_DEATH;
	}

	@Override
	public Item getItemDropped() {
		return JourneyItems.slugSlime;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(1) == 0) dropItem(JourneyItems.slugSlime, 1);
		if(rand.nextInt(3) == 0) dropItem(JourneyItems.slugSlime, 2);
		
	}
}