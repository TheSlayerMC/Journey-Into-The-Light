package net.journey.entity.mob.euca;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.journey.util.PotionEffects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityDynaster extends EntityModMob{

	
	public EntityDynaster(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(1.0F, 1.7F);
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.DynasterDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.DynasterHealth;
	}

	@Override
	public SoundEvent setLivingSound() {
		return JourneySounds.DYNASTER;
	}

	@Override
	public SoundEvent setHurtSound() {
		return JourneySounds.DYNASTER_HURT;
	}

	@Override
	public SoundEvent setDeathSound() {
		return JourneySounds.DYNASTER_DEATH;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource e, float a) {
		if(e.getImmediateSource() instanceof EntityPlayer)
			((EntityPlayer)e.getImmediateSource()).addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.blindness, 60, 10)));
		if(e.getImmediateSource() instanceof EntityPlayer)
			((EntityPlayer)e.getImmediateSource()).addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSlow, 60, 10)));
		return super.attackEntityFrom(e, a);
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(54) == 0) dropItem(JourneyItems.royalDisk, 1);
		if(rand.nextInt(5) == 0) dropItem(JourneyItems.shimmerdust, 2);
		if(rand.nextInt(10) == 0) dropItem(JourneyItems.shimmerdust, 4);
		super.dropFewItems(b, j);
	}

	@Override
	public Item getItemDropped() {
		return null;
	}
}