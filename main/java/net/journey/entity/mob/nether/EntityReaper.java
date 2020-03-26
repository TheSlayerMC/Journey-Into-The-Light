package net.journey.entity.mob.nether;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.journey.util.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityReaper extends EntityModMob {

	protected int animID = 1;
	protected int animTick = 1;
	
	public EntityReaper(World par1World) {
		super(par1World);
		addAttackingAI();
		this.isImmuneToFire = true;
		setSize(0.7F, 2.0F);
	}

	@Override
	public boolean attackEntityAsMob(Entity e) {
		boolean attacked = super.attackEntityAsMob(e);
		if(attacked) {
			if(e instanceof EntityLivingBase) 
				((EntityLivingBase)e).addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.blindness, 100, 2)));
		}
		return attacked;
	}
	
	@Override
	public double setMovementSpeed() {
		return 0.3F;
	}
	
	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.ReaperDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.ReaperHealth;
	}

	@Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_WITHER_AMBIENT;
    }

	@Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_WITHER_SKELETON_HURT;
    }
	
	@Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_WITHER_SKELETON_DEATH;
    }
	
	@Override
	public Item getItemDropped() {
		return JourneyItems.withicDust;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(5) == 0) dropItem(JourneyItems.withicDust, rand.nextInt(3));
		if(rand.nextInt(15) == 0) dropItem(JourneyItems.lostSoul, rand.nextInt(2));
		super.dropFewItems(b, j);
	}

	@Override
	public SoundEvent setLivingSound() {
		return JourneySounds.EMPTY;
	}

	@Override
	public SoundEvent setHurtSound() {
		return JourneySounds.EMPTY;
	}

	@Override
	public SoundEvent setDeathSound() {
		return JourneySounds.EMPTY;
	}
}