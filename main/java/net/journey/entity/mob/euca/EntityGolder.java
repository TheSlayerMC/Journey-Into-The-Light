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

public class EntityGolder extends EntityModMob{

	
	public EntityGolder(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(1.2F, 1.7F);
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.GolderDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.GolderHealth;
	}

	@Override
	public SoundEvent setLivingSound() {
		return JourneySounds.REAPER;
	}

	@Override
	public SoundEvent setHurtSound() {
		return JourneySounds.REAPER_HURT;
	}

	@Override
	public SoundEvent setDeathSound() {
		return JourneySounds.REAPER_HURT;
	}
	
	@Override
	public Item getItemDropped() {
		return null;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(5) == 0) dropItem(JourneyItems.golderDust, rand.nextInt(3));
		if(rand.nextInt(35) == 0) dropItem(JourneyItems.silverClump, rand.nextInt(3));
		super.dropFewItems(b, j);
	}
}